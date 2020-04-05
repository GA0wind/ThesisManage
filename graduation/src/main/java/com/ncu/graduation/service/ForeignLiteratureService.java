package com.ncu.graduation.service;

import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmDocumentError;
import com.ncu.graduation.mapper.ForeignLiteratureMapper;
import com.ncu.graduation.mapper.ForeignRecordMapper;
import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.ForeignLiteratureExample;
import com.ncu.graduation.model.ForeignRecord;
import com.ncu.graduation.model.ForeignRecordExample;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 17:27
 * @description：外文资料service
 * @modified By：
 * @version: 0.0.1
 */

@Service
public class ForeignLiteratureService {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private ForeignLiteratureMapper foreignLiteratureMapper;

  @Autowired
  private ForeignRecordMapper foreignRecordMapper;


  /**
   * 获取我的课题的外文信息
   */
  public List<TeaProjectDocumentVO<ForeignLiterature>> getTeaForeignLiterature(UserVO user) {
    //获取我的课题
    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k, l) -> {
      pnos.add(k);
    });
    //查询这些课题的外文资料数据
    ForeignLiteratureExample example = new ForeignLiteratureExample();
    example.createCriteria().andPnoIn(pnos);
    List<ForeignLiterature> foreignLiteratures = foreignLiteratureMapper.selectByExample(
        example);
    Map<String, ForeignLiterature> foreignLiteratureMap = new HashMap<>();
    foreignLiteratures.forEach((k) -> {
      foreignLiteratureMap.put(k.getPno(), k);
    });

    List<TeaProjectDocumentVO<ForeignLiterature>> teaProjectDocumentVOS = new ArrayList<>();

    teaProject.forEach((k, l) -> {
      TeaProjectDocumentVO<ForeignLiterature> teaProjectDocumentVO = new TeaProjectDocumentVO<>();
      teaProjectDocumentVO.setPno(k);
      teaProjectDocumentVO.setPname(l.getPname());
      teaProjectDocumentVO.setSno(l.getSno());
      teaProjectDocumentVO.setSname(l.getSname());
      teaProjectDocumentVO.setDocument(foreignLiteratureMap.get(k));
      teaProjectDocumentVOS.add(teaProjectDocumentVO);
    });
    return teaProjectDocumentVOS;
  }


  /**
   * 指导老师审核外文资料
   */
  public void verifyForeignLiterature(VerifyDocumentDTO verifyDocumentDTO) {
    ForeignLiterature foreignLiterature = new ForeignLiterature();
    foreignLiterature.setFno(verifyDocumentDTO.getDno());
    foreignLiterature.setGmtModified(new Date());
    //外文资料没有盲审
    foreignLiterature.setIsPass(verifyDocumentDTO.getIsPass());
    foreignLiterature.setTrialGrade(verifyDocumentDTO.getScore());
    foreignLiterature.setTrialComment(verifyDocumentDTO.getComment());
    //插入数据
    ForeignLiteratureExample foreignLiteratureExample = new ForeignLiteratureExample();
    foreignLiteratureExample.createCriteria().andFnoEqualTo(verifyDocumentDTO.getDno());
    int result = foreignLiteratureMapper
        .updateByExampleSelective(foreignLiterature, foreignLiteratureExample);
    if (result != 1) {
      throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
    }

    //插入评审记录
    ForeignRecord record = new ForeignRecord();
    if (verifyDocumentDTO.getIsBlind() == 0) {
      record.setFno(verifyDocumentDTO.getDno());
      record.setTrialGrade(verifyDocumentDTO.getScore());
      record.setTrialComment(verifyDocumentDTO.getComment());
      record.setForeignFile(verifyDocumentDTO.getFilePath());
      record.setTranslationFile(verifyDocumentDTO.getTranslationFile());
      record.setGmtCreate(foreignLiterature.getGmtModified());
    }
    result = foreignRecordMapper.insert(record);
    if (result != 1) {
      throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
    }

  }

  /**
   * 提交外文资料
   */
  public void submitForeignLiterature(UserVO user, String oldFile,
      MultipartFile foreignFile,
      MultipartFile translationFile, String pno) {

    String foreignFilePath = FileSave.fileSave(foreignFile, FileTypeEnum.FOREIGNFILE);
    String translationFilePath = FileSave.fileSave(translationFile, FileTypeEnum.TRANSLATIONFILE);
    ForeignLiterature foreignLiterature = new ForeignLiterature();
    foreignLiterature.setPno(pno);
    foreignLiterature.setForeignFile(foreignFilePath);
    foreignLiterature.setTranslationFile(translationFilePath);
    foreignLiterature.setIsPass((byte)2);
    foreignLiterature.setSchoolYear(user.getSchoolYear());
    if (StringUtils.isBlank(oldFile)){
      foreignLiterature.setFno(UUID.randomUUID().toString().replaceAll("-", ""));
      foreignLiterature.setGmtCreate(new Date());
      foreignLiterature.setGmtModified(foreignLiterature.getGmtCreate());
      int result = foreignLiteratureMapper.insertSelective(foreignLiterature);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    }else{
      foreignLiterature.setGmtModified(new Date());
      ForeignLiteratureExample example = new ForeignLiteratureExample();
      example.createCriteria().andPnoEqualTo(pno);
      int result = foreignLiteratureMapper.updateByExampleSelective(foreignLiterature,
          example);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    }


  }

  /**
   * 学生查看外文资料和审核
   */
  public StuProjectDocumentVO<ForeignLiterature> getStuForeignLiterature(UserVO user, ProjectApply projectApply) {
    ForeignLiteratureExample example = new ForeignLiteratureExample();
    example.createCriteria().andPnoEqualTo(projectApply.getPno());
    List<ForeignLiterature> foreignLiteratures = foreignLiteratureMapper.selectByExample(example);

    StuProjectDocumentVO<ForeignLiterature> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    if (foreignLiteratures != null && !foreignLiteratures.isEmpty()){
      stuProjectDocumentVOS.setDocument(foreignLiteratures.get(0));
    }
    stuProjectDocumentVOS.setProject(projectApply);
    return stuProjectDocumentVOS;
  }

  /**
   * 获取外文资料审核记录
   * @param fno
   * @return
   */
  public List<ForeignRecord> getForeignRecord(String fno) {
    ForeignRecordExample example = new ForeignRecordExample();
    example.createCriteria().andFnoEqualTo(fno);
    example.setOrderByClause("gmt_create desc");
    List<ForeignRecord> records = foreignRecordMapper.selectByExample(example);
    return records;
  }

}

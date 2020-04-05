package com.ncu.graduation.service;

import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmDocumentError;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.ThesisMapper;
import com.ncu.graduation.mapper.ThesisRecordMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import com.ncu.graduation.model.ThesisRecord;
import com.ncu.graduation.model.ThesisRecordExample;
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
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 21:01
 * @description：论文Service
 * @modified By：
 * @version:0.0.1
 */

@Service
public class ThesisService {

  @Autowired
  private ProjectService projectService;
  @Autowired
  private ThesisMapper thesisMapper;
  @Autowired
  private ThesisRecordMapper thesisRecordMapper;
  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;

  public List<TeaProjectDocumentVO<Thesis>> getTeaThesis(UserVO user) {
    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k, l) -> {
      pnos.add(k);
    });
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoIn(pnos);
    List<Thesis> thesisList = thesisMapper.selectByExample(thesisExample);
    Map<String, Thesis> thesisMap = new HashMap<>();
    thesisList.forEach((k) -> {
      thesisMap.put(k.getPno(), k);
    });

    List<TeaProjectDocumentVO<Thesis>> teaProjectDocumentVOS = new ArrayList<>();

    teaProject.forEach((k, l) -> {
      TeaProjectDocumentVO<Thesis> teaProjectDocumentVO = new TeaProjectDocumentVO<>();
      teaProjectDocumentVO.setPno(k);
      teaProjectDocumentVO.setPname(l.getPname());
      teaProjectDocumentVO.setSno(l.getSno());
      teaProjectDocumentVO.setSname(l.getSname());
      teaProjectDocumentVO.setDocument(thesisMap.get(k));
      teaProjectDocumentVOS.add(teaProjectDocumentVO);
    });
    return teaProjectDocumentVOS;
  }


  /**
   * 获取需要我盲审的论文
   */
  public List<TeaProjectDocumentVO<Thesis>> getTeaBlindThesis(UserVO user) {
//获取盲审文档
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear())
        .andBlindTrialNoEqualTo(user.getAccountNo());
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    //获取课题名
    List<String> pnos = new ArrayList<>();
    theses.forEach((k) -> {
      pnos.add(k.getPno());
    });
    ProjectSelectResultExample example = new ProjectSelectResultExample();
    example.createCriteria().andPnoIn(pnos);
    List<ProjectSelectResult> projectSelectResults = projectSelectResultMapper.selectByExample(example);
    //形成映射, 方便查找
    Map<String, ProjectSelectResult> projectMap = new HashMap<>();
    projectSelectResults.forEach((k) -> {
      projectMap.put(k.getPno(), k);
    });
    //填充数据
    List<TeaProjectDocumentVO<Thesis>> teaProjectDocumentVOS = new ArrayList<>();
    theses.forEach((k) -> {
      TeaProjectDocumentVO<Thesis> teaProjectDocumentVO = new TeaProjectDocumentVO();
      teaProjectDocumentVO.setPno(k.getPno());
      teaProjectDocumentVO.setPname(projectMap.get(k.getPno()).getPname());
      teaProjectDocumentVO.setDocument(k);
      teaProjectDocumentVOS.add(teaProjectDocumentVO);
    });
    return teaProjectDocumentVOS;
  }


  /**
   * 审核论文
   */
  public void verifyThesis(VerifyDocumentDTO verifyDocumentDTO) {
    Thesis thesis = new Thesis();
    thesis.setThesisNo(verifyDocumentDTO.getDno());
    thesis.setGmtModified(new Date());
    //盲审
    if (verifyDocumentDTO.getIsBlind() == 1) {
      thesis.setBlindTrialGrade(verifyDocumentDTO.getScore());
      thesis.setBlindTrialComment(verifyDocumentDTO.getComment());
    }
    //普通审核
    if (verifyDocumentDTO.getIsBlind() == 0) {
      thesis.setIsPass(verifyDocumentDTO.getIsPass());
      thesis.setTrialGrade(verifyDocumentDTO.getScore());
      thesis.setTrialComment(verifyDocumentDTO.getComment());
    }
    //插入数据
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andThesisNoEqualTo(verifyDocumentDTO.getDno());
    int result = thesisMapper.updateByExampleSelective(thesis, thesisExample);
    if (result != 1) {
      throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
    }

    //插入评审记录
    ThesisRecord record = new ThesisRecord();
    if (verifyDocumentDTO.getIsBlind() == 0) {
      record.setThesisNo(verifyDocumentDTO.getDno());
      record.setTrialGrade(verifyDocumentDTO.getScore());
      record.setFilePath(verifyDocumentDTO.getFilePath());
      record.setTrialComment(verifyDocumentDTO.getComment());
      record.setGmtCreate(thesis.getGmtModified());
    }
    result = thesisRecordMapper.insert(record);
    if (result != 1) {
      throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
    }

  }

  /**
   * 提交论文
   */
  public void submitThesis(UserVO user, String oldFile, MultipartFile file,
      String pno) {
    String filePath = FileSave.fileSave(file, FileTypeEnum.THESIS);
    Thesis thesis = new Thesis();
    thesis.setPno(pno);
    thesis.setFilePath(filePath);
    thesis.setIsPass((byte)2);
    thesis.setSchoolYear(user.getSchoolYear());
    if (StringUtils.isBlank(oldFile)){
      thesis.setThesisNo(UUID.randomUUID().toString().replaceAll("-", ""));
      thesis.setGmtCreate(new Date());
      thesis.setGmtModified(thesis.getGmtCreate());
      int result = thesisMapper.insertSelective(thesis);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    }else{
      thesis.setGmtModified(new Date());
      ThesisExample example = new ThesisExample();
      example.createCriteria().andPnoEqualTo(pno);
      int result = thesisMapper.updateByExampleSelective(thesis, example);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    }


  }

  /**
   * 学生查看论文和审核
   */
  public StuProjectDocumentVO<Thesis> getStuThesis(UserVO user,
      ProjectApply project) {
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoEqualTo(project.getPno());
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    StuProjectDocumentVO<Thesis> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    if (theses != null && !theses.isEmpty()) {
      stuProjectDocumentVOS.setDocument(theses.get(0));
    }
    stuProjectDocumentVOS.setProject(project);
    return stuProjectDocumentVOS;
  }

  /**
   * 获取审核记录
   */
  public List<ThesisRecord> getThesisRecord(String thesisNo) {
    ThesisRecordExample example = new ThesisRecordExample();
    example.createCriteria().andThesisNoEqualTo(thesisNo);
    example.setOrderByClause("gmt_create desc");
    List<ThesisRecord> records = thesisRecordMapper.selectByExample(example);
    return records;
  }

  public List<Thesis> getGroupThesis(List<ProjectSelectResult> groupStus) {
    List<String> pnos = new ArrayList<>();
    groupStus.forEach((k)->{
      pnos.add(k.getPno());
    });
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoIn(pnos);
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    return theses;
  }
}

package com.ncu.graduation.service.impl;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.ThesisMapper;
import com.ncu.graduation.mapper.ThesisRecordMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import com.ncu.graduation.model.ThesisRecord;
import com.ncu.graduation.model.ThesisRecordExample;
import com.ncu.graduation.service.ThesisService;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 21:01
 * @description：论文Service
 * @modified By：
 * @version:0.0.1
 */

@Service
public class ThesisServiceImpl implements ThesisService {

  @Autowired
  private ProjectServiceImpl projectService;
  @Autowired
  private ThesisMapper thesisMapper;
  @Autowired
  private ThesisRecordMapper thesisRecordMapper;
  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;

  @Autowired
  private ProjectApplyMapper projectApplyMapper;

  /**
   * 获取老师名下的课题论文
   */
  @Override
  public List<TeaProjectDocumentVO<Thesis>> getTeaThesis(
      Map<String, ProjectSelectResultBO> teaProject) {
    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k, l) -> pnos.add(k));
    if (pnos.isEmpty()) {
      return new ArrayList<>();
    }
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoIn(pnos);
    List<Thesis> thesisList = thesisMapper.selectByExample(thesisExample);
    Map<String, Thesis> thesisMap = new HashMap<>();
    thesisList.forEach(k -> thesisMap.put(k.getPno(), k));

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
  @Override
  public List<TeaProjectDocumentVO<Thesis>> getTeaBlindThesis(UserVO user) {
    //获取盲审文档
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andBlindTrialNoEqualTo(user.getAccountNo());
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    //获取课题名
    List<String> pnos = new ArrayList<>();
    theses.forEach(k -> pnos.add(k.getPno()));
    if (pnos.isEmpty()) {
      return new ArrayList<>();
    }
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoIn(pnos).andSchoolYearEqualTo(user.getSchoolYear());
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExample(projectApplyExample);
    //形成映射, 方便查找
    Map<String, ProjectApply> projectMap = new HashMap<>();
    projectApplies.forEach(k -> projectMap.put(k.getPno(), k));
    //填充数据
    List<TeaProjectDocumentVO<Thesis>> teaProjectDocumentVOS = new ArrayList<>();
    theses.forEach((k) -> {
      TeaProjectDocumentVO<Thesis> teaProjectDocumentVO = new TeaProjectDocumentVO<>();
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
  @Transactional
  @Override
  public void verifyThesis(VerifyDocumentDTO verifyDocumentDTO, UserVO user) {

    ThesisExample thesisExample = new ThesisExample();
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);

    Thesis thesis = theses.get(0);
    thesis.setPno(verifyDocumentDTO.getPno());
    thesis.setGmtModified(new Date());
     thesisExample.clear();
    //盲审
    if (verifyDocumentDTO.getIsBlind() == 1) {
      thesis.setBlindTrialGrade(verifyDocumentDTO.getScore());
      thesis.setBlindTrialComment(verifyDocumentDTO.getComment());
      thesisExample.createCriteria().andPnoEqualTo(verifyDocumentDTO.getPno())
          .andBlindTrialNoEqualTo(user.getAccountNo());
    }
    //普通审核
    if (verifyDocumentDTO.getIsBlind() == 0) {
      thesis.setIsPass(verifyDocumentDTO.getIsPass());
      if (thesis.getIsPass() == 1) {
        thesis.setModifiable((byte) 0);
      }
      Map<String, ProjectSelectResultBO> teaProject = projectService.getTeaProject(user);
      if (!teaProject.containsKey(verifyDocumentDTO.getPno())){
        throw new CommonException(EmProjectError.USER_NOT_HAVE_THE_PROJECT);
      }
      thesis.setTrialGrade(verifyDocumentDTO.getScore());
      thesis.setTrialComment(verifyDocumentDTO.getComment());
      thesisExample.createCriteria().andPnoEqualTo(verifyDocumentDTO.getPno());
    }
    //插入数据

    int result = thesisMapper.updateByExampleSelective(thesis, thesisExample);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "审核失败, 请重试");
    }

    //插入评审记录
    ThesisRecord record = new ThesisRecord();
    if (verifyDocumentDTO.getIsBlind() == 0) {
      record.setPno(thesis.getPno());
      record.setIsPass(thesis.getIsPass());
      record.setTrialGrade(thesis.getTrialGrade());
      record.setFilePath(thesis.getFilePath());
      record.setTrialComment(thesis.getTrialComment());
      record.setGmtCreate(thesis.getGmtModified());
    }
    result = thesisRecordMapper.insert(record);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "审核失败, 请重试");
    }

  }

  /**
   * 提交论文
   */
  @Override
  public void submitThesis(UserVO user, MultipartFile file,
      String id, String pno) {
    String filePath = FileSave.fileSave(file, FileTypeEnum.THESIS);
    Thesis thesis = new Thesis();
    thesis.setPno(pno);
    thesis.setFilePath(filePath);
    thesis.setIsPass((byte) 2);
    if (StringUtils.isBlank(id)) {
      thesis.setGmtCreate(new Date());
      thesis.setGmtModified(thesis.getGmtCreate());
      int result = thesisMapper.insertSelective(thesis);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR);
      }
    } else {
      thesis.setId(Long.parseLong(id));
      thesis.setGmtModified(new Date());
      ThesisExample example = new ThesisExample();
      example.createCriteria().andPnoEqualTo(pno);
      int result = thesisMapper.updateByExampleSelective(thesis, example);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR);
      }
    }


  }

  /**
   * 学生查看论文和审核
   */
  @Override
  public StuProjectDocumentVO<Thesis> getStuThesis(UserVO user,
      ProjectInfoVO project) {
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoEqualTo(project.getProjectApply().getPno());
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    StuProjectDocumentVO<Thesis> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    if (theses != null && !theses.isEmpty()) {
      stuProjectDocumentVOS.setDocument(theses.get(0));
    }
    stuProjectDocumentVOS.setProject(project.getProjectApply());
    stuProjectDocumentVOS.setProjectSelect(project.getProjectSelectResult());
    return stuProjectDocumentVOS;
  }

  /**
   * 获取审核记录
   */
  @Override
  public List<ThesisRecord> getThesisRecord(String pno) {
    ThesisRecordExample example = new ThesisRecordExample();
    example.createCriteria().andPnoEqualTo(pno);
    example.setOrderByClause("gmt_create desc");
    return thesisRecordMapper.selectByExample(example);
  }

  /**
   * 获取小组论文
   * @param pnos
   * @return
   */
  @Override
  public List<Thesis> getGroupThesis(List<String> pnos) {
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoIn(pnos);
    return thesisMapper.selectByExample(thesisExample);
  }

  /**
   * 设置论文可修改
   * @param pno
   */
  @Override
  public void setThesisModifiable(String pno) {
    Thesis thesis = new Thesis();
    thesis.setPno(pno);
    thesis.setModifiable((byte) 1);

    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoEqualTo(pno);
    int result = thesisMapper.updateByExampleSelective(thesis, thesisExample);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR);
    }
  }
}

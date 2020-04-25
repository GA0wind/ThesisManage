package com.ncu.graduation.service;

import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.OpenReportMapper;
import com.ncu.graduation.mapper.OpenReportRecordMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.model.OpenReportRecord;
import com.ncu.graduation.model.OpenReportRecordExample;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportExample;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 23:20
 * @description：开题报告Service
 * @modified By：
 * @version: 0.0.1
 */
@Service
public class OpenReportService {

  @Autowired
  private ProjectService projectService;

  @Autowired
  private OpenReportMapper openReportMapper;

  @Autowired
  private OpenReportRecordMapper openReportRecordMapper;

  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;

  /**
   * 获取教师的课题的开题报告
   */
  public List<TeaProjectDocumentVO<OpenReport>> getTeaOpenReport(
      Map<String, ProjectSelectResult> teaProject) {
    //获取有效课题
    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k, l) -> pnos.add(k));
    //获取开题报告
    if (pnos.isEmpty()) {
      return new ArrayList<>();
    }
    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andPnoIn(pnos);
    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    //组装数据
    Map<String, OpenReport> openReportMap = new HashMap<>(16);
    openReports.forEach((k) -> openReportMap.put(k.getPno(), k));
    List<TeaProjectDocumentVO<OpenReport>> teaProjectDocumentVOS = new ArrayList<>();
    teaProject.forEach((k, l) -> {
      TeaProjectDocumentVO<OpenReport> teaProjectDocumentVO = new TeaProjectDocumentVO<>();
      teaProjectDocumentVO.setPno(k);
      teaProjectDocumentVO.setPname(l.getPname());
      teaProjectDocumentVO.setSno(l.getSno());
      teaProjectDocumentVO.setSname(l.getSname());
      teaProjectDocumentVO.setDocument(openReportMap.get(k));
      teaProjectDocumentVOS.add(teaProjectDocumentVO);
    });
    return teaProjectDocumentVOS;
  }

  /**
   * 获取需要教师盲审的开题报告
   */
  public List<TeaProjectDocumentVO<OpenReport>> getTeaBlindOpenReport(UserVO user) {
//获取盲审文档
    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear())
        .andBlindTrialNoEqualTo(user.getAccountNo());
    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    //获取课题名
    List<String> pnos = new ArrayList<>();
    openReports.forEach((k) -> pnos.add(k.getPno()));
    if (pnos.isEmpty()) {
      return new ArrayList<>();
    }
    ProjectSelectResultExample examplexample = new ProjectSelectResultExample();
    examplexample.createCriteria().andPnoIn(pnos);
    List<ProjectSelectResult> projects = projectSelectResultMapper.selectByExample(examplexample);
    //形成映射, 方便查找
    Map<String, ProjectSelectResult> projectMap = new HashMap<>();
    projects.forEach((k) -> projectMap.put(k.getPno(), k));
    //填充数据
    List<TeaProjectDocumentVO<OpenReport>> teaProjectDocumentVOS = new ArrayList<>();
    openReports.forEach((k) -> {
      TeaProjectDocumentVO<OpenReport> teaProjectDocumentVO = new TeaProjectDocumentVO<>();
      teaProjectDocumentVO.setPno(k.getPno());
      teaProjectDocumentVO.setPname(projectMap.get(k.getPno()).getPname());
      teaProjectDocumentVO.setDocument(k);
      teaProjectDocumentVOS.add(teaProjectDocumentVO);
    });
    return teaProjectDocumentVOS;
  }


  /**
   * 审核开题报告
   */
  @Transactional
  public void verifyOpenReport(VerifyDocumentDTO verifyDocumentDTO,
      UserVO user) {
    OpenReport openReport = new OpenReport();
    openReport.setPno(verifyDocumentDTO.getPno());
    openReport.setGmtModified(new Date());
    OpenReportExample openReportExample = new OpenReportExample();
    //盲审
    if (verifyDocumentDTO.getIsBlind() == 1) {
      openReport.setBlindTrialGrade(verifyDocumentDTO.getScore());
      openReport.setBlindTrialComment(verifyDocumentDTO.getComment());
      //判断是否有该课题
      openReportExample.createCriteria().andPnoEqualTo(verifyDocumentDTO.getPno())
          .andBlindTrialNoEqualTo(user.getAccountNo());

    }
    //普通审核
    if (verifyDocumentDTO.getIsBlind() == 0) {
      openReport.setIsPass(verifyDocumentDTO.getIsPass());
      if (openReport.getIsPass() == 1) {
        openReport.setModifiable((byte) 0);
      }
      Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
      if (!teaProject.containsKey(verifyDocumentDTO.getPno())) {
        throw new CommonException(EmProjectError.USER_NOT_HAVE_THE_PROJECT);
      }
      openReport.setTrialGrade(verifyDocumentDTO.getScore());
      openReport.setTrialComment(verifyDocumentDTO.getComment());
      openReportExample.createCriteria().andPnoEqualTo(verifyDocumentDTO.getPno());
    }
    //插入数据

    int result = openReportMapper.updateByExampleSelective(openReport, openReportExample);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "审核失败请重试");
    }

    //插入评审记录
    if (verifyDocumentDTO.getIsBlind() == 0) {
      OpenReportRecord record = new OpenReportRecord();
      record.setPno(verifyDocumentDTO.getPno());
      record.setIsPass(verifyDocumentDTO.getIsPass());
      record.setTrialGrade(verifyDocumentDTO.getScore());
      record.setFilePath(verifyDocumentDTO.getFilePath());
      record.setTrialComment(verifyDocumentDTO.getComment());
      record.setGmtCreate(openReport.getGmtModified());
      result = openReportRecordMapper.insert(record);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR, "审核失败请重试");
      }
    }
  }

  /**
   * 提交开题报告
   */
  public void submitOpenReport(MultipartFile file, String id, UserVO user, String pno) {
    String filePath = FileSave.fileSave(file, FileTypeEnum.OPENING_REPORT);
    OpenReport openReport = new OpenReport();

    openReport.setPno(pno);
    openReport.setFilePath(filePath);
    openReport.setIsPass((byte) 2);
    openReport.setSchoolYear(user.getSchoolYear());
    if (StringUtils.isBlank(id)) {
      openReport.setGmtCreate(new Date());
      openReport.setGmtModified(openReport.getGmtCreate());
      int result = openReportMapper.insertSelective(openReport);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR, "审核失败请重试");
      }
    } else {
      openReport.setId(Long.parseLong(id));
      openReport.setGmtModified(new Date());
      OpenReportExample openReportExample = new OpenReportExample();
      openReportExample.createCriteria().andPnoEqualTo(pno);
      int result = openReportMapper.updateByExampleSelective(openReport, openReportExample);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR, "审核失败请重试");
      }
    }

  }

  /**
   * 学生查看开题报告和审核
   */
  public StuProjectDocumentVO<OpenReport> getStuOpenReport(ProjectApply project) {

    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andPnoEqualTo(project.getPno());

    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    StuProjectDocumentVO<OpenReport> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    if (openReports != null && !openReports.isEmpty()) {
      stuProjectDocumentVOS.setDocument(openReports.get(0));
    }
    stuProjectDocumentVOS.setProject(project);
    return stuProjectDocumentVOS;
  }

  /**
   * 获取审核记录
   */
  public List<OpenReportRecord> getOpenReportRecord(String pno) {
    OpenReportRecordExample example = new OpenReportRecordExample();
    example.createCriteria().andPnoEqualTo(pno);
    example.setOrderByClause("gmt_create desc");
    List<OpenReportRecord> records = openReportRecordMapper.selectByExample(example);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    records.forEach(k -> {
      try {
        Date date = format.parse(format.format(k.getGmtCreate()));
        k.setGmtCreate(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    });
    return records;
  }

  /**
   * 设置开题报告可修改
   */

  public void setOpenReportModifiable(String pno) {
    OpenReport openReport = new OpenReport();
    openReport.setPno(pno);
    openReport.setModifiable((byte) 1);

    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andPnoEqualTo(pno);
    int result = openReportMapper.updateByExampleSelective(openReport, openReportExample);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR);
    }
  }
}

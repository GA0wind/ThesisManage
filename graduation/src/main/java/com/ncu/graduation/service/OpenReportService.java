package com.ncu.graduation.service;

import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmDocumentError;
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
import com.ncu.graduation.vo.ProjectInfoVO;
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
  public List<TeaProjectDocumentVO<OpenReport>> getTeaOpenReport(UserVO user) {

    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k, l) -> {
      pnos.add(k);
    });
    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andPnoIn(pnos);
    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    Map<String, OpenReport> openReportMap = new HashMap<>();
    openReports.forEach((k) -> {
      openReportMap.put(k.getPno(), k);
    });

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
   * 获取需要我盲审的开题报告
   */
  public List<TeaProjectDocumentVO<OpenReport>> getTeaBlindOpenReport(UserVO user) {
//获取盲审文档
    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear())
        .andBlindTrialNoEqualTo(user.getAccountNo());
    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    //获取课题名
    List<String> pnos = new ArrayList<>();
    openReports.forEach((k) -> {
      pnos.add(k.getPno());
    });
    ProjectSelectResultExample examplexample = new ProjectSelectResultExample();
    examplexample.createCriteria().andPnoIn(pnos);
    List<ProjectSelectResult> projects = projectSelectResultMapper.selectByExample(examplexample);
    //形成映射, 方便查找
    Map<String, ProjectSelectResult> projectMap = new HashMap<>();
    projects.forEach((k) -> {
      projectMap.put(k.getPno(), k);
    });
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
  public void verifyOpenReport(VerifyDocumentDTO verifyDocumentDTO) {
    OpenReport openReport = new OpenReport();
    openReport.setOpenNo(verifyDocumentDTO.getDno());
    openReport.setGmtModified(new Date());
    //盲审
    if (verifyDocumentDTO.getIsBlind() == 1) {
      openReport.setBlindTrialGrade(verifyDocumentDTO.getScore());
      openReport.setBlindTrialComment(verifyDocumentDTO.getComment());
    }
    //普通审核
    if (verifyDocumentDTO.getIsBlind() == 0) {
      openReport.setIsPass(verifyDocumentDTO.getIsPass());
      openReport.setTrialGrade(verifyDocumentDTO.getScore());
      openReport.setTrialComment(verifyDocumentDTO.getComment());
    }
    //插入数据
    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andOpenNoEqualTo(verifyDocumentDTO.getDno());
    int result = openReportMapper.updateByExampleSelective(openReport, openReportExample);
    if (result != 1) {
      throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
    }

    //插入评审记录
    OpenReportRecord record = new OpenReportRecord();
    if (verifyDocumentDTO.getIsBlind() == 0) {
      record.setOpenNo(verifyDocumentDTO.getDno());
      record.setTrialGrade(verifyDocumentDTO.getScore());
      record.setFilePath(verifyDocumentDTO.getFilePath());
      record.setTrialComment(verifyDocumentDTO.getComment());
      record.setGmtCreate(openReport.getGmtModified());
      result = openReportRecordMapper.insert(record);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    }
  }

  /**
   * 提交开题报告
   */
  public void submitOpenReport(String oldFile,
      MultipartFile file, String dno, UserVO user) {
    String filePath = FileSave.fileSave(file, FileTypeEnum.OPENING_REPORT);
    OpenReport openReport = new OpenReport();
    openReport.setPno(dno);
    openReport.setFilePath(filePath);
    openReport.setIsPass((byte) 2);
    openReport.setSchoolYear(user.getSchoolYear());
    if (StringUtils.isBlank(oldFile)) {
      openReport.setOpenNo(UUID.randomUUID().toString().replaceAll("-", ""));
      openReport.setGmtCreate(new Date());
      openReport.setGmtModified(openReport.getGmtCreate());
      int result = openReportMapper.insertSelective(openReport);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    } else {
      openReport.setGmtModified(new Date());
      OpenReportExample openReportExample = new OpenReportExample();
      openReportExample.createCriteria().andPnoEqualTo(dno);
      int result = openReportMapper.updateByExampleSelective(openReport, openReportExample);
      if (result != 1) {
        throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
      }
    }

  }

  /**
   * 学生查看开题报告和审核
   */
  public StuProjectDocumentVO<OpenReport> getStuOpenReport(UserVO user,
      ProjectApply project) {

    OpenReportExample openReportExample = new OpenReportExample();
    openReportExample.createCriteria().andPnoEqualTo(project.getPno());

    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    StuProjectDocumentVO<OpenReport> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    if (openReports!=null && !openReports.isEmpty()){
      stuProjectDocumentVOS.setDocument(openReports.get(0));
    }
    stuProjectDocumentVOS.setProject(project);
    return stuProjectDocumentVOS;
  }

  /**
   * 获取审核记录
   */
  public List<OpenReportRecord> getOpenReportRecord(String openNo) {
    OpenReportRecordExample example = new OpenReportRecordExample();
    example.createCriteria().andOpenNoEqualTo(openNo);
    example.setOrderByClause("gmt_create desc");
    List<OpenReportRecord> records = openReportRecordMapper.selectByExample(example);
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    records.forEach((k)->{
      try {
        Date date = format.parse(format.format(k.getGmtCreate()));
        k.setGmtCreate(date);
      } catch (ParseException e) {
        e.printStackTrace();
      }
    });
    return records;
  }
}

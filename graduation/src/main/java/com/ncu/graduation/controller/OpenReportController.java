package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.error.RedirectException;
import com.ncu.graduation.mapper.OpenReportRecordMapper;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportRecord;
import com.ncu.graduation.model.OpenReportRecordExample;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.service.OpenReportService;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 22:34
 * @description：开题报告
 * @modified By：
 * @version: 0.0.1
 */

@Controller
public class OpenReportController {

  @Autowired
  private OpenReportService openReportService;
  @Autowired
  private ProjectService projectService;

  /**
   * 教师开题报告页面, 获取我的开题报告
   */
  @GetMapping("/teacher/openReport")
  public String getTeaOpenReport(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<TeaProjectDocumentVO<OpenReport>> myOpenReport = openReportService
        .getTeaOpenReport(teaProject);
    model.addAttribute("teaOpenReport", myOpenReport);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/teaOpenReport";
  }

  /**
   * 审核开题报告, 盲审也用这一URL
   */
  @ResponseBody
  @PostMapping("/teacher/openReport/verify")
  public ResultDTO verifyDocument(@Valid VerifyDocumentDTO verifyDocument, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    openReportService.verifyOpenReport(verifyDocument,user);
    return ResultDTO.okOf();
  }

  /**
   * 获取需要我盲审的开题报告
   */
  @GetMapping("/teacher/openReport/blindVerify")
  public String blindVerifyDocument(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<TeaProjectDocumentVO<OpenReport>> teaBlindOpenReport = openReportService
        .getTeaBlindOpenReport(user);
    model.addAttribute("teaOpenReport", teaBlindOpenReport);
    model.addAttribute("isBlind", 1);
    return "document/teaOpenReport";
  }

  /**
   * 允许修改开题报告
   */
  @ResponseBody
  @GetMapping("/teacher/openReport/modifiable")
  public ResultDTO setOpenReportModifiable(@RequestParam("pno") String pno, Model model) {
    openReportService.setOpenReportModifiable(pno);
    return ResultDTO.okOf();
  }


  /**
   * 提交开题报告
   */
  @ResponseBody
  @PostMapping("/student/openReport/submit")
  public ResultDTO submitOpenReport(HttpSession session,
      @RequestParam("openReport") MultipartFile file,
      @RequestParam(value = "id",required = false) String id) {
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectApply projectApply = projectService.getStuProject(user);
    openReportService.submitOpenReport(file, id, user, projectApply.getPno());
    return ResultDTO.okOf();
  }

  /**
   * 学生查看开题报告和审核结果
   */
  @GetMapping("/student/openReport")
  public String getStuOpenReport(@RequestParam(value = "pno", required = false) String pno,
      HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    ProjectApply stuProject;
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      stuProject = projectService.getStuProject(user);
    } else {
      stuProject = projectService.getProject(pno);
    }
    if (stuProject == null) {
      throw new RedirectException(EmProjectError.NO_PROJECT);
    }
    StuProjectDocumentVO<OpenReport> stuOpenReport = openReportService
        .getStuOpenReport(stuProject);
    model.addAttribute("stuOpenReport", stuOpenReport);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/stuOpenReport";
  }

  /**
   * 查看历史评价
   */
  @ResponseBody
  @GetMapping("/student/openReport/record")
  public ResultDTO getOpenReportRecord(@RequestParam("pno") String pno, Model model) {
    List<OpenReportRecord> records = openReportService.getOpenReportRecord(pno);
    model.addAttribute("openReportRecord", records);
    return ResultDTO.okOf(records);
  }
}

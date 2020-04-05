package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.mapper.OpenReportRecordMapper;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportRecord;
import com.ncu.graduation.model.OpenReportRecordExample;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.service.OpenReportService;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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


  /**
   * 教师开题报告页面, 获取我的开题报告
   */
  @GetMapping("/teaOpenReport")
  public String getTeaOpenReport(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    List<TeaProjectDocumentVO<OpenReport>> myOpenReport = openReportService
        .getTeaOpenReport(user);
    model.addAttribute("teaOpenReport", myOpenReport);
    return "document/teaOpenReport";
  }

  /**
   * 审核开题报告, 盲审也用这一URL
   * @param verifyDocument
   * @return
   */
  @ResponseBody
  @PostMapping("/openReport/verify")
  public ResultDTO verifyDocument(@Valid VerifyDocumentDTO verifyDocument) {
    openReportService.verifyOpenReport(verifyDocument);
    return ResultDTO.okOf();
  }

  /**
   * 获取需要我盲审的开题报告
   * @param session
   * @param model
   * @return
   */
  @GetMapping("/openReport/blindVerify")
  public String blindVerifyDocument(HttpSession session,Model model){
    UserVO user = (UserVO) session.getAttribute("user");
    List<TeaProjectDocumentVO<OpenReport>> teaBlindOpenReport = openReportService
        .getTeaBlindOpenReport(user);
    model.addAttribute("teaOpenReport",teaBlindOpenReport);
    model.addAttribute("isBlind",1);
    return "document/teaOpenReport";
  }


  /**
   * 提交开题报告
   * @param file
   * @param dno
   * @return
   */
  @ResponseBody
  @PostMapping("/openReport/submit")
  public ResultDTO submitTaskBook(@RequestParam("openReport") MultipartFile file,
      @RequestParam("oldFile") String oldFile,
      @RequestParam("dno") String dno,HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    openReportService.submitOpenReport(oldFile,file,dno,user);
    return ResultDTO.okOf();
  }

  /**
   * 学生查看开题报告和审核结果
   * @param request
   * @param model
   * @return
   */
  @GetMapping("/stuOpenReport")
  public String getStuOpenReport(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    ProjectApply project = (ProjectApply) request.getSession().getAttribute("stuProject");
    StuProjectDocumentVO<OpenReport> stuOpenReport = openReportService
        .getStuOpenReport(user,project);
    model.addAttribute("stuOpenReport", stuOpenReport);
    return "document/stuOpenReport";
  }

  /**
   * 查看历史评价
   * @param openNo
   * @param model
   * @return
   */
  @ResponseBody
  @GetMapping("/stuOpenReport/record")
  public ResultDTO getOpenReportRecord(@RequestParam("openNo") String openNo,Model model) {
    List<OpenReportRecord> records = openReportService.getOpenReportRecord(openNo);
    model.addAttribute("openReportRecord", records);
    return ResultDTO.okOf(records);
  }
}

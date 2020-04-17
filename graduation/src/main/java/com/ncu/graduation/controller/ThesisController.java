package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ProjectSearchDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportRecord;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisRecord;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.service.ThesisService;
import com.ncu.graduation.util.JedisOp;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 20:57
 * @description：论文controller
 * @modified By：
 * @version: 0.0.1
 */

@Controller
public class ThesisController {

  @Autowired
  private ThesisService thesisService;

  @Autowired
  private ProjectService projectService;

  /**
   * 教师论文页面, 获取我的课题的论文
   */
  @GetMapping("/teacher/thesis")
  public String getTeaThesis(HttpSession session,
      Model model) {
    UserVO user = (UserVO)session.getAttribute("user");
    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<TeaProjectDocumentVO<Thesis>> myThesis = thesisService
        .getTeaThesis(teaProject);
    model.addAttribute("teaThesis", myThesis);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/teaThesis";
  }

  /**
   * 审核论文, 盲审也用这一URL
   */
  @ResponseBody
  @PostMapping("/teacher/thesis/verify")
  public ResultDTO verifyDocument(@Valid VerifyDocumentDTO verifyDocument) {
    thesisService.verifyThesis(verifyDocument);
    return ResultDTO.okOf();
  }

  /**
   * 获取需要我盲审的论文
   */
  @GetMapping("/teacher/thesis/blindVerify")
  public String blindVerifyDocument(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<TeaProjectDocumentVO<Thesis>> teaBlindThesis = thesisService.getTeaBlindThesis(user);
    model.addAttribute("teaThesis", teaBlindThesis);
    model.addAttribute("isBlind", 1);
    return "document/teaThesis";
  }


  /**
   * 提交论文
   */
  @ResponseBody
  @PostMapping("/student/thesis/submit")
  public ResultDTO submitThesis(@RequestParam("thesis") MultipartFile file,
      @RequestParam("dno") String dno,
      HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectApply projectApply = (ProjectApply) session.getAttribute("stuProject");
    thesisService.submitThesis(user,file, dno,projectApply.getPno());
    return ResultDTO.okOf();
  }

  /**
   * 学生查看论文和审核结果
   */
  @GetMapping("/student/thesis")
  public String getStuThesis(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    ProjectApply stuProject = projectService.getStuProject(user);
    StuProjectDocumentVO<Thesis> stuThesis = thesisService
        .getStuThesis(user,stuProject);
    model.addAttribute("stuThesis", stuThesis);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/stuThesis";
  }

  /**
   * 查看历史评价
   */
  @ResponseBody
  @GetMapping("/student/thesis/record")
  public ResultDTO getThesisRecord(@RequestParam("thesisNo") String thesisNo, Model model) {
    List<ThesisRecord> records = thesisService.getThesisRecord(thesisNo);
    model.addAttribute("thesisRecord", records);
    return ResultDTO.okOf(records);
  }
}

package com.ncu.graduation.controller;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.error.RedirectException;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisRecord;
import com.ncu.graduation.service.impl.ProjectServiceImpl;
import com.ncu.graduation.service.impl.ThesisServiceImpl;
import com.ncu.graduation.vo.ProjectInfoVO;
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
  private ThesisServiceImpl thesisService;

  @Autowired
  private ProjectServiceImpl projectService;

  /**
   * 教师论文页面, 获取我的课题的论文
   */
  @GetMapping("/teacher/thesis")
  public String getTeaThesis(HttpSession session,
      Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    Map<String, ProjectSelectResultBO> teaProject = projectService.getTeaProject(user);
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
  public ResultDTO verifyDocument(@Valid VerifyDocumentDTO verifyDocument,HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    thesisService.verifyThesis(verifyDocument,user);
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
   * 允许修改论文
   */
  @ResponseBody
  @GetMapping("/teacher/thesis/modifiable")
  public ResultDTO setThesisModifiable(@RequestParam("pno") String pno, Model model) {
    thesisService.setThesisModifiable(pno);
    return ResultDTO.okOf();
  }


  /**
   * 提交论文
   */
  @ResponseBody
  @PostMapping("/student/thesis/submit")
  public ResultDTO submitThesis(HttpSession session, @RequestParam("thesis") MultipartFile file,
      @RequestParam(value = "id",required = false) String id) {
    UserVO user = (UserVO) session.getAttribute("user");
    FileTypeEnum.checkFileType(file.getOriginalFilename());
    ProjectInfoVO projectApply = projectService.getStuProject(user);
    thesisService.submitThesis(user, file, id, projectApply.getProjectApply().getPno());
    return ResultDTO.okOf();
  }

  /**
   * 学生查看论文和审核结果
   */
  @GetMapping("/student/thesis")
  public String getStuThesis(@RequestParam(value = "pno", required = false) String pno,
      HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    ProjectInfoVO stuProject;
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      stuProject = projectService.getStuProject(user);
    } else {
      stuProject = projectService.getStuProject(pno);
    }
    if (stuProject == null) {
      throw new RedirectException(EmProjectError.NO_PROJECT);
    }
    StuProjectDocumentVO<Thesis> stuThesis = thesisService
        .getStuThesis(user, stuProject);
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
  public ResultDTO getThesisRecord(@RequestParam("pno") String pno, Model model) {
    List<ThesisRecord> records = thesisService.getThesisRecord(pno);
    model.addAttribute("thesisRecord", records);
    return ResultDTO.okOf(records);
  }
}

package com.ncu.graduation.controller;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.error.RedirectException;
import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.ForeignRecord;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.service.impl.ForeignLiteratureServiceImpl;
import com.ncu.graduation.service.impl.ProjectServiceImpl;
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
 * @date ：Created in 2020/3/30 17:23
 * @description：外文资料controller
 * @modified By：
 * @version: 0.0.1
 */

@Controller
public class ForeignLiteratureController {

  @Autowired
  private ForeignLiteratureServiceImpl foreignLiteratureService;

  @Autowired
  private ProjectServiceImpl projectService;

  /**
   * 教师外文资料页面, 获取我的课题的外文资料
   */
  @GetMapping("/teacher/foreignLiterature")
  public String getTeaForeignLiterature(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    Map<String, ProjectSelectResultBO> teaProject = projectService.getTeaProject(user);
    List<TeaProjectDocumentVO<ForeignLiterature>> teaForeignLiterature = foreignLiteratureService
        .getTeaForeignLiterature(user, teaProject);
    model.addAttribute("teaForeignLiterature", teaForeignLiterature);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/teaForeignLiterature";
  }

  /**
   * 审核外文资料
   */
  @ResponseBody
  @PostMapping("/teacher/foreignLiterature/verify")
  public ResultDTO verifyDocument(HttpSession session, @Valid VerifyDocumentDTO verifyDocument) {
    UserVO user = (UserVO) session.getAttribute("user");
    foreignLiteratureService.verifyForeignLiterature(user, verifyDocument);
    return ResultDTO.okOf();
  }

  /**
   * 允许修改外文资料
   */
  @ResponseBody
  @GetMapping("/teacher/foreignLiterature/modifiable")
  public ResultDTO setForeignLiteratureModifiable(@RequestParam("pno") String pno, Model model) {
    foreignLiteratureService.setForeignLiteratureModifiable(pno);
    return ResultDTO.okOf();
  }

  /**
   * 提交外文资料
   */
  @ResponseBody
  @PostMapping("/student/foreignLiterature/submit")
  public ResultDTO submitForeignLiterature(HttpSession session,
      @RequestParam("oldFile") String oldFile,
      @RequestParam("foreignFile") MultipartFile foreignFile,
      @RequestParam("translationFile") MultipartFile translationFile,
      @RequestParam(value = "id", required = false) String id) {
    UserVO user = (UserVO) session.getAttribute("user");
    FileTypeEnum.checkFileType(foreignFile.getOriginalFilename());
    FileTypeEnum.checkFileType(translationFile.getOriginalFilename());
    ProjectInfoVO stuProject = projectService.getStuProject(user);
    foreignLiteratureService.submitForeignLiterature(foreignFile, translationFile, id,
        stuProject.getProjectApply().getPno());
    return ResultDTO.okOf();
  }

  /**
   * 学生查看开题报告和审核结果
   */
  @GetMapping("/student/foreignLiterature")
  public String getStuForeignLiterature(@RequestParam(value = "pno", required = false) String pno,
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
    StuProjectDocumentVO<ForeignLiterature> stuForeignLiterature = foreignLiteratureService
        .getStuForeignLiterature(stuProject);
    model.addAttribute("stuForeignLiterature", stuForeignLiterature);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/stuForeignLiterature";
  }

  /**
   * 查看历史评价
   */
  @ResponseBody
  @GetMapping("/student/foreignLiterature/record")
  public ResultDTO getForeignRecordRecord(@RequestParam("pno") String pno, Model model) {
    List<ForeignRecord> foreignRecord = foreignLiteratureService.getForeignRecord(pno);
    model.addAttribute("foreignRecord", foreignRecord);
    return ResultDTO.okOf(foreignRecord);
  }


}

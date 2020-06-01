package com.ncu.graduation.controller;

import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectVerifyDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.ProjectTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.service.impl.ProjectServiceImpl;
import com.ncu.graduation.service.impl.UserServiceImpl;
import com.ncu.graduation.vo.ProjectApplyVO;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.ProjectProgressVO;
import com.ncu.graduation.vo.UserVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 20:58
 * @description：课题Controller
 * @modified By：
 * @version: 0.0.1
 */
@Controller
public class ProjectController {

  @Autowired
  private ProjectServiceImpl projectService;

  @Autowired
  private UserServiceImpl userService;

  /**
   * 查看我申请的课题
   */
  @GetMapping("/project/myProject")
  public String getMyProject(HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    PaginationDTO<ProjectApplyVO> myApplyProject = projectService
        .getMyApplyProject(page, size, user);
    model.addAttribute("myProject", myApplyProject);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    model.addAttribute("collegeList", userService.getCollege());
    model.addAttribute("projectTypeList", ProjectTypeEnum.getProjectTypeList());
    return "/project/myProject";
  }

  /**
   * 查看课题信息
   */
  @GetMapping("/project/{pno}")
  public String getProject(@PathVariable("pno") String pno,
      Model model) {
    ProjectInfoVO projectInfoVO = projectService.getProjectInfo(pno);
    model.addAttribute("project", projectInfoVO);
    return "/project/projectInfo";
  }


  /**
   * 申请课题
   */
  @PostMapping("/project/apply")
  @ResponseBody
  public ResultDTO applyProject(HttpSession session,@Valid ProjectApplyDTO projectApplyDTO) {
    UserVO user = (UserVO) session.getAttribute("user");
    if (user == null) {
      throw new CommonException(EmUserOperatorError.USER_NOT_LOGIN);
    }
    if (!UserRoleEnum.STUDENT.getRole().equals(user.getRole()) && !UserRoleEnum.TEACHER.getRole().equals(user.getRole())){
      throw new CommonException(EmUserOperatorError.ROLE_CANNOT_DO_THIS);
    }
    FileTypeEnum.checkFileType(projectApplyDTO.getFile().getOriginalFilename());
    projectService.applyOrUpdate(projectApplyDTO, user);
    return ResultDTO.okOf();
  }

  /**
   * 获取课题申请信息，即简要信息，用于审核或修改或浏览
   */
  @GetMapping("/project/simple/{pno}")
  @ResponseBody
  public ResultDTO simpleProject(@PathVariable("pno") String pno) {
    ProjectApply projectApply = projectService.getProject(pno);
    return ResultDTO.okOf(projectApply);
  }

  /**
   * 撤销课题
   */
  @GetMapping("/project/revoke/{pno}")
  @ResponseBody
  public ResultDTO revokeProject(@PathVariable("pno") String pno) {
    projectService.revoke(pno);
    return ResultDTO.okOf();
  }

  /**
   * 复审课题
   */
  @GetMapping("/project/review/{pno}")
  @ResponseBody
  public ResultDTO reviewProject(@PathVariable("pno") String pno, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    projectService.review(pno, user);
    return ResultDTO.okOf();
  }

  /**
   * 获取需要我审核的课题
   */
  @GetMapping("/project/verifyProjectView")
  public String verifyProjectView(HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    PaginationDTO<ProjectApplyVO> verifyProject = projectService.getVerifyProject(page, size, user);
    model.addAttribute("projects", verifyProject);
    return "project/verifyProject";
  }

  /**
   * 审核课题
   */
  @ResponseBody
  @PostMapping("/project/verify")
  public ResultDTO verifyProjectApply(@Validated ProjectVerifyDTO verifyDocumentDTO,
      HttpServletRequest request) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    projectService.verifyProject(verifyDocumentDTO, user);
    return ResultDTO.okOf();
  }

  /**
   * 课题进度, 关于该毕设的所有信息
   */

  @GetMapping("/project/progress/{pno}")
  public String getProjectAllInfo(@PathVariable("pno") String pno, HttpSession session,
      Model model) {
    ProjectProgressVO projectProgressVO = projectService.getProjectProgress(pno);
    model.addAttribute("projectProgress",projectProgressVO);
    return "project/projectProgress";
  }
}

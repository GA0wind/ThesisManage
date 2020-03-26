package com.ncu.graduation.controller;

import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectVerifyDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.model.Project;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.ProjectSelectVO;
import com.ncu.graduation.vo.ProjectViewVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
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
  private ProjectService projectService;


  /**
   * 查看我的课题
   */
  @GetMapping("/myProject")
  public String getMyProject(HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    PaginationDTO<ProjectViewVO> myProject = projectService.getMyProject(page, size, user);
    model.addAttribute("projects", myProject);
    return "myProject";
  }

  /**
   * 查看课题信息
   */
  @GetMapping("/project/{no}")
  public String getProject(@PathVariable String no,
      Model model) {
    ProjectInfoVO projectInfoVO = projectService.getProjectInfo(no);
    model.addAttribute("project", projectInfoVO);
    return "project-info";
  }


  /**
   * 申请课题
   */
  @PostMapping("/project/apply")
  @ResponseBody
  public ResultDTO applyProject(@Valid ProjectApplyDTO projectApplyDTO,
      HttpServletRequest request) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    if (user == null) {
      throw new CommonException(EmUserOperatorError.USER_NOT_LOGIN);
    }
    projectService.applyOrUpdate
        (projectApplyDTO, user);
    return ResultDTO.okOf();
  }

  /**
   * 修改未被审核的课题
   */
  @GetMapping("/project/modify/{no}")
  @ResponseBody
  public ResultDTO modifyProject(@PathVariable("no") String no) {
    Project project = projectService.getProject(no);

    return ResultDTO.okOf(project);
  }

  /**
   * 撤销课题
   */
  @PostMapping("/project/revoke/{no}")
  @ResponseBody
  public ResultDTO revokeProject(@PathVariable("no") String no) {
    projectService.revoke(no);
    return ResultDTO.okOf();
  }

  /**
   * 获取需要我审核的课题
   */
  @GetMapping("/verifyProjectView")
  public String verifyProjectView(HttpServletRequest request,
      Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    PaginationDTO<ProjectViewVO> verifyProject = projectService.getVerifyProject(page, size, user);
    model.addAttribute("projects", verifyProject);
    return "verifyProject";
  }

  /**
   * 审核课题
   */
  @PostMapping("/project/verify")
  public ResultDTO verifyProjectApply(@Validated ProjectVerifyDTO projectVerifyDTO,
      HttpServletRequest request) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    projectService.verifyProject(projectVerifyDTO, user);
    return ResultDTO.okOf();
  }
}

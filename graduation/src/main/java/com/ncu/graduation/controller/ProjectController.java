package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectInfoDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonError;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.model.Project;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.apache.catalina.User;
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
 * @date ：Created in 2020/3/21 20:58
 * @description：课题Controller
 * @modified By：
 * @version: 0.0.1
 */
@Controller
public class ProjectController {

  @Autowired
  private ProjectService projectService;

  @GetMapping("/myProject")
  public String getMyProject(HttpServletRequest request,
      Model model){
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    List<Project> myProject = projectService.getMyProject(user);
    model.addAttribute("projects",myProject);
    return "myProject";
  }

  @GetMapping("/project/{no}")
  public String getProject(@PathVariable String no,
      Model model){
    ProjectInfoDTO projectInfoDTO = projectService.getProject(no);

    model.addAttribute("project",projectInfoDTO);
    return "project-info";
  }


  @PostMapping("/project/apply")
  @ResponseBody
  public ResultDTO applyProject(@Valid ProjectApplyDTO projectApplyDTO,
      HttpServletRequest request){
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    if (user == null){
      throw new CommonException(EmUserOperatorError.USER_NOT_LOGIN);
    }
    projectService.apply(projectApplyDTO,user);
    return ResultDTO.okOf();
  }

  @PostMapping("/project/revoke/{no}")
  @ResponseBody
  public ResultDTO revokeProject(@PathVariable("no") String no,
      HttpServletRequest request){
    projectService.revoke(no);
    return ResultDTO.okOf();
  }

}

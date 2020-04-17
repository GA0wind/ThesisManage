package com.ncu.graduation.controller;

import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectSearchDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.service.ProjectSelectService;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.util.JedisOp;
import com.ncu.graduation.vo.ProjectSelectVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 23:09
 * @description：师生双选Controller
 * @modified By：
 * @version: 0.0.1
 */
@Controller
public class ProjectSelectController {

  @Autowired
  private ProjectSelectService projectSelectService;

  @Autowired
  private ProjectService projectService;


  /**
   * 获取可选的课题
   */
  @GetMapping("/project/projectSelectView")
  public String getSelectiveProject(HttpSession session, Model model,
      @RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size,
      ProjectSearchDTO projectSearchDTO) {
    UserVO user = (UserVO) session.getAttribute("user");
    model.addAttribute("search",projectSearchDTO);
    PaginationDTO<ProjectSelectVO> selectiveProject = projectSelectService
        .getSelectiveProject(page, size, user,projectSearchDTO);
    model.addAttribute("projects", selectiveProject);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "select/project-select";
  }

  /**
   * 师生双选, 选择课题
   */
  @ResponseBody
  @GetMapping("/project/select")
  public ResultDTO projectSelect(@RequestParam("pno") String pno, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    projectSelectService.selectProject(user, pno);
    return ResultDTO.okOf();
  }

  /**
   * 我的选题情况
   */
  @GetMapping("/project/mySelectState")
  public String mySelectState(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<ProjectSelectVO> mySelectState = projectSelectService.getMySelectState(user);
    model.addAttribute("projectSelects", mySelectState);
    return "select/mySelectState";
  }

  /**
   * 我的课题的选题情况
   */
  @GetMapping("/project/myProject/selectState")
  public String myProjectSelectState(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<ProjectApply> projectSelectState = projectSelectService
        .getMyProjectSelectState(user);
    model.addAttribute("projectApplys", projectSelectState);
    return "select/myProjectSelectState";
  }

  /**
   * 某个课题的被选情况
   * @param pno
   * @return
   */
  @ResponseBody
  @GetMapping("/project/myProjectSelectState/{pno}")
  public ResultDTO getProjectSelectState(@PathVariable("pno") String pno) {
    List<ProjectSelectVO> projectSelectState = projectSelectService
        .getProjectSelectState(pno);

    return ResultDTO.okOf(projectSelectState);
  }


  /**
   * 确认选题人
   */
  @ResponseBody
  @GetMapping("/project/select/confirm")
  public ResultDTO confirmSelect(HttpSession session,
      @RequestParam("selectNo") String selectNo,
      @RequestParam("selectName") String selectName,
      @RequestParam("pno") String pno,
      @RequestParam("pname") String pname) {
    UserVO user = (UserVO) session.getAttribute("user");
    projectSelectService.confirmProjectSelector(user, selectNo, selectName, pno, pname);
    return ResultDTO.okOf();
  }

  /**
   * 拒绝选题人
   */
  @ResponseBody
  @GetMapping("/project/select/refuse")
  public ResultDTO refuseSelect(@RequestParam("selectNo") String selectNo,
      @RequestParam("pno") String pno) {
    projectSelectService.refuseProjectSelector(selectNo, pno);
    return ResultDTO.okOf();
  }
}

package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.error.RedirectException;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.service.TaskBookService;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
 * @date ：Created in 2020/3/29 20:53
 * @description：任务书Controller
 * @modified By：
 * @version: 0.0.1
 */
@Controller
public class TaskBookController {

  @Autowired
  private TaskBookService taskBookService;
  @Autowired
  private ProjectService projectService;

  /**
   * 教师任务书页面, 获取我的课题的任务书
   */
  @GetMapping("/teacher/taskBook")
  public String getTeaTaskBook(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<TeaProjectDocumentVO<TaskBook>> myTaskBook = taskBookService
        .getTeaTaskBook(teaProject);
    model.addAttribute("teaTaskBook", myTaskBook);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/teaTaskBook";
  }

  /**
   * 提交任务书
   */
  @ResponseBody
  @PostMapping("/teacher/taskBook/submit")
  public ResultDTO submitTaskBook(HttpSession session, @RequestParam("taskBook") MultipartFile file,
      @RequestParam(value = "id",required = false) String id, @RequestParam("pno") String pno) {
    UserVO user = (UserVO) session.getAttribute("user");
    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    //如果课题编号不在当前用户有效课题中, 不允许操作
    if (!teaProject.containsKey(pno)){
      return ResultDTO.errorOf(EmProjectError.USER_NOT_HAVE_THE_PROJECT);
    }
    taskBookService.submitTaskBook(user, file, id, pno);
    return ResultDTO.okOf();
  }

  /**
   * 查看任务书
   */
  @GetMapping("/student/taskBook")
  public String getStuTaskBook(@RequestParam(value = "pno", required = false) String pno,
      HttpSession session,
      Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectApply projectApply;
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      projectApply = projectService.getStuProject(user);
    } else {
      projectApply = projectService.getProject(pno);
    }
    if (projectApply == null) {
      throw new RedirectException(EmProjectError.NO_PROJECT);
    }
    StuProjectDocumentVO<TaskBook> stuTaskBook = taskBookService
        .getStuTaskBook(projectApply);
    model.addAttribute("stuTaskBook", stuTaskBook);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/stuTaskBook";

  }

  //todo 任务书修改功能
}

package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.error.EmProjectError;
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
  public ResultDTO submitTaskBook(@RequestParam("taskBook") MultipartFile file,
      @RequestParam("dno") String dno, @RequestParam("pno") String pno, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    taskBookService.submitTaskBook(user, file, dno, pno);
    return ResultDTO.okOf();
  }

  /**
   * 学生查看任务书
   */
  @GetMapping("/student/taskBook")
  public String getStuTaskBook(HttpSession session,
      Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectApply stuProject = projectService.getStuProject(user);
    if (stuProject == null){
      model.addAttribute("message", EmProjectError.NO_PROJECT.getErrMsg());
      return "error";
    }
    StuProjectDocumentVO<TaskBook> stuTaskBook = taskBookService
        .getStuTaskBook(stuProject);
    model.addAttribute("stuTaskBook", stuTaskBook);
    ProjectPlan projectPlan = projectService.getProjectPlan(user.getSchoolYear());
    model.addAttribute("projectPlan", projectPlan);
    return "document/stuTaskBook";
  }

  //todo 任务书修改功能
}

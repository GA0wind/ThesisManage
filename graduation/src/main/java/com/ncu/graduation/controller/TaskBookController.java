package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.service.TaskBookService;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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

  /**
   * 教师任务书页面, 获取我的课题的任务书
   */
  @GetMapping("/teaTaskBook")
  public String getTeaTaskBook(HttpServletRequest request,
      Model model) {
    Map<String, ProjectSelectResult> teaProject = (Map<String, ProjectSelectResult>) request
        .getSession().getAttribute("teaProject");
    List<TeaProjectDocumentVO<TaskBook>> myTaskBook = taskBookService
        .getTeaTaskBook(teaProject);
    model.addAttribute("teaTaskBook", myTaskBook);
    return "document/teaTaskBook";
  }

  /**
   * 提交任务书
   * @param file
   * @param dno
   * @return
   */
  @ResponseBody
  @PostMapping("/taskBook/submit")
  public ResultDTO submitTaskBook(@RequestParam("taskBook") MultipartFile file,
      @RequestParam("dno") String dno,@RequestParam("pno")String pno, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    taskBookService.submitTaskBook(user,file,dno,pno);
    return ResultDTO.okOf();
  }

  /**
   * 学生查看任务书
   * @param request
   * @param model
   * @return
   */
  @GetMapping("/stuTaskBook")
  public String getStuTaskBook(HttpServletRequest request,
      Model model) {
    ProjectApply project = (ProjectApply) request.getSession().getAttribute("stuProject");

    StuProjectDocumentVO<TaskBook> stuTaskBook = taskBookService
        .getStuTaskBook(project);
    model.addAttribute("stuTaskBook", stuTaskBook);
    return "document/stuTaskBook";
  }

  //todo 任务书修改功能
}

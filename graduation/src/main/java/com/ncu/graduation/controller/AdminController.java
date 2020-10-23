package com.ncu.graduation.controller;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectPlanDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.SelectiveProjectDTO;
import com.ncu.graduation.dto.UserAddDTO;
import com.ncu.graduation.dto.UserSearchDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.ProjectTypeEnum;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.service.impl.OralExaminationServiceImpl;
import com.ncu.graduation.service.impl.ProjectSelectServiceImpl;
import com.ncu.graduation.service.impl.ProjectServiceImpl;
import com.ncu.graduation.service.impl.UserServiceImpl;
import com.ncu.graduation.vo.ProAndStuNumVO;
import com.ncu.graduation.vo.UserVO;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.poi.ss.usermodel.Workbook;
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
 * @date ：Created in 2020/4/6 18:14
 * @description：后台控制controller
 * @modified By：
 * @version: 0.0.1
 */

@Controller
public class AdminController {

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private ProjectServiceImpl projectService;

  @Autowired
  private ProjectSelectServiceImpl projectSelectService;

  @Autowired
  private OralExaminationServiceImpl oralExaminationService;

  /**
   * 学生列表
   */
  @GetMapping("/admin/stuView")
  public String stuView(@RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size, HttpSession session,
      Model model, UserSearchDTO userSearchDTO) {
    UserVO user = (UserVO) session.getAttribute("user");
    model.addAttribute("search", userSearchDTO);
    PaginationDTO<Student> paginationDTO = userService.stuList(page, size, user, userSearchDTO);
    model.addAttribute("users", paginationDTO);
    model.addAttribute("role", "student");
    model.addAttribute("collegeList", userService.getCollege());
    return "admin/userOp";
  }

  /**
   * 某个学生具体信息
   */
  @ResponseBody
  @GetMapping("/admin/stu/{sno}")
  public ResultDTO getStu(@PathVariable String sno, Model model) {
    Student student = userService.getStu(sno);
    model.addAttribute("student", student);
    model.addAttribute("collegeList", userService.getCollege());
    return ResultDTO.okOf(student);
  }

  /**
   * excel添加学生
   */
  @ResponseBody
  @PostMapping("/admin/exlAddStu")
  public ResultDTO addStuByExcel(@RequestParam("excel") MultipartFile excel, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    FileTypeEnum.checkExcelFile(excel.getOriginalFilename());
    userService.addStuByExcel(excel, user);
    return ResultDTO.okOf();
  }

  /**
   * excel设置学生组别
   */
  @ResponseBody
  @PostMapping("/admin/setStuGroupByExcel")
  public ResultDTO setStuGroupByExcel(@RequestParam("excel") MultipartFile excel) {
    FileTypeEnum.checkExcelFile(excel.getOriginalFilename());
    userService.setStuGroup(excel);
    return ResultDTO.okOf();
  }


  /**
   * 教师列表
   */
  @GetMapping("/admin/teaView")
  public String teaView(@RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size, HttpSession session,
      Model model, UserSearchDTO userSearchDTO) {
    UserVO user = (UserVO) session.getAttribute("user");
    model.addAttribute("search", userSearchDTO);
    PaginationDTO<Teacher> paginationDTO = userService.teaList(page, size, user, userSearchDTO);
    model.addAttribute("users", paginationDTO);
    model.addAttribute("role", "teacher");
    model.addAttribute("collegeList", userService.getCollege());

    return "admin/userOp";
  }

  /**
   * 某个教师具体信息
   */
  @ResponseBody
  @GetMapping("/admin/tea/{tno}")
  public ResultDTO getTea(@PathVariable String tno, Model model) {
    Teacher teacher = userService.getTea(tno);
    model.addAttribute("teachers", teacher);
    model.addAttribute("collegeList", userService.getCollege());

    return ResultDTO.okOf(teacher);
  }

  /**
   * excel添加教师
   */
  @ResponseBody
  @PostMapping("/admin/exlAddTea")
  public ResultDTO addTeaByExcel(@RequestParam("excel") MultipartFile excel, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    FileTypeEnum.checkExcelFile(excel.getOriginalFilename());
    userService.addTeaByExcel(excel, user);
    return ResultDTO.okOf();
  }

  /**
   * excel设置教师组别
   */
  @ResponseBody
  @PostMapping("/admin/setTeaGroupByExcel")
  public ResultDTO setTeaGroupByExcel(@RequestParam("excel") MultipartFile excel,
      HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    FileTypeEnum.checkExcelFile(excel.getOriginalFilename());
    userService.setTeaGroup(excel, user);
    return ResultDTO.okOf();
  }

  /**
   * 修改或更新用户
   */
  @ResponseBody
  @PostMapping("/admin/add")
  public ResultDTO addOrUpdateUser(@Valid UserAddDTO userAddDTO, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    return userService.addOrUpdateUser(userAddDTO, user);
  }


  /**
   * 获取课题数, 以及学生总数, 用以查看课题数是否足够满足学生数
   */
  @GetMapping("/admin/projectSelectiveAndStu")
  public String projectSelectiveAndStu(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<ProAndStuNumVO> stuAndProNum = projectSelectService
        .getStuAndProNum(user);
    model.addAttribute("stuAndProNum", stuAndProNum);
    model.addAttribute("collegeList", userService.getCollege());
    model.addAttribute("projectTypeList", ProjectTypeEnum.getProjectTypeList());
    return "admin/projectOp";
  }

  /**
   * 管理员添加可选课题
   */
  @ResponseBody
  @PostMapping("/admin/addSelectiveProject")
  public ResultDTO addSelectiveProject(SelectiveProjectDTO projectApply,
      HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    projectService.addSelectiveProject(projectApply, user);
    return ResultDTO.okOf();
  }

  /**
   * 管理员设置毕设计划
   */
  @ResponseBody
  @PostMapping("/admin/projectPlan")
  public ResultDTO setProjectPlan(@Valid ProjectPlanDTO projectPlanDTO) {
    projectService.setProjectPlan(projectPlanDTO);
    return ResultDTO.okOf();
  }

  /**
   * 管理员获取毕设计划
   */
  @GetMapping("/admin/projectPlan")
  public String getProjectPlan(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<ProjectPlan> projectPlans = projectService.getProjectPlans();
    if (projectPlans == null || projectPlans.isEmpty()) {
      return "admin/projectPlan";
    }
    if (user.getSchoolYear().equals(projectPlans.get(0).getSchoolYear())) {
      model.addAttribute("newProjectPlan", projectPlans.get(0));
      projectPlans.remove(0);
    }
    model.addAttribute("collegeList",userService.getCollege());
    model.addAttribute("projectPlans", projectPlans);
    return "admin/projectPlan";
  }

  /**
   * 查看某个阶段未完成的学生
   */
  @GetMapping("/admin/undoneStu/{type}")
  public String getUndoneStu(@PathVariable String type, HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    List<ProjectSelectResultBO> undoneStu = projectService.getUndoneStu(user, type);
    model.addAttribute("undoneStu", undoneStu);
    return "admin/undoneStu";
  }

  /**
   * 导出成绩
   * @param college
   * @param schoolYear
   * @param response
   * @return
   */
  @GetMapping("/admin/exportScoreToExl")
  public ResultDTO exportScore(@RequestParam("college") String college,
      @RequestParam("schoolYear") String schoolYear,
      HttpServletResponse response) {
    ProjectPlan projectPlan = projectService.getProjectPlan(schoolYear);
    Workbook workbook = oralExaminationService.exportScore(college, projectPlan, schoolYear);
    response.reset();
    response.setContentType("application/octet-stream");
    try {
      response.setHeader("Content-Disposition",
          "attachment;fileName=" + URLEncoder
              .encode(schoolYear + "学年" + college + "学院学生成绩.xlsx", "utf8"));
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    }
    //输出流
    OutputStream os = null;

    try {
      os = response.getOutputStream();
      workbook.write(os);
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        if (os != null) {
          os.close();
        }
        if (workbook != null) {
          workbook.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    return ResultDTO.okOf();
  }
}

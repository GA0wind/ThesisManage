package com.ncu.graduation.controller;

import com.ncu.graduation.dto.LoginDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.enums.UserRoleEnum;

import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.service.UserService;
import com.ncu.graduation.util.VerifyCode;
import com.ncu.graduation.vo.UserVO;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private ProjectService projectService;

  @GetMapping(value = "/verifyCode")
  public void GetVerifyCode(HttpServletRequest request,
      HttpServletResponse response) {
    VerifyCode verifyCode = VerifyCode.print();
    request.getSession().setAttribute("code", verifyCode.getCode());
    try {
      ImageIO.write(verifyCode.getImage(), "png", response.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @GetMapping(value = "/")
  public String login(Model model) {
    List<String> schoolYear = userService.getSchoolYear();
    model.addAttribute("schoolYears", schoolYear);
    return "login";
  }

  @PostMapping(value = "/login")
  public Object login(@Valid LoginDTO loginDTO,
      HttpServletRequest request) {
    String verifyCode = (String) request.getSession().getAttribute("code");
//        if (!verifyCode.equals(loginDTO.getCode())){
//            return ResultDTO.errorOf(EmUserOperatorError.PARAMETER_VALIDATION_ERROR.getErrCode(),"验证码不正确");
//        }

    UserVO userVO = new UserVO();
    if (UserRoleEnum.ADMIN.getRole().equals(loginDTO.getRole()) || UserRoleEnum.TEACHER.getRole()
        .equals(loginDTO.getRole())) {
      if (StringUtils.isBlank(loginDTO.getSchoolYear())) {
        return ResultDTO.errorOf(EmBulletinError.PARAMETER_VALIDATION_ERROR.getErrCode(), "学年不能为空");
      }
      Teacher teacher = (Teacher) userService.login(loginDTO);
      userVO.setName(teacher.getTname());
      userVO.setId(teacher.getId());
      userVO.setRole(teacher.getRole());
      userVO.setSchoolYear(loginDTO.getSchoolYear());
      userVO.setAccountNo(teacher.getTno());
      userVO.setCollege(teacher.getCollege());
      userVO.setLeadNumber(teacher.getLeadStudentNum());
      userVO.setGroup(teacher.getGroupNum());
      request.getSession().setAttribute("user", userVO);

      Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(userVO);
      request.getSession().setAttribute("teaProject", teaProject);
    }
    if (UserRoleEnum.STUDENT.getRole().equals(loginDTO.getRole())) {
      Student student = (Student) userService.login(loginDTO);
      userVO.setName(student.getSname());
      userVO.setId(student.getId());
      userVO.setRole(UserRoleEnum.STUDENT.getRole());
      userVO.setSchoolYear(student.getSchoolYear());
      userVO.setAccountNo(student.getSno());
      userVO.setCollege(student.getCollege());
      request.getSession().setAttribute("user", userVO);

      ProjectApply stuProject = projectService.getStuProject(userVO);
      request.getSession().setAttribute("stuProject", stuProject);
    }

    return "redirect:project/myProject";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session){
    session.removeAttribute("user");
    session.removeAttribute("teaProject");
    session.removeAttribute("stuProject");
    return "redirect:/";
  }

}

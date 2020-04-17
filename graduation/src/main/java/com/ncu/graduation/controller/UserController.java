package com.ncu.graduation.controller;

import com.alibaba.fastjson.JSON;
import com.ncu.graduation.dto.LoginDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.UserModifyPwdDTO;
import com.ncu.graduation.enums.UserRoleEnum;


import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.service.UserService;
import com.ncu.graduation.util.JedisOp;
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
  @Autowired
  private JedisOp jedis;

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
        return ResultDTO.errorOf(EmCommonError.PARAMETER_VALIDATION_ERROR.getErrCode(), "学年不能为空");
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
      userVO.setTitle(teacher.getTitle());
      request.getSession().setAttribute("user", userVO);
      projectService.getTeaProject(userVO);
      //判断是否已经在redis中, 如果是就更新存储时间, 不是就插入

    }
    if (UserRoleEnum.STUDENT.getRole().equals(loginDTO.getRole())) {
      Student student = (Student) userService.login(loginDTO);
      userVO.setName(student.getSname());
      userVO.setId(student.getId());
      userVO.setRole(UserRoleEnum.STUDENT.getRole());
      userVO.setSchoolYear(student.getSchoolYear());
      userVO.setAccountNo(student.getSno());
      userVO.setCollege(student.getCollege());
      userVO.setMajor(student.getMajor());
      userVO.setGradeClass(student.getMajor());
      userVO.setGroup(student.getGroupNum());
      request.getSession().setAttribute("user", userVO);
      projectService.getStuProject(userVO);
    }
    return "redirect:project/myProject";
  }

  @GetMapping("/logout")
  public String logout(HttpSession session) {
    session.removeAttribute("user");
    session.removeAttribute("teaProject");
    session.removeAttribute("stuProject");
    return "redirect:/";
  }

  @ResponseBody
  @PostMapping("/user/modify")
  public ResultDTO modifyPwd(UserModifyPwdDTO userModifyDTO, HttpSession session) {

    if (!userModifyDTO.getNewPwd().equals(userModifyDTO.getConfirmNewPwd())) {
      return ResultDTO.errorOf(EmCommonError.PARAMETER_VALIDATION_ERROR.getErrCode(), "两次密码不一致");
    }
    UserVO user = (UserVO) session.getAttribute("user");
    LoginDTO loginDTO = new LoginDTO();
    loginDTO.setAccountNo(user.getAccountNo());
    loginDTO.setAccountPwd(userModifyDTO.getOldPwd());
    loginDTO.setRole(user.getRole());
    if (userService.login(loginDTO) == null) {
      return ResultDTO.errorOf(EmUserOperatorError.USER_LOGIN_FAIL.getErrCode(), "旧密码有误请重试");
    }
    Integer result = userService.updatePwd(user, userModifyDTO.getNewPwd());
    if (result != 1) {
      return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR.getErrCode(), "修改失败, 检查一下参数信息");
    }
    return ResultDTO.okOf();
  }

  @GetMapping("/user/info/{userNo}")
  public String getUserInfo(@PathVariable("userNo") String userNo,
      @RequestParam("role") String role, Model model) {
    UserVO userVO = new UserVO();
    if (UserRoleEnum.STUDENT.getRole().equals(role)) {
      Student student = userService.getStu(userNo);
      userVO.setName(student.getSname());
      userVO.setRole(UserRoleEnum.STUDENT.getRole());
      userVO.setAccountNo(student.getSno());
      userVO.setCollege(student.getCollege());
      userVO.setMajor(student.getMajor());
      userVO.setGradeClass(student.getGradeClass());
    }
    if (!UserRoleEnum.STUDENT.getRole().equals(role)) {
      Teacher teacher = userService.getTea(userNo);
      userVO.setName(teacher.getTname());
      userVO.setAccountNo(teacher.getTno());
      userVO.setRole(teacher.getRole());
      userVO.setCollege(teacher.getCollege());
      userVO.setGroup(teacher.getGroupNum());
      userVO.setTitle(teacher.getTitle());
    }
    model.addAttribute("user", userVO);

    return "profile";
  }
}

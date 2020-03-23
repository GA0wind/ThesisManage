package com.ncu.graduation.controller;

import com.ncu.graduation.dto.LoginDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.enums.UserRoleEnum;

import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.model.Menu;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.service.UserService;
import com.ncu.graduation.util.VerifyCode;
import com.ncu.graduation.vo.UserVO;
import javax.validation.Valid;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/verifyCode", method = RequestMethod.GET)
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(Model model) {
        List<String> schoolYear = userService.getSchoolYear();
        model.addAttribute("schoolYears",schoolYear);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object login(@Valid LoginDTO loginDTO,
                        HttpServletRequest request,
                        Model model) {
        String verifyCode = (String) request.getSession().getAttribute("code");
//        if (!verifyCode.equals(loginDTO.getCode())){
//            return ResultDTO.errorOf(EmUserOperatorError.PARAMETER_VALIDATION_ERROR.getErrCode(),"验证码不正确");
//        }

        UserVO userVO = new UserVO();
        if (UserRoleEnum.ADMIN.getRole().equals(loginDTO.getRole()) || UserRoleEnum.TEACHER.getRole().equals(loginDTO.getRole())) {
            if (StringUtils.isBlank(loginDTO.getSchoolYear())){
                return ResultDTO.errorOf(EmBulletinError.PARAMETER_VALIDATION_ERROR.getErrCode(),"学年不能为空");
            }
            Teacher teacher = (Teacher) userService.login(loginDTO);
            userVO.setName(teacher.getTname());
            userVO.setId(teacher.getId());
            userVO.setRole(teacher.getRole());
            userVO.setSchoolYear(loginDTO.getSchoolYear());
            userVO.setAccountNo(teacher.getTno());
            request.getSession().setAttribute("user", userVO);
        }
        if (UserRoleEnum.STUDENT.getRole().equals(loginDTO.getRole())) {
            Student student = (Student) userService.login(loginDTO);
            userVO.setName(student.getSname());
            userVO.setId(student.getId());
            userVO.setRole(UserRoleEnum.STUDENT.getRole());
            userVO.setSchoolYear(student.getSchoolYear());
            userVO.setAccountNo(student.getSno());
            request.getSession().setAttribute("user", userVO);
        }
        List<Menu> menus = userService.getMenu(loginDTO);
        model.addAttribute("menus",menus);
        return new ModelAndView("myProject") ;
    }
}

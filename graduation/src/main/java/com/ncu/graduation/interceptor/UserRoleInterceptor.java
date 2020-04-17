package com.ncu.graduation.interceptor;

import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.vo.UserVO;
import java.io.IOException;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author ：grh
 * @date ：Created in 2020/4/17 13:28
 * @description：根绝用户角色拦截
 * @modified By：
 * @version:
 */
@Slf4j
@Component
public class UserRoleInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    if (user == null){
      return true;
    }
    String url = request.getRequestURI();
    String teacher = ".*teacher.*";
    String student = ".*student.*";
    String admin = ".*admin.*";
    //指导老师
    if (user.getRole().equals(UserRoleEnum.TEACHER.getRole())) {
      if (Pattern.matches(admin, url)) {
        try {
          response.sendRedirect(request.getContextPath() + "/");
        } catch (IOException e) {
          e.printStackTrace();
        }
        return false;
      } else {
        return true;
      }
    }
    //管理员
    if (user.getRole().equals(UserRoleEnum.ADMIN.getRole())) {
      if (Pattern.matches(teacher, url) || Pattern.matches(student, url)) {
        try {
          response.sendRedirect(request.getContextPath() + "/");
        } catch (IOException e) {
          e.printStackTrace();
        }
        return false;
      } else {
        return true;
      }
    }
    //学生
    if (user.getRole().equals(UserRoleEnum.STUDENT.getRole())) {
      if (Pattern.matches(teacher, url) || Pattern.matches(admin, url)) {
        try {
          response.sendRedirect(request.getContextPath() + "/");
          return false;
        } catch (IOException e) {
          e.printStackTrace();
        }
      } else {
        return true;
      }
    }
    //主任
    if (user.getRole().equals(UserRoleEnum.DIRECTOR.getRole())) {
      if (Pattern.matches(admin, url)) {
        try {
          response.sendRedirect(request.getContextPath() + "/");
        } catch (IOException e) {
          e.printStackTrace();
        }
        return false;
      } else {
        return true;
      }
    }
    return true;
  }
}

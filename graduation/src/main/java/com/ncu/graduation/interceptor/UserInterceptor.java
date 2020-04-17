package com.ncu.graduation.interceptor;

import com.ncu.graduation.vo.UserVO;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author ：pierce.gan
 * @date ：Created in 2020/3/17 20:03
 * @description：登录拦截
 * @modified By：
 * @version: 0.0.1
 */
@Slf4j
@Component
public class UserInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    if (user == null){
      try {
        response.sendRedirect(request.getContextPath()+"/");
        return false;
      } catch (IOException e) {
        log.error("[{}] redirect fail", UserInterceptor.class);
      }
    }
    return true;
  }
}

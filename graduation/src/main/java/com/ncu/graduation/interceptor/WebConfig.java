package com.ncu.graduation.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @author     ：pierce.gan
 * @date       ：Created in 2020/4/4 17:17
 * @description：拦截器web配置
 * @modified By：
 * @version    ：0.0.2
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  private UserRoleInterceptor userRoleInterceptor;

  @Autowired
  private UserInterceptor userInterceptor;

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/**")
        .addResourceLocations("classpath:/META-INF/resources/")
        .addResourceLocations("classpath:/resources/")
        .addResourceLocations("classpath:/static/")
        .addResourceLocations("classpath:/public/");
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(userInterceptor).addPathPatterns("/**")
        .excludePathPatterns("/login")
        .excludePathPatterns("/css/**")
        .excludePathPatterns("/js/**")
        .excludePathPatterns("/images/**")
        .excludePathPatterns("/verifyCode")
        .excludePathPatterns("/fonts/**")
        .excludePathPatterns("/druid/*")
    ;
    registry.addInterceptor(userRoleInterceptor);
  }


}

package com.ncu.graduation.aop;

import com.alibaba.fastjson.JSON;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmDocumentError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.service.ProjectService;
import com.ncu.graduation.util.JedisOp;
import com.ncu.graduation.vo.UserVO;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.servlet.http.HttpSession;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：grh
 * @date ：Created in 2020/4/17 13,42
 * @description：超时拦截
 * @modified By：
 * @version, 0.0.1
 */
@Aspect
@Component
public class OverTimeInterceptor {

  @Autowired
  private JedisOp jedisOp;

  @Autowired
  private ProjectService projectService;

  private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Around("execution(* com.ncu.graduation.controller.ProjectController.applyProject(..))")
  @ResponseBody
  public ResultDTO projectInterceptor(ProceedingJoinPoint jp) {
    HttpSession session = (HttpSession) jp.getArgs()[0];
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectPlan projectPlan = JSON
        .parseObject(jedisOp.get(user.getSchoolYear()), ProjectPlan.class);
    if (projectPlan == null) {
      projectPlan = projectService.getProjectPlan(user.getSchoolYear());
      jedisOp.set(user.getSchoolYear(), JSON.toJSONString(projectPlan));
    }
    String[] time = projectPlan.getProjectApplyTime().split(",");
    String endTime = time[1];
    String startTime = time[0];

    if (LocalDateTime.now().isAfter(LocalDateTime.parse(endTime,formatter)) || LocalDateTime.now()
        .isBefore(LocalDateTime.parse(startTime,formatter))) {
      return ResultDTO.errorOf(EmProjectError.PROJECT_APPLY_IS_NOT_TIME);
    } else {
      try {
        return (ResultDTO) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR);
  }

  @Around("execution(* com.ncu.graduation.controller.ProjectSelectController.projectSelect(..))")
  @ResponseBody
  public ResultDTO projectSelectInterceptor(ProceedingJoinPoint jp) {
    HttpSession session = (HttpSession) jp.getArgs()[0];
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectPlan projectPlan = JSON
        .parseObject(jedisOp.get(user.getSchoolYear()), ProjectPlan.class);
    if (projectPlan == null) {
      projectPlan = projectService.getProjectPlan(user.getSchoolYear());
      jedisOp.set(user.getSchoolYear(), JSON.toJSONString(projectPlan));
    }
    String[] time = projectPlan.getProjectSelectTime().split(",");
    String endTime = time[1];
    String startTime = time[0];
    if (LocalDateTime.now().isAfter(LocalDateTime.parse(endTime,formatter)) || LocalDateTime.now()
        .isBefore(LocalDateTime.parse(startTime,formatter))) {
      return ResultDTO.errorOf(EmProjectError.PROJECT_SELECT_IS_NOT_TIME);
    } else {
      try {
        return (ResultDTO) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR);
  }

  @Around("execution(* com.ncu.graduation.controller.TaskBookController.submitTaskBook(..))")
  @ResponseBody
  public ResultDTO taskBookInterceptor(ProceedingJoinPoint jp) {
    HttpSession session = (HttpSession) jp.getArgs()[0];
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectPlan projectPlan = JSON
        .parseObject(jedisOp.get(user.getSchoolYear()), ProjectPlan.class);
    if (projectPlan == null) {
      projectPlan = projectService.getProjectPlan(user.getSchoolYear());
      jedisOp.set(user.getSchoolYear(), JSON.toJSONString(projectPlan));
    }
    String[] time = projectPlan.getTaskBookTime().split(",");
    String endTime = time[1];
    String startTime = time[0];
    if (LocalDateTime.now().isAfter(LocalDateTime.parse(endTime,formatter)) || LocalDateTime.now()
        .isBefore(LocalDateTime.parse(startTime,formatter))) {
      return ResultDTO.errorOf(EmDocumentError.TASK_SUBMIT_IS_NOT_TIME);
    } else {
      try {
        return (ResultDTO) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR);
  }

  @Around("execution(* com.ncu.graduation.controller.OpenReportController.submitOpenReport(..))")
  @ResponseBody
  public ResultDTO openReportInterceptor(ProceedingJoinPoint jp) {
    HttpSession session = (HttpSession) jp.getArgs()[0];
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectPlan projectPlan = JSON
        .parseObject(jedisOp.get(user.getSchoolYear()), ProjectPlan.class);
    if (projectPlan == null) {
      projectPlan = projectService.getProjectPlan(user.getSchoolYear());
      jedisOp.set(user.getSchoolYear(), JSON.toJSONString(projectPlan));
    }
    String[] time = projectPlan.getOpenReportTime().split(",");
    String endTime = time[1];
    String startTime = time[0];
    if (LocalDateTime.now().isAfter(LocalDateTime.parse(endTime,formatter)) || LocalDateTime.now()
        .isBefore(LocalDateTime.parse(startTime,formatter))) {
      return ResultDTO.errorOf(EmDocumentError.OPENREPORT_SUBMIT_IS_NOT_TIME);
    } else {
      try {
        return (ResultDTO) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR);
  }

  @Around("execution(* com.ncu.graduation.controller.ForeignLiteratureController.submitForeignLiterature(..))")
  @ResponseBody
  public ResultDTO ForeignLiteratureInterceptor(ProceedingJoinPoint jp) {
    HttpSession session = (HttpSession) jp.getArgs()[0];
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectPlan projectPlan = JSON
        .parseObject(jedisOp.get(user.getSchoolYear()), ProjectPlan.class);
    if (projectPlan == null) {
      projectPlan = projectService.getProjectPlan(user.getSchoolYear());
      jedisOp.set(user.getSchoolYear(), JSON.toJSONString(projectPlan));
    }
    String[] time = projectPlan.getForeignLiteratureTime().split(",");
    String endTime = time[1];
    String startTime = time[0];
    if (LocalDateTime.now().isAfter(LocalDateTime.parse(endTime,formatter)) || LocalDateTime.now()
        .isBefore(LocalDateTime.parse(startTime,formatter))) {
      return ResultDTO.errorOf(EmDocumentError.FOREIGNLITERATURE_SUBMIT_IS_NOT_TIME);
    } else {
      try {
        return (ResultDTO) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR);
  }

  @Around("execution(* com.ncu.graduation.controller.ThesisController.submitThesis(..))")
  @ResponseBody
  public ResultDTO ThesisInterceptor(ProceedingJoinPoint jp) {
    HttpSession session = (HttpSession) jp.getArgs()[0];
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectPlan projectPlan = JSON
        .parseObject(jedisOp.get(user.getSchoolYear()), ProjectPlan.class);
    if (projectPlan == null) {
      projectPlan = projectService.getProjectPlan(user.getSchoolYear());
      jedisOp.set(user.getSchoolYear(), JSON.toJSONString(projectPlan));
    }
    String[] time = projectPlan.getThesisTime().split(",");
    String endTime = time[1];
    String startTime = time[0];
    if (LocalDateTime.now().isAfter(LocalDateTime.parse(endTime,formatter)) || LocalDateTime.now()
        .isBefore(LocalDateTime.parse(startTime,formatter))) {
      return ResultDTO.errorOf(EmDocumentError.THESIS_SUBMIT_IS_NOT_TIME);
    } else {
      try {
        return (ResultDTO) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR);
  }
}

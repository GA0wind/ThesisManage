package com.ncu.graduation.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.ncu.graduation.dto.ProjectPlanDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.error.RedirectException;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.util.JedisOp;
import com.ncu.graduation.vo.UserVO;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：grh
 * @date ：Created in 2020/4/17 10:57
 * @description：Redis缓存
 * @modified By：
 * @version: 0.0.1
 */
@Aspect
@Component
public class RedisCache {

  @Autowired
  private JedisOp jedisOp;

  @Autowired
  private ProjectPlanMapper projectPlanMapper;


  /**
   * 将老师所拥有的有效课题存入redis
   */
  @Around("execution(* com.ncu.graduation.service.ProjectService.getTeaProject(..))")
  public Object beforeGetTeaProject(ProceedingJoinPoint jp) {
    Object[] args = jp.getArgs();
    UserVO user = (UserVO) args[0];
    Map projectMap = new HashMap<>();
    //老师和学年作为key
    if (Boolean.TRUE.equals(jedisOp.exists(user.getAccountNo() + user.getSchoolYear()))) {
      projectMap = JSON.parseObject(jedisOp.get(user.getAccountNo() + user.getSchoolYear()),
          new TypeReference<Map<String, ProjectSelectResult>>() {
          });
    } else {
      try {
        projectMap = (Map) jp.proceed();
        if (projectMap != null && !projectMap.isEmpty()) {
          jedisOp.setex(user.getAccountNo() + user.getSchoolYear(), JSON.toJSONString(projectMap));
        }
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return projectMap;
  }

  /**
   * 将学生所拥有的课题存入redis
   */
  @Around("execution(* com.ncu.graduation.service.ProjectService.getStuProject(..))")
  public Object beforeGetStuProject(ProceedingJoinPoint jp) {
    Object[] args = jp.getArgs();
    UserVO user = (UserVO) args[0];
    ProjectApply stuProject = new ProjectApply();
    if (Boolean.TRUE.equals(jedisOp.exists(user.getAccountNo()))) {
      stuProject = JSON.parseObject(jedisOp.get(user.getAccountNo()), ProjectApply.class);
    } else {
      try {
        stuProject = (ProjectApply) jp.proceed();
        if (stuProject != null) {
          jedisOp.setex(user.getAccountNo(), JSON.toJSONString(stuProject));
        }
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    return stuProject;
  }

  /**
   * 存课题计划进入redis便于查找
   */
  @Around("execution(* com.ncu.graduation.service.ProjectService.getProjectPlan(..))")
  public Object beforeGetProjectPlan(ProceedingJoinPoint jp) {
    Object[] args = jp.getArgs();
    String schoolYear = (String) args[0];
    ProjectPlan projectPlan = null;
    if (Boolean.TRUE.equals(jedisOp.exists(schoolYear))) {
      projectPlan = JSON.parseObject(jedisOp.get(schoolYear), ProjectPlan.class);
    } else {
      try {
        projectPlan = (ProjectPlan) jp.proceed();
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
    if (projectPlan== null ){
      throw new CommonException(EmCommonError.UNKNOWN_ERROR);
    }
    return projectPlan;
  }


  /**
   * 更新毕设计划后, 将数据存一份到redis中
   */
  @Around("execution(* com.ncu.graduation.service.ProjectService.setProjectPlan(..))")
  public void beforeSetProjectPlan(ProceedingJoinPoint jp) {
    try {
      jp.proceed();
    } catch (Throwable throwable) {
      throwable.printStackTrace();
    }
    ProjectPlanDTO projectPlanDTO = (ProjectPlanDTO) jp.getArgs()[0];
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.createCriteria().andSchoolYearEqualTo(projectPlanDTO.getSchoolYear());
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    jedisOp.set(projectPlans.get(0).getSchoolYear(), JSON.toJSONString(projectPlans.get(0)));
  }

}

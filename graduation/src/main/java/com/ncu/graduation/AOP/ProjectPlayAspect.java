package com.ncu.graduation.AOP;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author ：grh
 * @date ：Created in 2020/3/23 15:30
 * @description：管理员设置新学年时创建定时器自动分配盲审人员
 * @modified By：
 * @version: 0.0.1
 */
//@Aspect
//@Component
//public class ProjectPlayAspect {
//
//  @Before("execution(* com.ncu.graduation.service.ProjectService.apply())")
//  public void log(){
//    //logger.info("before method log done"+ AopContext.currentProxy().getClass());
//    logger.info("before method log done");
//  }
//
//  //可以通过JoinPoint取到aop的类名，方法参数，方法签名
//  @After("execution(* com.zhihao.miao.service..*.*(..))")
//  public void logAfter(JoinPoint joinPoint){
//    logger.info("after method log done "+joinPoint.getTarget().getClass()+",args="+ Arrays.asList(joinPoint.getArgs())+",method="+joinPoint.getSignature());
//  }
//}

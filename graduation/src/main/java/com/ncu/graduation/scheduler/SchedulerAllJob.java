package com.ncu.graduation.scheduler;

import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.util.CronDateUtils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author ：grh
 * @date ：Created in 2020/4/7 23:52
 * @description：定时任务管理器
 * @modified By：
 * @version: 0.0.1
 */

@Async
@Component
public class SchedulerAllJob {

  private SchedulerFactory schedulerFactoryBean = new StdSchedulerFactory();

  private ProjectPlan projectPlan;

  public void setProjectPlan(ProjectPlan projectPlan) {
    this.projectPlan = projectPlan;
  }

  /*
   * 此处可以注入数据库操作，查询出所有的任务配置
   */

  /**
   * 该方法用来启动所有的定时任务
   */
  public void scheduleJobs() throws SchedulerException {
    String endProjectApplyTime;
    String endOpeningReportTime;
    String endThesisTime;
    if (!StringUtils.isBlank(projectPlan.getProjectApplyTime())) {
      endProjectApplyTime = projectPlan.getProjectApplyTime().split(",")[1];
      startScheduler(ProjectApplyBlindDistribution.class, endProjectApplyTime);
    }
    if (!StringUtils.isBlank(projectPlan.getOpenReportTime())) {
      endOpeningReportTime = projectPlan.getOpenReportTime().split(",")[1];
      startScheduler(OpenReportBlindDistribution.class, endOpeningReportTime);
    }
    if (!StringUtils.isBlank(projectPlan.getThesisTime())) {
      endThesisTime = projectPlan.getThesisTime().split(",")[1];
      startScheduler(ThesisBlindDistribution.class, endThesisTime);
    }
  }

  private void startScheduler(Class t, String endTime)
      throws SchedulerException {
    //设置时间
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    Date date = new Date();
    try {
      date = format.parse(endTime);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    //如果已经过了时间, 就跳过不设置
    if (date.after(new Date())) {
      //获取scheduler
      Scheduler scheduler = schedulerFactoryBean.getScheduler();
      //构建任务
      JobDetail jobDetail1 = JobBuilder.newJob(t)
          .withIdentity(t.getName(), "group1")
          .build();

      //根据时间构建触发器
      String cron = CronDateUtils.getCron(date);
//      String cron = "0 0/3 * * * ?";
      CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
      CronTrigger cronTrigger = TriggerBuilder.newTrigger()
          .withIdentity(t.getName(), "group1")
          .withSchedule(scheduleBuilder).build();

      //判断是否已经有这个触发器的key
      TriggerKey triggerKey = new TriggerKey(t.getName(),
          "group1");
      //如果已经有了, 就更新触发器
      if (scheduler.checkExists(triggerKey)) {
        scheduler.rescheduleJob(triggerKey, cronTrigger);
      } else {
        //没有就将该任务和触发器放入开始调度
        scheduler.scheduleJob(jobDetail1, cronTrigger);
        scheduler.start();
      }
      System.out.println(triggerKey);
    }
  }
}

package com.ncu.graduation.scheduler;

import com.ncu.graduation.enums.CollegeEnum;
import com.ncu.graduation.mapper.OpenReportMapper;
import com.ncu.graduation.mapper.ProjectApplyExtMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.TeacherExtMapper;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportExample;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.util.BlindDistribution;
import com.ncu.graduation.util.BlindDistribution.ProjectTwotuple;
import com.ncu.graduation.util.BlindDistribution.TeacherTwotuple;
import com.ncu.graduation.util.SpringContextUtil;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.annotation.Configuration;

/**
 * @author ：grh
 * @date ：Created in 2020/3/23 14:03
 * @description：用于盲审的评审人分配
 * @modified By：
 * @version:
 */

@Configuration
public class OpenReportBlindDistribution implements Job {

  private ProjectPlanMapper projectPlanMapper;


  private OpenReportMapper openReportMapper;

  private TeacherExtMapper teacherExtMapper;

  private ProjectApplyExtMapper projectApplyExtMapper;


  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    //注入实例
    projectPlanMapper = SpringContextUtil.getBean(ProjectPlanMapper.class);
    openReportMapper = SpringContextUtil.getBean(OpenReportMapper.class);
    teacherExtMapper = SpringContextUtil.getBean(TeacherExtMapper.class);
    projectApplyExtMapper = SpringContextUtil.getBean(ProjectApplyExtMapper.class);

    //获取课题计划
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.setOrderByClause("id desc");
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);

    //学院循环
    for (CollegeEnum value : CollegeEnum.values()) {
      //获取该学年该学院有效课题
      ProjectPlan projectPlan = projectPlans.get(0);
      List<ProjectTwotuple> projectNoAndTno = projectApplyExtMapper
          .getSelectedProjectNoAndTno(projectPlan.getSchoolYear(), value.getCollegeName());
      //获取学年学院教师
      List<TeacherTwotuple> teacherTwotuples = teacherExtMapper
          .selectTnoAndStudentNum("%" + projectPlan.getSchoolYear() + "%", value.getCollegeName());
      //初始化数据并分配盲审
      BlindDistribution blindDistribution = new BlindDistribution();
      blindDistribution.setProject(projectNoAndTno);
      blindDistribution.setTeacher(teacherTwotuples);
      //分配
      List<ProjectTwotuple> twotuples = blindDistribution.distribution();
      //写入开题报告
      twotuples.forEach((k) -> {
        OpenReport openReport = new OpenReport();
        openReport.setPno(k.getPno());
        openReport.setBlindTrialNo(k.getTno());
        OpenReportExample openReportExample = new OpenReportExample();
        openReportExample.createCriteria().andPnoEqualTo(k.getPno());
        openReportMapper.updateByExampleSelective(openReport, openReportExample);
      });
    }
  }

}

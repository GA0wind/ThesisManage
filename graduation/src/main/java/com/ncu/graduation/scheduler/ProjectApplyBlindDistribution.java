package com.ncu.graduation.scheduler;

import com.ncu.graduation.enums.CollegeEnum;
import com.ncu.graduation.mapper.ProjectApplyExtMapper;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.TeacherExtMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
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
public class ProjectApplyBlindDistribution implements Job {

  private ProjectPlanMapper projectPlanMapper;

  private ProjectApplyMapper projectApplyMapper;

  private ProjectApplyExtMapper projectApplyExtMapper;

  private TeacherExtMapper teacherExtMapper;


  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    projectPlanMapper = SpringContextUtil.getBean(ProjectPlanMapper.class);
    projectApplyMapper = SpringContextUtil.getBean(ProjectApplyMapper.class);
    teacherExtMapper = SpringContextUtil.getBean(TeacherExtMapper.class);
    projectApplyExtMapper = SpringContextUtil.getBean(ProjectApplyExtMapper.class);

    //获取毕设计划
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.setOrderByClause("id desc");
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    ProjectPlan projectPlan = projectPlans.get(0);

    //学院循环
    for (CollegeEnum value : CollegeEnum.values()) {
      //获取待审课题
      List<ProjectTwotuple> projectNoAndTno = projectApplyExtMapper
          .getProjectApplyNoAndTno(projectPlan.getSchoolYear(), value.getCollegeName());
      //获取学院当前学年教师
      List<TeacherTwotuple> teacherTwotuples = teacherExtMapper
          .selectTnoAndStudentNum("%" + projectPlan.getSchoolYear() + "%", value.getCollegeName());
      //初始化并盲分配
      BlindDistribution blindDistribution = new BlindDistribution();
      blindDistribution.setProject(projectNoAndTno);
      blindDistribution.setTeacher(teacherTwotuples);
      List<ProjectTwotuple> twotuples = blindDistribution.avgDistribution();

      twotuples.forEach((k) -> {
        ProjectApply projectApply = new ProjectApply();
        projectApply.setPno(k.getPno());
        projectApply.setBlindTrialNo(k.getTno());
        ProjectApplyExample projectApplyExample = new ProjectApplyExample();
        projectApplyExample.createCriteria().andPnoEqualTo(k.getPno());
        projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
      });
    }
  }
}

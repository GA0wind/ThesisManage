package com.ncu.graduation.scheduler;

import com.ncu.graduation.enums.CollegeEnum;
import com.ncu.graduation.mapper.ExamGroupMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.ThesisMapper;
import com.ncu.graduation.model.ExamGroup;
import com.ncu.graduation.model.ExamGroupExample;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.StudentExample;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import com.ncu.graduation.util.BlindDistribution;
import com.ncu.graduation.util.BlindDistribution.ProjectTwotuple;
import com.ncu.graduation.util.BlindDistribution.TeacherTwotuple;
import com.ncu.graduation.util.SpringContextUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @author ：grh
 * @date ：Created in 2020/3/23 14:03
 * @description：用于盲审的评审人分配
 * @modified By：
 * @version:
 */

@Configuration
@Component
public class ThesisBlindDistribution implements Job {

  private ProjectPlanMapper projectPlanMapper;

  private ProjectSelectResultMapper projectSelectResultMapper;

  private ThesisMapper thesisMapper;

  private ExamGroupMapper examGroupMapper;

  private StudentMapper studentMapper;

  @Override
  public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    projectPlanMapper = SpringContextUtil.getBean(ProjectPlanMapper.class);
    projectSelectResultMapper = SpringContextUtil.getBean(ProjectSelectResultMapper.class);
    thesisMapper = SpringContextUtil.getBean(ThesisMapper.class);
    examGroupMapper = SpringContextUtil.getBean(ExamGroupMapper.class);
    studentMapper = SpringContextUtil.getBean(StudentMapper.class);

    //获取最新的学年
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.setOrderByClause("id desc");
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    ProjectPlan projectPlan = projectPlans.get(0);

    //学院循环
    for (CollegeEnum value : CollegeEnum.values()) {
      //获取学院内的答辩小组
      ExamGroupExample examGroupExample = new ExamGroupExample();
      examGroupExample.createCriteria().andSchoolYearEqualTo(projectPlan.getSchoolYear())
          .andCollegeEqualTo(value.getCollegeName());
      List<ExamGroup> groups = examGroupMapper.selectByExample(examGroupExample);
      groups.forEach((k) -> {
        //获取该答辩小组对应的学生
        StudentExample example = new StudentExample();
        example.createCriteria().andGroupNumEqualTo(k.getGroupNum());
        List<Student> students = studentMapper.selectByExample(example);
        List<String> snos = new ArrayList<>();
        students.forEach((l) -> {
          snos.add(l.getSno());
        });
        //查找学生对应的课题号
        ProjectSelectResultExample resultExample = new ProjectSelectResultExample();
        resultExample.createCriteria().andSnoIn(snos);
        List<ProjectSelectResult> projectSelectResults = projectSelectResultMapper
            .selectByExample(resultExample);
        //整理课题和老师对应关系
        List<ProjectTwotuple> projectNoAndTno = new ArrayList<>();
        for (ProjectSelectResult projectSelectResult : projectSelectResults) {
          ProjectTwotuple projectTwotuple = new ProjectTwotuple();
          projectTwotuple.setTno(projectSelectResult.getTno());
          projectTwotuple.setPno(projectSelectResult.getPno());
          projectNoAndTno.add(projectTwotuple);
        }
        //整理小组内老师数据
        List<String> tnos = new ArrayList<>();
        tnos.add(k.getLeaderNo());
        tnos.addAll(Arrays.asList(k.getMemberNo().split(",")));
        List<TeacherTwotuple> teacherTwotuples = new ArrayList<>();
        tnos.forEach((l) -> {
          TeacherTwotuple teacherTwotuple = new TeacherTwotuple();
          teacherTwotuple.setTno(l);
          teacherTwotuple.setLeadStudentNum(0);
          teacherTwotuples.add(teacherTwotuple);
        });
        //初始化盲审对象, 平均分配
        BlindDistribution blindDistribution = new BlindDistribution();
        blindDistribution.setProject(projectNoAndTno);
        blindDistribution.setTeacher(teacherTwotuples);

        List<ProjectTwotuple> twotuples = blindDistribution.avgDistribution();
        twotuples.forEach((l) -> {
          Thesis thesis = new Thesis();
          thesis.setPno(l.getPno());
          thesis.setBlindTrialNo(l.getTno());
          ThesisExample thesisExample = new ThesisExample();
          thesisExample.createCriteria().andPnoEqualTo(l.getPno());
          thesisMapper.updateByExampleSelective(thesis, thesisExample);
        });
      });
    }
  }
}

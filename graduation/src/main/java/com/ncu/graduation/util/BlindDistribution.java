package com.ncu.graduation.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author ：grh
 * @date ：Created in 2020/3/23 20:35
 * @description：盲审分配
 * @modified By：
 * @version: 0.0.1
 */

@Component
@Data
public class BlindDistribution {


  /**
   * 老师工号, 审核数量
   */
  private List<TeacherTwotuple> teacher;

  /**
   * 课程编号, 负责该课题的老师工号
   */
  private List<ProjectTwotuple> project;

  @Data
  public static class TeacherTwotuple {

    String tno;
    Integer leadStudentNum;
  }

  @Data
  public static class ProjectTwotuple {

    String pno;
    String tno;
  }


  //根据指导人数盲审
  public List<ProjectTwotuple> distribution() {
    Random random = new Random();
    for (int i = 0; i < project.size(); i++) {
      int index = random.nextInt(teacher.size());
      while (teacher.get(index).getTno().equals(project.get(i).getTno())
          || teacher.get(index).getLeadStudentNum() == 0) {
        index = random.nextInt(teacher.size());
      }
      teacher.get(index).setLeadStudentNum(teacher.get(index).getLeadStudentNum() - 1);
      project.get(i).tno = teacher.get(index).getTno();
    }
    return project;
  }


  //课题申请的盲审, 随机均分
  public List<ProjectTwotuple> avgDistribution() {
    List<ProjectTwotuple> result = new ArrayList<>();
    int i = 0;
    Random random = new Random();
    for (int j = project.size(); j > 0 ; j--,i++) {
      if (i == teacher.size()){
        i = 0;
      }
      int index = random.nextInt(project.size());
      int time = 0;
      while (project.get(index).getTno().equals(teacher.get(i).getTno())){
        index = random.nextInt(project.size());
        time++;
        if (time >= 4){
          i++;
          time = 0;
        }
      }
      ProjectTwotuple projectTwotuple= project.get(index);
      projectTwotuple.setTno(teacher.get(i).getTno());
      result.add(projectTwotuple);
      project.remove(index);
    }
    return result;
  }




  /**
   * 分配算法： 获取所有课题数量，除以老师数量得到一个中间值，再加上浮动范围，随机生成老师评审个数，个数多于课题数，就不管，个数少于课题数，然后分配课题
   */

  public void init(List<TeacherTwotuple> teacher, List<ProjectTwotuple> project) {
//    this.teacher = teacher;
//    this.project = project;
//    int totalCount = project.size();
//    int avg = totalCount / teacher.size();
//    Random random = new Random();
//    for (TeacherTwotuple teacherTwotuple : teacher) {
//      teacherTwotuple.leadStudentNum = random.nextInt(offset * 2) + avg - 2;
//      totalCount -= teacherTwotuple.leadStudentNum;
//    }
//    if (totalCount > 0) {
//      for (int i = 0; i < totalCount; i++) {
//        int x = random.nextInt(teacher.size());
//        teacher.get(x).leadStudentNum++;
//        totalCount--;
//      }
//    }
  }


}

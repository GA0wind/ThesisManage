package com.ncu.graduation.service;

import com.ncu.graduation.dto.LoginDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.*;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private TeacherMapper teacherMapper;

  @Autowired
  private ProjectPlanMapper projectPlanMapper;

  public List<String> getSchoolYear() {
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.setOrderByClause("id desc");
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    List<String> schoolYears = new ArrayList<>();
    for (ProjectPlan projectPlan : projectPlans) {
      schoolYears.add(projectPlan.getSchoolYear());
    }
    return schoolYears;
  }


  public Object login(LoginDTO loginDTO) {
    if (UserRoleEnum.ADMIN.getRole().equals(loginDTO.getRole()) || UserRoleEnum.TEACHER.getRole()
        .equals(loginDTO.getRole())) {
      TeacherExample teacherExample = new TeacherExample();
      teacherExample.createCriteria().andTnoEqualTo(loginDTO.getAccountNo())
          .andPasswordEqualTo(loginDTO.getAccountPwd());
      List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
      if (teachers == null || teachers.isEmpty()) {
        throw new CommonException(EmUserOperatorError.USER_LOGIN_FAIL);
      }
      return teachers.get(0);
    } else if (UserRoleEnum.STUDENT.getRole().equals(loginDTO.getRole())) {
      StudentExample studentExample = new StudentExample();
      studentExample.createCriteria().andSnoEqualTo(loginDTO.getAccountNo())
          .andPasswordEqualTo(loginDTO.getAccountPwd());
      List<Student> students = studentMapper.selectByExample(studentExample);
      if (students == null || students.isEmpty()) {
        throw new CommonException(EmUserOperatorError.USER_LOGIN_FAIL);
      }
      return students.get(0);
    }
    return null;
  }



}

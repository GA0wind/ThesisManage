package com.ncu.graduation.service;

import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectInfoDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.Project;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.ProjectExample;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.StudentExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.model.TeacherExample;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.UserVO;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 21:13
 * @description：课题service层
 * @modified By：
 * @version: 0.0.1
 */

@Service
public class ProjectService {

  @Autowired
  private ProjectMapper projectMapper;
  @Autowired
  private ProjectApplyMapper projectApplyMapper;
  @Autowired
  private TeacherMapper teacherMapper;
  @Autowired
  private StudentMapper studentMapper;

  public List<Project> getMyProject(UserVO user) {

    ProjectExample projectExample = new ProjectExample();
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())){
      projectExample.createCriteria().andTeacherNoEqualTo(user.getAccountNo())
          .andSchoolYearEqualTo(user.getSchoolYear());
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())){
      projectExample.createCriteria().andStudentNoEqualTo(user.getAccountNo())
          .andSchoolYearEqualTo(user.getSchoolYear());
    }
    return projectMapper.selectByExample(projectExample);
  }

  public ProjectInfoDTO getProject(String projectNo) {

    //查找课题
    ProjectExample projectExample = new ProjectExample();
    projectExample.createCriteria().andNoEqualTo(projectNo);
    List<Project> projects = projectMapper.selectByExampleWithBLOBs(projectExample);
    if (projects == null || projects.isEmpty()){
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    //复制字段
    ProjectInfoDTO projectInfoDTO = new ProjectInfoDTO();
    BeanUtils.copyProperties(projects.get(0),projectInfoDTO);
    //修改文件路径
    String[] strings = projectInfoDTO.getFilePath().split("/");
    projectInfoDTO.setFilePath(strings[strings.length - 1]);
    //查看课题审核情况
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andProjectNoEqualTo(projectNo);
    List<ProjectApply> projectApplies = projectApplyMapper.selectByExample(projectApplyExample);
    if (projectApplies != null && projectApplies.size() == 1){
      projectInfoDTO.setProjectApply(projectApplies.get(0));
    }
    //判断有没有老师
    if (projects.get(0).getTeacherNo() != null){
      TeacherExample teacherExample = new TeacherExample();
      teacherExample.createCriteria().andTnoEqualTo(projects.get(0).getTeacherNo());
      List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
      projectInfoDTO.setTeacher(teachers.get(0));
    }
    //判断有没有学生
    if (projects.get(0).getStudentNo() != null) {
      StudentExample studentExample = new StudentExample();
      studentExample.createCriteria().andSnoEqualTo(projects.get(0).getStudentNo());
      List<Student> students = studentMapper.selectByExample(studentExample);
      projectInfoDTO.setStudent(students.get(0));
    }
    return projectInfoDTO;
  }

  @Transactional
  public void apply(ProjectApplyDTO projectApplyDTO, UserVO user) {
    //构建持久层project
    Project project = new Project();
    project.setName(projectApplyDTO.getName());
    project.setContent(projectApplyDTO.getDesc());
    String filePath = FileSave.fileSave(projectApplyDTO.getFile(), FileTypeEnum.PROJECT);
    project.setFilePath(filePath);
    project.setType(projectApplyDTO.getType());
    project.setGmtCreate(new Date());
    project.setGmtModified(project.getGmtCreate());
    project.setNo(UUID.randomUUID().toString().replaceAll("-", ""));
    project.setSchoolYear(user.getSchoolYear());
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())){
      project.setStudentNo(user.getAccountNo());
    }
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())){
      project.setTeacherNo(user.getAccountNo());
    }
    project.setTags("");
    projectMapper.insertSelective(project);
    //构建持久层projectApply
    ProjectApply projectApply = new ProjectApply();
    projectApply.setGmtCreate(project.getGmtCreate());
    projectApply.setGmtModified(project.getGmtModified());
    projectApply.setProjectNo(project.getNo());
    projectApply.setSchoolYear(project.getSchoolYear());
    projectApplyMapper.insertSelective(projectApply);
  }

  @Transactional
  public void revoke(String no) {
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andProjectNoEqualTo(no);
    projectApplyMapper.deleteByExample(projectApplyExample);

    ProjectExample projectExample = new ProjectExample();
    projectApplyExample.createCriteria().andProjectNoEqualTo(no);
    projectMapper.deleteByExample(projectExample);
  }
}

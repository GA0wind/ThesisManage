package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.LoginDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.UserAddDTO;
import com.ncu.graduation.dto.UserSearchDTO;
import com.ncu.graduation.enums.CollegeEnum;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmFileError;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.mapper.ExamGroupMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.StudentExtMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherExtMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.*;
import com.ncu.graduation.util.BlindDistribution.TeacherTwotuple;
import com.ncu.graduation.util.ExcelOperate;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserService {

  @Autowired
  private StudentMapper studentMapper;
  @Autowired
  private StudentExtMapper studentExtMapper;
  @Autowired
  private TeacherMapper teacherMapper;
  @Autowired
  private TeacherExtMapper teacherExtMapper;

  @Autowired
  private ProjectPlanMapper projectPlanMapper;

  @Autowired
  private ExamGroupMapper examGroupMapper;

  /**
   * 获取学年
   * @return
   */
  public List<String> getSchoolYear() {
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.setOrderByClause("id");
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    List<String> schoolYears = new ArrayList<>();
    for (ProjectPlan projectPlan : projectPlans) {
      schoolYears.add(projectPlan.getSchoolYear());
    }
    return schoolYears;
  }

  /**
   * 登录
   * @param loginDTO
   * @return
   */
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


  public Integer updatePwd(UserVO user, String newPwd) {
    int result = 0;
    if (UserRoleEnum.ADMIN.getRole().equals(user.getRole()) || UserRoleEnum.TEACHER.getRole()
        .equals(user.getRole())) {
      Teacher teacher = new Teacher();
      teacher.setTno(user.getAccountNo());
      teacher.setPassword(newPwd);
      TeacherExample teacherExample = new TeacherExample();
      teacherExample.createCriteria().andTnoEqualTo(user.getAccountNo());
      result = teacherMapper.updateByExampleSelective(teacher, teacherExample);
    } else if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      Student student = new Student();
      student.setSno(user.getAccountNo());
      student.setPassword(newPwd);
      StudentExample studentExample = new StudentExample();
      studentExample.createCriteria().andSnoEqualTo(user.getAccountNo());
      result = studentMapper.updateByExampleSelective(student, studentExample);
    }
    return result;
  }

  public ResultDTO addOrUpdateUser(UserAddDTO userAddDTO, UserVO user) {
    int result;
    if (UserRoleEnum.STUDENT.getRole().equals(userAddDTO.getRole())) {
      Student student = new Student();
      student.setSno(userAddDTO.getUserNo());
      student.setSname(userAddDTO.getName());
      student.setMajor(userAddDTO.getMajor());
      student.setCollege(userAddDTO.getCollege());
      student.setGradeClass(userAddDTO.getGradeClass());
      student.setSchoolYear(user.getSchoolYear());
      student.setPassword(userAddDTO.getPassword());
      if (userAddDTO.getIsModify() != 1) {
        student.setGmtCreate(new Date());
        student.setGmtModified(student.getGmtCreate());
        result = studentMapper.insert(student);
      } else {
        StudentExample studentExample = new StudentExample();
        studentExample.createCriteria().andSnoEqualTo(userAddDTO.getUserNo());
        result = studentMapper.updateByExampleSelective(student, studentExample);
      }
    } else {
      Teacher teacher = new Teacher();
      teacher.setTno(userAddDTO.getUserNo());
      teacher.setTname(userAddDTO.getName());
      teacher.setCollege(userAddDTO.getCollege());
      teacher.setLeadStudentNum(userAddDTO.getLeadStudentNum());
      teacher.setPassword(userAddDTO.getPassword());
      teacher.setRole(userAddDTO.getRole());

      //判断是否是修改
      if (userAddDTO.getIsModify() != 1) {
        teacher.setSchoolYear(user.getSchoolYear());
        teacher.setGmtCreate(new Date());
        teacher.setGmtModified(teacher.getGmtCreate());
        result = teacherMapper.insert(teacher);
      } else {
        TeacherExample teacherExample = new TeacherExample();
        teacherExample.createCriteria().andTnoEqualTo(teacher.getTno()).andSchoolYearLike("%"+user.getSchoolYear()+"%");
        List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
        if (teachers == null || teachers.isEmpty()){
          teacher.setSchoolYear(user.getSchoolYear()+",");
          result = teacherExtMapper.updateByTno(teacher);
        }
        else{
          teacher.setSchoolYear(null);
          result = teacherExtMapper.updateByTno(teacher);
        }
      }
    }
    if (result == 0) {
      return ResultDTO.errorOf(EmCommonError.UNKNOWN_ERROR.getErrCode(), "操作失败, 请检查参数是否合法");
    }
    return ResultDTO.okOf();
  }

  @Transactional
  public void addStuByExcel(MultipartFile excel, UserVO user) {
    String fileName = FileSave.fileSave(excel, FileTypeEnum.BULLETIN);
    Map<String, Object> students = ExcelOperate.readExcel(fileName, "StuExl");
    if (students == null || students.isEmpty()) {
      throw new CommonException(EmFileError.FILE_IS_EMPTY, "未从文件中取得数据");
    }
    students.forEach((k, l) -> {
      Student student = (Student) l;
      student.setSchoolYear(user.getSchoolYear());
      student.setGmtCreate(new Date());
      student.setGmtModified(student.getGmtCreate());
      if (studentMapper.insert(student) != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR,
            "学生数据插入数据库失败" + student.getSno());
      }
    });
    File file = new File(FileTypeEnum.BULLETIN.getPreUrl() + fileName);
    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
      throw new CommonException(EmFileError.FILE_DELETE_FAIL,
          "学生成员文件删除异常, 文件路径" + file.getPath());
    }

  }

  @Transactional
  public void addTeaByExcel(MultipartFile excel, UserVO user) {
    String fileName = FileSave.fileSave(excel, FileTypeEnum.BULLETIN);
    Map<String, Object> teachers = ExcelOperate.readExcel(fileName, "TeaExl");
    if (teachers == null || teachers.isEmpty()) {
      throw new CommonException(EmFileError.FILE_IS_EMPTY, "未从文件中取得数据");
    }
    teachers.forEach((k, l) -> {
      Teacher teacher = (Teacher) l;
      teacher.setSchoolYear(user.getSchoolYear()+",");
      teacher.setGmtModified(new Date());
      TeacherExample teacherExample = new TeacherExample();
      teacherExample.createCriteria().andTnoEqualTo(teacher.getTno()).andSchoolYearLike("%"+user.getSchoolYear()+"%");
      List<Teacher> teacherList = teacherMapper.selectByExample(teacherExample);
      if (teacherList == null || teacherList.isEmpty()){
        teacher.setSchoolYear(user.getSchoolYear()+",");
      }
      else{
        teacher.setSchoolYear(null);
      }

      if (teacherExtMapper.updateByTno(teacher) != 1) {
        teacher.setGmtCreate(new Date());
        teacher.setGmtModified(teacher.getGmtCreate());
        teacher.setSchoolYear(user.getSchoolYear());
        if (teacherMapper.insert(teacher) != 1) {
          throw new CommonException(EmCommonError.UNKNOWN_ERROR,
              "教师数据插入数据库失败" + teacher.getTno());
        }
      }

    });
    File file = new File(FileTypeEnum.BULLETIN.getPreUrl() + fileName);
    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
      throw new CommonException(EmFileError.FILE_DELETE_FAIL,
          "教师成员文件删除异常, 文件路径" + file.getPath());
    }
  }

  @Transactional
  public void setTeaGroup(MultipartFile excel, UserVO user) {
    String fileName = FileSave.fileSave(excel, FileTypeEnum.BULLETIN);
    Map<String, Object> teachers = ExcelOperate.readExcel(fileName, "TeaGroupExl");
    if (teachers == null || teachers.isEmpty()) {
      throw new CommonException(EmFileError.FILE_IS_EMPTY, "未从文件中取得数据");
    }
    //组号 答辩小组
    Map<Integer, ExamGroup> groupMap = new HashMap<>();
    teachers.forEach((k, l) -> {
      //更新教师表数据
      Teacher teacher = (Teacher) l;
      teacher.setGmtModified(new Date());
      TeacherExample example = new TeacherExample();
      example.createCriteria().andTnoEqualTo(teacher.getTno());
      if (teacherMapper.updateByExampleSelective(teacher, example) != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR,
            "教师设置组别插入数据库失败, 教师工号" + teacher.getTno());
      }
      //将组别存入Map中, 判断是否已有该小组, 有就修改小组信息
      if (groupMap.containsKey(teacher.getGroupNum())) {
        //如果是组员
        if ("member".equals(teacher.getGroupRole())) {
          String memberNo = groupMap.get(teacher.getGroupNum()).getMemberNo();
          if (memberNo == null) {
            memberNo = "";
          }
          groupMap.get(teacher.getGroupNum()).setMemberNo(memberNo + "," + teacher.getTno());
        } else if ("leader".equals(teacher.getGroupRole())) {
          groupMap.get(teacher.getGroupNum()).setCollege(teacher.getCollege());
          groupMap.get(teacher.getGroupNum()).setLeaderNo(teacher.getTno());
        }
      } else {
        //判断是否已有该小组, 没有就新增小组信息
        ExamGroup examGroup = new ExamGroup();
        examGroup.setGroupNum(teacher.getGroupNum());
        examGroup.setSchoolYear(user.getSchoolYear());
        examGroup.setCollege(teacher.getCollege());
        if ("member".equals(teacher.getGroupRole())) {
          examGroup.setMemberNo(teacher.getTno());
        } else if ("leader".equals(teacher.getGroupRole())) {
          examGroup.setCollege(teacher.getCollege());
          examGroup.setLeaderNo(teacher.getTno());
        }
        groupMap.put(teacher.getGroupNum(), examGroup);
      }
    });
    //将组别存入数据库
    groupMap.forEach((k, l) -> {
      int insert = examGroupMapper.insert(l);
      if (insert != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR,
            "教师组别 " + l.getGroupNum() + " 插入失败");
      }
    });
    File file = new File(FileTypeEnum.BULLETIN.getPreUrl() + fileName);
    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
      throw new CommonException(EmFileError.FILE_DELETE_FAIL, "教师组别文件删除异常, 文件路径" + file.getPath());
    }

  }

  @Transactional
  public void setStuGroup(MultipartFile excel) {
    String fileName = FileSave.fileSave(excel, FileTypeEnum.BULLETIN);
    Map<String, Object> students = ExcelOperate.readExcel(fileName, "StuGroupExl");
    if (students == null || students.isEmpty()) {
      throw new CommonException(EmFileError.FILE_IS_EMPTY, "未从文件中取得数据");
    }
    students.forEach((k, l) -> {
      Student student = (Student) l;
      student.setGmtModified(new Date());
      StudentExample example = new StudentExample();
      example.createCriteria().andSnoEqualTo(student.getSno());
      if (studentMapper.updateByExampleSelective(student, example) != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR, "学生设置组别插入数据库失败" + student.getSno());
      }
    });
    File file = new File(FileTypeEnum.BULLETIN.getPreUrl() + fileName);
    try {
      Files.delete(file.toPath());
    } catch (IOException e) {
      e.printStackTrace();
      throw new CommonException(EmFileError.FILE_DELETE_FAIL, "学生组别文件删除异常, 文件路径" + file.getPath());
    }

  }

  public PaginationDTO<Student> stuList(int page, int size, UserVO user,
      UserSearchDTO userSearchDTO) {
    userSearchDTO.setRole(null);
    UserSearchDTO searchDTO = new UserSearchDTO();
    if (!StringUtils.isBlank(userSearchDTO.getCollege())){
      searchDTO.setCollege("%"+userSearchDTO.getCollege()+"%");
    }
    if (!StringUtils.isBlank(userSearchDTO.getName())){
      searchDTO.setName("%"+userSearchDTO.getName()+"%");
    }
    if (!StringUtils.isBlank(userSearchDTO.getUserNo())){
      searchDTO.setUserNo("%"+userSearchDTO.getUserNo()+"%");
    }
    PaginationDTO<Student> paginationDTO = new PaginationDTO<>();
    PageMethod.startPage(page,size);
    List<Student> students = studentExtMapper.searchStuList(user.getSchoolYear(),searchDTO);
    PageInfo<Student> studentPageInfo = new PageInfo<>(students);
    paginationDTO.setPagination((int)studentPageInfo.getTotal(),page,size);
    paginationDTO.setData(students);
    return paginationDTO;
  }

  public PaginationDTO<Teacher> teaList(int page, int size, UserVO user,
      UserSearchDTO userSearchDTO) {
    UserSearchDTO searchDTO = new UserSearchDTO();
    if (!StringUtils.isBlank(userSearchDTO.getCollege())){
      searchDTO.setCollege("%"+userSearchDTO.getCollege()+"%");
    }
    if (!StringUtils.isBlank(userSearchDTO.getName())){
      searchDTO.setName("%"+userSearchDTO.getName()+"%");
    }
    if (!StringUtils.isBlank(userSearchDTO.getUserNo())){
      searchDTO.setUserNo("%"+userSearchDTO.getUserNo()+"%");
    }
    if (!StringUtils.isBlank(userSearchDTO.getRole())){
      searchDTO.setRole("%"+userSearchDTO.getRole()+"%");
    }
    PaginationDTO<Teacher> paginationDTO = new PaginationDTO<>();
    PageMethod.startPage(page,size);
    List<Teacher> teachers = teacherExtMapper.searchTeaList("%"+user.getSchoolYear()+"%",searchDTO);
    PageInfo<Teacher> studentPageInfo = new PageInfo<>(teachers);
    paginationDTO.setPagination((int)studentPageInfo.getTotal(),page,size);
    paginationDTO.setData(teachers);
    return paginationDTO;
  }

  public Student getStu(String sno) {
    StudentExample studentExample = new StudentExample();
    studentExample.createCriteria().andSnoEqualTo(sno);
    List<Student> students = studentMapper.selectByExample(studentExample);
    return students.get(0);
  }

  public Teacher getTea(String tno) {
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andTnoEqualTo(tno);
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    return teachers.get(0);
  }

}

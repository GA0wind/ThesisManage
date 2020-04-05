package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectApplyExtMapper;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectExtMapper;
import com.ncu.graduation.mapper.ProjectSelectMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.model.ProjectSelectExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.StudentExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.model.TeacherExample;
import com.ncu.graduation.vo.ProjectSelectVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 23:11
 * @description：师生双选Service
 * @modified By：
 * @version:0.0.1
 */

@Service
public class ProjectSelectService {

  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;
  @Autowired
  private ProjectApplyMapper projectApplyMapper;
  @Autowired
  private ProjectApplyExtMapper projectApplyExtMapper;

  @Autowired
  private ProjectExtMapper projectExtMapper;
  @Autowired
  private ProjectSelectMapper projectSelectMapper;

  @Autowired
  private TeacherMapper teacherMapper;
  @Autowired
  private StudentMapper studentMapper;


  /**
   * 获取可选的课题
   */
  public PaginationDTO<ProjectSelectVO> getSelectiveProject(Integer page, Integer size,
      UserVO user) {

    PaginationDTO<ProjectSelectVO> paginationDTO = new PaginationDTO<>();
    List<ProjectApply> projectApplys = null;
    Map<String, String> userMap = new HashMap<>();
    //分页
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      String reg = "[0-9]{10}";
      PageMethod.startPage(page, size);
      projectApplys = projectApplyExtMapper.getSelectiveProjectByTea(reg, user.getSchoolYear());
      PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplys);
      paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);
      //获取创建者编号
      Set<String> creatorNos = new HashSet<>();
      projectApplys.forEach((k) -> {
        creatorNos.add(k.getCreatorNo());
      });
      //查找创建者信息

      StudentExample studentExample = new StudentExample();
      studentExample.createCriteria().andSnoIn(new ArrayList<>(creatorNos));
      List<Student> students = studentMapper.selectByExample(studentExample);
      students.forEach((k) -> {
        userMap.put(k.getSno(), k.getSname());
      });
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      String reg = "[0-9]{7}";
      PageMethod.startPage(page, size);
      projectApplys = projectApplyExtMapper.getSelectiveProjectByStu(reg, user.getSchoolYear());
      PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplys);
      paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);
      //获取创建者编号
      Set<String> creatorNos = new HashSet<>();
      projectApplys.forEach((k) -> {
        creatorNos.add(k.getCreatorNo());
      });
      //查找创建者信息
      TeacherExample teacherExample = new TeacherExample();
      teacherExample.createCriteria().andTnoIn(new ArrayList<>(creatorNos));
      List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
      teachers.forEach((k) -> {
        userMap.put(k.getTno(), k.getTname());
      });
    }
    if (projectApplys == null || projectApplys.isEmpty()) {
      throw new CommonException(EmProjectError.UNKNOWN_ERROR);
    }

    List<ProjectSelectVO> projectSelectVOS = new ArrayList<>();

    for (ProjectApply projectApply : projectApplys) {
      ProjectSelectVO projectSelectVO = new ProjectSelectVO();
      projectSelectVO.setPno(projectApply.getPno());
      projectSelectVO.setPname(projectApply.getPname());
      projectSelectVO.setType(projectApply.getType());
      projectSelectVO.setCreatorNo(projectApply.getCreatorNo());
      projectSelectVO.setCreatorName(userMap.get(projectApply.getCreatorNo()));
      projectSelectVOS.add(projectSelectVO);
    }
    paginationDTO.setData(projectSelectVOS);
    return paginationDTO;
  }

  /**
   * 选择课题
   */
  public void selectProject(UserVO user, String pno) {
    //判断是否已有课题, 或指导人数超过指定人数
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      ProjectSelectResultExample projectSelectResultExample = new ProjectSelectResultExample();
      projectSelectResultExample.createCriteria().andTnoEqualTo(user.getAccountNo());
      long result = projectSelectResultMapper.countByExample(projectSelectResultExample);
      if (result > user.getLeadNumber()) {
        throw new CommonException(EmProjectError.PROJECT_WAS_SELECTED_OVER_MAX_NUM);
      }
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      ProjectSelectResultExample projectSelectResultExample = new ProjectSelectResultExample();
      projectSelectResultExample.createCriteria().andSnoEqualTo(user.getAccountNo());
      long result = projectSelectResultMapper.countByExample(projectSelectResultExample);
      if (result >= 1) {
        throw new CommonException(EmProjectError.STUDENT_ONLY_ONE_PROJECT);
      }
    }
    //判断用户选择是否超过三个
    List<ProjectSelect> projectSelects;
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andSelectorNoEqualTo(user.getAccountNo())
        .andIsSelectEqualTo((byte) 2);
    long selectNum = 0;
    selectNum = projectSelectMapper.countByExample(projectSelectExample);
    if (selectNum >= 3) {
      throw new CommonException(EmProjectError.PROJECT_SELECT_OVER_MAX_NUM);
    }
    //判断是否重复选择
    projectSelectExample.clear();
    projectSelectExample.createCriteria().andPnoEqualTo(pno)
        .andSelectorNoEqualTo(user.getAccountNo());
    projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    if (projectSelects != null && !projectSelects.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_SELECT_REPEAT);
    }
    //判断是否该课题被选超过三次
    projectSelectExample.clear();
    projectSelectExample.createCriteria().andPnoEqualTo(pno)
        .andIsSelectEqualTo((byte) 2);
    selectNum = projectSelectMapper.countByExample(projectSelectExample);
    if (selectNum >= 3) {
      throw new CommonException(EmProjectError.PROJECT_WAS_SELECTED_OVER_MAX_NUM);
    }
    ProjectSelect projectSelect = new ProjectSelect();
    projectSelect.setPno(pno);
    projectSelect.setSelectorNo(user.getAccountNo());
    projectSelect.setSelectorName(user.getName());
    projectSelect.setSchoolYear(user.getSchoolYear());
    projectSelect.setGmtCreate(new Date());
    projectSelect.setGmtModified(projectSelect.getGmtCreate());
    projectSelectMapper.insertSelective(projectSelect);
  }

  /**
   * 获取我的申请情况
   */
  public List<ProjectSelectVO> getMySelectState(UserVO user) {
    //获取我申请的课题
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andSelectorNoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear());
    projectSelectExample.setOrderByClause("is_select desc");
    List<ProjectSelect> projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    List<String> pnos = new ArrayList<>();
    projectSelects.forEach((k) -> {
      pnos.add(k.getPno());
    });
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoIn(pnos);
    List<ProjectApply> projectApplies = projectApplyMapper.selectByExample(projectApplyExample);
    Map<String, ProjectApply> projectApplyMap = new HashMap<>();
    projectApplies.forEach((k) -> {
      projectApplyMap.put(k.getPno(), k);
    });

    //todo 添加创始人名称

    List<ProjectSelectVO> projectSelectedVOS = new ArrayList<>();
    for (ProjectSelect projectSelect : projectSelects) {
      ProjectSelectVO projectSelectVO = new ProjectSelectVO();
      projectSelectVO.setPno(projectSelect.getPno());
      projectSelectVO.setPname(projectApplyMap.get(projectSelectVO.getPno()).getPname());
      projectSelectVO.setType(projectApplyMap.get(projectSelectVO.getPno()).getType());
      projectSelectVO.setResult(projectSelect.getIsSelect());
      projectSelectedVOS.add(projectSelectVO);
    }
    return projectSelectedVOS;
  }


  /**
   * 获取我的课题的申请情况
   */
  public PaginationDTO<ProjectSelectVO> getMyProjectSelectState(Integer page, Integer size,
      UserVO user) {
    PaginationDTO<ProjectSelectVO> paginationDTO = new PaginationDTO<>();

    List<String> pnos = new ArrayList<>();
    //获取我的课题
    ProjectApplyExample example = new ProjectApplyExample();
    example.createCriteria().andCreatorNoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear());
    List<ProjectApply> projectApplies = projectApplyMapper.selectByExample(example);
    if (projectApplies == null || projectApplies.isEmpty()) {
      throw new CommonException(EmProjectError.NO_PROJECT);
    }
    Map<String, ProjectApply> projectApplyMap = new HashMap<>();

    projectApplies.forEach((k) -> {
      pnos.add(k.getPno());
      projectApplyMap.put(k.getPno(), k);
    });

    //分页
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    //查找课题
    projectSelectExample.createCriteria().andPnoIn(pnos);
    projectSelectExample.setOrderByClause("is_select desc");
    PageMethod.startPage(page, size);
    List<ProjectSelect> projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    PageInfo<ProjectSelect> pageInfo = new PageInfo<>(projectSelects);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectSelectVO> projectSelectVOS = new ArrayList<>();
    for (ProjectSelect projectSelect : projectSelects) {
      ProjectSelectVO projectSelectVO = new ProjectSelectVO();
      projectSelectVO.setPno(projectSelect.getPno());
      projectSelectVO.setPname(projectApplyMap.get(projectSelectVO.getPno()).getPname());
      projectSelectVO.setType(projectApplyMap.get(projectSelectVO.getPno()).getType());
      projectSelectVO.setSelectNo(projectSelect.getSelectorNo());
      projectSelectVO.setSelectName(projectSelect.getSelectorName());
      projectSelectVO.setResult(projectSelect.getIsSelect());
      projectSelectVOS.add(projectSelectVO);
    }
    paginationDTO.setData(projectSelectVOS);
    return paginationDTO;
  }

  /**
   * 确认选题人
   */
  @Transactional
  public void confirmProjectSelector(UserVO user, String selectNo, String selectName, String pno,
      String pname) {
    //将该课题的其余选题人设为不通过
    ProjectSelect projectSelect = new ProjectSelect();
    projectSelect.setPno(pno);
    projectSelect.setIsSelect((byte) 0);
    projectSelect.setGmtModified(new Date());
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andPnoEqualTo(pno);
    projectSelectMapper.updateByExampleSelective(projectSelect, projectSelectExample);
    //确认选该课题的人
    projectSelectExample.clear();
    projectSelect.setIsSelect((byte) 1);
    projectSelectExample.createCriteria().andPnoEqualTo(pno).andSelectorNoEqualTo(selectNo);
    projectSelectMapper.updateByExampleSelective(projectSelect, projectSelectExample);

    //更新课题数据库
    ProjectSelectResult projectSelectResult = new ProjectSelectResult();
    projectSelectResult.setPno(pno);
    projectSelectResult.setPname(pname);
    projectSelectResult.setGmtCreate(new Date());
    projectSelectResult.setGmtModified(projectSelectResult.getGmtCreate());
    projectSelectResult.setSchoolYear(user.getSchoolYear());
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      projectSelectResult.setSno(selectNo);
      projectSelectResult.setSname(selectName);
      projectSelectResult.setTno(user.getAccountNo());
      projectSelectResult.setTname(user.getName());
      //修改课题申请中的被选中信息
      ProjectApply projectApply = new ProjectApply();
      projectApply.setIsSelect((byte)1);
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(pno);
      projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      projectSelectResult.setTno(selectNo);
      projectSelectResult.setTname(selectName);
      projectSelectResult.setSno(user.getAccountNo());
      projectSelectResult.setSname(user.getName());
      //修改课题申请中的被选中信息, 并将课题放入老师名下
      ProjectApply projectApply = new ProjectApply();
      projectApply.setCreatorNo(selectNo);
      projectApply.setIsSelect((byte)1);
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(pno);
      projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }
    projectSelectResultMapper.insertSelective(projectSelectResult);
  }

  /**
   * 拒绝课题选择
   */
  public void refuseProjectSelector(String selectNo, String pno) {
    ProjectSelect projectSelect = new ProjectSelect();
    projectSelect.setPno(pno);
    projectSelect.setIsSelect((byte) 0);
    projectSelect.setGmtModified(new Date());
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andPnoEqualTo(pno).andSelectorNoEqualTo(selectNo);
    projectSelectMapper.updateByExampleSelective(projectSelect, projectSelectExample);
  }
}

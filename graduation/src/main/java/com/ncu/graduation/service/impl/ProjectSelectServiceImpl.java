package com.ncu.graduation.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.bo.MyProjectSelectStateBO;
import com.ncu.graduation.bo.MySelectStateBO;
import com.ncu.graduation.bo.SelectiveProjectBO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectSearchDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectApplyExtMapper;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectSelectExtMapper;
import com.ncu.graduation.mapper.ProjectSelectMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.StudentExtMapper;
import com.ncu.graduation.model.College;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.model.ProjectSelectExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.service.ProjectSelectService;
import com.ncu.graduation.util.JedisOp;
import com.ncu.graduation.vo.ProAndStuNumVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
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
public class ProjectSelectServiceImpl implements ProjectSelectService {

  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;
  @Autowired
  private ProjectApplyMapper projectApplyMapper;
  @Autowired
  private ProjectApplyExtMapper projectApplyExtMapper;

  @Autowired
  private ProjectSelectMapper projectSelectMapper;

  @Autowired
  private ProjectSelectExtMapper projectSelectExtMapper;

  @Autowired
  private StudentExtMapper studentExtMapper;

  @Autowired
  private UserServiceImpl userService;

  @Autowired
  private JedisOp jedis;

  /**
   * 获取可选的课题
   */
  @Override
  public PaginationDTO<SelectiveProjectBO> getSelectiveProject(Integer page, Integer size,
      UserVO user, ProjectSearchDTO projectSearchDTO) {
    PaginationDTO<SelectiveProjectBO> paginationDTO = new PaginationDTO<>();
    if (!StringUtils.isBlank(projectSearchDTO.getPname())) {
      projectSearchDTO.setPname("%" + projectSearchDTO.getPname() + "%");
    }
    if (!StringUtils.isBlank(projectSearchDTO.getCreatorName())) {
      projectSearchDTO.setCreatorName("%" + projectSearchDTO.getCreatorName() + "%");
    }
    if (!StringUtils.isBlank(projectSearchDTO.getTags())) {
      projectSearchDTO.setTags("%" + projectSearchDTO.getTags() + "%");
    }
    if (!StringUtils.isBlank(projectSearchDTO.getType())) {
      projectSearchDTO.setType("%" + projectSearchDTO.getType() + "%");
    }
    List<SelectiveProjectBO> selectiveProjectBOS = null;
    //map对应no, 姓名
    //如果是老师  查找学生申报的课题
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole()) || UserRoleEnum.ADMIN.getRole()
        .equals(user.getRole()) || UserRoleEnum.DIRECTOR.getRole().equals(user.getRole())) {
      String reg = "[0-9]{10}";
      PageMethod.startPage(page, size);
      selectiveProjectBOS = projectApplyExtMapper
          .getSelectiveProjectByTea(reg, user.getSchoolYear(), user.getCollege(), projectSearchDTO);
      PageInfo<SelectiveProjectBO> pageInfo = new PageInfo<>(selectiveProjectBOS);
      paginationDTO.setPagination( pageInfo.getPages(), page, size);
    }
    //如果是学生 查找老师的课题
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      String reg = "[0-9]{7}";
      PageMethod.startPage(page, size);
      selectiveProjectBOS = projectApplyExtMapper
          .getSelectiveProjectByStu(reg, user.getSchoolYear(), user.getCollege(), projectSearchDTO);
      PageInfo<SelectiveProjectBO> pageInfo = new PageInfo<>(selectiveProjectBOS);
      paginationDTO.setPagination( pageInfo.getPages(), page, size);
    }
    paginationDTO.setData(selectiveProjectBOS);
    if (!StringUtils.isBlank(projectSearchDTO.getPname())) {
      projectSearchDTO.setPname(projectSearchDTO.getPname().replaceAll("%",""));
    }
    if (!StringUtils.isBlank(projectSearchDTO.getCreatorName())) {
      projectSearchDTO.setCreatorName(projectSearchDTO.getCreatorName().replaceAll("%",""));
    }
    if (!StringUtils.isBlank(projectSearchDTO.getTags())) {
      projectSearchDTO.setTags(projectSearchDTO.getTags().replaceAll("%",""));
    }
    if (!StringUtils.isBlank(projectSearchDTO.getType())) {
      projectSearchDTO.setType(projectSearchDTO.getType().replaceAll("%",""));
    }
    return paginationDTO;
  }


  /**
   * 选择课题
   */
  @Override
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
    //判断学生是否已经有确定课题
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
    //判断是否该课题被选超过三次未被审核
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
    projectSelect.setIsSelect((byte) 2);
    projectSelect.setGmtCreate(new Date());
    projectSelect.setGmtModified(projectSelect.getGmtCreate());
    projectSelectMapper.insertSelective(projectSelect);
  }

  /**
   * 获取我的申请情况
   */
  @Override
  public List<MySelectStateBO> getMySelectState(UserVO user) {
    List<MySelectStateBO> mySelectState = null;
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole()) || UserRoleEnum.ADMIN.getRole()
        .equals(user.getRole()) || UserRoleEnum.DIRECTOR.getRole().equals(user.getRole())) {
      mySelectState = projectSelectExtMapper
          .getMySelectStateByTea(user.getAccountNo(), user.getSchoolYear());
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      mySelectState = projectSelectExtMapper
          .getMySelectStateByStu(user.getAccountNo(), user.getSchoolYear());
    }

    if (mySelectState == null || mySelectState.isEmpty()) {
      return new ArrayList<>();
    }

    return mySelectState;
  }


  /**
   * 获取我的课题的申请情况
   */
  @Override
  public List<ProjectApply> getMyProjectSelectState(UserVO user) {

    //获取我的课题
    ProjectApplyExample example = new ProjectApplyExample();
    example.createCriteria().andCreatorNoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear()).andIsPassEqualTo((byte) 1);
    return projectApplyMapper.selectByExample(example);
  }

  /**
   * 根据课题号查看选题人
   */
  @Override
  public List<MyProjectSelectStateBO> getProjectSelectState(String pno,UserVO user) {

    List<MyProjectSelectStateBO> myProjectSelectState = null;
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole()) || UserRoleEnum.ADMIN.getRole()
        .equals(user.getRole()) || UserRoleEnum.DIRECTOR.getRole().equals(user.getRole())) {
      myProjectSelectState = projectSelectExtMapper
          .getMyProjectSelectStateByTea(pno);
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      myProjectSelectState = projectSelectExtMapper
          .getMyProjectSelectStateByStu(pno);
    }

    if (myProjectSelectState == null || myProjectSelectState.isEmpty()) {
      return new ArrayList<>();
    }

    return myProjectSelectState;
  }


  /**
   * 确认选题人
   */
  @Transactional
  @Override
  public void confirmProjectSelector(UserVO user, String selectNo, String selectName, String pno,
      String pname) {
    //判断当前指导人数是否超出
    ProjectSelectResultExample projectSelectResultExample = new ProjectSelectResultExample();
    projectSelectResultExample.createCriteria().andTnoEqualTo(user.getAccountNo());
    long result = projectSelectResultMapper.countByExample(projectSelectResultExample);
    if (result > user.getLeadNumber()) {
      throw new CommonException(EmProjectError.PROJECT_WAS_SELECTED_OVER_MAX_NUM);
    }
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
    //更新有效课题数据库
    ProjectSelectResult projectSelectResult = new ProjectSelectResult();
    projectSelectResult.setPno(pno);
    projectSelectResult.setGmtCreate(new Date());
    projectSelectResult.setGmtModified(projectSelectResult.getGmtCreate());
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      projectSelectResult.setSno(selectNo);
      projectSelectResult.setTno(user.getAccountNo());
      //修改课题申请中的被选中信息
      ProjectApply projectApply = new ProjectApply();
      projectApply.setIsSelect((byte) 1);
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(pno);
      projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }
    //如果是学生, 将课题放入老师名下
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      projectSelectResult.setTno(selectNo);
      projectSelectResult.setSno(user.getAccountNo());
      //修改课题申请中的被选中信息, 并将课题放入老师名下
      ProjectApply projectApply = new ProjectApply();
      projectApply.setCreatorNo(selectNo);
      projectApply.setIsSelect((byte) 1);
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(pno);
      projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }
    projectSelectResultMapper.insertSelective(projectSelectResult);
    //更新数据后将redis中的数据删除, 等待下一次自动插入
    jedis.delete(user.getAccountNo());
  }

  /**
   * 拒绝课题选择
   */
  @Override
  public void refuseProjectSelector(String selectNo, String pno) {
    ProjectSelect projectSelect = new ProjectSelect();
    projectSelect.setPno(pno);
    projectSelect.setIsSelect((byte) 0);
    projectSelect.setGmtModified(new Date());
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andPnoEqualTo(pno).andSelectorNoEqualTo(selectNo);
    int result = projectSelectMapper.updateByExampleSelective(projectSelect, projectSelectExample);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "拒绝课题" + pno + "选择失败");
    }
    //查看是否有未明确的选题人
    projectSelectExample.clear();
    projectSelectExample.createCriteria().andPnoEqualTo(pno).andIsSelectEqualTo((byte) 2);
    List<ProjectSelect> projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    if (projectSelects == null || projectSelects.isEmpty()) {
      //没有则将课题被选情况设为未被人选
      ProjectApply projectApply = new ProjectApply();
      projectApply.setPno(pno);
      projectApply.setIsSelect((byte) 0);
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(pno);
      projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }

  }

  /**
   * 获取学院内学生数和课题数
   */
  @Override
  public List<ProAndStuNumVO> getStuAndProNum(UserVO user) {
    // 查看学院可选课题
    List<ProAndStuNumVO> proNumVOS = projectApplyExtMapper
        .countByCollege(user.getSchoolYear());
    List<ProAndStuNumVO> stuNumVOS = studentExtMapper.countByCollege(user.getSchoolYear());
    List<College> collegeList = userService.getCollege();
    //学院编号，学院名
    Map<String, String> collegeMap = new HashMap<>();
    collegeList.forEach(k -> {
      collegeMap.put(k.getCollegeNo(), k.getCollegeName());
    });
    //以学生编号为key
    Map<String, Long> stuByCollege = new HashMap<>();
    stuNumVOS.forEach((k) -> stuByCollege.put(k.getCollege(), k.getStuNum()));
    proNumVOS.forEach((k) -> {
      k.setStuNum(stuByCollege.get(k.getCollege()));
      //学院编号姓名转化
      k.setCollege(collegeMap.get(k.getCollege()));
    });
    return proNumVOS;
  }
}

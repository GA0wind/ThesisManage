package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectMapper;
import com.ncu.graduation.mapper.ProjectSelectMapper;
import com.ncu.graduation.model.Project;
import com.ncu.graduation.model.ProjectExample;
import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.model.ProjectSelectExample;
import com.ncu.graduation.vo.ProjectSelectVO;
import com.ncu.graduation.vo.ProjectViewVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
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
  private ProjectMapper projectMapper;
  @Autowired
  private ProjectSelectMapper projectSelectMapper;

  /**
   * 获取可选的课题
   */
  public PaginationDTO<ProjectViewVO> getSelectiveProject(Integer page, Integer size, UserVO user) {

    ProjectExample projectExample = new ProjectExample();
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      projectExample.createCriteria().andSnoIsNotNull().andIsPassEqualTo((byte) 1)
          .andTnoIsNull().andCollegeEqualTo(user.getCollege());
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      projectExample.createCriteria().andTnoIsNotNull().andIsPassEqualTo((byte) 1)
          .andSnoIsNull().andCollegeEqualTo(user.getCollege());
    }
    //分页
    PaginationDTO<ProjectViewVO> paginationDTO = new PaginationDTO<>();
    PageMethod.startPage(page,size);
    List<Project> projects = projectMapper.selectByExample(projectExample);
    PageInfo<Project> pageInfo = new PageInfo<>(projects);
    paginationDTO.setPagination((int)pageInfo.getTotal(),page,size);

    List<ProjectViewVO> projectViewVOs = new ArrayList<>();

    for (Project project : projects) {
      ProjectViewVO projectViewVO = new ProjectViewVO();
      projectViewVO.setPno(project.getPno());
      projectViewVO.setPname(project.getPname());
      projectViewVO.setType(project.getType());
      if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())){
        projectViewVO.setTname(project.getTname());
      }else {
        projectViewVO.setSname(project.getTname());
      }
      projectViewVOs.add(projectViewVO);
    }
    paginationDTO.setData(projectViewVOs);
    return paginationDTO;
  }

  /**
   * 选择课题
   * @param user
   * @param no
   */
  public void selectProject(UserVO user, String no) {
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
    projectSelectExample.createCriteria().andPnoEqualTo(no)
        .andSelectorNoEqualTo(user.getAccountNo());
    projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    if (projectSelects != null && !projectSelects.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_SELECT_REPEAT);
    }
    //判断是否该课题被选超过三次
    projectSelectExample.clear();
    projectSelectExample.createCriteria().andPnoEqualTo(no)
        .andIsSelectEqualTo((byte) 2);
    selectNum = projectSelectMapper.countByExample(projectSelectExample);
    if (selectNum >= 3) {
      throw new CommonException(EmProjectError.PROJECT_WAS_SELECTED_OVER_MAX_NUM);
    }
    ProjectSelect projectSelect = new ProjectSelect();
    projectSelect.setPno(no);
    projectSelect.setSelectorNo(user.getAccountNo());
    projectSelect.setSelectorName(user.getName());
    projectSelect.setGmtCreate(new Date());
    projectSelect.setGmtModified(projectSelect.getGmtCreate());
    projectSelectMapper.insertSelective(projectSelect);
  }

  /**
   * 获取我的申请情况
   * @param user
   * @return
   */
  public List<ProjectSelectVO> getMySelectState(UserVO user) {
    //获取我申请的课题
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andSelectorNoEqualTo(user.getAccountNo());
    List<ProjectSelect> projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    List<ProjectSelectVO> projectSelectVOs = new ArrayList<>();
    for (ProjectSelect projectSelect : projectSelects) {
      ProjectSelectVO projectSelectVO = new ProjectSelectVO();
      projectSelectVO.setPno(projectSelect.getPno());
      projectSelectVO.setPname(projectSelect.getPname());
      projectSelectVO.setResult(projectSelect.getIsSelect());
      projectSelectVOs.add(projectSelectVO);
    }
    return projectSelectVOs;
  }


  /**
   * 获取我的课题的申请情况
   * @param page
   * @param size
   * @param user
   * @return
   */
  public PaginationDTO<ProjectSelectVO> getMyProjectSelectState(Integer page, Integer size, UserVO user) {
    PaginationDTO<ProjectSelectVO> paginationDTO = new PaginationDTO<>();

    //分页
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andCreateNoEqualTo(user.getAccountNo());
    projectSelectExample.setOrderByClause("is_select desc");
    PageMethod.startPage(page,size);
    List<ProjectSelect> projectSelects = projectSelectMapper.selectByExample(projectSelectExample);
    PageInfo<ProjectSelect> pageInfo = new PageInfo<>(projectSelects);
    paginationDTO.setPagination((int)pageInfo.getTotal(),page,size);

    List<ProjectSelectVO> projectSelectVOs = new ArrayList<>();
    for (ProjectSelect projectSelect : projectSelects) {
      ProjectSelectVO projectSelectVO = new ProjectSelectVO();
      projectSelectVO.setPno(projectSelect.getPno());
      projectSelectVO.setPname(projectSelect.getPname());
      projectSelectVO.setSelectNo(projectSelect.getSelectorNo());
      projectSelectVO.setSelectName(projectSelect.getSelectorName());
      projectSelectVO.setResult(projectSelect.getIsSelect());
      projectSelectVOs.add(projectSelectVO);
    }
    paginationDTO.setData(projectSelectVOs);
    return paginationDTO;
  }

  /**
   * 确认选题人
   * @param user
   * @param selectNo
   * @param pno
   */
  @Transactional
  public void confirmProjectSelector(UserVO user, String selectNo, String pno){
    //将该课题的其余选题人设为不通过
    ProjectSelect projectSelect = new ProjectSelect();
    projectSelect.setPno(pno);
    projectSelect.setIsSelect((byte)0);
    projectSelect.setGmtModified(new Date());
    ProjectSelectExample projectSelectExample = new ProjectSelectExample();
    projectSelectExample.createCriteria().andPnoEqualTo(pno);
    projectSelectMapper.updateByExampleSelective(projectSelect, projectSelectExample);
    //确认选该课题的人
    projectSelectExample.clear();
    projectSelect.setIsSelect((byte)1);
    projectSelectExample.createCriteria().andPnoEqualTo(pno).andSelectorNoEqualTo(selectNo);
    projectSelectMapper.updateByExampleSelective(projectSelect, projectSelectExample);
  }

}

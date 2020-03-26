package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectVerifyDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectMapper;
import com.ncu.graduation.mapper.ProjectSelectMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.Project;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.ProjectExample;
import com.ncu.graduation.model.ProjectSelect;
import com.ncu.graduation.model.ProjectSelectExample;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.ProjectSelectVO;
import com.ncu.graduation.vo.ProjectViewVO;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
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
  @Autowired
  private ProjectSelectMapper projectSelectMapper;

  /**
   * 获取我的课题
   */
  public PaginationDTO<ProjectViewVO> getMyProject(Integer page, Integer size, UserVO user) {
    PaginationDTO<ProjectViewVO> paginationDTO = new PaginationDTO<>();

    //判断角色是学生还是老师
    ProjectExample projectExample = new ProjectExample();
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      projectExample.createCriteria().andTnoEqualTo(user.getAccountNo())
          .andSchoolYearEqualTo(user.getSchoolYear());
    }
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      projectExample.createCriteria().andSnoEqualTo(user.getAccountNo())
          .andSchoolYearEqualTo(user.getSchoolYear());
    }

    //获取公告并将公告排序
    projectExample.setOrderByClause("gmt_modified desc");
    PageMethod.startPage(page, size);
    List<Project> projects = projectMapper
        .selectByExample(projectExample);
    PageInfo<Project> pageInfo = new PageInfo<>(projects);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectViewVO> projectViewVOs = new ArrayList<>();
    for (Project project : projects) {
      ProjectViewVO projectViewVO = new ProjectViewVO();
      projectViewVO.setPno(project.getPno());
      projectViewVO.setSname(project.getSname());
      projectViewVO.setTname(project.getTname());
      projectViewVO.setIsPass(project.getIsPass());
      projectViewVO.setPname(project.getPname());
      projectViewVO.setType(project.getType());
      projectViewVOs.add(projectViewVO);
    }
    paginationDTO.setData(projectViewVOs);
    return paginationDTO;
  }

  /**
   * 获取课题详情信息
   */
  public ProjectInfoVO getProjectInfo(String projectNo) {

    //查找课题
    ProjectExample projectExample = new ProjectExample();
    projectExample.createCriteria().andPnoEqualTo(projectNo);
    List<Project> projects = projectMapper.selectByExampleWithBLOBs(projectExample);
    if (projects == null || projects.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    //复制字段
    ProjectInfoVO projectInfoVO = new ProjectInfoVO();
    BeanUtils.copyProperties(projects.get(0), projectInfoVO);
//    //查看课题审核情况
//    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
//    projectApplyExample.createCriteria().andProjectNoEqualTo(projectNo);
//    List<ProjectApply> projectApplies = projectApplyMapper.selectByExample(projectApplyExample);
//    if (projectApplies != null && projectApplies.size() == 1) {
//      projectInfoVO.setProjectApply(projectApplies.get(0));
//    }
    return projectInfoVO;
  }

  /**
   * 申请或修改已申请课题
   */
  //bug如果直接通过URL修改已经被审核的课题
  @Transactional
  public void applyOrUpdate(ProjectApplyDTO projectApplyDTO, UserVO user) {
    Project project = new Project();
    //判断是修改还是新增
    if (projectApplyDTO.getPno() != null) {
      project.setPno(projectApplyDTO.getPno());
      //判断是否有新文件
      if (projectApplyDTO.getFile() == null || projectApplyDTO.getFile().isEmpty()) {
        projectApplyDTO.setFile(null);
      } else {
        String oldFilePath = FileTypeEnum.PROJECT.getPreUrl() + projectApplyDTO.getOldFilePath();
        File file = new File(oldFilePath);
        //删除旧文件
        file.delete();
      }
      project.setGmtModified(new Date());
    } else {
      project.setPno(UUID.randomUUID().toString().replaceAll("-", ""));
      project.setGmtCreate(new Date());
      project.setGmtModified(project.getGmtCreate());
    }
    //构建持久层project
    project.setPname(projectApplyDTO.getPname());
    project.setContent(projectApplyDTO.getContent());
    String filePath = FileSave.fileSave(projectApplyDTO.getFile(), FileTypeEnum.PROJECT);
    project.setFilePath(filePath);
    project.setType(projectApplyDTO.getType());
    project.setSchoolYear(user.getSchoolYear());
    project.setCollege(user.getCollege());
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      project.setSno(user.getAccountNo());
      project.setSname(user.getName());
    }
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      project.setTno(user.getAccountNo());
      project.setTname(user.getName());
    }
    project.setTags("");

    //构建持久层projectApply, 修改更新不需要重新申请
    if (StringUtils.isBlank(projectApplyDTO.getPno())) {
      projectMapper.insertSelective(project);
      ProjectApply projectApply = new ProjectApply();
      projectApply.setPname(project.getPname());
      projectApply.setGmtCreate(project.getGmtCreate());
      projectApply.setGmtModified(project.getGmtModified());
      projectApply.setPno(project.getPno());
      projectApply.setSchoolYear(project.getSchoolYear());
      projectApplyMapper.insertSelective(projectApply);
    } else {
      ProjectExample projectExample = new ProjectExample();
      projectExample.createCriteria().andPnoEqualTo(project.getPno());
      projectMapper.updateByExampleSelective(project, projectExample);

    }
  }

  /**
   * 撤销
   */
  @Transactional
  public void revoke(String no) {
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(no);
    projectApplyMapper.deleteByExample(projectApplyExample);

    ProjectExample projectExample = new ProjectExample();
    projectApplyExample.createCriteria().andPnoEqualTo(no);
    projectMapper.deleteByExample(projectExample);
  }

  /**
   * 获取单个课题信息, 用于审核或选题时查看课题详情
   */
  public Project getProject(String no) {
    ProjectExample projectExample = new ProjectExample();
    projectExample.createCriteria().andPnoEqualTo(no);
    List<Project> projects = projectMapper.selectByExampleWithBLOBs(projectExample);
    if (projects == null || projects.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    return projects.get(0);
  }

  /**
   * 获取需要我审核的课题
   */
  public PaginationDTO<ProjectViewVO> getVerifyProject(Integer page, Integer size, UserVO user) {

    PaginationDTO<ProjectViewVO> paginationDTO = new PaginationDTO<>();
    //分页
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andBlindTrialNoEqualTo(user.getAccountNo());
    PageMethod.startPage(page, size);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExample(projectApplyExample);
    PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplies);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectViewVO> projectViewVOs = new ArrayList<>();
    for (ProjectApply projectApply : projectApplies) {
      ProjectViewVO projectViewVO = new ProjectViewVO();
      projectViewVO.setPno(projectApply.getPno());
      projectViewVO.setPname(projectApply.getPname());
      projectViewVOs.add(projectViewVO);
    }
    paginationDTO.setData(projectViewVOs);
    return paginationDTO;
  }


  /**
   * 审核课题
   */
  public void verifyProject(ProjectVerifyDTO projectVerifyDTO, UserVO user) {
    ProjectApply projectApply = new ProjectApply();
    projectApply.setPno(projectVerifyDTO.getPno());
    projectApply.setBlindTrialGrade(Byte.parseByte(projectVerifyDTO.getScore().toString()));
    projectApply.setBlindTrialComment(projectVerifyDTO.getComment());
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(projectApply.getPno())
        .andBlindTrialNoEqualTo(user.getAccountNo());
    projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
  }
}

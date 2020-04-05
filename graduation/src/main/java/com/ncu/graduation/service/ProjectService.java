package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.ProjectApplyVO;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
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
  private ProjectSelectResultMapper projectSelectResultMapper;
  @Autowired
  private ProjectApplyMapper projectApplyMapper;


  /**
   * 获取我申请的课题
   */
  public PaginationDTO<ProjectApplyVO> getMyApplyProject(Integer page, Integer size, UserVO user) {
    PaginationDTO<ProjectApplyVO> paginationDTO = new PaginationDTO<>();

    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andCreatorNoEqualTo(user.getAccountNo());

    PageMethod.startPage(page, size);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExample(projectApplyExample);
    PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplies);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectApplyVO> projectApplyVOS = new ArrayList<>();
    for (ProjectApply projectApply : projectApplies) {
      ProjectApplyVO projectApplyVO = new ProjectApplyVO();
      projectApplyVO.setPno(projectApply.getPno());
      projectApplyVO.setPname(projectApply.getPname());
      projectApplyVO.setIsPass(projectApply.getIsPass());
      projectApplyVOS.add(projectApplyVO);
    }
    paginationDTO.setData(projectApplyVOS);
    return paginationDTO;
  }

  /**
   * 获取课题详情信息
   */
  public ProjectInfoVO getProjectInfo(String pno) {

    //获取课题信息
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExampleWithBLOBs(projectApplyExample);
    if (projectApplies == null || projectApplies.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    //查找课题双选结果
    ProjectSelectResultExample projectSelectResultExample = new ProjectSelectResultExample();
    projectSelectResultExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectSelectResult> projectSelectResults = projectSelectResultMapper
        .selectByExample(projectSelectResultExample);

    //复制字段
    ProjectInfoVO projectInfoVO = new ProjectInfoVO();
    if (projectSelectResults != null && !projectSelectResults.isEmpty()) {
      projectInfoVO.setProjectSelectResult(projectSelectResults.get(0));
    }
    projectInfoVO.setProjectApply(projectApplies.get(0));

    return projectInfoVO;
  }

  /**
   * 申请或修改已申请课题
   */
  //bug如果直接通过URL修改已经被审核的课题
  @Transactional
  public void applyOrUpdate(ProjectApplyDTO projectApplyDTO, UserVO user) {
    ProjectApply projectApply = new ProjectApply();
    //判断是修改还是新增, pno不为空, 就是修改
    if (!StringUtils.isBlank(projectApplyDTO.getPno())) {
      projectApply.setPno(projectApplyDTO.getPno());
      //判断是否有新文件, 如果新文件为空, 就不需要修改文件
      if (projectApplyDTO.getFile() == null || projectApplyDTO.getFile().isEmpty()) {
        projectApplyDTO.setFile(null);
      } else {
        String oldFilePath = FileTypeEnum.PROJECT.getPreUrl() + projectApplyDTO.getOldFilePath();
        File file = new File(oldFilePath);
        //删除旧文件
        file.delete();
      }
    } else {
      //新课题, 设置新东西
      projectApply.setPno(UUID.randomUUID().toString().replaceAll("-", ""));
    }
    //构建持久层project
    projectApply.setPname(projectApplyDTO.getPname());
    projectApply.setContent(projectApplyDTO.getContent());
    String filePath = FileSave.fileSave(projectApplyDTO.getFile(), FileTypeEnum.PROJECT);
    projectApply.setFilePath(filePath);
    projectApply.setType(projectApplyDTO.getType());
    projectApply.setSchoolYear(user.getSchoolYear());
    projectApply.setCollege(user.getCollege());
    projectApply.setCreatorNo(user.getAccountNo());
    projectApply.setTags(projectApplyDTO.getTags());
    int result = 0;
    //构建持久层projectApply, 修改更新不需要重新申请
    if (StringUtils.isBlank(projectApplyDTO.getPno())) {
      projectApply.setGmtCreate(new Date());
      projectApply.setGmtModified(projectApply.getGmtCreate());
      result = projectApplyMapper.insertSelective(projectApply);
    } else {
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(projectApply.getPno());
      result = projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }
    if (result != 1) {
      throw new CommonException(EmProjectError.UNKNOWN_ERROR);
    }
  }

  /**
   * 撤销
   */
  @Transactional
  public void revoke(String pno) {
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    projectApplyMapper.deleteByExample(projectApplyExample);
  }

  /**
   * 获取单个课题信息, 用于审核或选题时查看简略课题信息
   */
  public ProjectApply getProject(String pno) {
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExampleWithBLOBs(projectApplyExample);
    if (projectApplies == null || projectApplies.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    return projectApplies.get(0);
  }

  /**
   * 获取需要我审核的课题
   */
  public PaginationDTO<ProjectApplyVO> getVerifyProject(Integer page, Integer size, UserVO user) {

    PaginationDTO<ProjectApplyVO> paginationDTO = new PaginationDTO<>();
    //分页
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andBlindTrialNoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear());
    PageMethod.startPage(page, size);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExample(projectApplyExample);
    PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplies);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectApplyVO> projectApplyVOS = new ArrayList<>();
    projectApplies.forEach((k) -> {
      ProjectApplyVO projectApplyVO = new ProjectApplyVO();
      projectApplyVO.setPno(k.getPno());
      projectApplyVO.setPname(k.getPname());
      projectApplyVO.setIsPass(k.getIsPass());
      projectApplyVO.setType(k.getType()
      );
      projectApplyVOS.add(projectApplyVO);
    });

    paginationDTO.setData(projectApplyVOS);
    return paginationDTO;
  }


  /**
   * 审核课题
   */
  public void verifyProject(VerifyDocumentDTO verifyDocumentDTO, UserVO user) {
    ProjectApply projectApply = new ProjectApply();
    projectApply.setPno(verifyDocumentDTO.getDno());
    projectApply.setBlindTrialGrade(Byte.parseByte(verifyDocumentDTO.getScore().toString()));
    projectApply.setIsPass(verifyDocumentDTO.getIsPass());
    projectApply.setBlindTrialComment(verifyDocumentDTO.getComment());
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(projectApply.getPno())
        .andBlindTrialNoEqualTo(user.getAccountNo());
    int result = projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    if (result != 1) {
      throw new CommonException(EmProjectError.UNKNOWN_ERROR);
    }
  }


  /**
   * 获取教师的多个项目
   */
  public Map<String, ProjectSelectResult> getTeaProject(UserVO user) {
    ProjectSelectResultExample example = new ProjectSelectResultExample();
    example.createCriteria().andTnoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear());
    List<ProjectSelectResult> projects = projectSelectResultMapper.selectByExample(example);
    Map<String, ProjectSelectResult> projectMap = new HashMap<>();
    if (projects != null) {
      projects.forEach((k) -> {
        projectMap.put(k.getPno(), k);
      });
    }
    return projectMap;
  }

  /**
   * 获取学生的一个项目
   */
  public ProjectApply getStuProject(UserVO user) {
    ProjectSelectResultExample example = new ProjectSelectResultExample();
    example.createCriteria().andSnoEqualTo(user.getAccountNo());
    List<ProjectSelectResult> projects = projectSelectResultMapper.selectByExample(example);

    if (projects != null && !projects.isEmpty()) {
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(projects.get(0).getPno());
      List<ProjectApply> projectApplies = projectApplyMapper
          .selectByExampleWithBLOBs(projectApplyExample);
      return projectApplies.get(0);
    } else {
      return null;
    }
  }
}

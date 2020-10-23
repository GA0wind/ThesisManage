package com.ncu.graduation.service;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectPlanDTO;
import com.ncu.graduation.dto.ProjectVerifyDTO;
import com.ncu.graduation.dto.SelectiveProjectDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.vo.ProjectApplyVO;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.ProjectProgressVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 21:13
 * @description：课题service层
 * @modified By：
 * @version: 0.0.1
 */

public interface ProjectService {

  /**
   * 获取我申请的课题
   */
  PaginationDTO<ProjectApplyVO> getMyApplyProject(Integer page, Integer size, UserVO user);

  /**
   * 获取课题详情信息 1. 获取课题申请信息 2. 查找是否已经被双选确定 3. 有则直接连学生老师表 4. 没有则根据创建人连指定表
   */
  ProjectInfoVO getProjectInfo(String pno);

  /**
   * 申请或修改课题
   */
  void applyOrUpdate(ProjectApplyDTO projectApplyDTO, UserVO user);

  /**
   * 撤销
   */
  void revoke(String pno);

  /**
   * 获取单个课题信息, 用于审核或选题时查看简略课题信息
   */
  ProjectApply getProject(String pno);

  /**
   * 获取需要我审核的课题
   */
  PaginationDTO<ProjectApplyVO> getVerifyProject(Integer page, Integer size, UserVO user);


  /**
   * 审核课题
   */
  void verifyProject(ProjectVerifyDTO verifyDocumentDTO, UserVO user);

  /**
   * 复审申请提交
   */
  void review(String pno, UserVO user);


  /**
   * 获取教师的多个已经被选课题
   */
  Map<String, ProjectSelectResultBO> getTeaProject(UserVO user);

  /**
   * 获取学生的一个项目 先从被选课题表中查一个出来, 然后查课题信息, 然后查教师信息
   */
  ProjectInfoVO getStuProject(UserVO user);

  /**
   * 根据课题编号获取课题 查课题信息, 然后查教师信息
   */
  ProjectInfoVO getStuProject(String pno);

  /**
   * 管理员添加可选课题
   */
  void addSelectiveProject(SelectiveProjectDTO projectApplyDTO,
      UserVO user);

  /**
   * 管理员设置毕设计划
   */
  void setProjectPlan(ProjectPlanDTO projectPlanDTO);

  /**
   * 管理员获取所有课题计划
   */
  List<ProjectPlan> getProjectPlans();

  /**
   * 管理员获取当前学年课题计划
   */
  ProjectPlan getProjectPlan(String schoolYear);

  /**
   * 查询未完成学生
   */
  List<ProjectSelectResultBO> getUndoneStu(UserVO user, String type);

  /**
   * 获取课题进度
   */
  ProjectProgressVO getProjectProgress(String pno);
}

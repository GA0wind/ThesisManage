package com.ncu.graduation.service;

import com.ncu.graduation.bo.MyProjectSelectStateBO;
import com.ncu.graduation.bo.MySelectStateBO;
import com.ncu.graduation.bo.SelectiveProjectBO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectSearchDTO;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.vo.ProAndStuNumVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 23:11
 * @description：师生双选Service
 * @modified By：
 * @version:0.0.1
 */

public interface ProjectSelectService {

  /**
   * 获取可选的课题
   */
  PaginationDTO<SelectiveProjectBO> getSelectiveProject(Integer page, Integer size,
      UserVO user, ProjectSearchDTO projectSearchDTO);


  /**
   * 选择课题
   */
  void selectProject(UserVO user, String pno);

  /**
   * 获取我的申请情况
   */
  List<MySelectStateBO> getMySelectState(UserVO user);


  /**
   * 获取我的课题的申请情况
   */
  List<ProjectApply> getMyProjectSelectState(UserVO user);

  /**
   * 根据课题号查看选题人
   */
  List<MyProjectSelectStateBO> getProjectSelectState(String pno, UserVO user);


  /**
   * 确认选题人
   */
  void confirmProjectSelector(UserVO user, String selectNo, String selectName, String pno,
      String pname);

  /**
   * 拒绝课题选择
   */
  void refuseProjectSelector(String selectNo, String pno);

  /**
   * 获取学院内学生数和课题数
   */
  List<ProAndStuNumVO> getStuAndProNum(UserVO user);
}

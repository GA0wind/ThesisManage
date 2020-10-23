package com.ncu.graduation.service;

import com.ncu.graduation.bo.OralExamStuProjectBO;
import com.ncu.graduation.dto.OralExamSearchDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.vo.OralExamScoreVO;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import org.apache.poi.ss.usermodel.Workbook;

/**
 * @author ：grh
 * @date ：Created in 2020/3/31 21:34
 * @description：答辩Service
 * @modified By：
 * @version: 0.0.1
 */

public interface OralExaminationService {

  /**
   * 获取答辩小组成员信息
   */
  List<UserVO> getGroupInfo(UserVO user);

  /**
   * 获取小组负责学生和课题信息
   */
  PaginationDTO<OralExamStuProjectBO> getGroupStus(UserVO user, Integer page, Integer size,
      OralExamSearchDTO oralExamSearchDTO);

  /**
   * 老师给学生打分
   */
  void scoreToStu(Integer score, String sno, UserVO user);

  /**
   * 学生查看答辩分数
   */
  OralExamScoreVO getExamScore(UserVO user, ProjectInfoVO projectApply);

  /**
   * 导出成绩数据
   */
  Workbook exportScore(String college, ProjectPlan projectPlan,
      String schoolYear);
}

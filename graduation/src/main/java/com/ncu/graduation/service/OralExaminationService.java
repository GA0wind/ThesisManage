package com.ncu.graduation.service;

import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.mapper.ExamGroupMapper;
import com.ncu.graduation.mapper.OralExamScoreMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.ExamGroup;
import com.ncu.graduation.model.ExamGroupExample;
import com.ncu.graduation.model.OralExamScore;
import com.ncu.graduation.model.OralExamScoreExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.model.TeacherExample;
import com.ncu.graduation.vo.OralExamScoreVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：grh
 * @date ：Created in 2020/3/31 21:34
 * @description：答辩Service
 * @modified By：
 * @version: 0.0.1
 */

@Service
public class OralExaminationService {

  @Autowired
  private TeacherMapper teacherMapper;

  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;

  @Autowired
  private OralExamScoreMapper oralExamScoreMapper;

  @Autowired
  private ExamGroupMapper examGroupMapper;

  /**
   * 获取答辩小组成员信息
   */
  public List<UserVO> getGroupInfo(int groupNo) {
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andGroupNumEqualTo(groupNo);
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    List<UserVO> userVOS = new ArrayList<>();
    teachers.forEach((k) -> {
      UserVO user = new UserVO();
      user.setAccountNo(k.getTno());
      user.setName(k.getTname());
      userVOS.add(user);
    });
    return userVOS;
  }

  /**
   * 获取小组负责学生和课题信息
   */
  public List<ProjectSelectResult> getGroupStus(int groupNo) {
    ProjectSelectResultExample example = new ProjectSelectResultExample();
    example.createCriteria().andGroupNumEqualTo(groupNo);
    List<ProjectSelectResult> projectSelectResults = projectSelectResultMapper.selectByExample(
        example);
    // TODO: 2020/4/1  获取自己打的分数
    return projectSelectResults;

  }

  /**
   * 老师给学生打分
   */
  public void scoreToStu(Integer score, String sno, UserVO user) {
    OralExamScore oralExamScore = new OralExamScore();
    oralExamScore.setTno(user.getAccountNo());
    oralExamScore.setSno(sno);
    oralExamScore.setSchoolYear(user.getSchoolYear());
    oralExamScore.setScore(score.byteValue());
    int result = oralExamScoreMapper.insertSelective(oralExamScore);
    if (result != 1) {
      throw new CommonException(EmUserOperatorError.UNKNOWN_ERROR);
    }
  }

  /**
   * 学生查看答辩分数
   */
  public List<OralExamScoreVO> getExamScore(UserVO user, ProjectSelectResult projectSelectResult) {
    //获取小组老师编号
    ExamGroupExample examGroupExample = new ExamGroupExample();
    examGroupExample.createCriteria().andGroupNumEqualTo(projectSelectResult.getGroupNum())
        .andSchoolYearEqualTo(user.getSchoolYear());
    List<ExamGroup> groups = examGroupMapper.selectByExample(examGroupExample);
    List<String> tnos = new ArrayList<>();
    tnos.add(groups.get(0).getLeaderNo());
    String[] strings = groups.get(0).getMemberNo().split(",");
    tnos.addAll(Arrays.asList(strings));
    //获取小组老师信息
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andTnoIn(tnos);
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    //获取成绩
    OralExamScoreExample oralExamScoreExample = new OralExamScoreExample();
    oralExamScoreExample.createCriteria().andSnoEqualTo(user.getAccountNo());
    List<OralExamScore> oralExamScores = oralExamScoreMapper.selectByExample(oralExamScoreExample);
    Map<String,OralExamScore> scoreMap = new HashMap<>();
    oralExamScores.forEach((k)->{
      scoreMap.put(k.getTno(),k);
    });
    List<OralExamScoreVO> scoreVOList = new ArrayList<>();
    teachers.forEach((k)->{
      OralExamScoreVO oralExamScoreVO = new OralExamScoreVO();
      oralExamScoreVO.setTno(k.getTno());
      oralExamScoreVO.setTname(k.getTname());
      oralExamScoreVO.setScore(scoreMap.get(oralExamScoreVO.getTno()).getScore());
    });
    return scoreVOList;
  }
}

package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.OralExamSearchDTP;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.mapper.ExamGroupMapper;
import com.ncu.graduation.mapper.OralExamScoreMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.model.ExamGroup;
import com.ncu.graduation.model.ExamGroupExample;
import com.ncu.graduation.model.OralExamScore;
import com.ncu.graduation.model.OralExamScoreExample;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.StudentExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.model.TeacherExample;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.vo.OralExamScoreVO;
import com.ncu.graduation.vo.OralExamScoreVO.Score;
import com.ncu.graduation.vo.OralExamStuProjectVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
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
  private ThesisService thesisService;

  @Autowired
  private TeacherMapper teacherMapper;

  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private OralExamScoreMapper oralExamScoreMapper;

  @Autowired
  private ExamGroupMapper examGroupMapper;

  @Autowired
  private ProjectPlanMapper projectPlanMapper;

  /**
   * 获取答辩小组成员信息
   */
  public List<UserVO> getGroupInfo(UserVO user) {
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andGroupNumEqualTo(user.getGroup())
        .andSchoolYearEqualTo(user.getSchoolYear()).andCollegeEqualTo(user.getCollege());
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    List<UserVO> userVOS = new ArrayList<>();
    teachers.forEach(k -> {
      UserVO userVO = new UserVO();
      userVO.setAccountNo(k.getTno());
      userVO.setName(k.getTname());
      userVO.setRole(k.getGroupRole());
      userVOS.add(userVO);
    });
    return userVOS;
  }

  /**
   * 获取小组负责学生和课题信息
   */
  public PaginationDTO<OralExamStuProjectVO> getGroupStus(UserVO user, Integer page, Integer size,
      OralExamSearchDTP oralExamSearchDTP) {
    PaginationDTO<OralExamStuProjectVO> paginationDTO = new PaginationDTO<>();
    List<ProjectSelectResult> projects;
    List<String> snos = new ArrayList<>();
    List<Student> students;
    //判断搜索条件中课题名是否存在
    if (!StringUtils.isBlank(oralExamSearchDTP.getPname())) {
      ProjectSelectResultExample selectResultExample = new ProjectSelectResultExample();
      selectResultExample.createCriteria().andPnameLike("%" + oralExamSearchDTP.getPname() + "%");
      projects = projectSelectResultMapper.selectByExample(selectResultExample);
      StudentExample studentExample = new StudentExample();
      studentExample.createCriteria().andSnameLike("%" + oralExamSearchDTP.getSname() + "%");
      students = studentMapper.selectByExample(studentExample);
      students.forEach(k -> snos.add(k.getSno()));
    } else {
      //获取学生
      StudentExample studentExample = new StudentExample();
      if (StringUtils.isBlank(oralExamSearchDTP.getSname())) {
        studentExample.createCriteria().andCollegeEqualTo(user.getCollege())
            .andGroupNumEqualTo(user.getGroup()).andSchoolYearEqualTo(user.getSchoolYear());
      } else {
        studentExample.createCriteria().andCollegeEqualTo(user.getCollege())
            .andGroupNumEqualTo(user.getGroup()).andSchoolYearEqualTo(user.getSchoolYear())
            .andSnameLike("%" + oralExamSearchDTP.getSname() + "%");
      }
      PageMethod.startPage(page, size);
      students = studentMapper.selectByExample(studentExample);
      if (students == null || students.isEmpty()) {
        return paginationDTO;
      }
      students.forEach(k -> snos.add(k.getSno()));
      PageInfo<Student> pageInfo = new PageInfo<>(students);
      paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);
      //获取课题学生中间表
      ProjectSelectResultExample selectResultExample = new ProjectSelectResultExample();
      selectResultExample.createCriteria().andSnoIn(snos);
      projects = projectSelectResultMapper
          .selectByExample(selectResultExample);
      if (projects == null || projects.isEmpty()) {
        return paginationDTO;
      }
    }

    //获取论文
    List<String> pnos = new ArrayList<>();
    projects.forEach(k -> pnos.add(k.getPno()));
    List<Thesis> thesis = thesisService.getGroupThesis(pnos);
    //pno,thesis
    Map<String, Thesis> thesisMap = new HashMap<>(16);
    thesis.forEach(k -> thesisMap.put(k.getPno(), k));
    //获取自己打的分数
    OralExamScoreExample oralExamScoreExample = new OralExamScoreExample();
    oralExamScoreExample.createCriteria().andSnoIn(snos).andTnoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear());
    List<OralExamScore> oralExamScores = oralExamScoreMapper.selectByExample(oralExamScoreExample);
    //sno, score  组装数据
    Map<String, OralExamScore> scoreMap = new HashMap<>();
    oralExamScores.forEach(k -> scoreMap.put(k.getSno(), k));

    List<OralExamStuProjectVO> projectVOS = new ArrayList<>();
    projects.forEach(k -> {
      OralExamStuProjectVO projectVO = new OralExamStuProjectVO();
      projectVO.setPno(k.getPno());
      projectVO.setPname(k.getPname());
      projectVO.setSno(k.getSno());
      projectVO.setSname(k.getSname());
      if (thesisMap.get(k.getPno()) != null) {
        projectVO.setThesis(thesisMap.get(k.getPno()).getFilePath());
      }
      if (scoreMap.get(k.getSno()) != null) {
        projectVO.setScore(scoreMap.get(k.getSno()).getScore());
      }
      projectVOS.add(projectVO);
    });
    paginationDTO.setData(projectVOS);
    return paginationDTO;
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
    oralExamScore.setGmtCreate(new Date());
    oralExamScore.setGmtModified(oralExamScore.getGmtCreate());
    int result = oralExamScoreMapper.insertSelective(oralExamScore);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "评分失败, 请重试");
    }
  }

  /**
   * 学生查看答辩分数
   */
  public OralExamScoreVO getExamScore(UserVO user, ProjectApply projectApply) {
    //获取小组老师编号
    ExamGroupExample examGroupExample = new ExamGroupExample();
    examGroupExample.createCriteria().andGroupNumEqualTo(user.getGroup())
        .andSchoolYearEqualTo(user.getSchoolYear());
    List<ExamGroup> groups = examGroupMapper.selectByExample(examGroupExample);

    List<String> tnos = new ArrayList<>();
    tnos.add(groups.get(0).getLeaderNo());
    String[] strings = groups.get(0).getMemberNo().split(",");
    tnos.addAll(Arrays.asList(strings));

    //获取小组老师
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andTnoIn(tnos);
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    //获取成绩
    OralExamScoreExample oralExamScoreExample = new OralExamScoreExample();
    oralExamScoreExample.createCriteria().andSnoEqualTo(user.getAccountNo());
    List<OralExamScore> oralExamScores = oralExamScoreMapper.selectByExample(oralExamScoreExample);
    BigDecimal avg = new BigDecimal(0);
    Map<String, OralExamScore> scoreMap = new HashMap<>();
    for (OralExamScore oralExamScore : oralExamScores) {
      scoreMap.put(oralExamScore.getTno(), oralExamScore);
      avg = avg.add(BigDecimal.valueOf(oralExamScore.getScore()));
    }

    //小组里的成绩填充
    List<OralExamScoreVO.Score> scores = new ArrayList<>();
    teachers.forEach((k) -> {
      OralExamScoreVO.Score score = new Score();
      score.setTno(k.getTno());
      score.setTname(k.getTname());
      if (scoreMap.get(k.getTno()) != null) {
        score.setScore(scoreMap.get(k.getTno()).getScore());
      }
      scores.add(score);
    });
    OralExamScoreVO scoreVO = new OralExamScoreVO();
    scoreVO.setScores(scores);
    //获取论文
    
    StuProjectDocumentVO<Thesis> stuThesis = thesisService.getStuThesis(user, projectApply);
    //盲审和老师成绩
    if (stuThesis.getDocument().getTrialGrade() == null) {
      scoreVO.setScore(BigDecimal.valueOf(0));
    } else {
      scoreVO.setScore(BigDecimal.valueOf(stuThesis.getDocument().getTrialGrade()));
    }
    if (stuThesis.getDocument().getTrialGrade() == null) {
      scoreVO.setBlindScore(BigDecimal.valueOf(0));
    } else {
      scoreVO.setBlindScore(BigDecimal.valueOf(stuThesis.getDocument().getBlindTrialGrade()));
    }
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear());
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    String[] split = projectPlans.get(0).getScoreComposition().split(",");
    BigDecimal[] scoreComposition = new BigDecimal[split.length];
    NumberFormat nf = NumberFormat.getPercentInstance();
    for (int i = 0; i < split.length; i++) {
      Number m = null;
      try {
        m = nf.parse(split[i]);
      } catch (ParseException e) {
        e.printStackTrace();
      }
      scoreComposition[i] = BigDecimal.valueOf((double) m);
    }
    //求最终得分

    scoreVO.setAvg((avg.divide(BigDecimal.valueOf(teachers.size()), 2,BigDecimal.ROUND_HALF_UP)));
    BigDecimal resultScore = avg.multiply(scoreComposition[0])
        .add(scoreVO.getScore().multiply(scoreComposition[1]))
        .add(scoreVO.getBlindScore().multiply(scoreComposition[2])).setScale(2,BigDecimal.ROUND_HALF_UP);
    scoreVO.setResultScore(resultScore);

    return scoreVO;
  }
}

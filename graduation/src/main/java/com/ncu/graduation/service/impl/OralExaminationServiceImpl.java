package com.ncu.graduation.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.bo.OralExamStuProjectBO;
import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.OralExamSearchDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ExamGroupExtMapper;
import com.ncu.graduation.mapper.ExamGroupMapper;
import com.ncu.graduation.mapper.OralExamScoreMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.ProjectSelectResultExtMapper;
import com.ncu.graduation.mapper.StudentMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.mapper.ThesisMapper;
import com.ncu.graduation.model.ExamGroup;
import com.ncu.graduation.model.ExamGroupExample;
import com.ncu.graduation.model.OralExamScore;
import com.ncu.graduation.model.OralExamScoreExample;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.StudentExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.model.TeacherExample;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import com.ncu.graduation.service.OralExaminationService;
import com.ncu.graduation.util.ExcelWriteOp;
import com.ncu.graduation.vo.OralExamScoreVO;
import com.ncu.graduation.vo.OralExamScoreVO.Score;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
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
public class OralExaminationServiceImpl implements OralExaminationService {

  @Autowired
  private ThesisServiceImpl thesisService;

  @Autowired
  private TeacherMapper teacherMapper;

  @Autowired
  private ProjectSelectResultExtMapper projectSelectResultExtMapper;

  @Autowired
  private StudentMapper studentMapper;

  @Autowired
  private OralExamScoreMapper oralExamScoreMapper;

  @Autowired
  private ExamGroupMapper examGroupMapper;

  @Autowired
  private ProjectPlanMapper projectPlanMapper;

  @Autowired
  private ThesisMapper thesisMapper;

  @Autowired
  private ExamGroupExtMapper examGroupExtMapper;

  /**
   * 获取答辩小组成员信息
   */
  @Override
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
  @Override
  public PaginationDTO<OralExamStuProjectBO> getGroupStus(UserVO user, Integer page, Integer size,
      OralExamSearchDTO oralExamSearchDTO) {
    PaginationDTO<OralExamStuProjectBO> paginationDTO = new PaginationDTO<>();
    if (!StringUtils.isBlank(oralExamSearchDTO.getPname())) {
      oralExamSearchDTO.setPname("%" + oralExamSearchDTO.getPname() + "%");
    }
    if (!StringUtils.isBlank(oralExamSearchDTO.getSname())) {
      oralExamSearchDTO.setSname("%" + oralExamSearchDTO.getSname() + "%");
    }

    PageMethod.startPage(page, size);
    List<OralExamStuProjectBO> projectVOS = examGroupExtMapper
        .getGroupStuProject(oralExamSearchDTO, user.getSchoolYear(), user.getGroup(),
            user.getCollege());
    PageInfo<OralExamStuProjectBO> pageInfo = new PageInfo<>(projectVOS);
    paginationDTO.setPagination(pageInfo.getPages(), page, size);
    paginationDTO.setData(projectVOS);
    if (!StringUtils.isBlank(oralExamSearchDTO.getSname())) {
      oralExamSearchDTO.setSname(oralExamSearchDTO.getSname().replaceAll("%", ""));
    }
    if (!StringUtils.isBlank(oralExamSearchDTO.getPname())) {
      oralExamSearchDTO.setPname(oralExamSearchDTO.getPname().replaceAll("%", ""));
    }
    return paginationDTO;
  }

  /**
   * 老师给学生打分
   */
  @Override
  public void scoreToStu(Integer score, String sno, UserVO user) {
    //判断该学生是不是属于用户同一答辩小组
    StudentExample studentExample = new StudentExample();
    studentExample.createCriteria().andSnoEqualTo(sno);
    List<Student> students = studentMapper.selectByExample(studentExample);
    if (!students.get(0).getGroupNum().equals(user.getGroup())) {
      throw new CommonException(EmProjectError.USER_NOT_HAVE_THE_PROJECT);
    }

    OralExamScore oralExamScore = new OralExamScore();
    oralExamScore.setTno(user.getAccountNo());
    oralExamScore.setSno(sno);
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
  @Override
  public OralExamScoreVO getExamScore(UserVO user, ProjectInfoVO projectApply) {
    //获取小组老师编号
    ExamGroupExample examGroupExample = new ExamGroupExample();
    examGroupExample.createCriteria().andGroupNumEqualTo(user.getGroup())
        .andSchoolYearEqualTo(user.getSchoolYear()).andCollegeEqualTo(user.getCollege());
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
    boolean flag_oralComplete = true;
    boolean flag_complete = true;
    //小组答辩里的成绩填充
    List<OralExamScoreVO.Score> scores = new ArrayList<>();
    for (Teacher teacher : teachers) {
      OralExamScoreVO.Score score = new Score();
      score.setTno(teacher.getTno());
      score.setTname(teacher.getTname());
      if (scoreMap.get(teacher.getTno()) != null) {
        score.setScore(scoreMap.get(teacher.getTno()).getScore());
      } else {
        flag_complete = false;
        flag_oralComplete = false;
      }
      scores.add(score);
    }
    OralExamScoreVO scoreVO = new OralExamScoreVO();
    scoreVO.setScores(scores);
    //获取论文

    StuProjectDocumentVO<Thesis> stuThesis = thesisService.getStuThesis(user, projectApply);
    //盲审和老师成绩
    if (stuThesis.getDocument().getTrialGrade() == null) {
      scoreVO.setScore(BigDecimal.valueOf(0));
      flag_complete = false;
    } else {
      scoreVO.setScore(BigDecimal.valueOf(stuThesis.getDocument().getTrialGrade()));
    }
    if (stuThesis.getDocument().getBlindTrialGrade() == null) {
      scoreVO.setBlindScore(BigDecimal.valueOf(0));
      flag_complete = false;
    } else {
      scoreVO.setBlindScore(BigDecimal.valueOf(stuThesis.getDocument().getBlindTrialGrade()));
    }
    //获取成绩组成
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
    if (flag_oralComplete == false) {
      scoreVO.setAvg(BigDecimal.ZERO);
    } else {
      scoreVO
          .setAvg((avg.divide(BigDecimal.valueOf(teachers.size()), 2, BigDecimal.ROUND_HALF_UP)));
    }
    if (flag_complete == false) {
      scoreVO.setResultScore(BigDecimal.ZERO);
    } else {
      BigDecimal resultScore = scoreVO.getAvg().multiply(scoreComposition[0])
          .add(scoreVO.getScore().multiply(scoreComposition[1]))
          .add(scoreVO.getBlindScore().multiply(scoreComposition[2]))
          .setScale(2, BigDecimal.ROUND_HALF_UP);
      scoreVO.setResultScore(resultScore);
    }
    return scoreVO;
  }

  /**
   * 导出成绩数据
   */
  @Override
  public Workbook exportScore(String college, ProjectPlan projectPlan,
      String schoolYear) {
    //获取学生信息
    StudentExample studentExample = new StudentExample();
    studentExample.createCriteria().andCollegeEqualTo(college)
        .andSchoolYearEqualTo(schoolYear);
    List<Student> students = studentMapper.selectByExample(studentExample);
    if (students == null || students.isEmpty()) {
      throw new CommonException(EmProjectError.SCORE_EMPTY);
    }
    List<String> sno = new ArrayList<>();
    students.forEach(k -> sno.add(k.getSno()));
    //获取对应课题信息
    List<ProjectSelectResultBO> projectSelectResults = projectSelectResultExtMapper
        .getProjectSelectResultBySnos(sno);
    if (projectSelectResults == null || projectSelectResults.isEmpty()) {
      throw new CommonException(EmProjectError.SCORE_EMPTY);
    }
    //利用学号方便查找score对象填充数据
    Map<String, OralExamScoreVO> studentScoreMap = new HashMap<>(256);
    //利用课题号方便查找score对象填充数据
    Map<String, OralExamScoreVO> pnoScoreMap = new HashMap<>(256);

    //返回的数据
    List<OralExamScoreVO> scoreVOS = new ArrayList<>();

    projectSelectResults.forEach(k -> {
      OralExamScoreVO scoreVO = new OralExamScoreVO();
      scoreVO.setProjectSelectResult(k);
      scoreVOS.add(scoreVO);
      studentScoreMap.put(k.getSno(), scoreVO);
      pnoScoreMap.put(k.getPno(), scoreVO);
    });

    //获取答辩成绩
    OralExamScoreExample oralExamScoreExample = new OralExamScoreExample();
    oralExamScoreExample.createCriteria().andSnoIn(sno);
    List<OralExamScore> oralExamScores = oralExamScoreMapper.selectByExample(oralExamScoreExample);
    //填充答辩成绩
    oralExamScores.forEach(k -> {
      if (studentScoreMap.get(k.getSno()).getScores() == null || studentScoreMap.get(k.getSno())
          .getScores().isEmpty()) {
        studentScoreMap.get(k.getSno()).setScores(new ArrayList<>());
        Score score = new Score();
        score.setTno(k.getTno());
//        score.setTname(k.getTname);
        score.setScore(k.getScore());
        studentScoreMap.get(k.getSno()).getScores().add(score);
      } else {
        Score score = new Score();
        score.setTno(k.getTno());
//        score.setTname(k.getTname);
        score.setScore(k.getScore());
        studentScoreMap.get(k.getSno()).getScores().add(score);
      }
    });
    //求答辩平均分

    studentScoreMap.forEach((k, l) -> {
      int allScore = 0;
      if (l.getScores() != null && !l.getScores().isEmpty()) {
        for (Score score : l.getScores()) {
          allScore += score.getScore();
        }
        l.setAvg(BigDecimal.valueOf(allScore)
            .divide(BigDecimal.valueOf(l.getScores().size()), 2, BigDecimal.ROUND_HALF_UP));
      } else {
        l.setAvg(BigDecimal.ZERO);
      }
    });
    //获取论文成绩
    ThesisExample thesisExample = new ThesisExample();
    List<String> pnos = new ArrayList<>();
    pnos.addAll(pnoScoreMap.keySet());
    thesisExample.createCriteria().andPnoIn(pnos);
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    theses.forEach(k -> {
      if (k.getTrialGrade() == null) {
        pnoScoreMap.get(k.getPno()).setScore(BigDecimal.ZERO);
      } else {
        pnoScoreMap.get(k.getPno()).setScore(BigDecimal.valueOf(k.getTrialGrade()));
      }

      if (k.getBlindTrialGrade() == null) {
        pnoScoreMap.get(k.getPno()).setBlindScore(BigDecimal.ZERO);
      } else {
        pnoScoreMap.get(k.getPno()).setBlindScore(BigDecimal.valueOf(k.getBlindTrialGrade()));
      }
    });

    String[] split = projectPlan.getScoreComposition().split(",");
    BigDecimal[] scoreComposition = new BigDecimal[split.length];
    //根据成绩组成计算结果
    scoreVOS.forEach(k -> {
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
      BigDecimal resultScore = k.getAvg().multiply(scoreComposition[0])
          .add(k.getScore().multiply(scoreComposition[1]))
          .add(k.getBlindScore().multiply(scoreComposition[2]))
          .setScale(2, BigDecimal.ROUND_HALF_UP);
      k.setResultScore(resultScore);
    });
    Workbook workbook = ExcelWriteOp.exportData(scoreVOS);
    return workbook;
  }
}

package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import com.ncu.graduation.service.OralExaminationService;
import com.ncu.graduation.service.ThesisService;
import com.ncu.graduation.vo.OralExamScoreVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ：grh
 * @date ：Created in 2020/3/31 20:10
 * @description：答辩controller
 * @modified By：
 * @version: 0.0.1\
 */

@Controller
public class OralExaminationController {

  @Autowired
  private OralExaminationService oralExaminationService;

  @Autowired
  private ThesisService thesisService;

  /**
   * 获取答辩小组成员信息
   */
  @GetMapping("/examGroup")
  public String getExamGroup(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    int groupNo = 0;
    if (UserRoleEnum.STUDENT.getRole().equals(user.getRole())) {
      ProjectSelectResult projectSelectResult = (ProjectSelectResult) session
          .getAttribute("stuProject");
      groupNo = projectSelectResult.getGroupNum();
    }
    if (UserRoleEnum.TEACHER.getRole().equals(user.getRole())) {
      groupNo = user.getGroup();
    }

    List<UserVO> groupInfo = oralExaminationService.getGroupInfo(groupNo);
    model.addAttribute("teachers", groupInfo);
    return "examGroupInfo";
  }

  /**
   * 获取小组内的学生和课题和论文信息
   */
  @GetMapping("/examGroupStu")
  public String getGroupStu(@RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size, HttpSession session,
      Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    int groupNo = user.getGroup();
    List<ProjectSelectResult> groupStus = oralExaminationService.getGroupStus(groupNo);
    List<Thesis> thesis = thesisService.getGroupThesis(groupStus);
    model.addAttribute("project", groupStus);
    model.addAttribute("thesis", thesis);
    return "examGroupStu";
  }

  /**
   * 老师给学生打分
   * @param score
   * @param sno
   * @param session
   * @return
   */
  @ResponseBody
  @PostMapping("/examGroupScore")
  public ResultDTO scoreToStu(@RequestParam("score") Integer score,
      @RequestParam("sno") String sno, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    oralExaminationService.scoreToStu(score, sno, user);
    return ResultDTO.okOf();
  }

  /**
   * 学生查看自己的成绩
   * @param session
   * @param model
   * @return
   */
  @GetMapping("/examScore")
  public String getExamScore(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectSelectResult projectSelectResult = (ProjectSelectResult) session
        .getAttribute("stuProject");
    List<OralExamScoreVO> examScore = oralExaminationService
        .getExamScore(user, projectSelectResult);
    model.addAttribute("examScore", examScore);
    return "examScore";
  }




}
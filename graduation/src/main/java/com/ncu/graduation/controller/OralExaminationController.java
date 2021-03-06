package com.ncu.graduation.controller;

import com.ncu.graduation.dto.OralExamSearchDTO;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.error.EmUserOperatorError;
import com.ncu.graduation.error.RedirectException;
import com.ncu.graduation.service.impl.OralExaminationServiceImpl;
import com.ncu.graduation.service.impl.ProjectServiceImpl;
import com.ncu.graduation.vo.OralExamScoreVO;
import com.ncu.graduation.bo.OralExamStuProjectBO;
import com.ncu.graduation.vo.ProjectInfoVO;
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
  private OralExaminationServiceImpl oralExaminationService;

  @Autowired
  private ProjectServiceImpl projectService;

  /**
   * 获取答辩小组成员信息
   */
  @GetMapping("/examGroup")
  public String getExamGroup(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    if (user.getGroup() == null){
      throw new CommonException(EmUserOperatorError.ORALEXAM_NOT_ARRANGE);
    }
    List<UserVO> groupInfo = oralExaminationService.getGroupInfo(user);
    model.addAttribute("teachers", groupInfo);
    return "oralExamination/groupInfo";
  }

  /**
   * 获取小组内的学生和课题和论文信息
   */
  @GetMapping("/teacher/examGroupStu")
  public String getGroupStu(@RequestParam(name = "page", defaultValue = "1") Integer page,
      @RequestParam(name = "size", defaultValue = "10") Integer size, HttpSession session,
      Model model, OralExamSearchDTO oralExamSearchDTO) {
    UserVO user = (UserVO) session.getAttribute("user");
    if (user.getGroup() == null){
      throw new RedirectException(EmProjectError.ORAL_EXAM_NOT_ARRANGE);
    }
    PaginationDTO<OralExamStuProjectBO> groupStus = oralExaminationService
        .getGroupStus(user, page, size, oralExamSearchDTO);
    model.addAttribute(",oralExamSearchDTP", oralExamSearchDTO);
    model.addAttribute("groupStus", groupStus);
    return "oralExamination/groupStu";
  }

  /**
   * 老师给学生打分
   * @param score
   * @param sno
   * @param session
   * @return
   */
  @ResponseBody
  @PostMapping("/teacher/examGroupScore")
  public ResultDTO scoreToStu(@RequestParam("score") Integer score,
      @RequestParam("sno") String sno, HttpSession session) {
    UserVO user = (UserVO) session.getAttribute("user");
    if (user.getGroup() == null){
      throw new RedirectException(EmUserOperatorError.ORALEXAM_NOT_ARRANGE);
    }
    oralExaminationService.scoreToStu(score, sno, user);
    return ResultDTO.okOf();
  }

  /**
   * 学生查看自己的成绩
   * @param session
   * @param model
   * @return
   */
  @GetMapping("/student/examScore")
  public String getExamScore(HttpSession session, Model model) {
    UserVO user = (UserVO) session.getAttribute("user");
    ProjectInfoVO stuProject = projectService.getStuProject(user);
    OralExamScoreVO scoreVO ;
    if (stuProject == null) {
      throw new RedirectException(EmProjectError.NO_PROJECT);
    }
    if (user.getGroup() == null){
      throw new RedirectException(EmProjectError.ORAL_EXAM_NOT_ARRANGE);
    }
    scoreVO= oralExaminationService.getExamScore(user, stuProject);
    model.addAttribute("stuProject",stuProject.getProjectApply());
    model.addAttribute("examScore", scoreVO);
    return "oralExamination/examScore";
  }

}
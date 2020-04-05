package com.ncu.graduation.controller;

import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.ForeignRecord;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportRecord;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.service.ForeignLiteratureService;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 17:23
 * @description：外文资料controller
 * @modified By：
 * @version: 0.0.1
 */

@Controller
public class ForeignLiteratureController {

  @Autowired
  private ForeignLiteratureService foreignLiteratureService;


  /**
   * 教师外文资料页面, 获取我的课题的外文资料
   */
  @GetMapping("/teaForeignLiterature")
  public String getTeaForeignLiterature(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    List<TeaProjectDocumentVO<ForeignLiterature>> teaForeignLiterature = foreignLiteratureService
        .getTeaForeignLiterature(user);
    model.addAttribute("teaForeignLiterature", teaForeignLiterature);
    return "document/teaForeignLiterature";
  }

  /**
   * 审核外文资料
   */
  @ResponseBody
  @PostMapping("/foreignLiterature/verify")
  public ResultDTO verifyDocument(@Valid VerifyDocumentDTO verifyDocument) {
    foreignLiteratureService.verifyForeignLiterature(verifyDocument);
    return ResultDTO.okOf();
  }

  /**
   * 提交外文资料
   */
  @ResponseBody
  @PostMapping("/foreignLiterature/submit")
  public ResultDTO submitForeignLiterature(@RequestParam("oldFile") String oldFile,
      @RequestParam("foreignFile") MultipartFile foreignFile,
      @RequestParam("translationFile") MultipartFile translationFile,
      @RequestParam("pno") String pno,
      HttpServletRequest request) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    foreignLiteratureService
        .submitForeignLiterature(user, oldFile, foreignFile, translationFile, pno);
    return ResultDTO.okOf();
  }

  /**
   * 学生查看开题报告和审核结果
   */
  @GetMapping("/stuForeignLiterature")
  public String getStuForeignLiterature(HttpServletRequest request,
      Model model) {
    UserVO user = (UserVO) request.getSession().getAttribute("user");
    ProjectApply project = (ProjectApply) request.getSession().getAttribute("stuProject");

    StuProjectDocumentVO<ForeignLiterature> stuForeignLiterature = foreignLiteratureService
        .getStuForeignLiterature(user, project);
    model.addAttribute("stuForeignLiterature", stuForeignLiterature);
    return "document/stuForeignLiterature";
  }

  /**
   * 查看历史评价
   */
  @ResponseBody
  @GetMapping("/stuForeignLiterature/record")
  public ResultDTO getForeignRecordRecord(@RequestParam("fno") String fno, Model model) {
    List<ForeignRecord> foreignRecord = foreignLiteratureService.getForeignRecord(fno);
    model.addAttribute("foreignRecord", foreignRecord);
    return ResultDTO.okOf(foreignRecord);
  }


}

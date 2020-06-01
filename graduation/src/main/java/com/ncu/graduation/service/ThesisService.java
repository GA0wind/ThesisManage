package com.ncu.graduation.service;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisRecord;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 21:01
 * @description：论文Service
 * @modified By：
 * @version:0.0.1
 */

public interface ThesisService {

  /**
   * 获取老师名下的课题论文
   */
  List<TeaProjectDocumentVO<Thesis>> getTeaThesis(
      Map<String, ProjectSelectResultBO> teaProject);


  /**
   * 获取需要我盲审的论文
   */
  List<TeaProjectDocumentVO<Thesis>> getTeaBlindThesis(UserVO user);


  /**
   * 审核论文
   */
  void verifyThesis(VerifyDocumentDTO verifyDocumentDTO, UserVO user);

  /**
   * 提交论文
   */
  void submitThesis(UserVO user, MultipartFile file,
      String id, String pno);

  /**
   * 学生查看论文和审核
   */
  StuProjectDocumentVO<Thesis> getStuThesis(UserVO user,
      ProjectInfoVO project);

  /**
   * 获取审核记录
   */
  List<ThesisRecord> getThesisRecord(String pno);

  /**
   * 获取小组论文
   */
  List<Thesis> getGroupThesis(List<String> pnos);

  /**
   * 设置论文可修改
   */
  void setThesisModifiable(String pno);
}

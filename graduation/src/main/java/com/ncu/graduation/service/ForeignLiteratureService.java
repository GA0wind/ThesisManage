package com.ncu.graduation.service;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.ForeignRecord;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 17:27
 * @description：外文资料service
 * @modified By：
 * @version: 0.0.1
 */

public interface ForeignLiteratureService {


  /**
   * 获取教师的课题的外文信息
   */
  List<TeaProjectDocumentVO<ForeignLiterature>> getTeaForeignLiterature(UserVO user,
      Map<String, ProjectSelectResultBO> teaProject);


  /**
   * 指导老师审核外文资料
   */
  void verifyForeignLiterature(UserVO user, VerifyDocumentDTO verifyDocumentDTO);

  /**
   * 提交外文资料
   */
  void submitForeignLiterature(MultipartFile foreignFile,
      MultipartFile translationFile, String id, String pno);

  /**
   * 学生查看外文资料和审核
   */
  StuProjectDocumentVO<ForeignLiterature> getStuForeignLiterature(
      ProjectInfoVO projectInfoVO);

  /**
   * 获取外文资料审核记录
   */
  List<ForeignRecord> getForeignRecord(String pno);

  /**
   * 设置外文资料可修改
   */
  void setForeignLiteratureModifiable(String pno);
}

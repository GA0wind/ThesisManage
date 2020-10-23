package com.ncu.graduation.service;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportRecord;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 23:20
 * @description：开题报告Service
 * @modified By：
 * @version: 0.0.1
 */
public interface OpenReportService {

  /**
   * 获取教师的课题的开题报告
   */
   List<TeaProjectDocumentVO<OpenReport>> getTeaOpenReport(
      Map<String, ProjectSelectResultBO> teaProject);

  /**
   * 获取需要教师盲审的开题报告
   */
   List<TeaProjectDocumentVO<OpenReport>> getTeaBlindOpenReport(UserVO user);


  /**
   * 审核开题报告
   */
   void verifyOpenReport(VerifyDocumentDTO verifyDocumentDTO,
      UserVO user);

  /**
   * 提交开题报告
   */
   void submitOpenReport(MultipartFile file, String id, UserVO user, String pno);

  /**
   * 学生查看开题报告和审核
   */
   StuProjectDocumentVO<OpenReport> getStuOpenReport(ProjectInfoVO project);

  /**
   * 获取审核记录
   */
   List<OpenReportRecord> getOpenReportRecord(String pno);

  /**
   * 设置开题报告可修改
   */

   void setOpenReportModifiable(String pno);
}

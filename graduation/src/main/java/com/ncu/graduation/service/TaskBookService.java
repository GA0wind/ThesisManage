package com.ncu.graduation.service;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.List;
import java.util.Map;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 21:00
 * @description：任务书service
 * @modified By：
 * @version: 0.0.1
 */

public interface TaskBookService {


  /**
   * 获取老师的任务书页面
   */
  List<TeaProjectDocumentVO<TaskBook>> getTeaTaskBook(
      Map<String, ProjectSelectResultBO> teaProject);

  /**
   * 提交任务书
   */
  void submitTaskBook(UserVO user, MultipartFile file, String id, String pno);

  /**
   * 获取学生任务书
   */
  StuProjectDocumentVO<TaskBook> getStuTaskBook(ProjectInfoVO project);
}

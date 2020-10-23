package com.ncu.graduation.service.impl;

import com.ncu.graduation.bo.ProjectSelectResultBO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.mapper.TaskBookMapper;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.model.TaskBookExample;
import com.ncu.graduation.service.TaskBookService;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.StuProjectDocumentVO;
import com.ncu.graduation.vo.TeaProjectDocumentVO;
import com.ncu.graduation.vo.UserVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 21:00
 * @description：任务书service
 * @modified By：
 * @version: 0.0.1
 */

@Service
public class TaskBookServiceImpl implements TaskBookService {

  @Autowired
  private TaskBookMapper taskBookMapper;



  /**
   * 获取老师的任务书页面
   */
  @Override
  public List<TeaProjectDocumentVO<TaskBook>> getTeaTaskBook(Map<String, ProjectSelectResultBO> teaProject) {

    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k, l) -> pnos.add(k));
    if (pnos.isEmpty()){
      return new ArrayList<>();
    }
    TaskBookExample taskBookExample = new TaskBookExample();
    taskBookExample.createCriteria().andPnoIn(pnos);
    List<TaskBook> taskBooks = taskBookMapper.selectByExample(taskBookExample);
    Map<String, TaskBook> taskBookMap = new HashMap<>(16);
    taskBooks.forEach(k -> taskBookMap.put(k.getPno(), k));

    List<TeaProjectDocumentVO<TaskBook>> teaProjectDocumentVOS = new ArrayList<>();

    teaProject.forEach((k, l) -> {
      TeaProjectDocumentVO<TaskBook> teaProjectDocumentVO = new TeaProjectDocumentVO<>();
      teaProjectDocumentVO.setPno(k);
      teaProjectDocumentVO.setPname(l.getPname());
      teaProjectDocumentVO.setSno(l.getSno());
      teaProjectDocumentVO.setSname(l.getSname());
      teaProjectDocumentVO.setDocument(taskBookMap.get(k));
      teaProjectDocumentVOS.add(teaProjectDocumentVO);
    });
    return teaProjectDocumentVOS;
  }

  /**
   * 提交任务书
   * @param user
   * @param file
   * @param id
   * @param pno
   */
  @Transactional
  @Override
  public void submitTaskBook(UserVO user ,MultipartFile file, String id, String pno) {
    String filePath = FileSave.fileSave(file, FileTypeEnum.TASK_BOOK);
    TaskBook taskBook = new TaskBook();
    //判断是新增还是修改
    if (StringUtils.isBlank(id)){
      taskBook.setGmtCreate(new Date());
      taskBook.setGmtModified(taskBook.getGmtCreate());
    }else{
      taskBook.setId(Long.parseLong(id));
      taskBook.setGmtModified(new Date());
    }
    taskBook.setPno(pno);
    taskBook.setFilePath(filePath);
    if (StringUtils.isBlank(id)) {
      int result = taskBookMapper.insertSelective(taskBook);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR);
      }
    }else{
      TaskBookExample taskBookExample = new TaskBookExample();
      taskBookExample.createCriteria().andIdEqualTo(Long.parseLong(id));
      int result = taskBookMapper.updateByExample(taskBook, taskBookExample);
      if (result != 1) {
        throw new CommonException(EmCommonError.UNKNOWN_ERROR);
      }
    }

  }

  /**
   * 获取学生任务书
   * @param project
   * @return
   */
  @Override
  public StuProjectDocumentVO<TaskBook> getStuTaskBook(ProjectInfoVO project) {
    TaskBookExample taskBookExample = new TaskBookExample();
    taskBookExample.createCriteria().andPnoEqualTo(project.getProjectApply().getPno());
    List<TaskBook> taskBooks = taskBookMapper.selectByExample(taskBookExample);
    StuProjectDocumentVO<TaskBook> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    stuProjectDocumentVOS.setDocument(taskBooks.get(0));
    stuProjectDocumentVOS.setProject(project.getProjectApply());
    stuProjectDocumentVOS.setProjectSelect(project.getProjectSelectResult());
    return stuProjectDocumentVOS;
  }
}

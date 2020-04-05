package com.ncu.graduation.service;

import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmDocumentError;
import com.ncu.graduation.mapper.TaskBookMapper;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.model.TaskBookExample;
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
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ：grh
 * @date ：Created in 2020/3/29 21:00
 * @description：任务书service
 * @modified By：
 * @version: 0.0.1
 */

@Service
public class TaskBookService {

  @Autowired
  private TaskBookMapper taskBookMapper;

  @Autowired
  private ProjectService projectService;

  public List<TeaProjectDocumentVO<TaskBook>> getTeaTaskBook(UserVO user) {

    Map<String, ProjectSelectResult> teaProject = projectService.getTeaProject(user);
    List<String> pnos = new ArrayList<>();
    teaProject.forEach((k,l)->{
      pnos.add(k);
    });
    TaskBookExample taskBookExample = new TaskBookExample();
    taskBookExample.createCriteria().andPnoIn(pnos);
    List<TaskBook> taskBooks = taskBookMapper.selectByExample(taskBookExample);
    Map<String,TaskBook> taskBookMap = new HashMap<>();
    taskBooks.forEach((k)->{
      taskBookMap.put(k.getPno(),k);
    });

    List<TeaProjectDocumentVO<TaskBook>> teaProjectDocumentVOS = new ArrayList<>();

    teaProject.forEach((k,l)->{
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

  public void submitTaskBook(MultipartFile file, String pno) {
    String filePath = FileSave.fileSave(file, FileTypeEnum.TASK_BOOK);
    TaskBook taskBook = new TaskBook();
    taskBook.setPno(pno);
    taskBook.setTaskNo(UUID.randomUUID().toString().replaceAll("-",""));
    taskBook.setFilePath(filePath);
    taskBook.setGmtCreate(new Date());
    taskBook.setGmtModified(taskBook.getGmtCreate());
    int result = taskBookMapper.insertSelective(taskBook);
    if (result != 1){
      throw new CommonException(EmDocumentError.UNKNOWN_ERROR);
    }
  }

  public StuProjectDocumentVO<TaskBook> getStuTaskBook(UserVO user,
      ProjectApply project) {

    TaskBookExample taskBookExample = new TaskBookExample();
    taskBookExample.createCriteria().andPnoEqualTo(project.getPno());
    List<TaskBook> taskBooks = taskBookMapper.selectByExample(taskBookExample);

    StuProjectDocumentVO<TaskBook> stuProjectDocumentVOS = new StuProjectDocumentVO<>();
    stuProjectDocumentVOS.setDocument(taskBooks.get(0));
    stuProjectDocumentVOS.setProject(project);
    return stuProjectDocumentVOS;
  }
}

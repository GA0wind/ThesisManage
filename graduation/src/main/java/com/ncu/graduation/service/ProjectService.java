package com.ncu.graduation.service;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import com.ncu.graduation.dto.PaginationDTO;
import com.ncu.graduation.dto.ProjectApplyDTO;
import com.ncu.graduation.dto.ProjectPlanDTO;
import com.ncu.graduation.dto.SelectiveProjectDTO;
import com.ncu.graduation.dto.VerifyDocumentDTO;
import com.ncu.graduation.enums.FileTypeEnum;
import com.ncu.graduation.enums.UserRoleEnum;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.EmFileError;
import com.ncu.graduation.error.EmProjectError;
import com.ncu.graduation.mapper.ForeignLiteratureMapper;
import com.ncu.graduation.mapper.OpenReportMapper;
import com.ncu.graduation.mapper.ProjectApplyMapper;
import com.ncu.graduation.mapper.ProjectPlanMapper;
import com.ncu.graduation.mapper.ProjectSelectResultMapper;
import com.ncu.graduation.mapper.TaskBookMapper;
import com.ncu.graduation.mapper.TeacherMapper;
import com.ncu.graduation.mapper.ThesisMapper;
import com.ncu.graduation.model.College;
import com.ncu.graduation.model.ForeignLiterature;
import com.ncu.graduation.model.ForeignLiteratureExample;
import com.ncu.graduation.model.OpenReport;
import com.ncu.graduation.model.OpenReportExample;
import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.ProjectApplyExample;
import com.ncu.graduation.model.ProjectPlan;
import com.ncu.graduation.model.ProjectPlanExample;
import com.ncu.graduation.model.ProjectSelectResult;
import com.ncu.graduation.model.ProjectSelectResultExample;
import com.ncu.graduation.model.TaskBook;
import com.ncu.graduation.model.TaskBookExample;
import com.ncu.graduation.model.Teacher;
import com.ncu.graduation.model.TeacherExample;
import com.ncu.graduation.model.Thesis;
import com.ncu.graduation.model.ThesisExample;
import com.ncu.graduation.scheduler.SchedulerAllJob;
import com.ncu.graduation.util.FileSave;
import com.ncu.graduation.vo.ProjectApplyVO;
import com.ncu.graduation.vo.ProjectInfoVO;
import com.ncu.graduation.vo.ProjectProgressVO;
import com.ncu.graduation.vo.UserVO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 21:13
 * @description：课题service层
 * @modified By：
 * @version: 0.0.1
 */

@Service
public class ProjectService {

  @Autowired
  private TeacherMapper teacherMapper;

  @Autowired
  private ProjectSelectResultMapper projectSelectResultMapper;
  @Autowired
  private ProjectApplyMapper projectApplyMapper;
  @Autowired
  private ProjectPlanMapper projectPlanMapper;

  @Autowired
  private TaskBookMapper taskBookMapper;
  @Autowired
  private OpenReportMapper openReportMapper;
  @Autowired
  private ForeignLiteratureMapper foreignLiteratureMapper;
  @Autowired
  private ThesisMapper thesisMapper;
  @Autowired
  private UserService userService;

  /**
   * 获取我申请的课题
   */
  public PaginationDTO<ProjectApplyVO> getMyApplyProject(Integer page, Integer size, UserVO user) {
    PaginationDTO<ProjectApplyVO> paginationDTO = new PaginationDTO<>();
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andCreatorNoEqualTo(user.getAccountNo());

    PageMethod.startPage(page, size);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExample(projectApplyExample);
    PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplies);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectApplyVO> projectApplyVOS = new ArrayList<>();
    for (ProjectApply projectApply : projectApplies) {
      ProjectApplyVO projectApplyVO = new ProjectApplyVO();
      projectApplyVO.setPno(projectApply.getPno());
      projectApplyVO.setType(projectApply.getType());
      projectApplyVO.setPname(projectApply.getPname());
      projectApplyVO.setIsPass(projectApply.getIsPass());
      projectApplyVOS.add(projectApplyVO);
    }
    paginationDTO.setData(projectApplyVOS);
    return paginationDTO;
  }

  /**
   * 获取课题详情信息
   */
  public ProjectInfoVO getProjectInfo(String pno) {

    //获取课题信息
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExampleWithBLOBs(projectApplyExample);
    if (projectApplies == null || projectApplies.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    //查找课题双选结果
    ProjectSelectResultExample projectSelectResultExample = new ProjectSelectResultExample();
    projectSelectResultExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectSelectResult> projectSelectResults = projectSelectResultMapper
        .selectByExample(projectSelectResultExample);

    //复制字段
    ProjectInfoVO projectInfoVO = new ProjectInfoVO();
    if (projectSelectResults != null && !projectSelectResults.isEmpty()) {
      projectInfoVO.setProjectSelectResult(projectSelectResults.get(0));
    }
    projectInfoVO.setProjectApply(projectApplies.get(0));

    return projectInfoVO;
  }

  /**
   * 申请或修改课题
   */
  @Transactional
  public void applyOrUpdate(ProjectApplyDTO projectApplyDTO, UserVO user) {
    ProjectApply projectApply = new ProjectApply();
    //判断是修改还是新增, pno不为空, 就是修改
    if (!StringUtils.isBlank(projectApplyDTO.getPno())) {
      projectApply.setPno(projectApplyDTO.getPno());
      //判断是否有新文件, 如果新文件为空, 就不需要修改文件
      if (projectApplyDTO.getFile() == null || projectApplyDTO.getFile().isEmpty()) {
        projectApplyDTO.setFile(null);
      } else {
        String oldFilePath = FileTypeEnum.PROJECT.getPreUrl() + projectApplyDTO.getOldFilePath();
        File file = new File(oldFilePath);
        //删除旧文件
        try {
          Files.delete(file.toPath());
        } catch (IOException e) {
          e.printStackTrace();
          throw new CommonException(EmFileError.FILE_DELETE_FAIL);
        }
      }
    } else {
      //新课题, 设置新东西
      projectApply.setPno(UUID.randomUUID().toString().replaceAll("-", ""));
    }
    //构建持久层project
    projectApply.setPname(projectApplyDTO.getPname());
    projectApply.setContent(projectApplyDTO.getContent());
    String filePath = FileSave.fileSave(projectApplyDTO.getFile(), FileTypeEnum.PROJECT);
    projectApply.setFilePath(filePath);
    projectApply.setType(projectApplyDTO.getType());
    projectApply.setSchoolYear(user.getSchoolYear());
    projectApply.setCollege(user.getCollege());
    projectApply.setCreatorNo(user.getAccountNo());
    projectApply.setCreatorName(user.getName());
    projectApply.setTags(projectApplyDTO.getTags());
    int result = 0;
    //构建持久层projectApply, 修改更新不需要重新申请
    if (StringUtils.isBlank(projectApplyDTO.getPno())) {
      projectApply.setGmtCreate(new Date());
      projectApply.setGmtModified(projectApply.getGmtCreate());
      result = projectApplyMapper.insertSelective(projectApply);
    } else {
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(projectApply.getPno());
      result = projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    }
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR);
    }
  }

  /**
   * 撤销
   */
  @Transactional
  public void revoke(String pno) {
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    projectApplyMapper.deleteByExample(projectApplyExample);
  }

  /**
   * 获取单个课题信息, 用于审核或选题时查看简略课题信息
   */
  public ProjectApply getProject(String pno) {
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectApply> projectApplies = projectApplyMapper
        .selectByExampleWithBLOBs(projectApplyExample);
    if (projectApplies == null || projectApplies.isEmpty()) {
      throw new CommonException(EmProjectError.PROJECT_NO_NOT_EXIST);
    }
    return projectApplies.get(0);
  }

  /**
   * 获取需要我审核的课题
   */
  public PaginationDTO<ProjectApplyVO> getVerifyProject(Integer page, Integer size, UserVO user) {
    PaginationDTO<ProjectApplyVO> paginationDTO;
    List<ProjectApply> projectApplies;
    //如果是主任, 复审课题
    if (UserRoleEnum.DIRECTOR.getRole().equals(user.getRole())) {
      paginationDTO = new PaginationDTO<>();
      //分页
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andTrialNoEqualTo(user.getAccountNo())
          .andSchoolYearEqualTo(user.getSchoolYear());
      PageMethod.startPage(page, size);
      projectApplies = projectApplyMapper
          .selectByExample(projectApplyExample);
    } else {
      paginationDTO = new PaginationDTO<>();
      //分页
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andBlindTrialNoEqualTo(user.getAccountNo())
          .andSchoolYearEqualTo(user.getSchoolYear()).andIsDeleteEqualTo((byte)0);
      PageMethod.startPage(page, size);
      projectApplies = projectApplyMapper
          .selectByExample(projectApplyExample);
    }
    PageInfo<ProjectApply> pageInfo = new PageInfo<>(projectApplies);
    paginationDTO.setPagination((int) pageInfo.getTotal(), page, size);

    List<ProjectApplyVO> projectApplyVOS = new ArrayList<>();
    projectApplies.forEach((k) -> {
      ProjectApplyVO projectApplyVO = new ProjectApplyVO();
      projectApplyVO.setPno(k.getPno());
      projectApplyVO.setPname(k.getPname());
      projectApplyVO.setIsPass(k.getIsPass());
      projectApplyVO.setType(k.getType());
      projectApplyVO.setTrialGrade(k.getTrialGrade());
      projectApplyVO.setBlindTrialGrade(k.getBlindTrialGrade());

      projectApplyVOS.add(projectApplyVO);
    });

    paginationDTO.setData(projectApplyVOS);
    return paginationDTO;
  }


  /**
   * 审核课题
   */
  public void verifyProject(VerifyDocumentDTO verifyDocumentDTO, UserVO user) {
    ProjectApply projectApply = new ProjectApply();
    projectApply.setPno(verifyDocumentDTO.getPno());
    projectApply.setIsPass(verifyDocumentDTO.getIsPass());
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();

    //主任复审
    if (UserRoleEnum.DIRECTOR.getRole().equals(user.getRole())) {
      projectApply.setTrialComment(verifyDocumentDTO.getComment());
      projectApplyExample.createCriteria().andPnoEqualTo(projectApply.getPno())
          .andTrialNoEqualTo(user.getAccountNo());
    } else {
      //指导老师盲审
      projectApply.setBlindTrialGrade(Byte.parseByte(verifyDocumentDTO.getScore().toString()));
      projectApply.setBlindTrialComment(verifyDocumentDTO.getComment());
      projectApplyExample.createCriteria().andPnoEqualTo(projectApply.getPno())
          .andBlindTrialNoEqualTo(user.getAccountNo());
    }
    int result = projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR);
    }
  }

  /**
   * 复审
   */
  public void review(String pno, UserVO user) {
    //找到当前学年的学院主任
    TeacherExample teacherExample = new TeacherExample();
    teacherExample.createCriteria().andRoleEqualTo("director").andCollegeEqualTo(user.getCollege())
        .andSchoolYearLike("%" + user.getSchoolYear() + "%");
    List<Teacher> teachers = teacherMapper.selectByExample(teacherExample);
    if (teachers == null || teachers.isEmpty()) {
      throw new CommonException(EmProjectError.NOT_EXIST_DIRECTOR);
    }
    //修改数据
    ProjectApply projectApply = new ProjectApply();
    projectApply.setPno(pno);
    projectApply.setTrialNo(teachers.get(0).getTno());
    projectApply.setIsPass((byte)2);
    ProjectApplyExample projectApplyExample = new ProjectApplyExample();
    projectApplyExample.createCriteria().andPnoEqualTo(pno);
    int i = projectApplyMapper.updateByExampleSelective(projectApply, projectApplyExample);
    if (i != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "复审申请失败, 请检查参数");
    }
  }


  /**
   * 获取教师的多个有效项目
   */
  public Map<String, ProjectSelectResult> getTeaProject(UserVO user) {
    ProjectSelectResultExample example = new ProjectSelectResultExample();
    example.createCriteria().andTnoEqualTo(user.getAccountNo())
        .andSchoolYearEqualTo(user.getSchoolYear());
    List<ProjectSelectResult> projects = projectSelectResultMapper.selectByExample(example);
    Map<String, ProjectSelectResult> projectMap = new HashMap<>(16);
    if (projects != null) {
      projects.forEach((k) -> {
        projectMap.put(k.getPno(), k);
      });
    }
    return projectMap;
  }

  /**
   * 获取学生的一个项目
   */
  public ProjectApply getStuProject(UserVO user) {
    ProjectSelectResultExample example = new ProjectSelectResultExample();
    example.createCriteria().andSnoEqualTo(user.getAccountNo());
    List<ProjectSelectResult> projects = projectSelectResultMapper.selectByExample(example);

    if (projects != null && !projects.isEmpty()) {
      ProjectApplyExample projectApplyExample = new ProjectApplyExample();
      projectApplyExample.createCriteria().andPnoEqualTo(projects.get(0).getPno());
      List<ProjectApply> projectApplies = projectApplyMapper
          .selectByExampleWithBLOBs(projectApplyExample);
      return projectApplies.get(0);
    } else {
      return null;
    }
  }

  /**
   * 管理员添加可选课题
   */
  public void addSelectiveProject(SelectiveProjectDTO projectApplyDTO,
      UserVO user) {
    String filePath = FileSave.fileSave(projectApplyDTO.getFile(), FileTypeEnum.PROJECT);
    ProjectApply projectApply = new ProjectApply();
    projectApply.setPno(UUID.randomUUID().toString().replaceAll("-", ""));
    projectApply.setPname(projectApplyDTO.getPname());
    projectApply.setContent(projectApplyDTO.getContent());
    projectApply.setType(projectApplyDTO.getType());
    projectApply.setCollege(projectApplyDTO.getCollege());
    projectApply.setCreatorNo(projectApplyDTO.getCreatorNo());
    projectApply.setCreatorName(projectApplyDTO.getCreatorName());
    projectApply.setIsSelect((byte) 0);
    projectApply.setIsPass((byte) 1);
    projectApply.setBlindTrialNo(user.getAccountNo());
    projectApply.setBlindTrialGrade(projectApplyDTO.getScore());
    projectApply.setBlindTrialComment(projectApplyDTO.getComment());
    projectApply.setGmtCreate(new Date());
    projectApply.setGmtModified(projectApply.getGmtCreate());
    projectApply.setSchoolYear(user.getSchoolYear());
    projectApply.setFilePath(filePath);
    int result = projectApplyMapper.insertSelective(projectApply);
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "管理员添加课题失败");
    }
  }

  /**
   * 管理员设置毕设计划
   */
  @Transactional
  public void setProjectPlan(ProjectPlanDTO projectPlanDTO) {
    ProjectPlan plan = new ProjectPlan();
    BeanUtils.copyProperties(projectPlanDTO, plan);
    int result;
    if (projectPlanDTO.getId() != null) {
      ProjectPlanExample example = new ProjectPlanExample();
      example.createCriteria().andIdEqualTo(projectPlanDTO.getId());
      result = projectPlanMapper.updateByExampleSelective(plan, example);
    } else {
      result = projectPlanMapper.insert(plan);
    }

    //存好数据后开启定时任务
    SchedulerAllJob schedulerAllJob = new SchedulerAllJob();
    schedulerAllJob.setProjectPlan(plan);
    try {
      schedulerAllJob.scheduleJobs();
    } catch (SchedulerException e) {
      e.printStackTrace();
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "盲审定时失败" + e.getMessage());
    }
    if (result != 1) {
      throw new CommonException(EmCommonError.UNKNOWN_ERROR, "毕设计划插入失败");
    }
  }

  /**
   * 管理员获取所有课题计划
   */
  public List<ProjectPlan> getProjectPlans() {
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.setOrderByClause("id");
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    return projectPlans;
  }

  /**
   * 管理员获取当前学年课题计划
   */
  public ProjectPlan getProjectPlan(String schoolYear) {
    ProjectPlanExample projectPlanExample = new ProjectPlanExample();
    projectPlanExample.createCriteria().andSchoolYearEqualTo(schoolYear);
    List<ProjectPlan> projectPlans = projectPlanMapper.selectByExample(projectPlanExample);
    return projectPlans.get(0);
  }

  /**
   * 查询未完成学生
   */
  public List<ProjectSelectResult> getUndoneStu(UserVO user, String type) {

    //根据类型判断, 并获取课题编号
    List<String> pnos = new ArrayList<>();
    switch (type) {
      case "taskBook": {
        TaskBookExample example = new TaskBookExample();
        example.createCriteria().andSchoolYearEqualTo(user.getSchoolYear());
        List<TaskBook> taskBooks = taskBookMapper.selectByExample(example);
        taskBooks.forEach((k) -> {
          pnos.add(k.getPno());
        });
        break;
      }
      case "openReport": {
        OpenReportExample openReportExample = new OpenReportExample();
        openReportExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear());
        List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
        openReports.forEach((k) -> {
          pnos.add(k.getPno());
        });
        break;
      }
      case "foreignFile": {
        ForeignLiteratureExample foreignLiteratureExample = new ForeignLiteratureExample();
        foreignLiteratureExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear());
        List<ForeignLiterature> foreignLiteratures = foreignLiteratureMapper
            .selectByExample(foreignLiteratureExample);
        foreignLiteratures.forEach((k) -> {
          pnos.add(k.getPno());
        });
        break;
      }
      case "thesis": {
        ThesisExample thesisExample = new ThesisExample();
        thesisExample.createCriteria().andSchoolYearEqualTo(user.getSchoolYear());
        List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
        theses.forEach((k) -> {
          pnos.add(k.getPno());
        });
        break;
      }
      default:
        break;
    }
    if (pnos.isEmpty()) {
      return new ArrayList<>();
    }
    //获取所有有效选题
    ProjectSelectResultExample selectResultExample = new ProjectSelectResultExample();
    selectResultExample.createCriteria().andPnoNotIn(pnos);
    selectResultExample.setOrderByClause("college desc");
    List<ProjectSelectResult> projectSelectResults = projectSelectResultMapper
        .selectByExample(selectResultExample);
    List<College> collegeList = userService.getCollege();
    //学院编号，学院名
    Map<String,String> collegeMap = new HashMap<>();
    collegeList.forEach(k->{
      collegeMap.put(k.getCollegeNo(),k.getCollegeName());
    });
    projectSelectResults.forEach(k->{
      k.setCollege(collegeMap.get(k.getCollege()));
    });
    return projectSelectResults;
  }

  /**
   * 获取课题进度
   * @param pno
   * @return
   */
  public ProjectProgressVO getProjectProgress(String pno) {
    ProjectSelectResultExample projectSelectResultExample = new ProjectSelectResultExample();
    projectSelectResultExample.createCriteria().andPnoEqualTo(pno);
    List<ProjectSelectResult> selectResults = projectSelectResultMapper
        .selectByExample(projectSelectResultExample);
    TaskBookExample taskBookExample = new TaskBookExample();
    taskBookExample.createCriteria().andPnoEqualTo(pno);
    List<TaskBook> taskBooks = taskBookMapper.selectByExample(taskBookExample);
    OpenReportExample openReportExample = new OpenReportExample();
    List<OpenReport> openReports = openReportMapper.selectByExample(openReportExample);
    ForeignLiteratureExample foreignLiteratureExample = new ForeignLiteratureExample();
    foreignLiteratureExample.createCriteria().andPnoEqualTo(pno);
    List<ForeignLiterature> foreignLiteratures = foreignLiteratureMapper
        .selectByExample(foreignLiteratureExample);
    ThesisExample thesisExample = new ThesisExample();
    thesisExample.createCriteria().andPnoEqualTo(pno);
    List<Thesis> theses = thesisMapper.selectByExample(thesisExample);
    ProjectProgressVO projectProgressVO = new ProjectProgressVO();
    projectProgressVO.setSelectResult(selectResults.get(0));

    if (taskBooks != null && !taskBooks.isEmpty()){
      projectProgressVO.setTaskBook(taskBooks.get(0));
    }
    if (openReports != null && !openReports.isEmpty()){
      projectProgressVO.setOpenReport(openReports.get(0));
    }
    if (foreignLiteratures != null && !foreignLiteratures.isEmpty()){
      projectProgressVO.setForeignLiterature(foreignLiteratures.get(0));
    }
    if (theses != null && !theses.isEmpty()){
      projectProgressVO.setThesis(theses.get(0));
    }
    return projectProgressVO;
  }
}

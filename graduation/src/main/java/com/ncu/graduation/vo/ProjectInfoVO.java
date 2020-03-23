package com.ncu.graduation.vo;

import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import java.util.Date;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 22:19
 * @description：传给前端的课题详情信息
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectInfoVO {
  private Long id;
  private String no;
  private String name;
  private String type;
  private String filePath;
  private String tags;
  private String schoolYear;
  private Date gmtCreate;
  private Date gmtModified;
  private Byte isPass;
  private String content;
  private String studentName;
  private String teacherName;
  private ProjectApply projectApply;
}

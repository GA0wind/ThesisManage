package com.ncu.graduation.dto;

import com.ncu.graduation.model.ProjectApply;
import com.ncu.graduation.model.Student;
import com.ncu.graduation.model.Teacher;
import java.util.Date;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 22:02
 * @description：课题详情信息
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class ProjectInfoDTO {
  private Long id;
  private String no;
  private String name;
  private String filePath;
  private String type;
  private String tags;
  private String schoolYear;
  private Date gmtCreate;
  private Date gmtModified;
  private Byte isPass;
  private String content;
  private Student student;
  private Teacher teacher;
  private ProjectApply projectApply;
}

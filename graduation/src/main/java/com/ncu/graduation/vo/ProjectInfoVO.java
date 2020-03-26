package com.ncu.graduation.vo;

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
public class ProjectInfoVO {
  private String pno;
  private String pname;
  private String sno;
  private String sname;
  private String tno;
  private String tname;
  private String content;
  private String filePath;
  private String type;
  private String tags;
  private String schoolYear;
  private Byte isPass;
}

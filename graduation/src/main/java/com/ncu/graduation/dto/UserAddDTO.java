package com.ncu.graduation.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/7 15:42
 * @description：管理员添加用户DTO
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class UserAddDTO {
  @NotBlank(message = "账号不能为空")
  private String userNo;
  @NotBlank(message = "姓名不能为空")
  private String name;
  @NotBlank(message = "学院不能为空")
  private String college;
  @NotBlank(message = "密码不能为空")
  private String password;
  @NotBlank(message = "角色不能为空")
  private String role;
  private Byte isModify;
  private Byte leadStudentNum;
  private String title;
  private String major;
  private String groupNum;
  private String gradeClass;
  private String schoolYear;
}

package com.ncu.graduation.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/7 15:42
 * @description：用户修改密码DTO
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class UserModifyPwdDTO {
  @NotBlank(message = "用户账号不能为空")
  private String userNo;
  @NotBlank(message = "旧密码不能为空")
  private String oldPwd;
  @NotBlank(message = "新密码不能为空")
  private String newPwd;
  @NotBlank(message = "确认密码不能为空")
  private String confirmNewPwd;
}

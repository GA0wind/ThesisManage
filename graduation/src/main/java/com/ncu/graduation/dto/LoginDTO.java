package com.ncu.graduation.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author ：mmzs
 * @date ：Created in 2020/3/19 15:40
 * @description：登录所需参数
 * @modified By：
 * @version:
 */
@Data
public class LoginDTO {
  @NotBlank(message = "用户名不能为空")
  private String accountNo;
  @NotBlank(message = "密码不能为空")
  private String accountPwd;
  @NotBlank(message = "验证码不能为空")
  private String code;
  @NotBlank(message = "角色不能为空")
  private String role;
  private String schoolYear;
}

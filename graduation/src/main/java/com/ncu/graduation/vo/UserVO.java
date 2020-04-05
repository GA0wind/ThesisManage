package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：pierce
 * @date ：Created in 2020/3/19 18:24
 * @description：通用账户信息
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class UserVO {
  private Long id;
  private String accountNo;
  private String name;
  private String role;
  private String college;
  private String schoolYear;
  private Byte leadNumber;
  private Integer group;
}

package com.ncu.graduation.dto;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/15 23:05
 * @description：用户搜索DTO
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class UserSearchDTO {

  private String userNo;
  private String name;
  private String college;
  private String role;
}

package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 18:57
 * @description：师生双选中的选择VO
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectSelectVO {
  private String pno;
  private String pname;
  private String selectNo;
  private String selectName;
  private Byte result;
}

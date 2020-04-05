package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/31 0:10
 * @description：我的选题情况VO
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectSelectVO {
  private String pno;
  private String pname;
  private String creatorNo;
  private String creatorName;
  private String selectNo;
  private String selectName;
  private String type;
  private Byte result;
}

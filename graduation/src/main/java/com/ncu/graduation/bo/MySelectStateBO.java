package com.ncu.graduation.bo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/5/12 21:26
 * @description：我的选题情况
 * @modified By：
 * @version:
 */
@Data
public class MySelectStateBO {
  private String pno;
  private String pname;
  private String creatorNo;
  private String creatorName;
  private Byte result;
}

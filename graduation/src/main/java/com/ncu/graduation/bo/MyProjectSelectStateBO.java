package com.ncu.graduation.bo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/5/12 22:01
 * @description：我的课题被选情况bo
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class MyProjectSelectStateBO {
  private String pno;
  private String selectNo;
  private String selectName;
  private Byte result;
}

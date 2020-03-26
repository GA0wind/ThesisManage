package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 19:48
 * @description：课题列表展示
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectViewVO {
  private String pno;
  private String pname;
  private String type;
  private String tname;
  private String sname;
  private Byte isPass;
}

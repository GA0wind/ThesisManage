package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/6 14:23
 * @description：答辩学生展示模型
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class OralExamStuProjectVO {
  private String pno;
  private String pname;
  private String sno;
  private String sname;
  private String thesis;
  private Byte score;
}

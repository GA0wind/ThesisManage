package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 22:55
 * @description：课题申请展示模型
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class ProjectApplyVO {
  private String pno;
  private String pname;
  private String type;
  private Byte isPass;
}

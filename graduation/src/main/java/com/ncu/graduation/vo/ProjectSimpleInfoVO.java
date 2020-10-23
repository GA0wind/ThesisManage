package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/3/30 23:14
 * @description：课题简要信息
 * @modified By：
 * @version: 0.0.1
 */

@Data
public class ProjectSimpleInfoVO {
  private String pno;
  private String pname;
  private String content;
  private String filePath;
  private String tags;
  private String type;
}

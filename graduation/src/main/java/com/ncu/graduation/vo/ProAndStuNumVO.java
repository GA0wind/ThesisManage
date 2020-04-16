package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/12 17:34
 * @description：统计一学院内课题数和人数
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProAndStuNumVO {
  private String college;
  private Long proNum;
  private Long stuNum;
}

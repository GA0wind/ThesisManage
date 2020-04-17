package com.ncu.graduation.dto;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/7 18:51
 * @description：毕设计划
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectPlanDTO {
  private Long id;
  private String projectApplyTime;
  private String projectSelectTime;
  private String taskBookTime;
  private String openReportTime;
  private String foreignLiteratureTime;
  private String oralExaminationTime;
  private String thesisTime;
  private String schoolYear;
  private String scoreComposition;
}

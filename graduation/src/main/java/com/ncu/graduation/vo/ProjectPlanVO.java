package com.ncu.graduation.vo;

import lombok.Data;

/**
 * @author ：grh
 * @date ：Created in 2020/4/19 18:03
 * @description：毕设计划展示
 * @modified By：
 * @version: 0.0.1
 */
@Data
public class ProjectPlanVO {

  private Long id;

  private String projectApplyTime;
  private String projectApplyIsOver;

  private String projectSelectTime;
  private String projectSelectIsOver;

  private String taskBookTime;
  private String taskBookIsOver;

  private String openReportTime;
  private String openReportIsOver;

  private String foreignLiteratureTime;
  private String foreignLiteratureIsOver;

  private String oralExaminationTime;
  private String oralExaminationIsOver;

  private String thesisTime;
  private String thesisIsOver;

  private String schoolYear;

  private String scoreComposition;
}

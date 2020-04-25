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
  private Byte projectApplyIsOver;
  private String projectSelectTime;
  private Byte projectSelectIsOver;

  private String taskBookTime;
  private Byte taskBookIsOver;

  private String openReportTime;
  private Byte openReportIsOver;

  private String foreignLiteratureTime;
  private Byte foreignLiteratureIsOver;

  private String oralExaminationTime;
  private Byte oralExaminationIsOver;

  private String thesisTime;
  private Byte thesisIsOver;

  private String schoolYear;

  private String scoreComposition;
}

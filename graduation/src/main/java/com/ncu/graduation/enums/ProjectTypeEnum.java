package com.ncu.graduation.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：grh
 * @date ：Created in 2020/4/24 22:49
 * @description：课题类型枚举
 * @modified By：
 * @version: 0.0.1
 */
public enum ProjectTypeEnum {
  SOFTWARE_COLLEGE("省级"),
  ART_COLLEGE("院级"),
  ;

  private String projectType;

  ProjectTypeEnum(String projectType) {
    this.projectType = projectType;
  }

  public String getProjectType() {
    return projectType;
  }

  public static List<String> getProjectTypeList(){
    List<String> projectTypeList = new ArrayList<>();
    for (ProjectTypeEnum value : ProjectTypeEnum.values()) {
      projectTypeList.add(value.getProjectType());
    }
    return projectTypeList;
  }
}

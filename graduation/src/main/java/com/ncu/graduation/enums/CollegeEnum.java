package com.ncu.graduation.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 23:29
 * @description：学院枚举类
 * @modified By：
 * @version: 0.0.1
 */
public enum CollegeEnum {
  SOFTWARE_COLLEGE("软件学院"),
  ART_COLLEGE("艺术学院"),
      ;

  private String collegeName;

  CollegeEnum(String collegeName) {
    this.collegeName = collegeName;
  }


  public String getCollegeName() {
    return collegeName;
  }

  public static CollegeEnum judgeCollege(String collegeName){
    for (CollegeEnum value : CollegeEnum.values()) {
      if (value.getCollegeName().equals(collegeName)){
        return value;
      }
    }
    return null;
  }

  public static List<CollegeEnum> getCollegeList(){
    List<CollegeEnum> collegeList = new ArrayList<>();
    for (CollegeEnum value : CollegeEnum.values()) {
      collegeList.add(value);
    }
    return collegeList;
  }
}

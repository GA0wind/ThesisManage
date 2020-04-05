package com.ncu.graduation.enums;

/**
 * @author ：grh
 * @date ：Created in 2020/3/25 23:29
 * @description：学院枚举类
 * @modified By：
 * @version: 0.0.1
 */
public enum CollegeEnum {
  SOFTWARE_COLLEGE("1000","软件学院"),
  ART_COLLEGE("2000","艺术学院"),
      ;

  private String collegeCode;
  private String collegeName;

  CollegeEnum(String collegeCode, String collegeName) {
    this.collegeCode = collegeCode;
    this.collegeName = collegeName;
  }

  public String getCollegeCode() {
    return collegeCode;
  }

  public String getCollegeName() {
    return collegeName;
  }

  public static CollegeEnum judgeCollege(String collegeCode){
    for (CollegeEnum value : CollegeEnum.values()) {
      if (value.collegeCode.equals(collegeCode)){
        return value;
      }
    }
    return null;
  }
}

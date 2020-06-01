package com.ncu.graduation.enums;


public enum FileTypeEnum {
  PROJECT("project", "D://FinallySchool/project/"),
  OPENING_REPORT("openReport", "D://FinallySchool/openingReport/"),
  TASK_BOOK("taskBook", "D://FinallySchool/taskBook/"),
  BULLETIN("bulletin", "D://FinallySchool/bulletin/"),
  THESIS("thesis", "D://FinallySchool/thesis/"),
  FOREIGNFILE("foreignFile", "D://FinallySchool/foreignFile/"),
  TRANSLATIONFILE("translationFile", "D://FinallySchool/translationFile/");


  private String type;
  private String preUrl;

  FileTypeEnum(String type, String preUrl) {
    this.type = type;
    this.preUrl = preUrl;
  }

  public String getType() {
    return type;
  }

  public String getPreUrl() {
    return preUrl;
  }

  static public FileTypeEnum judgeType(String type) {
    for (FileTypeEnum value : FileTypeEnum.values()) {
      if (type.equals(value.getType())) {
        return value;
      }
    }
    return null;
  }

  /**
   * 任务书 开题报告等文档, 只能word或者pdf
   */
  static public boolean checkFileType(String fileName) {
    String[] fileNameArr = new String[0];
    if (fileName != null) {
      fileNameArr = fileName.split("\\.");
    }
    // 获取文件的后缀名
    String suffix = fileNameArr[fileNameArr.length - 1];

    if (!suffix.equalsIgnoreCase("doc") &&
        !suffix.equalsIgnoreCase("docx") &&
        !suffix.equalsIgnoreCase("pdf")) {
      return false;
    }
    return true;
  }

  /**
   * 检测文件类型, 只能excel
   */
  static public boolean checkExcelFile(String fileName) {
    String[] fileNameArr = new String[0];
    if (fileName != null) {
      fileNameArr = fileName.split("\\.");
    }
    // 获取文件的后缀名
    String suffix = fileNameArr[fileNameArr.length - 1];

    if (!suffix.equalsIgnoreCase("xls") &&
        !suffix.equalsIgnoreCase("xlsx")) {
      return false;
    }
    return true;
  }

}

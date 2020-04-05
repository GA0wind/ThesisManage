package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/3/28 18:11
 * @description：论文相关文档错误枚举
 * @modified By：
 * @version: 0.0.1
 */
public enum EmDocumentError implements CommonError {

  /**
   *
   */
  PARAMETER_VALIDATION_ERROR(10001, "参数不合法"),
  UNKNOWN_ERROR(10002, "未知错误"),

  DOCUMENT_IS_EMPTY(60001, "文件为空"),
  STUDENT_CAN_USE(60002, "该页面为学生可进"),

  ;
  private Integer errCode;
  private String errMsg;

  EmDocumentError(Integer errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  @Override
  public int getErrCode() {
    return this.errCode;
  }

  @Override
  public String getErrMsg() {
    return this.errMsg;
  }

  @Override
  public CommonError setErrMsg(String errMsg) {
    this.errMsg = errMsg;
    return this;
  }

}


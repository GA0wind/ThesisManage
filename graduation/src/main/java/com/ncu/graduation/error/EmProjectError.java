package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 21:35
 * @description：枚举课题中的操作异常
 * @modified By：
 * @version: 0.0.1
 */
public enum EmProjectError implements CommonError{
  /**
   *
   */
  PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
  UNKNOWN_ERROR(10002,"未知错误"),

  PROJECT_NO_NOT_EXIST(40001,"课题编号不存在"),
  ;
  private Integer errCode;
  private String errMsg;

  EmProjectError(Integer errCode, String errMsg) {
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

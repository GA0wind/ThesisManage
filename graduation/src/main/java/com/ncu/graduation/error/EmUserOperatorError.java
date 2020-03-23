package com.ncu.graduation.error;

public enum EmUserOperatorError implements CommonError   {



  //通用错误类型10001
  PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
  UNKNOWN_ERROR(10002,"未知错误"),

  USER_NOT_LOGIN(30001,"用户未登录"),
  USER_LOGIN_FAIL(30002,"登录失败, 请检查账号密码"),

  ;

  EmUserOperatorError(Integer errCode, String errMsg)
  {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

  private Integer errCode;
  private String errMsg;

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

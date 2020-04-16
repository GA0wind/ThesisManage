package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/4/14 15:34
 * @description：通用错误类型
 * @modified By：
 * @version:
 */
public enum  EmCommonError  implements CommonError{
  //通用错误类型10001
  PARAMETER_VALIDATION_ERROR(1001,"参数不合法"),
  UNKNOWN_ERROR(1002,"未知错误"),
  ;
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

  EmCommonError(Integer errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }
}

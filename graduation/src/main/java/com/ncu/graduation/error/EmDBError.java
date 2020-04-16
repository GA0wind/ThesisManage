package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/4/14 15:31
 * @description：数据库异常
 * @modified By：
 * @version: 0.0.1
 */
public enum EmDBError  implements CommonError{



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
}

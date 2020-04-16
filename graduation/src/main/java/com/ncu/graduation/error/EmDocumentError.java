package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/3/28 18:11
 * @description：论文相关文档错误枚举
 * @modified By：
 * @version: 0.0.1
 */
public enum EmDocumentError implements CommonError {



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


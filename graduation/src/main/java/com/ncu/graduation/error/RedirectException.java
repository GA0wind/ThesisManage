package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/4/18 17:04
 * @description：重定向错误
 * @modified By：
 * @version: 0.0.1
 */
public class RedirectException extends RuntimeException implements CommonError{

  private CommonError commonError;

  //直接接受EmBusinessError的传参用于构造业务异常
  public RedirectException(CommonError commonError)
  {
    super();                   //
    this.commonError = commonError;
  }

  //接受自定义errMsg的方式构造业务异常
  public RedirectException(CommonError commonError, String errMsg)
  {
    super();
    this.commonError = commonError;
    this.commonError.setErrMsg(errMsg);
  }


  @Override
  public int getErrCode() {
    return this.commonError.getErrCode();
  }

  @Override
  public String getErrMsg() {
    return this.commonError.getErrMsg();
  }

  @Override
  public CommonError setErrMsg(String errMsg) {
    this.commonError.setErrMsg(errMsg);
    return this;
  }
}

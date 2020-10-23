package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/4/14 15:32
 * @description：文件异常
 * @modified By：
 * @version:
 */
public enum EmFileError implements CommonError{


  FILE_IS_EMPTY(4001,"文件为空"),
  FILE_IS_EXIST(4002,"文件已存在"),
  FILE_IS_NOT_EXIST(4003,"文件不存在"),

  FILE_UPLOAD_FAIL(4004,"文件上传失败, 请重试"),
  FILE_TYPE_IS_EMPTY(4005,"文件类型为空"),
  FILE_DELETE_FAIL(4006,"文件删除失败"),
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

  EmFileError(Integer errCode, String errMsg) {
    this.errCode = errCode;
    this.errMsg = errMsg;
  }

}

package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/3/28 18:11
 * @description：论文相关文档错误枚举
 * @modified By：
 * @version: 0.0.1
 */
public enum EmDocumentError implements CommonError {
  TASK_SUBMIT_IS_NOT_TIME(6001,"不在任务书提交时间, 无法提交"),
  OPENREPORT_SUBMIT_IS_NOT_TIME(6002,"不在开题报告提交时间, 无法提交"),
  FOREIGNLITERATURE_SUBMIT_IS_NOT_TIME(6003,"不在外文资料提交时间, 无法提交"),
  THESIS_SUBMIT_IS_NOT_TIME(6004,"不在论文提交时间, 无法提交"),
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


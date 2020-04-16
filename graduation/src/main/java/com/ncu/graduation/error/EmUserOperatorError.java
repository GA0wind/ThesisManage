package com.ncu.graduation.error;

public enum EmUserOperatorError implements CommonError   {

  USER_NOT_LOGIN(3001,"用户未登录"),
  USER_LOGIN_FAIL(3002,"登录失败, 请检查账号密码"),
  STUDENT_CAN_USE(3003, "学生可进"),
  ADMIN_CAN_USE(3004, "管理员可进"),
  TEACHER_CAN_USE(3005, "老师可进"),
  ORALEXAM_NOT_ARRANGE(3006, "答辩情况未安排, 请等待通知"),
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

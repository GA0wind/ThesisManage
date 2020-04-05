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
  PROJECT_SELECT_OVER_MAX_NUM(50001,"已选课题选择数量大于可选择最大数量"),
  PROJECT_SELECT_REPEAT(50002,"不能重复选择课题"),
  PROJECT_WAS_SELECTED_OVER_MAX_NUM(50002,"该课题被选择数量大于可被选择最大数量"),
  TEACHER_LEAD_STUDENT_NUM_IS_ENOUGH(50003,"指导学生数已达上限"),
  STUDENT_ONLY_ONE_PROJECT(50004,"已选定课题, 无法更改"),
  NO_PROJECT(50003,"你没有课题"),
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

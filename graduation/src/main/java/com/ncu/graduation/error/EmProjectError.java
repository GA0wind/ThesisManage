package com.ncu.graduation.error;

/**
 * @author ：grh
 * @date ：Created in 2020/3/21 21:35
 * @description：枚举课题中的操作异常
 * @modified By：
 * @version: 0.0.1
 */
public enum EmProjectError implements CommonError{

  NO_PROJECT(5001,"没有课题"),
  PROJECT_NO_NOT_EXIST(5002,"课题编号不存在"),
  PROJECT_SELECT_OVER_MAX_NUM(5003,"已选课题选择数量大于可选择最大数量"),
  PROJECT_SELECT_REPEAT(5004,"不能重复选择课题"),
  PROJECT_WAS_SELECTED_OVER_MAX_NUM(5005,"当前课题已达最大选择人数, 请等待重试或换一个课题"),
  TEACHER_LEAD_STUDENT_NUM_IS_ENOUGH(5006,"指导学生数已达上限"),
  STUDENT_ONLY_ONE_PROJECT(5007,"已选定课题, 无法报名"),
  NOT_EXIST_DIRECTOR(5008,"主任不存在, 请联系管理员"),
  PROJECT_APPLY_IS_NOT_TIME(5009,"不在课题申请时间, 无法申请"),
  PROJECT_SELECT_IS_NOT_TIME(5010,"不在课题选择时间, 无法报名"),
  USER_NOT_HAVE_THE_PROJECT(5011,"当前用户无法对该课题操作"),
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

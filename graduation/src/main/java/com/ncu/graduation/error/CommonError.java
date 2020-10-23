package com.ncu.graduation.error;

public interface CommonError {
      int getErrCode();
      String getErrMsg();
      CommonError setErrMsg(String errMsg);
}

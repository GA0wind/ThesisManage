package com.ncu.graduation.error;

public enum EmBulletinError implements CommonError   {

    BULLETIN_PUBLISH_FAIL(2001,"公告发布失败"),

    ;

    EmBulletinError(Integer errCode, String errMsg)
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

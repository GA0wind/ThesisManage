package com.ncu.graduation.error;

public enum EmBulletinError implements CommonError   {



    //通用错误类型10001
    PARAMETER_VALIDATION_ERROR(10001,"参数不合法"),
    UNKNOWN_ERROR(10002,"未知错误"),

    BULLETIN_PUBLISH_FAIL(2000,"公告发布失败"),
    BULLETIN_FILE_IS_EMPTY(2001,"文件为空"),
    BULLETIN_FILE_IS_EXIST(2002,"文件已存在"),
    BULLETIN_FILE_UPLOAD_FAIL(2003,"文件上传失败, 请重试"),
    BULLETIN_FILE_TYPE_IS_EMPTY(2004,"文件类型为空, 下载失败, 请重试"),
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

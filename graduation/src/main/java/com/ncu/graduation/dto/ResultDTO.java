package com.ncu.graduation.dto;


import com.ncu.graduation.error.CommonError;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmBulletinError;
import lombok.Data;

@Data
public class ResultDTO<T> {
    private Integer code;
    private String message;
    private T data;

    public static ResultDTO errorOf(Integer code, String message) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMessage(message);
        return resultDTO;
    }

    public static ResultDTO errorOf(CommonError commonError) {

        return errorOf(commonError.getErrCode(), commonError.getErrMsg());
    }

    public static ResultDTO errorOf(CommonException ex) {
        return errorOf(ex.getErrCode(), ex.getErrMsg());
    }

    public static ResultDTO okOf() {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        return resultDTO;
    }

    public static <T> ResultDTO okOf(T t) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(200);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }
}

package com.ncu.graduation.advice;


import com.alibaba.fastjson.JSON;
import com.ncu.graduation.dto.ResultDTO;
import com.ncu.graduation.error.CommonException;
import com.ncu.graduation.error.EmBulletinError;
import com.ncu.graduation.error.EmCommonError;
import com.ncu.graduation.error.RedirectException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomizeExceptionHandler {


  @ResponseBody
  @ExceptionHandler(value = {BindException.class, MethodArgumentNotValidException.class})
  public Object validationExceptionHandler(Exception exception) {

    BindingResult bindResult = null;
    if (exception instanceof BindException) {
      bindResult = ((BindException) exception).getBindingResult();
    } else if (exception instanceof MethodArgumentNotValidException) {
      bindResult = ((MethodArgumentNotValidException) exception).getBindingResult();
    }
    String msg;
    if (bindResult != null && bindResult.hasErrors()) {
      msg = bindResult.getAllErrors().get(0).getDefaultMessage();
    } else {

      msg = "不知道啥问题";
    }
    return ResultDTO.errorOf(EmCommonError.PARAMETER_VALIDATION_ERROR.getErrCode(), msg);
  }

  @ResponseBody
  @ExceptionHandler(value = CommonException.class)
  public Object commonExceptionHandler(Exception exception) {
    CommonException commonException = (CommonException) exception;
    return ResultDTO.errorOf(commonException);
  }

  @ResponseBody
  @ExceptionHandler(value = RedirectException.class)
  public Object redirectExceptionHandler(Exception ex, Model model) {

    ex.printStackTrace();
    //跳转错误页面
    if (ex instanceof RedirectException) {
      model.addAttribute("message", ((RedirectException) ex).getErrMsg());
    } else {
      model.addAttribute("message", EmCommonError.UNKNOWN_ERROR.getErrMsg());
    }
    return new ModelAndView("error");

  }

}

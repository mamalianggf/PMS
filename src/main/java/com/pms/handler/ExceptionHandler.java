package com.pms.handler;

import com.pms.constant.HttpConstant;
import com.pms.entity.EiInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

/*
统一处理controller抛出的异常
 */
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler()
    @ResponseBody
    public EiInfo handler(Exception e) {
        EiInfo eiInfo = new EiInfo();
        eiInfo.setStatus(HttpConstant.HTTP_CODE_500);
        eiInfo.setMessage(e.toString());
        return eiInfo;
    }
}
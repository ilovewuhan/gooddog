package com.gooddog.common.exception;

import com.gooddog.base.exception.ServiceException;
import com.gooddog.base.resp.JsonProtocol;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 * @author zlh
 * @date 2021/4/29 14:31
 * @description
 */
@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public JsonProtocol<?> handlerException(Exception e, HttpServletRequest request) {
        log.error("exception => {}", e);
        if(e instanceof HttpRequestMethodNotSupportedException){
            return JsonProtocol.failed("999998","(999998)客户端http请求方式有误，请检查!");
        }
        return JsonProtocol.failed("99997", "服务器繁忙，请稍后再试");
    }

    @ExceptionHandler({ServiceException.class})
    public JsonProtocol<?> handleServiceException(ServiceException e, HttpServletRequest request) {
        log.error("serviceException => {}",e.toString());
        return JsonProtocol.failed(e.getCode(), e.getMsg(), e.getData());
    }
}

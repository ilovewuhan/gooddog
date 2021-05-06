package com.gooddog.base.exception;

import com.gooddog.base.baseCons.DogBaseCons;
import com.gooddog.base.error.IError;

/**
 * 自定义异常类
 *
 * @author zlh
 * @date 2021/4/29
 */
public class ServiceException extends RuntimeException{

    private static final long serialVersionUID = 4870534847958975482L;

    /**
     * 返回状态码
     */
    private String code = DogBaseCons.PUBLIC_ERROR;
    /**
     * 返回异常信息
     */
    private String msg;
    /**
     * 自定义数据
     */
    private Object data;

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(IError error) {
        this.code = error.getCode();
        this.msg = error.getErrorMsg();
    }

    public ServiceException(IError error, Object data) {
        this.code = error.getCode();
        this.msg = error.getErrorMsg();
        this.data = data;
    }

    public ServiceException(String msg) {
        this.msg = msg;
    }

    public ServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ServiceException{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

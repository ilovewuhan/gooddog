package com.gooddog.base.resp;

import java.io.Serializable;

/**
 * @author zlh
 * @date 2021/4/25
 */
public class JsonProtocol<T> implements Serializable {
    private String code;
    private String msg;
    private T data;

    public static <T> JsonProtocol<T> success(T data) {
        return new JsonProtocol<>("00000", "成功", data);
    }

    public static <T> JsonProtocol<T> success() {
        return new JsonProtocol<>("00000", "操作成功");
    }

    public static <T> JsonProtocol<T> failed() {
        return new JsonProtocol<>("99999", "操作失败");
    }

    public static <T> JsonProtocol<T> failed(String code, String msg) {
        return new JsonProtocol<>(code, code);
    }

    public static <T> JsonProtocol<T> failed(String code, String msg, T data) {
        return new JsonProtocol<>(code, code, data);
    }

    public static <T> JsonProtocol<T> failed(String msg) {
        return new JsonProtocol<>("99998", msg);
    }

    public JsonProtocol(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonProtocol(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public JsonProtocol() {
    }

    public JsonProtocol(T data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return "00000".equals(code);
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
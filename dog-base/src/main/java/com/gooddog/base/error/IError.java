package com.gooddog.base.error;

/**
 * @author zlh
 * @date 2021/4/29 18:02
 * @description
 */
public interface IError {

    /**
     * 获取错误码
     * @return
     */
    String getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getErrorMsg();
}

package com.example.onlineteachingplatform.utils;

import lombok.Data;

@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    /**
     * 注册
     * @param code 0表示注册成功 2表示登录成功
     * @param message 操作成功的提示信息
     * @param data 返回给前端的数据
     */
    public static <T> Result<T> success(int code, String message, T data) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * 注册
     * @param code 1表示注册失败 3表示登录失败
     * @param message 操作失败的提示信息
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}

package com.example.onlineteachingplatform.utils;

import lombok.Data;

import java.util.List;

/**
 * @author Samoye
 */
@Data
public class Result<T> {
    private int code;
    private String message;
    private T data;

    /**
     * @param code 0表示注册成功 2表示登录成功 6表示选课成功 8表示学生退课成功 10表示获取课程视频URL成功 12表示获取单个课程信息成功
     *             14表示评论成功 16表示创建课程成功 18表示修改课程成功
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
     * @param code 4表示获取课程列表成功
     * @param message 操作成功的提示信息
     * @param data 返回给前端的数据
     */
    public static <T> Result<List<T>> success(int code, String message, List<T> data) {
        Result<List<T>> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    /**
     * @param code 1表示注册失败 3表示登录失败 5表示获取课程列表失败 7表示选课失败 9表示学生退课失败 11表示获取课程视频URL失败
     *             13表示获取单个课程信息失败 15表示评论失败 17表示创建课程失败 19表示修改课程失败
     * @param message 操作失败的提示信息
     */
    public static <T> Result<T> error(int code, String message) {
        Result<T> result = new Result<>();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}

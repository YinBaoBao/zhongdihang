package com.zhongdihang.bankdispatch.rest;

/**
 * @Author:Arthur Han
 * @Description:
 * @CreateDate:2017/3/2912:31
 * @Modified By:
 */
public class ResultModel {
    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    /**
     * 返回内容
     */
    private Object content;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getContent() {
        return content;
    }

    public ResultModel(int code, String message) {
        this.code = code;
        this.message = message;
        this.content = "";
    }

    public ResultModel(int code, String message, Object content) {
        this.code = code;
        this.message = message;
        this.content = content;
    }

    public ResultModel(ResultStatus status) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = "";
    }

    public ResultModel(ResultStatus status, Object content) {
        this.code = status.getCode();
        this.message = status.getMessage();
        this.content = content;
    }

    public static ResultModel ok(Object content) {
        return new ResultModel(ResultStatus.SUCCESS, content);
    }

    public static ResultModel ok() {
        return new ResultModel(ResultStatus.SUCCESS);
    }

    public static ResultModel error(ResultStatus error) {
        return new ResultModel(error);
    }

    public static ResultModel error(ResultStatus error, Object content) {
        return new ResultModel(error,content);
    }
    public static ResultModel error(ResultStatus error, String message) {
        return new ResultModel(error.getCode(),message);
    }

    public static ResultModel error(int code, String message) {
        return new ResultModel(code,message);
    }
}

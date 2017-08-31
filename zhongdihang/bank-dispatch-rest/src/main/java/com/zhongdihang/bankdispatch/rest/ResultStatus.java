package com.zhongdihang.bankdispatch.rest;

/**
 * @Author:Arthur Han
 * @Description:
 * @CreateDate:2017/3/2912:32
 * @Modified By:
 */
public enum ResultStatus {
    SUCCESS(1000, "成功"),

    FAILURE(4000,"操作失败"),

    INVALID_USERNAME(4001,"用户不存在"),

    INVALID_PASSWORD(4002,"密码错误"),

    UNAUTHORIZED(4003,"无身份信息"),

    INVALID_TOKEN(4004,"无效的TOEKN"),

    UNAUTHORIZED_ACCESS(4005,"无权访问"),

    INVALID_PARAMETER(4006,"无效的参数"),

    SERVER_ERROR(5000,"服务器发生未知错误");

    /**
     * 返回码
     */
    private int code;

    /**
     * 返回结果描述
     */
    private String message;

    ResultStatus(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}


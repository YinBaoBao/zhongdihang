package com.zhongdihang.bankdispatch.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Lu Jun
 * @date 2017/7/6.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class AbstractResult {
    private int code;
    private String message;

    public AbstractResult(int code){this.code = code;}
    public AbstractResult(int code,String message){this.code = code;this.message = message;}

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

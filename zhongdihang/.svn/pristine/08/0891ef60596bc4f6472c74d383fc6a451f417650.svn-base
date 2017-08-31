package com.zhongdihang.bankdispatch.rest;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @author Lu Jun
 * @date 2017/7/6.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class
Result<T>  extends AbstractResult {
    private T data;

    public Result () {
        this(0,null);
    }

    public Result(int code, String message) {
        super(code, message);
    }

    public static <T> Result<T> ok(){
        return new Result<T>(0,null);
    }
    public static <T> Result<T> error(){
        return new Result<T>(1,null);
    }
    public static <T> Result<T> error(Integer code,String message){
        return new Result<T>(code,message);
    }

    public static <T> Result<T> one(T t){
        Result<T> res = new Result<T>(0,null);
        res.data = t;
        return res;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

package com.zhongdihang.bankdispatch.common.exceptions;

import com.zhongdihang.bankdispatch.rest.ResultStatus;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -7672665859278177463L;

    private int code;
    public BusinessException() {
    }

    public BusinessException(ResultStatus status) {
        super(status.getMessage());
        this.code=status.getCode();
    }

    public BusinessException(ResultStatus status,String message) {
        super(message);
        this.code=status.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}

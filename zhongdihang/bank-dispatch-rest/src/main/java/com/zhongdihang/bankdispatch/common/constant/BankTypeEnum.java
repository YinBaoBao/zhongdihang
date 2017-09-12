package com.zhongdihang.bankdispatch.common.constant;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/31 14:58
 * @Modified By：
 * @Version: V1.0.0
 */
public enum  BankTypeEnum {
    分行("1"),
    支行("2");

    private String type;

    BankTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

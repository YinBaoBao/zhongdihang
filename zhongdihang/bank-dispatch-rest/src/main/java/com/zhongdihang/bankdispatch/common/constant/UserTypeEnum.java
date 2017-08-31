package com.zhongdihang.bankdispatch.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/27 14:29
 * @Modified By：
 * @Version: V1.0.0
 */
public enum UserTypeEnum {
    BANK("1","银行"),
    ASSESS("2","评估机构");

    private String code;

    private String name;

    UserTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<String, UserTypeEnum> stringToEnum = new HashMap<>();
    static {
        // Initialize map from constant name to enum constant
        for(UserTypeEnum userTypeEnum : values()) {
            stringToEnum.put(userTypeEnum.getCode(), userTypeEnum);
        }
    }

    // Returns Blah for string, or null if string is invalid
    public static UserTypeEnum fromString(String symbol) {
        return stringToEnum.get(symbol);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

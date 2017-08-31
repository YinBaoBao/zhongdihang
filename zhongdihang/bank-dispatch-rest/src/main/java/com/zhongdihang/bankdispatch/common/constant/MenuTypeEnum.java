package com.zhongdihang.bankdispatch.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/23 10:12
 * @Modified By：
 * @Version: V1.0.0
 */
public enum MenuTypeEnum {
    INTERFACE("1","接口"),
    HTML("2","页面"),
    BUTTON("3","按钮");

    private String code;

    private String name;

    MenuTypeEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<String, MenuTypeEnum> stringToEnum = new HashMap<String, MenuTypeEnum>();
    static {
        // Initialize map from constant name to enum constant
        for(MenuTypeEnum menuTypeEnum : values()) {
            stringToEnum.put(menuTypeEnum.getCode(), menuTypeEnum);
        }
    }

    // Returns Blah for string, or null if string is invalid
    public static MenuTypeEnum fromString(String symbol) {
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

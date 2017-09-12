package com.zhongdihang.bankdispatch.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 17:42
 * @Modified By：
 * @Version: V1.0.0
 */
public enum DictEnum {
    性别("sex"),
    状态("enable"),
    用户类别("userType"),
    菜单类别("menuType"),
    HTTP方法("method"),
    抵押物类别("guarantyType"),
    银行类别("type"),
    区域("area"),
    评估类型("dispatchType"),
    单据状态("dispatchStatus");
    private String dictCode;

    DictEnum(String dictCode) {
        this.dictCode = dictCode;
    }
    private static final Map<String, DictEnum> stringToEnum = new HashMap<String, DictEnum>();
    static {
        // Initialize map from constant name to enum constant
        for(DictEnum dictEnum : values()) {
            stringToEnum.put(dictEnum.getDictCode(), dictEnum);
        }
    }

    // Returns Blah for string, or null if string is invalid
    public static DictEnum fromString(String symbol) {
        return stringToEnum.get(symbol);
    }


    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }
}

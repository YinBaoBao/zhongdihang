package com.zhongdihang.bankdispatch.modular.service.dto;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/19 13:43
 * @Modified By：
 * @Version: V1.0.0
 */
public class SelectItem {
    private String value;

    private String name;

    public SelectItem(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public SelectItem() {
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

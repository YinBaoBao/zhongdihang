package com.zhongdihang.bankdispatch.common.constant;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/25 11:06
 * @Modified By：
 * @Version: V1.0.0
 */
public enum SMSTemplateEnum {
    DISPATCH("408087");
    private String template;

    SMSTemplateEnum(String template) {
        this.template = template;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}

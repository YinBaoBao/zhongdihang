package com.zhongdihang.bankdispatch.common.sms;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/25 10:17
 * @Modified By：
 * @Version: V1.0.0
 */
public class MessageContent {
    private String location;

    private String bankName;

    public MessageContent(String location, String bankName) {
        this.location = location;
        this.bankName = bankName;
    }

    public MessageContent() {
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}

package com.zhongdihang.bankdispatch.common.sms;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/25 10:18
 * @Modified By：
 * @Version: V1.0.0
 */
public class MessageReply {
    private String stat;
    private String message;

    public MessageReply() {

    }

    public MessageReply(String stat, String message) {
        this.stat = stat;
        this.message = message;
    }

    public String getStat() {
        return stat;
    }

    public void setStat(String stat) {
        this.stat = stat;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package com.zhongdihang.bankdispatch.common.sms;


import com.alibaba.fastjson.JSON;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/25 10:01
 * @Modified By：
 * @Version: V1.0.0
 */
public class SendMsgUtil {

    private static String url;

    private static String uid;

    private static String pwd;
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SendMsgUtil.class);

    static {
        Properties prop = new Properties();
        InputStream in = SendMsgUtil.class.getResourceAsStream("/setting.properties");
        try {
            prop.load(in);
            url = prop.getProperty("sms_url");
            uid = prop.getProperty("sms_uid");
            pwd = prop.getProperty("sms_pwd");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static MessageReply sendMsg(String phones, String content, String templeateId) {
        try {
            String sendContent = content;
            MessageContent messageContent = null;

            HttpClient client = new HttpClient();
            PostMethod post = new PostMethod(url);
            // 在头文件中设置转码
            post.addRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            NameValuePair[] data = {new NameValuePair("ac", "send"), new NameValuePair("uid", uid), new NameValuePair("pwd", pwd),
                    new NameValuePair("mobile", phones), new NameValuePair("content", sendContent), new NameValuePair("template", templeateId),
                    new NameValuePair("encode", "utf8")
            };
            post.setRequestBody(data);

            client.executeMethod(post);
            Header[] headers = post.getRequestHeaders();
            int statusCode = post.getStatusCode();
            for (Header h : headers) {
                System.out.println(h.toString());
            }
            String result = new String(post.getResponseBodyAsString().getBytes("ISO-8859-1"), "UTF-8");
            System.out.println(result);
            post.releaseConnection();
            MessageReply messageReply = JSON.parseObject(result, MessageReply.class);
            return messageReply;
        } catch (Exception e) {
            e.printStackTrace();
            return new MessageReply("200", "发送失败");
        }
    }

    /**
     * 将返回状态编码转化为描述结果
     *
     * @param result 状态编码
     * @return 描述结果
     */
    private static String getResponse(String result) {

        if (result.equals("100")) {
            logger.info("发送成功");
            return "发送成功";
        }
        if (result.equals("101")) {
            logger.info("验证失败");
            return "验证失败";
        }
        if (result.equals("102")) {
            logger.info("短信不足");
            return "短信不足";
        }
        if (result.equals("103")) {
            logger.info("操作失败");
            return "操作失败";
        }
        if (result.equals("104")) {
            logger.info("非法字符");
            return "非法字符";
        }
        if (result.equals("105")) {
            logger.info("内容过多");
            return "内容过多";
        }
        if (result.equals("106")) {
            logger.info("号码过多");
            return "号码过多";
        }

        if (result.equals("108")) {
            logger.info("号码内容空");
            return "号码内容空";
        }
        if (result.equals("109")) {
            logger.info("账号冻结");
            return "账号冻结";
        }

        if (result.equals("112")) {
            logger.info("号码不正确");
            return "号码不正确";
        }
        if (result.equals("117")) {
            logger.info("绑定IP不正确");
            return "绑定IP不正确";
        }
        if (result.equals("161")) {
            logger.info("未添加短信模板");
            return "未添加短信模板";
        }
        if (result.equals("162")) {
            logger.info("模板格式不正确");
            return "模板格式不正确";
        }
        if (result.equals("163")) {
            logger.info("模板ID不正确");
            return "模板ID不正确";
        }
        if (result.equals("164")) {
            logger.info("全文模板不匹配");
            return "全文模板不匹配";
        }
        return result;
    }
}


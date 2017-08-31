package com.zhongdihang.bankdispatch.modular.service.form;

import java.io.Serializable;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/14 21:25
 * @Modified By：
 * @Version: V1.0.0
 */
public class AuthenticationForm implements Serializable{

    private static final long serialVersionUID = 4115432734079472306L;
    private String userName;
    private String password;

    public AuthenticationForm() {
        super();
    }

    public AuthenticationForm(String userName, String password) {
        this.setUserName(userName);
        this.setPassword(password);
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

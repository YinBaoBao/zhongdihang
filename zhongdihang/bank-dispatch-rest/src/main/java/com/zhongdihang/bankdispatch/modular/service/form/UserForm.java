package com.zhongdihang.bankdispatch.modular.service.form;

import java.io.Serializable;

/**
 * @author Sun Xingyu
 * @date 2017/8/9.
 */
public class UserForm implements Serializable{

    private static final long serialVersionUID = -6881467025379724139L;

    private String userName;

    private String realName;

    private String password;

    private String sex;

    private String telephone;

    private String eMail;

    private Boolean isEnable;

    private Boolean isBankUser;

    private Long bankId;

    private Long assessId;

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    public Long getAssessId() {
        return assessId;
    }

    public void setAssessId(Long assessId) {
        this.assessId = assessId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public Boolean getBankUser() {
        return isBankUser;
    }

    public void setBankUser(Boolean bankUser) {
        isBankUser = bankUser;
    }
}

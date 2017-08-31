package com.zhongdihang.bankdispatch.modular.service.dto;

import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:用户详情返回实体
 * @CreateTime: 2017/8/9 9:01
 * @Modified By：
 * @Version: V1.0.0
 */
public class UserInfoDto {
    private String userName;

    private String realName;

    private Boolean isBankUser;

    private String orgName;

    private String telephone;

    private String sex;

    private String roles;

    private String Email;

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

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Boolean getBankUser() {
        return isBankUser;
    }

    public void setBankUser(Boolean bankUser) {
        isBankUser = bankUser;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }
}

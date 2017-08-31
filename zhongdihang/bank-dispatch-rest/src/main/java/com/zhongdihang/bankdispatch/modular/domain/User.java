package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User extends EntityLongId {

    private static final long serialVersionUID = -5238031498267295780L;

    private String avatar;

    @Column(unique = true,nullable = false,length = 20)
    private String userName;

    @Column(nullable = false,length =100 )
    private String password;

    @Column(nullable = false,length =20 )
    private String realName;

    @Column(length = 1)
    private String sex;

    @Column(length = 255)
    private String eMail;

    private String telephone;

    @Column(nullable = false)
    private Boolean isBankUser;

    @Column(nullable = false)
    private Boolean isEnable;

    @Column(nullable = false)
    private Boolean isDeleted;

    @ManyToOne
    private User createUser;

    @Column(nullable = false)
    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    @ManyToOne
    private Bank bank;

    @ManyToOne
    private Assess assess;

    @ManyToMany
    private Set<Role> roles = new HashSet<Role>();

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public Assess getAssess() {
        return assess;
    }

    public void setAssess(Assess assess) {
        this.assess = assess;
    }

    public Boolean getBankUser() {
        return isBankUser;
    }

    public void setBankUser(Boolean bankUser) {
        isBankUser = bankUser;
    }
}
package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:评估机构
 * @CreateTime: 2017/8/4 14:11
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class Assess extends EntityLongId {
    private static final long serialVersionUID = 6381406044815132624L;
    @Column(unique = true, nullable = false, length = 50)
    private String name;

    @Column(nullable = false)
    private String guarantyType;

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    private String address;

    @Column(nullable = false)
    private String linkMen;

    @Column(nullable = false)
    private String telephone;

    private Boolean isDeleted;

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @ManyToMany(mappedBy = "assesses")
    private Set<Bank> banks = new HashSet<Bank>();

    @ManyToOne
    private User createUser;

    @Column(nullable = false)
    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkMen() {
        return linkMen;
    }

    public void setLinkMen(String linkMen) {
        this.linkMen = linkMen;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Set<Bank> getBanks() {
        return banks;
    }

    public void setBanks(Set<Bank> banks) {
        this.banks = banks;
    }

}

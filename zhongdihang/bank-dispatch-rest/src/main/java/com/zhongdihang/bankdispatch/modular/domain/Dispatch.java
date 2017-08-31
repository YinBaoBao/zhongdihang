package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:派单表
 * @CreateTime: 2017/8/4 14:15
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class Dispatch extends EntityLongId{

    private static final long serialVersionUID = -4888952362372366044L;
    /*
    派单号
     */
    @Column(nullable = false,unique = true)
    private String dispatchNo;

    /*
    抵押物详情
     */
    @OneToMany(mappedBy = "dispatch")
    private Set<Guaranty> guaranties=new HashSet<>();

    /*
    评估时间限制
     */
    private Integer timeLimit;

    /*
    派单的银行
     */
    @ManyToOne
    private Bank bank;

    /*
    分配的评估机构
     */
    @ManyToOne
    private Assess assess;

    /*
    派单的状态
     */
    @Column(nullable = false)
    private String status;

    /*
    派单的类型 1：预评估 2：正式评估 31：预评估转正式评估
     */
    @Column(nullable = false)
    private String type;

    /*
    派单的客户经理
     */
    @ManyToOne
    private User bankUser;

    /*
    接单的评估机构人员
     */
    @ManyToOne
    private User assessUser;

    /*
    创建人员
     */
    @ManyToOne
    private User createUser;

    /*
    创建时间
     */
    @Column(nullable = false)
    private Date createTime;

    /*
    更新人员
     */
    @ManyToOne
    private User updateUser;

    /*
    更新时间
     */
    private Date updateTime;

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public Set<Guaranty> getGuaranties() {
        return guaranties;
    }

    public void setGuaranties(Set<Guaranty> guaranties) {
        this.guaranties = guaranties;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User getBankUser() {
        return bankUser;
    }

    public void setBankUser(User bankUser) {
        this.bankUser = bankUser;
    }

    public User getAssessUser() {
        return assessUser;
    }

    public void setAssessUser(User assessUser) {
        this.assessUser = assessUser;
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

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}

package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:派单追踪表
 * @CreateTime: 2017/8/4 14:17
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class DispatchTrack extends EntityLongId{
    private static final long serialVersionUID = -6419011671339507113L;

    @Column(nullable = false)
    private String dispatchNo;

    @Column(nullable = false)
    private String contact;

    @Column(nullable = false)
    private String contactTelephone;

    @Column(nullable = false)
    private String Assessname;

    @Column(nullable = false)
    private String AssessTelephone;

    public String getAssessname() {
        return Assessname;
    }

    public void setAssessname(String assessname) {
        Assessname = assessname;
    }

    public String getAssessTelephone() {
        return AssessTelephone;
    }

    public void setAssessTelephone(String assessTelephone) {
        AssessTelephone = assessTelephone;
    }

    @Column(nullable = false)
    private Integer nodeNo;

    @Column(nullable = false,length = 100)
    private String remark;

    @ManyToOne
    private User createUser;

    @Column(nullable = false)
    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContactTelephone() {
        return contactTelephone;
    }

    public void setContactTelephone(String contactTelephone) {
        this.contactTelephone = contactTelephone;
    }

    public Integer getNodeNo() {
        return nodeNo;
    }

    public void setNodeNo(Integer nodeNo) {
        this.nodeNo = nodeNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
}

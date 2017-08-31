package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:抵押物表
 * @CreateTime: 2017/8/4 14:15
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class Guaranty extends EntityLongId{
    private static final long serialVersionUID = 4908728732865238537L;

    @ManyToOne
    private Dispatch dispatch;

    @Column(nullable = false)
    private String guarantyType;

    @Column(nullable = false)
    private String document;

    private String clientName;

    private String clientTelephone;

    private String handlerName;

    private String handlerTelephone;

    @ManyToOne
    private User createUser;

    @ManyToOne
    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    @Column(nullable = false)
    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientTelephone() {
        return clientTelephone;
    }

    public void setClientTelephone(String clientTelephone) {
        this.clientTelephone = clientTelephone;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerTelephone() {
        return handlerTelephone;
    }

    public void setHandlerTelephone(String handlerTelephone) {
        this.handlerTelephone = handlerTelephone;
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

package com.zhongdihang.bankdispatch.modular.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:数据字典表项
 * @CreateTime: 2017/8/4 14:18
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class DictData extends EntityLongId {
    private static final long serialVersionUID = 6832419035173797056L;

    @Column(length = 20,nullable = false,unique = true)
    private String dictDataName;

    @Column(length = 20,nullable = false)
    private String dictDataCode;

    @ManyToOne
    private User createUser;

    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    @ManyToOne
    private Dict dict;

    public String getDictDataName() {
        return dictDataName;
    }

    public void setDictDataName(String dictDataName) {
        this.dictDataName = dictDataName;
    }

    public String getDictDataCode() {
        return dictDataCode;
    }

    public void setDictDataCode(String dictDataCode) {
        this.dictDataCode = dictDataCode;
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

    public Dict getDict() {
        return dict;
    }
    @JsonBackReference
    public void setDict(Dict dict) {
        this.dict = dict;
    }
}

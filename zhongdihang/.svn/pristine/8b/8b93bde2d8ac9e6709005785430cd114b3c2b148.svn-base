package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:数据字典表
 * @CreateTime: 2017/8/4 14:17
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class Dict extends EntityLongId{
    private static final long serialVersionUID = 3479593387121250825L;

    @Column(length = 20,nullable = false,unique = true)
    private String dictName;

    @Column(length = 20,nullable = false)
    private String dictCode;

    @ManyToOne
    private User createUser;

    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    @OneToMany(cascade={CascadeType.ALL},mappedBy = "dict",fetch = FetchType.EAGER)
    @NotFound(action= NotFoundAction.IGNORE)
    private Set<DictData> dictDatas=new HashSet<DictData>();

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
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

    public Set<DictData> getDictDatas() {
        return dictDatas;
    }

    public void setDictDatas(Set<DictData> dictDatas) {
        this.dictDatas = dictDatas;
    }
}

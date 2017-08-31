package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;
import org.aspectj.lang.annotation.control.CodeGenerationHint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:角色表
 * @CreateTime: 2017/8/4 14:13
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class Role extends EntityLongId {

    private static final long serialVersionUID = -1304216553819047935L;

    @Column(unique = true,nullable = false,length = 20)
    private String roleName;

    private String description;

    @Column(nullable = false)
    private Integer sortNum;

    @ManyToOne
    private Role pRole;

    @ManyToOne
    private User createUser;

    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    @ManyToMany(mappedBy = "roles")
    @Cascade(CascadeType.REFRESH)
    private Set<User> users=new HashSet<User>();

    @ManyToMany
    private Set<Menu> menus=new HashSet<Menu>();

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Role getpRole() {
        return pRole;
    }

    public void setpRole(Role pRole) {
        this.pRole = pRole;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Menu> getMenus() {
        return menus;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

}

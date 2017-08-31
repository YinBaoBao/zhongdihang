package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:菜单表
 * @CreateTime: 2017/8/4 14:13
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class Menu extends EntityLongId{

    private static final long serialVersionUID = 4261656768401128754L;

    @ManyToOne
    private Menu pMenu;

    @Column(unique = true,nullable = false,length = 20)
    private String menuName;

    private String description;

    private String url;

    @Column(nullable = false)
    private String method;

    @Column(nullable = false)
    private Integer sortNum;

    @Enumerated(EnumType.STRING)
    private MenuTypeEnum menuType;

    @OneToMany(mappedBy="pMenu")
    private Set<Menu> childMenus=new HashSet<>();

    @ManyToOne
    private User createUser;

    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    private String permission;

    @ManyToMany(mappedBy = "menus")
    private Set<Role> roles=new HashSet<Role>();

    public Menu getpMenu() {
        return pMenu;
    }

    public void setpMenu(Menu pMenu) {
        this.pMenu = pMenu;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCrateTime() {
        return createTime;
    }

    public void setCrateTime(Date crateTime) {
        this.createTime = crateTime;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public MenuTypeEnum getMenuType() {
        return menuType;
    }

    public void setMenuType(MenuTypeEnum menuType) {
        this.menuType = menuType;
    }

    public Set<Menu> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(Set<Menu> childMenus) {
        this.childMenus = childMenus;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

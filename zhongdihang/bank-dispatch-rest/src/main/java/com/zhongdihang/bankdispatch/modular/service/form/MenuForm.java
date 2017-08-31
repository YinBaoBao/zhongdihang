package com.zhongdihang.bankdispatch.modular.service.form;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/15.
 */
public class MenuForm implements Serializable{
    private static final long serialVersionUID = 6187203643798077227L;

    private Long id;

    @NotEmpty(message = "请输入菜单名称")
    private String menuName;

    private String description;

    private String url;

    private Integer sortNum;

    @NotEmpty(message = "请选择菜单类型")
    private String menuType;

    @NotEmpty
    private String method;

    private String permission;

    private Long pId;

    public String getMethod() {
        return method;
    }


    public void setMethod(String method) {
        this.method = method;
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


    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public Long getpId() {
        return pId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}

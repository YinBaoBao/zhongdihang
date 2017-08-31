package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.modular.domain.Menu;

import java.io.Serializable;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/16 14:27
 * @Modified By：
 * @Version: V1.0.0
 */
public class MenuDto implements Serializable {
    private static final long serialVersionUID = 4436376225108367682L;
    private String id;

    private String permission;

    private String pId;

    private String pMenuName;

    private String menuName;

    private String description;

    private String url;

    private String method;

    private Integer sortNum;

    private String menuType;

    public MenuDto(){}

    public MenuDto(Menu menu){
        this.id=menu.getId().toString();
        this.permission=menu.getPermission();
        if (menu.getpMenu()!=null) {
            this.pId = menu.getpMenu().getId().toString();
            this.pMenuName=menu.getpMenu().getMenuName();
        }
        this.menuName=menu.getMenuName();
        this.description=menu.getDescription();
        this.url=menu.getDescription();
        this.method=menu.getMethod();
        this.sortNum=menu.getSortNum();
        this.url=menu.getUrl();
        if (menu.getMenuType()!=null) {
            this.menuType = menu.getMenuType().getName();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
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

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
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

    public String getpMenuName() {
        return pMenuName;
    }

    public void setpMenuName(String pMenuName) {
        this.pMenuName = pMenuName;
    }


}

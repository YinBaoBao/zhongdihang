package com.zhongdihang.bankdispatch.modular.service.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/27 15:09
 * @Modified By：
 * @Version: V1.0.0
 */
public class NavMenuDto implements Serializable{
    private static final long serialVersionUID = -5703857736397932362L;
    private String menuName;

    private String url;

    private Integer sortNum;

    private List<NavMenuDto> childMenu=new ArrayList<>();

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<NavMenuDto> getChildMenu() {
        return childMenu;
    }

    public void setChildMenu(List<NavMenuDto> childMenu) {
        this.childMenu = childMenu;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }
}

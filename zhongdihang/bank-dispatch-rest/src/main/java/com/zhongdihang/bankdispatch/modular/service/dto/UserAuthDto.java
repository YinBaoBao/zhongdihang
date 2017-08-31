package com.zhongdihang.bankdispatch.modular.service.dto;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/25 14:52
 * @Modified By：
 * @Version: V1.0.0
 */
public class UserAuthDto {
    private String menuName;

    private String method;

    private String url;

    private String type;

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

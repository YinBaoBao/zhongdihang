package com.zhongdihang.bankdispatch.modular.service.form;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/18.
 */
public class BankForm implements Serializable{

    private static final long serialVersionUID = 4191329720328414323L;

    private String id;
    private String address;
    private String areaCode;
    private String linkMen;
    private String name;
    private String telephone;
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public String getLinkMen() {
        return linkMen;
    }

    public void setLinkMen(String linkMen) {
        this.linkMen = linkMen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}

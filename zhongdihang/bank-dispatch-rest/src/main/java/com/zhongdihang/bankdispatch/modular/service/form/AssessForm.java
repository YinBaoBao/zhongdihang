package com.zhongdihang.bankdispatch.modular.service.form;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/8/10.
 */
public class AssessForm implements Serializable{

    private static final long serialVersionUID = 7606219866248242167L;

    private String name;

    private String guarantyType;

    private String address;

    private String linkMen;

    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLinkMen() {
        return linkMen;
    }

    public void setLinkMen(String linkMen) {
        this.linkMen = linkMen;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}

package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.util.Set;

/**
 * Created by Administrator on 2017/8/10.
 */
public class AssessDto implements Converter<Assess,AssessDto>{

    private  String id;

    private String name;

    private String address;

    private String linkMen;

    private Date createTime;

    private String guarantyType;

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    private String telephone;

    private String banks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getBanks() {
        return banks;
    }

    public void setBanks(String banks) {
        this.banks = banks;
    }

    @Override
    public AssessDto convert(Assess assess) {
        AssessDto assessDto=new AssessDto();
        assessDto.setCreateTime(assess.getCreateTime());
        StringBuilder bankNames=new StringBuilder();
        if (assess.getBanks()!=null&&assess.getBanks().size()>0){
            Set<Bank> banks=assess.getBanks();
            for (Bank bank : banks) {
                bankNames.append(bank.getName()+",");
            }
        }
        assessDto.setId(assess.getId()+"");
        assessDto.setBanks(bankNames.toString());
        assessDto.setGuarantyType(assess.getGuarantyType());
        assessDto.setName(assess.getName());
        assessDto.setAddress(assess.getAddress());
        assessDto.setLinkMen(assess.getLinkMen());
        assessDto.setTelephone(assess.getTelephone());
        return assessDto;
    }


}

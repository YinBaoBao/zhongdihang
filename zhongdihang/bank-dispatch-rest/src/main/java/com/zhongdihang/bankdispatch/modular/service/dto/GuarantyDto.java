package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/11.
 */
public class GuarantyDto implements Converter<Guaranty,GuarantyDto> {

    private String id;

    private String dispatchNo;

    private String guarantyType;

    private String document;

    private String clientName;

    private String clientTelephone;

    private String handlerName;

    private String handlerTelephone;

    private Date createTime;

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

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientTelephone() {
        return clientTelephone;
    }

    public void setClientTelephone(String clientTelephone) {
        this.clientTelephone = clientTelephone;
    }

    public String getHandlerName() {
        return handlerName;
    }

    public void setHandlerName(String handlerName) {
        this.handlerName = handlerName;
    }

    public String getHandlerTelephone() {
        return handlerTelephone;
    }

    public void setHandlerTelephone(String handlerTelephone) {
        this.handlerTelephone = handlerTelephone;
    }

    @Override
    public GuarantyDto convert(Guaranty guaranty) {
        GuarantyDto guarantyDto=new GuarantyDto();
        guarantyDto.setId(guaranty.getId().toString());
        guarantyDto.setClientName(guaranty.getClientName());
        guarantyDto.setClientTelephone(guaranty.getClientTelephone());
//        guarantyDto.setDispatchNo(guaranty.getDispatchNo());
        guarantyDto.setDocument(guaranty.getDocument());
        guarantyDto.setGuarantyType(guaranty.getGuarantyType());
        guarantyDto.setHandlerName(guaranty.getHandlerName());
        guarantyDto.setHandlerTelephone(guaranty.getHandlerTelephone());
        return guarantyDto;
    }

}

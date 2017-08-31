package com.zhongdihang.bankdispatch.modular.service.dto;


import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.core.controoller.BaseController;
import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import com.zhongdihang.bankdispatch.modular.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by win10 on 2017/08/28.
 */

public class DispatchDto  implements Converter<Dispatch, DispatchDto> {

    private Long Id;
    private String type;
    private String status;
    private String assessName;
    private String bankName;
    private String dispatchNo;
    private List<GuarantyDto> guarantyList;

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public List<GuarantyDto> getGuarantyList() {
        return guarantyList;
    }

    public void setGuarantyList(List<GuarantyDto> guarantyList) {
        this.guarantyList = guarantyList;
    }

    @Override
    public DispatchDto convert(Dispatch dispatch) {
        DispatchDto dispatchDto =  new DispatchDto();
        dispatchDto.setId(dispatch.getId());
        dispatchDto.setAssessName(dispatch.getAssess().getName());
        dispatchDto.setBankName(dispatch.getBank().getName());
        dispatchDto.setStatus(dispatch.getStatus());
        dispatchDto.setType(dispatch.getType());
        dispatchDto.setType(dispatch.getType());
        dispatchDto.setDispatchNo(dispatch.getDispatchNo());
        List<GuarantyDto> guarantyDtoList = new ArrayList<>();
        for (Guaranty guaranty:dispatch.getGuaranties())
        {
            GuarantyDto guarantyDto= new GuarantyDto();
            guarantyDto.setId(guaranty.getId().toString());
            guarantyDto.setDocument(guaranty.getDocument());
            guarantyDto.setCreateTime(guaranty.getCreateTime());
            guarantyDto.setCreateTime(guaranty.getCreateTime());
            guarantyDto.setHandlerName(guaranty.getHandlerName());
            guarantyDto.setHandlerTelephone(guaranty.getHandlerTelephone());
            guarantyDto.setClientName(guaranty.getClientName());
            guarantyDto.setClientTelephone(guaranty.getClientName());
            guarantyDto.setGuarantyType(guaranty.getGuarantyType());
            guarantyDtoList.add(guarantyDto);
        }
        dispatchDto.setGuarantyList(guarantyDtoList);

        return dispatchDto;
    }
}

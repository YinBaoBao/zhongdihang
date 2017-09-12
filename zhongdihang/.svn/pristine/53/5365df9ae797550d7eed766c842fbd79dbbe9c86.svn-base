package com.zhongdihang.bankdispatch.modular.service.dto;


import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import org.springframework.core.convert.converter.Converter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by win10 on 2017/08/28.
 */

public class DispatchDto  implements Converter<Dispatch, DispatchDto> {

    private String Id;
    private String dispatchType;
    private String status;
    private String assessName;
    private String bankName;
    private Date createDate;
    private String termDate;
    private String dispatchNo;
    private String assessUserName;
    private String assessUserPhone;
    private String bankUserName;
    private String guarantyType;
    private String bankUserPhone;
    private String dispatchCompleteFile;

    public String getDispatchCompleteFile() {
        return dispatchCompleteFile;
    }

    public void setDispatchCompleteFile(String dispatchCompleteFile) {
        this.dispatchCompleteFile = dispatchCompleteFile;
    }

    private List<DispatchTrackDto> dispatchTrackDtoList;
    private List<GuarantyDto> guarantyDtoList;

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    public String getAssessUserName() {
        return assessUserName;
    }

    public void setAssessUserName(String assessUserName) {
        this.assessUserName = assessUserName;
    }

    public String getAssessUserPhone() {
        return assessUserPhone;
    }

    public void setAssessUserPhone(String assessUserPhone) {
        this.assessUserPhone = assessUserPhone;
    }

    public String getBankUserName() {
        return bankUserName;
    }

    public void setBankUserName(String bankUserName) {
        this.bankUserName = bankUserName;
    }

    public String getBankUserPhone() {
        return bankUserPhone;
    }

    public void setBankUserPhone(String bankUserPhone) {
        this.bankUserPhone = bankUserPhone;
    }

    public List<DispatchTrackDto> getDispatchTrackDtoList() {
        return dispatchTrackDtoList;
    }

    public void setDispatchTrackDtoList(List<DispatchTrackDto> dispatchTrackDtoList) {
        this.dispatchTrackDtoList = dispatchTrackDtoList;
    }

    public List<GuarantyDto> getGuarantyDtoList() {
        return guarantyDtoList;
    }

    public void setGuarantyDtoList(List<GuarantyDto> guarantyDtoList) {
        this.guarantyDtoList = guarantyDtoList;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getTermDate() {
        return termDate;
    }

    public void setTermDate(String termDate) {
        this.termDate = termDate;
    }

    private List<GuarantyDto> guarantyList;

    public String getDispatchNo() {
        return dispatchNo;
    }

    public void setDispatchNo(String dispatchNo) {
        this.dispatchNo = dispatchNo;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
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
        dispatchDto.setId(dispatch.getId().toString());
        dispatchDto.setAssessName(dispatch.getAssess().getName());
        dispatchDto.setBankName(dispatch.getBank().getName());
        dispatchDto.setStatus(dispatch.getStatus());
        dispatchDto.setDispatchType(dispatch.getDispatchType());
        dispatchDto.setCreateDate(dispatch.getCreateTime());
        dispatchDto.setTermDate(dispatch.getTimeLimit()+"");
        dispatchDto.setDispatchNo(dispatch.getDispatchNo());
        List<GuarantyDto> guarantyDtoList = new ArrayList<>();
        for (Guaranty guaranty:dispatch.getGuaranties())
        {
            GuarantyDto guarantyDto= new GuarantyDto();
            guarantyDto.setContactName(guaranty.getContactName());
            guarantyDto.setContactPhone(guaranty.getContactPhone());
            guarantyDto.setFilePath(guaranty.getFile().getAbsolutePath());
            guarantyDtoList.add(guarantyDto);
        }
        dispatchDto.setGuarantyList(guarantyDtoList);

        return dispatchDto;
    }
}

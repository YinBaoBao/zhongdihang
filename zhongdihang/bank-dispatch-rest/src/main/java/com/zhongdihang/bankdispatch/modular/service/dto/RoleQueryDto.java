package com.zhongdihang.bankdispatch.modular.service.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/17.
 */
public class RoleQueryDto implements Serializable{

    private static final long serialVersionUID = -6117824477664120958L;

    private String condition;

    private Date startDate;

    private Date endDate;

    public RoleQueryDto(String condition, Date startDate, Date endDate) {
        this.condition = condition;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public RoleQueryDto() {
    }

    public Date getStartDate() {
        return startDate;
    }

    public RoleQueryDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public RoleQueryDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }


    public String getCondition() {
        return condition;
    }

    public RoleQueryDto setCondition(String condition) {
        this.condition = condition;
        return this;
    }
}

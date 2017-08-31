package com.zhongdihang.bankdispatch.modular.service.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2017/8/10.
 */
public class AssessQueryDto implements Serializable{
    private static final long serialVersionUID = -2100357068992501923L;

    private String condition;

    private Date startDate;

    private Date endDate;

    public AssessQueryDto(String condition, Date startDate, Date endDate) {
        this.condition = condition;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public AssessQueryDto(){}

    public Date getStartDate() {
        return startDate;
    }

    public AssessQueryDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public AssessQueryDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }


    public String getCondition() {
        return condition;
    }

    public AssessQueryDto setCondition(String condition) {
        this.condition = condition;
        return this;
    }
}

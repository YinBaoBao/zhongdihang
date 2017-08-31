package com.zhongdihang.bankdispatch.modular.service.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:用户列表查询条件
 * @CreateTime: 2017/8/8 18:40
 * @Modified By：
 * @Version: V1.0.0
 */
public class UserQueryDto implements Serializable{
    private static final long serialVersionUID = -5161069070749656608L;

    public UserQueryDto() {
    }

    public UserQueryDto(String condition, Date startDate, Date endDate) {
        this.condition = condition;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private String condition;

    private Date startDate;

    private Date endDate;


    public Date getStartDate() {
        return startDate;
    }

    public UserQueryDto setStartDate(Date startDate) {
        this.startDate = startDate;
        return this;
    }

    public Date getEndDate() {
        return endDate;
    }

    public UserQueryDto setEndDate(Date endDate) {
        this.endDate = endDate;
        return this;
    }


    public String getCondition() {
        return condition;
    }

    public UserQueryDto setCondition(String condition) {
        this.condition = condition;
        return this;
    }
}

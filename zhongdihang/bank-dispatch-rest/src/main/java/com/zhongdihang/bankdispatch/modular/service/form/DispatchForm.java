package com.zhongdihang.bankdispatch.modular.service.form;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/31 14:01
 * @Modified By：
 * @Version: V1.0.0
 */
public class DispatchForm implements Serializable{
    private static final long serialVersionUID = 6773335779969083389L;
    /*
    主键
     */
    private Long id;

    /*
    借款人
     */
    @NotEmpty
    private String borrowerName;

    /*
    借款人联系电话
     */
    @NotEmpty
    private String borrowerPhone;

    /*
    派单类型（1：预评估，2：正式评估 3：预评估转正式评估）
     */
    @NotEmpty
    private String dispatchType;

    /*
   抵押物区域
    */
    @NotEmpty
    private String area;

    /*
    抵押物类型
     */
    @NotEmpty
    private String guarantyType;

    /*
    时间限制
     */
    @Min(value = 1,message = "评估时限不能小于一天")
    private Integer timeLimit;

    @NotEmpty
    private List<GuarantyItemForm> guarantyFormList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getBorrowerPhone() {
        return borrowerPhone;
    }

    public void setBorrowerPhone(String borrowerPhone) {
        this.borrowerPhone = borrowerPhone;
    }

    public String getDispatchType() {
        return dispatchType;
    }

    public void setDispatchType(String dispatchType) {
        this.dispatchType = dispatchType;
    }

    public String getGuarantyType() {
        return guarantyType;
    }

    public void setGuarantyType(String guarantyType) {
        this.guarantyType = guarantyType;
    }

    public List<GuarantyItemForm> getGuarantyFormList() {
        return guarantyFormList;
    }

    public void setGuarantyFormList(List<GuarantyItemForm> guarantyFormList) {
        this.guarantyFormList = guarantyFormList;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}

package com.zhongdihang.bankdispatch.modular.service.dto;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/9/7 14:03
 * @Modified By：
 * @Version: V1.0.0
 */
public class BankDispatchCountDto implements Serializable {

    private String bankName;

    private String assessName;

    private BigInteger count;

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAssessName() {
        return assessName;
    }

    public void setAssessName(String assessName) {
        this.assessName = assessName;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }

//    public BankDispatchCountDto(String bankName, String assessName, Long count) {
//        this.bankName = bankName;
//        this.assessName = assessName;
//        this.count = count;
//    }


}

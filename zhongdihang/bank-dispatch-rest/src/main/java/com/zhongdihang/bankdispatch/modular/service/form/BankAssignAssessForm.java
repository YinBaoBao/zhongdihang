package com.zhongdihang.bankdispatch.modular.service.form;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/9/1 20:51
 * @Modified By：
 * @Version: V1.0.0
 */
public class BankAssignAssessForm {
    private Long bankId;

    private Long[] assessIds;

    public Long[] getAssessIds() {
        return assessIds;
    }

    public void setAssessIds(Long[] assessIds) {
        this.assessIds = assessIds;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }
}

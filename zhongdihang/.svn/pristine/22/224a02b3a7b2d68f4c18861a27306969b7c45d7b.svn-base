package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.modular.domain.Bank;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/9 15:28
 * @Modified By：
 * @Version: V1.0.0
 */
public interface BankService {
    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:49
     * @MenthodName: saveBank
     * @Description: 保存银行信息
     * @param bank 银行信息
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Bank
     */
    Bank saveBank(Bank bank);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:50
     * @MenthodName: getBank
     * @Description: 获取银行信息
     * @param id 银行ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Bank
     */
    Bank getBank(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:50
     * @MenthodName: deleteBank
     * @Description: 删除银行信息/软删除,先决出和评估机构的入围关系
     * @param id 银行ID
     * @Return: void
     */
    void deleteBank(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:52
     * @MenthodName: bankAssess
     * @Description: 银行和评估机构入围关系
     * @param id 银行ID
     * @param assessIds 评估机构ID数组
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Bank
     */
    Bank bankAssess(Long id, Long[] assessIds);

    List<Bank> findBankList();

    List<Bank> findBankByName(String Name);

    Bank getTopBank();
}

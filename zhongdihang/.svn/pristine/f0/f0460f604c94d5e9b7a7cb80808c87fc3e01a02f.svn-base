package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.common.constant.BankTypeEnum;
import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.modular.dao.AssessDao;
import com.zhongdihang.bankdispatch.modular.dao.BankDao;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.bankdispatch.modular.service.BankService;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/9 15:28
 * @Modified By：
 * @Version: V1.0.0
 */
@Service
public class BankServiceImpl extends ServiceBean<Long, Bank, BankDao> implements BankService {



    @Autowired
    private AssessDao assessDao;

    @Autowired
    private BankDao bankDao;
    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:49
     * @MenthodName: saveBank
     * @Description: 保存银行信息
     * @param bank 银行信息
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Bank
     */
    @Override
    public Bank saveBank(Bank bank){
        if (bank.getType().equals(BankTypeEnum.分行.getType())){
            Bank topBank=getTopBank();
            if (topBank!=null&&topBank.getId()!=bank.getId()){
                throw new BusinessException(ResultStatus.FAILURE,"当前已存在分行，请勿重复添加！");
            }
        }
        return getDao().save(bank);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:50
     * @MenthodName: getBank
     * @Description: 获取银行信息
     * @param id 银行ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Bank
     */
    @Override
    public Bank getBank(Long id){
        Bank bank=findOne(id);
        if (bank==null||bank.getDeleted())return null;
        return bank;
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:50
     * @MenthodName: deleteBank
     * @Description: 删除银行信息/软删除,先决出和评估机构的入围关系
     * @param id 银行ID
     * @Return: void
     */
    @Override
    public void deleteBank(Long id){
        Bank bank=getBank(id);
        if (bank!=null){
            bank.getAssesses().removeAll(bank.getAssesses());
            bank.setDeleted(true);
            saveBank(bank);
        }
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:52
     * @MenthodName: bankAssess
     * @Description: 银行和评估机构入围关系
     * @param id 银行ID
     * @param assessIds 评估机构ID数组
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Bank
     */
    @Override
    public Bank bankAssess(Long id,Long[] assessIds){
        Bank bank=getDao().getOne(id);
        if (bank==null){
            throw new BusinessException(ResultStatus.FAILURE,"银行不存在");
        }else{
            bank.getAssesses().removeAll(bank.getAssesses());
            for (Long assessId : assessIds) {
                Assess assess=assessDao.getOne(assessId);
                if (assess==null||assess.getDeleted()){
                    throw new BusinessException(ResultStatus.FAILURE,"评估机构不存在");
                }
                bank.getAssesses().add(assess);
            }
            return getDao().save(bank);
        }
    }

    @Override
    public List<Bank> findBankList(){
        return getDao().findBankByIsDeleted(false);
    }

    @Override
    public List<Bank> findBankByName(String Name) {
        return bankDao.findBankByName(Name);
    }

    @Override
    public Bank getTopBank() {
        List<Bank> banks= bankDao.findBankByType(BankTypeEnum.分行.getType());
        if(banks==null||banks.size()<=0){
            return null;
        }
        if(banks.size()>1){
            throw new BusinessException(ResultStatus.FAILURE,"银行组织机构存在多个分行，请确认");
        }
        return banks.get(0);
    }

}

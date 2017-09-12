package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/9 15:29
 * @Modified By：
 * @Version: V1.0.0
 */
public interface BankDao extends Dao<Long, Bank>, JpaSpecificationExecutor<Bank> {

    List<Bank> findBankByIsDeleted(Boolean isDeleted);

    List<Bank> findBankByName(String Name);

    List<Bank> findBankByType(String type);
}

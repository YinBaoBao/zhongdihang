package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/10 9:47
 * @Modified By：
 * @Version: V1.0.0
 */
public class BankServiceTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(BankServiceTest.class);
    @Autowired
    private DispatchService dispatchService;
    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    @Test
    @Rollback(false)
    public void testSaveBank() throws Exception {
        Bank bank=new Bank();
        bank.setCreateTime(new Date());
        bank.setDeleted(false);
        bank.setCreateUser(userService.getUser(344925359327674368L));
        bank.setTelephone("15962664438");
        bank.setArea("320508");
        bank.setAddress("干将西路1008号");
        bank.setLinkMen("韩正天");
        bank.setName("中国银行");
        bank.setType("1");
        bankService.saveBank(bank);
    }

    @Test
    public void testGetBank() throws Exception {
        Bank bank = bankService.getBank(347013190724354048L);
        logger.info(JSON.toJSONString(bank));
    }

    @Test
    public void testDeleteBank() throws Exception {

    }

    @Test
    public void testBankAssess() throws Exception {

    }

    /*@Test
    @Rollback(false)
    public void testDispatch(){

       logger.info( "===================>"+dispatch.randomDispatch(1,1).getName());
    }*/
}
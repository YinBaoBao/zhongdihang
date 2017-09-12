package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.dao.AssessDao;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Administrator on 2017/8/10.
 */
public class AssessServiceTest extends ServiceTest {
    private static Logger logger = Logger.getLogger(AssessServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AssessService assessService;

    @Autowired
    private BankService bankService;

    @Autowired
    private AssessDao assessDao;

    @Test
    @Rollback(false)
    public void testSaveAssess() throws Exception {
        Assess assess1 = new Assess();
        assess1.setAddress("干将西路A号");
        assess1.setCreateTime(new Date());
        assess1.setDeleted(false);
        assess1.setCreateUser(userService.getUser(344925361366106112L));
        assess1.setLinkMen("C");
        assess1.setName("评估机构C");
        assess1.setGuarantyType("1");
        assess1.setTelephone("12345678911");

//        Assess assess2 = new Assess();
//        assess2.setAddress("干将西路B号");
//        assess2.setCreateTime(new Date());
//        assess2.setDeleted(false);
//        assess2.setCreateUser(userService.getUser(344925359327674368L));
//        assess2.setLinkMen("A");
//        assess2.setName("评估机构B");
//        assess2.setType("1");
//        assess2.setTelephone("12345678911");

        Bank bank=new Bank();
        bank.setCreateTime(new Date());
        bank.setDeleted(false);
        bank.setCreateUser(userService.getUser(344925361252859904L));
        bank.setTelephone("11111111111");
        bank.setArea("320308");
        bank.setAddress("干将西路A号");
        bank.setLinkMen("AAA");
        bank.setName("中国银行A");
        bank.setType("1");

        Bank bank2=new Bank();
        bank2.setCreateTime(new Date());
        bank2.setDeleted(false);
        bank2.setCreateUser(userService.getUser(344925361252859904L));
        bank2.setTelephone("11111111111");
        bank2.setArea("320308");
        bank2.setAddress("干将西路B号");
        bank2.setLinkMen("BBB");
        bank2.setName("中国银行B");
        bank2.setType("1");

        Set<Assess> assesses =new HashSet<Assess>();
        assesses.add(assess1);
       // assesses.add(assess2);
        assessService.saveAssess(assess1);
       // assessService.saveAssess(assess2);
       // bank.setAssesses(assesses);
       // bank2.setAssesses(assesses);
       // bankService.saveBank(bank);
      //  bankService.saveBank(bank2);

    }

    @Test
    public void testFindAll(){
        List<Assess> assesses= assessDao.findAll();
        logger.info(JSON.toJSONString(assesses));
    }
}

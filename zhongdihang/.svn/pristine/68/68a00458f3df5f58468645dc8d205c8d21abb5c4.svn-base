package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * Created by Administrator on 2017/8/11.
 */
public class GuarantyServiceTest extends ServiceTest {

    private static Logger logger = Logger.getLogger(GuarantyServiceTest.class);

    @Autowired
    private GuarantyService guarantyService;

    @Test
    @Rollback(false)
    public void testAddGuaranty(){
        for (int i = 0; i <10 ; i++) {
            Guaranty guaranty = new Guaranty();
            guaranty.setCreateTime(new Date());
//            guaranty.setDispatchNo("0000000"+i);
            guaranty=guarantyService.saveGuaranty(guaranty);
            logger.info(JSON.toJSONString(guaranty));
        }

    }
}

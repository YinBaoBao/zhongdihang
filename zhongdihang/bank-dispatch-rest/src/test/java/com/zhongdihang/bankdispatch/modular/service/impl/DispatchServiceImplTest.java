package com.zhongdihang.bankdispatch.modular.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.service.DispatchService;
import com.zhongdihang.bankdispatch.modular.service.dto.BankDispatchCountDto;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/9/8 18:02
 * @Modified By：
 * @Version: V1.0.0
 */
public class DispatchServiceImplTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(DispatchServiceImplTest.class);
    @Autowired
    private DispatchService dispatchService;
    @Test
    public void testAssessCountByBank() throws Exception {
        List<BankDispatchCountDto> bankDispatchCountDtoList=dispatchService.assessCountByBank();
        logger.info(JSON.toJSONString(bankDispatchCountDtoList));
    }
}
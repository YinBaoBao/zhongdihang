package com.zhongdihang.bankdispatch.modular.dao;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.framework.test.DaoTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/28 11:45
 * @Modified By：
 * @Version: V1.0.0
 */
public class DispatchDaoTest extends DaoTest{
    private static Logger logger = Logger.getLogger(DispatchDaoTest.class);
    @Autowired
    private DispatchDao dispatchDao;

    @Test
    public void testFindAll(){
        List<Dispatch> dispatchList= dispatchDao.findAll();
        logger.info(JSON.toJSONString(dispatchList));
    }
}
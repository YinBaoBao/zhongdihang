package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.modular.domain.Dict;
import com.zhongdihang.bankdispatch.modular.domain.DictData;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 17:11
 * @Modified By：
 * @Version: V1.0.0
 */
public class DictServiceTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(DictServiceTest.class);

    @Autowired
    private DictService dictService;
    @Test
    @Rollback(false)
    public void testSaveDict() throws Exception {
        Dict dict=new Dict();
        dict.setCreateTime(new Date());
        dict.setDictCode("sex");
        dict.setDictName("性别");
        DictData dictData=new DictData();
        dictData.setCreateTime(new Date());
        dictData.setDict(dict);
        dictData.setDictDataCode("1");
        dictData.setDictDataName("女");
        Set<DictData> dictDatas=new HashSet<>();
        dictDatas.add(dictData);
        dict.setDictDatas(dictDatas);
        dictService.saveDict(dict);
    }

    @Test
    @Rollback(false)
    public void testDeleteDict() throws Exception {
        dictService.deleteDict(dictService.getByDictCode("sex").getId());
    }

    @Test
    public void testGetDict() throws Exception {
        DictEnum dictEnum= DictEnum.fromString("sex");
        logger.info(JSON.toJSONString(dictEnum));
    }
}
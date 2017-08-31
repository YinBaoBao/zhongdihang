package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.core.redis.RedisClient;
import com.zhongdihang.bankdispatch.modular.dao.DictDao;
import com.zhongdihang.bankdispatch.modular.domain.Dict;
import com.zhongdihang.bankdispatch.modular.domain.DictData;
import com.zhongdihang.bankdispatch.modular.service.DictService;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 17:06
 * @Modified By：
 * @Version: V1.0.0
 */
@Service
public class DictServiceImpl extends ServiceBean<Long,Dict,DictDao> implements DictService {
    @Autowired
    private RedisClient redisClient;

    @Value("${spring.redis.dict-prefix}")
    private String dictPrefix;

    @Override
    public Dict saveDict(Dict dict) {
        if (dict==null){
            throw  new BusinessException(ResultStatus.FAILURE,"无效的字典！");
        }
        for (DictData dictData : dict.getDictDatas()) {
            redisClient.hset(dictPrefix+dict.getDictCode(),dictData.getDictDataCode(),dictData.getDictDataName());
        }
        return getDao().save(dict);
    }

    @Override
    public void deleteDict(Long id) {
        Dict dict=getDict(id);
        if (dict==null){
            throw  new BusinessException(ResultStatus.FAILURE,"无效的字典！");
        }
        for (DictData dictData : dict.getDictDatas()) {
            redisClient.hdel(dictPrefix+dict.getDictCode(),dictData.getDictDataCode());
        }
        getDao().delete(id);
    }

    @Override
    public Dict getDict(Long id) {
        Dict dict=findOne(id);
        if (dict==null)return null;
        return dict;
    }

    @Override
    public Dict getByDictName(String dictName) {
        return getDao().getByDictName(dictName);
    }

    @Override
    public Dict getByDictCode(String dictCode) {
        return getDao().getByDictCode(dictCode);
    }

    @Override
    @PostConstruct
    public void initDictData(){
        List<Dict> dicts=getDao().findAll();
        for (Dict dict : dicts) {
            Set<DictData> dictDataSet= dict.getDictDatas();
            for (DictData dictData : dictDataSet) {
                redisClient.hset(dictPrefix+dict.getDictCode(),dictData.getDictDataCode(),dictData.getDictDataName());
            }
        }
    }

    @Override
    public String getDictDataNameByDictCode(String dictCode, String dictDataCode){
        String dictDataName=redisClient.hget(dictPrefix+dictCode,dictDataCode);
        if (StringUtils.isEmpty(dictDataName)){
            Dict dict=getByDictName(dictCode);
            if(dict==null)return null;
            Set<DictData> dictDataSet=dict.getDictDatas();
            if (dictDataSet==null||dictDataSet.size()<=0)return null;
            for (DictData dictData : dictDataSet) {
                if (dictData.getDictDataCode().equals(dictDataCode)){
                    return dictData.getDictDataName();
                }
            }
        }
        return dictDataName;
    }

    @Override
    public Map<String, String> getDictDataByDictCode(String dictCode){
        Map<String, String> dictData=redisClient.hgetAll(dictPrefix+dictCode);
        return dictData;
    }

    @Override
    public List<Dict> findDictByCondition(String condition){
        return getDao().findDictByCondition(condition);
    }
}

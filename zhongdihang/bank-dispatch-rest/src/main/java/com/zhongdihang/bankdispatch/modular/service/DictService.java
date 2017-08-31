package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.modular.domain.Dict;

import java.util.List;
import java.util.Map;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 17:06
 * @Modified By：
 * @Version: V1.0.0
 */
public interface DictService {

    Dict saveDict(Dict dict);

    void deleteDict(Long id);

    Dict getDict(Long id);

    Dict getByDictName(String dictName);

    Dict getByDictCode(String dictCode);

    void initDictData();

    String getDictDataNameByDictCode(String dictName, String dictDataCode);

    List<Dict> findAll();

    Map<String, String> getDictDataByDictCode(String dictCode);

    List<Dict> findDictByCondition(String condition);
}

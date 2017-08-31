package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Dict;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 13:05
 * @Modified By：
 * @Version: V1.0.0
 */
//@Repository
public interface DictDao extends Dao<Long, Dict> {

    Dict getByDictName(String dictName);

    Dict getByDictCode(String dictCode);

    @Query(value = "select distinct d.* from dict d left join dict_data dd on d.id=dd.dict_id where d.dict_code=?1 or d.dict_name=?1 " +
            "or dd.dict_data_code=?1 or dd.dict_data_name=?1", nativeQuery = true)
    List<Dict> findDictByCondition(String condition);
}

package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface GuarantyDao extends Dao<Long,Guaranty>,JpaSpecificationExecutor<Guaranty> {

    List<Guaranty> findByDispatchId(Long dispatchId);

}

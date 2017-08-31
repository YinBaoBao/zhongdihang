package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by win10 on 2017/08/17.
 */
public interface DispatchDao extends Dao<Long,Dispatch>,JpaSpecificationExecutor<Dispatch> {

    List<Dispatch> findDispatchByAssessId(Long assessId);
    List<Dispatch> findDispatchByBankId(Long bankId);

    Page<Dispatch> findAll(Specification<Dispatch> spec,Pageable pageable);

}

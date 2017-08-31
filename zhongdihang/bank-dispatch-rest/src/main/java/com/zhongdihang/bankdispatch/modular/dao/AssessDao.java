package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/9 15:31
 * @Modified By：
 * @Version: V1.0.0
 */
public interface AssessDao extends Dao<Long, Assess>, JpaSpecificationExecutor<Assess> {

    Assess getByName(String name);

    Boolean existsByName(String name);

    @Query(value ="SELECT c.address, c.create_time, c.is_deleted, c.link_men, c.id,c.`name`,c.telephone,c.weight,c.guaranty_type,c.create_user_id,c.update_user_id "+
            "FROM bank as a LEFT JOIN bank_assesses as b ON a.id = b.banks_id LEFT JOIN assess as c on b.assesses_id = c.id "+
            "WHERE a.id = ?1 and c.guaranty_type = ?2",
            nativeQuery = true)
    List<Assess> findAssessByBankId(Long bankId,int guarantyType);

    List<Assess> findAssessByIsDeleted(Boolean isDeleted);
}

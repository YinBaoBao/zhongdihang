package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 13:01
 * @Modified By：
 * @Version: V1.0.0
 */
public interface UserDao extends Dao<Long,User>,JpaSpecificationExecutor<User> {
    User getByUserName(String userName);

    Boolean existsByUserName(String userName);

    List<User> findUserByBankIdAndIsDeletedAndIsBankUser(Long bankId,Boolean isDeleted,Boolean isBankUser);

    List<User> findUserByAssessIdAndIsDeletedAndIsBankUser(Long AssessId,Boolean isDeleted,Boolean isBankUser);
}

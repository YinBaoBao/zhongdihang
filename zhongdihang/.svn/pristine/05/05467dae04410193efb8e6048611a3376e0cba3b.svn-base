package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 13:02
 * @Modified By：
 * @Version: V1.0.0
 */
public interface RoleDao extends Dao<Long,Role> , JpaSpecificationExecutor<Role> {
    boolean existsByRoleName(String roleName);

    Role getByRoleName(String roleName);

    @Query("select r from Role r where r.roleName like %?1%")
    List<Role> findByRoleNameLike(String roleName);


}

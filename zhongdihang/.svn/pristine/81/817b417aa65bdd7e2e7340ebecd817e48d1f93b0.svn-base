package com.zhongdihang.bankdispatch.modular.dao;

import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.service.dto.AuthDto;
import com.zhongdihang.framework.biz.jpa.dal.Dao;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 13:05
 * @Modified By：
 * @Version: V1.0.0
 */
public interface MenuDao extends Dao<Long,Menu> {
    boolean existsByMenuName(String menuName);

    List<Menu> getByMenuType(MenuTypeEnum menuType);

    @Query(value = "select m.* from user u inner join user_roles ur on u.id=ur.users_id " +
            "inner join role r on ur.roles_id=r.id " +
            "inner join role_menus rm on r.id=rm.roles_id " +
            "inner join menu m on rm.menus_id=m.id where u.id=?1 and m.menu_type=?2", nativeQuery = true)
    List<Menu> findByUserIdAndMenuType(Long userId,String menuType);

}

package com.zhongdihang.bankdispatch.modular.dao;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.framework.test.DaoTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/9/1 15:14
 * @Modified By：
 * @Version: V1.0.0
 */
public class MenuDaoTest extends DaoTest{

    private static Logger logger = Logger.getLogger(MenuDaoTest.class);

    @Autowired
    private MenuDao menuDao;

    @Test
    public void testFindAll(){
        final Collection<ConfigAttribute> array;
        HashMap<String, Collection<ConfigAttribute>> map = new HashMap<>();
        List<Menu> menus = menuDao.findAll();
        menus.stream()
                .forEach(t->map.put(t.getUrl(),t.getRoles()
                        .stream()
                        .map( r->new SecurityConfig(r.getRoleName()))
                        .collect(Collectors.toList()))
                );

        logger.info(JSON.toJSONString(map));
    }

}
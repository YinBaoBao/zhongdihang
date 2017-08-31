package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/8 12:50
 * @Modified By：
 * @Version: V1.0.0
 */
public class RoleServiceTest extends ServiceTest{
    private static Logger logger = Logger.getLogger(RoleServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    @Test
    @Rollback(false)
    public void testSaveRole() throws Exception {
        Menu menu=new Menu();
        menu.setCreateUser(userService.getUser(344925359327674368L));
        menu.setDescription("登陆");
        menu.setMenuType(MenuTypeEnum.INTERFACE);
        menu.setMenuName("登陆");
        menu.setMethod("get");
        menu.setSortNum(0);
        menu.setUrl("/login");
        menu.setCrateTime(new Date());
        Role role=new Role();
        role.setCreateTime(new Date());
        role.setCreateUser(userService.getUser(344925359327674368L));
        role.setSortNum(0);
        role.setDescription("系统超级管理员");
        Set<Menu> menuSet=new HashSet<Menu>();
        menuSet.add(menu);
        menuService.saveMenu(menu);
        role.setMenus(menuSet);
        role.setRoleName("超级管理员");
        roleService.saveRole(role);
    }

    @Test
    public void testGetRole() throws Exception {
        Role role=roleService.getRole(344469733719932928L);
        logger.info(JSON.toJSONString(role));
    }

    @Test
    @Rollback(false)
    public void testDeleteRole() throws Exception {
        Role role=roleService.getByRoleName("超级管理员");
        roleService.deleteRole(role.getId());
    }

    @Test
    public void testExistsByRoleName() throws Exception {
        Boolean result=roleService.existsByRoleName("超级管理员");
        logger.info(JSON.toJSONString(result));
    }

    @Test
    @Rollback(false)
    public void testRoleMenu() throws Exception {
        Role role=roleService.roleMenu(344469733719932928L,new Long[]{344461513936863232L,344468894779441152L});
        logger.info(JSON.toJSONString(role));
    }
}
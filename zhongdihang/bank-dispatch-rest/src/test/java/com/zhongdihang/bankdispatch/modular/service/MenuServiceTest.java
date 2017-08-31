package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 18:18
 * @Modified By：
 * @Version: V1.0.0
 */
public class MenuServiceTest extends ServiceTest {

    private static Logger logger = Logger.getLogger(MenuServiceTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;

    @Test
    @Rollback(false)
    public void testSaveMenu() throws Exception {
        Menu menu = new Menu();
        menu.setDescription("首页");
        menu.setMenuType(MenuTypeEnum.INTERFACE);
        menu.setMenuName("首页");
        menu.setMethod("get");
        menu.setSortNum(0);
        menu.setUrl("/index");
        menu.setCrateTime(new Date());
        menuService.saveMenu(menu);
    }

    @Test
    public void testGetMenu() throws Exception {

    }

    @Test
    @Rollback(false)
    public void testDeleteMenu() throws Exception {
        menuService.deleteMenu(349937223103152128L);
    }

    @Test
    public void testFindMenuByUser(){
        List<Menu> menuList=menuService.findMenuByUser(344925359327674368L,MenuTypeEnum.HTML);
        logger.info(JSON.toJSONString(menuList));
    }
}
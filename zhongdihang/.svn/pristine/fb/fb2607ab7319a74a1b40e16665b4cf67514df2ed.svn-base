package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.service.dto.AuthDto;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:09
 * @Modified By：
 * @Version: V1.0.0
 */
public interface MenuService {

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:25
     * @MenthodName: saveMenu
     * @Description: 保存权限
     * @param menu 权限信息
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Menu
     */
    Menu saveMenu(Menu menu);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:26
     * @MenthodName: getMenu
     * @Description: 获取权限信息
     * @param id 权限ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Menu
     */
    Menu getMenu(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:26
     * @MenthodName: deleteMenu
     * @Description: 删除权限
     * @param id 权限ID
     * @Return: void
     */
    void deleteMenu(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:26
     * @MenthodName: existsByMenuName
     * @Description: 是否存在权限
     * @param menuName 权限名称
     * @Return: boolean
     */
    boolean existsByMenuName(String menuName);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 20:35
     * @MenthodName: findMenuByUser
     * @Description:查询用户拥有的权限
     * @param userId 用户ID
     * @param menuTypeEnum 权限类型
     * @Return: java.util.List<com.zhongdihang.bankdispatch.modular.domain.Menu>
     */
    List<Menu> findMenuByUser(Long userId, MenuTypeEnum menuTypeEnum);

    List<Menu> findMenu(String menuName);

    List<AuthDto> findAuth();

    List<ZTreeNode> getMenuTree();

    List<Menu> findAll();

}

package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.modular.dao.MenuDao;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.service.MenuService;
import com.zhongdihang.bankdispatch.modular.service.dto.AuthDto;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:09
 * @Modified By：
 * @Version: V1.0.0
 */
@Service
@Transactional
public class MenuServiceImpl extends ServiceBean<Long, Menu, MenuDao> implements MenuService {

    @PersistenceContext
    EntityManager em;
    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:25
     * @MenthodName: saveMenu
     * @Description: 保存权限
     * @param menu 权限信息
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Menu
     */
    @Override
    public Menu saveMenu(Menu menu) {
        return getDao().save(menu);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:26
     * @MenthodName: getMenu
     * @Description: 获取权限信息
     * @param id 权限ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Menu
     */
    @Override
    public Menu getMenu(Long id) {
        return getDao().findOne(id);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:26
     * @MenthodName: deleteMenu
     * @Description: 删除权限/手动解除和role的关联关系
     * @param id 权限ID
     * @Return: void
     */
    @Override
    public void deleteMenu(Long id) {
        Menu menu=getMenu(id);
        if (menu==null){
            return;
        }else {
            Set<Role> roles= menu.getRoles();
            for (Role role : roles) {
                role.getMenus().remove(menu);
            }
            Set<Menu> menus=menu.getChildMenus();
            menus.forEach(t->t.setpMenu(null));
        }
        getDao().delete(id);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:26
     * @MenthodName: existsByMenuName
     * @Description: 是否存在权限
     * @param menuName 权限名称
     * @Return: boolean
     */
    @Override
    public boolean existsByMenuName(String menuName){
        return getDao().existsByMenuName(menuName);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 20:35
     * @MenthodName: findMenuByUser
     * @Description:查询用户拥有的权限
     * @param userId 用户ID
     * @param menuTypeEnum 权限类型
     * @Return: java.util.List<com.zhongdihang.bankdispatch.modular.domain.Menu>
     */
    @Override
    public List<Menu> findMenuByUser(Long userId, MenuTypeEnum menuTypeEnum){
        return getDao().findByUserIdAndMenuType(userId,menuTypeEnum.toString());
    }

    @Override
    public List<Menu> findMenu(String menuName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Menu> query = cb.createQuery(Menu.class);
        Root<Menu> root = query.from(Menu.class);
        List<Predicate> list = new ArrayList<Predicate>();
        Path<String> menuNamePath =root.get("menuName");
        if(!StringUtils.isEmpty(menuName)) {
            list.add(cb.like(menuNamePath,"%"+menuName+"%"));
        }
        Predicate[] p = new Predicate[list.size()];
        cb.and(list.toArray(p));
        query.where(p);
        List<Menu> menus = em.createQuery(query).getResultList();
        return menus;
    }

    @Override
    public List<ZTreeNode> getMenuTree() {
        List<ZTreeNode> zTreeNodes=new ArrayList<>();
        List<Menu> menus=getDao().findAll();
        if (menus!=null&&menus.size()>0){
            for (Menu menu : menus) {
                ZTreeNode zTreeNode=new ZTreeNode();
                zTreeNode.setName(menu.getMenuName());
                zTreeNode.setId(menu.getId().toString());
                zTreeNode.setpId(menu.getpMenu()==null?"0":menu.getpMenu().getId().toString());
                zTreeNode.setIsOpen(true);
                zTreeNode.setChecked(false);
                zTreeNodes.add(zTreeNode);
            }
        }
        return zTreeNodes;
    }

    @Override
    public List<AuthDto> findAuth(){
        List<Menu> menus =getDao().getByMenuType(MenuTypeEnum.INTERFACE);
        List<AuthDto> authDtos=new ArrayList<>();
        if (menus!=null&&menus.size()>0){
            for (Menu menu : menus) {
                AuthDto authDto=new AuthDto();
                authDto.setMethod(menu.getMethod());
                authDto.setUrl(menu.getUrl());
                List<String> roleStr=new ArrayList<>();
                Set<Role> roles=menu.getRoles();
                if (roles!=null&&roles.size()>0){
                    for (Role role : roles) {
                        roleStr.add(role.getRoleName());
                    }
                }
                authDto.setRoleName(roleStr);
                authDtos.add(authDto);
            }
        }
        return authDtos;
    }
}

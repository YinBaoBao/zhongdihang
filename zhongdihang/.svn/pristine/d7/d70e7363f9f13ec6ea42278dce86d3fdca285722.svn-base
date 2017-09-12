package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.constant.MenuTypeEnum;
import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.MenuService;
import com.zhongdihang.bankdispatch.modular.service.RoleService;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import com.zhongdihang.bankdispatch.modular.service.dto.MenuDto;
import com.zhongdihang.bankdispatch.modular.service.dto.NavMenuDto;
import com.zhongdihang.bankdispatch.modular.service.form.MenuForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:43
 * @Modified By：
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/menu")
@Api(description = "权限项控制器")
public class MenuController extends BaseController{
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @ApiOperation("添加权限项")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultModel add(@Valid MenuForm menuForm) {
        boolean existName=menuService.existsByMenuName(menuForm.getMenuName());
        if (existName){
            return ResultModel.error(ResultStatus.FAILURE,"菜单名称已存在！");
        }
        Menu menu = new Menu();
        menu.setCrateTime(new Date());
        menu.setDescription(menuForm.getDescription());
        menu.setMenuName(menuForm.getMenuName());
        menu.setMenuType(MenuTypeEnum.fromString(menuForm.getMenuType()));
        menu.setUrl(menuForm.getUrl());
        menu.setSortNum(menuForm.getSortNum());
        menu.setPermission(menuForm.getPermission());
        menu.setMethod(menuForm.getMethod());
        Menu pMenu=menuService.getMenu(menuForm.getpId());
        menu.setCrateTime(new Date());
        menu.setCreateUser(getUser());
        if (pMenu!=null){
            menu.setpMenu(pMenu);
        }
        menuService.saveMenu(menu);
        return ResultModel.ok();
    }

    @ApiOperation("修改权限项")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultModel edit(@Valid MenuForm menuForm) {
        Menu menu = menuService.getMenu(menuForm.getId());
        if (menu==null){
            return ResultModel.error(ResultStatus.FAILURE,"菜单不存在");
        }
        if(!menu.getMenuName().equals(menuForm.getMenuName())){
            boolean existName=menuService.existsByMenuName(menuForm.getMenuName());
            if (existName){
                return ResultModel.error(ResultStatus.FAILURE,"菜单名称已存在！");
            }
        }
        menu.setMethod(menuForm.getMethod());
        menu.setMenuType(MenuTypeEnum.fromString(menuForm.getMenuType()));
        menu.setUrl(menuForm.getUrl());
        menu.setUpdateTime(new Date());
        menu.setUpdateUser(getUser());
        menu.setDescription(menuForm.getDescription());
        menu.setPermission(menuForm.getPermission());
        menu.setMenuName(menuForm.getMenuName());
        menu.setSortNum(menuForm.getSortNum());
        Menu pMenu=menuService.getMenu(menuForm.getpId());
        if (pMenu!=null) {
            boolean isEndLessLoop = isEndLessLoop(menu, pMenu.getId());
            if (isEndLessLoop){
                return ResultModel.error(ResultStatus.FAILURE,"不可以将子菜单设为自己的父级菜单！");
            }
        }
        menu.setpMenu(pMenu);
        menuService.saveMenu(menu);
        return ResultModel.ok();
    }

    @ApiOperation("权限列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel list(@RequestParam(required = false) String menuName) {
        List<Menu> menus= menuService.findMenu(menuName);
        List<MenuDto> menuDtos=new ArrayList<>();
        menus.forEach(t->menuDtos.add(new MenuDto(t)));
        return ResultModel.ok(menuDtos);
    }

    @ApiOperation("删除权限项")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultModel delete(@RequestParam Long id) {
        menuService.deleteMenu(id);
        return ResultModel.ok();
    }

    public boolean isEndLessLoop(Menu menu,Long pId){
        Set<Menu> childMenus=menu.getChildMenus();
        boolean isCycle=false;
        if (childMenus!=null&&childMenus.size()>0){
            for (Menu childMenu : childMenus) {
                if(childMenu.getId()==pId) {
                    isCycle=true;
                    break;
                }else{
                    isCycle=isEndLessLoop(childMenu,pId);
                }
            }
        }
        return isCycle;
    }


    @ApiOperation("获取权限项信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public ResultModel getInfo(@RequestParam Long id) {
        Menu menu = menuService.getMenu(id);
        MenuDto menuDto = new MenuDto();
        menuDto.setDescription(menu.getDescription());
        menuDto.setSortNum(menu.getSortNum());
        menuDto.setMenuType(menu.getMenuType().getCode());
        menuDto.setId(menu.getId() + "");
        menuDto.setUrl(menu.getUrl());
        menuDto.setMethod(menu.getMethod());
        menuDto.setMenuName(menu.getMenuName());
        menuDto.setPermission(menu.getPermission());
        if (menu.getpMenu()!=null){
            menuDto.setpId(menu.getpMenu().getId().toString());
            menuDto.setpMenuName(menu.getpMenu().getMenuName());
        }
        return ResultModel.ok(menuDto);
    }

    @ApiOperation("获取用户页面权限信息")
    @RequestMapping(value = "/nav", method = RequestMethod.GET)
    public ResultModel nav() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) throw new BusinessException(ResultStatus.FAILURE, "无身份信息");
        User user = userService.getUserByName(userDetails.getUsername());
        if (user == null) throw new BusinessException(ResultStatus.FAILURE, "用户不存在");
        List<Menu> menuList = menuService.findMenuByUser(user.getId(),MenuTypeEnum.HTML);
        List<Menu> rootMenus=menuList.stream()
                .filter(t->t.getpMenu()==null)
                .collect(Collectors.toList());
        if (rootMenus==null||rootMenus.size()<=0){
            return ResultModel.error(ResultStatus.FAILURE,"当前用户无权限");
        }
        Menu rootMenu=rootMenus.get(0);
        NavMenuDto navMenuDto=recursionMenu(rootMenu);
        return ResultModel.ok(navMenuDto);
    }

    private NavMenuDto recursionMenu(Menu menu){
        NavMenuDto navMenuDto=new NavMenuDto();
        navMenuDto.setMenuName(menu.getMenuName());
        navMenuDto.setUrl(menu.getUrl());
        navMenuDto.setSortNum(menu.getSortNum());
        Set<Menu> menus=menu.getChildMenus();
        menus= menus.stream().sorted((o1,o2)->o1.getSortNum().compareTo(o2.getSortNum())).collect(Collectors.toSet());
        if (menus!=null&&menus.size()>0){
            List<NavMenuDto> navMenuDtos=new ArrayList<>();
            for (Menu childMenu : menus) {
                NavMenuDto childNavMenuDto=new NavMenuDto();
                childNavMenuDto.setMenuName(menu.getMenuName());
                childNavMenuDto.setUrl(menu.getUrl());
                childNavMenuDto.setSortNum(menu.getSortNum());
                navMenuDtos.add(recursionMenu(childMenu));
            }
            navMenuDtos=navMenuDtos.stream().sorted((o1,o2)->o1.getSortNum().compareTo(o2.getSortNum())).collect(Collectors.toList());
            navMenuDto.setChildMenu(navMenuDtos);
        }
        return navMenuDto;
    }
    @ApiOperation("获取用户页面权限信息")
    @RequestMapping(value = "/permission", method = RequestMethod.GET)
    public ResultModel permission() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (userDetails == null) throw new BusinessException(ResultStatus.FAILURE, "无身份信息");
        User user = userService.getUserByName(userDetails.getUsername());
        if (user == null) throw new BusinessException(ResultStatus.FAILURE, "用户不存在");
        List<Menu> menuList = menuService.findMenuByUser(user.getId(),MenuTypeEnum.BUTTON);
        List<String> permissionDtos=menuList.stream()
                .filter(t->t.getpMenu()!=null)
                .map(t->t.getPermission())
                .filter(t->t!=null)
                .collect(Collectors.toList());
        return ResultModel.ok(permissionDtos);
    }

    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel roleTree(){
        List<ZTreeNode> zTreeNodes=menuService.getMenuTree();
        zTreeNodes.add(ZTreeNode.createParent());
        return ResultModel.ok(zTreeNodes);
    }

    /**
     * 获取权限列表
     */
    @RequestMapping(value = "/roleMenuTree",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel menuTreeListByRoleId(@RequestParam Long roleId) {
        Role role = roleService.getRole(roleId);
        if (role==null){
            return ResultModel.error(ResultStatus.FAILURE,"角色不存在");
        }
        Set<Menu> menus= role.getMenus();
        List<ZTreeNode> zTreeNodes=menuService.getMenuTree();
        if (menus==null||menus.size()<=0) {
            return ResultModel.ok(zTreeNodes);
        } else {
            for (ZTreeNode zTreeNode : zTreeNodes) {
                for (Menu menu : menus) {
                    if (menu.getId().toString().equals(zTreeNode.getId())){
                        zTreeNode.setChecked(true);
                    }
                }
            }
            return ResultModel.ok(zTreeNodes);
        }
    }
}

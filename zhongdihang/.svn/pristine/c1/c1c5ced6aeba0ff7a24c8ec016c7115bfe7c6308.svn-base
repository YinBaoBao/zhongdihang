package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.RoleService;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import com.zhongdihang.bankdispatch.modular.service.dto.RoleDto;
import com.zhongdihang.bankdispatch.modular.service.form.RoleForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:43
 * @Modified By：
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/role")
@Api(description = "角色控制器")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @ApiOperation("保存角色")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResultModel add(@Valid RoleForm roleForm){
        Role role = new Role();
        role.setRoleName(roleForm.getRoleName());
        role.setSortNum(roleForm.getSortNum());
        role.setDescription(roleForm.getDescription());
        Role pRole=roleService.getRole(roleForm.getpId());
        role.setpRole(pRole);
        roleService.saveRole(role);
        return ResultModel.ok();
    }

    @ApiOperation("获得角色详情")
    @RequestMapping(value = "/info",method = RequestMethod.GET)
    public ResultModel show(@RequestParam Long id){
        Role role = roleService.getRole(id);
        RoleDto roleDto = new RoleDto().convert(role);
        return ResultModel.ok(roleDto);
    }

    @ApiOperation("删除角色")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResultModel deleteRole(@RequestParam Long id){
        roleService.deleteRole(id);
        return ResultModel.ok();
    }

    @ApiOperation("角色列表")
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ResultModel showList(@RequestParam(required = false) String roleName){
        List<Role> roles=new ArrayList<>();
        if (StringUtils.isEmpty(roleName)){
            roles=roleService.findAll();
        }else {
            roles=roleService.findByRoleNameLike(roleName);
        }
        List<RoleDto> roleDtos=new ArrayList<>();
        if (roles!=null&&roles.size()>0) {
            roles.forEach(t -> roleDtos.add(new RoleDto().convert(t)));
        }
        roleDtos.sort((o1, o2) -> {
            if (o1 == null || o2 == null || StringUtils.isEmpty(o1.getSortNum()) || StringUtils.isEmpty(o2.getSortNum())) {
                return -1;
            }
            if (o1.getSortNum().compareTo(o2.getSortNum()) > 0) {
                return 1;
            }
            if (o1.getSortNum().compareTo(o2.getSortNum()) < 0) {
                return -1;
            }
            if (o1.getSortNum().compareTo(o2.getSortNum()) == 0) {
                return 0;
            }
            return 0;

        });
        return ResultModel.ok(roleDtos);
    }

    @ApiOperation("修改角色")
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public ResultModel edit(@Valid RoleForm roleForm){
        if (roleForm.getId()==null){
            return ResultModel.error(ResultStatus.FAILURE,"角色不存在！");
        }
        Role role = roleService.getRole(roleForm.getId());
        if (role==null){
            return ResultModel.error(ResultStatus.FAILURE,"角色不存在！");
        }
        role.setRoleName(roleForm.getRoleName());
        role.setSortNum(roleForm.getSortNum());
        role.setUpdateTime(new Date());
        role.setDescription(roleForm.getDescription());
        roleService.saveRole(role);
        return ResultModel.ok();
    }

    /**
     * 获取角色列表
     */
    @RequestMapping(value = "/userRoleTree",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel roleTreeListByUserId(@RequestParam Long userId) {
        User user = userService.getUser(userId);
        if (user==null){
            return ResultModel.error(ResultStatus.FAILURE,"用户不存在");
        }
        Set<Role> roles= user.getRoles();
        List<ZTreeNode> zTreeNodes=roleService.getRoleTree();
        if (roles==null||roles.size()<=0) {
            return ResultModel.ok(zTreeNodes);
        } else {
            for (ZTreeNode zTreeNode : zTreeNodes) {
                for (Role role : roles) {
                    if (role.getId().toString().equals(zTreeNode.getId())){
                        zTreeNode.setChecked(true);
                    }
                }
            }
            return ResultModel.ok(zTreeNodes);
        }
    }

    @RequestMapping(value = "/tree",method = RequestMethod.GET)
    @ResponseBody
    public ResultModel roleTree(){
        List<ZTreeNode> zTreeNodes=roleService.getRoleTree();
        zTreeNodes.add(ZTreeNode.createParent());
        return ResultModel.ok(zTreeNodes);
    }


    @ApiOperation("给角色分配权限")
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    public ResultModel menuAssign(Long roleId,@RequestParam("menuIds[]") Long[] menuIds){
        roleService.roleMenu(roleId,menuIds);
        return ResultModel.ok();
    }
}

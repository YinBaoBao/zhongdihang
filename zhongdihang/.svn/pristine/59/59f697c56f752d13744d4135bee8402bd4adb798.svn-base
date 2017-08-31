package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.service.dto.RoleQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:08
 * @Modified By：
 * @Version: V1.0.0
 */
public interface RoleService {

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:17
     * @MenthodName: saveRole
     * @Description: 保存角色 /新增/修改
     * @param role 角色信息
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Role
     */
    Role saveRole(Role role);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:18
     * @MenthodName: getRole
     * @Description: 获取角色信息
     * @param id 角色ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Role
     */
    Role getRole(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:18
     * @MenthodName: deleteRole
     * @Description: 删除角色/包括角色具有的权限
     * @param id 角色ID
     * @Return: void
     */
    void deleteRole(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:19
     * @MenthodName: findByRoleName
     * @Description: 查询角色信息
     * @param roleName 角色名称
     * @Return: java.util.List<com.zhongdihang.bankdispatch.modular.domain.Role>
     */
    List<Role> findByRoleNameLike(String roleName);

    Role getByRoleName(String roleName);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:20
     * @MenthodName: existsByRoleName
     * @Description: 是否存在角色
     * @param roleName 角色名称
     * @Return: java.lang.Boolean
     */
    Boolean existsByRoleName(String roleName);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:20
     * @MenthodName: roleMenu
     * @Description: 角色分配权限
     * @param id 角色ID
     * @param menuIds 权限ID数组
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Role
     */
    Role roleMenu(Long id, Long[] menuIds);

    List<ZTreeNode> getRoleTree();

    List<Role> findAll();
}

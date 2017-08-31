package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.core.redis.RedisClient;
import com.zhongdihang.bankdispatch.modular.dao.MenuDao;
import com.zhongdihang.bankdispatch.modular.dao.RoleDao;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.RoleService;
import com.zhongdihang.bankdispatch.modular.service.dto.RoleQueryDto;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
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
public class RoleServiceImpl extends ServiceBean<Long, Role, RoleDao> implements RoleService {
    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RedisClient redisClient;

    @Value("${spring.redis.user-prefix}")
    private String redisUserPrefix;


    /**
     * @param role 角色信息
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:17
     * @MenthodName: saveRole
     * @Description: 保存角色 /新增/修改
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Role
     */
    @Override
    public Role saveRole(Role role) {
        return getDao().save(role);
    }

    /**
     * @param id 角色ID
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:18
     * @MenthodName: getRole
     * @Description: 获取角色信息
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Role
     */
    @Override
    public Role getRole(Long id) {
        return getDao().findOne(id);
    }

    /**
     * @param id 角色ID
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:18
     * @MenthodName: deleteRole
     * @Description: 删除角色/自动解除和menu关联，手动解除和user关联
     * @Return: void
     */
    @Override
    public void deleteRole(Long id) {
        Role role = getRole(id);
        if (role != null) {
            Set<User> users = role.getUsers();
            if (users != null && users.size() > 0) {
                for (User user : users) {
                    user.getRoles().remove(role);
                }
            }
            getDao().delete(role);
        }
    }

    /**
     * @param roleName 角色名称
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:19
     * @MenthodName: findByRoleName
     * @Description: 查询角色信息
     * @Return: java.util.List<com.zhongdihang.bankdispatch.modular.domain.Role>
     */
    @Override
    public List<Role> findByRoleNameLike(String roleName) {
        return getDao().findByRoleNameLike(roleName);
    }

    @Override
    public Role getByRoleName(String roleName) {
        return getDao().getByRoleName(roleName);
    }


    /**
     * @param roleName 角色名称
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:20
     * @MenthodName: existsByRoleName
     * @Description: 是否存在角色
     * @Return: java.lang.Boolean
     */
    @Override
    public Boolean existsByRoleName(String roleName) {
        return getDao().existsByRoleName(roleName);
    }

    /**
     * @param id      角色ID
     * @param menuIds 权限ID数组
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:20
     * @MenthodName: roleMenu
     * @Description: 角色分配权限
     * @Return: com.zhongdihang.bankdispatch.modular.domain.Role
     */
    @Override
    public Role roleMenu(Long id, Long[] menuIds) {
        Role role = getDao().getOne(id);
        if (role == null) {
            throw new BusinessException(ResultStatus.FAILURE, "角色不存在");
        } else {
            role.getMenus().removeAll(role.getMenus());
            for (Long menuId : menuIds) {
                Menu menu = menuDao.getOne(menuId);
                if (menu==null){
                    throw new BusinessException(ResultStatus.FAILURE,"权限不存在");
                }
                role.getMenus().add(menu);
            }
            redisClient.del(redisUserPrefix);
            return getDao().save(role);
        }
    }

    @Override
    public List<ZTreeNode> getRoleTree() {
        List<ZTreeNode> zTreeNodes=new ArrayList<>();
        List<Role> roles=getDao().findAll();
        if (roles!=null&&roles.size()>0){
            for (Role role : roles) {
                ZTreeNode zTreeNode=new ZTreeNode();
                zTreeNode.setName(role.getRoleName());
                zTreeNode.setId(role.getId().toString());
                zTreeNode.setpId(role.getpRole()==null?"0":role.getpRole().getId().toString());
                zTreeNode.setIsOpen(true);
                zTreeNode.setChecked(false);
                zTreeNodes.add(zTreeNode);
            }
        }
        return zTreeNodes;
    }
}

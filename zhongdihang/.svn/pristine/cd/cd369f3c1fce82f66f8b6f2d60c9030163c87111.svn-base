package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.dto.UserQueryDto;
import com.zhongdihang.bankdispatch.modular.service.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 12:17
 * @Modified By：
 * @Version: V1.0.0
 */
public interface UserService{
    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:01
     * @MenthodName: saveUser
     * @Description:保存用户/新增/修改
     * @param user
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    User saveUser(User user);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:02
     * @MenthodName: getUser
     * @Description:获取一个用户
     * @param id 用户D
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    User getUser(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:02
     * @MenthodName: deleteUser
     * @Description:删除一个用户/逻辑删除
     * @param id 用户ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    void deleteUser(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:03
     * @MenthodName: disableUser
     * @Description:禁用用户
     * @param id 用户ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    User disableUser(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:03
     * @MenthodName: enableUser
     * @Description:启用用户
     * @param id 用户ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    User enableUser(Long id);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:04
     * @MenthodName: existsByUserName
     * @Description:用户是否存在
     * @param userName 用户名
     * @Return: java.lang.Boolean
     */
    Boolean existsByUserName(String userName);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:04
     * @MenthodName: userRole
     * @Description:用户分配角色，原有角色清楚，新增角色。
     * @param id 用户ID
     * @param roleIds 角色数组
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    User userRole(Long id, Long[] roleIds);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:08
     * @MenthodName: findUser
     * @Description:按条件检索用户，用户姓名，用户名称，手机模糊检索，注册时间限制
     * @param condition 查询条件
     * @param page 分页信息
     * @Return: org.springframework.data.domain.Page<com.zhongdihang.bankdispatch.modular.domain.User>
     */
    Page<User> findUser(UserQueryDto condition, PageRequest page);

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:09
     * @MenthodName: findUserByOrg
     * @Description:根据用户类别查询用户，银行或者评估机构
     * @param isBankUser 是否银行
     * @param orgID 银行ID/评估机构ID
     * @Return: java.util.List<com.zhongdihang.bankdispatch.modular.domain.User>
     */
    List<User> findUserByOrg(Boolean isBankUser, Long orgID);

    User getUserByName(String userName);
}

package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.core.redis.RedisClient;
import com.zhongdihang.bankdispatch.modular.dao.RoleDao;
import com.zhongdihang.bankdispatch.modular.dao.UserDao;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import com.zhongdihang.bankdispatch.modular.service.dto.UserQueryDto;
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

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 12:19
 * @Modified By：
 * @Version: V1.0.0
 */
@Service
public class UserServiceImpl extends ServiceBean<Long, User, UserDao> implements UserService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RedisClient redisClient;

    @Value("${spring.redis.user-prefix}")
    private String redisUserPrefix;


    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:01
     * @MenthodName: saveUser
     * @Description:保存用户/新增/修改
     * @param user
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    @Override
    public User saveUser(User user) {
        return getDao().save(user);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:02
     * @MenthodName: getUser
     * @Description:获取一个用户/排除已删除用户
     * @param id 用户D
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    @Override
    public User getUser(Long id) {
        User user=getDao().findOne(id);
        if (user==null||user.getDeleted()) return null;
        return user;
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:02
     * @MenthodName: deleteUser
     * @Description:删除一个用户/逻辑删除
     * @param id 用户ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    @Override
    public void deleteUser(Long id){
        User user = getUser(id);
        if (user != null) {
            user.setDeleted(true);
            user.getRoles().removeAll(user.getRoles());
            saveUser(user);
        }
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:03
     * @MenthodName: disableUser
     * @Description:禁用用户
     * @param id 用户ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    @Override
    public User disableUser(Long id) {
        User user = getUser(id);
        if (user != null) {
            user.setEnable(false);
            return saveUser(user);
        }else {
            throw new BusinessException(ResultStatus.FAILURE,"用户不存在");
        }
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:03
     * @MenthodName: enableUser
     * @Description:启用用户
     * @param id 用户ID
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    @Override
    public User enableUser(Long id) {

        User user = getUser(id);
        if (user != null) {
            user.setEnable(true);
            return saveUser(user);
        }else {
            throw new BusinessException(ResultStatus.FAILURE,"用户不存在");
        }
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:04
     * @MenthodName: existsByUserName
     * @Description:用户是否存在
     * @param userName 用户名
     * @Return: java.lang.Boolean
     */
    @Override
    public Boolean existsByUserName(String userName) {
        return getDao().existsByUserName(userName);
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:04
     * @MenthodName: userRole
     * @Description:用户分配角色，原有角色清楚，新增角色。
     * @param id 用户ID
     * @param roleIds 角色数组
     * @Return: com.zhongdihang.bankdispatch.modular.domain.User
     */
    @Override
    public User userRole(Long id, Long[] roleIds){
        User user=getDao().getOne(id);
        if (user==null){
            throw new BusinessException(ResultStatus.FAILURE,"用户不存在");
        }else{
            user.getRoles().removeAll(user.getRoles());
            for (Long roleId : roleIds) {
                Role role=roleDao.getOne(roleId);
                if (role==null){
                    throw new BusinessException(ResultStatus.FAILURE,"角色不存在");
                }
                user.getRoles().add(role);
            }
            redisClient.hdel(redisUserPrefix.getBytes(),user.getUserName().getBytes());
            return getDao().save(user);
        }
    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:08
     * @MenthodName: findUser
     * @Description:按条件检索用户，用户姓名，用户名称，手机模糊检索，注册时间限制
     * @param condition 查询条件
     * @param page 分页信息
     * @Return: org.springframework.data.domain.Page<com.zhongdihang.bankdispatch.modular.domain.User>
     */
    @Override
    public Page<User> findUser(UserQueryDto condition, PageRequest page){

        Pageable pageable = page;
        Page<User> users= getDao().findAll(new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Date> createTimePath = root.get("createTime");
                Path<String> userNamePath =root.get("userName");
                Path<String> realNamePath =root.get("realName");
                Path<String> telephonePath =root.get("telephone");
                Path<Bank> bankPath =root.get("bank");
                Path<Boolean> isDeletedPath =root.get("isDeleted");
                List<Predicate> list = new ArrayList<Predicate>();
                Join<User,Bank> bankUserJoin = root.join("bank",JoinType.INNER);
                if(condition.getCondition()!=null&&!"".equals(condition.getCondition())) {
                    list.add(cb.and(cb.or(cb.like(userNamePath,"%"+condition.getCondition()+"%"),
                            cb.or(cb.like(realNamePath,"%"+condition.getCondition()+"%")),
                            cb.or(cb.like(bankUserJoin.get("name").as(String.class),"%"+condition.getCondition()+"%")),
                            cb.or(cb.like(telephonePath,"%"+condition.getCondition()+"%")))));
                }
                if(condition.getStartDate()!=null) {
                    list.add(cb.greaterThanOrEqualTo(createTimePath,condition.getStartDate()));
                }
                if(condition.getEndDate()!=null) {
                    list.add(cb.lessThanOrEqualTo(createTimePath,condition.getEndDate()));
                }
                list.add(cb.equal(isDeletedPath, false));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageable);
        return users;

    }

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/9 15:09
     * @MenthodName: findUserByOrg
     * @Description:根据用户类别查询用户，银行或者评估机构
     * @param isBankUser 是否银行
     * @param orgID 银行ID/评估机构ID
     * @Return: java.util.List<com.zhongdihang.bankdispatch.modular.domain.User>
     */
    @Override
    public List<User> findUserByOrg(Boolean isBankUser, Long orgID){
        List<User> users=new ArrayList<>();
        if (isBankUser==null) return users;
        if (isBankUser){
            return getDao().findUserByBankIdAndIsDeletedAndIsBankUser(orgID,false,isBankUser);
        }else {
            return getDao().findUserByAssessIdAndIsDeletedAndIsBankUser(orgID,false,isBankUser);
        }
    }

    @Override
    public User getUserByName(String userName){
        User user = getDao().getByUserName(userName);
        if (user == null || user.getDeleted()) return null;
        return user;
    }

}

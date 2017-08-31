package com.zhongdihang.bankdispatch.modular.service;

import com.alibaba.fastjson.JSON;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.dto.UserQueryDto;
import com.zhongdihang.bankdispatch.modular.service.dto.UserDto;
import com.zhongdihang.framework.test.ServiceTest;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.annotation.Rollback;

import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 13:33
 * @Modified By：
 * @Version: V1.0.0
 */
public class UserServiceTest extends ServiceTest {

    private static Logger logger = Logger.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    @Test
    @Rollback(false)
    public void testAddUser(){
        for (int i = 0; i <1000 ; i++) {
            BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(4);
            User user=new User();
            user.setUserName("admin"+i);
            user.setRealName("admin"+i);
            user.setPassword(bCryptPasswordEncoder.encode("123456"));
            user.setCreateTime(new Date());
            user.setEnable(true);
            user.setSex("1");
            user.setDeleted(false);
            user.setTelephone("15962664438");
            user.seteMail("785411270@qq.com");
            user.setBankUser(true);
            user=userService.saveUser(user);
            logger.info(JSON.toJSONString(user));
        }

    }

    @Test
    public void testExistUser(){
        String userName="admin0";
        boolean result=userService.existsByUserName(userName);
        logger.info("是否存在用户admin："+result);
    }

    @Test
    public void testFindUser(){
        PageRequest pageRequest=new PageRequest(0,10,new Sort(Sort.Direction.DESC,"createTime"));
        UserQueryDto userQueryDto =new UserQueryDto().setCondition("15962664438");
        Page<User> users= userService.findUser(userQueryDto,pageRequest);
        logger.info(JSON.toJSONString(users));
    }

    @Test
    @Rollback(false)
    public void testUserRole(){
        userService.userRole(344925359327674368L,new Long[]{345138242854584320L});
    }

    @Test
    @Rollback(false)
    public void testDeleteUser(){
        userService.deleteUser(344925359327674368L);
    }

    @Test
    @Rollback(false)
    public void testUpdateUser(){
        User user=userService.getUserByName("admin0");
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(4);
        user.setPassword(bCryptPasswordEncoder.encode("123456"));
        userService.saveUser(user);
    }

    @Test
    public void testPassword(){
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder(4);
        String password=bCryptPasswordEncoder.encode("123456");
        boolean r=bCryptPasswordEncoder.matches("123456",password);
        logger.info(password);
        logger.info(r);
    }
}
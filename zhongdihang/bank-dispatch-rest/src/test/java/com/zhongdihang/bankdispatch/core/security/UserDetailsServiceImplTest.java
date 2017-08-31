package com.zhongdihang.bankdispatch.core.security;

import com.zhongdihang.bankdispatch.core.redis.RedisClient;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.framework.test.ServiceTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.SerializationUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/24 12:37
 * @Modified By：
 * @Version: V1.0.0
 */
public class UserDetailsServiceImplTest extends ServiceTest{

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisClient redisClient;

    @Test
    public void testLoadUserByUsername() throws Exception {
        Set<Role> roles=new HashSet<>();
        Role role=new Role();
        role.setRoleName("管理员");
        roles.add(role);
        AuthorityUser authorityUser=new AuthorityUser(1L,"admin0","123456","785411270@qq.com",roles.stream()
                .map(p->new SimpleGrantedAuthority(p.getRoleName()))
                .collect(Collectors.toList()));
        byte[] object= SerializationUtils.serialize(authorityUser);
        AuthorityUser o2= (AuthorityUser) SerializationUtils.deserialize(object);
        String userName="admin0";
        redisClient.hset("user-".getBytes(),userName.getBytes(),  SerializationUtils.serialize(authorityUser));
        byte[] byteUser=redisClient.hget("user-".getBytes(),userName.getBytes());
        o2= (AuthorityUser) SerializationUtils.deserialize(byteUser);
        userDetailsService.loadUserByUsername("admin0");
    }
}
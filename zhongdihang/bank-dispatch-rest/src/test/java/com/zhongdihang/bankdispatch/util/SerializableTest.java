package com.zhongdihang.bankdispatch.util;

import com.zhongdihang.bankdispatch.core.security.AuthorityUser;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import org.junit.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.SerializationUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/24 12:30
 * @Modified By：
 * @Version: V1.0.0
 */
public class SerializableTest {

    @Test
    public void  testAuthorityUser(){
        Set<Role> roles=new HashSet<>();
        Role role=new Role();
        role.setRoleName("管理员");
        roles.add(role);
        AuthorityUser authorityUser=new AuthorityUser(1L,"admin0","123456","785411270@qq.com",roles.stream()
                .map(p->new SimpleGrantedAuthority(p.getRoleName()))
                .collect(Collectors.toList()));
        byte[] object= SerializationUtils.serialize(authorityUser);
        AuthorityUser o2= (AuthorityUser) SerializationUtils.deserialize(object);

    }
}

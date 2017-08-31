package com.zhongdihang.bankdispatch.core.security;

import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.domain.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/14 20:56
 * @Modified By：
 * @Version: V1.0.0
 */
public class AuthorityUserFactory {

    private AuthorityUserFactory() {
    }

    public static AuthorityUser create(Long userId, String userName, String password, String eMail, Set<Role> roles) {
        return new AuthorityUser(
                userId,
                userName,
                password,
                eMail,
                mapToGrantedAuthorities(roles)
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<Role> authorities) {
//        List<GrantedAuthority> grantedAuthoritys=new ArrayList<>();
//        for (Role role : authorities) {
//            Set<Menu> menus=role.getMenus();
//            for (Menu menu : menus) {
//                grantedAuthoritys.add(new SimpleGrantedAuthority(menu.getMenuName()));
//            }
//        }
//        return grantedAuthoritys;
        return authorities.stream()
                .map(p->new SimpleGrantedAuthority(p.getRoleName()))
                .collect(Collectors.toList());
    }
}

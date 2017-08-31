package com.zhongdihang.bankdispatch.core.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 17:29
 * @Modified By：
 * @Version: V1.0.0
 */
public class AuthorityUser  implements UserDetails {
    private static final long serialVersionUID = -8838580138335444450L;
    private  Long id;
    private  String username;
    private  String password;
    private  String email;
    private  Collection<? extends GrantedAuthority> authorities;


    public AuthorityUser(){

    }

    public AuthorityUser(
            Long id,
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

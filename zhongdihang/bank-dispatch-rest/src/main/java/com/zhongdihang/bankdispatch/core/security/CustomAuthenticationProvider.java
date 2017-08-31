package com.zhongdihang.bankdispatch.core.security;

import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/14 20:00
 * @Modified By：
 * @Version: V1.0.0
 */
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    private UserService userService;


    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:41
     * @MenthodName: authenticate
     * @Description: 验证用户身份信息
     * @param authentication 用户身份信息
     * @Return: org.springframework.security.core.Authentication
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String loginName = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
        if (vaildateUser(loginName, password, grantedAuths)) {
            Authentication auth = new UsernamePasswordAuthenticationToken(loginName, password, grantedAuths);
            return auth;
        } else {
            return null;
        }
    }

    public boolean vaildateUser(String loginName, String password, List<GrantedAuthority> grantedAuths) {
        User user = userService.getUserByName(loginName);
        if (user == null || loginName == null || password == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        if (bCryptPasswordEncoder.matches(password,user.getPassword())) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                grantedAuths.add(new SimpleGrantedAuthority(role.getRoleName()));
                logger.debug("username is " + loginName + ", " + role.getRoleName());
            }
            return true;
        }else {
           throw new BadCredentialsException("密码错误");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}

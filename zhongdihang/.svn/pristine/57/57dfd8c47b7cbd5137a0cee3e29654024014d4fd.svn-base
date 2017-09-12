package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.core.security.TokenUtil;
import com.zhongdihang.bankdispatch.modular.service.AuthService;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private AuthenticationProvider authenticationProvider;
    private UserDetailsService userDetailsService;
    private TokenUtil tokenUtil;
    private UserService userService;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationProvider authenticationProvider,
            UserDetailsService userDetailsService,
            TokenUtil tokenUtil,
            UserService userService) {
        this.authenticationProvider = authenticationProvider;
        this.userDetailsService = userDetailsService;
        this.tokenUtil = tokenUtil;
        this.userService = userService;
    }


    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationProvider.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        final String token = tokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String loginOut(String userName) {
        tokenUtil.removeToken(userName);
        return null;
    }

}

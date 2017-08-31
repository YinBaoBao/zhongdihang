package com.zhongdihang.bankdispatch.core.security;

import com.zhongdihang.bankdispatch.core.redis.RedisClient;
import com.zhongdihang.bankdispatch.modular.domain.Menu;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.MenuService;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.SerializationUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisClient redisClient;

    @Autowired
    private MenuService menuService;

    @Value("${spring.redis.user-prefix}")
    private String redisUserPrefix;

    /**
     * @Author: Arthur Han
     * @CreateTime: 2017/8/15 14:42
     * @MenthodName: loadUserByUsername
     * @Description: 加载用户信息
     * @param username 用户名
     * @Return: org.springframework.security.core.userdetails.UserDetails
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        byte[] byteUser=redisClient.get((redisUserPrefix+username).getBytes());
        if (byteUser!=null){
            Object authorityUser=  SerializationUtils.deserialize(byteUser);
            return (UserDetails) authorityUser;
        }else {
            User user = userService.getUserByName(username);
            if (user == null) {
                throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
            } else {
                AuthorityUser authorityUser= AuthorityUserFactory.create(user.getId(), user.getUserName(), user.getPassword(), user.geteMail(), user.getRoles());
                redisClient.set((redisUserPrefix+username).getBytes(),  SerializationUtils.serialize(authorityUser));
                return authorityUser;
            }
        }
    }
}

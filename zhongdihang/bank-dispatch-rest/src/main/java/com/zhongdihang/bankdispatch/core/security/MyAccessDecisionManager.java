package com.zhongdihang.bankdispatch.core.security;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Iterator;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/21 19:56
 * @Modified By：
 * @Version: V1.0.0
 */
@Service
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection)  {
        HttpServletRequest request = ((FilterInvocation) o).getHttpRequest();
        if (request.getMethod().equals("OPTIONS"))return;
        if(null== collection || collection.size() <=0) {
            return;
        }
        ConfigAttribute c;
        String needRole;
        for(Iterator<ConfigAttribute> iter = collection.iterator(); iter.hasNext(); ) {
            c = iter.next();
            needRole = c.getAttribute();
            for(GrantedAuthority ga : authentication.getAuthorities()) {//authentication 为在注释1 中循环添加到 GrantedAuthority 对象中的权限信息集合
                if(needRole.trim().equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        throw new AccessDeniedException("无权操作");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

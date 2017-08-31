package com.zhongdihang.bankdispatch.core.security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/29 20:16
 * @Modified By：
 * @Version: V1.0.0
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException,
            ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, accessDeniedException.getMessage());
    }
}

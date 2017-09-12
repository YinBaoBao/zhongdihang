package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.modular.service.AuthService;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/12 14:31
 * @Modified By：
 * @Version: V1.0.0
 */
@RestController
@Api(description = "登陆控制器")
public class LoginController extends BaseController{

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public ResultModel login(@RequestParam  String userName, @RequestParam  String password)
            throws AuthenticationException {
        final String token = authService.login(userName, password);
        return ResultModel.ok(token);
    }

    @RequestMapping(value = "loginOut", method = RequestMethod.POST)
    public ResultModel loginOut(@RequestHeader String token) throws AuthenticationException {
        authService.loginOut(getUser().getUserName());
        return ResultModel.ok();
    }
}

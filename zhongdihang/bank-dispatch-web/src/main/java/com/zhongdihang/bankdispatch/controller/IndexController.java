package com.zhongdihang.bankdispatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/31 10:25
 * @Modified By：
 * @Version: V1.0.0
 */
@Controller
public class IndexController {
    @GetMapping(path={"/",""})
    public String index(){
        return "forward:login.html";
    }
}

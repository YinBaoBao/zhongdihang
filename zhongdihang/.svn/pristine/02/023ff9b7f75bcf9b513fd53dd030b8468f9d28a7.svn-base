package com.zhongdihang.bankdispatch.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/15 17:23
 * @Modified By：
 * @Version: V1.0.0
 */
@Configuration
@EnableAutoConfiguration
public class CorsConfigurator extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowCredentials(true);
//        registry
//                .addMapping("/**")
//                .allowedMethods("GET", "POST", "DELETE", "PUT", "OPTIONS")
//                .allowCredentials(true)
//                .allowedOrigins("*")
//                .maxAge(3600)
//                .allowedHeaders("Content-Type","X-Requested-With","token");//跨域请求头信息
    }
}

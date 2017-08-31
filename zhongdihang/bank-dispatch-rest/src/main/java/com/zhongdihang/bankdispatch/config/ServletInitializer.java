package com.zhongdihang.bankdispatch.config;

import com.zhongdihang.bankdispatch.BankDispatchApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 16:22
 * @Modified By：
 * @Version: V1.0.0
 */
public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(BankDispatchApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {
        FilterRegistration.Dynamic openEntityManagerInViewFilter = servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);
        openEntityManagerInViewFilter.setInitParameter("entityManagerFactoryBeanName","entityManagerFactory");
        openEntityManagerInViewFilter.addMappingForUrlPatterns(null, false, "/*");
        super.onStartup(servletContext);
    }
}

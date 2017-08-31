package com.zhongdihang.bankdispatch.core.page;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/16 19:18
 * @Modified By：
 * @Version: V1.0.0
 */
public class PageFactory {

    public PageRequest defaultPage() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        int pageSize=10;
        int pageNumber=1;
        String pageNumberStr=request.getParameter("pageNumber");
        String pageSizeStr=request.getParameter("pageSize");
        if (!StringUtils.isEmpty(pageSizeStr)){
            pageSize=Integer.valueOf(pageSizeStr);
        }
        if (!StringUtils.isEmpty(pageNumberStr)){
            pageNumber=Integer.valueOf(pageNumberStr);
        }
        String sortName = request.getParameter("sortName");
        String order = request.getParameter("sortOrder");
        if(StringUtils.isEmpty(sortName)){
            PageRequest pageRequest=new PageRequest(pageNumber - 1, pageSize);
            return pageRequest;
        }else{
            if(Sort.Direction.ASC.name().equals(order.toUpperCase())){
                PageRequest pageRequest=new PageRequest(pageNumber - 1, pageSize,new Sort(Sort.Direction.ASC, sortName));
                return pageRequest;
            }else{
                PageRequest pageRequest=new PageRequest(pageNumber - 1, pageSize,new Sort(Sort.Direction.DESC, sortName));
                return pageRequest;
            }
        }
    }
}

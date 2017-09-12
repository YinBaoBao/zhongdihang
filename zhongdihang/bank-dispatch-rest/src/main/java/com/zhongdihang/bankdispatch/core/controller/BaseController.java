package com.zhongdihang.bankdispatch.core.controller;

import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.common.exceptions.BusinessException;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.DictService;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;

import java.util.Map;

/**
 * Created by win10 on 2017/08/17.
 */
@Controller
public class BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private DictService dictService;

    public User getUser(){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        if (userDetails==null)throw new BusinessException(ResultStatus.FAILURE,"无身份信息");
        User user=userService.getUserByName(userDetails.getUsername());
        if(user==null)throw new BusinessException(ResultStatus.FAILURE,"用户不存在");
        return user;
    }

    public String getDictDataName(DictEnum dictEnum, String dictCode){
        return dictService.getDictDataNameByDictCode(dictEnum.getDictCode(),dictCode);
    }

    public Map<String, String> getDictData(DictEnum dictEnum){
        return dictService.getDictDataByDictCode(dictEnum.getDictCode());
    }
}

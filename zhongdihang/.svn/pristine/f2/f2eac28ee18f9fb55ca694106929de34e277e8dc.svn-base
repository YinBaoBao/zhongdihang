package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.bankdispatch.modular.domain.Dispatch;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.dto.AssessDto;
import com.zhongdihang.bankdispatch.modular.service.dto.DispatchDto;
import com.zhongdihang.bankdispatch.modular.service.dto.UserQueryDto;
import com.zhongdihang.bankdispatch.modular.service.form.newGUarantyForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * Created by win10 on 2017/08/16.
 */
public interface DispatchService {
    //=====================================
    //按权重派单
    //=====================================
    AssessDto randomDispatch(Long bankId, User user, List<newGUarantyForm> gUarantyFormList);
    //==========================================
    //查询当前机构订单
    //==========================================
    Page<Dispatch> findDispatch(String status, Bank bank_, Assess assess_,  String dispatchNo, PageRequest page);
    //==========================================
    //转为正式评估
    //==========================================
    Dispatch dispatchByFormal(Long dispatchId);

}

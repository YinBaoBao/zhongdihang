package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.config.CorsConfigurator;
import com.zhongdihang.bankdispatch.modular.domain.File;
import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import com.zhongdihang.bankdispatch.modular.service.dto.GuarantyQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Created by Administrator on 2017/8/11.
 */
public interface GuarantyService {

    Guaranty saveGuaranty(Guaranty guaranty);

    Guaranty getGuaranty(Long id);

    void deleteGuaranty(Long id);

    Page<Guaranty> findGuaranty(GuarantyQueryDto condition, PageRequest page);

}

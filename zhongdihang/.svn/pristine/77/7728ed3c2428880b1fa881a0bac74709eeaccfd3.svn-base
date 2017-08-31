package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.modular.dao.GuarantyDao;
import com.zhongdihang.bankdispatch.modular.domain.Guaranty;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.GuarantyService;
import com.zhongdihang.bankdispatch.modular.service.dto.GuarantyQueryDto;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/8/11.
 */
@Service
public class GuarantyServiceImpl extends ServiceBean<Long, Guaranty, GuarantyDao> implements GuarantyService {
    @Override
    public Guaranty saveGuaranty(Guaranty guaranty) {
        return getDao().save(guaranty);
    }

    @Override
    public Guaranty getGuaranty(Long id) {
        return getDao().findOne(id);
    }

    @Override
    public void deleteGuaranty(Long id) {
        getDao().delete(id);
    }

    @Override
    public Page<Guaranty> findGuaranty(GuarantyQueryDto condition, PageRequest page) {
        Pageable pageable = page;
        Page<Guaranty> guaranties= getDao().findAll(new Specification<Guaranty>(){
            @Override
            public Predicate toPredicate(Root<Guaranty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Date> createTimePath = root.get("createTime");
                Path<String> dispatchNoPath =root.get("dispatchNo");
                List<Predicate> list = new ArrayList<Predicate>();
                if(condition.getCondition()!=null&&!"".equals(condition.getCondition())) {
                    list.add(cb.and(cb.or(cb.like(dispatchNoPath,"%"+condition.getCondition()+"%"))));
                }
                if(condition.getStartDate()!=null) {
                    list.add(cb.greaterThan(createTimePath,condition.getStartDate()));
                }
                if(condition.getEndDate()!=null) {
                    list.add(cb.lessThan(createTimePath,condition.getEndDate()));
                }
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageable);
        return guaranties;
    }
}

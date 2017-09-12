package com.zhongdihang.bankdispatch.modular.service.impl;

import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.modular.dao.AssessDao;
import com.zhongdihang.bankdispatch.modular.dao.BankDao;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.bankdispatch.modular.service.AssessService;
import com.zhongdihang.bankdispatch.modular.service.dto.AssessQueryDto;
import com.zhongdihang.framework.biz.jpa.service.impl.ServiceBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/9 15:28
 * @Modified By：
 * @Version: V1.0.0
 */
@Service
public class AssessServiceImpl extends ServiceBean<Long, Assess, AssessDao> implements AssessService {

    @Autowired
    private BankDao bankDao;

    @Override
    public Assess saveAssess(Assess assess) {
        return getDao().save(assess);
    }

    @Override
    public Assess getAssess(Long id) {
        Assess assess = getDao().findOne(id);
        if (assess.getDeleted() == null || assess.getDeleted()) return null;
        return assess;
    }

    @Override
    public Assess findByAssessName(String assessName) {
        return getDao().getByName(assessName);
    }

    @Override
    public void deleteAssess(Long id) {
        Assess assess = getAssess(id);
        if (assess != null) {
            assess.setDeleted(true);
            Set<Bank> banks = assess.getBanks();
            if (banks != null && banks.size() > 0) {
                for (Bank bank : banks) {
                    bank.getAssesses().remove(assess);
                }
            }
            saveAssess(assess);
        }

    }

    @Override
    public Page<Assess> findAssess(AssessQueryDto condition, PageRequest page) {
        Pageable pageable = page;
        Page<Assess> assesses= getDao().findAll(new Specification<Assess>(){
            @Override
            public Predicate toPredicate(Root<Assess> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Path<Date> createTimePath = root.get("createTime");
                Path<String> NamePath =root.get("name");
                Path<String> telephonePath =root.get("telephone");
                Path<Boolean> isDeletedPath =root.get("isDeleted");
                List<Predicate> list = new ArrayList<Predicate>();
                if(condition.getCondition()!=null&&!"".equals(condition.getCondition())) {
                    list.add(cb.and(cb.or(cb.like(NamePath,"%"+condition.getCondition()+"%"),
                            cb.or(cb.like(telephonePath,"%"+condition.getCondition()+"%")))));
                }
                if(condition.getStartDate()!=null) {
                    list.add(cb.greaterThan(createTimePath,condition.getStartDate()));
                }
                if(condition.getEndDate()!=null) {
                    list.add(cb.lessThan(createTimePath,condition.getEndDate()));
                }
                list.add(cb.equal(isDeletedPath, false));
                Predicate[] p = new Predicate[list.size()];
                return cb.and(list.toArray(p));
            }
        },pageable);
        return assesses;
    }

    @Override
    public Boolean existsByAssessName(String assessName) {
        return getDao().existsByName(assessName);
    }

    @Override
    public List<Assess> findAssessList(){
        return getDao().findAssessByIsDeleted(false);
    }

    @Override
    public List<ZTreeNode> getAssessTree(){
        List<ZTreeNode> zTreeNodes=new ArrayList<>();
        List<Assess> assesses=findAssessList();
        if (assesses!=null&&assesses.size()>0){
            for (Assess assess : assesses) {
                ZTreeNode zTreeNode=new ZTreeNode();
                zTreeNode.setName(assess.getName());
                zTreeNode.setId(assess.getId().toString());
                zTreeNode.setIsOpen(true);
                zTreeNode.setChecked(false);
                zTreeNodes.add(zTreeNode);
            }
        }
        return zTreeNodes;
    }
}

package com.zhongdihang.bankdispatch.modular.service;

import com.zhongdihang.bankdispatch.common.node.ZTreeNode;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.service.dto.AssessQueryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/9 15:28
 * @Modified By：
 * @Version: V1.0.0
 */
public interface AssessService {
    Assess saveAssess(Assess assess);

    Assess getAssess(Long id);

    Assess findByAssessName(String assessName);

    void deleteAssess(Long id);

    Page<Assess> findAssess(AssessQueryDto condition, PageRequest page);

    Boolean existsByAssessName(String assessName);

    List<Assess> findAssessList();

    List<ZTreeNode> getAssessTree();
}

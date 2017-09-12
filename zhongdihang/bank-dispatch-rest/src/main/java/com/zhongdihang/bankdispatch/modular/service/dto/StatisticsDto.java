package com.zhongdihang.bankdispatch.modular.service.dto;

import java.util.List;

/**
 * @Author: Marco-Feng
 * @CreateTime: 2017/09/07
 */
public class StatisticsDto {
    private String waitingCount;//待接单总数
    private String completedCount;//已完成总数
    private String pendingEvaluationCount;//待完成总数
    private String BillCount;//单据总数

    public String getWaitingCount() {
        return waitingCount;
    }

    public void setWaitingCount(String waitingCount) {
        this.waitingCount = waitingCount;
    }

    public String getCompletedCount() {
        return completedCount;
    }

    public void setCompletedCount(String completedCount) {
        this.completedCount = completedCount;
    }

    public String getPendingEvaluationCount() {
        return pendingEvaluationCount;
    }

    public void setPendingEvaluationCount(String pendingEvaluationCount) {
        this.pendingEvaluationCount = pendingEvaluationCount;
    }

    public String getBillCount() {
        return BillCount;
    }

    public void setBillCount(String billCount) {
        BillCount = billCount;
    }

}

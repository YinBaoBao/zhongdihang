package com.zhongdihang.bankdispatch.modular.domain;

import com.zhongdihang.framework.biz.jpa.domian.EntityLongId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.util.Date;

/**
 * @Author: Arthur Han
 * @Description:预评估报告表
 * @CreateTime: 2017/8/4 14:16
 * @Modified By：
 * @Version: V1.0.0
 */
@Entity
public class PreReport extends EntityLongId{
    private static final long serialVersionUID = -2605454244456252051L;

    @OneToOne
    private Dispatch dispatch;

    private String predictNo;

    @Column(nullable = false)
    private String predict;

    @ManyToOne
    private User createUser;

    @Column(nullable = false)
    private Date createTime;

    @ManyToOne
    private User updateUser;

    private Date updateTime;

    @ManyToOne
    private File  file;


    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Dispatch getDispatch() {
        return dispatch;
    }

    public void setDispatch(Dispatch dispatch) {
        this.dispatch = dispatch;
    }

    public String getPredictNo() {
        return predictNo;
    }

    public void setPredictNo(String predictNo) {
        this.predictNo = predictNo;
    }

    public String getPredict() {
        return predict;
    }

    public void setPredict(String predict) {
        this.predict = predict;
    }

    public User getCreateUser() {
        return createUser;
    }

    public void setCreateUser(User createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public User getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(User updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

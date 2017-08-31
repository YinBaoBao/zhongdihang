package com.zhongdihang.bankdispatch.modular.service.dto;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/17 19:51
 * @Modified By：
 * @Version: V1.0.0
 */
public class DeptDto {
    private Integer type;

    private String typeName;

    private String deptId;

    private String deptName;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

package com.zhongdihang.bankdispatch.modular.service.dto;

import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/27 15:20
 * @Modified By：
 * @Version: V1.0.0
 */
public class PermissionDto {
    private List<String> permission;

    public List<String> getPermission() {
        return permission;
    }

    public void setPermission(List<String> permission) {
        this.permission = permission;
    }
}

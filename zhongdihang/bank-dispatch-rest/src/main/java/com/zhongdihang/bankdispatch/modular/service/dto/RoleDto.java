package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.modular.domain.Role;
import org.springframework.core.convert.converter.Converter;

/**
 * Created by Administrator on 2017/8/15.
 */
public class RoleDto implements Converter<Role,RoleDto> {
    private String id;

    private String roleName;

    private String pName;

    private String pId;

    private String description;

    private Integer sortNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public RoleDto convert(Role role) {
        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId()+"");
        roleDto.setRoleName(role.getRoleName());
        roleDto.setDescription(role.getDescription());
        roleDto.setSortNum(role.getSortNum());
        roleDto.setpName(role.getpRole()==null?"-":role.getpRole().getRoleName());
        roleDto.setpId(role.getpRole()==null?"0":role.getpRole().getId().toString());
        return roleDto;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }
}

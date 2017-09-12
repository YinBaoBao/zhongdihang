package com.zhongdihang.bankdispatch.modular.service.dto;

import com.zhongdihang.bankdispatch.modular.domain.Role;
import com.zhongdihang.bankdispatch.modular.domain.User;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;
import java.util.Set;

/**
 * @Author: Arthur Han
 * @Description:用户列表返回实体
 * @CreateTime: 2017/8/8 20:13
 * @Modified By：
 * @Version: V1.0.0
 */
public class UserDto implements Converter<User, UserDto> {

    private String id;

    private String userName;

    private String realName;

    private String orgName;

    private String orgId;

    private String userType;

    private String telephone;

    private Date createTime;

    private String eMail;

    private String isEnable;

    private String sex;

    private String roles;

    public UserDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    @Override
    public UserDto convert(User user) {
        UserDto userDto = new UserDto();
        userDto.setCreateTime(user.getCreateTime());
        userDto.setRealName(user.getRealName());
        userDto.setId(user.getId()+"");
        userDto.setSex(user.getSex());
        userDto.seteMail(user.geteMail());
        userDto.setTelephone(user.getTelephone());
        userDto.setEnable(user.getEnable()?"1":"0");
        userDto.setUserName(user.getUserName());
        StringBuilder roleNames = new StringBuilder();
        if (user.getRoles() != null && user.getRoles().size() > 0) {
            Set<Role> roles = user.getRoles();
            for (Role role : roles) {
                roleNames.append(","+role.getRoleName() );
            }
        }
        if (user.getBankUser() != null) {
            if (user.getBankUser()) {
                userDto.setOrgName(user.getBank() == null ? "" : user.getBank().getName());
                userDto.setUserType("1");
                userDto.setOrgId(user.getBank() == null ? "" : user.getBank().getId().toString());
            } else {
                userDto.setOrgName(user.getAssess() == null ? "" : user.getAssess().getName());
                userDto.setUserType("2");
                userDto.setOrgId(user.getAssess() == null ? "" : user.getAssess().getId().toString());
            }
        }
        userDto.setRoles(roleNames.length()>0?roleNames.substring(1):"");
        return userDto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getEnable() {
        return isEnable;
    }

    public void setEnable(String enable) {
        isEnable = enable;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}

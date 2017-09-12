package com.zhongdihang.bankdispatch.modular.controller;

import com.zhongdihang.bankdispatch.common.constant.DictEnum;
import com.zhongdihang.bankdispatch.common.constant.UserTypeEnum;
import com.zhongdihang.bankdispatch.core.controller.BaseController;
import com.zhongdihang.bankdispatch.core.page.PageFactory;
import com.zhongdihang.bankdispatch.modular.domain.Assess;
import com.zhongdihang.bankdispatch.modular.domain.Bank;
import com.zhongdihang.bankdispatch.modular.domain.User;
import com.zhongdihang.bankdispatch.modular.service.AssessService;
import com.zhongdihang.bankdispatch.modular.service.BankService;
import com.zhongdihang.bankdispatch.modular.service.UserService;
import com.zhongdihang.bankdispatch.modular.service.dto.UserDto;
import com.zhongdihang.bankdispatch.modular.service.dto.UserQueryDto;
import com.zhongdihang.bankdispatch.modular.service.form.UserForm;
import com.zhongdihang.bankdispatch.rest.ResultModel;
import com.zhongdihang.bankdispatch.rest.ResultStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author: Arthur Han
 * @Description:
 * @CreateTime: 2017/8/5 15:43
 * @Modified By：
 * @Version: V1.0.0
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户控制器")
public class UserController extends BaseController{
    private static Logger log = Logger.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @Autowired
    private BankService bankService;

    @Autowired
    private AssessService assessService;

    @ApiOperation("删除用户")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResultModel delete(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return ResultModel.ok();
    }

    @ApiOperation("启用用户")
    @RequestMapping(value = "/enable", method = RequestMethod.POST)
    public ResultModel isEnable(@RequestParam("id") Long id) {
        userService.enableUser(id);
        return ResultModel.ok();
    }

    @ApiOperation("禁用用户")
    @RequestMapping(value = "/disable", method = RequestMethod.POST)
    public ResultModel disAble(@RequestParam("id") Long id) {
        userService.disableUser(id);
        return ResultModel.ok();
    }


    @ApiOperation("获得当前用户的详细信息")
    @GetMapping(value = "/currentUserInfo")
    public ResultModel getCurrentUserInfo() {
        User user = getUser();
        UserDto userDto = new UserDto().convert(user);
        return ResultModel.ok(userDto);
    }

    @ApiOperation("获得用户的详细信息")
    @GetMapping(value = "/info")
    public ResultModel getUserInfo(@RequestParam("id") Long id) {
        User user = userService.getUser(id);
        UserDto userDto = new UserDto().convert(user);
        if (user == null) {
            return ResultModel.error(ResultStatus.FAILURE, "用户不存在");
        }
        return ResultModel.ok(userDto);
    }

    @ApiOperation("获得当前用户基本信息")
    @GetMapping(value = "/authInfo")
    public ResultModel getAuthInfo() {
        UserDto userDto = new UserDto().convert(getUser());
        return ResultModel.ok(userDto);
    }

    @ApiOperation("添加用户信息")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultModel addUser(@Valid UserForm userForm) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        Boolean existsUser = userService.existsByUserName(userForm.getUserName());
        if (existsUser) {
            return ResultModel.error(ResultStatus.FAILURE, "用户已经存在");
        }
        User user = new User();
        user.setUserName(userForm.getUserName());
        user.setRealName(userForm.getRealName());
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        user.setCreateTime(new Date());
        user.setSex(userForm.getSex());
        user.setTelephone(userForm.getTelephone());
        user.seteMail(userForm.geteMail());
        user.setEnable(true);
        user.setDeleted(false);
        if (userForm.getUserType().equals(UserTypeEnum.BANK.getCode())){
            Bank bank=bankService.getBank(userForm.getOrgId());
            if (bank==null){
                return ResultModel.error(ResultStatus.FAILURE,"银行不存在");
            }
            user.setBankUser(true);
            user.setBank(bank);
        }else {
            Assess assess=assessService.getAssess(userForm.getOrgId());
            if (assess==null){
                return ResultModel.error(ResultStatus.FAILURE,"评估机构不存在");
            }
            user.setBankUser(false);
            user.setAssess(assess);
        }
        user.setCreateTime(new Date());
        user.setCreateUser(getUser());
        userService.saveUser(user);
        return ResultModel.ok();
    }

    @ApiOperation("编辑用户信息")
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResultModel editUser(@Valid UserForm userForm) {
        User user = userService.getUser(userForm.getId());
        if(user==null){
            return ResultModel.error(ResultStatus.FAILURE,"用户不存在");
        }
        if (userForm.getUserType().equals(UserTypeEnum.BANK.getCode())){
            user.setBankUser(true);
            Bank bank=bankService.getBank(userForm.getOrgId());
            if (bank==null){
                return ResultModel.error(ResultStatus.FAILURE,"银行不存在");
            }
            user.setBank(bank);
        }else {
            user.setBankUser(false);
            Assess assess=assessService.getAssess(userForm.getOrgId());
            if (assess==null){
                return ResultModel.error(ResultStatus.FAILURE,"评估机构不存在");
            }
            user.setAssess(assess);
        }
        if (StringUtils.isEmpty(userForm.getUserName())){
            return ResultModel.error(ResultStatus.FAILURE,"请输入用户名");
        }
        if (StringUtils.isEmpty(userForm.getRealName())){
            return ResultModel.error(ResultStatus.FAILURE,"请输入姓名");
        }
        if (StringUtils.isEmpty(userForm.getTelephone())){
            return ResultModel.error(ResultStatus.FAILURE,"请输入联系电话");
        }
        user.setUpdateTime(new Date());
        user.setUserName(userForm.getUserName());
        user.setRealName(userForm.getRealName());
        user.setSex(userForm.getSex());
        user.seteMail(userForm.geteMail());
        user.setTelephone(userForm.getTelephone());
        user.setUpdateTime(new Date());
        user.setUpdateUser(getUser());
        user = userService.saveUser(user);
        return ResultModel.ok();
    }

    @ApiOperation("用户信息修改")
    @RequestMapping(value = "/infoedit", method = RequestMethod.POST)
    public ResultModel editUser(@RequestParam("realName") String realName, @RequestParam(value = "sex",required = false) String sex,
                                @RequestParam(value = "eMail",required = false) String eMail, @RequestParam("telephone") String telephone) {
        User user=getUser();
        if (StringUtils.isEmpty(realName)){
            return ResultModel.error(ResultStatus.FAILURE,"请输入姓名");
        }
        if (StringUtils.isEmpty(telephone)){
            return ResultModel.error(ResultStatus.FAILURE,"请输入联系电话");
        }
        user.setUpdateTime(new Date());
        user.setRealName(realName);
        user.setSex(sex);
        user.seteMail(eMail);
        user.setTelephone(telephone);
        user.setUpdateTime(new Date());
        user.setUpdateUser(getUser());
        user = userService.saveUser(user);
        return ResultModel.ok();
    }

    @ApiOperation("用户修改密码")
    @RequestMapping(value = "/password/modify", method = RequestMethod.POST)
    public ResultModel modifyUserPassword(String oldPassword, String newPassword) {
        User user = getUser();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
        if (bCryptPasswordEncoder.matches(oldPassword,user.getPassword())) {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.saveUser(user);
        } else {
            return ResultModel.error(ResultStatus.FAILURE, "原登录密码错误！");
        }
        return ResultModel.ok();
    }

    @ApiOperation("重置密码")
    @RequestMapping(value = "/password/force", method = RequestMethod.POST)
    public ResultModel forceModifyUserPassword(Long id, String newPassword) {
        User user = userService.getUser(id);
        if (user == null) {
            return ResultModel.error(ResultStatus.FAILURE, "用户不存在");
        } else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userService.saveUser(user);
        }
        return ResultModel.ok();
    }

    @ApiOperation("查询用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "String", name = "condition", value = "姓名/用户名/手机", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Date", name = "startDate", value = "注册开始日期", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Date", name = "endDate", value = "注册结束日期", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageIndex", value = "当前页", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "Integer", name = "pageSize", value = "每页数量", required = true)})
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResultModel findUser(String condition, String startDate, String endDate) {
        SimpleDateFormat dateFormat =   new SimpleDateFormat( "yyyy-MM-dd" );
        Date start=null;
        Date end=null;
        if (!StringUtils.isEmpty(startDate)){
                try {
                    start=dateFormat.parse(startDate);
                } catch (ParseException e) {
                    return ResultModel.error(ResultStatus.FAILURE,"日期格式错误！格式：YYYY-MM-DD");
                }
        }
        if (!StringUtils.isEmpty(endDate)){
            try {
                end=dateFormat.parse(endDate);
            } catch (ParseException e) {
                return ResultModel.error(ResultStatus.FAILURE,"日期格式错误！格式：YYYY-MM-DD");
            }
        }
        UserQueryDto userQueryDto = new UserQueryDto()
                .setCondition(condition)
                .setStartDate(start)
                .setEndDate(end);
        PageRequest pageRequest = new PageFactory().defaultPage();
        Page<User> users = userService.findUser(userQueryDto, pageRequest);
        Page<UserDto> userDtos = users.map(new UserDto());
        userDtos.forEach(t->t.setSex(getDictDataName(DictEnum.性别,t.getSex())));
        userDtos.forEach(t->t.setEnable(getDictDataName(DictEnum.状态,t.getEnable())));
        return ResultModel.ok(userDtos);

    }

    @ApiOperation("根据组织查询用户")
    @ApiImplicitParams({@ApiImplicitParam(paramType = "query", dataType = "Boolean", name = "isBankUser", value = "是否银行用户", required = false),
            @ApiImplicitParam(paramType = "query", dataType = "Long", name = "orgID", value = "组织机构ID", required = false)})
    @RequestMapping(value = "/dept", method = RequestMethod.GET)
    public ResultModel findUserByOrg(@RequestParam String deptType, @RequestParam Long deptId) {
        boolean isBankUser=false;
        if (deptType.equals("1"))isBankUser=true;
        List<User> users = userService.findUserByOrg(isBankUser, deptId);
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto().convert(user);
            userDtos.add(userDto);
        }
        Page<UserDto> userDtoPage=new PageImpl<UserDto>(userDtos);
        return ResultModel.ok(userDtoPage);
    }

    @ApiOperation("给用户分配角色")
    @RequestMapping(value = "/role", method = RequestMethod.POST)
    public ResultModel roleAssign(@RequestParam  Long userId,@RequestParam("roleIds[]") Long[] roleIds){
        userService.userRole(userId,roleIds);
        return ResultModel.ok();
    }
}

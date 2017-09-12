/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserInfoDlg = {
    userInfoData: {},
    validateFields: {
        userName: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                },
                stringLength: {
                    min: 6,
                    max: 18,
                    message: '用户名长度必须在6到18位之间'
                }
            }
        },
        realName: {
            validators: {
                notEmpty: {
                    message: '姓名不能为空'
                }
            }
        },
        userType: {
            validators: {
                notEmpty: {
                    message: '用户类型不能为空'
                }
            }
        },
        dept: {
            validators: {
                notEmpty: {
                    message: '部门不能为空'
                }
            }
        },
        telephone:{
            validators: {
                notEmpty: {
                    message: '手机号码不能为空'
                },
                stringLength: {
                    min: 11,
                    max: 11,
                    message: '请输入11位手机号码'
                },
                regexp: {
                    regexp: /^1[3|5|8]{1}[0-9]{9}$/,
                    message: '请输入正确的手机号码'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'rePassword',
                    message: '两次密码不一致'
                }
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'password',
                    message: '两次密码不一致'
                },
            }
        },
        eMail: {
            validators: {
                notEmpty:{
                    message: '邮箱输入不能为空'
                },
                emailAddress: {
                    message: '邮箱地址格式有误'
                }
            }
        }
    }
};
/**
 * 初始化性别下拉框
 */
UserInfoDlg.initSex=function(){
    var data=selectData("sex");
    Bank.initSelect("sex",data);
};

/**
 * 初始化用户类别下拉框
 */
UserInfoDlg.initUserType=function(){
    var data=selectData("userType");
    Bank.initSelect("userType",data);
};

/**
 * 初始化部门下拉框
 */
UserInfoDlg.initDept=function(){
    var type= $("#userType").val();
    var deptData;
    if(type=="1"){
        var ajax = new $ax(Bank.api.bank_select, function (data) {
            deptData = data;
        }, function (data) {
            Bank.error("查询银行列表记录出错！");
        });
        ajax.start();
    }else {
        var ajax = new $ax(Bank.api.assess_select, function (data) {
            deptData = data;
        }, function (data) {
            Bank.error("查询评估机构列表记录出错！");
        });
        ajax.start();
    }
    var selector = $('#dept');
    selector.empty();
    $.each(deptData, function (i) {
        $("<option></option>").val(deptData[i].deptId).text(deptData[i].deptName).appendTo(selector);
    });
};
/**
 * 用户界面初始化
 */
/**
 * 用户界面初始化
 */
UserInfoDlg.init=function(){
    UserInfoDlg.initSex();
    UserInfoDlg.initUserType();
    UserInfoDlg.initDept();
    var id=Bank.getQueryString("id");
    if(id) {
        var user;
        var ajax = new $ax(Bank.api.user_info, function (data) {
            user = data;
        }, function (data) {
            Bank.error("查询不到此用户记录！");
        });
        ajax.set("id", Bank.getQueryString("id"));
        ajax.start();
        var userVm = new Vue({
            el: '#userInfoForm',
            data: {
                user:user
            }
        });
    }
};

/*
* 超级管理员个人信息界面初始化
* */
UserInfoDlg.AdminiStrator = function () {
    var adminidata = '';
    var id = "344925359327674368";
    var ajax = new $ax(Bank.api.user_info, function (data) {
        adminidata = data;
    }, function (data) {
        Bank.error("查询不到此用户记录！");
    });
    ajax.set("id", id);
    ajax.start();
    var userVm = new Vue({
        el: '#userInfoForm',
        data: {
            adminidata:''
        },
        created: function () {
            this.adminidata  = adminidata
        },
        filters: {
            laydate: function (time) {
                var d = new Date(time);
                var year = d.getFullYear();
                var month = d.getMonth() + 1;
                var day = d.getDate() < 10 ? '0' + d.getDate() : '' + d.getDate();
                return year + '/' + month + '/' + day;
            }
        }
    });
}
/**
 * 清除数据
 */
UserInfoDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.set = function (key, value) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserInfoDlg.close = function () {
    parent.layer.close(window.parent.MgrUser.layerIndex);
};

/**
 * 收集数据
 */
UserInfoDlg.collectData = function () {
    this.set('id').set('userName').set('password').set('sex').set('eMail').set('realName').set('userType').set('orgId',$('#dept').val()).set('telephone');
};

/**
 * 验证两个密码是否一致
 */
UserInfoDlg.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/**
 * 验证数据是否为空
 */
UserInfoDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加用户
 */
UserInfoDlg.addSubmit = function () {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    if (!this.validatePwd()) {
        Bank.error("两次密码输入不一致");
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.user_add, function (data) {
        Bank.success("添加成功!");
        window.parent.MgrUser.table.refresh();
        UserInfoDlg.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
UserInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.user_edit, function (data) {
        Bank.success("修改成功!");
        if (window.parent.MgrUser != undefined) {
            window.parent.MgrUser.table.refresh();
            UserInfoDlg.close();
        }
    }, function (data) {
        Bank.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.userInfoData);
    ajax.start();
};

$(function () {
    UserInfoDlg.init();
    Bank.initValidator("userInfoForm", UserInfoDlg.validateFields);
});

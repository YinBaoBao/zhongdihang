/**
 * Created by Maybebane on 2017/9/2.
 */
/**
 * 用户详情对话框（可用于添加和修改对话框）
 */
var UserAuthDlg = {
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
        eMail: {
            validators: {
                notEmpty:{
                    message: '邮箱输入不能为空'
                },
                emailAddress: {
                    message: '邮箱地址格式有误'
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
        }
    }
};
/**
 * 初始化性别下拉框
 */
UserAuthDlg.initSex=function(){
    var data=selectData("sex");
    Bank.initSelect("sex",data);
};

/**
 * 初始化用户类别下拉框
 */
UserAuthDlg.initUserType=function(){
    var data=selectData("userType");
    Bank.initSelect("userType",data);
};

/**
 * 初始化部门下拉框
 */
UserAuthDlg.initDept=function(){
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
UserAuthDlg.initCurrentInfo=function(){
    UserAuthDlg.initSex();
    UserAuthDlg.initUserType();
    UserAuthDlg.initDept();
    var user;
    var ajax = new $ax(Bank.api.user_current_info, function (data) {
        user = data;
    }, function (data) {
        Bank.error("查询不到此用户记录！");
    });
    ajax.start();
    var userVm = new Vue({
        el: '#userInfoForm',
        data: {
            user:user
        }
    });
};

/**
 * 清除数据
 */
UserAuthDlg.clearData = function () {
    this.userInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAuthDlg.set = function (key, value) {
    this.userInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAuthDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
UserAuthDlg.close = function () {
    parent.layer.close(window.parent.Index.layerIndex);
};

/**
 * 验证数据是否为空
 */
UserAuthDlg.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};
UserAuthDlg.editUserInfoSubmit = function () {
    this.clearData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.user_infoedit, function (data) {
        Bank.success("修改成功!");
        UserAuthDlg.close();
    }, function (data) {
        Bank.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set("realName").set("sex").set("eMail").set("telephone");
    ajax.start();
};
$(function () {
    UserAuthDlg.initCurrentInfo();
    Bank.initValidator("userInfoForm", UserAuthDlg.validateFields);
});

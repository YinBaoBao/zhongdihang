/**
 * Created by Maybebane on 2017/8/23.
 */

var UserChPwd = {
    validateFields: {
        oldPassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                }
            }
        },
        newPassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                }
            }
        },
        rePassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                },
                identical: {
                    field: 'newPassword',
                    message: '两次密码不一致'
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
UserChPwd.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

UserChPwd.close=function(){
    parent.layer.close(window.parent.Index.layerIndex);
};

/**
 * 验证两个密码是否一致
 */
UserChPwd.validatePwd = function () {
    var newPassword = $("#newPassword").val();
    var rePassword = $("#rePassword").val();
    if (newPassword == '' || rePassword == '') {
        return false
    }
    if (newPassword == rePassword) {
        return true;
    } else {
        return false;
    }
};

/*
 / 管理员密码修改
 */
UserChPwd.chPwd = function () {
    if (!this.validate()) {
        return;
    }
    if (!this.validatePwd()) {
        Bank.error("两次密码输入不一致");
        return;
    }
    var ajax = new $ax(Bank.api.user_modify_password, function (data) {
        parent.layer.msg('重置密码成功，请重新登录', {
            time: 5000 //2秒关闭（如果不配置，默认是3秒）
        }, function(){
            Bank.goto(Bank.page.login);
        });
    }, function (data) {
        Bank.error("重置密码失败!");
    });
    ajax.set("oldPassword").set("newPassword");
    ajax.start();
};

window.onload = function () {
    Bank.initValidator("userInfoForm", UserChPwd.validateFields);
}

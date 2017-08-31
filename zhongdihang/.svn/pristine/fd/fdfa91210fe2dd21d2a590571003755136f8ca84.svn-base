/**
 * Created by YINBAOBAO on 2017/8/28.
 */
/**
 * Created by Maybebane on 2017/8/23.
 */

var UserAuth = {
    validateFields: {
        oldPassword: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
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
                }
            }
        }
    }
};

/**
 * 验证数据是否为空
 */
UserAuth.validate = function () {
    $('#userInfoForm').data("bootstrapValidator").resetForm();
    $('#userInfoForm').bootstrapValidator('validate');
    return $("#userInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
UserAuth.get = function (key) {
    return $("#" + key).val();
};

/**
 * 验证两个密码是否一致
 */
UserAuth.validatePwd = function () {
    var password = this.get("password");
    var rePassword = this.get("rePassword");
    if (password == '' || rePassword == '') {
        return false
    }
    if (password == rePassword) {
        return true;
    } else {
        return false;
    }
};

/*
 / 管理员密码修改
 */
UserAuth.chPwd = function () {
    if (!this.validate()) {
        return;
    }
    if (!this.validatePwd()) {
        Bank.error("两次密码输入不一致");
        return;
    }
    var userId = "344925359327674368";
    var oldPwd = $('#oldPassword').val(); // 原密码
    var newPwd = $('#rePassword').val(); // 新密码
    parent.layer.confirm('是否重置密码', {
        btn: ['确定', '取消'],
        shade: false // 不显示遮罩
    }, function () {
        var ajax = new $ax(Bank.route(Bank.api.user_force_password), function (data) {
            Bank.success("重置密码成功!");
        }, function (data) {
            Bank.error("重置密码失败!");
        });
        ajax.set("id", userId);
        ajax.set('{"oldpassword":"'+oldPwd+'","newpassword":'+newPwd+'}');
        ajax.start();
    });
};

window.onload = function () {
    Bank.initValidator("userInfoForm", UserAuth.validateFields);
}

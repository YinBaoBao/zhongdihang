
var LoginForm={
    loginData:{},
    validateFields: {
        userName: {
            validators: {
                notEmpty: {
                    message: '账户不能为空'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: '密码不能为空'
                }
            }
        }
    }
}

LoginForm.clearData = function() {
    this.loginData = {};
}

LoginForm.set = function (key, val) {
    this.loginData[key] = (typeof val == "undefined") ? $("[name='"+key+"']").val() : val;
    return this;
};

/**
 * 收集数据
 */
LoginForm.collectData = function () {
    this.loginData=Bank.formData('login-form');
};

/**
 * 验证数据是否为空
 */
LoginForm.validate = function () {
    $('#login-form').data("bootstrapValidator").resetForm();
    $('#login-form').bootstrapValidator('validate');
    return $('#login-form').data('bootstrapValidator').isValid();
};

LoginForm.login=function(){
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    } // 提交信息
    var ajax = new $ax(Bank.api.login, function (data) {
        Bank.setHeader(data);
        Bank.goto(Bank.page.index);
    },function(res){
        alert(res.response.message)
    });

    ajax.setData(this.loginData).start();
};
$(function(){
    Bank.initValidator("login-form", LoginForm.validateFields);
});
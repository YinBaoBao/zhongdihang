/**
 * 菜单详情对话框
 */
var BankInfoDlg = {
    bankInfoData: {},
    ztreeInstance: null,
    validateFields: {
        Name: {
            validators: {
                notEmpty: {
                    message: '机构名称不能为空'
                }
            }
        },
        Address: {
            validators: {
                notEmpty: {
                    message: '地址不能为空'
                }
            }
        },
        area: {
            validators: {
                notEmpty: {
                    message: '区号不能为空'
                }
            }
        },
        LinkMen: {
            validators: {
                notEmpty: {
                    message: '负责人不能为空'
                }
            }
        },
        Telephone: {
            validators: {
                notEmpty: {
                    message : '手机号码不能为空'
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
        type: {
            validators: {
                notEmpty: {
                    message : '类型不能为空'
                }
            }
        }
    }
};





/**
 * 清除数据
 */
BankInfoDlg.clearData = function () {
    this.bankInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BankInfoDlg.set = function (key, val) {
    this.bankInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BankInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 收集数据
 */
BankInfoDlg.collectData = function () {
    this.set('Address').set('Name').set('area').set('LinkMen').set('Telephone').set('type').set('id');
};

BankInfoDlg.init = function () {
    //初始化下拉框
    var data=selectData("type");
    Bank.initSelect("type",data);
    var data=selectData("area");
    Bank.initSelect("area",data);

    var id = Bank.getQueryString("id");
    if (id) {
        var bank;
        var ajax = new $ax(Bank.api.bank_info, function (data) {
            bank = data;
        }, function (data) {
            Bank.error("查询不到此银行记录！");
        });
        ajax.set("BankId", Bank.getQueryString("id"));
        ajax.start();
        var bankVm = new Vue({
            el: '#bankInfoForm',
            data: {
                bank: bank
            }
        });
    }
};

/**
 * 验证数据是否为空
 */
BankInfoDlg.validate = function () {
    $('#bankInfoForm').data("bootstrapValidator").resetForm();
    $('#bankInfoForm').bootstrapValidator('validate');
    return $("#bankInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加银行
 */
BankInfoDlg.addSubmit = function () {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new  $ax(Bank.api.bank_add, function(data) {
        Bank.success("添加成功!");
        window.parent.Banks.table.refresh();
        BankInfoDlg.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bankInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
BankInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.bank_edit, function (data) {
        Bank.success("修改成功!");
        window.parent.Banks.table.refresh();
        BankInfoDlg.close();
    }, function (data) {
        Bank.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.bankInfoData);
    ajax.start();
};


/**
 * 关闭此对话框
 */
BankInfoDlg.close = function () {
    parent.layer.close(window.parent.Banks.layerIndex);
};

$(function () {
    //初始化是否是菜单
    if($("#isbankValue").val() == undefined){
        $("#isbank").val(0);
    }else{
        $("#isbank").val($("#isbankValue").val());
    }
    BankInfoDlg.init();
    Bank.initValidator("bankInfoForm", BankInfoDlg.validateFields);
});

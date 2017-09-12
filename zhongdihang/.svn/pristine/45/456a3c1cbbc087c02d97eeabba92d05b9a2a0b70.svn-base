/**
 * Created by YINBAOBAO on 2017/8/18.
 **
 * 菜单详情对话框
 */
var AssessInfoDlg = {
    assessInfoData: {},
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '名称不能为空'
                }
            }
        },
        address: {
            validators: {
                notEmpty: {
                    message: '地址不能为空'
                }
            }
        },
        telephone: {
            validators: {
                notEmpty: {
                    message: '电话不能为空'
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
        guarantyType: {
            validators: {
                notEmpty: {
                    message: '类型不能为空'
                }
            }
        },
        linkMen: {
            validators: {
                notEmpty: {
                    message: '联系人不能为空'
                }
            }
        }
    }
};

AssessInfoDlg.initGuarantyType = function(){
    var data=selectData("guarantyType");
    AssessInfoDlg.initSelect("guarantyType",data);
};
AssessInfoDlg.initSelect = function (selectId,data,selectVue) {
    var selector = $('#'+selectId);
    selector.empty();
    $.each(data, function (i) {
        $("<option></option>").val(data[i].value).text(data[i].name).appendTo(selector);
    });
};
/**
 * 清除数据
 */
AssessInfoDlg.clearData = function () {
    this.assessInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
AssessInfoDlg.set = function (key, value) {
    this.assessInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

AssessInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
AssessInfoDlg.close = function () {
    parent.layer.close(window.parent.Assess.layerIndex);
};

/**
 * 收集数据
 */
AssessInfoDlg.collectData = function () {
    var str='';
    $('.ui-choose').ui_choose();
    var uc_04 = $('#guarantyType').ui_choose();
    for(var i=0;i<uc_04.val().length;i++){
        str+=uc_04.val()[i]+",";
    }
    str=(str.substring(str.length-1)==',')?str.substring(0,str.length-1):str;
    this.set('id').set('name').set('address').set('guarantyType',str).set('telephone').set('linkMen');
};

/**
 * 提交添加assess
 */
AssessInfoDlg.addSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Bank.api.assess_add, function (data) {
        Bank.success("添加成功!");
        window.parent.Assess.table.refresh();
        AssessInfoDlg.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.assessInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
AssessInfoDlg.editSubmit = function () {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Bank.api.assess_edit, function (data) {
        Bank.success("修改成功!");
        window.parent.Assess.table.refresh();
        AssessInfoDlg.close();
    }, function (data) {
        Bank.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.assessInfoData);
    ajax.start();
};

AssessInfoDlg.init=function(){
    AssessInfoDlg.initGuarantyType();
    var id=Bank.getQueryString("id");
    if(id) {
        var assess;
        var ajax = new $ax(Bank.api.assess_info, function (data) {
            assess = data;
        }, function (data) {
            Bank.error("查询不到此用户记录！");
        });
        ajax.set("id", Bank.getQueryString("id"));
        ajax.start();
        var assessVm = new Vue({
            el: '#assessInfoForm',
            data: {
                assess:assess
            }
        });
        var arrayOption=assess.guarantyType.split(',');
        for(var i=0;i<arrayOption.length;i++){
            $("#guarantyType option[value='"+arrayOption[i]+"']").attr("selected","selected");
        }
    }
};

/**
 * 验证数据是否为空
 */
AssessInfoDlg.validate = function () {
    $('#assessInfoForm').data("bootstrapValidator").resetForm();
    $('#assessInfoForm').bootstrapValidator('validate');
    return $("#assessInfoForm").data('bootstrapValidator').isValid();
};

$(function () {
    AssessInfoDlg.init();
    Bank.initValidator("assessInfoForm", AssessInfoDlg.validateFields);
});


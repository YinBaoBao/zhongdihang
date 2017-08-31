/**
 * 初始化派单详情对话框
 */
var DispatchInfoDlg = {
    count: $("#itemSize").val(),
    dictCode:'',
    dictName: '',			//字典的名称
    mutiString: '',		//拼接字符串内容(拼接字典条目)
    itemTemplate: $("#itemTemplate").html(),
    validateFields: {
        clientName: {
            validators: {
                notEmpty: {
                    message: '借款人不能为空'
                }
            }
        },
        clientPhone: {
            validators: {
                notEmpty: {
                    message: '借款人联系电话不能为空'
                }
            }
        }
    }
};

/**
 * item获取新的id
 */
DispatchInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "guarantyItem" + this.count;
};
/**
 * 关闭此对话框
 */
DispatchInfoDlg.close = function () {
    parent.layer.close(window.parent.Dispatch.layerIndex);
};

/**
 * 添加条目
 */
DispatchInfoDlg.addItem = function () {
    $("#dispatchArea").append(this.itemTemplate);
    $("#guarantyItem").attr("id", this.newId());
    $("#guaranty").attr("id", "guaranty" + this.count);
    $("#progressBar").attr("id", "progressBar" + this.count);
    $("#BtnId").attr("id", "guaranty" + this.count+"BtnId");
    $("#PreId").attr("id", "guaranty" + this.count+"PreId");
    var guarantyUp = new $WebUpload("guaranty" + this.count);
    guarantyUp.setUploadBarId("progressBar" + this.count);
    guarantyUp.init();
};

/**
 * 删除item
 */
DispatchInfoDlg.deleteItem = function (event) {
    var obj = Bank.eventParseObject(event);
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
DispatchInfoDlg.clearNullDom = function(){
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        if(num == '' || name == ''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加字典的数据
 */
DispatchInfoDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    $("[name='dictItem']").each(function(){
        var num = $(this).find("[name='itemNum']").val();
        var name = $(this).find("[name='itemName']").val();
        mutiString = mutiString + (num + ":" + name + ";");
    });
    this.dictCode=$("#dictCode").val();
    this.dictName = $("#dictName").val();
    this.mutiString = mutiString;
};


/**
 * 提交添加字典
 */
DispatchInfoDlg.addSubmit = function () {
    this.collectData();
    //提交信息
    var ajax = new $ax(Bank.api.dict_add, function (data) {
        Bank.success("添加成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set('dictName',this.dictName);
    ajax.set('dictCode',this.dictCode);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
};
DispatchInfoDlg.init=function(){
    var dictDatas;
    var dict;
    var ajax = new $ax(Bank.api.dict_info, function (data) {
        dict=data.dict;
        dictDatas=data.dictDatas;
    }, function (data) {
        Bank.error("查询不到此字典记录！");
    });
    ajax.set("id",Bank.getQueryString("id"));
    ajax.start();
    var dictVm = new Vue({
        el: '#dictForm',
        data: {
            dictDatas: dictDatas,
            dict:dict
        }
    })
};
/**
 * 提交修改
 */
DispatchInfoDlg.editSubmit = function () {
    this.collectData();
    var ajax = new $ax(Bank.api.dict_edit, function (data) {
        Bank.success("修改成功!");
        window.parent.Dict.table.refresh();
        DictInfoDlg.close();
    }, function (data) {
        Bank.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set('id',$("#dictId").val());
    ajax.set('dictName',this.dictName);
    ajax.set('dictCode',this.dictCode);
    ajax.set('dictValues',this.mutiString);
    ajax.start();
};

/**
 * 验证数据是否为空
 */
DispatchInfoDlg.validate = function () {
    $('#dispatchForm').data("bootstrapValidator").resetForm();
    $('#dispatchForm').bootstrapValidator('validate');
    return $("#dispatchForm").data('bootstrapValidator').isValid();
};

DispatchInfoDlg.initControl=function() {
    $('#wizard').wizard().on('change', function (e, data) {
        var $finish = $("#ensure");
        var $next = $("#btn_next");
        if (data.direction == "next") {
            switch (data.step) {
                case 1:
                    if (!DispatchInfoDlg.validate()) {
                        return false;
                    }
                    $finish.show();
                    $next.hide();
                    break;
                default:
                    break;
            }
        } else {
            $finish.hide();
            $next.show();
        }
    });
}

$(function () {
    // 初始化头像上传
    DispatchInfoDlg.initControl();
    Bank.initValidator("dispatchForm", DispatchInfoDlg.validateFields);
});


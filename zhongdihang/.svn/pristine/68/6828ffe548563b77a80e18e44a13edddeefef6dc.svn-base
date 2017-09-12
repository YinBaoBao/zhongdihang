/**
 * Created by Maybebane on 2017/9/6.
 */
/**
 * 初始化派单详情对话框
 */
var DispatchBankInfoDlg = {
    count: $("#itemSize").val(),
    dispatchData:{},
    itemTemplate: $("#itemTemplate").html(),
    validateFieldsStep1: {
        borrowerName: {
            validators: {
                notEmpty: {
                    message: '借款人不能为空'
                }
            }
        },
        borrowerPhone: {
            validators: {
                notEmpty: {
                    message: '借款人联系电话不能为空'
                }
            }
        },
        area: {
            validators: {
                notEmpty: {
                    message: '抵押物区域不能为空'
                }
            }
        },
        dispatchType: {
            validators: {
                notEmpty: {
                    message: '派单类型不能为空'
                }
            }
        },
        guarantyType: {
            validators: {
                notEmpty: {
                    message: '抵押物类型不能为空'
                }
            }
        },
        timeLimit: {
            validators: {
                notEmpty: {
                    message: '评估时限不能为空'
                }
            }
        }
    },
    validateFieldsStep2: {
        contactName: {
            validators: {
                notEmpty: {
                    message: '联系人不能为空'
                }
            }
        },
        contactPhone: {
            validators: {
                notEmpty: {
                    message: '联系人联系电话'
                }
            }
        },
        guaranty: {
            validators: {
                notEmpty: {
                    message: '请上传产证图片'
                }
            }
        }

    }
};
/**
 * 清除数据
 */
DispatchBankInfoDlg.clearData = function () {
    this.dispatchData = {};
};
/**
 * 关闭此对话框
 */
DispatchBankInfoDlg.close = function () {
    parent.layer.close(window.parent.DispatchBank.layerIndex);
};

/**
 * item获取新的id
 */
DispatchBankInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "guarantyItem" + this.count;
};

/**
 * 添加抵押物
 */
DispatchBankInfoDlg.addItem = function () {
    $("#dispatchArea").append(this.itemTemplate);
    $("#guarantyItem").attr("id", this.newId());
    $("#guaranty").attr("id", "guaranty" + DispatchBankInfoDlg.count);
    $("#progressBar").attr("id", "progressBar" + DispatchBankInfoDlg.count);
    $("#BtnId").attr("id", "guaranty" + DispatchBankInfoDlg.count+"BtnId");
    $("#PreId").attr("id", "guaranty" + DispatchBankInfoDlg.count+"PreId");
    var guarantyUp = new $WebUpload("guaranty" + DispatchBankInfoDlg.count);
    guarantyUp.setUploadBarId("progressBar" + DispatchBankInfoDlg.count);
    guarantyUp.init();
};

/**
 * 删除抵押物
 */
DispatchBankInfoDlg.deleteItem = function (event) {
    var obj = Bank.eventParseObject(event);
    obj.parents('.row').first().next('.hr-line-dashed').remove();
    obj.parents('.row').first().remove();
};

/**
 * 清除为空的抵押物 Dom
 */
DispatchBankInfoDlg.clearNullDom = function(){
    $("[name='guarantyItem']").each(function(){
        var contactName = $(this).find("[name='contactName']").val();
        var contactPhone = $(this).find("[name='contactPhone']").val();
        var guaranty = $(this).find("[name='guaranty']").val();
        if(contactName == '' || contactPhone == ''||guaranty==''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加抵押物的数据
 */
DispatchBankInfoDlg.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    var guaranties=new Array();
    $("[name='guarantyItem']").each(function(){
        var contactName = $(this).find("[name='contactName']").val();
        var contactPhone = $(this).find("[name='contactPhone']").val();
        var guaranty = $(this).find("[name='guaranty']").val();
        guaranties.push({fileId:guaranty,contactName:contactName,contactPhone:contactPhone});
    });
    this.dispatchData={id:$('#id').val(),borrowerName:$("#borrowerName").val(),borrowerPhone:$("#borrowerName").val(),
        dispatchType:$("#dispatchType").val(),area:$("#area").val(),guarantyType:$("#guarantyType").val(),
        timeLimit:$("#timeLimit").val(),guarantyFormList:guaranties};
};

/**
 * 提交添加派单
 */
DispatchBankInfoDlg.addSubmit = function () {
    //清除缓存
    this.clearData();
    //收集表单数据
    this.collectData();
    //验证表单数据
    if (!this.validateStep1()||!this.validateStep2()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.dispatch, function (data) {
        Bank.success("添加成功!");
        window.parent.DispatchBank.table.refresh();
        DispatchBank.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    //Json数据提交
    ajax.jsonData(this.dispatchData)
    ajax.start();
};

/**
 * 验证数据是否为空
 */
DispatchBankInfoDlg.validateStep1 = function () {
    $('#dispatchForm').data("bootstrapValidator").resetForm();
    $('#dispatchForm').bootstrapValidator('validate');
    return $("#dispatchForm").data('bootstrapValidator').isValid();
};
/**
 * 验证数据是否为空
 */
DispatchBankInfoDlg.validateStep2 = function () {
    $('#dispatchArea').data("bootstrapValidator").resetForm();
    $('#dispatchArea').bootstrapValidator('validate');
    return $("#dispatchArea").data('bootstrapValidator').isValid();
};

DispatchBankInfoDlg.init=function(){
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
 * 初始化wizard
 */
DispatchBankInfoDlg.initControl=function() {
    $('#wizard').wizard().on('change', function (e, data) {
        var $finish = $("#ensure");
        var $prev = $("#btn_last");
        var $next = $("#btn_next");
        if (data.direction == "next") {
            switch (data.step) {
                case 1:
                    if (!DispatchBankInfoDlg.validateStep1()) {
                        return false;
                    }
                    $finish.show();
                    $next.hide();
                    $prev.show();
                    //第一次加载，避免重复加载
                    if (typeof(DispatchBankInfoDlg.count) == "undefined") {
                        DispatchBankInfoDlg.addItem();
                        Bank.initValidator("dispatchArea", DispatchBankInfoDlg.validateFieldsStep2);
                        $('input[name=contactName]').val($('#borrowerName').val());
                        $('input[name=contactPhone]').val($('#borrowerPhone').val());
                    }
                    break;
                default:
                    break;
            }
        } else {
            $finish.hide();
            $next.show();
            $prev.hide();
        }
    });
}
/**
 * 初始化抵押物区域下拉框
 */
DispatchBankInfoDlg.initArea=function(){
    var data=selectData("area");
    Bank.initSelect("area",data);
};

/**
 * 初始化抵押物类型下拉框
 */
DispatchBankInfoDlg.initGuarantyType=function(){
    var data=selectData("guarantyType");
    Bank.initSelect("guarantyType",data);
};

/**
 * 初始化评估类型下拉框
 */
DispatchBankInfoDlg.initDispatchType=function(){
    var data=selectData("dispatchType");
    Bank.initSelect("dispatchType",data);
};

$(function () {
    //初始化表单
    DispatchBankInfoDlg.initControl();
    DispatchBankInfoDlg.initArea();
    DispatchBankInfoDlg.initGuarantyType();
    DispatchBankInfoDlg.initDispatchType();
    //初始化Step1的表单验证
    Bank.initValidator("dispatchForm", DispatchBankInfoDlg.validateFieldsStep1);
});



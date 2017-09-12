/**
 * Created by Maybebane on 2017/9/6.
 */
/**
 * 初始化派单详情对话框
 */
var dispatchComplete = {
    count: $("#itemSize").val(),
    dispatchData:{},
    itemTemplate: $("#itemTemplate").html(),
    validateFieldsStep1: {
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
dispatchComplete.clearData = function () {
    this.dispatchData = {};
};
/**
 * 关闭此对话框
 */
dispatchComplete.close = function () {
    parent.layer.close(window.parent.DispatchBank.layerIndex);
};

/**
 * item获取新的id
 */
dispatchComplete.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "guarantyItem" + this.count;
};

/**
 * 添加抵押物
 */
dispatchComplete.addItem = function () {
    $("#dispatchArea").append(this.itemTemplate);
    $("#guarantyItem").attr("id", this.newId());
    $("#guaranty").attr("id", "guaranty" + dispatchComplete.count);
    $("#progressBar").attr("id", "progressBar" + dispatchComplete.count);
    $("#BtnId").attr("id", "guaranty" + dispatchComplete.count+"BtnId");
    $("#PreId").attr("id", "guaranty" + dispatchComplete.count+"PreId");
    var guarantyUp = new $WebUpload("guaranty" + dispatchComplete.count);
    guarantyUp.setUploadBarId("progressBar" + dispatchComplete.count);
    guarantyUp.init();
};

/**
 * 删除抵押物
 */
dispatchComplete.deleteItem = function (event) {
    var obj = Bank.eventParseObject(event);
    obj.parents('.row').first().next('.hr-line-dashed').remove();
    obj.parents('.row').first().remove();
};

/**
 * 清除为空的抵押物 Dom
 */
dispatchComplete.clearNullDom = function(){
    $("[name='guarantyItem']").each(function(){
        var guaranty = $(this).find("[name='guaranty']").val();
        if(guaranty==''){
            $(this).remove();
        }
    });
};

/**
 * 收集添加抵押物的数据
 */
dispatchComplete.collectData = function () {
    this.clearNullDom();
    var mutiString = "";
    var guaranties=new Array();
    $("[name='guarantyItem']").each(function(){
        var guaranty = $(this).find("[name='guaranty']").val();
        guaranties.push({fileId:guaranty});
    });
    console.info(guaranties)
    this.dispatchData={guarantyFormList:guaranties};
};

/**
 * 提交添加派单
 */
dispatchComplete.addSubmit = function () {
    //清除缓存
    this.clearData();
    //收集表单数据
    this.collectData();
    console.info(this.dispatchData)
    //提交信息
    var ajax = new $ax(Bank.api.dispatch_update, function (data) {
        Bank.success("成功!");
        window.parent.DispatchAssess.table.refresh();
        DispatchAssess.close();
    }, function (data) {
        Bank.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.set("Status","3")
    ajax.set("dispatchId",Bank.getQueryString("id"))
    ajax.set("fileId",$("#guaranty1").val())
    ajax.start();
};


$(function () {
    dispatchComplete.addItem();
    //初始化Step1的表单验证
    Bank.initValidator("dispatchForm", dispatchComplete.validateFieldsStep1);
});
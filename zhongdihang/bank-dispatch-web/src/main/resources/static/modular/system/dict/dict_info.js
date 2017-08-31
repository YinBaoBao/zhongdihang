/**
 * 初始化字典详情对话框
 */
var DictInfoDlg = {
    count: $("#itemSize").val(),
    dictCode:'',
    dictName: '',			//字典的名称
    mutiString: '',		//拼接字符串内容(拼接字典条目)
    itemTemplate: $("#itemTemplate").html()
};

/**
 * item获取新的id
 */
DictInfoDlg.newId = function () {
    if(this.count == undefined){
        this.count = 0;
    }
    this.count = this.count + 1;
    return "dictItem" + this.count;
};

/**
 * 关闭此对话框
 */
DictInfoDlg.close = function () {
    parent.layer.close(window.parent.Dict.layerIndex);
};

/**
 * 添加条目
 */
DictInfoDlg.addItem = function () {
    $("#itemsArea").append(this.itemTemplate);
    $("#dictItem").attr("id", this.newId());
};

/**
 * 删除item
 */
DictInfoDlg.deleteItem = function (event) {
    var obj = Bank.eventParseObject(event);
    obj.parent().parent().remove();
};

/**
 * 清除为空的item Dom
 */
DictInfoDlg.clearNullDom = function(){
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
DictInfoDlg.collectData = function () {
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
DictInfoDlg.addSubmit = function () {
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
DictInfoDlg.init=function(){
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
},
/**
 * 提交修改
 */
DictInfoDlg.editSubmit = function () {
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

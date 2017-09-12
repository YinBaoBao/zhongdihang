/**
 * 系统管理--用户管理的单例对象
 */
var Banks = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
Banks.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'Id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '机构名称', field: 'name', align: 'center', valign: 'middle', sortable: false},
        {title: '地址', field: 'address', align: 'center', valign: 'middle', sortable: true},
        {title: '区域', field: 'area', align: 'center', valign: 'middle', sortable: true},
        {title: '负责人', field: 'linkMen', align: 'center', valign: 'middle', sortable: true},
        {title: '手机号码', field: 'telephone', align: 'center', valign: 'middle', sortable: false},
        {title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true}
        ];
    return columns;
};

/**
 * 检查是否选中
 */
Banks.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Banks.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加银行
 */
Banks.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加银行',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        offset: '8px',
        content: Bank.htmlPage(Bank.page.bank_add)
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
Banks.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '银行修改',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.bank_edit,{id:this.seItem.id})
        });
        this.layerIndex = index;
    }
};


/**
 * 删除银行
 */
Banks.delBanks = function () {
    if (this.check()) {
        var operation = function(){
            var BankId = Banks.seItem.id;
            var ajax = new $ax(Bank.api.bank_delete, function () {
                Bank.success("删除成功!");
                Banks.table.refresh();
            }, function (data) {
                Bank.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("BankId", BankId);
            ajax.start();
        };
        Bank.confirm("是否删除该机构" + Banks.seItem.name + "?",operation);
    }
};

/**
 * 入围评估机构
 * @param
 */
Banks.assessAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '入围评估机构',
            area: ['400px', '500px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.bank_assessassign,{bankId:this.seItem.id,bankName:this.seItem.name})
        });
        this.layerIndex = index;
    }
};


/**
 * 搜索
 */
Banks.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    Banks.search();
}

Banks.search = function () {
    var queryData = {};
    queryData['bankName'] = $("#bankName").val();
    Banks.table.refresh({url: Bank.route(Bank.api.bank_list),query: queryData});
}

$(function () {
    var defaultColunms = Banks.initColumn();
    var table = new BSTable(Banks.id, Bank.api.bank_list, defaultColunms);
    table.setPaginationType("client");
    Banks.table = table.init();

});

/**
 * 字典管理初始化
 */
var Dispatch = {
    id: "DispatchTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dispatch.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',
            formatter: function(value, row, index){
            return value.toString();
        }},
        {title: '订单编号', field: 'dispatchNo', align: 'center', valign: 'middle', sortable: true},
        {title: '银行名称', field: 'bankName', align: 'center', valign: 'middle', sortable: true},
        {title: '机构名称', field: 'assessName', align: 'center', valign: 'middle', sortable: true},
        {title: '当前状态', field: 'status', align: 'center', valign: 'middle', sortable: true}
        /*{title: '抵押物', field: 'guarantyList', align: 'center', valign: 'middle', sortable: true}*/
    ]
};

/**
 * 检查是否选中
 */
Dispatch.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Dispatch.seItem = selected[0];
        return true;
    }
};

/**
 * 完成当前单据
 */
Dispatch.openAddDict = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '银行订单查询',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.dispatch_add,{id:this.seItem.id})
        });
        this.layerIndex = index;
    }
};

$(function () {
    var defaultColunms = Dispatch.initColumn();
    // var table =new BSTable("DispatchTable",Bank.api.dispatch_Assess,defaultColunms);
    // table.setPaginationType("client");
    // Dispatch.table = table.init();
});

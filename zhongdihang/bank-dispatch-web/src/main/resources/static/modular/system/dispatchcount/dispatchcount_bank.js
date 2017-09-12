/**
 * 角色管理的单例
 */
var Dispatchcount_bank = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
};


/**
 * 初始化表格的列
 */
Dispatchcount_bank.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '银行', field: 'bankName', align: 'center', valign: 'middle', sortable: true},
        {title: '客户', field: 'assessName', align: 'center', valign: 'middle', sortable: true},
        {title: '派单数', field: 'count', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

$(function () {
    var defaultColunms = Dispatchcount_bank.initColumn();
    var table = new BSTable(Dispatchcount_bank.id, Bank.api.assess_count, defaultColunms);
    table.setPaginationType("client");
    Dispatchcount_bank.table = table.init();

});
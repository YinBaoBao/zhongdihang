/**
 * Created by YINBAOBAO on 2017/9/7.
 */
/**
 * 角色管理的单例
 */
var Dispatchcount_manager = {
    id: "count_managerTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Dispatchcount_manager.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '经理', field: 'userName', align: 'center', valign: 'middle', sortable: true},
        {title: '银行名称', field: 'assessName', align: 'center', valign: 'middle', sortable: true},
        {title: '接单数', field: 'count', align: 'center', valign: 'middle', sortable: true}
    ];
    return columns;
};

$(function () {
    var defaultColunms = Dispatchcount_manager.initColumn();
    var table = new BSTable("count_managerTable", Bank.api.assessCount_user, defaultColunms);
    Dispatchcount_manager.table = table.init();
});

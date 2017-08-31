/**
 * 角色管理的单例
 */
var Menu = {
    id: "menuTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',width:'200px'},
        {title: '菜单名称', field: 'menuName', align: 'center', valign: 'middle', sortable: true,width:'17%'},
        {title: '上级菜单', field: 'pMenuName', align: 'center', valign: 'middle', sortable: true,width:'12%'},
        {title: '权限类型', field: 'menuType', align: 'center', valign: 'middle', sortable: true},
        {title: '请求地址', field: 'url', align: 'center', valign: 'middle', sortable: true,width:'25%'},
        {title: '排序', field: 'sortNum', align: 'center', valign: 'middle', sortable: true},
        {title: '授权标识', field: 'permission', align: 'center', valign: 'middle', sortable: true}]
    return columns;
};


/**
 * 检查是否选中
 */
Menu.check = function () {
    var selected = $('#' + this.id).bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Menu.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加菜单
 */
Menu.openAddMenu = function () {
    var index = layer.open({
        type: 2,
        title: '添加菜单',
        area: ['830px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.menu_add)
    });
    this.layerIndex = index;
};

/**
 * 点击修改
 */
Menu.openChangeMenu = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改菜单',
            area: ['800px', '480px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.menu_edit,{id:this.seItem.id})
        });
        this.layerIndex = index;
    }
};

/**
 * 删除
 */
Menu.delMenu = function () {
    if (this.check()) {
        var operation = function(){
            var ajax = new $ax(Bank.api.menu_delete, function (data) {
                Bank.success("删除成功!");
                Menu.table.refresh();
            }, function (data) {
                Bank.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", Menu.seItem.id);
            ajax.start();
        };

        Bank.confirm("是否刪除该菜单?", operation);
    }
};

/**
 * 搜索
 */
Menu.search = function () {
    var queryData = {};
    queryData['menuName'] = $("#menuName").val();
    queryData['level'] = $("#level").val();
    Menu.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Menu.initColumn();
    var table = new BSTreeTable(Menu.id, Bank.api.menu_list, defaultColunms);
    table.setExpandColumn(2);
    table.setIdField("id");
    table.setCodeField("id");
    table.setParentCodeField("pId");
    table.setExpandAll(true);
    table.init();
    Menu.table = table;
});

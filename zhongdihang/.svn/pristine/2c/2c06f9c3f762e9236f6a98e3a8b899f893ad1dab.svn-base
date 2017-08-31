/**
 * 角色管理的单例
 */
var Role = {
    id: "roleTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
Role.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'roleName', align: 'center', valign: 'middle', sortable: true},
        {title: '上级角色', field: 'pName', align: 'center', valign: 'middle', sortable: true},
        {title: '描述', field: 'description', align: 'center', valign: 'middle', sortable: true}
    ]
    return columns;
};


/**
 * 检查是否选中
 */
Role.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Role.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加角色
 */
Role.openAddRole = function () {
    var index = layer.open({
        type: 2,
        title: '添加角色',
        area: ['800px', '450px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.role_add)
    });
    this.layerIndex = index;
};

/**
 * 点击修改角色
 */
Role.openChangeRole = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改角色',
            area: ['800px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content:Bank.htmlPage(Bank.page.role_edit,{id:Role.seItem.id})
        });
        this.layerIndex = index;
    }
};

/**
 * 删除角色
 */
Role.delRole = function () {
    if (this.check()) {

        var operation = function(){
            var ajax = new $ax(Bank.api.role_delete, function () {
                Bank.success("删除成功!");
                Role.table.refresh();
            }, function (data) {
                Bank.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", Role.seItem.id);
            ajax.start();
        };

        Bank.confirm("是否删除角色 " + Role.seItem.roleName + "?",operation);
    }
};

/**
 * 权限配置
 */
Role.assign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '权限配置',
            area: ['300px', '450px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.role_menuassign,{roleId:Role.seItem.id,roleName:Role.seItem.roleName})
        });
        this.layerIndex = index;
    }
};

/**
 * 搜索角色
 */
Role.search = function () {
    var queryData = {};
    queryData['roleName'] = $("#roleName").val();
    Role.table.refresh({query: queryData});
}

$(function () {
    var defaultColunms = Role.initColumn();
    var table = new BSTable(Role.id, Bank.route(Bank.api.role_list), defaultColunms);
    table.setPaginationType("client");
    table.init();
    Role.table = table;
});

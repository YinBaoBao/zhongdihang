/**
 * Created by YINBAOBAO on 2017/8/21.
 */
/**
 * 系统管理--用户管理的单例对象
 */
var Assess = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptid:0
};

/**
 * 初始化表格的列
 */
Assess.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '名称', field: 'name', align: 'center', valign: 'middle', sortable: true},
        {title: '联系人', field: 'linkMen', align: 'center', valign: 'middle', sortable: true},
        {title: '联系电话', field: 'telephone', align: 'center', valign: 'middle', sortable: true},
        {title: '地址', field: 'address', align: 'center', valign: 'middle', sortable: false},
        {title: '入围银行', field: 'banks', align: 'center', valign: 'middle', sortable: false},
        {title: '评估类型', field: 'guarantyType', align: 'center', valign: 'middle', sortable: false},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true,formatter: function (value, row, index) {
            return Bank.timeFormat(value)
        }}];
    return columns;
};

/**
 * 检查是否选中
 */
Assess.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Assess.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加评估机构
 */
Assess.openAddAssess = function () {
    var index = layer.open({
        type: 2,
        title: '添加评估机构',
        area: ['800px', '400px'], //宽高
        fix: false, //不固定
        maxmin: true,
        offset: '8px',
        content: Bank.htmlPage(Bank.page.assess_add)
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 评估机构id
 */
Assess.openChangeAssess = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '编辑评估机构',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.assess_edit,{id:this.seItem.id})
        });
        this.layerIndex = index;
    }
};

/**
 * 删除评估机构
 */
Assess.delAssess = function () {
    if (this.check()) {
        var operation = function(){
            var id = Assess.seItem.id;
            var ajax = new $ax(Bank.api.assess_delete, function () {
                Bank.success("删除成功!");
                Assess.table.refresh();
            }, function (data) {
                Bank.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", id);
            ajax.start();
        };
        Bank.confirm("是否删除评估机构" + Assess.seItem.name + "?",operation);
    }
};

// Assess.resetSearch = function () {
//     $("#name").val("");
//     $("#beginTime").val("");
//     $("#endTime").val("");
//     Assess.search();
// }

Assess.search = function () {
    var queryData = {};
    queryData['condition'] = $("#name").val();
    queryData['startDate'] = $("#beginTime").val();
    queryData['endDate'] = $("#endTime").val();
    Assess.table.refresh({url: Bank.route(Bank.api.assess_list),query: queryData});
}

// Assess.onClickDept = function (e, treeId, treeNode) {
//     Assess.deptid = treeNode.id;
//     Assess.search();
// };

$(function () {
    var defaultColunms = Assess.initColumn();
    var table = new BSTable("managerTable", Bank.api.assess_list, defaultColunms);
    Assess.table = table.init();
})

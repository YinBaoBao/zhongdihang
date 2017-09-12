/**
 * 字典管理初始化
 */
var DispatchAssess = {
    id: "DispatchAssessTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DispatchAssess.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',
            formatter: function(value, row, index){
                return value.toString();
            }},
        {title: '订单编号', field: 'dispatchNo', align: 'center', valign: 'middle', sortable: true},
        {title: '银行名称', field: 'bankName', align: 'center', valign: 'middle', sortable: true},
        {title: '机构名称', field: 'assessName', align: 'center', valign: 'middle', sortable: true},
        {title: '派单时间', field: 'createDate', align: 'center', valign: 'middle', sortable: true,
            formatter: function (value, row, index) {
                return Bank.timeFormat(value)
            }},
        {title: '评估时限', field: 'termDate', align: 'center', valign: 'middle', sortable: true},
        {title: '当前状态', field: 'status', align: 'center', valign: 'middle', sortable: true},
        {title: '评估类型', field: 'dispatchType', align: 'center', valign: 'middle', sortable: true}
    ]
};

/**
 * 检查是否选中
 */
DispatchAssess.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        DispatchAssess.seItem = selected[0];
        return true;
    }
};
//============================
//接单
//============================
DispatchAssess.openAddDispatch = function () {
    if (this.check()) {
        if (DispatchAssess.seItem.status=="待评估"){
            return Bank.error("该评估已接单,不可再次接单！");
        }
        if (DispatchAssess.seItem.status=="已完成"){
            return Bank.error("该评估已完成,不可再次接单！");
        }
        if (DispatchAssess.seItem.status=="已取消"){
            return Bank.error("该评估已取消,不可接单！");
        }
        var operation = function(){
            var dispatchId = DispatchAssess.seItem.id;
            var ajax = new $ax(Bank.api.dispatch_orders, function () {
                Bank.success("接单成功!");
                DispatchAssess.table.refresh();
            }, function (data) {
                Bank.error("接单失败!" + data.responseJSON.message + "!");
            });
            ajax.set("dispatchId", dispatchId);
            ajax.start();
        };
        Bank.confirm("是否确认派单" + DispatchAssess.seItem.assessName + "?",operation);
    }
};
//============================
//查看当前派单
//============================
DispatchAssess.openDispatchDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '派单详情',
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.dispatch_info,{id:DispatchAssess.seItem.id})
        });
        layer.full(index);
        this.layerIndex = index;
    }
};
//============================
//预评估完成
//============================
DispatchAssess.ycomplete = function () {
    if (this.check()) {
        if (DispatchAssess.seItem.dispatchType=="正式评估"){
            return Bank.error("该评估是正式评估！");
        }
        var index = layer.open({
            type: 2,
            title: '预评估上传',
            area: ['800px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.dispatch_complete,{id:DispatchAssess.seItem.id})
        });
        this.layerIndex = index;
    }
}
//============================
//正式评估完成
//============================
DispatchAssess.zcomplete= function () {
    if (this.check()) {
        if (DispatchAssess.seItem.dispatchType=="预评估"){
            return Bank.error("该评估是预评估！");
        }
        var operation = function(){
            var dispatchId = DispatchAssess.seItem.id;
            var ajax = new $ax(Bank.api.dispatch_UpdateZ, function () {
                Bank.success("成功!");
                DispatchAssess.table.refresh();
            }, function (data) {
                Bank.error("失败!" + data.responseJSON.message + "!");
            });
            ajax.set("dispatchId", dispatchId);
            ajax.start();
        };
        Bank.confirm("是否确定完成" + DispatchAssess.seItem.assessName + "?",operation);
    }
}



$(function () {
    var defaultColunms = DispatchAssess.initColumn();
    var table =new BSTable("DispatchAssessTable",Bank.api.dispatch_Assess,defaultColunms);
    DispatchAssess.table = table.init();
});

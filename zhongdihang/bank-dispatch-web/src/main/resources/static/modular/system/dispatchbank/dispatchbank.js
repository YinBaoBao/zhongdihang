/**
 * 字典管理初始化
 */
var DispatchBank = {
    id: "DispatchBankTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
DispatchBank.initColumn = function () {
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
        {title: '评估类型', field: 'dispatchType', align: 'center', valign: 'middle', sortable: true},
        //{title: '操作', field: 'operate', align: 'center', valign: 'middle', sortable: false,formatter: DispatchBank.operateFormatter}
        /*{title: '抵押物', field: 'guarantyList', align: 'center', valign: 'middle', sortable: true}*/
    ]
};
//DispatchBank.operateFormatter=function (value, row, index) {
//    var btnArray=new Array();
//    if(row.status!="已取消"&&row.status!="已完成"){
//        btnArray.push( "<button type=\"button\" class=\"btn btn-primary button-margin\" onclick=\"DispatchBank.delete("+row.id+")\">\n" +
//            "               <i class=\"fa fa-remove\"></i>&nbsp;取消" +
//            "           </button>");
//    }
//    if(row.status!="已取消"&&row.dispatchType!="正式评估"){
//        btnArray.push( "<button type=\"button\" class=\"btn btn-primary button-margin\" onclick=\"DispatchBank.turnoOficial("+row.id+")\">\n" +
//            "               <i class=\"fa fa-cogs\"></i>&nbsp;正式评估" +
//            "           </button>");
//    }
//    return btnArray.join('');
//};
/**
 * 检查是否选中
 */
DispatchBank.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        DispatchBank.seItem = selected[0];
        return true;
    }
};

/**
 * 新增派单
 */
DispatchBank.openAddDispatch = function () {
    var index = layer.open({
        type: 2,
        title: '派单',
        area: ['800px', '550px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.dispatchBank_add)
    });
    this.layerIndex = index;
};

/**
 * 查看当前派单
 */
DispatchBank.openDispatchDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '派单详情',
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.dispatchBank_info,{id:DispatchBank.seItem.id})
        });
        layer.full(index);
        this.layerIndex = index;
    }
};
//============================
//取消订单
//============================
DispatchBank.delete = function () {
    if (this.check()) {
        if (DispatchBank.seItem.status=="已取消"){
           return Bank.error("该评估已取消不可重复取消！");
        }else if(DispatchBank.seItem.status=="已完成"){
           return Bank.error("该评估已完成不可取消！");
        }
        var operation = function(){
            var dispatchId = DispatchBank.seItem.id;
            var ajax = new $ax(Bank.api.dispatch_delete, function () {
                Bank.success("取消成功!");
                DispatchBank.table.refresh();
            }, function (data) {
                Bank.error("取消失败!" + data.responseJSON.message + "!");
            });
            ajax.set("dispatchId", dispatchId);
            ajax.start();
        };
        Bank.confirm("是否取消该派单" + DispatchBank.seItem.assessName + "?",operation);
    }
}
//============================
//转正式
//============================
DispatchBank.turnoOficial= function () {
    if (this.check()) {
        if (DispatchBank.seItem.dispatchType=="正式评估"){
            return Bank.error("该评估已经是正式评估！");
        }
        var operation = function(){
            var dispatchId = DispatchBank.seItem.id;
            var ajax = new $ax(Bank.api.dispatch_formal, function () {
                Bank.success("转正式评估成功!");
                DispatchBank.table.refresh();
            }, function (data) {
                Bank.error("转正式评估失败!" + data.responseJSON.message + "!");
            });
            ajax.set("dispatchId", dispatchId);
            ajax.start();
        };
        Bank.confirm("是否转为正式评估" + DispatchBank.seItem.assessName + "?",operation);
    }
}



$(function () {
    var defaultColunms = DispatchBank.initColumn();
    var table =new BSTable("DispatchBankTable",Bank.api.dispatch_Assess,defaultColunms);
    DispatchBank.table = table.init();
});

/**
 * Created by YINBAOBAO on 2017/9/7.
 */
/**
 * 系统管理--用户管理的单例对象
 */
var Dispatchcount = {
    id: "countTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptId:0,
    deptType:1
};

/**
 * 初始化表格的列
 */
Dispatchcount.initColumn = function () {
    var columns = [
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
    ];
    return columns;
};

/**
 * 检查是否选中
 */
Dispatchcount.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        Dispatchcount.seItem = selected[0];
        return true;
    }
};

/*
 * 点击跳转银行与客户页面
 * */
Dispatchcount.open_bank = function () {
    var index = layer.open({
        type: 2,
        title: '银行与客户',
        area: ['830px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.dispatchcount_bank)
    });
    this.layerIndex = index;
};

/*
 * 点击跳转经理与客户页面
 * */
Dispatchcount.open_manager = function () {
    var index = layer.open({
        type: 2,
        title: '经理与客户',
        area: ['830px', '480px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.dispatchcount_manager)
    });
    this.layerIndex = index;
};

Dispatchcount.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    Dispatchcount.search();
};

Dispatchcount.search = function () {
    var queryData = {};
    queryData['search'] = $("#search_").val();
    queryData['startDate'] = $("#beginTime").val();
    queryData['endDate'] = $("#endTime").val();
    Dispatchcount.table.refresh({url: Bank.route(Bank.api.bank_Search),query: queryData});
};

Dispatchcount.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    MgrUser.search();
}

Dispatchcount.openDispatchDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '详情',
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.dispatchBank_info,{id:Dispatchcount.seItem.id})
        });
        layer.full(index);
        this.layerIndex = index;
    }
}


Dispatchcount.activeinit = function () {
    var count;
    var ajax = new $ax(Bank.api.dispatch_Statistics, function (data) {
        count = data;
    }, function (data) {
        Bank.error("失败!" + data.responseJSON.message + "!");
    });
    ajax.start();
    var bankVm = new Vue
    ({
        el: '#active',
        data:
            {
                count: count
            }
    });

}

Dispatchcount.waitingCount = function () {
    var queryData = {};
    queryData['status'] = "1";
    Dispatchcount.table.refresh({url: Bank.route(Bank.api.dispatch_status),query: queryData});
}
Dispatchcount.waitingCount2 = function () {
    var queryData = {};
    queryData['status'] = "2";
    Dispatchcount.table.refresh({url: Bank.route(Bank.api.dispatch_status),query: queryData});
}
Dispatchcount.waitingCount3 = function () {
    var queryData = {};
    queryData['status'] = "3";
    Dispatchcount.table.refresh({url: Bank.route(Bank.api.dispatch_status),query: queryData});
}


$(function () {
    var defaultColunms = Dispatchcount.initColumn();
    var table = new BSTable("countTable", Bank.api.dispatch_Assess, defaultColunms);
    Dispatchcount.table = table.init();
    Dispatchcount.activeinit();
});





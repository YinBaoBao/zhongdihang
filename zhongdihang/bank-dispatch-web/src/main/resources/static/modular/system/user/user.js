/**
 * 系统管理--用户管理的单例对象
 */
var MgrUser = {
    id: "managerTable",//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    deptId:0,
    deptType:1
};

/**
 * 初始化表格的列
 */
MgrUser.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle',formatter: function(value, row, index){
            return value.toString();
        }},
        {title: '账号', field: 'userName', align: 'center', valign: 'middle', sortable: true},
        {title: '姓名', field: 'realName', align: 'center', valign: 'middle', sortable: true},
        {title: '性别', field: 'sex', align: 'center', valign: 'middle', sortable: true},
        {title: '角色', field: 'roles', align: 'center', valign: 'middle', sortable: false},
        {title: '部门', field: 'orgName', align: 'center', valign: 'middle', sortable: false},
        {title: '邮箱', field: 'eMail', align: 'center', valign: 'middle', sortable: true},
        {title: '电话', field: 'telephone', align: 'center', valign: 'middle', sortable: true},
        {title: '创建时间', field: 'createTime', align: 'center', valign: 'middle', sortable: true, formatter: function (value, row, index) {
            return Bank.timeFormat(value)
        }},
        {title: '状态', field: 'enable', align: 'center', valign: 'middle', sortable: true, sortName:'isEnable'}];
    return columns;
};

/**
 * 检查是否选中
 */
MgrUser.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Bank.info("请先选中表格中的某一记录！");
        return false;
    } else {
        MgrUser.seItem = selected[0];
        return true;
    }
};
/**
 * 点击添加管理员
 */
MgrUser.openAddMgr = function () {
    var index = layer.open({
        type: 2,
        title: '添加用户',
        area: ['800px', '560px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.user_add)
    });
    this.layerIndex = index;
};

/**
 * 点击修改按钮时
 * @param userId 管理员id
 */
MgrUser.openChangeUser = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '修改用户',
            area: ['800px', '460px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.user_edit,{id:this.seItem.id})
        });
        this.layerIndex = index;
    }
};

/**
 * 点击角色分配
 * @param
 */
MgrUser.roleAssign = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '角色分配',
            area: ['300px', '400px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Bank.htmlPage(Bank.page.user_roleassign,{userId:this.seItem.id,userName:this.seItem.userName})
        });
        this.layerIndex = index;
    }
};

/**
 * 删除用户
 */
MgrUser.delMgrUser = function () {
    if (this.check()) {
        var operation = function(){
            var id = MgrUser.seItem.id;
            var ajax = new $ax(Bank.api.user_delete, function () {
                Bank.success("删除成功!");
                MgrUser.table.refresh();
            }, function (data) {
                Bank.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("id", id);
            ajax.start();
        };

        Bank.confirm("是否删除用户" + MgrUser.seItem.realName + "?",operation);
    }
};

/**
 * 冻结用户账户
 * @param userId
 */
MgrUser.freezeAccount = function () {
    if (this.check()) {
        var id = this.seItem.id;
        var ajax = new $ax(Bank.api.user_disable, function (data) {
            Bank.success("冻结成功!");
            MgrUser.table.refresh();
        }, function (data) {
            Bank.error("冻结失败!" + data.responseJSON.message + "!");
        });
        ajax.set("id", id);
        ajax.start();
    }
};

/**
 * 解除冻结用户账户
 * @param userId
 */
MgrUser.unfreeze = function () {
    if (this.check()) {
        var id = this.seItem.id;
        var ajax = new $ax(Bank.api.user_enable, function (data) {
            Bank.success("解除冻结成功!");
            MgrUser.table.refresh();
        }, function (data) {
            Bank.error("解除冻结失败!");
        });
        ajax.set("id", id);
        ajax.start();
    }
}

/**
 * 重置密码
 */
MgrUser.resetPwd = function () {
    if (this.check()) {
        var userId = this.seItem.id;
        parent.layer.confirm('是否重置密码为111111？', {
            btn: ['确定', '取消'],
            shade: false //不显示遮罩
        }, function () {
            var ajax = new $ax(Bank.api.user_force_password, function (data) {
                Bank.success("重置密码成功!");
            }, function (data) {
                Bank.error("重置密码失败!");
            });
            ajax.set("id", userId);
            ajax.set("newPassword",111111);
            ajax.start();
        });
    }
};

MgrUser.resetSearch = function () {
    $("#name").val("");
    $("#beginTime").val("");
    $("#endTime").val("");
    MgrUser.search();
}

MgrUser.search = function () {
    var queryData = {};
    queryData['condition'] = $("#name").val();
    queryData['startDate'] = $("#beginTime").val();
    queryData['endDate'] = $("#endTime").val();
    MgrUser.table.refresh({url: Bank.route(Bank.api.user_list),query: queryData});
}


MgrUser.initDept=function (){
    var banks;
    var assesses;
    var ajaxBank = new $ax(Bank.api.bank_select, function (data) {
        banks=data;
    });
    ajaxBank.start();
    var ajaxAssess = new $ax(Bank.api.assess_select, function (data) {
        assesses=data;
    });
    ajaxAssess.start();
    var deptVm = new Vue({
        el: '#deptTree',
        data: {
            banks: banks,
            assesses:assesses
        },
        methods: {
            dept: function (type,id) {
                var queryData = {};
                queryData['deptId'] = id;
                queryData['deptType'] = type;
                MgrUser.table.refresh({url: Bank.route(Bank.api.user_dept),query:queryData});
            }
        }
    })
}

$(function () {
    var defaultColunms = MgrUser.initColumn();
    var table = new BSTable("managerTable", Bank.api.user_list, defaultColunms);
    MgrUser.table = table.init();
    MgrUser.initDept();
})

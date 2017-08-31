/**
 * 角色详情对话框（可用于添加和修改对话框）
 */
var RolInfoDlg = {
    roleInfoData: {},
    pNameZtree: null,
    validateFields: {
        roleName: {
            validators: {
                notEmpty: {
                    message: '角色名不能为空'
                }
            }
        },
        pId: {
            validators: {
                notEmpty: {
                    message: '上级角色不能为空'
                }
            }
        },
        pName: {
            validators: {
                notEmpty: {
                    message: '上级角色不能为空'
                }
            }
        },
        description: {
            validators: {
                notEmpty: {
                    message: '角色描述不能为空'
                }
            }
        },
        sortNum: {
            validators: {
                notEmpty: {
                    message: '排序不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
RolInfoDlg.clearData = function () {
    this.roleInfoData = {};
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.set = function (key, value) {
    this.roleInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
};

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
RolInfoDlg.get = function (key) {
    return $("#" + key).val();
};

/**
 * 关闭此对话框
 */
RolInfoDlg.close = function () {
    parent.layer.close(window.parent.Role.layerIndex);
};

/**
 * 点击父级角色input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
RolInfoDlg.onClickPName = function (e, treeId, treeNode) {
    $("#pName").val(RolInfoDlg.pNameZtree.getSelectedVal());
    $("#pId").val(treeNode.id);
};


/**
 * 显示父级菜单的树
 *
 * @returns
 */
RolInfoDlg.showPNameSelectTree = function () {
    Bank.showInputTree("pName", "pNameContent");
};

/**
 * 收集数据
 */
RolInfoDlg.collectData = function () {
    this.set('id').set('roleName').set('pId').set('description').set('sortNum');
};

/**
 * 验证数据是否为空
 */
RolInfoDlg.validate = function () {
    $('#roleInfoForm').data("bootstrapValidator").resetForm();
    $('#roleInfoForm').bootstrapValidator('validate');
    return $("#roleInfoForm").data('bootstrapValidator').isValid();
};
/**
 * 角色信息界面初始化
 */
RolInfoDlg.init=function(){
    var id=Bank.getQueryString("id");
    if(id) {
        var role;
        var ajax = new $ax(Bank.api.role_info, function (data) {
            role = data;
        }, function (data) {
            Bank.error("查询不到此角色记录！");
        });
        ajax.set("id", Bank.getQueryString("id"));
        ajax.start();
        var roleVm = new Vue({
            el: '#roleInfoForm',
            data: {
                role:role
            }
        });
        $("#pName").val(role.pName);
        $("#pId").val(role.pId);
    }
    var pNameTree = new $ZTree("pNameTree", Bank.route(Bank.api.role_tree));
    pNameTree.bindOnClick(RolInfoDlg.onClickPName);
    pNameTree.init();
    RolInfoDlg.pNameZtree = pNameTree;
},

/**
 * 提交添加角色
 */
RolInfoDlg.addSubmit = function () {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.role_add, function (data) {
        Bank.success("添加成功!");
        window.parent.Role.table.refresh();
        RolInfoDlg.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

/**
 * 提交修改
 */
RolInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.role_edit, function (data) {
        Bank.success("修改成功!");
        window.parent.Role.table.refresh();
        RolInfoDlg.close();
    }, function (data) {
        Bank.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.roleInfoData);
    ajax.start();
};

$(function () {
    RolInfoDlg.init();
    Bank.initValidator("roleInfoForm", RolInfoDlg.validateFields);
});

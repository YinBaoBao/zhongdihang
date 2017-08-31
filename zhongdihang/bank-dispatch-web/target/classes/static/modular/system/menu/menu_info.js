/**
 * 菜单详情对话框
 */
var MenuInfoDlg = {
    menuInfoData: {},
    pMenuNameTree: null,
    validateFields: {
        menuName: {
            validators: {
                notEmpty: {
                    message: '菜单名称不能为空'
                }
            }
        },
        pMenuName: {
            validators: {
                notEmpty: {
                    message: '父菜单不能为空'
                }
            }
        },
        menuType: {
            validators: {
                notEmpty: {
                    message: '请选择'
                }
            }
        },
        url: {
            validators: {
                notEmpty: {
                    message : '输入不能为空'
                }
            }
        },
        sortNum: {
            validators: {
                notEmpty: {
                    message : '输入不能为空'
                }
            }
        },
        permission: {
            validators: {
                notEmpty: {
                    message: '授权标识不能为空'
                }
            }
        },
        logo: {
            validators: {
                notEmpty: {
                    message : '输入不能为空'
                }
            }
        },
    }
};

/**
 * 清除数据
 */
MenuInfoDlg.clearData = function () {
    this.menuInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.set = function (key, value) {
    this.menuInfoData[key] = (typeof value == "undefined") ? $("#" + key).val() : value;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
MenuInfoDlg.get = function (key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
MenuInfoDlg.close = function () {
    parent.layer.close(window.parent.Menu.layerIndex);
}

/**
 * 收集数据
 */
MenuInfoDlg.collectData = function () {
    this.set('id').set('menuName').set('description').set('pId')
        .set('url').set('sortNum').set('menuType').set('method').set('permission',$('#permission').tagEditor('getTags')[0].tags);
}

/**
 * 验证数据是否为空
 */
MenuInfoDlg.validate = function () {
    $('#menuInfoForm').data("bootstrapValidator").resetForm();
    $('#menuInfoForm').bootstrapValidator('validate');
    return $("#menuInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加用户
 */
MenuInfoDlg.addSubmit = function () {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.menu_add, function (data) {
        Bank.success("添加成功!");
        window.parent.Menu.table.refresh();
        MenuInfoDlg.close();
    }, function (data) {
        Bank.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.menuInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
MenuInfoDlg.editSubmit = function () {
    this.clearData();
    this.collectData();
    if (!this.validate()) {
        return;
    }
    //提交信息
    var ajax = new $ax(Bank.api.menu_edit, function (data) {
        Bank.success("修改成功!");
        window.parent.Menu.table.refresh();
        MenuInfoDlg.close();
    }, function (data) {
        Bank.error('修改失败!' + data.responseJSON.message + "!");
    });
    ajax.set(this.menuInfoData);
    ajax.start();
}
/**
 * 点击父级编号input框时
 */
MenuInfoDlg.onClickMenu = function (e, treeId, treeNode) {
    $('#pMenuName').val(MenuInfoDlg.pMenuNameTree.getSelectedVal());
    $('#pId').val( treeNode.id);
};

/**
 * 显示父级菜单选择的树
 */
MenuInfoDlg.showPMenuSelectTree = function () {
    Bank.showInputTree('pMenuName', 'pMenuTreeDiv', 15, 34);
};
/**
 * 初始化权限类别下拉框
 */
MenuInfoDlg.initMenuType=function(){
    var data=selectData('menuType');
    Bank.initSelect('menuType',data);
};
/**
 * 初始化HTTP方法下拉框
 */
MenuInfoDlg.initMethod=function(){
    var data=selectData('method');
    Bank.initSelect('method',data);
};
MenuInfoDlg.init=function(){
    MenuInfoDlg.initMenuType();
    MenuInfoDlg.initMethod();
    var id=Bank.getQueryString('id');
    var menu;
    if(id) {
        var ajax = new $ax(Bank.api.menu_info, function (data) {
            menu = data;
        }, function (data) {
            Bank.error('查询不到此菜单记录！');
        });
        ajax.set('id', Bank.getQueryString('id'));
        ajax.start();
        var menuVm = new Vue({
            el: '#menuInfoForm',
            data: {
                menu:menu
            }
        });
        $('#pMenuName').val(menu.pMenuName);
        $('#pId').val(menu.pId);
        $('#menuType').val(menu.menuType);
        $('#method').val(menu.method);
    }
    if(menu!=null&&menu.permission!=null){
        $('#permission').tagEditor({
            initialTags: ['+menu.permission+'],
            delimiter: ',', /* comma */
            placeholder: 'Enter tags ...'
        });
    }else{
        $('#permission').tagEditor({
            initialTags: [],
            delimiter: ',', /* comma */
            placeholder: 'Enter tags ...'
        });
    }
    var pNameTree = new $ZTree('pMenuNameTree', Bank.route(Bank.api.menu_tree));
    pNameTree.bindOnClick(MenuInfoDlg.onClickMenu);
    pNameTree.init();
    MenuInfoDlg.pMenuNameTree = pNameTree;
};
$(function () {
    MenuInfoDlg.init();
    Bank.initValidator('menuInfoForm', MenuInfoDlg.validateFields);
});

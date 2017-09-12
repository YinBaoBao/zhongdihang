/**
 * Created by Maybebane on 2017/8/23.
 */

var Index={
    userData:{},
    layerIndex:-1,
    page: {
    },
    permission:{
    }
}
Index.initNav=function(){
    var ajax = new $ax(Bank.api.menu_nav, function (data) {
        Index.page=data.childMenu;
    }, function (data) {
        Bank.error("查询用户权限失败!");
    });
    ajax.start();
}
Index.initPermission=function(){
    var ajax = new $ax(Bank.api.menu_permission, function (data) {
        Index.permission=data;
    }, function (data) {
        Bank.error("查询用户权限失败!");
    });
    ajax.start();
}
Index.initUserInfo=function(){
    var ajax = new $ax(Bank.api.user_authInfo, function (data) {
        Index.userData=data;
    }, function (data) {
        Bank.error("查询用户信息失败!");
    });
    ajax.start();
}
Index.editUserInfo=function(){
    var index = layer.open({
        type: 2,
        title: '用户信息',
        area: ['800px', '470px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.user_info)
    });
    this.layerIndex = index;
}
Index.modifyPassword=function(){
    var index = layer.open({
        type: 2,
        title: '修改密码',
        area: ['400px', '350px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Bank.htmlPage(Bank.page.user_chpwd)
    });
    this.layerIndex = index;
}
Index.init=function(){
    Index.initNav();
    Index.initPermission();
    Index.initUserInfo();
    var navVm=new Vue({
        el: '#side-menu',
        data: {
            menus:Index.page,
            user:Index.userData
        },
    });
    $.cookie('bankPermission', Index.permission);
    $("#side-menu").metisMenu();
}
Index.loginOut=function(){
    var ajax = new $ax(Bank.api.login_out, function (data) {
        Bank.goto(Bank.page.login);
    }, function (data) {
        Bank.goto(Bank.page.login);
    });
    ajax.start();
}
$(function(){
    Index.init();
})


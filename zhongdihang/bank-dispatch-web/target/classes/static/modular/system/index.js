/**
 * Created by Maybebane on 2017/8/23.
 */

var UserAuth={
    userData:{},
    page: {
    },
    permission:{
    }
}
UserAuth.initNav=function(){
    var ajax = new $ax(Bank.api.menu_nav, function (data) {
        UserAuth.page=data.childMenu;
    }, function (data) {
        Bank.error("查询用户权限失败!");
    });
    ajax.start();
}
UserAuth.initPermission=function(){
    var ajax = new $ax(Bank.api.menu_permission, function (data) {
        UserAuth.permission=data;
    }, function (data) {
        Bank.error("查询用户权限失败!");
    });
    ajax.start();
}
UserAuth.initUserInfo=function(){
    var ajax = new $ax(Bank.api.user_authInfo, function (data) {
        UserAuth.userData=data;
    }, function (data) {
        Bank.error("查询用户信息失败!");
    });
    ajax.start();
}
UserAuth.init=function(){
    UserAuth.initNav();
    UserAuth.initPermission();
    UserAuth.initUserInfo();
    var navVm=new Vue({
        el: '#side-menu',
        data: {
            menus:UserAuth.page,
            user:UserAuth.userData
        },
    });
    $.cookie('bankPermission', UserAuth.permission);
    $("#side-menu").metisMenu();
}
UserAuth.loginOut=function(){

}
$(function(){
    UserAuth.init();
})


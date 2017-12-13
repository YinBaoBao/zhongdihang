<template>
  <div style="height:100%;">
    <div class="context">
      <vheader @account="_account"></vheader>
      <div class="menu">
        <el-row class="tac">
          <el-col :span="24">
            <el-menu :default-active="tabIndex" router class="el-menu-vertical-demo" theme="light">
              <el-menu-item index="/index/shouye">
                <img src="./img/home.png" alt="首页"
                     style="width: 24px;height: 24px;margin-top: -6px;margin-right: 10px;">
                <span>首页</span>
                <i class="el-icon-caret-right tab_icon"></i>
              </el-menu-item>
              <div class="apply">
                <img src="./img/apply.png" alt="抵押申报申请">
                <span>抵押申报申请</span>
              </div>
              <el-menu-item index="/index/application">
                <span style="padding-left: 36px;">申报申请</span>
                <i class="el-icon-caret-right tab_icon"></i>
              </el-menu-item>
              <el-menu-item index="/index/cancel">
                <span style="padding-left: 36px;">注销申请</span>
                <i class="el-icon-caret-right tab_icon"></i>
              </el-menu-item>
              <el-menu-item index="/index/search">
                <span style="padding-left: 36px;">申请状态查询</span>
                <i class="el-icon-caret-right tab_icon"></i>
              </el-menu-item>
              <!--<div class="apply">-->
                <!--<img src="./img/inquire.png" alt="批量查询">-->
                <!--<span>批量查询</span>-->
              <!--</div>-->
              <!--<el-menu-item index="/index/chafeng">-->
                <!--<span style="padding-left: 36px;">查封查询</span>-->
                <!--<i class="el-icon-caret-right tab_icon"></i>-->
              <!--</el-menu-item>-->
              <!--<el-menu-item index="/index/swdycx">-->
                <!--<span style="padding-left: 36px;">顺位抵押查询</span>-->
                <!--<i class="el-icon-caret-right tab_icon"></i>-->
              <!--</el-menu-item>-->
              <!--<el-menu-item index="/index/bdcdyztcx">-->
                <!--<span style="padding-left: 36px;">不动产单元状态查询</span>-->
                <!--<i class="el-icon-caret-right tab_icon"></i>-->
              <!--</el-menu-item>-->
              <div class="apply">
                <img src="./img/account.png" alt="账户管理">
                <span>账户管理</span>
              </div>
              <el-menu-item index="/index/account">
                <span style="padding-left: 36px;">账号管理</span>
                <i class="el-icon-caret-right tab_icon"></i>
              </el-menu-item>
              <el-menu-item index="/index/manage">
                <span style="padding-left: 36px;">管理分支机构</span>
                <i class="el-icon-caret-right tab_icon"></i>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </div>
      <div class="tables" ref="tables">
        <keep-alive>
          <router-view @acIndex="_acIndex"></router-view>
        </keep-alive>
      </div>
      <div class="footer">
        <ul>
          <li><span>@ 2017 中地行信息技术有限有公司</span></li>
          <li><span style="padding-left: 12px;">联系电话：0512-68258999</span></li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import header from './header/header.vue';

  export default {
    data() {
      return {
        Propose: [],  // 查看申请数据
        tabIndex: '/index/shouye'
      };
    },
    methods: {
      Tabs(message) {
        this.$router.push({path: '/index/' + message + ''});
      },
      _account() {  // 账户管理
        this.$router.push({path: '/index/account'});
      },
      _acIndex(index) {
        this.tabIndex = index;
      }
    },
    created() {
      this.$router.push({path: '/index/shouye'});
    },
    components: {
      vheader: header
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .context
    width: 100%
    height: 100%
    background: #f8f8f8
    min-width: 1360px
    overflow: auto
    .menu
      float: left
      width: 16%
      height: calc(100% - 102px)
      border-right: 1px solid #e2e2e2
      min-width: 200px
      margin-top: 2px
      background-color: #f6f6f6
      overflow: hidden
      .apply
        height: 46px
        padding: 0 20px
        line-height: 46px
        img
          width: 24px
          height: 24px
          float: left
          margin-top: 8px
          margin-right: 10px
        span
          color: #393939
      .el-menu
        background-color: rgba(30, 133, 131, 0)
      .el-menu-item
        height: 46px;
        line-height: 46px;
        color: #393939
        position: relative
        .tab_icon
          position: absolute
          right: 0px
          top: 17px
          color: rgba(30, 133, 131, 0)
      .el-menu-item:hover
        color: #148582
        background: #eef2f6
      .el-menu-item.is-active
        color: #148582
        background: #eef2f6
      .el-menu-item.is-active .tab_icon
        color: #148582
    .tables
      float: left
      width: 83.9%

  .footer
    width: 100%
    height: 40px
    line-height: 40px
    margin-top: 2px
    background-color: rgba(30, 133, 131, 0.9)
    text-align: center
    overflow: hidden
    clear: both
    li
      display: inline-block
    span
      display: inline-block
      padding: 3px
      color: #fff
      font-size: 14px
</style>

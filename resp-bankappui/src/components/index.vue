<template>
  <div style="height: 100%;">
    <vheader @account="_account"></vheader>
    <div class="context">
      <div class="menu">
        <el-row class="tac">
          <el-col :span="24">
            <el-menu :default-active="tabIndex"	 router  class="el-menu-vertical-demo" theme="light">
              <el-menu-item index="/index/shouye">
                <i class="el-icon-menu"></i>首页
                <i class="el-icon-caret-right" style="padding-left: 80px;"></i>
              </el-menu-item>
              <el-menu-item index="/index/application">
                <i class="el-icon-check"></i>申报申请
                <i class="el-icon-caret-right" style="padding-left: 52px;"></i>
              </el-menu-item>
              <el-menu-item index="/index/cancel">
                <i class="el-icon-close"></i>注销申请
                <i class="el-icon-caret-right" style="padding-left: 52px;"></i>
              </el-menu-item>
              <el-menu-item index="/index/search">
                <i class="el-icon-search"></i>申请状态查询
                <i class="el-icon-caret-right" style="padding-left: 22px;"></i>
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
    </div>
    <div class="footer">
      <ul>
        <li><span>技术支持：中地行信息技术有限公司</span></li>
        <li><span>联系电话：0512-68258999</span></li>
      </ul>
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
    min-width: 1354px
    overflow: hidden
    .menu
      float: left
      width: 14%
      height: 100%
      min-width: 200px
      margin-top: 2px
      background-color: rgba(30, 133, 131, 0.9)
      overflow: hidden
      .el-menu
        background-color: rgba(30, 133, 131, 0)
      .el-menu-item
        color: #fff
      .el-menu-item:hover
        color: #20a0ff
        background: #fff
      .el-menu-item.is-active
        color: #20a0ff
        background: #fff
    .tables
      float: left
      width: 85.2%
      height: 100%
      overflow: hidden

  .footer
    width: 100%
    height: 40px
    line-height: 40px
    margin-top: 2px
    background-color: rgba(30, 133, 131, 0.9)
    text-align: center
    overflow: hidden
    li
      display: inline-block
    span
      display: inline-block
      padding: 3px
      color: #fff
      font-size: 14px
</style>

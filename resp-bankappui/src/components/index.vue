<template>
  <div>
    <vheader @account="_account"></vheader>
    <div class="context">
      <div class="menu">
        <el-row class="tac">
          <el-col :span="24">
            <el-menu default-active="1" class="el-menu-vertical-demo">
              <el-menu-item index="1" @click="Tabs('application')">
                <i class="el-icon-check"></i>申报申请
                <i class="el-icon-caret-right" style="padding-left: 52px;"></i>
              </el-menu-item>
              <el-menu-item index="2" @click="Tabs('cancel')">
                <i class="el-icon-close"></i>注销申请
                <i class="el-icon-caret-right" style="padding-left: 52px;"></i>
              </el-menu-item>
              <el-menu-item index="3" @click="Tabs('search')">
                <i class="el-icon-search"></i>申报/注销查询
                <i class="el-icon-caret-right" style="padding-left: 20px;"></i>
              </el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </div>
      <div class="tables" ref="tables">
        <keep-alive>
          <router-view></router-view>
        </keep-alive>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import header from './header/header.vue';

  export default {
    data() {
      return {
        Propose: []  // 查看申请数据
      };
    },
    methods: {
      Tabs(message) {
        this.$router.push({path: '/index/' + message + ''});
      },
      _account() {  // 账户管理
        this.$router.push({path: '/index/account'});
      }
    },
    created() {
      this.$router.push({path: '/index/application'});
    },
    components: {
      vheader: header
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .context
    width: 100%;
    min-width: 1354px
    overflow: hidden
    padding-bottom: 50px
    .menu
      float: left
      width: 16%;
      height: 100%;
      min-width: 200px;
      background-color: rgb(238, 241, 246);
    .tables
      float: left
      width: 84%
      overflow: hidden
</style>

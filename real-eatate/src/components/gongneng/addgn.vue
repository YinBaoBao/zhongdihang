<template>
  <div class="add-gn">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/gongneng' }">功能管理</el-breadcrumb-item>
        <el-breadcrumb-item>管理功能(房产数量查询)</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary" round @click="_addgn">保存</el-button>
    </div>
    <div class="main">
      <div class="nav">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="_handlemenu">
          <el-menu-item index="查询依据">查询依据</el-menu-item>
          <el-menu-item index="显示组成内容">显示组成内容</el-menu-item>
        </el-menu>
      </div>
      <keep-alive>
        <router-view></router-view>
      </keep-alive>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        activeIndex: '查询依据'    // 导航条
      };
    },
    methods: {
      _addgn() {},
      _tjchange() {  // 查询条件多选
      },
      _handlemenu(key, keyPath) {  // 导航栏
        switch (key) {
          case '查询依据':
            this.$router.push({path: '/index/addgn/cxyj'});
            break;
          case '显示组成内容':
            this.$router.push({path: '/index/addgn/content'});
            break;
        }
      },
      _typechange() { // 登记信息类型多选
      }
    },
    created() {
      this.$router.push({path: '/index/addgn/cxyj'});
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .add-gn
    width: 100%
    height: 100%
    .header
      width: 100%
      height: 54px
      line-height: 54px
      .el-breadcrumb
        display: inline-block
        margin-left: 36px
        color: #33363f
        font-size: 16px
        .el-breadcrumb__inner
          color: #409EFF
        .el-breadcrumb__item:last-child .el-breadcrumb__inner
          color: #33363f
      .el-button
        float: right
        padding: 8px 20px
        margin-top: 10px
        margin-right: 36px
        font-size: 16px
        font-family: '微软雅黑'
    .main
      width: calc(100% - 54px)
      height: calc(100% - 110px)
      margin: auto
      background: #fff
      box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
      border-radius: 5px
      .nav
        width: 100%
        .el-menu-item
          height: 46px
          line-height: 46px
          padding: 0 36px
</style>

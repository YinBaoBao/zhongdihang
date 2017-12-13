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
      <div v-if="searchyj" class="search">
        <div class="tiaojie">
          <div class="title">
            <span class="text">查询条件</span>
          </div>
          <div class="selects">
            <el-checkbox-group v-model="tjckList" @change="_tjchange">
              <el-checkbox label="权利人姓名"></el-checkbox>
              <el-checkbox label="权利人证件号"></el-checkbox>
              <el-checkbox label="不动产号"></el-checkbox>
              <el-checkbox label="坐落"></el-checkbox>
              <el-checkbox label="上传授权委托书"></el-checkbox>
              <el-checkbox label="在线智能授权"></el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
        <div class="area">
          <div class="title">
            <span class="text">查询区域</span>
            <span class="areamanage">区域管理</span>
          </div>
          <div class="ckgrounp">
            <div class="ckarea">
              <p class="quyu">区域选择</p>
              <el-checkbox-group v-model="areackList" @change="_tjchange">
                <el-checkbox label="姑苏"></el-checkbox>
                <el-checkbox label="相城"></el-checkbox>
                <el-checkbox label="园区"></el-checkbox>
                <el-checkbox label="吴中"></el-checkbox>
                <el-checkbox label="新区"></el-checkbox>
                <el-checkbox label="吴江"></el-checkbox>
                <el-checkbox label="太仓"></el-checkbox>
                <el-checkbox label="昆山"></el-checkbox>
              </el-checkbox-group>
            </div>
            <div class="ckareafs">
              <p class="quyu">区域组合方式</p>
              <el-radio-group v-model="arearadio">
                <el-radio :label="1">可多选查询</el-radio>
                <el-radio :label="2">单选</el-radio>
              </el-radio-group>
            </div>
          </div>
        </div>
      </div>
      <div v-if="showcontent" class="show-content">
        <div class="tiaojie">
          <div class="title">
            <span class="text">登记信息类型</span>
          </div>
          <div class="selects">
            <el-checkbox-group v-model="typeckList" @change="_typechange">
              <el-checkbox label="仅工作日8:00 - 20:00可查"></el-checkbox>
              <el-checkbox label="不同条件仅查询一次/天"></el-checkbox>
              <el-checkbox label="历史可查"></el-checkbox>
            </el-checkbox-group>
          </div>
        </div>
        <div class="area">
          <div class="title">
            <span class="text">基本信息</span>
          </div>
          <div class="ckgrounp">
            <div class="ckarea">
              <p class="quyu">区域选择</p>
              <el-checkbox-group v-model="areackList" @change="_tjchange">
                <el-checkbox label="房产数量证明"></el-checkbox>
                <el-checkbox label="合法住所(指定区域是否有合法住所,是否大于等于54平的房产)"></el-checkbox>
                <el-checkbox label="基础信息(不动产证号,区域,房屋性质,房屋面积,权利人姓名,权利人证件号码)"></el-checkbox>
              </el-checkbox-group>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        activeIndex: '查询依据',    // 导航条
        tjckList: [],                // 条件多选
        areackList: [],              // 区域多选
        arearadio: 1,                // 区域组合方式单选
        searchyj: true,              // 查询依据显示隐藏
        showcontent: false,          // 组成内容显示隐藏
        typeckList: []                // 登记信息类型选
      };
    },
    methods: {
      _addgn() {},
      _tjchange() {  // 查询条件多选
      },
      _handlemenu(key, keyPath) {  // 导航栏
        switch (key) {
          case '查询依据':
            this.searchyj = true;
            this.showcontent = false;
            break;
          case '显示组成内容':
            this.searchyj = false;
            this.showcontent = true;
            break;
        }
      },
      _typechange() { // 登记信息类型多选
      }
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
      border-radius: 5px
      .nav
        width: 100%
        .el-menu-item
          height: 46px
          line-height: 46px
          padding: 0 36px
      .search
        .tiaojie
          width: 100%
          .title
            height: 40px
            line-height: 40px
            .text
              padding-left: 36px
              font-size: 14px
              color: #33363f
          .selects
            padding: 10px 36px
            .el-checkbox-group
              padding-bottom: 20px
              border-radius: 5px
              background: #f4f8f9
              .el-checkbox
                min-width: 200px
                margin-left: 30px
                margin-top: 20px
              .el-checkbox__label
                font-size: 16px
                color: #33363f
        .area
          width: 100%
          .title
            height: 40px
            line-height: 40px
            font-size: 14px
            .text
              padding-left: 36px
              margin-right: 4px
              color: #33363f
            .areamanage
              display: inline-block
              padding: 0 10px
              color: #b0b0b0
              cursor: pointer
          .ckgrounp
            padding: 10px 36px
            .quyu
              display: block
              padding-top: 30px
              padding-left: 30px
              font-size: 14px
            .ckarea
              display: inline-block
              width: 56%
              height: 160px
              border-radius: 5px
              background: #f4f8f9
              vertical-align: top
              .el-checkbox
                width: 80px
                margin-left: 30px
                margin-top: 26px
              .el-checkbox__label
                font-size: 16px
                color: #33363f
            .ckareafs
              float: right
              width: 40%
              height: 160px
              border-radius: 5px
              background: #f4f8f9
              vertical-align: top
              .el-radio-group
                width: 100%
                .el-radio
                  display: block
                  margin-left: 30px
                  margin-top: 26px
                  text-align: left
      .show-content
        .tiaojie
          width: 100%
          .title
            height: 40px
            line-height: 40px
            .text
              padding-left: 36px
              font-size: 14px
              color: #33363f
          .selects
            padding: 10px 36px
            .el-checkbox-group
              border-radius: 5px
              background: #f4f8f9
              padding-bottom: 20px
              .el-checkbox
                margin-left: 30px
                margin-top: 20px
              .el-checkbox__label
                font-size: 16px
                color: #33363f
        .area
          width: 100%
          .title
            height: 40px
            line-height: 40px
            font-size: 14px
            .text
              padding-left: 36px
              margin-right: 4px
              color: #33363f
          .ckgrounp
            padding: 10px 36px
            .quyu
              display: block
              padding-top: 30px
              padding-left: 30px
              font-size: 14px
            .ckarea
              display: inline-block
              width: 100%
              padding-bottom: 30px
              border-radius: 5px
              background: #f4f8f9
              vertical-align: top
              .el-checkbox
                width: 100%
                margin-left: 30px
                margin-top: 26px
              .el-checkbox__label
                font-size: 16px
                color: #33363f

</style>

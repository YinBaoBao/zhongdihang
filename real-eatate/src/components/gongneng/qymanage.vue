<template>
  <div class="qymanage">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{path: '/index/gongneng'}">功能管理</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: '/index/addgn/cxyj'}">管理功能(房产数量查询)</el-breadcrumb-item>
        <el-breadcrumb-item>区域管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <ul class="qy-content">
      <li v-if="QyData" v-for="item in QyData" :key="item.index" class="list-item">
        <span class="text">{{item.areaname}}</span>
        <i class="el-icon-circle-close item-icon"></i>
      </li>
    </ul>
    <div class="sub_save">
      <el-button type="primary" round>保存</el-button>
    </div>
    <div class="footer-message">
      <span class="text">*您可以增加或删除区域，填写新区域后点击空白处，即可继续新增区域，点击"保存"可将变化后的区域保存。</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        QyData: [
          {
            areaname: '姑苏'
          },
          {
            areaname: '相城'
          },
          {
            areaname: '园区'
          },
          {
            areaname: '吴中'
          },
          {
            areaname: '新区'
          },
          {
            areaname: '吴江'
          },
          {
            areaname: '太仓'
          },
          {
            areaname: '昆山'
          }
        ]
      }
    },
    methods: {}
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .qymanage
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    border-radius: 5px
    position: relative
    .header
      width: 100%
      height: 60px
      line-height: 60px
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
        padding: 8px 12px
        margin-top: 14px
        margin-right: 24px
        font-size: 16px
        font-family: '微软雅黑'
    .qy-content
      padding: 30px 36px
      .list-item
        display: inline-block
        height: 40px
        line-height: 40px
        margin-right: 80px
        margin-bottom: 24px
        position: relative
        .text
          display: inline-block
          padding: 0 40px
          border-radius: 20px
          background: #ECECEC
          color: #474951
        .item-icon
          position: absolute
          top: -6px
          right: 0px
          font-size: 18px
          cursor: pointer
        .el-icon-circle-close:before
          color: #fff
          background: #D9D9D9
          border-radius: 50%
    .sub_save
      position: relative
      .el-button
        position: absolute
        left: 410px
        top: 10px
        padding: 14px 60px
    .footer-message
      position: absolute
      left: 0px
      bottom: 0px
      width: 100%
      height: 40px
      line-height: 40px
      text-align: center
      .text
        font-size: 14px
        color: #bababa
</style>

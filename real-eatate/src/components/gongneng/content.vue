<template>
  <div class="show-content">
    <div class="cont">
      <div class="title">
        <span class="text">登记信息类型</span>
      </div>
      <div class="selects">
        <el-checkbox-group v-model="Djxxck" @change="_djxxchange">
          <el-checkbox label="房地产权"></el-checkbox>
          <el-checkbox label="土地所有权"></el-checkbox>
          <el-checkbox label="建设用地使用权"></el-checkbox>
          <el-checkbox label="海域使用权"></el-checkbox>
          <el-checkbox label="土地承包经营权、农用地的其他使用权"></el-checkbox>
          <el-checkbox label="林权"></el-checkbox>
          <el-checkbox label="其他相关权利(取水权)"></el-checkbox>
          <el-checkbox label="抵押权"></el-checkbox>
          <el-checkbox label="预告"></el-checkbox>
          <el-checkbox label="查封"></el-checkbox>
          <el-checkbox label="异议"></el-checkbox>
        </el-checkbox-group>
      </div>
    </div>
    <div class="cont">
      <div class="title">
        <span class="text">基本信息</span>
      </div>
      <div class="selects">
        <el-checkbox-group v-model="Jbxxck" @change="_jbxxchange">
          <el-checkbox label="不动产单元号"></el-checkbox>
          <el-checkbox label="权证号"></el-checkbox>
          <el-checkbox label="坐落"></el-checkbox>
          <el-checkbox label="权利人姓名"></el-checkbox>
          <el-checkbox label="权利人证件号"></el-checkbox>
          <el-checkbox label="状态"></el-checkbox>
          <el-checkbox label="登簿时间"></el-checkbox>
          <el-checkbox label="不动产面积"></el-checkbox>
          <el-checkbox label="抵押数"></el-checkbox>
          <el-checkbox label="查封数"></el-checkbox>
          <el-checkbox label="行政区编码"></el-checkbox>
        </el-checkbox-group>
      </div>
    </div>
    <div class="cont">
      <div class="title">
        <span class="text">查询/显示限制</span>
      </div>
      <div class="selects">
        <el-checkbox-group v-model="Limitck" @change="_limitchange">
          <el-checkbox label="仅工作日8:00 - 20:00可查"></el-checkbox>
          <el-checkbox label="不同条件仅查询一次/天"></el-checkbox>
          <el-checkbox label="历史可查"></el-checkbox>
        </el-checkbox-group>
      </div>
    </div>
    <div class="area">
      <div class="title">
        <span class="text">统计信息</span>
      </div>
      <div class="ckgrounp">
        <div class="ckarea">
          <el-checkbox-group v-model="Tjck" @change="_tjchange">
            <el-checkbox label="房产数量证明"></el-checkbox>
            <br>
            <el-checkbox label="合法住所"></el-checkbox>
            <span class="text">(指定区域是否有合法住所,是否大于等于54平的房产)</span>
            <br>
            <el-checkbox label="基础信息"></el-checkbox>
            <span class="text">(不动产证号,区域,房屋性质,房屋面积,权利人姓名,权利人证件号码)</span>
          </el-checkbox-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        Djxxck: [],                    // 登记信息类型
        Jbxxck: [],                    // 基本信息
        Limitck: [],                   // 查询/显示限制
        Tjck: []                       // 统计信息
      };
    },
    methods: {
      _djxxchange() {   // 登记信息类型
      },
      _jbxxchange() {   // 基本信息
      },
      _limitchange() {   // 查询/显示限制
      },
      _tjchange() {      // 统计信息
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .show-content
    height: calc(100% - 50px)
    overflow: auto
    .cont
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
            margin-left: 30px
            margin-top: 26px
          .el-checkbox__label
            font-size: 16px
            color: #33363f
          .text
            padding-left: 8px
            font-size: 16px
            color: #A9ADAE

</style>

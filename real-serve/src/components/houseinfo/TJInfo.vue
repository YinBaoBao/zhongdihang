<template>
  <div class="tjinfo">
    <div class="tj_title">
      <span class="tj_text">统计信息-合法住所</span>
      <el-table :data="HfzsData">
        <el-table-column prop="name" label="区域" width="180"></el-table-column>
        <el-table-column prop="name" label="是否有合法固定住所"></el-table-column>
        <el-table-column prop="name" label="是否有≥54平的房产"></el-table-column>
      </el-table>
    </div>
    <div class="tj_title">
      <span class="tj_text">统计信息-基础信息</span>
      <el-table :data="BaseData">
        <el-table-column prop="name" label="区域" width="180"></el-table-column>
        <el-table-column prop="name" label="房屋性质"></el-table-column>
        <el-table-column prop="name" label="房屋面积"></el-table-column>
        <el-table-column prop="name" label="权利人姓名"></el-table-column>
        <el-table-column prop="name" label="权利人证件号"></el-table-column>
      </el-table>
    </div>
    <div class="tj_title">
      <span class="tj_text">统计信息-房产数量证明</span>
      <div class="fczm">
        <ul class="fc_list">
          <li class="list_item">
            <span class="fc_text" style="text-indent:20px;line-height: 24px;">
              经查询苏州市区不动产登记信息系统,截止到2017年09月22日 10:56:34,在中国江苏
              省苏州市市区(姑苏区、高新区、相城区、吴中区、园区)行政区内,查找到登记在xx
              (身份证号码xxxxxxxx)名下有5套住宅类不动产登记信息。
            </span>
          </li>
          <li class="list_item" style="text-align: right;">
            <span class="fc_text">被查询人签字:</span><span class="fc_text fc_qz">xxx</span>
          </li>
          <li class="list_item" style="text-align: right;">
            <span class="fc_text">xxxx 年 xx 月 xx</span>
          </li>
        </ul>
        <ul class="bz_list">
          <li class="list_item"><h2>备注:</h2></li>
          <li class="list_item">
            <span class="bz_text">1、本查询结果仅证明以上身份证号码在该查询时间点的不动产登记信息。</span>
          </li>
          <li class="list_item">
            <span class="bz_text">2、若本证明内容与被查询人申报情况不一致,以不动产登记薄为准。被查询人可到不动产登记机构进一步查询核实。</span>
          </li>
          <li class="list_item">
            <span class="bz_text">3、不动产登记机构拥有最终解释权。</span>
          </li>
          <li class="list_item" style="text-align: right;">
            <span class="bz_text">经办人签字:</span><span class="bz_qz">xxx</span>
          </li>
          <li class="list_item" style="text-align: right;">
            <span class="bz_text" style="margin-right: 108px;">行政机关公章</span>
          </li>
          <li class="list_item" style="text-align: right;">
            <span class="bz_text">xxxx 年 xx 月 xx</span>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        HfzsData: [],
        BaseData: []
      };
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .tjinfo
    .tj_title
      width: 100%
      margin-bottom: 20px
      .tj_text
        display: inline-block
        padding: 0 10px 15px 10px
        font-size: 14px
      .fczm
        border: 1px solid #ccc
        padding: 15px 40px
        margin: 0 10px
        .fc_list
          margin-bottom: 20px
          .list_item
            padding-bottom: 6px
            .fc_text
              display: inline-block
              font-size: 14px
            .fc_qz
              display: inline-block
              padding: 0 6px
              margin: 0 6px
              min-width: 100px
              text-align: left
              border-bottom: 1px solid #181818
        .bz_list
          .list_item
            padding-bottom: 6px
            .bz_text
              display: inline-block
              padding-left: 15px
              font-size: 14px
            .bz_qz
              display: inline-block
              padding: 0 6px
              margin-left: 6px
              border-bottom: 1px solid #181818
              min-width: 100px
              text-align: left

</style>

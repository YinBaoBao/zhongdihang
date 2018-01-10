<template>
  <div class="xq">
    <!--<div class="back">-->
      <!--<span><i class="el-icon-d-arrow-left"></i>返回</span>-->
    <!--</div>-->
    <div v-if="InfoData" v-for="item in InfoData" :key="item.id" class="info-tabs">
      <div class="title">
        <span class="text">{{item.title}}</span>
      </div>
      <table class="tabs-cont" cellpadding="" cellspacing="0">
        <tr v-for="data in item.tabledata" :key="data.id">
          <td class="tb-style">{{data.name1}}</td>
          <td class="tb-text">{{data.value1}}</td>
          <td class="tb-style">{{data.name2}}</td>
          <td class="tb-text">{{data.value2}}</td>
          <td class="tb-style">{{data.name3}}</td>
          <td class="tb-text">{{data.value3}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        InfoData: [
          {
            title: '房地产权登记信息',
            tabledata: [
              {
                name1: '不动产单元号',
                value1: '',
                name2: '不动产权证书号',
                value2: '',
                name3: '不动产坐落',
                value3: ''
              },
              {
                name1: '房屋所有人',
                value1: '',
                name2: '证件种类',
                value2: '',
                name3: '证件号',
                value3: ''
              },
              {
                name1: '房地产交易价格(元)',
                value1: '',
                name2: '规划用途',
                value2: '',
                name3: '房屋性质',
                value3: ''
              },
              {
                name1: '房屋结构',
                value1: '',
                name2: '所在层/总层数',
                value2: '',
                name3: '建筑面积(平方米)',
                value3: ''
              },
              {
                name1: '专有建筑面积(平方米)',
                value1: '',
                name2: '分摊建筑面积',
                value2: '',
                name3: '竣工时间',
                value3: ''
              },
              {
                name1: '房屋共有情况',
                value1: '',
                name2: '登记类型',
                value2: '',
                name3: '登记时间',
                value3: ''
              }
            ]
          },
          {
            title: '建设用地使用权限登记信息',
            tabledata: [
              {
                name1: '不动产单元号',
                value1: '',
                name2: '不动产权证书号',
                value2: '',
                name3: '不动产坐落',
                value3: ''
              },
              {
                name1: '权利人',
                value1: '',
                name2: '证件种类',
                value2: '',
                name3: '证件号',
                value3: ''
              },
              {
                name1: '权利类型',
                value1: '',
                name2: '权利性质',
                value2: '',
                name3: '土地用途',
                value3: ''
              },
              {
                name1: '土地使用期限',
                value1: '2017年1月19日-2037年1月18日',
                name2: '独用土地面积(平方米)',
                value2: '',
                name3: '分摊土地面积(平方米)',
                value3: ''
              },
              {
                name1: '共有情况',
                value1: '',
                name2: '登记类新',
                value2: '',
                name3: '登记时间',
                value3: ''
              }
            ]
          }
        ]
      };
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .xq
    height: calc(100% - 80px)
    padding: 0 30px
    overflow: auto
    .info-tabs
      margin-top: 20px
      .title
        height: 40px
        line-height: 40px
        .text
          font-family: '微软雅黑'
          font-size: 16px
          font-weight: 600
          color: #33363f
      .tabs-cont
        border-top: 1px solid #ccc
        border-left: 1px solid #ccc
        width: 100%
        background: #fff
        table-layout: fixed
        font-size: 14px
        .tb-style
          background: #DBEDFF
          min-width: 160px
      .tabs-cont th, .tabs-cont td
        border-right: 1px solid #ccc
        border-bottom: 1px solid #ccc
        padding: 8px 8px
        color: #656565
</style>

<template>
  <div class="houseinfo">
    <div class="info-header">
      <ul class="list">
        <li class="list-item">
          <span class="title">查询区域</span>
          <el-checkbox-group v-model="Areacheck">
            <el-checkbox label="姑苏"></el-checkbox>
            <el-checkbox label="相城"></el-checkbox>
            <el-checkbox label="园区"></el-checkbox>
            <el-checkbox label="吴中"></el-checkbox>
            <el-checkbox label="新区"></el-checkbox>
          </el-checkbox-group>
        </li>
        <li class="list-item">
          <span class="title">查询条件</span>
          <el-input v-model="powername" placeholder="请输入权利人姓名" style="margin-right: 15px;"></el-input>
          <el-input v-model="powerzjh" placeholder="请输入权利人证件号"></el-input>
        </li>
        <li class="list-item">
          <span class="title">查看历史</span>
          <el-checkbox v-model="checkhistory"></el-checkbox>
        </li>
        <li class="list-item">
          <span class="title">授权方式</span>
          <span class="state">暂未获取授权方式</span>
          <el-button>上传授权委托书</el-button>
        </li>
      </ul>
      <div class="show">
        <img class="imgs" src="./test.png" alt="">
      </div>
      <el-button class="seartch" type="primary">查询</el-button>
    </div>
    <div class="info-table">
      <div class="title">
        <span class="text">查询结果</span>
      </div>
      <el-table :data="InfoData">
        <el-table-column prop="index" label="序号" width="80"></el-table-column>
        <el-table-column prop="lx" label="登记信息类型"></el-table-column>
        <el-table-column prop="qzh" label="权证号"></el-table-column>
        <el-table-column prop="zl" label="坐落"></el-table-column>
        <el-table-column prop="qlr" label="权利人"></el-table-column>
        <el-table-column prop="dys" label="抵押数"></el-table-column>
        <el-table-column prop="cfs" label="查封数"></el-table-column>
        <el-table-column prop="xzq" label="行政区"></el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button @click="_editinfo(scope.row)" type="text"><i class="el-icon-edit-outline"></i></el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div class="info-tabs">
      <div class="title">
        <span class="text">房地产权登记信息</span>
      </div>
      <table class="tabs-cont" cellpadding="" cellspacing="0">
        <tr>
          <td class="tb-style" style="width:160px;">不动产单元号</td>
          <td></td>
          <td class="tb-style" style="width:160px;">不动产权证书号</td>
          <td></td>
          <td class="tb-style" style="width:160px;">不动产坐落</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">房屋所有人</td>
          <td></td>
          <td class="tb-style">证件种类</td>
          <td></td>
          <td class="tb-style">证件号</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">房地产交易价格(元)</td>
          <td></td>
          <td class="tb-style">规划用途</td>
          <td></td>
          <td class="tb-style">房屋性质</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">房屋结构</td>
          <td></td>
          <td class="tb-style">所在层/总层数</td>
          <td></td>
          <td class="tb-style">建筑面积(平方米)</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">专有建筑面积(平方米)</td>
          <td></td>
          <td class="tb-style">分摊建筑面积</td>
          <td></td>
          <td class="tb-style">竣工时间</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">房屋共有情况</td>
          <td></td>
          <td class="tb-style">登记类型</td>
          <td></td>
          <td class="tb-style">登记时间</td>
          <td></td>
        </tr>
      </table>
    </div>
    <div class="info-tabs">
      <div class="title">
        <span class="text">建设用地使用权限登记信息</span>
      </div>
      <table class="tabs-cont" cellpadding="" cellspacing="0">
        <tr>
          <td class="tb-style" style="width:160px;">不动产单元号</td>
          <td></td>
          <td class="tb-style" style="width:160px;">不动产权证书号</td>
          <td></td>
          <td class="tb-style" style="width:160px;">不动产坐落</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">权利人</td>
          <td></td>
          <td class="tb-style">证件种类</td>
          <td></td>
          <td class="tb-style">证件号</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">权利类型</td>
          <td></td>
          <td class="tb-style">权利性质</td>
          <td></td>
          <td class="tb-style">土地用途</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">土地使用期限</td>
          <td></td>
          <td class="tb-style">独用土地面积(平方米)</td>
          <td></td>
          <td class="tb-style">分摊土地面积(平方米)</td>
          <td></td>
        </tr>
        <tr>
          <td class="tb-style">共有情况</td>
          <td></td>
          <td class="tb-style">登记类型</td>
          <td></td>
          <td class="tb-style">登记时间</td>
          <td></td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        Areacheck: [],
        powername: '',  // 权利人姓名
        powerzjh: '',   // 权利人证件号
        checkhistory: false,  // 查看历史
        InfoData: [
          {
            index: '1',
            lx: '',
            qzh: '',
            zl: '',
            qlr: '',
            dys: '',
            cfs: '',
            xzq: ''
          }
        ]
      };
    },
    methods: {
      _editinfo(row) {
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .houseinfo
    height: calc(100% - 80px)
    padding: 0 30px
    overflow: auto
    .info-header
      padding-top: 20px
      position: relative
      .list
        display: inline-block
        vertical-align: top
        .list-item
          height: 48px
          line-height: 48px
          .title
            display: inline-block
            padding-right: 12px
            font-family: '微软雅黑'
            font-size: 14px
            font-weight: 500
            vertical-align: middle
          .state
            font-family: '微软雅黑'
            font-size: 14px
            color: #C3C3C3
            vertical-align: middle
          .el-checkbox-group
            display: inline-block
            .el-checkbox
              margin-right: 10px
          .el-input
            display: inline-block
            width: 180px
            .el-input__inner
              height: 36px
      .show
        display: inline-block
        vertical-align: top
        position: relative
        .imgs
          position: absolute
          left: 0px
          top: 30px
          width: 110px
          height: 160px
      .seartch
        position: absolute
        right: 300px
        bottom: 2px
        padding: 10px 26px
    .info-table
      width: 100%
      margin-top: 20px
      background: #fff
      box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
      border-radius: 5px
      .title
        height: 40px
        line-height: 40px
        .text
          padding-left: 10px
          font-family: '微软雅黑'
          font-size: 16px
          font-weight: 600px
          color: #33363f
      .el-table th, .el-table td
        font-family: '微软雅黑'
        color: #656565
      .el-table th
        background: #F4F8F9
        border: none
      .el-table td
        padding: 4px 0

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
        .tb-style
          background: #DBEDFF
      .tabs-cont th,.tabs-cont td
        border-right: 1px solid #ccc
        border-bottom: 1px solid #ccc
        padding: 10px 8px
        color: #656565
</style>

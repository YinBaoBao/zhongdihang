<template>
  <div class="housenum">
    <div class="num-header">
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
      </div>
      <el-button class="seartch" type="primary">查询</el-button>
    </div>
    <div class="num-table">
      <div class="title">
        <span class="text">查询结果</span>
      </div>
      <el-table :data="InfoData">
        <el-table-column prop="index" label="序号" width="80"></el-table-column>
        <el-table-column prop="db_time" label="登簿时间" width="180"></el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button @click="_editinfo(scope.row)" type="text"><i class="el-icon-edit-outline"></i></el-button>
          </template>
        </el-table-column>
      </el-table>
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
            db_time: ''
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
  .housenum
    height: calc(100% - 80px)
    padding: 0 30px
    overflow: auto
    .num-header
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
    .num-table
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

</style>

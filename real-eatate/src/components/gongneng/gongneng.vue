<template>
  <div class="Gn">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/gonneng' }">功能管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="Gn-table">
      <template>
        <el-table :data="GnData" ref="Gntable" style="width: 100%;height:100%;" max-height="600">
          <el-table-column prop="index" label="序号" width="150"></el-table-column>
          <el-table-column prop="date" label="用户账号" width="150"></el-table-column>
          <el-table-column prop="name" label="姓名" width="120"></el-table-column>
          <el-table-column prop="province" label="所属部门" width="120"></el-table-column>
          <el-table-column prop="city" label="角色" width="120"></el-table-column>
          <el-table-column prop="address" label="U盾密码" width="300"></el-table-column>
          <el-table-column prop="zip" label="创建时间" width="120"></el-table-column>
          <el-table-column prop="zip" label="状态" width="120"></el-table-column>
          <el-table-column label="操作" width="120">
            <template slot-scope="scope">
              <el-button @click.native.prevent="deleteRow(scope.$index, userData)"
                         type="text"><i class="el-icon-edit-outline"></i>
              </el-button>
              <el-button @click.native.prevent="deleteRow(scope.$index, userData)"
                         type="text"><i class="el-icon-delete"></i>
              </el-button>
            </template>
          </el-table-column>
          <div slot="append">
            <span class="app-index"></span>
            <span class="add-cont" @click="_ddGn">新增功能名称 <i class="el-icon-plus"></i></span>
          </div>
        </el-table>
      </template>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        GnData: [
          {
            date: '2016-05-01',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          },
          {
            date: '2016-05-08',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          },
          {
            date: '2016-05-06',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          },
          {
            date: '2016-05-07',
            name: '王小虎',
            province: '上海',
            city: '普陀区',
            address: '上海市普陀区金沙江路 1518 弄',
            zip: 200333
          }
        ]
      }
    },
    methods: {
      _ddGn() {
        this.$router.push({path: '/index/addgn'});
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      }
    },
    created() {
    },
    mounted() {
//      console.log(this.$refs.Gntable);
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .Gn
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    border-radius: 5px
    .header
      width: 100%
      height: 60px
      line-height: 60px
      .el-breadcrumb
        display: inline-block
        margin-left: 36px
        color: #33363f
        font-size: 16px
      .el-button
        float: right
        padding: 8px 12px
        margin-top: 14px
        margin-right: 24px
        font-size: 16px
        font-family: '微软雅黑'
    .Gn-table
      height: calc(100% - 60px)
      overflow: auto
      font-family: '微软雅黑'
      color: #656565
      .el-table th
        background: #f4f8f9
        font-size: 16px
      .el-table td
        padding: 6px 0
      .el-table__append-wrapper
        height: 36px
        line-height: 36px
        overflow: hidden
      .el-table__append-wrapper:hover
        background-color: #f5f7fa
      .app-index
        display: inline-block
        width: 100px
      .add-cont
        display: inline-block
        width: 120px
        cursor: pointer
</style>

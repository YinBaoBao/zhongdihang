<template>
  <div class="role">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/role' }">角色管理</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary" round @click="_addrole"><i class="el-icon-plus"></i> 新增角色</el-button>
    </div>
    <div class="role-table">
      <template>
        <el-table :data="roleData" style="width: 100%;height:100%;" height="460">
          <el-table-column prop="index" label="序号" width="60"></el-table-column>
          <el-table-column prop="name" label="角色名称"></el-table-column>
          <el-table-column prop="gn" label="功能"></el-table-column>
          <el-table-column prop="creat_time" label="创建时间"></el-table-column>
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
        roleData: [
          {
            index: 1,
            name: '公检法',
            gn: '基本信息查询',
            creat_time: '2017-12-20 10:50'
          }
        ]
      }
    },
    methods: {
      _addrole() {
        this.$router.push({path: '/index/addrole'});
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .role
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
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
    .role-table
      height: calc(100% - 60px)
      overflow: auto
      font-family: '微软雅黑'
      color: #656565
      .el-table th
        background: #f4f8f9
        font-size: 16px
      .el-table td
        padding: 4px 0
</style>

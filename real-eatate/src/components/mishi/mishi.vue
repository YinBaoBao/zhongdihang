<template>
  <div class="mishi">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/role' }">密匙管理</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary" round @click="_addmishi"><i class="el-icon-plus"></i> 新增U盾</el-button>
    </div>
    <div class="mishi-table">
      <template>
        <el-table :data="roleData" style="width: 100%;height:100%;" max-height="560">
          <el-table-column prop="index" label="序号" width="80"></el-table-column>
          <el-table-column prop="name" label="姓名" width="120"></el-table-column>
          <el-table-column prop="password" label="U盾密码" width=""></el-table-column>
          <el-table-column prop="creat_time" label="创建时间" width=""></el-table-column>
          <el-table-column label="操作" width="120">
            <template slot-scope="scope">
              <el-button @click.native.prevent="_EditRow(scope.$index, scope.row)"
                         type="text"><i class="el-icon-edit-outline"></i>
              </el-button>
              <el-button @click.native.prevent="_DeleteRow(scope.$index, scope.row)"
                         type="text"><i class="el-icon-delete"></i>
              </el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>
    <div class="pages">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="10"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
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
            index: '1',
            name: '王小虎',
            password: 'SZBCD0000001',
            creat_time: '2017-10-20 15:33'
          }
        ],
        currentPage: 2,
        total: 1000
      }
    },
    methods: {
      _addmishi() {  // 新增U盾
        this.$router.push({path: '/index/mishimanage'});
      },
      _EditRow() {    // 修改U盾
      },
      _DeleteRow(index, rows) {    // 删除U盾
        rows.splice(index, 1);
      },
      handleSizeChange(val) {    // 每页显示的条数
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {  // 当前第几页
        console.log(`当前页: ${val}`);
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .mishi
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    box-shadow: 0 0 10px rgba(69, 149, 255, 0.4);
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
    .mishi-table
      height: calc(100% - 120px)
      overflow: auto
      font-family: '微软雅黑'
      color: #656565
      .el-table th
        background: #f4f8f9
        font-size: 16px
    .pages
      height: 40px
      margin-top: 12px
      line-height: 40px
      text-align: center
      .el-pagination
        margin: 0 auto
</style>

<template>
  <div class="user">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/user' }">用户管理</el-breadcrumb-item>
      </el-breadcrumb>
      <el-button type="primary" round @click="_adduser"><i class="el-icon-plus"></i> 新增用户</el-button>
    </div>
    <div class="user-table">
      <template>
        <el-table :data="userData" style="width: 100%;height:100%;" max-height="600">
          <el-table-column prop="index" label="序号" width="60"></el-table-column>
          <el-table-column prop="account" label="用户账号"></el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="depart" label="所属部门"></el-table-column>
          <el-table-column prop="role" label="角色"></el-table-column>
          <el-table-column prop="password" label="U盾密码"></el-table-column>
          <el-table-column prop="creat_time" label="创建时间"></el-table-column>
          <el-table-column prop="state" label="状态"></el-table-column>
          <el-table-column label="操作" width="120">
            <template slot-scope="scope">
              <el-button @click.native.prevent="_edituser(scope.$index, scope.row)"
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
        userData: [
          {
            index: 1,
            account: 'GTB01',
            name: '王小虎',
            depart: '不动产登记中心信息处',
            role: '操作监督',
            password: 'SZBDC0000001',
            creat_time: '2017-12-20 10:40',
            state: '可用'
          }
        ]
      }
    },
    methods: {
      _adduser() {
        this.$store.commit('userTitle', '新增用户');
        this.$router.push({path: '/index/adduser'});
      },
      _edituser(index, row) {
        this.$store.commit('useredit', row);
        this.$store.commit('userTitle', '修改用户');
        this.$router.push({path: '/index/adduser'});
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .user
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
    .user-table
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

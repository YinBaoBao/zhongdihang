<template>
  <div class="add-role">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/role' }">角色管理</el-breadcrumb-item>
        <el-breadcrumb-item>新增角色</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="role-form">
      <el-form :model="RoleForm" :rules="addrule" ref="RoleForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="角色" prop="role">
          <el-input v-model="RoleForm.role" value="请输入角色"></el-input>
        </el-form-item>
        <el-form-item label="所属部门" prop="bumen">
          <el-select v-model="RoleForm.bumen" multiple placeholder="请选择所属部门">
            <el-option label="部门-1" value="shanghai"></el-option>
            <el-option label="部门-2" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <div class="form-submit">
          <el-button round @click="_Close()" style="padding: 14px 70px;">取 消</el-button>
          <el-button round type="primary" @click="_SaveForm('RoleForm')" style="padding: 14px 70px;margin-left: 40px;">
            保 存
          </el-button>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        RoleForm: {
          role: ''
        },
        addrule: {
          role: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 3, message: '长度最少 3 个字符', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      _Close() {   // 取消
        this.$router.push({path: '/index/role'});
      },
      _SaveForm(AddForm) {  // 保存
      },
      _Reader() {  // 读取U盾
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .add-role
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
    .role-form
      width: 40%
      height: calc(100% - 60px)
      min-width: 420px
      max-width: 600px
      margin: 0 auto
      margin-top: 20px
      .el-form-item__label
        font-size: 16px
        font-family: '微软雅黑'
        color: #33363f
      .el-select
        width: 100%
      .mima
        .el-input
          width: 70%
      .form-submit
        height: 60px
        margin-top: 160px
        text-align: right

</style>

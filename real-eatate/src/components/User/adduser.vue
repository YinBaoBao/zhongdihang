<template>
  <div class="add-user">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/user' }">用户管理</el-breadcrumb-item>
        <el-breadcrumb-item>新增用户</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="user-form">
      <el-form :model="AddForm" :rules="addrule" ref="AddForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="用户账号" prop="account">
          <el-input v-model="AddForm.account"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="AddForm.name"></el-input>
        </el-form-item>
        <el-form-item label="所属部门" prop="bumen">
          <el-select v-model="AddForm.bumen" placeholder="请选择所属部门">
            <el-option label="部门-1" value="shanghai"></el-option>
            <el-option label="部门-2" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="角色" prop="role">
          <el-select v-model="AddForm.role" placeholder="请选择角色">
            <el-option label="角色" value="shanghai"></el-option>
            <el-option label="角色" value="beijing"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="U盾密码" prop="mima" class="mima">
          <el-input v-model="AddForm.mima"></el-input>
          <el-button type="primary" @click="_Reader()" style="float: right;">读 取</el-button>
        </el-form-item>
        <el-form-item label="状态" prop="state">
          <el-radio-group v-model="AddForm.state">
            <el-radio label="可用"></el-radio>
            <el-radio label="禁用"></el-radio>
          </el-radio-group>
        </el-form-item>
        <div class="form-submit">
          <el-button round @click="_Close('ruleForm')" style="padding: 14px 70px;">取 消</el-button>
          <el-button round type="primary" @click="_SaveForm('AddForm')" style="padding: 14px 70px;margin-left: 40px;">
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
        AddForm: {
          account: '',
          name: '',
          bumen: '',
          role: '',
          mima: '',
          state: ''
        },
        addrule: {
          name: [
            {required: true, message: '请输入活动名称', trigger: 'blur'},
            {min: 3, message: '长度最少 3 个字符', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      _Close() {   // 取消
        this.$router.push({path: '/index/user'});
      },
      _SaveForm(AddForm) {  // 保存
      },
      _Reader() {  // 读取U盾
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .add-user
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
    .user-form
      width: 40%
      min-width: 420px
      max-width: 600px
      margin: 0 auto
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
        text-align: right

</style>

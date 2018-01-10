<template>
  <div class="departmanage">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/department' }">部门管理</el-breadcrumb-item>
        <el-breadcrumb-item>{{departTile}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="depart-form">
      <el-form :model="DepartForm" :rules="departrule" ref="DepartForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="部门名称" prop="departname">
          <el-input v-model="DepartForm.departname" value="" placeholder="请输入部门名称"></el-input>
        </el-form-item>
        <el-form-item label="部门用户" prop="departuser">
          <el-input type="textarea" :autosize="{ minRows: 2, maxRows: 6}" v-model="DepartForm.departuser"
                    placeholder="请输入部门用户">
          </el-input>
        </el-form-item>
        <div class="form-submit">
          <el-button round @click="_Close()" style="padding: 14px 70px;">取 消</el-button>
          <el-button round type="primary" @click="_SaveForm('RoleForm')" style="padding: 14px 70px;margin-left: 40px;">
            保 存
          </el-button>
        </div>
      </el-form>
    </div>
    <div class="depart-message">
      <span class="text">*您可以修改部门名称，查看部门所包括的用户。</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        departTile: '',
        DepartForm: {
          departname: '',
          departuser: ''
        },
        departrule: {
          departname: [
            {required: true, message: '请输入部门名称', trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      _Close() {   // 取消
        this.$router.push({path: '/index/department'});
      },
      _SaveForm(AddForm) {  // 保存
      },
      _Reader() {  // 读取U盾
      }
    },
    created() {
      let data = this.$store.state.departmanage;
      this.DepartForm.departname = data.depart;
      this.departTile = this.$store.state.departTitle;
      if (this.departTile === '新增部门') {
        this.DepartForm.departname = '';
        this.DepartForm.departuser = '';
      }
    },
    mounted() {
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .departmanage
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
    border-radius: 5px
    position: relative
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
    .depart-form
      width: 40%
      height: calc(100% - 80px)
      min-width: 420px
      max-width: 600px
      margin: 0 auto
      margin-top: 20px
      .el-form-item__label
        font-size: 16px
        font-family: '微软雅黑'
        color: #33363f
      .form-submit
        height: 60px
        margin-top: 160px
        text-align: right

    .depart-message
      position: absolute
      left: 0
      bottom: 50px
      width: 100%
      text-align: center
      .text
        font-size: 14px
        color: #C4C4C4
</style>

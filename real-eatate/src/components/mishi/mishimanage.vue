<template>
  <div class="mishimanage">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/mishi' }">密匙管理</el-breadcrumb-item>
        <el-breadcrumb-item>新增U盾</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mishi-form">
      <el-form :model="DepartForm" :rules="departrule" ref="DepartForm" class="demo-ruleForm">
        <el-form-item label="1.请将U盾插入USB接口后打开" prop="departname">
          <el-button type="primary">打开U盾</el-button>
        </el-form-item>
        <el-form-item label="2.然后点击获取密匙" prop="departuser">
          <el-input type="text" v-model="DepartForm.departuser" placeholder="点击右侧获取">
          </el-input>
          <el-button type="primary">获取</el-button>
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
      <span class="text">*以上操作需要在IE浏览器完成。</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
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
        this.$router.push({path: '/index/mishi'});
      },
      _SaveForm(AddForm) {  // 保存
      },
      _Reader() {  // 读取U盾
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .mishimanage
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
    .mishi-form
      width: 46%
      height: calc(100% - 80px)
      min-width: 420px
      max-width: 600px
      margin: 0 auto
      margin-top: 20px
      .el-form-item__label
        width: 240px
        font-size: 16px
        font-family: '微软雅黑'
        text-align: left
        color: #33363f
      .el-input
        width: 160px
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

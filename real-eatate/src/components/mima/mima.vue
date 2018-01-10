<template>
  <div class="mima">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item>修改密码</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="mima-form">
      <el-form :model="MimaForm" status-icon :rules="mimarule" ref="MimaForm" label-width="100px" class="demo-ruleForm">
        <el-form-item label="原密码" prop="oldpass">
          <el-input ref="Oldpass" type="password" v-model="MimaForm.oldpass" value="" placeholder="请输入原密码"></el-input>
        </el-form-item>
        <el-form-item label="新密码" prop="newpass">
          <el-input type="password" v-model="MimaForm.newpass" value="" placeholder="请输入新密码"
                    auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="repass">
          <el-input type="password" v-model="MimaForm.repass" value="" placeholder="请输入确认密码"
                    auto-complete="off"></el-input>
        </el-form-item>
        <div class="form-submit">
          <el-button round type="primary" @click="_SaveForm('MimaForm')" style="padding: 14px 70px;">
            保 存
          </el-button>
        </div>
      </el-form>
    </div>
    <div class="depart-message">
      <span class="text"></span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      let validateoldPass = (rule, value, callback) => {  // 动态验证
        if (!value) {
          return callback(new Error('请输入原密码'));
        }
        setTimeout(_ => {
          if (value.length < 6) {
            callback(new Error('密码至少6位数'));
          } else {
            callback();
          }
        }, 500);
      };
      let validatenewPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入新密码'));
        } else {
          if (value.length < 6) {
            callback(new Error('密码至少6位数'));
          }
          if (this.MimaForm.repass !== '') {
            this.$refs.MimaForm.validateField('repass');
          }
          callback();
        }
      };
      let validaterePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入确认密码'));
        } else if (value !== this.MimaForm.newpass) {
          callback(new Error('两次输入密码不一致!'));
        } else {
          callback();
        }
      };
      return {
        MimaForm: {
          oldpass: '',
          newpass: '',
          repass: ''
        },
        mimarule: {
          oldpass: [
            {validator: validateoldPass, trigger: 'blur,change'}
//            {required: true, message: '请输入原密码', trigger: 'blur'}
          ],
          newpass: [
            {validator: validatenewPass, trigger: 'blur'}
          ],
          repass: [
            {validator: validaterePass, trigger: 'blur'}
          ]
        }
      }
    },
    methods: {
      _SaveForm(AddForm) {  // 保存
      },
      _Reader() {  // 读取U盾
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .mima
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
    .mima-form
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
        margin-top: 120px
        text-align: center

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

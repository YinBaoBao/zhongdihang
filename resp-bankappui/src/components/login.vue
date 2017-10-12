<template>
  <div class="Login">
    <div class="title">
      <span>苏州不动产抵押申报、注销系统</span>
    </div>
    <div class="inputs">
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0" class="demo-ruleForm"
               style="width: 400px;margin: 0 auto;">
        <el-form-item prop="username">
          <el-input size="large" placeholder="请输入账号" v-model="ruleForm.username"
                    autofocus></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="large" type="password" placeholder="请输入密码" v-model="ruleForm.password"
          ></el-input>
        </el-form-item>
        <el-button type="danger" style="width: 400px;font-size: 16px;margin-top: 15px;" @click="submitForm('ruleForm')">
          登录
        </el-button>
      </el-form>
      <span class="forget" @click="forget">忘记密码</span>
    </div>
    <div class="support">
      <span>技术支持：中地行信息技术有限公司</span>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        username: '',
        password: '',
        token: '',
        ruleForm: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            {required: true, message: '请输入账户', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'change'}
          ]
        }
      };
    },
    computed: {},
    methods: {
      submitForm(ruleForm) {
        this.$refs[ruleForm].validate((valid) => {
          let admin = this.ruleForm.username;
          let password = this.ruleForm.password;
          if (valid) {
            this.$http.get(this.$store.state.Host + '/logControl/getToken', {
              params: {
                username: admin,
                password: password
              }
            }, {emulateJSON: true}).then((response) => {
              response = response.body;
              this.token = response.message;
              if (this.token) {
                this.$store.commit('newtoken', this.token);
                this.$store.commit('newname', admin);
                this.$message({
                  showClose: true,
                  message: '登录成功!',
                  type: 'success'
                });
                this.$router.push({path: '/index'});
                this.$http.post(this.$store.state.Host + '/TokrnControl/getToken', {
                  appid: '3644a684f98ea8fe223c713b77189a77',
                  secret: 200
                }).then((response) => {
                  response = response.body;
                  sessionStorage.setItem('login_token', response.body.access_token);
                });
              } else {
                this.$message.error('用户名或密码错误！');
                return false;
              }
            });
          } else {
            this.$message({
              showClose: true,
              message: '请输入用户名或密码',
              type: 'warning'
            });
            return false;
          }
        });
      },
      forget() {
        this.$message('请联系分行管理员重置密码');
      }

//      submitForm(formName) {
//        this.$refs[formName].validate((valid) => {
//          if (valid) {
//            alert('submit!');
//          } else {
//            console.log('error submit!!');
//            return false;
//          }
//        });
//      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .Login
    width: 100%
    height: 100%
    background: rgb(0, 105, 159)
    .title
      width: 100%
      text-align: center
      span
        display: inline-block
        padding: 15px 10px
        margin-top: 100px
        font-size: 36px
        font-weight: bolder
        color: #fff
    .inputs
      width: 100%
      text-align: center
      margin-top: 40px
      position: relative
      .forget
        position: absolute
        top: 112px
        right: 468px
        padding: 6px 8px
        font-size: 14px
        color: #fff
        cursor: pointer
      .forget:hover
        color: #F4F4F4
        font-weight: bolder
    .support
      position: absolute
      left: 0
      bottom: 15px
      width: 100%
      font-size: 14px
      text-align: center
      color: #fff
</style>

<template>
  <div class="Login">
    <div class="title">
      <img class="t_img" src="./img/login.png" alt="login">
      <span>中国农业银行 | 不动产信息金融专用平台</span>
    </div>
    <div class="inputs">
      <div class="user_title">
        <span>用户登录</span>
      </div>
      <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0" class="demo-ruleForm"
               style="margin: 0 auto;">
        <el-form-item prop="username">
          <el-input class="user_ipt" size="large" placeholder="请输入账号" v-model="ruleForm.username"
                    @keyup.enter.native="submitForm('ruleForm')" autofocus></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input class="pass_ipt" size="large" type="password" placeholder="请输入密码" v-model="ruleForm.password"
                    @keyup.enter.native="submitForm('ruleForm')"></el-input>
        </el-form-item>
        <span class="submit" @click="submitForm('ruleForm')">登 录</span>
      </el-form>
      <span class="forget" @click="forget">忘记密码</span>
    </div>
    <div class="support">
      <span>@ 2017 中地行信息技术有限有公司</span>
      <span style="padding-left: 16px;">联系电话：0512-68258999</span>
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
        },
        loginheight: document.documentElement.clientHeight
      };
    },
    methods: {
      submitForm(ruleForm) {
        this.$refs[ruleForm].validate((valid) => {
          let admin = this.ruleForm.username;
          let password = this.ruleForm.password;
          if (valid) {
            this.$http.post(this.$store.state.Host + '/logControl/getToken', {
              username: admin,
              password: password
            }).then((response) => {
              response = response.body;
//              console.log(response);
              if (response.code === 5000) {
                this.$message({
                  showClose: true,
                  message: response.message,
                  type: 'error'
                });
                return false;
              }
              if (response.body === null || response.body === '') {
                this.$message({
                  showClose: true,
                  message: response.message,
                  type: 'error'
                });
                return false;
              }
              if (response.status === '200') {
                if (response.body.Role.length === 0) {
                  this.$message.error('用户名或密码错误！');
                  return false;
                }
                this.$store.commit('newRole', response.body.Role);
                this.$store.commit('newbankUser', response.body.User);
                this.$store.commit('newtoken', response.body.Token);
                this.$store.commit('newname', admin);
                localStorage.setItem('username', admin);
                localStorage.setItem('userId', response.body.User.id);
                localStorage.setItem('bankId', response.body.bank.id);
                localStorage.setItem('bankinfo', JSON.stringify(response.body.bank));
                localStorage.setItem('description', response.body.Role[0].description);
                localStorage.setItem('headertoken', response.body.Token);
                this.$message({
                  showClose: true,
                  message: '登录成功!',
                  type: 'success'
                });
                this.$router.push({path: '/index'});
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
    },
    created() {
    },
    watch: {
      loginheight: () => {
        this.loginheight = document.documentElement.clientHeight;
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .Login
    width: 100%
    height: 100%
    background: url(img/bj.jpg) 0 0 no-repeat
    background-size: 100% 100%
    position: relative
    .title
      width: 100%
      text-align: left
      padding-top: 20px
      .t_img
        width: 60px
        height: 60px
        margin: 0 10px 0 15px
        vertical-align: middle
      span
        display: inline-block
        padding: 15px 10px
        vertical-align: middle
        font-size: 26px
        font-weight: bolder
        color: #fff
    .inputs
      position: absolute
      left: 50%
      top: 50%
      width: 22%
      height: 34%
      min-width: 380px
      min-height: 310px
      margin-left: -17%
      margin-top: -180px
      padding: 0 40px
      background: #fff
      border-radius: 5px
      text-align: center
      .user_title
        width: 100%
        height: 80px
        line-height: 80px
        span
          font-size: 24px
          color: #148583
          font-weight: 600
      .user_ipt
        .el-input__inner
          padding-left: 44px
          background: url('img/user.png') 12px 8px no-repeat
      .pass_ipt
        .el-input__inner
          padding-left: 44px
          background: url('img/password.png') 12px 8px no-repeat
      .submit
        display: inline-block
        border: 1px solid #148583
        width: 100%
        height: 42px
        border-radius: 4px
        line-height: 42px
        margin-top: 15px
        font-size: 16px
        background: #148583
        color: #fff
        font-weight: 600
        cursor: pointer
      .forget
        position: absolute
        top: 190px
        right: 34px
        padding: 6px 8px
        font-size: 14px
        color: #148583
        cursor: pointer
      .forget:hover
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

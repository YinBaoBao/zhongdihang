<template>
  <div class="login">
    <div class="nav">
      <img class="imgs" src="../assets/images/login.png" alt="">
      <span class="title">苏州不动产数据集成服务管监管平台</span>
    </div>
    <div class="content">
      <div class="title">
        <span class="text">用户登录</span>
      </div>
      <el-form :model="loginForm" :rules="loginrule" ref="loginForm" label-width="0" class="demo-ruleForm"
               style="margin: 0 auto;">
        <el-form-item prop="username">
          <span class="loginuser-icon"></span>
          <el-input size="large" placeholder="请输入账号" v-model.trim="loginForm.username"
                    @keyup.enter.native="submitForm('loginForm')" autofocus></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <span class="loginpass-icon"></span>
          <el-input size="large" type="password" placeholder="请输入密码" v-model.trim="loginForm.password"
                    @keyup.enter.native="submitForm('loginForm')"></el-input>
        </el-form-item>
        <div class="mima">
          <span style="display: inline-block;cursor: pointer;" @click="_savemima" onselectstart="return false">
            <i class="fontfamily my-icon-success_fill check_icon" :class="{'active': IsActive}"></i>
            <span class="text" onselectstart="return false">记住密码</span>
          </span>
        </div>
        <el-button type="primary" round @click="submitForm('loginForm')">登 录</el-button>
      </el-form>
      <div class="error-text">
        <span v-if="Iserror" class="text">{{errortext}}</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  // eslint-disable-next-line semi
  export default {
    data() {
      return {
        IsActive: false,
        Iserror: false,
        errortext: '*无效用户名，请重新输入。',
        loginForm: {
          username: '',
          password: ''
        },
        loginrule: {
          username: [
            {required: true, message: '请输入账号', trigger: 'change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'change'}
          ]
        }
      };
    },
    methods: {
      submitForm(loginForm) {
        this.$refs[loginForm].validate((valid) => {
//          let admin = this.loginForm.username;
//          let password = this.loginForm.password;
          if (valid) {
            this.$router.push({path: '/index'});
//            this.$http.post(this.$store.state.Host + '/logControl/getToken', {
//              username: admin,
//              password: password
//            }).then((response) => {
//              response = response.body;
//            });
          }
        });
      },
      _savemima() {  // 记住密码
        this.IsActive = !this.IsActive;
        let user = this.loginForm.username;
        let pass = this.loginForm.password;
        if (this.IsActive === true) {
          if (user !== '' && pass !== '') {
            this.setcookie('User', user, 7);
            this.setcookie('Pass', pass, 7);
          } else {
            this.$message({
              message: '请输用户或密码',
              type: 'warning'
            });
          }
        } else {
          this.setcookie('Pass', '', -1);
        }
      },
      setcookie(key, value, days) {  // 设置cookie
        let date = new Date();
        date.setDate(date.getDate() + days);
        document.cookie = key + '=' + escape(value) + ';exprise=' + date;
      },
      getcookie(key) {  // 获取cookoe
        let arr = document.cookie.split(';');
        for (let i = 0; i < arr.length; i++) {
          let arrkey = arr[i].split('=');
          if (arrkey[0].replace(/\s/g, '') === key) {
            return unescape(arrkey[1]);
          }
        }
        return '';
      },
      _Freshdata() {
      }
    },
    created() {
      if (this.getcookie('User') !== '') { // 获取cookie
        this.loginForm.username = this.getcookie('User');
        this.loginForm.password = this.getcookie('Pass');
      }
      this._Freshdata();
    },
    watch: {
      '$route': '_Freshdata'
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .login
    width: 100%
    height: 100%
    background: url("../assets/images/loginbg.png") 0 0 no-repeat
    background-size: 100% 100%
    position: relative
    .nav
      width: 100%
      height: 80px
      line-height: 80px
      .imgs
        width: 50px
        height: 50px
        padding-left: 20px
        vertical-align: middle
      .title
        padding-left: 15px
        font-family: '微软雅黑'
        font-size: 24px
        color: #3d4b5e
        vertical-align: middle
    .content
      position: absolute
      left: 50%
      top: 50%
      width: 22%
      height: 34%
      min-width: 380px
      min-height: 320px
      margin-left: -17%
      margin-top: -180px
      padding: 0 40px
      background: url("../assets/images/denglubg.png") 0 0 no-repeat
      background-size: 100% 100%
      box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
      border-radius: 5px
      text-align: center
      .title
        width: 100%
        height: 60px
        line-height: 60px
        .text
          font-size: 24px
          color: #509fee
          font-weight: 600
      .el-form-item__content
        position: relative
      .el-input__inner
        height: 40px
        padding-left: 60px
        border-radius: 0
      .loginuser-icon,.loginpass-icon
        position: absolute
        left: 0px
        top: 0px
        z-index: 999
        display: inline-block
        width: 50px
        height: 40px
      .loginuser-icon
        background: #509fee url('../assets/images/loginuser.png') 14px 6px no-repeat
        background-size: 24px 24px
      .loginpass-icon
        background:#509fee url('../assets/images/loginpass.png') 14px 6px no-repeat
        background-size: 24px 24px
      .mima
        width: 100%
        height: 34px
        line-height: 34px
        text-align: left
        .check_icon
          padding: 0 10px 0 16px
          color: #9E9E9E
          &.active
            color: #509fee
        .text
          display: inline-block
          font-size: 14px
          vertical-align: middle
      .el-button
        width: calc(100% - 60px)
        margin-top: 15px
        background: #509fee
        border-color: #509fee
        font-size: 16px
        font-weight: 600
      .error-text
        width: 100%
        height: 40px
        line-height: 40px
        text-align: center
        .text
          font-size: 14px
          font-family: '微软雅黑'
          color: #ed4545
</style>

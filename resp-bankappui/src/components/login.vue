<template>
  <div class="Login" :style="{height: loginheight + 'px'}">
    <div class="title">
      <span>苏州不动产抵押申报系统</span>
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
            this.$http.get(this.$store.state.Host + '/logControl/getToken', {
              params: {
                username: admin,
                password: password
              }
            }, {emulateJSON: true}).then((response) => {
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
                this.$store.commit('newRole', response.body.Role);
                this.$store.commit('newBank', response.body.bank);
                this.$store.commit('newbankUser', response.body.User);
                this.$store.commit('headertoken', response.body.Token);
                this.$store.commit('newname', admin);
                localStorage.setItem('username', admin);
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
    background: rgba(9, 72, 105, 1)
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
      width: 500px
      text-align: center
      margin: 40px auto
      position: relative
      .forget
        position: absolute
        top: 112px
        right: 40px
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

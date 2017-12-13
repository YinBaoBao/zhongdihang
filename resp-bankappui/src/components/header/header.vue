<template>
  <nav class="nav">
    <div class="login">
      <div class="avatar">
        <img src="./login.png" alt="login">
      </div>
      <span class="bank">中国农业银行 | 不动产数据共享金融专用平台</span>
    </div>
    <ul class="manager">
      <li>
        <span>{{Bankname}}</span>
      </li>
      <li>
        <span>{{userName}}</span>
      </li>
      <li class="account" @click="_account">账户管理</li>
      |
      <!--<li class="pass" @click="_edit_password">修改密码</li>-->
      <!--|-->
      <li class="back" @click="_go_back">退出</li>
    </ul>
  </nav>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        Bankname: '中国农业银行股份有限公司苏州分行',
        userName: 'admin'
      };
    },
    methods: {
      _account() {
        this.$emit('account');
      },
      _edit_password() {
        this.$message({
          showClose: true,
          message: '请联系管理员重置密码 !'
        });
      },
      _go_back() {
        this.$router.push({path: '/login'});
      }
    },
    created() {
      this.userName = localStorage.getItem('username');
      this.Bankname = this.$store.state.Bankinfo.bankName;
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .nav
    width: 100%
    height: 60px;
    background-color: rgb(30, 133, 131);
    .login
      float: left
      width: 42%
      height: 60px
      min-width: 600px
      line-height: 60px
      overflow: hidden
      .avatar
        float: left
        width: 40px
        height: 40px
        margin: 8px 10px 0 20px
        img
          width: 100%
          height: 100%
      .bank
        font-size: 22px
        color: #fff
    .manager
      float: right
      height: 60px
      padding: 0 15px
      background-color: rgb(30, 133, 131);
      line-height: 80px
      font-size: 14px
      color: #fff
      overflow: hidden
      li
        display: inline-block
        padding: 0 3px
        line-height: 26px
        cursor: pointer
</style>

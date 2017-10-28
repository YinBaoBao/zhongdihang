<template>
  <div>
    <nav class="nav">
      <div class="login">
        <div class="avatar">
          <img src="./nonghang.jpg" alt="login">
        </div>
        <span class="bank">中国农业银行抵押申报系统</span>
      </div>
      <div class="content">
        <div class="manager">
          <ul>
            <li class="account" @click="_account">账户管理</li>
            |
            <li class="pass" @click="_edit_password">修改密码</li>
            |
            <li class="back" @click="_go_back">退出</li>
          </ul>
        </div>
        <div class="user">
          <span class="company">{{Bankusename}}</span>
          <span class="userName">李建</span>
        </div>
      </div>
    </nav>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        Bankusename: '中国农业银行股份有限公司苏州分行'
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
      this.Bankusename = this.$store.state.Bankinfo.bankName;
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .nav
    width: 100%
    height: 60px;
    background-color: rgba(9, 102, 150, 1);
    .login
      float: left
      width: 36%
      min-width: 460px
      height: 60px
      line-height: 60px
      overflow: hidden
      .avatar
        float: left
        width: 45px
        height: 45px
        margin: 8px 10px 0 20px
        img
          width: 100%
          height: 100%
      .bank
        flaot: left
        height: 60px
        line-height: 60px
        font-size: 24px
        color: #fff
    .content
      float: left
      width: calc(100% - 36%)
      min-width: 500px
      height: 60px
      overflow: hidden;
      background-color: rgba(9, 102, 150, 1)
      .user
        float: right
        height: 60px
        line-height: 80px
        font-size: 14px
        color: #fff
        .userName
          padding: 0 6px
      .manager
        float: right
        height: 60px
        padding: 0 15px
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

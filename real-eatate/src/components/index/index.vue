<template>
  <div class="index">
    <div class="header">
      <div class="title">
        <img class="imglogo" src="./navlogo.png" alt="navlogo">
        <span class="text">苏州不动产数据集成服务管理后台</span>
      </div>
      <ul class="list">
        <li>
          <img class="avatar" src="./navuser.png" alt="navuser">
        </li>
        <li><span>欢迎,{{username}}</span></li>
        |
        <li><span @click="_editmima">修改密码</span></li>
        |
        <li><span @click="_goback">安全退出</span></li>
      </ul>
    </div>
    <div class="content">
      <div class="menu">
        <el-menu :default-active="Menuitem" router @select="_menuchange"
                 class="el-menu-vertical-demo">
          <el-menu-item index="/index/home">
            <img class="imgs" v-if="itemactive === 'home'" src="./home_blue.png" alt="home">
            <img class="imgs" v-else src="./home.png" alt="home">
            <span slot="title">首页</span>
          </el-menu-item>
          <el-menu-item index="/index/user">
            <img class="imgs" v-if="itemactive === 'user'" src="./user_blue.png" alt="user">
            <img class="imgs" v-else src="./user.png" alt="user">
            <span class="item-bg user-bg" :class="activemenu"></span>
            <span slot="title">用户管理</span>
          </el-menu-item>
          <el-menu-item index="/index/role">
            <img class="imgs" v-if="itemactive === 'role'" src="./role_blue.png" alt="role">
            <img class="imgs" v-else src="./role.png" alt="role">
            <span slot="title">角色管理</span>
          </el-menu-item>
          <el-menu-item index="/index/gongneng">
            <img class="imgs" v-if="itemactive === 'function'" src="./function_blue.png" alt="function">
            <img class="imgs" v-else src="./function.png" alt="function">
            <span slot="title">功能管理</span>
          </el-menu-item>
          <el-menu-item index="/index/department">
            <img class="imgs" v-if="itemactive === 'department'" src="./department_blue.png" alt="department">
            <img class="imgs" v-else src="./department.png" alt="department">
            <span slot="title">部门管理</span>
          </el-menu-item>
          <el-menu-item index="/index/mishi">
            <img class="imgs" v-if="itemactive === 'key'" src="./key_blue.png" alt="key">
            <img class="imgs" v-else src="./key.png" alt="key">
            <span slot="title">密匙管理</span>
          </el-menu-item>
          <el-menu-item index="/index/mima">
            <img class="imgs" v-if="itemactive === 'Password'" src="./Password_blue.png" alt="Password">
            <img class="imgs" v-else src="./Password.png" alt="Password">
            <span slot="title">修改密码</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="Tabs">
        <router-view></router-view>
        <p class="footer">技术支持: 苏州中地行信息技术有限公司</p>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        username: '李延亮',
        Menuitem: '/index/home',
        itemactive: 'home'
      }
    },
    computed: {
      activemenu() {
        return this.itemactive + '-bg';
      }
    },
    methods: {
      _editmima() {  // 修改密码
        this.$router.push({path: '/index/mima'});
      },
      _goback() { // 安全退出
        this.$router.push({path: '/login'});
      },
      _menuchange(val) {
        switch (val) {
          case '/index/home':
            this.itemactive = 'home';
            break;
          case '/index/user':
            this.itemactive = 'user';
            break;
          case '/index/role':
            this.itemactive = 'role';
            break;
          case '/index/gongneng':
            this.itemactive = 'function';
            break;
          case '/index/department':
            this.itemactive = 'department';
            break;
          case '/index/mishi':
            this.itemactive = 'key';
            break;
          case '/index/mima':
            this.itemactive = 'Password';
            break;
        }
      }
    },
    created() {
      this.$router.push({path: '/index/home'});
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .index
    width: 100%
    height: 100%
    .header
      width: 100%
      .title
        display: inline-block
        height: 60px
        line-height: 60px
        .imglogo
          width: 50px
          height: 50px
          margin: 0 8px 0 15px
          vertical-align: middle
        .text
          font-family: '微软雅黑'
          font-size: 24px
          color: #33363f
          vertical-align: middle
      .list
        float: right
        margin-right: 15px
        overflow: hidden
        li
          display: inline-block
          height: 60px
          line-height: 60px
          .avatar
            width: 34px
            height: 34px
            margin-top: -4px
            margin-right: 6px
            vertical-align: middle
          span
            padding: 0 3px
            font-size: 16px
            vertical-align: middle
            cursor: pointer
    .content
      width: 100%
      height: calc(100% - 60px)
      .menu
        float: left
        width: 240px
        overflow: hidden
        .imgs
          width: 20px
          height: 20px
          margin-left: 2px
          margin-top: -6px
          vertical-align: middle
      .Tabs
        float: left
        width: calc(100% - 240px)
        height: 100%
        background: rgb(244, 248, 249)
        position: relative
        .footer
          position: absolute
          left: 0px
          bottom: 20px
          width: 100%
          font-size: 14px
          font-weight: 600
          text-align: center
          color: #97a8bb
</style>

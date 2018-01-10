<template>
  <div class="index">
    <div class="header">
      <div class="title">
        <img class="imglogo" src="./navlogo.png" alt="navlogo">
        <span class="text">苏州不动产数据集成服务管理后台</span>
      </div>
      <ul class="in-list">
        <li class="inlist-item">
          <div class="fileinput">
            <img class="avatar" :src="dataUrl" alt="">
            <label for="file" class="lab_upload"></label>
            <input type="file" name="file" id="file" class="ipt_upload" title="" value="" ref="uploadImg"
                   @change="_upload">
          </div>
        </li>
        <li class="inlist-item"><span class="list-text">欢迎,{{username}}</span></li>
        <span style="vertical-align: middle;">|</span>
        <li class="inlist-item"><span class="list-text" @click="_editmima">修改密码</span></li>
        <span style="vertical-align: middle;">|</span>
        <li class="inlist-item"><span class="list-text" @click="_goback">安全退出</span></li>
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
    <transition name="pr_fade">
      <div v-if="showpercent" class="progress">
        <el-progress :text-inside="true" :stroke-width="14" :percentage="percent"></el-progress>
      </div>
    </transition>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        dataUrl: require('./navuser.png'),
        username: '李延亮',
        Menuitem: '/index/home',
        itemactive: 'home',
        percent: 0,
        showpercent: false
      }
    },
    computed: {
      activemenu() {
        return this.itemactive + '-bg';
      }
    },
    methods: {
      _upload(e) {  // 上传头像图片
//        console.log(e.currentTarget.files[0]);
        let file = this.$refs.uploadImg.files[0];
        this.imgPreview(file);
      },
      imgPreview(file) {
        let self = this;
        if (!file || !window.FileReader) {
          return false;
        }
        const isJPG = /^image/.test(file.type);
        const isLt2M = file.size / 1024 / 1024 < 2;
        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
          return false;
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
          return false;
        }
        const reader = new FileReader();
        reader.onloadstart = (e) => {
          self.showpercent = true;
          self.percent = 0;
        };
        reader.onprogress = (e) => {
          let percentLoaded = Math.round((e.loaded / e.total) * 100);
          if (percentLoaded < 100) {
            self.percent = percentLoaded;
          }
        };
        reader.onload = (event) => {
          self.percent = 100;
          self.dataUrl = event.target.result;
          setTimeout(_ => {
            self.showpercent = false;
          }, 1500);
        }
        reader.readAsDataURL(file);
        let formdata = new FormData();
        console.log(formdata);
      },
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
      },
      _Freshdata() {
      }
    },
    created() {
      this._Freshdata();
    },
    activated() {
      this.$router.push({path: '/index/home'});
    },
    watch: {
      '$route': '_Freshdata'
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
      .in-list
        float: right
        margin-right: 15px
        overflow: hidden
        .inlist-item
          display: inline-block
          height: 60px
          line-height: 60px
          vertical-align: middle
          .fileinput
            display: inline-block
            position: relative
            width: 34px
            height: 34px
            margin-top: 12px
            border-radius: 50%
            cursor: pointer
            overflow: hidden
            .avatar
              width: 34px
              height: 34px
              display: block
              border-radius: 50%
            .lab_upload
              position: absolute
              left: 0px
              top: 0px
              width: 34px
              height: 34px
              cursor: pointer
              z-index: 100
            .ipt_upload
              outline: none
              /*visibility: hidden*/
              display: none
              color: transparent
          .list-text
            padding: 0 3px
            font-size: 16px
            vertical-align: middle
            cursor: pointer
    .content
      width: 100%
      height: calc(100% - 62px)
      .menu
        float: left
        width: 240px
        height: 100%
        overflow: hidden
        .el-menu
          height: 100%
        .imgs
          width: 20px
          height: 20px
          margin-left: 2px
          margin-top: -2px
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
    .progress
      width: 300px
      height: 40px
      position: absolute
      right: 12px
      top: 76px
      &.pr_fade-enter, &.pr_fade-leave-active
        top: 60px
      &.pr_fade-enter-active, &.pr_fade-leave-active
        transition: all 0.3s linear
</style>

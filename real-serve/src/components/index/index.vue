<template>
  <div class="index">
    <div class="header">
      <div class="title">
        <img class="imglogo" src="./navlogo.png" alt="navlogo">
        <span class="text">苏州不动产数据集成服务平台</span>
      </div>
      <ul class="list">
        <li class="list-item">
          <a class="cont" href="javascript:;" @click="_navcont('print')">
            <img v-if="navcont!== 'print'" class="imgs" src="./print.png" alt="">
            <img v-else class="imgs" src="./print_blue.png" alt="">
          </a>
        </li>
        <li class="list-item">
          <a class="cont" href="javascript:;" @click="_navcont('qudong')">
            <img v-if="navcont!== 'qudong'" class="imgs" src="./qudong.png" alt="">
            <img v-else class="imgs" src="./qudong_blue.png" alt="">
          </a>
        </li>
        <li class="list-item">
          <a class="cont" href="javascript:;" @click="_navcont('export')">
            <img v-if="navcont!== 'export'" class="imgs" src="./export.png" alt="">
            <img v-else class="imgs" src="./export_blue.png" alt="">
          </a>
        </li>
        <li class="list-item">
          <el-menu :default-active="Navmenu" class="el-menu-demo" mode="horizontal">
            <el-submenu index="2">
              <template slot="title">我的工作台</template>
              <el-menu-item index="2-1" @click.native="_editmima"><i class="editpass"></i>修改密码</el-menu-item>
              <el-menu-item index="2-2" @click.native="_goback"><i class="safeback"></i>安全退出</el-menu-item>
            </el-submenu>
          </el-menu>
        </li>
        <li class="list-item" style="margin-right: 15px;">
          <div class="fileinput">
            <img class="avatar" :src="dataUrl" alt="">
            <input type="file" name="file" title="" value="" ref="uploadImg" class="img_upload" @change="_upload">
          </div>
        </li>
      </ul>
    </div>
    <div class="content">
      <div class="menu">
        <el-menu :default-active="Menuitem" router class="el-menu-vertical-demo">
          <el-menu-item index="/index/houseinfo">
            <img class="imgs" src="./houseinfo.png" alt="houseinfo">
            <span slot="title">房屋基本信息查询</span>
          </el-menu-item>
          <el-menu-item index="/index/housenum">
            <img class="imgs" src="./housenum.png" alt="num">
            <span slot="title">房产数查询</span>
          </el-menu-item>
        </el-menu>
      </div>
      <div class="Tabs">
        <router-view></router-view>
        <p class="footer">技术支持: 苏州中地行信息技术有限公司</p>
      </div>
    </div>
    <div class="in-dialog">
      <el-dialog title="修改密码" :visible.sync="editpassVisible" width="30%" center>
        <el-form :model="MimaForm" status-icon :rules="mimarule" ref="MimaForm" label-width="80px"
                 class="demo-ruleForm">
          <el-form-item label="原密码" prop="oldpass">
            <el-input type="password" v-model="MimaForm.oldpass" value="" placeholder="请输入原密码"></el-input>
          </el-form-item>
          <el-form-item label="新密码" prop="newpass">
            <el-input type="password" v-model="MimaForm.newpass" value="" placeholder="请输入新密码"
                      auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="repass">
            <el-input type="password" v-model="MimaForm.repass" value="" placeholder="请输入确认密码"
                      auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="editpassVisible = false">取 消</el-button>
          <el-button type="primary" @click="_editpass_submit">确 定</el-button>
        </span>
      </el-dialog>
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
        }, 5000);
      };
      let validatenewPass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请输入密码'));
        } else {
          if (this.MimaForm.repass !== '') {
            this.$refs.MimaForm.validateField('repass');
          }
          callback();
        }
      };
      let validaterePass = (rule, value, callback) => {
        if (value === '') {
          callback(new Error('请再次输入密码'));
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
        },
        dataUrl: require('./navuser.png'),
        username: '李延亮',
        Menuitem: '/index/houseinfo',
        editpassVisible: false,
        Navmenu: '1',
        navcont: ''  // 导航栏功能选项
      }
    },
    computed: {},
    methods: {
      _navcont(val) {  // 导航栏功能选项
        if (val === '' || val === null) {
          return false;
        }
        this.navcont = val;
      },
      _upload(e) {  // 上传头像图片
        console.log(e.currentTarget.files[0]);
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
        reader.onloadend = (event) => {
          self.dataUrl = event.target.result;
        };
        reader.readAsDataURL(file);
        let formdata = new FormData();
        console.log(formdata);
      },
      _editmima() {  // 修改密码
        this.editpassVisible = true;
      },
      _editpass_submit() {
        this.editpassVisible = false;
      },
      _goback() { // 安全退出
        this.$router.push({path: '/login'});
      }
    },
    created() {
      this.$router.push({path: '/index/houseinfo'});
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
          width: 44px
          height: 44px
          margin: 0 8px 0 15px
          vertical-align: middle
        .text
          font-family: '微软雅黑'
          font-size: 22px
          color: #33363f
          vertical-align: middle
      .list
        float: right
        margin-right: 15px
        .list-item
          display: inline-block
          height: 60px
          line-height: 60px
          vertical-align: middle
          .el-menu .el-menu-item
            width: 130px
            min-width: 130px
          .el-submenu.is-active .el-submenu__title
            border-bottom: none
          .el-submenu__title
            padding: 0 10px 0 8px
          .safeback, .editpass
            display: inline-block
            width: 18px
            height: 22px
            padding-right: 8px
            vertical-align: middle
          .safeback
            background: url("./safeback.png") 0 0 no-repeat
            background-size: 16px 18px
          .editpass
            background: url("./editpass.png") 0 0px no-repeat
            background-size: 16px 18px
          .cont
            display: inline-block
            margin-right: 15px
            vertical-align: middle
            .imgs
              width: 34px
              height: 34px
              display: block
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
            .img_upload
              position: absolute
              right: 0px
              top: 0px
              height: 34px
              cursor: pointer
              outline: none
              color: transparent
              opacity: 1
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
        height: 100%
        overflow: hidden
        .el-menu
          height: 100%
          background: #3d4b5e
          .el-menu-item
            color: #fff
          .el-menu-item:focus, .el-menu-item:hover
            background: #505e70
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
</style>

<template>
  <div class="index">
    <div class="header">
      <div class="title">
        <img class="imglogo" src="./navlogo.png" alt="navlogo">
        <span class="text">苏州不动产数据集成服务监管平台</span>
      </div>
      <ul class="list">
        <li>
          <div class="fileinput">
            <img class="avatar" :src="dataUrl" alt="">
            <input type="file" name="file" title="" value="" ref="uploadImg" class="img_upload" @change="_upload">
          </div>
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
        <el-menu :default-active="Menuitem" router class="el-menu-vertical-demo">
          <el-menu-item index="/index/log">
            <img class="imgs" src="./log.png" alt="log">
            <span slot="title">权属查询日志</span>
          </el-menu-item>
          <el-menu-item index="/index/statistics">
            <img class="imgs" src="./statistics.png" alt="user">
            <span slot="title">权属查询统计</span>
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
        Menuitem: '/index/home',
        editpassVisible: false
      }
    },
    computed: {},
    methods: {
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
        }
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
      this.$router.push({path: '/index/log'});
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

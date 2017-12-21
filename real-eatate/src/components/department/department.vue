<template>
  <div class="department">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/department'}">部门管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="main">
      <div class="inner">
        <div class="List" v-for="item in DepartData" :key="item.index">
          <departList :departData="item"></departList>
        </div>
        <div class="List1">
          <ul class="list-header">
            <li class="edit"><span>编辑</span></li>
            <li class="logo">
              <el-upload
                class="avatar-uploader"
                action="https://jsonplaceholder.typicode.com/posts/"
                :show-file-list="false"
                :on-success="handleAvatarSuccess"
                :before-upload="beforeAvatarUpload">
                <img v-if="imageUrl" :src="imageUrl" class="avatar">
                <div v-else class="logo-icon"><span class="add">添加</span><span class="logos">LOGO</span></div>
              </el-upload>
              <div class="addbumen"><span class="text">增加部门名称<i class="el-icon-plus"></i></span></div>
            </li>
            <li class="delete"><span>删除</span></li>
          </ul>
        </div>
        <div class="footer-message">
          <span class="text">*您可以增加或删除区域，填写新区域后点击空白处，即可继续新增区域，点击"保存"可将变化后的区域保存。</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */
  import departList from './departList.vue';

  export default {
    data() {
      return {
        imageUrl: '',
        DepartData: [
          {
            Url: 'http://c.hiphotos.baidu.com/image/h%3D300/sign=0637c5e533292df588c3aa158c335ce2/9345d688d43f8794361b48e4db1b0ef41ad53a17.jpg',
            departname: '不动产登记中心',
            partlist: [
              {
                depart: '信息处'
              },
              {
                depart: '不动产登记局'
              },
              {
                depart: '登记处'
              },
              {
                depart: '不动产登记局'
              },
              {
                depart: '房产交易中心'
              },
              {
                depart: '税务局'
              },
              {
                depart: '税务局'
              }
            ],
            bcolor: 'blue'
          },
          {
            Url: '',
            departname: '公检法',
            partlist: [
              {
                depart: '信息处'
              },
              {
                depart: '不动产登记局'
              }
            ],
            bcolor: 'orange'
          },
          {
            Url: '',
            departname: '公检法',
            partlist: [
              {
                depart: '信息处'
              },
              {
                depart: '不动产登记局'
              }
            ],
            bcolor: 'pink'
          },
          {
            Url: '',
            departname: '公检法',
            partlist: [
              {
                depart: '信息处'
              },
              {
                depart: '不动产登记局'
              }
            ],
            bcolor: 'skyblue'
          }
        ]
      };
    },
    methods: {
      handleAvatarSuccess(res, file) {
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
        const isJPG = file.type === 'image/jpeg';
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      }
    },
    components: {
      departList
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/mixin";

  .department
    width: 100%
    height: 100%
    .header
      width: 100%
      height: 54px
      line-height: 54px
      .el-breadcrumb
        display: inline-block
        margin-left: 36px
        color: #33363f
        font-size: 16px
        .el-breadcrumb__inner
          color: #409EFF
        .el-breadcrumb__item:last-child .el-breadcrumb__inner
          color: #33363f
    .main
      width: calc(100% - 54px)
      height: calc(100% - 110px)
      margin: auto
      border-radius: 5px
      overflow: auto
      .inner
        padding-bottom: 60px
        margin-top: 10px
        position: relative
        .List
          display: inline-block
          width: 30%
          max-width: 560px
          margin-bottom: 30px
          margin-right: 22px
          vertical-align: top
        .List1
          display: inline-block
          width: 30%
          height: 352px
          max-width: 560px
          border-color(#FE707D)
          background: #fff
          box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
          margin-left: -4px
          vertical-align: top
          .list-header
            display: flex
          .edit, .delete
            flex: 1
            display: inline-block
            vertical-align: top
          .edit, .delete
            span
              display: inline-block
              margin: 10px 10px
              padding: 10px
              font-size: 16px
              color: #D3D3D3
              cursor: pointer
          .edit
            text-align: left
          .delete
            text-align: right
          .logo
            flex: 0 1 140px
            .addbumen
              height: 40px
              line-height: 40px
              text-align: center
              .text
                font-size: 16px
                color: #D3D3D3
                cursor: pointer
          .avatar-uploader
            margin-top: 16px
            text-align: center
            .el-upload
              border: 1px solid #ccc;
              width: 100px
              height: 100px
              border-radius: 50%
              background: #EEE
              text-align: center
              cursor: pointer
              position: relative
              overflow: hidden
          .avatar-uploader .el-upload:hover
            border-color: #409EFF
          .logo-icon
            display: inline-block
            width: 100px
            height: 100px
            font-size: 14px
            color: #8c939d
            text-align: center
            .add, .logos
              display: block
              height: 20px
              line-height: 20px
            .add
              margin-top: 28px
          .avatar
            width: 100px
            height: 100px
            display: block
        .footer-message
          position: absolute
          left: 0px
          bottom: 0px
          width: 100%
          height: 40px
          line-height: 40px
          text-align: center
          .text
            font-size: 14px
            color: #bababa
</style>

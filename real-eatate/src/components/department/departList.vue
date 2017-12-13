<template>
  <div class="departList">
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
          <i v-else class="el-icon-plus avatar-uploader-icon"></i>
        </el-upload>
        <p class="text">不动产登记中心</p>
      </li>
      <li class="delete"><span>删除</span></li>
    </ul>
    <div class="list-content">
      <el-table :data="DataList" :show-header="false" style="width: 100%">
        <el-table-column prop="depart" label="日期"></el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button @click.native.prevent="_EditRow(scope.$index, tableData4)"
                       type="text"><i class="el-icon-edit"></i></el-button>
            <el-button @click.native.prevent="_DeleteRow(scope.$index, tableData4)"
                       type="text"><i class="el-icon-delete"></i></el-button>
          </template>
        </el-table-column>
        <div slot="append"><span class="text">增加部门</span><i class="el-icon-plus add-part" @click="_Addpart"></i></div>
      </el-table>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        imageUrl: require('./bdcdjlogo.png'),
        DataList: [
          {
            depart: '信息处'
          },
          {
            depart: '不动产登记局'
          }
        ]
      };
    },
    methods: {
      _EditRow() {      // 修改信息
      },
      _DeleteRow() {    // 删除信息
      },
      _Addpart() {      // 新增部门
        this.$router.push({path: '/index/departmanage'});
      },
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
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/mixin";

  .departList
    width: 100%
    border-color(#4595FF)
    background: #fff
    box-shadow: 0 0 10px rgba(69, 149, 255, 0.4);
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
          color: #4595ff
          cursor: pointer
      .edit
        text-align: left
      .delete
        text-align: right
      .logo
        flex: 0 1 140px
        .text
          height: 40px
          line-height: 40px
          text-align: center
          font-size: 16px
          color: #33363f
      .avatar-uploader
        margin-top: 16px
        text-align: center
        .el-upload
          border: 1px solid #ccc;
          width: 100px
          height: 100px
          border-radius: 50%
          text-align: center
          cursor: pointer
          position: relative
          overflow: hidden
      .avatar-uploader .el-upload:hover
        border-color: #409EFF
      .avatar-uploader-icon
        font-size: 28px
        color: #8c939d
        width: 100px
        height: 100px
        line-height: 100px
        text-align: center
      .avatar
        width: 100px
        height: 100px
        display: block
    .list-content
      width: 100%
      .el-button
        font-size: 14px
      .el-table td
        padding: 4px 0
        font-family: '微软雅黑'
        font-size: 16px
        font-weight: 600
        color: #33363f
      .el-table__append-wrapper
        height: 36px
        line-height: 36px
        padding: 6px 0
        font-family: '微软雅黑'
        font-size: 16px
        font-weight: 600
        color: #bababa
        overflow: hidden
        .text
          padding: 0 6px 0 10px
          vertical-align: middle
        .add-part
          padding: 4px
          cursor: pointer
          vertical-align: middle

</style>

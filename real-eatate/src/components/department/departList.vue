<template>
  <div class="departList" :class="'border-'+departData.bcolor">
    <ul class="list-header">
      <li class="edit"><span id="editdepart" @click="_editdepart">编辑</span></li>
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
        <p v-if="editdepart" class="text">{{departData.departname}}</p>
        <el-input v-else v-model="departData.departname" id="departipt" placeholder="请输入部门名称" autofocus
                  @blur="_editsave" @keyup.enter.native="_editsave">
        </el-input>
        <span v-if="departtext" class="add-text" @click="_editdepart">增加部门名称 <i class="el-icon-plus"></i></span>
      </li>
      <li class="delete"><span @click="_deletedepart">删除</span></li>
    </ul>
    <div class="list-content">
      <el-table :data="DataList" :show-header="false" style="width: 100%;" height="150">
        <el-table-column prop="depart" label="部门"></el-table-column>
        <el-table-column label="操作" width="80">
          <template slot-scope="scope">
            <el-button @click.native.prevent="_EditRow(scope.$index, scope.row)"
                       type="text"><i class="el-icon-edit"></i></el-button>
            <el-button @click.native.prevent="_DeleteRow(scope.$index, scope.row)"
                       type="text"><i class="el-icon-delete"></i></el-button>
          </template>
        </el-table-column>
      </el-table>
      <div class="add-depart"><span class="text">增加部门</span><i class="el-icon-plus add-part" @click="_Addpart"></i>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    props: {
      departData: {
        type: Object
      },
      Index: {
        type: Number
      }
    },
    data() {
      return {
        editdepart: true,
        departtext: false,
        imageUrl: '',
        DataList: [
          {
            depart: ''
          }
        ]
      };
    },
    methods: {
      _editdepart() {  // 编辑部门
        this.departtext = false;
        this.editdepart = false;
      },
      _editsave() {  // 保存编辑部门
        this.editdepart = true;
        if (this.departData.departname === '') {
          this.departtext = true;
        }
      },
      _deletedepart() {
        let index = this.Index;
        this.$emit('deletedepart', index);
      },
      _EditRow(index, row) {      // 修改部门
        this.$store.commit('departmanage', row);
        this.$store.commit('departTitle', '修改部门');
        this.$router.push({path: '/index/departmanage'});
      },
      _DeleteRow(index, row) {    // 删除信息
        this.DataList.splice(index, 1);
      },
      _Addpart() {      // 新增部门
        this.$store.commit('departTitle', '新增部门');
        this.$router.push({path: '/index/departmanage'});
      },
      handleAvatarSuccess(res, file) {
        console.log(file);
        this.imageUrl = URL.createObjectURL(file.raw);
      },
      beforeAvatarUpload(file) {
//        console.log(file);
        const isJPG = /^image/.test(file.type);
        const isLt2M = file.size / 1024 / 1024 < 2;

        if (!isJPG) {
          this.$message.error('上传头像图片只能是 JPG/PNG 格式!');
        }
        if (!isLt2M) {
          this.$message.error('上传头像图片大小不能超过 2MB!');
        }
        return isJPG && isLt2M;
      },
      freshData() {
        this.DataList = this.departData.partlist;
        this.imageUrl = this.departData.Url;
      }
    },
    created() {
      this.freshData();
      if (this.departData.departname === '') {
        this.departtext = true;
      }
    },
    watch: {
      departData: {
        handler(newValue, oldValue) {
          this.freshData();
//          console.log(newValue);
//          console.log(oldValue);
        },
        deep: true
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  @import "../../common/stylus/mixin";

  .departList
    width: 100%
    background: #fff
    box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
    border-radius: 0 0 5px 5px
    vertical-align: top
    &.border-blue
      border-colors(#4595FF)
    &.border-orange
      border-colors(#FFAA45)
    &.border-pink
      border-colors(#FEADBD)
    &.border-skyblue
      border-colors(#8ACFF3)
    &.border-green
      border-colors(#64B86E)
    &.border-red
      border-colors(#FE707D)
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
        flex: 0 1 150px
        position: relative
        .text
          height: 40px
          line-height: 40px
          text-align: center
          font-size: 16px
          color: #33363f
        .el-input__inner
          border-radius: 0
          height: 40px
          font-size: 16px
          color: #33363f
        .add-text
          position: absolute
          left: 0px
          bottom: 0px
          display: block
          width: 100%
          height: 40px
          line-height: 40px
          text-align: center
          font-size: 16px
          color: #CFCFCF
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
    .list-content
      width: 100%
      .el-button
        font-size: 14px
      .el-table::before
        background-color: #fff
      .el-table td
        border: none
        padding: 0
        font-family: '微软雅黑'
        font-size: 16px
        font-weight: 600
        color: #33363f
    .add-depart
      height: 36px
      line-height: 36px
      padding: 4px 0
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

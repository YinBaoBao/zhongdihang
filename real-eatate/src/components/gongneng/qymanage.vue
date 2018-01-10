<template>
  <div class="qymanage">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{path: '/index/gongneng'}">功能管理</el-breadcrumb-item>
        <el-breadcrumb-item :to="{path: '/index/addgn/cxyj'}">管理功能(房产数量查询)</el-breadcrumb-item>
        <el-breadcrumb-item>区域管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <ul class="qy-content">
      <li v-if="QyData" v-for="(item,index) in QyData" :key="item.id" class="list-item">
        <span class="text">{{item.areaname}}</span>
        <i class="el-icon-circle-close item-icon" @click="_deleteqy(index)"></i>
      </li>
      <li class="list-item">
        <span class="add-text" @click="_add_qy">新增 <i class="el-icon-plus"></i></span>
      </li>
    </ul>
    <div class="sub_save">
      <el-button type="primary" round @click="save_Submit">保存</el-button>
    </div>
    <div class="footer-message">
      <span class="text">*您可以增加或删除区域，填写新区域后点击空白处，即可继续新增区域，点击"保存"可将变化后的区域保存。</span>
    </div>
    <div class="qy-dialog">
      <el-dialog title="新增区域" :visible.sync="QyVisible"
                 width="400px" center>
        <el-select v-model="qyvalue" placeholder="请选择" @change="qychange">
          <el-option v-for="item in qyoptions" :key="item.value"
                     :label="item.label" :value="item.value">
          </el-option>
        </el-select>
        <span slot="footer" class="dialog-footer">
          <el-button @click="QyVisible = false">取 消</el-button>
          <el-button type="primary" @click="_saveqy">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        QyVisible: false,
        QyData: [
          {
            areaname: '姑苏'
          },
          {
            areaname: '相城'
          },
          {
            areaname: '园区'
          },
          {
            areaname: '吴中'
          },
          {
            areaname: '新区'
          },
          {
            areaname: '吴江'
          },
          {
            areaname: '太仓'
          },
          {
            areaname: '昆山'
          }
        ],
        qyoptions: [
          {
            value: '姑苏',
            label: '姑苏'
          },
          {
            value: '相城',
            label: '相城'
          }
        ],
        qyvalue: ''
      }
    },
    methods: {
      _add_qy() {  // 新增区域
        this.QyVisible = true;
      },
      _saveqy() {  // 确定新增区域
        this.QyVisible = false;
        let json = {
          areaname: this.qyvalue
        }
        this.QyData.push(json);
      },
      save_Submit() {   // 保存
      },
      qychange(val) {   // 区域下拉框
        if (val === '' || val === null) {
          return false;
        }
        this.qyvalue = val;
      },
      _deleteqy(index) {  // 删除区域
        this.QyData.splice(index, 1)
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .qymanage
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    border-radius: 5px
    position: relative
    .header
      width: 100%
      height: 60px
      line-height: 60px
      .el-breadcrumb
        display: inline-block
        margin-left: 36px
        color: #33363f
        font-size: 16px
        .el-breadcrumb__inner
          color: #409EFF
        .el-breadcrumb__item:last-child .el-breadcrumb__inner
          color: #33363f
      .el-button
        float: right
        padding: 8px 12px
        margin-top: 14px
        margin-right: 24px
        font-size: 16px
        font-family: '微软雅黑'
    .qy-content
      padding: 30px 36px
      .list-item
        display: inline-block
        height: 40px
        line-height: 40px
        margin-right: 80px
        margin-bottom: 24px
        position: relative
        .text
          display: inline-block
          padding: 0 40px
          border-radius: 20px
          background: #ECECEC
          color: #474951
        .add-text
          display: inline-block
          padding: 0 32px
          margin-left: -4px
          border-radius: 20px
          background: #F4F8F9
          color: #A0A0A0
          cursor: pointer
        .item-icon
          position: absolute
          top: -6px
          right: 0px
          color: #c8ceda
          border-radius: 50%
          font-size: 18px
          cursor: pointer
        .item-icon:hover
          color: #F51A05
    .sub_save
      position: relative
      .el-button
        position: absolute
        left: 410px
        top: 10px
        padding: 14px 60px
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
    .qy-dialog
      .el-dialog__body
        text-align: center
        .el-select
          width: 240px
</style>

<template>
  <div class="search">
    <div class="cont">
      <div class="title">
        <span class="text">查询条件</span>
      </div>
      <div class="selects">
        <el-checkbox-group v-model="Cxtjck" @change="_cxtjchange">
          <el-checkbox v-for="item in Cxtjoption" :label="item" :key="item.id"></el-checkbox>
        </el-checkbox-group>
      </div>
    </div>
    <div class="area">
      <div class="title">
        <span class="text">查询区域</span>
        <span class="areamanage" @click="_qymanage">区域管理</span>
      </div>
      <div class="ckgrounp">
        <div class="ckarea">
          <p class="quyu">区域选择</p>
          <el-checkbox-group v-model="Cxqyck" @change="_cxqychange">
            <el-checkbox v-for="item in Cxqyoption" :label="item" :key="item.id"></el-checkbox>
          </el-checkbox-group>
        </div>
        <div class="ckareafs">
          <p class="quyu">区域组合方式</p>
          <el-radio-group v-model="arearadio">
            <el-radio :label="1">可多选查询</el-radio>
            <el-radio :label="2">单选</el-radio>
          </el-radio-group>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        Cxtjck: [],
        Cxtjoption: [
          '权利人姓名',
          '权利人证件号',
          '不动产号',
          '坐落',
          '上传授权委托书',
          '在线智能授权'
        ],                  // 查询条件
        Cxqyck: [],                  // 查询区域
        Cxqyoption: ['姑苏', '相城', '园区', '吴中', '新区', '吴江', '太仓', '昆山'],
        arearadio: 1                // 区域组合方式单选
      };
    },
    methods: {
      _cxtjchange(val) {   // 查询条件
        console.log(val);
      },
      _cxqychange() {   // 查询区域
      },
      _qymanage() {     // 区域管理
        this.$router.push({path: '/index/qymanage'});
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .search
    .cont
      width: 100%
      .title
        height: 40px
        line-height: 40px
        .text
          padding-left: 36px
          font-size: 14px
          color: #33363f
      .selects
        padding: 10px 36px
        .el-checkbox-group
          padding-bottom: 20px
          border-radius: 5px
          background: #f4f8f9
          .el-checkbox
            min-width: 200px
            margin-left: 30px
            margin-top: 20px
          .el-checkbox__label
            font-size: 16px
            color: #33363f
    .area
      width: 100%
      .title
        height: 40px
        line-height: 40px
        font-size: 14px
        .text
          padding-left: 36px
          margin-right: 4px
          color: #33363f
        .areamanage
          display: inline-block
          padding: 0 10px
          color: #b0b0b0
          cursor: pointer
      .ckgrounp
        padding: 10px 36px
        .quyu
          display: block
          padding-top: 30px
          padding-left: 30px
          font-size: 14px
        .ckarea
          display: inline-block
          width: 56%
          height: 160px
          border-radius: 5px
          background: #f4f8f9
          vertical-align: top
          .el-checkbox
            width: 80px
            margin-left: 30px
            margin-top: 26px
          .el-checkbox__label
            font-size: 16px
            color: #33363f
        .ckareafs
          float: right
          width: 40%
          height: 160px
          border-radius: 5px
          background: #f4f8f9
          vertical-align: top
          .el-radio-group
            width: 100%
            .el-radio
              display: block
              margin-left: 30px
              margin-top: 26px
              text-align: left
</style>

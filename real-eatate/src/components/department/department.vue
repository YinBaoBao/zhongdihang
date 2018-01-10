<template>
  <div class="department">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/department'}">部门管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="main">
      <div class="inner">
        <div v-if="DepartData" class="List" v-for="(item,index) in DepartData" :key="item.id">
          <departList :departData="item" :Index="index" @deletedepart="_deletedepart"></departList>
        </div>
        <div class="add-List" @click="_addList">
          <i class="el-icon-plus text-icon"></i>
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
          }
        ]
      };
    },
    methods: {
      _addList() {  // 增加部门
        let data = {
          Url: '',
          departname: '',
          partlist: [
            {
              depart: ''
            }
          ],
          bcolor: 'red'
        }
        this.DepartData.push(data);
      },
      _deletedepart(index) {   // 删除部门
        console.log(index);
        this.DepartData.splice(index, 1);
      }
    },
    watch: {
      DepartData: {
        handler(newValue, oldValue) {
//          console.log(newValue);
//          this.DepartData = newValue;
        },
        deep: true
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
        .add-List
          display: inline-block
          border: 1px dashed #D9D9D9
          width: 200px
          height: 200px
          line-height: 200px
          text-align: center
          cursor: pointer
          .text-icon
            font-size: 30px
            color: #8C939D
        .add-List:hover
          border-color: #409EFF
          .text-icon
            color: #409EFF
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

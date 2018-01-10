<template>
  <div class="Gn">
    <div class="header">
      <el-breadcrumb separator-class="el-icon-arrow-right" replace>
        <el-breadcrumb-item :to="{ path: '/index/gonneng' }">功能管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div class="Gn-table">
      <template>
        <el-table :data="GnData" ref="Gntable" style="width: 100%;height:100%;" max-height="600">
          <el-table-column prop="index" label="序号" width="60"></el-table-column>
          <el-table-column prop="gn" label="功能"></el-table-column>
          <el-table-column prop="cxyj" label="查询依据"></el-table-column>
          <el-table-column prop="zcnr" label="显示组成内容"></el-table-column>
          <el-table-column prop="creat_time" label="创建时间"></el-table-column>
          <el-table-column label="操作" width="100">
            <template slot-scope="scope">
              <el-button @click.native.prevent="deleteRow(scope.$index, userData)"
                         type="text"><i class="el-icon-edit-outline"></i>
              </el-button>
              <el-button @click.native.prevent="deleteRow(scope.$index, userData)"
                         type="text"><i class="el-icon-delete"></i>
              </el-button>
            </template>
          </el-table-column>
          <el-table-column label="功能图标" width="100">
            <template slot-scope="scope">
              <el-dropdown trigger="click" :hide-on-click="false">
                <span class="el-dropdown-link">
                  <img class="hh_imgs" src="./fcslzm.png" alt=""><i class="el-icon-arrow-down el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item v-if="Icondata" v-for="item in Icondata" :key="item.index">
                    <img class="imgs" :src="item.url" alt="">
                    <span class="text">{{item.name}}</span>
                  </el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
            </template>
          </el-table-column>
          <div slot="append">
            <span class="add-cont" @click="_ddGn">新增功能名称 <i class="el-icon-plus"></i></span>
          </div>
        </el-table>
      </template>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  /* eslint-disable semi */

  export default {
    data() {
      return {
        GnData: [
          {
            index: '1',
            gn: '合法住所',
            cxyj: '权利人证件号',
            zcnr: '统计信息(合法住所)',
            creat_time: '2017-12-20 10:40'
          }
        ],
        Icondata: [
          {
            url: require('./fcslzm.png'),
            name: '房产数量证明'
          },
          {
            url: require('./hfzs.png'),
            name: '合法住所'
          }
//          {
//            url: './jbxx.png',
//            name: '基本信息'
//          },
//          {
//            url: './jcxx.png',
//            name: '基础信息'
//          },
//          {
//            url: './qlr.png',
//            name: '权利人'
//          },
//          {
//            url: './qlrxm.png',
//            name: '权利人姓名'
//          },
//          {
//            url: './qlrzjh.png',
//            name: '权利人证件号'
//          },
//          {
//            url: './qzh.png',
//            name: '权证号'
//          },
//          {
//            url: './wts.png',
//            name: '委托书'
//          },
//          {
//            url: './yfczm.png',
//            name: '原房产证号'
//          },
//          {
//            url: './zjh.png',
//            name: '证件号'
//          },
//          {
//            url: './zl.png',
//            name: '坐落'
//          }
        ]
      }
    },
    methods: {
      _ddGn() {
        this.$router.push({path: '/index/addgn'});
      },
      deleteRow(index, rows) {
        rows.splice(index, 1);
      }
    },
    created() {
    },
    mounted() {
//      console.log(this.$refs.Gntable);
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .Gn
    width: calc(100% - 54px)
    height: calc(100% - 80px)
    margin: auto
    margin-top: 26px
    background: #fff
    box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
    border-radius: 5px
    .header
      width: 100%
      height: 60px
      line-height: 60px
      .el-breadcrumb
        display: inline-block
        margin-left: 36px
        color: #33363f
        font-size: 16px
      .el-button
        float: right
        padding: 8px 12px
        margin-top: 14px
        margin-right: 24px
        font-size: 16px
        font-family: '微软雅黑'
    .Gn-table
      height: calc(100% - 60px)
      overflow: auto
      font-family: '微软雅黑'
      color: #656565
      .el-table th
        background: #f4f8f9
        font-size: 16px
      .el-table td
        padding: 4px 0
      .el-dropdown .hh_imgs
        width: 16px
        height: 16px
        padding-right: 6px
        vertical-align: middle
      .el-table__append-wrapper
        height: 48px
        line-height: 48px
        overflow: hidden
      .el-table__append-wrapper:hover
        background-color: #f5f7fa
      .add-cont
        display: inline-block
        padding: 0 4px 0 10px
        margin-left: 60px
        cursor: pointer
</style>

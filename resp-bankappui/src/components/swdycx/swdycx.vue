<template>
  <div class="swdycx">
    <div class="header">
      <div class="avatar">
        <img src="./time.png" alt="time">
      </div>
      <div class="content">
        <div class="checks">
          <el-radio-group v-model="radio">
            <el-radio :label="1">姑苏区</el-radio>
            <el-radio :label="2">相城区</el-radio>
            <el-radio :label="3">吴中区</el-radio>
            <el-radio :label="4">高新区</el-radio>
            <el-radio :label="5">园区</el-radio>
          </el-radio-group>
        </div>
        <div class="time">
          <div class="area">
            <el-date-picker
              v-model="starttimevalue"
              type="date"
              format="yyyy-MM-dd"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              @change="_starttime"
              style="width: 160px;">
            </el-date-picker>
            <span style="color: #bfcbd9;"> - </span>
            <el-date-picker
              v-model="endtimevalue"
              type="date"
              format="yyyy-MM-dd"
              placeholder="选择日期"
              :picker-options="pickerOptions"
              @change="_starttime"
              style="width: 160px;">
            </el-date-picker>
          </div>
          <el-button class="btn_1" style="padding: 8px 30px;margin-right: 15px;margin-left: 50px;">
            查询
          </el-button>
          <el-button class="btn_2" style="padding: 8px 30px" @click="export2Excel">导出</el-button>
        </div>
      </div>
    </div>
    <div class="cf_table">
      <el-table :data="tableData" border height="450"
                style="width: 100%">
        <el-table-column type="index" width="60"></el-table-column>
        <el-table-column prop="bdcdyh" label="不动产单元号" sortable></el-table-column>
        <el-table-column prop="dy_time" label="抵押登记时间" sortable></el-table-column>
        <el-table-column prop="dyr" label="抵押人"></el-table-column>
        <el-table-column prop="dyqr" label="抵押权人"></el-table-column>
        <el-table-column prop="dyfs" label="抵押方式"></el-table-column>
        <el-table-column prop="address" label="坐落"></el-table-column>
        <el-table-column prop="bdbzqs" label="被担主债权数额"></el-table-column>
        <el-table-column prop="starttime" label="债务履行起始时间"></el-table-column>
        <el-table-column prop="endtime" label="债务履行结束时间"></el-table-column>
        <el-table-column prop="bdczmh" label="不动产证明号"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        radio: 1,
        pickerOptions: {
          shortcuts: [{
            text: '今天',
            onClick(picker) {
              picker.$emit('pick', new Date());
            }
          }, {
            text: '昨天',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24);
              picker.$emit('pick', date);
            }
          }, {
            text: '一周前',
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
              picker.$emit('pick', date);
            }
          }]
        },
        starttimevalue: '',
        endtimevalue: '',
        tableData: [
          {
            bdcdyh: '',
            starttime: '',
            endtime: '',
            dy_time: '',
            dyr: '',
            dyqr: '',
            dyfs: '',
            address: '',
            bdbzqs: '',
            bdczmh: '',
            createtime: ''
          }
        ]
      };
    },
    methods: {
      _starttime() {
      },
      export2Excel() {
        require.ensure([], () => {
          const {export_json_to_excel} = require('../../common/pluings/excel/Export2Excel');
          const tHeader = ['不动产单元号', '抵押登记时间', '抵押人', '抵押权人', '抵押方式', '坐落', '被担主债权数额', '债务履行起始时间', '债务履行结束时间', '不动产证明号'];
          const filterVal = ['bdcdyh', 'dy_time', 'dyr', 'dyqr', 'dyfs', 'address', 'bdbzqs', 'starttime', 'endtime', 'bdczmh'];
          const list = this.tableData;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '顺位抵押查询excel');
        });
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .swdycx
    width: 100%
    .header
      width: 100%
      margin: 20px 0 10px 0
      .avatar
        display: inline-block
        width: 100px
        margin-left: 40px
        vertical-align: middle
        img
          display: block
          width: 60px
          height: 60px
      .content
        display: inline-block
        width: calc(100% - 150px)
        vertical-align: middle
        .checks
          width: 100%
          height: 40px
          margin-bottom: 8px
          line-height: 40px
          .el-radio__inner
            width: 16px
            height: 16px
          .el-radio__input.is-checked .el-radio__inner
            border-color: #148583
            background: #148583
        .time
          width: 100%
          .area
            display: inline-block
          .el-input__inner
            height: 34px
            border-radius: 0
          .el-button
            border-radius: 0
            span
              color: #fff
          .btn_1
            border-color: #148583
            background: #148583
          .btn_2
            border-color: #ff954d
            background: #ff954d
          .btn_1:active
            border-color: #148583
          .btn_1:hover
            border-color: #148583
    .cf_table
      width: 94%
      margin-left: 38px
      .el-table td, .el-table th
        height: 34px
      .el-table th
        border-right: 1px solid #148583
        border-bottom: 1px solid #148583
        background: #148583
        .cell
          background: #148583
          color: #fff
      .el-table .sort-caret .ascending
        border-bottom: 5px solid #97a8be
      .el-table .sort-caret .descending
        border-top: 5px solid #97a8be
      .el-table .ascending .ascending
        border-bottom: 5px solid #fff
      .el-table .descending .descending
        border-top: 5px solid #fff
</style>

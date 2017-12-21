<template>
  <div class="log">
    <ul class="select">
      <li class="list-item">
        <span class="title">查询功能</span>
        <el-select v-model="gnvalue" placeholder="请选择">
          <el-option
            v-for="item in gnoptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </li>
      <li class="list-item">
        <span class="title">查询部门</span>
        <el-select v-model="departvalue" placeholder="请选择">
          <el-option
            v-for="item in departoptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </li>
      <li class="list-item">
        <span class="title">查询用户/对象</span>
        <el-input v-model="username" placeholder="请输入用户姓名"></el-input>
      </li>
    </ul>
    <ul class="select">
      <li class="list-item">
        <span class="title">查询日期</span>
        <el-date-picker v-model="start_time" type="date" placeholder="起始日期"></el-date-picker>
        <span style="font-size: 14px;vertical-align: middle;padding: 0 4px;">至</span>
        <el-date-picker v-model="end_time" type="date" placeholder="截止日期"></el-date-picker>
      </li>
      <el-button type="primary">查询</el-button>
    </ul>
    <div class="log-table">
      <el-table :data="LogData" border style="width: 100%">
        <el-table-column prop="gn" label="查询功能" width="180"></el-table-column>
        <el-table-column prop="depart" label="查询部门" width="180"></el-table-column>
        <el-table-column prop="user" label="查询用户"></el-table-column>
        <el-table-column prop="obj" label="查询对象"></el-table-column>
        <el-table-column prop="zjh" label="查询对象证件号"></el-table-column>
        <el-table-column prop="time" label="查询时间"></el-table-column>
      </el-table>
    </div>
    <div class="log-pages">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="100"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        currentNum: 10,
        currentPage: 1,
        total: 1000,
        gnoptions: [
          {
            value: '',
            label: ''
          }
        ],
        gnvalue: '',
        departoptions: [
          {
            value: '',
            label: ''
          }
        ],
        departvalue: '',
        username: '',
        start_time: '',
        end_time: '',
        LogData: [
          {
            gn: '2016-05-02',
            depart: '王小虎',
            user: '上海市普陀区金沙江路 1518 弄',
            obj: '',
            zjh: '',
            time: ''
          },
          {
            gn: '2016-05-02',
            depart: '王小虎',
            user: '上海市普陀区金沙江路 1518 弄',
            obj: '',
            zjh: '',
            time: ''
          },
          {
            gn: '2016-05-02',
            depart: '王小虎',
            user: '上海市普陀区金沙江路 1518 弄',
            obj: '',
            zjh: '',
            time: ''
          }
        ]
      };
    },
    methods: {
      handleSizeChange(val) {   // 每页显示的条数
//        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {  // 当前第几页
//        console.log(`当前页: ${val}`);
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .log
    height: calc(100% - 60px)
    padding: 0 30px
    position: relative
    .select
      width: 100%
      padding-top: 24px
      .list-item
        display: inline-block
        margin-right: 20px
        .title
          display: inline-block
          padding-right: 8px
          font-size: 16px
          vertical-align: middle
        .el-input
          width: inherit
        .el-input__inner
          height: 36px
      .el-button
        font-size: 14px
        padding: 10px 36px
        margin-left: 44px
    .log-table
      width: 100%
      margin-top: 20px
      .el-table th, .el-table td
        font-family: '微软雅黑'
        font-size: 14px
        text-align: center
      .el-table th
        border: none
        color: #fff
        background: #3d4b5e
      .el-table td
        color: #6f6f6f
        padding: 4px
    .log-pages
      position: absolute
      left: 0
      bottom: 0
      width: 100%
      text-align: center
</style>

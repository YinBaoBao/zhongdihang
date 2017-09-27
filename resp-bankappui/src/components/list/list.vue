<template>
  <div>
    <div class="list">
      <div class="header">
        <div class="auto">
          <div id="needupdata" class="inner">
            <el-table ref="singleTable" :data="upData" highlight-current-row @current-change="needCurrentChange"
                      :row-class-name="tableRowClassName" style="width: 100%">
              <el-table-column type="index" width="50"></el-table-column>
              <el-table-column property="text" label="需上传资料"></el-table-column>
              <el-table-column property="state" label="状态" width="100"></el-table-column>
            </el-table>
          </div>
        </div>
        <div class="btn">
          <el-button @click="setCurrent(upData[updataindex])" style="padding: 6px 33px;margin-right: 15px;">上传
          </el-button>
          <el-button @click="deleteRow(deletindex,upData)" style="padding: 6px 33px;">删除</el-button>
        </div>
      </div>
      <div class="uploaded">
        <div class="auto">
          <div class="inner">
            <el-table ref="uploaded" :data="uploaded" highlight-current-row
                      style="width: 100%">
              <el-table-column type="index" width="50"></el-table-column>
              <el-table-column property="text" label="已上传资料" style="text-align: center;"></el-table-column>
            </el-table>
          </div>
        </div>
      </div>
      <div class="border-height"></div>
      <div class="service">
        <div class="check">
          <span @click="_Look">查看登记申请书</span>
        </div>
        <div class="btns">
          <el-button style="padding: 6px 4px" @click="_systemdata">系统获取数据</el-button>
          <el-button style="padding: 6px 4px">打印登记申请书</el-button>
          <el-button style="padding: 6px 4px">提交登记申请</el-button>
        </div>
        <div class="entrust">
          <span>委托授权状态:</span>
          <div class="entrust_input">
            <el-autocomplete
              class="inline-input"
              v-model="state"
              :fetch-suggestions="querySearch"
              placeholder="输入义务人证件号可查授权状态"
              @select="handleSelect" style="width: 100%;"
            ></el-autocomplete>
          </div>
        </div>
      </div>
      <div class="situation">
        <span class="authorization">委托授权状态:暂未线上授权</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  const ERR_OK = 0;

  export default {
    data() {
      return {
        upData: [],  // 需上传资料
        uploaded: [],   // 已上传资料对应的文件
        currentRow: null,
        updataindex: 0,
        deletindex: 0,
        restaurants: [], // 委托授权状态
        state: '',
        timeout: null
      };
    },
    computed: {},
    methods: {
      _Look(event) {  // 查看登记申请书
        this.$emit('Look');
      },
      setCurrent(row) {
//        console.log(row);
        this.$refs.singleTable.setCurrentRow(row);
      },
      tableRowClassName(row, index) { // 向行添加索引值
        row.index = index;
      },
      needCurrentChange(val) {
        if (val === null || val === '') {
          return false;
        }
        this.currentRow = val;
        this.updataindex = this.currentRow.id;
        this.deletindex = this.currentRow.index;
      },
      deleteRow(index, row) { // 删除行
        row.splice(index, 1);
      },
      _systemdata() { // 系统获取数据
        this.$emit('systemdata');
      },
      loadAll() {            // 输入框匹配建议对象
        return [
          {'value': '', 'address': ''},
          {'value': 'Hot honey', 'address': ''}
        ];
      },
      querySearch(queryString, cb) {
        var restaurants = this.restaurants;
        var results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.indexOf(queryString.toLowerCase()) === 0);
        };
      },
      handleSelect(item) {
        console.log(item);
      }
    },
    created() {
      this.$http.get(this.$store.state.api + '/updata').then((response) => {
        response = response.body;
        if (response.errno === ERR_OK) {
          this.upData = response.data.needupdata;
          this.uploaded = response.data.addupdata;
        }
      });
    },
    mounted() {
      this.restaurants = this.loadAll();
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .list
    width: 100%
    .header
      width: 100%
      .auto
        border: 1px solid #DFE6EC
        height: 200px
        overflow: auto
      .btn
        text-align: center
        margin-top: 10px
    .uploaded
      margin-top: 10px
      text-align: left
      .auto
        height: 158px
        overflow: auto;
    .service
      border: 1px solid #DFE6EC
      font-size: 0
      .check
        width: 100%
        text-align: right
        span
          display: inline-block
          padding: 8px 20px
          font-size: 14px
          text-decoration: underline
          color: rgba(138, 110, 110, 0.8)
          cursor: pointer
      .btns
        width: 100%
        margin-top: 12px
        text-align: center
      .entrust
        width: 100%
        margin: 20px 0 6px 0
        text-align: left
        span
          display: inline-block
          padding: 3px 4px 3px 10px
          font-size: 14px
        .entrust_input
          display: inline-block
          width: 218px
          .el-input__inner
            height: 26px
    .situation
      width: 100%
      .authorization
        display: inline-block
        padding: 12px 0 6px 12px
        font-size: 14px
</style>

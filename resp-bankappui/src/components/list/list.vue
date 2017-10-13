<template>
  <div>
    <div class="List_data">
      <div class="header">
        <div class="auto">
          <el-table ref="singleTable" border height="250" :data="upDatalist" highlight-current-row
                    @current-change="needCurrentChange"
                    :row-class-name="tableRowClassName"
                    @select="_upSelection" style="width: 100%">
            <el-table-column type="selection" width="50"></el-table-column>
            <el-table-column property="mlmc" label="需上传资料"></el-table-column>
            <el-table-column property="state" label="状态" width="100"></el-table-column>
          </el-table>
        </div>
        <div class="btn">
          <el-upload style="display: inline-block;width: 128px;"
                     class="upload-demo"
                     :headers="{'token':headerstoken,'username': haedersusername}"
                     :action="action"
                     :show-file-list="false"
                     :on-change="_onchange">
            <el-button @click="_updata_submit(upDatalist[updataindex])" style="padding: 6px 18px;">点击上传</el-button>
            <div v-show="false" slot="tip" class="el-upload__tip"></div>
          </el-upload>
          <el-button @click="deleteRow(deletindex,upDatalist)"
                     style="padding: 6px 33px;display: inline-block;vertical-align: top;">删除
          </el-button>
        </div>
      </div>
      <div class="uploaded">
        <div class="auto">
          <el-table ref="uploaded" height="150" :data="uploaded" highlight-current-row
                    style="width: 100%">
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column property="text" label="已上传资料" style="text-align: center;"></el-table-column>
          </el-table>
        </div>
      </div>
      <div class="border-height"></div>
      <div class="service">
        <div class="check">
          <span @click="_Look">查看登记申请书</span>
        </div>
        <div class="btns">
          <el-button style="padding: 6px 4px;margin-left: 3px;" @click="_systemdata">系统获取数据</el-button>
          <el-button style="padding: 6px 4px;margin-left: 3px;">打印登记申请书</el-button>
          <el-button style="padding: 6px 4px;margin-left: 3px;">提交登记申请</el-button>
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
  export default {
    props: {
      upDatalist: {
        type: Array
      }
    },
    data() {
      return {
        upData: [],  // 需上传资料
        uploaded: [],   // 已上传资料对应的文件
        fileList: [],   // 上传文件
        currentRow: null,
        updataindex: 0,
        deletindex: null,
        restaurants: [], // 委托授权状态
        state: '',
        timeout: null,
        action: '',
        updatas: '',
        headerstoken: '',
        haedersusername: '',
        filename: '',
        Bjbh: '',
        mlxh: '',
        acc_token: ''
      };
    },
    computed: {
      State() {
//        let state = '';
        return this.upDatalist.mlwjsl === 0 ? '未上传' : '已上传';
      },
      filelist() {
        return this.fileList;
      }
    },
    methods: {
      _Look(event) {  // 查看登记申请书
        this.$emit('appLook');
        this.$emit('canLook');
      },
      _updata_submit(row) {
      },
      _upSelection(row) {
        this.acc_token = localStorage.getItem('login_token');
        this.Bjbh = row[0].bjbh;
        this.mlxh = row[0].mlxh;
        this.action = this.$store.state.Host + '/BDCDJSQControl/saveQYCL/200/' + this.Bjbh + '/' + this.acc_token + '/' + this.mlxh;
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
        if (index === null) {
          this.$message({
            message: '请选择需要删除的资料!',
            type: 'warning'
          });
          return false;
        }
        row.splice(index, 1);
        this.deletindex = null;
      },
      _systemdata() { // 系统获取数据
        this.$emit('systemdata');
        this.$emit('cansystemdata');
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
      },
      _onchange(file, fileList) {
        console.log(file);
//        if (file.response.status === 200) {
//          this.$notify({
//            title: '提示',
//            message: '上传成功',
//            type: 'success'
//          });
//        } else {
//          this.$notify({
//            title: '警告',
//            message: '上传失败',
//            type: 'error'
//          });
//        }
      }
    },
    created() {
      this.headerstoken = this.$store.state.token;
      this.haedersusername = this.$store.state.username;
    },
    mounted() {
      this.restaurants = this.loadAll();
    },
    watch: {
      upDatalist: {
        handler(val, oldVal) {
        },
        deep: true
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .List_data
    width: 100%
    margin-left: 2px
    .header
      width: 100%
      .auto
        border: 1px solid #DFE6EC
        overflow: hidden
      .btn
        border-left: 1px solid #DFE6EC
        border-right: 1px solid #DFE6EC
        text-align: center
        padding: 12px 10px
    .uploaded
      text-align: left
      .auto
        overflow: hidden
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
        margin-top: 4px
        text-align: center
      .entrust
        width: 100%
        margin: 15px 0 6px 0
        text-align: left
        span
          display: inline-block
          padding: 3px 4px 3px 10px
          font-size: 12px
        .entrust_input
          display: inline-block
          width: 200px
          .el-input__inner
            height: 26px
            font-size: 12px
    .situation
      width: 100%
      .authorization
        display: inline-block
        padding: 12px 0 6px 12px
        font-size: 12px
</style>

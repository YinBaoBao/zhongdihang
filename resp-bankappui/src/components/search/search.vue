<template>
  <div>
    <div class="search">
      <div class="header">
        <ul>
          <li class="type">
            <span>查询类型</span>
            <el-select v-model="type" placeholder="请选择" style="width: 110px;">
              <el-option v-for="item in Type" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </li>
          <li class="filter">
            <span>状态筛选</span>
            <el-select v-model="filter" placeholder="请选择" @change="_filterchange" style="width: 110px;">
              <el-option v-for="item in Filter" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </li>
          <li class="search_btn">
            <el-input v-model="state" placeholder="输入不动产证明号/报件编号/义务人可查" style="float: left;width: 280px;"></el-input>
            <el-button type="primary" icon="search" style="float: left;margin-left: 18px;">查询</el-button>
          </li>
          <li class="export">
            <el-button @click="export2Excel">导出</el-button>
          </li>
        </ul>
      </div>
      <div class="content">
        <div class="tables">
          <el-table :data="tableData" border style="width: 100%;" height="420"
                    :default-sort="{prop: 'date', order: 'descending'}">
            <el-table-column type="index" width="60"></el-table-column>
            <el-table-column prop="date" label="申请时间" sortable width="180"></el-table-column>
            <el-table-column prop="prove" label="不动产权证明号" sortable width="160"></el-table-column>
            <el-table-column prop="Report" label="报件编号" sortable width="166"></el-table-column>
            <el-table-column prop="register" label="登记类型" width="100"></el-table-column>
            <el-table-column prop="obligation" label="义务人"></el-table-column>
            <el-table-column prop="address" label="坐落" :formatter="formatter" width="150"></el-table-column>
            <el-table-column prop="register_time" label="登记时间" width="180"></el-table-column>
            <el-table-column prop="state" label="状态"></el-table-column>
            <el-table-column prop="remark" label="备注"></el-table-column>
            <el-table-column label="操作" width="110">
              <template scope="scope">
                <el-button type="text" size="small" @click="_apply_look(scope.$index,scope.row)">查看</el-button>
                <el-button type="text" size="small" @click="_deletInfo(scope.$index,scope.row)">删 除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="pages">
          <el-pagination @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="currentPage"
                         :page-sizes="[10, 20, 30, 40]"
                         :page-size="pageSize"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="total">
          </el-pagination>
        </div>
      </div>
    </div>
    <div class="dialog">
      <el-dialog title="信息修改" :visible.sync="info_account" size="tiny" :before-close="handleClose">
        <el-form :model="Info_Form" :rules="rules" ref="Info_Form" label-width="100px" class="demo-ruleForm">
          <el-form-item label="义务人" prop="obligation">
            <el-input v-model="Info_Form.obligation"></el-input>
          </el-form-item>
          <el-form-item label="登记类型" prop="register">
            <el-input v-model="Info_Form.register"></el-input>
          </el-form-item>
          <el-form-item label="不动产权证明号" prop="prove">
            <el-input v-model="Info_Form.prove"></el-input>
          </el-form-item>
          <el-form-item label="报件编号" prop="Report">
            <el-input v-model="Info_Form.Report"></el-input>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="Info_Form.remark"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="info_account = false">取 消</el-button>
          <el-button type="primary" @click="info_account = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        Type: [
          {
            value: '全部',
            label: '全部'
          }, {
            value: '抵押申报',
            label: '抵押申报'
          }, {
            value: '抵押注销',
            label: '抵押注销'
          }
        ],    // 查询类型
        Filter: [
          {
            value: '全部状态',
            label: '',
            code: ''
          },
          {
            value: '待受理',
            label: '待受理',
            code: ''
          },
          {
            value: '退回',
            label: '退回',
            code: ''
          },
          {
            value: '待接件',
            label: '待接件',
            code: ''
          },
          {
            value: '受理中',
            label: '受理中',
            code: ''
          },
          {
            value: '已完成',
            label: '已完成',
            code: ''
          }
        ], // 状态筛选
        type: '全部',
        filter: '全部状态',
        state: '',
        tableData: [
          {
            id: '1',
            date: '2017-08-16 11:30',
            prove: '3638812345',
            Report: '163881234512345',
            register: '抵押申报',
            obligation: '',
            address: '姑苏区21号',
            register_time: '2017-08-21 11:30',
            state: '已完成',
            remark: '',
            bjblzt: ''
          }
        ], // 表格数据
        info_account: false,
        Info_Form: {
          obligation: '',
          register: '',
          prove: '',
          Report: '',
          remark: ''
        },
        rules: {
          obligation: [
            {required: true, message: '请输入义务人', trigger: 'blur'}
          ]
        },
        currentPage: 1, // 当前页
        pageSize: 10,  // 每页显示的条数
        total: 100,     // 每页显示的条数
        application: ''
      };
    },
    methods: {
      getFilter() {    // 获取筛选状态下拉列表
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 10001
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                value: data[i].name,
                code: data[i].code
              };
              arr.push(json);
            }
            this.Filter = arr;
          }
        });
      },
      gettableData() {    // 获取表格数据
        let username = localStorage.getItem('username');
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/findOneDJSQ', {
          jkzh: 200,
          jyczyzh: username,
          currentPage: this.currentPage + '',
          pageSize: this.pageSize + ''
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            if (response.body.body === null) {
              return false;
            }
            let data = response.body.body;
            this.total = 100;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
              if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                str = '';
              }
              let obj = {
                date: str,
                prove: data[i].bdcqk.bdcqzshx,
                Report: data[i].bjbh,
                register: data[i].sqdjsy.djlxmc,
                obligation: '',
                address: data[i].bdcqk.zl,
                register_time: str,
                state: data[i].bjblztmc,
                remark: '',
                bjblzt: data[i].bjblzt
              };
              arr.push(obj);
            }
            this.tableData = arr;
          }
        });
      },
      _getywr() {
        let data = this.tableData;
        for (var i = 0; i < data.length; i++) {
          this.$http.post(this.$store.state.Host + '/BDCDJSQControl/fidnOneOnligor', {
            jkzh: 200,
            ywrxh: 1,
            bjbh: data[i].Report
          }).then((response) => {
            response = response.body;
            if (response.status === '200') {
              if (response.body.body === null) {
                return false;
              }
              data[i].obligation = response.body.ywrmc;
              console.log(data);
            }
          });
        }
      },
      _filterchange(val) { // 筛选状态
//        console.log(val);
      },
      _deletInfo(index, row) {
        let bjbh = row.Report;
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/deleteDJSQ', {
          jkzh: 200,
          bjbh: bjbh
        }).then((response) => {
          response = response.body;
          switch (response.status) {
            case '200':
              this.$notify({
                title: '提示',
                message: '删除成功',
                type: 'success'
              });
              this.gettableData();
              break;
            default:
              this.$notify({
                title: '错误',
                message: '删除失败',
                type: 'error'
              });
          }
          if (response.body.body === null) {
            this.$notify({
              title: '错误',
              message: response.body.message,
              type: 'error'
            });
            return false;
          }
        });
      },
      _apply_look(index, row) {
        let bjbh = row.Report;
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/findDJSQ', {
          jkzh: 200,
          bjbh: bjbh,
          bjblzt: row.bjblzt
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            if (response.body.body === null) {
              return false;
            }
            this.$store.commit('application', response.body.body);
            this.$router.push({path: '/index/application'});
          }
        });
      },
      formatter(row, column) {  // 表格数据过滤
        return row.address;
      },
      handleClick() {
        console.log(1);
      },
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      handleSizeChange(val) {  // 每页显示多少条
        this.pageSize = val;
        this.gettableData();
      },
      handleCurrentChange(val) { // 当前第几页
        this.currentPage = val;
        this.gettableData();
      },
      export2Excel() {
        require.ensure([], () => {
          const {export_json_to_excel} = require('../../common/pluings/excel/Export2Excel');
          const tHeader = ['申请时间', '不动产权证明号', '报件编号', '登记类型', '义务人', '坐落', '登记时间', '状态', '备注'];
          const filterVal = ['date', 'prove', 'Report', 'register', 'obligation', 'address', 'register_time', 'state', 'remark'];
          const list = this.tableData;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '申报/注销查询excel');
        });
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      }
    },
    created() {
      this.getFilter();
      this.gettableData();
    },
    activated() {
      this.gettableData();
    },
    mounted() {
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .search
    float: left
    border: 2px solid #DFE6EC;
    width: 99%
    padding-bottom: 15px
    .header
      width: 100%
      overflow: hidden
      .type, .filter, .search_btn
        float: left
        padding: 6px 15px
        margin-top: 8px
      .export
        float: right
        padding: 6px 15px
        margin-top: 12px
      .type span, .filter span
        padding: 0 6px
        font-size: 14px
        vertical-align: middle;
      .search_btn
        margin-left: 20px
      .el-input__inner
        height: 32px
      .el-button
        padding: 8px 15px
    .content
      width: 100%
      height: 460px
      overflow: hidden;
      position: relative;
      .tables
        float: left
        width: 100%
        max-height: 420px
        overflow: hidden
        .el-table td, .el-table th
          height: 34px
      .pages
        display: inline-block
        position: absolute
        left: 160px
        bottom: -2px
</style>

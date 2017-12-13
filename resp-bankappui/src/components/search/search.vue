<template>
  <div>
    <div class="search">
      <div class="header">
        <ul>
          <!--<li class="type">-->
          <!--<span>查询类型</span>-->
          <!--<el-select v-model="type" placeholder="请选择" style="width: 110px;">-->
          <!--<el-option v-for="item in Type" :key="item.value" :label="item.label" :value="item.value"></el-option>-->
          <!--</el-select>-->
          <!--</li>-->
          <li class="filter">
            <span>状态筛选</span>
            <el-select v-model="filter" placeholder="请选择" @change="_filterchange" style="width: 110px;">
              <el-option v-for="item in Filter" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </li>
          <li class="search_btn">
            <el-input v-model="bjbh" placeholder="输入不动产证明号/报件编号/义务人可查" style="float: left;width: 280px;"></el-input>
            <el-button class="btn_1" type="primary" @click="_seartch" icon="search"
                       style="float: left;margin-left: 18px;">查询
            </el-button>
          </li>
          <li class="export">
            <el-button class="btn_2" @click="export2Excel">导出</el-button>
          </li>
        </ul>
      </div>
      <div class="content">
        <div class="tables">
          <el-table :data="tableData" border v-loading="tableloding" element-loading-text="拼命加载中" style="width: 100%;"
                    height="550" @sort-change="_sortchange"
                    :default-sort="{prop: 'date', order: 'descending'}">
            <el-table-column type="index" width="60"></el-table-column>
            <el-table-column prop="date" label="申请时间" sortable width="180"></el-table-column>
            <el-table-column prop="prove" label="不动产权证号" width="180"></el-table-column>
            <el-table-column prop="bdcqzhxt" label="不动产权证明号" width="180"></el-table-column>
            <el-table-column prop="Report" label="报件编号" width="166"></el-table-column>
            <el-table-column prop="register" label="登记类型" width="100"></el-table-column>
            <el-table-column prop="obligation" label="义务人"></el-table-column>
            <el-table-column prop="address" label="坐落" :formatter="formatter" width="150"></el-table-column>
            <el-table-column prop="register_time" label="登记时间" width="180"></el-table-column>
            <el-table-column prop="state" label="状态"></el-table-column>
            <el-table-column prop="remark" label="备注" show-overflow-tooltip></el-table-column>
            <el-table-column label="操作" width="120">
              <template scope="scope">
                <el-button type="text" size="small" @click="_apply_look(scope.$index,scope.row)">查看</el-button>
                <el-button type="text" size="small" @click="_deletInfo(scope.$index,scope.row)">删 除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
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
  import {formatDate} from '../../common/js/date.js';
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
          }
        ], // 状态筛选
        filecode: null,
        type: '全部',
        filter: '全部状态',
        state: '',
        bjbh: '',
        tableData: [], // 表格数据
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
        application: '',
        tableloding: false  // loading
      };
    },
    methods: {
      getFilter() {    // 获取筛选状态下拉列表
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10001
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [
              {
                value: '全部状态',
                code: ''
              }
            ];
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
      gettableData(bjbh, bjblztmc) {    // 获取表格数据
        this.tableloding = true;
        let query = '';
        if (bjblztmc === '' || bjblztmc === null) {
          query = JSON.stringify({bjbh: bjbh});
        } else {
          query = JSON.stringify({bjbh: bjbh, bjblzt: bjblztmc});
        }
        let description = localStorage.getItem('description');
        let username = localStorage.getItem('username');
        if (description === 'admin' || description === 'bankAdmin') {
          this.$http({
            url: this.$store.state.Host + '/BDCDJSQControl/{jkzh}/bdcdj/search',
            params: {
              jkzh: 200,
              query: query,
              page: this.currentPage + '',
              size: this.pageSize + '',
              sort: JSON.stringify({'jlcjsj': 'desc'})
            },
            method: 'GET'
          }).then((response) => {
            response = response.body;
            if (response.status === '200') {
              this.tableloding = false;
              if (response.body === null) {
                return false;
              }
              if (response.body.body === null) {
                return false;
              }
              this.total = response.body.count;
              let data = response.body.body;
              let arr = [];
              for (var i = 0; i < data.length; i++) {
                let obligation = '';
                let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
                if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                  str = '';
                }
                if (data[i].sqrqk.ywrs.length === 0) {
                  obligation = '';
                } else {
                  obligation = data[i].sqrqk.ywrs[0].ywrmc;
                }
                let obj = {
                  date: this.formatDate(data[i].jlcjsj),
                  prove: data[i].bdcqk.bdcqzshx,
                  Report: data[i].bjbh,
                  bdcqzhxt: data[i].bdcqzhxt,
                  register: data[i].sqdjsy.djlxmc,
                  obligation: obligation,
                  address: data[i].bdcqk.zl,
                  register_time: str,
                  state: data[i].bjblztmc,
                  remark: data[i].bjthyy,
                  bjblzt: data[i].bjblzt
                };
                arr.push(obj);
              }
              this.tableData = arr;
            }
          }, (error) => {
            this.tableloding = false;
            if (error.status === 401) {
              this.$notify({
                title: '警告',
                message: error.body,
                type: 'error'
              });
              this.$confirm('是否新建登录?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'info'
              }).then(() => {
                this.$router.push({path: '/login'});
              });
            }
          });
        } else {
          this.$http({
            url: this.$store.state.Host + '/BDCDJSQControl/{jkzh}/bdcdj/search/{jyczyzh}',
            params: {
              jkzh: 200,
              jyczyzh: username,
              query: query,
              page: this.currentPage + '',
              size: this.pageSize + '',
              sort: JSON.stringify({'jlcjsj': 'desc'})
            },
            method: 'GET'
          }).then((response) => {
            if (response.status === '401') {
              this.$message({
                message: '登录超时，请重新登录！',
                type: 'warning'
              });
            }
            response = response.body;
            if (response.status === '200') {
              this.tableloding = false;
              if (response.body === null) {
                return false;
              }
              if (response.body.body === null) {
                return false;
              }
              this.total = response.body.count;
              let data = response.body.body;
              let arr = [];
              for (var i = 0; i < data.length; i++) {
                let obligation = '';
                let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
                if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                  str = '';
                }
                if (data[i].sqrqk.ywrs.length === 0) {
                  obligation = '';
                } else {
                  obligation = data[i].sqrqk.ywrs[0].ywrmc;
                }
                let obj = {
                  date: this.formatDate(data[i].jlcjsj),
                  prove: data[i].bdcqk.bdcqzshx,
                  Report: data[i].bjbh,
                  bdcqzhxt: data[i].bdcqzhxt,
                  register: data[i].sqdjsy.djlxmc,
                  obligation: obligation,
                  address: data[i].bdcqk.zl,
                  register_time: str,
                  state: data[i].bjblztmc,
                  remark: data[i].bjthyy,
                  bjblzt: data[i].bjblzt
                };
                arr.push(obj);
              }
              this.tableData = arr;
            }
          }, (error) => {
            this.tableloding = false;
            if (error.status === 401) {
              this.$notify({
                title: '警告',
                message: error.body,
                type: 'error'
              });
            }
          });
        }
      },
      formatDate(time) {
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
      },
      _sortchange(colum) {
        let query = '';
        let description = localStorage.getItem('description');
        let username = localStorage.getItem('username');
        if (colum.order === 'ascending') {
          if (description === 'admin' || description === 'bankAdmin') {
            this.$http({
              url: this.$store.state.Host + '/BDCDJSQControl/{jkzh}/bdcdj/search',
              params: {
                jkzh: 200,
                query: query,
                page: this.currentPage + '',
                size: this.pageSize + '',
                sort: JSON.stringify({'jlcjsj': 'asc'})
              },
              method: 'GET'
            }).then((response) => {
              response = response.body;
              if (response.status === '200') {
                if (response.body === null) {
                  return false;
                }
                if (response.body.body === null) {
                  return false;
                }
                this.total = response.body.count;
                let data = response.body.body;
                let arr = [];
                for (var i = 0; i < data.length; i++) {
                  let obligation = '';
                  let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
                  if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                    str = '';
                  }
                  if (data[i].sqrqk.ywrs.length === 0) {
                    obligation = '';
                  } else {
                    obligation = data[i].sqrqk.ywrs[0].ywrmc;
                  }
                  let obj = {
                    date: this.formatDate(data[i].jlcjsj),
                    prove: data[i].bdcqk.bdcqzshx,
                    Report: data[i].bjbh,
                    bdcqzhxt: data[i].bdcqzhxt,
                    register: data[i].sqdjsy.djlxmc,
                    obligation: obligation,
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
          } else {
            this.$http({
              url: this.$store.state.Host + '/BDCDJSQControl/{jkzh}/bdcdj/search/{jyczyzh}',
              params: {
                jkzh: 200,
                jyczyzh: username,
                query: query,
                page: this.currentPage + '',
                size: this.pageSize + '',
                sort: JSON.stringify({'jlcjsj': 'asc'})
              },
              method: 'GET'
            }).then((response) => {
              if (response.status === '401') {
                this.$message({
                  message: '登录超时，请重新登录！',
                  type: 'warning'
                });
              }
              response = response.body;
              if (response.status === '200') {
                if (response.body === null) {
                  return false;
                }
                if (response.body.body === null) {
                  return false;
                }
                this.total = response.body.count;
                let data = response.body.body;
                let arr = [];
                for (var i = 0; i < data.length; i++) {
                  let obligation = '';
                  let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
                  if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                    str = '';
                  }
                  if (data[i].sqrqk.ywrs.length === 0) {
                    obligation = '';
                  } else {
                    obligation = data[i].sqrqk.ywrs[0].ywrmc;
                  }
                  let obj = {
                    date: str,
                    prove: data[i].bdcqk.bdcqzshx,
                    Report: data[i].bjbh,
                    bdcqzhxt: data[i].bdcqzhxt,
                    register: data[i].sqdjsy.djlxmc,
                    obligation: obligation,
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
          }
        }
        if (colum.order === 'descending') {
          if (description === 'admin' || description === 'bankAdmin') {
            this.$http({
              url: this.$store.state.Host + '/BDCDJSQControl/{jkzh}/bdcdj/search',
              params: {
                jkzh: 200,
                query: query,
                page: this.currentPage + '',
                size: this.pageSize + '',
                sort: JSON.stringify({'jlcjsj': 'desc'})
              },
              method: 'GET'
            }).then((response) => {
              response = response.body;
              if (response.status === '200') {
                if (response.body === null) {
                  return false;
                }
                if (response.body.body === null) {
                  return false;
                }
                this.total = response.body.count;
                let data = response.body.body;
                let arr = [];
                for (var i = 0; i < data.length; i++) {
                  let obligation = '';
                  let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
                  if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                    str = '';
                  }
                  if (data[i].sqrqk.ywrs.length === 0) {
                    obligation = '';
                  } else {
                    obligation = data[i].sqrqk.ywrs[0].ywrmc;
                  }
                  let obj = {
                    date: this.formatDate(data[i].jlcjsj),
                    prove: data[i].bdcqk.bdcqzshx,
                    Report: data[i].bjbh,
                    bdcqzhxt: data[i].bdcqzhxt,
                    register: data[i].sqdjsy.djlxmc,
                    obligation: obligation,
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
          } else {
            this.$http({
              url: this.$store.state.Host + '/BDCDJSQControl/{jkzh}/bdcdj/search/{jyczyzh}',
              params: {
                jkzh: 200,
                jyczyzh: username,
                query: query,
                page: this.currentPage + '',
                size: this.pageSize + '',
                sort: JSON.stringify({'jlcjsj': 'desc'})
              },
              method: 'GET'
            }).then((response) => {
              if (response.status === '401') {
                this.$message({
                  message: '登录超时，请重新登录！',
                  type: 'warning'
                });
              }
              response = response.body;
              if (response.status === '200') {
                if (response.body === null) {
                  return false;
                }
                if (response.body.body === null) {
                  return false;
                }
                this.total = response.body.count;
                let data = response.body.body;
                let arr = [];
                for (var i = 0; i < data.length; i++) {
                  let obligation = '';
                  let str = data[i].djjysj.jyrq + ' ' + data[i].djjysj.jysj;
                  if (data[i].djjysj.jyrq === null || data[i].djjysj.jysj === null) {
                    str = '';
                  }
                  if (data[i].sqrqk.ywrs.length === 0) {
                    obligation = '';
                  } else {
                    obligation = data[i].sqrqk.ywrs[0].ywrmc;
                  }
                  let obj = {
                    date: str,
                    prove: data[i].bdcqk.bdcqzshx,
                    Report: data[i].bjbh,
                    bdcqzhxt: data[i].bdcqzhxt,
                    register: data[i].sqdjsy.djlxmc,
                    obligation: obligation,
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
          }
        }
      },
      _seartch() {
        this.gettableData(this.bjbh, this.filecode);
      },
      _filterchange(val) { // 筛选状态
        if (val === '' || val === null) {
          return false;
        }
        if (val === '全部状态') {
          this.gettableData();
        }
        let options = this.Filter;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            let code = parseInt(options[i].code);
            this.filecode = code;
            this.gettableData('', this.filecode);
          }
        }
      },
      _deletInfo(index, row) {
        if (row.state === '待受理' || row.state === '待接件' || row.state === '已完成') {
          this.$notify({
            title: '警告',
            message: row.state + '。。。',
            type: 'warning'
          });
          return false;
        }
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
        }, (error) => {
          if (error.status === 401) {
            this.$notify({
              title: '警告',
              message: error.body,
              type: 'error'
            });
            this.$confirm('是否新建登录?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              this.$router.push({path: '/login'});
            });
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
        }, (error) => {
          if (error.status === 401) {
            this.$notify({
              title: '警告',
              message: error.body,
              type: 'error'
            });
            this.$confirm('是否新建登录?', '提示', {
              confirmButtonText: '确定',
              cancelButtonText: '取消',
              type: 'info'
            }).then(() => {
              this.$router.push({path: '/login'});
            });
          }
        });
      },
      formatter(row, column) {  // 表格数据过滤
        return row.address;
      },
      handleClick() {
//        console.log(1);
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
          const tHeader = ['申请时间', '不动产权证号', '不动产权证明号', '报件编号', '登记类型', '义务人', '坐落', '登记时间', '状态', '备注'];
          const filterVal = ['date', 'prove', 'bdcqzhxt', 'Report', 'register', 'obligation', 'address', 'register_time', 'state', 'remark'];
          const list = this.tableData;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '申请状态查询excel');
        });
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      }
    },
    created() {
      this.getFilter();
    },
    activated() {
      this.gettableData();
      this.$store.commit('application', '');
    },
    mounted() {
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .search
    float: left
    width: 99%
    padding-left: 20px
    padding-bottom: 64px
    position: relative;
    .header
      width: 100%
      overflow: hidden
      .type, .search_btn
        float: left
        padding: 6px 15px
        margin-top: 8px
      .filter
        float: left
        padding: 6px 15px 0 0
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
        height: 34px
        border-radius: 0
      .el-input__inner:hover
        border-color: #148583
      .el-input__inner:active
        border-color: #148583
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
    .content
      width: 100%
      overflow: hidden;
      .tables
        float: left
        width: 100%
        overflow: hidden
        .el-table td, .el-table th
          height: 34px
        .el-table th
          border-right: 1px solid #148583
          border-bottom: 1px solid #148583
          background: #148583
          .cell
            overflow: hidden !important
            text-overflow: ellipsis !important
            white-space: nowrap !important
            vertical-align: middle
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
    .pages
      display: inline-block
      position: absolute
      left: 30%
      bottom: 18px
      .el-pager li.active
        border-color: #148583
        background-color: #148583
</style>

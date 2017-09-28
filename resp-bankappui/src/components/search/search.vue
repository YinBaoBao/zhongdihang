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
            <el-select v-model="filter" placeholder="请选择" style="width: 110px;">
              <el-option v-for="item in Filter" :key="item.value" :label="item.label" :value="item.value"></el-option>
            </el-select>
          </li>
          <li class="search_btn">
            <el-row class="demo-autocomplete" style="float: left;width: 286px;">
              <el-col :span="24">
                <el-autocomplete class="inline-input" v-model="state" :fetch-suggestions="querySearch"
                                 placeholder="输入不动产证明号/报件编号/义务人可查"
                                 @select="handleSelect" style="width: 100%;"></el-autocomplete>
              </el-col>
            </el-row>
            <el-button type="primary" icon="search" style="float: left;margin-left: 18px;">查询</el-button>
          </li>
          <li class="export">
            <el-button>导出</el-button>
          </li>
        </ul>
      </div>
      <div class="content">
        <div class="tables">
          <el-table :data="tableData" border style="width: 100%;" height="420"
                    :default-sort="{prop: 'date', order: 'descending'}">
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column prop="date" label="申请时间" sortable width="160"></el-table-column>
            <el-table-column prop="prove" label="不动产权证明号" sortable width="160"></el-table-column>
            <el-table-column prop="Report" label="报件编号" sortable width="166"></el-table-column>
            <el-table-column prop="register" label="登记类型" width="100"></el-table-column>
            <el-table-column prop="obligation" label="义务人"></el-table-column>
            <el-table-column prop="address" label="坐落" :formatter="formatter" width="150"></el-table-column>
            <el-table-column prop="register_time" label="登记时间" width="166"></el-table-column>
            <el-table-column prop="remark" label="备注"></el-table-column>
            <el-table-column label="操作" width="100">
              <template scope="scope">
                <el-button type="text" size="small" @click="">查看</el-button>
                <el-button type="text" size="small" @click="info_edit(scope.$index,scope.row)">修改</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
        <div class="pages">
          <el-pagination @size-change="handleSizeChange"
                         @current-change="handleCurrentChange"
                         :current-page="currentPage"
                         :page-sizes="[10, 20, 30, 40]"
                         :page-size="10"
                         layout="total, sizes, prev, pager, next, jumper"
                         :total="400">
          </el-pagination>
        </div>
      </div>
    </div>
    <div class="time_limit">
      <span>查询期限是最近一个月</span>
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
  const ERR_OK = 0;
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
            label: '全部状态'
          }, {
            value: '待受理',
            label: '待受理'
          }, {
            value: '退回',
            label: '退回'
          }, {
            value: '待接件',
            label: '待接件'
          }, {
            value: '受理中',
            label: '受理中'
          }, {
            value: '已完成',
            label: '已完成'
          }
        ], // 状态筛选
        type: '全部',
        filter: '全部状态',
        restaurants: [], // 输入框状态提示数据
        state: '',
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
        currentPage: 1 // 当前页
      };
    },
    methods: {
      info_edit(index, row) {
        this.info_account = true;
        this.Info_Form = row;
      },
      querySearch(queryString, cb) {
        let restaurants = this.restaurants;
        let results = queryString ? restaurants.filter(this.createFilter(queryString)) : restaurants;
        // 调用 callback 返回建议列表的数据
        cb(results);
      },
      createFilter(queryString) {
        return (restaurant) => {
          return (restaurant.value.indexOf(queryString.toLowerCase()) === 0);
        };
      },
      loadAll() {
        return [
          {'value': '王发青', 'address': '长宁区新渔路144号'},
          {'value': '李发青', 'address': '上海市普陀区真北路988号创邑金沙谷6号楼113'}
        ];
      },
      handleSelect(item) {
//        console.log(item);
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
      handleSizeChange(val) {  // 分页
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      }
    },
    created() {
      this.$http.get('/api/search').then((response) => {
        response = response.body;
        if (response.errno === ERR_OK) {
          this.tableData = response.data.tableData;
        }
      });
    },
    mounted() {
      this.restaurants = this.loadAll();
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .search
    float: left
    border: 2px solid #DFE6EC;
    width: 86%
    padding-bottom: 15px
    .header
      width: 100%
      overflow: hidden
      .type, .filter, .search_btn
        float: left
        padding: 6px 15px
        margin-top: 12px
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
      .pages
        display: inline-block
        position: absolute
        left: 160px
        bottom: -2px

  .time_limit
    float: left
    width: calc(14% - 4px)
    text-align: center
    line-height: 100px
    span
      font-size: 14px


</style>

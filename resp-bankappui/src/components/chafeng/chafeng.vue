<template>
  <div class="chafeng">
    <div class="header">
      <div class="avatar">
        <img src="./time.png" alt="time">
      </div>
      <div class="content">
        <div class="checks">
          <el-radio-group v-model="radio" @change="_radiochange">
            <el-radio v-for="item in radioData" :key="item.id" :label="item.label">{{item.value}}</el-radio>
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
              @change="_endtime"
              style="width: 160px;">
            </el-date-picker>
          </div>
          <el-button class="btn_1" @click="_seartch" style="padding: 8px 30px;margin-right: 15px;margin-left: 50px;">
            查询
          </el-button>
          <el-button class="btn_2" style="padding: 8px 30px" @click="export2Excel">导出</el-button>
        </div>
      </div>
    </div>
    <div class="cf_table">
      <el-table id='_tablecontent' ref="sort_table" :data="tableData" border height="550" v-loading="tableloding"
                element-loading-text="拼命加载中..."
                @sort-change="_sortchange"
                style="width: 100%">
        <el-table-column type="index" width="60"></el-table-column>
        <el-table-column prop="bdcqzh" label="不动产权证号" sortable></el-table-column>
        <el-table-column prop="starttime" label="查封起始时间"></el-table-column>
        <el-table-column prop="endtime" label="查封结束时间"></el-table-column>
        <el-table-column prop="cfjg" label="查封机关"></el-table-column>
        <el-table-column prop="cfxztzsh" label="查封协助通知书号"></el-table-column>
        <el-table-column prop="dyr" label="抵押人"></el-table-column>
        <el-table-column prop="dyqr" label="抵押权人"></el-table-column>
        <el-table-column prop="address" label="坐落" sortable></el-table-column>
        <el-table-column prop="bdcdjzmh" label="不动产登记证明号"></el-table-column>
        <el-table-column prop="createtime" label="抵押登记时间"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        radio: '320508',
        radioData: [
          {
            label: '320508',
            value: '姑苏区'
          },
          {
            label: '320505',
            value: '虎丘区'
          }
        ],
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
        tableData: [],
        tableloding: false
      };
    },
    methods: {
      _seartch() {
        this.tableloding = true;
        this.freshData();
      },
      _radiochange(val) {
      },
      _starttime(date) {
        this.starttimevalue = date;
      },
      _endtime(date) {
        this.endtimevalue = date;
      },
      export2Excel() {
        require.ensure([], () => {
          const {export_json_to_excel} = require('../../common/pluings/excel/Export2Excel');
          const tHeader = ['不动产单元号', '查封起始时间', '查封结束时间', '查封机关', '查封协助通知书号', '抵押人', '抵押权人', '坐落', '不动产证明号', '抵押登记时间'];
          const filterVal = ['bdcdyh', 'starttime', 'endtime', 'cfjg', 'cfxztzsh', 'dyr', 'dyqr', 'address', 'bdczmh', 'createtime'];
          const list = this.tableData;
          const data = this.formatJson(filterVal, list);
          export_json_to_excel(tHeader, data, '查封查询excel');
        });
      },
      formatJson(filterVal, jsonData) {
        return jsonData.map(v => filterVal.map(j => v[j]));
      },
      freshData() {
        this.$http.post(this.$store.state.Host + '/bankSearch/getCFDJ', {
          jkzh: 200,
          district: this.radio,
          sdate: this.starttimevalue,
          edate: this.endtimevalue
        }).then((response) => {
          response = response.body;
          if (response.content === null || response.content === '') {
            this.$notify({
              title: '警告',
              message: response.message,
              type: 'error'
            });
            this.tableData = [];
            this.tableloding = false;
            return false;
          }
          if (response.code === 200) {
            let data = response.content;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                bdcqzh: data[i].bdcqzh,
                starttime: data[i].cfqssj,
                endtime: data[i].cfjssj,
                cfjg: data[i].cfjg,
                cfxztzsh: data[i].cfwh,
                dyr: data[i].dyr,
                dyqr: data[i].dyqr,
                address: data[i].zl,
                bdcdjzmh: data[i].bdcdjzmh,
                createtime: data[i].dydjsj
              };
              arr.push(json);
            }
            this.tableloding = false;
            this.tableData = arr;
          } else {
            this.$notify({
              title: '警告',
              message: '登录超时，请重新登录。',
              type: 'error'
            });
            this.tableData = [];
            this.tableloding = false;
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
          this.tableloding = false;
        });
      },
      _sortchange(colum) {
        let data = this.$refs.sort_table.tableData;
        this._gettablelist(data);
      },
      _gettablelist(row) {
        let arr = [];
        let repeatarr = [];
        let trastarr = [];
        let colorlist = [
          {
            bgcolor: '#d9e0f7',
            brclass: 'td-color1'
          },
          {
            bgcolor: '#c4ead7',
            brclass: 'td-color2'
          }
        ];
        for (let i = 0; i < row.length; i++) {
          arr.push(row[i].bdcqzh);
        }
        let newarr = arr.join(',') + ',';
        for (let k = 0; k < row.length; k++) {
          if (newarr.replace(row[k].bdcqzh + ',', '').indexOf(row[k].bdcqzh) > -1) {
            let json = {
              Indexkey: k,
              value: row[k].bdcqzh
            };
            repeatarr.push(json);
            if (trastarr.indexOf(row[k].bdcqzh) === -1) {
              trastarr.push(row[k].bdcqzh);
            }
          }
        }
        this.$nextTick(() => {
          let tableID = document.getElementById('_tablecontent'); // el-table__row
          let tablelist = tableID.getElementsByClassName('el-table__row');
          let cleartdclass = '';
          for (let f = 0; f < tablelist.length; f++) {  // 清除表格样式
            cleartdclass = tablelist[f].getElementsByTagName('td');
            tablelist[f].style.background = '#fff';
            for (let r = 0; r < cleartdclass.length; r++) {
              cleartdclass[r].className = '';
            }
          }
          for (let j = 0; j < trastarr.length; j++) {
            for (let h = 0; h < repeatarr.length; h++) {
              if (trastarr[j] === repeatarr[h].value) {
                let index = parseInt(j % 2);
                let tabIndex = repeatarr[h].Indexkey;
                let tdlist = tablelist[tabIndex].getElementsByTagName('td');
                tablelist[tabIndex].style.background = colorlist[index].bgcolor;
                for (let g = 0; g < tdlist.length; g++) {
                  tdlist[g].className = colorlist[index].brclass;
                }
              }
            }
          }
        });
      }
    },
    created() {
      this.$http.get(this.$store.state.Host + '/bankSearch/getDistricts').then((response) => {
        response = response.body;
        if (response.code === 200) {
          let arr = [];
          let data = response.content.body;
          for (var i = 0; i < data.length; i++) {
            let json = {
              label: data[i].code,
              value: data[i].name
            };
            arr.push(json);
          }
          this.radioData = arr;
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
    activated() {
      this.tableloding = false;
    },
    watch: {
      tableData: {
        handler(val, oldval) {
          this._gettablelist(val);
        },
        deep: true
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .chafeng
    width: 100%
    padding-bottom: 100px
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
      .el-table .td-color1
        border-right: 1px solid #eceef5
        border-top: 1px solid #d9e0f7
      .el-table .td-color2
        border-right: 1px solid #d9e0f7
        border-top: 1px solid #c4ead7
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

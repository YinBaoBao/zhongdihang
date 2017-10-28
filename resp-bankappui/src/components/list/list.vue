<template>
  <div>
    <div class="List_data">
      <div class="header">
        <div class="auto">
          <el-table ref="multipleTable" height="260" :data="upDatalist" highlight-current-row
                    @current-change="needCurrentChange"
                    :row-class-name="tableRowClassName"
                    @select-all="_selectall"
                    @selection-change="_Selectionchange" style="width: 100%">
            <el-table-column type="selection" width="36"></el-table-column>
            <el-table-column property="mlmc" label="需上传资料"></el-table-column>
            <el-table-column property="mlwjsl" label="数量" width="70"></el-table-column>
          </el-table>
        </div>
        <div class="btn">
          <el-upload style="display: inline-block;width: 128px;"
                     class="upload-demo"
                     :headers="{'token':headerstoken,'username': haedersusername}"
                     :action="action"
                     accept=".png,.jpg,.xls,.xlsx,.doc,.docx,.pdf,.flv,.mov"
                     :show-file-list="false"
                     :on-success="_onchange">
            <el-button @click="_updata_submit(upDatalist[updataindex])" style="padding: 6px 18px;">上传材料</el-button>
            <div v-show="false" slot="tip" class="el-upload__tip"></div>
          </el-upload>
        </div>
      </div>
      <div class="uploaded">
        <div class="auto">
          <el-table ref="uploaded" height="180" :data="uploaded" highlight-current-row
                    @row-dblclick="_doubleclick"
                    style="width: 100%">
            <el-table-column type="index" width="50"></el-table-column>
            <el-table-column property="state" label="已上传资料" style="text-align: center;"></el-table-column>
            <el-table-column label="操作" width="70">
              <template scope="scope">
                <el-button type="text" size="small" @click="_deleteuploaded(scope.$index,scope.row)">删除</el-button>
              </template>
            </el-table-column>
          </el-table>
        </div>
      </div>
      <div class="border-height"></div>
      <div class="service">
        <div class="check">
          <span @click="_Look">查看登记申请书</span>
        </div>
        <div class="btns">
          <el-button style="padding: 6px 4px;margin: 6px 0 10px 15px;" @click="_apply_submit">提交登记申请</el-button>
          <el-button style="padding: 6px 4px;margin: 8px 0 10px 15px;" @click="_createnewapply">新建申请</el-button>
          <el-button style="padding: 6px 4px;margin: 6px 0 20px 15px;" @click="_Printapply">打印登记申请书</el-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  export default {
    props: {
      upDatalist: {
        type: Array,
        default() {
          return [
            {
              bjbh: '',
              index: '',
              mlmc: '',
              mlwjlx: '',
              mlwjsl: '',
              mlxh: ''
            }
          ];
        }
      }
    },
    data() {
      return {
        uploaded: [
          {
            state: '',
            wjmlxh: '',
            wjxh: ''
          }
        ],   // 已上传资料对应的文件
        fileList: [],   // 上传文件
        currentRow: null,
        updataindex: 0,
        deletindex: null,
        state: '',
        timeout: null,
        action: '',
        updatas: '',
        headerstoken: '',
        haedersusername: '',
        filename: '',
        Bjbh: '',
        mlxh: ''
      };
    },
    computed: {
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
      _apply_submit() {
        this.$emit('apply_submit');
      },
      _createnewapply() {
        this.$confirm('是否新建项目?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.$emit('newcreateapply');
        });
      },
      _Selectionchange(row) {
        if (row.length === 0) {
          return false;
        }
        this.Bjbh = row[0].bjbh;
        this.mlxh = row[0].mlxh;
        this.action = this.$store.state.Host + '/BDCDJSQControl/saveQYCL/200/' + this.Bjbh + '/' + this.mlxh;
      },
      _selectall(row) {
        this.$refs.multipleTable.clearSelection();
        return false;
      },
      tableRowClassName(row, index) { // 向行添加索引值
        row.index = index;
      },
      needCurrentChange(val) {
        this.$refs.multipleTable.clearSelection();
        if (val === null || val === '') {
          return false;
        }
        this.currentRow = val;
        this.updataindex = this.currentRow.id;
        this.deletindex = this.currentRow.index;
        this.$refs.multipleTable.toggleRowSelection(this.upDatalist[val.index]);
      },
      _deleteuploaded(index, row) { // 删除行
        this.$confirm('是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          let uploaded = this.uploaded;
//        console.log(uploaded[index]);
          this.$http.post(this.$store.state.Host + '/BDCDJSQControl/deleteQYCL', {
            jkzh: 200,
            bjbh: this.Bjbh,
            qyclmlxh: uploaded[index].wjmlxh,
            qyclxh: uploaded[index].wjxh
          }).then((response) => {
            response = response.body;
            if (response.status === '200') {
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success'
              });
              uploaded.splice(index, 1);
            } else {
              this.$notify.error({
                title: '错误',
                message: '删除失败'
              });
            }
          });
        });
      },
      _onchange(response, file, fileList) {
        console.log(fileList);
        if (response.status === '200') {
          switch (response.body.status) {
            case '200':
              this.$notify({
                title: '提示',
                message: '上传成功',
                type: 'success'
              });
              let json = {
                state: response.body.body.wjmlmc + '(' + response.body.body.wjxh + ')',
                wjmlxh: response.body.body.wjmlxh,
                wjxh: response.body.body.wjxh,
                bjbh: response.body.body.bjbh
              };
              this.uploaded.splice(-1, 0, json);
              break;
            case '40020502':
              this.$notify({
                title: '警告',
                message: '已上传，不可重复提交。',
                type: 'error'
              });
              break;
          }
        } else {
          this.$notify({
            title: '警告',
            message: '上传失败',
            type: 'error'
          });
        }
      },
      _Printapply() {
        this.$router.push({path: '/print'});
        this.$nextTick(() => {
          window.print();
          this.$router.push({path: '/index'});
        });
      },
      _doubleclick(row) {
        this.$http.get(this.$store.state.Host + '/BDCDJSQControl/getFileToken').then((response) => {
          response = response.body;
          let token = response.message;
          window.open('http://10.5.0.228:9090' + '/200/bdcdj/' + row.bjbh + '/qycls/' + row.wjmlxh + '/' + row.wjxh + '?access_token=' + token + '');
//          this.$http.get('http://10.5.0.228:9090' + '/200/bdcdj/' + row.bjbh + '/qycls/' + row.wjmlxh + '/' + row.wjxh + '', {
//            params: {
//              access_token: token
//            }
//          }, {emulateJSON: true}).then((response) => {
//            response = response.body;
//            let reader = new FileReader();
//            reader.readAsText(response, 'UTF-16');
//          });
        });
        let files = new FileReader();
        console.log(files);
      }
    },
    created() {
      this.headerstoken = localStorage.getItem('headertoken');
      this.haedersusername = localStorage.getItem('username');
    },
    watch: {
      upDatalist: {
        handler(val, oldVal) {
          let arr = [{state: '', wjmlxh: '', wjxh: ''}];
          this.uploaded = arr;
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
    .el-table td, .el-table th
      height: 32px
</style>

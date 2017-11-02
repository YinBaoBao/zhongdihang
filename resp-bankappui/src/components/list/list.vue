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
            <el-table-column type="selection" width="36">
            </el-table-column>
            <el-table-column property="mlmc" label="需上传资料"></el-table-column>
            <el-table-column property="mlwjsl" label="数量" width="70"></el-table-column>
          </el-table>
        </div>
        <div class="btn">
          <el-upload style="display: inline-block;width: 128px;"
                     ref="uploud"
                     class="upload-demo"
                     multiple
                     :headers="{'token':headerstoken,'username': haedersusername}"
                     :action="action"
                     accept=".png,.jpg,.doc,.docx,.pdf,.flv"
                     :on-success="_onchange">
            <el-button type="primary" @click="_updata_submit(upDatalist[updataindex])" style="padding: 6px 18px;">上传材料
            </el-button>
            <div slot="tip" class="el-upload__tip"></div>
          </el-upload>
        </div>
      </div>
      <div class="uploaded">
        <div class="auto">
          <el-table ref="uploaded" height="180" :data="uploaded" highlight-current-row
                    @row-dblclick="_doubleclick"
                    style="width: 100%">
            <el-table-column type="index" width="70"></el-table-column>
            <el-table-column property="state" label="已上传资料" style="text-align: center;"></el-table-column>
            <el-table-column label="操作" width="70">
              <template scope="scope">
                <el-button type="text" size="small" @click="_deleteuploaded(scope.$index,scope.row)"><i
                  class="el-icon-delete2"></i></el-button>
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
      <div class="lookcontent" ref="Looked">
        <img :src="Imageurl" alt="">
        <!--<a :href="Imageurl" download="a"></a>-->
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
      },
      bjblztmc: {
        type: String
      }
    },
    data() {
      return {
        uploaded: [],   // 已上传资料对应的文件
        checked: true,
        fileList: [],   // 上传文件
        currentRow: null,
        updataindex: 0,
        deletindex: null,
        upspiceIndex: '',
        state: '',
        timeout: null,
        action: '',
        updatas: '',
        headerstoken: '',
        haedersusername: '',
        filename: '',
        Bjbh: '',
        mlxh: '',
        Imageurl: ''
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
      _onchange(response, file, fileList) {
//        console.log(response);
        if (response.status === '200') {
          switch (response.body.status) {
            case '200':
              let json = {
                state: response.body.body.wjmlmc,
                wjmlmc: response.body.body.wjmlmc,
                wjmlxh: response.body.body.wjmlxh,
                wjunid: response.body.body.wjunid,
                wjxh: response.body.body.wjxh,
                bjbh: response.body.body.bjbh
              };
              let index = this.uploaded.length;
              this.uploaded.splice(index, 0, json);
              break;
            case '40020502':
              this.$notify({
                title: '警告',
                message: '已上传，不可重复提交。',
                type: 'error'
              });
              break;
            case '40020206':
              this.$notify({
                title: '警告',
                message: response.body.message,
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
        setTimeout(_ => {
          this.$refs.uploud.clearFiles();
        }, 1500);
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
        this.upspiceIndex = row[0].index;
        this.action = this.$store.state.Host + '/BDCDJSQControl/saveQYCL/200/' + this.Bjbh + '/' + this.mlxh + '/' + this.mlxh;
//        console.log(row);
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/findOneQYCL', {
          jkzh: 200,
          bjbh: row[0].bjbh,
          wjmlxh: row[0].mlxh
        }).then((response) => {
          response = response.body;
          if (response.body.body === '' || response.body.body === null) {
            return false;
          }
          if (response.status === '200') {
            let arr = [];
            let data = response.body.body;
            for (var i = 0; i < data.length; i++) {
              let json = {
                state: data[i].wjmlmc + '(' + (i + 1) + ')',
                wjmlxh: data[i].wjmlxh,
                wjmlmc: data[i].wjmlmc,
                wjxh: data[i].wjxh,
                bjbh: data[i].bjbh
              };
              arr.push(json);
            }
            this.uploaded = arr;
          }
        });
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
        if (this.bjblztmc === '待受理' || this.bjblztmc === '待接件' || this.bjblztmc === '已完成') {
          this.$message({
            message: this.bjblztmc + '...',
            type: 'warning'
          });
          return false;
        }
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
      _Printapply() {
        this.$emit('Print');
      },
      _doubleclick(row) {
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/findOneQYCL', {
          jkzh: 200,
          bjbh: row.bjbh,
          wjmlxh: row.wjxh
        }).then((response) => {
          response = response.body;
          this.$http.post(this.$store.state.Host + '/BDCDJSQControl/getFile', {
            jkzh: 200,
            bjbh: response.body.body[0].bjbh,
            wjmlxh: response.body.body[0].wjmlxh,
            wjunid: response.body.body[0].wjunid
          }).then((response) => {
            response = response.body;
//            window.open(response.message);
//            this.Imageurl = response.message;
          });
        });
      }
    },
    created() {
//      console.log(this.$refs.Looked);
      this.headerstoken = localStorage.getItem('headertoken');
      this.haedersusername = localStorage.getItem('username');
    },
    watch: {
      upDatalist: {
        handler(val, oldVal) {
          this.uploaded = [];
        },
        deep: true
      }
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .List_data
    width: 100%
    height: 100%
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
    .lookcontent
      position: fixed
      left: 20%
      top: 1%;
</style>

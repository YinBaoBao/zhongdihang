<template>
  <div>
    <div class="List_data">
      <div class="header">
        <div class="auto">
          <div class="t_head">
            <img class="t_point" src="./purple.png" alt="purple">
            <span class="t_title">需上传资料</span>
            <span class="t_num">数量</span>
          </div>
          <el-table ref="multipleTable" height="280" :show-header="false" :data="upDatalist" highlight-current-row
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
            <el-button @click="_updata_submit(upDatalist[updataindex])" style="padding: 6px 18px;">上传资料
            </el-button>
            <div slot="tip" class="el-upload__tip"></div>
          </el-upload>
        </div>
      </div>
      <div class="uploaded">
        <div class="auto">
          <div class="t_head">
            <img class="t_point" src="./purple.png" alt="purple">
            <span class="t_title">已上传资料</span>
          </div>
          <el-table ref="uploaded" height="200" :show-header="false" :data="uploaded" highlight-current-row
                    @row-dblclick="_doubleclick"
                    style="width: 100%">
            <el-table-column type="index" width="36"></el-table-column>
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
      <div class="service">
        <ul class="btns">
          <li>
            <a class="btn_a" href="javascript:;" @click="_apply_submit"><img src="./submit.png" alt="submit"></a>
            <p class="btn_text">保存并提交登记申请</p>
          </li>
          <li>
            <a class="btn_a" href="javascript:;" @click="_createnewapply"><img src="./create.png" alt="submit"></a>
            <p class="btn_text">新建申请</p>
          </li>
          <li>
            <a class="btn_a" href="javascript:;" @click="_Printapply"><img src="./Print.png" alt="submit"></a>
            <p class="btn_text">打印登记申请</p>
          </li>
        </ul>
        <div class="look">
          <span @click="_Look">查看登记申请书</span>
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
        this.$confirm('是否新建申请?', '提示', {
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
                index: i,
                state: data[i].wjmlmc + '(' + (i + 1) + ')',
                wjmlxh: data[i].wjmlxh,
                wjmlmc: data[i].wjmlmc,
                wjxh: data[i].wjxh,
                bjbh: data[i].bjbh,
                wjunid: data[i].wjunid
              };
              arr.push(json);
            }
            this.uploaded = arr;
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
            message: this.bjblztmc + '中...',
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
            wjunid: uploaded[index].wjunid
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
        this.$confirm('打印后页面数据将清空,请保存申请后在打印，是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          setTimeout(_ => {
            this.$emit('Print');
          }, 500);
        });
      },
      _doubleclick(row) {
        let index = row.index;
        this.$http.post(this.$store.state.Host + '/BDCDJSQControl/findOneQYCL', {
          jkzh: 200,
          bjbh: row.bjbh,
          wjmlxh: row.wjxh
        }).then((response) => {
          response = response.body;
          this.$http.post(this.$store.state.Host + '/BDCDJSQControl/getFile', {
            jkzh: 200,
            bjbh: response.body.body[index].bjbh,
            wjmlxh: response.body.body[index].wjmlxh,
            wjunid: response.body.body[index].wjunid
          }).then((response) => {
            response = response.body;
//            let a = document.createElement('a');
//            let Img = document.createElement('img');
//            Img.style.flex = 'fixed';
//            a.herf = response.message;
//            a.download = '预览';
//            Img.src = response.message;
//            document.body.appendChild(Img);
//            document.body.appendChild(a);
            window.open(response.message, 'toolbar = no, menubar = no, scrollbars = no, resizable = no, location = no, status = no');
//            window.opener = '';
//            let tempForm = document.createElement('form');
//            tempForm.id = 'tempForm1';
//            tempForm.method = 'post';
//            tempForm.action = response.message;
//            tempForm.target = name;
//            let hideInput = document.createElement('input');
//            hideInput.type = 'hidden';
//            hideInput.name = 'categoryid';
//            tempForm.appendChild(hideInput);
//            let hideInput2 = document.createElement('input');
//            hideInput2.type = 'hidden';
//            hideInput2.name = 'reportId';
//            tempForm.appendChild(hideInput2);
          });
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
    .t_head
      border: 1px solid #DFE6EC
      border-bottom: none
      width: calc(100% - 2px)
      height: 34px
      line-height: 34px
      font-size: 14px
      background: #eef2f6
      .t_point
        width: 12px
        height: 12px
        margin-left: 15px
        margin-right: 24px
        vertical-align: middle
      .t_title
        vertical-align: middle
      .t_num
        float: right
        margin-right: 30px
        font-size: 14px
        vertical-align: middle
    .header
      width: 100%
      .auto
        overflow: hidden
        .el-checkbox__inner
          width: 16px
          height: 16px
      .btn
        border-left: 1px solid #DFE6EC
        border-right: 1px solid #DFE6EC
        text-align: center
        padding: 12px 10px
        .el-button
          border-color: #148583
          background: #148583
          span
            color: #fff
        .el-button:active
          border-color: #148583
        .el-button:hover
          border-color: #148583
    .uploaded
      text-align: left
      .auto
        overflow: hidden
    .service
      border: 1px solid #DFE6EC
      margin-top: 10px
      .look
        width: 100%
        text-align: right
        span
          display: inline-block
          padding: 8px 10px
          margin: 14px 0 16px 0
          font-size: 14px
          text-decoration: underline
          color: #949494
          cursor: pointer
      .btns
        width: 100%
        margin-top: 22px
        text-align: center
        li
          display: inline-block
          text-align: center
          vertical-align: top
          @media all and (max-width: 1366px)
            width: 86px
          @media all and (min-width: 1367px)
            width: 90px
          .btn_a
            display: inline-block
            border-radius: 50%
            img
              display: block
              width: 60px
              height: 60px
          .btn_text
            margin-top: 15px
            font-size: 14px
            color: #3a3a3a
    .el-table td, .el-table th
      height: 32px
    .lookcontent
      position: fixed
      left: 20%
      top: 1%;
</style>

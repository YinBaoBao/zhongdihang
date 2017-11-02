<template>
  <div class="handsontable">
    <el-table :data="tableData" border style="width: 100%">
      <el-table-column type="index"></el-table-column>
      <el-table-column prop="applyname" label="申请人" width="150"></el-table-column>
      <el-table-column prop="username" label="姓名或名称" width="120"></el-table-column>
      <el-table-column prop="zjlb" label="证件类别" width="120"></el-table-column>
      <el-table-column prop="zjh" label="证件号" width="120"></el-table-column>
      <el-table-column prop="address" label="联系地址"></el-table-column>
      <el-table-column prop="phone" label="联系电话" width="160"></el-table-column>
      <el-table-column label="操作" width="100">
        <template scope="scope">
          <el-button @click="_edit_apply(scope.$index,scope.row)" type="text" size="small">修改</el-button>
          <el-button @click="_delet_apply(scope.$index,scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-button @click="_add_apply" :plain="true" type="info" style="padding: 9px 14px;margin-top: 14px;">
      <i class="el-icon-plus" style="padding-right: 4px;"></i>{{add_Title}}
    </el-button>
    <div class="dialog">
      <el-dialog :title="add_Title" :visible.sync="add_apply" size="000" top="6%" :modal="false"
                 :close-on-click-modal="false">
        <el-form :model="AddForm" :rules="rules" ref="AddForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="申请人性质" prop="applyvalue">
            <el-select v-model="AddForm.applyvalue" :disabled="Disablel" placeholder="请选择" @change="_qlrzlchange">
              <el-option
                v-for="item in applyname"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="权利人类型" prop="qlrlxvalue">
            <el-select v-model="AddForm.qlrlxvalue" placeholder="请选择" @change="_qlrlxchange">
              <el-option
                v-for="item in qlrlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="姓名或名称" prop="username">
            <el-input v-model="AddForm.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="zjlxvalue">
            <el-select v-model="AddForm.zjlxvalue" placeholder="请选择" @change="_zjlxchange">
              <el-option
                v-for="item in zjlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="证件号" prop="zjh">
            <el-input v-model="AddForm.zjh"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="AddForm.address"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="telephone">
            <el-input v-model="AddForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="add_apply = false">关 闭</el-button>
          <el-button type="primary" @click="_add_apply_submit('AddForm')">保 存</el-button>
        </span>
      </el-dialog>
      <el-dialog title="修改申请人" :visible.sync="edit_apply" size="000" top="6%" :modal="false"
                 :close-on-click-modal="false">
        <el-form :model="AddForm" :rules="rules" ref="AddForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="申请人性质" prop="applyvalue">
            <el-select v-model="AddForm.applyvalue" placeholder="请选择" @change="_qlrzlchange">
              <el-option
                v-for="item in applyname"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="申请人类型" prop="qlrlxvalue">
            <el-select v-model="AddForm.qlrlxvalue" placeholder="请选择" @change="_qlrlxchange">
              <el-option
                v-for="item in qlrlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="姓名或名称" prop="username">
            <el-input v-model="AddForm.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="zjlxvalue">
            <el-select v-model="AddForm.zjlxvalue" placeholder="请选择" @change="_zjlxchange">
              <el-option
                v-for="item in zjlx"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="证件号" prop="zjh">
            <el-input v-model="AddForm.zjh"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="AddForm.address"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="telephone">
            <el-input v-model="AddForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="edit_apply = false">关 闭</el-button>
          <el-button type="primary" @click="_edit_apply_submit('AddForm')">保 存</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    props: {
      Handdata: {
        default() {
          return {
            bjbh: '201708200000004',
            qlrdh: '138000000000',
            qlrdz: '苏州工业园区星海街128号',
            qlrlx: 2,
            qlrlxmc: '企业',
            qlrmc: '中国建设银行苏州分行',
            qlrunid: '27e12405d57447038b181b2eb6f0c8a1',
            qlrxh: 1,
            qlrzjh: '123456789012345678',
            qlrzjzl: 7,
            qlrzjzlmc: '营业执照',
            qlrzl: 2,
            qlrzlmc: '抵押权人'
          };
        }
      },
      Title: {
        type: String,
        default() {
          return '申请人';
        }
      },
      bjbh: {}
    },
    data() {
      return {
        tableData: [
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          },
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          },
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          },
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          }
        ],
        tableTitle: [],
        add_Title: '添加' + this.Title,
        Disablel: false,
        add_apply: false,
        edit_apply: false,
        AddForm: {
          applyvalue: '',
          username: '',
          qlrlxvalue: '',
          zjlxvalue: '',
          zjh: '',
          address: '',
          telephone: ''
        },
        applyname: [
          {
            code: '',
            value: ''
          }
        ],
        qlrlx: [
          {
            code: '',
            value: ''
          }
        ],
        zjlx: [
          {
            code: '',
            value: ''
          }
        ],
        applycode: '',
        qlrlxcode: '',
        zjlxcode: '',
        qlrxh: null,
        editIndex: null,
        rules: {
          applyvalue: [
            {required: true, message: '请选择申请人', trigger: 'change'}
          ],
          qlrlxvalue: [
            {required: true, message: '请选择权利人类型', trigger: 'change'}
          ],
          username: [
            {required: true, message: '请输入姓名或名称', trigger: 'change'}
          ],
          zjlxvalue: [
            {required: true, message: '请选择证件类型', trigger: 'change'}
          ],
          zjh: [
            {required: true, message: '请输入证件号', trigger: 'change'}
          ],
          address: [
            {required: true, message: '请输入地址', trigger: 'change'}
          ],
          telephone: [
            {required: true, message: '请输入手机号', trigger: 'change'},
            {type: 'string', message: '请输入正确的手机号码', pattern: /^1[0-9]{10}$/, trigger: 'blur,change'}
          ]
        }
      };
    },
    methods: {
      _add_apply() {  // 添加申请人
        switch (this.Title) {
          case '权利人':
            this.Disablel = false;
            break;
          case '义务人':
            this.Disablel = true;
            this.AddForm.applyvalue = '不动产权利人';
            this.applycode = '1';
            break;
        }
        this.add_apply = true;
        this.getqlrzl();
        this.getqlrlx();
        this.getzjlx();
      },
      _add_apply_submit(AddForm) {
        this.$refs[AddForm].validate((valid) => {
          if (valid) {
            switch (this.Title) {
              case '权利人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/saveQlr', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  qlrzl: this.applycode,
                  qlrlx: this.qlrlxcode,
                  qlrmc: this.AddForm.username,
                  qlrzjzl: this.zjlxcode,
                  qlrzjh: this.AddForm.zjh,
                  qlrdz: this.AddForm.address,
                  qlrdh: this.AddForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.body === null) {
                    this.$notify({
                      title: '警告',
                      message: '保存失败，请生成报件编号。',
                      type: 'error'
                    });
                    this.add_apply = false;
                    return false;
                  }
                  switch (response.status) {
                    case '200':
                      this.$notify({
                        title: '提示',
                        message: '保存成功',
                        type: 'success'
                      });
                      this.Handdata.splice(-1, 0, response.body.body);
                      this.Handata();
                      this.add_apply = false;
                      break;
                    default:
                      this.$notify({
                        title: '警告',
                        message: '保存失败',
                        type: 'error'
                      });
                      this.add_apply = false;
                  }
                });
                break;
              case '代理人':
                break;
              case '义务人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/saveObligor', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  ywrzl: this.applycode,
                  ywrlx: this.qlrlxcode,
                  ywrmc: this.AddForm.username,
                  ywrzjzl: this.zjlxcode,
                  ywrzjh: this.AddForm.zjh,
                  ywrdz: this.AddForm.address,
                  ywrdh: this.AddForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.body === null) {
                    this.$notify({
                      title: '警告',
                      message: '保存失败，请生成报件编号。',
                      type: 'error'
                    });
                    this.add_apply = false;
                    return false;
                  }
                  switch (response.status) {
                    case '200':
                      this.$notify({
                        title: '提示',
                        message: '保存成功',
                        type: 'success'
                      });
                      this.Handdata.splice(-1, 0, response.body.body);
                      this.Handata();
                      this.add_apply = false;
                      break;
                    default:
                      this.$notify({
                        title: '警告',
                        message: '保存失败',
                        type: 'error'
                      });
                      this.add_apply = false;
                  }
                });
                break;
            }
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
        });
      },
      getqlrzl() {    // 获取权利人种类
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10007
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                value: data[i].name
              };
              arr.push(json);
            }
            this.applyname = arr;
          }
        });
      },
      _qlrzlchange(val) {
        let options = this.applyname;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            this.applycode = options[i].code;
          }
        }
      },
      getqlrlx() {    // 获取权利人类型
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          code: 10005
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                value: data[i].name
              };
              arr.push(json);
            }
            this.qlrlx = arr;
          }
        });
      },
      _qlrlxchange(val) {
        let options = this.qlrlx;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            this.qlrlxcode = options[i].code;
          }
        }
      },
      getzjlx() {    // 获取证件种类
        let token = localStorage.getItem('login_token');
        this.$http.post(this.$store.state.Host + '/TokrnControl/getzdb', {
          access_token: token,
          code: 130
        }).then((response) => {
          response = response.body;
          if (response.status === '200') {
            let data = response.body;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                code: data[i].code,
                value: data[i].name
              };
              arr.push(json);
            }
            this.zjlx = arr;
          }
        });
      },
      _zjlxchange(val) {
        let options = this.zjlx;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            this.zjlxcode = options[i].code;
          }
        }
      },
      _edit_apply(index, row) {
        let handata = this.Handdata;
        switch (this.Title) {
          case '权利人':
            this.AddForm.username = handata[index].qlrmc;
            this.AddForm.zjh = handata[index].qlrzjh;
            this.AddForm.address = handata[index].qlrdz;
            this.AddForm.telephone = handata[index].qlrdh;
            this.AddForm.applyvalue = handata[index].qlrzlmc;
            this.AddForm.qlrlxvalue = handata[index].qlrlxmc;
            this.AddForm.zjlxvalue = handata[index].qlrzjzlmc;
            this.qlrxh = handata[index].qlrxh;
            this.applycode = handata[index].qlrzl;
            this.zjlxcode = handata[index].qlrzjzl;
            this.qlrlxcode = handata[index].qlrlx;
            break;
          case '权利人代理人':
            break;
          case '义务人':
            this.AddForm.username = handata[index].ywrmc;
            this.AddForm.zjh = handata[index].ywrzjh;
            this.AddForm.address = handata[index].ywrdz;
            this.AddForm.telephone = handata[index].ywrdh;
            this.AddForm.applyvalue = handata[index].ywrzlmc;
            this.AddForm.qlrlxvalue = handata[index].ywrlxmc;
            this.AddForm.zjlxvalue = handata[index].ywrzjzlmc;
            this.qlrxh = handata[index].ywrxh;
            this.applycode = handata[index].ywrzl;
            this.zjlxcode = handata[index].ywrzjzl;
            this.qlrlxcode = handata[index].ywrlx;
            break;
          case '义务人代理人':
            break;
        }
        this.editIndex = index;
        this.getqlrzl();
        this.getqlrlx();
        this.getzjlx();
        this.edit_apply = true;
      },
      _edit_apply_submit(AddForm) {
        let index = this.editIndex;
        this.$refs[AddForm].validate((valid) => {
          if (valid) {
            switch (this.Title) {
              case '权利人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/updateQlr', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  qlrxh: this.qlrxh,
                  qlrzl: this.applycode,
                  qlrlx: this.qlrlxcode,
                  qlrmc: this.AddForm.username,
                  qlrzjzl: this.zjlxcode,
                  qlrzjh: this.AddForm.zjh,
                  qlrdz: this.AddForm.address,
                  qlrdh: this.AddForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.status === '200') {
                    this.$notify({
                      title: '提示',
                      message: '修改成功',
                      type: 'success'
                    });
                    this.Handdata[index] = response.body.body;
                    console.log(this.Handdata);
                    this.Handata();
                    this.edit_apply = false;
                  } else {
                    this.$notify({
                      title: '警告',
                      message: '修改失败',
                      type: 'error'
                    });
                    this.edit_apply = false;
                  }
                });
                break;
              case '代理人':
                break;
              case '义务人':
                this.$http.post(this.$store.state.Host + '/BDCDJSQControl/updateOnligor', {
                  jkzh: 200,
                  bjbh: this.bjbh,
                  access_token: token,
                  ywrxh: this.qlrxh,
                  ywrzl: this.applycode,
                  ywrlx: this.qlrlxcode,
                  ywrmc: this.AddForm.username,
                  ywrzjzl: this.zjlxcode,
                  ywrzjh: this.AddForm.zjh,
                  ywrdz: this.AddForm.address,
                  ywrdh: this.AddForm.telephone
                }).then((response) => {
                  response = response.body;
                  if (response.status === '200') {
                    this.$notify({
                      title: '提示',
                      message: '修改成功',
                      type: 'success'
                    });
                    this.Handdata[index] = response.body.body;
//                    console.log(this.Handdata);
                    this.Handata();
                    this.edit_apply = false;
                  } else {
                    this.$notify({
                      title: '警告',
                      message: '修改失败',
                      type: 'error'
                    });
                    this.edit_apply = false;
                  }
                });
                break;
            }
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
        });
      },
      _delet_apply(index, row) {
        let handata = this.Handdata;
        let xh = handata[index].qlrxh;
        let token = localStorage.getItem('login_token');
        switch (this.Title) {
          case '权利人':
            this.$http.post(this.$store.state.Host + '/BDCDJSQControl/deleteQlr', {
              jkzh: '200',
              bjbh: this.bjbh,
              access_token: token,
              qlrxh: xh
            }).then((response) => {
              response = response.body.body;
              switch (response.status) {
                case '200':
                  this.$notify({
                    title: '提示',
                    message: '删除成功',
                    type: 'success'
                  });
                  this.Handata(index);
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '删除失败',
                    type: 'error'
                  });
              }
            });
            break;
          case '代理人':
            break;
          case '义务人':
            this.$http.post(this.$store.state.Host + '/BDCDJSQControl/deleteonlior', {
              jkzh: '200',
              bjbh: this.bjbh,
              access_token: token,
              qlrxh: xh
            }).then((response) => {
              response = response.body;
              switch (response.status) {
                case '200':
                  this.$notify({
                    title: '提示',
                    message: '删除成功',
                    type: 'success'
                  });
                  this.Handata(index);
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '删除失败',
                    type: 'error'
                  });
              }
            });
            break;
        }
      },
      handleClick(index, row) {
      },
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      Handata(index) {  // 表格对应数据
        let arr = [
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          },
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          },
          {
            applyname: '',
            username: '',
            zjlb: '',
            zjh: '',
            address: '',
            phone: ''
          }
        ];
        switch (this.Title) {
          case '权利人':
            if (index !== undefined) {
              this.Handdata.splice(index, 1);
            }
            let Handdata = this.Handdata;
            for (var i = 0; i < Handdata.length; i++) {
              let json = {
                applyname: Handdata[i].qlrzlmc,
                username: Handdata[i].qlrmc,
                zjlb: Handdata[i].qlrzjzlmc,
                zjh: Handdata[i].qlrzjh,
                address: Handdata[i].qlrdz,
                phone: Handdata[i].qlrdh
              };
              arr.splice(i, 0, json);
            }
            this.tableData = arr;
            break;
          case '代理人':
            break;
          case '义务人':
            if (this.Handdata.length !== 0) {
              if (index !== undefined) {
                this.Handdata.splice(index, 1);
              }
              let Handdata = this.Handdata;
//              console.log(Handdata);
              for (var k = 0; k < Handdata.length; k++) {
                let json = {
                  applyname: Handdata[k].ywrzlmc,
                  username: Handdata[k].ywrmc,
                  zjlb: Handdata[k].ywrzjzlmc,
                  zjh: Handdata[k].ywrzjh,
                  address: Handdata[k].ywrdz,
                  phone: Handdata[k].ywrdh
                };
                arr.splice(k, 0, json);
              }
              this.tableData = arr;
            }
            break;
        }
      }
    },
    created() {
      console.log(this.Handdata);
      this.Handata();
    },
    watch: {
      Handdata: {
        handler(val, oldVal) {
        },
        deep: true
      }
    },
    components: {}
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .handsontable
    width: 1000px

  .dialog
    height: 66px
    .demo-ruleForm
      width: 660px
      overflow: hidden
      .el-form-item
        display: inline-block
        .el-form-item__content
          width: 212px
</style>

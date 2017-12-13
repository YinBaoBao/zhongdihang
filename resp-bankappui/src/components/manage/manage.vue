<template>
  <div class="manage">
    <div class="content">
      <div class="text">
        <img class="img_point" src="./account.png" alt="account">
        <span class="title">管理分支行</span>
        <span class="add" @click="_add_account">新增 <i class="el-icon-plus"></i></span>
      </div>
      <div class="manage_tables">
        <el-table :data="manageData" border height="550" style="width: 100%">
          <el-table-column type="index" width="60"></el-table-column>
          <el-table-column prop="name" label="一级分支行" sortable></el-table-column>
          <el-table-column prop="name1" label="二级分支行"></el-table-column>
          <el-table-column prop="name2" label="三级分支行"></el-table-column>
          <el-table-column prop="account" label="银行代码"></el-table-column>
          <el-table-column label="操作" width="160">
            <template scope="scope">
              <el-button v-if="scope.row.name2 === ''" type="text" size="small"
                         @click="_add_nextbank(scope.$index,scope.row)">增加下级分支行
              </el-button>
              <el-button type="text" size="small" @click="_delet_bank(scope.$index,scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </div>
    <div class="manage_dialog">
      <el-dialog title="新增账号" :visible.sync="add_account" size="000">
        <div style="width: 370px;padding-right: 15px;">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="accountNumber">
              <el-input v-model="ruleForm.accountNumber"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="telephone">
              <el-input v-model="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="所在银行" prop="bank">
              <el-select v-model="ruleForm.bank" placeholder="请选择" @change="_bankchange" style="width: 268px;">
                <el-option
                  v-for="item in bankoption"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="_add_account_submit('ruleForm')">完 成</el-button>
        </span>
      </el-dialog>
      <el-dialog class="add_zhihang" title="创建分支行" :visible.sync="add_bank" size="000">
        <el-form :model="Bank_Form" :rules="rules" ref="Bank_Form" label-width="100px" class="demo-ruleForm">
          <el-form-item label="所属行" prop="pidbankname">
            <el-input :disabled="true" type="text" v-model="Bank_Form.pidbankname"></el-input>
          </el-form-item>
          <el-form-item label="下级分支行" prop="bankname">
            <el-input type="text" v-model="Bank_Form.bankname"></el-input>
          </el-form-item>
          <el-form-item label="银行代码" prop="code">
            <el-input v-model="Bank_Form.code"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="address">
            <el-input v-model="Bank_Form.address"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phone">
            <el-input v-model="Bank_Form.phone"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="zjlx">
            <el-input type="text" v-model="Bank_Form.zjlx"></el-input>
          </el-form-item>
          <el-form-item label="证件号" prop="zjlxmc">
            <el-input v-model="Bank_Form.zjlxmc"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <span style="float: left;padding-left: 20px;font-size: 14px;color:red;">* 该信息将默认填写在不动产登记申报、注销页面</span>
          <el-button @click="_add_nextbank_submit">提 交</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        search_value: '', // 查询框
        manageData: [
          {
            name: '',
            name1: '',
            name2: '',
            account: ''
          }
        ],
        add_account: false,
        add_bank: false,
        ruleForm: {
          name: '',
          accountNumber: '',
          password: '',
          bank: '',
          telephone: ''
        },
        bankoption: [
          {
            id: '',
            value: ''
          },
          {
            id: '',
            value: ''
          }
        ],
        bankid: '',
        Bank_Form: {
          pidbankname: '',
          bankname: '',
          code: '',
          zjlx: '',
          zjlxmc: '',
          address: '',
          phone: ''
        },
        Manage_Form: {
          name: '',
          account: '',
          belong: '',
          role: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          telephone: [
            {required: true, message: '请输入电话号码', trigger: 'change'},
            {type: 'string', message: '请输入正确的手机号码', pattern: /^1[0-9]{10}$/, trigger: 'blur,change'}
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'change'}
          ],
          accountNumber: [
            {required: true, message: '请输入账号', trigger: 'change'}
          ],
          bank: [
            {required: true, message: '请选择所在行', trigger: 'change'}
          ]
        },
        Edit_rules: {  // 修改账户验证
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          account: [
            {required: true, message: '请输入账号', trigger: 'change'}
          ]
        },
        pId: '',
        brankname: '',
        deletbankdata: ''
      };
    },
    methods: {
      _add_nextbank(index, row) {
        if (row.name !== '') {
          this.Bank_Form.pidbankname = row.name;
        }
        if (row.name1 !== '') {
          this.Bank_Form.pidbankname = row.name1;
        }
        if (row.name2 !== '') {
          return false;
        }
        this.pId = row.id;
        this.add_bank = true;
      },
      _add_nextbank_submit() {
        this.$http.post(this.$store.state.Host + '/bankControl/saveBank', {
          name: this.Bank_Form.bankname,
          id: this.pId,
          code: this.Bank_Form.code,
          zjlx: this.Bank_Form.zjlx,
          zjlxmc: this.Bank_Form.zjlxmc,
          telephone: this.Bank_Form.phone,
          address: this.Bank_Form.address
        }).then((response) => {
          response = response.body;
          if (response.code === 5000) {
            this.$notify({
              title: '提示',
              message: '没有权限，请联系管理员。',
              type: 'error'
            });
            this.add_bank = false;
            return false;
          }
          if (response.code === 4000) {
            this.$notify({
              title: '警告!',
              message: response.message,
              type: 'warning'
            });
            this.freshTable();
            return false;
          }
          if (response.code === 200) {
            this.$notify({
              title: '成功',
              message: '提交成功',
              type: 'success'
            });
            this.freshTable();
          } else {
            this.$notify({
              title: '错误',
              message: '提交失败',
              type: 'error'
            });
          }
          this.add_bank = false;
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
      _add_account() {
        this.getbank();
        this.add_account = true;
      },
      _add_account_submit(ruleForm) { // 新增账号
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            this.$http.post(this.$store.state.Host + '/UserControl/saveUser', {
              bankId: this.bankid,
              telephone: this.ruleForm.telephone,
              password: this.ruleForm.password,
              name: this.ruleForm.name,
              accountNumber: this.ruleForm.accountNumber
            }).then((response) => {
              response = response.body;
              if (response.code === 5000) {
                this.$notify({
                  title: '提示',
                  message: '没有权限，请联系管理员修改。',
                  type: 'error'
                });
                return false;
              }
              switch (response.code) {
                case 1000:
                  this.$notify({
                    title: '提示',
                    message: '添加成功',
                    type: 'success'
                  });
                  break;
                case 4000:
                  this.$notify({
                    title: '警告',
                    message: '用户已经存在',
                    type: 'warning'
                  });
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '添加失败',
                    type: 'error'
                  });
              }
              this.add_account = false;
              this.$router.push({path: 'account'});
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
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
          this.add_account = false;
        });
      },
      _delet_bank(index, row) {
        let data = this.deletbankdata;
        let Id = row.id;
        for (var i = 0; i < data.length; i++) { // 判断二级支行
          if (Id === data[i].id && data[i].children.length === 0) {
            this.$notify({
              title: '提示',
              message: '该行不能删除！',
              type: 'warning'
            });
            return false;
          }
          if (Id === data[i].id && data[i].children.length > 0) {
            this.$notify({
              title: '提示',
              message: '该行有分支行，暂不能删除',
              type: 'warning'
            });
            return false;
          }
          for (var k = 0; k < data[i].children.length; k++) {
            if (Id === data[i].children[k].id && data[i].children[k].children.length > 0) {
              this.$notify({
                title: '提示',
                message: '该行有分支行，暂不能删除',
                type: 'warning'
              });
              return false;
            }
          }
          for (var h = 0; h < data[i].children.length; h++) {
            for (var j = 0; j < data[i].children[h].children.length; j++) {
              if (Id === data[i].children[h].children[j].id && data[i].children[h].children[j].children.length > 0) {
                this.$notify({
                  title: '提示',
                  message: '该行有分支行，暂不能删除',
                  type: 'warning'
                });
                return false;
              }
            }
          }
        }
        this.$confirm('是否删除?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'info'
        }).then(() => {
          this.$http.get(this.$store.state.Host + '/bankControl/deleteBank', {
            params: {
              bankId: Id
            }
          }).then((response) => {
            response = response.body;
            if (response.code === 5000) {
              this.$notify({
                title: '提示',
                message: '没有权限，请联系管理员。',
                type: 'error'
              });
              return false;
            }
            if (response.code === 200) {
              this.$notify({
                title: '成功',
                message: '删除成功',
                type: 'success'
              });
              this.freshTable();
            } else {
              this.$notify({
                title: '错误',
                message: '删除失败',
                type: 'error'
              });
            }
            this.freshTable();
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
      },
      getbank() {    // 获取证件种类
        this.$http.get(this.$store.state.Host + '/bankControl/findAllBank').then((response) => {
          response = response.body;
          if (response.code === 200) {
            let data = response.content;
            let arr = [];
            for (var i = 0; i < data.length; i++) {
              let json = {
                id: data[i].id,
                value: data[i].bankName
              };
              arr.push(json);
            }
            this.bankoption = arr;
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
      _bankchange(val) {
        let options = this.bankoption;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            this.bankid = options[i].id;
          }
        }
      },
      List(lsit, pId) {
        let ret = [];
        for (var k in lsit) {
          if (lsit[k].pId === pId) {
            lsit[k].children = this.List(lsit, lsit[k].id);
            ret.push(lsit[k]);
          }
        }
        return ret;
      },
      freshTable() {
        this.$http.get(this.$store.state.Host + '/bankControl/findAllBank').then((response) => {
          response = response.body;
          if (response.code === 200) {
            let data = response.content;
            let arr = [];
            let tree = this.List(data, '0');
            this.deletbankdata = tree;
            for (var i = 0; i < tree.length; i++) {
              let json = {
                name: tree[i].bankName,
                name1: '',
                name2: '',
                id: tree[i].id,
                Pid: '0',
                account: tree[i].code
              };
              arr.push(json);
              for (var k = 0; k < tree[i].children.length; k++) {
                let json1 = {
                  name: tree[i].bankName,
                  name1: tree[i].children[k].bankName,
                  name2: '',
                  id: tree[i].children[k].id,
                  Pid: tree[i].children[k].pId,
                  account: tree[i].children[k].code
                };
                arr.push(json1);
                for (var h = 0; h < tree[i].children[k].children.length; h++) {
                  let json2 = {
                    name: tree[i].bankName,
                    name1: tree[i].children[k].bankName,
                    name2: tree[i].children[k].children[h].bankName,
                    id: tree[i].children[k].children[h].id,
                    Pid: tree[i].children[k].children[h].pId,
                    account: tree[i].children[k].children[h].code
                  };
                  arr.push(json2);
                }
              }
            }
            this.manageData = arr;
          } else {
            this.$notify({
              title: '警告',
              message: '暂无数据',
              type: 'warning'
            });
          }
        }, (error) => {
          if (error.status === 401) {
            this.$notify({
              title: '警告',
              message: error.body,
              type: 'error'
            });
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
      }
    },
    created() {
      this.freshTable();
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .manage
    width: 100%
    .text
      width: 100%
      height: 60px
      line-height: 60px
      .img_point
        width: 30px
        height: 20px
        margin-top: -1px
        padding: 0 10px 0 30px
        vertical-align: middle
      .title
        color: #3a3a3a
        font-size: 16px
        vertical-align: middle
      .add
        float: right
        margin-right: 100px
        vertical-align: middle
        font-size: 16px
        color: #158684
        cursor: pointer
    .content
      width: 100%
      .manage_tables
        width: 96%
        margin-left: 30px
        position: relative
        overflow: hidden
        .el-table td, .el-table th
          height: 34px
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
    .manage_dialog
      .add_zhihang
        .el-form
          width: 800px
          .el-form-item
            display: inline-block
            width: 390px
</style>

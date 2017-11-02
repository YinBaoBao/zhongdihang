<template>
  <div class="manage">
    <div class="content">
      <div class="acc_search">
        <div class="title"><span>管理分支行</span></div>
        <div class="btns">
          <el-button :plain="true" type="info" icon="plus" style="float: right; margin: 6px 50px 0 0;"
                     @click="_add_account">新增账号
          </el-button>
        </div>
      </div>
      <div class="account_tables">
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
      <el-dialog title="创建账号" :visible.sync="add_account" size="tiny">
        <div style="width: 370px;">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="手机号码" prop="telephone">
              <el-input v-model="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="登录密码" prop="password">
              <el-input type="password" v-model="ruleForm.password"></el-input>
            </el-form-item>
            <el-form-item label="姓名" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
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
      <el-dialog title="增加分支行" :visible.sync="add_bank" size="tiny">
        <span style="display: inline-block;padding: 10px 15px 18px 46px;font-size: 14px;">所属行</span>
        <span>{{brankname}}</span>
        <el-form :model="Bank_Form" :rules="rules" ref="Bank_Form" label-width="100px" class="demo-ruleForm">
          <el-form-item label="下级分支行" prop="bankname">
            <el-input type="text" v-model="Bank_Form.bankname"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
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
          bankname: ''
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
          accountNumber: [
            {required: true, message: '请输入账号', trigger: 'change'}
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
        console.log(index);
        if (row.name !== '') {
          this.brankname = row.name;
        }
        if (row.name1 !== '') {
          this.brankname = row.name1;
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
          id: this.pId
        }).then((response) => {
          response = response.body;
          if (response.code === 1000) {
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
              accountNumber: this.ruleForm.name
            }).then((response) => {
              response = response.body;
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
            if (response.code === 1000) {
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
        });
      },
      getbank() {    // 获取证件种类
        this.$http.get(this.$store.state.Host + '/bankControl/findAllBank').then((response) => {
          response = response.body;
          if (response.code === 1000) {
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
          if (response.code === 1000) {
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
    border: 2px solid #DFE6EC
    width: 100%
    .information
      border-bottom: 1px solid #DFE6EC
      width: 100%
      overflow: hidden;
      .text
        float: left
        height: 80px
        line-height: 80px
        span
          font-size: 16px
          padding: 0 15px
      .info
        float: left
        padding-top: 16px;
        span
          font-size: 14px
          display: inline-block
          padding: 8px 10px
        .edit
          margin-left: 80px
          color: #ccc
          cursor: pointer
        .edit:hover
          font-weight: bolder
          color: #ADADAD
    .content
      width: 100%
      .acc_search
        width: 100%
        overflow: hidden
        .title
          float: left
          height: 50px
          line-height: 50px
          span
            padding: 0 15px
        .btns
          float: right
          .btns_sub
            float: left
      .account_tables
        width: 99%
        position: relative
        overflow: hidden
        .el-table td, .el-table th
          height: 34px
</style>

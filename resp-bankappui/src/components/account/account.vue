<template>
  <div class="accounts">
    <div class="information">
      <div class="text">
        <span>基本信息</span>
      </div>
      <div class="info">
        <span>{{BankInfo.bankName}}</span>
        <span>证件类型：{{BankInfo.zjlx}}</span>
        <span>证件号：{{BankInfo.zjlxmc}}</span><br>
        <span>联系地址：{{BankInfo.bankAddress}}</span>
        <span>电话：{{BankInfo.telephone}}</span>
        <!--<span class="edit" @click="Edit">修改</span>-->
      </div>
    </div>
    <div class="content">
      <div class="acc_search">
        <div class="title"><span>账号管理</span></div>
        <div class="btns">
          <div class="btns_sub">
            <el-input v-model="search_value" placeholder="输入账号/姓名可查" style="width: 220px"></el-input>
            <el-button type="primary" icon="search" style="margin-left: 20px;" @click="_search_submit">查询</el-button>
          </div>
          <el-button :plain="true" type="info" icon="plus" style="float: right; margin-right: 50px;"
                     @click="_account_add">新增账号
          </el-button>
          <el-button :plain="true" type="info" style="float: right; margin-right: 50px;"
                     @click="_manage_add">管理分支行
          </el-button>
        </div>
      </div>
      <div class="account_tables">
        <el-table :data="tableData" v-loading="tableloding" element-loading-text="拼命加载中" border height="450"
                  style="width: 100%">
          <el-table-column type="index" width="60"></el-table-column>
          <el-table-column prop="account" label="账号" sortable></el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="belong" label="所属行"></el-table-column>
          <el-table-column prop="role" label="角色"></el-table-column>
          <el-table-column prop="telephone" label="电话"></el-table-column>
          <el-table-column prop="create_time" label="创建日期"></el-table-column>
          <el-table-column label="操作" width="110">
            <template scope="scope">
              <el-button v-if="scope.$index !== 0" type="text" size="small" @click="_bind(scope.$index,scope.row)">
                禁 用
              </el-button>
              <el-button type="text" size="small" @click="manage_acount(scope.$index,scope.row)">修 改</el-button>
            </template>
          </el-table-column>
          <el-table-column prop="tag" label="标签" width="100"
                           :filters="[{ text: '公司', value: '公司' }, { text: '部门', value: '部门' }]"
                           :filter-method="filterTag"
                           filter-placement="bottom-end">
            <template scope="scope">
              <el-tag
                :type="scope.row.tag === '家' ? 'primary' : 'success'"
                close-transition>{{scope.row.tag}}
              </el-tag>
            </template>
          </el-table-column>
        </el-table>
        <div class="Pages">
          <el-pagination
            @size-change="handleSizeChange"
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
    <div class="acc_dialog">
      <el-dialog title="新增账号" :visible.sync="add_account" size="000">
        <div class="add_form" style="width: 400px;">
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="ruleForm.name"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="accountNumber">
              <el-input v-model="ruleForm.accountNumber"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newpassword">
              <el-input type="password" v-model="ruleForm.newpassword"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="telephone">
              <el-input v-model="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="所在银行" prop="bank">
              <el-select v-model="ruleForm.bank" placeholder="请选择" @change="_bankchange" style="width: 270px;">
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
      <el-dialog title="修改基本信息" :visible.sync="edit_account" size="tiny" :before-close="handleClose">
        <el-form :model="EditForm" ref="EditForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="username">
            <el-input v-model="EditForm.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="password">
            <el-input type="text" v-model="EditForm.type"></el-input>
          </el-form-item>
          <el-form-item label="证件号" prop="bank_cod">
            <el-input v-model="EditForm.bank_cod"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="phone">
            <el-input v-model="EditForm.address"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phone">
            <el-input v-model="EditForm.phone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="edit_account = false">取 消</el-button>
          <el-button type="primary" @click="_Edit_submit('EditForm')">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="账号修改" :visible.sync="manage_account" size="000" :before-close="handleClose">
        <div style="width: 420px;padding-right: 20px;">
          <el-form :model="Manage_Form" :rules="Edit_rules" ref="Manage_Form" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="Manage_Form.name"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="account">
              <el-input type="text" v-model="Manage_Form.account"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="Manage_Form.password"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="telephone">
              <el-input v-model="Manage_Form.telephone"></el-input>
            </el-form-item>
            <el-form-item label="所属行" prop="belong">
              <el-input v-model="Manage_Form.belong"></el-input>
            </el-form-item>
            <el-form-item label="角色 " prop="role">
              <el-input v-model="Manage_Form.role"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="manage_account = false">取 消</el-button>
          <el-button type="primary" @click="_manage_account_submit('Manage_Form')">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="账号修改" :visible.sync="manage_account1" size="000" :before-close="handleClose">
        <div style="width: 420px;padding-right: 20px;">
          <el-form :model="Manage_Form" :rules="Edit_rules" ref="Manage_Form" label-width="100px" class="demo-ruleForm">
            <el-form-item label="原密码" prop="newpasswor">
              <el-input type="password" v-model="Manage_Form.newpasswor"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="oldpassword">
              <el-input type="password" v-model="Manage_Form.oldpassword"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="manage_account1 = false">取 消</el-button>
          <el-button type="primary" @click="_manage_account_submit1('Manage_Form')">确 定</el-button>
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
        BankInfo: {
          id: '',
          bankName: '中国农业银行股份有限公司苏州分行',
          telephone: '0512-68268178',
          zjlxmc: '320500000010314',
          zjlx: '营业执照',
          bankAddress: ''
        },
        tableloding: false,
        search_value: '', // 查询框
        tableData: [
//          {
//            account: '',
//            name: '',
//            belong: '苏州分行',
//            role: '',
//            create_time: '',
//            tag: '公司',
//            telephone: ''
//          }
        ],
        currentPage: 1,  // 当前页
        pageSize: 10,    // 每页显示条数
        total: 100,      // 总条数
        add_account: false,
        edit_account: false,
        manage_account: false,
        manage_account1: false,
        ruleForm: {
          name: '',
          newpassword: '',
          bank: '',
          telephone: '',
          email: '',
          accountNumber: ''
        },
        rules: {
          name: [
            {required: true, message: '请输入姓名', trigger: 'change'}
          ],
          telephone: [
            {required: true, message: '请输入电话号码', trigger: 'change'},
            {type: 'string', message: '请输入正确的手机号码', pattern: /^1[0-9]{10}$/, trigger: 'blur,change'}
          ],
          newpassword: [
            {required: true, message: '请输新入密码', trigger: 'change'}
          ],
          accountNumber: [
            {required: true, message: '请输入账号', trigger: 'change'}
          ],
          bank: [
            {required: true, message: '请选择所在行', trigger: 'change'}
          ]
        },
        EditForm: {
          id: '',
          username: '中国建设银行股份有限公司苏州分行',
          type: '营业执照',
          bank_cod: '320500000010314',
          address: '苏州市姑苏区阊胥路88号',
          phone: '0512-68268178'
        },
        Manage_Form: {
          account: '',
          name: '',
          password: '',
          newpasswor: '',
          oldpassword: '',
          belong: '',
          role: '',
          create_time: '',
          telephone: '',
          tag: ''
        },
        Edit_rules: {  // 修改账户验证
          name: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ],
          account: [
            {required: true, message: '请输入账号', trigger: 'change'}
          ]
        },
        bankoption: [
          {
            id: '',
            value: '身份证'
          },
          {
            id: '',
            value: '营业执照'
          }
        ],
        bankid: ''
      };
    },
    methods: {
      _account_add() {   // 新增账号
        this.getbank();
        this.add_account = true;
      },
      _add_account_submit(ruleForm) { // 新增账号
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            this.$http.post(this.$store.state.Host + '/UserControl/saveUser', {
              bankId: this.bankid,
              telephone: this.ruleForm.telephone,
              password: this.ruleForm.newpassword,
              accountNumber: this.ruleForm.accountNumber,
              name: this.ruleForm.name,
              e_mail: this.ruleForm.email
            }).then((response) => {
              response = response.body;
              if (response.code === 5000) {
                this.$notify({
                  title: '提示',
                  message: response.message,
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
                  this.freshData();
                  this.add_account = false;
                  break;
                case 4000:
                  this.$notify({
                    title: '警告',
                    message: '用户已经存在',
                    type: 'warning'
                  });
                  this.freshData();
                  this.add_account = false;
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '添加失败',
                    type: 'error'
                  });
                  this.freshData();
                  this.add_account = false;
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
      _manage_add() {  // 管理分支行
        this.$router.push({path: 'manage'});
      },
      _search_submit() {
        this.freshData(this.search_value);
      },
      Edit() {    // 修改基本信息
        this.EditForm.id = this.BankInfo.id;
        this.EditForm.username = this.BankInfo.bankName;
        this.EditForm.type = this.BankInfo.zjlx;
        this.EditForm.bank_cod = this.BankInfo.zjlxmc;
        this.EditForm.address = this.BankInfo.bankAddress;
        this.EditForm.phone = this.BankInfo.telephone;
        this.edit_account = true;
      },
      _Edit_submit(EditForm) { // 修改基本信息
        this.$refs[EditForm].validate((valid) => {
          if (valid) {
            this.$http.post(this.$store.state.Host + '/bankControl/updateBank', {
              id: this.EditForm.id,
              name: this.EditForm.username,
              address: this.EditForm.address,
              zjlx: this.EditForm.type,
              zjlxmc: this.EditForm.bank_cod,
              telephone: this.EditForm.phone
            }).then((response) => {
              response = response.body;
              switch (response.code) {
                case 1000:
                  this.$notify({
                    title: '提示',
                    message: '修改成功',
                    type: 'success'
                  });
                  this.freshData();
                  this.add_account = false;
                  break;
                default:
                  this.$notify({
                    title: '警告',
                    message: '修改失败',
                    type: 'error'
                  });
              }
              this.edit_account = false;
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
      _bind(index, row) {  // 禁用
//        console.log(row);
        let userId = row.id;
        this.$http.get(this.$store.state.Host + '/UserControl/deleteUser', {
          params: {userId: userId}
        }).then((response) => {
          response = response.body;
          switch (response.code) {
            case 1000:
              this.$notify({
                title: '提示',
                message: '已禁用',
                type: 'success'
              });
              this.freshData();
              break;
            default:
              this.$notify({
                title: '错误',
                message: '修改失败',
                type: 'error'
              });
          }
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
      manage_acount(index, row) {   // 修改账户信息
        if (this.$store.state.Role[0].description === 'admin') {
          this.manage_account = true;
        } else {
          this.manage_account1 = true;
        }
        this.Manage_Form.account = row.account;
        this.Manage_Form.belong = row.belong;
        this.Manage_Form.telephone = row.telephone;
        this.Manage_Form.id = row.id;
        this.Manage_Form.bankId = row.bankId;
        this.Manage_Form.name = row.name;
        this.Manage_Form.role = row.role;
      },
      _manage_account_submit(ManageForm) {
        this.$refs[ManageForm].validate((valid) => {
          if (valid) {
            let Id = this.Manage_Form.id;
            let name = this.Manage_Form.name;
            let account = this.Manage_Form.account;
            this.$http.post(this.$store.state.Host + '/UserControl/updateUser', {
              bankId: this.Manage_Form.bankId,
              id: Id,
              name: name,
              accountNumber: account,
              password: this.Manage_Form.password,
              telephone: this.Manage_Form.telephone
            }).then((response) => {
              response = response.body;
              switch (response.code) {
                case 1000:
                  this.$notify({
                    title: '提示',
                    message: '修改成功',
                    type: 'success'
                  });
                  this.freshData();
                  this.manage_account = false;
                  break;
                default:
                  this.$notify({
                    title: '错误',
                    message: '修改失败',
                    type: 'error'
                  });
                  this.manage_account = false;
              }
            });
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
        });
      },
      _manage_account_submit1(ManageForm) {
        this.$refs[ManageForm].validate((valid) => {
          if (valid) {
            this.$http.post(this.$store.state.Host + '/UserControl/password/modify', {
              userId: this.Manage_Form.id,
              oldPassword: this.Manage_Form.newpasswor,
              newPassword: this.Manage_Form.oldpassword
            }).then((response) => {
              response = response.body;
              if (response.code === 1000) {
                this.$notify({
                  title: '提示',
                  message: '修改成功',
                  type: 'success'
                });
              } else {
                this.$notify({
                  title: '提示',
                  message: '修改失败！',
                  type: 'error'
                });
              }
              this.manage_account1 = false;
            });
          } else {
            this.$notify.info({
              title: '提示',
              message: '请填写信息'
            });
            return false;
          }
        });
      },
      formatter(row, column) {
        return row.address;
      },
      filterTag(value, row) {
        return row.tag === value;
      },
      handleClick() {
//        console.log(1);
      },
      handleSizeChange(val) {  // 每页显示多少条
        this.pageSize = val;
        this.freshData();
      },
      handleCurrentChange(val) {   // 当前第几页
        this.currentPage = val;
        this.freshData();
      },
      freshData(elements) {
        if (this.$store.state.Role[0].description === 'admin') {
          this.$http.get(this.$store.state.Host + '/UserControl/list', {
            params: {
              pageNumber: this.currentPage,
              pageSize: this.pageSize,
              condition: elements
            }
          }).then((response) => {
            response = response.body;
            this.tableloding = false;
            if (response.code === 1000) {
              this.total = response.content.totalElements;
              this.tableData = [];
              let data = response.content.content;
              for (var i = 0; i < data.length; i++) {
                let json = {};
                json['bankId'] = data[i].bankId;
                json['id'] = data[i].id;
                json['account'] = data[i].accountNumber;
                json['name'] = data[i].name;
                json['belong'] = data[i].bankName;
                json['telephone'] = data[i].telephone;
                json['role'] = data[i].roleStr[0].roleName;
                json['create_time'] = data[i].creatTime;
                json['tag'] = '部门';
                this.tableData.splice(i, 1, json);
              }
            } else {
              this.$notify({
                title: '警告',
                message: '暂无数据',
                type: 'warning'
              });
            }
          });
        } else {
          this.$http.get(this.$store.state.Host + '/UserControl/findOneUser', {
            params: {
              userId: this.$store.state.bankuser.id
            }
          }).then((response) => {
            response = response.body;
            this.tableloding = false;
            if (response.code === 1000) {
              this.total = 100;
              let data = response.content;
              let json = {};
              json['bankId'] = data.bankId;
              json['id'] = data.id;
              json['account'] = data.accountNumber;
              json['name'] = data.name;
              json['belong'] = data.bankName;
              json['telephone'] = data.telephone;
              json['role'] = data.roleStr[0].roleName;
              json['create_time'] = data.creatTime;
              json['tag'] = '部门';
              this.tableData.push(json);
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
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      },
      _getDate(style) {
        let date = new Date();
        return formatDate(date, style);
      }
    },
    created() {
      this.freshData();
      this.BankInfo = this.$store.state.Bankinfo;
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .accounts
    border: 2px solid #DFE6EC
    width: 100%
    .information
      border-bottom: 1px solid #DFE6EC
      width: 100%
      overflow: hidden;
      .text
        float: left
        height: 66px
        line-height: 66px
        span
          font-size: 16px
          padding: 0 15px
      .info
        float: left
        span
          display: inline-block
          min-width: 160px;
          padding: 8px 10px
          font-size: 14px
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
          width: 110px
          height: 50px
          line-height: 50px
          span
            padding: 0 15px
        .btns
          float: left
          width: calc(100% - 120px)
          padding: 10px 0 10px 8px;
          .btns_sub
            float: left
        .el-input__inner
          height: 32px
        .el-button
          padding: 8px 15px
      .account_tables
        width: 100%
        padding-bottom: 64px
        position: relative
        overflow: hidden
        .el-table td, .el-table th
          height: 34px
        .Pages
          display: inline-block
          position: absolute
          left: 26%
          bottom: 10px

    .acc_dialog
      .add_form
        .el-form-item
          width: 370px
          display: inline-block
</style>

<template>
  <div class="accounts">
    <div class="information">
      <div class="text">
        <img class="img_point" src="./red.png" alt="red">
        <span class="title">基本信息</span>
        <span class="edit" @click="Edit">修改 <i class="el-icon-edit"></i></span>
      </div>
      <div class="info">
        <span>{{BankInfo.bankName}}</span>
        <span>证件类型：{{BankInfo.zjlx}}</span>
        <span>证件号：{{BankInfo.zjlxmc}}</span><br>
        <span>联系地址：{{BankInfo.bankAddress}}</span>
        <span>电话：{{BankInfo.telephone}}</span>
      </div>
      <div class="text">
        <img class="img_point" src="./blue.png" alt="blue">
        <span class="title">账号管理</span>
      </div>
      <div class="acc_seartch">
        <div style="margin-left: 40px;display: inline-block;vertical-align: middle;">
          <el-input v-model="search_value" placeholder="输入账号/姓名可查" style="width: 220px"></el-input>
          <el-button @click="_search_submit">查询</el-button>
        </div>
        <span class="add" @click="_account_add">新增 <i class="el-icon-plus"></i></span>
      </div>
    </div>
    <div class="account_tables">
      <el-table :data="tableData" v-loading="tableloding" element-loading-text="拼命加载中" border height="400"
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
            <el-button v-if="scope.row.description!=='admin' && scope.row.description!=='bankAdmin'" type="text"
                       size="small"
                       @click="_bind(scope.$index,scope.row)">
              删 除
            </el-button>
            <el-button v-if="scope.row.description!=='admin' && scope.row.description!=='bankAdmin'" type="text"
                       size="small"
                       @click="manage_acount(scope.$index,scope.row)">修 改
            </el-button>
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
            <el-form-item label="密码" prop="newpassword">
              <el-input type="password" v-model="ruleForm.newpassword"></el-input>
            </el-form-item>
            <el-form-item label="手机号码" prop="telephone">
              <el-input v-model="ruleForm.telephone"></el-input>
            </el-form-item>
            <el-form-item label="角色" prop="role">
              <el-select v-model="ruleForm.role" placeholder="请选择" @change="_rolechange" style="width: 270px;">
                <el-option
                  v-for="item in Role"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
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
      <el-dialog title="修改基本信息" :visible.sync="edit_account" size="000">
        <div class="add_form" style="width: 400px;">
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
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="edit_account = false">取 消</el-button>
          <el-button type="primary" @click="_Edit_submit('EditForm')">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="账户修改" :visible.sync="manage_account" size="000">
        <div style="width: 420px;padding-right: 20px;">
          <el-form :model="Manage_Form" :rules="Edit_rules" ref="Manage_Form" label-width="100px" class="demo-ruleForm">
            <el-form-item label="姓名" prop="name">
              <el-input v-model="Manage_Form.name"></el-input>
            </el-form-item>
            <el-form-item label="账号" prop="account">
              <el-input disabled type="text" v-model="Manage_Form.account"></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="password">
              <el-input type="password" v-model="Manage_Form.password"></el-input>
            </el-form-item>
            <el-form-item label="电话" prop="telephone">
              <el-input v-model="Manage_Form.telephone"></el-input>
            </el-form-item>
            <el-form-item label="所属行" prop="belong">
              <el-input disabled v-model="Manage_Form.belong"></el-input>
            </el-form-item>
            <el-form-item label="角色" prop="role">
              <el-input disabled v-model="Manage_Form.role"></el-input>
            </el-form-item>
          </el-form>
        </div>
        <span slot="footer" class="dialog-footer">
          <el-button @click="manage_account = false">取 消</el-button>
          <el-button type="primary" @click="_manage_account_submit('Manage_Form')">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="账户修改" :visible.sync="manage_account1" size="000">
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
          role: '',
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
          role: [
            {required: true, message: '请选角色', trigger: 'change'}
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
          ],
          password: [
            {required: true, message: '请输入密码', trigger: 'change'}
          ]
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
        Role: [
          {
            id: '',
            value: '管理员'
          },
          {
            id: '',
            value: '操作员'
          }
        ],
        roleid: '',
        bankid: ''
      };
    },
    methods: {
      _account_add() {   // 新增账号
        this.getbank();
        this.getrole();
        this.add_account = true;
      },
      _add_account_submit(ruleForm) { // 保存新增账号
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            let roleId = [];
            roleId.push(this.roleid);
            this.$http.post(this.$store.state.Host + '/UserControl/saveUser', {
              bankId: this.bankid,
              telephone: this.ruleForm.telephone,
              password: this.ruleForm.newpassword,
              accountNumber: this.ruleForm.accountNumber,
              roleId: roleId,
              name: this.ruleForm.name,
              e_mail: this.ruleForm.email,
              is_Delete: false
            }).then((response) => {
              response = response.body;
              if (response.code === 5000) {
                this.$notify({
                  title: '提示',
                  message: '没有权限，请联系管理员修改。',
                  type: 'error'
                });
                this.add_account = false;
                return false;
              }
              switch (response.code) {
                case 200:
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
                case 200:
                  this.$notify({
                    title: '提示',
                    message: '修改成功',
                    type: 'success'
                  });
                  this.BankInfo = response.content;
                  this.edit_account = false;
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
          if (response.code === 5000) {
            this.$notify({
              title: '提示',
              message: '没有权限，请联系管理员。',
              type: 'error'
            });
            return false;
          }
          switch (response.code) {
            case 200:
              this.$notify({
                title: '提示',
                message: '已删除',
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
      getbank() {    // 获取所在银行
        let bankId = localStorage.getItem('bankId');
        this.$http.get(this.$store.state.Host + '/bankControl/findSubordinateBank', {
          params: {
            bankId: bankId
          }
        }).then((response) => {
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
      getrole() {    // 获取所在银行
        this.$http.get(this.$store.state.Host + '/RoleControl/findAllRole').then((response) => {
          response = response.body;
          let data = response;
          let arr = [];
          for (var i = 0; i < data.length; i++) {
            let json = {
              id: data[i].id,
              value: data[i].roleName
            };
            arr.push(json);
          }
          this.Role = arr;
        });
      },
      _rolechange(val) {
        let options = this.Role;
        for (var i = 0; i < options.length; i++) {
          if (val.indexOf(options[i].value) > -1) {
            this.roleid = options[i].id;
          }
        }
      },
      manage_acount(index, row) {   // 修改账户信息
        let description = localStorage.getItem('description');
        if (description === 'admin' || description === 'bankAdmin') {
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
                case 200:
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
              if (response.code === 200) {
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
        let description = localStorage.getItem('description');
        let userId = localStorage.getItem('userId');
        if (description === 'admin' || description === 'bankAdmin') {
          this.$http.get(this.$store.state.Host + '/UserControl/list', {
            params: {
              pageNumber: this.currentPage,
              pageSize: this.pageSize,
              condition: elements,
              userId: userId
            }
          }).then((response) => {
            response = response.body;
            this.tableloding = false;
            if (response.code === 200) {
              this.total = response.content.totalElements;
              let arr = [];
              let data = response.content.content;
              for (var i = 0; i < data.length; i++) {
                let json = {
                  bankId: data[i].bankId,
                  id: data[i].id,
                  account: data[i].accountNumber,
                  name: data[i].name,
                  belong: data[i].bankName,
                  telephone: data[i].telephone,
                  role: data[i].roleStr[0].roleName,
                  create_time: this.formatDate(data[i].creatTime)
                };
                if (description === 'admin') {
                  json['description'] = data[i].roleStr[0].description;
                }
                if (description === 'bankAdmin') {
                  json['description'] = data[i].roleStr[0].description;
                }
                if (description === 'admin' && data[i].roleStr[0].description === 'bankAdmin') {
                  json['description'] = '';
                }
                arr.push(json);
              }
              this.tableData = arr;
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
        } else {
          this.$http.get(this.$store.state.Host + '/UserControl/findOneUser', {
            params: {
              userId: userId
            }
          }).then((response) => {
            response = response.body;
            this.tableloding = false;
            if (response.code === 200) {
              this.total = 100;
              let arr = [];
              let data = response.content;
              let json = {
                bankId: data.bankId,
                id: data.id,
                account: data.accountNumber,
                name: data.name,
                belong: data.bankName,
                telephone: data.telephone,
                role: data.roleStr[0].roleName,
                description: data.roleStr[0].description,
                create_time: this.formatDate(data.creatTime)
              };
              arr.push(json);
              this.tableData = arr;
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
      formatDate(time) {
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
      }
    },
    created() {
      this.freshData();
      let data = JSON.parse(localStorage.getItem('bankinfo'));
      this.BankInfo = data;
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .accounts
    width: 100%
    .information
      width: 100%
      overflow: hidden;
      .text
        width: 100%
        height: 42px
        line-height: 50px
        .img_point
          width: 12px
          height: 12px
          margin-top: -1px
          padding: 0 10px 0 14px
          vertical-align: middle
        .title
          color: #3a3a3a
          font-size: 16px
          vertical-align: middle
        .edit
          float: right
          margin-right: 100px
          vertical-align: middle
          font-size: 15px
          color: #158684
          cursor: pointer
      .info
        padding-left: 26px
        span
          display: inline-block
          min-width: 180px
          padding: 8px 15px
          font-size: 14px
          color: #3a3a3a
      .acc_seartch
        width: 100%
        height: 40px
        margin-bottom: 10px
        line-height: 40px
        .add
          float: right
          margin-right: 100px
          vertical-align: middle
          font-size: 15px
          color: #158684
          cursor: pointer
        .el-input__inner
          height: 34px
          border-radius: 0
        .el-input__inner:hover
          border-color: #148583
        .el-input__inner:active
          border-color: #148583
        .el-button
          border-color: #148583
          background: #148583
          padding: 9px 30px
          margin-left: 20px
          border-radius: 0
          span
            color: #fff
    .account_tables
      width: 96%
      margin-left: 38px
      padding-bottom: 64px
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
      .Pages
        display: inline-block
        position: absolute
        left: 26%
        bottom: 10px
        .el-pager li.active
          border-color: #148583
          background-color: #148583
    .acc_dialog
      .add_form
        .el-form-item
          width: 370px
          display: inline-block
</style>

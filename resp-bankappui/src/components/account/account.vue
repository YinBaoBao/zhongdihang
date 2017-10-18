<template>
  <div class="accounts">
    <div class="information">
      <div class="text">
        <span>基本信息</span>
      </div>
      <div class="info">
        <span>中国建设银行股份有限公司苏州分行</span>
        <span>证件类型：营业执照</span>
        <span>证件号：320500000010314</span><br>
        <span>联系地址：苏州市姑苏区阊胥路88号</span>
        <span>电话：0512-68268178</span>
        <span class="edit" @click="Edit">修改</span>
      </div>
    </div>
    <div class="content">
      <div class="acc_search">
        <div class="title"><span>账号管理</span></div>
        <div class="btns">
          <div class="btns_sub">
            <el-input v-model="search_value" placeholder="输入账号/姓名可查" style="width: 200px"></el-input>
            <el-button type="primary" icon="search" style="margin-left: 20px;" @click="_search_submit">查询</el-button>
          </div>
          <el-button :plain="true" type="info" icon="plus" style="float: right; margin-right: 50px;"
                     @click="_account_add">新增账号
          </el-button>
        </div>
      </div>
      <div class="account_tables">
        <el-table :data="tableData" border height="450" style="width: 100%">
          <el-table-column type="index" width="60"></el-table-column>
          <el-table-column prop="account" label="账号" sortable></el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="belong" label="所属行"></el-table-column>
          <el-table-column prop="role" label="角色"></el-table-column>
          <el-table-column prop="create_time" label="创建日期"></el-table-column>
          <el-table-column label="操作" width="100">
            <template scope="scope">
              <el-button type="text" size="small" @click="_bind(scope.$index,scope.row)">禁用</el-button>
              <el-button type="text" size="small" @click="manage_acount(scope.$index,scope.row)">修该</el-button>
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
    <div class="dialog">
      <el-dialog title="创建账号" :visible.sync="add_account" size="tiny" :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="ruleForm.name"></el-input>
          </el-form-item>
          <el-form-item label="登录密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item label="银行代码" prop="accountNumber">
            <el-input v-model="ruleForm.accountNumber"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="telephone">
            <el-input v-model="ruleForm.telephone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="add_account = false">取 消</el-button>
          <el-button type="primary" @click="_add_account_submit('ruleForm')">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="基本信息修改" :visible.sync="edit_account" size="tiny" :before-close="handleClose">
        <el-form :model="Edit_Form" :rules="rules" ref="Edit_Form" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="username">
            <el-input v-model="Edit_Form.username"></el-input>
          </el-form-item>
          <el-form-item label="证件类型" prop="password">
            <el-input type="text" v-model="Edit_Form.type"></el-input>
          </el-form-item>
          <el-form-item label="证件号" prop="bank_cod">
            <el-input v-model="Edit_Form.bank_cod"></el-input>
          </el-form-item>
          <el-form-item label="联系地址" prop="phone">
            <el-input v-model="Edit_Form.address"></el-input>
          </el-form-item>
          <el-form-item label="电话号码" prop="phone">
            <el-input v-model="Edit_Form.phone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="edit_account = false">取 消</el-button>
          <el-button type="primary" @click="">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="账号修改" :visible.sync="manage_account" size="tiny" :before-close="handleClose">
        <el-form :model="Manage_Form" :rules="Edit_rules" ref="Manage_Form" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="Manage_Form.name"></el-input>
          </el-form-item>
          <el-form-item label="账号" prop="account">
            <el-input type="text" v-model="Manage_Form.account"></el-input>
          </el-form-item>
          <el-form-item label="所属行" prop="belong">
            <el-input v-model="Manage_Form.belong"></el-input>
          </el-form-item>
          <el-form-item label="角色 " prop="role">
            <el-input v-model="Manage_Form.role"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="manage_account = false">取 消</el-button>
          <el-button type="primary" @click="_manage_account_submit('Manage_Form')">确 定</el-button>
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
        tableData: [
//          {
//            account: '',
//            name: '',
//            belong: '苏州分行',
//            role: '',
//            create_time: '',
//            tag: '公司'
//          }
        ],
        currentPage: 1,  // 当前页
        pageSize: 10,    // 每页显示条数
        total: 100,      // 总条数
        add_account: false,
        edit_account: false,
        manage_account: false,
        ruleForm: {
          name: '',
          password: '',
          accountNumber: '',
          telephone: ''
        },
        Edit_Form: {
          username: '中国建设银行股份有限公司苏州分行',
          type: '营业执照',
          bank_cod: '320500000010314',
          address: '苏州市姑苏区阊胥路88号',
          phone: '0512-68268178'
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
        }
      };
    },
    methods: {
      _account_add() {   // 新增账号
        this.add_account = true;
      },
      _add_account_submit(ruleForm) { // 新增提交
        this.$refs[ruleForm].validate((valid) => {
          if (valid) {
            this.$http.post(this.$store.state.Host + '/UserControl/saveUser', this.ruleForm).then((response) => {
              response = response.body;
              console.log(response);
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
        });
      },
      _search_submit() {
        this.freshData(this.search_value);
      },
      Edit() {    // 修改基本信息
        this.edit_account = true;
      },
      _bind(index, row) {  // 禁用
        console.log(row);
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
      manage_acount(index, row) {   // 修改账户信息
        this.manage_account = true;
        this.Manage_Form = row;
      },
      _manage_account_submit(ManageForm) {
        this.$refs[ManageForm].validate((valid) => {
          if (valid) {
            let id = this.Manage_Form.id;
            let name = this.Manage_Form.name;
            let account = this.Manage_Form.account;
            this.$http.post(this.$store.state.Host + '/UserControl/updateUser', {
              id: id,
              name: name,
              accountNumber: account
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
        this.$http.get(this.$store.state.Host + '/UserControl/list', {
          params: {
            pageIndex: this.currentPage,
            pageSize: this.pageSize,
            condition: elements
          }
        }).then((response) => {
          response = response.body;
          if (response.code === 1000) {
            this.total = response.content.totalElements;
            this.tableData = [];
            let data = response.content.content;
            for (var i = 0; i < data.length; i++) {
              let json = {};
              json['id'] = data[i].id;
              json['account'] = data[i].accountNumber;
              json['name'] = data[i].name;
              json['belong'] = '苏州分行';
              json['role'] = data[i].roleStr;
              json['create_time'] = '2017-10-1';
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
      },
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      }
    },
    created() {
      this.freshData();
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
          float: left
          width: 700px;
          padding: 10px 0 10px 70px;
          .btns_sub
            float: left
      .account_tables
        width: 86%
        padding-bottom: 64px
        position: relative
        overflow: hidden
        .Pages
          display: inline-block
          position: absolute
          left: 260px
          bottom: 10px

</style>

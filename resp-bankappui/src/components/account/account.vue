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
            <el-input v-model="input" placeholder="输入账号/姓名可查" style="width: 200px"></el-input>
            <el-button type="primary" icon="search" style="margin-left: 20px;">查询</el-button>
          </div>
          <el-button :plain="true" type="info" icon="plus" style="float: right; margin-right: 50px;"
                     @click="_account_add">新增账号
          </el-button>
        </div>
      </div>
      <div class="account_tables">
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column type="index" width="50"></el-table-column>
          <el-table-column prop="account" label="账号" sortable></el-table-column>
          <el-table-column prop="name" label="姓名"></el-table-column>
          <el-table-column prop="belong" label="所属行"></el-table-column>
          <el-table-column prop="role" label="角色"></el-table-column>
          <el-table-column prop="create_time" label="创建日期"></el-table-column>
          <el-table-column label="操作" width="100">
            <template scope="scope">
              <el-button @click="handleClick" type="text" size="small">禁用</el-button>
              <el-button type="text" size="small">修该</el-button>
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
            :current-page="currentPage4"
            :page-sizes="[10, 20, 30, 40]"
            :page-size="10"
            layout="total, sizes, prev, pager, next, jumper"
            :total="400">
          </el-pagination>
        </div>
      </div>
    </div>
    <div class="dialog">
      <el-dialog title="创建账号" :visible.sync="add_account" size="tiny" :before-close="handleClose">
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="姓名" prop="username">
            <el-input v-model="ruleForm.username"></el-input>
          </el-form-item>
          <el-form-item label="登录密码" prop="password">
            <el-input type="password" v-model="ruleForm.password"></el-input>
          </el-form-item>
          <el-form-item label="银行代码" prop="bank_cod">
            <el-input v-model="ruleForm.bank_cod"></el-input>
          </el-form-item>
          <el-form-item label="手机号码" prop="phone">
            <el-input v-model="ruleForm.phone"></el-input>
          </el-form-item>
        </el-form>
        <span slot="footer" class="dialog-footer">
          <el-button @click="add_account = false">取 消</el-button>
          <el-button type="primary" @click="add_account = false">确 定</el-button>
        </span>
      </el-dialog>
      <el-dialog title="修改账号" :visible.sync="edit_account" size="tiny" :before-close="handleClose">
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
          <el-button type="primary" @click="edit_account = false">确 定</el-button>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  export default {
    data() {
      return {
        input: '', // 查询框
        tableData: [
          {
            account: '15811047085',
            name: '李健',
            belong: '苏州分行',
            role: '管理员',
            create_time: '2017-08-21',
            tag: '公司'
          }, {
            account: '18550086118',
            name: '李爽',
            belong: '唯亭支行',
            role: '操作员',
            create_time: '2017-08-20',
            tag: '公司'
          }
        ],
        currentPage4: 1,
        add_account: false,
        edit_account: false,
        ruleForm: {
          username: '',
          password: '',
          bank_cod: '',
          phone: ''
        },
        Edit_Form: {
          username: '中国建设银行股份有限公司苏州分行',
          type: '营业执照',
          bank_cod: '320500000010314',
          address: '苏州市姑苏区阊胥路88号',
          phone: '0512-68268178'
        },
        rules: {
          username: [
            {required: true, message: '请输入姓名', trigger: 'blur'}
          ]
        }
      };
    },
    methods: {
      _account_add() {
        this.add_account = true;
      },
      Edit() {
        this.edit_account = true;
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
      handleSizeChange(val) {
        console.log(`每页 ${val} 条`);
      },
      handleCurrentChange(val) {
        console.log(`当前页: ${val}`);
      },
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          })
          .catch(_ => {
          });
      }
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
        height: 330px
        padding-bottom: 64px
        position: relative
        .Pages
          display: inline-block
          position: absolute
          left: 200px
          bottom: 8px

</style>

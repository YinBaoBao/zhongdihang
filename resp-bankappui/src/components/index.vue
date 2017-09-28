<template>
  <div>
    <vheader @account="_account"></vheader>
    <div class="context">
      <div class="menu">
        <el-row class="tac">
          <el-col :span="24">
            <el-menu default-active="3" class="el-menu-vertical-demo">
              <el-menu-item index="3" @click="Tabs('search')"><i class="el-icon-search"></i>申报/注销查询
              </el-menu-item>
              <el-menu-item index="1" @click="Tabs('application')"><i class="el-icon-check"></i>申报申请
              </el-menu-item>
              <el-menu-item index="2" @click="Tabs('cancel')"><i class="el-icon-close"></i>注销申请</el-menu-item>
            </el-menu>
          </el-col>
        </el-row>
      </div>
      <div class="tables" ref="tables">
        <router-view :proposer="application.proposer" :mortgage="application.mortgage"
                     :Cancel_p="cancel.proposer" :Cancel_m="cancel.mortgage"
                     @application_add="_application_add"
                     @Look="_Look" @systemdata="_systemdata"></router-view>
      </div>
      <div class="Dialog">
        <el-dialog :title="dialogtitle" :visible.sync="dialogVisible" size="000" top="10%" :before-close="handleClose">
          <handsontable v-if="handtableshow" :Propose="proposer" :index="index"
                        ref="handsontable"></handsontable>
          <Register v-show="registershow" :Propose="proposer" @Jqprint="print"></Register>
          <span slot="footer" class="dialog-footer">
            <el-button @click="Close">取 消</el-button>
            <el-button type="primary" @click="Submit('handsub','registersub')">确 定</el-button>
          </span>
        </el-dialog>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import header from './header/header.vue';
  import handsontable from '../components/dialog/handsontable.vue';
  import Register from '../components/dialog/Register.vue';
  const ERR_OK = 0;

  export default {
    data() {
      return {
        dialogtitle: '',
        dialogVisible: false, // 对话框
        handtableshow: false,
        registershow: false,
        application: '',
        cancel: '',
        proposer: '',
        mortgage: '',
        index: 0
      };
    },
    methods: {
      Tabs(message) {
        this.$router.push({path: '/index/' + message + ''});
      },
      _account() {  // 账户管理
        this.$router.push({path: '/index/account'});
      },
      _application_add(index) {  // 权利申请添加
        this.$forceUpdate();
        this.dialogtitle = '权利申请添加';
        this.dialogVisible = true;
        this.registershow = false;
        this.handtableshow = true;
        this.index = index;
        if (typeof index !== Number) {
          this.index = parseInt(index);
        } else {
          this.index = index;
        }
      },
      _Look() {  // 查看登记申请书
        this.dialogtitle = '登记申请书';
        this.dialogVisible = true;
        this.handtableshow = false;
        this.registershow = true;
      },
      _systemdata() {  // 系统获取数据
        this.appliydata();
      },
      appliydata() {
        this.$http.get(this.$store.state.api + '/application').then((response) => {
          response = response.body;
          if (response.errno === ERR_OK) {
            this.application = response.data;
          }
        });
      },
      Submit() {
        this.$refs.handsontable.keep();
        this.dialogVisible = false;
        this.handtableshow = false;
        this.registershow = false;
      },
      Close() {
        this.dialogVisible = false;
        this.handtableshow = false;
        this.registershow = false;
      },
      print() {
        $('#JQprint').print({
          globalStyles: true,
          mediaPrint: false,
          stylesheet: './css/register.css',
          noPrintSelector: '.no-print',
          iframe: true,
          append: null,
          prepend: null,
          manuallyCopyFormValues: true,
          deferred: $.Deferred(),
          timeout: 750,
          title: null,
          doctype: '<!doctype html>'
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
      this.$router.push({path: '/index/search'});
      this.$http.get(this.$store.state.api + '/application').then((response) => {
        response = response.body;
        if (response.errno === ERR_OK) {
          this.cancel = response.data;
        }
      });
    },
    mounted() {
    },
    components: {
      vheader: header,
      handsontable,
      Register
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .context
    width: 100%;
    overflow: hidden
    padding-bottom: 50px
    .menu
      float: left
      width: 16%;
      height: 100%;
      min-width: 200px;
      background-color: rgb(238, 241, 246);
    .tables
      float: left;
      width: 84%;
</style>

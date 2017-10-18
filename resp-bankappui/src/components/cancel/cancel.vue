<template>
  <div>
    <div class="cancel">
      <div class="can_table">
        <div class="header">
          <table class="table" cellpadding="10" cellspacing="0">
            <tr>
              <td>登记类型</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" value="注销登记" placeholder="">
              </td>
              <td>登记事由</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" value="" placeholder="">
              </td>
              <td>报件编号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" value="201708001000001" placeholder="">
              </td>
              <td>经办人姓名</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" value="" placeholder="">
              </td>
            </tr>
          </table>
        </div>
        <div class="border-height"></div>
        <div class="proposer">
          <div class="inner">
            <table class="table">
              <tr>
                <td :rowspan="proposer.length+1" style="padding: 0;min-width: 27px;vertical-align: middle;"><span
                  class="proposer_situation">申请人情况</span></td>
                <td></td>
                <td>姓名/名称</td>
                <td>证件类型</td>
                <td>证件号</td>
                <td>联系地址</td>
                <td>电话</td>
              </tr>
              <tr v-if="proposer" v-for="(item,index) in proposer">
                <td>{{item.id}}<span class="el-icon-plus proposer_add" @click="_application_add(index,$event)"></span>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.value"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.type"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.zhengjianhao"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.address"></textarea>
                </td>
                <td class="td_text" style="padding: 0;">
                  <textarea class="textarea" name="" cols="" rows="" v-model="item.phone"></textarea>
                </td>
              </tr>
            </table>
          </div>
        </div>
        <div class="border-height"></div>
        <div class="mortgage">
          <table class="table">
            <tr>
              <td rowspan="5" style="padding: 0;min-width: 34px;vertical-align: middle;text-align: center;">
                <span class="mortgage_situation">抵押情况</span>
              </td>
              <td style="min-width: 50px;">债权人</td>
              <td class="td_text" style="padding: 0;">
                <textarea class="textarea" name="" cols="" rows="">中国建设银行股份有限公司苏州分行</textarea>
              </td>
              <td>债务人</td>
              <td class="td_text" style="padding: 0;">
                <textarea class="textarea" name="" cols="" rows="">中国建设银行股份有限公司苏州分行</textarea>
              </td>
              <td style="min-width: 100px;">被担保债权数额</td>
              <td class="td_text" style="padding: 0;min-width: 100px;">
                <input class="input_text" type="text" placeholder="万元">
              </td>
            </tr>
            <tr>
              <td colspan="6" style="padding: 0;">
                <table class="table_0" cellpadding="10" cellspacing="0">
                  <tr>
                    <td style="min-width: 116px;">债务履行开始时间</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="mortage[0].signdate"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 262px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                    <td style="min-width: 116px;">债务履行结束时间</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <el-date-picker
                        v-model="mortage[0].signdate"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 262px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                  </tr>
                  <tr>
                    <td style="min-width: 116px;">抵押合同签署日期</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="mortage[0].signdate"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 262px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                    <td style="min-width: 116px;">抵押注销时间</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="mortage[0].signdate"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 262px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="6" style="padding: 0;">
                <table class="table_1" cellpadding="10" cellspacing="0">
                  <tr>
                    <td>贷款业务编号</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>贷款账号</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>银行抵押业务号</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>抵押合同编号</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input class="input_text" type="text" placeholder=""
                             style="width: 110px;">
                    </td>
                  </tr>
                  <tr>
                    <td>担保范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>在建建筑物抵押范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>房产抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>土地抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="平方米"
                             style="width: 110px;">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="6" style="padding: 0;">
                <table cellpadding="10" cellspacing="0" style="width: 100%;">
                  <tr>
                    <td>不动产价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="万元">
                    </td>
                    <td>土地价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="万元">
                    </td>
                    <td>房产价格</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input class="input_text" type="text" placeholder="万元">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </div>
        <div class="border-height"></div>
        <div class="mortgage">
          <table class="table">
            <tr>
              <td rowspan="5" style="padding: 0;min-width: 34px;vertical-align: middle;text-align: center;"><span
                class="mortgage_situation">不动产情况</span></td>
              <td colspan="">坐落</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="">
              </td>
              <td>不动产单元号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="">
              </td>
            </tr>
            <tr>
              <td style="min-width: 100px;">不动产权证明号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="">
              </td>
              <td style="min-width: 118px;">不动产权属证书号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="">
              </td>
            </tr>
            <tr>
              <td colspan="">所属区县</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="">
              </td>
              <td>区县编号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="">
              </td>
            </tr>
            <tr>
              <td colspan="4" style="padding: 0;">
                <table class="table_1" cellpadding="10" cellspacing="0">
                  <tr>
                    <td style="min-width: 100px;">土地使用起始日</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="mortage[0].enddate"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 120px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                    <td style="min-width: 100px;">土地使用结束日</td>
                    <td class="td_text" style="padding: 0;">
                      <el-date-picker
                        v-model="mortage[0].enddate"
                        type="date"
                        placeholder="选择日期"
                        :picker-options="pickerOptions"
                        style="width: 120px; margin-left: -1px;">
                      </el-date-picker>
                    </td>
                    <td style="min-width: 58px;">权利性质</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="" value="">
                    </td>
                    <td style="min-width: 58px;">所属区县</td>
                    <td class="td_text" style="padding: 0;border-right: none;">
                      <input class="input_text" type="text" placeholder="" value="">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="list">
        <list @canLook="Look_can" @cansystemdata="systemdata"></list>
      </div>
    </div>
    <div class="dialog">
      <el-dialog title="登记申请书" :visible.sync="canVisible" size="000" top="10%" :before-close="handleClose">
        <Register></Register>
        <span slot="footer" class="dialog-footer">
            <el-button @click="canVisible = false">取 消</el-button>
            <el-button type="primary" @click="">确 定</el-button>
          </span>
      </el-dialog>
      <el-dialog title="权利人添加" :visible.sync="handVisible" size="000" top="10%" :before-close="handleClose">
        <handsontable></handsontable>
        <span slot="footer" class="dialog-footer">
            <el-button @click="handVisible = false">取 消</el-button>
            <el-button type="primary" @click="handVisible = false">确 定</el-button>
          </span>
      </el-dialog>
    </div>
  </div>
</template>
<script type="text/ecmascript-6">
  import list from '../list/list.vue';
  import Register from '../../components/dialog/Register.vue';
  import handsontable from '../../components/dialog/handsontable.vue';

  const ERR_OK = 0;

  export default {
    data() {
      return {
        canVisible: false, // 查看登记申请书
        handVisible: false,    //  申请人情况添加
        options: [
          {
            value: '选项1',
            label: '首次登记'
          }, {
            value: '选项2',
            label: '变更登记'
          }
        ],
        value: '选项1',
        pickerOptions: {
          shortcuts: [
            {
              text: '今天',
              onClick(picker) {
                picker.$emit('pick', new Date());
              }
            }, {
              text: '昨天',
              onClick(picker) {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24);
                picker.$emit('pick', date);
              }
            }, {
              text: '一周前',
              onClick(picker) {
                const date = new Date();
                date.setTime(date.getTime() - 3600 * 1000 * 24 * 7);
                picker.$emit('pick', date);
              }
            }
          ]
        },
        Cancel: '',
        Lx: '',
        proposer: [
          {
            id: '权利人',
            value: '',
            type: '',
            zhengjianhao: '',
            address: '',
            phone: ''
          }, {
            id: '代理人',
            value: '',
            type: '',
            zhengjianhao: '',
            address: '',
            phone: ''
          }, {
            id: '义务人',
            value: '',
            type: '',
            zhengjianhao: '',
            address: '',
            phone: ''
          }
        ],
        mortage: [
          {
            guarantee: '',
            signdate: null,
            bankcount: '',
            stardate: '',
            enddate: '',
            loannumbe: '',
            estateprice: '',
            landprice: '',
            Houseprice: '',
            Loanaccont: '',
            Guaranteescope: '',
            Mortgagescope: '',
            Mortgagearea: '',
            Landmortgagearea: '',
            creditor: '',
            debtor: ''
          }
        ]
      };
    },
    methods: {
      Look_can() {  // 查看登记申请书
        this.canVisible = true;
      },
      systemdata() {  // 获取系统数据
        this.$http.get(this.$store.state.api + '/application').then((response) => {
          response = response.body;
          if (response.errno === ERR_OK) {
            this.application = response.data;
          }
        });
      },
      _application_add(index, event) {
        this.handVisible = true;
      },
      handleClose(done) {  // 弹框
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
            this.regVisible = false;
            this.handVisible = false;
          })
          .catch(_ => {
          });
      }
    },
    created() {
      this.$http.get(this.$store.state.api + '/success').then((response) => {
        response = response.body;
        this.Cancel = response.data.body;
//        console.log(this.Cancel);
        this.Lx = this.Cancel.sqdjsy;
      });
    },
    watch: {
      proposer: {}
    },
    components: {
      list,
      Register,
      handsontable
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .cancel
    .can_table
      float: left
      width: 74%
      min-width: 776px
      font-size: 14px
      overflow: hidden
      .header
        width: 100%
        .table
          width: 100%
          border: 1px solid #DFE6EC
          text-align: center
          td
            padding: 6px;
            border-bottom: 1px solid #DFE6EC;
            border-right: 1px solid #DFE6EC;
            min-width: 70px
            vertical-align: middle
      .proposer
        width: 100%
        height: 148px
        overflow: auto
        .inner
          min-height: 200px
          .table
            float: left
            width: 100%
            border: 1px solid #DFE6EC
            text-align: center
            margin-top: 4px
            td
              padding: 6px;
              border-bottom: 1px solid #DFE6EC;
              border-right: 1px solid #DFE6EC;
              min-width: 70px
              vertical-align: middle
              .proposer_situation
                display: inline-block
                width: 15px
              .proposer_add
                display: inline-block
                margin-left: 5px
                color: #20A0FF;
                font-size: 12px
                cursor: pointer
      .mortgage
        width: 100%
        .table
          width: 100%
          border: 1px solid #DFE6EC
          .mortgage_situation
            display: inline-block
            width: 15px
          td
            padding: 6px;
            border-bottom: 1px solid #DFE6EC
            border-right: 1px solid #DFE6EC
            min-width: 70px
            vertical-align: middle
            .table_0
              width: 100%
            .table_1
              width: 100%
            .table_0 tr
              &:last-child td
                border-bottom: 1px solid #fff
            .table_1 tr
              &:last-child td
                border-bottom: 1px solid #fff
            .table_2 td
              border-bottom: 1px solid #fff
        .el-input__inner
          border-radius: 0
          border: none
          border-left: 1px solid #dfe6ec
      .td_text
        padding: 0
        font-size: 0
        vertical-align: middle
        .textarea
          width: 100%
          border: none
          padding: 2px 0
          line-height: 16px;
          text-indent: 6px
          font-size: 12px
          resize: none
        .input_text
          width: 100%
          padding: 9px 0
          font-size: 14px
          text-indent: 6px
    .list
      float: left
      width: 26%
      min-width: 290px
</style>

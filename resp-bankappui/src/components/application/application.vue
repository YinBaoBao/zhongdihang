<template>
  <div>
    <div class="application">
      <div class="app_table">
        <div class="header">
          <table class="table" cellpadding="10" cellspacing="0">
            <tr>
              <td>登记类型</td>
              <td style="min-width: 104px;padding: 0; background-color: #EEF1F6;">
                <el-select v-model="value" placeholder="请选择">
                  <el-option v-for="item in options" :key="item.value" :label="item.label"
                             :value="item.value"></el-option>
                </el-select>
              </td>
              <td>登记事由</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input class="input_text" type="text" value="" placeholder="" style="width: 90px;">
              </td>
              <td>报件编号</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input class="input_text" type="text" value="201708001000001" placeholder="" style="width: 110px;">
              </td>
              <td>经办人姓名</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input class="input_text" type="text" value="" placeholder="" style="width: 90px;">
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
              <td rowspan="5" style="padding: 0;min-width: 34px;vertical-align: middle;text-align: center;"><span
                class="mortgage_situation">抵押情况</span></td>
              <td colspan="">被担保债权数额</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="mortgage.guarantee" value="万元" class="input_text" type="text" placeholder="万元">
              </td>
              <td>抵押合同签署日期</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="mortgage.signdate" class="input_text" type="text" placeholder="">
              </td>
              <td style="">银行抵押业务号</td>
              <td class="td_text" style="padding: 0;width: 40px;">
                <input v-model="mortgage.bankcount" class="input_text" type="text" placeholder="">
              </td>
            </tr>
            <tr>
              <td colspan="">债务履行开始时间</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="mortgage.stardate" class="input_text" type="text" placeholder="">
              </td>
              <td>债务履行结束时间</td>
              <td class="td_text" style="padding: 0;">
                <input v-model="mortgage.enddate" class="input_text" type="text" placeholder="">
              </td>
              <td colspan="">贷款业务编号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="">
              </td>
            </tr>
            <tr>
              <td colspan="6" style="padding: 0;">
                <table class="table_1" cellpadding="10" cellspacing="0">
                  <tr>
                    <td>不动产价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>土地价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>房产价格</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;">
                    </td>
                    <td>贷款账号</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder=""
                             style="width: 110px;">
                    </td>
                  </tr>
                  <tr>
                    <td>担保范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;height: 30px;">
                    </td>
                    <td>在建建筑物抵押范围</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;height: 30px;">
                    </td>
                    <td>房产抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="万元"
                             style="width: 80px;height: 30px;">
                    </td>
                    <td>土地抵押面积</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text"
                             placeholder="平方米"
                             style="width: 110px;height: 30px;">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td colspan="8" style="padding: 0;">
                <table class="table_2" cellpadding="10" cellspacing="0" style="width: 100%;">
                  <tr>
                    <td>债权人</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder=""
                             value="中国建设银行股份有限公司苏州分行"
                             style="width: 270px">
                    </td>
                    <td>债务人</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder=""
                             value=""
                             style="width: 294px">
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
                <input class="input_text" type="text" placeholder="" value="" style="width: 246px;">
              </td>
              <td>不动产单元号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="" style="width: 246px;">
              </td>
            </tr>
            <tr>
              <td colspan="">不动产权证明号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="" style="width: 246px;">
              </td>
              <td>不动产权属证书号</td>
              <td class="td_text" style="padding: 0;">
                <input class="input_text" type="text" placeholder="" value="" style="width: 246px;">
              </td>
            </tr>
            <tr>
              <td colspan="4" style="padding: 0;">
                <table class="table_1" cellpadding="10" cellspacing="0">
                  <tr>
                    <td style="min-width: 100px;">土地使用起始日</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="" value="" style="width: 100px;">
                    </td>
                    <td style="min-width: 100px;">土地使用结束日</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="" value="" style="width: 100px;">
                    </td>
                    <td style="min-width: 58px;">权利性质</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="" value="" style="width: 60px;">
                    </td>
                    <td style="min-width: 58px;">所属区县</td>
                    <td class="td_text" style="padding: 0;">
                      <input class="input_text" type="text" placeholder="" value="" style="width: 60px;">
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="list">
        <list @Look="_List" @systemdata="systemdata"></list>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import list from '../list/list.vue';

  export default {
    props: {
      proposer: {
        type: Array,
        default() {
          return [
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
          ];
        }
      },
      mortgage: {}
    },
    data() {
      return {
        options: [{
          value: '选项1',
          label: '首次登记'
        }, {
          value: '选项2',
          label: '变更登记'
        }],
        value: '选项1'
      };
    },
    methods: {
      _List() {
        this.$emit('Look');
      },
      systemdata() {
        this.$emit('systemdata');
      },
      _application_add(index, event) {
        this.$forceUpdate();
        this.$emit('application_add', index);
      }
    },
    created() {
//      this.proposer = this.$store.state.application.proposer;
//      console.log(this.proposer);
//      console.log(this.proposer);
//      console.log(this.$store.state.proposer);
    },
    watch: {
      proposer: {
        handler: function(val, oldVal) {
          this.$store.commit('proposer', val);
        },
        deep: true
      }
    },
    components: {
      list
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .application
    .app_table
      float: left
      width: 808px
      font-size: 14px
      .header
        width: 100%
        .table
          width: 100%
          border: 1px solid #DFE6EC
          text-align: center
          td
            padding: 10px;
            border-bottom: 1px solid #DFE6EC;
            border-right: 1px solid #DFE6EC;
            min-width: 70px
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
            td
              padding: 10px;
              border-bottom: 1px solid #DFE6EC;
              border-right: 1px solid #DFE6EC;
              min-width: 70px
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
            padding: 10px;
            border-bottom: 1px solid #DFE6EC
            border-right: 1px solid #DFE6EC
            min-width: 70px
            .table_1 tr
              &:last-child td
                border-bottom: 1px solid #fff
            .table_2 td
              border-bottom: 1px solid #fff
      .td_text
        padding: 0
        font-size: 0
        vertical-align: middle
        .textarea
          width: 120px
          border: none
          padding: 2px 4px
          margin: 0 -12px
          vertical-align: middle
          text-align: center
          line-height: 16px;
          font-size: 12px
          resize: none
        .input_text
          width: 108px
          padding: 9px 6px
          font-size: 14px
    .list
      float: left
      width: 336px
</style>

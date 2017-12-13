<template>
  <div>
    <div id="JQprint">
      <div class="header">
        <div class="barcode">
          <barcode :value="$store.state.Bjbh" :options="{ displayValue: true }"
                   style="width: 100%;height: 100%;"></barcode>
        </div>
        <h2 class="title">苏州不动产登记申请书</h2>
        <p class="unit">单位: 平方米/万元</p>
      </div>
      <div id="Table">
        <div class="selects">
          <table cellpadding="10" cellspacing="0" class="tabs">
            <tr>
              <td class="td_w" rowspan="2" style="border-bottom: 1px solid #000;">
                <span class="title">申请登记事由</span>
              </td>
              <td>
                <ul class="list" v-if="checkdata[0]">
                  <li v-for="item in checkdata[0]">
                    <input type="checkbox" :checked="item.cheched" :id="item.id" style="font-size: 20px" class="ipts">
                    <label :for="item.id" class="labs">{{item.value}}</label>
                  </li>
                </ul>
              </td>
            </tr>
            <tr>
              <td>
                <ul class="list" v-if="checkdata[1]">
                  <li v-for="item in checkdata[1]">
                    <input type="checkbox" :id="item.id" style="font-size: 20px" class="ipts">
                    <label :for="item.id" class="labs">{{item.value}}</label>
                  </li>
                </ul>
              </td>
            </tr>
            <tr>
              <td colspan="2" style="padding: 0;">
                <table class="tabs0" cellpadding="10" cellspacing="0" style="width: 100%;">
                  <tr>
                    <td class="td_w" rowspan="9">
                      <span class="title">申请人情况</span>
                    </td>
                    <td colspan="2">姓名或名称</td>
                    <td>证件类别</td>
                    <td>证件号</td>
                    <td>联系地址或电话</td>
                  </tr>
                  <tr>    <!--// =============申请人情况=============-->
                    <td rowspan="3" style="width: 50px;">
                      <span>权利人</span>
                    </td>
                    <td style="width: 130px;">{{Qlrs[0].qlrmc}}</td>
                    <td>{{Qlrs[0].qlrzjzlmc}}</td>
                    <td>{{Qlrs[0].qlrzjh}}</td>
                    <td>{{Qlrs[0].qlrdh}}</td>
                  </tr>
                  <tr>
                    <td>{{Qlrs[1].qlrmc}}</td>
                    <td>{{Qlrs[1].qlrzjzlmc}}</td>
                    <td>{{Qlrs[1].qlrzjh}}</td>
                    <td>{{Qlrs[1].qlrdh}}</td>
                  </tr>
                  <tr>
                    <td>{{Qlrs[2].qlrmc}}</td>
                    <td>{{Qlrs[2].qlrzjzlmc}}</td>
                    <td>{{Qlrs[2].qlrzjh}}</td>
                    <td>{{Qlrs[2].qlrdh}}</td>
                  </tr>
                  <tr>  <!--代理人-->
                    <td>
                      <span>代理人</span>
                    </td>
                    <td>{{Propose.qlrdlr.qlrdlrmc}}</td>
                    <td>{{Propose.qlrdlr.qlrdlrzjzlmc}}</td>
                    <td>{{Propose.qlrdlr.qlrdlrzjh}}</td>
                    <td>{{Propose.qlrdlr.qlrdlrdz}}</td>
                  </tr>     <!--代理人-->
                  <tr>  <!--义务人-->
                    <td rowspan="3">
                      <span>义务人</span>
                    </td>
                    <td>{{Ywrs[0].ywrmc}}</td>
                    <td>{{Ywrs[0].ywrzjzlmc}}</td>
                    <td>{{Ywrs[0].ywrzjh}}</td>
                    <td>{{Ywrs[0].ywrdh}}</td>
                  </tr>
                  <tr>
                    <td>{{Ywrs[1].ywrmc}}</td>
                    <td>{{Ywrs[1].ywrzjzlmc}}</td>
                    <td>{{Ywrs[1].ywrzjh}}</td>
                    <td>{{Ywrs[1].ywrdh}}</td>
                  </tr>
                  <tr>
                    <td>{{Ywrs[2].ywrmc}}</td>
                    <td>{{Ywrs[2].ywrzjzlmc}}</td>
                    <td>{{Ywrs[2].ywrzjh}}</td>
                    <td>{{Ywrs[2].ywrdh}}</td>
                  </tr>     <!--义务人-->
                  <tr>  <!--代理人-->
                    <td>
                      <span>代理人</span>
                    </td>
                    <td>{{Propose.ywrdlr.ywrdlrmc}}</td>
                    <td>{{Propose.ywrdlr.ywrdlrzjzlmc}}</td>
                    <td>{{Propose.ywrdlr.ywrdlrzjh}}</td>
                    <td>{{Propose.ywrdlr.ywrdlrdh}}</td>
                  </tr>     <!--代理人-->  <!--// =============申请人情况=============-->
                  <tr>        <!--//==============不动产情况===========-->
                    <td rowspan="6" class="td_w">
                      <span class="title">不动产情况</span>
                    </td>
                    <td colspan="2">坐落</td>
                    <td colspan="3" style="text-align: left;">{{bdcqk.zl}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">不动产单元号</td>
                    <td colspan="3" style="text-align: left;">{{bdcqk.bdcdyh}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">原不动产权属证书</td>
                    <td colspan="3" style="text-align: left;">{{bdcqk.bdcqzshy}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">房屋用途</td>
                    <td>{{bdcqk.fwytmc}}</td>
                    <td>房屋面积</td>
                    <td>{{bdcqk.fwmj}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">土地用途</td>
                    <td>{{bdcqk.tdytmc}}</td>
                    <td>土地面积</td>
                    <td>{{bdcqk.tdmj}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">土地使用年限</td>
                    <td>{{bdcqk.tdsyqssj}} 至 {{bdcqk.tdsyjssj}}</td>
                    <td>权力性质</td>
                    <td>{{bdcqk.qlxz}}</td>
                  </tr>     <!--//==============不动产情况===========-->
                  <tr>         <!--//==============抵押情况===========-->
                    <td class="td_w" rowspan="3">
                      <span class="title">抵押情况</span>
                    </td>
                    <td colspan="2">被担保债权数额<br>(最高债权数额)</td>
                    <td>{{mortgage.bdbzqse}}</td>
                    <td>债务履行期限<br>(债权确定期限)</td>
                    <td>{{mortgage.zwlxqssj}} 至 {{mortgage.zwlxjssj}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">担保范围</td>
                    <td>{{mortgage.dbfw}}</td>
                    <td rowspan="2" style="padding: 0;">
                      <table cellpadding="10" cellspacing="0" style="width: 100%;">
                        <tr>
                          <td rowspan="2" style="border-bottom: none;width: 40px;min-width: 40px;">抵押面积</td>
                          <td style="border-right: none;width: 30px;min-width: 30px;">房产</td>
                        </tr>
                        <tr>
                          <td style="border-bottom: none;border-right: none;width: 30px;min-width: 30px;">土地</td>
                        </tr>
                      </table>
                    </td>
                    <td>{{mortgage.fcdymj}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">在建建筑物抵押范围</td>
                    <td>{{mortgage.zjjzwdyfw}}</td>
                    <td>{{mortgage.tddymj}}</td>
                  </tr>       <!--//==============抵押情况===========-->
                  <tr>        <!--//==============地役权情况===========-->
                    <td class="td_w" rowspan="2">
                      <span class="title">地役权情况</span>
                    </td>
                    <td colspan="2">坐落</td>
                    <td colspan="4" style="text-align: left;">{{bdcqk.zl}}</td>
                  </tr>
                  <tr>
                    <td colspan="2">不动产单元号</td>
                    <td colspan="4" style="text-align: left;">{{bdcqk.bdcdyh}}</td>
                  </tr>        <!--//==============地役权情况===========-->
                  <tr>          <!--//==============签字===========-->
                    <td colspan="6" style="border-bottom: none;text-align: left;">
                      本申请人对填写的上述内容，声明及提交的申请材料的真实性负责。如有不实，申请人愿承担法律责任。
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="text-align: left;text-indent: 74px;border-right: none;border-bottom: none;">
                      权力人(代理人)签章:
                    </td>
                    <td colspan="3" style="text-align: left;text-indent: 74px;border-bottom: none;">义务人(代理人)签章:</td>
                  </tr>
                  <tr>
                    <td colspan="3" style="border-right: none;border-bottom: none;text-indent: 140px;">
                      年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
                    </td>
                    <td colspan="3" style="border-bottom: none;">
                      年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
                    </td>
                  </tr>
                  <tr>
                    <td colspan="3" style="border-right: none;text-indent: 50px;">经办人签字:</td>
                    <td colspan="3" style="text-align: left;text-indent: 80px;">
                      年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
                    </td>
                  </tr>         <!--//==============签字===========-->
                  <tr>          <!--//==============询问事项===========-->
                    <td class="td_w" rowspan="6" style="text-align: center;border-bottom: none;">
                      <span class="title">询问事项</span>
                    </td>
                    <td colspan="5" style="text-align: left;border-bottom: none;">
                      <span style="vertical-align: middle;">1.申请的不动产是否共有？</span>
                      <span style="padding-left: 10px;">
                        <input class="check" type="checkbox" :checked="_sfgyIs" id="Is">
                        <label class="labs" for="Is">是</label>
                      </span>
                      <span style="padding-left: 10px;">
                        <input class="check" type="checkbox" :checked="_sfgyNo" id="No" style="font-size: 20px">
                        <label class="labs" for="No">否</label>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="border-bottom: none;text-align: left;">
                      <span style="vertical-align: middle;">2.共有类型: </span>
                      <input class="check" type="checkbox" id="" style="font-size: 20px">
                      <label class="labs" for="">按份共有 &nbsp;&nbsp;&nbsp;份额情况 :</label>
                      <input class="feqk" readonly="readonly" type="text">
                      <span style="padding-left: 10px;">
                        <input class="check" type="checkbox" id="gt" style="font-size: 20px">
                        <label class="labs" for="gt">共同共有</label>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td colspan="5" style="border-bottom: none;text-align: left;">
                      <span style="vertical-align: middle;">3.共有人是否申请分别持证？</span>
                      <span style="padding-left: 10px;">
                        <input class="check" type="checkbox" :checked="_sffbczIs" id="Iss" style="font-size: 20px">
                        <label class="labs" for="Iss">是</label>
                      </span>
                      <span style="padding-left: 10px;">
                        <input class="check" type="checkbox" :checked="_sffbczNo" id="Noo" style="font-size: 20px">
                        <label class="labs" for="Noo">否</label>
                      </span>
                    </td>
                  </tr>
                  <tr style="border-bottom: none;">
                    <td colspan="5" style="border-bottom: none;text-align: left;">4.其他说明</td>
                  </tr>
                  <tr>
                    <td colspan="5" style="text-align: right;padding-right: 200px;border-bottom: none;">权利人（代理人）签章:</td>
                  </tr>
                  <tr>
                    <td colspan="5" style="border-bottom: none;text-align: right;padding-right: 100px;">
                      年 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 日
                    </td>
                  </tr>         <!--//==============询问事项===========-->
                </table>
              </td>
            </tr>
          </table>
        </div>
      </div>
      <div class="mack">
        <span>苏州不动产中心印制</span>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import VueBarcode from '@xkeshi/vue-barcode';
  export default {
    props: {},
    data() {
      return {
        checkdata: [  // 多选框
          [
            {
              id: 'a',
              value: '国有建设用地使用权',
              cheched: ''
            },
            {
              id: 'b',
              value: '集体土地所有权'
            },
            {
              id: 'c',
              value: '宅基地使用权'
            },
            {
              id: 'd',
              value: '集体建设使用权'
            },
            {
              id: 'e',
              value: '林地使用权'
            },
            {
              id: 'f',
              value: '水域滩涂使用权'
            },
            {
              id: 'g',
              value: '房屋所有权'
            },
            {
              id: 'h',
              value: '构筑物所有权'
            },
            {
              id: 'i',
              value: '抵押权'
            },
            {
              id: 'g',
              value: '地役权'
            },
            {
              id: 'k',
              value: '其他'
            }
          ],
          [
            {
              id: 'aa',
              value: '首次登记'
            },
            {
              id: 'bb',
              value: '转移登记'
            },
            {
              id: 'cc',
              value: '变更登记'
            },
            {
              id: 'dd',
              value: '注销登记'
            },
            {
              id: 'ee',
              value: '更正登记'
            },
            {
              id: 'ff',
              value: '异议登记'
            },
            {
              id: 'gg',
              value: '预告登记'
            },
            {
              id: 'hh',
              value: '其他'
            }
          ]
        ],  // 多选框
        Propose: '',
        mortgage: '',
        bdcqk: '',
        xwsx: '',
        Qlrs: [
          {
            bjbh: '',
            qlrdh: '',
            qlrdz: '',
            qlrlx: '',
            qlrlxmc: '',
            qlrmc: '',
            qlrunid: '',
            qlrxh: '',
            qlrzjh: '',
            qlrzjzl: '',
            qlrzjzlmc: '',
            qlrzl: '',
            qlrzlmc: ''
          },
          {
            bjbh: '',
            qlrdh: '',
            qlrdz: '',
            qlrlx: '',
            qlrlxmc: '',
            qlrmc: '',
            qlrunid: '',
            qlrxh: '',
            qlrzjh: '',
            qlrzjzl: '',
            qlrzjzlmc: '',
            qlrzl: '',
            qlrzlmc: ''
          },
          {
            bjbh: '',
            qlrdh: '',
            qlrdz: '',
            qlrlx: '',
            qlrlxmc: '',
            qlrmc: '',
            qlrunid: '',
            qlrxh: '',
            qlrzjh: '',
            qlrzjzl: '',
            qlrzjzlmc: '',
            qlrzl: '',
            qlrzlmc: ''
          }
        ],
        Ywrs: [
          {
            ywrunid: '',
            bjbh: '',
            ywrxh: '',
            ywrzl: '',
            ywrzlmc: '',
            ywrlx: '',
            ywrlxmc: '',
            ywrmc: '',
            ywrzjzl: '',
            ywrzjzlmc: '',
            ywrzjh: '',
            ywrdh: '',
            ywrdz: ''
          },
          {
            ywrunid: '',
            bjbh: '',
            ywrxh: '',
            ywrzl: '',
            ywrzlmc: '',
            ywrlx: '',
            ywrlxmc: '',
            ywrmc: '',
            ywrzjzl: '',
            ywrzjzlmc: '',
            ywrzjh: '',
            ywrdh: '',
            ywrdz: ''
          },
          {
            ywrunid: '',
            bjbh: '',
            ywrxh: '',
            ywrzl: '',
            ywrzlmc: '',
            ywrlx: '',
            ywrlxmc: '',
            ywrmc: '',
            ywrzjzl: '',
            ywrzjzlmc: '',
            ywrzjh: '',
            ywrdh: '',
            ywrdz: ''
          }
        ]
      };
    },
    computed: {
      _sfgyIs() {
//        return this.xwsx.sfgy === true ? 'checked' : '';
        return '';
      },
      _sfgyNo() {
//        return this.xwsx.sfgy === true ? '' : 'chechbox';
        return '';
      },
      _sffbczIs() {
//        return this.xwsx.sffbcz === true ? 'chechbox' : '';
        return '';
      },
      _sffbczNo() {
//        return this.xwsx.sffbcz === true ? '' : 'chechbox';
        return '';
      }
    },
    methods: {
      winprint() {
        this.$nextTick(() => {
          window.print();
          this.$router.push({path: '/index'});
          return true;
        });
      },
      filesqqk() {
        let qlrs = this.Propose.qlrs;
        let ywrs = this.Propose.ywrs;
        for (var i = 0; i < qlrs.length; i++) {
          this.Qlrs[i] = qlrs[i];
        }
        for (var k = 0; k < ywrs.length; k++) {
          this.Ywrs[k] = ywrs[k];
        }
      }
    },
    created() {
      this.Propose = this.$store.state.Propose;
      this.mortgage = this.$store.state.mortgage;
      this.bdcqk = this.$store.state.bdcqk;
      this.xwsx = this.$store.state.xwsx;
      this.filesqqk();
    },
    mounted() {
    },
    components: {
      'barcode': VueBarcode
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  #JQprint
    width: 800px
    margin: 0 auto
    overflow: hidden;
    position: relative;
    font-family: '宋体'
    .header
      width: 800px
      height: 72px;
      margin: 0 20px
      text-align: center
      .barcode
        float: left
        width: 190px;
        height: 72px;
        padding: 0 15px
      .title
        display: inline-block
        line-height: 70px
        font-size: 28px
        font-weight: 500
      .unit
        display: inline-block
        float: right
        padding: 4px 15px
        margin-top: 46px
        margin-right: 10px
    #Table
      width: 800px
      margin: 2px auto
      .selects
        width: 100%
        .tabs
          width: 100%
          border: 1px solid #000
          border-right-color: #fff
          border-bottom-color: #fff
          .list li
            float: left
            padding: 6px 10px
            .ipts
              width: 16px;
              height: 14px;
              margin-top: -1px
              vertical-align: middle;
            .labs
              padding-left: 5px
              vertical-align: middle;
          .tabs0 tr td
            &:last-child
              border-right: none
          td
            height: 14px
            padding: 6px;
            border-bottom: 1px solid #000
            border-right: 1px solid #000
            min-width: 70px
            line-height: 16px
            text-align: center
            vertical-align: middle
          .td_w
            width: 18px
            min-width: 70px
            padding: 10px 4px
            text-align: center
            vertical-align: middle
            .title
              display: inline-block
              width: 15px
          .feqk
            width: 200px
            border-bottom: 1px solid #000
          .feqk:focus
            outline: none;
          .check
            vertical-align: middle
          .labs
            vertical-align: middle
            padding: 0 4px
    .mack
      height: 60px
      line-height: 40px
      text-align: right
      span
        padding-right: 16px
        font-size: 14px
</style>
<style media="print">
  @page {
    size: auto;  /* auto is the initial value */
    margin: 0mm 10mm; /* this affects the margin in the printer settings */
  }

  @media print {
    #JQprint {
      margin-top: 10px;
    }

    .header {
      width: 800px
    }

    #Table {
      width: 800px
    }
  }
</style>

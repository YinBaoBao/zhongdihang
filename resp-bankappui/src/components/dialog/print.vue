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
              <td class="td_w" rowspan="2" style="border-bottom: 1px solid #DFE6EC;">
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
            <tr>      <!--//申请人情况-->
              <td colspan="2" style="padding: 0;">
                <table class="tabs0" cellpadding="10" cellspacing="0">
                  <tr>
                    <td class="td_w" rowspan="9">
                      <span class="title">申请人情况</span>
                    </td>
                    <td>姓名或名称</td>
                    <td>证件类别</td>
                    <td>证件号</td>
                    <td>联系地址或电话</td>
                  </tr>
                  <tr>
                    <td rowspan="3" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td rowspan="3" style="border-bottom: none;width: 124px;;">
                            <span>权利人</span>
                          </td>
                          <td>{{Propose.qlrs[0].qlrmc}}</td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td>{{Propose.qlrs[0].qlrzjzlmc}}</td>
                    <td>{{Propose.qlrs[0].qlrzjh}}</td>
                    <td>{{Propose.qlrs[0].qlrdh}}</td>
                  </tr>     <!--权利人-->
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>  <!--代理人-->
                    <td style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="border-bottom: none;width: 124px;;">
                            <span>代理人</span>
                          </td>
                          <td>{{Propose.qlrdlr.qlrdlrmc}}</td>
                        </tr>
                      </table>
                    </td>
                    <td>{{Propose.qlrdlr.qlrdlrzjzl}}</td>
                    <td>{{Propose.qlrdlr.qlrdlrzjh}}</td>
                    <td>{{Propose.qlrdlr.qlrdlrdz}}</td>
                  </tr>     <!--代理人-->
                  <tr>  <!--义务人-->
                    <td rowspan="3" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td rowspan="3" style="border-bottom: none;width: 124px;;">
                            <span>义务人</span>
                          </td>
                          <td>{{Propose.ywrs[0].ywrmc}}</td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td>{{Propose.ywrs[0].ywrzjzlmc}}</td>
                    <td>{{Propose.ywrs[0].ywrzjh}}</td>
                    <td>{{Propose.ywrs[0].ywrdh}}</td>
                  </tr>     <!--义务人-->
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>
                  <tr>  <!--代理人-->
                    <td style="border-bottom: none;padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="border-bottom: none;width: 124px;;">
                            <span>代理人</span>
                          </td>
                          <td>{{Propose.ywrdlr.ywrdlrmc}}</td>
                        </tr>
                      </table>
                    </td>
                    <td style="border-bottom: none;">{{Propose.ywrdlr.ywrdlrzjzl}}</td>
                    <td style="border-bottom: none;">{{Propose.ywrdlr.ywrdlrzjh}}</td>
                    <td style="border-bottom: none;">{{Propose.ywrdlr.ywrdlrdh}}</td>
                  </tr>     <!--代理人-->
                </table>
              </td>
            </tr>     <!--//申请人情况-->
            <tr>   <!--//不动产情况-->
              <td colspan="2" style="padding: 0;">
                <table class="tabs0" cellpadding="10" cellspacing="0">
                  <tr>
                    <td class="td_w" rowspan="6">
                      <span class="title">不动产情况</span>
                    </td>
                    <td style="width: 124px;;">坐落</td>
                    <td>{{bdcqk.zl}}</td>
                  </tr>
                  <tr>
                    <td>不动产单元号</td>
                    <td>{{bdcqk.bdcdyh}}</td>
                  </tr>
                  <tr>
                    <td>原不动产权属证书</td>
                    <td>{{bdcqk.bdcqzshy}}</td>
                  </tr>
                  <tr>
                    <td colspan="2" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="width: 124px;;">房屋用途</td>
                          <td>{{bdcqk.fwyt}}</td>
                          <td style="width: 124px;;">房屋面积</td>
                          <td>{{bdcqk.fwmj}}</td>
                        </tr>
                        <tr>
                          <td>土地用途</td>
                          <td>{{bdcqk.tdyt}}</td>
                          <td>土地面积</td>
                          <td>{{bdcqk.tdmj}}</td>
                        </tr>
                        <tr>
                          <td>土地使用年限</td>
                          <td>{{bdcqk.tdsyqssj}} 至 {{bdcqk.tdsyjssj}}</td>
                          <td>权力性质</td>
                          <td>{{bdcqk.qlxz}}</td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>     <!--//不动产情况-->
            <tr>   <!--//抵押情况-->
              <td colspan="2" style="padding: 0;">
                <table class="tabs0" cellpadding="10" cellspacing="0">
                  <tr>
                    <td class="td_w" rowspan="3">
                      <span class="title">抵押情况</span>
                    </td>
                    <td style="width: 124px;;">被担保债权数额<br>（最高债权数额）</td>
                    <td>{{mortgage.bdbzqse}}</td>
                    <td style="width: 124px;;">债务履行期限<br>（债权确定期限）</td>
                    <td>{{mortgage.zwlxqssj}} 至 {{mortgage.zwlxjssj}}</td>
                  </tr>
                  <tr>
                    <td colspan="4" style="border-bottom: none;padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="width: 124px;;">担保范围</td>
                          <td>{{mortgage.dbfw}}</td>
                          <td rowspan="2" style="width: 80px;">抵押面积</td>
                          <td style="width: 100px;">房产</td>
                          <td>{{mortgage.fcdymj}}</td>
                        </tr>
                        <tr>
                          <td>在建建筑物抵押范围</td>
                          <td>{{mortgage.zjjzwdyfw}}</td>
                          <td>土地</td>
                          <td>{{mortgage.tddymj}}</td>
                        </tr>
                        <tr>
                          <td>土地使用年限</td>
                          <td>{{bdcqk.tdsyqssj}} 至 {{bdcqk.tdsyjssj}}</td>
                          <td>权力性质</td>
                          <td>{{bdcqk.qlxz}}</td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>     <!--//抵押情况-->
            <tr>   <!--//地役权情况-->
              <td colspan="2" style="padding: 0;">
                <table class="tabs0" cellpadding="10" cellspacing="0">
                  <tr>
                    <td class="td_w" rowspan="2">
                      <span class="title">地役权情况</span>
                    </td>
                    <td style="width: 124px;;">坐落</td>
                    <td>{{bdcqk.zl}}</td>
                  </tr>
                  <tr>
                    <td style="border-bottom: none;">不动产单元号</td>
                    <td style="border-bottom: none;">{{bdcqk.bdcdyh}}</td>
                  </tr>
                </table>
              </td>
            </tr>     <!--//地役权情况-->
            <tr>   <!--//签字-->
              <td colspan="2" style="padding: 0;">
                <table class="tabs0 tb_dashed" cellpadding="10" cellspacing="0">
                  <tr>
                    <td style="text-align: left;">本申请人对填写的上述内容，声明及提交的申请材料的真实性负责。如有不实，申请人愿承担法律责任。</td>
                  </tr>
                  <tr>
                    <td style="border-bottom: none;padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="border-right: 1px dashed #DFE6EC;">权力人(代理人)签章:</td>
                          <td>义务人(代理人)签章:</td>
                        </tr>
                        <tr>
                          <td style="border-right: 1px dashed #DFE6EC;">年 月 日</td>
                          <td>年 月 日</td>
                        </tr>
                        <tr>
                          <td style="border-bottom: none;border-right: 1px dashed #DFE6EC;">经办人签字:</td>
                          <td style="border-bottom: none;">年 月 日</td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>     <!--//签字-->
            <tr>   <!--//询问事项-->
              <td colspan="2" style="padding: 0;">
                <table class="tabs0 tb_dashed tb_text" cellpadding="10" cellspacing="0">
                  <tr>
                    <td class="td_w" rowspan="6" style="text-align: center;border-bottom: none;">
                      <span class="title">询问事项</span>
                    </td>
                    <td>
                      <span style="vertical-align: middle;">1.申请的不动产是否共有？</span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="_sfgyIs" id="Is"
                               style="font-size: 20px" class="check"><label
                        for="Is"
                        class="text">是</label>
                      </span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="_sfgyNo" id="No" style="font-size: 20px" class="check"><label
                        for="No"
                        class="text">否</label>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <span style="vertical-align: middle;">2.共有类型: 按份共有 &nbsp;&nbsp;&nbsp;份额情况 :</span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" id="gt" style="font-size: 20px"
                               class="check"><label
                        for="gt"
                        class="text">共同共有</label>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <span style="vertical-align: middle;">3.共有人是否申请分别持证？</span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="_sffbczIs" id="Iss" style="font-size: 20px"
                               class="check"><label for="Iss"
                                                    class="text">是</label>
                      </span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="_sffbczNo" id="Noo" style="font-size: 20px"
                               class="check"><label for="Noo"
                                                    class="text">否</label>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td>4.其他说明</td>
                  </tr>
                  <tr>
                    <td style="text-align: right;padding-right: 200px;">权利人（代理人）签章:</td>
                  </tr>
                  <tr>
                    <td style="border-bottom: none;text-align: right;padding-right: 100px;"> 年 月 日</td>
                  </tr>
                </table>
              </td>
            </tr>     <!--//询问事项-->
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import VueBarcode from '@xkeshi/vue-barcode';
  export default {
    props: {
      Propose: {
        type: Object,
        default() {
          return {
            qlrdlr: {
              qlrdlrdh: '',
              qlrdlrdz: '',
              qlrdlrmc: '',
              qlrdlrzjh: '',
              qlrdlrzjzl: '',
              qlrdlrzjzlmc: ''
            },
            qlrs: [
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
            ywrdlr: {
              ywrdlrdh: '',
              ywrdlrdz: '',
              ywrdlrmc: '',
              ywrdlrzjh: '',
              ywrdlrzjzl: '',
              ywrdlrzjzlmc: ''
            },
            ywrs: [
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
            ],
            bjbh: '201708200000001'
          };
        }
      },
      mortgage: {
        type: Object,
        default() {
          return {
            bdbzqse: '',
            bdcpgjg: '',
            dbfw: '',
            dkywbh: '',
            dkzh: '',
            dyfs: '',
            dyfsmc: '',
            dyhtqdrq: '',
            fcdymj: '',
            fcpgjg: '',
            tddymj: '',
            tdpgjg: '',
            yhdyywh: '',
            zjjzwdyfw: '',
            zqr: '',
            zwlxjssj: '',
            zwlxqssj: '',
            zwr: ''
          };
        }
      },
      bdcqk: {
        type: Object,
        default() {
          return {
            bdcdyh: '',
            bdcqzshx: '',
            bdcqzshy: '',
            fwmj: '',
            fwyt: '',
            fwytmc: '',
            qlxz: '',
            qlxzmc: '',
            ssqx: '',
            ssqxmc: '',
            tdmj: '',
            tdsyjssj: '',
            tdsyqssj: '',
            tdyt: '',
            tdytmc: '',
            zl: ''
          };
        }
      },
      xwsx: {
        type: Object,
        default() {
          return {
            afgyfeqk: '',
            gyfs: '',
            gyfsmc: '',
            qtsm: '',
            sffbcz: false,
            sffbczmc: '',
            sfgy: true,
            sfgymc: ''
          };
        }
      }
    },
    data() {
      return {
        checkdata: [  // 多选框
          [
            {
              id: 'a',
              value: '国有建设用地使用权',
              cheched: 'checkbox'
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
        ]  // 多选框
      };
    },
    computed: {
      _sfgyIs() {
        return this.xwsx.sfgy === true ? 'checked' : '';
      },
      _sfgyNo() {
        return this.xwsx.sfgy === true ? '' : 'chechbox';
      },
      _sffbczIs() {
        return this.xwsx.sffbcz === true ? 'chechbox' : '';
      },
      _sffbczNo() {
        return this.xwsx.sffbcz === true ? '' : 'chechbox';
      }
    },
    methods: {
      winprint() {
        this.$nextTick(() => {
          window.print();
          this.$router.push({path: '/index'});
          return true;
        });
      }
    },
    created() {
//      console.log(this.bdcqk);
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
    width: 880px
    margin: 0 auto
    overflow: hidden;
    position: relative;
    .header
      width: 880px
      height: 70px;
      margin: 0 20px
      text-align: center
      .barcode
        float: left
        width: 190px;
        height: 70px;
        padding: 0 15px
      .title
        display: inline-block
        line-height: 70px
        font-size: 20px
      .unit
        display: inline-block
        float: right
        padding: 4px 15px
        margin-top: 44px
        margin-right: 10px
    #Table
      width: 880px
      margin: 2px auto
      .selects
        width: 100%
        .tabs
          width: 100%
          border: 1px solid #DFE6EC
          border-right-color: #fff
          border-bottom-color: #fff
          .list li
            float: left
            padding: 6px 10px
            .ipts
              width: 16px;
              height: 14px;
              vertical-align: middle;
            .labs
              padding-left: 5px
              vertical-align: middle;
          td
            height: 14px
            padding: 6px;
            border-bottom: 1px solid #DFE6EC
            border-right: 1px solid #DFE6EC
            min-width: 70px
            line-height: 16px
            text-align: center
            vertical-align: middle
          .td_w
            width: 18px
            min-width: 70px
            border-bottom: none
            padding: 10px 4px
            text-align: center
            vertical-align: middle
            .title
              display: inline-block
              width: 15px
          .tabs0
            width: 100%
            tr td
              &:last-child
                border-right: none
            .tabs1
              width: 100%
              tr
                &:last-child td
                  border-bottom: none
          .tb_dashed td
            border-bottom: 1px dashed #DFE6EC
          .tb_text td
            text-align: left
            .check
              vertical-align: middle
            .text
              vertical-align: middle
              padding: 0 4px
</style>
<style media="print">
  @page {
    size: auto;  /* auto is the initial value */
    margin: 0mm 10mm; /* this affects the margin in the printer settings */
  }

  @media print {
    #JQprint {
      margin-top: 20px;
    }

    .header {
      width: 880px
    }

    #Table {
      width: 880px
    }
  }
</style>

<template>
  <div id="Register">
    <div id="JQprint" ref="Jqprint">
      <div class="header">
        <div class="barcode">
          <barcode :value="Propose.bjbh" :options="{ displayValue: true }"
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
                    <input type="checkbox" :id="item.id" style="font-size: 20px" class="ipts">
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
                          <td rowspan="3" style="border-bottom: none;">
                            <span>权利人</span>
                          </td>
                          <td>{{Propose.sqrqk.qlrs[0].qlrmc}}</td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td>{{Propose.sqrqk.qlrs[0].qlrzjzl}}</td>
                    <td>{{Propose.sqrqk.qlrs[0].qlrzjh}}</td>
                    <td>{{Propose.sqrqk.qlrs[0].qlrdz}}</td>
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
                          <td style="border-bottom: none;">
                            <span>代理人</span>
                          </td>
                          <td>{{Propose.sqrqk.qlrdlr.qlrdlrmc}}</td>
                        </tr>
                      </table>
                    </td>
                    <td>{{Propose.sqrqk.qlrdlr.qlrdlrzjzl}}</td>
                    <td>{{Propose.sqrqk.qlrdlr.qlrdlrzjh}}</td>
                    <td>{{Propose.sqrqk.qlrdlr.qlrdlrdz}}</td>
                  </tr>     <!--代理人-->
                  <tr>  <!--义务人-->
                    <td rowspan="3" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td rowspan="3" style="border-bottom: none;">
                            <span>义务人</span>
                          </td>
                          <td>{{Propose.sqrqk.ywrs[0].ywrmc}}</td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td>{{Propose.sqrqk.ywrs[0].ywrzjzl}}</td>
                    <td>{{Propose.sqrqk.ywrs[0].ywrzjh}}</td>
                    <td>{{Propose.sqrqk.ywrs[0].ywrdz}}</td>
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
                          <td style="border-bottom: none;">
                            <span>代理人</span>
                          </td>
                          <td>{{Propose.sqrqk.ywrdlr.ywrdlrmc}}</td>
                        </tr>
                      </table>
                    </td>
                    <td style="border-bottom: none;">{{Propose.sqrqk.ywrdlr.ywrdlrzjzl}}</td>
                    <td style="border-bottom: none;">{{Propose.sqrqk.ywrdlr.ywrdlrzjh}}</td>
                    <td style="border-bottom: none;">{{Propose.sqrqk.ywrdlr.ywrdlrdz}}</td>
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
                    <td style="width: 123px;">坐落</td>
                    <td>{{Propose.bdcqk.zl}}</td>
                  </tr>
                  <tr>
                    <td>不动产单元号</td>
                    <td>{{Propose.bdcqk.bdcdyh}}</td>
                  </tr>
                  <tr>
                    <td>原不动产权属证书</td>
                    <td>{{Propose.bdcqk.bdcqzshy}}</td>
                  </tr>
                  <tr>
                    <td colspan="2" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="width: 123px;">房屋用途</td>
                          <td>{{Propose.bdcqk.fwyt}}</td>
                          <td style="width: 123px;">房屋面积</td>
                          <td>{{Propose.bdcqk.fwmj}}</td>
                        </tr>
                        <tr>
                          <td>土地用途</td>
                          <td>{{Propose.bdcqk.tdyt}}</td>
                          <td>土地面积</td>
                          <td>{{Propose.bdcqk.tdmj}}</td>
                        </tr>
                        <tr>
                          <td>土地使用年限</td>
                          <td>{{Propose.bdcqk.tdsyqssj}}至<br>{{Propose.bdcqk.tdsyjssj}}</td>
                          <td>权力性质</td>
                          <td>{{Propose.bdcqk.qlxz}}</td>
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
                    <td style="width: 123px;">被担保债权数额<br>（最高债权数额）</td>
                    <td>{{Propose.dyqk.bdbzqse}}</td>
                    <td style="width: 123px;">债务履行期限<br>（债权确定期限）</td>
                    <td>{{Propose.dyqk.zwlxqssj}}至<br>{{Propose.dyqk.zwlxjssj}}</td>
                  </tr>
                  <tr>
                    <td colspan="4" style="border-bottom: none;padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="width: 123px;">担保范围</td>
                          <td>{{Propose.dyqk.dbfw}}</td>
                          <td rowspan="2" style="width: 80px;">抵押面积</td>
                          <td style="width: 100px;">房产</td>
                          <td>{{Propose.dyqk.fcdymj}}</td>
                        </tr>
                        <tr>
                          <td>在建建筑物抵押范围</td>
                          <td>{{Propose.dyqk.zjjzwdyfw}}</td>
                          <td>土地</td>
                          <td>{{Propose.dyqk.tddymj}}</td>
                        </tr>
                        <tr>
                          <td>土地使用年限</td>
                          <td>至<br></td>
                          <td>权力性质</td>
                          <td></td>
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
                    <td style="width: 123px;">坐落</td>
                    <td></td>
                  </tr>
                  <tr>
                    <td style="border-bottom: none;">不动产单元号</td>
                    <td style="border-bottom: none;"></td>
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
                    <td class="td_w" rowspan="6">
                      <span class="title">询问事项</span>
                    </td>
                    <td>
                      <span style="vertical-align: middle;">1.申请的不动产是否共有？</span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="CheckIs" id="Is"
                               style="font-size: 20px" class="check"><label
                        for="Is"
                        class="text">是</label>
                      </span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="CheckNo" id="No" style="font-size: 20px" class="check"><label
                        for="No"
                        class="text">否</label>
                      </span>
                    </td>
                  </tr>
                  <tr>
                    <td>
                      <span style="vertical-align: middle;">2.共有类型: 按份共有 &nbsp;&nbsp;&nbsp;份额情况 :</span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" :checked="Propose.xwsx.sfgymc" id="gt" style="font-size: 20px"
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
                        <input type="checkbox" id="Iss" style="font-size: 20px" class="check"><label for="Iss"
                                                                                                     class="text">是</label>
                      </span>
                      <span style="padding-left: 10px;">
                        <input type="checkbox" id="Noo" style="font-size: 20px" class="check"><label for="Noo"
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
    <div class="info">
      <ul>注：
        <li>1、按标准表格样式填写内容后，生成样表</li>
        <li>2、其中代理人在没有代理的情况下为空</li>
        <li>3、地役情况签章、询问事宜为空</li>
        <li>4、左上角条形码由系统生成，便于国土局线下办理接件</li>
      </ul>
    </div>
    <div class="print">
      <el-button :plain="true" type="success" style="padding: 8px 36px;" @click="_print">打印</el-button>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import VueBarcode from '@xkeshi/vue-barcode';
  import {getLodop} from '../../../static/LodopFuncs.js';

  let LODOP;
  export default {
    name: '',
    props: {
      Propose: {
        type: Object,
        default() {
          return {
            sqrqk: {
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
              ]
            },
            bdcqk: {
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
            },
            dyqk: {
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
            },
            xwsx: {
              sfgy: false
            },
            bjbh: '201708200000027'
          };
        }
      }
    },
    data() {
      return {
        proposer: '',
        checkdata: [  // 多选框
          [
            {
              id: 'a',
              value: '国有建设用地使用权'
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
      CheckIs() {
        return this.Propose.xwsx.sfgy === true ? 'checked' : false;
      },
      CheckNo() {
        return this.Propose.xwsx.sfgy === true ? false : 'checked';
      }
    },
    methods: {
      _print() {
        this.CreateOneFormPage();
//        LODOP.PRINT();
        LODOP.PREVIEW();
      },
      CreateOneFormPage() {
        LODOP = getLodop();
        LODOP.PRINT_INIT('订货单');
        LODOP.SET_PRINT_STYLE('FontSize', 18);
        LODOP.SET_PRINT_STYLE('Bold', 1);
        LODOP.ADD_PRINT_TEXT(50, 231, 260, 39, '打印页面部分内容');
        LODOP.ADD_PRINT_HTM(88, 200, 350, 600,
          document.getElementById('JQprint').innerHTML);
      }
    },
    created() {
      this.$forceUpdate();
      if (this.$store.state.proposer === '' || this.$store.state.proposer === null) {
        this.proposer = this.Propose;
      } else {
        this.proposer = this.$store.state.proposer;
      }
      console.log(this.Propose);
    },
    components: {
      'barcode': VueBarcode
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  #Register
    width: 100%
    position: relative
    #JQprint
      width: 100%
      .header
        width: 810px
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
          padding: 6px 15px
          margin-top: 42px
      #Table
        width: 810px
        margin: 0 20px
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
              width: 22px
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
    .info
      width: 100%
      margin-top: 20px
      padding-left: 20PX
      li
        padding: 4px 15px
    .print
      display: inline-block
      position: absolute
      right: 20px
      bottom: 72px
</style>

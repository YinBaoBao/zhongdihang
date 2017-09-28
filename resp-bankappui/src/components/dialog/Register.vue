<template>
  <div id="Register">
    <div id="JQprint" ref="Jqprint">
      <div class="header">
        <div class="barcode">
          <div class="bar_img">
            <img src="./barcode.png" alt="条形码">
          </div>
          <span>201708001000001</span>
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
                <ul class="list">
                  <li><input type="checkbox" id="a" style="font-size: 20px" class="ipts"><label for="a" class="labs">国有建设用地使用权</label>
                  </li>
                  <li><input type="checkbox" id="b" style="font-size: 20px" class="ipts"><label for="b" class="labs">集体土地所有权</label>
                  </li>
                  <li><input type="checkbox" id="c" style="font-size: 20px" class="ipts"><label for="c" class="labs">宅基地使用权</label>
                  </li>
                  <li><input type="checkbox" id="d" style="font-size: 20px" class="ipts"><label for="d" class="labs">集体建设使用权</label>
                  </li>
                  <li><input type="checkbox" id="e" style="font-size: 20px" class="ipts"><label for="e"
                                                                                                class="labs">林地使用权</label>
                  </li>
                  <li><input type="checkbox" id="f" style="font-size: 20px" class="ipts"><label for="f" class="labs">水域滩涂使用权</label>
                  </li>
                  <li><input type="checkbox" id="g" style="font-size: 20px" class="ipts"><label for="g"
                                                                                                class="labs">房屋所有权</label>
                  </li>
                  <li><input type="checkbox" id="h" style="font-size: 20px" class="ipts"><label for="h" class="labs">构筑物所有权</label>
                  </li>
                  <li><input type="checkbox" id="i" style="font-size: 20px" class="ipts"><label for="i"
                                                                                                class="labs">抵押权</label>
                  </li>
                  <li>
                    <input type="checkbox" id="j" style="font-size: 20px" class="ipts"><label for="j"
                                                                                              class="labs">地役权</label>
                  </li>
                  <li>
                    <input type="checkbox" id="k" style="font-size: 20px" class="ipts"><label for="k"
                                                                                              class="labs">其他</label>
                  </li>
                </ul>
              </td>
            </tr>
            <tr>
              <td>
                <ul class="list">
                  <li><input type="checkbox" id="aa" style="font-size: 20px" class="ipts"><label for="aa" class="labs">首次登记</label>
                  </li>
                  <li><input type="checkbox" id="bb" style="font-size: 20px" class="ipts"><label for="bb" class="labs">转移登记</label>
                  </li>
                  <li><input type="checkbox" id="cc" style="font-size: 20px" class="ipts"><label for="cc" class="labs">变更登记</label>
                  </li>
                  <li><input type="checkbox" id="dd" style="font-size: 20px" class="ipts"><label for="dd" class="labs">注销登记</label>
                  </li>
                  <li><input type="checkbox" id="ee" style="font-size: 20px" class="ipts"><label for="ee" class="labs">更正登记</label>
                  </li>
                  <li><input type="checkbox" id="ff" style="font-size: 20px" class="ipts"><label for="ff" class="labs">异议登记</label>
                  </li>
                  <li><input type="checkbox" id="gg" style="font-size: 20px" class="ipts"><label for="gg" class="labs">预告登记</label>
                  </li>
                  <li><input type="checkbox" id="hh" style="font-size: 20px" class="ipts"><label for="hh"
                                                                                                 class="labs">其他</label>
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
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
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
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
                  </tr>     <!--代理人-->
                  <tr>  <!--义务人-->
                    <td rowspan="3" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td rowspan="3" style="border-bottom: none;">
                            <span>义务人</span>
                          </td>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                        <tr>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
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
                    <td style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="border-bottom: none;">
                            <span>代理人</span>
                          </td>
                          <td></td>
                        </tr>
                      </table>
                    </td>
                    <td></td>
                    <td></td>
                    <td></td>
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
                    <td></td>
                  </tr>
                  <tr>
                    <td>不动产单元号</td>
                    <td></td>
                  </tr>
                  <tr>
                    <td>原不动产权属证书</td>
                    <td></td>
                  </tr>
                  <tr>
                    <td colspan="2" style="padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="width: 123px;">房屋用途</td>
                          <td></td>
                          <td style="width: 123px;">房屋面积</td>
                          <td></td>
                        </tr>
                        <tr>
                          <td>土地用途</td>
                          <td></td>
                          <td>土地面积</td>
                          <td></td>
                        </tr>
                        <tr>
                          <td>土地使用年限</td>
                          <td>至</td>
                          <td>权力性质</td>
                          <td></td>
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
                    <td style="width: 123px;">被担保债权数额<br>最高债权数额）</td>
                    <td></td>
                    <td style="width: 123px;">债务履行期限<br>（债权确定期限）</td>
                    <td>至</td>
                  </tr>
                  <tr>
                    <td colspan="4" style="border-bottom: none;padding: 0;">
                      <table class="tabs1" cellpadding="10" cellspacing="0">
                        <tr>
                          <td style="width: 123px;">担保范围</td>
                          <td></td>
                          <td rowspan="2" style="width: 80px;">抵押面积</td>
                          <td style="width: 100px;">房产</td>
                          <td></td>
                        </tr>
                        <tr>
                          <td>在建建筑物抵押范围</td>
                          <td></td>
                          <td>土地</td>
                          <td></td>
                        </tr>
                        <tr>
                          <td>土地使用年限</td>
                          <td>至</td>
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
                    <td>本申请人对填写的上述内容，声明及提交的申请材料的真实性负责。如有不实，申请人愿承担法律责任。</td>
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
                <table class="tabs0 tb_dashed" cellpadding="10" cellspacing="0">
                  <tr>
                    <td class="td_w" rowspan="6">
                      <span class="title">询问事项</span>
                    </td>
                    <td>1.申请的不动产是否共有？</td>
                  </tr>
                  <tr>
                    <td>2.共有类型： 按份共有 份额情况：</td>
                  </tr>
                  <tr>
                    <td>3.共有人是否申请分别持证？： 按份共有 份额情况：</td>
                  </tr>
                  <tr>
                    <td>4.其他说明</td>
                  </tr>
                  <tr>
                    <td>权利人（代理人）签章:</td>
                  </tr>
                  <tr>
                    <td style="border-bottom: none;"> 年 月 日</td>
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
  export default {
    name: '',
    props: {
      Propose: {
        type: Array,
        default: []
      }
    },
    data() {
      return {
        proposer: ''
      };
    },
    methods: {
      _print() {
        this.$emit('Jqprint');
      },
      tableData(data) {
        console.log(data);
        let settable = this.Resetting.data;
        console.log(settable);
        if (data === '' || data === null) {
          return false;
        }
        for (var i = 0; i < data.length; i++) {
          switch (data[i][0]) {
            case 'porwer':
              settable[i + 1] = data[i];
              continue;
            case 'daili':
              settable[i + 2] = data[i];
              continue;
            case 'yiwu':
              settable[i + 2] = data[i];
              continue;
          }
        }
      },
      proposercontent(event, type, id) {
        let Arr = [type, id];
        Arr.push(event.value);
        Arr.push(event.type);
        Arr.push(event.zhengjianhao);
        Arr.push(event.phone);
        return Arr;
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
      let pro = this.proposer;
      let setdata = [];
      for (var i = 0; i < pro.length; i++) {
        switch (pro[i].id) {
          case '权利人' :
            setdata.push(this.proposercontent(pro[i], 'porwer', '权利人'));
            continue;
          case '代理人' :
            setdata.push(this.proposercontent(pro[i], 'daili', '代理人'));
            continue;
          case '义务人' :
            setdata.push(this.proposercontent(pro[i], 'yiwu', '义务人'));
            continue;
        }
      }
      this.tableData(setdata, 1, 8);
    },
    components: {}
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
        margin: 0 20px
        text-align: center
        .barcode
          float: left
          height: 64px
          padding: 0 15px
          .bar_img
            width: 130px
            height: 40px
            overflow: hidden
            img
              display: block
              width: 100%
              height: 100%
          span
            display: block
            padding: 4px 2px
            font-size: 14px;
        .title
          display inline
          line-height: 40px
          font-size: 20px
        .unit
          display: inline-block
          float: right
          padding: 6px 15px
          margin-top: 36px
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

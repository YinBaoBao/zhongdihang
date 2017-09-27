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
              <td rowspan="2" style="width: 64px;border-right: 1px solid #ccc;padding: 0 4px 0 4px;"><span>申请登记事由</span>
              </td>
              <td style="border-bottom: 1px solid #ccc">
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
          </table>
        </div>
        <HotTable :root="root" :settings="Resetting"></HotTable>
        <!--<div class="state">-->
          <!--<ul>-->
            <!--<li class="is1">-->
              <!--<input type="checkbox" id="is" style="font-size: 20px" class="ipts"><label for="is" class="labs">是</label>-->
              <!--<input type="checkbox" id="no" style="font-size: 20px" class="ipts"><label for="no"-->
                                                                                         <!--class="labs">不是</label>-->
            <!--</li>-->
            <!--<li class="gt1">-->
              <!--<input type="checkbox" id="gt" style="font-size: 20px" class="ipts"><label for="gt"-->
                                                                                         <!--class="labs">共同共有</label>-->
            <!--</li>-->
            <!--<li class="is2">-->
              <!--<input type="checkbox" id="iss" style="font-size: 20px" class="ipts"><label for="iss"-->
                                                                                          <!--class="labs">是</label>-->
              <!--<input type="checkbox" id="nno" style="font-size: 20px" class="ipts"><label for="nno"-->
                                                                                          <!--class="labs">不是</label>-->
            <!--</li>-->
          <!--</ul>-->
        <!--</div>-->
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
  import HotTable from 'vue-handsontable-official';

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
        root: 'HotTable',
        Resetting: {
          data: [
            ['申请人情况', '姓名或名称', '', '证件类别', '证件号', '联系地址或电话'],
            ['', '权利人'], [], [],
            ['', '代理人'],
            ['', '义务人'], [], [],
            ['', '代理人'],
            ['不动产情况', '坐落'],
            ['', '不动产单元号'],
            ['', '原不动产权属证书号'],
            ['', '房屋用途', '', '房屋面积', ''],
            ['', '土地用途', '', '土地面积', ''],
            ['', '土地使用年限', '至', '权利性质', ''],
            ['抵押情况', '被担保债权数额（最高债权数额）', '', '债务履行期限（债权确定时间）', '至'],
            ['', '担保范围', '', '抵押面积', '房产', ''],
            ['', '在建建筑物抵押范围', '', '', '土地'],
            ['地役权情况', '需役权坐落'],
            ['', '需役地不动产单元号'],
            ['本申请人对填写的上述内容，声明及提交的申请材料的真实性负责。如有不实，申请人愿承担法律责任。'],
            ['权利人 （代理人) 签章 :', '', '', '义务人 （代理人) 签章 :', '', ''],
            ['', '年  月  日', '', '', '年  月  日', ''],
            ['', '经办人签字 :', '', '年  月  日', '', ''],
            ['询问事项', '1.申请的不动产是否共有？'],
            ['', '2.共有类型： 按份共有 份额情况：'],
            ['', '3.共有人是否申请分别持证？： 按份共有 份额情况：'],
            ['', '4.其他说明'],
            ['', '', '', '权利人（代理人）签章:', '', ''],
            ['', '', '', '', ' 年 月 日', '']
          ],
          width: 810,
          height: 900,
          startRows: 11, // 行列范围
          startCols: 12,
          minRows: 5,  // 最小行列
          minCols: 5,
          rowHeaders: false, // 行表头
//          rowHeaders: ['权利人1', '权利人2', '权利人3', '权利人4'],
          currentRowClassName: false, // 为选中行添加类名，可以更改样式
          currentColClassName: false, // 为选中列添加类名
          autoWrapRow: true, // 自动换行
          mergeCells: [   // 合并
            {row: 0, col: 0, rowspan: 9, colspan: 1},  // 指定合并，从（1,1）开始行3列3合并成一格
            {row: 0, col: 1, rowspan: 1, colspan: 2},
            {row: 1, col: 1, rowspan: 3, colspan: 1},
            {row: 5, col: 1, rowspan: 3, colspan: 1},
            {row: 9, col: 0, rowspan: 6, colspan: 1},
            {row: 9, col: 2, rowspan: 1, colspan: 4},
            {row: 10, col: 2, rowspan: 1, colspan: 4},
            {row: 11, col: 2, rowspan: 1, colspan: 4},
            {row: 15, col: 0, rowspan: 3, colspan: 1},
            {row: 16, col: 3, rowspan: 2, colspan: 1},
            {row: 18, col: 0, rowspan: 2, colspan: 1},
            {row: 18, col: 2, rowspan: 1, colspan: 4},
            {row: 19, col: 2, rowspan: 1, colspan: 4},
            {row: 20, col: 0, rowspan: 1, colspan: 6},
            {row: 21, col: 0, rowspan: 1, colspan: 3},
            {row: 21, col: 3, rowspan: 1, colspan: 3},
            {row: 22, col: 1, rowspan: 1, colspan: 2},
            {row: 22, col: 4, rowspan: 1, colspan: 2},
            {row: 23, col: 1, rowspan: 1, colspan: 2},
            {row: 23, col: 3, rowspan: 1, colspan: 2},
            {row: 24, col: 0, rowspan: 6, colspan: 1},
            {row: 24, col: 1, rowspan: 1, colspan: 5},
            {row: 25, col: 1, rowspan: 1, colspan: 5},
            {row: 26, col: 1, rowspan: 1, colspan: 5},
            {row: 27, col: 1, rowspan: 1, colspan: 5},
            {row: 28, col: 1, rowspan: 1, colspan: 2},
            {row: 28, col: 3, rowspan: 1, colspan: 3},
            {row: 29, col: 1, rowspan: 1, colspan: 3},
            {row: 29, col: 4, rowspan: 1, colspan: 2}
          ],
          columns: [
            {
              width: 20,
              height: 40,
              readOnly: true
            },
            {
              width: 20,
              readOnly: true
            }, {
              width: 20,
              type: 'text'
            }, {
              width: 20,
              type: 'text'
            }, {
              width: 20
            }, {
              width: 20
            }
          ],
//          cells: function(row, col, prop) {
//            var cellProperties = {};
//            if (row === 3 && col === 2) {
//              cellProperties.type = 'checkbox';
//            }
//            return cellProperties;
//          },
          customBorders: [], // 添加边框
          columnSorting: true, // 排序
          fillHandle: true,
          manualColumnMove: false, // 手动移动列
          manualRowMove: false,   // 手动移动行
          manualColumnResize: false, // 手工更改列距
          manualRowResize: false, // 手动更改行距
          stretchH: 'all' // 根据宽度横向扩展，last:只扩展最后一列，none：默认不扩展
        },  // 表格数据
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
    components: {
      HotTable
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
        margin: 0 20px
        text-align: center
        overflow: hidden;
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
        position: relative
        .selects
          width: 100%
          .tabs
            width: 100%
            border: 1px solid #ccc
            border-bottom: none
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
        #HotTable
          width: 100%
          /*overflow: hidden*/
          tr td
            vertical-align: middle
            text-align: center
          tr:nth-child(21) td
            border-bottom: 1px dashed #ccc
            text-align: left
          tr:nth-child(22) td
            border-bottom: 1px dashed #ccc
          tr:nth-child(23) td
            border-bottom: 1px dashed #ccc
          tr:nth-child(24) td
            text-align: left
          tr:nth-child(25) td
            text-align: left
          tr:nth-child(26) td
            border-bottom: 1px dashed #ccc
            text-align: left
          tr:nth-child(27) td
            border-bottom: 1px dashed #ccc
            text-align: left
          tr:nth-child(28) td
            border-bottom: 1px dashed #ccc
            text-align: left
          tr:nth-child(29) td
            border-left: none
            border-bottom: 1px dashed #ccc
            text-align: left
          tr:nth-child(30) td
            border-left: 1px dashed #ccc
            text-align: left
        .state
          position: absolute;
          left: 0
          bottom: 0
          z-index: 999999
          .is1
            width: 100px
            position: absolute
            left: 260px
            top: -142px
          .gt1
            width: 100px
            position: absolute
            left: 390px
            top: -120px
          .is2
            width: 100px
            position: absolute
            left: 424px
            top: -96px
          .ipts
            width: 16px;
            height: 14px;
            vertical-align: middle;
          .labs
            padding: 0 8px 0 4px
            vertical-align: middle;
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

<template>
  <div class="handsontable">
    <HotTable :root="root" :settings="hotSettings"></HotTable>
  </div>
</template>

<script type="text/ecmascript-6">
  import HotTable from 'vue-handsontable-official';

  export default {
    props: {
      Propose: {
        type: Array
      },
      index: {
        type: Number
      }
    },
    data() {
      return {
        root: 'test-hot',
        hotSettings: {
          data: [
            ['权利人1', '中国建设银行股份有限公司苏州分行', '营业执照', '320500000010314', '苏州市姑苏区阊胥路88号', '0512-68268179']
          ],
          width: 800,
          height: 240,
          startRows: 11, // 行列范围
          startCols: 12,
          minRows: 5,  // 最小行列
          minCols: 5,
          rowHeaders: true, // 行表头
//          rowHeaders: ['权利人1', '权利人2', '权利人3', '权利人4'],
          colHeaders: ['', '姓名/名称', '证件类型', '证件号', '联系地址', '电话'], // 自定义列表头or 布尔值
          autoWrapRow: true, // 自动换行
          fillHandle: true, // 选中拖拽复制 possible values: true, false, "horizontal", "vertical"
          fixedRowsTop: 0, // 固定上边列数
          mergeCells: [   // 合并
//            {row: 1, col: 1, rowspan: 3, colspan: 3},  // 指定合并，从（1,1）开始行3列3合并成一格
//            {row: 3, col: 4, rowspan: 2, colspan: 2}
          ],
          columns: [
            {
              width: 10
            }, {
              width: 20,
              height: 40
            }, {
              width: 20
            }, {
              width: 20
            }, {
              width: 20
            }
          ],
          manualColumnMove: true, // 手动移动列
          manualRowMove: true,   // 手动移动行
          manualColumnResize: true, // 手工更改列距
          manualRowResize: true, // 手动更改行距
          customBorders: [], // 添加边框
          columnSorting: true, // 排序
          stretchH: 'all' // 根据宽度横向扩展，last:只扩展最后一列，none：默认不扩展
        },
        proposer: '',
        tableData: [],
        tableTitle: [],
        Arr: []
      };
    },
    methods: {
      keep() {
//        console.log(this.hotSettings.data);
        let index = this.index + 1;
        this.proposer.splice(index, 0, this.hotset());
        this.$store.commit('proposer', this.proposer);
      },
      hotset() { // 过滤表格
        let hotset = this.hotSettings.data;
        let json = {};
        let arr = ['id', 'value', 'address', 'type', 'zhengjianhao', 'phone'];
        for (var i = 0; i < hotset.length; i++) {
          if (hotset[i][1] !== null) {
            for (var k = 0; k < hotset[i].length; k++) {
              json[arr[k]] = hotset[i][k];
            }
          }
        }
        return json;
      }
    },
    created() {
      this.$forceUpdate();
      if (this.$store.state.proposer === '' || this.$store.state.proposer === null) {
        this.proposer = this.Propose;
      } else {
        this.proposer = this.$store.state.proposer;
      }
      let index = this.index;
      for (var k in this.proposer[index]) {
        this.tableData.push(this.proposer[index][k]);
      }
      this.hotSettings.data[0] = this.tableData;
    },
    watch: {},
    components: {
      HotTable
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .handsontable
    width: 100%
    #H_Table
      width: 100%
    .handsontable .currentRow
      background-color: #E7E8EF
    .handsontable .currentCol
      background-color: #F9F9FB

</style>

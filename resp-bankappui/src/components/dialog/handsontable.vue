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
        type: Object
      },
      Title: {
        type: String
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
            ['', '', '', '', '', '']
          ],
          width: 800,
          height: 240,
          startRows: 11, // 行列范围
          startCols: 12,
          minRows: 6,  // 最小行列
          minCols: 6,
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
        let index = this.index + 1;
        switch (this.Title) {
          case '权利人':
            this.Propose.qlrs.splice(index, 0, this.hotset());
            break;
          case '代理人':
            this.Propose.qlrdlr = this.hotset();
            break;
          case '义务人':
            this.Propose.ywrdlr = this.hotset();
            break;
        }
      },
      hotset() { // 过滤表格
        let hotset = this.hotSettings.data;
        let json = {};
        let arr = ['', 'qlrmc', 'qlrzjzlmc', 'qlrzjh', 'qlrdz', 'qlrdh'];
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
      this.hotSettings.data[0][0] = this.Title;
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

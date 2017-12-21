<template>
  <div class="statiic">
    <ul class="select">
      <li class="list-item">
        <span class="title">查询功能</span>
        <el-select v-model="gnvalue" placeholder="请选择">
          <el-option
            v-for="item in gnoptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </li>
      <li class="list-item">
        <span class="title">查询条件</span>
        <el-select v-model="tjvalue" placeholder="请选择">
          <el-option
            v-for="item in tjoptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </li>
      <li class="list-item">
        <span class="title">区域</span>
        <el-select v-model="areavalue" placeholder="请选择">
          <el-option
            v-for="item in areaoptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </li>
      <li class="list-item">
        <span class="title">时间维度</span>
        <el-select v-model="wdvalue" placeholder="请选择">
          <el-option
            v-for="item in wdoptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </li>
      <el-button type="primary">查询</el-button>
    </ul>
    <div class="chart">
      <div class="content" ref="chart"></div>
    </div>
    <div class="st-content">
      <div class="title">
        <span class="text">统计表</span>
      </div>
      <table class="st-table">
        <tr>
          <th>{{TjData[0].title}}</th>
          <th>{{TjData[0].date1}}</th>
          <th>{{TjData[0].date2}}</th>
          <th>{{TjData[0].date3}}</th>
          <th>{{TjData[0].date4}}</th>
          <th>{{TjData[0].date5}}</th>
          <th>{{TjData[0].date6}}</th>
        </tr>
        <tr>
          <td>{{TjData[1].title}}</td>
          <td>{{TjData[1].num1}}</td>
          <td>{{TjData[1].num2}}</td>
          <td>{{TjData[1].num3}}</td>
          <td>{{TjData[1].num4}}</td>
          <td>{{TjData[1].num5}}</td>
          <td>{{TjData[1].num6}}</td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script type="text/ecmascript-6">
  import echarts from 'echarts';
  export default {
    data() {
      return {
        gnoptions: [
          {
            label: '',
            value: ''
          }
        ],
        gnvalue: '',
        tjoptions: [
          {
            label: '',
            value: ''
          }
        ],
        tjvalue: '',
        areaoptions: [
          {
            label: '',
            value: ''
          }
        ],
        areavalue: '',
        wdoptions: [
          {
            label: '',
            value: ''
          }
        ],
        wdvalue: '',
        TjData: [
          {
            title: '月份',
            date1: '2017-05',
            date2: '2017-06',
            date3: '2017-07',
            date4: '2017-08',
            date5: '2017-09',
            date6: '2017-10'
          },
          {
            title: '查询次数',
            num1: '120',
            num2: '160',
            num3: '130',
            num4: '230',
            num5: '150',
            num6: '170'
          }
        ],
        chartoption: {
          title: {
            text: '统计图',
            subtext: '查询/次数'
          },
          tooltip: {
            trigger: 'axis'
          },
          itemStyle: {
            normal: {
              color: '#4595ff'
            }
          },
          legend: {
            data: ['', '']
          },
          toolbox: {
            show: true,
            feature: {
              dataZoom: {
                yAxisIndex: 'none'
              },
              dataView: {readOnly: false},
              magicType: {type: ['line', 'bar']},
              restore: {},
              saveAsImage: {}
            }
          },
          xAxis: {
            type: 'category',
            boundaryGap: false,
            data: ['2017-5', '2017-6', '2017-7', '2017-8', '2017-9', '2017-10']
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              formatter: '{value}'
            }
          },
          series: [
            {
              name: '',
              type: 'line',
              data: [120, 160, 130, 230, 150, 170],
              markPoint: {
                data: [
                  {type: 'max', name: '最大值'},
                  {type: 'min', name: '最小值'}
                ]
              },
              markLine: {
                data: [
                  {type: 'average', name: '平均值'}
                ]
              }
            }
          ]
        }
      };
    },
    created() {
    },
    mounted() {
      const myChart = echarts.init(this.$refs.chart);
      myChart.setOption(this.chartoption);
    }
  };
</script>
<style lang="stylus" rel="stylesheet/stylus">
  .statiic
    height: calc(100% - 80px)
    padding: 0 30px 10px 30px
    overflow: auto
    .select
      width: 100%
      padding-top: 24px
      .list-item
        display: inline-block
        margin-right: 10px
        .title
          display: inline-block
          padding-right: 6px
          vertical-align: middle
        .el-select
          width: inherit
          max-width: 160px
          vertical-align: middle
          .el-input__inner
            width: inherit
            height: 36px
      .el-button
        font-size: 14px
        padding: 10px 24px
    .chart
      width: 100%
      background: #fff
      border-radius: 5px
      margin-top: 20px
      box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
      .content
        width: 100%
        height: 400px
    .st-content
      width: 100%
      margin-top: 20px
      background: #fff
      box-shadow: 0 0 10px rgba(69, 149, 255, 0.4)
      border-radius: 5px
      .title
        height: 40px
        line-height: 40px
        .text
          padding-left: 15px
          font-size: 16px
          font-weight: 600
          color: #101010
      .st-table
        width: 100%
      .st-table td, .st-table th
        padding: 10px 8px
        text-align: center
        font-family: '微软雅黑'
        font-size: 14px
        color: #656565
      .st-table th
        background: #F4F8F9
</style>

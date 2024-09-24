<template>
  <div>
    <div class="block">
      <span class="DataSelect" style="margin-right:10px">日期选择</span>
      <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
        start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions" @change="handleDateChange">
      </el-date-picker>
    </div>
    <div id="main" ref="main"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment';
import { getOutputPercapitavalueData } from '@/api/safety/chartAPI'

export default {
  data() {
    return {
      loading: false,
      data: [],
      timeData: {
        startTime: new Date(),
        endTime: new Date(),
      },
      selectedDate: [],
      pickerOptions: [],
      option: {},
      myChart: {},
      chartData: [], // 存放格式化后的数据
      parsedData: {},
      routerData: {}
    }
  },
  computed: {},
  mounted() {
    this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '', showTargetValue: false };
    this.defaultMonth()
    this.myChart = echarts.init(document.getElementById('main'))
    this.initData()

  },
  methods: {
    async initData() {
      this.timeData.startTime = this.selectedDate[0],
        this.timeData.endTime = this.selectedDate[1]
      try {
        this.loading = true
        const res = await getOutputPercapitavalueData(this.timeData);
        this.data = res
        console.log(this.data)
        console.log('++++++++++++++++')
        this.loading = false
        this.formatData()
        this.updateChart()
      } catch (error) {
        console.log('初始化失败')
        this.loading = false
      }
    },
    handleDateChange(value) {
      if (value && value[1]) {
        let endDate = new Date(value[1]);
        endDate.setMonth(endDate.getMonth() + 1);
        endDate.setDate(0);
        this.selectedDate[1] = endDate;
      }
      this.initData()
    },
    updateChart() {
      var app = {};
      const posList = [
        'left',
        'right',
        'top',
        'bottom',
        'inside',
        'insideTop',
        'insideLeft',
        'insideRight',
        'insideBottom',
        'insideTopLeft',
        'insideTopRight',
        'insideBottomLeft',
        'insideBottomRight'
      ];
      app.configParameters = {
        rotate: {
          min: -90,
          max: 90
        },
        align: {
          options: {
            left: 'left',
            center: 'center',
            right: 'right'
          }
        },
        verticalAlign: {
          options: {
            top: 'top',
            middle: 'middle',
            bottom: 'bottom'
          }
        },
        position: {
          options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
          }, {})
        },
        distance: {
          min: 0,
          max: 100
        }
      };
      app.config = {
        rotate: 0,
        align: 'center',
        verticalAlign: 'middle',
        position: 'top',
        distance: 15,
        onChange: function () {
          const labelOption = {
            rotate: app.config.rotate,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            position: app.config.position,
            distance: app.config.distance
          };
          this.myChart.setOption({
            series: [
              {
                label: labelOption
              },
              {
                label: labelOption
              },
              {
                label: labelOption
              },
              {
                label: labelOption
              }
            ]
          });
        }
      };
      const labelOption = {
        show: true,
        position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        rotate: app.config.rotate,
        formatter: '{c}',
        fontSize: 16,
        rich: {
          name: {}
        }
      };
      this.option = {
        title: {
          text: '设备故障类别次数分布图',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['机械', '气动', '液压', '电器']
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            mark: { show: true, },
            dataView: { show: true, readOnly: false, title: '数据视图' },
            magicType: { show: true, type: ['bar', 'line', 'stack'], title: { bar: '切换为柱状图', line: '切换为折线图', stack: '切换为堆叠图' } },
            restore: { show: true, title: '还原' },
            saveAsImage: { show: true, title: '保存为图片' }
          }
        },
        xAxis: [
          {
            // type: 'category',
            axisTick: { show: false },
            data: this.chartData.map(data => moment(data.month).format('YY-MM'))
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [
          {
            name: '机械',
            type: 'bar',
            label: labelOption,
            emphasis: {
              focus: 'series'
            },
            data: this.chartData.map(data => data.mechanical),
          },
          {
            name: '气动',
            type: 'bar',
            label: labelOption,
            emphasis: {
              focus: 'series'
            },
            data: this.chartData.map(data => data.pneumatic),
          },
          {
            name: '液压',
            type: 'bar',
            label: labelOption,
            emphasis: {
              focus: 'series'
            },
            data: this.chartData.map(data => data.hydraulic),
          },
          {
            name: '电器',
            type: 'bar',
            label: labelOption,
            emphasis: {
              focus: 'series'
            },
            data: this.chartData.map(data => data.electrical),
          }]
      };

      this.option && this.myChart.setOption(this.option);
      this.myChart.on('magictypechanged', (params) => {
        var magicType = params.currentType;
        if (magicType == 'line') {
          this.option.xAxis[0].boundaryGap = true;
          for (let i = 0; i < this.option.series.length; i++) {
            this.option.series[i].type = magicType;
          }
          this.myChart.setOption(this.option);
        }
      });
    },
    defaultMonth() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear, 0, 1);
      const endDate = new Date(currentYear, currentMonth, 0);
      this.selectedDate = [startDate, endDate];
    },

    formatData() {
      this.chartData = this.data.rows.map(rows => {
        const month = rows.Year_And_Month ? rows.Year_And_Month : '';
        const resultData = rows.resultData ? rows.resultData.split(',') : '';
        console.log('----------')
        if (resultData != '') {
          return {
            month,
            mechanical: parseInt(resultData[0].split(':')[1]),
            pneumatic: parseInt(resultData[1].split(':')[1]),
            hydraulic: parseInt(resultData[2].split(':')[1]),
            electrical: parseInt(resultData[3].split(':')[1]),
          };
        } else {
          return {
            month,
            mechanical: 0,
            pneumatic: 0,
            hydraulic: 0,
            electrical: 0,
          };
        }

      });
      console.log(this.chartData)
      console.log('+++++++++++++')
    }

  },



}





</script>

<style lang="scss" scoped>
#main {
  width: 1000px;
  height: 600px;
  margin: 40px auto;
}

.block {
  margin-top: 50px;
  text-align: center;
}
</style>

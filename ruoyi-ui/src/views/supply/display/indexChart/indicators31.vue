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
import moment from 'moment'
import { getControlledPurchaseAmountRatioData } from '@/api/supply/chartData'
import { getTargetData } from '@/api/financial/target'
import { getNameTarget } from '@/api/financial/target'
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
      ifTargetEmpty: '',
      myChart: {},
      routerData: [
        { id: '31', showTarget: 'supply', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'controlledPurchaseAmountRatio', icon: 'el-icon-s-data', title: '集团管控物资采购金额占比', content: '集团管控物资采购金额占比', path: '/supply/indicators31' },
      ],
    }
  },
  computed: {},
  mounted() {
    this.defaultMonth()
    this.myChart = echarts.init(document.getElementById('main'))
    this.initData()
  },
  methods: {
    async initData() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear - 3, currentMonth - 1, 1);
      let target = {
        date: startDate,
        deptName: 'supply',
      }

      const resTarget = await getTargetData(target)
      //目标值赋予及上下限预警
      this.routerData.forEach(item => {
        resTarget.rows.forEach(row => {
          if (item.sum === row.indicatorName) {
            item.targetValue = row.targetValue;
            item.targetValueDate = row.natureYear;
            // if (allTargetData[item.sum] < row.targetLowerLimit || allTargetData[item.sum] > row.targetUpperLimit) {
            //   item.showWarning = true;
            // }
          }
        });
      });
      this.timeData.startTime = this.selectedDate[0],
        this.timeData.endTime = this.selectedDate[1]
      try {
        this.loading = true
        const res = await getControlledPurchaseAmountRatioData(this.timeData);
        this.data = res.rows
        const yAxisDataLength = this.data.length;
        this.targetValueArray = Array(yAxisDataLength).fill(this.routerData[0].targetValue);

        //目标值
        let newTarget = {
          name: 'controlledPurchaseAmountRatio',
          startDate: this.selectedDate[0],
          endDate: this.selectedDate[1]
        }
        const tmp = await getNameTarget(newTarget)
        let nowTarget = tmp.rows
        this.ifTargetEmpty = tmp.rows.length
        console.log(this.ifTargetEmpty)
        if (this.ifTargetEmpty) {
          let allTarget = []; // 初始化目标数组
          nowTarget.forEach(item => {
            let natureYear = moment(item.natureYear).format('YYYY')
            let targetValue = item.targetValue; // 目标值可能是数字或null
            allTarget.push({ natureYear, targetValue });
          })
          this.data.forEach(item => {
            const year = moment(item.yearAndMonth).format('YYYY')
            allTarget.forEach(row => {
              if (year === row.natureYear) {
                item.targetValue = row.targetValue
              }
            })
          });
        }

        console.log(this.data)

        this.updateChart()
        this.loading = false
      } catch (error) {
        this.loading = false
        console.log(error)
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
        formatter: '{c} ',
        fontSize: 16,
        rich: {
          name: {}
        }
      };
      // 根据条件决定是否添加目标值系列
      let series = [
        {
          name: '采购金额',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: this.data.map(item => item.controlledMaterialPurchases),
        },
        {
          name: '总金额',
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: this.data.map(item => item.totalPurchaseAmount),
        },
        {
          name: '占比',
          type: 'line',
          label: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c} %',
            fontSize: 16,
            rich: {
              name: {}
            }
          },
          emphasis: {
            focus: 'series'
          },

          yAxisIndex: 1,
          tooltip: {
            valueFormatter: function (value) {
              return value + '%';
            }
          },
          data: this.data.map(item => item.controlledPurchaseAmountRatio),
        },
        {
          name: '月度求和比例',
          type: 'bar',
          label: {
            show: true,
            position: app.config.position,
            distance: app.config.distance,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            rotate: app.config.rotate,
            formatter: '{c} %',
            fontSize: 16,
            rich: {
              name: {}
            }
          },
          emphasis: {
            focus: 'series'
          },
          yAxisIndex: 1,
          tooltip: {
            valueFormatter: function (value) {
              return value + '%';
            }
          },
          data: this.data.map(item => item.yearAmountRatio),
        }
      ];

      if (this.ifTargetEmpty) {
        series.push({
          name: '目标值',
          type: 'line',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: this.data.map(item => item.targetValue),
        });
      }
      this.option = {
        title: {
          text: '集团管控物资采购金额占比',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['采购金额', '总金额', '占比', '月度求和比例', this.ifTargetEmpty ? '目标值' : null].filter(item => item !== null),
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            mark: { show: true, },
            dataView: { show: true, readOnly: false, title: '数据视图' },
            magicType: { show: true, type: ['bar'], title: { bar: '切换为柱状图', line: '切换为折线图' } },
            restore: { show: true, title: '还原' },
            saveAsImage: { show: true, title: '保存为图片' }
          }
        },
        xAxis: [
          {
            // type: 'category',
            axisTick: { show: false },
            data: this.data.map(item => moment(item.yearAndMonth).format('YY-MM')),
          }
        ],
        yAxis: [
          {
            type: 'value'
          },
          {
            type: 'value',
            name: '占比',
            // interval: 5,
            splitLine: { show: false },
            axisLabel: {
              formatter: '{value} %'
            }
          }

        ],
        series: series
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
      });//切换为折线图时，设置boundaryGap为true
    },
    defaultMonth() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear, 0, 1);
      const endDate = new Date(currentYear, currentMonth, 0);
      this.selectedDate = [startDate, endDate];
    },
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

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
import { getPartyBuildingRankData } from '@/api/partybuilding/data'
import { getTargetData } from '@/api/financial/target'
import { getNameTarget } from '@/api/financial/target'
export default {
  name: 'Indicators50',
  data() {
    return {
      loading: false,
      data: [],
      rankData: [],
      sortedScores: [],
      timeData: {
        startTime: new Date(),
        endTime: new Date(),
      },
      selectedDate: [],
      pickerOptions: [],
      option: {},
      myChart: {},
      ifTargetEmpty: '',
      targetValueArray: [],
      routerData:
        [
          { id: '50', showTarget: 'partybuilding', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'partyBuildingRank' },
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
      // let target = {
      //   date: new Date(),
      //   deptName: 'partybuilding',
      // }
      // const resTarget = await getTargetData(target)
      // 是否目标值赋予
      // this.routerData.forEach(item => {
      //   resTarget.rows.forEach(row => {
      //     if (item.sum === row.indicatorName) {
      //       item.targetValue = row.targetValue;
      //       item.targetValueDate = row.natureYear;
      //     }
      //   });
      // });
      // console.log(this.routerData)
      // console.log('++++++++++++')
      this.timeData.startTime = this.selectedDate[0],
        this.timeData.endTime = this.selectedDate[1]
      try {
        this.loading = true
        const res = await getPartyBuildingRankData(this.timeData);
        this.data = res.rows
        const yAxisDataLength = this.data.length;
        this.targetValueArray = Array(yAxisDataLength).fill(this.routerData[0].targetValue);

        //目标值
        let newTarget = {
          name: 'partyBuildingRank',
          startDate: this.selectedDate[0],
          endDate: this.selectedDate[1]
        }
        const tmp = await getNameTarget(newTarget)
        let nowTarget = tmp.rows
        this.ifTargetEmpty = tmp.rows.length
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



        this.loading = false
        this.updateChart()
      } catch (error) {
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
      // 根据条件决定是否添加目标值系列
      let series = [{
        name: '分数',
        type: 'bar',
        label: labelOption,
        emphasis: {
          focus: 'series'
        },
        data: this.data.map(item => item.score),
      },
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
          text: '党建月度考核分数',
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: ['分数', this.ifTargetEmpty ? '目标值' : null].filter(item => item !== null),
        },
        toolbox: {
          show: true,
          orient: 'vertical',
          left: 'right',
          top: 'center',
          feature: {
            mark: { show: true, },
            dataView: { show: true, readOnly: false, title: '数据视图' },
            magicType: { show: true, type: ['bar', 'line'], title: { bar: '切换为柱状图', line: '切换为折线图' } },
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
        ],
        series: series
      };

      this.option && this.myChart.setOption(this.option);
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

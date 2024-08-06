<template>
  <div id="main" ref="main"></div>
</template>

<script>
import * as echarts from 'echarts'

export default {
  props: {
    title: { type: String, default: '主营业务收入' },
    dataName: { type: String, default: '金额' },
    xAxisData: { type: Array, default: () => [] },
    yAxisData: { type: Array, default: () => [] },
    legendData: { type: String, default: null },
    targetValue: { type: Number, default: 0 },
    targetValueDate: { type: String, default: null },
  },
  data() {
    return {
      loading: false,
      data: [],
      option: {},
      myChart: {},
      targetValueArray: [],
    }
  },
  watch: {
    xAxisData: {
      handler() {
        this.updateChart()
      },
      deep: true
    },
    yAxisData: {
      handler() {
        this.updateChart()
      },
      deep: true
    },
  },
  mounted() {
    const yAxisDataLength = this.yAxisData.length;
    this.targetValueArray = Array(yAxisDataLength).fill(this.targetValue);
    this.myChart = echarts.init(document.getElementById('main'))
    this.updateChart()
  },
  methods: {
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
          text: this.title
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        legend: {
          data: [this.dataName, '目标值'],
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
            // restore: { show: true, title: '还原' },
            saveAsImage: { show: true, title: '保存为图片' }
          }
        },
        xAxis: [
          {
            // type: 'category',
            axisTick: { show: false },
            data: this.xAxisData,
          }
        ],
        yAxis: [
          {
            type: 'value'
          }
        ],
        series: [{
          name: this.dataName,
          type: 'bar',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: this.yAxisData,
        },
        {
          name: '目标值',
          type: 'line',
          label: labelOption,
          emphasis: {
            focus: 'series'
          },
          data: this.targetValueArray,
        }
        ]
      };

      this.option && this.myChart.setOption(this.option);

      //折线图切换
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
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
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

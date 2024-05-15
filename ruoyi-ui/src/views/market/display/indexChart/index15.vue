<template>
    
    <div>
        <div class="block">
            
    <!-- 添加标签页标题 -->
    <span class="DataSelect" style="margin-right:10px">填写数字</span>
    <!-- 添加输入框 -->
    <el-input v-model.number="numberInput" placeholder="请输入订单总台数"></el-input>
  
            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange">
            </el-date-picker>
            <p>{{ this.timeData.startTime }},{{ this.timeData.endTime }}</p>
        </div>
        <div id="main" ref="main"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';     
import moment from 'moment'
import { getIndex15 } from '@/api/market/index'

export default {
    data() {
        return {
            result:null,
            provinces: [],
            months: [],
            quantities: [],
             quantitiesForMonth3: [], // 针对月份3的数量数组
            numberInput:null,
            loading: false,
            financialId: '',
            data: [],
            // timeData: { startTime: null, endTime: null },
            timeData: {
                startTime: new Date('2024-01-02'),
                endTime: new Date('2024-09-01'),
                numberInput:null
    
            },
            selectedDate: [new Date('2024-01-02'),new Date('2024-10-01')],
            pickerOptions: [],
            option: {},
            myChart: {}
        }
    },
    mounted() {
        this.financialId = this.$route.query.id;
        this.myChart = echarts.init(document.getElementById('main'));
        this.initData();
        this.getCurrentMonth();
        this.getCurrentYear();
        const currentDate = new Date();
        //
//   const currentYear = currentDate.getFullYear();
//   const firstMonthOfYear = new Date(currentYear, 0, 1); // 设置为每一年的第一个月的第一天
//   const currentMonth = currentDate.getMonth() + 1; // 月份从0开始，所以要加1
//   const lastDayOfMonth = new Date(currentYear, currentMonth, 0); // 设置为每一年的当前月份的最后一天

  this.timeData.startTime = firstMonthOfYear;
  this.timeData.endTime = lastDayOfMonth;
    },
    methods: {

    splitData() {
        const integerMap = this.result.integerMap;

        // 拆分地名
        this.provinces = Object.keys(integerMap);

        // 拆分月份和数量
    for (let province in integerMap) {
            if (integerMap.hasOwnProperty(province)) {
                const monthsForProvince = Object.keys(integerMap[province]);
                this.months.push(...monthsForProvince);
                monthsForProvince.forEach(month => {
                    if (month === '3') {
                        this.quantitiesForMonth3.push(integerMap[province][month]);
                    } else {
                        this.quantities.push(integerMap[province][month]);
                    }
                });
            }
        }

        // 数组去重
        this.months = Array.from(new Set(this.months));
    },

        async initData() {
            this.timeData.startTime = this.selectedDate[0],
                this.timeData.endTime = this.selectedDate[1]
                 this.timeData.numberInput=this.numberInput
            try {
                this.loading = true
                console.log(this.timeData)
                this.result = await getIndex15(this.timeData);
                console.log("======>");
                console.log(this.result)
                this.splitData();
                this.data = this.result.integerMap
                this.loading = false
                this.updateChart()
            } catch (error) {
                this.loading = false
            }
        },
        handleDateChange(val) {
            console.log(val, 'val')
            this.initData()
        },
      updateChart() {


var app = {};

var chartDom = document.getElementById('main');
var myChart = echarts.init(chartDom);
var option;

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
  rotate: 90,
  align: 'left',
  verticalAlign: 'middle',
  position: 'insideBottom',
  distance: 15,
  onChange: function () {
    const labelOption = {
      rotate: app.config.rotate,
      align: app.config.align,
      verticalAlign: app.config.verticalAlign,
      position: app.config.position,
      distance: app.config.distance
    };
    myChart.setOption({
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
  formatter: '{c}  {name|{a}}',
  fontSize: 16,
  rich: {
    name: {}
  }
};
option = {
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {
    data: this.provinces
  },
  toolbox: {
    show: true,
    orient: 'vertical',
    left: 'right',
    top: 'center',
    feature: {
      mark: { show: true },
      dataView: { show: true, readOnly: false },
      magicType: { show: true, type: ['line', 'bar', 'stack'] },
      restore: { show: true },
      saveAsImage: { show: true }
    }
  },
  xAxis: [
    {
      type: 'category',
      axisTick: { show: false },
      data: this.months
    }
  ],
  yAxis: [
    {
      type: 'value'
    }
  ],

  series: this.provinces.map((province, index) => ({
        name: province,
        type: 'bar',
        label: labelOption,
        emphasis: {
        focus: 'series'
      },
      data: [this.quantities[index]] // 此处假设每个省份只有一个数量
      }))
};

option && myChart.setOption(option);

},


        // updateChart() {
        //     this.option = {
        //         title: {
        //             text: '主营业务收入'
        //         },
        //         tooltip: {},
        //         xAxis: {
        //             data: this.data.map(item => moment(item.Year_And_Month).format('YY-MM')),
        //         },
        //         yAxis: {},
        //         series: [
        //             {
        //                 name: '金额',
        //                 type: 'line',
        //                 data: this.data.map(item => item.MainRevenue),
        //                 label: {
        //                     show: true,
        //                     position: 'top'
        //                 }
        //             },
        //         ],
        //     };
        //     this.option && this.myChart.setOption(this.option)
        // },
        getCurrentMonth() {
            const currentDate = new Date();
            const currentMonth = currentDate.getMonth() + 1;
            this.timeData.currentMonth = currentMonth;
        },
        getCurrentYear() {
            const currentDate = new Date();
            const currentYear = currentDate.getFullYear();
            this.timeData.currentYear = currentYear;
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

<template>
    
    <div>
        <div class="block">
            
    <!-- 添加标签页标题 -->
    <span class="DataSelect" style="margin-right:10px"></span>
    <!-- 添加输入框 -->
    <!-- <el-input v-model.number="numberInput" placeholder="请输入订单总台数"></el-input> -->
  
  

            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <!-- <el-date-picker v-model="selectedDate" type="daterange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange" >
            </el-date-picker> -->
              <el-date-picker v-model="selectedDate" type="daterange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange" >
            </el-date-picker>
<!-- 
            <el-input v-model.number="numberInput" placeholder="请输入订单总台数" style="width: 200px;"></el-input>
            <el-button type="primary" @click="handleConfirm">确定</el-button> -->
            <!-- <p>{{ this.timeData.startTime }},{{ this.timeData.endTime }}</p> -->
        </div>
        <div id="main" ref="main"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';     
import moment from 'moment'
import { getIndex12 } from '@/api/market/index'

export default {
    data() {
        return {
          transposedSeriesData:[],
            branchmonthIdx:[],
            seriesData :[],
            branches :[],
         months : [],
        numbers : [],
            result:[],
            provinces: [],
          
            quantities: [],
             quantitiesForMonth3: [], // 针对月份3的数量数组
            numberInput:null,
            loading: false,
            financialId: '',
            data: [],
            // timeData: { startTime: null, endTime: null },
            timeData: {
                startTime: new Date(),
                endTime: new Date(),
                numberInput:null
    
            },
            
            selectedDate: [],
            pickerOptions: [],
            option: {},
            myChart: {}
        }
        
    },
    //    watch: {
    //     numberInput(newValue) {
    //         if (newValue) {
    //             // 输入框有数据时，处理 transposedSeriesData
    //           this.updateChart()
    //         } 
            
    //         console.log("_____--------总数量seriesData：", this.transposedSeriesData);
    //     }
    // },
    mounted() {
        this.financialId = this.$route.query.id;
        this.myChart = echarts.init(document.getElementById('main'));
        this.defaultMonth()
        this.initData();
        // this.getCurrentMonth();
        // this.getCurrentYear();
        
        const currentDate = new Date();
        //
//   const currentYear = currentDate.getFullYear();
//   const firstMonthOfYear = new Date(currentYear, 0, 1); // 设置为每一年的第一个月的第一天
//   const currentMonth = currentDate.getMonth() + 1; // 月份从0开始，所以要加1
//   const lastDayOfMonth = new Date(currentYear, currentMonth, 0); // 设置为每一年的当前月份的最后一天

  // this.timeData.startTime = firstMonthOfYear;
  // this.timeData.endTime = lastDayOfMonth;
    },
    methods: {
      handleConfirm(){
         this.initDataNumber()
    //         if (this.numberInput) {
    //     for (let i = 0; i < this.transposedSeriesData.length; i++) {
    //         for (let j = 0; j < this.transposedSeriesData[i].length; j++) {
    //             // 更新 transposedSeriesData 数组项
    //             this.$set(this.transposedSeriesData[i], j, (this.transposedSeriesData[i][j] / this.numberInput).toFixed(3));
    //         }
    //     }
    // }
    // console.log("更新 transposedSeriesData 的值：", this.transposedSeriesData);
      }, 
   splitData() {
    // 初始化空数组用来存放拆分后的数据


    // 遍历后端传来的数据
    this.result.forEach(item => {
        // 拆分地名、月份和数量
        this.branches.push(item.branch);
        this.months.push(item.yearMonth);
        this.numbers.push(item.number);
    });

    // 输出拆分后的数据，用于调试
    console.log("拆分后的地名：", this.branches);
    console.log("拆分后的月份：", this.months);
    console.log("拆分后的数量：", this.numbers);
}
,
        Branchsplit() {
    // 创建一个二维数组，用于存放按照年月分组地区的结果
    const groupedData = [];
    const uniqueMonths = Array.from(new Set(this.months)); // 去重月份

    // 初始化二维数组
    for (let i = 0; i < this.branches.length; i++) {
        groupedData[i] = Array(uniqueMonths.length).fill(0);
    }

    // 填充数据到二维数组中
    this.months.forEach((month, monthIndex) => {
        const monthIdx = uniqueMonths.indexOf(month); // 获取月份在去重后的月份数组中的索引
        const branchIdx = this.branches.indexOf(this.branches[monthIndex]); // 获取地区在地区数组中的索引
        groupedData[branchIdx][monthIdx] = this.numbers[monthIndex]; // 根据月份和地区索引填充数量到对应位置
    });

    return groupedData;
},

        async initData() {
            this.timeData.startTime = this.selectedDate[0],
                this.timeData.endTime = this.selectedDate[1]
                 this.timeData.numberInput=this.numberInput
            try {
                this.loading = true
                console.log(this.timeData)
                this.result = await getIndex12(this.timeData);
                console.log("======>");
                  console.log("后端传过来的数据：", this.result[0]);
                   console.log("后端传过来的数据：", this.result[0].branch);
                   console.log("后端传过来的数据：", this.result[0].yearMonth);
                this.splitData();
                this.data = this.result.integerMap
                this.loading = false
                this.updateChart()
            } catch (error) {
                this.loading = false
            }
        },
       async initDataNumber() {
            this.timeData.startTime = this.selectedDate[0],
                this.timeData.endTime = this.selectedDate[1]
                 this.timeData.numberInput=this.numberInput
            try {
                this.loading = true
                console.log(this.timeData)
                this.result = await getIndex11(this.timeData);
                console.log("======>");
                  console.log("后端传过来的数据：", this.result[0]);
                   console.log("后端传过来的数据：", this.result[0].branch);
                   console.log("后端传过来的数据：", this.result[0].yearMonth);
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
// let a = document.getElementById("echart");
// a.removeAttribute('_echarts_instance_');
// const myChart = echarts.init(a);

var chartDom = document.getElementById('main');
chartDom.removeAttribute('_echarts_instance_');
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
    rotate: 0, // Set rotate to 0 for horizontal labels
  align: 'center', // Align labels in the center horizontally
  verticalAlign: 'top', // Align labels at the top vertically
  position: 'top', // Position labels at the top of the bars
  // rotate: 90,
  // align: 'left',
  // verticalAlign: 'middle',
  // position: 'insideBottom',
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
    },true);
  }
};
const labelOption = {
  show: true,
  position: app.config.position,
  distance: app.config.distance,
  align: app.config.align,
  verticalAlign: app.config.verticalAlign,
  rotate: app.config.rotate,
  fontSize: 16,
  rich: {
    name: {}
  },
   formatter: function (params) {
    return `${(params.value * 100).toFixed(2)}%`; // 将数值转换为百分比
  }
};

// 提取 xAxis 数据
var xAxisData = [];
this.result.forEach(function (item) {
    // 拆分后端传来的数据，提取月份并添加到 xAxisData 数组中
    xAxisData.push(item.yearMonth);
});

// 输出拆分后的月份，用于调试
console.log("拆分后的月份：", xAxisData);

// 提取 legend 数据
var legendData = [];
this.result.forEach(function (item) {
    item.minEntity.forEach(function (minEntity) {
        if (!legendData.includes(minEntity.branch)) {
            legendData.push(minEntity.branch);
        }
    });
});

// 输出提取的 legend 数据，用于调试
console.log("提取的 legend 数据：", legendData);

     // 构建 series 数据
var seriesData = [];

// 遍历每个 result 中的 minEntity 数据
this.result.forEach(function (item) {
    var data = [];
    
    // 遍历图例数据
    legendData.forEach(function (legendItem) {
        var found = false;
        
        // 在当前 result 的 minEntity 中查找与图例数据匹配的 branch
        item.minEntity.forEach(function (minEntity) {
            if (minEntity.branch === legendItem) {
                // 找到了匹配的 branch，则将其对应的 number 添加到 data 数组中
                data.push(minEntity.proportion);
                found = true;
            }
        });
        
        // 如果当前 branch 在当前 result 的 minEntity 中未找到，则将 0 添加到 data 数组中
        if (!found) {
            data.push(0);
        }
    });
    
    // 将当前 result 的 data 添加到 series 数据中
    seriesData.push(data);
});

// 输出 series 数据，用于调试
console.log("series 数据：", seriesData);

// 创建新数组，行数为 legendData 的长度，列数为 result 的长度
this.transposedSeriesData = Array(legendData.length).fill().map(() => []);

// 遍历 result 数据，并将数据按行列互换放入新数组中
for (var i = 0; i < this.result.length; i++) {
    for (var j = 0; j < legendData.length; j++) {
        // 从原 seriesData 中取出数据并放入新数组对应位置
        this.transposedSeriesData[j][i] = seriesData[i][j];
    }
}

// 输出转置后的 seriesData，用于调试
console.log("转置后的 seriesData：", this.transposedSeriesData);
   // 在这里更新 transposedSeriesData 的值
    // 在这里更新 transposedSeriesData 的值
    // if (this.numberInput) {
    //     for (let i = 0; i < this.transposedSeriesData.length; i++) {
    //         for (let j = 0; j < this.transposedSeriesData[i].length; j++) {
    //             // 更新 transposedSeriesData 数组项
    //             this.$set(this.transposedSeriesData[i], j, (this.transposedSeriesData[i][j] / this.numberInput).toFixed(3));
    //         }
    //     }
    // }
    // console.log("更新 transposedSeriesData 的值：", this.transposedSeriesData);
option = {
  tooltip: {
    trigger: 'axis',
    axisPointer: {
      type: 'shadow'
    }
  },
  legend: {
    data: legendData
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
      data: xAxisData
    }
  ],
  yAxis: [
    {
      type: 'value',
       axisLabel: {  
                formatter: function(value) {  
                    return value * 100 + '%'; // 将Y轴的值乘以100后显示百分比  
                }  
      }
    }
  ],
series : this.transposedSeriesData.map( (item,index) => {
     return {
      // console:console.log(legendData[index]),
      name :legendData[index],
      type: 'bar',
      label: labelOption,
      emphasis: {
          focus: 'series'
      },
      data: item
      // data: item.map(value => {
      //    return `${(value * 100).toFixed(2)}%`; // 将每个值转换为百分比并格式化
      //  })
     }
  })
};

option && myChart.setOption(option);

},

     
            //时间选择器的默认月份设置
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

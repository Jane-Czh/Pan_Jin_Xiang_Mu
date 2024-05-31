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
import { getMainRevenueData } from '@/api/financial/data'

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
      myChart: {}
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
      this.timeData.startTime = this.selectedDate[0],
        this.timeData.endTime = this.selectedDate[1]
      try {
        this.loading = true
        const res = await getMainRevenueData(this.timeData);
        this.data = res.rows
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
      this.option = {
        title: {
          text: '各主要车型同比增长比例'
        },
        tooltip: {},
        xAxis: {
          data: this.data.map(item => moment(item.Year_And_Month).format('YY-MM')),
        },
        yAxis: {},
        series: [
          {
            name: '比例',
            type: 'bar',
            data: this.data.map(item => item.MainRevenue),
            label: {
              show: true,
              position: 'top'
            }
          },
        ],
      };
      this.option && this.myChart.setOption(this.option)
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

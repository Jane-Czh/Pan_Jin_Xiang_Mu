<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card" @tab-click="handleTabClick">
      <el-tab-pane label="日上线数" name="0">
        <div class="block">
          <span class="DataSelect" style="margin-right:10px">日期选择</span>
          <el-date-picker v-model="selectedDateDay" type="daterange" unlink-panels range-separator="至"
            start-placeholder="开始日期" end-placeholder="结束日期" :picker-options="pickerOptions" @change="handleDateChange">
          </el-date-picker>
          <!-- <div class="threeData">
        <el-checkbox-group v-model="checkList" size="medium" @change="handleSelectChange">
          <el-checkbox checked='true' label="日"></el-checkbox>
          <el-checkbox label="月"></el-checkbox>
          <el-checkbox label="年"></el-checkbox>
        </el-checkbox-group>
      </div> -->
        </div>
        <div class="DAY">
          <div v-if="loading"
            style="display: flex; justify-content: center; align-items: center; height: 50vh; font-size: 24px;">加载中……
          </div>
          <div v-if="!loading" id="main1" ref="main1"></div>
        </div>

      </el-tab-pane>
      <el-tab-pane label="月上线数" name="1">
        <div class="block">
          <span class="DataSelect" style="margin-right:10px">月份选择</span>
          <el-date-picker v-model="selectedDateMonth" type="monthrange" unlink-panels range-separator="至"
            start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions" @change="handleDateChange">
          </el-date-picker>
        </div>
        <div class="MONTH">
          <div v-if="loading"
            style="display: flex; justify-content: center; align-items: center; height: 50vh; font-size: 24px;">加载中……
          </div>
          <div v-if="!loading" id="main2" ref="main2"></div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="年上线数" name="2">
        <div class="block">
          <span class="DataSelect" style="margin-right:10px">年份选择</span>
          <el-date-picker v-model="selectedDateYear" type="monthrange" unlink-panels range-separator="至"
            start-placeholder="开始年份" end-placeholder="结束年份" :picker-options="pickerOptions" @change="handleDateChange">
          </el-date-picker>
        </div>
        <div class="YEAR">
          <div v-if="loading"
            style="display: flex; justify-content: center; align-items: center; height: 50vh; font-size: 24px;">加载中……
          </div>
          <div v-if="!loading" id="main3" ref="main3"></div>
        </div>
      </el-tab-pane>
      <!-- <el-tab-pane label="全部" name="3">

      </el-tab-pane> -->
      <!-- <div>

      <div class="DAYandMONTH">
        <div id="main1" ref="main1"></div>
        <div id="main2" ref="main2"></div>
      </div>
      <div class="YEAR">
        <div id="main3" ref="main3"></div>
      </div>
    </div> -->
    </el-tabs>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment'
import { getDailyLaunchAndCompletionData, getMonthlyLaunchAndCompletionData, getAnnualLaunchAndCompletionData } from '@/api/production/chartAPI'

export default {
  name: 'indicators74',
  data() {
    return {
      NewDate: [],
      activeName: '0',
      loading: false,
      data1: [],//0123分别为日月年
      data2: [],
      data3: [],
      dataGroup: [],
      apiGroup: [getDailyLaunchAndCompletionData, getMonthlyLaunchAndCompletionData, getAnnualLaunchAndCompletionData],
      timeDataDay: {
        startTime: new Date(),
        endTime: new Date(),
      },
      timeData: {
        startTime: new Date(),
        endTime: new Date(),
      },

      checkList: [],
      selectedDate: [],
      selectedDateDay: [],
      selectedDateMonth: [],
      selectedDateYear: [],
      pickerOptions: [],
      optionDay: {},
      optionMonth: {},
      optionYear: {},
      dayChart: {},
      monthChart: {},
      yearChart: {},
      option: {},
      routerData: {}
    }
  },
  computed: {},
  mounted() {
    this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '' }
    console.log(this.option)
    this.defaultDay()
    this.defaultMonth()
    this.defaultYear()
    this.dayChart = echarts.init(document.getElementById('main1'))
    this.monthChart = echarts.init(document.getElementById('main2'))
    this.yearChart = echarts.init(document.getElementById('main3'))
    this.initData(0)
  },
  methods: {
    async initData(num) {
      this.dataGroup = []
      if (num === 0) {
        this.timeData.startTime = this.selectedDateDay[0]
        this.timeData.endTime = this.selectedDateDay[1]
      } else if (num === 1) {
        this.timeData.startTime = this.selectedDateMonth[0]
        this.timeData.endTime = this.selectedDateMonth[1]
      } else if (num === 2) {
        this.timeData.startTime = this.selectedDateYear[0]
        this.timeData.endTime = this.selectedDateYear[1]
      }
      try {

        this.loading = true
        const res = await this.apiGroup[num](this.timeData);
        this.dataGroup[num] = res.rows
        this.loading = false
        this.updateChart(this.dataGroup[num])//0123分别为日月年全部
        // this.updateChart(this.data2)
        // this.updateChart(this.data3)
      } catch (error) {
        this.loading = false
      }
    },
    handleDateChange(value) {
      if (this.activeName === '0') {
        if (value && value[1]) {
          let endDate = new Date(value[1]);
          endDate.setHours(endDate.getHours() + 13);
          this.selectedDateDay[1] = endDate;
        }
        this.initData(0)
      } else if (this.activeName === '1') {
        if (value && value[1]) {
          let endDate = new Date(value[1]);
          endDate.setMonth(endDate.getMonth() + 1);
          endDate.setDate(0);
          this.selectedDateMonth[1] = endDate;
        }
        this.initData(1)
      } else if (this.activeName === '2') {
        this.initData(2)
      }
    },
    // handleSelectChange() {
    //   if (this.checkList.length === 0) {
    //     this.initData(0)
    //     this.initData(1)
    //     this.initData(2)
    //   } else {
    //     this.initData(this.checkList[0])
    //   }
    // },
    updateChart(rows) {
      // if (num === 1) {

      this.optionDay = {
        title: {
          text: '日上线数'
        },
        tooltip: {},
        xAxis: {
          data: rows.map(item => moment(item.realTime).format('YY-MM-DD')),
        },
        yAxis: {},
        series: [
          {
            name: '上线数',
            type: 'bar',
            data: rows.map(item => item.launch),
            label: {
              show: true,
              position: 'top'
            }
          },
        ],
      };
      this.optionDay && this.dayChart.setOption(this.optionDay, true)
      // }
      // else if (num === 2) {
      this.optionMonth = {
        title: {
          text: '月上线数'
        },
        tooltip: {},
        xAxis: {
          data: rows.map(item => moment(item.realTime).format('YY-MM')),
        },
        yAxis: {},
        series: [
          {
            name: '上线数',
            type: 'bar',
            data: rows.map(item => item.launch),
            label: {
              show: true,
              position: 'top'
            }
          },
        ],
      };
      this.optionMonth && this.monthChart.setOption(this.optionMonth, true)
      // }
      // else if (num === 3) {
      this.optionYear = {
        title: {
          text: '年上线数'
        },
        tooltip: {},
        xAxis: {
          data: rows.map(item => moment(item.realTime).format('YYYY')),
        },
        yAxis: {},
        series: [
          {
            name: '上线数',
            type: 'bar',
            data: rows.map(item => item.launch),
            label: {
              show: true,
              position: 'top'
            }
          },
        ],
      };
      this.optionYear && this.yearChart.setOption(this.optionYear, true)
      // }
    },

    defaultDay() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth();
      const startDate = new Date(currentYear, currentMonth, 1);
      const endDate = new Date(currentYear, currentMonth + 1, 0);
      this.selectedDateDay = [startDate, endDate];
    },
    defaultMonth() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear, 0, 1);
      const endDate = new Date(currentYear, currentMonth, 0);
      this.selectedDateMonth = [startDate, endDate];
    },
    defaultYear() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const fiveYearsAgo = currentYear - 5;
      // 设置起始日期为5年前的1月1日
      const startDate = new Date(fiveYearsAgo, 0, 1);
      // 设置结束日期为当前日期
      const endDate = currentDate;
      this.selectedDateYear = [startDate, endDate];
    },
    handleTabClick(tab) {
      if (tab.name === '0') {
        this.hideChart('main2');
        this.hideChart('main3');
        this.initData(0)
      } else if (tab.name === '1') {
        this.hideChart('main1');
        this.hideChart('main3');
        this.initData(1)
      } else if (tab.name === '2') {
        this.hideChart('main2');
        this.hideChart('main');
        this.initData(2)
      }
    },
    hideChart(chartId) {
      const chart = this.$refs[chartId];
      if (chart) {
        chart.style.display = 'none';
      }
    }
  },



}





</script>

<style lang="scss" scoped>
#main1,
#main2 {
  width: 1000px;
  height: 600px;
  margin: 40px auto;
}

#main3 {
  width: 1000px;
  height: 600px;
  margin: 40px auto;
}

.current-page {
  padding: 16px;
}

// .threeData {
//   margin-top: 20px;
//   display: flex;
//   justify-content: center;
// }



.block {
  margin-top: 50px;
  text-align: center;
}
</style>

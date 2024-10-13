<template>  
  <div>  
    <div class="block">  
      <span class="DataSelect" style="margin-right:10px">日期选择</span>  
      <el-date-picker  
        v-model="selectedDate"  
        type="monthrange"  
        unlink-panels  
        range-separator="至"  
        start-placeholder="开始月份"  
        end-placeholder="结束月份"  
        :picker-options="pickerOptions"  
        @change="handleDateChange"  
      >  
      </el-date-picker>  
    </div>  

    <!-- Tabs 组件 -->  
    <el-tabs v-model="activeTab" @tab-click="handleTabClick">  
      <el-tab-pane label="A" name="A"></el-tab-pane>  
      <el-tab-pane label="B" name="B"></el-tab-pane>  
      <el-tab-pane label="C" name="C"></el-tab-pane>  
    </el-tabs>  

    <div id="main" ref="main"></div>  
  </div>  
</template>  

<script>  
import * as echarts from 'echarts';  
import moment from 'moment';  
import { getMainRevenueDataA, getMainRevenueDataB, getMainRevenueDataC } from '@/api/financial/data';  

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
      activeTab: 'A', // 当前激活的标签  
    };  
  },  
  mounted() {  
    this.defaultMonth();  
    this.myChart = echarts.init(document.getElementById('main'));  
    this.initData(this.activeTab); // 初始化数据  
  },  
  methods: {  
    async initData(tab) {  
      this.timeData.startTime = this.selectedDate[0];  
      this.timeData.endTime = this.selectedDate[1];  
      try {  
        this.loading = true;  
        let res;  
        // 根据当前激活的标签发送不同的请求  
        if (tab === 'A') {  
          res = await getMainRevenueDataA(this.timeData);  
        } else if (tab === 'B') {  
          res = await getMainRevenueDataB(this.timeData);  
        } else if (tab === 'C') {  
          res = await getMainRevenueDataC(this.timeData);  
        }  
        this.data = res.rows;  
        this.loading = false;  
        this.updateChart();  
      } catch (error) {  
        this.loading = false;  
        console.error(error);  
      }  
    },  
    handleDateChange(val) {  
      this.initData(this.activeTab); // 日期变化时重新加载数据  
    },  
    handleTabClick(tab) {  
      this.activeTab = tab.name; // 更新当前激活的标签  
      this.initData(this.activeTab); // 切换标签时加载相应的数据  
    },  
    updateChart() {  
      this.option = {  
        title: {  
          text: '日/月/年接单数',  
        },  
        tooltip: {},  
        xAxis: {  
          data: this.data.map(item => moment(item.Year_And_Month).format('YY-MM')),  
        },  
        yAxis: {},  
        series: [  
          {  
            name: '金额',  
            type: 'line',  
            data: this.data.map(item => item.MainRevenue),  
            label: {  
              show: true,  
              position: 'top',  
            },  
          },  
        ],  
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
};  
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
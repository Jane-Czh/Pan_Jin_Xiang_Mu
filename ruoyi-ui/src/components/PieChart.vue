<template>
    <div ref="pieChart" style="width: 100%; height: 400px;"></div>
  </template>
  
  <script>
  import echarts from 'echarts';
  import { listReport } from '@/api/market/report';

  export default {
    data() {
      return {
        pieChart: null
      };
    },
    mounted() {
      this.loadData().then(() => {
        this.$nextTick(() => {
          setTimeout(() => {
            console.log("this");
            this.initPieChart();
          }, 100); // 延迟 100 毫秒执行
        });
      });
    },

    methods: {
      loadData() {
        return new Promise((resolve, reject) => {
          // 在此处加载数据的代码
          // 假设您使用的是异步请求，比如 axios 或者 fetch
          listReport(this.queryParams).then(response => {
            this.reportList = response.rows;
            this.total = response.total;
            this.loading = false;
            resolve(); // 数据加载完成后，调用 resolve 方法
          }).catch(error => {
            reject(error); // 如果加载数据时出现错误，调用 reject 方法
          });
        });
      },

      initPieChart() {
        // 基于准备好的dom，初始化echarts实例
        this.pieChart = echarts.init(this.$refs.pieChart);
        console.log("看这里");
        console.log(this.pieChart);
        // 指定图表的配置项和数据
        const option = {
              title: {
                    text: '不同网点接单情况',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: '{a} <br/>{b}: {c} ({d}%)'
                },
                series: [
                    {
                        name: '接单情况',
                        type: 'pie',
                        radius: '50%',
                        data: this.formatPieData() // 数据项
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表
            this.pieChart.setOption(option);
      },

      formatPieData() {
            // 根据 reportList 数据格式化饼状图数据
            // 假设 reportList 格式为 [{ name: '网点A', todayOrder: 10, monthOrder: 100, yearOrder: 1000 }, ...]
            return this.reportList.map(item => ({
                name: item.commercialNetworks || '',
                value: item.orderReceivedToday || 0
            }));
      }
    }

    
  };
  </script>
  
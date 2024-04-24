<template>
    <div>
        <div class="block">
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
import { getMainRevenueData } from '@/api/financial/data'

export default {
    data() {
        return {
            loading: false,
            financialId: '',
            data: [],
            // timeData: { startTime: null, endTime: null },
            timeData: {
                startTime: new Date('2024-01-02'),
                endTime: new Date('2024-09-01'),
            },
            selectedDate: [],
            pickerOptions: [],
            option: {},
            myChart: {}
        }
    },
    mounted() {
        this.financialId = this.$route.query.id
        this.myChart = echarts.init(document.getElementById('main'))
        this.initData()
        this.getCurrentMonth()
        this.getCurrentYear()
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
                    text: '主营业务收入'
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
                            position: 'top'
                        }
                    },
                ],
            };
            this.option && this.myChart.setOption(this.option)
        },
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

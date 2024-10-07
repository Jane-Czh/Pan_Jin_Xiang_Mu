<template>
    <div>
        <div class="block">
            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange">
            </el-date-picker>
        </div>
        <div id="main" ref="main"></div>

    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment'
import { getProjectProgressData } from '@/api/tech/data'

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
            routerData: {},
            totalCompleted: 0,
            totalOngoing: 0,
            totalUnstarted: 0,
        }
    },
    computed: {},
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', showTarget: '', targetValue: 0, targetValueDate: '', }
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
                const res = await getProjectProgressData(this.timeData);
                this.data = res.rows
                this.totalCompleted = 0
                this.totalOngoing = 0
                this.totalUnstarted = 0
                //遍历data，让相同字段相加
                this.data.forEach(item => {
                    if (item.completedProjectCount !== null) {
                        this.totalCompleted += item.completedProjectCount;
                    }
                    if (item.ongoingProjectCount !== null) {
                        this.totalOngoing += item.ongoingProjectCount;
                    }
                    if (item.unstartedProjectCount !== null) {
                        this.totalUnstarted += item.unstartedProjectCount;
                    }
                });
                // 填充目标值
                // const yAxisDataLength = this.data.length;
                // this.targetValueArray = Array(yAxisDataLength).fill(this.routerData.targetValue);
                this.loading = false
                this.updateChart()
            } catch (error) {
                console.log(error)
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
            // var chartDom = document.getElementById('main');
            // var myChart = echarts.init(chartDom);
            var option;
            option = {
                title: {
                    text: '项目进度情况',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'item',
                    formatter: "{a} <br/>{b}: {c} ({d}%)"
                },
                legend: {
                    orient: 'vertical',
                    left: 'left'
                },
                series: [
                    {
                        name: '项目状态',
                        type: 'pie',
                        radius: '50%',
                        center: ['50%', '50%'], // 饼图位置
                        data: [
                            { value: this.totalUnstarted, name: '暂未开展' },
                            { value: this.totalOngoing, name: '进行中' },
                            { value: this.totalCompleted, name: '已完成' },
                        ],
                        emphasis: {
                            itemStyle: {
                                shadowBlur: 10,
                                shadowOffsetX: 0,
                                shadowColor: 'rgba(0, 0, 0, 0.5)',
                                borderColor: '#4c4c4c',
                                borderWidth: 1
                            }
                        },
                        label: {
                            show: true,
                            position: 'outside', // 标签位置,inside、outside、top、bottom、left、right
                            formatter: '{b}: {c} ({d}%)'
                        },
                        labelLine: {
                            show: true,
                            smooth: 0.2,
                        },
                        // 设置颜色
                        color: ['#b4bccc', '#73c0de', '#91cc75'] // 灰，蓝，绿
                    }
                ]
            };
            option && this.myChart.setOption(option);
        },
        defaultMonth() {
            const currentDate = new Date();
            const currentYear = currentDate.getFullYear();
            const currentMonth = currentDate.getMonth() + 1;
            const startDate = new Date(currentYear, currentMonth - 1, 1);
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

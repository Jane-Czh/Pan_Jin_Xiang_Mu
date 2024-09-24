<template>
    <div>
        <div class="block">
            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <el-date-picker v-model="selectedDate" type="daterange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange">
            </el-date-picker>
        </div>
        <div>
            <br>
            <div class="centered-value">
                当前累计值：{{ this.currentSum }} (元)
            </div>
        </div>
        <div v-if="loading"
            style="display: flex; justify-content: center; align-items: center; height: 50vh; font-size: 24px;">加载中……
        </div>
        <div id="main" ref="main"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment'
import { getInprogressDayrevenueData } from '@/api/financial/chartAPI'

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
            currentSum: [],
            option: {},
            myChart: {},
            routerData: {},
            targetValueArray: [],
        }
    },
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '', showTargetValue: false };
        this.defaultDay()
        this.myChart = echarts.init(document.getElementById('main'))
        this.initData()
    },
    methods: {
        async initData() {
            this.timeData.startTime = this.selectedDate[0];
            this.timeData.endTime = this.selectedDate[1]
            try {
                this.loading = true
                const res = await getInprogressDayrevenueData(this.timeData);
                this.data = res.rows
                const yAxisDataLength = this.data.length;
                this.targetValueArray = Array(yAxisDataLength).fill(this.routerData.targetValue);
                // this.yAxisData = res.rows.map(item => item[this.routerData.yDataName]);
                this.yAxisData = res.rows
                    .map(item => item[this.routerData.yDataName])
                    .map(item => {
                        return !item || isNaN(item) ? 0 : parseFloat(item);
                    });
                this.currentSum = this.yAxisData.reduce((a, b) => a + b, 0)
                this.currentSum = this.formatNumber(this.currentSum)
                this.loading = false
                this.updateChart()
            } catch (error) {
                console.log(error)
                this.loading = false
            }
        },
        handleDateChange(value) {
            let endDate = new Date(value[1]);
            endDate.setHours(endDate.getHours() + 13);
            this.selectedDate[1] = endDate;
            this.initData()
        },
        formatNumber(value) {
            if (value === null || value === undefined) return '';
            return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
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
                name: '金额',
                type: 'line',
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: this.data.map(item => item.InProgress_DayRevenue),
            }];

            if (this.routerData.showTarget && (this.routerData.targetValue != 0 && this.routerData.targetValue != '')) {
                series.push({
                    name: '目标值',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: this.targetValueArray,
                });
            }
            this.option = {
                title: {
                    text: '当日在制品金额'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['金额', this.routerData.targetValue != '' && this.routerData.targetValue != 0 ? '目标值' : null].filter(item => item !== null),
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true, },
                        dataView: { show: true, readOnly: false, title: '数据视图' },
                        magicType: { show: true, type: ['line', 'bar'], title: { line: '切换为折线图', bar: '切换为柱状图' } },
                        // restore: { show: true, title: '还原' },
                        saveAsImage: { show: true, title: '保存为图片' }
                    }
                },
                xAxis: [
                    {
                        // type: 'category',
                        axisTick: { show: false },
                        boundaryGap: true,
                        data: this.data.map(item => moment(item.Data_Time).format('YY-MM-DD')),
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    }
                ],
                series: series
            };

            this.option && this.myChart.setOption(this.option);
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
        defaultDay() {
            const currentDate = new Date();
            const currentYear = currentDate.getFullYear();
            const currentMonth = currentDate.getMonth();
            const startDate = new Date(currentYear, currentMonth, 1);
            const endDate = new Date(currentYear, currentMonth + 1, 0);
            this.selectedDate = [startDate, endDate];
        },
    }

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

.centered-value {
    display: flex;
    justify-content: center;

    align-items: center;

    margin-top: 10px;

}
</style>

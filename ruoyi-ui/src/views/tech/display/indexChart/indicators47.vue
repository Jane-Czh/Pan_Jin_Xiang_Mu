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
        <div id="main2" ref="main2"></div>

    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment'
import { getNonStandardAVGPreparationDaysData } from '@/api/tech/data'

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
            option2: {},
            myChart: {},
            myChart2: {},
            routerData: {},
            targetValueArray: [],
        }
    },
    computed: {},
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', showTarget: '', targetValue: 0, targetValueDate: '', }
        this.defaultMonth()
        this.myChart = echarts.init(document.getElementById('main'))
        this.myChart2 = echarts.init(document.getElementById('main2'))
        this.initData()
    },
    methods: {
        async initData() {
            this.timeData.startTime = this.selectedDate[0],
                this.timeData.endTime = this.selectedDate[1]
            try {
                this.loading = true
                const res = await getNonStandardAVGPreparationDaysData(this.timeData);
                this.data = res.rows
                const yAxisDataLength = this.data.length;
                this.targetValueArray = Array(yAxisDataLength).fill(this.routerData.targetValue);
                // this.data[4].nonStandardOrderGrowthRate = 20
                // this.data[5].nonStandardOrderGrowthRate = -20
                this.updateChart()
                console.log(this.data)
                // console.log(this.targetValue)
                // console.log(this.targetValue != 0 && this.targetValue != '' && this.targetValue != undefined)
                this.loading = false
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
                formatter: function (params) {
                    let value = parseFloat(params.value).toFixed(2);
                    // 移除多余的零
                    value = value.replace(/\.?0+$/, '');
                    if (params.seriesName.includes('率')) {
                        value += '%';
                    }
                    return value;
                },
                fontSize: 16,
                rich: {
                    name: {}
                }
            };
            // 根据条件决定是否添加目标值系列
            let series = [
                {
                    name: '平均准备天数',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    // yAxisIndex: 1,
                    data: this.data.map(item => item.nonStandardAvgPreparationDays),
                },
            ];
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
                    text: '非标订单平均技术准备天数'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['平均准备天数', (this.routerData.targetValue != 0 && this.routerData.targetValue != '') ? '目标值' : null].filter(item => item !== null),
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true, },
                        dataView: { show: true, readOnly: false, title: '数据视图' },
                        magicType: { show: true, type: ['bar'], title: { bar: '切换为柱状图' } },
                        restore: { show: true, title: '还原' },
                        saveAsImage: { show: true, title: '保存为图片' }
                    }
                },
                xAxis: [
                    {
                        // type: 'category',
                        axisTick: { show: false },
                        data: this.data.map(item => moment(item.yearAndMonth).format('YY-MM')),
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                ],
                series: series
            };
            this.option && this.myChart.setOption(this.option);
            this.updateChart2()
        },
        updateChart2() {
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
                formatter: function (params) {
                    let value = parseFloat(params.value).toFixed(2);
                    // 移除多余的零
                    value = value.replace(/\.?0+$/, '');
                    if (params.seriesName.includes('率')) {
                        value += '%';
                    }
                    return value;
                },
                fontSize: 16,

                rich: {
                    name: {}
                }
            };
            // 根据条件决定是否添加目标值系列
            let series = [
                {
                    name: '当月总条数',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: this.data.map(item => item.nonStandardNum),
                },
                {
                    name: '同比增幅率',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    yAxisIndex: 1,
                    data: this.data.map(item => item.nonStandardOrderGrowthRate),
                    //折线图加粗
                    lineStyle: {
                        width: 5
                    }
                },
            ];

            this.option2 = {
                title: {
                    text: '总条数与同比增幅率'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['当月总条数', '同比增幅率'],
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
                        restore: { show: true, title: '还原' },
                        saveAsImage: { show: true, title: '保存为图片' }
                    }
                },
                xAxis: [
                    {
                        // type: 'category',
                        axisTick: { show: false },
                        data: this.data.map(item => moment(item.yearAndMonth).format('YY-MM')),
                    }
                ],
                yAxis: [
                    {
                        type: 'value',
                        axisLine: {
                            show: true // y轴线
                        },
                    },
                    {
                        type: 'value',
                        name: '率',
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: '#9b9ca3'
                            },
                            show: true // y轴线
                        },
                        splitLine: {
                            show: false // 网格线
                        },
                        axisTick: {
                            show: false // 刻度标记
                        },
                    },

                ],
                series: series
            };
            this.option2 && this.myChart2.setOption(this.option2);
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

#main2 {
    width: 1000px;
    height: 600px;
    margin: 40px auto;
}

.block {
    margin-top: 50px;
    text-align: center;
}
</style>

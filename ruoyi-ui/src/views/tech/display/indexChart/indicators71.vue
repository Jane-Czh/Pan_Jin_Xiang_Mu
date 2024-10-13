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
import { getPrdScheduleCompletionRateData } from '@/api/tech/data'
import { getNameTarget } from '@/api/financial/target'
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
            ifTargetEmpty: '',
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
                const res = await getPrdScheduleCompletionRateData(this.timeData);
                this.data = res.rows
                const yAxisDataLength = this.data.length;
                this.targetValueArray = Array(yAxisDataLength).fill(this.routerData.targetValue);

                //目标值
                let newTarget = {
                    name: this.routerData.sum,
                    startDate: this.selectedDate[0],
                    endDate: this.selectedDate[1]
                }
                console.log(newTarget)
                const res1 = await getNameTarget(newTarget)
                let nowTarget = res1.rows
                this.ifTargetEmpty = res1.rows.length
                // console.log(res1)
                if (this.ifTargetEmpty) {
                    let allTarget = []; // 初始化目标数组
                    nowTarget.forEach(item => {
                        let natureYear = moment(item.natureYear).format('YYYY')
                        let targetValue = item.targetValue; // 目标值可能是数字或null
                        allTarget.push({ natureYear, targetValue });
                    })
                    console.log(nowTarget)
                    this.data.forEach(item => {
                        const year = moment(item.yearAndMonth).format('YYYY')
                        allTarget.forEach(row => {
                            if (year === row.natureYear) {
                                item.targetValue = row.targetValue
                            }
                        })
                    });
                }

                console.log(this.data)

                this.loading = false
                this.updateChart()
            } catch (error) {
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
                fontSize: 14,
                // emphasis: {
                //     borderWidth: 0,
                //     borderColor: 'transparent',
                //     // 其他样式调整
                // },
                // borderColor: '#f20012',
                // rich: {
                //     name: {
                //         fontWeight: 'bold',
                //         color: '#f20012' // 名称部分的样式
                //     },
                //     value: {
                //         color: '#5e58e7' // 数值部分的样式
                //     }
                // }
            };

            let series = [
                {
                    name: '项目总数',
                    type: 'bar',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },

                    data: this.data.map(item => item.totalProjectCount),
                },
                {

                    name: '积分占比率',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    yAxisIndex: 1,
                    data: this.data.map(item => item.projectPointsPercentage),
                }
            ];
            // 根据条件决定是否添加目标值
            if (this.ifTargetEmpty) {
                series.push({
                    name: '目标值',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: this.data.map(item => item.targetValue),
                });
            }

            this.option = {
                title: {
                    text: '项目完成情况'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['项目总数', '积分占比率', this.ifTargetEmpty ? '目标值' : null].filter(item => item !== null),
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
                    {
                        type: 'value',
                        name: '率',
                        min: 0,
                        max: 100,
                        position: 'right',
                        axisLine: {
                            lineStyle: {
                                color: '#9b9ca3'
                            }
                        }
                    },
                ],
                series: series
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

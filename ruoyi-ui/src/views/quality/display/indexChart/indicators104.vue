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
import { getPartQualificationRateData } from '@/api/quality/chartAPI'
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
            targetValueArray: [],
            ifTargetEmpty: '',
        }
    },
    computed: {},
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '', showTargetValue: false };
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
                const res = await getPartQualificationRateData(this.timeData);
                this.data = res.rows
                const yAxisDataLength = this.data.length;
                this.targetValueArray = Array(yAxisDataLength).fill(this.routerData.targetValue);

                //目标值
                let newTarget = {
                    name: this.routerData.sum,
                    startDate: this.selectedDate[0],
                    endDate: this.selectedDate[1]
                }
                let newTarget2 = {
                    name: this.routerData.sum2,
                    startDate: this.selectedDate[0],
                    endDate: this.selectedDate[1]
                }
                // console.log(newTarget)
                const res1 = await getNameTarget(newTarget)
                const res2 = await getNameTarget(newTarget2)
                let nowTarget = res1.rows
                let nowTarget2 = res2.rows
                this.ifTargetEmpty = res1.rows.length
                this.ifTargetEmpty2 = res2.rows.length
                // console.log(this.ifTargetEmpty)
                // console.log(nowTarget)
                if (this.ifTargetEmpty) {
                    let allTarget = []; // 初始化目标数组
                    nowTarget.forEach(item => {
                        let natureYear = moment(item.natureYear).format('YYYY')
                        let targetValue = item.targetValue; // 目标值可能是数字或null
                        allTarget.push({ natureYear, targetValue });
                    })
                    this.data.forEach(item => {
                        const year = moment(item.yearAndMonth).format('YYYY')
                        allTarget.forEach(row => {
                            if (year === row.natureYear) {
                                item.targetValue = row.targetValue
                            }
                        })
                    });
                    console.log(allTarget)
                }
                if (this.ifTargetEmpty2) {
                    let allTarget2 = []; // 初始化目标数组
                    nowTarget2.forEach(item => {
                        let natureYear2 = moment(item.natureYear).format('YYYY')
                        let targetValue2 = item.targetValue; // 目标值可能是数字或null
                        allTarget2.push({ natureYear2, targetValue2 });
                    })
                    this.data.forEach(item => {
                        const year = moment(item.yearAndMonth).format('YYYY')
                        allTarget2.forEach(row => {
                            if (year === row.natureYear2) {
                                item.targetValue2 = row.targetValue2
                            }
                        })
                    });
                    console.log(allTarget2)
                }

                console.log(this.data)

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
                formatter: (params) => {
                    return params.value.toLocaleString(undefined, { minimumFractionDigits: 0, maximumFractionDigits: 2, useGrouping: false });
                },
                fontSize: 16,
                rich: {
                    name: {}
                }
            };
            // 根据条件决定是否添加目标值系列
            let series = [{
                name: '班组自查合格率',
                type: 'line',
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: this.data.map(item => item.selfcheckPassrate),
            },
            {
                name: '下道工序反馈合格率',
                type: 'line',
                label: labelOption,
                emphasis: {
                    focus: 'series'
                },
                data: this.data.map(item => item.nextprocessFeedbackPassrate),
            }];

            if (this.ifTargetEmpty) {
                series.push({
                    name: '班组目标值',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: this.data.map(item => item.targetValue),
                });
            }
            if (this.ifTargetEmpty2) {
                series.push({
                    name: '下道目标值',
                    type: 'line',
                    label: labelOption,
                    emphasis: {
                        focus: 'series'
                    },
                    data: this.data.map(item => item.targetValue2),
                });
            }
            this.option = {
                title: {
                    text: '班组自查合格率与\n下道工序反馈合格率'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['班组自查合格率', '下道工序反馈合格率', this.ifTargetEmpty ? '班组目标值' : null, this.ifTargetEmpty2 ? '下道目标值' : null].filter(item => item !== null),
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true, },
                        dataView: { show: true, readOnly: false, title: '数据视图' },
                        magicType: { show: true, type: ['line', 'bar', 'stack'], title: { line: '切换为折线图', bar: '切换为柱状图', stack: '切换为堆叠图' } },
                        restore: { show: true, title: '还原' },
                        saveAsImage: { show: true, title: '保存为图片' }
                    }
                },
                xAxis: [
                    {
                        // type: 'category',
                        axisTick: { show: false },
                        boundaryGap: true,
                        data: this.data.map(item => moment(item.yearAndMonth).format('YY-MM')),
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

            //折线图切换
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

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
import { getSingleInspectionPassRateData } from '@/api/quality/chartAPI'
import { getTargetData } from '@/api/financial/target'


export default {
    data() {
        return {
            loading: false,
            data: [],
            date: new Date(),
            timeData: {
                startTime: new Date(),
                endTime: new Date(),
            },
            targetValue: 0,
            selectedDate: [],
            targetValueArray: [],
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
            // const datePost = {
            //     date: new Date(),
            //     deptName: 'quality'
            // }
            this.timeData.startTime = this.selectedDate[0],
                this.timeData.endTime = this.selectedDate[1]
            try {
                this.loading = true
                const res = await getSingleInspectionPassRateData(this.timeData);
                this.data = res.rows

                // let target = await getTargetData(datePost);

                // target.forEach(item => {
                //     item.rows.forEach(row => {
                //         if (row.indicatorName === 'singleInspectionPassRate') {
                //             this.targetValue = row.targetValue;
                //         }
                //     });
                // });
                // console.log(this.targetValue)
                // console.log('-------------++++++')
                // const yAxisDataLength = this.data.length;
                // this.targetValueArray = Array(yAxisDataLength).fill(this.targetValue);
                this.loading = false
                this.updateChart()
            } catch (error) {
                console.log('-------------++++++:error')
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
                formatter: '{c}%',
                fontSize: 16,
                rich: {
                    name: {}
                }
            };
            this.option = {
                title: {
                    text: '电车、大吨位一次交检合格率'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    data: ['电车', '大吨位', '电车和大吨位']
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
                        // restore: { show: true, title: '还原' },
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
                series: [
                    {
                        name: '电车',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.data.map(item => item.electricCarPassRate),
                    },
                    {
                        name: '大吨位',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.data.map(item => item.largeTonPassRate),
                    },
                    {
                        name: '电车和大吨位',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.data.map(item => item.singleInspectionPassRate),
                    },
                    // {
                    //     name: '目标值',
                    //     type: 'line',
                    //     label: labelOption,
                    //     emphasis: {
                    //         focus: 'series'
                    //     },
                    //     data: this.targetValueArray,
                    // }
                ]
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

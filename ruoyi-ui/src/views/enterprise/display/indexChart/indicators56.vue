<template>
    <div>
        <div class="block">
            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange">
            </el-date-picker>
        </div>
        <div v-if="loading" class="loading-indicator">
            <br><br>加载中...
        </div>

        <div>
            <div id="main" ref="main"></div>
        </div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment';
import { getMonthlyFunctionalAVGIncomeData } from '@/api/enterprise/chartAPI'

export default {
    data() {
        return {
            loading: false,
            data: [],
            dataBefore: [],
            timeData: {
                startTime: new Date(),
                endTime: new Date(),
            },
            selectedDate: [],
            pickerOptions: [],
            option: {},
            myChart: {},
            parsedData: {},
            routerData: {},
        }
    },
    computed: {},
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '' };
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
                const res = await getMonthlyFunctionalAVGIncomeData(this.timeData);
                this.data = res.rows
                const timeDataBefore = this.timeData
                timeDataBefore.startTime.setFullYear(timeDataBefore.startTime.getFullYear() - 1)
                timeDataBefore.endTime.setFullYear(timeDataBefore.endTime.getFullYear() - 1)
                const res2 = await getMonthlyFunctionalAVGIncomeData(timeDataBefore);
                this.dataBefore = res2.rows
                this.data.forEach(row => {
                    this.$set(row, 'monthlyFunctionalAVGIncomeBefore', '');
                });
                this.data.forEach((item, index) => {
                    let date1 = new Date(item.yearAndMonth)
                    this.dataBefore.forEach((itemBefore, indexBefore) => {
                        let newDateBefore = new Date(itemBefore.yearAndMonth)
                        if (date1.getFullYear() - 1 === newDateBefore.getFullYear() && date1.getMonth() === newDateBefore.getMonth()) {
                            this.data[index].monthlyFunctionalAVGIncomeBefore = this.dataBefore[indexBefore].monthlyFunctionalAVGIncome
                        }
                    })
                });
                this.loading = false
                this.updateChart()
            } catch (error) {
                this.loading = false
                console.log(error)
            }
        },
        handleDateChange(value) {
            if (value && value[1]) {
                let endDate = new Date(value[1]);
                endDate.setHours(endDate.getHours() + 13);
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
                formatter: '{c}',
                fontSize: 12,
                rich: {
                    name: {}
                }
            };
            this.option = {
                title: {
                    text: '月度累计职能人均收入',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                },
                legend: {
                    data: ['收入', '同期收入', this.option.targetValue != '' ? '目标值' : null],
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true, },
                        dataView: { show: true, readOnly: false, title: '数据视图' },
                        magicType: { show: true, type: ['bar', 'line', 'stack'], title: { bar: '切换为柱状图', line: '切换为折线图', stack: '切换为堆叠图' } },
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
                    }
                ],
                series: [
                    {
                        name: '收入',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.data.map(item => item.monthlyFunctionalAVGIncome),
                    },
                    {
                        name: '同期收入',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        data: this.data.map(item => item.monthlyFunctionalAVGIncomeBefore),
                    }]
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
        // defaultMonth() {
        //     const currentDate = new Date();
        //     const currentYear = currentDate.getFullYear();
        //     const currentMonth = currentDate.getMonth() + 1;
        //     const startDate = new Date(currentYear, 0, 1);
        //     const endDate = new Date(currentYear, currentMonth, 0);
        //     this.selectedDate = [startDate, endDate];
        // },
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

.chart-title {
    position: absolute;
    left: 27%;
    /* 水平位置，基于容器的% */
    // top: 50%;
    /* 垂直位置，基于容器的% */
    transform: translate(-50%, -50%);
    /* 使用transform来真正居中文本 */
    margin-bottom: 10px;
    /* 为标题添加底部边距 */
    font-size: 14px;
    font-weight: 900;
    /* 或者使用 font-weight: 700; */
}

.loading-indicator {
    position: absolute;
    left: 50%;
    /* 水平位置，基于容器的% */
    // top: 50%;
    /* 垂直位置，基于容器的% */
    transform: translate(-50%, -50%);
    /* 使用transform来真正居中文本 */
    margin-bottom: 10px;
    /* 为标题添加底部边距 */
    font-size: 20px;

    /* 或者使用 font-weight: 700; */
}
</style>

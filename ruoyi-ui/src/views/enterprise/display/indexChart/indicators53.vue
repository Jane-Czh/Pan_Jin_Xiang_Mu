<template>
    <div>
        <div class="block">
            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <!-- <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange">
            </el-date-picker> -->
            <el-date-picker v-model="selectedDateNew" type="month" placeholder="选择日期" value-format="yyyy-MM-dd HH:mm:ss"
                @change="handleDateChange">
            </el-date-picker>
        </div>
        <div v-if="loading" class="loading-indicator">
            <br><br>加载中...
        </div>
        <div class="chart-title">
            <br>
            <br>
            <h2>日清日结指标</h2>
        </div>
        <div>
            <div id="main" ref="main"></div>
        </div>
        <div>
            <div id="main2" ref="main2"></div>
        </div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment';
import { getDailyClearingSettlementData } from '@/api/enterprise/chartAPI'
import { getNameTarget } from '@/api/financial/target'
export default {
    data() {
        return {
            loading: false,
            data: [],
            actualYY: [],
            timeData: {
                startTime: new Date(),
                endTime: new Date(),
            },
            selectedDate: [],
            selectedDateNew: '',
            pickerOptions: [],
            option: {},
            option2: {},
            myChart: {},
            myChart2: {},
            parsedData: {},
            routerData: {},
            actualData: [],
            targetData: [],
            scoreData: [],
            dataYY: [],
            flag: 0,
        }
    },
    computed: {},
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '' };
        this.defaultDay()
        this.myChart = echarts.init(document.getElementById('main'))
        this.myChart2 = echarts.init(document.getElementById('main2'))
        this.initData()
    },
    methods: {
        async initData() {
            console.log(this.selectedDateNew)
            this.timeData.startTime = this.selectedDateNew;
            this.timeData.endTime = this.selectedDateNew;
            try {
                this.loading = true
                // let newTarget = {}
                const res = await getDailyClearingSettlementData(this.timeData);
                this.data = res.rows
                this.targetData = []
                this.actualData = []
                this.scoreData = []
                let flagTarget = 0
                let flagActual = 0
                let flagScore = 0
                res.rows.forEach(item => {
                    if (item.flag === 1) {
                        this.targetData.push(item)
                        flagTarget = 1
                    } else if (item.flag === 2) {
                        this.actualData.push(item)
                        flagActual = 1
                    } else if (item.flag === 3) {
                        this.scoreData.push(item)
                        flagScore = 1
                    }
                })
                // console.log(flagActual)
                this.actualYY = []
                this.dataYY = []
                if (flagActual) {
                    this.actualData.forEach(item => {
                        this.targetData.forEach(row => {
                            let item1 = moment(item.yearAndMonth).format('YYYY')
                            let row1 = moment(row.yearAndMonth).format('YYYY')
                            if (item1 == row1) {
                                item.orderEntryDelayTarget = row.orderEntryDelayRatio ? row.orderEntryDelayRatio : 0
                                item.shipmentDelayTarget = row.shipmentDelayRatio ? row.shipmentDelayRatio : 0
                                item.productionReportDelayTarget = row.productionReportDelayRatio ? row.productionReportDelayRatio : 0
                                item.inspectionDelayTarget = row.inspectionDelayRate ? row.inspectionDelayRate : 0
                                item.invoicePostingDelayTarget = row.invoicePostingDelayRate ? row.invoicePostingDelayRate : 0
                                item.unsettledAccountsTarget = row.unsettledAccountsRatio ? row.unsettledAccountsRatio : 0
                                // this.flag = 1
                            }
                        })
                    })
                    this.actualYY[0] = this.actualData[0].orderEntryDelayRatio ? this.actualData[0].orderEntryDelayRatio : 0
                    this.actualYY[1] = this.actualData[0].shipmentDelayRatio ? this.actualData[0].shipmentDelayRatio : 0
                    this.actualYY[2] = this.actualData[0].productionReportDelayRatio ? this.actualData[0].productionReportDelayRatio : 0
                    this.actualYY[3] = this.actualData[0].inspectionDelayRate ? this.actualData[0].inspectionDelayRate : 0
                    this.actualYY[4] = this.actualData[0].invoicePostingDelayRate ? this.actualData[0].invoicePostingDelayRate : 0
                    this.actualYY[5] = this.actualData[0].unsettledAccountsRatio ? this.actualData[0].unsettledAccountsRatio : 0

                    this.dataYY[0] = this.actualData[0].orderEntryDelayTarget ? this.actualData[0].orderEntryDelayTarget : 0
                    this.dataYY[1] = this.actualData[0].shipmentDelayTarget ? this.actualData[0].shipmentDelayTarget : 0
                    this.dataYY[2] = this.actualData[0].productionReportDelayTarget ? this.actualData[0].productionReportDelayTarget : 0
                    this.dataYY[3] = this.actualData[0].inspectionDelayTarget ? this.actualData[0].inspectionDelayTarget : 0
                    this.dataYY[4] = this.actualData[0].invoicePostingDelayTarget ? this.actualData[0].invoicePostingDelayTarget : 0
                    this.dataYY[5] = this.actualData[0].unsettledAccountsTarget ? this.actualData[0].unsettledAccountsTarget : 0
                }


                console.log(this.actualYY)

                // console.log(this.dataYY)
                // console.log(this.actualData)
                // console.log(this.scoreData)
                this.loading = false
                this.updateChart()
            } catch (error) {
                console.log(error)
                this.loading = false
            }
        },
        handleDateChange(value) {
            let startDate = new Date(value)
            let endDate = new Date(value);
            endDate.setHours(endDate.getHours() + 13);
            this.selectedDate[0] = startDate
            this.selectedDate[1] = endDate;
            this.initData()
        },
        //目标值，实际值
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
                    let value = params.value;
                    if (params.seriesName.includes('%') && value != 0) {
                        value += '%';
                    }
                    return value;
                },
                fontSize: 14,
                rich: {
                    name: {}
                }
            };
            this.option = {
                title: {
                    text: '',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                },
                legend: {
                    type: 'scroll',
                    animationDurationUpdate: 0,
                    data: ['实际值', '目标值'],
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
                        type: 'category',
                        axisTick: { show: false },
                        data: ['销售录入不及时', '销售发货不及时', '生产报工不及时', '成品检验业务不及时', '销售发票过账不及时', '客户未清账'],
                    },
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    // {
                    //     type: 'value',
                    //     name: '比例',
                    //     // interval: 5,
                    //     splitLine: { show: false },
                    //     axisLabel: {
                    //         formatter: '{value} %'
                    //     }
                    // }
                ],
                series: [
                    {
                        name: '实际值',
                        type: 'line',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // color: '#fadc03',
                        // yAxisIndex: 1,
                        // yAxisIndex: 1,
                        // stack: 'stack1',
                        // symbolOffset: [50, 0],
                        data: this.actualYY,
                    },
                    {
                        name: '目标值',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // color: '#fab103',
                        // yAxisIndex: 1,
                        // stack: 'stack1',
                        data: this.dataYY,
                        // data: this.actualData.map(item => item.orderEntryDelayTarget),
                    },

                ]
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
            this.updateChart2()
        },
        //得分情况
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
                    this.myChart2.setOption({
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
                    let value = params.value;
                    if (params.seriesName.includes('%') && value != 0) {
                        value += '%';
                    }
                    return value;
                },
                fontSize: 14,
                rich: {
                    name: {}
                }
            };
            this.option2 = {
                title: {
                    text: '',
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    },
                },
                legend: {
                    data: ['销售订单录入不及时得分', '销售订单不及时发货得分', '生产订单不及时报工得分', '成品检验业务不及时得分', '销售发票过账不及时得分', '客户未清账得分'],

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
                        data: this.scoreData.map(item => moment(item.yearAndMonth).format('YY-MM')),
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },

                ],
                series: [
                    {
                        name: '销售订单录入不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        data: this.scoreData.map(item => item.orderEntryDelayRatio),
                    },
                    {
                        name: '销售订单不及时发货得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },

                        // stack: 'stack2',
                        data: this.scoreData.map(item => item.shipmentDelayRatio),
                    },
                    {
                        name: '生产订单不及时报工得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        data: this.scoreData.map(item => item.productionReportDelayRatio),
                    },
                    {
                        name: '成品检验业务不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        data: this.scoreData.map(item => item.inspectionDelayRate),
                    },
                    {
                        name: '销售发票过账不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        data: this.scoreData.map(item => item.invoicePostingDelayRate),
                    },
                    {
                        name: '客户未清账得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        data: this.scoreData.map(item => item.unsettledAccountsRatio),
                    },

                ]
            };
            this.option2 && this.myChart2.setOption(this.option2);

            this.myChart2.on('magictypechanged', (params) => {
                var magicType = params.currentType;
                if (magicType == 'line') {
                    this.option2.xAxis[0].boundaryGap = true;
                    for (let i = 0; i < this.option2series.length; i++) {
                        this.option2.series[i].type = magicType;
                    }
                    this.myChart2.setOption(this.option2);
                }
            });

        },
        defaultDay() {
            const currentDate = new Date();
            const currentYear = currentDate.getFullYear();
            const currentMonth = currentDate.getMonth();
            const startDate = new Date(currentYear, currentMonth - 1, 2);
            const endDate = new Date(currentYear, currentMonth, 0);
            this.selectedDate = [startDate, endDate];
            this.selectedDateNew = startDate
        },
    },



}





</script>

<style lang="scss" scoped>
#main {
    width: 1400px;
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

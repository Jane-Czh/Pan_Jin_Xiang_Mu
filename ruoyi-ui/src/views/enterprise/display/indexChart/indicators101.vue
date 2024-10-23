<template>
    <div>
        <div class="block">
            <span class="DataSelect" style="margin-right:10px">日期选择</span>
            <!-- <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
                start-placeholder="开始月份" end-placeholder="结束月份" :picker-options="pickerOptions"
                @change="handleDateChange">
            </el-date-picker> -->
            <el-date-picker v-model="selectedDateNew" type="month" placeholder="选择日期" value-format="yyyy-MM-dd"
                @change="handleDateChange">
            </el-date-picker>
        </div>
        <div v-if="loading" class="loading-indicator">
            <br><br>加载中...
        </div>
        <div class="chart-title">
            <br>
            <br>
            <h2>SAP管理指标</h2>
        </div>
        <div id="main" ref="main"></div>
        <div id="main2" ref="main2"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment';
import { getManagementData } from '@/api/enterprise/chartAPI'
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
            option2: {},
            myChart: {},
            myChart2: {},
            selectedDateNew: '',
            parsedData: {},
            targetData: [],
            panJinTarget: [],
            shareTarget: [],
            actualValue: [],
            actualScore: [],
            scoreArray: [],
            valueArray: [],
            shareArray: [],
            panJinArray: [],
            target: {
                date: new Date(),
                deptName: 'enterprise',
            },
            routerData: {},
        }
    },
    computed: {},
    mounted() {
        this.routerData = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '' };
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
                this.shareTarget = []
                this.panJinTarget = []
                this.actualValue = []
                this.actualScore = []
                let flagShareTarget = 0
                let flagPanJinTarget = 0
                let flagActualValue = 0
                const res = await getManagementData(this.timeData);
                this.data = res.rows
                res.rows.forEach(item => {
                    if (item.flag === 1) {
                        this.shareTarget.push(item)
                        flagShareTarget = 1
                    } else if (item.flag === 2) {
                        this.panJinTarget.push(item)
                        flagPanJinTarget = 1
                    } else if (item.flag === 3) {
                        this.actualValue.push(item)
                        flagActualValue = 1
                    } else if (item.flag === 4) {
                        this.actualScore.push(item)
                    }
                });
                // console.log(this.actualScore)
                this.valueArray = []
                this.shareArray = []
                this.panJinArray = []
                if (flagActualValue) {
                    this.valueArray[0] = this.actualValue[0].sdSalesordervalidity
                    this.valueArray[1] = this.actualValue[0].ppManualpocreationratio
                    this.valueArray[2] = this.actualValue[0].ppDeliveredunreportedratio
                    this.valueArray[3] = this.actualValue[0].mesLateworkreportingrate
                    this.valueArray[4] = this.actualValue[0].qmExternalinspectiondelay
                    this.valueArray[5] = this.actualValue[0].mmPurchaseorderlatedelivery
                    this.valueArray[6] = this.actualValue[0].mmManualpocreation
                    this.valueArray[7] = this.actualValue[0].mmUnsettledpurchaserequests
                    this.valueArray[8] = this.actualValue[0].ficoMonthlystandardpricevariation
                    this.valueArray[9] = this.actualValue[0].pmLatemaintenanceordercompletion
                    this.valueArray[10] = this.actualValue[0].crossMonthProductionOrders
                }
                if (flagShareTarget) {
                    this.shareArray[0] = this.shareTarget[0].sdSalesordervalidity
                    this.shareArray[1] = this.shareTarget[0].ppManualpocreationratio
                    this.shareArray[2] = this.shareTarget[0].ppDeliveredunreportedratio
                    this.shareArray[3] = this.shareTarget[0].mesLateworkreportingrate
                    this.shareArray[4] = this.shareTarget[0].qmExternalinspectiondelay
                    this.shareArray[5] = this.shareTarget[0].mmPurchaseorderlatedelivery
                    this.shareArray[6] = this.shareTarget[0].mmManualpocreation
                    this.shareArray[7] = this.shareTarget[0].mmUnsettledpurchaserequests
                    this.shareArray[8] = this.shareTarget[0].ficoMonthlystandardpricevariation
                    this.shareArray[9] = this.shareTarget[0].pmLatemaintenanceordercompletion
                    this.shareArray[10] = this.shareTarget[0].crossMonthProductionOrders
                }
                if (flagPanJinTarget) {
                    this.panJinArray[0] = this.panJinTarget[0].sdSalesordervalidity
                    this.panJinArray[1] = this.panJinTarget[0].ppManualpocreationratio
                    this.panJinArray[2] = this.panJinTarget[0].ppDeliveredunreportedratio
                    this.panJinArray[3] = this.panJinTarget[0].mesLateworkreportingrate
                    this.panJinArray[4] = this.panJinTarget[0].qmExternalinspectiondelay
                    this.panJinArray[5] = this.panJinTarget[0].mmPurchaseorderlatedelivery
                    this.panJinArray[6] = this.panJinTarget[0].mmManualpocreation
                    this.panJinArray[7] = this.panJinTarget[0].mmUnsettledpurchaserequests
                    this.panJinArray[8] = this.panJinTarget[0].ficoMonthlystandardpricevariation
                    this.panJinArray[9] = this.panJinTarget[0].pmLatemaintenanceordercompletion
                    this.panJinArray[10] = this.panJinTarget[0].crossMonthProductionOrders
                }


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
        // handleDateChange(value) {
        //     if (value && value[1]) {
        //         let endDate = new Date(value[1]);
        //         endDate.setMonth(endDate.getMonth() + 1);
        //         endDate.setDate(0);
        //         this.selectedDate[1] = endDate;
        //     }
        //     this.initData()
        // },
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
                // formatter: function (params) {
                //     let value = params.value;
                //     if (params.seriesName.includes('%') && value != 0) {
                //         value += '%';
                //     }
                //     return value;
                // },
                formatter: '{c} %',
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
                        type: 'shadow',
                    },

                },
                legend: {
                    type: 'scroll',
                    animationDurationUpdate: 0,
                    data: ['股份目标值', '盘锦目标值', '当月实际值']
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true, },
                        dataView: { show: true, readOnly: false, title: '数据视图' },
                        // magicType: { show: true, type: ['bar', 'line', 'stack'], title: { bar: '切换为柱状图', line: '切换为折线图', stack: '切换为堆叠图' } },
                        // restore: { show: true, title: '还原' },
                        saveAsImage: { show: true, title: '保存为图片' }
                    }
                },
                xAxis: [
                    {
                        // type: 'category',
                        axisTick: { show: false },
                        data: ['SD销售\n订单有效\n性考核', 'PP手工\n创建生产\n订单比例', 'PP生产订\n单已收货\n未报工的\n比例', 'MES报工\n不及时率', 'QM外检\n业务不及\n时率', 'MM采购\n订单交货\n不及时的\n比例', 'MM手工\n创建采购\n订单比例', 'MM未清\n采购申请', 'FICO月度\n标准价格\n与周期单\n位价格综\n合差异率', '跨月生产\n订单比例', 'PM维修订\n单完工不\n及时率'],
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                    {
                        type: 'value',
                        name: '比例',
                        // interval: 5,
                        splitLine: { show: false },
                        axisLabel: {
                            formatter: '{value} %'
                        }
                    }
                ],
                series: [
                    {
                        name: '股份目标值',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.shareArray,
                    },

                    {
                        name: '盘锦目标值',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        // yAxisIndex: 1,
                        data: this.panJinArray,
                    },
                    {
                        name: '当月实际值',
                        type: 'line',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack2',
                        // yAxisIndex: 1,
                        data: this.valueArray,
                    },
                ]
            };
            this.option && this.myChart.setOption(this.option);
            // this.myChart.on('magictypechanged', (params) => {
            //     var magicType = params.currentType;
            //     if (magicType == 'line') {
            //         this.option.xAxis[0].boundaryGap = true;
            //         for (let i = 0; i < this.option.series.length; i++) {
            //             this.option.series[i].type = magicType;
            //         }
            //         this.myChart.setOption(this.option);
            //     }
            // });
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
                        type: 'shadow',
                    },

                },
                legend: {
                    type: 'scroll',
                    animationDurationUpdate: 0,
                    data: ['SD销售订单有效性考核得分', 'PP手工创建生产订单得分', 'PP生产订单已收货未报工得分', 'MES报工不及时得分', 'QM外检业务不及时得分', 'MM采购订单交货不及时得分', 'MM手工创建采购订单得分', 'MM未清采购申请得分', 'FICO月度标准价格与周期单位价格综合差异得分', '跨月生产订单得分', 'PM维修订单完工不及时得分']
                },
                toolbox: {
                    show: true,
                    orient: 'vertical',
                    left: 'right',
                    top: 'center',
                    feature: {
                        mark: { show: true, },
                        dataView: { show: true, readOnly: false, title: '数据视图' },
                        // magicType: { show: true, type: ['bar', 'line', 'stack'], title: { bar: '切换为柱状图', line: '切换为折线图', stack: '切换为堆叠图' } },
                        // restore: { show: true, title: '还原' },
                        saveAsImage: { show: true, title: '保存为图片' }
                    }
                },
                xAxis: [
                    {
                        // type: 'category',
                        axisTick: { show: false },
                        data: this.actualScore.map(item => moment(item.yearAndMonth).format('YY-MM')),
                    }
                ],
                yAxis: [
                    {
                        type: 'value'
                    },
                ],
                series: [
                    {
                        name: 'SD销售订单有效性考核得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.sdSalesordervalidity),
                    },
                    {
                        name: 'PP手工创建生产订单得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.ppManualpocreationratio),
                    },
                    {
                        name: 'PP生产订单已收货未报工得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.ppDeliveredunreportedratio),
                    },
                    {
                        name: 'MES报工不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.mesLateworkreportingrate),
                    },
                    {
                        name: 'QM外检业务不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.qmExternalinspectiondelay),
                    },
                    {
                        name: 'MM采购订单交货不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.mmPurchaseorderlatedelivery),
                    },
                    {
                        name: 'MM手工创建采购订单得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.mmManualpocreation),
                    },
                    {
                        name: 'MM未清采购申请得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.mmUnsettledpurchaserequests),
                    },
                    {
                        name: 'FICO月度标准价格与周期单位价格综合差异得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.ficoMonthlystandardpricevariation),
                    },
                    {
                        name: '跨月生产订单得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.crossMonthProductionOrders),
                    },
                    {
                        name: 'PM维修订单完工不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        // stack: 'stack1',
                        data: this.actualScore.map(item => item.pmLatemaintenanceordercompletion),
                    },
                ]
            };
            this.option2 && this.myChart2.setOption(this.option2);
            // this.myChart.on('magictypechanged', (params) => {
            //     var magicType = params.currentType;
            //     if (magicType == 'line') {
            //         this.option.xAxis[0].boundaryGap = true;
            //         for (let i = 0; i < this.option.series.length; i++) {
            //             this.option.series[i].type = magicType;
            //         }
            //         this.myChart.setOption(this.option);
            //     }
            // });
        },
        defaultMonth() {
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

.chart-title {
    position: absolute;
    left: 28%;
    /* 水平位置，基于容器的% */
    // top: 50%;
    /* 垂直位置，基于容器的% */
    transform: translate(-50%, -50%);
    /* 使用transform来真正居中文本 */
    margin-bottom: 10px;
    /* 为标题添加底部边距 */
    font-size: 14px;
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

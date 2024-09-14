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
        <div class="chart-title">
            <br>
            <br>
            <h2>SAP管理指标</h2>
        </div>
        <div id="main" ref="main"></div>
    </div>
</template>

<script>
import * as echarts from 'echarts';
import moment from 'moment';
import { getManagementData } from '@/api/enterprise/chartAPI'
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
            parsedData: {},
            targetData: [],
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
        this.initData()

    },
    methods: {
        async initData() {
            this.timeData.startTime = this.selectedDate[0],
                this.timeData.endTime = this.selectedDate[1]
            try {

                this.loading = true
                const res = await getManagementData(this.timeData);
                this.data = res.rows
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
                position: 'inside',
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
                fontSize: 12,
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
                    data: ['SD销售订单有效性考核得分', 'SD销售订单有效性考核', 'PP手工创建生产订单得分', 'PP手工创建生产订单比例(%)', 'PP生产订单已收货未报工得分', 'PP生产订单已收货未报工的比例(%)', 'MES报工不及时得分', 'MES报工不及时率比率(%)', 'QM外检业务不及时得分', 'QM外检业务不及时率(%)', 'MM采购订单交货不及时得分', 'MM采购订单交货不及时的比例(%)', 'MM手工创建采购订单得分', 'MM手工创建采购订单比例(%)', 'MM未清采购申请得分', 'MM未清采购申请', 'FICO月度标准价格与周期单位价格综合差异得分', 'FICO月度标准价格与周期单位价格综合差异率(%)', '跨月生产订单得分', '跨月生产订单比例(%)', 'PM维修订单完工不及时得分', 'PM维修订单完工不及时率(%)']
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
                        data: this.data.map(item => moment(item.yearAndMonth).format('YY-MM')),
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
                        name: 'SD销售订单有效性考核得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.sdSalesordervalidityscore),
                    },
                    {
                        name: 'SD销售订单有效性考核',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.sdSalesordervalidity),
                    },
                    {
                        name: 'PP手工创建生产订单得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.ppManualpocreationratioscore),
                    },
                    {
                        name: 'PP手工创建生产订单比例(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.ppManualpocreationratio),
                    },
                    {
                        name: 'PP生产订单已收货未报工得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.ppDeliveredunreportedratioscore),
                    },
                    {
                        name: 'PP生产订单已收货未报工的比例(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.ppDeliveredunreportedratio),
                    },
                    {
                        name: 'MES报工不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.mesLateworkreportingscore),
                    },
                    {
                        name: 'MES报工不及时率比率(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.mesLateworkreportingrate),
                    },
                    {
                        name: 'QM外检业务不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.qmExternalinspectiondelayscore),
                    },
                    {
                        name: 'QM外检业务不及时率(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.qmExternalinspectiondelay),
                    },
                    {
                        name: 'MM采购订单交货不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.mmPurchaseorderlatedeliveryscore),
                    },
                    {
                        name: 'MM采购订单交货不及时的比例(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.mmPurchaseorderlatedelivery),
                    },
                    {
                        name: 'MM手工创建采购订单得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.mmManualpocreationscore),
                    },
                    {
                        name: 'MM手工创建采购订单比例(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.mmManualpocreation),
                    },
                    {
                        name: 'MM未清采购申请得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.mmUnsettledpurchaserequestsscore),
                    },
                    {
                        name: 'MM未清采购申请',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.mmUnsettledpurchaserequests),
                    },
                    {
                        name: 'FICO月度标准价格与周期单位价格综合差异得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.ficoMonthlystandardpricevariationscore),
                    },
                    {
                        name: 'FICO月度标准价格与周期单位价格综合差异率(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.ficoMonthlystandardpricevariation),
                    },
                    {
                        name: '跨月生产订单得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.crossMonthProductionOrdersScore),
                    },
                    {
                        name: '跨月生产订单比例(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.crossMonthProductionOrders),
                    },
                    {
                        name: 'PM维修订单完工不及时得分',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack1',
                        data: this.data.map(item => item.pmLatemaintenanceordercompletionscore),
                    },
                    {
                        name: 'PM维修订单完工不及时率(%)',
                        type: 'bar',
                        label: labelOption,
                        emphasis: {
                            focus: 'series'
                        },
                        stack: 'stack2',
                        yAxisIndex: 1,
                        data: this.data.map(item => item.pmLatemaintenanceordercompletion),
                    }
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
        },
        defaultMonth() {
            const currentDate = new Date();
            const currentYear = currentDate.getFullYear();
            const currentMonth = currentDate.getMonth();
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

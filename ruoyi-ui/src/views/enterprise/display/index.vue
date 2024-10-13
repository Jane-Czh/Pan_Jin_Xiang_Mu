<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="全部" name="first">
        <div class="allIndex">
          <div v-for="(item, index) in allIndex" :key="index" class="allIndex-item">
            <div class="allIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>
                  {{ item.title }}
                  <span v-if="item.showWarning">
                    <i class="el-icon-warning-outline" style="font-size: 20px; color: red;"></i>
                  </span>
                </h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="allIndex-item__footer">
              <div class="footer-content">
                <!-- <span v-if="item.id === '102'" style="font-size: 16px;">{{ item.date }}</span> -->
                <span style="font-size: 16px;">截至{{ item.date }}</span>
                <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
import { getTargetData } from '@/api/financial/target'
import { getSalaryNewData, getMonthlyNewData } from '@/api/enterprise/chartAPI'
import moment from 'moment'
export default {
  components: {},
  props: ['option'],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '49', showTarget: 'enterprise', extra: 'month', type: '人', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'employeesNumber', icon: 'el-icon-s-data', title: '一线从业人数', apiName: 'getEmployeesNumberData', yDataName: 'employeesNumber', dataName: '人数', content: '一线从业人数', },
        { id: '51', showTarget: 'enterprise', extra: 'month', type: '人', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'employeesAvgMonthlyNumber', icon: 'el-icon-s-data', title: '公司平均从业人数（月度）', apiName: 'getEmployeesAVGMonthlyNumberData', yDataName: 'employeesAVGMonthlyNumber', dataName: '人数', content: '公司平均从业人数（月度）', },
        { id: '100', showTarget: 'enterprise', extra: 'month', type: '人', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'employeesAvgAnnualNumber', icon: 'el-icon-s-data', title: '公司平均从业人数（年度）', apiName: 'getEmployeesAVGAnnualNumberData', yDataName: 'employeesAVGAnnualNumber', dataName: '人数', content: '公司平均从业人数（年度）', },
        { id: '52', showTarget: 'enterprise', extra: 'salary', type: '%', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'annualSalaryRatio', icon: 'el-icon-s-data', title: '工资总额年度占比', apiName: 'getAnnualSalaryRatioData', yDataName: 'annualSalaryRatio', dataName: '占比', content: '工资总额年度占比', },
        { id: 54, showTarget: 'enterprise', extra: 'salary', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'cumulativeAverageIncome', icon: 'el-icon-s-data', title: '累计人均收入', apiName: 'getCumulativeAverageIncomeData', yDataName: 'cumulativeAverageIncome', dataName: '金额', content: '累计人均收入', },
        { id: 55, showTarget: 'enterprise', extra: 'salary', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'productionAvgIncome', icon: 'el-icon-s-data', title: '月度累计生产人均收入', apiName: 'getMonthlyProductionAVGIncomeData', yDataName: 'monthlyProductionAVGIncome', dataName: '金额', content: '月度累计生产人均收入', },
        { id: 56, showTarget: 'enterprise', extra: 'salary', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'functionalAvgIncome', icon: 'el-icon-s-data', title: '月度累计职能人均收入', apiName: 'getMonthlyFunctionalAVGIncomeData', yDataName: 'monthlyFunctionalAVGIncome', dataName: '金额', content: '月度累计职能人均收入', },
        { id: '76', showTarget: 'enterprise', extra: 'salary', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'functionalDeptOvertimeCost', icon: 'el-icon-s-data', title: '职能部门人均加班费用', apiName: 'getFunctionalDeptOvertimeCostData', yDataName: 'functionalDeptOvertimeCost', dataName: '金额', content: '职能部门人均加班费用', },
        { id: 101, showTarget: 'enterprise', extra: 'month', type: '', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'management', icon: 'el-icon-s-data', title: 'SAP管理指标', apiName: 'getManagementData', yDataName: 'Total_Sales_Revenue', dataName: '得分', content: 'SAP管理指标', },
        { id: 53, showTarget: 'enterprise', extra: 'month', type: '', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'functionalDeptOvertimeCost', icon: 'el-icon-s-data', title: '日清日结指标', apiName: 'getFunctionalDeptOvertimeCostData', yDataName: 'Total_Sales_Revenue', dataName: '得分', content: '日清日结指标', },
      ],
      formData: {},
      rules: {},
      allTargetData: {},
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
    this.init();
  },
  methods: {
    async init() {
      if (!this.$auth.hasPermi("enterprise:index")) {
        this.allIndex = [
          { id: '49', showTarget: 'enterprise', extra: 'month', type: '人', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'employeesNumber', icon: 'el-icon-s-data', title: '一线从业人数', apiName: 'getEmployeesNumberData', yDataName: 'employeesNumber', dataName: '人数', content: '一线从业人数', },
          { id: '51', showTarget: 'enterprise', extra: 'month', type: '人', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'employeesAVGMonthlyNumber', icon: 'el-icon-s-data', title: '公司平均从业人数（月度）', apiName: 'getEmployeesAVGMonthlyNumberData', yDataName: 'employeesAVGMonthlyNumber', dataName: '人数', content: '公司平均从业人数（月度）', },
          { id: 101, showTarget: 'enterprise', extra: 'month', type: '', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'management', icon: 'el-icon-s-data', title: 'SAP管理指标', apiName: 'getManagementData', yDataName: 'Total_Sales_Revenue', dataName: '得分', content: 'SAP管理指标', },
          { id: 53, showTarget: 'enterprise', extra: 'month', type: '', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'functionalDeptOvertimeCost', icon: 'el-icon-s-data', title: '日清日结指标', apiName: 'getFunctionalDeptOvertimeCostData', yDataName: 'Total_Sales_Revenue', dataName: '得分', content: '日清日结指标', },
        ]
      }

      const res1 = await getSalaryNewData()
      const salaryRes = res1.data

      const res2 = await getMonthlyNewData()
      const monthlyRes = res2.data
      // console.log(salaryRes)

      //页面截至
      this.allIndex.forEach(item => {
        if (this.$auth.hasPermi("enterprise:index") && item.extra === 'salary') {
          item.date = salaryRes['yearAndMonth'] ? moment(salaryRes['yearAndMonth']).format('YYYY-MM') : '—';
        } else {
          item.date = monthlyRes['yearAndMonth'] ? moment(monthlyRes['yearAndMonth']).format('YYYY-MM') : '—';
        }
      });
      //卡片content
      this.allIndex.forEach(item => {
        const key = item.sum;
        // if (item.id != '100') {
        if (this.$auth.hasPermi("enterprise:index") && item.extra === 'salary') {
          item.content = `最新一月：${salaryRes[key] || '--'} (${item.type})`;
        } else {
          if (item.id == '100') {
            item.content = `最新数据：${monthlyRes[key] || '--'} (${item.type})`;

          } else if (item.id !== 53 && item.id !== 101) {
            item.content = `最新一月：${monthlyRes[key] || '--'} (${item.type})`;
          }
        }
        // }
      });
      if (!this.$auth.hasPermi("enterprise:index")) {
        this.allTargetData = {
          ...monthlyRes,
        }
      } else {
        this.allTargetData = {
          ...salaryRes,
          employeesAvgAnnualNumber: monthlyRes.employeesAvgAnnualNumber,
          employeesAvgMonthlyNumber: monthlyRes.employeesAvgMonthlyNumber,
          employeesNumber: monthlyRes.employeesNumber,
        }
      }
      console.log(this.allTargetData)


      let targetByDeptParam = {
        date: new Date(),
        deptName: 'enterprise',
      }
      const resTarget = await getTargetData(targetByDeptParam)

      //目标值赋予及上下限预警
      this.allIndex.forEach(item => {
        resTarget.rows.forEach(row => {
          if (item.sum === row.indicatorName) {
            item.targetValue = row.targetValue;
            item.targetValueDate = row.natureYear ? moment(row.natureYear).format('YYYY') : '—';
            const newDate = monthlyRes['yearAndMonth'] ? moment(monthlyRes['yearAndMonth']).format('YYYY') : '--';
            if (newDate == item.targetValueDate) {
              if (row.targetLowerLimit != null || row.targetUpperLimit != null) {
                if (this.allTargetData[item.sum] < row.targetLowerLimit || this.allTargetData[item.sum] > row.targetUpperLimit) {
                  item.showWarning = true;
                }
              }
            }

          }
        });
      });
    },
    toDetail(item) {
      if (item.id === 54 || item.id === 55 || item.id === 56 || item.id === 53 || item.id === 101) {
        this.$router.push({
          path: `/enterprise/indicators${item.id}`,
          query: {
            data: JSON.stringify(item)
          }
        }
        )
      } else {
        this.$router.push({
          path: '/enterprise/index-detail-enterprise',
          query: {
            data: JSON.stringify(item)
          }
        })
      }
    }
  }
}

</script>
<style lang="scss" scoped>
.current-page {
  padding: 16px;
}

.allIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .allIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .allIndex-item__body,
  .allIndex-item__footer {
    padding: 16px;
  }

  .allIndex-item__body {
    display: flex;
  }

  .allIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .allIndex-item__body i {
    font-size: 30px;
  }

  .allIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .allIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;
  }


  .footer-content {
    display: flex;
    justify-content: space-between;
    /* 文本靠左，按钮靠右 */
    align-items: center;
  }
}
</style>

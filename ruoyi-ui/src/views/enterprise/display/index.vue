<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="全部" name="first">
        <div class="allIndex">
          <div v-for="(item, index) in allIndex" :key="index" class="allIndex-item">
            <div class="allIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="allIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>


  </div>

</template>

<script>

export default {
  components: {},
  props: ['option'],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '49', icon: 'el-icon-s-data', title: '一线从业人数', apiName: 'getEmployeesNumberData', yDataName: 'employeesNumber', dataName: '人数', content: '一线从业人数', },
        { id: '51', icon: 'el-icon-s-data', title: '公司平均从业人数（月度）', apiName: 'getEmployeesAVGMonthlyNumberData', yDataName: 'employeesAVGMonthlyNumber', dataName: '人数', content: '公司平均从业人数（月度）', },
        { id: '52', icon: 'el-icon-s-data', title: '工资总额年度占比', apiName: 'getAnnualSalaryRatioData', yDataName: 'annualSalaryRatio', dataName: '占比', content: '工资总额年度占比', },
        // { id: '53', icon: 'el-icon-s-data', title: '日清日结考核得分', apiName: 'getMainRevenueData',yDataName: 'Total_Sales_Revenue',dataName: '金额',content: '企业主要营业产品财务收入', },
        { id: '54', icon: 'el-icon-s-data', title: '累计人均收入', apiName: 'getCumulativeAverageIncomeData', yDataName: 'cumulativeAverageIncome', dataName: '金额', content: '累计人均收入', },
        { id: '55', icon: 'el-icon-s-data', title: '月度累计生产人均收入', apiName: 'getMonthlyProductionAVGIncomeData', yDataName: 'monthlyProductionAVGIncome', dataName: '金额', content: '月度累计生产人均收入', },
        { id: '56', icon: 'el-icon-s-data', title: '月度累计职能人均收入', apiName: 'getMonthlyFunctionalAVGIncomeData', yDataName: 'monthlyFunctionalAVGIncome', dataName: '金额', content: '月度累计职能人均收入', },
        { id: '76', icon: 'el-icon-s-data', title: '职能部门人均加班费用', apiName: 'getFunctionalDeptOvertimeCostData', yDataName: 'functionalDeptOvertimeCost', dataName: '金额', content: '职能部门人均加班费用', },
        { id: '100', icon: 'el-icon-s-data', title: '公司平均从业人数（年度）', apiName: 'getEmployeesAVGAnnualNumberData', yDataName: 'employeesAVGAnnualNumber', dataName: '人数', content: '公司平均从业人数（年度）', },
        //   { id: '101', icon: 'el-icon-s-data', title: '11项管理指标-单个指标得分', apiName: 'getMainRevenueData',yDataName: 'Total_Sales_Revenue',dataName: '得分',content: '企业主要营业产品财务收入', },
      ],

      formData: {},
      rules: {},
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() { },
  methods: {
    toDetail(item) {
      // if (item.id === '30') {
      //   this.$router.push('/financial/indicators30')
      // }
      // else if (item.id === '70') {
      //   this.$router.push('/financial/indicators70')
      // }
      this.$router.push({
        path: '/enterprise/index-detail-enterprise',
        query: {
          data: JSON.stringify(item)
        }
      })


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
}
</style>

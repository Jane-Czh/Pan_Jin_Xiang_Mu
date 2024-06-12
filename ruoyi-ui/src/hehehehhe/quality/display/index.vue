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
        { id: '57', apiName: 'getWarrantyRepairRateData', yDataName: 'warrantyRepairRate', dataName: '返修率', icon: 'el-icon-s-data', title: '三包期内新车返修率', content: '企业主要营业产品财务收入' },
        { id: '58', apiName: 'getWarrantyVehicleRepairRateData', yDataName: 'warrantyVehicleRepairRate', dataName: '返修率', icon: 'el-icon-s-data', title: '三包期内整车月度返修率', content: '企业主要营业产品财务收入' },
        { id: '59', apiName: 'getExternalLossRateData', yDataName: 'externalLossRate', dataName: '损失率', icon: 'el-icon-s-data', title: '外部质量损失率', content: '企业主要营业产品财务收入' },
        { id: '61', apiName: 'getMonthlyAfterSalesIssuesData', yDataName: 'monthlyAfterSalesIssues', dataName: '总数', icon: 'el-icon-s-data', title: '月度售后质量问题总数', content: '企业主要营业产品财务收入' },
        { id: '62', apiName: 'getProductionLiabilityIssuesData', yDataName: 'productionLiabilityIssues', dataName: '次数', icon: 'el-icon-s-data', title: '售后问题生产责任次数', content: '企业主要营业产品财务收入' },
        { id: '63', apiName: 'getQuarterlyRankData', yDataName: 'quarterlyRank', dataName: '排名', icon: 'el-icon-s-data', title: '质量考核季度排名', content: '企业主要营业产品财务收入' },
        { id: '64', apiName: 'getMeantimeWithoutFailureData', yDataName: 'meantimeWithoutFailure', dataName: '时间', icon: 'el-icon-s-data', title: '平均无故障时间', content: '企业主要营业产品财务收入' },
        { id: '102', apiName: 'getSingleInspectionPassRateData', yDataName: 'singleInspectionPassRate', dataName: '合格率', con: 'el-icon-s-data', title: '电车、大吨位一次交检合格率', content: '企业主要营业产品财务收入' },
        { id: '103', apiName: 'getInTimeReturnRateData', yDataName: 'inTimeReturnRate', dataName: '及时率', icon: 'el-icon-s-data', title: '供应商不合格件返厂及时率', content: '企业主要营业产品财务收入' },
        { id: '104', apiName: 'getPartQualificationRateData', yDataName: 'partQualificationRate', dataName: '合格率', icon: 'el-icon-s-data', title: '班组自查合格率与下道工序反馈合格率', content: '企业主要营业产品财务收入' },
        // { id: '105', apiName: 'getcurNonBomMaterialCostData', yDataName: 'curNonBomMaterialCost', dataName: '合格率', icon: 'el-icon-s-data', title: '下道工序反馈合格率', content: '企业主要营业产品财务收入' },
      ],

      formData: {
      },
      rules: {
      },
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() { },
  methods: {
    toDetail(item) {
      //指标104+105同一个页面
      if (item.id === '104') {
        this.$router.push('/quality/indicators104')
      }
      else {
        this.$router.push({
          path: '/quality/index-detail-quality',
          query: {
            data: JSON.stringify(item),
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

.enterpriseOperatingIndicators {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .enterpriseOperatingIndicators-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .enterpriseOperatingIndicators-item__body,
  .enterpriseOperatingIndicators-item__footer {
    padding: 16px;
  }

  .enterpriseOperatingIndicators-item__body {
    display: flex;
  }

  .enterpriseOperatingIndicators-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .enterpriseOperatingIndicators-item__body i {
    font-size: 30px;
  }

  .enterpriseOperatingIndicators-item__body i+.main-content {
    margin-left: 10px;
  }

  .enterpriseOperatingIndicators-item__footer {
    border-top: 1px solid #eee;
    text-align: right;
  }
}

.costIndicators {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .costIndicators-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .costIndicators-item__body,
  .costIndicators-item__footer {
    padding: 16px;
  }

  .costIndicators-item__body {
    display: flex;
  }

  .costIndicators-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .costIndicators-item__body i {
    font-size: 30px;
  }

  .costIndicators-item__body i+.main-content {
    margin-left: 10px;
  }

  .costIndicators-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }
}

.efficiencyIndicators {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .efficiencyIndicators-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .efficiencyIndicators-item__body,
  .efficiencyIndicators-item__footer {
    padding: 16px;
  }

  .efficiencyIndicators-item__body {
    display: flex;
  }

  .efficiencyIndicators-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .efficiencyIndicators-item__body i {
    font-size: 30px;
  }

  .efficiencyIndicators-item__body i+.main-content {
    margin-left: 10px;
  }

  .efficiencyIndicators-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }
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

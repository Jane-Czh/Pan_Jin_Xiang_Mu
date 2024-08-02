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
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>


  </div>

</template>

<script>
import { getTargetData } from '@/api/financial/target'

export default {

  components: {},
  props: ['option'],
  data() {
    return {
      date: {
        startTime: new Date(),
        endTime: new Date(),
      },
      activeName: 'first',
      allIndex: [
        { id: '57', showWarning: false, targetValue: '', apiName: 'getWarrantyRepairRateData', sum: 'warrantyRepairRate', yDataName: 'warrantyRepairRate', dataName: '返修率', icon: 'el-icon-s-data', title: '三包期内新车返修率', content: '三包期内新车返修率' },
        { id: '58', showWarning: false, targetValue: '', apiName: 'getWarrantyVehicleRepairRateData', sum: 'warrantyVehicleRepairRate', yDataName: 'warrantyVehicleRepairRate', dataName: '返修率', icon: 'el-icon-s-data', title: '三包期内整车月度返修率', content: '三包期内整车月度返修率' },
        { id: '59', showWarning: false, targetValue: '', apiName: 'getExternalLossRateData', sum: 'externalLossRate', yDataName: 'externalLossRate', dataName: '损失率', icon: 'el-icon-s-data', title: '外部质量损失率', content: '外部质量损失率' },
        { id: '61', showWarning: false, targetValue: '', apiName: 'getMonthlyAfterSalesIssuesData', sum: 'monthlyAfterSalesIssues', yDataName: 'monthlyAfterSalesIssues', dataName: '总数', icon: 'el-icon-s-data', title: '月度售后质量问题总数', content: '月度售后质量问题总数' },
        { id: '62', showWarning: false, targetValue: '', apiName: 'getProductionLiabilityIssuesData', sum: 'productionLiabilityIssues', yDataName: 'productionLiabilityIssues', dataName: '次数', icon: 'el-icon-s-data', title: '售后问题生产责任次数', content: '售后问题生产责任次数' },
        { id: '63', showWarning: false, targetValue: '', apiName: 'getQuarterlyRankData', sum: 'quarterlyRank', yDataName: 'quarterlyRank', dataName: '排名', icon: 'el-icon-s-data', title: '质量考核季度排名', content: '质量考核季度排名' },
        { id: '64', showWarning: false, targetValue: '', apiName: 'getMeantimeWithoutFailureData', sum: 'meantimeWithoutFailure', yDataName: 'meantimeWithoutFailure', dataName: '时间', icon: 'el-icon-s-data', title: '平均无故障时间', content: '平均无故障时间' },
        { id: '102', showWarning: false, targetValue: '', apiName: 'getSingleInspectionPassRateData', sum: 'singleInspectionPassRate', yDataName: 'singleInspectionPassRate', dataName: '合格率', con: 'el-icon-s-data', title: '电车、大吨位一次交检合格率', content: '电车、大吨位一次交检合格率' },
        { id: '103', showWarning: false, targetValue: '', apiName: 'getInTimeReturnRateData', sum: 'inTimeReturnRate', yDataName: 'inTimeReturnRate', dataName: '及时率', icon: 'el-icon-s-data', title: '供应商不合格件返厂及时率', content: '供应商不合格件返厂及时率' },
        { id: '104', showWarning: false, targetValue: '', apiName: 'getPartQualificationRateData', sum: 'partQualificationRate', yDataName: 'partQualificationRate', dataName: '合格率', icon: 'el-icon-s-data', title: '班组自查合格率与下道工序反馈合格率', content: '班组自查合格率与下道工序反馈合格率' },
        // 与上个指标合并
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
    this.initData();
  },
  mounted() { },
  methods: {
    async initData() {
      try {
        let target = {
          date: new Date(),
          deptName: 'quality',
        }
        // 定义一个空对象来存储所有数据
        let allData = {};
        // 发起并行请求
        const [res1] = await Promise.all([
          getTargetData(target)
        ].map(p => p.catch(e => {
          console.error('API 请求失败:', e);
          return null; // 返回 null 或其他默认值
        }))
        );

        //页面截至
        // this.allIndex.forEach(item => {
        //   if (item.kind === 'balance') {
        //     item.date = res1 && res1.data ? moment(res1.data.yearAndMonth).format('YYYY-MM') : '';
        //   } else if (item.kind === 'interests') {
        //     item.date = res2 && res2.data ? moment(res2.data.yearAndMonth).format('YYYY-MM') : '';
        //   } else if (item.kind === 'day') {
        //     item.date = res6 && res6.data ? res6.data.dataTime : '';
        //   } else {
        //     item.date = res3 && res3.data ? moment(res3.data.yearAndMonth).format('YYYY-MM') : '';
        //   }
        // });
        // this.allData.yearAndMonth = moment(res1.data.yearAndMonth).format('YYYY-MM') || '';
        // 更新 allIndex 数组中每个元素的 content 字段

        this.allIndex.forEach(item => {
          const key = item.sum;
          if (allData[key] !== undefined) {
            item.content = `总计：${allData[key] || ''}`;
          } else {
            console.warn(`Key ${key} not found in response data.`);
          }
        });

        //警告图标
        this.allIndex.forEach(item => {
          res1.rows.forEach(row => {
            if (item.sum === row.indicatorName) {
              item.targetValue = row.targetValue;
              if (allData[item.sum] < row.targetLowerLimit || allData[item.sum] > row.targetUpperLimit) {
                item.showWarning = true;
              }
            }
          });
        });
        // 返回整合后的数据对象
      } catch (error) {
        console.error('初始化数据失败:', error);
      }
    },

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

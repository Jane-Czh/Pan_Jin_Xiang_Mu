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
import { getMonthNewData, getAfterNewData, getInspectionNewData } from '@/api/quality/chartAPI'
import moment from 'moment'

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
        { id: '57', kind: 'after', date: '', showWarning: false, targetValue: '', apiName: 'getWarrantyRepairRateData', sum: 'warrantyRepairRate', yDataName: 'warrantyRepairRate', dataName: '返修率', icon: 'el-icon-s-data', title: '三包期内新车返修率', content: '' },
        { id: '58', kind: 'after', date: '', showWarning: false, targetValue: '', apiName: 'getWarrantyVehicleRepairRateData', sum: 'warrantyVehicleRepairRate', yDataName: 'warrantyVehicleRepairRate', dataName: '返修率', icon: 'el-icon-s-data', title: '三包期内整车月度返修率', content: '' },
        { id: '59', kind: 'after', date: '', showWarning: false, targetValue: '', apiName: 'getExternalLossRateData', sum: 'externalLossRate', yDataName: 'externalLossRate', dataName: '损失率', icon: 'el-icon-s-data', title: '外部质量损失率', content: '' },
        { id: '61', kind: 'after', date: '', showWarning: false, targetValue: '', apiName: 'getMonthlyAfterSalesIssuesData', sum: 'monthlyAfterSalesIssues', yDataName: 'monthlyAfterSalesIssues', dataName: '总数', icon: 'el-icon-s-data', title: '月度售后质量问题总数', content: '' },
        { id: '62', kind: 'after', date: '', showWarning: false, targetValue: '', apiName: 'getProductionLiabilityIssuesData', sum: 'productionLiabilityIssues', yDataName: 'productionLiabilityIssues', dataName: '次数', icon: 'el-icon-s-data', title: '售后问题生产责任次数', content: '' },
        { id: '63', kind: 'month', date: '', showWarning: false, targetValue: '', apiName: 'getQuarterlyRankData', sum: 'quarterlyRank', yDataName: 'quarterlyRank', dataName: '排名', icon: 'el-icon-s-data', title: '质量考核季度排名', content: '' },
        { id: '64', kind: 'month', date: '', showWarning: false, targetValue: '', apiName: 'getMeantimeWithoutFailureData', sum: 'meantimeWithoutFailure', yDataName: 'meantimeWithoutFailure', dataName: '时间', icon: 'el-icon-s-data', title: '平均无故障时间', content: '' },
        { id: '102', kind: 'inspection', date: '', showWarning: false, targetValue: '', apiName: 'getSingleInspectionPassRateData', sum: 'singleInspectionPassRate', yDataName: 'singleInspectionPassRate', dataName: '合格率', con: 'el-icon-s-data', title: '电车、大吨位一次交检合格率', content: '' },
        { id: '103', kind: 'month', date: '', showWarning: false, targetValue: '', apiName: 'getInTimeReturnRateData', sum: 'intimeReturnrate', yDataName: 'inTimeReturnRate', dataName: '及时率', icon: 'el-icon-s-data', title: '供应商不合格件返厂及时率', content: '' },
        { id: '104', kind: 'month', date: '', showWarning: false, targetValue: '', apiName: 'getPartQualificationRateData', sum: 'selfcheckPassrate', sum2: 'nextprocessFeedbackPassrate', yDataName: 'partQualificationRate', dataName: '合格率', icon: 'el-icon-s-data', title: '班组自查合格率与下道工序反馈合格率', content: '' },
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
  mounted() {

  },
  methods: {
    async initData() {
      try {
        let target = {
          date: new Date(),
          // endTime: new Date(),
          deptName: 'quality',
        }
        // 定义一个空对象来存储所有数据
        let allData = {};
        // 发起并行请求
        const [resTargetData, resMonthNewData, resAfterNewData, resInspectionNewData] = await Promise.all([
          getTargetData(target),
          getMonthNewData(),
          getAfterNewData(),
          getInspectionNewData()
        ].map(p => p.catch(e => {
          console.error('API 请求失败:', e);
          return null; // 返回 null 或其他默认值
        }))
        );

        //月度最新数据
        const resMonthNewNeededFields = {
          quarterlyRank: resMonthNewData && resMonthNewData.data ? resMonthNewData.data.quarterlyRank : '',
          meantimeWithoutFailure: resMonthNewData && resMonthNewData.data ? resMonthNewData.data.meantimeWithoutFailure : '',
          intimeReturnrate: resMonthNewData && resMonthNewData.data ? resMonthNewData.data.intimeReturnrate : '',
          selfcheckPassrate: resMonthNewData && resMonthNewData.data ? resMonthNewData.data.selfcheckPassrate : '',
          nextprocessFeedbackPassrate: resMonthNewData && resMonthNewData.data ? resMonthNewData.data.nextprocessFeedbackPassrate : '',
        };

        //售后最新数据
        const resAfterNewNeededFields = {
          warrantyRepairRate: resAfterNewData && resAfterNewData.data ? resAfterNewData.data.warrantyRepairRate : '',
          monthlyAfterSalesIssues: resAfterNewData && resAfterNewData.data ? resAfterNewData.data.monthlyAfterSalesIssues : '',
          warrantyVehicleRepairRate: resAfterNewData && resAfterNewData.data ? resAfterNewData.data.warrantyVehicleRepairRate : '',
          externalLossRate: resAfterNewData && resAfterNewData.data ? resAfterNewData.data.externalLossRate : '',
          productionLiabilityIssues: resAfterNewData && resAfterNewData.data ? resAfterNewData.data.productionLiabilityIssues : '',
        };

        //质检最新数据
        const resInspectionNewNeededFields = {
          electricCarPassRate: resInspectionNewData && resInspectionNewData.data ? resInspectionNewData.data.electricCarPassRate : '',
          largeTonPassRate: resInspectionNewData && resInspectionNewData.data ? resInspectionNewData.data.largeTonPassRate : '',
          singleInspectionPassRate: resInspectionNewData && resInspectionNewData.data ? resInspectionNewData.data.singleInspectionPassRate : '',
        };

        //页面截至
        this.allIndex.forEach(item => {
          if (item.id === '102') {
            item.date = resInspectionNewData && resInspectionNewData.data ? moment(resInspectionNewData.data.yearAndMonth).format('YYYY-MM') : '';
          } else if (item.kind === 'month') {
            item.date = resMonthNewData && resMonthNewData.data ? moment(resMonthNewData.data.yearAndMonth).format('YYYY-MM') : '';
          }
          else {
            item.date = resAfterNewData && resAfterNewData.data ? moment(resAfterNewData.data.yearAndMonth).format('YYYY-MM') : '';
          }
        });
        // this.allData.yearAndMonth = moment(res1.data.yearAndMonth).format('YYYY-MM') || '';
        // 更新 allIndex 数组中每个元素的 content 字段

        allData = {
          ...resMonthNewNeededFields,
          ...resAfterNewNeededFields,
          ...resInspectionNewNeededFields,
        };

        this.allIndex.forEach(item => {
          const key = item.sum;
          if (allData[key] !== undefined) {
            item.content = `最新一月：${allData[key] || ''}`;
          } else {
            console.warn(`Key ${key} not found in response data.`);
          }
        });

        //警告图标
        this.allIndex.forEach(item => {
          resTargetData.rows.forEach(row => {
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
      } else if (item.id === '102') {
        this.$router.push('/quality/indicators102')
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

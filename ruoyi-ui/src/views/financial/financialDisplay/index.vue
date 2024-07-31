<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="企业运营指标" name="1">
        <div class="enterpriseOperatingIndicators">
          <div v-for="(item, index) in allIndex.filter(e => e.type === '1')" :key="index"
            class="enterpriseOperatingIndicators-item">
            <div class="enterpriseOperatingIndicators-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}
                  <span v-if="item.showWarning">
                    <i class="el-icon-warning-outline" style="font-size: 20px; color: red;"></i>
                  </span>
                </h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="enterpriseOperatingIndicators-item__footer">
              <div class="footer-content">
                <span style="font-size: 16px;">截至{{ item.date }}</span>
                <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="成本指标" name="2">
        <div class="costIndicators">
          <div v-for="(item, index) in  allIndex.filter(e => e.type === '2')" :key="index" class="costIndicators-item">
            <div class="costIndicators-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="costIndicators-item__footer">
              <div class="footer-content">
                <span style="font-size: 16px;">截至{{ item.date }}</span>
                <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="效率指标" name="3">
        <div class="efficiencyIndicators">
          <div v-for="(item, index) in  allIndex.filter(e => e.type === '3')" :key="index"
            class="efficiencyIndicators-item">
            <div class="efficiencyIndicators-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="efficiencyIndicators-item__footer">
              <div class="footer-content">
                <span style="font-size: 16px;">截至{{ item.date }}</span>
                <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="全部" name="0">
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
import { getBalanceSumData, getInterestSumData, getMonthSumData, getMonthRateData, getBalanceData, getDaySumData } from '@/api/financial/chartAPI'
import { getTargetData } from '@/api/financial/target'
import moment from 'moment'
export default {
  name: 'Index',
  components: {},
  props: ['option'],
  data() {
    return {
      date: {
        startTime: new Date(),
        endTime: new Date(),
      },
      balanceData: {},
      interestsData: {},
      monthData: {},
      allData: {
        yearAndMonth: ''
      },
      activeName: '1',
      // 企业运营指标: 1, 成本指标: 2, 效率指标: 3
      allIndex: [
        { id: '1', showWarning: false, targetValue: '', kind: 'interests', apiName: 'getMainRevenueData', date: '', yDataName: 'MainRevenue', type: '1', icon: 'el-icon-s-data', title: '主营业务收入', dataName: '金额', content: ``, sum: 'mainRevenue' },
        { id: '2', showWarning: false, targetValue: '', kind: 'month', apiName: 'getTotalSalesRevenueData', date: '', yDataName: 'Total_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '整机销售收入', dataName: '金额', content: '', sum: 'totalSalesRevenue' },
        { id: '3', showWarning: false, targetValue: '', kind: 'month', apiName: 'getExternalGroupSalesRevenueData', date: '', yDataName: 'External_Group_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '集团外部销售收入', dataName: '金额', content: '', sum: 'externalGroupSalesRevenue' },
        { id: '4', showWarning: false, targetValue: '', kind: 'month', apiName: 'getTotalVehicleProductionData', date: '', yDataName: 'Total_Vehicle_Production', type: '1', icon: 'el-icon-s-data', title: '整车产量', dataName: '金额', content: '', sum: 'totalVehicleProduction' },
        { id: '5', showWarning: false, targetValue: '', kind: 'month', apiName: 'getTotalVehicleSalesData', date: '', yDataName: 'Total_Vehicle_Sales', type: '1', icon: 'el-icon-s-data', title: '整车销量', dataName: '金额', content: '', sum: 'totalVehicleSales' },
        { id: '6', showWarning: false, targetValue: '', kind: 'month', apiName: 'getNewProductSalesRevenueData', date: '', yDataName: 'New_Product_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '新产品销售收入', dataName: '金额', content: '', sum: 'newProductSalesRevenue' },
        { id: '7', showWarning: false, targetValue: '', kind: 'month', apiName: 'getSpecialtyProductRevenueData', date: '', yDataName: 'Specialty_Product_Revenue', type: '1', icon: 'el-icon-s-data', title: '特色产品收入', dataName: '金额', content: '', sum: 'specialtyProductRevenue' },
        { id: '8', showWarning: false, targetValue: '', kind: 'interests', apiName: 'getCOGSData', date: '', yDataName: 'COGS', type: '1', icon: 'el-icon-s-data', title: '主营业务成本', dataName: '金额', content: '', sum: 'cogs' },
        { id: '9', showWarning: false, targetValue: '', kind: 'month', apiName: 'getTotalSalesCostData', date: '', yDataName: 'Total_Sales_Cost', type: '1', icon: 'el-icon-s-data', title: '整机销售成本', dataName: '金额', content: '', sum: 'totalSalesCost' },
        { id: '10', showWarning: false, targetValue: '', kind: 'interests', apiName: 'getNetProfitData', date: '', yDataName: 'NetProfit', type: '1', icon: 'el-icon-s-data', title: '净利润', dataName: '金额', content: '', sum: 'netProfit' },
        { id: '20', showWarning: false, targetValue: '', kind: 'interests', apiName: 'getManagementExpenseData', date: '', yDataName: 'ManagementExpense', type: '2', icon: 'el-icon-s-data', title: '当月管理费用', dataName: '金额', content: '', sum: 'managementExpense' },
        { id: '21', showWarning: false, targetValue: '', kind: 'interests', apiName: 'getRdExpenseData', date: '', yDataName: 'RD_Expense', type: '2', icon: 'el-icon-s-data', title: '当月研发费用', dataName: '金额', content: '', sum: 'rdExpense' },
        { id: '22', showWarning: false, targetValue: '', kind: 'month', apiName: 'getManufacturingExpensesMonthData', date: '', yDataName: 'Manufacturing_Expenses_Month', type: '2', icon: 'el-icon-s-data', title: '当月制造费用', dataName: '金额', content: '', sum: 'manufacturingExpensesMonth' },
        { id: '26', showWarning: false, targetValue: '', kind: 'balance', apiName: 'getMonthlyRawMaterialInventoryData', date: '', yDataName: 'MonthlyRawMaterialInventory', type: '2', icon: 'el-icon-s-data', title: '当月原材料存货额', dataName: '金额', content: '', sum: 'monthlyRawMaterialInventory' },
        { id: '27', showWarning: false, targetValue: '', kind: 'balance', apiName: 'getMonthlyWorkInProgressInventoryData', date: '', yDataName: 'MonthlyWorkInProgressInventory', type: '2', icon: 'el-icon-s-data', title: '当月在制品存货额', dataName: '金额', content: '', sum: 'monthlyWorkInProgressInventory' },
        { id: '28', showWarning: false, targetValue: '', kind: 'balance', apiName: 'getMonthAmountInStockData', date: '', yDataName: 'Month_Amount_InStock', type: '2', icon: 'el-icon-s-data', title: '当月库存商品存货额', dataName: '金额', content: '', sum: 'monthAmountInStock' },
        { id: '30', showWarning: false, targetValue: '', kind: 'balance', apiName: 'getGrowthRateInventoryAndSalesData', date: '', yDataName: 'GrowthRate_Sales', yDataName2: 'GrowthRate_Inventory', type: '2', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', dataName: '增长率', content: '', sum: 'growthRateSales', sum2: 'growthRateInventory' },
        { id: '32', showWarning: false, targetValue: '', kind: 'balance', apiName: 'getTurnoverRateReceivableData', date: '', yDataName: 'TurnoverRate_Receivable', type: '3', icon: 'el-icon-s-data', title: '应收账款周转率', dataName: '周转率', content: '', sum: 'turnoverRateReceivable' },
        { id: '33', showWarning: false, targetValue: '', kind: 'month', apiName: 'getCapitalTurnoverRateData', date: '', yDataName: 'Capital_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '当月资金周转率', dataName: '周转率', content: '', sum: 'capitalTurnoverRate' },
        { id: '34', showWarning: false, targetValue: '', kind: 'month', apiName: 'getInventoryTurnoverRateData', date: '', yDataName: 'Inventory_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '库存商品周转率', dataName: '周转率', content: '', sum: 'inventoryTurnoverRate' },
        { id: '35', showWarning: false, targetValue: '', kind: 'month', apiName: 'getRawMaterialTurnoverRateData', date: '', yDataName: 'Raw_Material_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '原材料周转率', dataName: '周转率', content: '', sum: 'rawMaterialTurnoverRate' },
        { id: '36', showWarning: false, targetValue: '', kind: 'month', apiName: 'getInprogressTurnoverRateData', date: '', yDataName: 'InProgress_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '当月在制品周转率', dataName: '周转率', content: '', sum: 'inprogressTurnoverRate' },
        { id: '66', showWarning: false, targetValue: '', kind: 'month', apiName: 'getLongEstimatedItemsData', date: '', yDataName: 'Long_Estimated_Items', type: '3', icon: 'el-icon-s-data', title: '一年以上暂估行项目', dataName: '项目', content: '', sum: 'longEstimatedItems' },
        { id: '70', showWarning: false, targetValue: '', kind: 'day', apiName: 'getInprogressDayrevenueData', date: '', yDataName: 'InProgress_DayRevenue', type: '2', icon: 'el-icon-s-data', title: '当日在制品金额', dataName: '金额', content: '', sum: 'inProgressDayRevenue' },
        { id: '77', showWarning: false, targetValue: '', kind: 'balance', apiName: 'getMonthlyInventoryTotalAmountData', date: '', yDataName: 'MonthlyInventoryTotalAmount', type: '2', icon: 'el-icon-s-data', title: '月度存货金额', dataName: '金额', content: '', sum: 'monthlyInventoryTotalAmount' },
        { id: '78', showWarning: false, targetValue: '', kind: 'month', apiName: 'getAddedValueMonthlyData', date: '', yDataName: 'Added_Value_Monthly', type: '1', icon: 'el-icon-s-data', title: '当月经济增加值', dataName: '金额', content: '', sum: 'addedValueMonthly' },
      ]
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
          deptName: 'financial',
        }
        // 定义一个空对象来存储所有数据
        let allData = {};
        // 发起并行请求
        const [res1, res2, res3, res4, res5, res6, res7] = await Promise.all([
          getBalanceSumData(this.date),
          getInterestSumData(this.date),
          getMonthSumData(this.date),
          getMonthRateData(this.date),
          getBalanceData(this.date),
          getDaySumData(this.date),
          getTargetData(target)
        ].map(p => p.catch(e => {
          console.error('API 请求失败:', e);
          return null; // 返回 null 或其他默认值
        }))
        );

        if (res1.data.growthRateSales) {
          delete res1.data.growthRateSales;
        }
        if (res1.data.growthRateInventory) {
          delete res1.data.growthRateInventory;
        }
        if (res1.data.turnoverRateReceivable) {
          delete res1.data.turnoverRateReceivable;
        }
        if (res3.data.capitalTurnoverRate) {
          delete res3.data.capitalTurnoverRate;
        }
        if (res3.data.inventoryTurnoverRate) {
          delete res3.data.inventoryTurnoverRate;
        }
        if (res3.data.rawMaterialTurnoverRate) {
          delete res3.data.rawMaterialTurnoverRate;
        }
        if (res3.data.inprogressTurnoverRate) {
          delete res3.data.inprogressTurnoverRate;
        }

        const res4DataNeededFields = {
          capitalTurnoverRate: res4.data.capitalTurnoverRate || '',
          inventoryTurnoverRate: res4.data.inventoryTurnoverRate || '',
          rawMaterialTurnoverRate: res4.data.rawMaterialTurnoverRate || '',
          inprogressTurnoverRate: res4.data.inprogressTurnoverRate || ''
        };

        const res5DataNeededFields = {
          growthRateInventory: res5.data.growthRateInventory || '',
          growthRateSales: res5.data.growthRateSales || '',
          turnoverRateReceivable: res5.data.turnoverRateReceivable || ''
        };
        const res6DataNeededFields = {
          dataTime: res6.data.dataTime || '',
          inProgressDayRevenue: res6.data.inProgressDayRevenue || 0
        };

        this.allIndex.forEach(item => {
          if (item.kind === 'balance') {
            item.date = moment(res1.data.yearAndMonth).format('YYYY-MM');
          } else if (item.kind === 'interests') {
            item.date = moment(res2.data.yearAndMonth).format('YYYY-MM');
          } else if (item.kind === 'day') {
            item.date = res6.data.dataTime;
          } else {
            item.date = moment(res3.data.yearAndMonth).format('YYYY-MM');
          }
        });
        allData = {
          ...res1.data,
          ...res2.data,
          ...res3.data,
          ...res4DataNeededFields,
          ...res5DataNeededFields,
          ...res6DataNeededFields,
        };
        this.balanceData = res1.data;
        this.interestsData = res2.data;
        this.monthData = res3.data;
        this.allData.yearAndMonth = moment(res1.data.yearAndMonth).format('YYYY-MM');
        // 更新 allIndex 数组中每个元素的 content 字段
        this.allIndex.forEach(item => {
          if (item.id === '30') {
            item.content = `销售增长率：${allData[item.sum] || ''}%\t\t存货增长率：${allData[item.sum2] || ''}%`;
          } else if (item.id === '70') {
            item.content = `当月总计：${allData[item.sum] || ''}\t(万元)`;
          } else {
            const key = item.sum;
            if (allData[key] !== undefined) {
              item.content = `全年总计：${allData[key] || ''}\t(万元)`;
            } else {
              console.warn(`Key ${key} not found in response data.`);
            }
          }
        });
        this.allIndex.forEach(item => {
          res7.rows.forEach(row => {
            if (item.sum === row.indicatorName) {
              item.targetValue = row.targetValue;
              if (allData[item.sum] < row.targetLowerLimit || allData[item.sum] > row.targetUpperLimit) {
                console.log(allData[item.sum]);
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
      if (item.id === '30') {
        this.$router.push('/financial/indicators30')
      }
      else if (item.id === '70') {
        this.$router.push('/financial/indicators70')
      }
      else {
        this.$router.push({
          path: '/financial/index-detail-financial',
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

  .enterpriseOperatingIndicators-item__body {
    padding: 16px;
  }

  .enterpriseOperatingIndicators-item__footer {
    padding: 10px;
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
    margin-left: 16px;
  }

  .enterpriseOperatingIndicators-item__footer {
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

.costIndicators {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .costIndicators-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .costIndicators-item__body {
    padding: 16px;
  }

  .costIndicators-item__footer {
    padding: 10px;
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
    margin-left: 16px;
  }

  .costIndicators-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }

  .footer-content {
    display: flex;
    /* 使用 Flexbox 布局 */
    justify-content: space-between;
    /* 让文本靠左，按钮靠右 */
    align-items: center;
    /* 垂直居中对齐 */
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

  .efficiencyIndicators-item__body {
    padding: 16px;
  }

  .efficiencyIndicators-item__footer {
    padding: 10px;
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
    margin-left: 16px;
  }

  .efficiencyIndicators-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }

  .footer-content {
    display: flex;
    /* 使用 Flexbox 布局 */
    justify-content: space-between;
    /* 让文本靠左，按钮靠右 */
    align-items: center;
    /* 垂直居中对齐 */
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

  .allIndex-item__body {
    padding: 16px;
  }

  .allIndex-item__footer {
    padding: 10px;
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
    margin-left: 16px;
  }

  .allIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;
  }

  .footer-content {
    display: flex;
    /* 使用 Flexbox 布局 */
    justify-content: space-between;
    /* 让文本靠左，按钮靠右 */
    align-items: center;
    /* 垂直居中对齐 */
  }
}
</style>

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
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="enterpriseOperatingIndicators-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
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
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
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
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
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
  name: 'Index',
  components: {},
  props: ['option'],
  data() {
    return {
      activeName: '1',
      // 企业运营指标: 1, 成本指标: 2, 效率指标: 3
      allIndex: [
        { id: '1', apiName: 'getMainRevenueData', yDataName: 'MainRevenue', type: '1', icon: 'el-icon-s-data', title: '主营业务收入', dataName: '金额', content: '企业主要营业产品财务收入' },
        { id: '2', apiName: 'getTotalSalesRevenueData', yDataName: 'Total_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '整机销售收入', dataName: '金额', content: '整机销售收入' },
        { id: '3', apiName: 'getExternalGroupSalesRevenueData', yDataName: 'External_Group_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '集团外部销售收入', dataName: '金额', content: '集团外部销售收入' },
        { id: '4', apiName: 'getTotalVehicleProductionData', yDataName: 'Total_Vehicle_Production', type: '1', icon: 'el-icon-s-data', title: '整车产量', dataName: '金额', content: '整车产量' },
        { id: '5', apiName: 'getTotalVehicleSalesData', yDataName: 'Total_Vehicle_Sales', type: '1', icon: 'el-icon-s-data', title: '整车销量', dataName: '金额', content: '整车销量' },
        { id: '6', apiName: 'getNewProductSalesRevenueData', yDataName: 'New_Product_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '新产品销售收入', dataName: '金额', content: '新产品销售收入' },
        { id: '7', apiName: 'getSpecialtyProductRevenueData', yDataName: 'Specialty_Product_Revenue', type: '1', icon: 'el-icon-s-data', title: '特色产品收入', dataName: '金额', content: '特色产品收入' },
        { id: '8', apiName: 'getCOGSData', yDataName: 'COGS', type: '1', icon: 'el-icon-s-data', title: '主营业务成本', dataName: '金额', content: '主营业务成本' },
        { id: '9', apiName: 'getTotalSalesCostData', yDataName: 'Total_Sales_Cost', type: '1', icon: 'el-icon-s-data', title: '整机销售成本', dataName: '金额', content: '整机销售成本' },
        { id: '10', apiName: 'getNetProfitData', yDataName: 'NetProfit', type: '1', icon: 'el-icon-s-data', title: '净利润', dataName: '金额', content: '净利润' },
        { id: '20', apiName: 'getManagementExpenseData', yDataName: 'ManagementExpense', type: '2', icon: 'el-icon-s-data', title: '当月管理费用', dataName: '金额', content: '当月管理费用' },
        { id: '21', apiName: 'getRdExpenseData', yDataName: 'RD_Expense', type: '2', icon: 'el-icon-s-data', title: '当月研发费用', dataName: '金额', content: '当月研发费用' },
        { id: '22', apiName: 'getManufacturingExpensesMonthData', yDataName: 'Manufacturing_Expenses_Month', type: '2', icon: 'el-icon-s-data', title: '当月制造费用', dataName: '金额', content: '当月制造费用' },
        { id: '26', apiName: 'getMonthlyRawMaterialInventoryData', yDataName: 'MonthlyRawMaterialInventory', type: '2', icon: 'el-icon-s-data', title: '当月原材料存货额', dataName: '金额', content: '当月原材料存货额' },
        { id: '27', apiName: 'getMonthlyWorkInProgressInventoryData', yDataName: 'MonthlyWorkInProgressInventory', type: '2', icon: 'el-icon-s-data', title: '当月在制品存货额', dataName: '金额', content: '当月在制品存货额' },
        { id: '28', apiName: 'getMonthAmountInStockData', yDataName: 'Month_Amount_InStock', type: '2', icon: 'el-icon-s-data', title: '当月库存商品存货额', dataName: '金额', content: '当月库存商品存货额' },
        { id: '30', apiName: 'getGrowthRateInventoryAndSalesData', yDataName: 'GrowthRate_Sales', yDataName2: 'GrowthRate_Inventory', type: '2', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', dataName: '增长率', content: '存货增长率和销售增长率', },
        { id: '32', apiName: 'getTurnoverRateReceivableData', yDataName: 'TurnoverRate_Receivable', type: '3', icon: 'el-icon-s-data', title: '应收账款周转率', dataName: '周转率', content: '应收账款周转率' },
        { id: '33', apiName: 'getCapitalTurnoverRateData', yDataName: 'Capital_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '资金周转率', dataName: '周转率', content: '资金周转率' },
        { id: '34', apiName: 'getInventoryTurnoverRateData', yDataName: 'Inventory_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '库存商品周转率', dataName: '周转率', content: '库存商品周转率' },
        { id: '35', apiName: 'getRawMaterialTurnoverRateData', yDataName: 'Raw_Material_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '原材料周转率', dataName: '周转率', content: '原材料周转率' },
        { id: '36', apiName: 'getInprogressTurnoverRateData', yDataName: 'InProgress_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '在制品周转率', dataName: '周转率', content: '在制品周转率' },
        { id: '66', apiName: 'getLongEstimatedItemsData', yDataName: 'Long_Estimated_Items', type: '3', icon: 'el-icon-s-data', title: '一年以上暂估行项目', dataName: '项目', content: '一年以上暂估行项目' },
        { id: '70', apiName: 'getInprogressDayrevenueData', yDataName: 'InProgress_DayRevenue', type: '2', icon: 'el-icon-s-data', title: '当日在制品金额', dataName: '金额', content: '当日在制品金额' },
        { id: '77', apiName: 'getMonthlyInventoryTotalAmountData', yDataName: 'MonthlyInventoryTotalAmount', type: '2', icon: 'el-icon-s-data', title: '月度存货金额', dataName: '金额', content: '月度存货金额' },
        { id: '78', apiName: 'getAddedValueMonthlyData', yDataName: 'Added_Value_Monthly', type: '1', icon: 'el-icon-s-data', title: '当月经济增加值', dataName: '金额', content: '当月经济增加值' },
      ]
    }
  },
  computed: {},
  watch: {},
  created() {
  },

  mounted() { },
  methods: {
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

<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="财务指标类" name="first">
        <div class="financialIndex">
          <div v-for="(item, index) in financialIndex" :key="index" class="financialIndex-item">
            <div class="financialIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="financialIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="销售指标类" name="second">
        <div class="SalesIndex">
          <div v-for="(item, index) in SalesIndex" :key="index" class="SalesIndex-item">
            <div class="SalesIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="SalesIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="经营指标类" name="third">
        <div class="OperatingEfficiencyIndex">
          <div v-for="(item, index) in OperatingEfficiencyIndex" :key="index" class="OperatingEfficiencyIndex-item">
            <div class="OperatingEfficiencyIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="OperatingEfficiencyIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="全部" name="fourth">
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
        { id: '1', icon: 'el-icon-s-data', title: '主营业务收入', content: '企业主要营业产品财务收入', path: '/financial/mainRevenue' },
        { id: '2', icon: 'el-icon-s-data', title: '整机销售收入', content: '企业主要营业产品财务收入', path: '/financial/totalSalesRevenue' },
        { id: '3', icon: 'el-icon-s-data', title: '集团外部销售收入', content: '企业主要营业产品财务收入', path: '/financial/externalGroupSalesRevenue' },
        { id: '4', icon: 'el-icon-s-data', title: '整车产量', content: '企业主要营业产品财务收入', path: '/financial/totalVehicleProduction' },
        { id: '5', icon: 'el-icon-s-data', title: '整车销量', content: '企业主要营业产品财务收入', path: '/financial/totalVehicleSales' },
        { id: '6', icon: 'el-icon-s-data', title: '新产品销售收入', content: '企业主要营业产品财务收入', path: '/financial/newProductSalesRevenue' },
        { id: '7', icon: 'el-icon-s-data', title: '特色产品收入', content: '企业主要营业产品财务收入', path: '/financial/specialtyProductRevenue' },
        { id: '8', icon: 'el-icon-s-data', title: '主营业务成本', content: '企业主要营业产品财务收入', path: '/financial/COGS' },
        { id: '9', icon: 'el-icon-s-data', title: '整机销售成本', content: '企业主要营业产品财务收入', path: '/financial/totalSalesCost' },
        { id: '10', icon: 'el-icon-s-data', title: '净利润', content: '企业主要营业产品财务收入', path: '/financial/netProfit' },
        { id: '20', icon: 'el-icon-s-data', title: '当月管理费用', content: '企业主要营业产品财务收入', path: '/financial/managementExpense' },
        { id: '21', icon: 'el-icon-s-data', title: '当月研发费用', content: '企业主要营业产品财务收入', path: '/financial/rdExpense' },
        { id: '22', icon: 'el-icon-s-data', title: '当月制造费用', content: '企业主要营业产品财务收入', path: '/financial/manufacturingExpensesMonth' },
        { id: '26', icon: 'el-icon-s-data', title: '当月原材料存货额', content: '企业主要营业产品财务收入', path: '/financial/monthlyRawMaterialInventory' },
        { id: '27', icon: 'el-icon-s-data', title: '当月在制品存货额', content: '企业主要营业产品财务收入', path: '/financial/monthlyWorkInProgressInventory' },
        { id: '30', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', content: '企业主要营业产品财务收入', path: '/financial/growthRateInventorySales' },
        { id: '32', icon: 'el-icon-s-data', title: '应收账款周转率', content: '企业主要营业产品财务收入', path: '/financial/turnoverRateReceivable' },
        { id: '33', icon: 'el-icon-s-data', title: '资金周转率', content: '企业主要营业产品财务收入', path: '/financial/capitalTurnoverRate' },
        { id: '34', icon: 'el-icon-s-data', title: '库存商品周转率', content: '企业主要营业产品财务收入', path: '/financial/inventoryTurnoverRate' },
        { id: '35', icon: 'el-icon-s-data', title: '原材料周转率', content: '企业主要营业产品财务收入', path: '/financial/rawMaterialTurnoverRate' },
        { id: '36', icon: 'el-icon-s-data', title: '在制品周转率', content: '企业主要营业产品财务收入', path: '/financial/inprogressTurnoverRate' },
        { id: '66', icon: 'el-icon-s-data', title: '一年以上暂估行项目', content: '企业主要营业产品财务收入', path: '/financial/longEstimatedItems' },
        { id: '70', icon: 'el-icon-s-data', title: '当日在制品金额', content: '企业主要营业产品财务收入', path: '/financial/inprogressDayrevenue' },
        { id: '77', icon: 'el-icon-s-data', title: '月度存货金额', content: '企业主要营业产品财务收入', path: '/financial/monthlyInventoryTotalAmount' },
        { id: '78', icon: 'el-icon-s-data', title: '当月经济增加值', content: '企业主要营业产品财务收入', path: '/financial/addedValueMonthly' },
      ],
      financialIndex: [
        { id: '1', icon: 'el-icon-s-data', title: '主营业务收入', content: '企业主要营业产品财务收入', path: '/financial/mainRevenue' },
        { id: '2', icon: 'el-icon-s-data', title: '整机销售收入', content: '企业主要营业产品财务收入', path: '/financial/totalSalesRevenue' },
        { id: '3', icon: 'el-icon-s-data', title: '集团外部销售收入', content: '企业主要营业产品财务收入', path: '/financial/externalGroupSalesRevenue' },
        { id: '6', icon: 'el-icon-s-data', title: '新产品销售收入', content: '企业主要营业产品财务收入', path: '/financial/newProductSalesRevenue' },
        { id: '7', icon: 'el-icon-s-data', title: '特色产品收入', content: '企业主要营业产品财务收入', path: '/financial/specialtyProductRevenue' },
        { id: '8', icon: 'el-icon-s-data', title: '主营业务成本', content: '企业主要营业产品财务收入', path: '/financial/COGS' },
        { id: '9', icon: 'el-icon-s-data', title: '整机销售成本', content: '企业主要营业产品财务收入', path: '/financial/totalSalesCost' },
        { id: '10', icon: 'el-icon-s-data', title: '净利润', content: '企业主要营业产品财务收入', path: '/financial/netProfit' },
        { id: '20', icon: 'el-icon-s-data', title: '当月管理费用', content: '企业主要营业产品财务收入', path: '/financial/managementExpense' },
        { id: '21', icon: 'el-icon-s-data', title: '当月研发费用', content: '企业主要营业产品财务收入', path: '/financial/rdExpense' },
        { id: '22', icon: 'el-icon-s-data', title: '当月制造费用', content: '企业主要营业产品财务收入', path: '/financial/manufacturingExpensesMonth' },
        { id: '70', icon: 'el-icon-s-data', title: '当日在制品金额', content: '企业主要营业产品财务收入', path: '/financial/inprogressDayrevenue' },
        { id: '77', icon: 'el-icon-s-data', title: '月度存货金额', content: '企业主要营业产品财务收入', path: '/financial/monthlyInventoryTotalAmount' },
      ],
      SalesIndex: [
        { id: '4', icon: 'el-icon-s-data', title: '整车产量', content: '企业主要营业产品财务收入', path: '/financial/totalVehicleProduction' },
        { id: '5', icon: 'el-icon-s-data', title: '整车销量', content: '企业主要营业产品财务收入', path: '/financial/totalVehicleSales' },
        { id: '26', icon: 'el-icon-s-data', title: '当月原材料存货额', content: '企业主要营业产品财务收入', path: '/financial/monthlyRawMaterialInventory' },
        { id: '27', icon: 'el-icon-s-data', title: '当月在制品存货额', content: '企业主要营业产品财务收入', path: '/financial/monthlyWorkInProgressInventory' },
        { id: '66', icon: 'el-icon-s-data', title: '一年以上暂估行项目', content: '企业主要营业产品财务收入', path: '/financial/longEstimatedItems' },
      ],
      OperatingEfficiencyIndex: [
        { id: '30', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', content: '企业主要营业产品财务收入', path: '/financial/growthRateInventorySales' },
        { id: '32', icon: 'el-icon-s-data', title: '应收账款周转率', content: '企业主要营业产品财务收入', path: '/financial/turnoverRateReceivable' },
        { id: '33', icon: 'el-icon-s-data', title: '资金周转率', content: '企业主要营业产品财务收入', path: '/financial/capitalTurnoverRate' },
        { id: '34', icon: 'el-icon-s-data', title: '库存商品周转率', content: '企业主要营业产品财务收入', path: '/financial/inventoryTurnoverRate' },
        { id: '35', icon: 'el-icon-s-data', title: '原材料周转率', content: '企业主要营业产品财务收入', path: '/financial/rawMaterialTurnoverRate' },
        { id: '36', icon: 'el-icon-s-data', title: '在制品周转率', content: '企业主要营业产品财务收入', path: '/financial/inprogressTurnoverRate' },
        { id: '78', icon: 'el-icon-s-data', title: '当月经济增加值', content: '企业主要营业产品财务收入', path: '/financial/addedValueMonthly' },
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
    toDetail(item, index) {
      this.$router.push(item.path)
    }
  }
}

</script>
<style lang="scss" scoped>
.current-page {
  padding: 16px;
}

.financialIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .financialIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .financialIndex-item__body,
  .financialIndex-item__footer {
    padding: 16px;
  }

  .financialIndex-item__body {
    display: flex;
  }

  .financialIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .financialIndex-item__body i {
    font-size: 30px;
  }

  .financialIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .financialIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;
  }
}

.SalesIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .SalesIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .SalesIndex-item__body,
  .SalesIndex-item__footer {
    padding: 16px;
  }

  .SalesIndex-item__body {
    display: flex;
  }

  .SalesIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .SalesIndex-item__body i {
    font-size: 30px;
  }

  .SalesIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .SalesIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }
}

.OperatingEfficiencyIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .OperatingEfficiencyIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .OperatingEfficiencyIndex-item__body,
  .OperatingEfficiencyIndex-item__footer {
    padding: 16px;
  }

  .OperatingEfficiencyIndex-item__body {
    display: flex;
  }

  .OperatingEfficiencyIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .OperatingEfficiencyIndex-item__body i {
    font-size: 30px;
  }

  .OperatingEfficiencyIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .OperatingEfficiencyIndex-item__footer {
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

<template>
  <div class="current-page">
    <div class="list">
      <div v-for="(item,index) in list" :key="index" class="list-item">
        <div class="list-item__body">
          <i :class="item.icon" />
          <div class="main-content">
            <h3>{{ item.title }}</h3>
            <div>{{ item.content }}</div>
          </div>
        </div>
        <div class="list-item__footer">
          <el-button type="text" @click="toDetail(item,index)">指标数据图</el-button>
        </div>
      </div>
    </div>

  </div>

</template>

<script>

export default {
  components: { },
  props: [],
  data() {
    return {
      list: [
        { id: '1', icon: 'el-icon-s-goods', title: '指标1：主营业务收入', content: '企业主要营业产品财务收入' , path: '/financial/mainRevenue' },
        { id: '2', icon: 'el-icon-s-goods', title: '指标2：整机销售收入', content: '企业主要营业产品财务收入' , path: '/financial/totalSalesRevenue' },
        { id: '3', icon: 'el-icon-s-goods', title: '指标3：集团外部销售收入', content: '企业主要营业产品财务收入' , path: '/financial/externalGroupSalesRevenue' },
        { id: '4', icon: 'el-icon-s-goods', title: '指标4：整车产量', content: '企业主要营业产品财务收入' , path: '/financial/totalVehicleProduction' },
        { id: '5', icon: 'el-icon-s-goods', title: '指标5：整车销量', content: '企业主要营业产品财务收入' , path: '/financial/totalVehicleSales' },
        { id: '6', icon: 'el-icon-s-goods', title: '指标6：新产品销售收入', content: '企业主要营业产品财务收入' , path: '/financial/newProductSalesRevenue' },
        { id: '7', icon: 'el-icon-s-goods', title: '指标7：特色产品收入', content: '企业主要营业产品财务收入' , path: '/financial/specialtyProductRevenue' },
        { id: '8', icon: 'el-icon-s-goods', title: '指标8：主营业务成本', content: '企业主要营业产品财务收入' , path: '/financial/COGS' },
        { id: '9', icon: 'el-icon-s-goods', title: '指标9：整机销售成本', content: '企业主要营业产品财务收入' , path: '/financial/totalSalesCost' },
        { id: '10', icon: 'el-icon-s-goods', title: '指标10：净利润', content: '企业主要营业产品财务收入' , path: '/financial/netProfit' },  
        { id: '20', icon: 'el-icon-s-goods', title: '指标20：当月管理费用', content: '企业主要营业产品财务收入' , path: '/financial/managementExpense'},
        { id: '21', icon: 'el-icon-s-goods', title: '指标21：当月研发费用', content: '企业主要营业产品财务收入' , path: '/financial/rdExpense'},
        { id: '22', icon: 'el-icon-s-goods', title: '指标22：当月制造费用', content: '企业主要营业产品财务收入', path: '/financial/manufacturingExpensesMonth' },
        { id: '26', icon: 'el-icon-s-goods', title: '指标26：当月原材料存货额', content: '企业主要营业产品财务收入' , path: '/financial/monthlyRawMaterialInventory'},
        { id: '27', icon: 'el-icon-s-goods', title: '指标27：当月在制品存货额', content: '企业主要营业产品财务收入' , path: '/financial/monthlyWorkInProgressInventory'},
        { id: '30', icon: 'el-icon-s-goods', title: '指标30：存货增长率/销售增长率', content: '企业主要营业产品财务收入' , path: '/financial/growthRateInventorySales'}, 
        { id: '32', icon: 'el-icon-s-goods', title: '指标32：应收账款周转率', content: '企业主要营业产品财务收入' , path: '/financial/turnoverRateReceivable'},
        { id: '33', icon: 'el-icon-s-goods', title: '指标33：资金周转率', content: '企业主要营业产品财务收入' , path: '/financial/capitalTurnoverRate'},
        { id: '34', icon: 'el-icon-s-goods', title: '指标34：库存商品周转率', content: '企业主要营业产品财务收入' , path: '/financial/inventoryTurnoverRate'},
        { id: '35', icon: 'el-icon-s-goods', title: '指标35：原材料周转率', content: '企业主要营业产品财务收入' , path: '/financial/rawMaterialTurnoverRate'},
        { id: '36', icon: 'el-icon-s-goods', title: '指标36：在制品周转率', content: '企业主要营业产品财务收入' , path: '/financial/inprogressTurnoverRate'},
        { id: '66', icon: 'el-icon-s-goods', title: '指标66：一年以上暂估行项目', content: '企业主要营业产品财务收入' , path: '/financial/longEstimatedItems'},
        { id: '70', icon: 'el-icon-s-goods', title: '指标70：当日在制品金额', content: '企业主要营业产品财务收入' , path: '/financial/inprogressDayrevenue'},
        { id: '77', icon: 'el-icon-s-goods', title: '指标77：月度存货金额', content: '企业主要营业产品财务收入' , path: '/financial/monthlyInventoryTotalAmount'},
        { id: '78', icon: 'el-icon-s-goods', title: '指标78：当月经济增加值', content: '企业主要营业产品财务收入' , path: '/financial/addedValueMonthly'},    
      ],
      
      
      formData: {
        field101: undefined,
        field102: undefined,
        field103: undefined,
        field104: '主营业务收入',
        field106: '整机销售收入',
        field107: '集团外部销售收入',
      },
      rules: {
        field104: [],
        field106: [],
        field107: [],
      },
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {},
  methods: {
    toDetail(item,index) {
      this.$router.push(item.path)
    }
  }
}

</script>
<style lang="scss" scoped>
.current-page {
  padding: 16px;
}
.list {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;
  .list-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0,0,0,.1);
  }
  .list-item__body,
  .list-item__footer {
    padding: 16px;
  }
  .list-item__body {
    display: flex;
  }
  .list-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }
  .list-item__body i {
    font-size: 30px;
  }
  .list-item__body i + .main-content {
    margin-left: 10px;
  }
  .list-item__footer {
    border-top: 1px solid #eee;
    text-align: right;
  }
}
</style>

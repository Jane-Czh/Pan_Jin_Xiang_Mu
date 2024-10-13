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
                <h3>{{ item.title }}
                  <span v-if="item.showWarning">
                    <i class="el-icon-warning-outline" style="font-size: 20px; color: red;"></i>
                  </span>
                </h3>
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
                <h3>{{ item.title }}
                  <span v-if="item.showWarning">
                    <i class="el-icon-warning-outline" style="font-size: 20px; color: red;"></i>
                  </span>
                </h3>
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
                <h3>{{ item.title }}
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
import { getBalanceNewData, getInterestSumData, getMonthSumData, getBalanceNewRateData, getDaySumData, getInterestNewData, getMonthNewData } from '@/api/financial/chartAPI'
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
        { id: '1', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'interests', apiName: 'getMainRevenueData', date: '', yDataName: 'MainRevenue', type: '1', icon: 'el-icon-s-data', title: '主营业务收入', dataName: '金额', content: ``, sum: 'mainRevenue' },
        { id: '2', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getTotalSalesRevenueData', date: '', yDataName: 'Total_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '整机销售收入', dataName: '金额', content: '', sum: 'totalSalesRevenue' },
        { id: '3', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getExternalGroupSalesRevenueData', date: '', yDataName: 'External_Group_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '集团外部销售收入', dataName: '金额', content: '', sum: 'externalGroupSalesRevenue' },
        { id: '4', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getTotalVehicleProductionData', date: '', yDataName: 'Total_Vehicle_Production', type: '1', icon: 'el-icon-s-data', title: '整车产量', dataName: '台', content: '', sum: 'totalVehicleProduction' },
        { id: '5', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getTotalVehicleSalesData', date: '', yDataName: 'Total_Vehicle_Sales', type: '1', icon: 'el-icon-s-data', title: '整车销量', dataName: '台', content: '', sum: 'totalVehicleSales' },
        { id: '6', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getNewProductSalesRevenueData', date: '', yDataName: 'New_Product_Sales_Revenue', type: '1', icon: 'el-icon-s-data', title: '新产品销售收入', dataName: '金额', content: '', sum: 'newProductSalesRevenue' },
        { id: '7', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getSpecialtyProductRevenueData', date: '', yDataName: 'Specialty_Product_Revenue', type: '1', icon: 'el-icon-s-data', title: '特色产品收入', dataName: '金额', content: '', sum: 'specialtyProductRevenue' },
        { id: '8', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'interests', apiName: 'getCOGSData', date: '', yDataName: 'COGS', type: '1', icon: 'el-icon-s-data', title: '主营业务成本', dataName: '金额', content: '', sum: 'cogs' },
        { id: '9', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'month', apiName: 'getTotalSalesCostData', date: '', yDataName: 'Total_Sales_Cost', type: '1', icon: 'el-icon-s-data', title: '整机销售成本', dataName: '金额', content: '', sum: 'totalSalesCost' },
        { id: '10', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'interests', apiName: 'getNetProfitData', date: '', yDataName: 'NetProfit', type: '1', icon: 'el-icon-s-data', title: '净利润', dataName: '金额', content: '', sum: 'netProfit' },
        { id: '20', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'interestsNew', apiName: 'getManagementExpenseData', date: '', yDataName: 'ManagementExpense', type: '2', icon: 'el-icon-s-data', title: '当月管理费用', dataName: '金额', content: '', sum: 'managementExpense' },
        { id: '21', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'interestsNew', apiName: 'getRdExpenseData', date: '', yDataName: 'RD_Expense', type: '2', icon: 'el-icon-s-data', title: '当月研发费用', dataName: '金额', content: '', sum: 'rdExpense' },
        { id: '22', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getManufacturingExpensesMonthData', date: '', yDataName: 'Manufacturing_Expenses_Month', type: '2', icon: 'el-icon-s-data', title: '当月制造费用', dataName: '金额', content: '', sum: 'manufacturingExpensesMonth' },
        { id: '26', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'balance', apiName: 'getMonthlyRawMaterialInventoryData', date: '', yDataName: 'MonthlyRawMaterialInventory', type: '2', icon: 'el-icon-s-data', title: '当月原材料存货额', dataName: '金额', content: '', sum: 'monthlyRawMaterialInventory' },
        { id: '27', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'balance', apiName: 'getMonthlyWorkInProgressInventoryData', date: '', yDataName: 'MonthlyWorkInProgressInventory', type: '2', icon: 'el-icon-s-data', title: '当月在制品存货额', dataName: '金额', content: '', sum: 'monthlyWorkInProgressInventory' },
        { id: '28', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'balance', apiName: 'getMonthAmountInStockData', date: '', yDataName: 'Month_Amount_InStock', type: '2', icon: 'el-icon-s-data', title: '当月库存商品存货额', dataName: '金额', content: '', sum: 'monthAmountInStock' },
        { id: '30', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'balanceRate', apiName: 'getGrowthRateInventoryAndSalesData', date: '', yDataName: 'GrowthRate_Sales', yDataName2: 'GrowthRate_Inventory', type: '2', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', dataName: '增长率', content: '', sum: 'growthRateInventoryAndSales', sum2: 'growthRateSales' },
        { id: '32', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'balanceRate', apiName: 'getTurnoverRateReceivableData', date: '', yDataName: 'TurnoverRate_Receivable', type: '3', icon: 'el-icon-s-data', title: '应收账款周转率', dataName: '周转率', content: '', sum: 'turnoverRateReceivable' },
        { id: '33', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getCapitalTurnoverRateData', date: '', yDataName: 'Capital_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '当月资金周转率', dataName: '周转率', content: '', sum: 'capitalTurnoverRate' },
        { id: '34', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getInventoryTurnoverRateData', date: '', yDataName: 'Inventory_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '当月库存商品周转率', dataName: '周转率', content: '', sum: 'inventoryTurnoverRate' },
        { id: '35', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getRawMaterialTurnoverRateData', date: '', yDataName: 'Raw_Material_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '当月原材料周转率', dataName: '周转率', content: '', sum: 'rawMaterialTurnoverRate' },
        { id: '36', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getInprogressTurnoverRateData', date: '', yDataName: 'InProgress_Turnover_Rate', type: '3', icon: 'el-icon-s-data', title: '当月在制品周转率', dataName: '周转率', content: '', sum: 'inprogressTurnoverRate' },
        { id: '66', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getLongEstimatedItemsData', date: '', yDataName: 'Long_Estimated_Items', type: '3', icon: 'el-icon-s-data', title: '一年以上暂估行项目', dataName: '项目', content: '', sum: 'longEstimatedItems' },
        { id: '70', showTarget: 'financial', showType: 'SUM', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'day', apiName: 'getInprogressDayrevenueData', date: '', yDataName: 'InProgress_DayRevenue', type: '2', icon: 'el-icon-s-data', title: '当日在制品金额', dataName: '金额', content: '', sum: 'inprogressDayrevenue' },
        { id: '77', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'balance', apiName: 'getMonthlyInventoryTotalAmountData', date: '', yDataName: 'MonthlyInventoryTotalAmount', type: '2', icon: 'el-icon-s-data', title: '当月存货总额', dataName: '金额', content: '', sum: 'monthlyInventoryTotalAmount' },
        { id: '78', showTarget: 'financial', showType: 'NEW', showWarning: false, targetValue: 0, targetValueDate: '', kind: 'monthNew', apiName: 'getAddedValueMonthlyData', date: '', yDataName: 'Added_Value_Monthly', type: '1', icon: 'el-icon-s-data', title: '当月经济增加值', dataName: '金额', content: '', sum: 'addedValueMonthly' },
      ],
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
        if (!this.$auth.hasPermi("financial:index")) {
          this.activeName = '0';
          if (this.$auth.hasPermi("financial:market")) {
            this.allIndex = this.allIndex.filter(item => {
              return ['4', '5', '6', '7', '28', '34'].includes(item.id);
            });
          } else if (this.$auth.hasPermi("financial:tech")) {
            this.allIndex = this.allIndex = this.allIndex.filter(item => {
              return ['4', '5', '6', '7'].includes(item.id);
            });
          } else if (this.$auth.hasPermi("financial:supply")) {
            this.allIndex = this.allIndex = this.allIndex = this.allIndex.filter(item => {
              return ['4', '5', '26', '35', '66'].includes(item.id);
            });
          } else if (this.$auth.hasPermi("financial:production")) {
            this.allIndex = this.allIndex = this.allIndex = this.allIndex = this.allIndex.filter(item => {
              return ['4', '5', '27', '36', '70'].includes(item.id);
            });
          } else {
            this.allIndex = this.allIndex = this.allIndex = this.allIndex = this.allIndex = this.allIndex.filter(item => {
              return ['4', '5'].includes(item.id);
            });
          }
        }

        let target = {
          date: new Date(),
          deptName: 'financial',
        }
        // 定义一个空对象来存储所有数据
        let allData = {};
        let allNewData = {};
        // 发起并行请求
        const [resBalanceNew, resInterestSum, resMonthSum, resBalanceRate, resDaySum, resTarget, resInterestNew, resMonthNew] = await Promise.all([
          getBalanceNewData(),
          getInterestSumData(this.date),
          getMonthSumData(this.date),
          getBalanceNewRateData(),
          getDaySumData(this.date),
          getTargetData(target),
          getInterestNewData(),
          getMonthNewData()
        ].map(p => p.catch(e => {
          console.error('API 请求失败:', e);
          return null; // 返回 null 或其他默认值
        }))
        );

        //利润总和数据
        const resInterestSumNeededFields = {
          mainRevenue: resInterestSum && resInterestSum.data ? resInterestSum.data.mainRevenue : '',
          cogs: resInterestSum && resInterestSum.data ? resInterestSum.data.cogs : '',
          netProfit: resInterestSum && resInterestSum.data ? resInterestSum.data.netProfit : '',
        };

        //月度总和数据
        const resMonthSumNeededFields = {
          totalSalesRevenue: resMonthSum && resMonthSum.data ? resMonthSum.data.totalSalesRevenue : '',
          externalGroupSalesRevenue: resMonthSum && resMonthSum.data ? resMonthSum.data.externalGroupSalesRevenue : '',
          totalVehicleProduction: resMonthSum && resMonthSum.data ? resMonthSum.data.totalVehicleProduction : '',
          totalVehicleSales: resMonthSum && resMonthSum.data ? resMonthSum.data.totalVehicleSales : '',
          newProductSalesRevenue: resMonthSum && resMonthSum.data ? resMonthSum.data.newProductSalesRevenue : '',
          totalSalesCost: resMonthSum && resMonthSum.data ? resMonthSum.data.totalSalesCost : '',
          specialtyProductRevenue: resMonthSum && resMonthSum.data ? resMonthSum.data.specialtyProductRevenue : '',
        };

        //当日在制品总和数据
        const resDaySumDataNeededFields = {
          dataTime: resDaySum && resDaySum.data ? resDaySum.data.dataTime : '',
          inprogressDayrevenue: resDaySum && resDaySum.data ? resDaySum.data.inProgressDayRevenue : 0
        };

        //资产负债最新数据
        const resBalanceNewDataNeededFields = {
          monthlyRawMaterialInventory: resBalanceNew && resBalanceNew.data ? resBalanceNew.data.monthlyRawMaterialInventory : '',
          monthlyWorkInProgressInventory: resBalanceNew && resBalanceNew.data ? resBalanceNew.data.monthlyWorkInProgressInventory : '',
          monthAmountInStock: resBalanceNew && resBalanceNew.data ? resBalanceNew.data.monthAmountInStock : '',
          monthlyInventoryTotalAmount: resBalanceNew && resBalanceNew.data ? resBalanceNew.data.monthlyInventoryTotalAmount : '',
        };

        //利润最新数据
        const resInterestNewNeededFields = {
          managementExpense: resInterestNew && resInterestNew.data ? resInterestNew.data.managementExpense : '',
          rdExpense: resInterestNew && resInterestNew.data ? resInterestNew.data.rdExpense : '',

        };
        //月度最新数据
        const resMonthNewNeededFields = {
          manufacturingExpensesMonth: resMonthNew && resMonthNew.data ? resMonthNew.data.manufacturingExpensesMonth : '',
          capitalTurnoverRate: resMonthNew && resMonthNew.data ? resMonthNew.data.capitalTurnoverRate : '',
          inprogressTurnoverRate: resMonthNew && resMonthNew.data ? resMonthNew.data.inprogressTurnoverRate : '',
          longEstimatedItems: resMonthNew && resMonthNew.data ? resMonthNew.data.longEstimatedItems : '',
          addedValueMonthly: resMonthNew && resMonthNew.data ? resMonthNew.data.addedValueMonthly : '',
          rawMaterialTurnoverRate: resMonthNew && resMonthNew.data ? resMonthNew.data.rawMaterialTurnoverRate : '',
          inventoryTurnoverRate: resMonthNew && resMonthNew.data ? resMonthNew.data.inventoryTurnoverRate : '',
        };

        //资产负债比例数据
        const resBalanceRateDataNeededFields = {
          growthRateInventory: resBalanceRate && resBalanceRate.data ? resBalanceRate.data.growthRateInventory : '',
          turnoverRateReceivable: resBalanceRate && resBalanceRate.data ? resBalanceRate.data.turnoverRateReceivable : ''
        };

        //截至日期展示
        this.allIndex.forEach(item => {
          if (item.kind === 'balanceRate') {
            item.date = resBalanceRate && resBalanceRate.data ? moment(resBalanceRate.data.yearAndMonth).format('YYYY-MM') : '—';
          }
          else if (item.kind === 'interestNew') {
            item.date = resInterestNew && resInterestNew.data ? moment(resInterestNew.data.yearAndMonth).format('YYYY-MM') : '—';
          }

          else if (item.kind === 'monthNew') {
            item.date = resMonthNew && resMonthNew.data ? moment(resMonthNew.data.yearAndMonth).format('YYYY-MM') : '—';
          }
          else if (item.kind === 'balance') {
            item.date = resBalanceNew && resBalanceNew.data ? moment(resBalanceNew.data.yearAndMonth).format('YYYY-MM') : '—';
          } else if (item.kind === 'interests') {
            item.date = resInterestSum && resInterestSum.data ? moment(resInterestSum.data.yearAndMonth).format('YYYY-MM') : '—';
          } else if (item.kind === 'day') {
            item.date = resDaySum && resDaySum.data ? resDaySum.data.dataTime : '—';
          } else {
            item.date = resMonthSum && resMonthSum.data ? moment(resMonthSum.data.yearAndMonth).format('YYYY-MM') : '—';
          }
        });

        allData = {
          ...resInterestSumNeededFields,
          ...resMonthSumNeededFields,
          ...resDaySumDataNeededFields,
        };
        const allTargetData = allData
        Object.keys(allData).forEach(key => {
          allData[key] = this.formatNumber(allData[key]);
        });
        allNewData = {
          ...resBalanceNewDataNeededFields,
          ...resBalanceRateDataNeededFields,
          ...resInterestNewNeededFields,
          ...resMonthNewNeededFields,
        };
        Object.keys(allNewData).forEach(key => {
          if (key !== 'growthRateInventory' && key !== 'turnoverRateReceivable' && key !== 'capitalTurnoverRate' && key !== 'inventoryTurnoverRate' && key !== 'rawMaterialTurnoverRate' && key !== 'inprogressTurnoverRate' && key !== 'longEstimatedItems')
            allNewData[key] = this.formatNumber(allNewData[key]);
        });

        this.balanceData = resBalanceNew.data || '';
        this.interestsData = resInterestSum.data || '';
        this.monthData = resMonthSum.data || '';
        this.allData.yearAndMonth = moment(resBalanceNew.data.yearAndMonth).format('YYYY-MM') || '—';
        // 更新 allIndex 数组中每个元素的 content 字段
        this.allIndex.forEach(item => {
          if (item.id === '30') {
            item.content = `最新一月：${allNewData[item.sum] !== undefined ? allNewData[item.sum] : '—'}`;
          }
          else if (item.id === '70') {
            item.content = `最近一日：${allData[item.sum] || '—'}\t(元)`;
          }
          else {
            const key = item.sum;
            if (item.id === '4' || item.id === '5') {
              item.content = `全年总计：${allData[key] || '—'}\t(台)`;
            }
            else if (allData[key] !== undefined && item.showType == "SUM") {
              item.content = `全年总计：${allData[key] || '—'}\t(万元)`;
            }
            else if (allNewData[key] !== undefined && item.showType == "NEW") {
              if (item.id === '32' || item.id === '33' || item.id === '34' || item.id === '35' || item.id === '36') {
                item.content = `最新一月：${allNewData[key] || '—'}\t(次)`;
              } else if (item.id === '66') {
                item.content = `最新一月：${allNewData[key] || '—'}\t(项)`;
              }
              else {
                item.content = `最新一月：${allNewData[key] || '—'}\t(万元)`;
              }
            }
          }
        });

        //目标值赋予及上下限预警
        this.allIndex.forEach(item => {
          resTarget.rows.forEach(row => {
            if (item.sum === row.indicatorName) {
              // item.targetValue = row.targetValue;
              item.targetValueDate = row.natureYear;
              if (row.targetLowerLimit || row.targetUpperLimit) {
                if (allData[item.sum] < row.targetLowerLimit || allData[item.sum] > row.targetUpperLimit) {
                  item.showWarning = true;
                }
              }
            }
          });
        });
      } catch (error) {
        console.error('初始化数据失败:', error);
      }
    },

    toDetail(item) {
      if (item.id === '30') {
        this.$router.push({
          path: '/financial/indicators30',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
      else if (item.id === '70') {
        this.$router.push({
          path: '/financial/indicators70',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
      else {
        this.$router.push({
          path: '/financial/index-detail-financial',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
    },
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
    },

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

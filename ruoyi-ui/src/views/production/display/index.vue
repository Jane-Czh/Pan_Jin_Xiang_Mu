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
import { getProductionNewData, getUpNewData, getDownNewData } from '@/api/production/chartAPI'
import moment from 'moment'
export default {
  components: {},
  props: [],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '24', showTarget: 'production', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'curNonBomMaterialCost', apiName: 'getcurNonBomMaterialCostData', yDataName: 'curNonBomMaterialCost', dataName: '金额', icon: 'el-icon-s-data', title: '当月单台非BOM物料费用', content: '当月单台非BOM物料费用' },
        { id: '25', showTarget: 'production', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'curLowValueConsumables', apiName: 'getcurLowValueConsumablesData', yDataName: 'curLowValueConsumables', dataName: '金额', icon: 'el-icon-s-data', title: '当月单台低值易耗费用', content: '当月单台低值易耗费用' },
        { id: '29', showTarget: 'production', type: '天', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'inventoryTurnoverdays', apiName: 'getinventoryTurnoverdaysData', yDataName: 'inventoryTurnoverdays', dataName: '天数', icon: 'el-icon-s-data', title: '在制物资年化周转天数', content: '在制物资年化周转天数' },
        { id: '37', showTarget: 'production', type: '台', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'outputPercapitacounts', apiName: 'getoutputPercapitacountsData', yDataName: 'outputPercapitacounts', dataName: '台数', icon: 'el-icon-s-data', title: '人均生产台数', content: '人均生产台数' },
     { id: '39', showTarget: 'production', type: '%', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'outputPercapitacounts', apiName: 'getoutputPercapitacountsData', yDataName: 'outputPercapitacounts', dataName: '比率', icon: 'el-icon-s-data', title: '商品车计划兑现率', content: '商品车计划兑现率' },

        { id: '38', showTarget: 'production', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'outputPercapitavalue', apiName: 'getoutputPercapitavalueData', yDataName: 'outputPercapitavalue', dataName: '产值', icon: 'el-icon-s-data', title: '人均产值', content: '人均产值' },
        { id: '41', showTarget: 'production', type: '%', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'onlineOntimerate', apiName: 'getonlineOntimerateData', yDataName: 'onlineOntimerate', dataName: '及时率', icon: 'el-icon-s-data', title: '上线及时率', content: '上线及时率' },
        { id: '48', showTarget: 'production', type: '小时', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'overtimeFrontlinemonth', apiName: 'getOvertimeFrontlinemonthData', yDataName: 'overtimeFrontlinemonth', dataName: '金额', icon: 'el-icon-s-data', title: '一线当月加班时长', content: '一线当月加班时长' },
        { id: '74', showTarget: 'production', type: '个', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'launch', icon: 'el-icon-s-data', title: '日/月/年上线数', content: '日/月/年上线数', },
        { id: '75', showTarget: 'production', type: '个', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'completion', icon: 'el-icon-s-data', title: '日/月/年完工数', content: '日/月/年完工数', },
      ],
      formData: {},
      rules: {},
      newData: {},
      newDateData: {},

    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() {
    this.init()
  },
  methods: {
    async init() {

      // 发起并行请求
      const [res, upRes, downRes] = await Promise.all([
        getProductionNewData(),
        getUpNewData(),
        getDownNewData(),
      ].map(p => p.catch(e => {
        console.error('API 请求失败:', e);
        return null; // 返回 null 或其他默认值
      }))
      );
      this.newData = res.data
      const dayData = {
        yearAndMonthLunch: upRes.data.yearAndMonth,
        launch: upRes.data.data,
        yearAndMonthCompletion: downRes.data.yearAndMonth,
        completion: downRes.data.data,
      }
      const allTargetData = {
        ...this.newData,
        launch: upRes.data.data,
        completion: downRes.data.data,
      }
      // console.log('allTargetData', allTargetData)
      //页面截至
      this.allIndex.forEach(item => {
        if (item.id != '74' && item.id != '75') {
          item.date = res && res.data ? moment(res.data.yearAndMonth).format('YYYY-MM') : '—';
        }
        else if (item.id === '74') {
          item.date = dayData['yearAndMonthLunch'] ? moment(dayData['yearAndMonthLunch']).format('YYYY-MM-DD') : '—';
        } else {
          item.date = dayData['yearAndMonthCompletion'] ? moment(dayData['yearAndMonthCompletion']).format('YYYY-MM-DD') : '—';
        }
      });
      //卡片content
      this.allIndex.forEach(item => {
        const key = item.sum;
        if (item.id != '74' && item.id != '75') {
          if (this.newData[key] !== undefined) {
            item.content = `最新一月：${this.newData[key] || ''} (${item.type})`;
          }
        } else {
          item.content = `最新一日：${dayData[key] || ''} (${item.type})`;
        }
      });

      let target = {
        date: new Date(),
        deptName: 'production',
      }
      const resTarget = await getTargetData(target)
      //目标值赋予及上下限预警
      this.allIndex.forEach(item => {
        resTarget.rows.forEach(row => {
          if (item.sum === row.indicatorName) {
            item.targetValue = row.targetValue;
            item.targetValueDate = row.natureYear ? moment(row.natureYear).format('YYYY') : '—';
            //获取最新值年份
            if (item.id != '74' && item.id != '75') {
              this.newDateData = res && res.data ? moment(res.data.yearAndMonth).format('YYYY') : '--';
            }
            else if (item.id === '74') {
              this.newDateData = dayData['yearAndMonthLunch'] ? moment(dayData['yearAndMonthLunch']).format('YYYY') : '--';
            } else {
              this.newDateData = dayData['yearAndMonthCompletion'] ? moment(dayData['yearAndMonthCompletion']).format('YYYY') : '--';
            }
            //上下限预警
            if (this.newDateData == item.targetValueDate) {
              if (row.targetLowerLimit != null || row.targetUpperLimit != null) {
                if (allTargetData[item.sum] < row.targetLowerLimit || allTargetData[item.sum] > row.targetUpperLimit) {
                  item.showWarning = true;
                }
              }
            }

          }
        });
      });
    },
    toDetail(item) {
      if (item.id === '74') {
        this.$router.push({
          path: '/production/indicators74',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
      else if (item.id === '75') {
        this.$router.push({
          path: '/production/indicators75',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
        else if (item.id === '39') {
        this.$router.push({
          path: '/production/index39',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
      else {
        this.$router.push({
          path: '/production/index-detail-production',
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

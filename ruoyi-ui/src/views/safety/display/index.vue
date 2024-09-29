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
                <span v-if="item.id === '102'" style="font-size: 16px;">{{ item.date }}</span>
                <span v-else style="font-size: 16px;">截至{{ item.date }}</span>
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
import { getSafetyNewData, getSafetyTypeNewData } from '@/api/safety/chartAPI'
import moment from 'moment'

export default {
  components: {},
  props: [],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '23', showTarget: 'safety', date: '', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'curEquipmentMaintenanceCost', apiName: 'getCurEquipmentMaintenanceCostData', yDataName: 'curEquipmentMaintenanceCost', dataName: '金额', icon: 'el-icon-s-data', title: '当月度设备维修总费用', content: '' },
        { id: '45', showTarget: 'safety', date: '', type: '%', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'keyEquipmentFailureRate', apiName: 'getKeyEquipmentFailureRateData', yDataName: 'keyEquipmentFailureRate', dataName: '故障率', icon: 'el-icon-s-data', title: '重点设备故障率', content: '' },
        { id: '46', showTarget: 'safety', date: '', type: '小时', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'curEquipmentFailuresTotaltime', apiName: 'getCurEquipmentFailuresTotaltimeData', yDataName: 'curEquipmentFailuresTotaltime', dataName: '停产时间', icon: 'el-icon-s-data', title: '当月设备故障累计停产时间', content: '' },
        { id: '67', showTarget: 'safety', date: '', type: '次', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'keyEquipmentTotalFailureCount', apiName: 'getKeyEquipmentTotalFailureCountData', yDataName: 'keyEquipmentTotalFailureCount', dataName: '总次数', icon: 'el-icon-s-data', title: '主要设备故障总次数', content: '' },
        { id: '68', showTarget: 'safety', date: '', type: '次', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'outputPercapitavalue', apiName: 'getOutputPercapitavalueData', yDataName: 'resultData', dataName: '次数', icon: 'el-icon-s-data', title: '设备故障类别次数分布图', content: '', path: '/safety/indicators68' },
        { id: '69', showTarget: 'safety', date: '', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'curEquipmentReplacementCost', apiName: 'getCurEquipmentReplacementCostData', yDataName: 'curEquipmentReplacementCost', dataName: '金额', icon: 'el-icon-s-data', title: '当月设备维修易损件成本', content: '' },
      ],
      formData: {},
      rules: {},
      newData: {},
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

      const res = await getSafetyNewData()
      this.newData = res.data;
      const resType = await getSafetyTypeNewData()
      const typeData = resType.rows[0];
      console.log(typeData)
      //页面截至
      this.allIndex.forEach(item => {
        item.date = res && res.data ? moment(res.data.yearAndMonth).format('YYYY-MM') : '--';
      });
      //卡片content
      this.allIndex.forEach(item => {
        const key = item.sum;
        if (item.id != '68') {
          if (this.newData[key] !== undefined) {
            item.content = `最新一月：${this.newData[key] || ''} (${item.type})`;
          } else {
            console.warn(`Key ${key} not found in response data.`);
          }
        } else {
          item.content = `最新一月：${typeData.resultData || ''} (${item.type})`;
        }
      });

      let target = {
        date: new Date(),
        deptName: 'safety',
      }
      const resTarget = await getTargetData(target)
      //目标值赋予及上下限预警
      this.allIndex.forEach(item => {
        resTarget.rows.forEach(row => {
          if (item.sum === row.indicatorName) {
            item.targetValue = row.targetValue;
            item.targetValueDate = row.natureYear;
            if (row.targetLowerLimit != null || row.targetUpperLimit != null) {
              if (this.newData[item.sum] < row.targetLowerLimit || this.newData[item.sum] > row.targetUpperLimit) {
                item.showWarning = true;
              }
            }
          }
        });
      });
    },
    toDetail(item) {
      if (item.id === '68') {
        this.$router.push({
          path: '/safety/indicators68',
          query: {
            data: JSON.stringify(item),
          }
        })
      }
      else {
        this.$router.push({
          path: '/safety/index-detail-safety',
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

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
  props: [],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '23', apiName: 'getCurEquipmentMaintenanceCostData', yDataName: 'curEquipmentMaintenanceCost', dataName: '金额', icon: 'el-icon-s-data', title: '当月度设备维修总费用', content: '当月度设备维修总费用' },
        { id: '45', apiName: 'getKeyEquipmentFailureRateData', yDataName: 'keyEquipmentFailureRate', dataName: '故障率', icon: 'el-icon-s-data', title: '重点设备故障率', content: '重点设备故障率' },
        { id: '46', apiName: 'getCurEquipmentFailuresTotaltimeData', yDataName: 'curEquipmentFailuresTotaltime', dataName: '停产时间', icon: 'el-icon-s-data', title: '当月设备故障累计停产时间', content: '当月设备故障累计停产时间' },
        { id: '67', apiName: 'getKeyEquipmentTotalFailureCountData', yDataName: 'keyEquipmentTotalFailureCount', dataName: '总次数', icon: 'el-icon-s-data', title: '主要设备故障总次数', content: '主要设备故障总次数' },
        { id: '68', apiName: 'getOutputPercapitavalueData', yDataName: 'resultData', dataName: '次数', icon: 'el-icon-s-data', title: '设备故障类别次数分布图', content: '设备故障类别次数分布图', path: '/safety/indicators68' },
        { id: '69', apiName: 'getCurEquipmentReplacementCostData', yDataName: 'curEquipmentReplacementCost', dataName: '金额', icon: 'el-icon-s-data', title: '当月设备维修成本', content: '当月设备维修成本' },
      ],
      formData: {},
      rules: {},
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() { },
  methods: {
    toDetail(item) {
      if (item.id === '68') {
        this.$router.push('/safety/indicators68')
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
}
</style>

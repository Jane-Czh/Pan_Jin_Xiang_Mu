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
        { id: '24', apiName: 'getcurNonBomMaterialCostData', yDataName: 'curNonBomMaterialCost', dataName: '金额', icon: 'el-icon-s-data', title: '当月单台非BOM物料费用', content: '当月单台非BOM物料费用' },
        { id: '25', apiName: 'getcurLowValueConsumablesData', yDataName: 'curLowValueConsumables', dataName: '金额',  icon: 'el-icon-s-data', title: '当月单台低值易耗费用', content: '当月单台低值易耗费用' },
        { id: '29', apiName: 'getinventoryTurnoverdaysData', yDataName: 'inventoryTurnoverdays', dataName: '天数', icon: 'el-icon-s-data', title: '在制物资年化周转天数', content: '在制物资年化周转天数' },
        { id: '37', apiName: 'getoutputPercapitacountsData', yDataName: 'outputPercapitacounts', dataName: '台数',  icon: 'el-icon-s-data', title: '人均生产台数', content: '人均生产台数' },
        { id: '38', apiName: 'getoutputPercapitavalueData', yDataName: 'outputPercapitavalue', dataName: '产值',  icon: 'el-icon-s-data', title: '人均产值', content: '人均产值' },
        { id: '41', apiName: 'getonlineOntimerateData', yDataName: 'onlineOntimerate', dataName: '及时率',  icon: 'el-icon-s-data', title: '上线及时率', content: '上线及时率' },
        { id: '48', apiName: 'getOvertimeFrontlinemonthData', yDataName: 'overtimeFrontlinemonth', dataName: '金额',  icon: 'el-icon-s-data', title: '一线当月加班时长', content: '一线当月加班时长' },
        { id: '74', icon: 'el-icon-s-data', title: '日/月/年上线数', content: '日/月/年上线数', },
        { id: '75', icon: 'el-icon-s-data', title: '日/月/年完工数', content: '日/月/年完工数', },
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
      if (item.id === '74') {
        this.$router.push('/production/indicators74')
      }
      else if (item.id === '75') {
        this.$router.push('/production/indicators75')
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
}
</style>

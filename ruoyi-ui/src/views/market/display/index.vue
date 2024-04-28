<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="企业运营指标" name="first">
        <div class="enterpriseOperatingIndicators">
          <div v-for="(item, index) in enterpriseOperatingIndicators" :key="index"
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
      <el-tab-pane label="效率指标" name="second">
        <div class="efficiencyIndicators">
          <div v-for="(item, index) in efficiencyIndicators" :key="index" class="efficiencyIndicators-item">
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
      <el-tab-pane label="全部" name="third">
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
        { id: '11', icon: 'el-icon-s-data', title: '各销售网点月度(I类)电车盘锦区域内订单占比 ', content: '企业主要营业产品财务收入', path: '/market/indicators11' },
        { id: '12', icon: 'el-icon-s-data', title: '各销售网点月度(IV-V类)内燃车盘锦区域内订单占比', content: '企业主要营业产品财务收入', path: '/market/indicators12' },
        { id: '13', icon: 'el-icon-s-data', title: '各主要车型产量月度比率', content: '企业主要营业产品财务收入', path: '/market/indicators13' },
        { id: '14', icon: 'el-icon-s-data', title: '各主要车型同比增长比例', content: '企业主要营业产品财务收入', path: '/market/indicators14' },
        { id: '15', icon: 'el-icon-s-data', title: '各销售网点月度各主要车型分布表', content: '企业主要营业产品财务收入', path: '/market/indicators15' },
        { id: '16', icon: 'el-icon-s-data', title: '各网点已接单未发车总台数', content: '企业主要营业产品财务收入', path: '/market/indicators16' },
        { id: '17', icon: 'el-icon-s-data', title: '各网点未排产订单台数', content: '企业主要营业产品财务收入', path: '/market/indicators17' },
        { id: '18', icon: 'el-icon-s-data', title: '各网点已完工未发车订单数', content: '企业主要营业产品财务收入', path: '/market/indicators18' },
        { id: '19', icon: 'el-icon-s-data', title: '各网点已到期未完工订单数', content: '企业主要营业产品财务收入', path: '/market/indicators19' },
        { id: '39', icon: 'el-icon-s-data', title: '商品车计划兑现率', content: '企业主要营业产品财务收入', path: '/market/indicators39' },
        { id: '40', icon: 'el-icon-s-data', title: '订单兑现率', content: '企业主要营业产品财务收入', path: '/market/indicators40' },
        { id: '42', icon: 'el-icon-s-data', title: '大叉位叉车整机交货天数平均数', content: '企业主要营业产品财务收入', path: '/market/indicators42' },
        { id: '43', icon: 'el-icon-s-data', title: '电动车整机交货天数', content: '企业主要营业产品财务收入', path: '/market/indicators43' },
        { id: '44', icon: 'el-icon-s-data', title: '小吨位内燃叉车整机交货天数', content: '企业主要营业产品财务收入', path: '/market/indicators44' },
        { id: '72', icon: 'el-icon-s-data', title: '日/月/年接单数', content: '企业主要营业产品财务收入', path: '/market/indicators72' },
        { id: '73', icon: 'el-icon-s-data', title: '日/月/年交货数', content: '企业主要营业产品财务收入', path: '/market/indicators73' },
      ],
      enterpriseOperatingIndicators: [
        { id: '11', icon: 'el-icon-s-data', title: '各销售网点月度(I类)电车盘锦区域内订单占比 ', content: '企业主要营业产品财务收入', path: '/market/indicators11' },
        { id: '12', icon: 'el-icon-s-data', title: '各销售网点月度(IV-V类)内燃车盘锦区域内订单占比', content: '企业主要营业产品财务收入', path: '/market/indicators12' },
        { id: '13', icon: 'el-icon-s-data', title: '各主要车型产量月度比率', content: '企业主要营业产品财务收入', path: '/market/indicators13' },
        { id: '14', icon: 'el-icon-s-data', title: '各主要车型同比增长比例', content: '企业主要营业产品财务收入', path: '/market/indicators14' },
        { id: '15', icon: 'el-icon-s-data', title: '各销售网点月度各主要车型分布表', content: '企业主要营业产品财务收入', path: '/market/indicators15' },
        { id: '16', icon: 'el-icon-s-data', title: '各网点已接单未发车总台数', content: '企业主要营业产品财务收入', path: '/market/indicators16' },
        { id: '17', icon: 'el-icon-s-data', title: '各网点未排产订单台数', content: '企业主要营业产品财务收入', path: '/market/indicators17' },
        { id: '18', icon: 'el-icon-s-data', title: '各网点已完工未发车订单数', content: '企业主要营业产品财务收入', path: '/market/indicators18' },
        { id: '19', icon: 'el-icon-s-data', title: '各网点已到期未完工订单数', content: '企业主要营业产品财务收入', path: '/market/indicators19' },
      ],
      efficiencyIndicators: [
        { id: '39', icon: 'el-icon-s-data', title: '商品车计划兑现率', content: '企业主要营业产品财务收入', path: '/market/indicators39' },
        { id: '40', icon: 'el-icon-s-data', title: '订单兑现率', content: '企业主要营业产品财务收入', path: '/market/indicators40' },
        { id: '42', icon: 'el-icon-s-data', title: '大叉位叉车整机交货天数平均数', content: '企业主要营业产品财务收入', path: '/market/indicators42' },
        { id: '43', icon: 'el-icon-s-data', title: '电动车整机交货天数', content: '企业主要营业产品财务收入', path: '/market/indicators43' },
        { id: '44', icon: 'el-icon-s-data', title: '小吨位内燃叉车整机交货天数', content: '企业主要营业产品财务收入', path: '/market/indicators44' },
        { id: '72', icon: 'el-icon-s-data', title: '日/月/年接单数', content: '企业主要营业产品财务收入', path: '/market/indicators72' },
        { id: '73', icon: 'el-icon-s-data', title: '日/月/年交货数', content: '企业主要营业产品财务收入', path: '/market/indicators73' },
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
      this.$router.push(item.path)
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

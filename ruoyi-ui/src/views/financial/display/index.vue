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
      <el-tab-pane label="成本指标" name="second">
        <div class="costIndicators">
          <div v-for="(item, index) in costIndicators" :key="index" class="costIndicators-item">
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
      <el-tab-pane label="效率指标" name="third">
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
        { id: '1', icon: 'el-icon-s-data', title: '主营业务收入', content: '企业主要营业产品财务收入', path: '/financial/indicators1' },
        { id: '2', icon: 'el-icon-s-data', title: '整机销售收入', content: '企业主要营业产品财务收入', path: '/financial/indicators2' },
        { id: '3', icon: 'el-icon-s-data', title: '集团外部销售收入', content: '企业主要营业产品财务收入', path: '/financial/indicators3' },
        { id: '4', icon: 'el-icon-s-data', title: '整车产量', content: '企业主要营业产品财务收入', path: '/financial/indicators4' },
        { id: '5', icon: 'el-icon-s-data', title: '整车销量', content: '企业主要营业产品财务收入', path: '/financial/indicators5' },
        { id: '6', icon: 'el-icon-s-data', title: '新产品销售收入', content: '企业主要营业产品财务收入', path: '/financial/indicators6' },
        { id: '7', icon: 'el-icon-s-data', title: '特色产品收入', content: '企业主要营业产品财务收入', path: '/financial/indicators7' },
        { id: '8', icon: 'el-icon-s-data', title: '主营业务成本', content: '企业主要营业产品财务收入', path: '/financial/indicators8' },
        { id: '9', icon: 'el-icon-s-data', title: '整机销售成本', content: '企业主要营业产品财务收入', path: '/financial/indicators9' },
        { id: '10', icon: 'el-icon-s-data', title: '净利润', content: '企业主要营业产品财务收入', path: '/financial/indicators10' },
        { id: '20', icon: 'el-icon-s-data', title: '当月管理费用', content: '企业主要营业产品财务收入', path: '/financial/indicators20' },
        { id: '21', icon: 'el-icon-s-data', title: '当月研发费用', content: '企业主要营业产品财务收入', path: '/financial/indicators21' },
        { id: '22', icon: 'el-icon-s-data', title: '当月制造费用', content: '企业主要营业产品财务收入', path: '/financial/indicators22' },
        { id: '26', icon: 'el-icon-s-data', title: '当月原材料存货额', content: '企业主要营业产品财务收入', path: '/financial/indicators26' },
        { id: '27', icon: 'el-icon-s-data', title: '当月在制品存货额', content: '企业主要营业产品财务收入', path: '/financial/indicators27' },
        { id: '28', icon: 'el-icon-s-data', title: '当月库存商品存货额', content: '企业主要营业产品财务收入', path: '/financial/indicators28' },
        { id: '30', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', content: '企业主要营业产品财务收入', path: '/financial/indicators30' },
        { id: '32', icon: 'el-icon-s-data', title: '应收账款周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators32' },
        { id: '33', icon: 'el-icon-s-data', title: '资金周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators33' },
        { id: '34', icon: 'el-icon-s-data', title: '库存商品周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators34' },
        { id: '35', icon: 'el-icon-s-data', title: '原材料周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators35' },
        { id: '36', icon: 'el-icon-s-data', title: '在制品周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators36' },
        { id: '66', icon: 'el-icon-s-data', title: '一年以上暂估行项目', content: '企业主要营业产品财务收入', path: '/financial/indicators66' },
        { id: '70', icon: 'el-icon-s-data', title: '当日在制品金额', content: '企业主要营业产品财务收入', path: '/financial/indicators70' },
        { id: '77', icon: 'el-icon-s-data', title: '月度存货金额', content: '企业主要营业产品财务收入', path: '/financial/indicators77' },
        { id: '78', icon: 'el-icon-s-data', title: '当月经济增加值', content: '企业主要营业产品财务收入', path: '/financial/indicators78' },
      ],
      enterpriseOperatingIndicators: [
        { id: '1', icon: 'el-icon-s-data', title: '主营业务收入', content: '企业主要营业产品财务收入', path: '/financial/indicators1' },
        { id: '2', icon: 'el-icon-s-data', title: '整机销售收入', content: '企业主要营业产品财务收入', path: '/financial/indicators2' },
        { id: '3', icon: 'el-icon-s-data', title: '集团外部销售收入', content: '企业主要营业产品财务收入', path: '/financial/indicators3' },
        { id: '4', icon: 'el-icon-s-data', title: '整车产量', content: '企业主要营业产品财务收入', path: '/financial/indicators4' },
        { id: '5', icon: 'el-icon-s-data', title: '整车销量', content: '企业主要营业产品财务收入', path: '/financial/indicators5' },
        { id: '6', icon: 'el-icon-s-data', title: '新产品销售收入', content: '企业主要营业产品财务收入', path: '/financial/indicators6' },
        { id: '7', icon: 'el-icon-s-data', title: '特色产品收入', content: '企业主要营业产品财务收入', path: '/financial/indicators7' },
        { id: '8', icon: 'el-icon-s-data', title: '主营业务成本', content: '企业主要营业产品财务收入', path: '/financial/indicators8' },
        { id: '9', icon: 'el-icon-s-data', title: '整机销售成本', content: '企业主要营业产品财务收入', path: '/financial/indicators9' },
        { id: '10', icon: 'el-icon-s-data', title: '净利润', content: '企业主要营业产品财务收入', path: '/financial/indicators10' },
        { id: '78', icon: 'el-icon-s-data', title: '当月经济增加值', content: '企业主要营业产品财务收入', path: '/financial/indicators78' },
      ],
      costIndicators: [
        { id: '20', icon: 'el-icon-s-data', title: '当月管理费用', content: '企业主要营业产品财务收入', path: '/financial/indicators20' },
        { id: '21', icon: 'el-icon-s-data', title: '当月研发费用', content: '企业主要营业产品财务收入', path: '/financial/indicators21' },
        { id: '22', icon: 'el-icon-s-data', title: '当月制造费用', content: '企业主要营业产品财务收入', path: '/financial/indicators22' },
        { id: '26', icon: 'el-icon-s-data', title: '当月原材料存货额', content: '企业主要营业产品财务收入', path: '/financial/indicators26' },
        { id: '27', icon: 'el-icon-s-data', title: '当月在制品存货额', content: '企业主要营业产品财务收入', path: '/financial/indicators27' },
        { id: '28', icon: 'el-icon-s-data', title: '当月库存商品存货额', content: '企业主要营业产品财务收入', path: '/financial/indicators28' },
        { id: '30', icon: 'el-icon-s-data', title: '存货增长率/销售增长率', content: '企业主要营业产品财务收入', path: '/financial/indicators30' },
        { id: '70', icon: 'el-icon-s-data', title: '当日在制品金额', content: '企业主要营业产品财务收入', path: '/financial/indicators70' },
        { id: '77', icon: 'el-icon-s-data', title: '月度存货金额', content: '企业主要营业产品财务收入', path: '/financial/indicators77' },
      ],
      efficiencyIndicators: [
        { id: '32', icon: 'el-icon-s-data', title: '应收账款周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators32' },
        { id: '33', icon: 'el-icon-s-data', title: '资金周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators33' },
        { id: '34', icon: 'el-icon-s-data', title: '库存商品周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators34' },
        { id: '35', icon: 'el-icon-s-data', title: '原材料周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators35' },
        { id: '36', icon: 'el-icon-s-data', title: '在制品周转率', content: '企业主要营业产品财务收入', path: '/financial/indicators36' },
        { id: '66', icon: 'el-icon-s-data', title: '一年以上暂估行项目', content: '企业主要营业产品财务收入', path: '/financial/indicators66' },
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

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
        { id: '49', icon: 'el-icon-s-data', title: '一线从业人数', content: '企业主要营业产品财务收入', path: '/enterprise/indicators49' },
        { id: '51', icon: 'el-icon-s-data', title: '公司平均从业人数（月度）', content: '企业主要营业产品财务收入', path: '/enterprise/indicators51' },
        // { id: '52', icon: 'el-icon-s-data', title: '工资总额年度占比', content: '企业主要营业产品财务收入', path: '/enterprise/indicators52' },
        // { id: '53', icon: 'el-icon-s-data', title: '日清日结考核得分', content: '企业主要营业产品财务收入', path: '/enterprise/indicators53' },
        // { id: '54', icon: 'el-icon-s-data', title: '累计人均收入', content: '企业主要营业产品财务收入', path: '/enterprise/indicators54' },
        // { id: '55', icon: 'el-icon-s-data', title: '月度累计生产人均收入', content: '企业主要营业产品财务收入', path: '/enterprise/indicators55' },
        // { id: '56', icon: 'el-icon-s-data', title: '月度累计职能人均收入', content: '企业主要营业产品财务收入', path: '/enterprise/indicators56' },
        // { id: '76', icon: 'el-icon-s-data', title: '职能部门人均加班费用', content: '企业主要营业产品财务收入', path: '/enterprise/indicators76' },
        // { id: '暂无', icon: 'el-icon-s-data', title: '公司平均从业人数（年度）', content: '企业主要营业产品财务收入', path: '/enterprise/indicators47' },
        // { id: '暂无', icon: 'el-icon-s-data', title: '11项管理指标-单个指标得分', content: '企业主要营业产品财务收入', path: '/enterprise/indicators47' },
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

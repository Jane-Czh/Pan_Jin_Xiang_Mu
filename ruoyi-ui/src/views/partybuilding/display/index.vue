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
        { id: '50', icon: 'el-icon-s-data', title: '党建月度考核排名', content: '党建月度考核排名', path: '/partybuilding/indicators50' },
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

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
import { getTargetData } from '@/api/financial/target'
export default {
  components: {},
  props: [],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '47', showTarget: 'tech', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'nonStandardAVGPreparationDays', icon: 'el-icon-s-data', title: '非标订单平均技术准备天数', content: '非标订单平均技术准备天数', path: '/tech/indicators47' },
        { id: '71', showTarget: 'tech', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'prdScheduleCompletionRate', icon: 'el-icon-s-data', title: '研发项目计划进度完成率', content: '研发项目计划进度完成率', path: '/tech/indicators71' },
      ],
      formData: {},
      rules: {},
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
      let target = {
        date: new Date(),
        deptName: 'tech',
      }
      const resTarget = await getTargetData(target)
      //目标值赋予及上下限预警
      this.allIndex.forEach(item => {
        resTarget.rows.forEach(row => {
          if (item.sum === row.indicatorName) {
            item.targetValue = row.targetValue;
            item.targetValueDate = row.natureYear;
            // if (allTargetData[item.sum] < row.targetLowerLimit || allTargetData[item.sum] > row.targetUpperLimit) {
            //   item.showWarning = true;
            // }
          }
        });
      });
    },
    toDetail(item) {
      this.$router.push({
        path: item.path,
        query: {
          data: JSON.stringify(item),
        }
      })
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

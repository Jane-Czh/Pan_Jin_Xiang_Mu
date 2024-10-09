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
import { getTechNewData } from '@/api/tech/data'
import moment from 'moment'
export default {
  components: {},
  props: [],
  data() {
    return {
      activeName: 'first',
      allIndex: [
        { id: '47', showTarget: 'tech', date: '', type: '天', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'nonStandardAvgPreparationDays', icon: 'el-icon-s-data', title: '非标订单平均技术准备天数', content: '非标订单平均技术准备天数', path: '/tech/indicators47' },
        { id: '71', showTarget: 'tech', date: '', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: 'prdScheduleCompletionRate', icon: 'el-icon-s-data', title: '项目完成情况', content: '项目完成情况', path: '/tech/indicators71' },
        { id: '106', showTarget: 'tech', date: '', type: '元', showWarning: false, targetValue: 0, targetValueDate: '', sum: '', icon: 'el-icon-s-data', title: '项目进度情况', content: '项目进度情况', path: '/tech/indicators106' },
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

      const res = await getTechNewData()
      this.newData = res.data

      //页面截至
      this.allIndex.forEach(item => {
        item.date = res && res.data ? moment(res.data.yearAndMonth).format('YYYY-MM') : '--';
      });
      //卡片content
      this.allIndex.forEach(item => {
        console.log(item.id)
        if (item.id === '47') {
          item.content = `最新一月：平均天数：${this.newData['nonStandardAvgPreparationDays'] || '-'} / 总条数：${this.newData['nonStandardNum'] || '-'} / 增幅率：${this.newData['nonStandardOrderGrowthRate'] || '-'}`;
        } else if (item.id === '71') {
          item.content = `最新一月：总数：${this.newData['totalProjectCount'] || '-'} / 占比率：${this.newData['projectPointsPercentage'] || '-'}`;
        } else if (item.id === '106') {
          item.content = `最新一月：已完成：${this.newData['completedProjectCount'] || '-'} / 进行中：${this.newData['ongoingProjectCount'] || '-'} / 暂未开展：${this.newData['unstartedProjectCount'] || '-'}`;
        } else {
          console.warn(`Not found in response data.`);
        }
      });

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


  .footer-content {
    display: flex;
    justify-content: space-between;
    /* 文本靠左，按钮靠右 */
    align-items: center;
  }
}
</style>

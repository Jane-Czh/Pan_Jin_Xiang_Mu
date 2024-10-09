<template>
  <div>
    <div class="block">
      <span class="DataSelect" style="margin-right:10px">日期选择</span>
      <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
        start-placeholder="开始月份" end-placeholder="结束月份" @change="handleDateChange">
      </el-date-picker>
    </div>
    <div>

    </div>
    <div v-if="loading"
      style="display: flex; justify-content: center; align-items: center; height: 50vh; font-size: 24px;">加载中……</div>
    <indicator-chart v-else :title="option.title" :dataName="option.dataName" :xAxisData="xAxisData"
      :yAxisData="yAxisData" :targetValue="option.targetValue" :showTarget="option.showTarget"
      :dataOfTarget="dataOfTarget"></indicator-chart>
  </div>
</template>

<script>
import moment from 'moment'
import chartAPI from '@/api/quality/chartAPI.js'
import indicatorChart from '@/views/financial/financialDisplay/indexChart/indicatorChart.vue';
import { getNameTarget } from '@/api/financial/target'
export default {
  components: { indicatorChart },
  data() {
    return {
      loading: false,
      data: [],
      timeData: {
        startTime: new Date(),
        endTime: new Date(),
      },
      selectedDate: [],
      endSelectedDate: null,
      pickerOptions: [],
      xAxisData: [],
      yAxisData: [],
      dataOfTarget: [],
      option: { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, showTarget: 'quality' }
    }
  },
  computed: {},
  mounted() {
    this.option = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, showTarget: 'quality' }
    this.$route.meta.title = `指标${this.option.id}: ${this.option.title}`
    this.$store.dispatch('tagsView/editVisitedViews', this.$route)
    this.defaultMonth()
    this.initData()
  },
  methods: {
    async initData() {

      this.timeData.startTime = this.selectedDate[0]
      this.timeData.endTime = this.selectedDate[1]


      try {
        this.loading = true
        const res = await chartAPI[this.option.apiName](this.timeData);
        this.data = res.rows
        this.xAxisData = res.rows.map(item => moment(item.yearAndMonth).format('YY-MM'))
        this.yAxisData = res.rows.map(item => item[this.option.yDataName])

        //目标值
        let newTarget = {
          name: this.option.sum,
          startDate: this.timeData.startTime,
          endDate: this.timeData.endTime
        }
        const tmp = await getNameTarget(newTarget)
        let nowTarget = tmp.rows
        let allTarget = []; // 初始化目标数组
        nowTarget.forEach(item => {
          let natureYear = item.natureYear = moment(item.natureYear).format('YYYY')
          let targetValue = item.targetValue; // 目标值可能是数字或null
          allTarget.push({ natureYear, targetValue });
        })
        this.data.forEach(item => {
          const year = moment(item.yearAndMonth).format('YYYY')
          allTarget.forEach(row => {
            if (year === row.natureYear) {
              item.targetValue = row.targetValue
            }
          })
        });
        console.log(this.data)
        this.dataOfTarget = this.data

        this.loading = false
      } catch (error) {
        console.log(error)
        this.loading = false
      }
    },
    handleDateChange(value) {
      if (value && value[1]) {
        let endDate = new Date(value[1]);
        endDate.setMonth(endDate.getMonth() + 1);
        endDate.setDate(0);
        this.selectedDate[1] = endDate;
      }
      this.initData()
    },
    defaultMonth() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear, 0, 1);
      const endDate = new Date(currentYear, currentMonth, 0);
      this.selectedDate = [startDate, endDate];
    },
  },
}





</script>

<style lang="scss" scoped>
.el-radio-group {
  margin: 50px;
  margin-bottom: 0;
}

#main {
  width: 1000px;
  height: 600px;
  margin: 40px auto;
}

.block {
  margin-top: 50px;
  text-align: center;
}
</style>

<template>
  <div>
    <div class="block">
      <span class="DataSelect" style="margin-right:10px">日期选择</span>
      <el-date-picker v-model="selectedDate" type="monthrange" unlink-panels range-separator="至"
        start-placeholder="开始月份" end-placeholder="结束月份" @change="handleDateChange">
      </el-date-picker>
    </div>
    <div>
      <br>
      <div class="centered-value">
        当前累计值：{{ this.currentSum }} (万元)
      </div>
    </div>
    <div v-if="loading"
      style="display: flex; justify-content: center; align-items: center; height: 50vh; font-size: 24px;">加载中……</div>
    <indicator-chart v-else :title="option.title" :dataName="option.dataName" :xAxisData="xAxisData"
      :yAxisData="yAxisData" :targetValue="option.targetValue" :targetValueDate="option.targetValueDate"
      :showTarget="option.showTarget"></indicator-chart>
  </div>
</template>


<script>
import moment from 'moment'
import chartAPI from '@/api/financial/chartAPI.js'
import indicatorChart from './indexChart/indicatorChart.vue';


export default {
  components: { indicatorChart },
  name: 'indexDetailFinancial',

  data() {
    return {
      radioDate: '月',
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
      currentSum: [],
      option: { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '', showTarget: '' },
      name: '',
    }

  },
  computed: {},
  mounted() {
    this.option = this.$route.query.data ? JSON.parse(this.$route.query.data) : { id: '', title: '', dataName: '', apiName: '', yDataName: '', targetValue: 0, targetValueDate: '', showTarget: false }
    this.$route.meta.title = `指标${this.option.id}: ${this.option.title}`
    this.$store.dispatch('tagsView/editVisitedViews', this.$route)
    this.defaultMonth()
    this.initData()
  },
  created() {

  },
  methods: {
    async initData() {

      this.timeData.startTime = this.selectedDate[0]
      this.timeData.endTime = this.selectedDate[1]

      try {
        this.loading = true;
        const res = await chartAPI[this.option.apiName](this.timeData);
        this.data = res.rows;
        this.xAxisData = res.rows.map(item => moment(item.Year_And_Month).format('YY-MM'));
        this.yAxisData = res.rows.map(item => item[this.option.yDataName]);
        this.currentSum = this.yAxisData.reduce((a, b) => a + b, 0)
        this.currentSum = this.formatNumber(this.currentSum)
        this.loading = false;
      } catch (error) {
        this.$modal.msgError("数据加载失败")
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
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
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

.centered-value {
  display: flex;
  justify-content: center;

  align-items: center;

  margin-top: 10px;

}
</style>

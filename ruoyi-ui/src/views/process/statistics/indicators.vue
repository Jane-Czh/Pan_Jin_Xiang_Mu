<template>
  <div>
    <div class="block">
      <span class="DataSelect" style="margin-right: 10px">日期选择</span>
      <el-date-picker
        v-model="selectedDate"
        type="monthrange"
        unlink-panels
        range-separator="至"
        start-placeholder="开始月份"
        end-placeholder="结束月份"
        :picker-options="pickerOptions"
        @change="handleDateChange"
      >
      </el-date-picker>
      <span class="DataSelect" style="margin-right: 100px"
        >当前期间变更次数：{{ this.totalCounts }} 次</span
      >
    </div>

    <div id="main" ref="main"></div>
  </div>
</template>

<script>
import * as echarts from "echarts";
import moment from "moment";
import { getMainRevenueData } from "@/api/financial/data";

//获取流程相关的api
import { getProjectName, getIndicatorUpdateCounts } from "@/api/system/project";

export default {
  data() {
    return {
      // 时间选择
      historyStartTime: [],

      // 流程表格数据
      historyProjectList: [],

      loading: false,
      data: [],
      //当前时间期间内统计的流程变更总次数
      totalCounts: 0,
      //当前流程的名称
      projectName: "",

      timeData: {
        id: null,
        startTime: new Date(),
        endTime: new Date(),
      },
      selectedDate: [],
      pickerOptions: [],
      option: {},
      myChart: {},
    };
  },
  computed: {},
  mounted() {
    this.defaultMonth();
    this.myChart = echarts.init(document.getElementById("main"));
    this.initData();
    this.getThisProjectName();
  },
  methods: {
    /** 查询当前流程的name */
    getThisProjectName() {
      const id = this.$route.params.id;
      getProjectName(id).then((res) => {
        this.projectName = res;
        // console.log("this.projectName===>", this.projectName);
      });
    },

    // -----------------------------------------------------------------------

    async initData() {
      const id = this.$route.params.id;
      this.timeData.id = id;
      this.timeData.startTime = this.selectedDate[0];
      this.timeData.endTime = this.selectedDate[1];
      try {
        this.loading = true;
        //在选择的时间区间内刷新数据
        // const res = await getMainRevenueData(this.timeData);

        const res = await getIndicatorUpdateCounts(this.timeData);
        // console.log("res===>", res);
        let counts = 0;
        res.map((item) => {
          counts += item.times;
        });
        // console.log("counts===>", counts);
        this.totalCounts = counts;
        /**
         * 返回的数据格式
         * times: 2      --数据
         * yearAndMonth: "2024-01-01"  --时间,展示时只取 年和月
         * updateDates: 更具体的更新日期
         */

        this.data = res;
        this.loading = false;
        // 更新图表显示
        this.updateChart();
      } catch (error) {
        this.loading = false;
      }
    },
    handleDateChange() {
      this.initData();
    },
    updateChart() {
      var app = {};
      const posList = [
        "left",
        "right",
        "top",
        "bottom",
        "inside",
        "insideTop",
        "insideLeft",
        "insideRight",
        "insideBottom",
        "insideTopLeft",
        "insideTopRight",
        "insideBottomLeft",
        "insideBottomRight",
      ];
      app.configParameters = {
        rotate: {
          min: -90,
          max: 90,
        },
        align: {
          options: {
            left: "left",
            center: "center",
            right: "right",
          },
        },
        verticalAlign: {
          options: {
            top: "top",
            middle: "middle",
            bottom: "bottom",
          },
        },
        position: {
          options: posList.reduce(function (map, pos) {
            map[pos] = pos;
            return map;
          }, {}),
        },
        distance: {
          min: 0,
          max: 100,
        },
      };
      app.config = {
        rotate: 0,
        align: "center",
        verticalAlign: "middle",
        position: "top",
        distance: 15,
        onChange: function () {
          const labelOption = {
            rotate: app.config.rotate,
            align: app.config.align,
            verticalAlign: app.config.verticalAlign,
            position: app.config.position,
            distance: app.config.distance,
          };
          this.myChart.setOption({
            series: [
              {
                label: labelOption,
              },
              {
                label: labelOption,
              },
              {
                label: labelOption,
              },
              {
                label: labelOption,
              },
            ],
          });
        },
      };
      const labelOption = {
        show: true,
        position: app.config.position,
        distance: app.config.distance,
        align: app.config.align,
        verticalAlign: app.config.verticalAlign,
        rotate: app.config.rotate,
        formatter: "{c}",
        fontSize: 16,
        rich: {
          name: {},
        },
      };
      this.option = {
        title: {
          text: "流程 "+this.projectName+" 更新频率统计 / 月",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: {
            type: "shadow",
          },
        },
        // legend: {
        //     data: ['更新次数']
        // },
        toolbox: {
          show: true,
          orient: "vertical",
          left: "right",
          top: "center",
          feature: {
            mark: { show: true },
            dataView: { show: true, readOnly: false, title: "数据视图" },
            magicType: {
              show: true,
              type: ["bar"],
              title: { bar: "切换为柱状图" },
            },
            restore: { show: true, title: "还原" },
            saveAsImage: { show: true, title: "保存为图片" },
          },
        },
        xAxis: [
          {
            type: "category",
            axisTick: { show: false },
            data: this.data.map((item) =>
              moment(item.yearAndMonth).format("YYYY-MM")
            ),
          },
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              formatter: function (value) {
                return parseInt(value);
              },
            },
            interval: 1, // 设置刻度之间的间隔为1，确保每个整数值都有刻度
          },
        ],
        series: [
          {
            name: "更新次数",
            type: "line",
            label: labelOption,
            emphasis: {
              focus: "series",
            },
            data: this.data.map((item) => item.times),
          },
        ],
      };

      this.option && this.myChart.setOption(this.option);
    },

    //时间选择器的默认月份设置
    defaultMonth() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear, 0, 1);
      const endDate = new Date(currentYear, currentMonth, 0);

      //转换this.historyStartTime 为日期选择器符合的格式
      // const year = this.historyStartTime.getFullYear();
      // const month = String(this.historyStartTime.getMonth() + 1).padStart(2, "0"); // 月份是从0开始计数，需要加1
      // const day = String(this.historyStartTime.getDate()).padStart(2, "0");

      // // 拼接成所需的日期格式
      // const formattedDate = `${year}-${month}-${day}`;
      // const startDate = new Date(formattedDate, 0, 1);

      this.selectedDate = [startDate, endDate];
      // console.log("this.selectedDat=====", this.selectedDate);
    },
  },
};
</script>

<style lang="scss" scoped>
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

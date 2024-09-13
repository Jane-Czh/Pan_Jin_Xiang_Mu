<template>
  <div class="app-container">
    <div class="box">
      制度总数：{{ regulationCount }}
    </div>
    <div class="box">
      流程总数：{{ processCount }}
    </div>
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
    </div>
    <div id="regulationchart1" class="box">区域 3</div>
    <div class="box">区域 4</div>
    <div id="regulationchart2" class="box"></div>
    <div class="box">区域 6</div>
  </div>
</template>

<script>
import {getRegulationCounts, listDept02, listFilemanagement} from "@/api/file/filemanagement";
import {listProject2} from "@/api/system/project";
import * as echarts from "echarts";
import {getRegulationsImplementationRateByData} from "@/api/file/regulationsImplementation";
import moment from "moment/moment";

export default {
  name: "dataOverview",
  data() {
    return {
      departments: [],  //部门列表
      timeData: {
        startTime: new Date(),
        endTime: new Date(),
      },
      selectedDate: [],
      pickerOptions: [],
      option: {},
      myRegulationChart1: {}, //制度柱状图
      myRegulationChart2: {}, //制度饼图
      data: [],
      //当前时间期间内统计的制度总次数
      totalCounts: 0,

      regulationCount: 0,  //制度总数
      processCount: 0,     //流程总数
      queryParams: {},
    }
  },
  mounted() {
    this.getDeptList();
    this.getRegulationCounts();
    this.defaultMonth();
    this.myRegulationChart1 = echarts.init(document.getElementById("regulationchart1"));
    this.myRegulationChart2 = echarts.init(document.getElementById("regulationchart2"));
    this.initRegulationChart1Data();
    // this.getThisProjectName();
  },
  created() {

  },
  methods: {
    getRegulationCounts() {
      listFilemanagement(this.queryParams).then(response => {
        this.regulationCount = response.total;
      })
    },
    async initRegulationChart1Data() {
      this.timeData.startTime = this.selectedDate[0];
      // 获取该月的最后一天
      const endOfMonth = new Date(this.selectedDate[1].getFullYear(), this.selectedDate[1].getMonth() + 1, 0);
      this.timeData.endTime = endOfMonth;
      // this.timeData.endTime = this.selectedDate[1];
      console.log("startTime=>",this.timeData.startTime);
      console.log("endTime=>",this.timeData.endTime);
      try {
        this.loading = true;
        //在选择的时间区间内刷新数据
        // const res = await getMainRevenueData(this.timeData);

        const res = await getRegulationCounts(this.timeData);
        console.log("res===>", res);
        let counts = 0;
        // res.map((item) => {
        //   counts += item.times;
        // });
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
        // 处理数据，确保所有部门都有对应的制度数量，不存在的部门设置为 0
        this.processData();
        // 更新图表显示
        this.updateRegulationChart1();
      } catch (error) {
        this.loading = false;
      }
    },
    // 处理数据，确保所有部门都有对应的制度数量，不存在的部门设置为 0
    processData() {
      // 初始化部门制度数量为 0
      const deptMap = {};
      this.departments.forEach(dept => {
        deptMap[dept] = 0;
      });

      // 将查询结果中存在的部门制度数量添加到映射中
      this.data.forEach(item => {
        const dept = item.mainResponsibleDepartment;
        if (deptMap.hasOwnProperty(dept)) {
          deptMap[dept] = item.times; // 假设 item.times 是制度数量
        }
      });

      // 将映射转换为处理后的数据，用于图表显示
      this.data = Object.keys(deptMap).map(dept => ({
        mainResponsibleDepartment: dept,
        times: deptMap[dept],
      }));
    },
    /** 查询部门列表 */
    getDeptList() {
      listDept02().then((response) => {
        console.log("response======>",response);
        // 过滤掉 deptName 为 "产品研发"、"研发"、"测试" 和 "总部" 的部门
        const filteredData = response.data.filter(
          (department) =>
            department.deptName !== "产品研发" &&
            department.deptName !== "研发" &&
            department.deptName !== "测试" &&
            department.deptName !== "总部" &&
            department.deptName !== "合力（盘锦）"
        );

        // 将每个过滤后的部门的 deptName 放入 departments 数组
        this.departments = filteredData.map(
          (department) => department.deptName
        );
        console.log("this.departments======>",this.departments);
      });
    },
    handleDateChange() {
      this.initRegulationChart1Data();
    },

    updateRegulationChart1() {
      const formattedData = this.data.map((item) => {
        return {
          mainResponsibleDepartment: item.mainResponsibleDepartment,
          times: item.times,

        };
      });

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
        rotate: { min: -90, max: 90 },
        align: { options: { left: "left", center: "center", right: "right" } },
        verticalAlign: {
          options: { top: "top", middle: "middle", bottom: "bottom" },
        },
        position: {
          options: posList.reduce((map, pos) => {
            map[pos] = pos;
            return map;
          }, {}),
        },
        distance: { min: 0, max: 100 },
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
          this.myRegulationChart1.setOption({
            series: [
              { label: labelOption },
              { label: labelOption },
              { label: labelOption },
              { label: labelOption },
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
        formatter: function (params) {
          return params.data.times;
        },
        fontSize: 16,
        rich: { name: {} },
      };
      this.option = {
        title: {
          text: "各部门制度数量",
          left: "center", // 居中显示
        },
        tooltip: {
          trigger: "axis",
          axisPointer: { type: "shadow" },
          // formatter: function (params) {
          //   const data = params[0].data;
          //   const updateDates =
          //     data.updateDates.length > 0
          //       ? data.updateDates.join("<br/>")
          //       : "无更新记录";
          //   return `具体更新时间:<br/>${updateDates}`;
          // },
        },
        // toolbox: {
        //   show: true,
        //   orient: "vertical",
        //   left: "right",
        //   top: "center",
        //   feature: {
        //     mark: { show: true },
        //     dataView: { show: true, readOnly: false, title: "数据视图" },
        //     magicType: {
        //       show: true,
        //       type: ["bar"],
        //       title: { bar: "切换为柱状图" },
        //     },
        //     restore: { show: true, title: "还原" },
        //     saveAsImage: { show: true, title: "保存为图片" },
        //   },
        // },
        xAxis: [
          {
            type: "category",
            axisTick: { show: false },
            data: this.departments,
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
            interval: 1,
          },
        ],
        series: [
          {
            name: "制度总数",
            type: "bar",
            label: labelOption,
            emphasis: { focus: "series" },
            data: formattedData.map((item) => ({
              value: item.times,
              times: item.times,
              // updateDates: item.updateDates,
            })),
          },
        ],
      };

      this.option && this.myRegulationChart1.setOption(this.option);
    },
    //时间选择器的默认月份设置
    defaultMonth() {
      const currentDate = new Date();
      const currentYear = currentDate.getFullYear();
      const currentMonth = currentDate.getMonth() + 1;
      const startDate = new Date(currentYear, 0, 1);
      const endDate = new Date(currentYear, currentMonth, 0);

      this.selectedDate = [startDate, endDate];
    },

    // getProcessCounts() {
    //   listProject2(this.queryParams).then(response => {
    //     this.processCount = response.total;
    //   })
    // },


  }
}
</script>

<style scoped>
.app-container {
  display: flex;
  flex-wrap: wrap; /* 自动换行 */
  gap: 20px; /* 每个区域之间的间距 */
}

.box {
  flex: 1 1 40%; /* 每个区域占 30% 宽度 */
  //background-color: #f2f2f2;
  text-align: center;
  padding: 20px;
  border-radius: 4px;
  min-width: 200px; /* 设置最小宽度，防止过小 */
  height: 400px; /* 设置每个容器的高度为父容器的百分比 */
}

.block {
  width: 100%; /* 使 block 占据整个宽度 */
  text-align: center;
  margin: 20px 0; /* 设置上下间距 */
}
</style>

<template>
  <div class="app-container">
    <div class="block">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" label-width="68px">
        <el-form-item label="日期" prop="updateDate">
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
        </el-form-item>
        <el-form-item label="主责部门" prop="mainResponsibleDepartment" >
          <el-select
            v-model="queryParams.mainResponsibleDepartment"
            placeholder="请选择主责部门"
            clearable
            @change="handleDepartmentChange"
          >
            <el-option
              v-for="item in departments"
              :key="item"
              :label="item"
              :value="item"
            />
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <div class="box1">
      制度总数：{{ regulationCount }}
    </div>
    <div class="box2">
      流程总数：{{ pcount }}
      流程A数：{{ pcountA }}
      流程B数：{{ pcountB }}

    </div>
    <div id="regulationchart1" class="box"></div>
    <div id="pchart" class="box">区域 4</div>
    <div id="regulationchart2" class="box"></div>
    <div id="processchart2" class="box">区域 6</div>
  </div>
</template>

<script>
import {
  getRegulationCounts,
  getRegulationCountsByClassification,
  listDept02,
  listFilemanagement
} from "@/api/file/filemanagement";
import {listProject2} from "@/api/system/project";
import * as echarts from "echarts";
import {getRegulationsImplementationRateByData} from "@/api/file/regulationsImplementation";
import moment from "moment/moment";



//---流程
import { listProjectWithTime } from "@/api/system/project";
import {
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
} from "echarts/components";
import { BarChart } from "echarts/charts";
import { CanvasRenderer } from "echarts/renderers";

echarts.use([
  ToolboxComponent,
  TooltipComponent,
  GridComponent,
  LegendComponent,
  BarChart,
  CanvasRenderer,
]);

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
      myProcessChart2: {},    //流程饼图
      regulationChart1data: [],  //制度柱状图查询数据
      regulationChart2data: [],  //流程饼图查询数据
      data: [],
      //当前时间期间内统计的制度总次数
      totalCounts: 0,

      regulationCount: 0,  //制度总数
      processCount: 0,     //流程总数
      queryParams: {
        timeData: this.timeData,
        mainResponsibleDepartment: "",
      },

      //--流程总数 newest = 1
      pChartData: {}, //流程柱状图
      pcount: 0,
      Pdata: [],
      Pdata2: [],//统计流程A、B
      pcountA: 0,
      pcountB: 0,

    }
  },
  mounted() {
    this.getDeptList();
    this.defaultMonth();
    this.myRegulationChart1 = echarts.init(document.getElementById("regulationchart1"));
    this.myRegulationChart2 = echarts.init(document.getElementById("regulationchart2"));
    this.myProcessChart2 = echarts.init(document.getElementById("processchart2"));
    this.getList();

    //流程
    this.initPData();
    this.Pdata2();
    this.pChartData = echarts.init(document.getElementById("pchart"));
  },
  created() {

  },
  methods: {
    getList() {
      this.getRegulationCounts();
      this.initRegulationChart1Data();
      this.initRegulationChart2Data();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.selectedDate = [];
      this.getList();
    },
    getRegulationCounts() {
      listFilemanagement(this.queryParams).then(response => {
        this.regulationCount = response.total;
      })
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
      this.initRegulationChart2Data();
    },
    async handleDepartmentChange() {
      await this.getRegulationCounts();
      await this.initRegulationChart2Data();
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

        this.regulationChart1data = res;
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
      this.regulationChart1data.forEach(item => {
        const dept = item.mainResponsibleDepartment;
        if (deptMap.hasOwnProperty(dept)) {
          deptMap[dept] = item.times; // 假设 item.times 是制度数量
        }
      });

      // 将映射转换为处理后的数据，用于图表显示
      this.regulationChart1data = Object.keys(deptMap).map(dept => ({
        mainResponsibleDepartment: dept,
        times: deptMap[dept],
      }));
    },


    updateRegulationChart1() {
      const formattedData = this.regulationChart1data.map((item) => {
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
            axisLabel: {
              rotate: 60, // 设置标签旋转角度
            },
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

    async initRegulationChart2Data() {
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

        const res = await getRegulationCountsByClassification(this.timeData);
        console.log("res222===>", res);
        /**
         * 返回的数据格式
         * times: 2      --数据
         * yearAndMonth: "2024-01-01"  --时间,展示时只取 年和月
         * updateDates: 更具体的更新日期
         */

        this.regulationChart2data = res;
        this.loading = false;
        // 处理数据，确保所有部门都有对应的制度数量，不存在的部门设置为 0
        // this.processData();
        // 更新图表显示
        this.updateRegulationChart2();
        this.updateProcessChart2();
      } catch (error) {
        this.loading = false;
      }
    },
    updateRegulationChart2() {
      const formattedData = this.regulationChart2data.map((item) => {
        return {
          classificationOfSpecialties: item.classificationOfSpecialties,
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
          this.myRegulationChart2.setOption({
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
          text: "制度分类",
          left: "center", // 居中显示
        },
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            name: "制度总数",
            type: "pie",
            emphasis: { focus: "series" },
            data: formattedData.map((item) => ({
              value: item.times,
              name: item.classificationOfSpecialties,
            })),
          },
        ],
      };

      this.option && this.myRegulationChart2.setOption(this.option);
    },
    updateProcessChart2() {
      const formattedData = this.regulationChart2data.map((item) => {
        return {
          classificationOfSpecialties: item.classificationOfSpecialties,
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
          this.myRegulationChart2.setOption({
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
          text: "流程分类",
          left: "center", // 居中显示
        },
        tooltip: {
          trigger: 'item'
        },
        series: [
          {
            name: "流程总数",
            type: "pie",
            emphasis: { focus: "series" },
            data: formattedData.map((item) => ({
              value: item.times,
              name: item.classificationOfSpecialties,
            })),
          },
        ],
      };

      this.option && this.myProcessChart2.setOption(this.option);
    },

    //-------------------------------流程-----------------------------------------------

    async initPData() {
      this.timeData.startTime = this.selectedDate[0];
      // 获取该月的最后一天
      const endOfMonth = new Date(
        this.selectedDate[1].getFullYear(),
        this.selectedDate[1].getMonth() + 1,
        0
      );
      this.timeData.endTime = endOfMonth;
      // this.timeData.endTime = this.selectedDate[1];
      console.log("startTime=>", this.timeData.startTime);
      console.log("endTime=>", this.timeData.endTime);

      //   console.log("timeData===>", this.timeData);

      try {
        this.loading = true;
        //在时间区间[startTime, endTime] 刷新柱状图 各部门流程数量

        //获取总数
        const res = await listProjectWithTime(this.timeData).then(
          (response) => {
            this.Pdata = response;
            this.pcount = response.length;
          }
        );
        console.log("my xht 流程 this.Pdata1===>", this.Pdata);

        this.loading = false;

        // 处理数据，确保所有部门都有对应的制度数量，不存在的部门设置为 0
        this.processPData();
      } catch (error) {
        this.loading = false;
      }
    },

    // 处理数据，确保所有部门都有对应的制度数量，不存在的部门设置为 0
    processPData() {
      // 创建一个映射对象来统计每个 department 出现的次数
      const deptMap = {};
      this.departments.forEach((dept) => {
        deptMap[dept] = 0;
      });

      // 遍历 this.data 数组，统计 department 出现的次数
      this.Pdata.forEach((item) => {
        const department = item.department;
        if (deptMap[department]) {
          deptMap[department] += 1;
        } else {
          deptMap[department] = 1;
        }
      });

      // 将映射对象转换为 ECharts 所需的数据格式并保存回 this.data
      this.Pdata = Object.keys(deptMap).map((department) => {
        return {
          department: department,
          times: deptMap[department],
        };
      });

      // console.log("my xht 流程 this.Pdata2===>", this.Pdata);
      // 更新图表显示
      this.updatePChart();
    },

    processPData2() {
      const deptLevelMap = {};

      // 初始化每个科室的等级统计
      this.departments.forEach((dept) => {
        deptLevelMap[dept] = { A级: 0, B级: 0, C级: 0 };
      });

      // 统计 department 和 level 的数据
      this.Pdata2.forEach((item) => {
        const department = item.department;
        const level = item.level;
        if (deptLevelMap[department]) {
          deptLevelMap[department][level] += 1;
        }
      });

      // 转换数据为ECharts需要的格式
      this.Pdata2 = Object.keys(deptLevelMap).map((department) => {
        return {
          department: department,
          A: deptLevelMap[department]["A级"],
          B: deptLevelMap[department]["B级"],
          C: deptLevelMap[department]["C级"],
        };
      });

      // 遍历 Pdata 并统计 A级和 B级 的数量
      this.Pdata2.forEach((item) => {
        this.pcountA += item.A;
        this.pcountB += item.B;
      });

      // console.log("my xht 流程 this.Pdata2===>", this.Pdata);
      this.updatePChart();
    },

    //升级版本1
    // updatePChart() {
    //   const labelOption = {
    //     show: true,
    //     position: "insideBottom",
    //     distance: 15,
    //     align: "left",
    //     verticalAlign: "middle",
    //     rotate: 0,
    //     formatter: "{c}",
    //     fontSize: 16,
    //   };

    //   const seriesData = [
    //     {
    //       name: "A级",
    //       type: "bar",
    //       label: labelOption,
    //       data: this.Pdata.map((item) => item.A),
    //       itemStyle: {
    //         // color: "#FF5733", // A级的颜色
    //       },
    //       emphasis: {
    //         focus: "series",
    //       },
    //     },
    //     {
    //       name: "B级",
    //       type: "bar",
    //       label: labelOption,
    //       data: this.Pdata.map((item) => item.B),
    //       itemStyle: {
    //         // color: "#33FF57", // B级的颜色
    //       },

    //       emphasis: {
    //         focus: "series",
    //       },
    //     },
    //     {
    //       name: "C级",
    //       type: "bar",
    //       label: labelOption,
    //       data: this.Pdata.map((item) => item.C),
    //       itemStyle: {
    //         // color: "#3357FF", // C级的颜色
    //       },
    //       emphasis: {
    //         focus: "series",
    //       },
    //     },
    //   ];

    //   const option = {
    //     title: {
    //       text: "各部门流程数量",
    //       left: "center",
    //     },
    //     tooltip: {
    //       trigger: "axis",
    //       axisPointer: { type: "shadow" },
    //     },
    //     legend: {
    //       data: ["A级", "B级", "C级"],
    //       orient: "horizontal",
    //       left: "center",
    //       top: "15%",
    //     },
    //     toolbox: {
    //       show: true,
    //       feature: {
    //         mark: { show: true },
    //         dataView: { show: true, readOnly: false },
    //         magicType: { show: true, type: ["line", "bar", "stack"] },
    //         restore: { show: true },
    //         saveAsImage: { show: true },
    //       },
    //     },
    //     xAxis: [
    //       {
    //         // type: "category",
    //         axisTick: {
    //           show: false,
    //         },
    //         axisLabel: {
    //           rotate: 60, // 设置标签旋转角度
    //         },
    //         data: this.departments,
    //       },
    //     ],
    //     yAxis: [
    //       {
    //         type: "value",
    //         // min: 0, // 设置y轴最小值为0
    //         axisLabel: {
    //           show: false, // 隐藏y轴的标签
    //         },
    //         interval: 1,
    //       },
    //     ],
    //     series: seriesData,
    //   };

    //   this.pChartData.setOption(option);
    // },
    //原始版本1
    updatePChart() {
      const formattedData = this.Pdata.map((item) => {
        return {
          mainResponsibleDepartment: item.department,
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
          this.pChartData.setOption({
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
          text: "各部门流程数量",
          left: "center", // 居中显示
        },
        tooltip: {
          trigger: "axis",
          axisPointer: { type: "shadow" },
        },

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
            name: "流程总数",
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

      this.option && this.pChartData.setOption(this.option);
    },

  }
}
</script>

<style scoped>
.app-container {
  display: flex;
  flex-wrap: wrap; /* 自动换行 */
  gap: 20px; /* 每个区域之间的间距 */
}
.box1 {
  font-size: 24px; /* 设置字体大小 */
  flex: 1 1 40%; /* 每个区域占 30% 宽度 */
  //background-color: #f2f2f2;
  text-align: center;
  padding: 20px;
  border-radius: 4px;
  min-width: 200px; /* 设置最小宽度，防止过小 */
  height: 100px; /* 设置每个容器的高度为父容器的百分比 */
}
.box2 {
  font-size: 24px; /* 设置字体大小 */
  flex: 1 1 40%; /* 每个区域占 30% 宽度 */
  //background-color: #f2f2f2;
  text-align: center;
  padding: 20px;
  border-radius: 4px;
  min-width: 200px; /* 设置最小宽度，防止过小 */
  height: 100px; /* 设置每个容器的高度为父容器的百分比 */
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

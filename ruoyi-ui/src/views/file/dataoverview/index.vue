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
            v-model="selectedDepartment"
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
      制度总数：{{ totalRegulationCount }}
      <br>
      公司级数：{{ companyLevelCounts }}
      <br>
      部门级数：{{ departmentLevelCounts }}
    </div>
    <div class="box2">
      流程总数：{{ pcount }}
      <br>
      流程A数：{{ pcountA }}
      <br>
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
      selectedDepartment: [],  //选中的部门
      selectedDate: [],
      pickerOptions: [],
      option: {},
      myRegulationChart1: {}, //制度柱状图
      myRegulationChart2: {}, //制度饼图
      myProcessChart2: {},    //流程饼图
      regulationChart1data: [],  //制度柱状图查询数据
      regulationChart2data: [],  //制度饼图查询数据
      data: [],
      //当前时间期间内统计的制度总次数
      totalCounts: 0,

      regulationCount: 0,  //制度总数


      departmentLevelCounts: 0,  //部门级总数
      companyLevelCounts: 0,     //公司级总数
      totalRegulationCount: 0,  //制度总数
      processCount: 0,     //流程总数
      queryParams: {
        timeData: this.timeData,
        mainResponsibleDepartment: "",
      },

      //--流程总数 newest = 1
      pChartData: {}, //流程柱状图
      pcount: 0,
      Pdata: [],
      pcountA: 0,
      pcountB: 0,
      pcountC: 0,

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
    this.pChartData = echarts.init(document.getElementById("pchart"));
  },
  created() {

  },
  methods: {
    getList() {
      this.initRegulationChart1Data();
      this.initPData();
      this.initRegulationChart2Data();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.selectedDate = [];
      this.selectedDepartment = '';
      this.defaultMonth();
      this.getList();
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
      this.initPData();
    },
    handleDepartmentChange() {
      this.updateCounts();
      this.initRegulationChart2Data();
      this.initPData();
    },
    updateCounts() {
      // 过滤数据，若未选择部门，则计算全部
      const filteredData = this.selectedDepartment
        ? this.regulationChart1data.filter(item => item.mainResponsibleDepartment === this.selectedDepartment)
        : this.regulationChart1data;

      // 计算部门级总数
      this.departmentLevelCounts = filteredData
        .filter(item => item.regulationLevel === '部门级')
        .reduce((acc, item) => acc + item.times, 0);

      // 计算公司级总数
      this.companyLevelCounts = filteredData
        .filter(item => item.regulationLevel === '公司级')
        .reduce((acc, item) => acc + item.times, 0);

      // 计算制度总数
      this.totalRegulationCount = filteredData.reduce((acc, item) => acc + item.times, 0);
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
    // 计算制度总数，公司级总数，部门级总数
    processData() {

      // 初始化
      this.totalRegulationCount = 0;
      this.departmentLevelCounts = 0;
      this.companyLevelCounts = 0;

      this.regulationChart1data.forEach(item => {
        this.totalRegulationCount += item.times; // 统计制度总数
        if (item.regulationLevel === "部门级") {
          this.departmentLevelCounts += item.times; // 统计部门级制度
        } else if (item.regulationLevel === "公司级") {
          this.companyLevelCounts += item.times; // 统计公司级制度
        }
      });
    },


    updateRegulationChart1() {
      // 如果查询到的数据为空，默认设置为 0
      if (!this.regulationChart1data || this.regulationChart1data.length === 0) {
        this.regulationChart1data = [{
          mainResponsibleDepartment: '未知部门',
          regulationLevel: '部门级',
          times: 0
        }, {
          mainResponsibleDepartment: '未知部门',
          regulationLevel: '公司级',
          times: 0
        }];
      }
      // 按制度等级分组数据
      const groupedData = this.regulationChart1data.reduce((acc, item) => {
        const { mainResponsibleDepartment, regulationLevel, times } = item;
        if (!acc[regulationLevel]) {
          acc[regulationLevel] = [];
        }
        acc[regulationLevel].push({ mainResponsibleDepartment, times });
        return acc;
      }, {});

      console.log("groupedData===>", groupedData);
      const labelOptionInside = {
        show: true,
        position: 'inside',  // 设置数字显示在柱体内部
        formatter: function (params) {
          return params.data.times; // 显示各制度等级的数量
        },
        fontSize: 12,
        rich: { name: {} },
      };
      const labelOptionTop = {
        show: true,
        position: 'top',  // 设置数字显示在柱体顶部
        formatter: function (params) {
          const total = Object.values(groupedData).reduce((acc, levelData) => {
            const item = levelData.find(d => d.mainResponsibleDepartment === params.name);
            return acc + (item ? item.times : 0);
          }, 0);
          return total; // 显示部门总数
        },
        fontSize: 14,
        rich: { name: {} },
      };
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

      // // 获取不同制度等级的部门
      // const departments = [...new Set(this.regulationChart1data.map(item => item.mainResponsibleDepartment))];

      this.option = {
        title: {
          text: "各部门制度数量",
          left: "center",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: { type: "shadow" },
        },
        legend: {
          orient: "horizontal",   //水平排列
          left: "center",
          top: "10%",
        },
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
        series: Object.keys(groupedData).map(level => {
          return {
            name: level, // 使用制度等级作为系列名称
            type: "bar",
            stack: "总数",  // 设置相同的 stack 名称，表示堆叠
            label: labelOptionInside,  // 堆叠柱体内部显示制度等级的数量
            emphasis: { focus: "series" },
            data: this.departments.map(department => {
              const item = groupedData[level].find(d => d.mainResponsibleDepartment === department);
              return item ? item.times : 0; // 如果没有数据，默认设置为 0
            }),
          };
        }),

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

        const res = await getRegulationCountsByClassification(this.timeData,this.selectedDepartment);
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
      console.log("xxxhhhttt startTime=>", this.timeData.startTime);
      console.log("xxxhhhttt endTime=>", this.timeData.endTime);

      //   console.log("timeData===>", this.timeData);

      try {
        this.loading = true;
        //在时间区间[startTime, endTime] 刷新柱状图 各部门流程数量

        //获取总数
        const res = await listProjectWithTime(this.timeData).then(
          (response) => {
            this.Pdata = response;
            this.Pdata2 = response;
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
      const deptLevelMap = {};

      // 初始化每个科室的等级统计
      this.departments.forEach((dept) => {
        deptLevelMap[dept] = { A级: 0, B级: 0, C级: 0 };
      });

      // 统计 department 和 level 的数据
      this.Pdata.forEach((item) => {
        const department = item.department;
        const level = item.level;
        if (deptLevelMap[department]) {
          deptLevelMap[department][level] += 1;
        }
      });

      // 转换数据为ECharts需要的格式
      this.Pdata = Object.keys(deptLevelMap).map((department) => {
        return {
          department: department,
          A: deptLevelMap[department]["A级"],
          B: deptLevelMap[department]["B级"],
          C: deptLevelMap[department]["C级"],
        };
      });
      //
      // // 遍历 Pdata 并统计 A级和 B级 的数量
      // this.Pdata.forEach((item) => {
      //   this.pcountA += item.A;
      //   this.pcountB += item.B;
      // });


      // ----------------根据部门筛选计算流程总数--------------------
      let filteredData = this.Pdata;

      // 如果选择了某个部门，过滤数据
      if (this.selectedDepartment) {
        filteredData = this.Pdata.filter(
          (item) => item.department === this.selectedDepartment
        );
      }

      // 初始化计数
      this.pcount = 0;
      this.pcountA = 0;
      this.pcountB = 0;
      this.pcountC = 0;

      // 遍历数据并统计
      filteredData.forEach((item) => {
        this.pcountA += item.A;
        this.pcountB += item.B;
        this.pcountC += item.C;
        this.pcount += item.A + item.B + item.C; // 计算总流程数
      });

      console.log("my xht 流程 this.Pdata2===>", this.Pdata);
      this.updatePChart();
    },

    //升级版本1
    // 升级版本1 - 堆叠图
    updatePChart() {
      const labelOption = {
        show: true,
        position: "insideBottom",
        distance: 15,
        align: "left",
        verticalAlign: "middle",
        rotate: 0,
        formatter: "{c}",
        fontSize: 16,
      };
      const labelOptionInside = {
        show: true,
        position: 'inside',  // 设置数字显示在柱体内部
        fontSize: 12,
        rich: { name: {} },
      };

      const seriesData = [
        {
          name: "A级",
          type: "bar",
          label: labelOptionInside,
          data: this.Pdata.map((item) => item.A),
          stack: "总量",  // 堆叠图设置
          itemStyle: {
            // color: "#FF5733", // A级的颜色
          },
          emphasis: {
            focus: "series",
          },
        },
        {
          name: "B级",
          type: "bar",
          label: labelOptionInside,
          data: this.Pdata.map((item) => item.B),
          stack: "总量",  // 堆叠图设置
          itemStyle: {
            // color: "#33FF57", // B级的颜色
          },
          emphasis: {
            focus: "series",
          },
        },
        {
          name: "C级",
          type: "bar",
          label: labelOptionInside,
          data: this.Pdata.map((item) => item.C),
          stack: "总量",  // 堆叠图设置
          itemStyle: {
            // color: "#3357FF", // C级的颜色
          },
          emphasis: {
            focus: "series",
          },
        },
      ];

      const option = {
        title: {
          text: "各部门流程数量",
          left: "center",
        },
        tooltip: {
          trigger: "axis",
          axisPointer: { type: "shadow" },
        },
        legend: {
          data: ["A级", "B级", "C级"],
          orient: "horizontal",
          left: "center",
          top: "10%",
        },
        xAxis: [
          {
            axisTick: {
              show: false,
            },
            axisLabel: {
              rotate: 60, // 设置标签旋转角度
            },
            data: this.departments,
          },
        ],
        yAxis: [
          {
            type: "value",
            axisLabel: {
              show: false, // 隐藏y轴的标签
            },
            interval: 1,
          },
        ],
        series: seriesData,
      };

      this.pChartData.setOption(option);
    },

    //原始版本1
    // updatePChart() {
    //   const formattedData = this.Pdata.map((item) => {
    //     return {
    //       mainResponsibleDepartment: item.department,
    //       times: item.times,
    //     };
    //   });
    //
    //   var app = {};
    //   const posList = [
    //     "left",
    //     "right",
    //     "top",
    //     "bottom",
    //     "inside",
    //     "insideTop",
    //     "insideLeft",
    //     "insideRight",
    //     "insideBottom",
    //     "insideTopLeft",
    //     "insideTopRight",
    //     "insideBottomLeft",
    //     "insideBottomRight",
    //   ];
    //   app.configParameters = {
    //     rotate: { min: -90, max: 90 },
    //     align: { options: { left: "left", center: "center", right: "right" } },
    //     verticalAlign: {
    //       options: { top: "top", middle: "middle", bottom: "bottom" },
    //     },
    //     position: {
    //       options: posList.reduce((map, pos) => {
    //         map[pos] = pos;
    //         return map;
    //       }, {}),
    //     },
    //     distance: { min: 0, max: 100 },
    //   };
    //   app.config = {
    //     rotate: 0,
    //     align: "center",
    //     verticalAlign: "middle",
    //     position: "top",
    //     distance: 15,
    //     onChange: function () {
    //       const labelOption = {
    //         rotate: app.config.rotate,
    //         align: app.config.align,
    //         verticalAlign: app.config.verticalAlign,
    //         position: app.config.position,
    //         distance: app.config.distance,
    //       };
    //       this.pChartData.setOption({
    //         series: [
    //           { label: labelOption },
    //           { label: labelOption },
    //           { label: labelOption },
    //           { label: labelOption },
    //         ],
    //       });
    //     },
    //   };
    //   const labelOption = {
    //     show: true,
    //     position: app.config.position,
    //     distance: app.config.distance,
    //     align: app.config.align,
    //     verticalAlign: app.config.verticalAlign,
    //     rotate: app.config.rotate,
    //     formatter: function (params) {
    //       return params.data.times;
    //     },
    //     fontSize: 16,
    //     rich: { name: {} },
    //   };
    //   this.option = {
    //     title: {
    //       text: "各部门流程数量",
    //       left: "center", // 居中显示
    //     },
    //     tooltip: {
    //       trigger: "axis",
    //       axisPointer: { type: "shadow" },
    //     },
    //
    //     xAxis: [
    //       {
    //         type: "category",
    //         axisTick: { show: false },
    //         data: this.departments,
    //         axisLabel: {
    //           rotate: 60, // 设置标签旋转角度
    //         },
    //       },
    //     ],
    //     yAxis: [
    //       {
    //         type: "value",
    //         axisLabel: {
    //           formatter: function (value) {
    //             return parseInt(value);
    //           },
    //         },
    //         interval: 1,
    //       },
    //     ],
    //     series: [
    //       {
    //         name: "流程总数",
    //         type: "bar",
    //         label: labelOption,
    //         emphasis: { focus: "series" },
    //         data: formattedData.map((item) => ({
    //           value: item.times,
    //           times: item.times,
    //           // updateDates: item.updateDates,
    //         })),
    //       },
    //     ],
    //   };
    //
    //   this.option && this.pChartData.setOption(this.option);
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

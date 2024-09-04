<template>

  <div class="app-container">

    <div class="operation">

      <span class="DataSelect" style="margin-right:10px">部门选择</span>
      <el-select v-model="queryParams.selectedOption" placeholder="请选择部门" size="small" @change="handleDepartmentChange">
        <option disabled value="">请选择部门</option>
        <el-option
          v-for="option in departmentOptions"
          :key="option.value"
          :label="option.text"
          :value="option.value">
        </el-option>
      </el-select>

      <span class="DataSelect" style="margin-right:10px">日期选择</span>
      <el-date-picker
        v-model="selectedDate"
        type="daterange"
        align="right"
        unlink-panels
        range-separator="至"
        start-placeholder="立项时间"
        end-placeholder="结项时间"
        :picker-options="pickerOptions"
        size="small">
      </el-date-picker>

    </div>

    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主责部门" prop="department">
        <el-select
          v-model="queryParams.department"
          placeholder="请选择主责部门"
          clearable
          filterable
          @change="handleQuery"
        >
          <el-option
            v-for="item in departmentOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="项目等级" prop="level">
        <el-select
          v-model="queryParams.level"
          placeholder="请输入项目等级"
          clearable
          filterable
          @change="handleQuery"
        >
          <el-option
            v-for="item in levelOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="项目类别" prop="category">
        <el-select
          v-model="queryParams.category"
          placeholder="请输入项目类别"
          clearable
          filterable
          @change="handleQuery"
        >
          <el-option
            v-for="item in categoryOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['project:Info:add']">新增</el-button>
      </el-form-item>
    </el-form> -->

    <div class="echarts-wrapper">
      <div
        class="echart"
        ref="ProjectCategoriesProportion"
        id="ProjectCategoriesProportion"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="ProjectLevelDistribution"
        id="ProjectLevelDistribution"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="ProjectAverageSchedule"
        id="ProjectAverageSchedule"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
    </div>

    <div class="charts-container">
      <div ref="ProjectDevelopmentStatus" style="width: 600px; height: 400px;"></div>
      <div ref="DepartmentProjectStatistics" style="width: 600px; height: 400px;"></div>
    </div>





    <!-- <el-table v-loading="loading" :data="InfoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目类别" align="center" prop="category" />
      <el-table-column label="项目等级" align="center" prop="level" />
      <el-table-column label="主责部门" align="center" prop="department" />
      <el-table-column label="承接属性" align="center" prop="attribute" />
      <el-table-column label="项目描述" align="center" prop="description" />
      <el-table-column label="立项时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目总进度" align="center" prop="progressAlloverProgress" />
      <el-table-column label="导入时间" align="center" prop="importDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="remake" />
      <el-table-column label="历史项目" align="center" prop="oldProjectId" />
      <el-table-column label="关联时间" align="center" prop="associationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.associationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>


      <el-table-column label="负责人" align="center" prop="manager" />
      <el-table-column label="组成员" align="center" prop="teamMembers" />
      <el-table-column label="项目状态" align="center" prop="status" />
      <el-table-column label="项目进度" align="center" prop="progress" />
      <el-table-column label="项目现状" align="center" prop="currentStatus" />
      <el-table-column label="目标" align="center" prop="goal" />
      <el-table-column label="范围" align="center" prop="scope" />
      <el-table-column label="计划结项时间" align="center" prop="plannedCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="已过天数(自动计算)" align="center" prop="daysPassed" />
      <el-table-column label="剩余天数(自动计算)" align="center" prop="daysRemaining" />
      <el-table-column label="完成内容概述" align="center" prop="completionSummary" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:Info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:Info:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table> -->





  </div>
</template>

<script>
import { listInfo, getProjectInfo, delInfo, addInfo, updateInfo } from "@/api/project/info";
import PieChart from '@/components/PieChart.vue'; // 导入饼状图组件
import * as echarts from "echarts";
export default {
  name: "Info",
  components: {
    PieChart
  },
  data() {
    return {

      //展示数据
      ProjectCategoriesProportionData: [],  //项目类别占比
      isAllZeroProjectCategoriesProportion: false,
      ProjectLevelDistributionData: [],  //项目等级分布
      isAllZeroProjectLevelDistribution: false,
      ProjectAverageScheduleData: [],  //项目开展状态占比
      isAllZeroProjectAverageSchedule: false,
      ProjectDevelopmentStatusData: [],  //项目平均进度

      DepartmentProjectStatisticsData: [],  //部门项目统计

      // categorizedInfo: [], // 分类信息数据

      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 项目基本信息表格数据
      InfoList: [],
      rowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        category: null,
        level: null,
        department: null,
        attribute: null,
        description: null,
        startDate: null,
        progressAlloverProgress: null,
        importDate: null,
        remake: null,
        oldProjectId: null,
        associationDate: null,
        manager: null,
        teamMembers: null,
        status: null,
        progress: null,
        currentStatus: null,
        goal: null,
        scope: null,
        plannedCompletionTime: null,
        daysPassed: null,
        daysRemaining: null,
        completionSummary: null,
      },


      selectedOption: '', // 绑定选中的值
      selectedTime: [],
      shouldUpdateChart: false, // 控制图表是否需要更新

      //项目主责部门
      departmentOptions:[
        { value: '财务科', label: '财务科' },
        { value: '市场科', label: '市场科' },
        { value: '安环设备科', label: '安环设备科' },
        { value: '生产管理科', label: '生产管理科' },
        { value: '供应科', label: '供应科' },
        { value: '技术科', label: '技术科' },
        { value: '企业管理科', label: '企业管理科' },
        { value: '党群办公室', label: '党群办公室科' },
        { value: '质量科', label: '质量科' },
        { value: '执纪监督室', label: '执纪监督室' },
        { value: '团委', label: '团委' }
      ],
      //项目等级
      levelOptions:[
        { value: 'A级', label: 'A级' },
        { value: 'B级', label: 'B级' },
        { value: 'C级', label: 'C级' },
      ],
      //项目类别
      categoryOptions: [
        { value: '党工群团类', label: '党工群团类' },
        { value: '质量攻关类', label: '质量攻关类' },
        { value: '工艺技改类', label: '工艺技改类' },
        { value: '生产改善类', label: '生产改善类' },
        { value: '设备改善类', label: '设备改善类' },
        { value: '安全环保类', label: '安全环保类' },
        { value: '采购供应类', label: '采购供应类' },
        { value: '财务管理类', label: '财务管理类' },
        { value: '企业运行类', label: '企业运行类' },
      ],

      selectedDate: [],
      pickerOptions: [],

      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        category: [
          { required: true, message: "项目类别不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "项目等级不能为空", trigger: "blur" }
        ],
        department: [
          { required: true, message: "主责部门不能为空", trigger: "blur" }
        ],
        attribute: [
          { required: true, message: "承接属性不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "项目描述不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "立项时间不能为空", trigger: "blur" }
        ],
        progressAlloverProgress: [
          { required: true, message: "项目总进度不能为空", trigger: "blur" }
        ],
        importDate: [
          { required: true, message: "导入时间不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "项目状态不能为空", trigger: "blur" }
        ],
        progress: [
          { required: true, message: "项目进度不能为空", trigger: "blur" }
        ],
        plannedCompletionTime: [
          { required: true, message: "计划结项时间不能为空", trigger: "blur" }
        ],
      },

      //图表名称
      myChart1: null,
      myChart2: null,
      myChart3: null,
      myChart4: null,
      myChart5: null,
    };
  },
  created() {
    this.getList();
  },

  mounted() {

    // 加载数据后拆分成适合饼状图的格式
    listInfo(this.queryParams).then(response => {
      // 将后端返回的数据赋值给 reportList
      this.InfoList = response.rows;
      this.total = response.total;
      // 拆分数据为适合饼状图的格式

      // console.log(this.InfoList);
      // 使用 reduce 方法遍历数据列表，统计不同类别的数量

      this.DepartmentProjectStatisticsData = {
        '财务科': 0,
        '市场科': 0,
        '安环设备科': 0,
        '生产管理科': 0,
        '供应科': 0,
        '技术科': 0,
        '企业管理科': 0,
        '党群办公室': 0,
        '质量科': 0,
        '执纪监督室': 0,
        '团委': 0

      };

      this.InfoList.forEach(item => {
        if (item.hasOwnProperty('category') && (this.selectedOption === '' || item.department === this.selectedOption)) {
          this.ProjectCategoriesProportionData[item.category] = (this.ProjectCategoriesProportionData[item.category] || 0) + 1;
        }
        if (item.hasOwnProperty('level') && (this.selectedOption === '' || item.department === this.selectedOption)) {
          this.ProjectLevelDistributionData[item.level] = (this.ProjectLevelDistributionData[item.level] || 0) + 1;
        }
        if (item.hasOwnProperty('status') && (this.selectedOption === '' || item.department === this.selectedOption)) {
          this.ProjectAverageScheduleData[item.status] = (this.ProjectAverageScheduleData[item.status] || 0) + 1;
        }

        if (!this.ProjectDevelopmentStatusData.hasOwnProperty(item.level)) {
          this.ProjectDevelopmentStatusData[item.level] = [];
        }
        // 将数据存入对应级别的数组中
        this.ProjectDevelopmentStatusData[item.level].push(item.progressAlloverProgress);



        if (item.hasOwnProperty('department')) {
          // 更新已出现的部门项目数量
          if (this.DepartmentProjectStatisticsData.hasOwnProperty(item.department)) {
            this.DepartmentProjectStatisticsData[item.department]++;
          }
        }

      });

      // 判断数据是否全部为零

      this.isAllZeroProjectCategoriesProportion = Object.values(this.ProjectCategoriesProportionData).every(count => count === 0);
      this.isAllZeroProjectLevelDistribution = Object.values(this.ProjectLevelDistributionData).every(count => count === 0);
      this.isAllZeroProjectAverageSchedule = Object.values(this.ProjectAverageScheduleData).every(count => count === 0);

      // 当数据全部为零时，手动构造一个灰色的数据项
      if (this.isAllZeroProjectCategoriesProportion) {
        this.ProjectCategoriesProportionData.push({
          value: 1, // 给一个任意非零值
          name: '无数据' // 用于显示的名称
        });
      }
      if (this.isAllZeroProjectLevelDistribution) {
        this.ProjectLevelDistributionData.push({
          value: 1, // 给一个任意非零值
          name: '无数据' // 用于显示的名称
        });
      }
      if (this.isAllZeroProjectAverageSchedule) {
        this.ProjectAverageScheduleData.push({
          value: 1, // 给一个任意非零值
          name: '无数据' // 用于显示的名称
        });
      }

      // 初始化饼状图

      this.ProjectCategoriesProportionPieChart();
      this.ProjectLevelDistributionPieChart();
      this.ProjectAverageSchedulePieChart();
      this.ProjectDevelopmentStatusChart();
      this.DepartmentProjectStatisticsChart();

    });


  },

  methods: {

    ProjectCategoriesProportionPieChart(){

      // 如果数据全部为零，则直接显示一个灰色的饼图
      //重组数据格式
      const pieChartData = Object.keys(this.ProjectCategoriesProportionData).map(key => {
        return { value: this.ProjectCategoriesProportionData[key], name: key };
      });
      this.ProjectCategoriesProportionData = [];
      const option = this.isAllZeroProjectCategoriesProportion ? {
        title: {
          text: '项目类别占比',
          left: 'center'
        },
        series: [
          {
            type: 'pie',
            radius: '50%',
            itemStyle: {
              color: '#ccc' // 灰色
            },
            label: {
              show: false, // 不显示标签
            },
            emphasis: {
              label: {
                show: false // 不显示强调时的标签
              }
            }
          }
        ]
      } : {
        title: {
          text: '项目类别占比',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '类别占比',
            type: 'pie',
            radius: '50%',
            data: pieChartData,
            label: {
              show: true, // 不显示标签
              formatter: '{b}: {d}%' // 显示名称和数值
            },
          }

        ]
      };

      this.isAllZeroProjectCategoriesProportion = false;
      this.myChart1 = echarts.init(this.$refs.ProjectCategoriesProportion);// 图标初始化
      this.myChart1.setOption(option);// 渲染页面

      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        this.myChart1.resize();
      });

    },

    ProjectLevelDistributionPieChart(){

      // 如果数据全部为零，则直接显示一个灰色的饼图
      //重组数据格式
      const pieChartData = Object.keys(this.ProjectLevelDistributionData).map(key => {
        return { value: this.ProjectLevelDistributionData[key], name: key };
      });
      this.ProjectLevelDistributionData = [];
      const option = this.isAllZeroProjectLevelDistribution ? {
        title: {
          text: '项目等级分布',
          left: 'center'
        },
        series: [
          {
            type: 'pie',
            radius: '50%',
            itemStyle: {
              color: '#ccc' // 灰色
            },
            label: {
              show: false // 不显示标签
            },
            emphasis: {
              label: {
                show: false // 不显示强调时的标签
              }
            }
          }
        ]
      } : {
        title: {
          text: '项目等级分布',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '等级分布',
            type: 'pie',
            radius: '50%',
            data: pieChartData,
            label: {
              show: true, // 不显示标签
              formatter: '{b}: {d}%' // 显示名称和数值
            },
          }
        ]
      };

      this.isAllZeroProjectLevelDistribution = false;
      this.myChart2 = echarts.init(this.$refs.ProjectLevelDistribution);// 图标初始化
      this.myChart2.setOption(option);// 渲染页面

      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        this.myChart2.resize();
      });

    },

    ProjectAverageSchedulePieChart(){

      // 如果数据全部为零，则直接显示一个灰色的饼图
      //重组数据格式
      const pieChartData = Object.keys(this.ProjectAverageScheduleData).map(key => {
        return { value: this.ProjectAverageScheduleData[key], name: key };
      });
      this.ProjectAverageScheduleData = [];
      const option = this.isAllZeroProjectAverageSchedule ? {
        title: {
          text: '开展状态占比',
          left: 'center'
        },
        series: [
          {
            type: 'pie',
            radius: '50%',
            itemStyle: {
              color: '#ccc' // 灰色
            },
            label: {
              show: false // 不显示标签
            },
            emphasis: {
              label: {
                show: false // 不显示强调时的标签
              }
            }
          }
        ]
      } : {
        title: {
          text: '开展状态占比',
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '开展状态占比',
            type: 'pie',
            radius: '50%',
            data: pieChartData,
            label: {
              show: true, // 不显示标签
              formatter: '{b}: {d}%' // 显示名称和数值
            },
          }
        ]
      };

      this.myChart3 = echarts.init(this.$refs.ProjectAverageSchedule);// 图标初始化
      this.myChart3.setOption(option);// 渲染页面
      this.isAllZeroProjectAverageSchedule = false;
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        this.myChart3.resize();
      });

    },

    ProjectDevelopmentStatusChart(){
      const ChartData = Object.keys(this.ProjectDevelopmentStatusData).map(key => {
        return { value: this.ProjectDevelopmentStatusData[key], name: key };
      });
      this.ProjectDevelopmentStatusData = [];
      ChartData.forEach(item => {
        let total = 0;
        const values = item.value.map(val => parseFloat(val)); // 将数组中的每个元素解析为整数
        const sum = values.reduce((acc, curr) => acc + curr, 0); // 使用 reduce() 方法计算数组的总和
        total += sum; // 将每个对象的值累加到总和中
        const averageValue = total / item.value.length; // 计算平均值
        item.value = averageValue; // 直接将平均值赋值给 item.value
      });

      const option = {
        title: {
          text: '平均进度统计',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: ChartData.map(item => item.name)
        },
        yAxis: {
          type: 'value'
        },
        tooltip: {
          trigger: 'axis', // 设置触发类型为坐标轴
          formatter: '{b}: {c}', // 设置提示框内容格式，{b} 表示类目轴的值，{c} 表示数据值
          axisPointer: { // 设置坐标轴指示器
            type: 'shadow' // 阴影指示器
          }
        },
        series: [{
          name: '得分',
          data: ChartData.map(item => item.value),
          type: 'bar'
        }]
      };


      this.chart4 = echarts.init(this.$refs.ProjectDevelopmentStatus);
      this.chart4.setOption(option);

      window.addEventListener("resize", () => {
        this.chart4.resize();
      });
    },

    DepartmentProjectStatisticsChart(){
      const ChartData = Object.keys(this.DepartmentProjectStatisticsData).map(key => {
        return { value: this.DepartmentProjectStatisticsData[key], name: key };
      });
      this.DepartmentProjectStatisticsData = [];
      const option = {
        title: {
          text: '部门项目统计',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: ChartData.map(item => item.name),
          axisLabel: {
            interval: 0, // 默认为0，表示显示所有标签。可以设置为1，表示隔一个标签显示一个，或者设置其他值。
            rotate: 45, // 标签旋转的角度，根据需要调整
            margin: 10, // 标签与横轴之间的距离
            // formatter: function(value) {
            //   return value.substring(0, 10) + '...'; // 截取前10个字符，根据需要调整
            // }
          }
        },
        yAxis: {
          type: 'value'
        },
        tooltip: {
          trigger: 'axis', // 设置触发类型为坐标轴
          formatter: '{b}: {c}', // 设置提示框内容格式，{b} 表示类目轴的值，{c} 表示数据值
          axisPointer: { // 设置坐标轴指示器
            type: 'shadow' // 阴影指示器
          }
        },
        series: [{
          name: '部门',
          data: ChartData.map(item => item.value),
          type: 'bar'
        }]
      };


      this.chart5 = echarts.init(this.$refs.DepartmentProjectStatistics);
      this.chart5.setOption(option);

      window.addEventListener("resize", () => {
        this.chart5.resize();
      });
    },

    /** 查询项目基本信息列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.InfoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },



    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        projectId: null,
        projectName: null,
        category: null,
        level: null,
        department: null,
        attribute: null,
        description: null,
        startDate: null,
        progressAlloverProgress: null,
        importDate: null,
        remake: null,
        oldProjectId: null,
        associationDate: null,
        manager: null,
        teamMembers: null,
        status: null,
        progress: null,
        currentStatus: null,
        goal: null,
        scope: null,
        plannedCompletionTime: null,
        daysPassed: null,
        daysRemaining: null,
        completionSummary: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目基本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProjectInfo(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目基本信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目基本信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delInfo(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/Info/export', {
        ...this.queryParams
      }, `Info_${new Date().getTime()}.xlsx`)
    },


    handleDepartmentChange(value) {
      this.selectedOption = value;

      listInfo(this.queryParams).then(response => {
        // 将后端返回的数据赋值给 reportList
        this.InfoList = response.rows;
        this.total = response.total;

        // const filteredInfoList = this.InfoList.filter(item => {
        //   return this.selectedTime.length > 0 &&
        //         item.startDate >= this.selectedTime[0] &&
        //         item.plannedCompletionTime <= this.selectedTime[1];
        // });

        // this.InfoList = filteredInfoList; // 更新 InfoList 数据


        this.InfoList.forEach(item => {
          if (item.hasOwnProperty('category') && (this.selectedOption === '' || item.department === this.selectedOption)) {
            this.ProjectCategoriesProportionData[item.category] = (this.ProjectCategoriesProportionData[item.category] || 0) + 1;
          }
          if (item.hasOwnProperty('level') && (this.selectedOption === '' || item.department === this.selectedOption)) {
            this.ProjectLevelDistributionData[item.level] = (this.ProjectLevelDistributionData[item.level] || 0) + 1;
          }
          if (item.hasOwnProperty('status') && (this.selectedOption === '' || item.department === this.selectedOption)) {
            this.ProjectAverageScheduleData[item.status] = (this.ProjectAverageScheduleData[item.status] || 0) + 1;
          }
        });

        // this.InfoList = [];
        // 判断数据是否全部为零

        this.isAllZeroProjectCategoriesProportion = Object.values(this.ProjectCategoriesProportionData).every(count => count === 0);
        this.isAllZeroProjectLevelDistribution = Object.values(this.ProjectLevelDistributionData).every(count => count === 0);
        this.isAllZeroProjectAverageSchedule = Object.values(this.ProjectAverageScheduleData).every(count => count === 0);

        // 当数据全部为零时，手动构造一个灰色的数据项
        if (this.isAllZeroProjectCategoriesProportion) {
          this.ProjectCategoriesProportionData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
        }
        if (this.isAllZeroProjectLevelDistribution) {
          this.ProjectLevelDistributionData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
        }
        if (this.isAllZeroProjectAverageSchedule) {
          this.ProjectAverageScheduleData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
        }

        //拼装新数据
        const pieChartData1 = Object.keys(this.ProjectCategoriesProportionData).map(key => {
          return { value: this.ProjectCategoriesProportionData[key], name: key };
        });
        this.ProjectCategoriesProportionData = [];
        const option1 = this.isAllZeroProjectCategoriesProportion ? {
          title: {
            text: '项目类别占比',
            left: 'center'
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              itemStyle: {
                color: '#ccc' // 灰色
              },
              label: {
                show: false, // 不显示标签
              },
              emphasis: {
                label: {
                  show: false // 不显示强调时的标签
                }
              }
            }
          ]
        } : {
          title: {
            text: '项目类别占比',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          series: [
            {
              name: '类别占比',
              type: 'pie',
              radius: '50%',
              data: pieChartData1,
              label: {
                show: true, // 不显示标签
                formatter: '{b}: {d}%' // 显示名称和数值
              },
            }

          ]
        };

        const pieChartDat2 = Object.keys(this.ProjectLevelDistributionData).map(key => {
          return { value: this.ProjectLevelDistributionData[key], name: key };
        });
        this.ProjectLevelDistributionData = [];
        const option2 = this.isAllZeroProjectLevelDistribution ? {
          title: {
            text: '项目等级分布',
            left: 'center'
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              itemStyle: {
                color: '#ccc' // 灰色
              },
              label: {
                show: false // 不显示标签
              },
              emphasis: {
                label: {
                  show: false // 不显示强调时的标签
                }
              }
            }
          ]
        } : {
          title: {
            text: '项目等级分布',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          series: [
            {
              name: '等级分布',
              type: 'pie',
              radius: '50%',
              data: pieChartDat2,
              label: {
                show: true, // 不显示标签
                formatter: '{b}: {d}%' // 显示名称和数值
              },
            }
          ]
        };

        const pieChartData3 = Object.keys(this.ProjectAverageScheduleData).map(key => {
          return { value: this.ProjectAverageScheduleData[key], name: key };
        });
        this.ProjectAverageScheduleData = [];
        const option3 = this.isAllZeroProjectAverageSchedule ? {
          title: {
            text: '开展状态占比',
            left: 'center'
          },
          series: [
            {
              type: 'pie',
              radius: '50%',
              itemStyle: {
                color: '#ccc' // 灰色
              },
              label: {
                show: false // 不显示标签
              },
              emphasis: {
                label: {
                  show: false // 不显示强调时的标签
                }
              }
            }
          ]
        } : {
          title: {
            text: '开展状态占比',
            left: 'center'
          },
          tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          series: [
            {
              name: '开展状态占比',
              type: 'pie',
              radius: '50%',
              data: pieChartData3,
              label: {
                show: true, // 不显示标签
                formatter: '{b}: {d}%' // 显示名称和数值
              },
            }
          ]
        };

        this.isAllZeroProjectCategoriesProportion = false;
        this.isAllZeroProjectLevelDistribution = false;
        this.isAllZeroProjectAverageSchedule = false;

        this.myChart1.setOption(option1);
        this.myChart2.setOption(option2);
        this.myChart3.setOption(option3);

      });
    },



  }
};
</script>

<style>

.operation {
  /* 添加底部边距 */
  margin-bottom: 20px; /* 调整这个值来改变两个 div 之间的距离 */
}
.echarts-wrapper {
  white-space: nowrap;
}

.echart {
  vertical-align: top;
}

.charts-container {
  display: flex;
}
.charts-container > div {
  flex: 1;
  margin-right: 20px; /* 可以调整两个图表之间的间距 */
}

</style>

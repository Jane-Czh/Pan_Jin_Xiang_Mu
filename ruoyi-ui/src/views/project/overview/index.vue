<template>
  <div class="app-container">

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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/project/info";
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
        completionSummary: null
      },


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
      }
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
          '安环科': 0,
          '生产科': 0,
          '供应科': 0,
          '技术科': 0,
          '企管科': 0,
          '党群科': 0,
          '质量科': 0

      };

      this.InfoList.forEach(item => {
          if (item.hasOwnProperty('category')) {
            this.ProjectCategoriesProportionData[item.category] = (this.ProjectCategoriesProportionData[item.category] || 0) + 1;
          }
          if (item.hasOwnProperty('level')) {
            this.ProjectLevelDistributionData[item.level] = (this.ProjectLevelDistributionData[item.level] || 0) + 1;
          }
          if (item.hasOwnProperty('status')) {
            this.ProjectAverageScheduleData[item.status] = (this.ProjectAverageScheduleData[item.status] || 0) + 1;
          }

          if (!this.ProjectDevelopmentStatusData.hasOwnProperty(item.level)) {
              this.ProjectDevelopmentStatusData[item.level] = [];
          }
          // 将数据存入对应级别的数组中
          this.ProjectDevelopmentStatusData[item.level].push(item.progressAlloverProgress);
            
          // if (item.hasOwnProperty('department')) {
          //   this.DepartmentProjectStatisticsData[item.department] = (this.DepartmentProjectStatisticsData[item.department] || 0) + 1;
          // }

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
                data: pieChartData
            }
        ]
    };

    const myChart = echarts.init(this.$refs.ProjectCategoriesProportion);// 图标初始化
    myChart.setOption(option);// 渲染页面

    //随着屏幕大小调节图表
    window.addEventListener("resize", () => {
        myChart.resize();
    });

    },

    ProjectLevelDistributionPieChart(){

      // 如果数据全部为零，则直接显示一个灰色的饼图
      //重组数据格式
      const pieChartData = Object.keys(this.ProjectLevelDistributionData).map(key => {
        return { value: this.ProjectLevelDistributionData[key], name: key };
      });

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
                data: pieChartData
            }
        ]
      };

      const myChart = echarts.init(this.$refs.ProjectLevelDistribution);// 图标初始化
      myChart.setOption(option);// 渲染页面

      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });

    },

    ProjectAverageSchedulePieChart(){

      // 如果数据全部为零，则直接显示一个灰色的饼图
      //重组数据格式
      const pieChartData = Object.keys(this.ProjectAverageScheduleData).map(key => {
        return { value: this.ProjectAverageScheduleData[key], name: key };
      });

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
                data: pieChartData
            }
        ]
      };

      const myChart = echarts.init(this.$refs.ProjectAverageSchedule);// 图标初始化
      myChart.setOption(option);// 渲染页面

      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });

    },

    ProjectDevelopmentStatusChart(){
      const ChartData = Object.keys(this.ProjectDevelopmentStatusData).map(key => {
        return { value: this.ProjectDevelopmentStatusData[key], name: key };
      });

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


      const chart = echarts.init(this.$refs.ProjectDevelopmentStatus);
      chart.setOption(option);

      window.addEventListener("resize", () => {
        chart.resize();
      });
    },

    DepartmentProjectStatisticsChart(){
      const ChartData = Object.keys(this.DepartmentProjectStatisticsData).map(key => {
        return { value: this.DepartmentProjectStatisticsData[key], name: key };
      });

    //   ChartData.forEach(item => {
    //     let total = 0;
    //     const values = item.value.map(val => parseFloat(val)); // 将数组中的每个元素解析为整数
    //     const sum = values.reduce((acc, curr) => acc + curr, 0); // 使用 reduce() 方法计算数组的总和
    //     total += sum; // 将每个对象的值累加到总和中
    //     const averageValue = total / item.value.length; // 计算平均值
    //     item.value = averageValue; // 直接将平均值赋值给 item.value
    // });
    console.log(ChartData);

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
        name: '部门',
        data: ChartData.map(item => item.value),
        type: 'bar'
      }]
    };


      const chart = echarts.init(this.$refs.DepartmentProjectStatistics);
      chart.setOption(option);

      window.addEventListener("resize", () => {
        chart.resize();
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
      getInfo(projectId).then(response => {
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
    }
  }
};
</script>

<style>
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
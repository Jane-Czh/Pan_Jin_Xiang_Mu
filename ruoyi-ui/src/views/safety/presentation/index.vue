<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="班组" prop="checkTeam">
        <el-input
          v-model="queryParams.checkTeam"
          placeholder="请输入班组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="一月成绩" prop="januaryResults">
        <el-input
          v-model="queryParams.januaryResults"
          placeholder="请输入一月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二月成绩" prop="februaryResults">
        <el-input
          v-model="queryParams.februaryResults"
          placeholder="请输入二月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="三月成绩" prop="marchResults">
        <el-input
          v-model="queryParams.marchResults"
          placeholder="请输入三月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="四月成绩" prop="aprilResults">
        <el-input
          v-model="queryParams.aprilResults"
          placeholder="请输入四月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="五月成绩" prop="mayResults">
        <el-input
          v-model="queryParams.mayResults"
          placeholder="请输入五月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="六月成绩" prop="juneResults">
        <el-input
          v-model="queryParams.juneResults"
          placeholder="请输入六月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="七月成绩" prop="julyResults">
        <el-input
          v-model="queryParams.julyResults"
          placeholder="请输入七月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="八月成绩" prop="augustResults">
        <el-input
          v-model="queryParams.augustResults"
          placeholder="请输入八月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="九月成绩" prop="septemberResults">
        <el-input
          v-model="queryParams.septemberResults"
          placeholder="请输入九月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="十月成绩" prop="octoberResults">
        <el-input
          v-model="queryParams.octoberResults"
          placeholder="请输入十月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="十一月成绩" prop="novemberResults">
        <el-input
          v-model="queryParams.novemberResults"
          placeholder="请输入十一月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="十二月成绩" prop="decemberResults">
        <el-input
          v-model="queryParams.decemberResults"
          placeholder="请输入十二月成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="第一季度成绩" prop="firstQuarterResults">
        <el-input
          v-model="queryParams.firstQuarterResults"
          placeholder="请输入第一季度成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="第二季度成绩" prop="secondQuarterResults">
        <el-input
          v-model="queryParams.secondQuarterResults"
          placeholder="请输入第二季度成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="第三季度成绩" prop="thirdQuarterResults">
        <el-input
          v-model="queryParams.thirdQuarterResults"
          placeholder="请输入第三季度成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="第四季度成绩" prop="fourthQuarterResults">
        <el-input
          v-model="queryParams.fourthQuarterResults"
          placeholder="请输入第四季度成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="全年成绩" prop="annualResults">
        <el-input
          v-model="queryParams.annualResults"
          placeholder="请输入全年成绩"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

  <div class="charts-container">
    <div>
      <div ref="ThisMonthResultsChart" style="width: 600px; height: 400px;"></div>
    </div>
    <div>
      <div ref="ThisQuarterResultsChart" style="width: 600px; height: 400px;"></div>
    </div>
    <!-- <div>
      <div ref="ThisAnnualResultsChart" style="width: 600px; height: 400px;"></div>
    </div> -->
  </div>
  <div>
      <div ref="ThisAnnualResultsChart" style="width: 600px; height: 400px;"></div>
    </div>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['safety:presentation:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['safety:presentation:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['safety:presentation:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['safety:presentation:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="syncReport"
        v-hasPermi="['safety:presentation:syncReport']"
      >同步数据</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="presentationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="searpId" />
      <el-table-column label="班组" align="center" prop="checkTeam" />
      <el-table-column label="一月成绩" align="center" prop="januaryResults" />
      <el-table-column label="二月成绩" align="center" prop="februaryResults" />
      <el-table-column label="三月成绩" align="center" prop="marchResults" />
      <el-table-column label="四月成绩" align="center" prop="aprilResults" />
      <el-table-column label="五月成绩" align="center" prop="mayResults" />
      <el-table-column label="六月成绩" align="center" prop="juneResults" />
      <el-table-column label="七月成绩" align="center" prop="julyResults" />
      <el-table-column label="八月成绩" align="center" prop="augustResults" />
      <el-table-column label="九月成绩" align="center" prop="septemberResults" />
      <el-table-column label="十月成绩" align="center" prop="octoberResults" />
      <el-table-column label="十一月成绩" align="center" prop="novemberResults" />
      <el-table-column label="十二月成绩" align="center" prop="decemberResults" />
      <el-table-column label="第一季度成绩" align="center" prop="firstQuarterResults" />
      <el-table-column label="第二季度成绩" align="center" prop="secondQuarterResults" />
      <el-table-column label="第三季度成绩" align="center" prop="thirdQuarterResults" />
      <el-table-column label="第四季度成绩" align="center" prop="fourthQuarterResults" />
      <el-table-column label="全年成绩" align="center" prop="annualResults" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['safety:presentation:edit']"
          >修改</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['safety:presentation:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改成绩考核展示对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="班组" prop="checkTeam">
          <el-input v-model="form.checkTeam" placeholder="请输入班组" />
        </el-form-item>
        <el-form-item label="一月成绩" prop="januaryResults">
          <el-input v-model="form.januaryResults" placeholder="请输入一月成绩" />
        </el-form-item>
        <el-form-item label="二月成绩" prop="februaryResults">
          <el-input v-model="form.februaryResults" placeholder="请输入二月成绩" />
        </el-form-item>
        <el-form-item label="三月成绩" prop="marchResults">
          <el-input v-model="form.marchResults" placeholder="请输入三月成绩" />
        </el-form-item>
        <el-form-item label="四月成绩" prop="aprilResults">
          <el-input v-model="form.aprilResults" placeholder="请输入四月成绩" />
        </el-form-item>
        <el-form-item label="五月成绩" prop="mayResults">
          <el-input v-model="form.mayResults" placeholder="请输入五月成绩" />
        </el-form-item>
        <el-form-item label="六月成绩" prop="juneResults">
          <el-input v-model="form.juneResults" placeholder="请输入六月成绩" />
        </el-form-item>
        <el-form-item label="七月成绩" prop="julyResults">
          <el-input v-model="form.julyResults" placeholder="请输入七月成绩" />
        </el-form-item>
        <el-form-item label="八月成绩" prop="augustResults">
          <el-input v-model="form.augustResults" placeholder="请输入八月成绩" />
        </el-form-item>
        <el-form-item label="九月成绩" prop="septemberResults">
          <el-input v-model="form.septemberResults" placeholder="请输入九月成绩" />
        </el-form-item>
        <el-form-item label="十月成绩" prop="octoberResults">
          <el-input v-model="form.octoberResults" placeholder="请输入十月成绩" />
        </el-form-item>
        <el-form-item label="十一月成绩" prop="novemberResults">
          <el-input v-model="form.novemberResults" placeholder="请输入十一月成绩" />
        </el-form-item>
        <el-form-item label="十二月成绩" prop="decemberResults">
          <el-input v-model="form.decemberResults" placeholder="请输入十二月成绩" />
        </el-form-item>
        <el-form-item label="第一季度成绩" prop="firstQuarterResults">
          <el-input v-model="form.firstQuarterResults" placeholder="请输入第一季度成绩" />
        </el-form-item>
        <el-form-item label="第二季度成绩" prop="secondQuarterResults">
          <el-input v-model="form.secondQuarterResults" placeholder="请输入第二季度成绩" />
        </el-form-item>
        <el-form-item label="第三季度成绩" prop="thirdQuarterResults">
          <el-input v-model="form.thirdQuarterResults" placeholder="请输入第三季度成绩" />
        </el-form-item>
        <el-form-item label="第四季度成绩" prop="fourthQuarterResults">
          <el-input v-model="form.fourthQuarterResults" placeholder="请输入第四季度成绩" />
        </el-form-item>
        <el-form-item label="全年成绩" prop="annualResults">
          <el-input v-model="form.annualResults" placeholder="请输入全年成绩" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listPresentation, getPresentation, delPresentation, addPresentation, updatePresentation, synchronization } from "@/api/safety/presentation";
import * as echarts from 'echarts'

export default {
  name: "Presentation",
  data() {
    return {
      ThisMonthResults: [], // 当月得分数据
      currentMonthParameter: '', // 当前月份对应的参数名

      ThisQuarterResults: [], //本季度得分
      currentQuarterParameter: '', // 当前季度对应的参数名

      ThisAnnualResults: [], //本年度得分
      currentAnnualParameter: 'annualResults',

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
      // 成绩考核展示表格数据
      presentationList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        checkTeam: null,
        januaryResults: null,
        februaryResults: null,
        marchResults: null,
        aprilResults: null,
        mayResults: null,
        juneResults: null,
        julyResults: null,
        augustResults: null,
        septemberResults: null,
        octoberResults: null,
        novemberResults: null,
        decemberResults: null,
        firstQuarterResults: null,
        secondQuarterResults: null,
        thirdQuarterResults: null,
        fourthQuarterResults: null,
        annualResults: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        checkTeam: [
          { required: true, message: "班组不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.setCurrentMonthParameter();
    this.setCurrentQuarterParameter();
    this.$nextTick(() => {
      this.loadMonthDataAndInitializeBarCharts();
      this.loadQuarterDataAndInitializeBarCharts();
      this.loadAnnualDataAndInitializeBarCharts();
    });
  },
  methods: {
    setCurrentMonthParameter() {
      const currentMonth = new Date().getMonth() + 1; // JavaScript 中月份从 0 开始，所以需要加 1
      switch (currentMonth) {
        case 1:
          this.currentMonthParameter = 'januaryResults';
          break;
        case 2:
          this.currentMonthParameter = 'februaryResults';
          break;
        case 3:
          this.currentMonthParameter = 'marchResults';
          break;
        case 4:
          this.currentMonthParameter = 'aprilResults';
          break;  
        case 5:
          this.currentMonthParameter = 'mayResults';
          break;  
        case 6:
          this.currentMonthParameter = 'juneResults';
          break;  
        case 7:
          this.currentMonthParameter = 'julyResults';
          break;  
        case 8:
          this.currentMonthParameter = 'augustResults';
          break;  
        case 9:
          this.currentMonthParameter = 'septemberResults';
          break;  
        case 10:
          this.currentMonthParameter = 'octoberResults';
          break;  
        case 11:
          this.currentMonthParameter = 'novemberResults';
          break;   
        default:
          this.currentMonthParameter = 'decemberResults';
          break;
      }
    },
    setCurrentQuarterParameter(){
      const currentMonth = new Date().getMonth() + 1; // JavaScript 中月份从 0 开始，所以需要加 1
      switch (currentMonth) {
        case 1:
          this.currentQuarterParameter = 'firstQuarterResults';
          break;
        case 2:
          this.currentQuarterParameter = 'firstQuarterResults';
          break;
        case 3:
          this.currentQuarterParameter = 'firstQuarterResults';
          break;
        case 4:
          this.currentQuarterParameter = 'firstQuarterResults';
          break;  
        case 5:
          this.currentQuarterParameter = 'secondQuarterResults';
          break;  
        case 6:
          this.currentQuarterParameter = 'secondQuarterResults';
          break;  
        case 7:
          this.currentQuarterParameter = 'secondQuarterResults';
          break;  
        case 8:
          this.currentQuarterParameter = 'secondQuarterResults';
          break;  
        case 9:
          this.currentQuarterParameter = 'thirdQuarterResults';
          break;  
        case 10:
          this.currentQuarterParameter = 'thirdQuarterResults';
          break;  
        case 11:
          this.currentQuarterParameter = 'thirdQuarterResults';
          break;   
        default:
          this.currentQuarterParameter = 'thirdQuarterResults';
          break;
      }

    },

    loadMonthDataAndInitializeBarCharts(){
      listPresentation(this.queryParams).then(response => {
        this.presentationList = response.rows;
        this.ThisMonthResults = this.presentationList.map(item => ({
          value: item[this.currentMonthParameter], // 使用当前月份对应的参数名获取数据
          name: item.checkTeam
        }));


        // 如果有数据，则初始化柱状图

        if (this.ThisMonthResults.length > 0) {
          this.initializeThisMonthResultsChart();
        }

      }).catch(error => {
      console.error('Error fetching data:', error);
    });

    },

    loadQuarterDataAndInitializeBarCharts(){
      listPresentation(this.queryParams).then(response => {
        this.presentationList = response.rows;
        this.ThisQuarterResults = this.presentationList.map(item => ({
          value: item[this.currentQuarterParameter], // 使用当前月份对应的参数名获取数据
          name: item.checkTeam
        }));

        // 如果有数据，则初始化柱状图

        if (this.ThisQuarterResults.length > 0) {
          this.initializeThisQuarterResultsChart();
        }

      }).catch(error => {
      console.error('Error fetching data:', error);
    });

    },

    loadAnnualDataAndInitializeBarCharts(){
      listPresentation(this.queryParams).then(response => {
        this.presentationList = response.rows;
        this.ThisAnnualResults = this.presentationList.map(item => ({
          value: item[this.currentAnnualParameter], // 使用当前月份对应的参数名获取数据
          name: item.checkTeam
        }));

        // 如果有数据，则初始化柱状图

        if (this.ThisQuarterResults.length > 0) {
          this.initializeAnnualResultsChart();
        }

      }).catch(error => {
      console.error('Error fetching data:', error);
    });

    },

    initializeThisMonthResultsChart() {
      const option = {
        title: {
          text: '本月得分',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: this.ThisMonthResults.map(item => item.name)
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '得分',
          data: this.ThisMonthResults.map(item => item.value),
          type: 'bar'
        }]
      };

      const chart = echarts.init(this.$refs.ThisMonthResultsChart);
      chart.setOption(option);

      window.addEventListener("resize", () => {
        chart.resize();
      });
    },

    initializeThisQuarterResultsChart() {
      const option = {
        title: {
          text: '本季度得分',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: this.ThisQuarterResults.map(item => item.name)
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '得分',
          data: this.ThisQuarterResults.map(item => item.value),
          type: 'bar'
        }]
      };

      const chart = echarts.init(this.$refs.ThisQuarterResultsChart);
      chart.setOption(option);

      window.addEventListener("resize", () => {
        chart.resize();
      });
    },

    initializeAnnualResultsChart(){
      const option = {
        title: {
          text: '本年度得分',
          left: 'center'
        },
        xAxis: {
          type: 'category',
          data: this.ThisAnnualResults.map(item => item.name)
        },
        yAxis: {
          type: 'value'
        },
        series: [{
          name: '得分',
          data: this.ThisAnnualResults.map(item => item.value),
          type: 'bar'
        }]
      };

      const chart = echarts.init(this.$refs.ThisAnnualResultsChart);
      chart.setOption(option);

      window.addEventListener("resize", () => {
        chart.resize();
      });
    },

    /** 查询成绩考核展示列表 */
    getList() {
      this.loading = true;
      listPresentation(this.queryParams).then(response => {
        this.presentationList = response.rows;
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
        searpId: null,
        checkTeam: null,
        januaryResults: null,
        februaryResults: null,
        marchResults: null,
        aprilResults: null,
        mayResults: null,
        juneResults: null,
        julyResults: null,
        augustResults: null,
        septemberResults: null,
        octoberResults: null,
        novemberResults: null,
        decemberResults: null,
        firstQuarterResults: null,
        secondQuarterResults: null,
        thirdQuarterResults: null,
        fourthQuarterResults: null,
        annualResults: null
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
      this.ids = selection.map(item => item.searpId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加成绩考核展示";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const searpId = row.searpId || this.ids
      getPresentation(searpId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改成绩考核展示";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.searpId != null) {
            updatePresentation(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPresentation(this.form).then(response => {
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
      const searpIds = row.searpId || this.ids;
      this.$modal.confirm('是否确认删除成绩考核展示编号为"' + searpIds + '"的数据项？').then(function() {
        return delPresentation(searpIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('safety/presentation/export', {
        ...this.queryParams
      }, `presentation_${new Date().getTime()}.xlsx`)
    },

    syncReport() {
      // 使用 Fetch API 发送 POST 请求到后端
      // fetch('http://localhost:8080/safety/presentation/synchronization', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   }
      // })
      // .then(response => {
      //   if (!response.ok) {
      //     throw new Error('Network response was not ok');
      //   }
      //   // 如果请求成功，可以进行下一步操作
      // })
      // .catch(error => {
      //   console.error('There was an error!', error);
      // });

      synchronization()
      .then(response => {
        // if (!response.ok) {
        //   throw new Error('Network response was not ok');
        // }
        // 如果请求成功，可以进行下一步操作
        this.$message.success("同步成功");
        //页面刷新
        location.reload();
      })
      .catch(error => {
        console.error('There was an error!', error);
      });

    },
  }
};
</script>

<style>
  .charts-container {
    display: flex;
  }
  .charts-container > div {
    flex: 1;
    margin-right: 20px; /* 可以调整两个图表之间的间距 */
  }
</style>
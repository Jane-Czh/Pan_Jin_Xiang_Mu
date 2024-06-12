<template>
  <div class="currentPage">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年月" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择年月">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['enterprise:Data:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['enterprise:Data:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['enterprise:Data:remove']">删除</el-button>
      </el-col>
      <import-excel :name="'工资表'" :url="'/enterprise/data/salary'" />

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="DataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="esId" /> -->
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="一线从业人数" align="center" prop="employeesNumber" />
      <el-table-column label="公司平均从业人数" align="center" prop="employeesAvgMonthlyNumber" />
      <el-table-column label="公司平均从业人数" align="center" prop="employeesAvgAnnualNumber" />
      <el-table-column label="工资总额月度值" align="center" prop="totalMonthlySalary" />
      <el-table-column label="工资总额月度占比" align="center" prop="monthlySalaryRatio" />
      <el-table-column label="工资总额年度占比" align="center" prop="annualSalaryRatio" />
      <el-table-column label="累计人均收入" align="center" prop="cumulativeAverageIncome" />
      <el-table-column label="月度累计生产人均收入" align="center" prop="monthlyProductionAvgIncome" width="150" />
      <el-table-column label="月度累计职能人均收入" align="center" prop="monthlyFunctionalAvgIncome" width="150" />
      <el-table-column label="职能部门人均加班费用" align="center" prop="functionalDeptOvertimeCost" width="150" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:Data:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:Data:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[企业管理]指标月度数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="160px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="一线从业人数" prop="employeesNumber">
          <el-input v-model="form.employeesNumber" placeholder="请输入一线从业人数" />
        </el-form-item>
        <el-form-item label="公司平均从业人数" prop="employeesAvgMonthlyNumber">
          <el-input v-model="form.employeesAvgMonthlyNumber" placeholder="请输入公司平均从业人数" />
        </el-form-item>
        <el-form-item label="公司平均从业人数(年度)" prop="employeesAvgAnnualNumber">
          <el-input v-model="form.employeesAvgAnnualNumber" placeholder="请输入公司平均从业人数" />
        </el-form-item>
        <el-form-item label="工资总额月度值" prop="totalMonthlySalary">
          <el-input v-model="form.totalMonthlySalary" placeholder="请输入工资总额月度值" />
        </el-form-item>
        <el-form-item label="工资总额月度占比" prop="monthlySalaryRatio">
          <el-input v-model="form.monthlySalaryRatio" placeholder="请输入工资总额月度占比" />
        </el-form-item>
        <el-form-item label="工资总额年度占比" prop="annualSalaryRatio">
          <el-input v-model="form.annualSalaryRatio" placeholder="请输入工资总额年度占比" />
        </el-form-item>
        <el-form-item label="累计人均收入" prop="cumulativeAverageIncome">
          <el-input v-model="form.cumulativeAverageIncome" placeholder="请输入累计人均收入" />
        </el-form-item>
        <el-form-item label="月度累计生产人均收入" prop="monthlyProductionAvgIncome">
          <el-input v-model="form.monthlyProductionAvgIncome" placeholder="请输入月度累计生产人均收入" />
        </el-form-item>
        <el-form-item label="月度累计职能人均收入" prop="monthlyFunctionalAvgIncome">
          <el-input v-model="form.monthlyFunctionalAvgIncome" placeholder="请输入月度累计职能人均收入" />
        </el-form-item>
        <el-form-item label="职能部门人均加班费用" prop="functionalDeptOvertimeCost">
          <el-input v-model="form.functionalDeptOvertimeCost" placeholder="请输入职能部门人均加班费用" />
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
import { listMonthData, getMonthData, addMonthData, delMonthData, updateMonthData } from "@/api/enterprise/data";
import importExcel from "@/views/financial/importExcel.vue";

export default {
  name: "Data",
  components: { importExcel },
  data() {
    return {

      // 遮罩层
      loading: true,

      // 选中数组
      ids: [],
      dates: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,

      // [企业管理]指标月度数据表格数据
      DataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        employeesNumber: null,
        employeesAvgMonthlyNumber: null,
        employeesAvgAnnualNumber: null,
        totalMonthlySalary: null,
        monthlySalaryRatio: null,
        annualSalaryRatio: null,
        cumulativeAverageIncome: null,
        monthlyProductionAvgIncome: null,
        monthlyFunctionalAvgIncome: null,
        functionalDeptOvertimeCost: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.DataList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.DataList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询[企业管理]指标月度数据列表 */
    getList() {
      this.loading = true;
      listMonthData(this.queryParams).then(response => {
        this.DataList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'yearAndMonth',
          order: 'descending' // 或'descending'
        });
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
        esId: null,
        yearAndMonth: null,
        employeesNumber: null,
        employeesAvgMonthlyNumber: null,
        employeesAvgAnnualNumber: null,
        totalMonthlySalary: null,
        monthlySalaryRatio: null,
        annualSalaryRatio: null,
        cumulativeAverageIncome: null,
        monthlyProductionAvgIncome: null,
        monthlyFunctionalAvgIncome: null,
        functionalDeptOvertimeCost: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.esId)
      this.dates = selection.map(item => item.yearAndMonth)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加[企业管理]指标月度数据";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const esId = row.esId || this.ids
      getMonthData(esId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改[企业管理]指标月度数据";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.esId != null) {
            updateMonthData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMonthData(this.form).then(response => {
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
      const esIds = row.esId || this.ids;
      const date = row.yearAndMonth || this.dates;
      this.$modal.confirm('是否确认删除日期为"' + date + '"的数据？').then(function () {
        return delMonthData(esIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },



  }
};
</script>


<style lang="scss" scoped>
.currentPage {
  padding: 10px;
}
</style>

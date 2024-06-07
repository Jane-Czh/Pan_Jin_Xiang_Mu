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
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['enterprise:Data:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="DataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="esId" />
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="一线从业人数-填报" align="center" prop="employeesNumber" />
      <el-table-column label="公司平均从业人数" align="center" prop="employeesAvgMonthlyNumber" />
      <el-table-column label="公司平均从业人数" align="center" prop="employeesAvgAnnualNumber" />
      <el-table-column label="工资总额月度值-填报" align="center" prop="totalMonthlySalary" />
      <el-table-column label="工资总额月度占比" align="center" prop="monthlySalaryRatio" />
      <el-table-column label="工资总额年度占比" align="center" prop="annualSalaryRatio" />
      <el-table-column label="累计人均收入" align="center" prop="cumulativeAverageIncome" />
      <el-table-column label="月度累计生产人均收入" align="center" prop="monthlyProductionAvgIncome" />
      <el-table-column label="月度累计职能人均收入" align="center" prop="monthlyFunctionalAvgIncome" />
      <el-table-column label="职能部门人均加班费用" align="center" prop="functionalDeptOvertimeCost" />
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
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="一线从业人数-填报" prop="employeesNumber">
          <el-input v-model="form.employeesNumber" placeholder="请输入一线从业人数-填报" />
        </el-form-item>
        <el-form-item label="公司平均从业人数" prop="employeesAvgMonthlyNumber">
          <el-input v-model="form.employeesAvgMonthlyNumber" placeholder="请输入公司平均从业人数" />
        </el-form-item>
        <el-form-item label="公司平均从业人数" prop="employeesAvgAnnualNumber">
          <el-input v-model="form.employeesAvgAnnualNumber" placeholder="请输入公司平均从业人数" />
        </el-form-item>
        <el-form-item label="工资总额月度值-填报" prop="totalMonthlySalary">
          <el-input v-model="form.totalMonthlySalary" placeholder="请输入工资总额月度值-填报" />
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
import { listManagement, getManagement, delManagement, addManagement, updateManagement } from "@/api/enterprise/managementIndex";

export default {
  name: "Data",
  data() {
    return {
      activeName: '1',
      // 遮罩层
      loading: true,
      loading2: true,
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
      total2: 0,
      // [企业管理]指标月度数据表格数据
      DataList: [],
      ManagementList: [],
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
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        sdSalesordervalidity: null,
        ppManualpocreationratio: null,
        ppDeliveredunreportedratio: null,
        mesLateworkreportingrate: null,
        qmExternalinspectiondelay: null,
        mmPurchaseorderlatedelivery: null,
        mmManualpocreation: null,
        mmUnsettledpurchaserequests: null,
        ficoMonthlystandardpricevariation: null,
        CrossMonthProductionOrders: null,
        pmLatemaintenanceordercompletion: null,
      },
      // 表单参数
      form: {},
      form2: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
    this.getList2();
  },
  methods: {
    /** 查询[企业管理]指标月度数据列表 */
    getList() {
      this.loading = true;
      listMonthData(this.queryParams).then(response => {
        this.DataList = response.rows;
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
            delMonthData(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除[企业管理]指标月度数据编号为"' + esIds + '"的数据项？').then(function () {
        return addMonthData(esIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('enterprise/Data/export', {
        ...this.queryParams
      }, `Data_${new Date().getTime()}.xlsx`)
    },



    //11项指标

    /** 查询十一项管理指标列表 */
    getList2() {
      this.loading2 = true;
      listManagement(this.queryParams2).then(response => {
        this.ManagementList = response.rows;
        this.total2 = response.total2;
        this.loading2 = false;
      });
    },
    // 取消按钮
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    // 表单重置
    reset2() {
      this.form2 = {
        emId: null,
        yearAndMonth: null,
        sdSalesordervalidity: null,
        ppManualpocreationratio: null,
        ppDeliveredunreportedratio: null,
        mesLateworkreportingrate: null,
        qmExternalinspectiondelay: null,
        mmPurchaseorderlatedelivery: null,
        mmManualpocreation: null,
        mmUnsettledpurchaserequests: null,
        ficoMonthlystandardpricevariation: null,
        CrossMonthProductionOrders: null,
        pmLatemaintenanceordercompletion: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form2");
    },
    /** 搜索按钮操作 */
    handleQuery2() {
      this.queryParams2.pageNum = 1;
      this.getList2();
    },
    /** 重置按钮操作 */
    resetQuery2() {
      this.resetForm("queryForm2");
      this.handleQuery2();
    },
    // 多选框选中数据
    handleSelectionChange2(selection) {
      this.ids = selection.map(item => item.emId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd2() {
      this.reset2();
      this.open2 = true;
      this.title = "添加十一项管理指标";
    },
    /** 修改按钮操作 */
    handleUpdate2(row) {
      this.reset2();
      const emId = row.emId || this.ids
      getManagement(emId).then(response => {
        this.form2 = response.data;
        this.open2 = true;
        this.title = "修改十一项管理指标";
      });
    },
    /** 提交按钮 */
    submitForm2() {
      this.$refs["form2"].validate(valid => {
        if (valid) {
          if (this.form2.emId != null) {
            updateManagement(this.form2).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open2 = false;
              this.getList2();
            });
          } else {
            addManagement(this.form2).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open2 = false;
              this.getList2();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete2(row) {
      const emIds = row.emId || this.ids;
      this.$modal.confirm('是否确认删除十一项管理指标编号为' + emIds + '的数据项？').then(function () {
        return delManagement(emIds);
      }).then(() => {
        this.getList2();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    // /** 导出按钮操作 */
    // handleExport2() {
    //   this.download('enterprise/data/management/export', {
    //     ...this.queryParams2
    //   }, `Management_${new Date().getTime()}.xlsx`)
    // }
  }
};
</script>


<style lang="scss" scoped>
.currentPage {
  padding: 10px;
}
</style>

<template>
  <div class="app-container">
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
          v-hasPermi="['quality:Metrics:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['quality:Metrics:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['quality:Metrics:remove']">删除</el-button>
      </el-col>
      <import-excel :name="'售后表'" :url="'/quality/after-sales/read'" />
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['quality:Metrics:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="MetricsList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="${comment}" align="center" prop="qcId" /> -->
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当月反馈新车病车数" align="center" prop="newCarDefects" />
      <el-table-column label="三包期内新车返修率" align="center" prop="warrantyRepairRate" />
      <el-table-column label="月度售后质量问题总数" align="center" prop="monthlyAfterSalesIssues" />
      <el-table-column label="三包期内整车月度返修率" align="center" prop="warrantyVehicleRepairRate" />
      <el-table-column label="外部质量损失率" align="center" prop="externalLossRate" />
      <el-table-column label="售后问题生产责任次数" align="center" prop="productionLiabilityIssues" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:Metrics:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['quality:Metrics:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改质量指标-统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="当月反馈新车病车数" prop="newCarDefects">
          <el-input v-model="form.newCarDefects" placeholder="请输入当月反馈新车病车数" />
        </el-form-item>
        <el-form-item label="三包期内新车返修率" prop="warrantyRepairRate">
          <el-input v-model="form.warrantyRepairRate" placeholder="请输入三包期内新车返修率" />
        </el-form-item>
        <el-form-item label="月度售后质量问题总数" prop="monthlyAfterSalesIssues">
          <el-input v-model="form.monthlyAfterSalesIssues" placeholder="请输入月度售后质量问题总数" />
        </el-form-item>
        <el-form-item label="三包期内整车月度返修率" prop="warrantyVehicleRepairRate">
          <el-input v-model="form.warrantyVehicleRepairRate" placeholder="请输入三包期内整车月度返修率" />
        </el-form-item>
        <el-form-item label="外部质量损失率" prop="externalLossRate">
          <el-input v-model="form.externalLossRate" placeholder="请输入外部质量损失率" />
        </el-form-item>
        <el-form-item label="售后问题生产责任次数" prop="productionLiabilityIssues">
          <el-input v-model="form.productionLiabilityIssues" placeholder="请输入售后问题生产责任次数" />
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
import { listMetrics, getMetrics, delMetrics, addMetrics, updateMetrics } from "@/api/quality/afterSales";
import importExcel from "@/views/financial/importExcel.vue";
export default {
  name: "Metrics",
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
      // 质量指标-统计表格数据
      MetricsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        newCarDefects: null,
        warrantyRepairRate: null,
        monthlyAfterSalesIssues: null,
        warrantyVehicleRepairRate: null,
        externalLossRate: null,
        productionLiabilityIssues: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleSortChange(sort) {
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.MetricsList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.MetricsList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询质量指标-统计列表 */
    getList() {
      this.loading = true;
      listMetrics(this.queryParams).then(response => {
        this.MetricsList = response.rows;
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
        qcId: null,
        yearAndMonth: null,
        newCarDefects: null,
        warrantyRepairRate: null,
        monthlyAfterSalesIssues: null,
        warrantyVehicleRepairRate: null,
        externalLossRate: null,
        productionLiabilityIssues: null,
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
      this.ids = selection.map(item => item.qcId)
      this.dates = selection.map(item => item.yearAndMonth)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加质量指标-统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const qcId = row.qcId || this.ids
      getMetrics(qcId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改质量指标-统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.qcId != null) {
            updateMetrics(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMetrics(this.form).then(response => {
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
      const qcIds = row.qcId || this.ids;
      const date = row.yearAndMonth || this.dates;
      this.$modal.confirm('是否确认删除日期为"' + date + '"的数据？').then(function () {
        return delMetrics(qcIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('quality/Metrics/export', {
        ...this.queryParams
      }, `Metrics_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
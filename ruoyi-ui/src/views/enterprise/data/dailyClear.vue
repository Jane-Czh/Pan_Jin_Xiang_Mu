<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
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
          v-hasPermi="['enterprise:dailyclear:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['enterprise:dailyclear:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['enterprise:dailyclear:remove']">删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SettlementList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="edId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="180" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="销售订单录入不及时比例(%)" align="center" prop="orderEntryDelayRatio" />
      <el-table-column label="销售订单不及时发货比例(%)" align="center" prop="shipmentDelayRatio" />
      <el-table-column label="生产订单不及时报工比例(%)" align="center" prop="productionReportDelayRatio" />
      <el-table-column label="成品检验业务不及时率(%)" align="center" prop="inspectionDelayRate" />
      <el-table-column label="销售发票过账不及时率(%)" align="center" prop="invoicePostingDelayRate" />
      <el-table-column label="客户未清账比例(%)" align="center" prop="unsettledAccountsRatio" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:dailyclear:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:dailyclear:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改日清日结对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="210px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="销售订单录入不及时比例(%)" prop="orderEntryDelayRatio">
          <el-input v-model="form.orderEntryDelayRatio" placeholder="请输入销售订单录入不及时比例(%)" />
        </el-form-item>
        <el-form-item label="销售订单不及时发货比例(%)" prop="shipmentDelayRatio">
          <el-input v-model="form.shipmentDelayRatio" placeholder="请输入销售订单不及时发货比例(%)" />
        </el-form-item>
        <el-form-item label="生产订单不及时报工比例(%)" prop="productionReportDelayRatio">
          <el-input v-model="form.productionReportDelayRatio" placeholder="请输入生产订单不及时报工比例(%)" />
        </el-form-item>
        <el-form-item label="成品检验业务不及时率(%)" prop="inspectionDelayRate">
          <el-input v-model="form.inspectionDelayRate" placeholder="请输入成品检验业务不及时率(%)" />
        </el-form-item>
        <el-form-item label="销售发票过账不及时率(%)" prop="invoicePostingDelayRate">
          <el-input v-model="form.invoicePostingDelayRate" placeholder="请输入销售发票过账不及时率(%)" />
        </el-form-item>
        <el-form-item label="客户未清账比例(%)" prop="unsettledAccountsRatio">
          <el-input v-model="form.unsettledAccountsRatio" placeholder="请输入客户未清账比例(%)" />
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
import { listDailyClearData, getDailyClearData, addDailyClearData, updateDailyClearData, delDailyClearData } from "@/api/enterprise/dailyClearData";
import { numValidatorPercentage } from '@/api/financial/numValidator.js';
export default {
  name: "Settlement",
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
      // 日清日结表格数据
      SettlementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        orderEntryDelayRatio: null,
        shipmentDelayRatio: null,
        productionReportDelayRatio: null,
        inspectionDelayRate: null,
        invoicePostingDelayRate: null,
        unsettledAccountsRatio: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        orderEntryDelayRatio: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur",
          }
        ],
        shipmentDelayRatio: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur",
          }
        ],
        productionReportDelayRatio: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur",
          }
        ],
        inspectionDelayRate: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur",
          }
        ],
        invoicePostingDelayRate: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur",
          }
        ],
        unsettledAccountsRatio: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur",
          }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleClose(done) {
      this.$confirm('确定关闭吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        done();
      }).catch(() => {
      });
    },
    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.SettlementList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.SettlementList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询日清日结列表 */
    getList() {
      this.loading = true;
      listDailyClearData(this.queryParams).then(response => {
        this.SettlementList = response.rows;
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
        edId: null,
        yearAndMonth: null,
        orderEntryDelayRatio: null,
        shipmentDelayRatio: null,
        productionReportDelayRatio: null,
        inspectionDelayRate: null,
        invoicePostingDelayRate: null,
        unsettledAccountsRatio: null,
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
      this.ids = selection.map(item => item.edId)
      this.dates = selection.map(item => item.yearAndMonth)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新增";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const edId = row.edId || this.ids
      getDailyClearData(edId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.edId != null) {
            updateDailyClearData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDailyClearData(this.form).then(response => {
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
      const edIds = row.edId || this.ids;
      const date = row.yearAndMonth || this.dates;
      this.$modal.confirm('是否删除日期为"' + date + '"的数据？').then(function () {
        return delDailyClearData(edIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

  }
};
</script>
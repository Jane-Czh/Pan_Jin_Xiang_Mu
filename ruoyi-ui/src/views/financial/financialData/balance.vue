<template>
  <div class="currentPage">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- 资产负债表 -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['financial:balance:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['financial:balance:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['financial:balance:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['financial:balance:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="balanceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id(主键)" align="center" prop="fbId" /> -->
      <el-table-column label="导入人" align="center" prop="createdBy" />
      <el-table-column label="导入时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="在途物资" align="center" prop="inTransitInventory" />
      <el-table-column label="原材料" align="center" prop="materials" />
      <el-table-column label="材料成本差异" align="center" prop="materialCostVariance" />
      <el-table-column label="材料成本差异-差异待分摊" align="center" prop="materialCostVarianceUnallocated" />
      <el-table-column label="当月原材料存货额(前四行计算和)" align="center" prop="monthlyRawMaterialInventory" />
      <el-table-column label="库存商品-半成品" align="center" prop="workInProgressSemiFinishedGoods" />
      <el-table-column label="产品成本差异-半成品" align="center" prop="productCostVarianceSemiFinishedGoods" />
      <el-table-column label="月末在制品" align="center" prop="workInProgressEndOfMonth" />
      <el-table-column label="当月在制品存货额(前三行和)" align="center" prop="monthlyWorkInProgressInventory" />
      <el-table-column label="库存商品-整车" align="center" prop="inventoryVehicles" />
      <el-table-column label="产品成本差异-产成品" align="center" prop="pcvFinished" />
      <el-table-column label="当月库存商品存货额(计算)" align="center" prop="monthAmountInStock" />
      <el-table-column label="月度存货总金额(导入来源为存货列)" align="center" prop="monthlyInventoryTotalAmount" />
      <el-table-column label="存货增长率/销售增长率(计算)" align="center" prop="growthRateInventorySales" />
      <el-table-column label="应收账款" align="center" prop="receivables" />
      <el-table-column label="应收帐款周转率(计算)" align="center" prop="turnoverRateReceivable" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['financial:balance:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['financial:balance:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改财务-资产负债对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="导入人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入导入人" />
        </el-form-item>
        <el-form-item label="导入时间" prop="createdTime">
          <el-date-picker clearable v-model="form.createdTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择导入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="在途物资" prop="inTransitInventory">
          <el-input v-model="form.inTransitInventory" placeholder="请输入在途物资" />
        </el-form-item>
        <el-form-item label="原材料" prop="materials">
          <el-input v-model="form.materials" placeholder="请输入原材料" />
        </el-form-item>
        <el-form-item label="材料成本差异" prop="materialCostVariance">
          <el-input v-model="form.materialCostVariance" placeholder="请输入材料成本差异" />
        </el-form-item>
        <el-form-item label="材料成本差异-差异待分摊" prop="materialCostVarianceUnallocated">
          <el-input v-model="form.materialCostVarianceUnallocated" placeholder="请输入材料成本差异-差异待分摊" />
        </el-form-item>
        <el-form-item label="当月原材料存货额(前四行计算和)" prop="monthlyRawMaterialInventory">
          <el-input v-model="form.monthlyRawMaterialInventory" placeholder="请输入当月原材料存货额(前四行计算和)" />
        </el-form-item>
        <el-form-item label="库存商品-半成品" prop="workInProgressSemiFinishedGoods">
          <el-input v-model="form.workInProgressSemiFinishedGoods" placeholder="请输入库存商品-半成品" />
        </el-form-item>
        <el-form-item label="产品成本差异-半成品" prop="productCostVarianceSemiFinishedGoods">
          <el-input v-model="form.productCostVarianceSemiFinishedGoods" placeholder="请输入产品成本差异-半成品" />
        </el-form-item>
        <el-form-item label="月末在制品" prop="workInProgressEndOfMonth">
          <el-input v-model="form.workInProgressEndOfMonth" placeholder="请输入月末在制品" />
        </el-form-item>
        <el-form-item label="当月在制品存货额(前三行和)" prop="monthlyWorkInProgressInventory">
          <el-input v-model="form.monthlyWorkInProgressInventory" placeholder="请输入当月在制品存货额(前三行和)" />
        </el-form-item>
        <el-form-item label="库存商品-整车" prop="inventoryVehicles">
          <el-input v-model="form.inventoryVehicles" placeholder="请输入库存商品-整车" />
        </el-form-item>
        <el-form-item label="产品成本差异-产成品" prop="pcvFinished">
          <el-input v-model="form.pcvFinished" placeholder="请输入产品成本差异-产成品" />
        </el-form-item>
        <el-form-item label="当月库存商品存货额(计算)" prop="monthAmountInStock">
          <el-input v-model="form.monthAmountInStock" placeholder="请输入当月库存商品存货额(计算)" />
        </el-form-item>
        <el-form-item label="月度存货总金额(导入来源为存货列)" prop="monthlyInventoryTotalAmount">
          <el-input v-model="form.monthlyInventoryTotalAmount" placeholder="请输入月度存货总金额(导入来源为存货列)" />
        </el-form-item>
        <el-form-item label="存货增长率/销售增长率(计算)" prop="growthRateInventorySales">
          <el-input v-model="form.growthRateInventorySales" placeholder="请输入存货增长率/销售增长率(计算)" />
        </el-form-item>
        <el-form-item label="应收账款" prop="receivables">
          <el-input v-model="form.receivables" placeholder="请输入应收账款" />
        </el-form-item>
        <el-form-item label="应收帐款周转率(计算)" prop="turnoverRateReceivable">
          <el-input v-model="form.turnoverRateReceivable" placeholder="请输入应收帐款周转率(计算)" />
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
import { listBalance, getBalance, delBalance, addBalance, updateBalance } from "@/api/financial/balance";

export default {
  name: "Balance",
  data() {
    return {
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
      // 财务-资产负债表格数据
      balanceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createdBy: null,
        createdTime: null,
        yearAndMonth: null,
        inTransitInventory: null,
        materials: null,
        materialCostVariance: null,
        materialCostVarianceUnallocated: null,
        monthlyRawMaterialInventory: null,
        workInProgressSemiFinishedGoods: null,
        productCostVarianceSemiFinishedGoods: null,
        workInProgressEndOfMonth: null,
        monthlyWorkInProgressInventory: null,
        inventoryVehicles: null,
        pcvFinished: null,
        monthAmountInStock: null,
        monthlyInventoryTotalAmount: null,
        growthRateInventorySales: null,
        receivables: null,
        turnoverRateReceivable: null
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
    /** 查询财务-资产负债列表 */
    getList() {
      this.loading = true;
      listBalance(this.queryParams).then(response => {
        this.balanceList = response.rows;
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
        fbId: null,
        createdBy: null,
        createdTime: null,
        yearAndMonth: null,
        inTransitInventory: null,
        materials: null,
        materialCostVariance: null,
        materialCostVarianceUnallocated: null,
        monthlyRawMaterialInventory: null,
        workInProgressSemiFinishedGoods: null,
        productCostVarianceSemiFinishedGoods: null,
        workInProgressEndOfMonth: null,
        monthlyWorkInProgressInventory: null,
        inventoryVehicles: null,
        pcvFinished: null,
        monthAmountInStock: null,
        monthlyInventoryTotalAmount: null,
        growthRateInventorySales: null,
        receivables: null,
        turnoverRateReceivable: null
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
      this.ids = selection.map(item => item.fbId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加财务-资产负债";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fbId = row.fbId || this.ids
      getBalance(fbId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改财务-资产负债";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fbId != null) {
            updateBalance(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBalance(this.form).then(response => {
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
      const fbIds = row.fbId || this.ids;
      this.$modal.confirm('是否确认删除财务-资产负债编号为"' + fbIds + '"的数据项？').then(function () {
        return delBalance(fbIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('financial/balance/export', {
        ...this.queryParams
      }, `balance_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style lang="scss" scoped>
.currentPage {
  padding: 16px;
}
</style>
<template>
  <div class="currentPage">
    <!-- 月度数据表 -->
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <div>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
            v-hasPermi="['financial:fill:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
            v-hasPermi="['financial:fill:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
            v-hasPermi="['financial:fill:remove']">删除</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="180"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="整机销售收入" align="center" prop="totalSalesRevenue" width="100" />
      <el-table-column label="集团外部销售收入" align="center" prop="externalGroupSalesRevenue" width="130" />
      <el-table-column label="整车产量" align="center" prop="totalVehicleProduction" />
      <el-table-column label="整车销量" align="center" prop="totalVehicleSales" />
      <el-table-column label="新产品销售收入" align="center" prop="newProductSalesRevenue" width="120" />
      <el-table-column label="特色产品收入" align="center" prop="specialtyProductRevenue" width="100" />
      <el-table-column label="整机销售成本" align="center" prop="totalSalesCost" width="100" />
      <el-table-column label="当月制造费用" align="center" prop="manufacturingExpensesMonth" width="100" />
      <el-table-column label="储备车金额" align="center" prop="reserveCarAmount" width="90" />
      <el-table-column label="资金周转率(次)" align="center" prop="capitalTurnoverRate" width="120" />
      <el-table-column label="库存商品周转率(次)" align="center" prop="inventoryTurnoverRate" width="140" />
      <el-table-column label="原材料周转率(次)" align="center" prop="rawMaterialTurnoverRate" width="120" />
      <el-table-column label="在制品周转率(次)" align="center" prop="inprogressTurnoverRate" width="120" />
      <el-table-column label="一年以上暂估行项目" align="center" prop="longEstimatedItems" width="140" />
      <!-- <el-table-column label="当日在制品金额" align="center" prop="inprogressDayrevenue" width="120" /> -->
      <el-table-column label="当月经济增加值" align="center" prop="addedValueMonthly" width="120" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['financial:data:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['financial:data:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[财务]手动填报指标对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="550px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="150px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="整机销售收入(万元)" prop="totalSalesRevenue">
          <el-input v-model="form.totalSalesRevenue" placeholder="请输入整机销售收入(万元)" />
        </el-form-item>
        <el-form-item label="集团外部销售收入(万元)" prop="externalGroupSalesRevenue">
          <el-input v-model="form.externalGroupSalesRevenue" placeholder="请输入集团外部销售收入(万元)" />
        </el-form-item>
        <el-form-item label="整车产量" prop="totalVehicleProduction">
          <el-input v-model="form.totalVehicleProduction" placeholder="请输入整车产量" />
        </el-form-item>
        <el-form-item label="整车销量" prop="totalVehicleSales">
          <el-input v-model="form.totalVehicleSales" placeholder="请输入整车销量" />
        </el-form-item>
        <el-form-item label="新产品销售收入(万元)" prop="newProductSalesRevenue">
          <el-input v-model="form.newProductSalesRevenue" placeholder="请输入新产品销售收入(万元)" />
        </el-form-item>
        <el-form-item label="特色产品收入(万元)" prop="specialtyProductRevenue">
          <el-input v-model="form.specialtyProductRevenue" placeholder="请输入特色产品收入(万元)" />
        </el-form-item>
        <el-form-item label="整机销售成本(万元)" prop="totalSalesCost">
          <el-input v-model="form.totalSalesCost" placeholder="请输入整机销售成本(万元)" />
        </el-form-item>
        <el-form-item label="当月制造费用(万元)" prop="manufacturingExpensesMonth">
          <el-input v-model="form.manufacturingExpensesMonth" placeholder="请输入当月制造费用(万元)" />
        </el-form-item>
        <el-form-item label="储备车金额(万元)" prop="reserveCarAmount">
          <el-input v-model="form.reserveCarAmount" placeholder="请输入储备车金额(万元)" />
        </el-form-item>
        <el-form-item label="资金周转率(次)" prop="capitalTurnoverRate">
          <el-input v-model="form.capitalTurnoverRate" placeholder="请输入资金周转率(次)" />
        </el-form-item>
        <el-form-item label="库存商品周转率(次)" prop="inventoryTurnoverRate">
          <el-input v-model="form.inventoryTurnoverRate" placeholder="请输入库存商品周转率(次)" />
        </el-form-item>
        <el-form-item label="原材料周转率(次)" prop="rawMaterialTurnoverRate">
          <el-input v-model="form.rawMaterialTurnoverRate" placeholder="请输入原材料周转率(次)" />
        </el-form-item>
        <el-form-item label="在制品周转率(次)" prop="inprogressTurnoverRate">
          <el-input v-model="form.inprogressTurnoverRate" placeholder="请输入在制品周转率(次)" />
        </el-form-item>
        <el-form-item label="一年以上暂估行项目" prop="longEstimatedItems">
          <el-input v-model="form.longEstimatedItems" placeholder="请输入一年以上暂估行项目" />
        </el-form-item>
        <el-form-item label="当月经济增加值(万元)" prop="addedValueMonthly">
          <el-input v-model="form.addedValueMonthly" placeholder="请输入当月经济增加值(万元)" />
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
import { listData, getData, delData, addData, updateData } from "@/api/financial/data";
import { numValidator, numValidatorPercentageNegative, numValidatorOnlyNature } from '@/api/financial/numValidator.js';
// import * as XLSX from "xlsx";
// import "font-awesome/css/font-awesome.css";
//引入font-awesome
// import "font-awesome/css/font-awesome.css";
import axios from "axios";

export default {
  name: "Data",
  data() {
    return {
      activeName: "1",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dates: [],
      showDialog: false,

      progress: 0,
      selectedType: '',

      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // [财务]手动填报指标表格数据
      dataList: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,

      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        totalSalesRevenue: null,
        externalGroupSalesRevenue: null,
        totalVehicleProduction: null,
        totalVehicleSales: null,
        newProductSalesRevenue: null,
        specialtyProductRevenue: null,
        totalSalesCost: null,
        manufacturingExpensesMonth: null,
        reserveCarAmount: null,
        capitalTurnoverRate: null,
        inventoryTurnoverRate: null,
        rawMaterialTurnoverRate: null,
        inprogressTurnoverRate: null,
        longEstimatedItems: null,
        inprogressDayrevenue: null,
        addedValueMonthly: null,
        Reporter: null,
        reportingDate: null,
        updatedBy: null,
        updatedDate: null

      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        totalSalesRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        externalGroupSalesRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        totalVehicleProduction: [
          {
            required: true,
            validator: numValidatorOnlyNature,
            trigger: "blur",
          }
        ],
        totalVehicleSales: [
          {
            required: true,
            validator: numValidatorOnlyNature,
            trigger: "blur",
          }
        ],
        newProductSalesRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        specialtyProductRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        totalSalesCost: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        manufacturingExpensesMonth: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        reserveCarAmount: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        // 周转率(%)
        capitalTurnoverRate: [
          {
            required: true,
            validator: numValidatorPercentageNegative,
            trigger: "blur",
          }
        ],
        inventoryTurnoverRate: [
          {
            required: true,
            validator: numValidatorPercentageNegative,
            trigger: "blur",
          }
        ],
        rawMaterialTurnoverRate: [
          {
            required: true,
            validator: numValidatorPercentageNegative,
            trigger: "blur",
          }
        ],
        inprogressTurnoverRate: [
          {
            required: true,
            validator: numValidatorPercentageNegative,
            trigger: "blur",
          }
        ],
        //
        addedValueMonthly: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        longEstimatedItems: [
          {
            required: true,
            validator: numValidator,
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
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },
    /** 查询[财务]手动填报指标列表 */
    getList() {
      this.loading = true;
      this.queryParams.page = 10
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
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
        fihfId: null,
        yearAndMonth: null,
        totalSalesRevenue: null,
        externalGroupSalesRevenue: null,
        totalVehicleProduction: null,
        totalVehicleSales: null,
        newProductSalesRevenue: null,
        specialtyProductRevenue: null,
        totalSalesCost: null,
        manufacturingExpensesMonth: null,
        reserveCarAmount: null,
        capitalTurnoverRate: null,
        inventoryTurnoverRate: null,
        rawMaterialTurnoverRate: null,
        inprogressTurnoverRate: null,
        longEstimatedItems: null,
        inprogressDayrevenue: null,
        addedValueMonthly: null,
        Reporter: null,
        reportingDate: null,
        updatedBy: null,
        updatedDate: null
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
      this.ids = selection.map(item => item.fihfId)
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
      const fihfId = row.fihfId || this.ids
      getData(fihfId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
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
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fihfId != null) {
            updateData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addData(this.form).then(response => {
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
      const fihfIds = row.fihfId || this.ids;
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delData(fihfIds);
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
  padding: 16px;
}
</style>

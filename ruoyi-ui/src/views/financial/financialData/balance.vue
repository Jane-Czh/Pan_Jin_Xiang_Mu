<template>
  <div class="currentPage">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- 资产负债表 -->
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
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" size="mini" plain @click="showDialog = true" v-if="true"
          v-hasPermi="['financial:balance:import']">导入Excel
        </el-button>

        <el-dialog title="导入Excel" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <!-- 下拉框 -->
          <el-form :model="form" ref="form" label-width="90px">

            <el-form-item label="上传表类">
              <span style="color: rgb(68, 140, 39);">资产负债表</span>
              <br>
              <el-date-picker clearable v-model="form3.yearAndMonth" type="month" value-format="yyyy-MM-dd"
                placeholder="请选择日期">
              </el-date-picker>

            </el-form-item>
          </el-form>

          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

          <span slot="footer" class="dialog-footer">
            <el-button @click="showDialog = false">取 消</el-button>
            <el-button type="primary" @click="fileSend()" v-if="!isLoading">确 定</el-button>
            <el-button type="primary" v-if="isLoading" :loading="true">上传中</el-button>
          </span>
        </el-dialog>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-download" @click="handleDownload" size="mini" plain v-if="true">下载模版文件
        </el-button>
        <!-- <el-button size="mini" type="text" icon="el-icon-edit" @click="handleDownload()">下载</el-button> -->

      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="balanceList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id(主键)" align="center" prop="fbId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="100"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="在途物资(万元)" align="center" prop="inTransitInventory" width="140" /> -->
      <el-table-column label="在途物资(万元)" align="center" prop="inTransitInventory" width="140">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.inTransitInventory) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="原材料(万元)" align="center" prop="materials" width="130" /> -->
      <el-table-column label="原材料(万元)" align="center" prop="materials" width="130">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.materials) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="材料成本差异(万元)" align="center" prop="materialCostVariance" width="140" /> -->
      <el-table-column label="材料成本差异(万元)" align="center" prop="materialCostVariance" width="140">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.materialCostVariance) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="材料成本差异-差异待分摊(万元)" align="center" prop="materialCostVarianceUnallocated" width="210" /> -->
      <el-table-column label="材料成本差异-差异待分摊(万元)" align="center" prop="materialCostVarianceUnallocated" width="210">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.materialCostVarianceUnallocated) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="当月原材料存货额(万元)" align="center" prop="monthlyRawMaterialInventory" width="170" /> -->
      <el-table-column label="当月原材料存货额(万元)" align="center" prop="monthlyRawMaterialInventory" width="170">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.monthlyRawMaterialInventory) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="库存商品-半成品(万元)" align="center" prop="workInProgressSemiFinishedGoods" width="160" /> -->
      <el-table-column label="库存商品-半成品(万元)" align="center" prop="workInProgressSemiFinishedGoods" width="160">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.workInProgressSemiFinishedGoods) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="产品成本差异-半成品(万元)" align="center" prop="productCostVarianceSemiFinishedGoods" width="180" /> -->
      <el-table-column label="产品成本差异-半成品(万元)" align="center" prop="productCostVarianceSemiFinishedGoods" width="180">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.productCostVarianceSemiFinishedGoods) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="月末在制品(万元)" align="center" prop="workInProgressEndOfMonth" width="120" /> -->
      <el-table-column label="月末在制品(万元)" align="center" prop="workInProgressEndOfMonth" width="120">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.workInProgressEndOfMonth) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="当月在制品存货额(万元)" align="center" prop="monthlyWorkInProgressInventory" width="160" /> -->
      <el-table-column label="当月在制品存货额(万元)" align="center" prop="monthlyWorkInProgressInventory" width="160">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.monthlyWorkInProgressInventory) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="库存商品-整车(万元)" align="center" prop="inventoryVehicles" width="140" /> -->
      <el-table-column label="库存商品-整车(万元)" align="center" prop="inventoryVehicles" width="140">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.inventoryVehicles) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="产品成本差异-产成品(万元)" align="center" prop="pcvFinished" width="180" /> -->
      <el-table-column label="产品成本差异-产成品(万元)" align="center" prop="pcvFinished" width="180">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.pcvFinished) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="当月库存商品存货额(万元)" align="center" prop="monthAmountInStock" width="180" /> -->
      <el-table-column label="当月库存商品存货额(万元)" align="center" prop="monthAmountInStock" width="180">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.monthAmountInStock) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="月度存货总金额(万元)" align="center" prop="monthlyInventoryTotalAmount" width="150" /> -->
      <el-table-column label="月度存货总金额(万元)" align="center" prop="monthlyInventoryTotalAmount" width="150">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.monthlyInventoryTotalAmount) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="存货增长率" align="center" prop="growthRateInventory" width="160" />
      <el-table-column label="销售增长率" align="center" prop="growthRateSales" width="160" />
      <!-- <el-table-column label="应收账款(万元)" align="center" prop="receivables" width="120" /> -->
      <el-table-column label="应收账款(万元)" align="center" prop="receivables" width="120">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.receivables) }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="应收帐款周转率(次)" align="center" prop="turnoverRateReceivable" width="140" /> -->
      <el-table-column label="应收帐款周转率(次)" align="center" prop="turnoverRateReceivable" width="140">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.turnoverRateReceivable) }}</span>
        </template>
      </el-table-column>
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
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="240px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="在途物资(万元)" prop="inTransitInventory">
          <el-input v-model="form.inTransitInventory" placeholder="请输入在途物资(万元)" />
        </el-form-item>
        <el-form-item label="原材料(万元)" prop="materials">
          <el-input v-model="form.materials" placeholder="请输入原材料(万元)" />
        </el-form-item>
        <el-form-item label="材料成本差异(万元)" prop="materialCostVariance">
          <el-input v-model="form.materialCostVariance" placeholder="请输入材料成本差异(万元)" />
        </el-form-item>
        <el-form-item label="材料成本差异-差异待分摊(万元)" prop="materialCostVarianceUnallocated">
          <el-input v-model="form.materialCostVarianceUnallocated" placeholder="请输入材料成本差异-差异待分摊(万元)" />
        </el-form-item>
        <el-form-item label="当月原材料存货额(万元)" prop="monthlyRawMaterialInventory">
          <el-input v-model="form.monthlyRawMaterialInventory" placeholder="请输入当月原材料存货额(万元)" />
        </el-form-item>
        <el-form-item label="库存商品-半成品(万元)" prop="workInProgressSemiFinishedGoods">
          <el-input v-model="form.workInProgressSemiFinishedGoods" placeholder="请输入库存商品-半成品(万元)" />
        </el-form-item>
        <el-form-item label="产品成本差异-半成品(万元)" prop="productCostVarianceSemiFinishedGoods">
          <el-input v-model="form.productCostVarianceSemiFinishedGoods" placeholder="请输入产品成本差异-半成品(万元)" />
        </el-form-item>
        <el-form-item label="月末在制品(万元)" prop="workInProgressEndOfMonth">
          <el-input v-model="form.workInProgressEndOfMonth" placeholder="请输入月末在制品(万元)" />
        </el-form-item>
        <el-form-item label="当月在制品存货额(万元)" prop="monthlyWorkInProgressInventory">
          <el-input v-model="form.monthlyWorkInProgressInventory" placeholder="请输入当月在制品存货额(万元)" />
        </el-form-item>
        <el-form-item label="库存商品-整车(万元)" prop="inventoryVehicles">
          <el-input v-model="form.inventoryVehicles" placeholder="请输入库存商品-整车(万元)" />
        </el-form-item>
        <el-form-item label="产品成本差异-产成品(万元)" prop="pcvFinished">
          <el-input v-model="form.pcvFinished" placeholder="请输入产品成本差异-产成品(万元)" />
        </el-form-item>
        <!-- <el-form-item label="当月库存商品存货额" prop="monthAmountInStock">
          <el-input v-model="form.monthAmountInStock" placeholder="请输入当月库存商品存货额" />
        </el-form-item> -->
        <el-form-item label="当月存货总额(万元)" prop="monthlyInventoryTotalAmount">
          <el-input v-model="form.monthlyInventoryTotalAmount" placeholder="请输入当月存货总额(万元)" />
        </el-form-item>
        <!-- <el-form-item label="存货增长率" prop="growthRateInventory">
          <el-input v-model="form.growthRateInventory" placeholder="请输入存货增长率/销售增长率" />
        </el-form-item> -->
        <!-- <el-form-item label="销售增长率" prop="growthRateSales">
          <el-input v-model="form.growthRateSales" placeholder="请输入存货增长率/销售增长率" />
        </el-form-item> -->
        <el-form-item label="应收账款(万元)" prop="receivables">
          <el-input v-model="form.receivables" placeholder="请输入应收账款(万元)" />
        </el-form-item>
        <!-- <el-form-item label="应收帐款周转率" prop="turnoverRateReceivable">
          <el-input v-model="form.turnoverRateReceivable" placeholder="请输入应收帐款周转率" />
        </el-form-item> -->
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
import { numValidator } from '@/api/financial/numValidator.js';
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';

export default {
  name: "Balance",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dates: [],

      // 导入Excel对话框
      selectedType: '',
      progress: 0,
      showDialog: false,
      isLoading: false,
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
        growthRateInventory: null,
        growthRateSales: null,
        receivables: null,
        turnoverRateReceivable: null
      },
      // 表单参数
      form: {},
      form3: {
        yearAndMonth: null
      },
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        inTransitInventory: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        materials: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        materialCostVariance: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        materialCostVarianceUnallocated: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        monthlyRawMaterialInventory: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        workInProgressSemiFinishedGoods: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        monthlyWorkInProgressInventory: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        inventoryVehicles: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        pcvFinished: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],

        monthlyInventoryTotalAmount: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],

        receivables: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],

        productCostVarianceSemiFinishedGoods: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        workInProgressEndOfMonth: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        //不用输入
        // turnoverRateReceivable: [
        //   { required: true, message: "数据不能为空", trigger: "blur" }
        // ],
        // monthAmountInStock: [
        //   { required: true, message: "数据不能为空", trigger: "blur" }
        // ],
        // growthRateInventory: [
        //   { required: true, message: "数据不能为空", trigger: "blur" }
        // ],
        // growthRateSales: [
        //   { required: true, message: "数据不能为空", trigger: "blur" }
        // ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
    },

    handleDownload() {
      const url = "/profile/modelFile/资产负债表样表.xlsx";
      handleTrueDownload(url);
    },


    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },

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
        growthRateInventory: null,
        growthRateSales: null,
        receivables: null,
        turnoverRateReceivable: null
      };
      this.resetForm("form");
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
      const fbId = row.fbId || this.ids
      getBalance(fbId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
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
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delBalance(fbIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    //检查输入的数据


    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        // this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    /** 导入按钮 */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      const yearAndMonth = this.form3.yearAndMonth;
      if (file === undefined || yearAndMonth == null) {
        if (file === undefined) {
          this.$message.error("请选择文件!");
          return;
        } else {
          this.$message.error("请选择日期!");
          return;
        }
      } else {
        this.isLoading = true;
        formData.append("yearAndMonth", yearAndMonth);
        formData.append("BalanceFile", file);
        const aimUrl = `/financial/data/balance/import`
        uploadFile(formData, aimUrl)
          .then(data => {
            // 处理上传成功的情况
            this.$message.success("上传成功");
            this.getList();
          })
          .catch(error => {
            // 处理上传失败的情况
            console.error('上传失败：', error);
            // this.$message.error("上传失败，请重试");
          })
          .finally(() => {
            // 无论成功或失败，都关闭上传面板
            this.showDialog = false;
            this.isLoading = false;
          });

      }
    },
  }
};
</script>

<style lang="scss" scoped>
.currentPage {
  padding: 16px;
}
</style>

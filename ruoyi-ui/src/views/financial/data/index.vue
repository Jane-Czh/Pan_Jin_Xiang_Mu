<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年月" prop="yearAndMonth">
        <el-date-picker clearable
          v-model="queryParams.yearAndMonth"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择年月">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="整机销售收入" prop="totalSalesRevenue">
        <el-input
          v-model="queryParams.totalSalesRevenue"
          placeholder="请输入整机销售收入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="集团外部销售收入" prop="externalGroupSalesRevenue">
        <el-input
          v-model="queryParams.externalGroupSalesRevenue"
          placeholder="请输入集团外部销售收入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整车产量" prop="totalVehicleProduction">
        <el-input
          v-model="queryParams.totalVehicleProduction"
          placeholder="请输入整车产量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整车销量" prop="totalVehicleSales">
        <el-input
          v-model="queryParams.totalVehicleSales"
          placeholder="请输入整车销量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="新产品销售收入" prop="newProductSalesRevenue">
        <el-input
          v-model="queryParams.newProductSalesRevenue"
          placeholder="请输入新产品销售收入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="特色产品收入" prop="specialtyProductRevenue">
        <el-input
          v-model="queryParams.specialtyProductRevenue"
          placeholder="请输入特色产品收入"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="整机销售成本" prop="totalSalesCost">
        <el-input
          v-model="queryParams.totalSalesCost"
          placeholder="请输入整机销售成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当月制造费用" prop="manufacturingExpensesMonth">
        <el-input
          v-model="queryParams.manufacturingExpensesMonth"
          placeholder="请输入当月制造费用"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="储备车金额" prop="reserveCarAmount">
        <el-input
          v-model="queryParams.reserveCarAmount"
          placeholder="请输入储备车金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="资金周转率" prop="capitalTurnoverRate">
        <el-input
          v-model="queryParams.capitalTurnoverRate"
          placeholder="请输入资金周转率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="库存商品周转率" prop="inventoryTurnoverRate">
        <el-input
          v-model="queryParams.inventoryTurnoverRate"
          placeholder="请输入库存商品周转率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="原材料周转率" prop="rawMaterialTurnoverRate">
        <el-input
          v-model="queryParams.rawMaterialTurnoverRate"
          placeholder="请输入原材料周转率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="在制品周转率" prop="inprogressTurnoverRate">
        <el-input
          v-model="queryParams.inprogressTurnoverRate"
          placeholder="请输入在制品周转率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一年以上暂估行项目" prop="longEstimatedItems">
        <el-input
          v-model="queryParams.longEstimatedItems"
          placeholder="请输入一年以上暂估行项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当日在制品金额" prop="inprogressDayrevenue">
        <el-input
          v-model="queryParams.inprogressDayrevenue"
          placeholder="请输入当日在制品金额"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当月经济增加值" prop="addedValueMonthly">
        <el-input
          v-model="queryParams.addedValueMonthly"
          placeholder="请输入当月经济增加值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="填报人" prop="Reporter">
        <el-input
          v-model="queryParams.Reporter"
          placeholder="请输入填报人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="填报时间" prop="reportingDate">
        <el-date-picker clearable
          v-model="queryParams.reportingDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择填报时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新人" prop="updatedBy">
        <el-input
          v-model="queryParams.updatedBy"
          placeholder="请输入更新人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="跟新时间" prop="updatedDate">
        <el-date-picker clearable
          v-model="queryParams.updatedDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择跟新时间">
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
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['financial:data:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['financial:data:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['financial:data:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
           <!--Excel 参数导入 -->
      <el-button
        type="primary"
        @click="showDialog = true"

        v-if="true"

        ><i class="fa fa-download"></i>导入Excel文件
      </el-button>

      <el-dialog
        title="导入Excel文件"
        :visible.sync="showDialog"
        width="30%"
        :before-close="handleClose"
        @close="resetFileInput"
      >
       <!-- 下拉框 -->
  <el-form :model="form" ref="form" label-width="90px">
    <el-form-item label="选择表类型">
      <el-select v-model="selectedType" placeholder="请选择Excel类型">
        <el-option label="利润表" value="profit"></el-option>
        <el-option label="资产负债表" value="balance"></el-option>
      </el-select>
    </el-form-item>
  </el-form>

        <i class="el-icon-upload"></i>
        <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

        <!-- 进度动画条 -->
        <div v-if="progress > 0">
          <el-progress
            :percentage="progress"
            color="rgb(19, 194, 194)"
          ></el-progress>
        </div>

        <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取 消</el-button>
          <el-button type="primary" @click="fileSend()">确 定</el-button>
        </span>
      </el-dialog>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['financial:data:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>
    </div>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="fihfId" />
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="整机销售收入" align="center" prop="totalSalesRevenue" />
      <el-table-column label="集团外部销售收入" align="center" prop="externalGroupSalesRevenue" />
      <el-table-column label="整车产量" align="center" prop="totalVehicleProduction" />
      <el-table-column label="整车销量" align="center" prop="totalVehicleSales" />
      <el-table-column label="新产品销售收入" align="center" prop="newProductSalesRevenue" />
      <el-table-column label="特色产品收入" align="center" prop="specialtyProductRevenue" />
      <el-table-column label="整机销售成本" align="center" prop="totalSalesCost" />
      <el-table-column label="当月制造费用" align="center" prop="manufacturingExpensesMonth" />
      <el-table-column label="储备车金额" align="center" prop="reserveCarAmount" />
      <el-table-column label="资金周转率" align="center" prop="capitalTurnoverRate" />
      <el-table-column label="库存商品周转率" align="center" prop="inventoryTurnoverRate" />
      <el-table-column label="原材料周转率" align="center" prop="rawMaterialTurnoverRate" />
      <el-table-column label="在制品周转率" align="center" prop="inprogressTurnoverRate" />
      <el-table-column label="一年以上暂估行项目" align="center" prop="longEstimatedItems" />
      <el-table-column label="当日在制品金额" align="center" prop="inprogressDayrevenue" />
      <el-table-column label="当月经济增加值" align="center" prop="addedValueMonthly" />
      <el-table-column label="填报人" align="center" prop="Reporter" />
      <el-table-column label="填报时间" align="center" prop="reportingDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reportingDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新人" align="center" prop="updatedBy" />
      <el-table-column label="跟新时间" align="center" prop="updatedDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updatedDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['financial:data:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['financial:data:remove']"
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

    <!-- 添加或修改[财务]手动填报指标对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable
            v-model="form.yearAndMonth"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="整机销售收入" prop="totalSalesRevenue">
          <el-input v-model="form.totalSalesRevenue" placeholder="请输入整机销售收入" />
        </el-form-item>
        <el-form-item label="集团外部销售收入" prop="externalGroupSalesRevenue">
          <el-input v-model="form.externalGroupSalesRevenue" placeholder="请输入集团外部销售收入" />
        </el-form-item>
        <el-form-item label="整车产量" prop="totalVehicleProduction">
          <el-input v-model="form.totalVehicleProduction" placeholder="请输入整车产量" />
        </el-form-item>
        <el-form-item label="整车销量" prop="totalVehicleSales">
          <el-input v-model="form.totalVehicleSales" placeholder="请输入整车销量" />
        </el-form-item>
        <el-form-item label="新产品销售收入" prop="newProductSalesRevenue">
          <el-input v-model="form.newProductSalesRevenue" placeholder="请输入新产品销售收入" />
        </el-form-item>
        <el-form-item label="特色产品收入" prop="specialtyProductRevenue">
          <el-input v-model="form.specialtyProductRevenue" placeholder="请输入特色产品收入" />
        </el-form-item>
        <el-form-item label="整机销售成本" prop="totalSalesCost">
          <el-input v-model="form.totalSalesCost" placeholder="请输入整机销售成本" />
        </el-form-item>
        <el-form-item label="当月制造费用" prop="manufacturingExpensesMonth">
          <el-input v-model="form.manufacturingExpensesMonth" placeholder="请输入当月制造费用" />
        </el-form-item>
        <el-form-item label="储备车金额" prop="reserveCarAmount">
          <el-input v-model="form.reserveCarAmount" placeholder="请输入储备车金额" />
        </el-form-item>
        <el-form-item label="资金周转率" prop="capitalTurnoverRate">
          <el-input v-model="form.capitalTurnoverRate" placeholder="请输入资金周转率" />
        </el-form-item>
        <el-form-item label="库存商品周转率" prop="inventoryTurnoverRate">
          <el-input v-model="form.inventoryTurnoverRate" placeholder="请输入库存商品周转率" />
        </el-form-item>
        <el-form-item label="原材料周转率" prop="rawMaterialTurnoverRate">
          <el-input v-model="form.rawMaterialTurnoverRate" placeholder="请输入原材料周转率" />
        </el-form-item>
        <el-form-item label="在制品周转率" prop="inprogressTurnoverRate">
          <el-input v-model="form.inprogressTurnoverRate" placeholder="请输入在制品周转率" />
        </el-form-item>
        <el-form-item label="一年以上暂估行项目" prop="longEstimatedItems">
          <el-input v-model="form.longEstimatedItems" placeholder="请输入一年以上暂估行项目" />
        </el-form-item>
        <el-form-item label="当日在制品金额" prop="inprogressDayrevenue">
          <el-input v-model="form.inprogressDayrevenue" placeholder="请输入当日在制品金额" />
        </el-form-item>
        <el-form-item label="当月经济增加值" prop="addedValueMonthly">
          <el-input v-model="form.addedValueMonthly" placeholder="请输入当月经济增加值" />
        </el-form-item>
        <el-form-item label="填报人" prop="Reporter">
          <el-input v-model="form.Reporter" placeholder="请输入填报人" />
        </el-form-item>
        <el-form-item label="填报时间" prop="reportingDate">
          <el-date-picker clearable
            v-model="form.reportingDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择填报时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新人" prop="updatedBy">
          <el-input v-model="form.updatedBy" placeholder="请输入更新人" />
        </el-form-item>
        <el-form-item label="跟新时间" prop="updatedDate">
          <el-date-picker clearable
            v-model="form.updatedDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择跟新时间">
          </el-date-picker>
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
// import * as XLSX from "xlsx";
// import "font-awesome/css/font-awesome.css";
//引入font-awesome
// import "font-awesome/css/font-awesome.css";
import axios from "axios";


export default {
  name: "Data",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
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
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询[财务]手动填报指标列表 */
    getList() {
      this.loading = true;
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
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加[财务]手动填报指标";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fihfId = row.fihfId || this.ids
      getData(fihfId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改[财务]手动填报指标";
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
      this.$modal.confirm('是否确认删除[财务]手动填报指标编号为"' + fihfIds + '"的数据项？').then(function() {
        return delData(fihfIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
     checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
     //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
     handleClose(done) {
      // 在关闭对话框之前，你可以执行任何必要的操作
      // 例如，你可能希望在关闭之前与用户进行确认
      if (confirm("确定要关闭吗？")) {
        done(); // 调用 done() 方法来关闭对话框
      }
    },
     /** 导入按钮 */
     fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      formData.append("excelFile", file);
      

       // 根据用户选择的 Excel 类型执行不同的操作
  if (this.selectedType === 'profit') {
      axios({
        method: "post",
        // url: this.$http.url('/financial/data/upload'),
          url:"http://localhost:8080/financial/interests/importTable",
        // params: this.$http.adornParams({
        //   userName: this.$store.state.user.name,
        // }),
        headers: {
          "Content-Type": "multipart/form-data",
        },
        withCredentials: true,
        data: formData,
        onUploadProgress: (progressEvent) => {
          this.progress = Math.round(
            (progressEvent.loaded * 100) / progressEvent.total
          );
        },
      });
    console.log("利润表")
  } else if (this.selectedType === 'balance') {
       axios({
        method: "post",
        // url: this.$http.url('/financial/data/upload'),
          url:"http://localhost:8080/financial/balance/importTable",
        // params: this.$http.adornParams({
        //   userName: this.$store.state.user.name,
        // }),
        headers: {
          "Content-Type": "multipart/form-data",
        },
        withCredentials: true,
        data: formData,
        onUploadProgress: (progressEvent) => {
          this.progress = Math.round(
            (progressEvent.loaded * 100) / progressEvent.total
          );
        },
      });
    //  axios.post('financial/balance/importTable', formData, {
    //   headers: {
    //     'Content-Type': 'multipart/form-data'
    //   },
    //   onUploadProgress: (progressEvent) => {
    //     this.progress = Math.round((progressEvent.loaded * 100) / progressEvent.total);
    //   }
    // });
    console.log("资产负债表类型")
  }
      // axios({
      //   method: "post",
      //   // url: this.$http.url('/financial/data/upload'),
      //     url:"http://localhost:8080/financial/interests/importTable",
      //   // params: this.$http.adornParams({
      //   //   userName: this.$store.state.user.name,
      //   // }),
      //   headers: {
      //     "Content-Type": "multipart/form-data",
      //   },
      //   withCredentials: true,
      //   data: formData,
      //   onUploadProgress: (progressEvent) => {
      //     this.progress = Math.round(
      //       (progressEvent.loaded * 100) / progressEvent.total
      //     );
      //   },
      // });
      this.$message.success("上传成功");

      // .then((response) => {

      //   if(response === 导入成功){
      //      // 处理成功后的操作
      //   this.progress = 100; // 确保进度条显示100%
      //   this.$message.success("上传成功");
      //   }

      // })
      // .catch((error) => {
      //   // 处理错误
      //   this.$message.error("上传失败");
      // });

      setTimeout(() => {
        this.showDialog = false; // 关闭上传面板

        // location.reload(); // 调用此方法刷新页面数据
      }, 2000); // 2000毫秒后关闭
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('financial/data/export', {
        ...this.queryParams
      }, `data_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

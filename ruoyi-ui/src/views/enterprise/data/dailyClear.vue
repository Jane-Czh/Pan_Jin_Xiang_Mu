<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="类别" prop="flag">
        <el-select v-model="queryParams.flag" placeholder="请选择类别" clearable @keyup.enter.native="handleQuery">
          <el-option value="1" label="目标值"></el-option>
          <el-option value="2" label="实际值"></el-option>
          <el-option value="3" label="得分"></el-option>
        </el-select>
      </el-form-item>
      <!-- <el-form-item label="类别" prop="flag">
        <el-input v-model="queryParams.flag" placeholder="请输入SD 销售订单有效性考核得分" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
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
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['enterprise:dailyclear:export']">导出</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
          v-hasPermi="['enterprise:dailyclear:import']">导入Excel文件
        </el-button>
        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类">
              <span style="color: rgb(68, 140, 39);">日清日结表</span>
              <br>
              <el-date-picker clearable v-model="form3.yearAndMonth" type="year" value-format="yyyy-MM-dd"
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
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="SettlementList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日期" fixed align="center" prop="yearAndMonth" sortable="custom" width="130">
        <template slot-scope="scope">
          <span v-if="scope.row.flag === 1">{{ parseTime(scope.row.yearAndMonth, '{y}') }}</span>
          <span v-else>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类别" align="center" prop="flag" width="140">
        <template slot-scope="scope">
          <span v-if="scope.row.flag === 1">目标值</span>
          <span v-else-if="scope.row.flag === 2">实际值</span>
          <span v-else-if="scope.row.flag === 3">得分</span>
          <span v-else>--</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="销售订单录入不及时得分" align="center" prop="orderEntryDelayScore" width="180" /> -->
      <el-table-column label="销售订单录入不及时比例" align="center" prop="orderEntryDelayRatio" width="200" />
      <!-- <el-table-column label="销售订单不及时发货得分" align="center" prop="shipmentDelayScore" width="180" /> -->
      <el-table-column label="销售订单不及时发货比例" align="center" prop="shipmentDelayRatio" width="200" />
      <!-- <el-table-column label="生产订单不及时报工得分" align="center" prop="productionReportDelayScore" width="180" /> -->
      <el-table-column label="生产订单不及时报工比例" align="center" prop="productionReportDelayRatio" width="200" />
      <!-- <el-table-column label="成品检验业务不及时得分" align="center" prop="inspectionDelayScore" width="180" /> -->
      <el-table-column label="成品检验业务不及时率" align="center" prop="inspectionDelayRate" width="180" />
      <!-- <el-table-column label="销售发票过账不及得分" align="center" prop="invoicePostingDelayScore" width="180" /> -->
      <el-table-column label="销售发票过账不及时率" align="center" prop="invoicePostingDelayRate" width="180" />
      <!-- <el-table-column label="客户未清账得分" align="center" prop="unsettledAccountsScore" width="180" /> -->
      <el-table-column label="客户未清账比例" align="center" prop="unsettledAccountsRatio" width="180" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right" width="120">
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
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="目标值日期" prop="yearAndMonth" v-show="showTargetDate">
          <el-date-picker clearable v-model="form.yearAndMonth" type="year" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="日期" prop="yearAndMonth" v-show="!showTargetDate">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="类别" prop="flag" v-show="title !== '修改日清日结'">
          <el-select v-model="form.flag" placeholder="请选择类别" @change="onMonthChange">
            <el-option label="目标值" value="1"></el-option>
            <el-option label="实际值" value="2"></el-option>
            <el-option label="得分" value="3"></el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="销售订单录入不及时得分" prop="orderEntryDelayScore">
          <el-input v-model="form.orderEntryDelayScore" placeholder="请输入销售订单录入不及时得分" />
        </el-form-item> -->
        <el-form-item label="销售订单录入不及时比例" prop="orderEntryDelayRatio">
          <el-input v-model="form.orderEntryDelayRatio" placeholder="请输入销售订单录入不及时比例" />
        </el-form-item>
        <!-- <el-form-item label="销售订单不及时发货得分" prop="shipmentDelayScore">
          <el-input v-model="form.shipmentDelayScore" placeholder="请输入销售订单不及时发货得分" />
        </el-form-item> -->
        <el-form-item label="销售订单不及时发货比例" prop="shipmentDelayRatio">
          <el-input v-model="form.shipmentDelayRatio" placeholder="请输入销售订单不及时发货比例" />
        </el-form-item>
        <!-- <el-form-item label="生产订单不及时报工得分" prop="productionReportDelayScore">
          <el-input v-model="form.productionReportDelayScore" placeholder="请输入生产订单不及时报工得分" />
        </el-form-item> -->
        <el-form-item label="生产订单不及时报工比例" prop="productionReportDelayRatio">
          <el-input v-model="form.productionReportDelayRatio" placeholder="请输入生产订单不及时报工比例" />
        </el-form-item>
        <!-- <el-form-item label="成品检验业务不及时得分" prop="inspectionDelayScore">
          <el-input v-model="form.inspectionDelayScore" placeholder="请输入成品检验业务不及时得分" />
        </el-form-item> -->
        <el-form-item label="成品检验业务不及时率" prop="inspectionDelayRate">
          <el-input v-model="form.inspectionDelayRate" placeholder="请输入成品检验业务不及时率" />
        </el-form-item>
        <!-- <el-form-item label="销售发票过账不及得分" prop="invoicePostingDelayScore">
          <el-input v-model="form.invoicePostingDelayScore" placeholder="请输入销售发票过账不及得分" />
        </el-form-item> -->
        <el-form-item label="销售发票过账不及时率" prop="invoicePostingDelayRate">
          <el-input v-model="form.invoicePostingDelayRate" placeholder="请输入销售发票过账不及时率" />
        </el-form-item>
        <!-- <el-form-item label="客户未清账得分" prop="unsettledAccountsScore">
          <el-input v-model="form.unsettledAccountsScore" placeholder="请输入客户未清账得分" />
        </el-form-item> -->
        <el-form-item label="客户未清账比例" prop="unsettledAccountsRatio">
          <el-input v-model="form.unsettledAccountsRatio" placeholder="请输入客户未清账比例" />
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
import { listDailyClearData, getDailyClearData, delDailyClearData, addDailyClearData, updateDailyClearData } from "@/api/enterprise/dailyClearData";
import { numValidatorPercentage, numValidator } from '@/api/financial/numValidator.js';
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';
import moment from 'moment';
export default {
  name: "Settlement",
  data() {
    return {
      showTargetDate: false,
      isLoading: false,
      // 遮罩层
      loading: true,
      selectedType: '',
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
      showDialog: false,
      // 日清日结表格数据
      SettlementList: [],
      // 弹出层标题
      title: "",
      open2: false,
      form3: {
        yearAndMonth: null
      },
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        orderEntryDelayScore: null,
        orderEntryDelayRatio: null,
        shipmentDelayScore: null,
        shipmentDelayRatio: null,
        productionReportDelayScore: null,
        productionReportDelayRatio: null,
        inspectionDelayScore: null,
        inspectionDelayRate: null,
        invoicePostingDelayScore: null,
        invoicePostingDelayRate: null,
        unsettledAccountsScore: null,
        unsettledAccountsRatio: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        flag: [
          { required: true, message: "类别不能为空", trigger: "blur" }
        ],
        orderEntryDelayRatio: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        orderEntryDelayScore: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        shipmentDelayRatio: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        shipmentDelayScore: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        productionReportDelayRatio: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        productionReportDelayScore: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        inspectionDelayRate: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        inspectionDelayScore: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        invoicePostingDelayRate: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        invoicePostingDelayScore: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        unsettledAccountsRatio: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        unsettledAccountsScore: [
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
    handleDownload() {

      const url = "/profile/excel_templates/日清日结指标模版.xlsx";
      handleTrueDownload(url);
    },
    /** 查询日清日结列表 */
    getList() {
      this.loading = true;
      listDailyClearData(this.queryParams).then(response => {
        this.SettlementList = response.rows;
        this.total = response.total;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'yearAndMonth',
          order: 'descending' // 默认降序
        });
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    onMonthChange(value) {

      // console.log(value)
      if (value == 1) {
        this.showTargetDate = true;
      } else {
        this.showTargetDate = false;
      }
      console.log(this.showTargetDate)

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
    // 表单重置
    reset() {
      this.form = {
        edId: null,
        yearAndMonth: null,
        orderEntryDelayScore: null,
        orderEntryDelayRatio: null,
        shipmentDelayScore: null,
        shipmentDelayRatio: null,
        productionReportDelayScore: null,
        productionReportDelayRatio: null,
        inspectionDelayScore: null,
        inspectionDelayRate: null,
        invoicePostingDelayScore: null,
        invoicePostingDelayRate: null,
        unsettledAccountsScore: null,
        unsettledAccountsRatio: null,
        flag: null,
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
      this.title = "添加日清日结";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const edId = row.edId || this.ids
      getDailyClearData(edId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改日清日结";
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
      let date = row.yearAndMonth || this.dates;
      date = moment(date).format('YYYY-MM')
      this.$modal.confirm('是否删除日期为"' + date + '"的数据？').then(function () {
        return delDailyClearData(edIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

    // 导入excel，检查文件类型
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm" && fileExt.toLowerCase() !== "xls") {
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
        formData.append("multipartFile", file);
        const aimUrl = `/enterprise/data/dailyclear/read`
        uploadFile(formData, aimUrl)
          .then(data => {
            // 处理上传成功的情况
            this.$message.success("上传成功");
            this.getList();
            this.showDialog = false;
            this.isLoading = false;
          })
          .catch(error => {
            // 处理上传失败的情况
            console.error('上传失败：', error);
            // this.$message.error("上传失败，请重试");
            this.isLoading = false;
          });
      }
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('enterprise/Settlement/export', {
        ...this.queryParams
      }, `Settlement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

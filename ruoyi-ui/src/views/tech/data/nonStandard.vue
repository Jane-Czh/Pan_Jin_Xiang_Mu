<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="序号" prop="tableId">
        <el-input v-model="queryParams.tableId" placeholder="请输入序号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="订单号" prop="orderId">
        <el-input v-model="queryParams.orderId" placeholder="请输入订单号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="车型" prop="carModel">
        <el-input v-model="queryParams.carModel" placeholder="请输入车型" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="配置信息" prop="carConfiguration">
        <el-input v-model="queryParams.carConfiguration" placeholder="请输入配置信息" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="负责人" prop="principal">
        <el-input v-model="queryParams.principal" placeholder="请输入负责人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="参与人" prop="participants">
        <el-input v-model="queryParams.participants" placeholder="请输入参与人" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="接单日期" prop="pickupDate">
        <el-input v-model="queryParams.pickupDate" placeholder="请输入接单日期" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="转单日期" prop="transferDate">
        <el-input v-model="queryParams.transferDate" placeholder="请输入转单日期" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="累计时长" prop="totalDurationHours">
        <el-input v-model="queryParams.totalDurationHours" placeholder="请输入累计时长" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="累计天数" prop="totalDurationDays">
        <el-input v-model="queryParams.totalDurationDays" placeholder="请输入累计天数" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="超时说明" prop="overtimeRemark">
        <el-input v-model="queryParams.overtimeRemark" placeholder="请输入超时说明" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <!-- <el-form-item label="备注" prop="comments">
        <el-input v-model="queryParams.comments" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['tech:Tech_Non_Standard_Order:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['tech:Tech_Non_Standard_Order:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['tech:Tech_Non_Standard_Order:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['tech:Tech_Non_Standard_Order:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
          v-hasPermi="['tech:Tech_Non_Standard_Order:import']">导入Excel文件
        </el-button>
        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类:">
              <span style="color: rgb(68, 140, 39);">非标订单</span>
              <br>
              <el-date-picker clearable v-model="form3.yearAndMonth" type="month" value-format="yyyy-MM-dd"
                placeholder="请选择日期">
              </el-date-picker>
            </el-form-item>
          </el-form>
          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />
          <!-- 进度动画条 -->
          <!-- <div v-if="progress > 0">
            <el-progress :percentage="progress" color="rgb(19, 194, 194)"></el-progress>
          </div> -->
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

    <el-table v-loading="loading" :data="Tech_Non_Standard_OrderList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="${comment}" align="center" prop="tnId" /> -->
      <el-table-column fixed label="日期" align="center" prop="yearAndMonth" width="100">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="序号" align="center" prop="tableId" width="55" />
      <el-table-column label="订单号" align="center" prop="orderId" />
      <el-table-column label="车型" align="center" prop="carModel" />
      <el-table-column label="配置信息" align="center" prop="carConfiguration" width="200" />
      <el-table-column label="负责人" align="center" prop="principal" width="140" />
      <el-table-column label="参与人" align="center" prop="participants" width="100" />
      <el-table-column label="接单日期" align="center" prop="pickupDate" />
      <el-table-column label="转单日期" align="center" prop="transferDate" />
      <el-table-column label="累计时长" align="center" prop="totalDurationHours">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.totalDurationHours) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="累计天数" align="center" prop="totalDurationDays">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.totalDurationDays) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="超时说明" align="center" prop="overtimeRemark" />
      <el-table-column label="备注" align="center" prop="comments" width="200" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['tech:Tech_Non_Standard_Order:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['tech:Tech_Non_Standard_Order:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改Tech_Non_Standard_Order对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="序号" prop="tableId">
          <el-input v-model="form.tableId" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="订单号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="车型" prop="carModel">
          <el-input v-model="form.carModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="配置信息" prop="carConfiguration">
          <el-input v-model="form.carConfiguration" type="textarea" placeholder="请输入配置信息" />
        </el-form-item>
        <el-form-item label="负责人" prop="principal">
          <el-input v-model="form.principal" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="参与人" prop="participants">
          <el-input v-model="form.participants" placeholder="请输入参与人" />
        </el-form-item>
        <el-form-item label="接单日期" prop="pickupDate">
          <el-input v-model="form.pickupDate" placeholder="请输入接单日期" />
        </el-form-item>
        <el-form-item label="转单日期" prop="transferDate">
          <el-input v-model="form.transferDate" placeholder="请输入转单日期" />
        </el-form-item>
        <el-form-item label="累计时长" prop="totalDurationHours">
          <el-input v-model="form.totalDurationHours" placeholder="请输入累计时长" />
        </el-form-item>
        <el-form-item label="累计天数" prop="totalDurationDays">
          <el-input v-model="form.totalDurationDays" placeholder="请输入累计天数" />
        </el-form-item>
        <el-form-item label="超时说明" prop="overtimeRemark">
          <el-input v-model="form.overtimeRemark" placeholder="请输入超时说明" />
        </el-form-item>
        <el-form-item label="备注" prop="comments">
          <el-input v-model="form.comments" type="textarea" placeholder="请输入备注" />
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
import { listTech_Non_Standard_Order, getTech_Non_Standard_Order, delTech_Non_Standard_Order, addTech_Non_Standard_Order, updateTech_Non_Standard_Order } from "@/api/tech/nonStandard";
import { uploadFile, handleTrueDownload, checkExist } from '@/api/financial/excelImport';
export default {
  name: "Tech_Non_Standard_Order",
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
      isLoading: false,
      // Tech_Non_Standard_Order表格数据
      Tech_Non_Standard_OrderList: [],
      techList: [],
      // 弹出层标题
      title: "",
      dates: [],
      showDialog: false,
      selectedType: '',
      form3: { yearAndMonth: null },
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        tableId: null,
        orderId: null,
        carModel: null,
        carConfiguration: null,
        principal: null,
        participants: null,
        pickupDate: null,
        transferDate: null,
        totalDurationHours: null,
        totalDurationDays: null,
        overtimeRemark: null,
        comments: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        tableId: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        orderId: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        carModel: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        carConfiguration: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        principal: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        participants: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        pickupDate: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        transferDate: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        totalDurationHours: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        totalDurationDays: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        overtimeRemark: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
    },
    formatNumberOne(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 1 });
    },
    handleDownload() {
      const url = "/profile/excel_templates/非标订单统计样表.xlsx"
      handleTrueDownload(url);
    },
    /** 查询Tech_Non_Standard_Order列表 */
    getList() {
      this.loading = true;
      listTech_Non_Standard_Order(this.queryParams).then(response => {
        this.Tech_Non_Standard_OrderList = response.rows;
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
        tnId: null,
        yearAndMonth: null,
        tableId: null,
        orderId: null,
        carModel: null,
        carConfiguration: null,
        principal: null,
        participants: null,
        pickupDate: null,
        transferDate: null,
        totalDurationHours: null,
        totalDurationDays: null,
        overtimeRemark: null,
        comments: null,
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
      this.ids = selection.map(item => item.tnId)
      this.dates = selection.map(item => item.yearAndMonth)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新增非标订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tnId = row.tnId || this.ids
      getTech_Non_Standard_Order(tnId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改非标订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tnId != null) {
            updateTech_Non_Standard_Order(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTech_Non_Standard_Order(this.form).then(response => {
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
      const techIds = row.tnId || this.ids;
      const date = row.yearAndMonth || this.dates;

      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delTech_Non_Standard_Order(techIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tech/Tech_Non_Standard_Order/export', {
        ...this.queryParams
      }, `Tech_Non_Standard_Order_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮 */
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
    async fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      let yearAndMonth = this.form3.yearAndMonth;
      let startTime = {
        startTime: yearAndMonth
      };
      if (file === undefined || yearAndMonth == null) {
        if (file === undefined) {
          this.$message.error("请选择文件!");
          return;
        } else {
          this.$message.error("请选择日期!");
          return;
        }
      } else {
        let result = await checkExist(startTime);
        formData.append("yearAndMonth", yearAndMonth);
        formData.append("multipartFile", file);
        const aimUrl = `/tech/data/nonStandardOrder/read`
        if (result.data === true) {
          this.$modal.confirm('文件日期已存在，确定继续上传将覆盖数据').then(() => {
            return uploadFile(formData, aimUrl)
              .then(data => {
                // 处理上传成功的情况0.
                this.$message.success("上传成功");
                this.getList();
                this.showDialog = false;
                this.isLoading = false;
              })
              .catch(error => {
                // 处理上传失败的情况
                // this.$message.error("上传失败，请重试");
                this.isLoading = false;
              })
              ;
          })
        } else {
          this.isLoading = true;
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
              // this.$message.error("上传失败");
              this.isLoading = false;
            })
            ;
        }
      }
    },
  }
};
</script>

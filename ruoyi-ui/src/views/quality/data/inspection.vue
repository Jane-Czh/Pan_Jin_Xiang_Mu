<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM"
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
          v-hasPermi="['quality:inspection:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['quality:inspection:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['quality:inspection:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
          v-hasPermi="['quality:inspection:import']">导入Excel文件
        </el-button>

        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">

          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类：">
              <span style="color: rgb(68, 140, 39);">质检表</span>
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

    <el-table v-loading="loading" :data="inspectionList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="" align="center" prop="qiId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="100"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电车的问题数量" align="center" prop="electricCarProductionQuantity" width="150" />
      <el-table-column label="K2型号中小于5吨的问题数量" align="center" prop="k2lessthan5tonProductionQuantity" width="190" />
      <el-table-column label="K2型号中大吨位的问题数量" align="center" prop="k2largetonnageProductionQuantity" width="190" />
      <el-table-column label="电车的问题车数量" align="center" prop="electricCarProblemVehicles" width="180" />
      <el-table-column label="K2型号中小于5吨的问题车数量 " align="center" prop="k2lessthan5tonProblemVehicles" width="210" />
      <el-table-column label="K2型号中大吨位的问题车数量" align="center" prop="k2largetonnageProblemVehicles" width="200" />
      <!-- <el-table-column label="电车、大吨位一次交检合格率(%)" align="center" prop="singleInspectionPassRate" width="210" /> -->
      <el-table-column label="电车一次交检合格率(%)" align="center" prop="electricCarPassRate" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.electricCarPassRate || scope.row.electricCarPassRate === 0">{{
      scope.row.electricCarPassRate }}%</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="大吨位一次交检合格率(%)" align="center" prop="largeTonPassRate" width="200">
        <template slot-scope="scope">
          <span v-if="scope.row.largeTonPassRate || scope.row.largeTonPassRate === 0">{{ scope.row.largeTonPassRate
            }}%</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="电车、大吨位一次交检合格率(%)" align="center" prop="singleInspectionPassRate" width="210">
        <template slot-scope="scope">
          <span v-if="scope.row.singleInspectionPassRate || scope.row.singleInspectionPassRate === 0">{{
      scope.row.singleInspectionPassRate }}%</span>
          <span v-else>-</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:inspection:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['quality:inspection:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改质检部分字段对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="230px">
        <el-form-item fixed label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM" placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电车的问题数量" prop="electricCarProductionQuantity">
          <el-input v-model="form.electricCarProductionQuantity" placeholder="请输入电车的问题数量" />
        </el-form-item>
        <el-form-item label="K2型号中小于5吨的问题数量" prop="k2lessthan5tonProductionQuantity">
          <el-input v-model="form.k2lessthan5tonProductionQuantity" placeholder="请输入K2型号中小于5吨的问题数量" />
        </el-form-item>
        <el-form-item label="K2型号中大吨位的问题数量" prop="k2largetonnageProductionQuantity">
          <el-input v-model="form.k2largetonnageProductionQuantity" placeholder="请输入K2型号中大吨位的问题数量" />
        </el-form-item>
        <el-form-item label="电车的问题车数量" prop="electricCarProblemVehicles">
          <el-input v-model="form.electricCarProblemVehicles" placeholder="请输入电车的问题车数量" />
        </el-form-item>
        <el-form-item label="K2型号中小于5吨的问题车数量 " prop="k2lessthan5tonProblemVehicles">
          <el-input v-model="form.k2lessthan5tonProblemVehicles" placeholder="请输入K2型号中小于5吨的问题车数量 " />
        </el-form-item>
        <el-form-item label="K2型号中大吨位的问题车数量" prop="k2largetonnageProblemVehicles">
          <el-input v-model="form.k2largetonnageProblemVehicles" placeholder="请输入K2型号中大吨位的问题车数量" />
        </el-form-item>
        <!-- <el-form-item label="电车、大吨位一次交检合格率(%)" prop="singleInspectionPassRate">
          <el-input v-model="form.singleInspectionPassRate" placeholder="请输入电车、大吨位一次交检合格率(%)" />
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
import { listInspection, getInspection, delInspection, addInspection, updateInspection } from "@/api/quality/inspection";
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';
import { numValidatorPercentage, numValidatorNonZeroNature, numValidator } from '@/api/financial/numValidator.js';
export default {

  name: "Inspection",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,

      showDialog: false,
      progress: 0,
      selectedType: '',

      isLoading: false,

      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 质检部分字段表格数据
      inspectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        electricCarProductionQuantity: null,
        k2lessthan5tonProductionQuantity: null,
        k2largetonnageProductionQuantity: null,
        electricCarProblemVehicles: null,
        k2lessthan5tonProblemVehicles: null,
        k2largetonnageProblemVehicles: null,
        singleInspectionPassRate: null,
        electricCarPassRate: null,
        largeTonPassRate: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
        electricCarProductionQuantity: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        k2lessthan5tonProductionQuantity: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        k2largetonnageProductionQuantity: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        electricCarProblemVehicles: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        k2lessthan5tonProblemVehicles: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        k2largetonnageProblemVehicles: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        singleInspectionPassRate: [
          {
            required: true,
            validator: numValidator,
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
    handleDownload() {
      const url = "/profile/excel_templates/整机质检记录样表.xlsx";
      handleTrueDownload(url);
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
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },
    /** 查询质检部分字段列表 */
    getList() {
      this.loading = true;
      listInspection(this.queryParams).then(response => {
        this.inspectionList = response.rows;
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
        qiId: null,
        yearAndMonth: null,
        electricCarProductionQuantity: null,
        k2lessthan5tonProductionQuantity: null,
        k2largetonnageProductionQuantity: null,
        electricCarProblemVehicles: null,
        k2lessthan5tonProblemVehicles: null,
        k2largetonnageProblemVehicles: null,
        singleInspectionPassRate: null,
        electricCarPassRate: null,
        largeTonPassRate: null,
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
      this.ids = selection.map(item => item.qiId)
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
      const qiId = row.qiId || this.ids
      getInspection(qiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.qiId != null) {
            updateInspection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInspection(this.form).then(response => {
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
      const qiIds = row.qiId || this.ids;
      const date = row.yearAndMonth || this.dates;

      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delInspection(qiIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
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
        formData.append("excelFile", file);
        const aimUrl = `/quality/data/inspection/read`;
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
  }
};
</script>

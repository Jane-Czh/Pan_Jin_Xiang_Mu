<template>

  <div class="app-container">
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['supply:data:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['supply:data:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['supply:data:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain
          v-if="true">导入Excel文件
        </el-button>

        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">

          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类:">
              <span style="color: rgb(68, 140, 39);">供应订单表</span>
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

    <el-table v-loading="loading" :data="controlledAmountList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="scpId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="180"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当月采购总金额(万元)" align="center" prop="totalPurchaseAmount" />
      <el-table-column label="当月管控物资采购金额(万元)" align="center" prop="controlledMaterialPurchases" />
      <el-table-column label="比例(%)" align="center" prop="controlledPurchaseAmountRatio" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['supply:data:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['supply:data:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改供应-指标-集团管控物资占比对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="210px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="当月采购总金额(万元)" prop="totalPurchaseAmount">
          <el-input v-model="form.totalPurchaseAmount" placeholder="请输入当月采购总金额(万元)" />
        </el-form-item>
        <el-form-item label="当月管控物资采购金额(万元)" prop="controlledMaterialPurchases">
          <el-input v-model="form.controlledMaterialPurchases" placeholder="请输入当月管控物资采购金额(万元)" />
        </el-form-item>
        <el-form-item label="比例(%)" prop="controlledPurchaseAmountRatio">
          <el-input v-model="form.controlledPurchaseAmountRatio" placeholder="请输入比例(%)" />
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
import { listData, getData, delData, addData, updateData } from "@/api/supply/data";
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';
import { numValidator, numValidatorOnlyNature, numValidatorPercentage } from '@/api/financial/numValidator.js';

export default {

  name: "ControlledAmount",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dates: [],
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
      // 供应-指标-集团管控物资占比表格数据
      controlledAmountList: [],
      afterList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        totalPurchaseAmount: null,
        controlledMaterialPurchases: null,
        controlledPurchaseAmountRatio: null,
      },
      // 表单参数
      form: {},
      form3: { yearAndMonth: null },
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        totalPurchaseAmount: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        controlledMaterialPurchases: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        //比例
        controlledPurchaseAmountRatio: [
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
      const url = "/profile/excel_templates/采购订单汇总表样表.xlsx"
      handleTrueDownload(url);
    },
    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.controlledAmountList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.controlledAmountList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询供应-指标-集团管控物资占比列表 */
    getList() {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.controlledAmountList = response.rows;
        this.total = response.total;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'yearAndMonth',
          order: 'descending' // 或'descending'
        });
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
        scpId: null,
        yearAndMonth: null,
        totalPurchaseAmount: null,
        controlledMaterialPurchases: null,
        controlledPurchaseAmountRatio: null,
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
      this.ids = selection.map(item => item.scpId)
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
      const scpId = row.scpId || this.ids
      getData(scpId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scpId != null) {
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
      const scpIds = row.scpId || this.ids;
      const date = row.yearAndMonth || this.dates;
      this.$modal.confirm('是否删除日期为"' + date + '"的数据？').then(function () {
        return delData(scpIds);
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
        formData.append("multipartFile", file);
        const aimUrl = `/supply/data/readPurchaseOrderTable`
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
          })
          ;

        // .then(response => {
        //   // 处理请求成功的情况
        //   this.$message.success("上传成功");
        //   this.getList()
        // })
        //   .catch(error => {
        //     // 处理请求失败的情况
        //     console.error('上传失败：', error);
        //   });
        // setTimeout(() => {
        //   this.showDialog = false; // 关闭上传面板
        // }, 2000); // 2000毫秒后关闭
      }
    }
  },
};
</script>

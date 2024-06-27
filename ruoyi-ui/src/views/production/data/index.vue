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

    <div>
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
            v-hasPermi="['production:fill:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
            v-hasPermi="['production:fill:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
            v-hasPermi="['production:fill:remove']">删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <!--Excel 参数导入 -->
          <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
            v-hasPermi="['production:fill:import']">导入Excel文件
          </el-button>

          <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">
            <el-form :model="form" ref="form" label-width="90px">
              <el-form-item label="上传表类:">
                <span style="color: rgb(68, 140, 39);">商品车台账表</span>
                <br>
              </el-form-item>
            </el-form>
            <i class="el-icon-upload"></i>
            <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />
            <!-- 进度动画条 -->
            <div v-if="progress > 0">
              <el-progress :percentage="progress" color="rgb(19, 194, 194)"></el-progress>
            </div>

            <span slot="footer" class="dialog-footer">
              <el-button @click="showDialog = false">取 消</el-button>
              <el-button type="primary" @click="fileSend()">确 定</el-button>
            </span>
          </el-dialog>
        </el-col>

        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
    </div>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column sortable="custom" :sort-orders="['descending', 'ascending']" label="日期" align="center"
        prop="yearAndMonth" width="150">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="当月单台非BOM物料费用" align="center" prop="curNonBomMaterialCost" width="180" />
      <el-table-column label="当月单台低值易耗费用" align="center" prop="curLowValueConsumables" width="160" />
      <el-table-column label="在制物资年化周转天数" align="center" prop="inventoryTurnoverdays" width="160" />
      <el-table-column label="人均生产台数" align="center" prop="outputPercapitacounts" />
      <el-table-column label="人均产值" align="center" prop="outputPercapitavalue" />
      <el-table-column label="上线及时率" align="center" prop="onlineOntimerate" />
      <el-table-column label="一线当月加班时长" align="center" prop="overtimeFrontlinemonth" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['production:fill:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['production:fill:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[生产]手动填报指标对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="190px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="当月单台非BOM物料费用" prop="curNonBomMaterialCost">
          <el-input v-model="form.curNonBomMaterialCost" placeholder="请输入当月单台非BOM物料费用" />
        </el-form-item>
        <el-form-item label="当月单台低值易耗费用" prop="curLowValueConsumables">
          <el-input v-model="form.curLowValueConsumables" placeholder="请输入当月单台低值易耗费用" />
        </el-form-item>
        <el-form-item label="在制物资年化周转天数" prop="inventoryTurnoverdays">
          <el-input v-model="form.inventoryTurnoverdays" placeholder="请输入在制物资年化周转天数" />
        </el-form-item>
        <el-form-item label="人均生产台数" prop="outputPercapitacounts">
          <el-input v-model="form.outputPercapitacounts" placeholder="请输入人均生产台数" />
        </el-form-item>
        <el-form-item label="人均产值" prop="outputPercapitavalue">
          <el-input v-model="form.outputPercapitavalue" placeholder="请输入人均产值" />
        </el-form-item>
        <el-form-item label="上线及时率" prop="onlineOntimerate">
          <el-input v-model="form.onlineOntimerate" placeholder="请输入上线及时率" />
        </el-form-item>
        <el-form-item label="一线当月加班时长" prop="overtimeFrontlinemonth">
          <el-input v-model="form.overtimeFrontlinemonth" placeholder="请输入一线当月加班时长" />
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
import { listData, getData, delData, addData, updateData } from "@/api/production/data";
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
      // [生产]手动填报指标表格数据
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
        curNonBomMaterialCost: null,
        curLowValueConsumables: null,
        inventoryTurnoverdays: null,
        outputPercapitacounts: null,
        outputPercapitavalue: null,
        onlineOntimerate: null,
        overtimeFrontlinemonth: null,
        upload: null,

      },
      // 表单参数
      form: {},
      form3: { yearAndMonth: null },
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        curNonBomMaterialCost: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        curLowValueConsumables: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        inventoryTurnoverdays: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        outputPercapitacounts: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        outputPercapitavalue: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        onlineOntimerate: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        overtimeFrontlinemonth: [
          { required: true, message: "数据不能为空", trigger: "blur" }
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
    // handleSortChange(sort) {
    //   if (sort.column && sort.prop === 'yearAndMonth') {
    //     if (sort.order === 'ascending') {
    //       this.dataList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
    //     } else if (sort.order === 'descending') {
    //       this.dataList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
    //     }
    //   }
    // },

    /** 查询[生产]手动填报指标列表 */
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
        productionId: null,
        yearAndMonth: null,
        curNonBomMaterialCost: null,
        curLowValueConsumables: null,
        inventoryTurnoverdays: null,
        outputPercapitacounts: null,
        outputPercapitavalue: null,
        onlineOntimerate: null,
        overtimeFrontlinemonth: null,
        upload: null,
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
      this.ids = selection.map(item => item.productionId)
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
      const productionId = row.productionId || this.ids
      getData(productionId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.productionId != null) {
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
      const productionIds = row.productionId || this.ids;
      const date = row.yearAndMonth || this.dates;
      this.$modal.confirm('是否确认删除日期为"' + date + '"的数据？').then(function () {
        return delData(productionIds);
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
      if (file === undefined) {
        this.$message.error("请选择文件!");
        return;
      } else {
        // const yearAndMonth = this.form3.yearAndMonth;
        // formData.append("yearAndMonth", yearAndMonth);
        formData.append("multipartFile", file);
        axios({
          method: "post",
          url: "http://localhost:8080/production/table/simpleRead",
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
        this.$message.success("上传成功");
        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板
        }, 2000); // 2000毫秒后关闭
      }
    },
  }
};
</script>

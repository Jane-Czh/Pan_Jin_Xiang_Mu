<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="序号" prop="materialSerialNumber">
        <el-input v-model="queryParams.materialSerialNumber" placeholder="请输入序号" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['supply:dictionary:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['supply:dictionary:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['supply:dictionary:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
          v-hasPermi="['supply:dictionary:import']">导入Excel文件
        </el-button>

        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">

          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类:">
              <span style="color: rgb(68, 140, 39);">管控物资字典</span>
              <br>
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

    <el-table v-loading="loading" :data="DictionaryList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="SCM_ID" align="center" prop="scmId" /> -->
      <el-table-column label="序号" align="center" prop="materialSerialNumber" />
      <el-table-column label="物料号" align="center" prop="materialNumber" />
      <el-table-column label="物料名称" align="center" prop="materialName" />
      <el-table-column label="集采类别" align="center" prop="centralizedProcurementCategory" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['supply:dictionary:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['supply:dictionary:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改供应科-指标-集采物料字典对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="序号" prop="materialSerialNumber">
          <el-input v-model="form.materialSerialNumber" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="物料号" prop="materialNumber">
          <el-input v-model="form.materialNumber" placeholder="请输入物料号" />
        </el-form-item>
        <el-form-item label="物料名称" prop="materialName">
          <el-input v-model="form.materialName" placeholder="请输入物料名称" />
        </el-form-item>
        <el-form-item label="集采类别" prop="centralizedProcurementCategory">
          <el-input v-model="form.centralizedProcurementCategory" placeholder="请输入集采类别" />
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
import { listDictionary, getDictionary, delDictionary, addDictionary, updateDictionary } from "@/api/supply/dictionaryData";
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';
import { numValidatorOnlyNature, numValidator } from '@/api/financial/numValidator.js';
export default {
  name: "Index",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      names: [],
      // 非单个禁用
      fileName: '',
      showDialog: false,
      progress: 0,
      selectedType: '',
      selectedFile: null,

      isLoading: false,

      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 供应科-指标-集采物料字典表格数据
      DictionaryList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialSerialNumber: null,
        materialNumber: null,
        materialName: null,
        centralizedProcurementCategory: null,
      },
      // 表单参数
      form: {},

      // 表单校验
      rules: {
        materialSerialNumber: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur"
          }
        ],
        materialNumber: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        materialName: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
        centralizedProcurementCategory: [
          { required: true, message: "数据不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleDownload() {
      const url = "/digital_operations_management_system/file/采购订单汇总表样表.xlsx"
      handleTrueDownload(url);
    },
    /** 查询供应科-指标-集采物料字典列表 */
    getList() {
      this.loading = true;
      listDictionary(this.queryParams).then(response => {
        this.DictionaryList = response.rows;
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
        scmId: null,
        materialSerialNumber: null,
        materialNumber: null,
        materialName: null,
        centralizedProcurementCategory: null,
        createBy: null,
        createTime: null
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
      this.ids = selection.map(item => item.scmId)
      this.names = selection.map(item => item.materialNumber)
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
      const scmId = row.scmId || this.ids
      getDictionary(scmId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.scmId != null) {
            updateDictionary(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDictionary(this.form).then(response => {
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
      const scmIds = row.scmId || this.ids;
      const name = row.materialNumber || this.names;
      this.$modal.confirm('是否删除物料号为"' + name + '"的数据？').then(function () {
        return delDictionary(scmIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导入按钮 */
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      this.fileName = file.name;
      const fileExt = this.fileName.split(".").pop(); // 获取文件的扩展名

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
        this.isLoading = true;
        const aimUrl = `/supply/data/readCollectibleMaterialsTable`;
        formData.append("multipartFile", file);
        const purchaseOrder = /采购/.test(this.fileName);
        const materialDictionary = /字典/.test(this.fileName);
        if (purchaseOrder || materialDictionary) {
          uploadFile(formData, aimUrl)
            .then(data => {
              // 处理上传成功的情况
              this.$message.success("上传成功");
              this.getList();
            })
            .catch(error => {
              // 处理上传失败的情况
              this.$message.error("上传失败，请重试");
            })
            .finally(() => {
              // 无论成功或失败，都关闭上传面板
              this.showDialog = false;
              this.isLoading = false;
            });
        } else {
          this.$modal.confirm('确认上传该表吗').then(() => {
            return uploadFile(formData, aimUrl)
              .then(data => {
                // 处理上传成功的情况
                this.$message.success("上传成功");
                this.getList();
              })
              .catch(error => {
                // 处理上传失败的情况
                this.$message.error("上传失败，请重试");
              })
              .finally(() => {
                // 无论成功或失败，都关闭上传面板
                this.showDialog = false;
                this.isLoading = false;
              });
          });
        }
      }
    },
  }
};
</script>

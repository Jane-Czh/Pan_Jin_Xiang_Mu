<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="导入人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入导入人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="导入时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择导入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="凭证日期" prop="documentDate">
        <el-date-picker clearable
          v-model="queryParams.documentDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择凭证日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="采购凭证" prop="purchasingDocuments">
        <el-input
          v-model="queryParams.purchasingDocuments"
          placeholder="请输入采购凭证"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料号" prop="materialNumber">
        <el-input
          v-model="queryParams.materialNumber"
          placeholder="请输入物料号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否为集采(0/1)" prop="collectiveProcurement">
        <el-input
          v-model="queryParams.collectiveProcurement"
          placeholder="请输入是否为集采(0/1)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="短文本" prop="ShortText">
        <el-input
          v-model="queryParams.ShortText"
          placeholder="请输入短文本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="单位" prop="Unit">
        <el-input
          v-model="queryParams.Unit"
          placeholder="请输入单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="Quantity">
        <el-input
          v-model="queryParams.Quantity"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单净值" prop="orderNetvalue">
        <el-input
          v-model="queryParams.orderNetvalue"
          placeholder="请输入订单净值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商" prop="Supplier">
        <el-input
          v-model="queryParams.Supplier"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['supply:purchase:add']"
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
          v-hasPermi="['supply:purchase:edit']"
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
          v-hasPermi="['supply:purchase:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['supply:purchase:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['market:import:export']"
        >导入Excel文件</el-button>

        <el-dialog
          title="导入Excel文件"
          :visible.sync="showDialog"
          width="30%"
          :before-close="handleClose"
          @close="resetFileInput"
        >
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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="purchaseList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="编号" align="center" prop="spId" />
      <el-table-column label="导入人" align="center" prop="createdBy" />
      <el-table-column label="导入时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="凭证日期" align="center" prop="documentDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.documentDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="采购凭证" align="center" prop="purchasingDocuments" />
      <el-table-column label="物料号" align="center" prop="materialNumber" />
      <el-table-column label="是否为集采(0/1)" align="center" prop="collectiveProcurement" />
      <el-table-column label="短文本" align="center" prop="shortText" />
      <el-table-column label="单位" align="center" prop="unit" />
      <el-table-column label="数量" align="center" prop="quantity" />
      <el-table-column label="订单净值" align="center" prop="orderNetvalue" />
      <el-table-column label="供应商" align="center" prop="supplier" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['supply:purchase:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['supply:purchase:remove']"
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

    <!-- 添加或修改采购订单导入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="导入人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入导入人" />
        </el-form-item>
        <el-form-item label="导入时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择导入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="凭证日期" prop="documentDate">
          <el-date-picker clearable
            v-model="form.documentDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择凭证日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="采购凭证" prop="purchasingDocuments">
          <el-input v-model="form.purchasingDocuments" placeholder="请输入采购凭证" />
        </el-form-item>
        <el-form-item label="物料号" prop="materialNumber">
          <el-input v-model="form.materialNumber" placeholder="请输入物料号" />
        </el-form-item>
        <el-form-item label="是否为集采(0/1)" prop="collectiveProcurement">
          <el-input v-model="form.collectiveProcurement" placeholder="请输入是否为集采(0/1)" />
        </el-form-item>
        <el-form-item label="短文本" prop="ShortText">
          <el-input v-model="form.ShortText" placeholder="请输入短文本" />
        </el-form-item>
        <el-form-item label="单位" prop="Unit">
          <el-input v-model="form.Unit" placeholder="请输入单位" />
        </el-form-item>
        <el-form-item label="数量" prop="Quantity">
          <el-input v-model="form.Quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="订单净值" prop="orderNetvalue">
          <el-input v-model="form.orderNetvalue" placeholder="请输入订单净值" />
        </el-form-item>
        <el-form-item label="供应商" prop="Supplier">
          <el-input v-model="form.Supplier" placeholder="请输入供应商" />
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
import { listPurchase, getPurchase, delPurchase, addPurchase, updatePurchase } from "@/api/supply/purchase";
import axios from 'axios'

export default {
  name: "Purchase",
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
      // 采购订单导入表格数据
      purchaseList: [],
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
        documentDate: null,
        purchasingDocuments: null,
        materialNumber: null,
        collectiveProcurement: null,
        ShortText: null,
        Unit: null,
        Quantity: null,
        orderNetvalue: null,
        Supplier: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        materialNumber: [
          { required: true, message: "物料号不能为空", trigger: "blur" }
        ],
        Quantity: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        Supplier: [
          { required: true, message: "供应商不能为空", trigger: "blur" }
        ]
      },

      //新增参数
      showDialog: false,
      progress: 0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询采购订单导入列表 */
    getList() {
      this.loading = true;
      listPurchase(this.queryParams).then(response => {
        this.purchaseList = response.rows;
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
        spId: null,
        createdBy: null,
        createdTime: null,
        documentDate: null,
        purchasingDocuments: null,
        materialNumber: null,
        collectiveProcurement: null,
        ShortText: null,
        Unit: null,
        Quantity: null,
        orderNetvalue: null,
        Supplier: null
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
      this.ids = selection.map(item => item.spId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加采购订单导入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const spId = row.spId || this.ids
      getPurchase(spId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改采购订单导入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.spId != null) {
            updatePurchase(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addPurchase(this.form).then(response => {
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
      const spIds = row.spId || this.ids;
      this.$modal.confirm('是否确认删除采购订单导入编号为"' + spIds + '"的数据项？').then(function() {
        return delPurchase(spIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('supply/purchase/export', {
        ...this.queryParams
      }, `purchase_${new Date().getTime()}.xlsx`)
    },

    /*  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      console.log(file);
      formData.append("file", file);
      console.log("file====>",formData)
      axios({
        method: "post",
        // this $axios.post,
        url: "http://localhost:8080/supply/purchase/import",
        // params:{
        //   userName: this.$store.state.user.name,
        // },
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
      // this.$message.success("上传成功");


      setTimeout(() => {
        this.showDialog = false; // 关闭上传面板

        location.reload(); // 调用此方法刷新页面数据
      }, 2000); // 2000毫秒后关闭
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    //检查文件是否为excel
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    }

  }
};
</script>

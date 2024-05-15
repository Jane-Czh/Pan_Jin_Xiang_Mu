<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="表单名称" prop="formTitle">
        <el-input
          v-model="queryParams.formTitle"
          placeholder="请输入表单名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="存储表单内容" prop="scope">
        <el-input
          v-model="queryParams.scope"
          placeholder="请输入存储表单内容"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表单上传时间" prop="effectiveDate">
        <el-date-picker clearable
          v-model="queryParams.effectiveDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择表单上传时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="表单大小" prop="formSize">
        <el-input
          v-model="queryParams.formSize"
          placeholder="请输入表单大小"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传人" prop="createUsername">
        <el-input
          v-model="queryParams.createUsername"
          placeholder="请输入上传人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="表单所属科室" prop="departmentCategory">
        <el-input
          v-model="queryParams.departmentCategory"
          placeholder="请输入表单所属科室"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="历史表单" prop="oldFormId">
        <el-input
          v-model="queryParams.oldFormId"
          placeholder="请输入历史表单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="修订时间" prop="revisionTime">
        <el-date-picker clearable
          v-model="queryParams.revisionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择修订时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="修订人" prop="reviser">
        <el-input
          v-model="queryParams.reviser"
          placeholder="请输入修订人"
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
          v-hasPermi="['file:formfilemanagement:add']"
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
          v-hasPermi="['file:formfilemanagement:edit']"
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
          v-hasPermi="['file:formfilemanagement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['file:formfilemanagement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="formfilemanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="formId" />
      <el-table-column label="表单名称" align="center" prop="formTitle" />
      <el-table-column label="存储表单内容" align="center" prop="scope" />
      <el-table-column label="表单上传时间" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表单类型word/pdf" align="center" prop="formType" />
      <el-table-column label="表单路径" align="center" prop="formPath">
        <template slot-scope="scope">
          <a :href="baseUrl + scope.row.formPath" download>点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="表单大小" align="center" prop="formSize" />
      <el-table-column label="上传人" align="center" prop="createUsername" />
      <el-table-column label="表单所属科室" align="center" prop="departmentCategory" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="历史表单" align="center" prop="oldFormId" />
      <el-table-column label="修订时间" align="center" prop="revisionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.revisionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修订内容" align="center" prop="revisionContent" />
      <el-table-column label="修订人" align="center" prop="reviser" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['file:formfilemanagement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:formfilemanagement:remove']"
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

    <!-- 添加或修改表单文件管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="表单名称" prop="formTitle">
          <el-input v-model="form.formTitle" placeholder="请输入表单名称" />
        </el-form-item>
        <el-form-item label="存储表单内容" prop="scope">
          <el-input v-model="form.scope" placeholder="请输入存储表单内容" />
        </el-form-item>
        <el-form-item label="表单上传时间" prop="effectiveDate">
          <el-date-picker clearable
            v-model="form.effectiveDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择表单上传时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="表单类型" prop="formType">
          <el-input v-model="form.formType" placeholder="请输入表单类型" />
        </el-form-item>
        <el-form-item label="表单路径" prop="formPath">
          <file-upload v-model="form.formPath"/>
        </el-form-item>
        <el-form-item label="表单大小" prop="formSize">
          <el-input v-model="form.formSize" placeholder="请输入表单大小" />
        </el-form-item>
        <el-form-item label="上传人" prop="createUsername">
          <el-input v-model="form.createUsername" placeholder="请输入上传人" />
        </el-form-item>
        <el-form-item label="表单所属科室" prop="departmentCategory">
          <el-input v-model="form.departmentCategory" placeholder="请输入表单所属科室" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="历史表单" prop="oldFormId">
          <el-input v-model="form.oldFormId" placeholder="请输入历史表单" />
        </el-form-item>
        <el-form-item label="修订时间" prop="revisionTime">
          <el-date-picker clearable
            v-model="form.revisionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择修订时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="修订内容">
          <editor v-model="form.revisionContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="修订人" prop="reviser">
          <el-input v-model="form.reviser" placeholder="请输入修订人" />
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
import { listFormfilemanagement, getFormfilemanagement, delFormfilemanagement, addFormfilemanagement, updateFormfilemanagement } from "@/api/file/formfilemanagement";

export default {
  name: "Formfilemanagement",
  data() {
    return {
      baseUrl: process.env.VUE_APP_BASE_API,
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload", // 上传文件服务器地址
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
      // 表单文件管理表格数据
      formfilemanagementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        formTitle: null,
        scope: null,
        effectiveDate: null,
        formType: null,
        formPath: null,
        formSize: null,
        createUsername: null,
        departmentCategory: null,
        oldFormId: null,
        revisionTime: null,
        revisionContent: null,
        reviser: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        formTitle: [
          { required: true, message: "表单名称不能为空", trigger: "blur" }
        ],
        scope: [
          { required: true, message: "存储表单内容不能为空", trigger: "blur" }
        ],
        effectiveDate: [
          { required: true, message: "表单上传时间不能为空", trigger: "blur" }
        ],
        formType: [
          { required: true, message: "表单类型word/pdf不能为空", trigger: "change" }
        ],
        formSize: [
          { required: true, message: "表单大小不能为空", trigger: "blur" }
        ],
        createUsername: [
          { required: true, message: "上传人不能为空", trigger: "blur" }
        ],
        departmentCategory: [
          { required: true, message: "表单所属科室不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询表单文件管理列表 */
    getList() {
      this.loading = true;
      listFormfilemanagement(this.queryParams).then(response => {
        this.formfilemanagementList = response.rows;
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
        formId: null,
        formTitle: null,
        scope: null,
        effectiveDate: null,
        formType: null,
        formPath: null,
        formSize: null,
        createUsername: null,
        departmentCategory: null,
        remark: null,
        oldFormId: null,
        revisionTime: null,
        revisionContent: null,
        reviser: null
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
      this.ids = selection.map(item => item.formId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加表单文件管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const formId = row.formId || this.ids
      getFormfilemanagement(formId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改表单文件管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.formId != null) {
            updateFormfilemanagement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFormfilemanagement(this.form).then(response => {
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
      const formIds = row.formId || this.ids;
      this.$modal.confirm('是否确认删除表单文件管理编号为"' + formIds + '"的数据项？').then(function() {
        return delFormfilemanagement(formIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('file/formfilemanagement/export', {
        ...this.queryParams
      }, `formfilemanagement_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目名称" prop="projectName">
        <el-input
          v-model="queryParams.projectName"
          placeholder="请输入项目名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主责部门" prop="department">
        <el-input
          v-model="queryParams.department"
          placeholder="请输入主责部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="importDate">
        <el-date-picker clearable
          v-model="queryParams.importDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="associationDate">
        <el-date-picker clearable
          v-model="queryParams.associationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目总进度" prop="progressAlloverProgress">
        <el-input
          v-model="queryParams.progressAlloverProgress"
          placeholder="请输入项目总进度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否有相关方案或计划" prop="remake">
        <el-input
          v-model="queryParams.remake"
          placeholder="请输入是否有相关方案或计划"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交付物" prop="progress">
        <el-input
          v-model="queryParams.progress"
          placeholder="请输入交付物"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键事项说明" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入关键事项说明"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="待定1" prop="determined1">
        <el-input
          v-model="queryParams.determined1"
          placeholder="请输入待定1"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="待定2" prop="determined2">
        <el-input
          v-model="queryParams.determined2"
          placeholder="请输入待定2"
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
          v-hasPermi="['project:recode:add']"
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
          v-hasPermi="['project:recode:edit']"
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
          v-hasPermi="['project:recode:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:recode:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recodeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="recodeId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="主责部门" align="center" prop="department" />
      <el-table-column label="开始时间" align="center" prop="importDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="associationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.associationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目总进度" align="center" prop="progressAlloverProgress" />
      <el-table-column label="项目状态" align="center" prop="status" />
      <el-table-column label="是否有相关方案或计划" align="center" prop="remake" />
      <el-table-column label="完成工作事项" align="center" prop="completionSummary" />
      <el-table-column label="交付物" align="center" prop="progress" />
      <el-table-column label="关键事项说明" align="center" prop="description" />
      <el-table-column label="待定1" align="center" prop="determined1" />
      <el-table-column label="待定2" align="center" prop="determined2" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:recode:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:recode:remove']"
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

    <!-- 添加或修改项目修改记录对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="主责部门" prop="department">
          <el-input v-model="form.department" placeholder="请输入主责部门" />
        </el-form-item>
        <el-form-item label="开始时间" prop="importDate">
          <el-date-picker clearable
            v-model="form.importDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="associationDate">
          <el-date-picker clearable
            v-model="form.associationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目总进度" prop="progressAlloverProgress">
          <el-input v-model="form.progressAlloverProgress" placeholder="请输入项目总进度" />
        </el-form-item>
        <el-form-item label="是否有相关方案或计划" prop="remake">
          <el-input v-model="form.remake" placeholder="请输入是否有相关方案或计划" />
        </el-form-item>
        <el-form-item label="完成工作事项" prop="completionSummary">
          <el-input v-model="form.completionSummary" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="交付物" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入交付物" />
        </el-form-item>
        <el-form-item label="关键事项说明" prop="description">
          <el-input v-model="form.description" placeholder="请输入关键事项说明" />
        </el-form-item>
        <el-form-item label="待定1" prop="determined1">
          <el-input v-model="form.determined1" placeholder="请输入待定1" />
        </el-form-item>
        <el-form-item label="待定2" prop="determined2">
          <el-input v-model="form.determined2" placeholder="请输入待定2" />
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
import { listRecode, getRecode, delRecode, addRecode, updateRecode } from "@/api/project/recode";

export default {
  name: "Recode",
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
      // 项目修改记录表格数据
      recodeList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        department: null,
        importDate: null,
        associationDate: null,
        progressAlloverProgress: null,
        status: null,
        remake: null,
        completionSummary: null,
        progress: null,
        description: null,
        determined1: null,
        determined2: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        department: [
          { required: true, message: "主责部门不能为空", trigger: "blur" }
        ],
        progressAlloverProgress: [
          { required: true, message: "项目总进度不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "项目状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目修改记录列表 */
    getList() {
      this.loading = true;
      listRecode(this.queryParams).then(response => {
        this.recodeList = response.rows;
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
        recodeId: null,
        projectName: null,
        department: null,
        importDate: null,
        associationDate: null,
        progressAlloverProgress: null,
        status: null,
        remake: null,
        completionSummary: null,
        progress: null,
        description: null,
        determined1: null,
        updateTime: null,
        determined2: null
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
      this.ids = selection.map(item => item.recodeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目修改记录";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const recodeId = row.recodeId || this.ids
      getRecode(recodeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目修改记录";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.recodeId != null) {
            updateRecode(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecode(this.form).then(response => {
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
      const recodeIds = row.recodeId || this.ids;
      this.$modal.confirm('是否确认删除项目修改记录编号为"' + recodeIds + '"的数据项？').then(function() {
        return delRecode(recodeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/recode/export', {
        ...this.queryParams
      }, `recode_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

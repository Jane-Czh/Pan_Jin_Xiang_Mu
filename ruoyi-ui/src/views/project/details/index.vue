<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="项目(外键)" prop="projectId">
        <el-input
          v-model="queryParams.projectId"
          placeholder="请输入项目(外键)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="负责人" prop="manager">
        <el-input
          v-model="queryParams.manager"
          placeholder="请输入负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="立项时间" prop="startDate">
        <el-date-picker clearable
          v-model="queryParams.startDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择立项时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="组成员" prop="teamMembers">
        <el-input
          v-model="queryParams.teamMembers"
          placeholder="请输入组成员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目进度" prop="progress">
        <el-input
          v-model="queryParams.progress"
          placeholder="请输入项目进度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="目标" prop="goal">
        <el-input
          v-model="queryParams.goal"
          placeholder="请输入目标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="范围" prop="scope">
        <el-input
          v-model="queryParams.scope"
          placeholder="请输入范围"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划结项时间" prop="plannedCompletionTime">
        <el-date-picker clearable
          v-model="queryParams.plannedCompletionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择计划结项时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="已过天数(自动计算)" prop="daysPassed">
        <el-input
          v-model="queryParams.daysPassed"
          placeholder="请输入已过天数(自动计算)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="剩余天数(自动计算)" prop="daysRemaining">
        <el-input
          v-model="queryParams.daysRemaining"
          placeholder="请输入剩余天数(自动计算)"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="完成内容概述" prop="completionSummary">
        <el-input
          v-model="queryParams.completionSummary"
          placeholder="请输入完成内容概述"
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
          v-hasPermi="['project:details:add']"
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
          v-hasPermi="['project:details:edit']"
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
          v-hasPermi="['project:details:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:details:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="detailsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="detailsId" />
      <el-table-column label="项目(外键)" align="center" prop="projectId" />
      <el-table-column label="负责人" align="center" prop="manager" />
      <el-table-column label="立项时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="组成员" align="center" prop="teamMembers" />
      <el-table-column label="项目状态" align="center" prop="status" />
      <el-table-column label="项目进度" align="center" prop="progress" />
      <el-table-column label="项目现状" align="center" prop="currentStatus" />
      <el-table-column label="目标" align="center" prop="goal" />
      <el-table-column label="范围" align="center" prop="scope" />
      <el-table-column label="计划结项时间" align="center" prop="plannedCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="已过天数(自动计算)" align="center" prop="daysPassed" />
      <el-table-column label="剩余天数(自动计算)" align="center" prop="daysRemaining" />
      <el-table-column label="完成内容概述" align="center" prop="completionSummary" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:details:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:details:remove']"
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

    <!-- 添加或修改项目详情对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目(外键)" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目(外键)" />
        </el-form-item>
        <el-form-item label="负责人" prop="manager">
          <el-input v-model="form.manager" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="立项时间" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择立项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="组成员" prop="teamMembers">
          <el-input v-model="form.teamMembers" placeholder="请输入组成员" />
        </el-form-item>
        <el-form-item label="项目进度" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入项目进度" />
        </el-form-item>
        <el-form-item label="目标" prop="goal">
          <el-input v-model="form.goal" placeholder="请输入目标" />
        </el-form-item>
        <el-form-item label="范围" prop="scope">
          <el-input v-model="form.scope" placeholder="请输入范围" />
        </el-form-item>
        <el-form-item label="计划结项时间" prop="plannedCompletionTime">
          <el-date-picker clearable
            v-model="form.plannedCompletionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划结项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="已过天数(自动计算)" prop="daysPassed">
          <el-input v-model="form.daysPassed" placeholder="请输入已过天数(自动计算)" />
        </el-form-item>
        <el-form-item label="剩余天数(自动计算)" prop="daysRemaining">
          <el-input v-model="form.daysRemaining" placeholder="请输入剩余天数(自动计算)" />
        </el-form-item>
        <el-form-item label="完成内容概述" prop="completionSummary">
          <el-input v-model="form.completionSummary" placeholder="请输入完成内容概述" />
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
import { listDetails, getDetails, delDetails, addDetails, updateDetails } from "@/api/project/details";

export default {
  name: "Details",
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
      // 项目详情表格数据
      detailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectId: null,
        manager: null,
        startDate: null,
        teamMembers: null,
        status: null,
        progress: null,
        currentStatus: null,
        goal: null,
        scope: null,
        plannedCompletionTime: null,
        daysPassed: null,
        daysRemaining: null,
        completionSummary: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectId: [
          { required: true, message: "项目(外键)不能为空", trigger: "blur" }
        ],
        manager: [
          { required: true, message: "负责人不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "立项时间不能为空", trigger: "blur" }
        ],
        teamMembers: [
          { required: true, message: "组成员不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "项目状态不能为空", trigger: "change" }
        ],
        progress: [
          { required: true, message: "项目进度不能为空", trigger: "blur" }
        ],
        currentStatus: [
          { required: true, message: "项目现状不能为空", trigger: "change" }
        ],
        goal: [
          { required: true, message: "目标不能为空", trigger: "blur" }
        ],
        scope: [
          { required: true, message: "范围不能为空", trigger: "blur" }
        ],
        plannedCompletionTime: [
          { required: true, message: "计划结项时间不能为空", trigger: "blur" }
        ],
        daysPassed: [
          { required: true, message: "已过天数(自动计算)不能为空", trigger: "blur" }
        ],
        daysRemaining: [
          { required: true, message: "剩余天数(自动计算)不能为空", trigger: "blur" }
        ],
        completionSummary: [
          { required: true, message: "完成内容概述不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目详情列表 */
    getList() {
      this.loading = true;
      listDetails(this.queryParams).then(response => {
        this.detailsList = response.rows;
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
        detailsId: null,
        projectId: null,
        manager: null,
        startDate: null,
        teamMembers: null,
        status: null,
        progress: null,
        currentStatus: null,
        goal: null,
        scope: null,
        plannedCompletionTime: null,
        daysPassed: null,
        daysRemaining: null,
        completionSummary: null
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
      this.ids = selection.map(item => item.detailsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目详情";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const detailsId = row.detailsId || this.ids
      getDetails(detailsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目详情";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.detailsId != null) {
            updateDetails(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDetails(this.form).then(response => {
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
      const detailsIds = row.detailsId || this.ids;
      this.$modal.confirm('是否确认删除项目详情编号为"' + detailsIds + '"的数据项？').then(function() {
        return delDetails(detailsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/details/export', {
        ...this.queryParams
      }, `details_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

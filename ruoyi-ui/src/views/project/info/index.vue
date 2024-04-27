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
      <el-form-item label="项目类别" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入项目类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目等级" prop="level">
        <el-input
          v-model="queryParams.level"
          placeholder="请输入项目等级"
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
      <el-form-item label="承接属性" prop="attribute">
        <el-input
          v-model="queryParams.attribute"
          placeholder="请输入承接属性"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入项目描述"
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
      <el-form-item label="项目总进度" prop="progressAlloverProgress">
        <el-input
          v-model="queryParams.progressAlloverProgress"
          placeholder="请输入项目总进度"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="导入时间" prop="importDate">
        <el-date-picker clearable
          v-model="queryParams.importDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择导入时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="描述" prop="remake">
        <el-input
          v-model="queryParams.remake"
          placeholder="请输入描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="历史项目" prop="oldProjectId">
        <el-input
          v-model="queryParams.oldProjectId"
          placeholder="请输入历史项目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关联时间" prop="associationDate">
        <el-date-picker clearable
          v-model="queryParams.associationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择关联时间">
        </el-date-picker>
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
          v-hasPermi="['project:info:add']"
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
          v-hasPermi="['project:info:edit']"
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
          v-hasPermi="['project:info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:info:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="infoList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目类别" align="center" prop="category" />
      <el-table-column label="项目等级" align="center" prop="level" />
      <el-table-column label="主责部门" align="center" prop="department" />
      <el-table-column label="承接属性" align="center" prop="attribute" />
      <el-table-column label="项目描述" align="center" prop="description" />
      <el-table-column label="立项时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目总进度" align="center" prop="progressAlloverProgress" />
      <el-table-column label="导入时间" align="center" prop="importDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="remake" />
      <el-table-column label="历史项目" align="center" prop="oldProjectId" />
      <el-table-column label="关联时间" align="center" prop="associationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.associationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:info:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:info:remove']"
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




    <!-- 添加或修改项目基本信息表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目类别" prop="category">
          <el-input v-model="form.category" placeholder="请输入项目类别" />
        </el-form-item>
        <el-form-item label="项目等级" prop="level">
          <el-input v-model="form.level" placeholder="请输入项目等级" />
        </el-form-item>
        <el-form-item label="主责部门" prop="department">
          <el-input v-model="form.department" placeholder="请输入主责部门" />
        </el-form-item>
        <el-form-item label="承接属性" prop="attribute">
          <el-input v-model="form.attribute" placeholder="请输入承接属性" />
        </el-form-item>
        <el-form-item label="项目描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入项目描述" />
        </el-form-item>
        <el-form-item label="立项时间" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择立项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目总进度" prop="progressAlloverProgress">
          <el-input v-model="form.progressAlloverProgress" placeholder="请输入项目总进度" />
        </el-form-item>
        <el-form-item label="导入时间" prop="importDate">
          <el-date-picker clearable
            v-model="form.importDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择导入时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="描述" prop="remake">
          <el-input v-model="form.remake" placeholder="请输入描述" />
        </el-form-item>
        <el-form-item label="历史项目" prop="oldProjectId">
          <el-input v-model="form.oldProjectId" placeholder="请输入历史项目" />
        </el-form-item>
        <el-form-item label="关联时间" prop="associationDate">
          <el-date-picker clearable
            v-model="form.associationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择关联时间">
          </el-date-picker>
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
import { listInfo, getInfo, delInfo, addInfo, updateInfo } from "@/api/project/info";

export default {
  name: "Info",
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
      // 项目基本信息表表格数据
      infoList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        projectName: null,
        category: null,
        level: null,
        department: null,
        attribute: null,
        description: null,
        startDate: null,
        progressAlloverProgress: null,
        importDate: null,
        remake: null,
        oldProjectId: null,
        associationDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        projectName: [
          { required: true, message: "项目名称不能为空", trigger: "blur" }
        ],
        category: [
          { required: true, message: "项目类别不能为空", trigger: "blur" }
        ],
        level: [
          { required: true, message: "项目等级不能为空", trigger: "blur" }
        ],
        department: [
          { required: true, message: "主责部门不能为空", trigger: "blur" }
        ],
        attribute: [
          { required: true, message: "承接属性不能为空", trigger: "blur" }
        ],
        description: [
          { required: true, message: "项目描述不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "立项时间不能为空", trigger: "blur" }
        ],
        progressAlloverProgress: [
          { required: true, message: "项目总进度不能为空", trigger: "blur" }
        ],
        importDate: [
          { required: true, message: "导入时间不能为空", trigger: "blur" }
        ],
        associationDate: [
          { required: true, message: "关联时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目基本信息表列表 */
    getList() {
      this.loading = true;
      listInfo(this.queryParams).then(response => {
        this.infoList = response.rows;
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
        projectId: null,
        projectName: null,
        category: null,
        level: null,
        department: null,
        attribute: null,
        description: null,
        startDate: null,
        progressAlloverProgress: null,
        importDate: null,
        remake: null,
        oldProjectId: null,
        associationDate: null
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目基本信息表";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getInfo(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目基本信息表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInfo(this.form).then(response => {
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
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目基本信息表编号为"' + projectIds + '"的数据项？').then(function() {
        return delInfo(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/info/export', {
        ...this.queryParams
      }, `info_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

.el-row {
  display:flex;
  flex-wrap: wrap;
  align-items: center;
}
.el-row  .el-card {
  min-width: 100%;
  height: 100%;
  margin-right: 20px;
  transition: all .5s;
}
.el-form-item {
  margin-bottom: 0 !important;
}
.pagination-container {
  margin-top: -3px;
  margin-bottom: 30px;
}
.box-card {
  .header {
    position: relative;
    .label{
      padding: 0 3px;
      background-color: #fdf0da;
      color: #f19901;
    }
    .header-label {
      padding-left: 10px;
    }
    .card-header-tag-blue {
      position: absolute;
      width: 68px;
      height: 30px;
      top: -14px;
      right: -15px;
      background-image: url("~@/assets/images/img_dpj_t.png");
      display: inline-block;
    }
    .card-header-tag-green {
      position: absolute;
      width: 68px;
      height: 30px;
      top: -14px;
      right: -15px;
      background-image: url("~@/assets/images/img_ypj_t.png");
      display: inline-block;
    }
  }
  .footer {
    font-size: 18px !important;
    background-color: rgb(245, 247, 251);
    display: flex;
    height: 50px;
    justify-content: space-evenly;
  }
  .card-label {
    color: rgb(197, 197, 197);
    margin-right: 8px;
    width: 70px;
    display: inline-block;
    margin-bottom: 5px;
  }
}

</style>


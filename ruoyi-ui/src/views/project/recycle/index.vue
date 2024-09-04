<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
      <el-form-item label="负责人" prop="manager">
        <el-input
          v-model="queryParams.manager"
          placeholder="请输入负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目组成员" prop="teamMembers">
        <el-input
          v-model="queryParams.teamMembers"
          placeholder="请输入项目组成员"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目目标" prop="goal">
        <el-input
          v-model="queryParams.goal"
          placeholder="请输入项目目标"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="项目范围" prop="scope">
        <el-input
          v-model="queryParams.scope"
          placeholder="请输入项目范围"
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
      <el-form-item label="计划结项时间" prop="plannedCompletionTime">
        <el-date-picker clearable
          v-model="queryParams.plannedCompletionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择计划结项时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目归属" prop="attribute">
        <el-input
          v-model="queryParams.attribute"
          placeholder="请输入项目归属"
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
      <el-form-item label="项目总进度" prop="progressAlloverProgress">
        <el-input
          v-model="queryParams.progressAlloverProgress"
          placeholder="请输入项目总进度"
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
      <el-form-item label="关联时间" prop="associationDate">
        <el-date-picker clearable
          v-model="queryParams.associationDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择关联时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="项目进度" prop="progress">
        <el-input
          v-model="queryParams.progress"
          placeholder="请输入项目进度"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
    </el-form> -->

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主责部门" prop="department">
        <el-select
          v-model="queryParams.department"
          placeholder="请选择主责部门"
          clearable
          filterable
          @change="handleQuery"
        >
          <el-option
            v-for="item in departmentOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="项目等级" prop="level">
        <el-select
          v-model="queryParams.level"
          placeholder="请输入项目等级"
          clearable
          filterable
          @change="handleQuery"
        >
          <el-option
            v-for="item in levelOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="项目类别" prop="category">
        <el-select
          v-model="queryParams.category"
          placeholder="请输入项目类别"
          clearable
          filterable
          @change="handleQuery"
        >
          <el-option
            v-for="item in categoryOptions"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      
      <el-form-item label="负责人" prop="manager">
        <el-input
          v-model="queryParams.manager"
          placeholder="请输入负责人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd" v-hasPermi="['project:Info:add']">新增</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <!-- <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:recycle:add']"
        >新增</el-button>
      </el-col> -->
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:recycle:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['project:recycle:remove']"
        >彻底删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:recycle:export']"
        >导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recycleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目类别" align="center" prop="category" />
      <el-table-column label="项目等级" align="center" prop="level" />
      <el-table-column label="主责部门" align="center" prop="department" />
      <el-table-column label="负责人" align="center" prop="manager" />
      <el-table-column label="项目组成员" align="center" prop="teamMembers" />
      <el-table-column label="项目现状" align="center" prop="currentStatus" />
      <el-table-column label="项目目标" align="center" prop="goal" />
      <el-table-column label="项目范围" align="center" prop="scope" />
      <el-table-column label="立项时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划结项时间" align="center" prop="plannedCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目归属" align="center" prop="attribute" />
      <el-table-column label="历史项目" align="center" prop="oldProjectId" />
      <el-table-column label="项目状态" align="center" prop="status" />
      <el-table-column label="项目总进度" align="center" prop="progressAlloverProgress" />
      <el-table-column label="项目描述" align="center" prop="description" />
      <el-table-column label="导入时间" align="center" prop="importDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="描述" align="center" prop="remake" />
      <el-table-column label="关联时间" align="center" prop="associationDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.associationDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目进度" align="center" prop="progress" />
      <el-table-column label="已过天数(自动计算)" align="center" prop="daysPassed" />
      <el-table-column label="剩余天数(自动计算)" align="center" prop="daysRemaining" />
      <el-table-column label="完成内容概述" align="center" prop="completionSummary" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <!-- <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['project:recycle:edit']"
          >修改</el-button> -->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handlerestore(scope.row)"
            v-hasPermi="['project:recycle:remove']"
          >还原</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['project:recycle:remove']"
          >彻底删除</el-button>
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

    <!-- 添加或修改项目信息回收对话框 -->
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
        <el-form-item label="负责人" prop="manager">
          <el-input v-model="form.manager" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="项目组成员" prop="teamMembers">
          <el-input v-model="form.teamMembers" placeholder="请输入项目组成员" />
        </el-form-item>
        <el-form-item label="项目目标" prop="goal">
          <el-input v-model="form.goal" placeholder="请输入项目目标" />
        </el-form-item>
        <el-form-item label="项目范围" prop="scope">
          <el-input v-model="form.scope" placeholder="请输入项目范围" />
        </el-form-item>
        <el-form-item label="立项时间" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择立项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="计划结项时间" prop="plannedCompletionTime">
          <el-date-picker clearable
            v-model="form.plannedCompletionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划结项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目归属" prop="attribute">
          <el-input v-model="form.attribute" placeholder="请输入项目归属" />
        </el-form-item>
        <el-form-item label="历史项目" prop="oldProjectId">
          <el-input v-model="form.oldProjectId" placeholder="请输入历史项目" />
        </el-form-item>
        <el-form-item label="项目总进度" prop="progressAlloverProgress">
          <el-input v-model="form.progressAlloverProgress" placeholder="请输入项目总进度" />
        </el-form-item>
        <el-form-item label="项目描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入项目描述" />
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
        <el-form-item label="关联时间" prop="associationDate">
          <el-date-picker clearable
            v-model="form.associationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择关联时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目进度" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入项目进度" />
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
import { listRecycle, getRecycle, delRecycle, addRecycle, updateRecycle } from "@/api/project/recycle";
import { addInfo} from "@/api/project/info";


export default {
  name: "Recycle",
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
      // 项目信息回收表格数据
      recycleList: [],
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
        manager: null,
        teamMembers: null,
        currentStatus: null,
        goal: null,
        scope: null,
        startDate: null,
        plannedCompletionTime: null,
        attribute: null,
        oldProjectId: null,
        status: null,
        progressAlloverProgress: null,
        description: null,
        importDate: null,
        remake: null,
        associationDate: null,
        progress: null,
        daysPassed: null,
        daysRemaining: null,
        completionSummary: null
      },

            //项目进度选项
            progressOptions: [
        { value: '10%', label: '10%' },
        { value: '20%', label: '20%' },
        { value: '30%', label: '30%' },
        { value: '40%', label: '40%' },
        { value: '50%', label: '50%' },
        { value: '60%', label: '60%' },
        { value: '70%', label: '70%' },
        { value: '80%', label: '80%' },
        { value: '90%', label: '90%' },
        { value: '100%', label: '100%' }
      ],
      //项目等级选项
      levelOptions:[
        { 
          value: 'A级',
          label: 'A级'},
        {
          value: 'B级',
          label: 'B级'
        },
        {
          value: 'C级',
          label: 'C级'
        }
      ],
      //项目主责部门
      departmentOptions:[
        { value: '财务科', label: '财务科' },
        { value: '市场科', label: '市场科' },
        { value: '安环设备科', label: '安环设备科' },
        { value: '生产管理科', label: '生产管理科' },
        { value: '供应科', label: '供应科' },
        { value: '技术科', label: '技术科' },
        { value: '企业管理科', label: '企业管理科' },
        { value: '党群办公室', label: '党群办公室科' },
        { value: '质量科', label: '质量科' },
        { value: '执纪监督室', label: '执纪监督室' },
        { value: '团委', label: '团委' }
      ],
      //项目等级
      levelOptions:[
        { value: 'A级', label: 'A级' },
        { value: 'B级', label: 'B级' },
        { value: 'C级', label: 'C级' },
      ],
      //项目类别
      categoryOptions: [
        { value: '党工群团类', label: '党工群团类' },
        { value: '质量攻关类', label: '质量攻关类' },
        { value: '工艺技改类', label: '工艺技改类' },
        { value: '生产改善类', label: '生产改善类' },
        { value: '设备改善类', label: '设备改善类' },
        { value: '安全环保类', label: '安全环保类' },
        { value: '采购供应类', label: '采购供应类' },
        { value: '财务管理类', label: '财务管理类' },
        { value: '企业运行类', label: '企业运行类' },
      ],
      //项目状态选项
      statusOptions:[
        { 
          value: '未开始',
          label: '未开始'},
        {
          value: '进行中',
          label: '进行中'
        },
        {
          value: '滞后',
          label: '滞后'
        }
      ],
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
        startDate: [
          { required: true, message: "立项时间不能为空", trigger: "blur" }
        ],
        plannedCompletionTime: [
          { required: true, message: "计划结项时间不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "项目状态不能为空", trigger: "change" }
        ],
        progressAlloverProgress: [
          { required: true, message: "项目总进度不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询项目信息回收列表 */
    getList() {
      this.loading = true;
      listRecycle(this.queryParams).then(response => {
        this.recycleList = response.rows;
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
        manager: null,
        teamMembers: null,
        currentStatus: null,
        goal: null,
        scope: null,
        startDate: null,
        plannedCompletionTime: null,
        attribute: null,
        oldProjectId: null,
        status: null,
        progressAlloverProgress: null,
        description: null,
        importDate: null,
        remake: null,
        associationDate: null,
        progress: null,
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目信息回收";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getRecycle(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目信息回收";
      });
    },
//还原项目信息至原表格中
    handlerestore(row){
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认还原项目基本信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delRecycle(projectIds);
      }).then(function(){
        row.status = "已取消";
        return addInfo(row);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("还原成功");
      }).catch(() => {});
    },

    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateRecycle(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecycle(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除项目信息回收编号为"' + projectIds + '"的数据项？').then(function() {
        return delRecycle(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/recycle/export', {
        ...this.queryParams
      }, `recycle_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

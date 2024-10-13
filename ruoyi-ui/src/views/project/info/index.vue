<template>
  <div class="app-container">
    <!-- 查询 -->
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
      </el-form-item>
    </el-form>

    <!-- 操作栏 -->
    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['project:Info:add']"
        >新增</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['project:Info:edit']"
        >修改</el-button>
      </el-col> -->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleBatchRecycle"
          v-hasPermi="['project:Info:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['project:Info:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button 
          type="primary" 
          plain
          size="mini"
          @click="dialogVisible = true"
           v-hasPermi="['updata_recode:recode:list']"
        >历史记录</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->

        <!-- <el-button
          type="primary"
          @click="showDialog = true"
        ><i class="fa fa-download"></i>导入Excel文件
        </el-button> -->

        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['project:Info:import']"
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

      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-download" @click="handleDownload" size="mini" plain v-if="true">下载模版文件
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <!-- 更新历史记录的弹窗 -->
    <el-dialog title="日志详情" :visible.sync="dialogVisible" width="80%">
      <el-table v-loading="loading" :data="recodeList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="模块标题" align="center" prop="title" />
        <el-table-column label="业务类型" align="center" prop="businessType" />
        <el-table-column label="方法名称" align="center" prop="method" >
          <template slot-scope="scope">
            <span>{{ scope.row.method === "com.heli.project.controller.ProjectInfoTableController.add()" ? '新增' : '修改' }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作人员" align="center" prop="operName" />
        <el-table-column label="部门名称" align="center" prop="deptName" />
        <el-table-column label="请求参数" align="center" prop="operParam" />
        <el-table-column label="操作结果" align="center" prop="status" >
          <template slot-scope="scope">
            <span>{{ scope.row.status === 1 ? '失败' : '成功' }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作时间" align="center" prop="operTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.operTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
      </el-table>

      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getrecodeList"
      />

      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关 闭</el-button>
      </span>
    </el-dialog>

    <el-table v-loading="loading" :data="InfoList" @selection-change="handleSelectionChange">
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="100px"> 
        <template slot-scope="scope">
          <div class="button-group">
            <!-- <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['project:Info:edit']"
            >修改</el-button> -->
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="recycleInfo(scope.row)"
              v-hasPermi="['project:Info:remove']"
            >删除</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDeletefinish(scope.row)"
              v-hasPermi="['project:Info:remove']"
            >项目完成</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDeleteaborted(scope.row)"
              v-hasPermi="['project:Info:remove']"
            >项目终止</el-button>
            <el-popover ref="historyPopover" :visible.sync="isPopoverVisible" placement="right" trigger="click" width="750">
              <el-button size="mini" type="text" slot="reference" @click="fetchHistoryList" v-hasPermi="['project:Info:relevance']">关联历史项目</el-button>
              <div v-if="loading">加载中...</div>
              <div v-else>
                <h3>历史项目列表</h3>
                <el-table :data="historyList" @selection-change="handleRowClick" style="width: 100%">
                  <el-table-column type="selection" width="55"></el-table-column>
                  <el-table-column prop="projectName" label="项目名称"></el-table-column>
                </el-table>
                <el-button type="primary" @click="confirmSelection(scope.row)">确认</el-button>
                <el-button @click="cancelSelection">取消</el-button>
              </div>
            </el-popover>
          </div>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="projectId" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目类别" align="center" prop="category" />
      <el-table-column label="项目等级" align="center" prop="level" />
      <el-table-column label="主责部门" align="center" prop="department" />
      <el-table-column label="项目负责人" align="center" prop="manager" />
      <!-- <el-table-column label="项目描述" align="center" prop="description" /> -->
      <el-table-column label="立项评审时间" align="center" prop="startDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.startDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目组成员" align="center" prop="teamMembers" />
      <el-table-column label="项目现状" align="center" prop="currentStatus" />

      <el-table-column label="项目目标" align="center" prop="goal" />
      <el-table-column label="项目范围" align="center" prop="scope" />

      <!-- <el-table-column label="导入时间" align="center" prop="importDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <!-- <el-table-column label="描述" align="center" prop="remake" /> -->
      <el-table-column label="计划结项时间" align="center" prop="plannedCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目归属" align="center" prop="attribute" />
      <el-table-column label="历史项目" align="center" prop="oldProjectId" />
      <el-table-column label="项目状态" align="center" prop="status" />
      <el-table-column label="项目总进度" align="center" prop="progressAlloverProgress" />
      <el-table-column label="完成内容概述" align="center" prop="completionSummary" />

    </el-table>
    
    <!-- <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->

    <!-- 添加或修改项目基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目类别" prop="category">
          <el-select v-model="form.category" placeholder="请选择项目类别">
            <el-option
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目等级" prop="level">
          <el-select v-model="form.level" placeholder="请选择项目等级">
            <el-option
              v-for="item in levelOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="主责部门" prop="department">
          <!-- <el-input v-model="form.department" placeholder="请输入主责部门" /> -->
          <el-select v-model="form.department" placeholder="请选择主责部门">
            <el-option
              v-for="item in departmentOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目负责人" prop="manager">
          <el-input v-model="form.manager" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="立项评审时间" prop="startDate">
          <el-date-picker clearable
            v-model="form.startDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择立项时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="项目组成员" prop="teamMembers">
          <el-input v-model="form.teamMembers" placeholder="请输入组成员" />
        </el-form-item>
        <el-form-item label="项目现状" prop="currentStatus">
          <el-input v-model="form.currentStatus" placeholder="请输入项目现状" />
        </el-form-item>
        <el-form-item label="项目目标" prop="goal">
          <el-input v-model="form.goal" placeholder="请输入目标" />
        </el-form-item>
        <el-form-item label="项目范围" prop="scope">
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
        <!-- <el-form-item label="项目归属" prop="attribute">
          <el-input v-model="form.attribute" placeholder="请输入承接属性" />
        </el-form-item> -->

        <el-form-item label="项目归属" prop="attribute">
          <el-select v-model="selectedAttributes" multiple placeholder="请选择承接属性" value-key="attribute" allow-no-choice>
            <el-option label="四零活动" value="四零活动"></el-option>
            <el-option label="三大行动" value="三大行动"></el-option>
            <el-option label="精益党建项目" value="精益党建项目"></el-option>
            <el-option label="公司方针目标" value="公司方针目标"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="历史项目" prop="oldProjectId">
          <el-input v-model="form.oldProjectId" placeholder="请输入历史项目" />
        </el-form-item>

        <el-form-item label="项目总进度" prop="progressAlloverProgress">
          <el-input v-model="form.progressAlloverProgress" placeholder="请输入一个百分数（例如20%）" />
        </el-form-item>
        <el-form-item label="项目状态" prop="status">
          <el-select v-model="form.status" placeholder="请选择项目状态">
            <el-option
              v-for="item in statusOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- <el-form-item label="项目描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入项目描述" />
        </el-form-item> -->


        <!-- <el-form-item label="导入时间" prop="importDate">
          <el-date-picker clearable
            v-model="form.importDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择导入时间">
          </el-date-picker>
        </el-form-item> -->

        <!-- <el-form-item label="描述" prop="remake">
          <el-input v-model="form.remake" placeholder="请输入描述" />
        </el-form-item> -->

        <!-- <el-form-item label="关联时间" prop="associationDate">
          <el-date-picker clearable
            v-model="form.associationDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择关联时间">
          </el-date-picker>
        </el-form-item> -->

        <!-- <el-form-item label="项目进度" prop="progress">
          <el-input v-model="form.progress" placeholder="请输入项目进度" />
        </el-form-item> -->

        <!-- <el-form-item label="完成内容概述" prop="completionSummary">
          <el-input v-model="form.completionSummary" placeholder="请输入完成内容概述" />
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
import { listInfo, getProjectInfo, delInfo, addInfo, updateInfo, updateInfoHistory, uploadImport, handleTrueDownload} from "@/api/project/info";
import { listHistory } from "@/api/project/history";
import { listRecycle, addRecycle } from "@/api/project/recycle";
import { listRecode } from "@/api/project/recode";
import { getUserProfile } from "@/api/system/user";
import { getDept } from "@/api/system/project";

export default {
  name: "Info",
  data() {

    const validateProgressFormat = (rule, value, callback) => {
      if (!value) {
        return callback(new Error("项目总进度不能为空"));
      }
      // 检查是否为"20%"格式的字符串
      const regex = /^(\d{1,2}|\d{1,2}\.\d+)%$/;
      if (!regex.test(value)) {
        callback(new Error("项目总进度格式不正确，应输入一个百分数，例如10%"));
      } else {
        callback();
      }
    };

    return {

      selectedAttributes: [],

      departments: [],
      //用户名
      Username: null,
      //所属部门
      departmentCategory: null,
      userInfo: {}, // 存储用户信息
      historyList: [],
      selectedHistoryList: [],
      loading: false,
      isPopoverVisible: false,

      //更新历史记录弹窗相关参数
      dialogVisible: false, // 控制弹窗显示
      loading: false, // 控制表格加载状态
      recodeList: [], // 表格数据
      //更新历史记录弹窗的分页相关参数
      pagedRecodeList: [], // 当前页表格数据
      currentPage: 1, // 当前页码
      pageSize: 10, // 每页显示条数

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
      // 项目基本信息表格数据
      InfoList: [],
      rowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        oldProjectList:null,
        pageNum: 1,
        pageSize: 10000,
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
        associationDate: null,
        manager: null,
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
        },
        // {
        //   value: '已完成',
        //   label: '已完成'
        // },
        // {
        //   value: '已取消',
        //   label: '已取消'
        // }
      ],

      // 表单参数
      form: {
        attribute: '',
      },
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
        /*attribute: [
          { required: true, message: "承接属性不能为空", trigger: "blur" }
        ],*/
        // description: [
        //   { required: true, message: "项目描述不能为空", trigger: "blur" }
        // ],
        startDate: [
          { required: true, message: "立项时间不能为空", trigger: "blur" }
        ],
        progressAlloverProgress: [
          { required: true, message: "项目总进度不能为空", trigger: "blur" },
          { validator: validateProgressFormat, trigger: 'blur' }
        ],
        // importDate: [
        //   { required: true, message: "导入时间不能为空", trigger: "blur" }
        // ],
        status: [
          { required: true, message: "项目状态不能为空", trigger: "blur" }
        ],
        // progress: [
        //   { required: true, message: "项目进度不能为空", trigger: "blur" }
        // ],
        plannedCompletionTime: [
          { required: true, message: "计划结项时间不能为空", trigger: "blur" }
        ],
      },

      showDialog: false,
      progress: 0,

    };

    
  },
  watch: {
    // 监听 selectedAttributes 数组的变化，并转换为字符串
    selectedAttributes(newVal) {
      this.form.attribute = newVal.join(','); // 使用逗号将数组元素连接成字符串
    }
  },
  created() {
    this.getList();
    this.getrecodeList();
  },

  methods: {


    // 调用接口获取用户信息  uploadUsername、departmentCategory
    async getUserInfo() {
      try {
        const response = await getUserProfile();
        const userInfo = response.data; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        console.log("成功获取用户信息=======", userInfo);
        this.Username = userInfo.userName;

        const deptResponse = await getDept(userInfo.deptId);
        const deptInfo = deptResponse.data;
        this.departmentCategory = deptInfo.deptName;
        console.log("成功获取部门信息=======", this.departmentCategory);
      } catch (error) {
        console.error("获取用户信息失败:", error);
      }
    },

    formattedDaysPassed(startDate) {
      // 获取当前日期
      const currentDate = new Date();
      const startDateObj = new Date(startDate);

      // 计算已过天数
      const daysPassed = Math.floor((currentDate - startDateObj) / (1000 * 60 * 60 * 24));

      // 判断并返回结果
      if (daysPassed < 0) {
        return '未开始';
      } else {
        return daysPassed;
      }
    },

    formattedDaysRemaining(endDate) {
      // 获取当前日期
      const currentDate = new Date();
      const endDateObj = new Date(endDate);
      
      // 计算已过天数
      const daysRemaining = Math.floor((endDateObj - currentDate) / (1000 * 60 * 60 * 24));

      // 判断并返回结果
      if (daysRemaining < 0) {
        return '0';
      } else {
        return daysRemaining;
      }

    },

    //获取历史项目信息
    async fetchHistoryList() {
      try {
        this.loading = true; // 开始加载数据，显示加载中提示
        // 调用 API 方法获取历史项目列表
        const response = await listHistory(this.queryParams);
        this.historyList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.isPopoverVisible = true; // 打开弹窗

      } catch (error) {
        console.error("获取历史项目列表失败:", error);
        this.loading = false;
      }
    },

    handleRowClick(rows) {
      // 获取当前选中行的项目ID集合
      const selectedIds = new Set(rows.map(row => row.projectId));
      this.selectedHistoryList = Array.from(selectedIds);
      console.log(JSON.parse(JSON.stringify(this.selectedHistoryList)))
    },

    confirmSelection(info) {
      // 处理确认选项的逻辑，例如将选中的历史项目进行关联操作
      console.log("确认选择的历史项目:", this.selectedHistoryList);
      if (this.selectedHistoryList.length === 0) {
        this.$message.error('请选择至少一个历史项目！');
        return;
      }

      const data = {
        projectId: info, // 项目ID，假设您从 this.form 中获取
        selectedHistoryList: this.selectedHistoryList // 选中的历史项目列表
      };
      // 填充数据
      // info.oldProjectId = this.selectedHistoryList;
      info.oldProjectList = this.selectedHistoryList;
      info.associationDate = new Date();

      this.selectedHistoryList = [];
      this.isPopoverVisible = false; // 关闭弹窗


      console.log("projectId========="+info.projectId);
      console.log("history========="+info.oldProjectId)
      // 调用更新数据库的函数
      updateInfoHistory(info).then(response => {
        this.$message.success('关联成功');
        this.isPopoverVisible = false; // 关闭弹窗
        this.getList(); // 重新获取数据
      }).catch(error => {
        this.$message.error('关联失败: ' + error);
      });

      // location.reload();
    },

    cancelSelection() {
      // 处理取消选项的逻辑，例如清空选中的历史项目
      console.log("取消选择");
      // 重置状态
      this.selectedHistoryList = [];
      this.isPopoverVisible = false;
      this.$nextTick(() => {
        if (this.$refs.historyPopover) {
          this.$refs.historyPopover.doClose(); // 手动关闭弹窗
        }
      });
    },

    /** 查询项目基本信息列表 */
    // getList() {
    //   this.loading = true;
    //   listInfo(this.queryParams).then(response => {
    //     this.InfoList = response.rows;
    //     this.total = response.total;
    //     this.loading = false;
    //     this.split(this.InfoList,this.total);
    //   });
    // },

    async getList() {
      this.loading = true;
      this.InfoList = [],
      this.rowList = [],

      await this.getUserInfo();
      listInfo(this.queryParams).then(response => {

        for (var i = 0; i < response.total; i++) {

          if (
            this.departmentCategory == response.rows[i].department ||
            this.departmentCategory == "研发" ||
            this.departmentCategory == "总部"
          ) {
            this.InfoList.push(response.rows[i]);
            
          }
        }
        // this.InfoList = response.rows;
        this.total = response.total;
        this.loading = false;
        
        this.split(this.InfoList,this.total);
      });
    },

    getrecodeList() {
      this.loading = true;
      listRecode(this.queryParams).then(response => {
        this.recodeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询项目信息回收列表 */
    getRecycleList() {
      this.loading = true;
      listRecycle(this.queryParams).then(response => {
        this.recycleList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    split(data, total){
      for (let i = 0; i < total; i += 4) {
          // 4表示每行4条
          this.rowList.push(data.slice(i, i + 4));
        }
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
        associationDate: null,
        manager: null,
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
      this.ids = selection.map(item => item.projectId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加项目基本信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const projectId = row.projectId || this.ids
      getProjectInfo(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目基本信息";
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
    handleDeletefinish(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认移除项目基本信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delInfo(projectIds);
      }).then(function(){
        row.status = "已完成";
        return addHistory(row);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("移除成功，已加入历史项目");
      }).catch(() => {});

    },
    handleDeleteaborted(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认移除项目基本信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delInfo(projectIds);
      }).then(function(){
        row.status = "已取消";
        return addHistory(row);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("移除成功，已加入历史项目");
      }).catch(() => {});

    },
    //将项目移入回收站
    recycleInfo(row){
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认移除项目基本信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delInfo(projectIds);
      }).then(function(){
        row.status = "已取消";
        return addRecycle(row);
      }).then(() => {
        this.getList();
        // this.getRecycleList();
        this.$modal.msgSuccess("移除成功，已移入回收站");
      }).catch(() => {});
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download('project/Info/export', {
        ...this.queryParams
      }, `Info_${new Date().getTime()}.xlsx`)
    },

    handleBatchRecycle() {
      // 检查是否有项目被选中
      if (!this.ids || this.ids.length === 0) {
        this.$modal.msgWarning('请至少选择一个项目进行操作。');
        return;
      }

      // 确认删除操作
      this.$modal.confirm('是否确认移除选中的项目信息？').then(() => {
        // 从选中的项目中提取所有的 projectId
        // 调用删除接口
        // return delInfo(this.ids);
      }).then(() => {
        
        console.log("this.ids=="+this.ids);
        var temp = getProjectInfo(this.ids).data;

        temp = temp.data;
        console.log("hello +"+temp);
       

          // 获取每个 projectId 对应的完整项目信息
          const getInfoPromises = this.ids.map(projectId =>   getProjectInfo(projectId));

      


          console.log("--------"+temp);

          console.log("======"+getInfoPromises);
          // 等待所有项目信息获取完成
          return Promise.all(getInfoPromises);
        }).then(fullProjectInfos => {
          // 使用获取到的完整项目信息来调用 addRecycle
          const addRecyclePromises = fullProjectInfos.map(projectInfo => {
            // 假设这里需要设置项目的状态为 "已取消"
            projectInfo.status = "已取消";
            return addRecycle(projectInfo);
          });

          // 等待所有 addRecycle 调用完成
          return Promise.all(addRecyclePromises);
      }).then(() => {
        // 删除和更新操作成功后，重新获取列表
        this.getList();
        // 显示成功消息
        this.$modal.msgSuccess("移除成功，已移入回收站");
        this.ids = [];
      }).catch(() => {
        // 处理取消操作或其他错误
        this.$modal.msgError('操作已取消或发生错误。');
      });
    },


        /*  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      // console.log(file);
      formData.append("file", file);
      uploadImport(formData)
        .then(response => {
          // 文件上传成功
          setTimeout(() => {
            this.showDialog = false; // 关闭上传面板
            location.reload(); // 调用此方法刷新页面数据
          }, 2000); // 2000毫秒后关闭
          this.$message.success("上传成功");
        })
        .catch(error => {
          // 处理错误
          console.error('Error uploading file:', error);
        });


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
    },

    //下载模板文件

    handleDownload() {
      const url = "/profile/excel_templates/项目台账导入.xlsx";
      handleTrueDownload(url);
    },



  }
};
</script>

<style>
  .el-row {
    margin-bottom: 20px;
  }
  .el-col {
    border-radius: 4px;
  }
  .bg-purple-dark {
    background: #99a9bf;
  }
  .bg-purple {
    background: #d3dce6;
  }
  .bg-purple-light {
    background: #e5e9f2;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
  }
  .two-column {
  display: flex;
}
  .column {
    flex: 1;
  }
  /* .button-group {
  display: flex;
  gap: 8px; 
  justify-content: flex-start; 
  margin-right: 16px;
} */
</style>
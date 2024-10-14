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

      <el-form-item label="项目归属" prop="attribute">
        <el-select
          v-model="queryParams.attribute"
          placeholder="请输入项目归属"
          clearable
          filterable
          @change="handleQuery"
          allow-no-choice
        >
          <el-option label="四零活动" value="四零活动"></el-option>
          <el-option label="三大行动" value="三大行动"></el-option>
          <el-option label="精益党建项目" value="精益党建项目"></el-option>
          <el-option label="公司方针目标" value="公司方针目标"></el-option>
        </el-select>
      </el-form-item>

      <el-form-item label="立项时间" prop="startDate">  
        <el-date-picker  
          v-model="queryParams.startDate"  
          type="date"  
          value-format="yyyy-MM-dd"  
          placeholder="请选择立项时间"  
          clearable  
          @change="handleQuery"  
        >  
        </el-date-picker>  
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

    <el-row v-for="(infodata, index) in rowList" :key="index"  :gutter="20">
    <el-col v-for="(info, index1) in infodata" :key="index1" :span="6">
      <div class="grid-content bg-purple">
      <el-card>
        <div slot="header" class="clearfix">
          <span>项目信息</span>
        </div>
        
        <div class="card-container">
          <el-card class="user-card">

            <el-progress v-if="!isNaN(getPercentageValue(info.progressAlloverProgress))" :percentage="getPercentageValue(info.progressAlloverProgress)" type = "circle" >
            </el-progress>

            <p>已过天数: {{ formattedDaysPassed(info.startDate) }}</p>
            <el-progress :percentage="progress1(info.startDate, info.plannedCompletionTime)"></el-progress> 
            <p>剩余天数: {{ formattedDaysRemaining(info.plannedCompletionTime) }}</p>
            <el-progress :percentage="progress2(info.startDate, info.plannedCompletionTime)"></el-progress> 
            <p>项目id: {{ info.projectId }}</p>
            <p>项目名称: {{ info.projectName }}</p>
            <p>项目类别: {{ info.category }}</p>
            <p>项目等级: {{ info.level }}</p>

            <div style="display: flex; justify-content: flex-start; margin-bottom: 10px;">
              <el-button @click="ProgressReporting(info)">进度上报</el-button>
              <el-button @click="showDialog(info)">修改记录</el-button>

              <!-- 详情查看 -->
              <el-popover placement="right" trigger="click" width="1000">

                <div class="two-column">
                  <div class="column" style="width: 50%; margin-right: 10px;">
                    <pre style="white-space: pre-wrap;">
                      <pre style="white-space: pre-wrap;">项目id: {{ info.projectId }}</pre>
                      <pre style="white-space: pre-wrap;">项目名称: {{ info.projectName }}</pre>
                      <pre style="white-space: pre-wrap;">项目类别: {{ info.category }}</pre>
                      <pre style="white-space: pre-wrap;">项目等级: {{ info.level }}</pre>
                      <pre style="white-space: pre-wrap;">主责部门: {{ info.department }}</pre>
                      <pre style="white-space: pre-wrap;">项目描述: {{ info.description }}</pre>
                      <pre style="white-space: pre-wrap;">立项时间: {{ info.startDate }}</pre>
                      <pre style="white-space: pre-wrap;">项目总进度: {{ info.progressAlloverProgress }}</pre>
                      <pre style="white-space: pre-wrap;">导入时间: {{ info.importDate }}</pre>
                      <pre style="white-space: pre-wrap;">描述: {{ info.remake }}</pre>
                      <pre style="white-space: pre-wrap;">历史项目: {{ info.oldProjectId }}</pre>
                      <pre style="white-space: pre-wrap;">关联时间: {{ info.associationDate }}</pre>
                    </pre>
                  </div>
                  <div class="column" style="width: 50%; margin-left: 10px;">
                    <pre style="white-space: pre-wrap;">
                      <pre style="white-space: pre-wrap;">负责人: {{ info.manager }}</pre>
                      <pre style="white-space: pre-wrap;">组成员: {{ info.teamMembers }}</pre>
                      <pre style="white-space: pre-wrap;">项目状态: {{ info.status }}</pre>
                      <pre style="white-space: pre-wrap;">交付物: </pre>
                      <div style="white-space: pre-wrap;">
                        <div v-if="info.progress">
                          <a :href="info.progress" download>
                            <el-button size="small" type="primary">下载</el-button>
                          </a>
                        </div>
                        <div v-else>
                          无文件
                        </div>
                      </div>
                      
                      <pre style="white-space: pre-wrap;">项目现状: {{ info.currentStatus }}</pre>
                      <pre style="white-space: pre-wrap;">目标: {{ info.goal }}</pre>
                      <pre style="white-space: pre-wrap;">范围: {{ info.scope }}</pre>
                      <pre style="white-space: pre-wrap;">计划结项时间: {{ info.plannedCompletionTime }}</pre>
                      <pre style="white-space: pre-wrap;">已过天数: {{ formattedDaysPassed(info.startDate) }}</pre>
                      <pre style="white-space: pre-wrap;">剩余天数: {{ formattedDaysRemaining(info.plannedCompletionTime) }}</pre>
                      <pre style="white-space: pre-wrap;">完成内容概述: {{ info.completionSummary }}</pre>
                    </pre>
                  </div>
                </div>

                <el-button slot="reference">查看详情</el-button>
              </el-popover>
            </div>

          </el-card>
        </div>
      </el-card>
      </div>
    </el-col>

    </el-row>
    
    <!-- <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    /> -->

        <!-- 项目修改记录 -->
        <el-dialog
          title="修改记录"
          :visible.sync="dialogVisible"
          width="85%"
        >
          <p>项目名称：{{ selectedInfo.projectName }}</p>
          <el-table v-loading="loading" :data="recoding" >

            <el-table-column label="项目名称" align="center" prop="projectName" />
            <el-table-column label="主责部门" align="center" prop="department" />
            <el-table-column label="开始时间" align="center" prop="importDate" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="结束时间" align="center" prop="associationDate" width="180">
              <template slot-scope="scope">
                <span>{{ parseTime(scope.row.importDate, '{y}-{m}-{d}') }}</span>
              </template>
            </el-table-column>
            <el-table-column label="项目总进度" align="center" prop="progressAlloverProgress" />
            <el-table-column label="项目状态" align="center" prop="status" />
            <el-table-column label="是否有相关方案或计划" align="center" prop="remake" />
            <el-table-column label="完成工作事项" align="center" prop="completionSummary" />
            <el-table-column label="交付物" align="center" prop="progress" />
            <el-table-column label="关键事项说明" align="center" prop="description" />

            
          </el-table>
          <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">关闭</el-button>
          </span>
        </el-dialog>

        <!-- 进度上报对话框 -->
        <el-dialog :title="title" :visible="reportingopen" width="500px" append-to-body :before-close="handleClose">
          <el-form ref="form" :model="form" label-width="80px">

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
              <el-select v-model="form.progressAlloverProgress" placeholder="请选择进度">
                <el-option
                  v-for="item in progressOptions"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
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

            <el-form-item label="是否有相关方案或计划" prop="remake">
              <el-select v-model="form.remake" placeholder="目前是否有相关方案或计划">
                <el-option key="是" label="是" value="是">是</el-option>
                <el-option key="否" label="否" value="否">否</el-option>
              </el-select>
            </el-form-item>

            <el-form-item label="完成工作事项" prop="completionSummary">
              <el-input type="textarea" :rows="2" v-model="form.completionSummary" placeholder="请输入" />
            </el-form-item>

            <el-form-item label="交付物" prop="progress">
              <!-- <el-input type="textarea" :rows="2" v-model="form.progress" placeholder="请输入交付物" /> -->
              <div >
                <!--pdf文件上传-->
                <el-upload
                  ref="fileUpload"
                  v-model="fileInfo.filePath"
                  class="upload-file-uploader"
                  :action="uploadFileUrl"
                  :headers="headers"
                  :on-change="handleFileChange"
                  :on-success="prehandleUploadSuccess"
                  multiple
                  :limit="3"
                  :file-list="fileList"
                  :accept="acceptedFileTypes"
                  list-type="picture-card"
                >
                  <el-button size="small" type="primary">点击上传</el-button>
                </el-upload>
              </div>
            </el-form-item>

            <el-form-item label="关键事项说明" prop="description">
              <el-input  type="textarea" :rows="2" v-model="form.description" placeholder="请输入关键事项说明" />
            </el-form-item>

          </el-form>

          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>

        <el-dialog :title="title" :visible="open" width="500px" append-to-body :before-close="handleClose">
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

          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </el-dialog>
  </div>
</template>

<script>
import { listInfo, getProjectInfo, delInfo, addInfo, updateInfo, updateInfoHistory, Recodingquery} from "@/api/project/info";
import { listRecode, getRecode, delRecode, addRecode, updateRecode } from "@/api/project/recode";
import { listHistory } from "@/api/project/history";
import { getUserProfile } from "@/api/system/user";
import { getToken } from "@/utils/auth";

//获取用户信息-部门
import { getDept } from "@/api/system/project";

export default {
  inject: ["reload"],
  name: "Info",
  props: {
      value: [String, Object, Array],
      limit: {
        type: Number,
        default: 10,
      },
      fileSize: {
        type: Number,
        default: 5,
      },
      fileType: {
        type: Array,
        default: () => ["doc", "docx", "pdf", "xlsx", "jpg", "png"],
      },
      isShowTip: {
        type: Boolean,
        default: true,
      },
    },


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
      selectedInfo: {}, // 存储被点击的行数据

      historyList: [],
      selectedHistoryList: [],
      loading: false,
      isPopoverVisible: false,
      dialogVisible: false, // 控制对话框的显示与隐藏
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

      recodingtotal: 100,
      // 统计库存车数量表格数据

      recoding: [],

      // 项目基本信息表格数据
      InfoList: [],
      rowList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      reportingopen: false,
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
        description: [
          { required: true, message: "项目描述不能为空", trigger: "blur" }
        ],
        startDate: [
          { required: true, message: "立项时间不能为空", trigger: "blur" }
        ],
        progressAlloverProgress: [
          { required: true, message: "项目总进度不能为空", trigger: "blur" },
          { validator: validateProgressFormat, trigger: 'blur' }
        ],
        importDate: [
          { required: true, message: "导入时间不能为空", trigger: "blur" }
        ],
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
      userInfo: {}, // 存储用户信息

      reportingopen: false,


      //文件上传相关参数
      fileInfo: {
        filePath: '',
        fileName: '',
        fileType: '',
        fileSize: 0,
        uploadDate: '',
        createUsername: '',
        departmentCategory: ''
      },
      fileList: [],
      uploadList: [],
      headers: {
        Authorization: `Bearer ${getToken()}`
      },
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      acceptedFileTypes: '.jpg,.png,.pdf,.docx,.xlsx,.txt,.rar,.zip' // 接收的文件类型

    };
  },
  watch: {
    // 监听 selectedAttributes 数组的变化，并转换为字符串
    selectedAttributes(newVal) {
      this.form.attribute = newVal.join(','); // 使用逗号将数组元素连接成字符串
    }
  },
  created() {

    // this.fetchUserInfo();
    this.getList();
  },

  methods: {

    showDialog(row) {
      this.selectedInfo = row; // 将被点击的行数据赋值给selectedRow
      this.dialogVisible = true; // 显示对话框
      
      this.recoding = [];
      Recodingquery(row.projectName).then(response => {

        response.rows.forEach(item => {
      // 创建一个新的对象，只包含需要的字段
          const recode = {
            projectName: item.projectName,
            department: item.department,
            importDate: item.importDate,
            associationDate: item.associationDate,
            progressAlloverProgress: item.progressAlloverProgress,
            status: item.status,
            remake: item.remake,
            completionSummary: item.completionSummary,
            progress: item.progress,
            description: item.description

          };
          // 将新的对象添加到 this.recoding 中
          this.recoding.push(recode);
        });


      }).catch(error => {
      // console.error("Error calling Recodingquery:", error);
      // 这里可以添加错误处理逻辑，比如显示错误消息给用户
    });
    },

    // 调用接口获取用户信息  uploadUsername、departmentCategory
    async getUserInfo() {
      try {
        const response = await getUserProfile();
        const userInfo = response.data; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        this.Username = userInfo.userName;

        const deptResponse = await getDept(userInfo.deptId);
        const deptInfo = deptResponse.data;
        this.departmentCategory = deptInfo.deptName;
      } catch (error) {
        console.error("获取用户信息失败:", error);
      }
    },

    getPercentageValue(progressString) {
      // 移除字符串末尾的 '%'
      const progressValue = progressString.replace('%', '');
      // 将字符串转换为整数
      return parseInt(progressValue, 10);
    },
    
    //计算当日与startDate的日期差
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

    //获取已过时间的百分比
     progress1(startDate, endDate){
      const currentDate = new Date();
      const startDateObj = new Date(startDate);
      const endDateObj = new Date(endDate);

          // 计算已过天数
      const daysPassed = Math.floor((currentDate - startDateObj) / (1000 * 60 * 60 * 24));
      const allDays = Math.floor((endDateObj - startDateObj) / (1000 * 60 * 60 * 24));


      let res = 0;

      if(daysPassed >= allDays){
        return 100;
      }
      if(daysPassed <= 0){
        return 0;
      }

      res = Math.floor(daysPassed / allDays * 100);

      res = Math.max(res, 0);

      return res;
     },

    //获取剩余时间的百分比
    progress2(startDate, endDate){
      const currentDate = new Date();
      const startDateObj = new Date(startDate);
      const endDateObj = new Date(endDate);

      const daysPassed = Math.floor((currentDate - startDateObj) / (1000 * 60 * 60 * 24));
      const allDays = Math.floor((endDateObj - startDateObj) / (1000 * 60 * 60 * 24));


      let res = 0;

      if(daysPassed >= allDays){
        return 0;
      }
      if(daysPassed <= 0){
        return 100;
      }
      res = Math.floor(daysPassed / allDays * 100);

      res = 100 - res;

      return res;
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
    },



    confirmSelection(info) {
      // 处理确认选项的逻辑，例如将选中的历史项目进行关联操作
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

      // 调用更新数据库的函数
      updateInfoHistory(info).then(response => {
        this.$message.success('关联成功');
        this.isPopoverVisible = false; // 关闭弹窗
        this.getList(); // 重新获取数据
      }).catch(error => {
        this.$message.error('关联失败: ' + error);
      });

      location.reload();
      this.reload();
    },


    cancelSelection() {
      // 处理取消选项的逻辑，例如清空选中的历史项目
      // 重置状态
      this.selectedHistoryList = [];
      this.isPopoverVisible = false;
      // this.$refs.historyPopover.doClose(); // 手动关闭弹窗
    },

    /** 查询项目基本信息列表 */
    async getList() {
      this.loading = true;
      this.InfoList = [],
      this.rowList = [],

      await this.getUserInfo();
      listInfo(this.queryParams).then(response => {
        this.InfoList = response.rows;
        this.total = response.total;
        this.loading = false;
        
        this.split(this.InfoList,this.total);
      });
    },

    split(data, total){
      this.rowList = [];
      for (let i = 0; i < total; i += 4) {
          // 4表示每行4条
          this.rowList.push(data.slice(i, i + 4));
        }
    }, 

    // 取消按钮
    cancel() {
      this.open = false;
      this.reportingopen = false;
      this.closeDialog(); // 点击取消关闭对话框
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
      const projectId = row.projectId || this.ids;
      getProjectInfo(projectId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目基本信息";
      });
    },
    ProgressReporting(row){
      this.reset();
      const projectId = row.projectId || this.ids;
      getProjectInfo(projectId).then(response => {
        this.form = response.data;
        this.reportingopen = true;
        this.title = "进度上报";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.projectId != null) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.reportingopen = false;
              this.open = false;
              this.getList();
            });
            addRecode(this.form);

          } else {
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.reportingopen = false;
              this.open = false;
              this.getList();
            });
          }
        }
        this.closeDialog();
      });
    },


    /** 删除按钮操作 */
    handleDelete(row) {
      const projectIds = row.projectId || this.ids;
      this.$modal.confirm('是否确认删除项目基本信息编号为"' + projectIds + '"的数据项？').then(function() {
        return delInfo(projectIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('project/Info/export', {
        ...this.queryParams
      }, `Info_${new Date().getTime()}.xlsx`)
    },

    // 打开对话框的方法
    openDialog() {
      this.reportingopen = true;
    },
    // 关闭对话框的方法
    closeDialog() {
      this.reportingopen = false;
    },

    // 处理对话框关闭前的逻辑
    handleClose(done) {
      // 阻止默认的关闭行为
      done(false);
    },

    //文件上传相关方法
    //&&&&&&&&&&&&&&&&&&&&&&&&&&//
    handleFileChange(file, fileList) {
      const uploadedFile = file.raw; // 获取上传的文件对象

      // 更新文件信息
      this.$set(this.fileInfo, 'fileName', uploadedFile.name);
      this.$set(this.fileInfo, 'filePath', '');
      this.$set(this.fileInfo, 'fileType', this.getFileType(uploadedFile.type));
      this.$set(this.fileInfo, 'fileSize', this.formatFileSize(uploadedFile.size));
      this.$set(this.fileInfo, 'uploadDate', new Date().toISOString().split('T')[0]);
      this.getUserInfo();
    },
    prehandleUploadSuccess(res, file) {
      //将返回的地址存入数据库中
      // console.log(res.url);
      this.form.progress = res.url;

      if (res.code === 200) {
        this.path = res.url;
        this.uploadList.push({ name: res.fileName, url: res.fileName });
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$refs.fileUpload.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },
    posthandleUploadSuccess(res, file) {
      //将返回的地址存入数据库中
      // console.log(res.url);
      this.form.postRectificationPhoto = res.url;

      if (res.code === 200) {
        this.path = res.url;
        this.uploadList.push({ name: res.fileName, url: res.fileName });
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$refs.fileUpload.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },
    formatFileSize(sizeInBytes) {
      const KB = 1024;
      const MB = KB * 1024;
      const GB = MB * 1024;

      if (sizeInBytes < KB) {
        return sizeInBytes + "B";
      } else if (sizeInBytes < MB) {
        return (sizeInBytes / KB).toFixed(2) + "KB";
      } else if (sizeInBytes < GB) {
        return (sizeInBytes / MB).toFixed(2) + "MB";
      } else {
        return (sizeInBytes / GB).toFixed(2) + "GB";
      }
    },
    getFileType(fullType) {
      if (fullType.includes('jpeg') || fullType.includes('jpg')) {
        return 'jpg';
      } else if (fullType.includes('png')) {
        return 'png';
      } else {
        return 'other';
      }
    },
    getUserInfo() {
      getUserProfile().then(response => {
        const userInfo = response.data;
        this.$set(this.fileInfo, 'createUsername', userInfo.userName);
        getDept(userInfo.deptId).then(response => {
          const deptInfo = response.data;
          this.$set(this.fileInfo, 'departmentCategory', deptInfo.deptName);
        });
      }).catch(error => {
        console.error('获取用户信息失败:', error);
      });
    },
    uploadedSuccessfully() {
      if (this.number > 0 && this.uploadList.length === this.number) {
        this.fileList = this.fileList.concat(this.uploadList);
        this.uploadList = [];
        this.number = 0;
        this.$emit("input", this.fileList.map(file => file.url).join(','));
      }
    },
    previewImage(photoUrl) {
      // 在这里实现预览图片的逻辑
      // 例如，可以打开一个模态框显示图片
      console.log('预览图片', photoUrl);
      if (!photoUrl) {
        // 如果URL为空，显示提示信息
        this.$message({
          message: '没有图片可供预览',
          type: 'warning'
        });
        return;
      }
      window.open(photoUrl, '_blank');
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
  .progress-text {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  color: white;
  font-size: 16px;
  font-weight: bold;
}

.resizable-dialog {  
  min-width: 600px; /* 设置最小宽度 */  
  min-height: 400px; /* 设置最小高度 */  
  resize: both; /* 允许用户调整大小 */  
  overflow: auto; /* 允许滚动 */  
} 
</style>

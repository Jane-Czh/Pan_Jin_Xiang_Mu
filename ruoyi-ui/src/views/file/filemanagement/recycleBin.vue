<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
             label-width="68px">

      <!-- 部门 进行搜索  -->
      <el-form-item label="主责部门" prop="mainResponsibleDepartment" >
        <el-select
          v-model="queryParams.mainResponsibleDepartment"
          placeholder="请选择主责部门"
          clearable
          @change="handleDepartmentChange"
        >
          <el-option
            v-for="item in departments"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <!-- 2. 可选, 从已有的业务模块中进行选择 ; 当上级部门被选了, 就只能从对应的业务模块中进行选择 -->
      <el-form-item label="业务模块" prop="businesses">
        <el-select
          v-model="queryParams.businesses"
          placeholder="请选择业务模块"
          clearable
          :disabled="!queryParams.mainResponsibleDepartment"
          @change="handleModuleChange"
        >
          <el-option
            v-for="item in modules"
            :key="item.bmId"
            :label="item.moduleName"
            :value="item.moduleName"
          />
        </el-select>
      </el-form-item>
      <!-- 3. 可选, 从已有的细分业务中进行选择 ; 当上级业务模块被选了, 就只能从对应的细分业务中进行选择 -->
      <el-form-item label="细分业务" prop="subBusinesses">
        <el-select
          v-model="queryParams.subBusinesses"
          placeholder="请选择细分业务"
          clearable
          :disabled="!queryParams.businesses"
        >
          <el-option
            v-for="item in subBusinesses"
            :key="item.subbId"
            :label="item.subBusinessesName"
            :value="item.subBusinessesName"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="专业分类" prop="classificationOfSpecialties">
        <el-select
          v-model="queryParams.classificationOfSpecialties"
          placeholder="请选择制度专业分类"
          clearable
        >
          <el-option
            v-for="item in classificationOfSpecialtiesList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="制度等级" prop="regulationLeval">
        <el-select
          v-model="queryParams.regulationLeval"
          placeholder="请选择制度专业分类"
          clearable
        >
          <el-option
            v-for="item in regulationLevalList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布日期" prop="createDate">
        <el-date-picker clearable
                        v-model="queryParams.createDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择制度创建日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="制度名称" prop="regulationsTitle">
        <el-input
          v-model="queryParams.regulationsTitle"
          placeholder="请输入制度名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="fileTag">
        <el-input
          v-model="queryParams.fileTag"
          placeholder="请输入关键字"
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
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['file:filemanagement:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportAll"
          v-hasPermi="['file:filemanagement:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filemanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="主责部门" align="center" prop="mainResponsibleDepartment" />
      <el-table-column label="业务模块" align="center" prop="businesses" />
      <el-table-column label="细分业务" align="center" prop="subBusinesses" />
      <el-table-column label="制度名称" align="center" prop="regulationsTitle">
        <template slot-scope="scope">
          <a  @click.prevent="previewFile(scope.row.pdfPath)" style="color: #6495ED;">{{scope.row.regulationsTitle}}</a>
        </template>
      </el-table-column>
      <el-table-column label="专业分类" align="center" prop="classificationOfSpecialties" />
      <el-table-column label="制度编号" align="center" prop="regulationNumber" />
      <el-table-column label="制度等级" align="center" prop="regulationLeval" />
      <el-table-column label="发布日期" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实施日期" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关联流程" align="center">
        <template slot-scope="scope">
          <!-- 111111111111制度文件 -->
          <el-tooltip
            class="item"
            effect="dark"
            content="点击查看详情！"
            placement="top"
          >
            <!-- popover：1、制度文件显示 -->
            <el-popover
              placement="bottom"
              title="绑定的流程"
              trigger="click"
            >
              <template slot="reference">
                <span class="file" @click="handleProjectDetails(scope.row)">
                  <i class="el-icon-files"></i>
                </span>
              </template>
              <!-- slot插槽展示自定义内容 -->
              <div v-if="projectNames.length != 0">
                <ul>
                  <li v-for="(file, index) in projectNames" :key="index">
                    {{ file }}
                  </li>
                </ul>
              </div>
              <div v-else>"无绑定"</div>
              <!-- slot插槽over -->
            </el-popover>
          </el-tooltip>
        </template>
      </el-table-column>
<!--      <el-table-column label="关联表单" align="center" prop="formId" />-->
      <el-table-column label="状态" align="center" prop="revisionContent"/>
      <el-table-column label="最新上传日期" align="center" prop="uploadDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="文件名称" align="center" prop="fileName"/>-->
      <!--      <el-table-column label="文件下载" align="center" prop="filePath">-->
      <!--        <template slot-scope="scope">-->
      <!--          <a @click.prevent="downloadFile(scope.row.filePath)" style="color: #6495ED;">点击下载</a>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="pdf下载" align="center" prop="pdfPath">
        <template v-slot:default="scope">
          <a v-if="scope.row.pdfPath" @click.prevent="downloadFile(scope.row.pdfPath)" style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="word下载" align="center" prop="wordPath">
        <template v-slot:default="scope">
          <a
            v-if="scope.row.wordPath"
            :disabled="thisDept !== scope.row.departmentCategory && ![ '研发', '企业管理科', '总部' ].includes(thisDept)"
            @click.prevent="downloadFile(scope.row.wordPath)"
            :style="{ color: (thisDept === scope.row.mainResponsibleDepartment || thisDept === '研发' || thisDept === '企业管理科' || thisDept === '总部') ? '#6495ED' : '#CCC', pointerEvents: (thisDept === scope.row.mainResponsibleDepartment || thisDept === '研发' || thisDept === '企业管理科' || thisDept === '总部') ? 'auto' : 'none' }"
          >
            点击下载
          </a>
        </template>
      </el-table-column>

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:filemanagement:remove']"
            :disabled="thisDept !== scope.row.departmentCategory && ![ '研发', '企业管理科', '总部' ].includes(thisDept)"
          >
            删除
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handlerestore(scope.row)"
            v-hasPermi="['file:filemanagement:remove']"
            :disabled="thisDept !== scope.row.departmentCategory && ![ '研发', '企业管理科', '总部' ].includes(thisDept)"
          >
            还原
          </el-button>

        </template>
      </el-table-column>

      <el-table-column label="查看" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="previewFile(scope.row.pdfPath)"
          >预览
          </el-button>
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
  </div>



</template>

<script>
import {
  listFilemanagement,
  getFilemanagement,
  delFilemanagement,
  addFilemanagement,
  updateFilemanagement, getRegulationRecycleList
} from "@/api/file/filemanagement";
import {
  listProject,
} from "@/api/system/project";
import {listNode,getNode} from "@/api/file/flownode";
import {getUserProfile02} from '@/api/file/filemanagement'
import {getDept02} from '@/api/file/filemanagement'
import {getToken} from "@/utils/auth"
import {word2Pdf} from "../../../api/file/filemanagement";
import mammoth from 'mammoth';
import {listDept02} from "../../../api/file/filemanagement";
//业务模块api，
import {listModules, listModuless} from "@/api/function/modules";
//细分业务api
import {listBusinesses, listBusinessess} from "@/api/function/businesses";
//导出总台账excel功能
import * as XLSX from "xlsx";
import { saveAs } from "file-saver";
import {Loading} from "element-ui";
import { listDept } from "@/api/system/project";
import {listFormfilemanagement} from "@/api/file/formfilemanagement";  //获取部门列表
import { mapActions } from 'vuex';


export default {
  name: "Filemanagement",
  props: {
    value: [String, Object, Array],
    limit: {
      type: Number,
      default: 1,
    },
    fileSize: {
      type: Number,
      default: 5,
    },
    fileType: {
      type: Array,
      default: () => ["doc", "docx", "pdf"],
    },
    isShowTip: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      //部门列表
      departments: [],
      // 查询参数
      subBusinessesList: [], //获取的全部的业务列表
      subBusinesses: [], //过滤后的细分业务列表

      // 业务模块 查询参数
      moduleQueryParams: {
        pageNum: 1,
        pageSize: 5000,
        moduleName: null,
        parentDepartment: null,
        isDeleted: null,
        description: null,
      },

      // 细分业务 查询参数
      xifenQueryParams: {
        pageNum: 1,
        pageSize: 5000,
        subBusinessesName: null,
        parentDepartment: null,
        parentModule: null,
        isDeleted: null,
        description: null,
      },
      modules: [], //过滤后 业务模块 数据
      modulesList: [],//全部的 业务模块 数据
      //制度专业类别
      classificationOfSpecialtiesList: [
        "安全管理",
        "财务管理",
        "采购管理",
        "成本管理",
        "党群管理",
        "法人治理",
        "会计核算管理",
        "监察管理",
        "人力资源管理",
        "设备管理",
        "生产管理",
        "投资、规划管理",
        "信息管理",
        "研发管理",
        "营销管理",
        "质量管理",
        "综合管理"
      ],
      //制度等级
      regulationLevalList: [
        "公司级",
        "部门级"
      ],
      //表单展示数据
      formData: {
        //主责部门
        department: "",
        //业务模块
        businessesModules: "",
        //细分业务
        subBusinesses: "",
      },
      projectNames:[], //关联流程名称列表
      projectNamesString : "",  //关联流程名称列表（用”，“拼接）
      allProjectList:[],  //所有制度关联的流程名称列表
      //当前账号的dept
      thisDept: null,
      //文件上传绑定的部门
      fileDept: null,
      number: 0,
      uploadList: [],
      fileList: [],
      pdfList: [],  //上传的pdf文件list
      wordList: [], //上传的word文件list
      fd: {},
      path: '',
      baseUrl: process.env.VUE_APP_BASE_API,
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      // uploadFileUrl:"/profile/upload"+"/"+this.file.name,
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      userProfile: {}, // 存储用户个人信息的对象
      input: '',
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
      // 制度文件管理表格数据
      filemanagementList: [],
      // 表单文件管理表格数据
      formmanagementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      fileUpdateDialogVisible: false,
      fileModifyDialogVisible: false,
      fileUploadDialogVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        regulationsTitle: null,
        useScope: null,
        uploadDate: null,
        effectiveDate: null,
        fileName: null,
        filePath: null,
        pdfPath: null,
        wordPath: null,
        fileType: null,
        fileSize: null,
        pdfSize: null,
        wordSize: null,
        fileContent: null,
        createDate: null,
        uploadUsername: null,
        useState: null,
        departmentCategory: null,
        mainResponsibleDepartment: null,
        classificationOfSpecialties: null,
        regulationLeval: null,
        regulationNumber: null,
        fileTag: null,
        oldRegulationsId: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        formId: null,
        projectIds: null,
        newFlag: null,
        addFlag: null,
        newRegulationsId: null,
        businesses: null,
        subBusinesses: null
      },
      // 表单查询参数
      formQueryParams: {
        formTitle: null,
        scope: null,
        effectiveDate: null,
        formName: null,
        formType: null,
        formPath: null,
        formSize: null,
        createUsername: null,
        departmentCategory: null,
        remark: null,
        oldFormId: null,
        revisionTime: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        newFormId: null,
        businesses: null,
        subBusinesses: null
      },
      //流程查询参数
      projecQueryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        state: null
      },
      //流程节点查询参数
      nodeQueryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        projectId: null,
        name: null,
        state: null
      },

      // 表单参数
      form: {
        regulationsId: null,
        regulationsTitle: null,
        useScope: null,
        uploadDate: null,
        effectiveDate: null,
        fileName: null,
        filePath: null,
        pdfPath: null,
        wordPath: null,
        fileType: null,
        fileSize: null,
        pdfSize: null,
        wordSize: null,
        fileContent: null,
        createDate: null,
        uploadUsername: null,
        useState: null,
        departmentCategory: null,
        mainResponsibleDepartment: null,
        classificationOfSpecialties: null,
        regulationLeval: null,
        regulationNumber: null,
        fileTag: null,
        oldRegulationsId: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        formId: null,
        projectIds: null,
        newFlag: null,
        addFlag: null,
        newRegulationsId: null,
        businesses: null,
        subBusinesses: null
      },
      // 表单校验
      rules: {
        regulationsTitle: [
          {required: true, message: "制度标题不能为空", trigger: "blur"}
        ],
        useScope: [
          {required: true, message: "制度范围不能为空", trigger: "blur"}
        ],
        createDate: [
          {required: true, message: "制度发布日期不能为空", trigger: "blur"}
        ],
        effectiveDate: [
          {required: true, message: "制度实施日期不能为空", trigger: "blur"}
        ],
        departmentCategory: [
          {required: true, message: "制度所属科室不能为空", trigger: "blur"}
        ],
        mainResponsibleDepartment: [
          {required: true, message: "制度主责部门不能为空", trigger: "blur"}
        ],
        businesses: [
          {required: true, message: "业务模块不能为空", trigger: "blur"}
        ],
        subBusinesses: [
          {required: true, message: "细分业务不能为空", trigger: "blur"}
        ],
        classificationOfSpecialties: [
          {required: true, message: "专业分类不能为空", trigger: "blur"}
        ],
        regulationLeval: [
          {required: true, message: "制度等级不能为空", trigger: "blur"}
        ],
        regulationNumber: [
          {required: true, message: "制度编号不能为空", trigger: "blur"}
        ],

      },
      uploadVisible: false,

      ruleForm: {
        remark: ''
      },


    };
  },
  watch: {
    value: {
      handler(val) {
        if (val) {
          let temp = 1;
          // 首先将值转为数组
          const list = Array.isArray(val) ? val : this.value.split(',');
          // 然后将数组转为对象数组
          this.fileList = list.map(item => {
            if (typeof item === "string") {
              item = {name: item, url: item};
            }
            item.uid = item.uid || new Date().getTime() + temp++;
            return item;
          });
        } else {
          this.pdfList = [];
          this.wordList = [];
          return [];
        }
      },
      deep: true,
      immediate: true
    }
  },
  computed: {
    // 是否显示提示
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize);
    },
  },
  created() {
    getUserProfile02().then(response => {
      // 处理成功的情况
      console.log('成功获取用户信息response.data====>', response.data);
      console.log('成功获取用户信息response.data.dept.deptName====>', response.data.dept.deptName);
      // const userInfo =; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
      this.thisDept =  response.data.dept.deptName;
      this.getList();
    }).catch(error => {
      // 处理失败的情况
      console.error('获取用户信息失败:', error);
    });

    //获取部门列表
    this.getDeptList();
  },

  // 路由钩子，每次进入该路由时都会调用getList方法
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.getList(); // 在路由导航完成后调用getList
    });
  },
  methods: {
    /** 查询文件管理列表 */
    getList() {
      this.loading = true;
      // 如果部门是研发或总部，则不添加departmentCategory到queryParams
      // if (!['研发', '总部'].includes(this.thisDept)) {
      //   this.queryParams.departmentCategory = this.thisDept;
      // }
      getRegulationRecycleList(this.queryParams).then(response => {
        console.log("response:：", response);
        this.filemanagementList = response.rows;
        console.log("filemanagementList=>", this.filemanagementList);
        this.total = response.total;
        this.loading = false;
      });

    },
    // 查询绑定的流程信息
    handleProjectDetails(row) {
      return listProject(this.projecQueryParams).then(response => {
        console.log("response111:", response);
        const projectList = response;
        this.projectNames = [];

        projectList.forEach(process => {
          if (process.state && process.state.includes(row.regulationsId)) {
            this.projectNames.push(process.name);
            console.log("projectNames=>", this.projectNames);
          }
        });

        // 将 projectNames 转换为用逗号分隔的字符串
        this.projectNamesString = this.projectNames.join(",");
        console.log("projectNamesString=>", this.projectNamesString);
        // 将projectNamesString插入到allProjectList中
        this.allProjectList.push(this.projectNamesString);
        console.log("this.allProjectList====>",this.allProjectList);
      });
    },
    // 表单重置
    reset() {
      this.form = {
        regulationsId: null,
        regulationsTitle: null,
        useScope: null,
        uploadDate: null,
        effectiveDate: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        createDate: null,
        uploadUsername: null,
        useState: null,
        departmentCategory: null,
        fileTag: null,
        oldRegulationsId: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        newRegulationsId: null,
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
      this.ids = selection.map(item => item.regulationsId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 删除按钮操作 */
    /** 删除按钮操作 */
    handleDelete(row) {
      const regulationsIds = row.regulationsId || this.ids;
      this.$modal.confirm('是否确认删除？').then(function() {
        return delFilemanagement(regulationsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('file/filemanagement/export', {
        ...this.exportQueryParams,
        exportFields: this.exportList
      }, `制度文件管理_${new Date().getTime()}.xlsx`)
    },
    /** 文件下载 */
    downloadFile(url) {
      fetch(url)
        .then(response => response.blob())
        .then(blob => {
          const downloadUrl = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = downloadUrl;
          link.setAttribute('download', decodeURIComponent(url.split('/').pop())); // 解码文件名
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(downloadUrl);
        })
        .catch(error => console.error('Download error:', error));
    },
    getFileType(filePath) {
      // 获取文件名的后缀名
      const fileExtension = filePath.split('.').pop();
      console.log("fileExtension=>", fileExtension);

      // 根据文件后缀名判断文件类型
      switch (fileExtension.toLowerCase()) {
        case 'pdf':
          return 'pdf';
        case 'doc':
        case 'docx':
          return 'word';
        case 'xls':
        case 'xlsx':
          return 'Excel 文档';
        case 'ppt':
        case 'pptx':
          return 'PowerPoint 文档';
        // 可以根据需要添加更多的文件类型判断
        default:
          return '未知类型';
      }
    },
    // 调用接口获取用户信息
    getUserInfo() {
      getUserProfile02().then(response => {
        // 处理成功的情况
        console.log('成功获取用户信息:', response.data)
        const userInfo = response.data // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        // 填充到对应的输入框中
        this.form.uploadUsername = userInfo.userName
        //根据部门id获取部门名称
        getDept02(userInfo.deptId).then(response => {
          const deptInfo = response.data
          this.form.departmentCategory = deptInfo.deptName
        })
      }).catch(error => {
        // 处理失败的情况
        console.error('获取用户信息失败:', error)
      })
    },
    //文件预览
    previewFile(pdfPath) {
      if (pdfPath) {
        window.open(pdfPath, '_blank');
      } else {
        this.$modal.msgError('没有上传pdf文件，无法预览！');
      }
    },

    convertToPdfPath(wordFilePath) {
      // 找到文件路径中的最后一个点的位置
      const lastDotIndex = wordFilePath.lastIndexOf('.');

      if (lastDotIndex != -1) {
        // 获取文件路径中最后一个点之前的部分（文件名部分）
        const prefix = wordFilePath.substring(0, lastDotIndex);

        // 将文件名部分与 .pdf 后缀拼接，形成 PDF 文件路径
        const pdfFilePath = prefix + ".pdf";

        return pdfFilePath;
      } else {
        // 文件路径中没有点，无法更改后缀
        throw new IllegalArgumentException("文件路径无效：" + wordFilePath);
      }
    },

    async handleDepartmentChange(department) {
      this.formData.businessesModules = ""; // 重置上级业务模块选择
      this.modules = []; // 清空之前的模块
      if (department) {
        try {
          await listModuless(this.moduleQueryParams).then((response) => {
            this.modulesList = response.rows;
          });

          for (let i = 0; i < this.modulesList.length; i++) {
            console.log("123===" + this.modulesList[i].parentDepartment);
            // 根据部门字段进行筛选
            if (this.modulesList[i].parentDepartment === department) {
              this.modules.push(this.modulesList[i]);
            }
          }

          console.log("this.modules===", this.modules);
        } catch (error) {
          console.error("Failed to fetch modules:", error);
        }
      }
    },


    //通过 业务模块内容 限制选择: 细分业务内容
    async handleModuleChange(module) {
      console.log("module===", module);
      this.formData.subBusinesses = ""; // 重置细分业务选择
      this.subBusinesses = []; // 清空之前的细分业务
      if (module) {
        try {
          // 获取所有细分业务
          await listBusinessess(this.xifenQueryParams).then((response) => {
            this.subBusinessesList = response.rows;
          });

          for (let i = 0; i < this.subBusinessesList.length; i++) {
            console.log("12321===" + this.subBusinessesList[i]);
            // 根据业务模块字段进行筛选
            if (this.subBusinessesList[i].parentModule === module) {
              this.subBusinesses.push(this.subBusinessesList[i]);
            }
          }

          console.log("this.subBusinesses===", this.subBusinesses);
        } catch (error) {
          console.error("Failed to fetch sub-businesses:", error);
        }
      }
    },
// 使用命名空间映射actions
    ...mapActions('exportData', ['updateExportedData']),
    exportAll(){
      const loadingInstance = Loading.service({
        lock: true,
        text: "正在导出，请稍后...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });

      const promises = this.filemanagementList.map((regulation) => {
        return this.handleProjectDetails(regulation).then((projectNames) => {
          return {
            主责部门 : regulation.departmentCategory,
            制度名称 : regulation.regulationsTitle,
            专业分类 : regulation.classificationOfSpecialties,
            制度范围 : regulation.useScope,
            制度编号 : regulation.regulationNumber,
            发布日期 : regulation.createDate,
            实施日期 : regulation.effectiveDate,
            关联流程 :this.projectNamesString,
            状态 :regulation.revisionContent,
            最新上传日期 : regulation.uploadDate,
          };
        });
      });
      Promise.all(promises)
        .then((data) => {
          const ws = XLSX.utils.json_to_sheet(data);
          const wb = XLSX.utils.book_new();
          XLSX.utils.book_append_sheet(wb, ws, "项目列表");

          const wbout = XLSX.write(wb, { bookType: "xlsx", type: "array" });
          saveAs(
            new Blob([wbout], { type: "application/octet-stream" }),
            "制度总台账.xlsx"
          );

          // 提交数据到Vuex Store
          this.updateExportedData(data);


        })
        .finally(() => {
          loadingInstance.close();
        })
        .catch((error) => {
          console.error("导出失败:", error);
          loadingInstance.close();
        });

    },
    /** 还原操作 */
    handlerestore(row) {
      this.$confirm('是否确认还原数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        row.newFlag = 1;
        row.revisionContent = "新增";
      }).then(() => {
        //还原
        updateFilemanagement(row);
        //历史版本
        if(row.oldRegulationsId) {
          getFilemanagement(row.oldRegulationsId).then(response => {
            const  thisForm = response.data;
            thisForm.newFlag = 0;
            thisForm.revisionContent = "更新";
            updateFilemanagement(thisForm);
          })
        }
      }).then(() => {
        this.$modal.msgSuccess("还原成功");
      }).then(() => {
        this.getList();
      }).catch(function() {});
    },
    /** 查询部门列表 */
    getDeptList() {
      listDept02().then((response) => {
        console.log("response======>",response);
        // 过滤掉 deptName 为 "产品研发"、"研发"、"测试" 和 "总部" 的部门
        const filteredData = response.data.filter(
          (department) =>
            department.deptName !== "产品研发" &&
            department.deptName !== "研发" &&
            department.deptName !== "测试" &&
            department.deptName !== "总部" &&
            department.deptName !== "合力（盘锦）"
        );

        // 将每个过滤后的部门的 deptName 放入 departments 数组
        this.departments = filteredData.map(
          (department) => department.deptName
        );
        console.log("this.departments======>",this.departments);
      });
    },
  }
};
</script>

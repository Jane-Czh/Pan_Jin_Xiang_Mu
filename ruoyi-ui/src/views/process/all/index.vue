<template>
  <div class="app-container">
    <!-- 查看流程数据详情-->
    <show-panel ref="ShowPanel" v-if="showPanelVisible"></show-panel>
    <!-- 编辑流程数据详情  -->
    <edit-panel ref="EditPanel" v-if="editPanelVisible"></edit-panel>

    <!-- 搜索 -->
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="70px"
    >
      <!-- 123 -->
      <!-- 1\部门 -->
      <el-form-item label="部门" required="true">
        <el-select
          v-model="queryParams.department"
          placeholder="请选择部门"
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
      <el-form-item label="业务模块">
        <el-select
          v-model="queryParams.businessesModules"
          placeholder="请选择业务模块"
          clearable
          :disabled="!queryParams.department"
          @change="handleModuleChange"
        >
          <el-option
            v-for="item in modules"
            :key="item.bm_id"
            :label="item.moduleName"
            :value="item.moduleName"
          />
        </el-select>
      </el-form-item>

      <!-- 3. 可选, 从已有的细分业务中进行选择 ; 当上级业务模块被选了, 就只能从对应的细分业务中进行选择 -->
      <el-form-item label="细分业务">
        <el-select
          v-model="queryParams.subBusinesses"
          placeholder="请选择细分业务"
          clearable
          :disabled="!queryParams.businessesModules"
        >
          <el-option
            v-for="item in subBusinesses"
            :key="item.subb_id"
            :label="item.subBusinessesName"
            :value="item.subBusinessesName"
          />
        </el-select>
      </el-form-item>
      <!-- 123 over -->

      <!-- 搜索 制度文件名称进行搜索 -->
      <el-form-item prop="regularName">
        <el-input
          v-model="queryParams.regularName"
          placeholder="请输入制度文件名称"
          clearable
        />
      </el-form-item>

      <!--  搜索 制度等级进行搜索-->
      <el-form-item prop="regularLevel">
        <el-select
          v-model="queryParams.regularLevel"
          placeholder="请选择制度等级"
        >
          <el-option
            v-for="item in options1"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <!-- 搜索 表单文件名称进行搜索 -->
     <el-form-item prop="formName">
        <el-input
          v-model="queryParams.formName"
          placeholder="请输入表单文件名称"
          clearable
        />
      </el-form-item>

      <!-- 搜索 流程等级进行搜索-->
    <el-form-item prop="processLevel">
        <el-select
          v-model="queryParams.processLevel"
          placeholder="请选择流程等级"
        >
          <el-option
            v-for="item in options2"
            :key="item.value"
            :label="item.label"
            :value="item.value"
          >
          </el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery(queryParams)"
          >搜索</el-button
        >
        <el-form-item>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
            >重置</el-button
          >
        </el-form-item>
      </el-form-item>

      <el-form-item class="export-button" style="float: right">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="small"
          @click="exportAll"
          v-if="this.departmentCategory == '企业管理科'"
          >总台账导出</el-button
        >
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
          v-hasPermi="['system:project:add']"
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
          v-hasPermi="['system:project:edit']"
          >修改</el-button
        >
      </el-col> -->

      <!-- <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:project:remove']"
          >批量删除</el-button
        >
      </el-col> -->

      <!--  v-hasPermi="['system:project:export']" -->
      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"

          >导出</el-button
        >
      </el-col> -->

      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="projectList"
      stripe
      @cell-mouse-enter="enter"
    >
      <!-- <el-table-column type="selection" width="55" align="center" /> -->
      <!-- <el-table-column label="ID" align="center" prop="id" /> -->
      <el-table-column label="序号" align="center" prop="id">
        <template slot-scope="scope">
          <!-- <span>{{ scope.$index + 1 }}</span> -->
          <span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>

      <el-table-column label="部门" align="center" prop="department" />
      <el-table-column
        label="业务模块"
        align="center"
        prop="businessesModules"
      />
      <el-table-column label="细分业务" align="center" prop="subBusinesses" />

      <el-table-column label="制度" align="center" prop="regularName" />
      <el-table-column label="制度等级" align="center" prop="regularLevel" />

      <el-table-column label="流程" align="center" prop="processName" />
      <el-table-column label="流程等级" align="center" prop="processLevel" />

      <el-table-column label="表单" align="center" prop="formName" />
    </el-table>

    <!-- 分页 -->
    <el-pagination
      @size-change="sizeChangeHandle"
      @current-change="currentChangeHandle"
      :current-page="pageIndex"
      :page-sizes="[5, 10, 20, 50, 100]"
      :page-size="pageSize"
      :total="totalPage"
      layout="->, total, sizes, prev, pager, next, jumper"
    >
    </el-pagination>
  </div>
</template>

<script>
//业务模块api，
import { listModuless } from "@/api/function/modules";
//细分业务api
import { listBusinessess } from "@/api/function/businesses";

import {
  listProject,
  getProject,
  getProject1,
  getProject2,
  delProject,
  addProject,
  updateProject,
  getProjectByName,
  getProjectFileName,
} from "@/api/system/project";

import {
  listFilemanagement,
  listFormfilemanagement,
  listFilemanagementAll,
  listFormfilemanagementAll,
} from "@/api/system/project";

import ShowPanel from "@/views/process/ef/show_panel";
import EditPanel from "@/views/process/ef/edit_panel";
import "@/views/process/ef/button.css";
import { word2Pdf } from "@/api/file/filemanagement";
//获取用户信息-用户名
import { getUserProfile } from "@/api/system/user";
//获取用户信息-部门
import { getDept } from "@/api/system/project";
//导出总台账excel功能
import * as XLSX from "xlsx";
import { saveAs } from "file-saver";
//加载效果
import { Loading } from "element-ui";
import { listDept, getAllShowEntity } from "@/api/system/project";

import { Filemanagement } from "@/views/file/filemanagement";
import { mapState } from "vuex";

export default {
  name: "Project",
  inject: ["reload"],
  data() {
    return {
      //制度等级
      options1: [
        {
          value: "公司级",
          label: "公司级",
        },
        {
          value: "部门级",
          label: "部门级",
        },
        {
          value: "暂无",
          label: "暂无",
        },
      ],
      //流程等级
      options2: [
        {
          value: "A",
          label: "A",
        },
        {
          value: "B",
          label: "B",
        },
        {
          value: "C",
          label: "C",
        },
        {
          value: "暂无",
          label: "暂无",
        },
      ],
      //over
      exportedData: [], // 用于存储从制度页面获取的数据
      modules: [], //过滤后 业务模块 数据
      modulesList: [], //全部的 业务模块 数据
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

      // -------------------------------------
      //用户名
      uploadUsername: null,
      //所属部门
      departmentCategory: null,

      baseUrl: process.env.VUE_APP_BASE_API,

      //popover可见
      popover1Visible: false, // Popover是否可见
      popover2Visible: false, // Popover是否可见

      /** 流程管理部分对【绑定文件】的下载和预览 */
      //Restful 获取的 [制度文件]数据
      filemanagementList: [],
      // 制度文件名称, 悬浮面板的展示数据
      nodeFileNames: [],
      // 制度文件下载链接
      fileHyperLinks: [],
      //word
      wordHyperLinks: [],

      //  ----------------------------------------------

      //Restful 获取的 [表单文件]数据
      formList: [],
      // 表单文件名称, 悬浮面板的展示数据
      nodeFormNames: [],
      // 表单文件下载链接
      formHyperLinks: [],
      // <!-- ---------------------------------------------- -->

      // 制度文件中 选中数组传给 node
      idsRegulation: [],
      namesRegulation: [],
      // 从node传来的 已绑定的文件信息 x
      // ----------------------------------------------

      // 表单文件中 选中数组传给 node
      idsForm: [],
      namesForm: [],
      // 从node传来的 已绑定的文件信息 x
      // ----------------------------------------------

      // 控制查看的流程数据显示与隐藏
      showPanelVisible: false,
      // 控制可编辑的的流程数据显示与隐藏
      editPanelVisible: false,

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
      // 流程表格数据
      projectList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        regularName: null, // 制度 名称
        regularLevel: null, // 制度等级
        processName: null, // 流程名称
        processLevel: null, // 流程等级
        formName: null, // 表单名称
        department: null, //业务部门
        businessesModules: null, //业务模块
        subBusinesses: null, //细分业务
      },

      //保存输入的流程名称
      formData: {
        project_Name: "",
        //备注
        formData: "",
        //流程编号
        number: "",
        //主责部门
        department: "",
        //流程等级
        level: "",
        //流程目的
        purpose: "",
        //适用范围
        applicationScope: "",

        //业务模块
        businessesModules: "",
        //细分业务
        subBusinesses: "",
      },

      dataList: [],
      // 分页参数
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,

      dataListSelections: [],

      // 表单参数
      form: {
        project_Name: "",
        number: "",
        department: "",
        level: "",
        purpose: "",
        applicationScope: "",

        //业务模块
        businessesModules: "",
        //细分业务
        subBusinesses: "",
      },
      // departments: [
      //   "安环设备科",
      //   "财务科",
      //   "党群办公室",
      //   "供应科",
      //   "技术科",
      //   "企业管理科",
      //   "生产管理科",
      //   "市场科",
      //   "执纪监督室",
      //   "质量科",
      // ],
      departments: [],
      // 查询参数
      queryDeptParams: {
        deptName: undefined,
        status: undefined,
      },
      levels: ["A级", "B级", "C级"],
      rules: {
        name: [{ required: true, message: "请输入流程名称", trigger: "blur" }],
        number: [
          { required: true, message: "请输入流程编号", trigger: "blur" },
        ],
        department: [
          { required: true, message: "请选择主责部门", trigger: "change" },
        ],
        level: [
          { required: true, message: "请选择流程等级", trigger: "change" },
        ],
        purpose: [
          { required: true, message: "请输入流程目的", trigger: "blur" },
        ],
        applicationScope: [
          { required: true, message: "请输入适用范围", trigger: "blur" },
        ],
      },
      // 表单校验
      rules: {
        name: [
          { required: true, message: "项目名称不能为空", trigger: "blur" },
        ],
      },
    };
  },
  //组件
  components: {
    ShowPanel,
    EditPanel,
    Filemanagement,
  },

  mounted() {
    console.log("Setting up EventBus listener for exportAllResult");
    this.exportedData = this.$store.state.exportData.exportedData;
    console.log("Received exported data:", this.exportedData);
    //获取当前用户信息
    this.getList();
    this.getDeptList();
  },
  created() {},

  methods: {
    /** 查询部门列表 */
    getDeptList() {
      listDept(this.queryDeptParams).then((response) => {
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
      });
    },
    //流程信息导出
    exportAll() {
      const loadingInstance = Loading.service({
        lock: true,
        text: "正在导出，请稍后...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });

      let index = 1; // 初始化序号

      const promises = this.projectList.map((project) => {
        return {
          部门: project.department,
          序号: index++, // 自增序号
          业务模块: project.businessesModules,
          细分业务: project.subBusinesses,
          制度名称: "《" + project.regularName + "》",
          制度等级: project.regularLevel,
          流程名称: "《" + project.processName + "》",
          流程等级: project.processLevel,
          表单名称: "《" + project.formName + "》",
        };
      });

      Promise.all(promises)
        .then((data) => {
          const ws = XLSX.utils.json_to_sheet(data);
          const wb = XLSX.utils.book_new();
          XLSX.utils.book_append_sheet(wb, ws, "项目列表");

          // 设置列宽
          const wscols = [
            { wch: 15 }, // 部门
            { wch: 5 }, // 序号
            { wch: 15 }, // 业务模块
            { wch: 15 }, // 细分业务
            { wch: 15 }, // 制度名称
            { wch: 15 }, // 制度等级
            { wch: 15 }, // 流程名称
            { wch: 15 }, // 流程等级
            { wch: 15 }, // 表单名称
          ];
          ws["!cols"] = wscols;

          const wbout = XLSX.write(wb, { bookType: "xlsx", type: "array" });
          saveAs(
            new Blob([wbout], { type: "application/octet-stream" }),
            "盘锦合力制度流程表单总台账.xlsx"
          );
        })
        .finally(() => {
          loadingInstance.close();
        })
        .catch((error) => {
          console.error("导出失败:", error);
          loadingInstance.close();
        });
    },

    // 通过 department部门 限制选择:  业务模块内容 this.modules
    async handleDepartmentChange(department) {
      this.form.businessesModules = ""; // 重置上级业务模块选择
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
      this.form.subBusinesses = ""; // 重置细分业务选择
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

    // 调用接口获取用户信息  uploadUsername、departmentCategory
    async getUserInfo() {
      try {
        const response = await getUserProfile();
        const userInfo = response.data; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        console.log("成功获取用户信息=======", userInfo);
        this.uploadUsername = userInfo.userName;

        const deptResponse = await getDept(userInfo.deptId);
        const deptInfo = deptResponse.data;
        this.departmentCategory = deptInfo.deptName;
        console.log("成功获取部门信息=======", deptInfo);
      } catch (error) {
        console.error("获取用户信息失败:", error);
      }
    },

    /** 查询总台账信息列表 */
    async getList() {
      this.projectList = [];
      this.loading = true;

      await this.getUserInfo();

      console.log("this.departmentCategory===>", this.departmentCategory);
      console.log("this.uploadUsername===>", this.uploadUsername);

      getAllShowEntity().then((response) => {
        console.log("getAllShowEntity  从后端获取的 response===>", response);

        // 数据过滤和处理
        this.projectList = response.map((item) => {
          // 进行字段检查并设置默认值
          return {
            ...item,
            // 检查并设置字段值
            department: item.department || "暂无", // 假设字段名为field1
            formName: item.formName || "暂无", // 假设字段名为field2
            processLevel: item.processLevel || "暂无", // 假设字段名为field2
            processName: item.processName || "暂无", // 假设字段名为field2
            regularLevel: item.regularLevel || "暂无", // 假设字段名为field2
            regularName: item.regularName || "暂无", // 假设字段名为field2
            subBusinesses: item.subBusinesses || "暂无", // 假设字段名为field2
            // 其他字段处理
          };
        });

        // 按照updateDate字段进行排序
        // this.projectList.sort((a, b) => {
        //   // 按照updateDate字段从小到大排序
        //   return new Date(a.createDate) - new Date(b.createDate);
        // });

        // 分页功能
        this.totalPage = this.projectList.length;

        // 数据分页
        this.projectList = this.projectList.slice(
          (this.pageIndex - 1) * this.pageSize,
          this.pageIndex * this.pageSize
        );

        this.loading = false;
      });
    },

    /** 带条件的---查询总台账信息列表 */
    async getListByQueryParams(queryParams) {
      this.projectList = [];
      this.loading = true;

      await this.getUserInfo();

      console.log("this.departmentCategory===>", this.departmentCategory);
      console.log("this.uploadUsername===>", this.uploadUsername);

      getAllShowEntity().then((response) => {
        console.log("getAllShowEntity  从后端获取的 response===>", response);

        // 数据过滤和处理
        this.projectList = response
          .map((item) => {
            // 进行字段检查并设置默认值
            return {
              ...item,
              department: item.department || "暂无",
              formName: item.formName || "暂无",
              processLevel: item.processLevel || "暂无",
              processName: item.processName || "暂无",
              regularLevel: item.regularLevel || "暂无",
              regularName: item.regularName || "暂无",
              subBusinesses: item.subBusinesses || "暂无",
              // 其他字段处理
            };
          })
          .filter((item) => {
            // 根据查询参数进行过滤
            return (
              (!queryParams.regularName ||
                item.regularName.includes(queryParams.regularName)) &&
              (!queryParams.regularLevel ||
                item.regularLevel.includes(queryParams.regularLevel)) &&
              (!queryParams.processName ||
                item.processName.includes(queryParams.processName)) &&
              (!queryParams.processLevel ||
                item.processLevel.includes(queryParams.processLevel)) &&
              (!queryParams.formName ||
                item.formName.includes(queryParams.formName)) &&
              (!queryParams.department ||
                item.department.includes(queryParams.department)) &&
              (!queryParams.businessesModules ||
                item.businessesModules.includes(
                  queryParams.businessesModules
                )) &&
              (!queryParams.subBusinesses ||
                item.subBusinesses.includes(queryParams.subBusinesses))
            );
          });

        // 分页功能
        this.totalPage = this.projectList.length;

        // 数据分页
        this.projectList = this.projectList.slice(
          (this.pageIndex - 1) * this.pageSize,
          this.pageIndex * this.pageSize
        );

        this.loading = false;
      });
    },

    /** 文件下载 */
    downloadFile(url) {
      fetch(url)
        .then((response) => response.blob())
        .then((blob) => {
          const downloadUrl = window.URL.createObjectURL(blob);
          const link = document.createElement("a");
          link.href = downloadUrl;
          link.setAttribute(
            "download",
            decodeURIComponent(url.split("/").pop())
          ); // 解码文件名
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(downloadUrl);
        })
        .catch((error) => console.error("Download error:", error));
    },

    /**搜索功能1、按照project name进行搜索 */

    /**根据project的state(制度文件ids)、type(表单文件ids)查找filenames */
    // 1.1 查询制度文件列表
    getRegularFileData(row) {
      //存储制度文件名称
      this.nodeFileNames = [];
      //存储相应的下载地址--pdf
      this.fileHyperLinks = [];
      // -- word
      this.wordHyperLinks = [];

      console.log("1.1 查询制度文件列表 row=====>", row);
      listFilemanagementAll(this.queryParams).then((response) => {
        this.filemanagementList = response.rows;
      });
      //对 filemanagementList 查找符合state中ids的文件 将其放入 nodeFileNames
      JSON.parse(row.state).forEach((stateId) => {
        let row = this.filemanagementList.find(
          (item) =>
            JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
        );
        if (row != null) {
          //将匹配的记录的文件名、链接保存 (nodeFileNames )
          this.nodeFileNames.push(row.fileName);
          // this.fileHyperLinks.push(row.filePath); 原来是word和pdf分开管理的
          this.fileHyperLinks.push(row.pdfPath);
          this.wordHyperLinks.push(row.wordPath);
        }
      });
      // console.log("this.nodeFileNames===>", this.nodeFileNames);
      // console.log("this.fileHyperLinks===>", this.fileHyperLinks);
    },

    // 1.2 查询表单文件列表
    getFormFileData(row) {
      //存储表单文件名称
      this.nodeFormNames = [];
      //存储相应的下载地址
      this.formHyperLinks = [];
      console.log("1.1 查询表单文件列表 row=====>", row);

      listFormfilemanagementAll(this.queryParams).then((response) => {
        this.formList = response.rows;
        console.log("this.formList===>", this.formList);
      });

      //对 formList 查找符合type中ids的文件 将其放入 nodeFormNames
      JSON.parse(row.type).forEach((stateId) => {
        let row = this.formList.find(
          (item) => JSON.stringify(item.formId) === JSON.stringify(stateId)
        );
        if (row != null) {
          //将匹配的记录的文件名、链接保存 (nodeFileNames )
          this.nodeFormNames.push(row.formName);
          this.formHyperLinks.push(row.formPath);
        }
      });
    },

    // -------------------------------------------------------------------------
    // 鼠标进入表格 当前行 的调用函数 获取要展示的文件(制度/表单)的数据
    // enter(row, column, cell) {
    //   // console.log("当前的 row=========>",row)
    //   this.getRegularFileData(row);
    //   this.getFormFileData(row);
    // },
    // leave(row, column, cell) {
    //   // this.$refs["popover" + row.rowIndex].showPopper = false;
    // },

    //文件预览部分

    previewFile(filePath) {
      const fileType = this.getFileType(filePath);
      console.log("filePath:", filePath);
      console.log("fileType:", fileType);
      switch (fileType) {
        case "pdf":
          console.log("fileType1111:", fileType);
          window.open(filePath, "_blank");
          break;
        case "word":
          const pdfFilePath = this.convertToPdfPath(filePath);
          console.log("filePath:", filePath);
          console.log("pdfFilePath:", pdfFilePath);
          word2Pdf(filePath, pdfFilePath).then((response) => {});
          window.open(pdfFilePath, "_blank");
          break;
      }
      // 使用 window.open 方法打开一个新窗口，并将文件路径传递给该窗口
    },

    getFileType(filePath) {
      // 获取文件名的后缀名
      const fileExtension = filePath.split(".").pop();
      console.log("fileExtension=>", fileExtension);

      // 根据文件后缀名判断文件类型
      switch (fileExtension.toLowerCase()) {
        case "pdf":
          return "pdf";
        case "doc":
        case "docx":
          return "word";
        case "xls":
        case "xlsx":
          return "Excel 文档";
        case "ppt":
        case "pptx":
          return "PowerPoint 文档";
        // 可以根据需要添加更多的文件类型判断
        default:
          return "未知类型";
      }
    },

    convertToPdfPath(wordFilePath) {
      // 找到文件路径中的最后一个点的位置
      const lastDotIndex = wordFilePath.lastIndexOf(".");

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

    // -------------------------------------------------------------------------

    //可编辑流程
    edit(row) {
      //加载流程面板可见
      this.editPanelVisible = true;
      //getProject 从后端读取数据
      getProject1(row.id).then((response) => {
        // 获取show_panel.vue组件的实例 this.$refs.ShowPanel, 调用方法dataReload()
        this.$refs.EditPanel.dataReload(
          response.data,
          row.id,
          row.state,
          row.type
        );
      });
      if (this.easyFlowVisible) {
        this.reload();
      }
    },

    //查看流程
    view(row) {
      //加载流程面板可见
      this.showPanelVisible = true;
      //getProject 从后端读取数据
      getProject2(row.id).then((response) => {
        // 获取show_panel.vue组件的实例 this.$refs.ShowPanel, 调用方法dataReload()
        this.$refs.ShowPanel.dataReload(response.data);
      });
    },

    /** 搜索流程by name */
    searchByName() {
      this.projectList = [];

      this.loading = true;
      getProjectByName(this.queryParams).then((response) => {
        // console.log("manage/index从后端获取的response===>", response);

        
        // for (var i = 0; i < response.length; i++) {
        //   this.projectList.push(response[i]);
        // }
        for (var i = 0; i < response.length; i++) {
          console.log("response[i].createBy===>", response[i].createBy);

          if (
            this.departmentCategory == response[i].createBy.split("/")[1] ||
            this.departmentCategory == "研发" ||
            this.departmentCategory == "总部"
          ) {
            this.projectList.push(response[i]);
          }
        }

        // // 按照updateDate字段进行排序
        // this.projectList.sort((a, b) => {
        //   // 按照updateDate字段从小到大排序
        //   return new Date(a.createDate) - new Date(b.createDate);
        // });

        //分页功能
        this.totalPage = response.length;

        //数据分页
        this.projectList = this.projectList.slice(
          (this.pageIndex - 1) * this.pageSize,
          this.pageIndex * this.pageSize
        );

        this.loading = false;
      });
    },
    /** 搜索流程by 制度文件名称 */
    searchFileName() {
      this.projectList = [];

      this.loading = true;
      getProjectFileName(this.queryParams).then((response) => {
        // console.log("manage/index从后端获取的response===>", response);
        // for (var i = 0; i < response.length; i++) {
        //   this.projectList.push(response[i]);
        // }

        for (var i = 0; i < response.length; i++) {
          console.log("response[i].createBy===>", response[i].createBy);

          if (
            this.departmentCategory == response[i].createBy.split("/")[1] ||
            this.departmentCategory == "研发" ||
            this.departmentCategory == "总部"
          ) {
            this.projectList.push(response[i]);
          }
        }

        // // 按照updateDate字段进行排序
        // this.projectList.sort((a, b) => {
        //   // 按照updateDate字段从小到大排序
        //   return new Date(a.createDate) - new Date(b.createDate);
        // });

        //分页功能
        this.totalPage = response.length;

        //数据分页
        this.projectList = this.projectList.slice(
          (this.pageIndex - 1) * this.pageSize,
          this.pageIndex * this.pageSize
        );

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
        id: null,
        name: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    // handleQuery() {
    //   this.queryParams.pageNum = 1;
    //   // this.getList();
    //   this.searchByName();
    // },

    handleQuery(queryParams) {
      this.getListByQueryParams(queryParams);
    },

    /** 根据制度名称进行搜索 */
    handleQueryFile() {
      this.queryParams.pageNum = 1;
      // this.getList();
      this.searchFileName();
    },

    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.getList();
      // this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.id);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加流程";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids;
      //先获取流程原始的名称进行显示
      getProject(id).then((response) => {
        this.form = response.data;
        console.log("response.data;===+++" + response.data);
        this.open = true;
        this.title = "修改流程名称";
      });
    },

    /** 修改流程的提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            // console.log("this.form=============>"+this.form.id + " " + this.form.name);

            // 如果存在同名的项目流程，则提示【this.form.name】 已存在
            const projectExists = this.projectList.find(
              (project) => project.name === this.form.name
            );
            // if (projectExists) {
            // this.$message({
            //   type: "warning",
            //   message: `名称为 ${this.form.name} 的流程已存在!!!`,
            // });
            // return;
            // } else {
            //进行流程名称修改的else
            updateProject(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.reload();
            });
            // } //else--over
          } else {
            addProject(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 单个删除操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm(
          `是否确认删除流程[ ${row.name} ]？这会删除与此流程相关的所有历史流程！`
        )
        .then(() => {
          // 只有当用户确认删除时才执行删除操作
          return delProject(ids);
        })
        .then(() => {
          // 处理成功的删除操作
          // this.getList();
          this.reload();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {
          // 处理用户取消操作或者任何删除过程中出现的错误
        });
    },

    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getList();
    },

    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getList();
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/project/export",
        {
          ...this.queryParams,
        },
        `project_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>

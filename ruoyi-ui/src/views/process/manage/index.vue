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
      label-width="40px"
    >
      <!-- 搜索 流程名称进行搜索 -->
      <el-form-item label="流程标题" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入流程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- 部门 进行搜索  -->
      <el-form-item label="主责部门" prop="department">
        <el-select
          v-model="queryParams.department"
          placeholder="请选择主责部门"
          clearable
        >
          <el-option
            v-for="item in departments"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <!-- 等级 进行搜索 -->
      <el-form-item label="流程等级" prop="level">
        <el-select
          v-model="queryParams.level"
          placeholder="请选择流程等级"
          clearable
        >
          <el-option
            v-for="level in levels"
            :key="level"
            :label="level"
            :value="level"
          ></el-option>
        </el-select>
      </el-form-item>

      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
      </el-form-item>

      <!-- 搜索 制度文件名称进行搜索 -->
      <el-form-item label="或" prop="filename">
        <el-input
          v-model="queryParams.filename"
          placeholder="请输入制度文件名称"
          clearable
          @keyup.enter.native="handleQueryFile"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQueryFile"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>

      <el-form-item class="export-button" style="float: right">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="small"
          @click="exportAll"
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
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="ID" align="center" prop="id" /> -->
      <el-table-column label="序号" align="center" prop="id">
        <template slot-scope="scope">
          <!-- <span>{{ scope.$index + 1 }}</span> -->
          <span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>

      <el-table-column label="主责部门" align="center" prop="department" />

      <el-table-column label="流程名称" align="center" prop="name" />

      <el-table-column
        label="业务模块"
        align="center"
        prop="businessesModules"
      />
      <el-table-column label="细分业务" align="center" prop="subBusinesses" />

      <el-table-column label="流程等级" align="center" prop="level" />

      <el-table-column label="流程编号" align="center" prop="number" />

      <el-table-column label="支撑文件" align="center">
        <template slot-scope="scope">
          <!-- 111111111111制度文件 -->
          <el-tooltip
            class="item"
            effect="dark"
            content="制度文件,点击查看详情！"
            placement="top"
          >
            <!-- popover：1、制度文件显示 -->
            <el-popover
              placement="bottom"
              title="绑定的制度文件"
              trigger="click"
            >
              <template slot="reference">
                <span class="file">
                  <i class="el-icon-files"></i>
                </span>
              </template>
              <!-- slot插槽展示自定义内容 -->
              <div v-if="nodeFileNames.length != 0">
                <ul>
                  <li v-for="(file, index) in nodeFileNames" :key="index">
                    {{ file }}
                    <el-divider direction="vertical"></el-divider>
                    <!-- 1、下载文件 pdf -->
                    <i class="el-icon-download download-icon">
                      <!-- <a :href="baseUrl + fileHyperLinks[index]" download
                        >点击下载</a
                      > -->

                      <a @click.prevent="downloadFile(fileHyperLinks[index])"
                        >点击下载pdf</a
                      >
                    </i>
                    <el-divider direction="vertical"></el-divider>
                    <!-- 2、word  -->
                    <i class="el-icon-download download-icon">
                      <a @click.prevent="downloadFile(wordHyperLinks[index])"
                        >点击下载word</a
                      >
                    </i>
                    <el-divider direction="vertical"></el-divider>
                    <!-- 预览文件 -->
                    <i
                      class="el-icon-view preview-icon"
                      @click="previewFile(fileHyperLinks[index])"
                    >
                      <a href="#">预览</a>
                    </i>
                  </li>
                </ul>
              </div>
              <div v-else>"无绑定"</div>
              <!-- slot插槽over -->
            </el-popover>
          </el-tooltip>

          <!-- 222222222222222表单文件  @mouseenter.native="getFormFileData(scope.row)"-->
          <el-tooltip
            style="margin-left: 10px"
            class="item"
            effect="dark"
            content="表单文件,点击查看详情！"
            placement="top"
          >
            <!-- popover：2、制度文件显示 -->
            <el-popover
              placement="bottom"
              title="绑定的表单文件"
              trigger="click"
            >
              <!-- slot插槽展示自定义内容 -->
              <template slot="reference">
                <span class="file">
                  <i class="el-icon-tickets"></i>
                </span>
              </template>
              <!-- slot插槽展示自定义内容 -->
              <div v-if="nodeFormNames.length != 0">
                <ul>
                  <li v-for="(file, index) in nodeFormNames" :key="index">
                    {{ file }}
                    <el-divider direction="vertical"></el-divider>
                    <!-- 下载文件  -->
                    <i class="el-icon-download download-icon">
                      <!-- <a :href="baseUrl + formHyperLinks[index]" download
                        >点击下载</a
                      > -->
                      <a @click.prevent="downloadFile(formHyperLinks[index])"
                        >点击下载</a
                      >
                    </i>
                    <el-divider direction="vertical"></el-divider>
                    <!-- 预览文件 -->
                    <i
                      class="el-icon-view preview-icon"
                      @click="previewFile(formHyperLinks[index])"
                    >
                      <a href="#"> 预览</a>
                    </i>
                  </li>
                </ul>
              </div>
              <div v-else>"无绑定"</div>
              <!-- slot插槽over -->
            </el-popover>
          </el-tooltip>
        </template>
      </el-table-column>

      <!-- <el-table-column label="${comment}" align="center" prop="json" /> -->
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column
        label="创建时间"
        align="center"
        prop="createDate"
        width="180"
      >
        <template slot-scope="scope">
          <i class="el-icon-time"></i>
          <span>{{
            parseTime(scope.row.createDate, "{y}-{m}-{d}  {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>

      <el-table-column label="更新人" align="center" prop="updateBy" />
      <el-table-column
        label="更新时间"
        align="center"
        prop="updateDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{
            parseTime(scope.row.updateDate, "{y}-{m}-{d}  {h}:{i}:{s}")
          }}</span>
        </template>
      </el-table-column>

      <!-- 0731新增字段 -->

      <el-table-column label="流程目的" align="center" prop="purpose" />

      <el-table-column
        label="适用范围"
        align="center"
        prop="applicationScope"
      />

      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            type="primary"
            size="small"
            plain
            round
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:project:edit']"
            >修改</el-button
          >
          <!-- <el-button type="primary" icon="el-icon-edit" circle></el-button> -->

          <el-button
            type="success"
            size="small"
            plain
            round
            icon="el-icon-edit-outline"
            @click="edit(scope.row)"
            v-hasPermi="['system:project:update']"
          >
            更新</el-button
          >

          <br />

          <el-button
            type="info"
            size="small"
            plain
            round
            icon="el-icon-document"
            @click="view(scope.row)"
            v-hasPermi="['system:project:view']"
          >
            查看</el-button
          >

          <el-button
            type="danger"
            size="small"
            plain
            round
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:project:delete']"
            >删除</el-button
          >
        </template>
      </el-table-column>
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

    <!-- 修改流程基本信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <!-- <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="流程名称" prop="name">
          <el-input
            v-model="form.name"
            placeholder="输入流程名称"
            @input="validateSB"
            maxlength="20"
            show-word-limit
          />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div> -->

      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="流程名称" prop="name">
          <el-input
            v-model="form.name"
            placeholder="输入流程名称"
            @input="validateSB1"
            maxlength="20"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="流程编号" prop="number">
          <el-input
            v-model="form.number"
            placeholder="输入流程编号"
            @input="validateNumber"
            maxlength="20"
            show-word-limit
          />
        </el-form-item>

        <el-form-item label="主责部门" prop="department">
          <el-select
            v-model="form.department"
            placeholder="请选择主责部门"
            @change="handleDepartmentChange"
          >
            <el-option
              v-for="item in departments"
              :key="item"
              :label="item"
              :value="item"
            ></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="流程等级" prop="level">
          <el-select v-model="form.level" placeholder="请选择流程等级">
            <el-option
              v-for="level in levels"
              :key="level"
              :label="level"
              :value="level"
            ></el-option>
          </el-select>
        </el-form-item>

        <!-- 0801 0810 -->
        <el-form-item label="业务模块" prop="businessesModules">
          <el-select
            v-model="form.businessesModules"
            placeholder="请选择业务模块"
            clearable
            @change="handleModuleChange"
            :disabled="!form.department"
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
        <el-form-item label="细分业务" prop="subBusinesses">
          <el-select
            v-model="form.subBusinesses"
            placeholder="请选择细分业务"
            clearable
            :disabled="!form.businessesModules"
          >
            <el-option
              v-for="item in subBusinesses"
              :key="item.subb_id"
              :label="item.subBusinessesName"
              :value="item.subBusinessesName"
            />
          </el-select>
        </el-form-item>

        <!-- ---------------------------------------------------------------------------------- -->

        <el-form-item label="流程目的" prop="purpose">
          <el-input
            v-model="form.purpose"
            placeholder="输入流程目的"
            @input="validatePurpose"
            maxlength="20"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="适用范围" prop="applicationScope">
          <el-input
            v-model="form.applicationScope"
            placeholder="输入适用范围"
            @input="validateApplicationScope"
            maxlength="20"
            show-word-limit
          />
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

export default {
  name: "Project",
  inject: ["reload"],
  data() {
    return {
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
        name: null,
        filename: null,
        department: null,
        level: null,
        businessesModules: null,
        subBusinesses: null,
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
      departments: [
        "安环设备科",
        "财务科",
        "党群办公室",
        "供应科",
        "技术科",
        "企业管理科",
        "生产管理科",
        "市场科",
        "执纪监督室",
        "质量科",
      ],
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
  },

  mounted() {
    //获取当前用户信息
    this.getList();
  },
  created() {},

  methods: {
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
        return this.getFileNamesByIds(project).then((fileNames) => {
          console.log("fileNames:", fileNames); // 调试输出
          return {
            部门: project.department,
            序号: index++, // 自增序号
            业务模块: project.businessesModules,
            细分业务: project.subBusinesses,
            制度名称: "《" + fileNames.selectedFileNames + "》",
            制度等级: fileNames.selectedFileLeval,
            流程名称:  "《" + project.name + "》",
            流程等级: project.level,
            表单名称: "《" + fileNames.selectedFormsNames + "》",
          };
        });
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
            "流程总台账.xlsx"
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
    getFileNamesByIds(project) {
      return new Promise((resolve, reject) => {
        // 初始化
        this.selectedFileNames = [];
        this.selectedFormsNames = [];
        let selectedFileLeval = [];

        // 制度文件
        listFilemanagement(this.queryParams)
          .then((response) => {
            this.filemanagementList = response.rows;
            console.log(
              "今天是0801，filemanagementList:",
              this.filemanagementList
            ); // 调试输出
          })
          .then(() => {
            if (project.state && project.state !== "no") {
              const stateIds = Array.isArray(JSON.parse(project.state))
                ? JSON.parse(project.state)
                : [project.state];
              stateIds.forEach((stateId) => {
                let row = this.filemanagementList.find(
                  (item) =>
                    JSON.stringify(item.regulationsId) ===
                    JSON.stringify(stateId)
                );
                if (row != null) {
                  this.selectedFileNames.push(row.fileName);
                  selectedFileLeval.push(row.regulationLeval); // 获取 regulationLeval 属性
                }
              });
            }
            console.log("0801 selectedFileNames:", this.selectedFileNames); // 调试输出
            console.log("0801 selectedFileLeval:", selectedFileLeval); // 调试输出
          })
          .then(() => {
            // 表单文件
            return listFormfilemanagement(this.queryParams);
          })
          .then((response) => {
            this.formmanagementList = response.rows;
            console.log(
              "表单也是0801 formmanagementList:",
              this.formmanagementList
            ); // 调试输出
          })
          .then(() => {
            if (project.type && project.type !== "no") {
              const typeIds = Array.isArray(JSON.parse(project.type))
                ? JSON.parse(project.type)
                : [project.type];
              typeIds.forEach((typeId) => {
                let row = this.formmanagementList.find(
                  (item) =>
                    JSON.stringify(item.formId) === JSON.stringify(typeId)
                );
                if (row != null) {
                  this.selectedFormsNames.push(row.formName);
                }
              });
            }
            console.log(
              "是啊0801111selectedFormsNames:",
              this.selectedFormsNames
            ); // 调试输出
          })
          .then(() => {
            resolve({
              selectedFileNames: this.selectedFileNames.join(", "),
              selectedFormsNames: this.selectedFormsNames.join(", "),
              selectedFileLeval: selectedFileLeval.join(", "), // 返回 selectedFileLeval 字段
            });
          })
          .catch((error) => {
            reject(error);
          });
      });
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

    /** 查询流程列表 */
    async getList() {
      this.projectList = [];
      this.loading = true;

      await this.getUserInfo();

      console.log("this.departmentCategory===>", this.departmentCategory);
      console.log("this.uploadUsername===>", this.uploadUsername);

      listProject(this.queryParams).then((response) => {
        console.log("manage/index从后端获取的response===>", response);
        /**0727 对获取的数据进行初步过滤 只能由创建的部门才能才看到对应的数据 departmentCategory*/
        //从projectList 获取 createBy 属性 , 与departmentCategory进行对比
        //createBy : "admin/研发" 比对 departmentCategory: "研发"
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

        // 不做过滤
        // for (var i = 0; i < response.length; i++) {
        //   this.projectList.push(response[i]);
        // }

        // 按照updateDate字段进行排序
        this.projectList.sort((a, b) => {
          // 按照updateDate字段从小到大排序
          return new Date(a.createDate) - new Date(b.createDate);
        });

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

    validateSB() {
      const regex = /^[\u4e00-\u9fa5\dA-Za-z.\(\)\-（）]*$/;
      this.formData.project_Name = this.formData.project_Name
        .split("")
        .filter((char) => regex.test(char))
        .join("");
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
      listFilemanagement(this.queryParams).then((response) => {
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

      listFormfilemanagement(this.queryParams).then((response) => {
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
    enter(row, column, cell) {
      // console.log("当前的 row=========>",row)
      this.getRegularFileData(row);
      this.getFormFileData(row);
    },
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
        for (var i = 0; i < response.length; i++) {
          this.projectList.push(response[i]);
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
        for (var i = 0; i < response.length; i++) {
          this.projectList.push(response[i]);
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
    handleQuery() {
      this.queryParams.pageNum = 1;
      // this.getList();
      this.searchByName();
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

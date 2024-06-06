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
      label-width="68px"
      class="query-form"
    >
      <el-form-item label="流程名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入流程名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
      </el-form-item>

      <el-form-item class="export-button">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportAll"
          >总台账导出</el-button
        >
      </el-form-item>
    </el-form>

    <!-- 记录流程变更展示数据面板 -->
    <el-row v-for="(row, index) in this.rowList" :key="index" :gutter="20">
      <el-col v-for="item in row" :key="item.id" :span="6">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>流程：{{ item.name }}</span>
          </div>

          <div>
            <el-button type="text" @click="toDetail(item)"
              >流程{{ item.name }}的变更次数统计</el-button
            >
          </div>
          <!-- 搞一个button、点击跳转页面展示数据 -->
          <!-- <el-popover placement="right" trigger="click" width="750"> -->

          <!-- </el-popover> -->
        </el-card>
      </el-col>
    </el-row>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改流程对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="流程名称" prop="name">
          <el-input v-model="form.name" placeholder="输入流程名称" />
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
import {
  listProject,
  getProject,
  delProject,
  addProject,
  updateProject,
  getProjectByName,
} from "@/api/system/project";
// 制度文件api
import { listFilemanagement, word2Pdf } from "@/api/file/filemanagement";
// 表单文件api
import { listFormfilemanagement } from "@/api/file/formfilemanagement";

import ShowPanel from "@/views/process/ef/show_panel";
import EditPanel from "@/views/process/ef/edit_panel";
import "@/views/process/ef/button.css";
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
      // 流程表格数据--布局分组数据
      rowList: [],

      //excel 导出
      queryParams: {},
      // filemanagementList: [],
      formmanagementList: [],
      selectedFileNames: [],
      selectedFormsNames: [],

      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        name: null,
      },
      // 分页参数
      dataList: [],
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      dataListSelections: [],

      // 表单参数
      form: {},
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

  created() {
    //获取newest==1的流程数据
    this.getList();
  },

  methods: {
    getFileNamesByIds(project) {
      return new Promise((resolve, reject) => {
        // 初始化
        this.selectedFileNames = [];
        this.selectedFormsNames = [];

        // 制度文件
        listFilemanagement(this.queryParams)
          .then((response) => {
            this.filemanagementList = response.rows;
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
                }
              });
            }
          })
          .then(() => {
            // 表单文件
            return listFormfilemanagement(this.queryParams);
          })
          .then((response) => {
            this.formmanagementList = response.rows;
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
          })
          .then(() => {
            resolve({
              selectedFileNames: this.selectedFileNames.join(", "),
              selectedFormsNames: this.selectedFormsNames.join(", "),
            });
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    //file字段的格式转换
    formattedContent(content) {
      if (content) {
        // 确认替换前内容
        console.log("Original content:", content);
        const formatted = content.replace(/\\+/g, "\n");
        // 确认替换后内容
        console.log("Formatted content:", formatted);
        return formatted;
      }
      return "无更新内容！";
    },
    //导出总台账 excel
    exportAll() {
      const loadingInstance = Loading.service({
        lock: true,
        text: "正在导出，请稍后...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });

      const promises = this.projectList.map((project) => {
        return this.getFileNamesByIds(project).then((fileNames) => {
          return {
            流程名称: project.name,
           
            创建日期: project.createDate,
            创建人: project.createBy,
            更新日期: project.updateDate,
            更新人: project.updateBy,
            // 流程绑定的制度文件: fileNames.selectedFileNames,
            // 流程绑定的表单文件: fileNames.selectedFormsNames,
            // 节点绑定的文件s???

            最近一次更新内容描述: this.formattedContent(project.file),
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

    //-----------------------------------------------------------------------
    //路由跳转--转到数据展示部分
    toDetail(item) {
      //item 是当前的最新版本流程
      // console.log("item========>",item)
      //设置id为每个流程都单独指定一个统计数据的页面
      let id = item.id;
      this.$router.push("/process/statistics/indicators/" + id);
    },
 //-----------------------------------------------------------------------
  
    /** 查询流程列表 */
    getList() {
      this.projectList = [];
      this.rowList = [];

      this.loading = true;
      listProject(this.queryParams).then((response) => {
        // console.log("manage/index从后端获取的response===>", response);
        for (var i = 0; i < response.length; i++) {
          this.projectList.push(response[i]);
        }

        // 按照updateDate字段进行排序
        this.projectList.sort((a, b) => {
          // 按照updateDate字段从小到大排序
          return new Date(a.createDate) - new Date(b.createDate);
        });

        //TODO 分页功能、分科室搜索功能等
        // this.total = response.total;

        //el-card做分组布局
        for (let i = 0; i < this.projectList.length; i += 4) {
          // 4表示每行4条
          this.rowList.push(this.projectList.slice(i, i + 4));
        }

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

    /** 搜索流程by name */
    searchByName() {
      this.projectList = [];
      this.rowList = [];

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
        // 按照updateDate字段进行排序
        this.projectList.sort((a, b) => {
          // 按照updateDate字段从小到大排序
          return new Date(a.createDate) - new Date(b.createDate);
        });

        //TODO 分页功能、分科室搜索功能等
        // this.total = response.total;

        //el-card做分组布局
        for (let i = 0; i < this.projectList.length; i += 4) {
          // 4表示每行4条
          this.rowList.push(this.projectList.slice(i, i + 4));
        }

        this.loading = false;
      });
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      // this.getList();
      this.searchByName();
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
      getProject(id).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改流程名称";
      });
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

<style>

.query-form {
  display: flex;
  align-items: center;
}

.query-form .el-form-item.export-button {
  margin-left: auto;
}

</style>
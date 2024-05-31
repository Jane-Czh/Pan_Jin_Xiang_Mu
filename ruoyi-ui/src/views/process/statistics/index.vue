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
import { listFilemanagement } from "@/api/file/filemanagement";
import ShowPanel from "@/views/process/ef/show_panel";
import EditPanel from "@/views/process/ef/edit_panel";
import "@/views/process/ef/button.css";
import { word2Pdf } from "@/api/file/filemanagement";

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
    //获取数据 --> 改为获取当前流程的全部历史数据
    this.getList();
  },

  methods: {
    //路由跳转--转到数据展示部分
    toDetail(item) {
      //item 是当前的最新版本流程
      // console.log("item========>",item)
      //设置id为每个流程都单独指定一个统计数据的页面
      let id = item.id;
      this.$router.push("/process/statistics/indicators/" + id);
    },

    /**根据project的state(制度文件ids)、type(表单文件ids)查找filenames */
    // 1.1 查询制度文件列表
    getRegularFileData(row) {
      //存储制度文件名称
      this.nodeFileNames = [];
      //存储相应的下载地址
      this.fileHyperLinks = [];

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
          this.fileHyperLinks.push(row.filePath);
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

      listFilemanagement(this.queryParams).then((response) => {
        this.formList = response.rows;
      });
      //对 formList 查找符合type中ids的文件 将其放入 nodeFormNames
      JSON.parse(row.type).forEach((stateId) => {
        let row = this.formList.find(
          (item) =>
            JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
        );
        if (row != null) {
          //将匹配的记录的文件名、链接保存 (nodeFileNames )
          this.nodeFormNames.push(row.fileName);
          this.formHyperLinks.push(row.filePath);
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
      getProject(row.id).then((response) => {
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
      getProject(row.id).then((response) => {
        // 获取show_panel.vue组件的实例 this.$refs.ShowPanel, 调用方法dataReload()
        this.$refs.ShowPanel.dataReload(response.data);
      });
    },

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
    /** 修改流程的提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.id != null) {
            updateProject(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.reload();
              // this.getList();
            });
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

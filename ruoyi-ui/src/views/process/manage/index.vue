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

      <!-- <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:project:export']"
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
      <el-table-column label="流程名称" align="center" prop="name" />
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

      <el-table-column label="绑定文件" align="center">
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
                    <!-- 下载文件  -->
                    <i class="el-icon-download download-icon">
                      <a :href="baseUrl + fileHyperLinks[index]" download
                        >点击下载</a
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
                <!-- <el-button
                  type="file1"
                  size="small"
                  round
                  icon="el-icon-files"
                ></el-button> -->
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
                      <a :href="baseUrl + formHyperLinks[index]" download
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
  getProject1,
  getProject2,
  delProject,
  addProject,
  updateProject,
  getProjectByName,
} from "@/api/system/project";
import { listFilemanagement, listFormfilemanagement } from "@/api/system/project";
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

      dataList: [],
      // 分页参数
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
    //获取数据
    this.getList();
  },

  methods: {
    /**搜索功能1、按照project name进行搜索 */

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
      console.log("1.1 查询表单文件列表 row=====>", row);

      listFormfilemanagement(this.queryParams).then((response) => {
        this.formList = response.rows;
        console.log("this.formList===>", this.formList);
      });

      
      //对 formList 查找符合type中ids的文件 将其放入 nodeFormNames
      JSON.parse(row.type).forEach((stateId) => {
        let row = this.formList.find(
          (item) =>
            JSON.stringify(item.formId) === JSON.stringify(stateId)
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

    /** 查询流程列表 */
    getList() {
      this.projectList = [];

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
            if (projectExists) {
              this.$message({
                type: "warning",
                message: `名称为 ${this.form.name} 的流程已存在!!!`,
              });
              return;
            } else {//进行流程名称修改的else
              updateProject(this.form).then((response) => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.reload();
              });
            }//else--over

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

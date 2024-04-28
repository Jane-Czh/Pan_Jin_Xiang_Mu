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
      <el-form-item label="项目名称" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入项目名称"
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

    <el-table v-loading="loading" :data="projectList" stripe>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="ID" align="center" prop="id" /> -->
      <el-table-column label="序号" align="center" prop="id">
        <template slot-scope="scope">
          <span>{{ scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <el-table-column label="流程名称" align="center" prop="name" />
      <!-- <el-table-column label="${comment}" align="center" prop="json" /> -->
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
      <el-table-column label="创建人" align="center" prop="createBy" />
      <el-table-column
        label="修改时间"
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
      <el-table-column label="修改人" align="center" prop="updataBy" />

      <el-table-column
        label="绑定文件"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <!-- 制度文件显示 -->
          <el-button
            size="mini"
            type="text"
            v-hasPermi="['system:project:edit']"
          ></el-button>
          <!-- 下载图标 -->
          <el-tooltip content="下载" placement="top" v-if="scope.row.state">
            <i
              class="el-icon-download"
              style="font-size: 16px; cursor: pointer"
            ></i>
          </el-tooltip>
          <!-- 预览图标 -->
          <el-tooltip content="预览" placement="top" v-if="scope.row.state">
            <i
              class="el-icon-view"
              style="font-size: 16px; cursor: pointer"
            ></i>
          </el-tooltip>
          <!-- 无制度文件绑定 -->
          <el-tooltip content="无" placement="top" v-if="!scope.row.state">
            <i
              class="el-icon-document-delete"
              style="font-size: 16px; cursor: pointer"
            ></i>
          </el-tooltip>

          <br />
          <!-- 表单文件显示 -->
          <el-button
            size="mini"
            type="text"
            v-hasPermi="['system:project:edit']"
          ></el-button>
          <!-- 下载图标 -->
          <el-tooltip content="下载" placement="top" v-if="scope.row.type">
            <i
              class="el-icon-download"
              style="font-size: 16px; cursor: pointer"
            ></i>
          </el-tooltip>
          <!-- 预览图标 -->
          <el-tooltip content="预览" placement="top" v-if="scope.row.type">
            <i
              class="el-icon-view"
              style="font-size: 16px; cursor: pointer"
            ></i>
          </el-tooltip>
          <!-- 无表单文件绑定 -->
          <el-tooltip content="无" placement="top" v-if="!scope.row.type">
            <i
              class="el-icon-document-delete"
              style="font-size: 16px; cursor: pointer"
            ></i>
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
            type="primary"
            size="small"
            plain
            round
            icon="el-icon-edit-outline"
            @click="edit(scope.row)"
          >
            更新</el-button
          >

          <br />
          <el-button
            type="primary"
            size="small"
            plain
            round
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:project:remove']"
            >删除</el-button
          >
          <el-button
            type="primary"
            size="small"
            plain
            round
            icon="el-icon-document"
            @click="view(scope.row)"
          >
            查看</el-button
          >
        </template>
      </el-table-column>
    </el-table>

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
} from "@/api/system/project";
import { listFilemanagement } from "@/api/file/filemanagement";
import ShowPanel from "@/views/process/ef/show_panel";
import EditPanel from "@/views/process/ef/edit_panel";
import axios from "axios";

export default {
  name: "Project",
  inject: ["reload"],
  data() {
    return {
      // 制度文件数据
      filemanagementList: [],
      // 制度文件中 选中数组传给 node
      idsRegulation: [],
      namesRegulation: [],
      // 从node传来的 已绑定的文件信息 x
      // ----------------------------------------------

      // 表单文件数据
      formsmanagementList: [],
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
    //获取数据
    this.getList();
  },

  methods: {
    /**根据project的state(制度文件ids)、type(表单文件ids)查找filenames */
    /** 1.1 查询制度文件列表 */
    // getRegularFileData() {
    //   listFilemanagement(this.queryParams).then((response) => {
    //     this.filemanagementList = response.rows;
    //   });
    //   //对 filemanagementList 查找符合state的文件 将其放入 namesRegulation
    //   JSON.parse(row.state).forEach((stateId) => {
    //     let row = this.filemanagementList.find(
    //       (item) =>
    //         JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
    //     );
    //     if (row != null) {
    //       //将匹配的记录的文件名、链接保存 (namesRegulation )
    //       this.namesRegulation.push(row.fileName);
    //       // this.fileHyperLinks.push(row.filePath);
    //     }
    //   });
    // },

    // /** 1.2 查询表单文件列表 */
    // getFormFileData() {
    //   listFilemanagement(this.queryParams).then((response) => {
    //     this.formsmanagementList = response.rows;
    //   });
    //   //对 formsmanagementList 查找符合type的文件 将其放入 namesForm
    // },

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
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
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

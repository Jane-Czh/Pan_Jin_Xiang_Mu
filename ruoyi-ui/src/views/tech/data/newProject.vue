<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="序号" prop="projectId">
        <el-input v-model="queryParams.projectId" placeholder="请输入序号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input v-model="queryParams.projectName" placeholder="请输入项目名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目负责人" prop="projectLeader">
        <el-input v-model="queryParams.projectLeader" placeholder="请输入项目负责人" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目来源" prop="projectSource">
        <el-input v-model="queryParams.projectSource" placeholder="请输入项目来源" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="计划开始时间" prop="plannedStartTime">
        <el-input v-model="queryParams.plannedStartTime" placeholder="请输入计划开始时间" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="计划完成时间" prop="plannedCompletionTime">
        <el-input v-model="queryParams.plannedCompletionTime" placeholder="请输入计划完成时间" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="完成度" prop="completionRate">
        <el-input v-model="queryParams.completionRate" placeholder="请输入完成度" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="当前阶段" prop="currentPhase">
        <el-input v-model="queryParams.currentPhase" placeholder="请输入当前阶段" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="上市车型数" prop="numberOfModels">
        <el-input v-model="queryParams.numberOfModels" placeholder="请输入上市车型数" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目积分" prop="projectPoints">
        <el-input v-model="queryParams.projectPoints" placeholder="请输入项目积分" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="当前积分" prop="currentPoints">
        <el-input v-model="queryParams.currentPoints" placeholder="请输入当前积分" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
      <el-form-item label="备注" prop="Remarks">
        <el-input v-model="queryParams.Remarks" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['tech:newProject:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['tech:newProject:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['tech:newProject:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['tech:newProject:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
          v-hasPermi="['tech:newProject:import']">导入Excel文件
        </el-button>
        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类:">
              <span style="color: rgb(68, 140, 39);">项目进度表</span>
              <br>
              <el-date-picker clearable v-model="form3.yearAndMonth" type="month" value-format="yyyy-MM-dd"
                placeholder="请选择日期">
              </el-date-picker>
            </el-form-item>
          </el-form>
          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />
          <!-- 进度动画条 -->
          <!-- <div v-if="progress > 0">
            <el-progress :percentage="progress" color="rgb(19, 194, 194)"></el-progress>
          </div> -->
          <span slot="footer" class="dialog-footer">
            <el-button @click="showDialog = false">取 消</el-button>
            <el-button type="primary" @click="fileSend()" v-if="!isLoading">确 定</el-button>
            <el-button type="primary" v-if="isLoading" :loading="true">上传中</el-button>
          </span>
        </el-dialog>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-download" @click="handleDownload" size="mini" plain v-if="true">下载模版文件
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="TechNewProjectDesignList" @selection-change="handleSelectionChange" border
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="" align="center" prop="tdId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="120" fixed sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="序号" align="center" prop="projectId" width="60" />
      <el-table-column label="项目名称" align="center" prop="projectName" />
      <el-table-column label="项目负责人" align="center" prop="projectLeader" />
      <el-table-column label="项目来源" align="center" prop="projectSource" />
      <el-table-column label="项目类型" align="center" prop="projectType" />
      <el-table-column label="计划开始时间" align="center" prop="plannedStartTime" />
      <el-table-column label="计划完成时间" align="center" prop="plannedCompletionTime" />
      <el-table-column label="完成度(%)" align="center" prop="completionRate" />
      <el-table-column label="当前阶段" align="center" prop="currentPhase" />
      <el-table-column label="上市车型数" align="center" prop="numberOfModels" />
      <el-table-column label="项目积分" align="center" prop="projectPoints" />
      <el-table-column label="当前积分" align="center" prop="currentPoints" />
      <el-table-column label="备注" align="center" prop="Remarks" width="200" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width" fixed="right">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['tech:TechNewProjectDesign:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['tech:TechNewProjectDesign:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改技术-项目计划完成统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="120px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="序号" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="项目名称" prop="projectName">
          <el-input v-model="form.projectName" placeholder="请输入项目名称" />
        </el-form-item>
        <el-form-item label="项目负责人" prop="projectLeader">
          <el-input v-model="form.projectLeader" placeholder="请输入项目负责人" />
        </el-form-item>
        <el-form-item label="项目来源" prop="projectSource">
          <el-input v-model="form.projectSource" placeholder="请输入项目来源" />
        </el-form-item>
        <el-form-item label="计划开始时间" prop="plannedStartTime">
          <el-input v-model="form.plannedStartTime" placeholder="请输入计划开始时间" />
        </el-form-item>
        <el-form-item label="计划完成时间" prop="plannedCompletionTime">
          <el-input v-model="form.plannedCompletionTime" placeholder="请输入计划完成时间" />
        </el-form-item>
        <el-form-item label="完成度" prop="completionRate">
          <el-input v-model="form.completionRate" placeholder="请输入完成度" />
        </el-form-item>
        <el-form-item label="当前阶段" prop="currentPhase">
          <el-input v-model="form.currentPhase" placeholder="请输入当前阶段" />
        </el-form-item>
        <el-form-item label="上市车型数" prop="numberOfModels">
          <el-input v-model="form.numberOfModels" placeholder="请输入上市车型数" />
        </el-form-item>
        <el-form-item label="项目积分" prop="projectPoints">
          <el-input v-model="form.projectPoints" placeholder="请输入项目积分" />
        </el-form-item>
        <el-form-item label="当前积分" prop="currentPoints">
          <el-input v-model="form.currentPoints" placeholder="请输入当前积分" />
        </el-form-item>
        <el-form-item label="备注" prop="Remarks">
          <el-input v-model="form.Remarks" placeholder="请输入备注" />
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
import { listTechNewProjectDesign, getTechNewProjectDesign, delTechNewProjectDesign, addTechNewProjectDesign, updateTechNewProjectDesign } from "@/api/tech/newProject";
import { uploadFile, handleTrueDownload, checkExistProject } from '@/api/financial/excelImport';
export default {
  name: "TechNewProjectDesign",
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
      dates: [],
      // 总条数
      total: 0,
      isLoading: false,
      // 技术-项目计划完成统计表格数据
      TechNewProjectDesignList: [],
      // 弹出层标题
      title: "",
      showDialog: false,
      selectedType: '',
      form3: { yearAndMonth: null },
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        projectId: null,
        projectName: null,
        projectLeader: null,
        projectSource: null,
        projectType: null,
        plannedStartTime: null,
        plannedCompletionTime: null,
        completionRate: null,
        currentPhase: null,
        numberOfModels: null,
        projectPoints: null,
        currentPoints: null,
        Remarks: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        projectId: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        projectName: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        projectLeader: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        projectParticipants: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        establishmentState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        designPlanState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        drawingDesignState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        prototypeProductionState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        prototypeTestState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        pilotReleaseState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        massReleaseState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        completionRate: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        isCompleted: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
        prototypeReviewState: [
          {
            required: true,
            message: "不能为空",
            trigger: "blur"
          }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    handleDownload() {
      const url = "/profile/excel_templates/产品开发项目样表.xlsx"
      handleTrueDownload(url);
    },

    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.TechNewProjectDesignList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.TechNewProjectDesignList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询技术-项目计划完成统计列表 */
    getList() {
      this.loading = true;
      listTechNewProjectDesign(this.queryParams).then(response => {
        this.TechNewProjectDesignList = response.rows;
        this.total = response.total;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'yearAndMonth',
          order: 'descending' // 或'descending'
        });
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
        tdId: null,
        yearAndMonth: null,
        projectId: null,
        projectName: null,
        projectLeader: null,
        projectSource: null,
        projectType: null,
        plannedStartTime: null,
        plannedCompletionTime: null,
        completionRate: null,
        currentPhase: null,
        numberOfModels: null,
        projectPoints: null,
        currentPoints: null,
        Remarks: null,
        isCompleted: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
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
      this.ids = selection.map(item => item.tdId)
      this.dates = selection.map(item => item.yearAndMonth)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新增项目计划统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tdId = row.tdId || this.ids
      getTechNewProjectDesign(tdId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目计划统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.tdId != null) {
            updateTechNewProjectDesign(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTechNewProjectDesign(this.form).then(response => {
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
      const tdIds = row.tdId || this.ids;
      const date = row.yearAndMonth || this.dates;

      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delTechNewProjectDesign(tdIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tech/TechNewProjectDesign/export', {
        ...this.queryParams
      }, `TechNewProjectDesign_${new Date().getTime()}.xlsx`)
    },
    /** 导入按钮 */
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm" && fileExt.toLowerCase() !== "xls") {
        this.$message.error("只能上传 Excel 文件！");
        // this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },

    async fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      let yearAndMonth = this.form3.yearAndMonth;
      let startTime = {
        startTime: yearAndMonth
      };
      if (file === undefined || yearAndMonth == null) {
        if (file === undefined) {
          this.$message.error("请选择文件!");
          return;
        } else {
          this.$message.error("请选择日期!");
          return;
        }
      } else {
        let result = await checkExistProject(startTime);
        console.log(result)
        console.log('+*---------------------')
        formData.append("yearAndMonth", yearAndMonth);
        formData.append("multipartFile", file);
        const aimUrl = `/tech/data/newProject/read`
        if (result) {
          this.$modal.confirm('文件日期已存在，确定继续上传将覆盖数据').then(() => {
            return uploadFile(formData, aimUrl)
              .then(data => {
                // 处理上传成功的情况0.
                this.$message.success("上传成功");
                this.getList();
                this.showDialog = false;
                this.isLoading = false;
              })
              .catch(error => {
                // 处理上传失败的情况
                // this.$message.error("上传失败，请重试");
                this.isLoading = false;

              })
              ;
          })
        } else {
          this.isLoading = true;
          uploadFile(formData, aimUrl)
            .then(data => {
              // 处理上传成功的情况
              this.$message.success("上传成功");
              this.getList();
              this.showDialog = false;
              this.isLoading = false;
            })
            .catch(error => {
              // 处理上传失败的情况
              // this.$message.error("上传失败");
              this.isLoading = false;
            })
            ;
        }
      }
    },
  }
};
</script>

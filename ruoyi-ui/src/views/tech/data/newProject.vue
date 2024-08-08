<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="序号" prop="projectId">
        <el-input v-model="queryParams.projectId" placeholder="请输入序号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目名称" prop="projectName">
        <el-input v-model="queryParams.projectName" placeholder="请输入项目名称" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <!-- <el-form-item label="负责人" prop="projectLeader">
        <el-input v-model="queryParams.projectLeader" placeholder="请输入负责人" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="参与人" prop="projectParticipants">
        <el-input v-model="queryParams.projectParticipants" placeholder="请输入参与人" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目立项" prop="establishmentState">
        <el-input v-model="queryParams.establishmentState" placeholder="请输入项目立项" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="方案设计" prop="designPlanState">
        <el-input v-model="queryParams.designPlanState" placeholder="请输入方案设计" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="图纸设计" prop="drawingDesignState">
        <el-input v-model="queryParams.drawingDesignState" placeholder="请输入图纸设计" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="样机试制" prop="prototypeProductionState">
        <el-input v-model="queryParams.prototypeProductionState" placeholder="请输入样机试制" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="样机试验" prop="prototypeTestState">
        <el-input v-model="queryParams.prototypeTestState" placeholder="请输入样机试验" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="样机评审" prop="prototypeReviewState">
        <el-input v-model="queryParams.prototypeReviewState" placeholder="请输入样机评审" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="小批量上市" prop="pilotReleaseState">
        <el-input v-model="queryParams.pilotReleaseState" placeholder="请输入小批量上市" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="批量上市" prop="massReleaseState">
        <el-input v-model="queryParams.massReleaseState" placeholder="请输入批量上市" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目完成率" prop="completionRate">
        <el-input v-model="queryParams.completionRate" placeholder="请输入项目完成率" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="备注" prop="remarks">
        <el-input v-model="queryParams.remarks" placeholder="请输入备注" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目是否完成" prop="isCompleted">
        <el-input v-model="queryParams.isCompleted" placeholder="请输入项目是否完成" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item> -->
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

    <el-table v-loading="loading" :data="TechNewProjectDesignList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="${comment}" align="center" prop="tdId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="120">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="序号" align="center" prop="projectId" width="55" />
      <el-table-column label="项目名称" align="center" prop="projectName" width="200" />
      <el-table-column label="项目类型" align="center" prop="projectType" width="140" />
      <el-table-column label="负责人" align="center" prop="projectLeader" />
      <el-table-column label="参与人" align="center" prop="projectParticipants" width="160" />
      <el-table-column label="项目立项" align="center" prop="establishmentState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.establishmentState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.establishmentState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="方案设计" align="center" prop="designPlanState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.designPlanState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.designPlanState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="图纸设计" align="center" prop="drawingDesignState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.drawingDesignState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.drawingDesignState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="样机试制" align="center" prop="prototypeProductionState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.prototypeProductionState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.prototypeProductionState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="样机试验" align="center" prop="prototypeTestState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.prototypeTestState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.prototypeTestState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="样机评审" align="center" prop="prototypeReviewState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.prototypeReviewState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.prototypeReviewState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="小批量上市" align="center" prop="pilotReleaseState" width="100">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.pilotReleaseState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.pilotReleaseState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="批量上市" align="center" prop="massReleaseState">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.massReleaseState === '进行中' ? 'blue' : 'green' }">
            {{ scope.row.massReleaseState }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="项目完成率(%)" align="center" prop="completionRate" width="120" />
      <el-table-column label="项目是否完成" align="center" prop="isCompleted">
        <template slot-scope="scope">
          <span :style="{ color: scope.row.isCompleted === 1 ? 'green' : 'blue' }">{{ scope.row.isCompleted === 1 ?
      '完成'
      : '未完成' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remarks" width="260" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
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
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
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
        <el-form-item label="负责人" prop="projectLeader">
          <el-input v-model="form.projectLeader" placeholder="请输入负责人" />
        </el-form-item>
        <el-form-item label="参与人" prop="projectParticipants">
          <el-input v-model="form.projectParticipants" placeholder="请输入参与人" />
        </el-form-item>
        <el-form-item label="项目立项" prop="establishmentState">
          <el-select v-model="form.establishmentState" placeholder="请选择项目立项">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="方案设计" prop="designPlanState">
          <el-select v-model="form.designPlanState" placeholder="请选择方案设计">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="图纸设计" prop="drawingDesignState">
          <el-select v-model="form.drawingDesignState" placeholder="请选择图纸设计">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="样机试制" prop="prototypeProductionState">
          <el-select v-model="form.prototypeProductionState" placeholder="请选择样机试制">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="样机试验" prop="prototypeTestState">
          <el-select v-model="form.prototypeTestState" placeholder="请选择样机试验">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="样机评审" prop="prototypeReviewState">
          <el-select v-model="form.prototypeReviewState" placeholder="请选择样机评审">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="小批量上市" prop="pilotReleaseState">
          <el-select v-model="form.pilotReleaseState" placeholder="请选择小批量上市">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="批量上市" prop="massReleaseState">
          <el-select v-model="form.massReleaseState" placeholder="请选择批量上市">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="进行中" value="进行中"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="项目完成率" prop="completionRate">
          <el-input v-model="form.completionRate" placeholder="请输入项目完成率" />
        </el-form-item>
        <el-form-item label="项目是否完成" prop="isCompleted">
          <el-select v-model="form.isCompleted" placeholder="请选择项目是否完成">
            <el-option label="完成" value="完成"></el-option>
            <el-option label="未完成" value="未完成"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="备注" prop="remarks">
          <el-input v-model="form.remarks" type="textarea" placeholder="请输入备注" />
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
        projectType: null,
        projectLeader: null,
        projectParticipants: null,
        establishmentState: null,
        designPlanState: null,
        drawingDesignState: null,
        prototypeProductionState: null,
        prototypeTestState: null,
        prototypeReviewState: null,
        pilotReleaseState: null,
        massReleaseState: null,
        completionRate: null,
        remarks: null,
        isCompleted: null,
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
      const url = "/digital_operations_management_system/file/产品开发项目样表.xlsx"
      handleTrueDownload(url);
    },
    /** 查询技术-项目计划完成统计列表 */
    getList() {
      this.loading = true;
      listTechNewProjectDesign(this.queryParams).then(response => {
        this.TechNewProjectDesignList = response.rows;
        this.total = response.total;
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
        projectType: null,
        projectLeader: null,
        projectParticipants: null,
        establishmentState: null,
        designPlanState: null,
        drawingDesignState: null,
        prototypeProductionState: null,
        prototypeTestState: null,
        prototypeReviewState: null,
        pilotReleaseState: null,
        massReleaseState: null,
        completionRate: null,
        remarks: null,
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
      this.title = "新增项目计划完成统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const tdId = row.tdId || this.ids
      getTechNewProjectDesign(tdId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改项目计划完成统计";
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
      const techIds = row.tdId || this.ids;
      const date = row.yearAndMonth || this.dates;

      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delTechNewProjectDesign(techIds);
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

      if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        // this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    /** 导入按钮 */
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
              })
              .catch(error => {
                // 处理上传失败的情况
                this.$message.error("上传失败，请重试");
              })
              .finally(() => {
                // 无论成功或失败，都关闭上传面板
                this.showDialog = false;
                this.isLoading = false;
              });
          })
        } else {
          this.isLoading = true;
          uploadFile(formData, aimUrl)
            .then(data => {
              // 处理上传成功的情况
              this.$message.success("上传成功");
              this.getList();
            })
            .catch(error => {
              // 处理上传失败的情况
              this.$message.error("上传失败");
            })
            .finally(() => {
              // 无论成功或失败，都关闭上传面板
              this.showDialog = false;
              this.isLoading = false;
            });
        }
      }
    },
  }
};
</script>

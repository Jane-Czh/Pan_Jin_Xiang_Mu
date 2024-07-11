<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['quality:handFill:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['quality:handFill:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['quality:handFill:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-refresh" size="mini" @click="handleUpdateList"
          v-hasPermi="['quality:handFill:update']">更新</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="handFillList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="qihfId" /> -->
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="180"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="外部质量损失金额" align="center" prop="moleculeExternalMassLossRate" />
      <el-table-column label="外部质量损失率(%)" align="center" prop="externalMassLossRate" width="150" />
      <el-table-column label="质量考核季度排名" align="center" prop="quarterlyRank" />
      <el-table-column label="平均无故障时间(小时)" align="center" prop="meantimeWithoutFailure" width="200">
      </el-table-column>
      <el-table-column label="供应商不合格件返厂及时率(%)" align="center" prop="intimeReturnrate" width="220" />
      <el-table-column label="班组自查合格率(%)" align="center" prop="selfcheckPassrate" />
      <el-table-column label="下道工序反馈合格率(%)" align="center" prop="nextprocessFeedbackPassrate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:handFill:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['quality:handFill:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[质量]指标填报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="190px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="外部质量损失金额" prop="moleculeExternalMassLossRate">
          <el-input v-model="form.moleculeExternalMassLossRate" placeholder="请输入外部质量损失金额" />
        </el-form-item>
        <el-form-item label="外部质量损失率(%)" prop="externalMassLossRate">
          <el-input v-model="form.externalMassLossRate" placeholder="请输入外部质量损失率(%)" />
        </el-form-item>
        <el-form-item label="质量考核季度排名" prop="quarterlyRank">
          <el-input v-model="form.quarterlyRank" placeholder="请输入质量考核季度排名" />
        </el-form-item>
        <el-form-item label="平均无故障时间(小时)" prop="meantimeWithoutFailure">
          <el-input v-model="form.meantimeWithoutFailure" placeholder="请选择平均无故障时间(小时)" />
        </el-form-item>
        <el-form-item label="供应商不合格件返厂及时率(%)" prop="intimeReturnrate">
          <el-input v-model="form.intimeReturnrate" placeholder="请输入供应商不合格件返厂及时率(%)" />
        </el-form-item>
        <el-form-item label="班组自查合格率(%)" prop="selfcheckPassrate">
          <el-input v-model="form.selfcheckPassrate" placeholder="请输入班组自查合格率(%)" />
        </el-form-item>
        <el-form-item label="下道工序反馈合格率(%)" prop="nextprocessFeedbackPassrate">
          <el-input v-model="form.nextprocessFeedbackPassrate" placeholder="请输入下道工序反馈合格率(%)" />
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
import { listHandFill, getHandFill, delHandFill, addHandFill, updateHandFill } from "@/api/quality/data";
import { numValidatorNonZeroNature, numValidatorOnlyPositive, numValidatorPercentage } from '@/api/financial/numValidator.js';
export default {
  name: "HandFill",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dates: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // [质量]指标填报表格数据
      handFillList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        moleculeExternalMassLossRate: null,
        externalMassLossRate: null,
        quarterlyRank: null,
        meantimeWithoutFailure: null,
        intimeReturnrate: null,
        selfcheckPassrate: null,
        nextprocessFeedbackPassrate: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        moleculeExternalMassLossRate: [
          {
            required: true,
            validator: numValidatorOnlyPositive,
            trigger: "blur"
          }
        ],
        externalMassLossRate: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur"
          }
        ],
        quarterlyRank: [
          {
            required: true,
            validator: numValidatorNonZeroNature,
            trigger: "blur"
          }
        ],
        meantimeWithoutFailure: [
          {
            required: true,
            validator: numValidatorOnlyPositive,
            trigger: "blur"
          }
        ],
        intimeReturnrate: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur"
          }
        ],
        selfcheckPassrate: [
          {
            required: true,
            validator: numValidatorPercentage,
            trigger: "blur"
          }
        ],
        nextprocessFeedbackPassrate: [
          {
            required: true,
            validator: numValidatorPercentage,
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
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },
    /** 查询[质量]指标填报列表 */
    getList() {
      this.loading = true;
      listHandFill(this.queryParams).then(response => {
        this.handFillList = response.rows;
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
        qihfId: null,
        yearAndMonth: null,
        moleculeExternalMassLossRate: null,
        externalMassLossRate: null,
        quarterlyRank: null,
        meantimeWithoutFailure: null,
        intimeReturnrate: null,
        selfcheckPassrate: null,
        nextprocessFeedbackPassrate: null,
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
      this.ids = selection.map(item => item.qihfId)
      this.dates = selection.map(item => item.yearAndMonth)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "新增";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const qihfId = row.qihfId || this.ids
      getHandFill(qihfId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.qihfId != null) {
            updateHandFill(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addHandFill(this.form).then(response => {
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
      const qihfIds = row.qihfId || this.ids;
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delHandFill(qihfIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 更新按钮操作 */
    handleUpdateList() {
      this.getList()
      this.$modal.msgSuccess("更新成功");
    }
  }
};
</script>

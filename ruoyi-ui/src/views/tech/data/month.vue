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
          v-hasPermi="['tech:monthly:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['tech:monthly:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['tech:monthly:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['tech:monthly:export']">导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button v-if="updateLoading" type="primary" plain icon="el-icon-loading" size="mini"
          v-hasPermi="['tech:monthly:update']" disable>更新中</el-button>
        <el-button v-else type="primary" plain icon="el-icon-refresh" size="mini" @click="handleUpdateListWithDate"
          v-hasPermi="['tech:monthly:update']">更新</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="Tech_ID" align="center" prop="techId" /> -->
      <el-table-column label="日期" fixed align="center" prop="yearAndMonth" width="120"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="非标准单平均技术准备天数" align="center" prop="nonStandardAvgPreparationDays" width="200">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.nonStandardAvgPreparationDays) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="非标订单条数" align="center" prop="nonStandardNum">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.nonStandardNum) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="非标准单超时数" align="center" prop="nonStandardOvertimeNum">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.nonStandardOvertimeNum) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目总数" align="center" prop="totalProjectCount">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.totalProjectCount) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="未开始项目数" align="center" prop="unstartedProjectCount">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.unstartedProjectCount) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="进行中项目数" align="center" prop="ongoingProjectCount">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.ongoingProjectCount) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="已完成项目数" align="center" prop="completedProjectCount">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.completedProjectCount) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="总积分数" align="center" prop="totalPoints">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.totalPoints) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="累计得分" align="center" prop="accumulatedScore">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.accumulatedScore) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="项目积分占比(%)" align="center" prop="projectPointsPercentage" width="120">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.projectPointsPercentage) }}</span>
        </template>
      </el-table-column>

      <el-table-column label="操作" fixed="right" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['tech:monthly:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['tech:monthly:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[技术]指标填报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="200px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="非标准单平均技术准备天数" prop="nonStandardAvgPreparationDays">
          <el-input v-model="form.nonStandardAvgPreparationDays" placeholder="请输入非标准单平均技术准备天数" />
        </el-form-item>
        <el-form-item label="非标订单条数" prop="nonStandardNum">
          <el-input v-model="form.nonStandardNum" placeholder="请输入非标订单条数" />
        </el-form-item>
        <el-form-item label="非标订单超时条数" prop="nonStandardOvertimeNum">
          <el-input v-model="form.nonStandardOvertimeNum" placeholder="请输入非标订单超时条数" />
        </el-form-item>
        <el-form-item label="项目总数" prop="totalProjectCount">
          <el-input v-model="form.totalProjectCount" placeholder="请输入项目总数" />
        </el-form-item>
        <el-form-item label="未开始项目数" prop="unstartedProjectCount">
          <el-input v-model="form.unstartedProjectCount" placeholder="请输入未开始项目数" />
        </el-form-item>
        <el-form-item label="进行中项目数" prop="ongoingProjectCount">
          <el-input v-model="form.ongoingProjectCount" placeholder="请输入进行中项目数" />
        </el-form-item>
        <el-form-item label="已完成项目数" prop="completedProjectCount">
          <el-input v-model="form.completedProjectCount" placeholder="请输入已完成项目数" />
        </el-form-item>
        <el-form-item label="总积分数" prop="totalPoints">
          <el-input v-model="form.totalPoints" placeholder="请输入总积分数" />
        </el-form-item>
        <el-form-item label="累计得分" prop="accumulatedScore">
          <el-input v-model="form.accumulatedScore" placeholder="请输入累计得分" />
        </el-form-item>
        <el-form-item label="项目积分占比" prop="projectPointsPercentage">
          <el-input v-model="form.projectPointsPercentage" placeholder="请输入项目积分占比" />
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
import { listData, getData, delData, addData, updateData, updateList } from "@/api/tech/data";
import { numValidator, numValidatorOnlyPositive } from '@/api/financial/numValidator.js';
export default {
  name: "Data",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dates: [],
      showDialog: false,

      updateLoading: false,
      form3: { yearAndMonth: null },
      selectedType: '',
      dataList: [],
      techList: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // [技术]指标填报表格数据
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        nonStandardAvgPreparationDays: null,
        nonStandardNum: null,
        nonStandardOvertimeNum: null,
        totalProjectCount: null,
        unstartedProjectCount: null,
        ongoingProjectCount: null,
        completedProjectCount: null,
        totalPoints: null,
        accumulatedScore: null,
        projectPointsPercentage: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
    },
    formatNumberOne(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 1 });
    },
    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.dataList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.dataList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询[技术]指标填报列表 */
    getList() {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
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
        techId: null,
        yearAndMonth: null,
        nonStandardAvgPreparationDays: null,
        nonStandardNum: null,
        nonStandardOvertimeNum: null,
        totalProjectCount: null,
        unstartedProjectCount: null,
        ongoingProjectCount: null,
        completedProjectCount: null,
        totalPoints: null,
        accumulatedScore: null,
        projectPointsPercentage: null,
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
      this.ids = selection.map(item => item.techId)
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
      const techId = row.techId || this.ids
      getData(techId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.techId != null) {
            updateData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addData(this.form).then(response => {
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
      const techIds = row.techId || this.ids;
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delData(techIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('tech/data/export', {
        ...this.queryParams
      }, `data_${new Date().getTime()}.xlsx`)
    },
    handleUpdateListWithDate() {
      this.updateLoading = true
      const timeData = {
        startTime: new Date(),
        endTime: new Date(),
      }
      updateList(timeData).then(() => {
        this.getList();
        this.$modal.msgSuccess("更新成功");
      });
      this.updateLoading = false
    }
  }
};
</script>

<style lang="scss" scoped>
.current-page {
  padding: 16px;
}
</style>

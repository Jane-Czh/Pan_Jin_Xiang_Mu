<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="指标名" prop="indicatorNameCn">
        <el-input v-model="queryParams.indicatorNameCn" placeholder="请输入指标名" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="日期" prop="natureYear">
        <el-date-picker clearable v-model="queryParams.natureYear" type="year" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="目标值" prop="targetValue">
        <el-input v-model="queryParams.targetValue" placeholder="请输入目标值" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="目标下限" prop="targetLowerLimit">
        <el-input v-model="queryParams.targetLowerLimit" placeholder="请输入目标下限" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="目标上限" prop="targetUpperLimit">
        <el-input v-model="queryParams.targetUpperLimit" placeholder="请输入目标上限" clearable
          @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['indicators:target:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['indicators:target:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['indicators:target:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['indicators:target:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="targetList" @selection-change="handleSelectionChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="itId" /> -->
      <!-- <el-table-column label="指标所属部门" align="center" prop="indicatorDept">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.indicators_safety" :value="scope.row.indicatorDept" />
        </template>
</el-table-column> -->
      <el-table-column label="指标名" align="center" prop="indicatorNameCn" />
      <el-table-column label="日期" align="center" prop="natureYear" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.natureYear, '{y}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="目标值" align="center" prop="targetValue" />
      <el-table-column label="目标下限" align="center" prop="targetLowerLimit" />
      <el-table-column label="目标上限" align="center" prop="targetUpperLimit" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['indicators:target:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['indicators:target:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改指标-目标值对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="指标名" prop="indicatorDept">
          <el-select v-model="form.indicatorDept" placeholder="请选择指标名">
            <el-option v-for="dict in dict.type.indicators_safety" :key="dict.value" :label="dict.label"
              :value="dict.value"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="日期" prop="natureYear">
          <el-date-picker clearable v-model="form.natureYear" type="year" value-format="yyyy-MM-dd" placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="目标值" prop="targetValue">
          <el-input v-model="form.targetValue" placeholder="请输入目标值" />
        </el-form-item>
        <el-form-item label="目标下限" prop="targetLowerLimit">
          <el-input v-model="form.targetLowerLimit" placeholder="请输入目标下限" />
        </el-form-item>
        <el-form-item label="目标上限" prop="targetUpperLimit">
          <el-input v-model="form.targetUpperLimit" placeholder="请输入目标上限" />
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
import { getTargetData, getTarget, delTarget, addTarget, updateTarget } from "@/api/financial/target";
import { numValidator, numValidatorEnableEmpty } from '@/api/financial/numValidator.js';
export default {
  name: "Target",
  dicts: ['indicators_safety'],
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
      // 总条数
      total: 0,
      // 指标-目标值表格数据
      targetList: [],
      dates: [],
      indicatorNameCn: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        indicatorDept: null,
        natureYear: null,
        indicatorNameCn: null,
        targetValue: null,
        targetLowerLimit: null,
        targetUpperLimit: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        indicatorDept: [
          { required: true, message: "指标名不能为空", trigger: "blur" }
        ],
        natureYear: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        targetValue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        targetLowerLimit: [
          {
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
        targetUpperLimit: [
          {
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询指标-目标值列表 */
    getList() {
      this.loading = true;
      let target = {
        date: '',
        deptName: 'safety',
      }
      getTargetData(target).then(response => {
        this.targetList = response.rows;
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
        itId: null,
        indicatorName: null,
        indicatorDept: null,
        natureYear: null,
        indicatorNameCn: null,
        targetValue: null,
        targetLowerLimit: null,
        targetUpperLimit: null,
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
      this.ids = selection.map(item => item.itId)
      this.dates = selection.map(item => item.natureYear)
      this.indicatorNameCn = selection.map(item => item.indicatorNameCn)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加目标值";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const itId = row.itId || this.ids
      getTarget(itId).then(response => {
        this.form = response.data;
        this.form.indicatorDept = response.data.indicatorNameCn;
        this.open = true;
        this.title = "修改目标值";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.itId != null) {
            this.form.indicatorDept = 'safety';
            updateTarget(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTarget(this.form).then(response => {
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
      const itIds = row.itId || this.ids;
      const date = row.natureYear || this.dates;
      const name = row.indicatorNameCn || this.indicatorNameCn;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';

      const yearMonth = year ? `${year}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的"${name}"的数据？`).then(() => {
        return delTarget(itIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('indicators/target/export', {
        ...this.queryParams
      }, `target_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

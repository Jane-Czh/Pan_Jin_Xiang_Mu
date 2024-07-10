<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年份" prop="naturalYear">
        <el-input v-model="queryParams.naturalYear" placeholder="请输入年份" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['enterprise:annual:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['enterprise:annual:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['enterprise:annual:remove']">删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="emasId" /> -->
      <el-table-column label="年份" align="center" prop="naturalYear" sortable="custom" />
      <el-table-column label="工资总额年度值" align="center" prop="annualTotalSalary" />
      <el-table-column label="日清日结-股份目标值" align="center" prop="stockTargetValue" />
      <el-table-column label="日清日结-盘锦目标值" align="center" prop="panjinTargetValue" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:annual:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:annual:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[企业管理]指标年度数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="170px">
        <el-form-item label="年份" prop="naturalYear">
          <el-input v-model="form.naturalYear" placeholder="请输入年" />
        </el-form-item>
        <el-form-item label="工资总额年度值" prop="annualTotalSalary">
          <el-input v-model="form.annualTotalSalary" placeholder="请输入工资总额年度值" />
        </el-form-item>
        <el-form-item label="日清日结-股份目标值" prop="stockTargetValue">
          <el-input v-model="form.stockTargetValue" placeholder="请输入日清日结-股份目标值" />
        </el-form-item>
        <el-form-item label="日清日结-盘锦目标值" prop="panjinTargetValue">
          <el-input v-model="form.panjinTargetValue" placeholder="请输入日清日结-盘锦目标值" />
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
import { listYearData, getYearData, delYearData, addYearData, updateYearData } from "@/api/enterprise/data";
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
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // [企业管理]指标年度数据表格数据
      dataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        naturalYear: null,
        annualTotalSalary: null,
        stockTargetValue: null,
        panjinTargetValue: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        naturalYear: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        annualTotalSalary: [
          {
            required: true,
            validator: numValidatorOnlyPositive,
            trigger: "blur",
          }
        ],
        stockTargetValue: [
          {
            required: true,
            validator: numValidatorOnlyPositive,
            trigger: "blur",
          }
        ],
        panjinTargetValue: [
          {
            required: true,
            validator: numValidatorOnlyPositive,
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
    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'naturalYear') {
        if (sort.order === 'ascending') {
          this.dataList.sort((a, b) => new Date(a.naturalYear) - new Date(b.naturalYear));
        } else if (sort.order === 'descending') {
          this.dataList.sort((a, b) => new Date(b.naturalYear) - new Date(a.naturalYear));
        }
      }
    },
    /** 查询[企业管理]指标年度数据列表 */
    getList() {
      this.loading = true;
      listYearData(this.queryParams).then(response => {
        this.dataList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'naturalYear',
          order: 'descending' // 或'descending'
        });
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
        emasId: null,
        naturalYear: null,
        annualTotalSalary: null,
        stockTargetValue: null,
        panjinTargetValue: null,
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
      this.ids = selection.map(item => item.emasId)
      this.dates = selection.map(item => item.naturalYear)
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
      const emasId = row.emasId || this.ids
      getYearData(emasId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emasId != null) {
            updateYearData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addYearData(this.form).then(response => {
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
      const emasIds = row.emasId || this.ids;
      const date = row.naturalYear || this.dates;
      this.$modal.confirm('是否删除日期为"' + date + '"的数据？').then(function () {
        return delYearData(emasIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

  }
};
</script>
<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
        </el-date-picker>
      </el-form-item>

      <el-form-item label="党建分数" prop="score">
        <el-input v-model="queryParams.score" placeholder="请输入党建分数" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['partybuilding:data:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['partybuilding:data:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['partybuilding:data:remove']">删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="日期" align="center" prop="yearAndMonth" width="180"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="党建分数" align="center" prop="score" />
      <el-table-column label="党建排名" align="center" prop="ranking" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['partybuilding:data:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['partybuilding:data:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[党建]指标填报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="党建分数" prop="score">
          <el-input v-model="form.score" placeholder="请输入党建分数" />
        </el-form-item>
        <el-form-item label="党建排名" prop="ranking">
          <el-input v-model="form.ranking" placeholder="请输入党建排名" />
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
import { listData, getData, delData, addData, updateData } from "@/api/partybuilding/data";
import { numValidator, numValidatorNonZeroNature } from '@/api/financial/numValidator.js';

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
      // [党建]指标填报表格数据
      dataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        ranking: null,
        score: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        score: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        ranking: [
          {
            required: true,
            validator: numValidator,
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
    handleSortChange(column) {
      this.queryParams.orderByColumn = column.prop;//查询字段是表格中字段名字
      this.queryParams.isAsc = column.order;//动态取值排序顺序
      this.getList();
    },

    /** 查询[党建]指标填报列表 */
    getList() {
      this.loading = true;
      listData(this.queryParams).then(response => {
        this.dataList = response.rows;
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
        pbId: null,
        yearAndMonth: null,
        ranking: null,
        score: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null
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
      this.ids = selection.map(item => item.pbId)
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
      const pbId = row.pbId || this.ids
      getData(pbId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pbId != null) {
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
      const pbIds = row.pbId || this.ids;
      const date = row.yearAndMonth || this.dates;

      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delData(pbIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

  }
};
</script>

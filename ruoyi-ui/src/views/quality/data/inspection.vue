<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="质检月份" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM"
          placeholder="请选择质检月份">
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
          v-hasPermi="['quality:inspection:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['quality:inspection:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['quality:inspection:remove']">删除</el-button>
      </el-col>
      <import-excel :name="'质检表'" :url="'/quality/data/inspection/read'" />
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['quality:inspection:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="inspectionList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="" align="center" prop="qiId" /> -->
      <el-table-column label="质检月份" align="center" prop="yearAndMonth" width="100" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="电车的问题数量" align="center" prop="electricCarProductionQuantity" width="150" />
      <el-table-column label="K2型号中小于5吨的问题数量" align="center" prop="k2lessthan5tonProductionQuantity" width="190" />
      <el-table-column label="K2型号中大吨位的问题数量" align="center" prop="k2largetonnageProductionQuantity" width="180" />
      <el-table-column label="电车的问题车数量" align="center" prop="electricCarProblemVehicles" width="180" />
      <el-table-column label="K2型号中小于5吨的问题车数量 " align="center" prop="k2lessthan5tonProblemVehicles" width="200" />
      <el-table-column label="K2型号中大吨位的问题车数量" align="center" prop="k2largetonnageProblemVehicles" width="200" />
      <el-table-column label="电车、大吨位一次交检合格率" align="center" prop="singleInspectionPassRate" width="200" />

      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['quality:inspection:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['quality:inspection:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改质检部分字段对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="220px">
        <el-form-item label="质检月份" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM"
            placeholder="请选择质检月份">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="电车的问题数量" prop="electricCarProductionQuantity">
          <el-input v-model="form.electricCarProductionQuantity" placeholder="请输入电车的问题数量" />
        </el-form-item>
        <el-form-item label="K2型号中小于5吨的问题数量" prop="k2lessthan5tonProductionQuantity">
          <el-input v-model="form.k2lessthan5tonProductionQuantity" placeholder="请输入K2型号中小于5吨的问题数量" />
        </el-form-item>
        <el-form-item label="K2型号中大吨位的问题数量" prop="k2largetonnageProductionQuantity">
          <el-input v-model="form.k2largetonnageProductionQuantity" placeholder="请输入K2型号中大吨位的问题数量" />
        </el-form-item>
        <el-form-item label="电车的问题车数量" prop="electricCarProblemVehicles">
          <el-input v-model="form.electricCarProblemVehicles" placeholder="请输入电车的问题车数量" />
        </el-form-item>
        <el-form-item label="K2型号中小于5吨的问题车数量 " prop="k2lessthan5tonProblemVehicles">
          <el-input v-model="form.k2lessthan5tonProblemVehicles" placeholder="请输入K2型号中小于5吨的问题车数量 " />
        </el-form-item>
        <el-form-item label="K2型号中大吨位的问题车数量" prop="k2largetonnageProblemVehicles">
          <el-input v-model="form.k2largetonnageProblemVehicles" placeholder="请输入K2型号中大吨位的问题车数量" />
        </el-form-item>
        <el-form-item label="电车、大吨位一次交检合格率" prop="singleInspectionPassRate">
          <el-input v-model="form.singleInspectionPassRate" placeholder="请输入电车、大吨位一次交检合格率" />
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
import { listInspection, getInspection, delInspection, addInspection, updateInspection } from "@/api/quality/inspection";
import importExcel from "@/views/financial/importExcel.vue";

export default {
  components: { importExcel },
  name: "Inspection",
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
      // 质检部分字段表格数据
      inspectionList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        electricCarProductionQuantity: null,
        k2lessthan5tonProductionQuantity: null,
        k2largetonnageProductionQuantity: null,
        electricCarProblemVehicles: null,
        k2lessthan5tonProblemVehicles: null,
        k2largetonnageProblemVehicles: null,
        singleInspectionPassRate: null,
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
    handleSortChange(sort) {
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.inspectionList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.inspectionList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询质检部分字段列表 */
    getList() {
      this.loading = true;
      listInspection(this.queryParams).then(response => {
        this.inspectionList = response.rows;
        this.total = response.total;
        this.loading = false;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'yearAndMonth',
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
        qiId: null,
        yearAndMonth: null,
        electricCarProductionQuantity: null,
        k2lessthan5tonProductionQuantity: null,
        k2largetonnageProductionQuantity: null,
        electricCarProblemVehicles: null,
        k2lessthan5tonProblemVehicles: null,
        k2largetonnageProblemVehicles: null,
        singleInspectionPassRate: null,
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
      this.ids = selection.map(item => item.qiId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加质检部分字段";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const qiId = row.qiId || this.ids
      getInspection(qiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改质检部分字段";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.qiId != null) {
            updateInspection(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInspection(this.form).then(response => {
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
      const qiIds = row.qiId || this.ids;
      this.$modal.confirm('是否确认删除质检部分字段编号为"' + qiIds + '"的数据项？').then(function () {
        return delInspection(qiIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('quality/inspection/export', {
        ...this.queryParams
      }, `inspection_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

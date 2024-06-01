<template>
  <el-tabs v-model="activeName" type="card" class="current-page">
    <el-tab-pane label="数据管理" name="1">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="queryParams.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
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
            v-hasPermi="['tech:data:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
            v-hasPermi="['tech:data:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
            v-hasPermi="['tech:data:remove']">删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
            v-hasPermi="['tech:data:export']">导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <!-- <el-table-column label="Tech_ID" align="center" prop="techId" /> -->
        <el-table-column label="年月" align="center" prop="yearAndMonth" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="非标准单平均技术准备天数" align="center" prop="nonStandardAvgPreparationDays" />
        <el-table-column label="当月完成的计划" align="center" prop="completedmonthlyPlancounts" />
        <el-table-column label="研发项目计划进度完成率" align="center" prop="prdscheduleCompletionrate" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
              v-hasPermi="['tech:data:edit']">修改</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
              v-hasPermi="['tech:data:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
        @pagination="getList" />

      <!-- 添加或修改[技术]指标填报对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="年月" prop="yearAndMonth">
            <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
              placeholder="请选择年月">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="非标准单平均技术准备天数" prop="nonStandardAvgPreparationDays">
            <el-input v-model="form.nonStandardAvgPreparationDays" placeholder="请输入非标准单平均技术准备天数" />
          </el-form-item>
          <el-form-item label="当月完成的计划" prop="completedmonthlyPlancounts">
            <el-input v-model="form.completedmonthlyPlancounts" placeholder="请输入当月完成的计划" />
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </el-tab-pane>
    <el-tab-pane label="总计划年初填报" name="2">
      <el-form :model="queryParams2" ref="queryForm2" size="small" :inline="true" v-show="showSearch"
        label-width="68px">
        <el-form-item label="自然年" prop="naturalYear">
          <el-input v-model="queryParams2.naturalYear" placeholder="请输入自然年" clearable
            @keyup.enter.native="handleQuery2" />
        </el-form-item>
        <!-- <el-form-item label="年度计划总数" prop="annualPlancounts">
          <el-input v-model="queryParams2.annualPlancounts" placeholder="请输入年度计划总数" clearable
            @keyup.enter.native="handleQuery2" />
        </el-form-item> -->
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery2">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery2">重置</el-button>
        </el-form-item>
      </el-form>

      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd2"
            v-hasPermi="['tech:data:add']">新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate2"
            v-hasPermi="['tech:data:edit']">修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete2"
            v-hasPermi="['tech:data:remove']">删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport2"
            v-hasPermi="['tech:data:export']">导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList2"></right-toolbar>
      </el-row>

      <el-table v-loading="loading" :data="dataList2" @selection-change="handleSelectionChange2">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="id" align="center" prop="tapcId" />
        <el-table-column label="自然年" align="center" prop="naturalYear" />
        <el-table-column label="年度计划总数" align="center" prop="annualPlancounts" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate2(scope.row)"
              v-hasPermi="['Tech:data:edit']">修改</el-button>
            <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete2(scope.row)"
              v-hasPermi="['Tech:data:remove']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <pagination v-show="total > 0" :total="total" :page.sync="queryParams2.pageNum"
        :limit.sync="queryParams2.pageSize" @pagination="getList2" />

      <!-- 添加或修改【技术】总计划年初填报对话框 -->
      <el-dialog :title="title" :visible.sync="open2" width="500px" append-to-body>
        <el-form ref="form2" :model="form2" :rules="rules" label-width="80px">
          <el-form-item label="自然年" prop="naturalYear">
            <el-input v-model="form2.naturalYear" placeholder="请输入自然年" />
          </el-form-item>
          <el-form-item label="年度计划总数" prop="annualPlancounts">
            <el-input v-model="form2.annualPlancounts" placeholder="请输入年度计划总数" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm2">确 定</el-button>
          <el-button @click="cancel2">取 消</el-button>
        </div>
      </el-dialog>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { listData, getData, delData, addData, updateData, listData2, getData2, delData2, addData2, updateData2 } from "@/api/tech/data";

export default {
  name: "Data",
  data() {
    return {
      activeName: "1",
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      ids2: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // [技术]指标填报表格数据
      dataList: [],
      dataList2: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        nonStandardAvgPreparationDays: null,
        completedmonthlyPlancounts: null,
        annualPlancounts: null,
        prdscheduleCompletionrate: null,
      },
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        naturalYear: null,
        annualPlancounts: null,
      },
      // 表单参数
      form: {},
      form2: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "年月不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
    this.getList2();
  },
  methods: {
    /** 查询[技术]指标填报列表 */
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
        techId: null,
        yearAndMonth: null,
        nonStandardAvgPreparationDays: null,
        completedmonthlyPlancounts: null,
        annualPlancounts: null,
        prdscheduleCompletionrate: null,
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
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加[技术]指标";
    },

    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const techId = row.techId || this.ids
      getData(techId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改[技术]指标填报";
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
      this.$modal.confirm('是否确认删除[技术]指标填报编号为"' + techIds + '"的数据项？').then(function () {
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








    //年度计划




    /** 查询【技术】总计划年初填报列表 */
    getList2() {
      this.loading = true;
      listData2(this.queryParams2).then(response => {
        this.dataList2 = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    // 表单重置
    reset2() {
      this.form2 = {
        tapcId: null,
        naturalYear: null,
        annualPlancounts: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form2");
    },
    /** 搜索按钮操作 */
    handleQuery2() {
      this.queryParams2.pageNum = 1;
      this.getList2();
    },
    /** 重置按钮操作 */
    resetQuery2() {
      this.resetForm("queryForm2");
      this.handleQuery2();
    },
    // 多选框选中数据
    handleSelectionChange2(selection) {
      this.ids2 = selection.map(item => item.tapcId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd2() {
      this.reset2();
      this.open2 = true;
      this.title = "添加【技术】总计划年初填报";
    },
    /** 修改按钮操作 */
    handleUpdate2(row) {
      this.reset2();
      const tapcId = row.tapcId || this.ids2
      getData2(tapcId).then(response => {
        this.form2 = response.data;
        this.open2 = true;
        this.title = "修改【技术】总计划年初填报";
      });
    },
    /** 提交按钮 */
    submitForm2() {
      this.$refs["form2"].validate(valid => {
        if (valid) {
          if (this.form2.tapcId != null) {
            updateData2(this.form2).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open2 = false;
              this.getList2();
            });
          } else {
            addData2(this.form2).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open2 = false;
              this.getList2();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete2(row) {
      const tapcIds = row.tapcId || this.ids2;
      this.$modal.confirm('是否确认删除【技术】总计划年初填报编号为"' + tapcIds + '"的数据项？').then(function () {
        return delData2(tapcIds);
      }).then(() => {
        this.getList2();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport2() {
      this.download('tech/data/export', {
        ...this.queryParams2
      }, `data_${new Date().getTime()}.xlsx`)
    }

  }
};
</script>


<style lang="scss" scoped>
.current-page {
  padding: 16px;
}
</style>

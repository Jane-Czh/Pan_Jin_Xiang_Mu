<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="相同问题复发率" prop="problemRecurrenceRate">
        <el-input
          v-model="queryParams.problemRecurrenceRate"
          placeholder="请输入相同问题复发率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="不同问题出现率" prop="differentProblemOccurrenceRate">
        <el-input
          v-model="queryParams.differentProblemOccurrenceRate"
          placeholder="请输入不同问题出现率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['market:recurrence:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['market:recurrence:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['market:recurrence:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:recurrence:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="syncReport"
      >同步数据</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="recurrenceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="masrrId" /> -->
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="相同问题复发率" align="center" prop="problemRecurrenceRate" />
      <el-table-column label="不同问题出现率" align="center" prop="differentProblemOccurrenceRate" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:recurrence:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:recurrence:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改售后问题复发率对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="相同问题复发率" prop="problemRecurrenceRate">
          <el-input v-model="form.problemRecurrenceRate" placeholder="请输入相同问题复发率" />
        </el-form-item>
        <el-form-item label="不同问题出现率" prop="differentProblemOccurrenceRate">
          <el-input v-model="form.differentProblemOccurrenceRate" placeholder="请输入不同问题出现率" />
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
import { listRecurrence, getRecurrence, delRecurrence, addRecurrence, updateRecurrence } from "@/api/market/recurrence";

export default {
  name: "Recurrence",
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
      // 售后问题复发率表格数据
      recurrenceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleModel: null,
        problemRecurrenceRate: null,
        differentProblemOccurrenceRate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vehicleModel: [
          { required: true, message: "车型不能为空", trigger: "blur" }
        ],
        problemRecurrenceRate: [
          { required: true, message: "相同问题复发率不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询售后问题复发率列表 */
    getList() {
      this.loading = true;
      listRecurrence(this.queryParams).then(response => {
        this.recurrenceList = response.rows;
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
        masrrId: null,
        vehicleModel: null,
        problemRecurrenceRate: null,
        differentProblemOccurrenceRate: null
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
      this.ids = selection.map(item => item.masrrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加售后问题复发率";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const masrrId = row.masrrId || this.ids
      getRecurrence(masrrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改售后问题复发率";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.masrrId != null) {
            updateRecurrence(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRecurrence(this.form).then(response => {
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
      const masrrIds = row.masrrId || this.ids;
      this.$modal.confirm('是否确认删除售后问题复发率编号为"' + masrrIds + '"的数据项？').then(function() {
        return delRecurrence(masrrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/recurrence/export', {
        ...this.queryParams
      }, `recurrence_${new Date().getTime()}.xlsx`)
    },
    syncReport() {
      // 使用 Fetch API 发送 POST 请求到后端
      fetch('http://localhost:8080/market/recurrence/synchronization', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      })
      .then(response => {
        if (!response.ok) {
          throw new Error('Network response was not ok');
        }
        // 如果请求成功，可以进行下一步操作
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
    }
  }
};
</script>

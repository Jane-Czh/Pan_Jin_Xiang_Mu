<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="区域" prop="Area">
        <el-input
          v-model="queryParams.Area"
          placeholder="请输入区域"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="区域问题占比" prop="regionalProblemsProportion">
        <el-input
          v-model="queryParams.regionalProblemsProportion"
          placeholder="请输入区域问题占比"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未处理数目" prop="unprocessedNmber">
        <el-input
          v-model="queryParams.unprocessedNmber"
          placeholder="请输入未处理数目"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="周数" prop="Week">
        <el-input
          v-model="queryParams.Week"
          placeholder="请输入周数"
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
          v-hasPermi="['market:regional:add']"
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
          v-hasPermi="['market:regional:edit']"
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
          v-hasPermi="['market:regional:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:regional:export']"
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

    <el-table v-loading="loading" :data="regionalList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="masrcId" /> -->
      <el-table-column label="区域" align="center" prop="area" />
      <el-table-column label="区域问题占比" align="center" prop="regionalProblemsProportion" />
      <el-table-column label="未处理数目" align="center" prop="unprocessedNmber" />
      <el-table-column label="周数" align="center" prop="week" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:regional:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:regional:remove']"
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

    <!-- 添加或修改售后区域分类对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="区域" prop="Area">
          <el-input v-model="form.Area" placeholder="请输入区域" />
        </el-form-item>
        <el-form-item label="区域问题占比" prop="regionalProblemsProportion">
          <el-input v-model="form.regionalProblemsProportion" placeholder="请输入区域问题占比" />
        </el-form-item>
        <el-form-item label="未处理数目" prop="unprocessedNmber">
          <el-input v-model="form.unprocessedNmber" placeholder="请输入未处理数目" />
        </el-form-item>
        <el-form-item label="周数" prop="Week">
          <el-input v-model="form.Week" placeholder="请输入周数" />
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
import { listRegional, getRegional, delRegional, addRegional, updateRegional } from "@/api/market/regional";

export default {
  name: "Regional",
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
      // 售后区域分类表格数据
      regionalList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Area: null,
        regionalProblemsProportion: null,
        unprocessedNmber: null,
        Week: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        Area: [
          { required: true, message: "区域不能为空", trigger: "blur" }
        ],
        regionalProblemsProportion: [
          { required: true, message: "区域问题占比不能为空", trigger: "blur" }
        ],
        unprocessedNmber: [
          { required: true, message: "未处理数目不能为空", trigger: "blur" }
        ],
        Week: [
          { required: true, message: "周数不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询售后区域分类列表 */
    getList() {
      this.loading = true;
      listRegional(this.queryParams).then(response => {
        this.regionalList = response.rows;
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
        masrcId: null,
        Area: null,
        regionalProblemsProportion: null,
        unprocessedNmber: null,
        Week: null
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
      this.ids = selection.map(item => item.masrcId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加售后区域分类";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const masrcId = row.masrcId || this.ids
      getRegional(masrcId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改售后区域分类";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.masrcId != null) {
            updateRegional(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegional(this.form).then(response => {
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
      const masrcIds = row.masrcId || this.ids;
      this.$modal.confirm('是否确认删除售后区域分类编号为"' + masrcIds + '"的数据项？').then(function() {
        return delRegional(masrcIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/regional/export', {
        ...this.queryParams
      }, `regional_${new Date().getTime()}.xlsx`)
    },

    syncReport() {
      // 使用 Fetch API 发送 POST 请求到后端
      fetch('http://localhost:8080/market/regional/synchronization', {
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

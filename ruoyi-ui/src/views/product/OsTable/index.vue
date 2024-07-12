<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="Number">-->
<!--        <el-input-->
<!--          v-model="queryParams.Number"-->
<!--          placeholder="请输入序号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="员工编号" prop="idNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.idNumber"-->
<!--          placeholder="请输入员工编号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="姓名" prop="Name">-->
<!--        <el-input-->
<!--          v-model="queryParams.Name"-->
<!--          placeholder="请输入姓名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="性别" prop="Gender">-->
<!--        <el-input-->
<!--          v-model="queryParams.Gender"-->
<!--          placeholder="请输入性别"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="出勤次数" prop="attendanceSituation">-->
<!--        <el-input-->
<!--          v-model="queryParams.attendanceSituation"-->
<!--          placeholder="请输入出勤次数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="加班时长(单位:小时)" prop="overtimeDuration">-->
<!--        <el-input-->
<!--          v-model="queryParams.overtimeDuration"-->
<!--          placeholder="请输入加班时长(单位:小时)"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="异常情况" prop="abnormalSituation">-->
<!--        <el-input-->
<!--          v-model="queryParams.abnormalSituation"-->
<!--          placeholder="请输入异常情况"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
                        v-model="queryParams.createTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['product:OsTable:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['product:OsTable:edit']"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['product:OsTable:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['product:OsTable:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
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

    <el-table v-loading="loading" :data="OsTableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="postId" />-->
<!--      <el-table-column label="序号" align="center" prop="Number" />-->
      <el-table-column label="员工编号" align="center" prop="idNumber" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="出勤次数" align="center" prop="attendanceSituation" />
      <el-table-column label="加班时长(单位:分钟)" align="center" prop="overtimeDuration" />
      <el-table-column label="异常情况" align="center" prop="abnormalSituation" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['product:OsTable:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['product:OsTable:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改加班统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="序号" prop="Number">
          <el-input v-model="form.Number" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="员工编号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入员工编号" />
        </el-form-item>
        <el-form-item label="姓名" prop="Name">
          <el-input v-model="form.Name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="Gender">
          <el-input v-model="form.Gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="出勤次数" prop="attendanceSituation">
          <el-input v-model="form.attendanceSituation" placeholder="请输入出勤次数" />
        </el-form-item>
        <el-form-item label="加班时长(单位:小时)" prop="overtimeDuration">
          <el-input v-model="form.overtimeDuration" placeholder="请输入加班时长(单位:小时)" />
        </el-form-item>
        <el-form-item label="异常情况" prop="abnormalSituation">
          <el-input v-model="form.abnormalSituation" placeholder="请输入异常情况" />
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
import {listOsTable, getOsTable, delOsTable, addOsTable, updateOsTable, syncReport} from "@/api/product/OsTable";


export default {
  name: "OsTable",
  // name: "Project",
  inject: ["reload"],
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
      showSearch: false,
      // 总条数
      total: 0,
      // 加班统计表格数据
      OsTableList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Number: null,
        idNumber: null,
        Name: null,
        Gender: null,
        attendanceSituation: null,
        overtimeDuration: null,
        abnormalSituation: null,
        createTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {

  /*同步*/
  // syncReport() {
  //   // 使用 Fetch API 发送 POST 请求到后端
  //   fetch('http://localhost:8080/product/OsTable/synchronization', {
  //     method: 'POST',
  //     headers: {
  //       'Content-Type': 'application/json'
  //     }
  //   })
  //     .then(response => {
  //       if (!response.ok) {
  //         throw new Error('Network response was not ok');
  //       }
  //       // 如果请求成功，可以进行下一步操作
  //     })
  //     .catch(error => {
  //       console.error('There was an error!', error);
  //     });
  //   this.reload();
  // },
    async syncReport() {
      try {
        await syncReport();
        this.getList();
        this.$modal.msgSuccess("同步成功"); // 新增的提示
      } catch (error) {
        console.error('同步失败', error);
      }
      this.reload();
    },
  /** 查
    /** 查询加班统计列表 */
    getList() {
      this.loading = true;
      listOsTable(this.queryParams).then(response => {
        this.OsTableList = response.rows;
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
        postId: null,
        Number: null,
        idNumber: null,
        Name: null,
        Gender: null,
        attendanceSituation: null,
        overtimeDuration: null,
        abnormalSituation: null,
        createTime: null
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
      this.ids = selection.map(item => item.postId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加加班统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const postId = row.postId || this.ids
      getOsTable(postId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改加班统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.postId != null) {
            updateOsTable(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOsTable(this.form).then(response => {
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
      const postIds = row.postId || this.ids;
      this.$modal.confirm('是否确认删除加班统计编号为"' + postIds + '"的数据项？').then(function() {
        return delOsTable(postIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/OsTable/export', {
        ...this.queryParams
      }, `OsTable_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

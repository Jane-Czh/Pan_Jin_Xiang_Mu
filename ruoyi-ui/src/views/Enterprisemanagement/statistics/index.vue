<template>
  <div class="app-container">
    <div>
      <el-select v-model="selectedItem" placeholder="选择统计数据">
        <el-option :label="'合同工总人数: ' + totalNumberOfContractedInFirstRow" :value="1" />
        <el-option :label="'实习生总人数: ' + totalNumberOfInterns1" :value="2" />
        <el-option :label="'劳动派遣总人数: ' + ttotalNumberOfLaborDispatch1" :value="3" />
      </el-select>
    </div>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="员工编号" prop="employeeId">-->
<!--        <el-input-->
<!--          v-model="queryParams.employeeId"-->
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
<!--      <el-form-item label="员工组" prop="employeeGroup">-->
<!--        <el-input-->
<!--          v-model="queryParams.employeeGroup"-->
<!--          placeholder="请输入员工组"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="实习生总人数" prop="totalNumberOfInterns">-->
<!--        <el-input-->
<!--          v-model="queryParams.totalNumberOfInterns"-->
<!--          placeholder="请输入实习生总人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="劳动派遣总人数" prop="ttotalNumberOfLaborDispatch">-->
<!--        <el-input-->
<!--          v-model="queryParams.ttotalNumberOfLaborDispatch"-->
<!--          placeholder="请输入劳动派遣总人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="合同工总人数" prop="totalNumberOfContracted">-->
<!--        <el-input-->
<!--          v-model="queryParams.totalNumberOfContracted"-->
<!--          placeholder="请输入合同工总人数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->

    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['Enterprisemanagement:statistics:add']"-->
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
<!--          v-hasPermi="['Enterprisemanagement:statistics:edit']"-->
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
          v-hasPermi="['Enterprisemanagement:statistics:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['Enterprisemanagement:statistics:export']"-->
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

    <el-table v-loading="loading" :data="statisticsList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="emesId" />-->
      <el-table-column label="员工编号" align="center" prop="employeeId" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="员工组" align="center" prop="employeeGroup" />
<!--      <el-table-column label="实习生总人数" align="center" prop="totalNumberOfInterns" />-->
<!--      <el-table-column label="劳动派遣总人数" align="center" prop="ttotalNumberOfLaborDispatch" />-->
<!--      <el-table-column label="合同工总人数" align="center" prop="totalNumberOfContracted" />-->
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--&lt;!&ndash;        <template slot-scope="scope">&ndash;&gt;-->
<!--&lt;!&ndash;          <el-button&ndash;&gt;-->
<!--&lt;!&ndash;            size="mini"&ndash;&gt;-->
<!--&lt;!&ndash;            type="text"&ndash;&gt;-->
<!--&lt;!&ndash;            icon="el-icon-edit"&ndash;&gt;-->
<!--&lt;!&ndash;            @click="handleUpdate(scope.row)"&ndash;&gt;-->
<!--&lt;!&ndash;            v-hasPermi="['Enterprisemanagement:statistics:edit']"&ndash;&gt;-->
<!--&lt;!&ndash;          >修改</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;          <el-button&ndash;&gt;-->
<!--&lt;!&ndash;            size="mini"&ndash;&gt;-->
<!--&lt;!&ndash;            type="text"&ndash;&gt;-->
<!--&lt;!&ndash;            icon="el-icon-delete"&ndash;&gt;-->
<!--&lt;!&ndash;            @click="handleDelete(scope.row)"&ndash;&gt;-->
<!--&lt;!&ndash;            v-hasPermi="['Enterprisemanagement:statistics:remove']"&ndash;&gt;-->
<!--&lt;!&ndash;          >删除</el-button>&ndash;&gt;-->
<!--&lt;!&ndash;        </template>&ndash;&gt;-->
<!--      </el-table-column>-->
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改员工统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工编号" prop="employeeId">
          <el-input v-model="form.employeeId" placeholder="请输入员工编号" />
        </el-form-item>
        <el-form-item label="姓名" prop="Name">
          <el-input v-model="form.Name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="Gender">
          <el-input v-model="form.Gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="员工组" prop="employeeGroup">
          <el-input v-model="form.employeeGroup" placeholder="请输入员工组" />
        </el-form-item>
        <el-form-item label="实习生总人数" prop="totalNumberOfInterns">
          <el-input v-model="form.totalNumberOfInterns" placeholder="请输入实习生总人数" />
        </el-form-item>
        <el-form-item label="劳动派遣总人数" prop="ttotalNumberOfLaborDispatch">
          <el-input v-model="form.ttotalNumberOfLaborDispatch" placeholder="请输入劳动派遣总人数" />
        </el-form-item>
        <el-form-item label="合同工总人数" prop="totalNumberOfContracted">
          <el-input v-model="form.totalNumberOfContracted" placeholder="请输入合同工总人数" />
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
import { listStatistics, getStatistics, delStatistics, addStatistics, updateStatistics } from "@/api/Enterprisemanagement/statistics";
import axios from "axios";
export default {
  name: "Statistics",
  data() {
    return {
      selectedItem: null,
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
      // 员工统计表格数据
      statisticsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        employeeId: null,
        Name: null,
        Gender: null,
        employeeGroup: null,
        totalNumberOfInterns: null,
        ttotalNumberOfLaborDispatch: null,
        totalNumberOfContracted: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      totalNumberOfInterns1:null,//添加变量用于存储实习生总人数
      totalNumberOfContractedInFirstRow: null, // 添加变量用于存储合同工总人数
      ttotalNumberOfLaborDispatch1:null,//添加变量用于存储劳动派遣总人数
    };
  },
  created() {
    this.getList();
    // console.log('123',this.statisticsList);
    // console.log('000',this.statisticsList.totalNumberOfContracted);
    //
    //   this.totalNumberOfContractedInFirstRow = this.statisticsList.totalNumberOfContracted;
    //   console.log('111',this.statisticsList.totalNumberOfContracted);

    // this.getList().then(() => {
    //   // 获取第一行的数据并赋值给变量
    //   if (this.statisticsList.length > 0) {
    //     this.totalNumberOfContractedInFirstRow = this.statisticsList.totalNumberOfContracted;
    //     console.log('111',this.statisticsList.totalNumberOfContracted);
    //   }
    // });
  },
  methods: {

    syncReport() {
      // 使用 Fetch API 发送 POST 请求到后端
      fetch('http://localhost:8080/Enterprisemanagement/statistics/synchronization', {
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
      this.getList();
    },

    /** 查询员工统计列表 */
    getList() {
      this.loading = true;
      listStatistics(this.queryParams).then(response => {
        this.statisticsList = response.rows;
        if(this.statisticsList.length>0) {
          this.totalNumberOfContractedInFirstRow = this.statisticsList[0].totalNumberOfContracted;
          this.totalNumberOfInterns1 = this.statisticsList[0].totalNumberOfInterns;
          this.ttotalNumberOfLaborDispatch1 = this.statisticsList[0].ttotalNumberOfLaborDispatch;
        }
        // console.log('123',this.statisticsList);
        // console.log('000',this.statisticsList[0].totalNumberOfContracted);
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
        emesId: null,
        employeeId: null,
        Name: null,
        Gender: null,
        employeeGroup: null,
        totalNumberOfInterns: null,
        ttotalNumberOfLaborDispatch: null,
        totalNumberOfContracted: null
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
      this.ids = selection.map(item => item.emesId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emesId = row.emesId || this.ids
      getStatistics(emesId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emesId != null) {
            updateStatistics(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStatistics(this.form).then(response => {
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
      const emesIds = row.emesId || this.ids;
      this.$modal.confirm('是否确认删除员工统计编号为"' + emesIds + '"的数据项？').then(function() {
        return delStatistics(emesIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Enterprisemanagement/statistics/export', {
        ...this.queryParams
      }, `statistics_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style>
.contracted-total {
  font-size: 20px;
  font-weight: bold;
  color: #007bff;
  margin-bottom: 15px;
}
.intern-total {
  font-size: 20px;
  font-weight: bold;
  color: #28a745;
  margin-bottom: 15px;
}

.labor-total {
  font-size: 20px;
  font-weight: bold;
  color: #dc3545;
  margin-bottom: 15px;
}
</style>


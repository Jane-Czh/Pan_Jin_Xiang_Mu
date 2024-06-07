<template>
  <div class="app-container">
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
          v-hasPermi="['enterprise:Management:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['enterprise:Management:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['enterprise:Management:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['enterprise:Management:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ManagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="emId" />
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="SD 销售订单有效性考核" align="center" prop="sdSalesordervalidity" />
      <el-table-column label="PP 手工创建生产订单比例" align="center" prop="ppManualpocreationratio" />
      <el-table-column label="PP 生产订单已收货未报工的比例" align="center" prop="ppDeliveredunreportedratio" />
      <el-table-column label="MES 报工不及时率比率" align="center" prop="mesLateworkreportingrate" />
      <el-table-column label="QM 外检业务不及时率" align="center" prop="qmExternalinspectiondelay" />
      <el-table-column label="MM 采购订单交货不及时的比例" align="center" prop="mmPurchaseorderlatedelivery" />
      <el-table-column label="MM 手工创建采购订单比例" align="center" prop="mmManualpocreation" />
      <el-table-column label="MM 未清采购申请" align="center" prop="mmUnsettledpurchaserequests" />
      <el-table-column label="FICO 月度标准价格与周期单位价格综合差异率" align="center" prop="ficoMonthlystandardpricevariation" />
      <el-table-column label="跨月生产订单比例" align="center" prop="CrossMonthProductionOrders" />
      <el-table-column label="PM 维修订单完工不及时率" align="center" prop="pmLatemaintenanceordercompletion" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:Management:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:Management:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改十一项管理指标对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="SD 销售订单有效性考核" prop="sdSalesordervalidity">
          <el-input v-model="form.sdSalesordervalidity" placeholder="请输入SD 销售订单有效性考核" />
        </el-form-item>
        <el-form-item label="PP 手工创建生产订单比例" prop="ppManualpocreationratio">
          <el-input v-model="form.ppManualpocreationratio" placeholder="请输入PP 手工创建生产订单比例" />
        </el-form-item>
        <el-form-item label="PP 生产订单已收货未报工的比例" prop="ppDeliveredunreportedratio">
          <el-input v-model="form.ppDeliveredunreportedratio" placeholder="请输入PP 生产订单已收货未报工的比例" />
        </el-form-item>
        <el-form-item label="MES 报工不及时率比率" prop="mesLateworkreportingrate">
          <el-input v-model="form.mesLateworkreportingrate" placeholder="请输入MES 报工不及时率比率" />
        </el-form-item>
        <el-form-item label="QM 外检业务不及时率" prop="qmExternalinspectiondelay">
          <el-input v-model="form.qmExternalinspectiondelay" placeholder="请输入QM 外检业务不及时率" />
        </el-form-item>
        <el-form-item label="MM 采购订单交货不及时的比例" prop="mmPurchaseorderlatedelivery">
          <el-input v-model="form.mmPurchaseorderlatedelivery" placeholder="请输入MM 采购订单交货不及时的比例" />
        </el-form-item>
        <el-form-item label="MM 手工创建采购订单比例" prop="mmManualpocreation">
          <el-input v-model="form.mmManualpocreation" placeholder="请输入MM 手工创建采购订单比例" />
        </el-form-item>
        <el-form-item label="MM 未清采购申请" prop="mmUnsettledpurchaserequests">
          <el-input v-model="form.mmUnsettledpurchaserequests" placeholder="请输入MM 未清采购申请" />
        </el-form-item>
        <el-form-item label="FICO 月度标准价格与周期单位价格综合差异率" prop="ficoMonthlystandardpricevariation">
          <el-input v-model="form.ficoMonthlystandardpricevariation" placeholder="请输入FICO 月度标准价格与周期单位价格综合差异率" />
        </el-form-item>
        <el-form-item label="跨月生产订单比例" prop="CrossMonthProductionOrders">
          <el-input v-model="form.CrossMonthProductionOrders" placeholder="请输入跨月生产订单比例" />
        </el-form-item>
        <el-form-item label="PM 维修订单完工不及时率" prop="pmLatemaintenanceordercompletion">
          <el-input v-model="form.pmLatemaintenanceordercompletion" placeholder="请输入PM 维修订单完工不及时率" />
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
import { listManagement, getManagement, delManagement, addManagement, updateManagement } from "@/api/enterprise/managementIndex";

export default {
  name: "Management",
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
      // 十一项管理指标表格数据
      ManagementList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        sdSalesordervalidity: null,
        ppManualpocreationratio: null,
        ppDeliveredunreportedratio: null,
        mesLateworkreportingrate: null,
        qmExternalinspectiondelay: null,
        mmPurchaseorderlatedelivery: null,
        mmManualpocreation: null,
        mmUnsettledpurchaserequests: null,
        ficoMonthlystandardpricevariation: null,
        CrossMonthProductionOrders: null,
        pmLatemaintenanceordercompletion: null,
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
    /** 查询十一项管理指标列表 */
    getList() {
      this.loading = true;
      listManagement(this.queryParams).then(response => {
        this.ManagementList = response.rows;
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
        emId: null,
        yearAndMonth: null,
        sdSalesordervalidity: null,
        ppManualpocreationratio: null,
        ppDeliveredunreportedratio: null,
        mesLateworkreportingrate: null,
        qmExternalinspectiondelay: null,
        mmPurchaseorderlatedelivery: null,
        mmManualpocreation: null,
        mmUnsettledpurchaserequests: null,
        ficoMonthlystandardpricevariation: null,
        CrossMonthProductionOrders: null,
        pmLatemaintenanceordercompletion: null,
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
      this.ids = selection.map(item => item.emId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加十一项管理指标";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emId = row.emId || this.ids
      getManagement(emId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改十一项管理指标";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emId != null) {
            updateManagement(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addManagement(this.form).then(response => {
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
      const emIds = row.emId || this.ids;
      this.$modal.confirm('是否确认删除十一项管理指标编号为' + emIds + '的数据项？').then(function () {
        return delManagement(emIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('enterprise/data/management/export', {
        ...this.queryParams
      }, `Management_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

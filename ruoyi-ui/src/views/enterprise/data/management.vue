<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
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
          v-hasPermi="['enterprise:management:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['enterprise:management:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['enterprise:management:remove']">删除</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ManagementList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id" align="center" prop="emId" /> -->
      <el-table-column fixed label="日期" align="center" prop="yearAndMonth" width="120" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="SD 销售订单有效性考核" align="center" prop="sdSalesordervalidity" width="170" />
      <el-table-column label="PP 手工创建生产订单比例(%)" align="center" prop="ppManualpocreationratio" width="200" />
      <el-table-column label="PP 生产订单已收货未报工的比例(%)" align="center" prop="ppDeliveredunreportedratio" width="250" />
      <el-table-column label="MES 报工不及时率比率(%)" align="center" prop="mesLateworkreportingrate" width="190" />
      <el-table-column label="QM 外检业务不及时率(%)" align="center" prop="qmExternalinspectiondelay" width="180" />
      <el-table-column label="MM 采购订单交货不及时的比例(%)" align="center" prop="mmPurchaseorderlatedelivery" width="230" />
      <el-table-column label="MM 手工创建采购订单比例(%)" align="center" prop="mmManualpocreation" width="200" />
      <el-table-column label="MM 未清采购申请" align="center" prop="mmUnsettledpurchaserequests" width="140" />
      <el-table-column label="FICO 月度标准价格与周期单位价格综合差异率(%)" align="center" prop="ficoMonthlystandardpricevariation"
        width="310" />
      <el-table-column label="跨月生产订单比例(%)" align="center" prop="crossMonthProductionOrders" width="160" />
      <el-table-column label="PM 维修订单完工不及时率(%)" align="center" prop="pmLatemaintenanceordercompletion" width="200" />
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:management:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:management:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改十一项管理指标对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="250px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="SD 销售订单有效性考核" prop="sdSalesordervalidity">
          <el-input v-model="form.sdSalesordervalidity" placeholder="请输入SD 销售订单有效性考核" />
        </el-form-item>
        <el-form-item label="PP 手工创建生产订单比例(%)" prop="ppManualpocreationratio">
          <el-input v-model="form.ppManualpocreationratio" placeholder="请输入PP 手工创建生产订单比例(%)" />
        </el-form-item>
        <el-form-item label="PP 生产订单已收货未报工的比例(%)" prop="ppDeliveredunreportedratio">
          <el-input v-model="form.ppDeliveredunreportedratio" placeholder="请输入PP 生产订单已收货未报工的比例(%)" />
        </el-form-item>
        <el-form-item label="MES 报工不及时率比率(%)" prop="mesLateworkreportingrate">
          <el-input v-model="form.mesLateworkreportingrate" placeholder="请输入MES 报工不及时率比率(%)" />
        </el-form-item>
        <el-form-item label="QM 外检业务不及时率(%)" prop="qmExternalinspectiondelay">
          <el-input v-model="form.qmExternalinspectiondelay" placeholder="请输入QM 外检业务不及时率(%)" />
        </el-form-item>
        <el-form-item label="MM 采购订单交货不及时的比例(%)" prop="mmPurchaseorderlatedelivery">
          <el-input v-model="form.mmPurchaseorderlatedelivery" placeholder="请输入MM 采购订单交货不及时的比例(%)" />
        </el-form-item>
        <el-form-item label="MM 手工创建采购订单比例(%)" prop="mmManualpocreation">
          <el-input v-model="form.mmManualpocreation" placeholder="请输入MM 手工创建采购订单比例(%)" />
        </el-form-item>
        <el-form-item label="MM 未清采购申请" prop="mmUnsettledpurchaserequests">
          <el-input v-model="form.mmUnsettledpurchaserequests" placeholder="请输入MM 未清采购申请" />
        </el-form-item>
        <el-form-item label="FICO 月度标准价格与周期单位价格综合差异率(%)" prop="ficoMonthlystandardpricevariation">
          <el-input v-model="form.ficoMonthlystandardpricevariation" placeholder="请输入FICO 月度标准价格与周期单位价格综合差异率(%)" />
        </el-form-item>
        <el-form-item label="跨月生产订单比例(%)" prop="crossMonthProductionOrders">
          <el-input v-model="form.crossMonthProductionOrders" placeholder="请输入跨月生产订单比例(%)" />
        </el-form-item>
        <el-form-item label="PM 维修订单完工不及时率(%)" prop="pmLatemaintenanceordercompletion">
          <el-input v-model="form.pmLatemaintenanceordercompletion" placeholder="请输入PM 维修订单完工不及时率(%)" />
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
import { numValidator, numValidatorPercentage } from '@/api/financial/numValidator.js';
export default {
  name: "Management",
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
        crossMonthProductionOrders: null,
        pmLatemaintenanceordercompletion: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        sdSalesordervalidity: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        ppManualpocreationratio: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        ppDeliveredunreportedratio: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        mesLateworkreportingrate: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        qmExternalinspectiondelay: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        mmPurchaseorderlatedelivery: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ], mmManualpocreation: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        mmUnsettledpurchaserequests: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        ficoMonthlystandardpricevariation: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        crossMonthProductionOrders: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        pmLatemaintenanceordercompletion: [
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
    handleClose(done) {
      this.$confirm('确定关闭吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        done();
      }).catch(() => {
      });
    },
    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.ManagementList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.ManagementList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询十一项管理指标列表 */
    getList() {
      this.loading = true;
      listManagement(this.queryParams).then(response => {
        this.ManagementList = response.rows;
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
        crossMonthProductionOrders: null,
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
      const emId = row.emId || this.ids
      getManagement(emId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
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
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delManagement(emIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

  }
};
</script>

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="网点" prop="Branch">
        <el-input
          v-model="queryParams.Branch"
          placeholder="请输入网点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="合同号" prop="contractNumber">
        <el-input
          v-model="queryParams.contractNumber"
          placeholder="请输入合同号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接单日期" prop="orderAcceptanceTime">
        <el-date-picker clearable
          v-model="queryParams.orderAcceptanceTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择接单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="Number">
        <el-input
          v-model="queryParams.Number"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="阀片" prop="valveBlock">
        <el-input
          v-model="queryParams.valveBlock"
          placeholder="请输入阀片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="货叉" prop="Fork">
        <el-input
          v-model="queryParams.Fork"
          placeholder="请输入货叉"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门架" prop="doorFrame">
        <el-input
          v-model="queryParams.doorFrame"
          placeholder="请输入门架"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="空滤" prop="airFilter">
        <el-input
          v-model="queryParams.airFilter"
          placeholder="请输入空滤"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="属具" prop="Accessory">
        <el-input
          v-model="queryParams.Accessory"
          placeholder="请输入属具"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="轮胎" prop="Tyre">
        <el-input
          v-model="queryParams.Tyre"
          placeholder="请输入轮胎"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配置" prop="Configuration">
        <el-input
          v-model="queryParams.Configuration"
          placeholder="请输入配置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车号" prop="carNumber">
        <el-input
          v-model="queryParams.carNumber"
          placeholder="请输入车号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单系统交货期" prop="orderSystemDeliveryTime">
        <el-date-picker clearable
          v-model="queryParams.orderSystemDeliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择订单系统交货期">
        </el-date-picker>
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
          v-hasPermi="['market:Storage:add']"
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
          v-hasPermi="['market:Storage:edit']"
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
          v-hasPermi="['market:Storage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:Storage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="StorageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="mstsId" /> -->
      <el-table-column label="网点" align="center" prop="branch" />
      <el-table-column label="合同号" align="center" prop="contractNumber" />
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column label="接单日期" align="center" prop="orderAcceptanceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderAcceptanceTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="阀片" align="center" prop="valveBlock" />
      <el-table-column label="货叉" align="center" prop="fork" />
      <el-table-column label="门架" align="center" prop="doorFrame" />
      <el-table-column label="空滤" align="center" prop="airFilter" />
      <el-table-column label="属具" align="center" prop="accessory" />
      <el-table-column label="轮胎" align="center" prop="tyre" />
      <el-table-column label="配置" align="center" prop="configuration" />
      <el-table-column label="车号" align="center" prop="carNumber" />
      <el-table-column label="订单系统交货期" align="center" prop="orderSystemDeliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderSystemDeliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:Storage:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:Storage:remove']"
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

    <!-- 添加或修改销售台账存储对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="网点" prop="Branch">
          <el-input v-model="form.Branch" placeholder="请输入网点" />
        </el-form-item>
        <el-form-item label="合同号" prop="contractNumber">
          <el-input v-model="form.contractNumber" placeholder="请输入合同号" />
        </el-form-item>
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="接单日期" prop="orderAcceptanceTime">
          <el-date-picker clearable
            v-model="form.orderAcceptanceTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择接单日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="数量" prop="Number">
          <el-input v-model="form.Number" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="阀片" prop="valveBlock">
          <el-input v-model="form.valveBlock" placeholder="请输入阀片" />
        </el-form-item>
        <el-form-item label="货叉" prop="Fork">
          <el-input v-model="form.Fork" placeholder="请输入货叉" />
        </el-form-item>
        <el-form-item label="门架" prop="doorFrame">
          <el-input v-model="form.doorFrame" placeholder="请输入门架" />
        </el-form-item>
        <el-form-item label="空滤" prop="airFilter">
          <el-input v-model="form.airFilter" placeholder="请输入空滤" />
        </el-form-item>
        <el-form-item label="属具" prop="Accessory">
          <el-input v-model="form.Accessory" placeholder="请输入属具" />
        </el-form-item>
        <el-form-item label="轮胎" prop="Tyre">
          <el-input v-model="form.Tyre" placeholder="请输入轮胎" />
        </el-form-item>
        <el-form-item label="配置" prop="Configuration">
          <el-input v-model="form.Configuration" placeholder="请输入配置" />
        </el-form-item>
        <el-form-item label="车号" prop="carNumber">
          <el-input v-model="form.carNumber" placeholder="请输入车号" />
        </el-form-item>
        <el-form-item label="订单系统交货期" prop="orderSystemDeliveryTime">
          <el-date-picker clearable
            v-model="form.orderSystemDeliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单系统交货期">
          </el-date-picker>
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
import { listStorage, getStorage, delStorage, addStorage, updateStorage } from "@/api/market/Storage";

export default {
  name: "Storage",
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
      // 销售台账存储表格数据
      StorageList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Branch: null,
        contractNumber: null,
        orderNumber: null,
        orderAcceptanceTime: null,
        vehicleModel: null,
        Number: null,
        valveBlock: null,
        Fork: null,
        doorFrame: null,
        airFilter: null,
        Accessory: null,
        Tyre: null,
        Configuration: null,
        carNumber: null,
        orderSystemDeliveryTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        Branch: [
          { required: true, message: "网点不能为空", trigger: "blur" }
        ],
        contractNumber: [
          { required: true, message: "合同号不能为空", trigger: "blur" }
        ],
        orderNumber: [
          { required: true, message: "订单号不能为空", trigger: "blur" }
        ],
        orderAcceptanceTime: [
          { required: true, message: "接单日期不能为空", trigger: "blur" }
        ],
        vehicleModel: [
          { required: true, message: "车型不能为空", trigger: "blur" }
        ],
        Number: [
          { required: true, message: "数量不能为空", trigger: "blur" }
        ],
        orderSystemDeliveryTime: [
          { required: true, message: "订单系统交货期不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询销售台账存储列表 */
    getList() {
      this.loading = true;
      listStorage(this.queryParams).then(response => {
        this.StorageList = response.rows;
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
        mstsId: null,
        Branch: null,
        contractNumber: null,
        orderNumber: null,
        orderAcceptanceTime: null,
        vehicleModel: null,
        Number: null,
        valveBlock: null,
        Fork: null,
        doorFrame: null,
        airFilter: null,
        Accessory: null,
        Tyre: null,
        Configuration: null,
        carNumber: null,
        orderSystemDeliveryTime: null
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
      this.ids = selection.map(item => item.mstsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加销售台账存储";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mstsId = row.mstsId || this.ids
      getStorage(mstsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改销售台账存储";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mstsId != null) {
            updateStorage(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStorage(this.form).then(response => {
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
      const mstsIds = row.mstsId || this.ids;
      this.$modal.confirm('是否确认删除销售台账存储编号为"' + mstsIds + '"的数据项？').then(function() {
        return delStorage(mstsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/Storage/export', {
        ...this.queryParams
      }, `Storage_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

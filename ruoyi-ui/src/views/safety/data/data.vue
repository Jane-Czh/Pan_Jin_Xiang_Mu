<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="年月" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择年月">
        </el-date-picker>
      </el-form-item>

      <!-- <el-form-item label="当月设备维修总费用" prop="curEquipmentMaintenanceCost">
        <el-input
          v-model="queryParams.curEquipmentMaintenanceCost"
          placeholder="请输入当月设备维修总费用"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当月设备故障累计停产时间" prop="curEquipmentFailuresTotaltime">
        <el-input
          v-model="queryParams.curEquipmentFailuresTotaltime"
          placeholder="请输入当月设备故障累计停产时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="当月设备维修替换件成本" prop="curEquipmentReplacementCost">
        <el-input
          v-model="queryParams.curEquipmentReplacementCost"
          placeholder="请输入当月设备维修替换件成本"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="重点设备故障率" prop="keyEquipmentFailureRate">
        <el-input
          v-model="queryParams.keyEquipmentFailureRate"
          placeholder="请输入重点设备故障率"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="主要设备故障总次数" prop="keyEquipmentTotalFailureCount">
        <el-input
          v-model="queryParams.keyEquipmentTotalFailureCount"
          placeholder="请输入主要设备故障总次数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['safety:data:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['safety:data:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['safety:data:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['safety:data:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="Safety_EP_ID" align="center" prop="safetyEpId" /> -->
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>

      <el-table-column label="当月设备维修总费用" align="center" prop="curEquipmentMaintenanceCost" />
      <el-table-column label="当月设备故障累计停产时间" align="center" prop="curEquipmentFailuresTotaltime" />
      <el-table-column label="当月设备维修替换件成本" align="center" prop="curEquipmentReplacementCost" />
      <el-table-column label="重点设备故障率" align="center" prop="keyEquipmentFailureRate" />
      <el-table-column label="主要设备故障总次数" align="center" prop="keyEquipmentTotalFailureCount" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['safety:data:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['safety:data:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[安全环保]指标填报对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>

        <el-form-item label="当月设备维修总费用" prop="curEquipmentMaintenanceCost">
          <el-input v-model="form.curEquipmentMaintenanceCost" placeholder="请输入当月设备维修总费用" />
        </el-form-item>
        <el-form-item label="当月设备故障累计停产时间" prop="curEquipmentFailuresTotaltime">
          <el-input v-model="form.curEquipmentFailuresTotaltime" placeholder="请输入当月设备故障累计停产时间" />
        </el-form-item>
        <el-form-item label="当月设备维修替换件成本" prop="curEquipmentReplacementCost">
          <el-input v-model="form.curEquipmentReplacementCost" placeholder="请输入当月设备维修替换件成本" />
        </el-form-item>

        <el-form-item label="主要设备故障总次数" prop="keyEquipmentTotalFailureCount">
          <el-input v-model="form.keyEquipmentTotalFailureCount" placeholder="请输入主要设备故障总次数" />
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
import { listData, getData, delData, addData, updateData } from "@/api/safety/data";

export default {
  name: "Data",
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
      // [安全环保]指标填报表格数据
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
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        curEquipmentMaintenanceCost: null,
        curEquipmentFailuresTotaltime: null,
        curEquipmentReplacementCost: null,
        keyEquipmentFailureRate: null,
        keyEquipmentTotalFailureCount: null
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
    /** 查询[安全环保]指标填报列表 */
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
        safetyEpId: null,
        yearAndMonth: null,
        createdBy: null,
        createdTime: null,
        updatedBy: null,
        updatedTime: null,
        curEquipmentMaintenanceCost: null,
        curEquipmentFailuresTotaltime: null,
        curEquipmentReplacementCost: null,
        keyEquipmentFailureRate: null,
        keyEquipmentTotalFailureCount: null
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
      this.ids = selection.map(item => item.safetyEpId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加[安全环保]指标";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const safetyEpId = row.safetyEpId || this.ids
      getData(safetyEpId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改[安全环保]指标";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.safetyEpId != null) {
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
      const safetyEpIds = row.safetyEpId || this.ids;
      this.$modal.confirm('是否确认删除[安全环保]指标编号为"' + safetyEpIds + '"的数据项？').then(function () {
        return delData(safetyEpIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('safety/data/export', {
        ...this.queryParams
      }, `data_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
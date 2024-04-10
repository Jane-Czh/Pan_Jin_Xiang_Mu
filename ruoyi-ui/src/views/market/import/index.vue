<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
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
      <el-form-item label="备注" prop="Remarks">
        <el-input
          v-model="queryParams.Remarks"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交货形式" prop="deliveryForm">
        <el-input
          v-model="queryParams.deliveryForm"
          placeholder="请输入交货形式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="交货地点" prop="deliveryLocation">
        <el-input
          v-model="queryParams.deliveryLocation"
          placeholder="请输入交货地点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系人" prop="Contacts">
        <el-input
          v-model="queryParams.Contacts"
          placeholder="请输入联系人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电话" prop="Telephone">
        <el-input
          v-model="queryParams.Telephone"
          placeholder="请输入电话"
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
      <el-form-item label="订单超期预警" prop="orderOverdueWarning">
        <el-input
          v-model="queryParams.orderOverdueWarning"
          placeholder="请输入订单超期预警"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划发车日期" prop="plannedDepartureDate">
        <el-date-picker clearable
          v-model="queryParams.plannedDepartureDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择计划发车日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="实际发车日期" prop="actualDepartureDate">
        <el-date-picker clearable
          v-model="queryParams.actualDepartureDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择实际发车日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="系统交货期" prop="systemDeliveryTime">
        <el-date-picker clearable
          v-model="queryParams.systemDeliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择系统交货期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="交货单号" prop="deliveryNoteNumber">
        <el-input
          v-model="queryParams.deliveryNoteNumber"
          placeholder="请输入交货单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="技术准备完成时间" prop="technicalPreparationCompletionTime">
        <el-date-picker clearable
          v-model="queryParams.technicalPreparationCompletionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择技术准备完成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['market:import:add']"
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
          v-hasPermi="['market:import:edit']"
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
          v-hasPermi="['market:import:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:import:export']"
        >导出</el-button>
      </el-col>

      <el-col>
        <!--Excel 参数导入 -->
        <el-button
          type="primary"
          @click="showDialog = true"
        ><i class="fa fa-download"></i>导入Excel文件
        </el-button>
        <el-dialog
          title="导入Excel文件"
          :visible.sync="showDialog"
          width="30%"
          :before-close="handleClose"
          @close="resetFileInput"
        >
          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

          <!-- 进度动画条 -->
          <div v-if="progress > 0">
            <el-progress
              :percentage="progress"
              color="rgb(19, 194, 194)"
            ></el-progress>
          </div>

          <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取 消</el-button>
          <el-button type="primary" @click="fileSend()">确 定</el-button>
        </span>
        </el-dialog>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="importList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="MS_ID" align="center" prop="msId" />
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="网点" align="center" prop="branch" />
      <el-table-column label="合同号" align="center" prop="contractNumber" />
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column label="接单日期" align="center" prop="orderAcceptanceTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderAcceptanceTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="数量" align="center" prop="Number" />
      <el-table-column label="阀片" align="center" prop="valveBlock" />
      <el-table-column label="货叉" align="center" prop="Fork" />
      <el-table-column label="门架" align="center" prop="doorFrame" />
      <el-table-column label="空滤" align="center" prop="airFilter" />
      <el-table-column label="属具" align="center" prop="Accessory" />
      <el-table-column label="轮胎" align="center" prop="Tyre" />
      <el-table-column label="配置" align="center" prop="Configuration" />
      <el-table-column label="车号" align="center" prop="carNumber" />
      <el-table-column label="备注" align="center" prop="Remarks" />
      <el-table-column label="交货形式" align="center" prop="deliveryForm" />
      <el-table-column label="交货地点" align="center" prop="deliveryLocation" />
      <el-table-column label="联系人" align="center" prop="Contacts" />
      <el-table-column label="电话" align="center" prop="Telephone" />
      <el-table-column label="订单系统交货期" align="center" prop="orderSystemDeliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.orderSystemDeliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单超期预警" align="center" prop="orderOverdueWarning" />
      <el-table-column label="计划发车日期" align="center" prop="plannedDepartureDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedDepartureDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实际发车日期" align="center" prop="actualDepartureDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.actualDepartureDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="系统交货期" align="center" prop="systemDeliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.systemDeliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="交货单号" align="center" prop="deliveryNoteNumber" />
      <el-table-column label="技术准备完成时间" align="center" prop="technicalPreparationCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.technicalPreparationCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:import:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:import:remove']"
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

    <!-- 添加或修改市场科销售台账对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
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
        <el-form-item label="备注" prop="Remarks">
          <el-input v-model="form.Remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="交货形式" prop="deliveryForm">
          <el-input v-model="form.deliveryForm" placeholder="请输入交货形式" />
        </el-form-item>
        <el-form-item label="交货地点" prop="deliveryLocation">
          <el-input v-model="form.deliveryLocation" placeholder="请输入交货地点" />
        </el-form-item>
        <el-form-item label="联系人" prop="Contacts">
          <el-input v-model="form.Contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="电话" prop="Telephone">
          <el-input v-model="form.Telephone" placeholder="请输入电话" />
        </el-form-item>
        <el-form-item label="订单系统交货期" prop="orderSystemDeliveryTime">
          <el-date-picker clearable
            v-model="form.orderSystemDeliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择订单系统交货期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单超期预警" prop="orderOverdueWarning">
          <el-input v-model="form.orderOverdueWarning" placeholder="请输入订单超期预警" />
        </el-form-item>
        <el-form-item label="计划发车日期" prop="plannedDepartureDate">
          <el-date-picker clearable
            v-model="form.plannedDepartureDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划发车日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际发车日期" prop="actualDepartureDate">
          <el-date-picker clearable
            v-model="form.actualDepartureDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择实际发车日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="系统交货期" prop="systemDeliveryTime">
          <el-date-picker clearable
            v-model="form.systemDeliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择系统交货期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="交货单号" prop="deliveryNoteNumber">
          <el-input v-model="form.deliveryNoteNumber" placeholder="请输入交货单号" />
        </el-form-item>
        <el-form-item label="技术准备完成时间" prop="technicalPreparationCompletionTime">
          <el-date-picker clearable
            v-model="form.technicalPreparationCompletionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择技术准备完成时间">
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
import { listImport, getImport, delImport, addImport, updateImport } from "@/api/market/import";
import axios from 'axios'

export default {
  name: "Import",
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
      // 市场科销售台账表格数据
      importList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        createdBy: null,
        createdTime: null,
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
        Remarks: null,
        deliveryForm: null,
        deliveryLocation: null,
        Contacts: null,
        Telephone: null,
        orderSystemDeliveryTime: null,
        orderOverdueWarning: null,
        plannedDepartureDate: null,
        actualDepartureDate: null,
        systemDeliveryTime: null,
        deliveryNoteNumber: null,
        technicalPreparationCompletionTime: null
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
        valveBlock: [
          { required: true, message: "阀片不能为空", trigger: "blur" }
        ],
        Fork: [
          { required: true, message: "货叉不能为空", trigger: "blur" }
        ],
        doorFrame: [
          { required: true, message: "门架不能为空", trigger: "blur" }
        ],
        Tyre: [
          { required: true, message: "轮胎不能为空", trigger: "blur" }
        ],
        Configuration: [
          { required: true, message: "配置不能为空", trigger: "blur" }
        ],
        deliveryForm: [
          { required: true, message: "交货形式不能为空", trigger: "blur" }
        ],
        deliveryLocation: [
          { required: true, message: "交货地点不能为空", trigger: "blur" }
        ],
        Contacts: [
          { required: true, message: "联系人不能为空", trigger: "blur" }
        ],
        orderSystemDeliveryTime: [
          { required: true, message: "订单系统交货期不能为空", trigger: "blur" }
        ],
        orderOverdueWarning: [
          { required: true, message: "订单超期预警不能为空", trigger: "blur" }
        ],
      },

      //新增参数
      showDialog: false,
      progress: 0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询市场科销售台账列表 */
    getList() {
      this.loading = true;
      listImport(this.queryParams).then(response => {
        console.log("response===>",response);
        this.importList = response.rows;
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
        msId: null,
        createdBy: null,
        createdTime: null,
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
        Remarks: null,
        deliveryForm: null,
        deliveryLocation: null,
        Contacts: null,
        Telephone: null,
        orderSystemDeliveryTime: null,
        orderOverdueWarning: null,
        plannedDepartureDate: null,
        actualDepartureDate: null,
        systemDeliveryTime: null,
        deliveryNoteNumber: null,
        technicalPreparationCompletionTime: null
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
      this.ids = selection.map(item => item.msId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加市场科销售台账";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const msId = row.msId || this.ids
      getImport(msId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改市场科销售台账";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.msId != null) {
            updateImport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addImport(this.form).then(response => {
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
      const msIds = row.msId || this.ids;
      this.$modal.confirm('是否确认删除市场科销售台账编号为"' + msIds + '"的数据项？').then(function() {
        return delImport(msIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/import/export', {
        ...this.queryParams
      }, `import_${new Date().getTime()}.xlsx`)
    },

    /*  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      console.log(file);
      formData.append("file", file);
      console.log("file====>",formData)
      axios({
        method: "post",
        // this $axios.post,
        url: "http://localhost:8080/market/import/import",
        // params:{
        //   userName: this.$store.state.user.name,
        // },
        headers: {
          "Content-Type": "multipart/form-data",
        },
        withCredentials: true,
        data: formData,
        onUploadProgress: (progressEvent) => {
          this.progress = Math.round(
            (progressEvent.loaded * 100) / progressEvent.total
          );
        },
      });
      // this.$message.success("上传成功");


      setTimeout(() => {
        this.showDialog = false; // 关闭上传面板

        location.reload(); // 调用此方法刷新页面数据
      }, 2000); // 2000毫秒后关闭
    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    //检查文件是否为excel
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    }


  }
};
</script>

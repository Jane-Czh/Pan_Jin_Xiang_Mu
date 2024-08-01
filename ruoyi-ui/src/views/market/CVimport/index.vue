<template>
  <div class="app-container">
<!--    <el-upload-->
<!--      id="inputFile"-->
<!--      :auto-upload="false"-->
<!--      :on-change="handleFileChange">-->
<!--      <el-button slot="trigger" type="primary">选取文件</el-button>-->
<!--      <el-button type="success" @click="fileSend">上传到服务器</el-button>-->
<!--    </el-upload>-->
    <el-progress v-if="showProgress" :percentage="100" status="success"></el-progress>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="接单日期" prop="acceptanceDate">
        <el-date-picker clearable
          v-model="queryParams.acceptanceDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择接单日期">
        </el-date-picker>
      </el-form-item>-->
<!--      <el-form-item label="上线日期" prop="launchDate">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.launchDate"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择上线日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="订单号" prop="orderNumber">
        <el-input
          v-model="queryParams.orderNumber"
          placeholder="请输入订单号"
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
      <el-form-item label="车号" prop="carNumber">
        <el-input
          v-model="queryParams.carNumber"
          placeholder="请输入车号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item label="门架高度" prop="doorFrameHeight">
        <el-input
          v-model="queryParams.doorFrameHeight"
          placeholder="请输入门架高度"
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
      <el-form-item label="属具" prop="Accessory">
        <el-input
          v-model="queryParams.Accessory"
          placeholder="请输入属具"
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
      <el-form-item label="计划完工期" prop="plannedCompletionPeriod">
        <el-date-picker clearable
          v-model="queryParams.plannedCompletionPeriod"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择计划完工期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="客户" prop="Customer">
        <el-input
          v-model="queryParams.Customer"
          placeholder="请输入客户"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车体上线日期" prop="vehicleLaunchDate">
        <el-date-picker clearable
          v-model="queryParams.vehicleLaunchDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择车体上线日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="门架合装完工期" prop="completionPeriodOfDoorFrameAssembly">
        <el-date-picker clearable
          v-model="queryParams.completionPeriodOfDoorFrameAssembly"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择门架合装完工期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="试车完工期" prop="trialCompletionPeriod">
        <el-date-picker clearable
          v-model="queryParams.trialCompletionPeriod"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择试车完工期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="特种作业" prop="specialOperations">
        <el-input
          v-model="queryParams.specialOperations"
          placeholder="请输入特种作业"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="精整完工期" prop="precisionCompletionPeriod">
        <el-date-picker clearable
          v-model="queryParams.precisionCompletionPeriod"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择精整完工期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="生产现场问题" prop="productionSiteissues">
        <el-input
          v-model="queryParams.productionSiteissues"
          placeholder="请输入生产现场问题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="现场异常问题采购" prop="procurementOfOnSiteAbnormalIssues">
        <el-input
          v-model="queryParams.procurementOfOnSiteAbnormalIssues"
          placeholder="请输入现场异常问题采购"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="现场异常问题质量" prop="qualityOfOnSiteAbnormalIssues">
        <el-input
          v-model="queryParams.qualityOfOnSiteAbnormalIssues"
          placeholder="请输入现场异常问题质量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上线" prop="goLive">
        <el-input
          v-model="queryParams.goLive"
          placeholder="请输入上线"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入库" prop="Warehousing">
        <el-input
          v-model="queryParams.Warehousing"
          placeholder="请输入入库"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item label="生产周期" prop="productionCycle">
        <el-input
          v-model="queryParams.productionCycle"
          placeholder="请输入生产周期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>-->
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['market:CVimport:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['market:CVimport:edit']"-->
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
          v-hasPermi="['market:CVimport:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['market:CVimport:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->

      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['market:CVimport:import']"
        >导入Excel文件</el-button>
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

    <el-table v-loading="loading" :data="CVimportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="MCV_ID" align="center" prop="mcvId" />-->
<!--      <el-table-column label="创建人" align="center" prop="createdBy" />-->
<!--      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="接单日期" align="center" prop="acceptanceDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.acceptanceDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上线日期" align="center" prop="launchDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.launchDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="订单号" align="center" prop="orderNumber" />
      <el-table-column label="合同号" align="center" prop="contractNumber" />
      <el-table-column label="车号" align="center" prop="carNumber" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="门架高度" align="center" prop="doorFrameHeight" />
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="属具" align="center" prop="accessory" />
      <el-table-column label="阀片" align="center" prop="valveBlock" />
      <el-table-column label="配置" align="center" prop="configuration" width="280" />
      <el-table-column label="计划完工期" align="center" prop="plannedCompletionPeriod" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedCompletionPeriod, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="客户" align="center" prop="customer" />
      <el-table-column label="车体上线日期" align="center" prop="vehicleLaunchDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vehicleLaunchDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="门架合装完工期" align="center" prop="completionPeriodOfDoorFrameAssembly" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.completionPeriodOfDoorFrameAssembly, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="试车完工期" align="center" prop="trialCompletionPeriod" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.trialCompletionPeriod, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="特种作业" align="center" prop="specialOperations" />
      <el-table-column label="精整完工期" align="center" prop="precisionCompletionPeriod" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.precisionCompletionPeriod, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产现场问题" align="center" prop="productionSiteissues" />
      <el-table-column label="现场异常问题采购" align="center" prop="procurementOfOnSiteAbnormalIssues" />
      <el-table-column label="现场异常问题质量" align="center" prop="qualityOfOnSiteAbnormalIssues" />

      <el-table-column label="入库" align="center" prop="warehousing" />
      <el-table-column label="生产周期" align="center" prop="productionCycle" />
      <el-table-column label="未报工原因" align="center" prop="goLive" />
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['market:CVimport:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['market:CVimport:remove']"-->
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

    <!-- 添加或修改商品车台账对话框 -->
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
        <el-form-item label="接单日期" prop="acceptanceDate">
          <el-date-picker clearable
            v-model="form.acceptanceDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择接单日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="上线日期" prop="launchDate">
          <el-date-picker clearable
            v-model="form.launchDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上线日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="订单号" prop="orderNumber">
          <el-input v-model="form.orderNumber" placeholder="请输入订单号" />
        </el-form-item>
        <el-form-item label="合同号" prop="contractNumber">
          <el-input v-model="form.contractNumber" placeholder="请输入合同号" />
        </el-form-item>
        <el-form-item label="车号" prop="carNumber">
          <el-input v-model="form.carNumber" placeholder="请输入车号" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="门架高度" prop="doorFrameHeight">
          <el-input v-model="form.doorFrameHeight" placeholder="请输入门架高度" />
        </el-form-item>
        <el-form-item label="数量" prop="number">
          <el-input v-model="form.number" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="属具" prop="accessory">
          <el-input v-model="form.accessory" placeholder="请输入属具" />
        </el-form-item>
        <el-form-item label="阀片" prop="valveBlock">
          <el-input v-model="form.valveBlock" placeholder="请输入阀片" />
        </el-form-item>
        <el-form-item label="配置" prop="configuration">
          <el-input v-model="form.configuration" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="计划完工期" prop="plannedCompletionPeriod">
          <el-date-picker clearable
            v-model="form.plannedCompletionPeriod"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划完工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="客户" prop="customer">
          <el-input v-model="form.customer" placeholder="请输入客户" />
        </el-form-item>
        <el-form-item label="车体上线日期" prop="vehicleLaunchDate">
          <el-date-picker clearable
            v-model="form.vehicleLaunchDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择车体上线日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="门架合装完工期" prop="completionPeriodOfDoorFrameAssembly">
          <el-date-picker clearable
            v-model="form.completionPeriodOfDoorFrameAssembly"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择门架合装完工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="试车完工期" prop="trialCompletionPeriod">
          <el-date-picker clearable
            v-model="form.trialCompletionPeriod"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择试车完工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="特种作业" prop="specialOperations">
          <el-input v-model="form.specialOperations" placeholder="请输入特种作业" />
        </el-form-item>
        <el-form-item label="精整完工期" prop="precisionCompletionPeriod">
          <el-date-picker clearable
            v-model="form.precisionCompletionPeriod"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择精整完工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="生产现场问题" prop="productionSiteissues">
          <el-input v-model="form.productionSiteissues" placeholder="请输入生产现场问题" />
        </el-form-item>
        <el-form-item label="现场异常问题采购" prop="procurementOfOnSiteAbnormalIssues">
          <el-input v-model="form.procurementOfOnSiteAbnormalIssues" placeholder="请输入现场异常问题采购" />
        </el-form-item>
        <el-form-item label="现场异常问题质量" prop="qualityOfOnSiteAbnormalIssues">
          <el-input v-model="form.qualityOfOnSiteAbnormalIssues" placeholder="请输入现场异常问题质量" />
        </el-form-item>
        <el-form-item label="入库" prop="warehousing">
          <el-input v-model="form.warehousing" placeholder="请输入入库" />
        </el-form-item>
        <el-form-item label="生产周期" prop="productionCycle">
          <el-input v-model="form.productionCycle" placeholder="请输入生产周期" />
        </el-form-item>
        <el-form-item label="未报工原因" prop="goLive">
          <el-input v-model="form.goLive" placeholder="请输入上线" />
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
import { listCVimport, getCVimport, delCVimport, addCVimport, updateCVimport,uploadFile } from "@/api/market/CVimport";
import axios from "axios";
//

export default {
  name: "CVimport",
  data() {
    const validateAlphanumeric = (rule, value, callback) => {
      if (value && !/^[a-zA-Z0-9]+$/.test(value)) {
        callback(new Error('只能输入英文字符或数字'));
      } else {
        callback();
      }
    };

    const validateChineseOrEnglish = (rule, value, callback) => {
      if (value && !/^[\u4e00-\u9fa5a-zA-Z]+$/.test(value)) {
        callback(new Error('只能输入中文字符或英文字符'));
      } else {
        callback();
      }
    };

    const validateNumber = (rule, value, callback) => {
      if (value && !/^\d+$/.test(value)) {
        callback(new Error('只能输入数字'));
      } else {
        callback();
      }
    };
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
      // 商品车台账表格数据
      CVimportList: [],
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
        acceptanceDate: null,
        launchDate: null,
        orderNumber: null,
        contractNumber: null,
        carNumber: null,
        vehicleModel: null,
        doorFrameHeight: null,
        number: null,
        accessory: null,
        valveBlock: null,
        configuration: null,
        plannedCompletionPeriod: null,
        customer: null,
        vehicleLaunchDate: null,
        completionPeriodOfDoorFrameAssembly: null,
        trialCompletionPeriod: null,
        specialOperations: null,
        precisionCompletionPeriod: null,
        productionSiteissues: null,
        procurementOfOnSiteAbnormalIssues: null,
        qualityOfOnSiteAbnormalIssues: null,
        goLive: null,
        warehousing: null,
        productionCycle: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        createdBy: [
          { required: false },
          { validator: validateChineseOrEnglish, trigger: 'blur' }
        ],
        orderNumber: [
          { required: false },
          { validator: validateAlphanumeric, trigger: 'blur' }
        ],
        contractNumber: [
          { required: false },
          { validator: validateAlphanumeric, trigger: 'blur' }
        ],
        carNumber: [
          { required: true, message: '请输入车号', trigger: 'blur' },
          { validator: validateAlphanumeric, trigger: 'blur' }
        ],
        vehicleModel: [
          { required: false },
          { validator: validateAlphanumeric, trigger: 'blur' }
        ],
        doorFrameHeight: [
          { required: false },
          { validator: validateAlphanumeric, trigger: 'blur' }
        ],
        number: [
          { required: false },
          { validator: validateNumber, trigger: 'blur' }
        ],
        valveBlock: [
          { required: false },
          { validator: validateNumber, trigger: 'blur' }
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
    /** 查询商品车台账列表 */
    getList() {
      this.loading = true;
      listCVimport(this.queryParams).then(response => {
        this.CVimportList = response.rows;
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
        mcvId: null,
        createdBy: null,
        createdTime: null,
        acceptanceDate: null,
        launchDate: null,
        orderNumber: null,
        contractNumber: null,
        carNumber: null,
        vehicleModel: null,
        doorFrameHeight: null,
        Number: null,
        Accessory: null,
        valveBlock: null,
        Configuration: null,
        plannedCompletionPeriod: null,
        Customer: null,
        vehicleLaunchDate: null,
        completionPeriodOfDoorFrameAssembly: null,
        trialCompletionPeriod: null,
        specialOperations: null,
        precisionCompletionPeriod: null,
        productionSiteissues: null,
        procurementOfOnSiteAbnormalIssues: null,
        qualityOfOnSiteAbnormalIssues: null,
        goLive: null,
        Warehousing: null,
        productionCycle: null
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
      this.ids = selection.map(item => item.mcvId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加商品车台账";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mcvId = row.mcvId || this.ids
      getCVimport(mcvId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改商品车台账";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mcvId != null) {
            updateCVimport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCVimport(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
        else {
          console.log('表单验证失败!');
          return false;
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const mcvIds = row.mcvId || this.ids;
      this.$modal.confirm('是否确认删除商品车台账编号为"' + mcvIds + '"的数据项？').then(function() {
        return delCVimport(mcvIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/CVimport/export', {
        ...this.queryParams
      }, `CVimport_${new Date().getTime()}.xlsx`)
    },


    // fileSend() {
    //   const formData = new FormData();
    //   const file = document.getElementById("inputFile").files[0]; // 获取文件对象
    //   console.log(file);
    //   formData.append("file", file);
    //   console.log("file====>",formData)
    //   axios({
    //     method: "post",
    //     // this $axios.post,
    //     url: "http://localhost:8080/market/CVimport/CVimport",
    //     // params:{
    //     //   userName: this.$store.state.user.name,
    //     // },
    //     headers: {
    //       "Content-Type": "multipart/form-data",
    //     },
    //     withCredentials: true,
    //     data: formData,
    //     onUploadProgress: (progressEvent) => {
    //       this.progress = Math.round(
    //         (progressEvent.loaded * 100) / progressEvent.total
    //       );
    //     },
    //   });
    //   // this.$message.success("上传成功");
    //
    //
    //   setTimeout(() => {
    //     this.showDialog = false; // 关闭上传面板
    //
    //     // location.reload(); // 调用此方法刷新页面数据
    //   }, 2000); // 2000毫秒后关闭
    // },
    async fileSend() {
      try {
        const fileInput = document.getElementById("inputFile");
        if (!fileInput.files.length) {
          this.$message.error("请选择上传文件");
          return;
        }
        const formData = new FormData();
        const file = document.getElementById("inputFile").files[0]; // 获取文件对象
        console.log(file);
        formData.append("file", file);
        console.log("file====>", formData);
        await uploadFile(formData); // 调用 uploadFile API

        // 上传成功后的处理
        this.$message.success("上传成功"); // 提示上传成功
        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板
          // location.reload(); // 调用此方法刷新页面数据
        }, 1000); // 2000毫秒后关闭
        this.getList();
      } catch (error) {
        console.error('上传失败!', error);
      }
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

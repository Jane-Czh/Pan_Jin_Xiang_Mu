<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="编号" prop="Number">
        <el-input
          v-model="queryParams.Number"
          placeholder="请输入编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户单位" prop="userUnit">
        <el-input
          v-model="queryParams.userUnit"
          placeholder="请输入用户单位"
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
      <el-form-item label="联系电话" prop="Phone">
        <el-input
          v-model="queryParams.Phone"
          placeholder="请输入联系电话"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="反馈日期" prop="feedbackDate">
        <el-date-picker clearable
          v-model="queryParams.feedbackDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择反馈日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="整车生产单位" prop="vehicleProductionUnit">
        <el-input
          v-model="queryParams.vehicleProductionUnit"
          placeholder="请输入整车生产单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车" prop="Vehicle">
        <el-input
          v-model="queryParams.Vehicle"
          placeholder="请输入车"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="吨" prop="Ton">
        <el-input
          v-model="queryParams.Ton"
          placeholder="请输入吨"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发" prop="Launch">
        <el-input
          v-model="queryParams.Launch"
          placeholder="请输入发"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车号" prop="wagonNumber">
        <el-input
          v-model="queryParams.wagonNumber"
          placeholder="请输入车号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="销售时间" prop="sellingTime">
        <el-date-picker clearable
          v-model="queryParams.sellingTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择销售时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="使用时间" prop="useHours">
        <el-input
          v-model="queryParams.useHours"
          placeholder="请输入使用时间"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否新车" prop="newOrNot">
        <el-input
          v-model="queryParams.newOrNot"
          placeholder="请输入是否新车"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="存在问题" prop="existingProblem">
        <el-input
          v-model="queryParams.existingProblem"
          placeholder="请输入存在问题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一级分类" prop="primaryClassification">
        <el-input
          v-model="queryParams.primaryClassification"
          placeholder="请输入一级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二级分类" prop="secondaryClassification">
        <el-input
          v-model="queryParams.secondaryClassification"
          placeholder="请输入二级分类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="最终处理方法" prop="finalTreatmentMethod">
        <el-input
          v-model="queryParams.finalTreatmentMethod"
          placeholder="请输入最终处理方法"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="赔偿件" prop="Indemnity">
        <el-input
          v-model="queryParams.Indemnity"
          placeholder="请输入赔偿件"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="零件编号" prop="partNumber">
        <el-input
          v-model="queryParams.partNumber"
          placeholder="请输入零件编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商" prop="Supplier">
        <el-input
          v-model="queryParams.Supplier"
          placeholder="请输入供应商"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="Quantity">
        <el-input
          v-model="queryParams.Quantity"
          placeholder="请输入数量"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="责任单位" prop="accountabilityUnit">
        <el-input
          v-model="queryParams.accountabilityUnit"
          placeholder="请输入责任单位"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="故障级别" prop="faultLevel">
        <el-input
          v-model="queryParams.faultLevel"
          placeholder="请输入故障级别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="评分" prop="Score">
        <el-input
          v-model="queryParams.Score"
          placeholder="请输入评分"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发件方式" prop="sendingMethod">
        <el-input
          v-model="queryParams.sendingMethod"
          placeholder="请输入发件方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="一级网点" prop="primaryBranch">
        <el-input
          v-model="queryParams.primaryBranch"
          placeholder="请输入一级网点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="二级网点" prop="secondaryBranch">
        <el-input
          v-model="queryParams.secondaryBranch"
          placeholder="请输入二级网点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发货方式" prop="despatchMode">
        <el-input
          v-model="queryParams.despatchMode"
          placeholder="请输入发货方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="结算方式" prop="paymentsMode">
        <el-input
          v-model="queryParams.paymentsMode"
          placeholder="请输入结算方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="反馈方式" prop="feedbackMode">
        <el-input
          v-model="queryParams.feedbackMode"
          placeholder="请输入反馈方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="预留单号" prop="reservationNumber">
        <el-input
          v-model="queryParams.reservationNumber"
          placeholder="请输入预留单号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考核情况" prop="assessmentSituation">
        <el-input
          v-model="queryParams.assessmentSituation"
          placeholder="请输入考核情况"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['market:ledger:add']"
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
          v-hasPermi="['market:ledger:edit']"
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
          v-hasPermi="['market:ledger:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:ledger:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->

        <!-- <el-button
          type="primary"
          @click="showDialog = true"
        ><i class="fa fa-download"></i>导入Excel文件
        </el-button> -->

        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['market:import:export']"
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

    <el-table v-loading="loading" :data="ledgerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="maslId" />
      <el-table-column label="编号" align="center" prop="Number" />
      <el-table-column label="用户单位" align="center" prop="userUnit" />
      <el-table-column label="联系人" align="center" prop="Contacts" />
      <el-table-column label="联系电话" align="center" prop="Phone" />
      <el-table-column label="反馈日期" align="center" prop="feedbackDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.feedbackDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="整车生产单位" align="center" prop="vehicleProductionUnit" />
      <el-table-column label="车" align="center" prop="Vehicle" />
      <el-table-column label="吨" align="center" prop="Ton" />
      <el-table-column label="发" align="center" prop="Launch" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="车号" align="center" prop="wagonNumber" />
      <el-table-column label="销售时间" align="center" prop="sellingTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.sellingTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="使用时间" align="center" prop="useHours" />
      <el-table-column label="是否新车" align="center" prop="newOrNot" />
      <el-table-column label="存在问题" align="center" prop="existingProblem" />
      <el-table-column label="一级分类" align="center" prop="primaryClassification" />
      <el-table-column label="二级分类" align="center" prop="secondaryClassification" />
      <el-table-column label="最终处理方法" align="center" prop="finalTreatmentMethod" />
      <el-table-column label="赔偿件" align="center" prop="Indemnity" />
      <el-table-column label="零件编号" align="center" prop="partNumber" />
      <el-table-column label="供应商" align="center" prop="Supplier" />
      <el-table-column label="数量" align="center" prop="Quantity" />
      <el-table-column label="责任单位" align="center" prop="accountabilityUnit" />
      <el-table-column label="故障级别" align="center" prop="faultLevel" />
      <el-table-column label="评分" align="center" prop="Score" />
      <el-table-column label="发件方式" align="center" prop="sendingMethod" />
      <el-table-column label="一级网点" align="center" prop="primaryBranch" />
      <el-table-column label="二级网点" align="center" prop="secondaryBranch" />
      <el-table-column label="发货方式" align="center" prop="despatchMode" />
      <el-table-column label="结算方式" align="center" prop="paymentsMode" />
      <el-table-column label="反馈方式" align="center" prop="feedbackMode" />
      <el-table-column label="预留单号" align="center" prop="reservationNumber" />
      <el-table-column label="考核情况" align="center" prop="assessmentSituation" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:ledger:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:ledger:remove']"
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

    <!-- 添加或修改售后台账导入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="编号" prop="Number">
          <el-input v-model="form.Number" placeholder="请输入编号" />
        </el-form-item>
        <el-form-item label="用户单位" prop="userUnit">
          <el-input v-model="form.userUnit" placeholder="请输入用户单位" />
        </el-form-item>
        <el-form-item label="联系人" prop="Contacts">
          <el-input v-model="form.Contacts" placeholder="请输入联系人" />
        </el-form-item>
        <el-form-item label="联系电话" prop="Phone">
          <el-input v-model="form.Phone" placeholder="请输入联系电话" />
        </el-form-item>
        <el-form-item label="反馈日期" prop="feedbackDate">
          <el-date-picker clearable
            v-model="form.feedbackDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择反馈日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="整车生产单位" prop="vehicleProductionUnit">
          <el-input v-model="form.vehicleProductionUnit" placeholder="请输入整车生产单位" />
        </el-form-item>
        <el-form-item label="车" prop="Vehicle">
          <el-input v-model="form.Vehicle" placeholder="请输入车" />
        </el-form-item>
        <el-form-item label="吨" prop="Ton">
          <el-input v-model="form.Ton" placeholder="请输入吨" />
        </el-form-item>
        <el-form-item label="发" prop="Launch">
          <el-input v-model="form.Launch" placeholder="请输入发" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="车号" prop="wagonNumber">
          <el-input v-model="form.wagonNumber" placeholder="请输入车号" />
        </el-form-item>
        <el-form-item label="销售时间" prop="sellingTime">
          <el-date-picker clearable
            v-model="form.sellingTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择销售时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="使用时间" prop="useHours">
          <el-input v-model="form.useHours" placeholder="请输入使用时间" />
        </el-form-item>
        <el-form-item label="是否新车" prop="newOrNot">
          <el-input v-model="form.newOrNot" placeholder="请输入是否新车" />
        </el-form-item>
        <el-form-item label="存在问题" prop="existingProblem">
          <el-input v-model="form.existingProblem" placeholder="请输入存在问题" />
        </el-form-item>
        <el-form-item label="一级分类" prop="primaryClassification">
          <el-input v-model="form.primaryClassification" placeholder="请输入一级分类" />
        </el-form-item>
        <el-form-item label="二级分类" prop="secondaryClassification">
          <el-input v-model="form.secondaryClassification" placeholder="请输入二级分类" />
        </el-form-item>
        <el-form-item label="最终处理方法" prop="finalTreatmentMethod">
          <el-input v-model="form.finalTreatmentMethod" placeholder="请输入最终处理方法" />
        </el-form-item>
        <el-form-item label="赔偿件" prop="Indemnity">
          <el-input v-model="form.Indemnity" placeholder="请输入赔偿件" />
        </el-form-item>
        <el-form-item label="零件编号" prop="partNumber">
          <el-input v-model="form.partNumber" placeholder="请输入零件编号" />
        </el-form-item>
        <el-form-item label="供应商" prop="Supplier">
          <el-input v-model="form.Supplier" placeholder="请输入供应商" />
        </el-form-item>
        <el-form-item label="数量" prop="Quantity">
          <el-input v-model="form.Quantity" placeholder="请输入数量" />
        </el-form-item>
        <el-form-item label="责任单位" prop="accountabilityUnit">
          <el-input v-model="form.accountabilityUnit" placeholder="请输入责任单位" />
        </el-form-item>
        <el-form-item label="故障级别" prop="faultLevel">
          <el-input v-model="form.faultLevel" placeholder="请输入故障级别" />
        </el-form-item>
        <el-form-item label="评分" prop="Score">
          <el-input v-model="form.Score" placeholder="请输入评分" />
        </el-form-item>
        <el-form-item label="发件方式" prop="sendingMethod">
          <el-input v-model="form.sendingMethod" placeholder="请输入发件方式" />
        </el-form-item>
        <el-form-item label="一级网点" prop="primaryBranch">
          <el-input v-model="form.primaryBranch" placeholder="请输入一级网点" />
        </el-form-item>
        <el-form-item label="二级网点" prop="secondaryBranch">
          <el-input v-model="form.secondaryBranch" placeholder="请输入二级网点" />
        </el-form-item>
        <el-form-item label="发货方式" prop="despatchMode">
          <el-input v-model="form.despatchMode" placeholder="请输入发货方式" />
        </el-form-item>
        <el-form-item label="结算方式" prop="paymentsMode">
          <el-input v-model="form.paymentsMode" placeholder="请输入结算方式" />
        </el-form-item>
        <el-form-item label="反馈方式" prop="feedbackMode">
          <el-input v-model="form.feedbackMode" placeholder="请输入反馈方式" />
        </el-form-item>
        <el-form-item label="预留单号" prop="reservationNumber">
          <el-input v-model="form.reservationNumber" placeholder="请输入预留单号" />
        </el-form-item>
        <el-form-item label="考核情况" prop="assessmentSituation">
          <el-input v-model="form.assessmentSituation" placeholder="请输入考核情况" />
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
import { listLedger, getLedger, delLedger, addLedger, updateLedger } from "@/api/market/ledger";
import axios from 'axios'

export default {
  name: "Ledger",
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
      // 售后台账导入表格数据
      ledgerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Number: null,
        userUnit: null,
        Contacts: null,
        Phone: null,
        feedbackDate: null,
        vehicleProductionUnit: null,
        Vehicle: null,
        Ton: null,
        Launch: null,
        vehicleModel: null,
        wagonNumber: null,
        sellingTime: null,
        useHours: null,
        newOrNot: null,
        existingProblem: null,
        primaryClassification: null,
        secondaryClassification: null,
        finalTreatmentMethod: null,
        Indemnity: null,
        partNumber: null,
        Supplier: null,
        Quantity: null,
        accountabilityUnit: null,
        faultLevel: null,
        Score: null,
        sendingMethod: null,
        primaryBranch: null,
        secondaryBranch: null,
        despatchMode: null,
        paymentsMode: null,
        feedbackMode: null,
        reservationNumber: null,
        assessmentSituation: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
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
    /** 查询售后台账导入列表 */
    getList() {
      this.loading = true;
      listLedger(this.queryParams).then(response => {
        this.ledgerList = response.rows;
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
        maslId: null,
        Number: null,
        userUnit: null,
        Contacts: null,
        Phone: null,
        feedbackDate: null,
        vehicleProductionUnit: null,
        Vehicle: null,
        Ton: null,
        Launch: null,
        vehicleModel: null,
        wagonNumber: null,
        sellingTime: null,
        useHours: null,
        newOrNot: null,
        existingProblem: null,
        primaryClassification: null,
        secondaryClassification: null,
        finalTreatmentMethod: null,
        Indemnity: null,
        partNumber: null,
        Supplier: null,
        Quantity: null,
        accountabilityUnit: null,
        faultLevel: null,
        Score: null,
        sendingMethod: null,
        primaryBranch: null,
        secondaryBranch: null,
        despatchMode: null,
        paymentsMode: null,
        feedbackMode: null,
        reservationNumber: null,
        assessmentSituation: null
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
      this.ids = selection.map(item => item.maslId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加售后台账导入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const maslId = row.maslId || this.ids
      getLedger(maslId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改售后台账导入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.maslId != null) {
            updateLedger(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLedger(this.form).then(response => {
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
      const maslIds = row.maslId || this.ids;
      this.$modal.confirm('是否确认删除售后台账导入编号为"' + maslIds + '"的数据项？').then(function() {
        return delLedger(maslIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/ledger/export', {
        ...this.queryParams
      }, `ledger_${new Date().getTime()}.xlsx`)
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
        url: "http://localhost:8080/market/ledger/import",
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

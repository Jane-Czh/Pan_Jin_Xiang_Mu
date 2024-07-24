<template>
  <div class="app-container">
<!--    <div>-->
<!--      <el-select v-model="selectedItem" placeholder="选择统计数据">-->
<!--        <el-option :label="'本日车体上线总数: ' + totalNumberWaitingTimeForProduction1" :value="1" />-->
<!--        <el-option :label="'本日门架合装完工总数: ' + totalNumberCompletionPeriodOfDoorFrameAssembly1" :value="2" />-->
<!--        <el-option :label="'本日试车完工总数: ' + totalNumberTrialCompletionPeriod1" :value="3" />-->
<!--        <el-option :label="'本日特种作业总数: ' + totalNumberSpecialOperations1" :value="4" />-->
<!--        <el-option :label="'本日精整完工总数: ' + totalNumberPrecisionCompletionPeriod1" :value="5" />-->
<!--      </el-select>-->
<!--    </div>-->
    <div class="list-container">
      <div class="list-item">
        <i class="el-icon-data-line"></i>
        <span class="list-title">本日车体上线总数</span>
        <span class="list-value">{{ totalNumberWaitingTimeForProduction1 }}</span>
      </div>
      <div class="list-item">
        <i class="el-icon-data-line"></i>
        <span class="list-title">本日门架合装完工总数</span>
        <span class="list-value">{{ totalNumberCompletionPeriodOfDoorFrameAssembly1 }}</span>
      </div>
      <div class="list-item">
        <i class="el-icon-data-line"></i>
        <span class="list-title">本日试车完工总数</span>
        <span class="list-value">{{ totalNumberTrialCompletionPeriod1 }}</span>
      </div>
      <div class="list-item">
        <i class="el-icon-data-line"></i>
        <span class="list-title">本日特种作业总数</span>
        <span class="list-value">{{ totalNumberSpecialOperations1 }}</span>
      </div>
      <div class="list-item">
        <i class="el-icon-data-line"></i>
        <span class="list-title">本日精整完工总数</span>
        <span class="list-value">{{ totalNumberPrecisionCompletionPeriod1 }}</span>
      </div>
    </div>
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">

<!--      <el-form-item label="车号" prop="carNumber">
        <el-input
          v-model="queryParams.carNumber"
          placeholder="请输入车号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="接单日期" prop="acceptanceDate">
        <el-date-picker clearable
                        v-model="queryParams.acceptanceDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择接单日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上线日期" prop="launchDate">
        <el-date-picker clearable
                        v-model="queryParams.launchDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择上线日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="待排产时长" prop="waitingTimeForProduction">
        <el-input
          v-model="queryParams.waitingTimeForProduction"
          placeholder="请输入待排产时长"
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
      <el-form-item label="计划完工期" prop="plannedCompletionPeriod">
        <el-date-picker clearable
                        v-model="queryParams.plannedCompletionPeriod"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择计划完工期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="生产周期" prop="productionCycle">
        <el-input
          v-model="queryParams.productionCycle"
          placeholder="请输入生产周期"
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
      <el-form-item label="超期天数" prop="overdueDays">
        <el-input
          v-model="queryParams.overdueDays"
          placeholder="请输入超期天数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车体上线日期总条数" prop="totalNumberWaitingTimeForProduction">
        <el-input
          v-model="queryParams.totalNumberWaitingTimeForProduction"
          placeholder="请输入车体上线日期总条数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="门架合装完工期总条数" prop="totalNumberCompletionPeriodOfDoorFrameAssembly">
        <el-input
          v-model="queryParams.totalNumberCompletionPeriodOfDoorFrameAssembly"
          placeholder="请输入门架合装完工期总条数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="试车完工期总条数" prop="totalNumberTrialCompletionPeriod">
        <el-input
          v-model="queryParams.totalNumberTrialCompletionPeriod"
          placeholder="请输入试车完工期总条数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="特种作业总条数" prop="totalNumberSpecialOperations">
        <el-input
          v-model="queryParams.totalNumberSpecialOperations"
          placeholder="请输入特种作业总条数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="精整完工期总条数" prop="totalNumberPrecisionCompletionPeriod">
        <el-input
          v-model="queryParams.totalNumberPrecisionCompletionPeriod"
          placeholder="请输入精整完工期总条数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划兑现率" prop="planRate">
        <el-input
          v-model="queryParams.planRate"
          placeholder="请输入计划兑现率"
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
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['product:status:add']"-->
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
<!--          v-hasPermi="['product:status:edit']"-->
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
          v-hasPermi="['product:status:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['product:status:export']"-->
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
<!--      <div class="echarts-wrapper">-->
<!--        <div-->
<!--          class="echart"-->
<!--          ref="orderReceivedToday"-->
<!--          id="totalNumberWaitingTimeForProduction"-->
<!--          :style="{ width: '40%', height: '40px', display: 'inline-block' }"-->
<!--        ></div>-->
<!--        <div-->
<!--          class="echart"-->
<!--          ref="orderReceivedToday"-->
<!--          id="totalNumberCompletionPeriodOfDoorFrameAssembly"-->
<!--          :style="{ width: '40%', height: '40px', display: 'inline-block' }"-->
<!--        ></div>-->
<!--        <div-->
<!--          class="echart"-->
<!--          ref="orderReceivedToday"-->
<!--          id="totalNumberTrialCompletionPeriod"-->
<!--          :style="{ width: '40%', height: '40px', display: 'inline-block' }"-->
<!--        ></div>-->
<!--        <div-->
<!--          class="echart"-->
<!--          ref="orderReceivedToday"-->
<!--          id="totalNumberSpecialOperations"-->
<!--          :style="{ width: '40%', height: '40px', display: 'inline-block' }"-->
<!--        ></div>-->
<!--        <div-->
<!--          class="echart"-->
<!--          ref="orderReceivedToday"-->
<!--          id="totalNumberPrecisionCompletionPeriod"-->
<!--          :style="{ width: '40%', height: '40px', display: 'inline-block' }"-->
<!--        ></div>-->
<!--      </div>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="statusList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="MPC_ID" align="center" prop="mpcId" />-->
      <el-table-column label="车号" align="center" prop="carNumber" />
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
      <el-table-column label="待排产时长" align="center" prop="waitingTimeForProduction" />
      <el-table-column label="车体上线日期" align="center" prop="vehicleLaunchDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.vehicleLaunchDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="计划完工期" align="center" prop="plannedCompletionPeriod" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.plannedCompletionPeriod, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生产周期" align="center" prop="productionCycle" />
      <el-table-column label="精整完工期" align="center" prop="precisionCompletionPeriod" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.precisionCompletionPeriod, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="超期天数" align="center" prop="overdueDays" />
<!--      <el-table-column label="车体上线日期总条数" align="center" prop="totalNumberWaitingTimeForProduction" />-->
<!--      <el-table-column label="门架合装完工期总条数" align="center" prop="totalNumberCompletionPeriodOfDoorFrameAssembly" />-->
<!--      <el-table-column label="试车完工期总条数" align="center" prop="totalNumberTrialCompletionPeriod" />-->
<!--      <el-table-column label="特种作业总条数" align="center" prop="totalNumberSpecialOperations" />-->
<!--      <el-table-column label="精整完工期总条数" align="center" prop="totalNumberPrecisionCompletionPeriod" />-->
<!--      <el-table-column label="计划兑现率" align="center" prop="planRate" />-->
      <el-table-column label="计划兑现率" align="center">
        <template slot-scope="scope">
          {{ scope.row.planRate }}%
        </template>
      </el-table-column>
<!--      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-edit"-->
<!--            @click="handleUpdate(scope.row)"-->
<!--            v-hasPermi="['product:status:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['product:status:remove']"-->
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

    <!-- 添加或修改计划完成情况对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车号" prop="carNumber">
          <el-input v-model="form.carNumber" placeholder="请输入车号" />
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
        <el-form-item label="待排产时长" prop="waitingTimeForProduction">
          <el-input v-model="form.waitingTimeForProduction" placeholder="请输入待排产时长" />
        </el-form-item>
        <el-form-item label="车体上线日期" prop="vehicleLaunchDate">
          <el-date-picker clearable
                          v-model="form.vehicleLaunchDate"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择车体上线日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="计划完工期" prop="plannedCompletionPeriod">
          <el-date-picker clearable
                          v-model="form.plannedCompletionPeriod"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择计划完工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="生产周期" prop="productionCycle">
          <el-input v-model="form.productionCycle" placeholder="请输入生产周期" />
        </el-form-item>
        <el-form-item label="精整完工期" prop="precisionCompletionPeriod">
          <el-date-picker clearable
                          v-model="form.precisionCompletionPeriod"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择精整完工期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="超期天数" prop="overdueDays">
          <el-input v-model="form.overdueDays" placeholder="请输入超期天数" />
        </el-form-item>
        <el-form-item label="车体上线日期总条数" prop="totalNumberWaitingTimeForProduction">
          <el-input v-model="form.totalNumberWaitingTimeForProduction" placeholder="请输入车体上线日期总条数" />
        </el-form-item>
        <el-form-item label="门架合装完工期总条数" prop="totalNumberCompletionPeriodOfDoorFrameAssembly">
          <el-input v-model="form.totalNumberCompletionPeriodOfDoorFrameAssembly" placeholder="请输入门架合装完工期总条数" />
        </el-form-item>
        <el-form-item label="试车完工期总条数" prop="totalNumberTrialCompletionPeriod">
          <el-input v-model="form.totalNumberTrialCompletionPeriod" placeholder="请输入试车完工期总条数" />
        </el-form-item>
        <el-form-item label="特种作业总条数" prop="totalNumberSpecialOperations">
          <el-input v-model="form.totalNumberSpecialOperations" placeholder="请输入特种作业总条数" />
        </el-form-item>
        <el-form-item label="精整完工期总条数" prop="totalNumberPrecisionCompletionPeriod">
          <el-input v-model="form.totalNumberPrecisionCompletionPeriod" placeholder="请输入精整完工期总条数" />
        </el-form-item>
        <el-form-item label="计划兑现率" prop="planRate">
          <el-input v-model="form.planRate" placeholder="请输入计划兑现率" />
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
import {listStatus, getStatus, delStatus, addStatus, updateStatus, syncReport} from "@/api/product/status";


export default {
  name: "Status",
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
      // 计划完成情况表格数据
      statusList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        carNumber: null,
        acceptanceDate: null,
        launchDate: null,
        waitingTimeForProduction: null,
        vehicleLaunchDate: null,
        plannedCompletionPeriod: null,
        productionCycle: null,
        precisionCompletionPeriod: null,
        overdueDays: null,
        totalNumberWaitingTimeForProduction: null,
        totalNumberCompletionPeriodOfDoorFrameAssembly: null,
        totalNumberTrialCompletionPeriod: null,
        totalNumberSpecialOperations: null,
        totalNumberPrecisionCompletionPeriod: null,
        planRate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      totalNumberWaitingTimeForProduction1:null,
      totalNumberCompletionPeriodOfDoorFrameAssembly1:null,
      totalNumberTrialCompletionPeriod1:null,
      totalNumberSpecialOperations1:null,
      totalNumberPrecisionCompletionPeriod1:null,
    };
  },
  created() {
    this.getList();
  },
  methods: {


    /*同步*/
    // syncReport() {
    //   // 使用 Fetch API 发送 POST 请求到后端
    //   fetch('http://localhost:8080/product/status/synchronization', {
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
    //   this.getList();
    // },
    async syncReport() {
      try {
        await syncReport();
        this.getList();
        this.$modal.msgSuccess("同步成功"); // 新增的提示
      } catch (error) {
        console.error('同步失败!', error);
      }
      this.reload();
    },
    // /** 查询计划完成情况列表 */
    getList() {
      this.loading = true;
      listStatus(this.queryParams).then(response => {
        this.statusList = response.rows;
        if (this.statusList.length>0) {
          this.totalNumberWaitingTimeForProduction1 = this.statusList[0].totalNumberWaitingTimeForProduction;
          this.totalNumberCompletionPeriodOfDoorFrameAssembly1 = this.statusList[0].totalNumberCompletionPeriodOfDoorFrameAssembly;
          this.totalNumberTrialCompletionPeriod1 = this.statusList[0].totalNumberTrialCompletionPeriod;
          this.totalNumberSpecialOperations1 = this.statusList[0].totalNumberSpecialOperations;
          this.totalNumberPrecisionCompletionPeriod1 = this.statusList[0].totalNumberPrecisionCompletionPeriod;
        }
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
        mpcId: null,
        carNumber: null,
        acceptanceDate: null,
        launchDate: null,
        waitingTimeForProduction: null,
        vehicleLaunchDate: null,
        plannedCompletionPeriod: null,
        productionCycle: null,
        precisionCompletionPeriod: null,
        overdueDays: null,
        totalNumberWaitingTimeForProduction: null,
        totalNumberCompletionPeriodOfDoorFrameAssembly: null,
        totalNumberTrialCompletionPeriod: null,
        totalNumberSpecialOperations: null,
        totalNumberPrecisionCompletionPeriod: null,
        planRate: null
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
      this.ids = selection.map(item => item.mpcId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加计划完成情况";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mpcId = row.mpcId || this.ids
      getStatus(mpcId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改计划完成情况";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mpcId != null) {
            updateStatus(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addStatus(this.form).then(response => {
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
      const mpcIds = row.mpcId || this.ids;
      this.$modal.confirm('是否确认删除计划完成情况编号为"' + mpcIds + '"的数据项？').then(function() {
        return delStatus(mpcIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/status/export', {
        ...this.queryParams
      }, `status_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
<style scoped>
.list-container {
  display: flex;
  justify-content: space-between;
  width: 100%;
  padding: 0 20px;
  margin-bottom: 10px;
}

.list-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  background-color: #fff;
  transition: transform 0.2s, box-shadow 0.2s;
  width: 18%;
  height: 100px;
}

.list-item:hover {
  transform: translateY(-5px);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.list-item i {
  font-size: 24px;
  margin-bottom: 10px;
}

.list-title {
  font-size: 16px;
  font-weight: bold;
  color: #333;
  margin-bottom: 5px;
}

.list-value {
  font-size: 18px;
  color: #666;
}
</style>

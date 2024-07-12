<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="交货天数" prop="numberOfWeekDelivery">-->
<!--        <el-input-->
<!--          v-model="queryParams.numberOfWeekDelivery"-->
<!--          placeholder="请输入交货天数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="交货天数" prop="numberOfDayDelivery">-->
<!--        <el-input-->
<!--          v-model="queryParams.numberOfDayDelivery"-->
<!--          placeholder="请输入交货天数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车数" prop="carNumberDay">-->
<!--        <el-input-->
<!--          v-model="queryParams.carNumberDay"-->
<!--          placeholder="请输入车数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车型" prop="vehicleModel">-->
<!--        <el-input-->
<!--          v-model="queryParams.vehicleModel"-->
<!--          placeholder="请输入车型"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="精整完工期" prop="precisionCompletionPeriod">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.precisionCompletionPeriod"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择精整完工期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="接单日期" prop="acceptanceDate">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.acceptanceDate"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择接单日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="上线日期" prop="launchDate">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.launchDate"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择上线日期">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="吨位" prop="tonnage">-->
<!--        <el-input-->
<!--          v-model="queryParams.tonnage"-->
<!--          placeholder="请输入吨位"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="车数" prop="carNumberWeek">-->
<!--        <el-input-->
<!--          v-model="queryParams.carNumberWeek"-->
<!--          placeholder="请输入车数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="累计天数" prop="cumulativeNumberOfDays">-->
<!--        <el-input-->
<!--          v-model="queryParams.cumulativeNumberOfDays"-->
<!--          placeholder="请输入累计天数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="累计天数" prop="cumulativeNumberOfWeek">-->
<!--        <el-input-->
<!--          v-model="queryParams.cumulativeNumberOfWeek"-->
<!--          placeholder="请输入累计天数"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="时间" prop="createTime">-->
<!--        <el-date-picker clearable-->
<!--                        v-model="queryParams.createTime"-->
<!--                        type="date"-->
<!--                        value-format="yyyy-MM-dd"-->
<!--                        placeholder="请选择时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <!-- 饼状图部分 -->
      <div class="echarts-wrapper">
        <div
          class="echart"
          ref="orderReceivedToday"
          id="today"
          :style="{ width: '40%', height: '400px', display: 'inline-block' }"
        ></div>
        <div
          class="echart"
          ref="ordersReceivedThisMonth"
          id="week"
          :style="{ width: '40%', height: '400px', display: 'inline-block' }"
        ></div>
      </div>
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
<!--          v-hasPermi="['market:compare:add']"-->
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
<!--          v-hasPermi="['market:compare:edit']"-->
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
          v-hasPermi="['market:compare:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['market:compare:export']"-->
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

    <el-table v-loading="loading" :data="compareList" @selection-change="handleSelectionChange" @sort-change="handleSortChanged">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="ID" align="center" prop="mfcdId" />-->
      <el-table-column label="车型（前缀吨位）" align="center" prop="vehicleModel" />
      <el-table-column label="每天平均交货天数" align="center" prop="numberOfDayDelivery" />
      <el-table-column label="每周平均交货天数" align="center" prop="numberOfWeekDelivery" />
      <el-table-column label="操作时间" align="center" prop="createTime" width="180" sortable="custom">
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
<!--            v-hasPermi="['market:compare:edit']"-->
<!--          >修改</el-button>-->
<!--          <el-button-->
<!--            size="mini"-->
<!--            type="text"-->
<!--            icon="el-icon-delete"-->
<!--            @click="handleDelete(scope.row)"-->
<!--            v-hasPermi="['market:compare:remove']"-->
<!--          >删除</el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="syncReport"
        >同步数据</el-button>
      </el-col>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改同配置车型交货天数对比功能对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="交货天数" prop="numberOfWeekDelivery">
          <el-input v-model="form.numberOfWeekDelivery" placeholder="请输入交货天数" />
        </el-form-item>
        <el-form-item label="交货天数" prop="numberOfDayDelivery">
          <el-input v-model="form.numberOfDayDelivery" placeholder="请输入交货天数" />
        </el-form-item>
        <el-form-item label="车数" prop="carNumberDay">
          <el-input v-model="form.carNumberDay" placeholder="请输入车数" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="精整完工期" prop="precisionCompletionPeriod">
          <el-date-picker clearable
                          v-model="form.precisionCompletionPeriod"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择精整完工期">
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
        <el-form-item label="吨位" prop="tonnage">
          <el-input v-model="form.tonnage" placeholder="请输入吨位" />
        </el-form-item>
        <el-form-item label="车数" prop="carNumberWeek">
          <el-input v-model="form.carNumberWeek" placeholder="请输入车数" />
        </el-form-item>
        <el-form-item label="累计天数" prop="cumulativeNumberOfDays">
          <el-input v-model="form.cumulativeNumberOfDays" placeholder="请输入累计天数" />
        </el-form-item>
        <el-form-item label="累计天数" prop="cumulativeNumberOfWeek">
          <el-input v-model="form.cumulativeNumberOfWeek" placeholder="请输入累计天数" />
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
import {listCompare, getCompare, delCompare, addCompare, updateCompare, syncReport} from "@/api/market/compare";
import * as echarts from "echarts";


export default {
  name: "Compare",
  inject: ["reload"],
  data() {
    return {
      //饼状图数据
      numberOfDayDeliveryData: [],
      numberOfWeekDeliveryData: [],
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
      // 同配置车型交货天数对比功能表格数据
      compareList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        numberOfWeekDelivery: null,
        numberOfDayDelivery: null,
        carNumberDay: null,
        vehicleModel: null,
        precisionCompletionPeriod: null,
        acceptanceDate: null,
        launchDate: null,
        tonnage: null,
        carNumberWeek: null,
        cumulativeNumberOfDays: null,
        cumulativeNumberOfWeek: null,
        createTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        numberOfDayDelivery: [
          { required: true, message: "交货天数不能为空", trigger: "blur" }
        ],
        carNumberDay: [
          { required: true, message: "车数不能为空", trigger: "blur" }
        ],
        vehicleModel: [
          { required: true, message: "车型不能为空", trigger: "blur" }
        ],
        precisionCompletionPeriod: [
          { required: true, message: "精整完工期不能为空", trigger: "blur" }
        ],
        acceptanceDate: [
          { required: true, message: "接单日期不能为空", trigger: "blur" }
        ],
        launchDate: [
          { required: true, message: "上线日期不能为空", trigger: "blur" }
        ],
        tonnage: [
          { required: true, message: "吨位不能为空", trigger: "blur" }
        ],
        carNumberWeek: [
          { required: true, message: "车数不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();

  },
  mounted(){
    // 加载数据后拆分成适合饼状图的格式
    listCompare(this.queryParams).then(response => {
      // 将后端返回的数据赋值给 reportList
      this.compareList = response.rows;
      // 拆分数据为适合饼状图的格式
      // this.numberOfDayDeliveryData = this.compareList.map(item => ({
      //   value: item.numberOfDayDelivery, // 今日平均天数作为值
      //   name: item.vehicleModel // 销售网点名称作为名称
      // }));
      this.numberOfDayDeliveryData = this.compareList.filter(item => {
        const today = new Date();
        const itemCreateDate = new Date(item.createTime);
        return itemCreateDate.toDateString() === today.toDateString();
      }).map(filteredItem => ({
        value: filteredItem.numberOfDayDelivery,
        name: filteredItem.vehicleModel
      }));
      // this.numberOfWeekDeliveryData = this.compareList.map(item => ({
      //   value: item.numberOfWeekDelivery, // 本周平均天数作为值
      //   name: item.vehicleModel // 销售网点名称作为名称
      // }));
      this.numberOfWeekDeliveryData = this.compareList.filter(item => {
        const today = new Date();
        const itemCreateDate = new Date(item.createTime);
        return itemCreateDate.toDateString() === today.toDateString();
      }).map(filteredItem => ({
        value: filteredItem.numberOfWeekDelivery,
        name: filteredItem.vehicleModel
      }));
      this.initday();
      this.initweek();
    });

  },
  methods: {

    handleSortChanged(){

    },
    //
    // syncReport() {
    //   // 使用 Fetch API 发送 POST 请求到后端
    //   fetch('http://localhost:8080/market/compare/synchronization', {
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
        console.error('同步失败!', error);
      }
      this.reload();
    },
    /** 查询同配置车型交货天数对比功能列表 */
    getList() {
      this.loading = true;
      listCompare(this.queryParams).then(response => {
        this.compareList = response.rows.sort((a, b) => new Date(b.createTime) - new Date(a.createTime));
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
        mfcdId: null,
        numberOfWeekDelivery: null,
        numberOfDayDelivery: null,
        carNumberDay: null,
        vehicleModel: null,
        precisionCompletionPeriod: null,
        acceptanceDate: null,
        launchDate: null,
        tonnage: null,
        carNumberWeek: null,
        cumulativeNumberOfDays: null,
        cumulativeNumberOfWeek: null,
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
      this.ids = selection.map(item => item.mfcdId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加同配置车型交货天数对比功能";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mfcdId = row.mfcdId || this.ids
      getCompare(mfcdId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改同配置车型交货天数对比功能";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mfcdId != null) {
            updateCompare(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCompare(this.form).then(response => {
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
      const mfcdIds = row.mfcdId || this.ids;
      this.$modal.confirm('是否确认删除同配置车型交货天数对比功能编号为"' + mfcdIds + '"的数据项？').then(function() {
        return delCompare(mfcdIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/compare/export', {
        ...this.queryParams
      }, `compare_${new Date().getTime()}.xlsx`)
    },
    initday(){
      let chartDom = document.getElementById('today');
      let myChart = echarts.init(chartDom);
      // 生成 x 轴数据
      let xAxisData = this.numberOfDayDeliveryData.map(item => `${item.name} 吨位`);
      let option;

      option = {
        title: {
          text: '本日同配置车型平均交货天数',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        // legend: {
        //   data: ['每日平均交货天数']
        // },
        xAxis: {
          data: xAxisData, // 车型名称作为 x 轴数据
        },
        yAxis: {},
        series: [{
          name: '每日平均交货天数',
          type: 'bar',
          data: this.numberOfDayDeliveryData.map(item => item.value) // 每日平均交货天数作为柱状图数据
        }]
      };

      option && myChart.setOption(option);
    },
    initweek(){
      let chartDom = document.getElementById('week');
      let myChart = echarts.init(chartDom);
      // 生成 x 轴数据
      let xAxisData = this.numberOfDayDeliveryData.map(item => `${item.name} 吨位`);
      let option;

      option = {
        title: {
          text: '本周同配置车型平均交货天数',
          left: 'center'
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          }
        },
        xAxis: {
          data: xAxisData // 车型名称作为 x 轴数据
        },
        yAxis: {},
        series: [{
          name: '本周平均交货天数',
          type: 'bar',
          data: this.numberOfWeekDeliveryData.map(item => item.value) // 每日平均交货天数作为柱状图数据
        }]
      };

      option && myChart.setOption(option);
    }
  }
};
</script>

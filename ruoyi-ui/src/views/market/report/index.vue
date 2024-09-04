<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="上月结余订单" prop="lastMonthBalanceOrder">
        <el-input
          v-model="queryParams.lastMonthBalanceOrder"
          placeholder="请输入上月结余订单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="销售网点" prop="commercialNetworks">
        <el-input
          v-model="queryParams.commercialNetworks"
          placeholder="请输入销售网点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="今日接单" prop="orderReceivedToday">
        <el-input
          v-model="queryParams.orderReceivedToday"
          placeholder="请输入今日接单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本月累计接单" prop="ordersReceivedThisMonth">
        <el-input
          v-model="queryParams.ordersReceivedThisMonth"
          placeholder="请输入本月累计接单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本年订单累计" prop="orderAccumulationThisYear">
        <el-input
          v-model="queryParams.orderAccumulationThisYear"
          placeholder="请输入本年订单累计"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="今日交货台数" prop="deliveryToday">
        <el-input
          v-model="queryParams.deliveryToday"
          placeholder="请输入今日交货台数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本月系统内交货" prop="deliveryWithinTheSystemThisMonth">
        <el-input
          v-model="queryParams.deliveryWithinTheSystemThisMonth"
          placeholder="请输入本月系统内交货"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本年累计系统交货数" prop="cumulativeSystemDeliveriesForTheYear">
        <el-input
          v-model="queryParams.cumulativeSystemDeliveriesForTheYear"
          placeholder="请输入本年累计系统交货数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="未交货订单" prop="unfilledOrders">
        <el-input
          v-model="queryParams.unfilledOrders"
          placeholder="请输入未交货订单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="今日实发" prop="issuedThisDay">
        <el-input
          v-model="queryParams.issuedThisDay"
          placeholder="请输入今日实发"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本月实发" prop="issuedThisMonth">
        <el-input
          v-model="queryParams.issuedThisMonth"
          placeholder="请输入本月实发"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="本年累计实发" prop="cumulativeIssuedThisMonth">
        <el-input
          v-model="queryParams.cumulativeIssuedThisMonth"
          placeholder="请输入本年累计实发"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="系统交货实物未提" prop="physicalDeliveryOfTheSystemWasNotMentioned">
        <el-input
          v-model="queryParams.physicalDeliveryOfTheSystemWasNotMentioned"
          placeholder="请输入系统交货实物未提"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="网点未排订单" prop="theNetworkHasNoOrders">
        <el-input
          v-model="queryParams.theNetworkHasNoOrders"
          placeholder="请输入网点未排订单"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传日期" prop="uploadDate">
        <el-date-picker clearable
          v-model="queryParams.uploadDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上传日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->
    <!-- 饼状图部分 -->
    <div class="echarts-wrapper">
      <div
        class="echart"
        ref="orderReceivedToday"
        id="orderReceivedToday"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="ordersReceivedThisMonth"
        id="ordersReceivedThisMonth"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="orderAccumulationThisYear"
        id="orderAccumulationThisYear"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
    </div>

    <div class="echarts-wrapper">
      <div
        class="echart"
        ref="deliveryToday"
        id="deliveryToday"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="deliveryWithinTheSystemThisMonth"
        id="deliveryWithinTheSystemThisMonth"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="cumulativeSystemDeliveriesForTheYear"
        id="cumulativeSystemDeliveriesForTheYear"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
    </div>

    <div class="echarts-wrapper">
      <div
        class="echart"
        ref="issuedThisDay"
        id="issuedThisDay"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="issuedThisMonth"
        id="issuedThisMonth"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
      <div
        class="echart"
        ref="cumulativeIssuedThisMonth"
        id="cumulativeIssuedThisMonth"
        :style="{ width: '33.3%', height: '400px', display: 'inline-block' }"
      ></div>
    </div>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['market:report:add']"
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
          v-hasPermi="['market:report:edit']"
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
          v-hasPermi="['market:report:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:report:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="syncReport"
        v-hasPermi="['market:report:syncReport']"
      >同步数据</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="reportList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="id(主键)" align="center" prop="mfqrId" /> -->
      <el-table-column label="销售网点" align="center" prop="commercialNetworks" />
      <el-table-column label="上月结余订单" align="center" prop="lastMonthBalanceOrder" />
      <el-table-column label="今日接单" align="center" prop="orderReceivedToday" />
      <el-table-column label="本月累计接单" align="center" prop="ordersReceivedThisMonth" />
      <el-table-column label="本年订单累计" align="center" prop="orderAccumulationThisYear" />
      <el-table-column label="今日交货台数" align="center" prop="deliveryToday" />
      <el-table-column label="本月系统内交货" align="center" prop="deliveryWithinTheSystemThisMonth" />
      <el-table-column label="本年累计系统交货数" align="center" prop="cumulativeSystemDeliveriesForTheYear" />
      <el-table-column label="未交货订单" align="center" prop="unfilledOrders" />
      <el-table-column label="今日实发" align="center" prop="issuedThisDay" />
      <el-table-column label="本月实发" align="center" prop="issuedThisMonth" />
      <el-table-column label="本年累计实发" align="center" prop="cumulativeIssuedThisMonth" />
      <el-table-column label="系统交货实物未提" align="center" prop="physicalDeliveryOfTheSystemWasNotMentioned" />
      <el-table-column label="网点未排订单" align="center" prop="theNetworkHasNoOrders" />
      <el-table-column label="上传日期" align="center" prop="uploadDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:report:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:report:remove']"
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

    <!-- 添加或修改市场科销售快报功能对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上月结余订单" prop="lastMonthBalanceOrder">
          <el-input v-model="form.lastMonthBalanceOrder" placeholder="请输入上月结余订单" />
        </el-form-item>
        <el-form-item label="销售网点" prop="commercialNetworks">
          <el-input v-model="form.commercialNetworks" placeholder="请输入销售网点" />
        </el-form-item>
        <el-form-item label="今日接单" prop="orderReceivedToday">
          <el-input v-model="form.orderReceivedToday" placeholder="请输入今日接单" />
        </el-form-item>
        <el-form-item label="本月累计接单" prop="ordersReceivedThisMonth">
          <el-input v-model="form.ordersReceivedThisMonth" placeholder="请输入本月累计接单" />
        </el-form-item>
        <el-form-item label="本年订单累计" prop="orderAccumulationThisYear">
          <el-input v-model="form.orderAccumulationThisYear" placeholder="请输入本年订单累计" />
        </el-form-item>
        <el-form-item label="今日交货台数" prop="deliveryToday">
          <el-input v-model="form.deliveryToday" placeholder="请输入今日交货台数" />
        </el-form-item>
        <el-form-item label="本月系统内交货" prop="deliveryWithinTheSystemThisMonth">
          <el-input v-model="form.deliveryWithinTheSystemThisMonth" placeholder="请输入本月系统内交货" />
        </el-form-item>
        <el-form-item label="本年累计系统交货数" prop="cumulativeSystemDeliveriesForTheYear">
          <el-input v-model="form.cumulativeSystemDeliveriesForTheYear" placeholder="请输入本年累计系统交货数" />
        </el-form-item>
        <el-form-item label="未交货订单" prop="unfilledOrders">
          <el-input v-model="form.unfilledOrders" placeholder="请输入未交货订单" />
        </el-form-item>
        <el-form-item label="今日实发" prop="issuedThisDay">
          <el-input v-model="form.issuedThisDay" placeholder="请输入今日实发" />
        </el-form-item>
        <el-form-item label="本月实发" prop="issuedThisMonth">
          <el-input v-model="form.issuedThisMonth" placeholder="请输入本月实发" />
        </el-form-item>
        <el-form-item label="本年累计实发" prop="cumulativeIssuedThisMonth">
          <el-input v-model="form.cumulativeIssuedThisMonth" placeholder="请输入本年累计实发" />
        </el-form-item>
        <el-form-item label="系统交货实物未提" prop="physicalDeliveryOfTheSystemWasNotMentioned">
          <el-input v-model="form.physicalDeliveryOfTheSystemWasNotMentioned" placeholder="请输入系统交货实物未提" />
        </el-form-item>
        <el-form-item label="网点未排订单" prop="theNetworkHasNoOrders">
          <el-input v-model="form.theNetworkHasNoOrders" placeholder="请输入网点未排订单" />
        </el-form-item>
        <el-form-item label="上传日期" prop="uploadDate">
          <el-date-picker clearable
            v-model="form.uploadDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上传日期">
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
import { listReport, getReport, delReport, addReport, updateReport, synchronization } from "@/api/market/report";
import PieChart from '@/components/PieChart.vue'; // 导入饼状图组件
import * as echarts from "echarts";
import { subtract } from "lodash";

export default {
  name: "Report",
  components: {
    PieChart
  },
  data() {
    return {
      //饼状图数据
      orderReceivedTodayData: [],
      isAllZeroReceivedDay: false,
      ordersReceivedThisMonthData: [],
      isAllZeroReceivedMonth: false,
      orderAccumulationThisYearData: [],
      isAllZeroReceivedYear: false,
      deliveryTodayData: [],
      isAllZerodeliverydDay: false,
      deliveryWithinTheSystemThisMonthData: [],
      isAllZerodeliverydMonth: false,
      cumulativeSystemDeliveriesForTheYearData: [],
      isAllZerodeliverydYear: false,
      issuedThisDayData: [],
      isAllZeroissuedDay: false,
      issuedThisMonthData: [],
      isAllZeroissuedMonth: false,
      cumulativeIssuedThisMonthData: [],
      isAllZeroissuedYear: false,
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
      // 市场科销售快报功能表格数据
      reportList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 50,
        lastMonthBalanceOrder: null,
        commercialNetworks: null,
        orderReceivedToday: null,
        ordersReceivedThisMonth: null,
        orderAccumulationThisYear: null,
        deliveryToday: null,
        deliveryWithinTheSystemThisMonth: null,
        cumulativeSystemDeliveriesForTheYear: null,
        unfilledOrders: null,
        issuedThisDay: null,
        issuedThisMonth: null,
        cumulativeIssuedThisMonth: null,
        physicalDeliveryOfTheSystemWasNotMentioned: null,
        theNetworkHasNoOrders: null,
        uploadDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        lastMonthBalanceOrder: [
          { required: true, message: "上月结余订单不能为空", trigger: "blur" }
        ],
        commercialNetworks: [
          { required: true, message: "销售网点不能为空", trigger: "blur" }
        ],
        orderReceivedToday: [
          { required: true, message: "今日接单不能为空", trigger: "blur" }
        ],
        ordersReceivedThisMonth: [
          { required: true, message: "本月累计接单不能为空", trigger: "blur" }
        ],
        orderAccumulationThisYear: [
          { required: true, message: "本年订单累计不能为空", trigger: "blur" }
        ],
        deliveryToday: [
          { required: true, message: "今日交货台数不能为空", trigger: "blur" }
        ],
        deliveryWithinTheSystemThisMonth: [
          { required: true, message: "本月系统内交货不能为空", trigger: "blur" }
        ],
        cumulativeSystemDeliveriesForTheYear: [
          { required: true, message: "本年累计系统交货数不能为空", trigger: "blur" }
        ],
        unfilledOrders: [
          { required: true, message: "未交货订单不能为空", trigger: "blur" }
        ],
        issuedThisDay: [
          { required: true, message: "今日实发不能为空", trigger: "blur" }
        ],
        issuedThisMonth: [
          { required: true, message: "本月实发不能为空", trigger: "blur" }
        ],
        cumulativeIssuedThisMonth: [
          { required: true, message: "本年累计实发不能为空", trigger: "blur" }
        ],
        physicalDeliveryOfTheSystemWasNotMentioned: [
          { required: true, message: "系统交货实物未提不能为空", trigger: "blur" }
        ],
        theNetworkHasNoOrders: [
          { required: true, message: "网点未排订单不能为空", trigger: "blur" }
        ],
        uploadDate: [
          { required: true, message: "上传日期不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();
  },
  mounted() {

    // 加载数据后拆分成适合饼状图的格式
    listReport(this.queryParams).then(response => {
      // 将后端返回的数据赋值给 reportList
      this.reportList = response.rows;
      // 拆分数据为适合饼状图的格式

      this.orderReceivedTodayData = this.reportList.map(item => ({
        value: item.orderReceivedToday, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      // 判断数据是否全部为零
      this.isAllZeroReceivedDay = this.orderReceivedTodayData.every(item => item.value === 0);
      // 当数据全部为零时，手动构造一个灰色的数据项
      if (this.isAllZeroReceivedDay) {
          this.orderReceivedTodayData.push({
              value: 1, // 给一个任意非零值
              name: '无数据' // 用于显示的名称
          });
      }

      this.ordersReceivedThisMonthData = this.reportList.map(item => ({
        value: item.ordersReceivedThisMonth, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZeroReceivedMonth = this.ordersReceivedThisMonthData.every(item => item.value === 0);
      if (this.isAllZeroReceivedMonth) {
        this.ordersReceivedThisMonthData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }

      this.orderAccumulationThisYearData = this.reportList.map(item => ({
        value: item.orderAccumulationThisYear, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZeroReceivedYear = this.orderAccumulationThisYearData.every(item => item.value === 0);
      if (this.isAllZeroReceivedYear) {
        this.orderAccumulationThisYearData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }

      this.deliveryTodayData = this.reportList.map(item => ({
        value: item.deliveryToday, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZerodeliverydDay = this.deliveryTodayData.every(item => item.value === 0);
      if (this.isAllZerodeliverydDay) {
        this.deliveryTodayData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }

      this.deliveryWithinTheSystemThisMonthData = this.reportList.map(item => ({
        value: item.deliveryWithinTheSystemThisMonth, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZerodeliverydMonth = this.deliveryWithinTheSystemThisMonthData.every(item => item.value === 0);
      if (this.isAllZerodeliverydMonth) {
        this.deliveryWithinTheSystemThisMonthData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }

      this.cumulativeSystemDeliveriesForTheYearData = this.reportList.map(item => ({
        value: item.cumulativeSystemDeliveriesForTheYear, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZerodeliverydYear = this.cumulativeSystemDeliveriesForTheYearData.every(item => item.value === 0);
      if (this.isAllZerodeliverydYear) {
        this.cumulativeSystemDeliveriesForTheYearData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }


      this.issuedThisDayData = this.reportList.map(item => ({
        value: item.issuedThisDay, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZeroissuedDay = this.issuedThisDayData.every(item => item.value === 0);
      if (this.isAllZeroissuedDay) {
        this.issuedThisDayData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }


      this.issuedThisMonthData = this.reportList.map(item => ({
        value: item.issuedThisMonth, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZeroissuedMonth = this.issuedThisMonthData.every(item => item.value === 0);
      if (this.isAllZeroissuedMonth) {
        this.issuedThisMonthData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }

      this.cumulativeIssuedThisMonthData = this.reportList.map(item => ({
        value: item.cumulativeIssuedThisMonth, // 今日接单数量作为值
        name: item.commercialNetworks // 销售网点名称作为名称
      }));
      this.isAllZeroissuedYear = this.cumulativeIssuedThisMonthData.every(item => item.value === 0);
      if (this.isAllZeroissuedYear) {
        this.cumulativeIssuedThisMonthData.push({
            value: 1, // 给一个任意非零值
            name: '无数据' // 用于显示的名称
          });
      }


      // 初始化饼状图

      this.orderReceivedTodayPieChart();
      this.ordersReceivedThisMonthPieChart();
      this.orderAccumulationThisYearPieChart();
      this.deliveryTodayPieChart();
      this.deliveryWithinTheSystemThisMonthPieChart();
      this.cumulativeSystemDeliveriesForTheYearPieChart();
      this.issuedThisDayPieChart();
      this.issuedThisMonthPieChart();
      this.cumulativeIssuedThisMonthPieChart();
    });

    },
  methods: {
    /** 显示饼状图 */
    /** 今日接单 */
    orderReceivedTodayPieChart(){

      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"

      // 如果数据全部为零，则直接显示一个灰色的饼图
        const option = this.isAllZeroReceivedDay ? {
          title: {
              text: '网点本日接单',
              subtext: dateString, // 在这里添加副标题显示日期
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
          title: {
              text: '网点本日接单',
              subtext: dateString, // 在这里添加副标题显示日期
              left: 'center'
          },
          tooltip: {
              trigger: 'item',
              formatter: '{a} <br/>{b}: {c} ({d}%)'
          },
          series: [
              {
                  name: '接单情况',
                  type: 'pie',
                  radius: '50%',
                  data: this.orderReceivedTodayData,
                  label: {
                    show: true,
                    formatter: '{b}: {d}%' // 显示名称和百分比
                  }
              }
          ]
      };

      const myChart = echarts.init(this.$refs.orderReceivedToday);// 图标初始化
      myChart.setOption(option);// 渲染页面
      
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
          myChart.resize();
      });

    },
    /** 本月累计接单 */
    ordersReceivedThisMonthPieChart(){

      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZeroReceivedMonth ? {
        title: {
              text: '网点本月累计接单',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本月累计接单',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.ordersReceivedThisMonthData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.ordersReceivedThisMonth);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 本年累计接单 */
    orderAccumulationThisYearPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZeroReceivedYear ? {
        title: {
              text: '网点本年累计接单',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本年累计接单',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.orderAccumulationThisYearData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.orderAccumulationThisYear);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 本日交货 */
    deliveryTodayPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZerodeliverydDay ? {
        title: {
              text: '网点本日交货',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本日交货',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.deliveryTodayData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.deliveryToday);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 本月系统内交货 */
    deliveryWithinTheSystemThisMonthPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZerodeliverydMonth ? {
        title: {
              text: '网点本月交货',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本月交货',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.deliveryWithinTheSystemThisMonthData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.deliveryWithinTheSystemThisMonth);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 本年累计系统交货数 */
    cumulativeSystemDeliveriesForTheYearPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZerodeliverydYear ? {
        title: {
              text: '网点本年交货',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本年交货',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.cumulativeSystemDeliveriesForTheYearData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.cumulativeSystemDeliveriesForTheYear);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 今日实发 */
    issuedThisDayPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZeroissuedDay ? {
        title: {
              text: '网点本日实发',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本日实发',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.issuedThisDayData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.issuedThisDay);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 本月实发 */
    issuedThisMonthPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZeroissuedMonth ? {
        title: {
              text: '网点本月实发',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本月实发',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.issuedThisMonthData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.issuedThisMonth);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    /** 本年累计实发 */
    cumulativeIssuedThisMonthPieChart(){
      const today = new Date();
      const dateString = today.toISOString().split('T')[0]; // 格式为 "YYYY-MM-DD"
      // 饼状图配置项
      const option = this.isAllZeroissuedYear ? {
        title: {
              text: '网点本年实发',
              subtext: dateString,
              left: 'center'
          },
          series: [
              {
                  type: 'pie',
                  radius: '50%',
                  itemStyle: {
                      color: '#ccc' // 灰色
                  },
                  label: {
                      show: false // 不显示标签
                  },
                  emphasis: {
                      label: {
                          show: false // 不显示强调时的标签
                      }
                  }
              }
          ]
      } : {
        title: {
          text: '网点本年实发',
          subtext: dateString,
          left: 'center'
        },
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b}: {c} ({d}%)'
        },
        series: [
          {
            name: '接单情况',
            type: 'pie',
            radius: '50%',
            data: this.cumulativeIssuedThisMonthData,
            label: {
              show: true,
              formatter: '{b}: {d}%' // 显示名称和百分比
            }
          }
        ]
      };

      const myChart = echarts.init(this.$refs.cumulativeIssuedThisMonth);// 图标初始化
      myChart.setOption(option);// 渲染页面
      //随着屏幕大小调节图表
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },

    syncReport() {
      // 使用 Fetch API 发送 POST 请求到后端
      // fetch('http://localhost:8080/market/report/synchronization', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   }
      // })
      // .then(response => {
      //   if (!response.ok) {
      //     throw new Error('Network response was not ok');
      //   }
      //   // 如果请求成功，可以进行下一步操作
      // })
      // .catch(error => {
      //   console.error('There was an error!', error);
      // });

      synchronization()
      .then(response => {
        // if (!response.ok) {
        //   throw new Error('Network response was not ok');
        // }
        // 如果请求成功，可以进行下一步操作
        this.$message.success("同步成功");
        location.reload();
      })
      .catch(error => {
        console.error('There was an error!', error);
      });

    },

    /** 查询市场科销售快报功能列表 */
    getList() {
      this.loading = true;
      listReport(this.queryParams).then(response => {
        this.reportList = response.rows;
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
        mfqrId: null,
        lastMonthBalanceOrder: null,
        commercialNetworks: null,
        orderReceivedToday: null,
        ordersReceivedThisMonth: null,
        orderAccumulationThisYear: null,
        deliveryToday: null,
        deliveryWithinTheSystemThisMonth: null,
        cumulativeSystemDeliveriesForTheYear: null,
        unfilledOrders: null,
        issuedThisDay: null,
        issuedThisMonth: null,
        cumulativeIssuedThisMonth: null,
        physicalDeliveryOfTheSystemWasNotMentioned: null,
        theNetworkHasNoOrders: null,
        uploadDate: null
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
      this.ids = selection.map(item => item.mfqrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加市场科销售快报功能";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const mfqrId = row.mfqrId || this.ids
      getReport(mfqrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改市场科销售快报功能";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.mfqrId != null) {
            updateReport(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addReport(this.form).then(response => {
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
      const mfqrIds = row.mfqrId || this.ids;
      this.$modal.confirm('是否确认删除市场科销售快报功能编号为"' + mfqrIds + '"的数据项？').then(function() {
        return delReport(mfqrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/report/export', {
        ...this.queryParams
      }, `report_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>
.echarts-wrapper {
  white-space: nowrap;
}

.echart {
  vertical-align: top;
}
</style>

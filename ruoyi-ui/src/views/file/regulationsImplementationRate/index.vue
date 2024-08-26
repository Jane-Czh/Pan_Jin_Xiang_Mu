<template>
  <div>
    <div class="block">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleUpload"
            v-hasPermi="['file:regulationsImplementationRate:add']"
          >上传
          </el-button>
        </el-col>
<!--        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>-->
      </el-row>
      <span class="DataSelect" style="margin-right: 10px">日期选择</span>
      <el-date-picker
        v-model="selectedDate"
        type="monthrange"
        unlink-panels
        range-separator="至"
        start-placeholder="开始月份"
        end-placeholder="结束月份"
        :picker-options="pickerOptions"
        @change="handleDateChange"
      >
      </el-date-picker>
<!--      <span class="DataSelect" style="margin-right: 100px"-->
<!--      >当前期间制度修订频率：{{ this.totalCounts }} 次</span-->
<!--      >-->
    </div>

    <div id="main" ref="main"></div>

    <!-- 新增制度执行率对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable
                          v-model="form.yearAndMonth"
                          type="month"
                          value-format="yyyy-MM"
                          placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际执行制度次数" prop="actualImplementedTimes">
          <el-input v-model="form.actualImplementedTimes" placeholder="请输入实际执行制度次数" />
        </el-form-item>
        <el-form-item label="应执行制度次数" prop="shouldImplementedTimes">
          <el-input v-model="form.shouldImplementedTimes" placeholder="请输入应执行制度次数" />
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
  import * as echarts from "echarts";
  console.log("echarts===>",echarts);
  import moment from "moment";
  import { getMainRevenueData } from "@/api/financial/data";
  import {getRegulationsImplementationRateByData, addRegulationsImplementationRate} from "@/api/file/regulationsImplementation";
  //获取流程相关的api
  import { getProjectName, getIndicatorUpdateCounts } from "@/api/system/project";

  export default {
    data() {
      return {
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
        // 制度执行率表格数据
        regulationsImplementationRateList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 时间选择
        historyStartTime: [],

        // 流程表格数据
        historyProjectList: [],
        // 表单参数
        form: {
          id: null,
          yearAndMonth: null,
          actualImplementedTimes: null,
          shouldImplementedTimes: null,
          regulationsImplementationRate: null
        },
        // 表单参数
        regulationImplementationRateRespondEntity: {},
        // 表单校验
        rules: {
          yearAndMonth: [
            {required: true, message: "时间不能为空", trigger: "blur"}
          ],
          actualImplementedTimes: [
            {required: true, message: "实际执行制度次数不能为空", trigger: "blur"}
          ],
          shouldImplementedTimes: [
            {required: true, message: "应执行制度次数不能为空", trigger: "blur"}
          ],
        },
        loading: false,
        data: [],
        //当前时间期间内统计的流程变更总次数
        totalCounts: 0,
        //当前流程的名称
        projectName: "",

        timeData: {
          startTime: new Date(),
          endTime: new Date(),
        },
        selectedDate: [],
        pickerOptions: [],
        option: {},
        myChart: {},
      };
    },
    computed: {},
    mounted() {
      this.defaultMonth();
      this.myChart = echarts.init(document.getElementById("main"));
      this.initData();
      // this.getThisProjectName();
    },
    methods: {
      /** 上传制度执行率信息 */
      handleUpload() {
        this.reset();
        this.open = true;
        this.title = "制度手动填报指标";
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          id: null,
          yearAndMonth: null,
          actualImplementedTimes: null,
          shouldImplementedTimes: null,
          regulationsImplementationRate: null
        };
        this.resetForm("form");
      },
      /** 提交按钮 */
      submitForm(file) {
        this.$refs["form"].validate(valid => {
          if (valid) {
            console.log("form===>",this.form);
            console.log("实际执行次数:", this.form.actualImplementedTimes, typeof this.form.actualImplementedTimes);
            console.log("应执行次数:", this.form.shouldImplementedTimes, typeof this.form.shouldImplementedTimes);
            // this.form.yearAndMonth = `${this.form.yearAndMonth}-01`;
            // 新增条件判断逻辑
            if ((this.form.shouldImplementedTimes != 0) && (parseInt(this.form.actualImplementedTimes, 10) <= parseInt(this.form.shouldImplementedTimes, 10))) {
              this.form.yearAndMonth = `${this.form.yearAndMonth}-01`;
              addRegulationsImplementationRate(this.form).then(response => {
                this.$message.success("上传成功");
                this.open = false;
                this.initData();
              });
            } else {
              this.$message.error("输入数据有误");
            }
            // addRegulationsImplementationRate(this.form).then(response => {
            //   this.$modal.msgSuccess("上传成功");
            //   this.open = false;
            //   this.initData();
            // });
          }
        });
      },


      /** 查询当前流程的name */
      getThisProjectName() {
        const id = this.$route.params.id;
        getProjectName(id).then((res) => {
          this.projectName = res;
          // console.log("this.projectName===>", this.projectName);
        });
      },

      // -----------------------------------------------------------------------

      async initData() {
        this.timeData.startTime = this.selectedDate[0];
        // 获取该月的最后一天
        const endOfMonth = new Date(this.selectedDate[1].getFullYear(), this.selectedDate[1].getMonth() + 1, 0);
        this.timeData.endTime = endOfMonth;
        // this.timeData.endTime = this.selectedDate[1];
        console.log("startTime=>",this.timeData.startTime);
        console.log("endTime=>",this.timeData.endTime);
        try {
          this.loading = true;
          //在选择的时间区间内刷新数据
          // const res = await getMainRevenueData(this.timeData);

          const res = await getRegulationsImplementationRateByData(this.timeData);
          console.log("res===>", res);
          let counts = 0;
          // res.map((item) => {
          //   counts += item.times;
          // });
          // console.log("counts===>", counts);
          this.totalCounts = counts;
          /**
           * 返回的数据格式
           * times: 2      --数据
           * yearAndMonth: "2024-01-01"  --时间,展示时只取 年和月
           * updateDates: 更具体的更新日期
           */

          this.data = res;
          this.loading = false;
          // 更新图表显示
          this.updateChart();
        } catch (error) {
          this.loading = false;
        }
      },
      handleDateChange() {
        this.initData();
      },

      updateChart() {
        const formattedData = this.data.map((item) => {
          return {
            yearAndMonth: moment(item.yearAndMonth).format("YYYY-MM"),
            rate: item.rate,

          };
        });

        var app = {};
        const posList = [
          "left",
          "right",
          "top",
          "bottom",
          "inside",
          "insideTop",
          "insideLeft",
          "insideRight",
          "insideBottom",
          "insideTopLeft",
          "insideTopRight",
          "insideBottomLeft",
          "insideBottomRight",
        ];
        app.configParameters = {
          rotate: { min: -90, max: 90 },
          align: { options: { left: "left", center: "center", right: "right" } },
          verticalAlign: {
            options: { top: "top", middle: "middle", bottom: "bottom" },
          },
          position: {
            options: posList.reduce((map, pos) => {
              map[pos] = pos;
              return map;
            }, {}),
          },
          distance: { min: 0, max: 100 },
        };
        app.config = {
          rotate: 0,
          align: "center",
          verticalAlign: "middle",
          position: "top",
          distance: 15,
          onChange: function () {
            const labelOption = {
              rotate: app.config.rotate,
              align: app.config.align,
              verticalAlign: app.config.verticalAlign,
              position: app.config.position,
              distance: app.config.distance,
            };
            this.myChart.setOption({
              series: [
                { label: labelOption },
                { label: labelOption },
                { label: labelOption },
                { label: labelOption },
              ],
            });
          },
        };
        const labelOption = {
          show: true,
          position: app.config.position,
          distance: app.config.distance,
          align: app.config.align,
          verticalAlign: app.config.verticalAlign,
          rotate: app.config.rotate,
          formatter: function (params) {
            return params.data.times;
          },
          fontSize: 16,
          rich: { name: {} },
        };
        this.option = {
          title: {
            text: "制度" + this.projectName + "执行率（月）",
          },
          tooltip: {
            trigger: "axis",
            axisPointer: { type: "shadow" },
            // formatter: function (params) {
            //   const data = params[0].data;
            //   const updateDates =
            //     data.updateDates.length > 0
            //       ? data.updateDates.join("<br/>")
            //       : "无更新记录";
            //   return `具体更新时间:<br/>${updateDates}`;
            // },
          },
          toolbox: {
            show: true,
            orient: "vertical",
            left: "right",
            top: "center",
            feature: {
              mark: { show: true },
              dataView: { show: true, readOnly: false, title: "数据视图" },
              magicType: {
                show: true,
                type: ["bar"],
                title: { bar: "切换为柱状图" },
              },
              restore: { show: true, title: "还原" },
              saveAsImage: { show: true, title: "保存为图片" },
            },
          },
          xAxis: [
            {
              type: "category",
              axisTick: { show: false },
              data: formattedData.map((item) => item.yearAndMonth),
            },
          ],
          yAxis: [
            {
              type: "value",
              axisLabel: {
                formatter: function (value) {
                  return parseInt(value);
                },
              },
              interval: 1,
            },
          ],
          series: [
            {
              name: "制度执行率",
              type: "line",
              label: labelOption,
              emphasis: { focus: "series" },
              data: formattedData.map((item) => ({
                value: item.rate,
                times: item.rate,
                // updateDates: item.updateDates,
              })),
            },
          ],
        };

        this.option && this.myChart.setOption(this.option);
      },
      //时间选择器的默认月份设置
      defaultMonth() {
        const currentDate = new Date();
        const currentYear = currentDate.getFullYear();
        const currentMonth = currentDate.getMonth() + 1;
        const startDate = new Date(currentYear, 0, 1);
        const endDate = new Date(currentYear, currentMonth, 0);

        this.selectedDate = [startDate, endDate];
      },
    },
  };
</script>

<style lang="scss" scoped>
  #main {
    width: 1000px;
    height: 600px;
    margin: 40px auto;
  }

  .block {
    margin-top: 50px;
    text-align: center;
  }
</style>

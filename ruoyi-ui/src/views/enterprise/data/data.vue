<template>
  <div class="currentPage">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="日期" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="month" value-format="yyyy-MM-dd"
          placeholder="请选择日期">
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
          v-hasPermi="['enterprise:monthly:add']">新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['enterprise:monthly:edit']">修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['enterprise:monthly:remove']">删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-refresh" v-if="!updateLoading" size="mini"
          @click="handleCacluation" v-hasPermi="['enterprise:monthly:calculation']">更新
        </el-button>
        <el-button type="primary" v-if="updateLoading" :loading="true">更新中</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" @click="showDialog = true" size="mini" plain v-if="true"
          v-hasPermi="['enterprise:monthly:import']">导入Excel文件
        </el-button>
        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类">
              <span style="color: rgb(68, 140, 39);">工资表</span>
              <br>
              <el-date-picker clearable v-model="form3.yearAndMonth" type="month" value-format="yyyy-MM-dd"
                placeholder="请选择日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="含其他奖金">
              <el-switch v-model="form4.otherBonus"></el-switch>
            </el-form-item>
          </el-form>
          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />
          <span slot="footer" class="dialog-footer">
            <el-button @click="showDialog = false">取 消</el-button>
            <el-button type="primary" @click="fileSend()" v-if="!isLoading">确 定</el-button>
            <el-button type="primary" v-if="isLoading" :loading="true">上传中</el-button>
          </span>
        </el-dialog>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" icon="el-icon-download" @click="handleDownload" size="mini" plain v-if="true">下载模版文件
        </el-button>
      </el-col>

      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['enterprise:monthly:export']">导出
        </el-button>
      </el-col>


      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="DataList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="esId" /> -->
      <el-table-column fixed label="日期" align="center" prop="yearAndMonth" width="120" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="一线从业人数" align="center" prop="employeesNumber" width="120" />
      <el-table-column label="生产实习生人数" align="center" prop="productionInternNumbers" width="150" />
      <el-table-column label="公司当月从业人数" align="center" prop="employeesNumberCurrentMonth" width="150" />
      <el-table-column label="公司平均从业人数(月度)" align="center" prop="employeesAvgMonthlyNumber" width="170" />
      <el-table-column label="公司平均从业人数(年度)" align="center" prop="employeesAvgAnnualNumber" width="170" />
      <el-table-column label="工资总额月度值" align="center" prop="totalMonthlySalary" width="150" />
      <el-table-column label="工资总额月度占比(%)" align="center" prop="monthlySalaryRatio" width="150" />
      <el-table-column label="工资总额年度占比(%)" align="center" prop="annualSalaryRatio" width="150" />

      <el-table-column label="月度人均收入" align="center" prop="monthlyCumulativeAverageIncome" width="140" />
      <el-table-column label="累计人均收入" align="center" prop="cumulativeAverageIncome" width="140" />

      <el-table-column label="月度生产人均收入" align="center" prop="monthlyProductionAvgIncome" width="150" />
      <el-table-column label="累计生产人均收入" align="center" prop="productionAvgIncome" width="140" />

      <el-table-column label="月度职能人均收入" align="center" prop="monthlyFunctionalAvgIncome" width="150" />
      <el-table-column label="累计职能人均收入" align="center" prop="functionalAvgIncome" width="140" />


      <el-table-column label="职能部门人均加班费用" align="center" prop="functionalDeptOvertimeCost" width="150" />
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['enterprise:monthly:edit']">修改
          </el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['enterprise:monthly:remove']">删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改[企业管理]指标月度数据对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="工资总额月度值" prop="totalMonthlySalary">
          <el-input v-model="form.totalMonthlySalary" placeholder="请输入工资总额月度值" />
        </el-form-item>
        <el-form-item label="公司当月从业人数" prop="employeesNumberCurrentMonth">
          <el-input v-model="form.employeesNumberCurrentMonth" placeholder="请输入公司当月从业人数" />
        </el-form-item>
        <el-form-item label="一线从业人数" prop="employeesNumber">
          <el-input v-model="form.employeesNumber" placeholder="请输入一线从业人数" />
        </el-form-item>
        <el-form-item label="生产实习生人数" prop="productionInternNumbers">
          <el-input v-model="form.productionInternNumbers" placeholder="请输入生产实习生人数" />
        </el-form-item>
        <!-- <el-form-item label="累计生产人均收入" prop="productionAvgIncome">
          <el-input v-model="form.productionAvgIncome" placeholder="请输入生产人均收入" />
        </el-form-item> -->
        <!-- <el-form-item label="公司平均从业人数" prop="employeesAvgMonthlyNumber">
          <el-input v-model="form.employeesAvgMonthlyNumber" placeholder="请输入公司平均从业人数" />
        </el-form-item> -->
        <!-- <el-form-item label="公司年度平均从业人数" prop="employeesAvgAnnualNumber">
          <el-input v-model="form.employeesAvgAnnualNumber" placeholder="请输入公司年度平均从业人数" />
        </el-form-item> -->
        <!-- <el-form-item label="工资总额月度占比" prop="monthlySalaryRatio">
          <el-input v-model="form.monthlySalaryRatio" placeholder="请输入工资总额月度占比" />
        </el-form-item> -->
        <!-- <el-form-item label="工资总额年度占比" prop="annualSalaryRatio">
          <el-input v-model="form.annualSalaryRatio" placeholder="请输入工资总额年度占比" />
        </el-form-item> -->
        <!-- <el-form-item label="累计人均收入" prop="cumulativeAverageIncome">
          <el-input v-model="form.cumulativeAverageIncome" placeholder="请输入累计人均收入" />
        </el-form-item> -->
        <el-form-item label="月度人均收入" prop="monthlyCumulativeAverageIncome">
          <el-input v-model="form.monthlyCumulativeAverageIncome" placeholder="请输入当月人均收入" />
        </el-form-item>
        <el-form-item label="月度生产人均收入" prop="monthlyProductionAvgIncome">
          <el-input v-model="form.monthlyProductionAvgIncome" placeholder="请输入月度累计生产人均收入" />
        </el-form-item>
        <el-form-item label="月度职能人均收入" prop="monthlyFunctionalAvgIncome">
          <el-input v-model="form.monthlyFunctionalAvgIncome" placeholder="请输入月度累计职能人均收入" />
        </el-form-item>
        <el-form-item label="职能部门人均加班费用" prop="functionalDeptOvertimeCost">
          <el-input v-model="form.functionalDeptOvertimeCost" placeholder="请输入职能部门人均加班费用" />
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
import {
  listMonthData,
  getMonthData,
  addMonthData,
  delMonthData,
  updateMonthData,
  calculation
} from "@/api/enterprise/data";
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';
import {
  numValidatorEnableEmpty,
  numValidator,
  numValidatorEnableEmptyNature,
  numValidatorOnlyNature
} from '@/api/financial/numValidator.js';
import moment from 'moment';

export default {
  name: "Data",

  data() {
    return {

      // 遮罩层
      loading: true,
      showDialog: false,
      progress: 0,
      selectedType: '',
      isLoading: false,
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
      updateLoading: false,
      // [企业管理]指标月度数据表格数据
      DataList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      open2: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        yearAndMonth: null,
        employeesNumber: null,
        employeesAvgMonthlyNumber: null,
        employeesAvgAnnualNumber: null,
        productionInternNumbers: null,
        totalMonthlySalary: null,
        monthlySalaryRatio: null,
        functionalAvgIncome: null,
        employeesNumberCurrentMonth: null,
        monthlyCumulativeAverageIncome: null,
        annualSalaryRatio: null,
        cumulativeAverageIncome: null,
        monthlyProductionAvgIncome: null,
        monthlyFunctionalAvgIncome: null,
        productionAvgIncome: null,
        functionalDeptOvertimeCost: null,
      },
      // 表单参数
      form: {},
      form3: { yearAndMonth: null },
      form4: { otherBonus: true },
      // 表单校验
      rules: {
        yearAndMonth: [
          {
            required: true, message: "日期不能为空", trigger: "blur"
          }
        ],
        employeesNumber: [
          {
            validator: numValidatorEnableEmptyNature,
            trigger: "blur",
          }
        ],
        // employeesAvgMonthlyNumber: [
        //   {
        //     required: true,
        //     validator: numValidatorPositive,
        //     trigger: "blur",
        //   }
        // ],
        // employeesAvgAnnualNumber: [
        //   {
        //     required: true,
        //     validator: numValidatorPositive,
        //     trigger: "blur",
        //   }
        // ],

        totalMonthlySalary: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        employeesNumberCurrentMonth: [
          {
            // required: true,
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
        // monthlySalaryRatio: [
        //   {
        //     required: true,
        //     validator: numValidator,
        //     trigger: "blur",
        //   }
        // ],
        // annualSalaryRatio: [
        //   {
        //     required: true,
        //     validator: numValidator,
        //     trigger: "blur",
        //   }
        // ],

        //允许空值
        monthlyCumulativeAverageIncome: [
          {
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
        productionInternNumbers: [
          {
            validator: numValidatorEnableEmptyNature,
            trigger: "blur",
          }
        ],
        cumulativeAverageIncome: [
          {
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
        monthlyProductionAvgIncome: [
          {
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
        monthlyFunctionalAvgIncome: [
          {
            validator: numValidatorEnableEmpty,
            trigger: "blur",
          }
        ],
        functionalDeptOvertimeCost: [
          {
            validator: numValidatorEnableEmpty,
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

    handleDownload() {
      const url = "/profile/excel_templates/工资表样表.xlsx";
      handleTrueDownload(url);
    },

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
          this.DataList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.DataList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询[企业管理]指标月度数据列表 */
    getList() {
      this.loading = true;
      listMonthData(this.queryParams).then(response => {
        this.DataList = response.rows;
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
        esId: null,
        yearAndMonth: null,
        employeesNumber: null,
        employeesAvgMonthlyNumber: null,
        employeesAvgAnnualNumber: null,
        totalMonthlySalary: null,
        monthlySalaryRatio: null,
        annualSalaryRatio: null,
        cumulativeAverageIncome: null,
        employeesNumberCurrentMonth: null,
        functionalAvgIncome: null,
        monthlyProductionAvgIncome: null,
        productionAvgIncome: null,
        monthlyFunctionalAvgIncome: null,
        productionInternNumbers: null,
        functionalDeptOvertimeCost: null,
        monthlyCumulativeAverageIncome: null,
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
      this.ids = selection.map(item => item.esId)
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
    //同步数据
    handleCacluation() {
      calculation().then(response => {
        this.updateLoading = true;
        this.$modal.msgSuccess("更新成功");
        this.getList();
        this.updateLoading = false;
      }).catch(error => {
        this.$modal.msgError("更新失败: " + error.message);
        this.updateLoading = false;
      })
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const esId = row.esId || this.ids
      getMonthData(esId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.esId != null) {
            updateMonthData(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addMonthData(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).catch(error => {
              console.error("Error:", error);
              this.$modal.msgError("新增失败: " + error.message);
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const esIds = row.esId || this.ids;
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';
      const yearMonth = year && month ? `${year}-${month}` : '';
      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delMonthData(esIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      });
    },
    /** 导入按钮 */

    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm" && fileExt.toLowerCase() !== "et") {
        this.$message.error("只能上传 Excel 文件！");
        // this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    extractMiddleMonth(dateStr) {
      // 使用正则表达式匹配月份部分
      const match = dateStr.match(/-(\d{2})-/);
      if (match) {
        // 提取匹配到的月份，并移除前导0
        const month = parseInt(match[1], 10);
        return month.toString();
      } else {
        return null; // 或者返回一个默认值
      }
    },
    convertMonthToChinese(month) {
      const monthMap = {
        '1': '一',
        '2': '二',
        '3': '三',
        '4': '四',
        '5': '五',
        '6': '六',
        '7': '七',
        '8': '八',
        '9': '九',
        '10': '十',
        '11': '十一',
        '12': '十二'
      };

      // 返回中文全称加上“月”
      return `${monthMap[month]}月`;
    },
    /** 导入按钮 */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      const yearAndMonth = this.form3.yearAndMonth;
      const otherBonus = this.form4.otherBonus;
      formData.append("yearAndMonth", yearAndMonth);
      formData.append("multipartFile", file);
      formData.append("otherBonus", otherBonus);
      const aimUrl = `/enterprise/data/salary`;
      const Str = this.extractMiddleMonth(yearAndMonth);
      const charMonth = this.convertMonthToChinese(Str);
      const regex1 = new RegExp(Str + '月');
      const regex2 = new RegExp(charMonth);
      const check1 = regex1.test(file.name);//检查上传文件是否与所选日期匹配
      const check2 = regex2.test(file.name);
      if (file === undefined || yearAndMonth == null) {
        if (file === undefined) {
          this.$message.error("请选择文件!");
          return;
        } else {
          this.$message.error("请选择日期!");
          return;
        }
      } else {
        this.isLoading = true;
        if (check1 || check2) {
          uploadFile(formData, aimUrl)
            .then(data => {
              // 处理上传成功的情况0.
              this.$message.success("上传成功");
              this.getList();
              this.showDialog = false;
              this.isLoading = false;
            })
            .catch(error => {
              // 处理上传失败的情况
              console.error('上传失败：', error);
              // this.$message.error("上传失败，请重试");
              this.isLoading = false;
            })
            ;
        } else {
          let nowDate = moment(yearAndMonth).format("YYYY-MM");

          let file1 = file.name;
          //给${nowDate}${file1}修改为绿色颜色

          this.$modal.confirm(`当前选择的工资表月份为${nowDate},您当前上传的表格为《${file1}》是否确认上传？`).then(() => {
            return uploadFile(formData, aimUrl)
              .then(data => {
                // 处理上传成功的情况0.
                this.$message.success("上传成功");
                this.getList();
                this.showDialog = false;
                this.isLoading = false;
              })
              .catch(error => {
                // 处理上传失败的情况
                console.error('上传失败：', error);
                // this.$message.error("上传失败，请重试");
                this.isLoading = false;
              })
          });
        }

      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('enterprise/data/monthly/export', {
        ...this.queryParams
      }, `企业管理月度数据_${new Date().getTime()}.xlsx`)
    },
  }
};
</script>


<style lang="scss" scoped>
.currentPage {
  padding: 10px;
}
</style>

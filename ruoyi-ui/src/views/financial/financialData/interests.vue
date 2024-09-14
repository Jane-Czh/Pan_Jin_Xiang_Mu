<template>
  <div class="currentPage">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- 利润表 -->
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
          v-hasPermi="['financial:interests:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['financial:interests:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['financial:interests:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->
        <el-button type="primary" icon="el-icon-share" size="mini" plain @click="showDialog = true" v-if="true"
          v-hasPermi="['financial:interests:import']">导入Excel
        </el-button>

        <el-dialog title="导入Excel" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <!-- 下拉框 -->
          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="上传表类:">
              <span style="color: rgb(68, 140, 39);">利润表</span>
              <br>
              <el-date-picker clearable v-model="form3.yearAndMonth" type="month" value-format="yyyy-MM-dd"
                placeholder="请选择日期">
              </el-date-picker>
            </el-form-item>
          </el-form>

          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

          <!-- 进度动画条 -->
          <!-- <div v-if="progress > 0">
            <el-progress :percentage="progress" color="rgb(19, 194, 194)"></el-progress>
          </div> -->

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
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="interestsList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange" border>
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column fixed label="日期" align="center" prop="yearAndMonth" width="120"
        :sort-orders="['descending', 'ascending']" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="集团内主营业务收入(万元)" align="center" prop="internalMainRevenue" width="180" /> -->
      <el-table-column label="集团内主营业务收入(万元)" align="center" prop="internalMainRevenue" width="180">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.internalMainRevenue) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="集团外主营业务收入(万元)" align="center" prop="externalMainRevenue" width="180">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.externalMainRevenue) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主营业务收入(万元)" align="center" prop="mainRevenue" width="140">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.mainRevenue) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主营业务成本-产品销售SD(万元)" align="center" prop="cogsProductSalesSd" width="210">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.cogsProductSalesSd) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主营业务成本-运费(万元)" align="center" prop="cogsFreight" width="170">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.cogsFreight) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主营业务成本-运费变化(万元)" align="center" prop="cogsVariation" width="190">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.cogsVariation) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="主营业务成本(万元)" align="center" prop="cogs" width="140">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.cogs) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="净利润(万元)" align="center" prop="netProfit" width="120">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.netProfit) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="管理费用(万元)" align="center" prop="managementExpense" width="130">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.managementExpense) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="研发费用(万元)" align="center" prop="rdExpense" width="130">
        <template slot-scope="scope">
          <span>{{ formatNumber(scope.row.rdExpense) }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['financial:interests:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['financial:interests:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改财务-利润对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body :before-close="handleClose">
      <el-form ref="form" :model="form" :rules="rules" label-width="230px">
        <el-form-item label="日期" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="month" value-format="yyyy-MM-dd"
            placeholder="请选择日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="集团内主营业务收入(万元)" prop="internalMainRevenue">
          <el-input v-model="form.internalMainRevenue" placeholder="请输入集团内主营业务收入(万元)" />
        </el-form-item>
        <el-form-item label="集团外主营业务收入(万元)" prop="externalMainRevenue">
          <el-input v-model="form.externalMainRevenue" placeholder="请输入集团外主营业务收入(万元)" />
        </el-form-item>
        <el-form-item label="主营业务收入(万元)" prop="mainRevenue">
          <el-input v-model="form.mainRevenue" placeholder="请输入主营业务收入(万元)" />
        </el-form-item>
        <el-form-item label="主营业务成本-产品销售SD(万元)" prop="cogsProductSalesSd">
          <el-input v-model="form.cogsProductSalesSd" placeholder="请输入主营业务成本-产品销售SD(万元)" />
        </el-form-item>
        <el-form-item label="主营业务成本-运费(万元)" prop="cogsFreight">
          <el-input v-model="form.cogsFreight" placeholder="请输入主营业务成本-运费(万元)" />
        </el-form-item>
        <el-form-item label="主营业务成本-差异(万元)" prop="cogsVariation">
          <el-input v-model="form.cogsVariation" placeholder="请输入主营业务成本-差异(万元)" />
        </el-form-item>
        <el-form-item label="主营业务成本(万元)" prop="cogs">
          <el-input v-model="form.cogs" placeholder="请输入主营业务成本(万元)" />
        </el-form-item>
        <el-form-item label="净利润(万元)" prop="netProfit">
          <el-input v-model="form.netProfit" placeholder="请输入净利润(万元)" />
        </el-form-item>
        <el-form-item label="管理费用(万元)" prop="managementExpense">
          <el-input v-model="form.managementExpense" placeholder="请输入管理费用(万元)" />
        </el-form-item>
        <el-form-item label="研发费用(万元)" prop="rdExpense">
          <el-input v-model="form.rdExpense" placeholder="请输入研发费用(万元)" />
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
import { listInterests, getInterests, delInterests, addInterests, updateInterests } from "@/api/financial/interestsData";
import { uploadFile, handleTrueDownload } from '@/api/financial/excelImport';
import { numValidator } from '@/api/financial/numValidator.js';

export default {
  name: "Interests",
  data() {
    return {
      yearAndMonth: [],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      dates: [],
      // 非单个禁用
      single: true,
      showDialog: false,
      // 导入Excel弹出层
      selectedType: '',
      progress: 0,
      isLoading: false,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 财务-利润表格数据
      interestsList: [],
      threeList: [],
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
        yearAndMonth: null,
        internalMainRevenue: null,
        externalMainRevenue: null,
        mainRevenue: null,
        cogsProductSalesSd: null,
        cogsFreight: null,
        cogsVariation: null,
        cogs: null,
        netProfit: null,
        managementExpense: null,
        rdExpense: null
      },
      // 表单参数
      form: {

      },
      form3: {
        yearAndMonth: null
      },
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
        internalMainRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        externalMainRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        mainRevenue: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        cogsProductSalesSd: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        cogsFreight: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        cogsVariation: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        cogs: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        netProfit: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        managementExpense: [
          {
            required: true,
            validator: numValidator,
            trigger: "blur",
          }
        ],
        rdExpense: [
          {
            required: true,
            validator: numValidator,
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
    formatNumber(value) {
      if (value === null || value === undefined) return '';
      return value.toLocaleString('en-US', { minimumFractionDigits: 0, maximumFractionDigits: 2 });
    },
    handleDownload() {
      const url = "/profile/excel_templates/利润表样表.xlsx";
      handleTrueDownload(url);
    },

    handleSortChange(sort) {
      // sort.order: 排序的顺序，'ascending' 或 'descending'
      if (sort.column && sort.prop === 'yearAndMonth') {
        if (sort.order === 'ascending') {
          this.interestsList.sort((a, b) => new Date(a.yearAndMonth) - new Date(b.yearAndMonth));
        } else if (sort.order === 'descending') {
          this.interestsList.sort((a, b) => new Date(b.yearAndMonth) - new Date(a.yearAndMonth));
        }
      }
    },
    /** 查询财务-利润列表 */
    getList() {
      this.loading = true;
      listInterests(this.queryParams).then(response => {
        this.interestsList = response.rows;
        this.total = response.total;
        this.handleSortChange({
          column: {}, // 这个对象可以为空，因为在handleSortChange方法中并没有使用
          prop: 'yearAndMonth',
          order: 'descending' // 或'descending'
        });
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
        fiId: null,
        createdBy: null,
        createdTime: null,
        yearAndMonth: null,
        internalMainRevenue: null,
        externalMainRevenue: null,
        mainRevenue: null,
        cogsProductSalesSd: null,
        cogsFreight: null,
        cogsVariation: null,
        cogs: null,
        netProfit: null,
        managementExpense: null,
        rdExpense: null
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
      this.ids = selection.map(item => item.fiId)
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
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fiId = row.fiId || this.ids
      getInterests(fiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fiId != null) {
            updateInterests(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addInterests(this.form).then(response => {
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
      const fiIds = row.fiId || this.ids;
      const date = row.yearAndMonth || this.dates;
      // 提取年份和月份
      const parsedDate = date ? new Date(date) : null;
      const year = parsedDate ? parsedDate.getFullYear() : '';
      const month = parsedDate ? ('0' + (parsedDate.getMonth() + 1)).slice(-2) : '';

      const yearMonth = year && month ? `${year}-${month}` : '';

      this.$modal.confirm(`是否删除日期为"${yearMonth}"的数据？`).then(() => {
        return delInterests(fiIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
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
    // 导入excel，检查文件类型
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        // this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    /** 导入按钮 */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      const yearAndMonth = this.form3.yearAndMonth;
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
        formData.append("yearAndMonth", yearAndMonth);
        formData.append("excelFile", file);
        const aimUrl = `/financial/data/interests/import`
        uploadFile(formData, aimUrl)
          .then(data => {
            // 处理上传成功的情况
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
          });
      }
    },
    // // 导入excel，检查文件类型
    // checkFile() {
    //   const file = this.$refs.fileInput.files[0];
    //   const fileName = file.name;
    //   const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

    //   if (fileExt.toLowerCase() !== "xlsx" && fileExt.toLowerCase() !== "xlsm") {
    //     this.$message.error("只能上传 Excel 文件！");
    //     // this.$refs.fileInput.value = ""; // 清空文件选择框
    //   }
    // },
    // //导入excel，取消按钮绑定取消所选的xlsx
    // resetFileInput() {
    //   this.$refs.fileInput.value = "";
    // },
    // /** 导入按钮 */
    // fileSend() {
    //   const formData = new FormData();
    //   const file = document.getElementById("inputFile").files[0];// 获取文件对象
    //   if (file === undefined) {
    //     this.$message.error("请选择文件!");
    //     return;
    //   } else {

    //     formData.append("InterestsFile", file);
    //     formData.append("yearAndMonth", this.form.yearAndMonth);

    //     // formData.append('file', fileInput.files[0]);
    //     // formData.append('yearAndMonth', '2021-07-01');

    //     // 将日期数据添加到一个对象中
    //     // const dataToSend = {
    //     //   InterestsFile: file,
    //     //   yearAndMonth: this.form.yearAndMonth
    //     // };

    //     axios({
    //       method: "post",
    //       url: "http://localhost:8080/financial/data/interests/import",
    //       headers: {
    //         "Content-Type": "multipart/form-data",
    //       },
    //       withCredentials: true,
    //       data: formData,
    //       onUploadProgress: (progressEvent) => {
    //         this.progress = Math.round(
    //           (progressEvent.loaded * 100) / progressEvent.total
    //         );
    //       },
    //     })
    //       .then(response => {
    //         // 处理请求成功的情况
    //         this.showDialog = false; // 关闭上传面板
    //         this.$message.success("上传成功");
    //       })
    //       .catch(error => {
    //         // 处理请求失败的情况
    //         console.error('上传失败：', error);
    //       });
    //     console.log("利润表")
    //     // }

    //     setTimeout(() => {
    //       this.showDialog = false; // 关闭上传面板

    //       // location.reload(); // 调用此方法刷新页面数据
    //     }, 2000); // 2000毫秒后关闭

    //   }
    // },
  }
};
</script>


<style lang="scss" scoped>
.currentPage {
  padding: 16px;
}
</style>

<template>
  <div class="currentPage">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- 利润表 -->
      <el-form-item label="年月" prop="yearAndMonth">
        <el-date-picker clearable v-model="queryParams.yearAndMonth" type="date" value-format="yyyy-MM-dd"
          placeholder="请选择年月">
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
        <el-button type="primary" icon="el-icon-share" size="mini" plain @click="showDialog = true" v-if="true">导入Excel
        </el-button>

        <el-dialog title="导入Excel" :visible.sync="showDialog" width="30%" @close="resetFileInput">
          <!-- 下拉框 -->
          <el-form :model="form" ref="form" label-width="90px">
            <el-form-item label="选择表类型">
              <el-select v-model="selectedType" placeholder="请选择Excel类型">
                <el-option label="利润表" value="profit"></el-option>
                <!-- <el-option label="资产负债表" value="balance"></el-option> -->
              </el-select>
            </el-form-item>
          </el-form>

          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

          <!-- 进度动画条 -->
          <div v-if="progress > 0">
            <el-progress :percentage="progress" color="rgb(19, 194, 194)"></el-progress>
          </div>

          <span slot="footer" class="dialog-footer">
            <el-button @click="showDialog = false">取 消</el-button>
            <el-button type="primary" @click="fileSend()">确 定</el-button>
          </span>
        </el-dialog>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['financial:interests:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="interestsList" @selection-change="handleSelectionChange"
      @sort-change="handleSortChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="年月" align="center" prop="yearAndMonth" width="120" sortable="custom">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.yearAndMonth, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="集团内主营业务收入" align="center" prop="internalMainrevenue" width="140" />
      <el-table-column label="集团外主营业务收入" align="center" prop="externalMainrevenue" width="140" />
      <el-table-column label="主营业务收入" align="center" prop="MainRevenue" />
      <el-table-column label="主营业务成本-产品销售SD" align="center" prop="cogsProductsalesSd" width="180" />
      <el-table-column label="主营业务成本-运费" align="center" prop="cogsFreight" />
      <el-table-column label="主营业务成本-运费变化" align="center" prop="cogsVariation" />
      <el-table-column label="主营业务成本" align="center" prop="COGS" />
      <el-table-column label="净利润" align="center" prop="NetProfit" />
      <el-table-column label="管理费用" align="center" prop="ManagementExpense" />
      <el-table-column label="研发费用" align="center" prop="rdExpense" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
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
    <el-dialog :title="title" :visible.sync="open" width="600px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="180px">
        <el-form-item label="年月" prop="yearAndMonth">
          <el-date-picker clearable v-model="form.yearAndMonth" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择年月">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="集团内主营业务收入" prop="internalMainrevenue">
          <el-input v-model="form.internalMainrevenue" placeholder="请输入集团内主营业务收入" />
        </el-form-item>
        <el-form-item label="集团外主营业务收入" prop="externalMainrevenue">
          <el-input v-model="form.externalMainrevenue" placeholder="请输入集团外主营业务收入" />
        </el-form-item>
        <el-form-item label="主营业务收入" prop="MainRevenue">
          <el-input v-model="form.MainRevenue" placeholder="请输入主营业务收入" />
        </el-form-item>
        <el-form-item label="主营业务成本-产品销售SD" prop="cogsProductsalesSd">
          <el-input v-model="form.cogsProductsalesSd" placeholder="请输入主营业务成本-产品销售SD" />
        </el-form-item>
        <el-form-item label="主营业务成本-运费" prop="cogsFreight">
          <el-input v-model="form.cogsFreight" placeholder="请输入主营业务成本-运费" />
        </el-form-item>
        <el-form-item label="主营业务成本-运费变化" prop="cogsVariation">
          <el-input v-model="form.cogsVariation" placeholder="请输入主营业务成本-运费变化" />
        </el-form-item>
        <el-form-item label="主营业务成本" prop="COGS">
          <el-input v-model="form.COGS" placeholder="请输入主营业务成本" />
        </el-form-item>
        <el-form-item label="净利润" prop="NetProfit">
          <el-input v-model="form.NetProfit" placeholder="请输入净利润" />
        </el-form-item>
        <el-form-item label="管理费用" prop="ManagementExpense">
          <el-input v-model="form.ManagementExpense" placeholder="请输入管理费用" />
        </el-form-item>
        <el-form-item label="研发费用" prop="rdExpense">
          <el-input v-model="form.rdExpense" placeholder="请输入研发费用" />
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
import axios from "axios";

export default {
  name: "Interests",
  data() {
    return {
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


      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 财务-利润表格数据
      interestsList: [],
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
        internalMainrevenue: null,
        externalMainrevenue: null,
        MainRevenue: null,
        cogsProductsalesSd: null,
        cogsFreight: null,
        cogsVariation: null,
        COGS: null,
        NetProfit: null,
        ManagementExpense: null,
        rdExpense: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        yearAndMonth: [
          { required: true, message: "日期不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();

  },
  methods: {
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
        fiId: null,
        createdBy: null,
        createdTime: null,
        yearAndMonth: null,
        internalMainrevenue: null,
        externalMainrevenue: null,
        MainRevenue: null,
        cogsProductsalesSd: null,
        cogsFreight: null,
        cogsVariation: null,
        COGS: null,
        NetProfit: null,
        ManagementExpense: null,
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
      this.title = "添加财务-利润";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fiId = row.fiId || this.ids
      getInterests(fiId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改财务-利润";
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
      this.$modal.confirm('是否确认删除日期为"' + date + '"的数据？').then(function () {
        return delInterests(fiIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },

    // 导入excel，检查文件类型
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm") {
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
      formData.append("excelFile", file);
      if (this.selectedType === 'profit') {
        axios({
          method: "post",
          // url: this.$http.url('/financial/data/upload'),
          url: "http://localhost:8080//financial/data/interests/import",
          // params: this.$http.adornParams({
          //   userName: this.$store.state.user.name,
          // }),
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
        })
          .then(response => {
            // 处理请求成功的情况
            this.showDialog = false; // 关闭上传面板
          })
          .catch(error => {
            // 处理请求失败的情况
            console.error('上传失败：', error);
          });
        console.log("利润表")
        // }
        this.$message.success("上传成功");
        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板

          // location.reload(); // 调用此方法刷新页面数据
        }, 2000); // 2000毫秒后关闭
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('financial/interests/export', {
        ...this.queryParams
      }, `interests_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>


<style lang="scss" scoped>
.currentPage {
  padding: 16px;
}
</style>
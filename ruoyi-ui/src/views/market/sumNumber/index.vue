<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="地区名称" prop="marketLedger">
        <el-input
          v-model="queryParams.marketLedger"
          placeholder="请输入地区名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单总数" prop="marketOrderSumnumber">
        <el-input
          v-model="queryParams.marketOrderSumnumber"
          placeholder="请输入订单总数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="统计时间" prop="marketTime">
        <el-date-picker clearable
          v-model="queryParams.marketTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择统计时间">
        </el-date-picker>
      </el-form-item>
      <!-- <el-form-item label="创建时间" prop="creatTime">
        <el-date-picker clearable
          v-model="queryParams.creatTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item> -->
      <el-form-item label="车型" prop="creatPeople">
        <el-input
          v-model="queryParams.creatPeople"
          placeholder="请输入车型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['system:sumNumber:add']"
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
          v-hasPermi="['system:sumNumber:edit']"
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
          v-hasPermi="['system:sumNumber:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:sumNumber:export']"
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
          v-hasPermi="['market:sumNumber:import']"
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

    <el-table v-loading="loading" :data="sumNumberList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id" />
      <el-table-column label="地区名称" align="center" prop="marketLedger" />
      <el-table-column label="订单总数" align="center" prop="marketOrderSumnumber" />
      <el-table-column label="统计时间" align="center" prop="marketTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.marketTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="创建时间" align="center" prop="creatTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.creatTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column> -->
      <el-table-column label="车型" align="center" prop="creatPeople" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['system:sumNumber:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:sumNumber:remove']"
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="地区名称" prop="marketLedger">
          <el-input v-model="form.marketLedger" placeholder="请输入地区名称" />
        </el-form-item>
        <el-form-item label="订单总数" prop="marketOrderSumnumber">
          <el-input v-model="form.marketOrderSumnumber" placeholder="请输入订单总数" />
        </el-form-item>
        <el-form-item label="统计时间" prop="marketTime">
          <el-date-picker clearable
            v-model="form.marketTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择统计时间">
          </el-date-picker>
        </el-form-item>
        <!-- <el-form-item label="创建时间" prop="creatTime">
          <el-date-picker clearable
            v-model="form.creatTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item> -->
        <el-form-item label="车型" prop="creatPeople">
          <el-input v-model="form.creatPeople" placeholder="请输入车型" />
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
import { listSumNumber, getSumNumber, delSumNumber, addSumNumber, updateSumNumber,uploadImport } from "@/api/system/sumNumber";

export default {
  name: "SumNumber",
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
      // 【请填写功能名称】表格数据
      sumNumberList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        marketLedger: null,
        marketOrderSumnumber: null,
        marketTime: null,
        creatTime: null,
        creatPeople: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      showDialog: false,
         progress: 0
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listSumNumber(this.queryParams).then(response => {
        this.sumNumberList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
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
    },
         fileSend() {
        const formData = new FormData();
        const file = document.getElementById("inputFile").files[0]; // 获取文件对象
        // console.log(file);
        formData.append("file", file);
        // console.log("file====>",formData)
        // axios({
        //   method: "post",
        //   // this $axios.post,
        //   url: "http://localhost:8080/market/ledger/import",
        //   // params:{
        //   //   userName: this.$store.state.user.name,
        //   // },
        //   headers: {
        //     "Content-Type": "multipart/form-data",
        //   },
        //   withCredentials: true,
        //   data: formData,
        //   onUploadProgress: (progressEvent) => {
        //     this.progress = Math.round(
        //       (progressEvent.loaded * 100) / progressEvent.total
        //     );
        //   },
        // });

        uploadImport(formData)
        .then(response => {
          // 文件上传成功

          setTimeout(() => {
            this.showDialog = false; // 关闭上传面板
            location.reload(); // 调用此方法刷新页面数据
          }, 5000); // 2000毫秒后关闭
          this.$message.success("上传成功");
        })
        .catch(error => {
          // 处理错误
          console.error('Error uploading file:', error);
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
        id: null,
        marketLedger: null,
        marketOrderSumnumber: null,
        marketTime: null,
        creatTime: null,
        creatPeople: null
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getSumNumber(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateSumNumber(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSumNumber(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除【请填写功能名称】编号为"' + ids + '"的数据项？').then(function() {
        return delSumNumber(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/sumNumber/export', {
        ...this.queryParams
      }, `sumNumber_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

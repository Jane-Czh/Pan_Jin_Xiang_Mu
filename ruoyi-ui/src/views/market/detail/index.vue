<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="上线时间" prop="onlineTime">
        <el-date-picker clearable
          v-model="queryParams.onlineTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上线时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="车号" prop="wagonNumber">
        <el-input
          v-model="queryParams.wagonNumber"
          placeholder="请输入车号"
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
      <!-- <el-form-item label="门架" prop="doorFrame">
        <el-input
          v-model="queryParams.doorFrame"
          placeholder="请输入门架"
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
      <el-form-item label="阀片数" prop="valveBlockNumber">
        <el-input
          v-model="queryParams.valveBlockNumber"
          placeholder="请输入阀片数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="配置" prop="Configuration">
        <el-input
          v-model="queryParams.Configuration"
          placeholder="请输入配置"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="计划完工期" prop="planndeCompletionTime">
        <el-date-picker clearable
          v-model="queryParams.planndeCompletionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择计划完工期">
        </el-date-picker>
      </el-form-item> -->
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
          v-hasPermi="['market:detail:add']"
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
          v-hasPermi="['market:detail:edit']"
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
          v-hasPermi="['market:detail:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:detail:export']"
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

    <el-table v-loading="loading" :data="detailList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="micdId" />
      <el-table-column label="上线时间" align="center" prop="onlineTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.onlineTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="车号" align="center" prop="wagonNumber" />
      <el-table-column label="车型" align="center" prop="vehicleModel" />
      <el-table-column label="门架" align="center" prop="doorFrame" />
      <el-table-column label="属具" align="center" prop="Accessory" />
      <el-table-column label="阀片数" align="center" prop="valveBlockNumber" />
      <el-table-column label="配置" align="center" prop="Configuration" />
      <el-table-column label="计划完工期" align="center" prop="planndeCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.planndeCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:detail:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:detail:remove']"
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

    <!-- 添加或修改库存车明细对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="上线时间" prop="onlineTime">
          <el-date-picker clearable
            v-model="form.onlineTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择上线时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="车号" prop="wagonNumber">
          <el-input v-model="form.wagonNumber" placeholder="请输入车号" />
        </el-form-item>
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="门架" prop="doorFrame">
          <el-input v-model="form.doorFrame" placeholder="请输入门架" />
        </el-form-item>
        <el-form-item label="属具" prop="Accessory">
          <el-input v-model="form.Accessory" placeholder="请输入属具" />
        </el-form-item>
        <el-form-item label="阀片数" prop="valveBlockNumber">
          <el-input v-model="form.valveBlockNumber" placeholder="请输入阀片数" />
        </el-form-item>
        <el-form-item label="配置" prop="Configuration">
          <el-input v-model="form.Configuration" placeholder="请输入配置" />
        </el-form-item>
        <el-form-item label="计划完工期" prop="planndeCompletionTime">
          <el-date-picker clearable
            v-model="form.planndeCompletionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择计划完工期">
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
import { listDetail, getDetail, delDetail, addDetail, updateDetail } from "@/api/market/detail";
import axios from 'axios'

export default {
  name: "Detail",
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
      // 库存车明细表格数据
      detailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        onlineTime: null,
        wagonNumber: null,
        vehicleModel: null,
        doorFrame: null,
        Accessory: null,
        valveBlockNumber: null,
        Configuration: null,
        planndeCompletionTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        wagonNumber: [
          { required: true, message: "车号不能为空", trigger: "blur" }
        ],
        vehicleModel: [
          { required: true, message: "车型不能为空", trigger: "blur" }
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
    /** 查询库存车明细列表 */
    getList() {
      this.loading = true;
      listDetail(this.queryParams).then(response => {
        this.detailList = response.rows;
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
        micdId: null,
        onlineTime: null,
        wagonNumber: null,
        vehicleModel: null,
        doorFrame: null,
        Accessory: null,
        valveBlockNumber: null,
        Configuration: null,
        planndeCompletionTime: null
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
      this.ids = selection.map(item => item.micdId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加库存车明细";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const micdId = row.micdId || this.ids
      getDetail(micdId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改库存车明细";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.micdId != null) {
            updateDetail(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDetail(this.form).then(response => {
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
      const micdIds = row.micdId || this.ids;
      this.$modal.confirm('是否确认删除库存车明细编号为"' + micdIds + '"的数据项？').then(function() {
        return delDetail(micdIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/detail/export', {
        ...this.queryParams
      }, `detail_${new Date().getTime()}.xlsx`)
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
        url: "http://localhost:8080/market/detail/import",
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

<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item>-->
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
<!--      </el-form-item>-->
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['product:rate:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['product:rate:edit']"-->
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
          v-hasPermi="['product:rate:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="syncReport"-->
<!--        >同步数据</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['product:rate:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['product:rate:import']"
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

    <el-table v-loading="loading" :data="rateList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键id" align="center" prop="pqrId" />-->
      <el-table-column label="型号" align="center" prop="model" />
      <el-table-column label="生产数量" align="center" prop="productionNumber" />
      <el-table-column label="合格数量" align="center" prop="qualifiedNumber" />
      <el-table-column label="不合格数量" align="center" prop="disqualifiedNumber" />
      <el-table-column label="合格率" align="center" prop="qualificationRate" />
      <el-table-column label="日期" align="center" prop="date" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.date, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>


      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:rate:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:rate:remove']"
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

    <!-- 添加或修改自制件合格率对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="型号" prop="model">
            <el-input v-model="form.model" placeholder="请输入型号" />
          </el-form-item>
          <el-form-item label="合格数量" prop="qualifiedNumber">
            <el-input v-model="form.qualifiedNumber" placeholder="请输入合格数量" />
          </el-form-item>
          <el-form-item label="不合格数量" prop="disqualifiedNumber">
            <el-input v-model="form.disqualifiedNumber" placeholder="请输入不合格数量" />
          </el-form-item>
          <el-form-item label="生产数量" prop="productionNumber">
            <el-input v-model="form.productionNumber" placeholder="请输入生产数量" />
          </el-form-item>
          <el-form-item label="合格率" prop="qualificationRate">
            <el-input v-model="form.qualificationRate" placeholder="请输入合格率" />
          </el-form-item>
          <el-form-item label="日期" prop="date">
            <el-date-picker clearable
                            v-model="form.date"
                            type="date"
                            value-format="yyyy-MM-dd"
                            placeholder="请选择日期">
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
import { listRate, getRate, delRate, addRate, updateRate,uploadFile } from "@/api/product/rate";
import axios from "axios";


export default {
  name: "Rate",
  data() {
    const validateModel = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入型号'));
      } else {
        const regex = /^[A-Za-z0-9]+$/;
        if (!regex.test(value)) {
          callback(new Error('型号只能包含英文字符或数字'));
        } else {
          callback();
        }
      }
    };
    const validateNumber = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入数字'));
      } else {
        const regex = /^\d+$/;
        if (!regex.test(value)) {
          callback(new Error('只能输入数字'));
        } else {
          callback();
        }
      }
    };

    const validateQualificationRate = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入合格率'));
      } else {
        const regex = /^\d+(\.\d+)?%?$/;
        if (!regex.test(value)) {
          callback(new Error('合格率只能输入数字和%'));
        } else {
          callback();
        }
      }
    };
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
      // 自制件合格率表格数据
      rateList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        model: [
          { required: true, message: '请输入型号', trigger: 'blur' },
          { validator: validateModel, trigger: 'blur' }
        ],
        qualifiedNumber: [
          { required: true, message: '请输入合格数量', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' }
        ],
        disqualifiedNumber: [
          { required: true, message: '请输入不合格数量', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' }
        ],
        productionNumber: [
          { required: true, message: '请输入生产数量', trigger: 'blur' },
          { validator: validateNumber, trigger: 'blur' }
        ],
        qualificationRate: [
          { required: true, message: '请输入合格率', trigger: 'blur' },
          { validator: validateQualificationRate, trigger: 'blur' }
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
    /*同步*/
    // syncReport() {
    //   // 使用 Fetch API 发送 POST 请求到后端
    //   fetch('http://localhost:8080/product/rate/synchronization', {
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
    /** 查询自制件合格率列表 */
    getList() {
      this.loading = true;
      const newRegulationId =1;
      listRate(this.queryParams).then(response => {
        this.rateList = response.rows;
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
        pqrId: null,
        qualificationRate: null,
        qualifiedNumber: null,
        productionNumber: null,
        date: null,
        model: null,
        disqualifiedNumber: null
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
      this.ids = selection.map(item => item.pqrId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加自制件合格率";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pqrId = row.pqrId || this.ids
      getRate(pqrId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改自制件合格率";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pqrId != null) {
            updateRate(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRate(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
        else {
          console.log('表单验证失败!');
          return false;
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const pqrIds = row.pqrId || this.ids;
      this.$modal.confirm('是否确认删除自制件合格率编号为"' + pqrIds + '"的数据项？').then(function() {
        return delRate(pqrIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/rate/export', {
        ...this.queryParams
      }, `rate_${new Date().getTime()}.xlsx`)
    },



    // fileSend() {
    //   const formData = new FormData();
    //   const file = document.getElementById("inputFile").files[0]; // 获取文件对象
    //   console.log(file);
    //   formData.append("file", file);
    //   console.log("file====>",formData)
    //   axios({
    //     method: "post",
    //     // this $axios.post,
    //     url: "http://localhost:8080/product/rate/Dimport",
    //     // params:{
    //     //   userName: this.$store.state.user.name,
    //     // },
    //     headers: {
    //       "Content-Type": "multipart/form-data",
    //     },
    //     withCredentials: true,
    //     data: formData,
    //     onUploadProgress: (progressEvent) => {
    //       this.progress = Math.round(
    //         (progressEvent.loaded * 100) / progressEvent.total
    //       );
    //     },
    //   });
    //   // this.$message.success("上传成功");
    //
    //
    //   setTimeout(() => {
    //     this.showDialog = false; // 关闭上传面板
    //
    //     // location.reload(); // 调用此方法刷新页面数据
    //   }, 2000); // 2000毫秒后关闭
    //   this.getList();
    // },

    async fileSend() {
      try {
        const fileInput = document.getElementById("inputFile");
        if (!fileInput.files.length) {
          this.$message.error("请选择上传文件");
          return;
        }
        const formData = new FormData();
        const file = document.getElementById("inputFile").files[0]; // 获取文件对象
        console.log(file);
        formData.append("file", file);
        console.log("file====>", formData);

        await uploadFile(formData); // 调用 uploadFile API

        // 上传成功后的处理
        this.$message.success("上传成功"); // 提示上传成功
        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板
          // location.reload(); // 调用此方法刷新页面数据
        }, 1000); // 2000毫秒后关闭
        this.getList();
      } catch (error) {
        console.error('上传失败!', error);
      }
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

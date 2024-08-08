<template>
  <div class="app-container">
    <!--    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">-->
    <!--      <el-form-item label="编号" prop="Number">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.Number"-->
    <!--          placeholder="请输入编号"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="姓名" prop="Name">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.Name"-->
    <!--          placeholder="请输入姓名"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="科室" prop="Document">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.Document"-->
    <!--          placeholder="请输入科室"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="身份证号" prop="idNumber">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.idNumber"-->
    <!--          placeholder="请输入身份证号"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="电话号码" prop="phoneNumber">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.phoneNumber"-->
    <!--          placeholder="请输入电话号码"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第一次合同起始" prop="firstContractPeriodStart">-->
    <!--        <el-date-picker clearable-->
    <!--                        v-model="queryParams.firstContractPeriodStart"-->
    <!--                        type="date"-->
    <!--                        value-format="yyyy-MM-dd"-->
    <!--                        placeholder="请选择第一次合同起始">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第一次合同结束" prop="firstContractPeriodEnd">-->
    <!--        <el-date-picker clearable-->
    <!--                        v-model="queryParams.firstContractPeriodEnd"-->
    <!--                        type="date"-->
    <!--                        value-format="yyyy-MM-dd"-->
    <!--                        placeholder="请选择第一次合同结束">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第一次合同状况" prop="contractStatusFirstly">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.contractStatusFirstly"-->
    <!--          placeholder="请输入第一次合同状况"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第二次合同起始" prop="secondContractPeriodStart">-->
    <!--        <el-date-picker clearable-->
    <!--                        v-model="queryParams.secondContractPeriodStart"-->
    <!--                        type="date"-->
    <!--                        value-format="yyyy-MM-dd"-->
    <!--                        placeholder="请选择第二次合同起始">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第二次合同结束" prop="secondContractPeriodEnd">-->
    <!--        <el-date-picker clearable-->
    <!--                        v-model="queryParams.secondContractPeriodEnd"-->
    <!--                        type="date"-->
    <!--                        value-format="yyyy-MM-dd"-->
    <!--                        placeholder="请选择第二次合同结束">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第二次合同状况" prop="contractStatusSecondly">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.contractStatusSecondly"-->
    <!--          placeholder="请输入第二次合同状况"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第三次合同起始" prop="thirdContractPeriodStart">-->
    <!--        <el-date-picker clearable-->
    <!--                        v-model="queryParams.thirdContractPeriodStart"-->
    <!--                        type="date"-->
    <!--                        value-format="yyyy-MM-dd"-->
    <!--                        placeholder="请选择第三次合同起始">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第三次合同结束" prop="thirdContractPeriodEnd">-->
    <!--        <el-date-picker clearable-->
    <!--                        v-model="queryParams.thirdContractPeriodEnd"-->
    <!--                        type="date"-->
    <!--                        value-format="yyyy-MM-dd"-->
    <!--                        placeholder="请选择第三次合同结束">-->
    <!--        </el-date-picker>-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="第三次合同状况" prop="contractStatusThirdly">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.contractStatusThirdly"-->
    <!--          placeholder="请输入第三次合同状况"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item label="是否预警" prop="Warn">-->
    <!--        <el-input-->
    <!--          v-model="queryParams.Warn"-->
    <!--          placeholder="请输入是否预警"-->
    <!--          clearable-->
    <!--          @keyup.enter.native="handleQuery"-->
    <!--        />-->
    <!--      </el-form-item>-->
    <!--      <el-form-item>-->
    <!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
    <!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
    <!--      </el-form-item>-->
    <!--    </el-form>-->

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['Enterprisemanagement:LbaorLedger:add']">新增</el-button>
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          plain-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['Enterprisemanagement:LbaorLedger:edit']"-->
      <!--        >修改</el-button>-->
      <!--      </el-col>-->
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['Enterprisemanagement:LbaorLedger:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-download" size="mini" @click="showDialog = true"
          v-hasPermi="['Enterprisemanagement:LbaorLedger:import']">导入Excel文件</el-button>
        <el-dialog title="导入Excel文件" :visible.sync="showDialog" width="30%" :before-close="handleClose"
          @close="resetFileInput">
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
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="warning"-->
      <!--          plain-->
      <!--          icon="el-icon-download"-->
      <!--          size="mini"-->
      <!--          @click="handleExport"-->
      <!--          v-hasPermi="['Enterprisemanagement:LbaorLedger:export']"-->
      <!--        >导出</el-button>-->
      <!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="LbaorLedgerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="主键" align="center" prop="emlcId" />-->
      <!--      <el-table-column label="编号" align="center" prop="Number" />-->
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="科室" align="center" prop="document" />
      <el-table-column label="就职状态" align="center" prop="employmentStatus" />
      <el-table-column label="身份证号" align="center" prop="idNumber" />
      <el-table-column label="电话号码" align="center" prop="phoneNumber" />
      <el-table-column label="第一次合同起始" align="center" prop="firstContractPeriodStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.firstContractPeriodStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第一次合同结束" align="center" prop="firstContractPeriodEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.firstContractPeriodEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第一次合同状况" align="center" prop="contractStatusFirstly" />
      <el-table-column label="第二次合同起始" align="center" prop="secondContractPeriodStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.secondContractPeriodStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第二次合同结束" align="center" prop="secondContractPeriodEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.secondContractPeriodEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第二次合同状况" align="center" prop="contractStatusSecondly" />
      <el-table-column label="第三次合同起始" align="center" prop="thirdContractPeriodStart" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.thirdContractPeriodStart, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第三次合同结束" align="center" prop="thirdContractPeriodEnd" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.thirdContractPeriodEnd, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第三次合同状况" align="center" prop="contractStatusThirdly" />
      <!--      <el-table-column label="是否预警" align="center" prop="warn" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['Enterprisemanagement:LbaorLedger:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['Enterprisemanagement:LbaorLedger:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改劳动合同台账导入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <!--        <el-form-item label="编号" prop="number">-->
        <!--          <el-input v-model="form.Number" placeholder="请输入编号" />-->
        <!--        </el-form-item>-->
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="科室" prop="document">
          <el-input v-model="form.document" placeholder="请输入科室" />
        </el-form-item>
        <el-form-item label="就职状态" prop="employmentStatus">
          <el-input v-model="form.employmentStatus" placeholder="请输入就职状态" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="电话号码" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入电话号码" />
        </el-form-item>
        <el-form-item label="第一次合同起始" prop="firstContractPeriodStart">
          <el-date-picker clearable v-model="form.firstContractPeriodStart" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择第一次合同起始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第一次合同结束" prop="firstContractPeriodEnd">
          <el-date-picker clearable v-model="form.firstContractPeriodEnd" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择第一次合同结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第一次合同状况" prop="contractStatusFirstly">
          <el-input v-model="form.contractStatusFirstly" placeholder="请输入第一次合同状况" />
        </el-form-item>
        <el-form-item label="第二次合同起始" prop="secondContractPeriodStart">
          <el-date-picker clearable v-model="form.secondContractPeriodStart" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择第二次合同起始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第二次合同结束" prop="secondContractPeriodEnd">
          <el-date-picker clearable v-model="form.secondContractPeriodEnd" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择第二次合同结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第二次合同状况" prop="contractStatusSecondly">
          <el-input v-model="form.contractStatusSecondly" placeholder="请输入第二次合同状况" />
        </el-form-item>
        <el-form-item label="第三次合同起始" prop="thirdContractPeriodStart">
          <el-date-picker clearable v-model="form.thirdContractPeriodStart" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择第三次合同起始">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第三次合同结束" prop="thirdContractPeriodEnd">
          <el-date-picker clearable v-model="form.thirdContractPeriodEnd" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择第三次合同结束">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第三次合同状况" prop="contractStatusThirdly">
          <el-input v-model="form.contractStatusThirdly" placeholder="请输入第三次合同状况" />
        </el-form-item>
        <!--        <el-form-item label="是否预警" prop="warn">-->
        <!--          <el-input v-model="form.Warn" placeholder="请输入是否预警" />-->
        <!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listLbaorLedger, getLbaorLedger, delLbaorLedger, addLbaorLedger, updateLbaorLedger, uploadFile } from "@/api/Enterprisemanagement/LbaorLedger";
import axios from "axios";
export default {
  name: "LbaorLedger",
  data() {
    const validateChinese = (rule, value, callback) => {
      if (value && !/^[\u4e00-\u9fa5]+$/.test(value)) {
        callback(new Error('只能输入中文字符'));
      } else {
        callback();
      }
    };

    const validateAlphanumeric = (rule, value, callback) => {
      if (value && !/^[a-zA-Z0-9]+$/.test(value)) {
        callback(new Error('只能输入英文字符或数字'));
      } else {
        callback();
      }
    };

    const validateNumber = (rule, value, callback) => {
      if (value && !/^\d+$/.test(value)) {
        callback(new Error('只能输入数字'));
      } else {
        callback();
      }
    };

    const validateChineseOrPunctuation = (rule, value, callback) => {
      if (value && !/^[\u4e00-\u9fa5\p{P}]+$/u.test(value)) {
        callback(new Error('只能输入中文字符或标点符号'));
      } else {
        callback();
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
      // 劳动合同台账导入表格数据
      LbaorLedgerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        number: null,
        name: null,
        document: null,
        employmentStatus: null,
        idNumber: null,
        phoneNumber: null,
        firstContractPeriodStart: null,
        firstContractPeriodEnd: null,
        contractStatusFirstly: null,
        secondContractPeriodStart: null,
        secondContractPeriodEnd: null,
        contractStatusSecondly: null,
        thirdContractPeriodStart: null,
        thirdContractPeriodEnd: null,
        contractStatusThirdly: null,
        warn: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        name: [
          { required: false },
          { validator: validateChinese, trigger: 'blur' }
        ],
        document: [
          { required: false },
          { validator: validateChinese, trigger: 'blur' }
        ],
        employmentStatus: [
          { required: false },
          { validator: validateChinese, trigger: 'blur' }
        ],
        idNumber: [
          { required: true, message: '请输入身份证号', trigger: 'blur' },
          { validator: validateAlphanumeric, trigger: 'blur' }
        ],
        phoneNumber: [
          { required: false },
          { validator: validateNumber, trigger: 'blur' }
        ],
        contractStatusFirstly: [
          { required: false },
          { validator: validateChineseOrPunctuation, trigger: 'blur' }
        ],
        contractStatusSecondly: [
          { required: false },
          { validator: validateChineseOrPunctuation, trigger: 'blur' }
        ],
        contractStatusThirdly: [
          { required: false },
          { validator: validateChineseOrPunctuation, trigger: 'blur' }
        ]
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
    /** 查询劳动合同台账导入列表 */
    getList() {
      this.loading = true;
      listLbaorLedger(this.queryParams).then(response => {
        this.LbaorLedgerList = response.rows;
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
        emlcId: null,
        Number: null,
        Name: null,
        Document: null,
        employmentStatus: null,
        idNumber: null,
        phoneNumber: null,
        firstContractPeriodStart: null,
        firstContractPeriodEnd: null,
        contractStatusFirstly: null,
        secondContractPeriodStart: null,
        secondContractPeriodEnd: null,
        contractStatusSecondly: null,
        thirdContractPeriodStart: null,
        thirdContractPeriodEnd: null,
        contractStatusThirdly: null,
        Warn: null
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
      this.ids = selection.map(item => item.emlcId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加劳动合同台账导入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emlcId = row.emlcId || this.ids
      getLbaorLedger(emlcId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改劳动合同台账导入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emlcId != null) {
            updateLbaorLedger(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addLbaorLedger(this.form).then(response => {
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
      const emlcIds = row.emlcId || this.ids;
      this.$modal.confirm('是否确认删除劳动合同台账导入编号为"' + emlcIds + '"的数据项？').then(function () {
        return delLbaorLedger(emlcIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Enterprisemanagement/LbaorLedger/export', {
        ...this.queryParams
      }, `LbaorLedger_${new Date().getTime()}.xlsx`)
    },
    async fileSend() {
      const fileInput = document.getElementById("inputFile");
      if (!fileInput.files.length) {
        this.$message.error("请选择上传文件");
        return;
      }
      try {
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
        .catch(_ => { });
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

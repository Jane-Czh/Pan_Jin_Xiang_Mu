<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="Number">
        <el-input
          v-model="queryParams.Number"
          placeholder="请输入序号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="部门" prop="Department">
        <el-input
          v-model="queryParams.Department"
          placeholder="请输入部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="姓名" prop="Name">
        <el-input
          v-model="queryParams.Name"
          placeholder="请输入姓名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="Gender">
        <el-input
          v-model="queryParams.Gender"
          placeholder="请输入性别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发证机关" prop="issuingAuthority">
        <el-input
          v-model="queryParams.issuingAuthority"
          placeholder="请输入发证机关"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="作业类别" prop="assignmentCategory">
        <el-input
          v-model="queryParams.assignmentCategory"
          placeholder="请输入作业类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idNumber">
        <el-input
          v-model="queryParams.idNumber"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="证件编号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入证件编号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="取证时间" prop="evidenceCollectionTime">
        <el-date-picker clearable
                        v-model="queryParams.evidenceCollectionTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择取证时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="第一次到期" prop="firstExpiration">
        <el-date-picker clearable
                        v-model="queryParams.firstExpiration"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择第一次到期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="第一次复审" prop="reexaminationFirstly">
        <el-date-picker clearable
                        v-model="queryParams.reexaminationFirstly"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择第一次复审">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="第二次到期" prop="secondExpiration">
        <el-date-picker clearable
                        v-model="queryParams.secondExpiration"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择第二次到期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="第二次复审" prop="reexaminationSecondly">
        <el-date-picker clearable
                        v-model="queryParams.reexaminationSecondly"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择第二次复审">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="第三次到期" prop="thirdExpiration">
        <el-date-picker clearable
                        v-model="queryParams.thirdExpiration"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择第三次到期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="第三次复审" prop="reexaminationThirdly">
        <el-date-picker clearable
                        v-model="queryParams.reexaminationThirdly"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择第三次复审">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="是否预警到期" prop="Warn">
        <el-input
          v-model="queryParams.Warn"
          placeholder="请输入是否预警到期"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建人" prop="createdBy">
        <el-input
          v-model="queryParams.createdBy"
          placeholder="请输入创建人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createdTime">
        <el-date-picker clearable
                        v-model="queryParams.createdTime"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>-->
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          plain-->
<!--          icon="el-icon-plus"-->
<!--          size="mini"-->
<!--          @click="handleAdd"-->
<!--          v-hasPermi="['Enterprisemanagement:specialwarning:add']"-->
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
<!--          v-hasPermi="['Enterprisemanagement:specialwarning:edit']"-->
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
          v-hasPermi="['Enterprisemanagement:specialwarning:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['Enterprisemanagement:specialwarning:export']"-->
<!--        >导出</el-button>-->
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="syncReport"
          >同步数据</el-button>
        </el-col>
<!--      </el-col>-->
<!--      <el-col>-->
<!--        &lt;!&ndash;Excel 参数导入 &ndash;&gt;-->
<!--        <el-button-->
<!--          type="primary"-->
<!--          @click="showDialog = true"-->
<!--        ><i class="fa fa-download"></i>导入Excel文件-->
<!--        </el-button>-->
<!--        <el-dialog-->
<!--          title="导入Excel文件"-->
<!--          :visible.sync="showDialog"-->
<!--          width="30%"-->
<!--          :before-close="handleClose"-->
<!--          @close="resetFileInput"-->
<!--        >-->
<!--          <i class="el-icon-upload"></i>-->
<!--          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />-->

<!--          &lt;!&ndash; 进度动画条 &ndash;&gt;-->
<!--          <div v-if="progress > 0">-->
<!--            <el-progress-->
<!--              :percentage="progress"-->
<!--              color="rgb(19, 194, 194)"-->
<!--            ></el-progress>-->
<!--          </div>-->

<!--          <span slot="footer" class="dialog-footer">-->
<!--          <el-button @click="showDialog = false">取 消</el-button>-->
<!--          <el-button type="primary" @click="fileSend()">确 定</el-button>-->
<!--        </span>-->
<!--        </el-dialog>-->
<!--      </el-col>-->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="specialwarningList" @selection-change="handleSelectionChange" :row-style="rowStyle">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="emsoId" />-->
<!--      <el-table-column label="序号" align="center" prop="number" />-->
      <el-table-column label="部门" align="center" prop="department" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="发证机关" align="center" prop="issuingAuthority" />
      <el-table-column label="证件类型" align="center" prop="documentType" />
      <el-table-column label="作业类别" align="center" prop="assignmentCategory" />
      <el-table-column label="身份证号" align="center" prop="idNumber" />
      <el-table-column label="证件编号" align="center" prop="idCard" />
      <el-table-column label="取证时间" align="center" prop="evidenceCollectionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.evidenceCollectionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第一次到期" align="center" prop="firstExpiration" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.firstExpiration, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第一次复审" align="center" prop="reexaminationFirstly" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reexaminationFirstly, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第二次到期" align="center" prop="secondExpiration" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.secondExpiration, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第二次复审" align="center" prop="reexaminationSecondly" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reexaminationSecondly, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第三次到期" align="center" prop="thirdExpiration" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.thirdExpiration, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="第三次复审" align="center" prop="reexaminationThirdly" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.reexaminationThirdly, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="是否预警到期" align="center" prop="warn" >
      <template slot-scope="scope">
        <span :style="{ color: scope.row.warn === '是' ? 'red' : 'inherit' }">{{ scope.row.warn }}</span>
      </template>
      </el-table-column>
      <el-table-column label="创建人" align="center" prop="createdBy" />
      <el-table-column label="创建时间" align="center" prop="createdTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Enterprisemanagement:specialwarning:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Enterprisemanagement:specialwarning:remove']"
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

    <!-- 添加或修改企管特种作业证到期预警对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="序号" prop="Number">
          <el-input v-model="form.Number" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="部门" prop="Department">
          <el-input v-model="form.Department" placeholder="请输入部门" />
        </el-form-item>
        <el-form-item label="姓名" prop="Name">
          <el-input v-model="form.Name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="Gender">
          <el-input v-model="form.Gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="发证机关" prop="issuingAuthority">
          <el-input v-model="form.issuingAuthority" placeholder="请输入发证机关" />
        </el-form-item>
        <el-form-item label="作业类别" prop="assignmentCategory">
          <el-input v-model="form.assignmentCategory" placeholder="请输入作业类别" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入身份证号" />
        </el-form-item>
        <el-form-item label="证件编号" prop="idCard">
          <el-input v-model="form.idCard" placeholder="请输入证件编号" />
        </el-form-item>
        <el-form-item label="取证时间" prop="evidenceCollectionTime">
          <el-date-picker clearable
                          v-model="form.evidenceCollectionTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择取证时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第一次到期" prop="firstExpiration">
          <el-date-picker clearable
                          v-model="form.firstExpiration"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择第一次到期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第一次复审" prop="reexaminationFirstly">
          <el-date-picker clearable
                          v-model="form.reexaminationFirstly"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择第一次复审">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第二次到期" prop="secondExpiration">
          <el-date-picker clearable
                          v-model="form.secondExpiration"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择第二次到期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第二次复审" prop="reexaminationSecondly">
          <el-date-picker clearable
                          v-model="form.reexaminationSecondly"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择第二次复审">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第三次到期" prop="thirdExpiration">
          <el-date-picker clearable
                          v-model="form.thirdExpiration"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择第三次到期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第三次复审" prop="reexaminationThirdly">
          <el-date-picker clearable
                          v-model="form.reexaminationThirdly"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择第三次复审">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="是否预警到期" prop="Warn">
          <el-input v-model="form.Warn" placeholder="请输入是否预警到期" />
        </el-form-item>
        <el-form-item label="创建人" prop="createdBy">
          <el-input v-model="form.createdBy" placeholder="请输入创建人" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createdTime">
          <el-date-picker clearable
                          v-model="form.createdTime"
                          type="date"
                          value-format="yyyy-MM-dd"
                          placeholder="请选择创建时间">
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
import { listSpecialwarning, getSpecialwarning, delSpecialwarning, addSpecialwarning, updateSpecialwarning } from "@/api/Enterprisemanagement/specialwarning";
import axios from "axios";
import {parseTime} from "../../../utils/ruoyi";

export default {
  name: "Specialwarning",
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
      // 企管特种作业证到期预警表格数据
      specialwarningList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Number: null,
        Department: null,
        Name: null,
        Gender: null,
        issuingAuthority: null,
        documentType: null,
        assignmentCategory: null,
        idNumber: null,
        idCard: null,
        evidenceCollectionTime: null,
        firstExpiration: null,
        reexaminationFirstly: null,
        secondExpiration: null,
        reexaminationSecondly: null,
        thirdExpiration: null,
        reexaminationThirdly: null,
        Warn: null,
        createdBy: null,
        createdTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
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
    parseTime,
    syncReport() {
      // 使用 Fetch API 发送 POST 请求到后端
      fetch('http://localhost:8080/Enterprisemanagement/specialwarning/synchronization', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        }
      })
        .then(response => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
          // 如果请求成功，可以进行下一步操作
        })
        .catch(error => {
          console.error('There was an error!', error);
        });
      this.getList();
    },
    /** 查询企管特种作业证到期预警列表 */
    getList() {
      this.loading = true;
      listSpecialwarning(this.queryParams).then(response => {
        this.specialwarningList = response.rows;
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
        emsoId: null,
        Number: null,
        Department: null,
        Name: null,
        Gender: null,
        issuingAuthority: null,
        documentType: null,
        assignmentCategory: null,
        idNumber: null,
        idCard: null,
        evidenceCollectionTime: null,
        firstExpiration: null,
        reexaminationFirstly: null,
        secondExpiration: null,
        reexaminationSecondly: null,
        thirdExpiration: null,
        reexaminationThirdly: null,
        Warn: null,
        createdBy: null,
        createdTime: null
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
      this.ids = selection.map(item => item.emsoId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加企管特种作业证到期预警";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const emsoId = row.emsoId || this.ids
      getSpecialwarning(emsoId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改企管特种作业证到期预警";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.emsoId != null) {
            updateSpecialwarning(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addSpecialwarning(this.form).then(response => {
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
      const emsoIds = row.emsoId || this.ids;
      this.$modal.confirm('是否确认删除企管特种作业证到期预警编号为"' + emsoIds + '"的数据项？').then(function() {
        return delSpecialwarning(emsoIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Enterprisemanagement/specialwarning/export', {
        ...this.queryParams
      }, `specialwarning_${new Date().getTime()}.xlsx`)
    },
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      console.log(file);
      formData.append("file", file);
      console.log("file====>",formData)
      axios({
        method: "post",
        // this $axios.post,
        url: "http://localhost:8080/Enterprisemanagement/specialwarning/SOimport",
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

        // location.reload(); // 调用此方法刷新页面数据
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

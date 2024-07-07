<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="Number">-->
<!--        <el-input-->
<!--          v-model="queryParams.Number"-->
<!--          placeholder="请输入序号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="员工编号" prop="idNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.idNumber"-->
<!--          placeholder="请输入员工编号"-->
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
<!--      <el-form-item label="性别" prop="Gender">-->
<!--        <el-input-->
<!--          v-model="queryParams.Gender"-->
<!--          placeholder="请输入性别"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="申请加班开始时间" prop="applicationForOvertimeStartTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.applicationForOvertimeStartTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择申请加班开始时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="申请加班结束时间" prop="applicationForOvertimeEndTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.applicationForOvertimeEndTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择申请加班结束时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
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
<!--          v-hasPermi="['product:Oaform:add']"-->
<!--        >新增</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['product:Oaform:edit']"-->
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
          v-hasPermi="['product:Oaform:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['product:Oaform:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['product:Oaform:import']"
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

    <el-table v-loading="loading" :data="OaformList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="poafId" />-->
<!--      <el-table-column label="序号" align="center" prop="Number" />-->
      <el-table-column label="员工编号" align="center" prop="idNumber" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="申请加班开始时间" align="center" prop="applicationForOvertimeStartTime" width="180">

      </el-table-column>
      <el-table-column label="申请加班结束时间" align="center" prop="applicationForOvertimeEndTime" width="180">

      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:Oaform:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:Oaform:remove']"
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

    <!-- 添加或修改加班申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="序号" prop="Number">
          <el-input v-model="form.Number" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="员工编号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入员工编号" />
        </el-form-item>
        <el-form-item label="姓名" prop="Name">
          <el-input v-model="form.Name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="Gender">
          <el-input v-model="form.Gender" placeholder="请输入性别" />
        </el-form-item>
<!--        <el-form-item label="申请加班开始时间" prop="applicationForOvertimeStartTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.applicationForOvertimeStartTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择申请加班开始时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
        <el-form-item label="申请加班开始时间" prop="applicationForOvertimeStartTime">
          <el-date-picker
            clearable
            v-model="form.applicationForOvertimeStartTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择申请加班开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="申请加班结束时间" prop="applicationForOvertimeEndTime">
          <el-date-picker clearable
            v-model="form.applicationForOvertimeEndTime"
            type="datetime"
            value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择申请加班结束时间">
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
import { listOaform, getOaform, delOaform, addOaform, updateOaform } from "@/api/product/Oaform";
import axios from "axios";

export default {
  name: "Oaform",
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
      // 加班申请表格数据
      OaformList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Number: null,
        idNumber: null,
        Name: null,
        Gender: null,
        applicationForOvertimeStartTime: null,
        applicationForOvertimeEndTime: null
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
    /** 查询加班申请列表 */
    getList() {
      this.loading = true;
      listOaform(this.queryParams).then(response => {
        this.OaformList = response.rows;
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
        poafId: null,
        Number: null,
        idNumber: null,
        Name: null,
        Gender: null,
        applicationForOvertimeStartTime: null,
        applicationForOvertimeEndTime: null
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
      this.ids = selection.map(item => item.poafId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加加班申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const poafId = row.poafId || this.ids
      getOaform(poafId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改加班申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.poafId != null) {
            updateOaform(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOaform(this.form).then(response => {
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
      const poafIds = row.poafId || this.ids;
      this.$modal.confirm('是否确认删除加班申请编号为"' + poafIds + '"的数据项？').then(function() {
        return delOaform(poafIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/Oaform/export', {
        ...this.queryParams
      }, `Oaform_${new Date().getTime()}.xlsx`)
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
        url: "http://localhost:8080/product/Oaform/Oaimport",
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
      this.getList();
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

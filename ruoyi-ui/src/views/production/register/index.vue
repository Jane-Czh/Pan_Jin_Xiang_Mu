<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="关键字" prop="Keyword">
        <el-input
          v-model="queryParams.Keyword"
          placeholder="请输入关键字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下发对象" prop="deliverObject">
        <el-input
          v-model="queryParams.deliverObject"
          placeholder="请输入下发对象"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="下发时间" prop="deliveryTime">
        <el-date-picker clearable
          v-model="queryParams.deliveryTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择下发时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="文件名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名称"
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
          v-hasPermi="['production:register:add']"
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
          v-hasPermi="['production:register:edit']"
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
          v-hasPermi="['production:register:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['production:register:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="registerList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="psjrfId" />
      <el-table-column label="关键字" align="center" prop="keyword" />
      <el-table-column label="下发对象" align="center" prop="deliverObject" />
      <el-table-column label="下发时间" align="center" prop="deliveryTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.deliveryTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['production:register:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['production:register:remove']"
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

    <!-- 添加或修改记录标准作业信息对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="关键字" prop="Keyword">
          <el-input v-model="form.Keyword" placeholder="请输入关键字" />
        </el-form-item>
        <el-form-item label="下发对象" prop="deliverObject">
          <el-input v-model="form.deliverObject" placeholder="请输入下发对象" />
        </el-form-item>
        <el-form-item label="下发时间" prop="deliveryTime">
          <el-date-picker clearable
            v-model="form.deliveryTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择下发时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="文件名称" prop="fileName">
          <el-input v-model="form.fileName" placeholder="请输入文件名称" />
        </el-form-item>
        
        <el-form-item label="文件上传" >
          <el-upload
          v-model="form.filePath"
          class="upload-file-uploader"
          :action="uploadFileUrl"
          :headers="headers"
          :on-change="handleFileChange"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :on-exceed="handleExceed"
          :on-success="handleUploadSuccess"
          multiple
          :limit="3"
          :file-list="fileList"
          >
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
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
import { listRegister, getRegister, delRegister, addRegister, updateRegister } from "@/api/production/register";
import {getUserProfile} from '@/api/system/user'
import {getDept} from '@/api/system/dept'
import {getToken} from "@/utils/auth"

export default {
  name: "Register",
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
      // 记录标准作业信息表格数据
      registerList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        Keyword: null,
        deliverObject: null,
        deliveryTime: null,
        fileName: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },

      //新增
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      headers: {
          Authorization: "Bearer " + getToken(),
        }
    };
  },

  watch: {
      value: {
        handler(val) {
          if (val) {
            let temp = 1;
            // 首先将值转为数组
            const list = Array.isArray(val) ? val : this.value.split(',');
            // 然后将数组转为对象数组
            this.fileList = list.map(item => {
              if (typeof item === "string") {
                item = {name: item, url: item};
              }
              item.uid = item.uid || new Date().getTime() + temp++;
              return item;
            });
          } else {
            this.fileList = [];
            return [];
          }
        },
        deep: true,
        immediate: true
      }
    },
    computed: {
      // 是否显示提示
      showTip() {
        return this.isShowTip && (this.fileType || this.fileSize);
      },
    },

  created() {
    this.getList();
  },
  methods: {
    /** 查询记录标准作业信息列表 */
    getList() {
      this.loading = true;
      listRegister(this.queryParams).then(response => {
        this.registerList = response.rows;
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
        psjrfId: null,
        Keyword: null,
        deliverObject: null,
        deliveryTime: null,
        fileName: null
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
      this.ids = selection.map(item => item.psjrfId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加记录标准作业信息";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const psjrfId = row.psjrfId || this.ids
      getRegister(psjrfId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改记录标准作业信息";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.psjrfId != null) {
            updateRegister(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRegister(this.form).then(response => {
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
      const psjrfIds = row.psjrfId || this.ids;
      this.$modal.confirm('是否确认删除记录标准作业信息编号为"' + psjrfIds + '"的数据项？').then(function() {
        return delRegister(psjrfIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('production/register/export', {
        ...this.queryParams
      }, `register_${new Date().getTime()}.xlsx`)
    },


    // 新增
    handleFileChange(file, fileList) {
        console.log("urlurl1===>", this.uploadFileUrl)
        console.log("urlurl1===>", this.baseURL)
        const uploadedFile = file.raw; // 获取上传的文件对象

        // 将文件名填充到对应的输入框
        this.form.fileName = uploadedFile.name;
        // //将文件路径填充到对应的输入框
        this.form.filePath = this.path;
        console.log(this.path);
        console.log(this.form.fileName);
        // this.form.filePath = uploadedFile.webkitRelativePath || uploadedFile.relativePath || "文件路径不可用";
        // 将文件类型填充到对应的输入框
        this.form.fileType = this.getFileType(uploadedFile.type);

        // 将文件大小填充到对应的输入框
        // this.form.fileSize = this.formatFileSize(uploadedFile.size);
        this.form.fileSize = uploadedFile.size;
        // 获取当前时间作为上传日期，并填充到对应的输入框
        const currentDate = new Date();
        this.form.uploadDate = currentDate.toISOString().split('T')[0];
        this.getUserInfo();

    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
        if (res.code === 200) {
          console.log("上传成功回调");
          console.log(res);
          this.path = res.url;
          console.log(res.url);
          console.log(this.path);
          this.uploadList.push({name: res.fileName, url: res.fileName});
          this.uploadedSuccessfully();
        } else {
          this.number--;
          this.$modal.closeLoading();
          this.$modal.msgError(res.msg);
          this.$refs.fileUpload.handleRemove(file);
          this.uploadedSuccessfully();
        }
    },
    // 上传结束处理
    uploadedSuccessfully() {
        if (this.number > 0 && this.uploadList.length === this.number) {
          this.fileList = this.fileList.concat(this.uploadList);
          this.uploadList = [];
          this.number = 0;
          this.$emit("input", this.listToString(this.fileList));
          this.$modal.closeLoading();
        }
    },
    handleRemove(file, fileList) {
        console.log(file, fileList);
    },
    handlePreview(file) {
        console.log(file);
    },
    handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 2 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
    },
    beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${file.name}？`);
    },
    // 文件大小自动转换单位
    formatFileSize(sizeInBytes) {
        const KB = 1024;
        const MB = KB * 1024;
        const GB = MB * 1024;

        if (sizeInBytes < KB) {
          return sizeInBytes + "KB";
        } else if (sizeInBytes < MB) {
          return (sizeInBytes / KB).toFixed(2) + "KB";
        } else if (sizeInBytes < GB) {
          return (sizeInBytes / MB).toFixed(2) + "MB";
        } else {
          return (sizeInBytes / GB).toFixed(2) + "GB";
        }
    },
    getFileType(fullType) {  //获取详细的文件类型
      // 根据完整的文件类型(fullType)获取简短的文件类型
      if (fullType.includes('pdf')) {
          return 'pdf';
        } else if (fullType.includes('word')) {
          return 'word';
        } else {
          // 其他类型的文件处理方式
          return 'other';
        }
    },
    // 调用接口获取用户信息
    getUserInfo() {
        getUserProfile().then(response => {
          // 处理成功的情况
          console.log('成功获取用户信息:', response.data)
          const userInfo = response.data // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
          // 填充到对应的输入框中
          this.form.createUsername = userInfo.userName
          //根据部门id获取部门名称
          getDept(userInfo.deptId).then(response => {
            const deptInfo = response.data
            this.form.departmentCategory = deptInfo.deptName
          })
        }).catch(error => {
          // 处理失败的情况
          console.error('获取用户信息失败:', error)
        })
    },
    //文件预览
    previewFile(filePath) {
        // 使用 window.open 方法打开一个新窗口，并将文件路径传递给该窗口
        window.open(filePath, '_blank');
    }
  }
};
</script>

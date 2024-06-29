<template>
  <div class="app-container">

    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="检查时间" prop="checkTime">
        <el-date-picker clearable
          v-model="queryParams.checkTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择检查时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="检查班组" prop="checkTeam">
        <el-input
          v-model="queryParams.checkTeam"
          placeholder="请输入检查班组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="设备问题点" prop="equipmentProblemSpot">
        <el-input
          v-model="queryParams.equipmentProblemSpot"
          placeholder="请输入设备问题点"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="责任人" prop="chargePerson">
        <el-input
          v-model="queryParams.chargePerson"
          placeholder="请输入责任人"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="整改完成时间" prop="rectificationCompletionTime">
        <el-date-picker clearable
          v-model="queryParams.rectificationCompletionTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择整改完成时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="整改前照片" prop="preRectificationPhoto">
        <el-input
          v-model="queryParams.preRectificationPhoto"
          placeholder="请输入整改前照片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="整改后照片" prop="postRectificationPhoto">
        <el-input
          v-model="queryParams.postRectificationPhoto"
          placeholder="请输入整改后照片"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="考核分数" prop="assessmentScore">
        <el-input
          v-model="queryParams.assessmentScore"
          placeholder="请输入考核分数"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="验收人" prop="accepter">
        <el-input
          v-model="queryParams.accepter"
          placeholder="请输入验收人"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
          v-hasPermi="['safety:entering:add']"
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
          v-hasPermi="['safety:entering:edit']"
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
          v-hasPermi="['safety:entering:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['safety:entering:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="enteringList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="seaeId" /> -->
      <el-table-column label="检查时间" align="center" prop="checkTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.checkTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="检查班组" align="center" prop="checkTeam" />
      <el-table-column label="设备问题点" align="center" prop="equipmentProblemSpot" />
      <el-table-column label="责任人" align="center" prop="chargePerson" />
      <el-table-column label="整改完成时间" align="center" prop="rectificationCompletionTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.rectificationCompletionTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="整改前照片" align="center" prop="preRectificationPhoto">
        <template slot-scope="scope">
          <!-- 假设 preRectificationPhoto 是一个图片 URL -->
          <!-- <img :src="scope.row.preRectificationPhoto" alt="整改前后照片" style="max-width: 100px; max-height: 100px;" /> -->
          <!-- 添加预览按钮 -->
          <el-button type="text" @click="previewImage(scope.row.preRectificationPhoto)">预览</el-button>
        </template>
      </el-table-column>

      <el-table-column label="整改后照片" align="center" prop="postRectificationPhoto">
        <template slot-scope="scope">
          <!-- 添加预览按钮 -->
          <el-button type="text" @click="previewImage(scope.row.postRectificationPhoto)">预览</el-button>
        </template>
      </el-table-column>

      <el-table-column label="考核分数" align="center" prop="assessmentScore" />
      <el-table-column label="验收人" align="center" prop="accepter" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['safety:entering:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['safety:entering:remove']"
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

    <!-- 添加或修改安环科考核录入对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="检查时间" prop="checkTime">
          <el-date-picker clearable
            v-model="form.checkTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择检查时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="检查班组" prop="checkTeam">
          <el-input v-model="form.checkTeam" placeholder="请输入检查班组" />
        </el-form-item>
        <el-form-item label="设备问题点" prop="equipmentProblemSpot">
          <el-input v-model="form.equipmentProblemSpot" placeholder="请输入设备问题点" />
        </el-form-item>
        <el-form-item label="责任人" prop="chargePerson">
          <el-input v-model="form.chargePerson" placeholder="请输入责任人" />
        </el-form-item>
        <el-form-item label="整改完成时间" prop="rectificationCompletionTime">
          <el-date-picker clearable
            v-model="form.rectificationCompletionTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择整改完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="整改前照片" prop="preRectificationPhoto">
          <!-- <el-input v-model="form.preRectificationPhoto" placeholder="请输入整改前后照片" /> -->
          <el-upload
            ref="fileUpload"
            v-model="fileInfo.filePath"
            class="upload-file-uploader"
            :action="uploadFileUrl"
            :headers="headers"
            :on-change="handleFileChange"
            :on-success="prehandleUploadSuccess"
            multiple
            :limit="3"
            :file-list="fileList"
            :accept="acceptedFileTypes"
            list-type="picture-card"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>

        <el-form-item label="整改后照片" prop="postRectificationPhoto">
          <!-- <el-input v-model="form.preRectificationPhoto" placeholder="请输入整改前后照片" /> -->
          <el-upload
            ref="fileUpload"
            v-model="fileInfo.filePath"
            class="upload-file-uploader"
            :action="uploadFileUrl"
            :headers="headers"
            :on-change="handleFileChange"
            :on-success="posthandleUploadSuccess"
            multiple
            :limit="3"
            :file-list="fileList"
            :accept="acceptedFileTypes"
            list-type="picture-card"
          >
            <el-button size="small" type="primary">点击上传</el-button>
          </el-upload>
        </el-form-item>



        <!-- <el-form-item label="整改后照片" prop="postRectificationPhoto">
          <el-input v-model="form.postRectificationPhoto" placeholder="请输入整改后照片" />
        </el-form-item> -->
        <el-form-item label="考核分数" prop="assessmentScore">
          <el-input v-model="form.assessmentScore" placeholder="请输入考核分数" />
        </el-form-item>
        <el-form-item label="验收人" prop="accepter">
          <el-input v-model="form.accepter" placeholder="请输入验收人" />
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
import { listEntering, getEntering, delEntering, addEntering, updateEntering } from "@/api/safety/entering";
import {getUserProfile} from '@/api/system/user'
import {getDept} from '@/api/system/dept'
import {getToken} from "@/utils/auth"

export default {
  name: "Entering",
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
      // 安环科考核录入表格数据
      enteringList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        checkTime: null,
        checkTeam: null,
        equipmentProblemSpot: null,
        chargePerson: null,
        rectificationCompletionTime: null,
        preRectificationPhoto: null,
        postRectificationPhoto: null,
        assessmentScore: null,
        accepter: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        checkTime: [
          { required: true, message: "检查时间不能为空", trigger: "blur" }
        ],
        checkTeam: [
          { required: true, message: "检查班组不能为空", trigger: "blur" }
        ],
        chargePerson: [
          { required: true, message: "责任人不能为空", trigger: "blur" }
        ],
        accepter: [
          { required: true, message: "验收人不能为空", trigger: "blur" }
        ]
      },

      fileInfo: {
        filePath: '',
        fileName: '',
        fileType: '',
        fileSize: 0,
        uploadDate: '',
        createUsername: '',
        departmentCategory: ''
      },
      fileList: [],
      uploadList: [],
      headers: {
        Authorization: `Bearer ${getToken()}`
      },
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      acceptedFileTypes: '.jpg,.png' // 只接受 .jpg 和 .png 文件

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询安环科考核录入列表 */
    getList() {
      this.loading = true;
      listEntering(this.queryParams).then(response => {
        this.enteringList = response.rows;
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
        seaeId: null,
        checkTime: null,
        checkTeam: null,
        equipmentProblemSpot: null,
        chargePerson: null,
        rectificationCompletionTime: null,
        preRectificationPhoto: null,
        postRectificationPhoto: null,
        assessmentScore: null,
        accepter: null
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
      this.ids = selection.map(item => item.seaeId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加安环科考核录入";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const seaeId = row.seaeId || this.ids
      getEntering(seaeId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改安环科考核录入";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.seaeId != null) {
            updateEntering(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addEntering(this.form).then(response => {
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
      const seaeIds = row.seaeId || this.ids;
      this.$modal.confirm('是否确认删除安环科考核录入编号为"' + seaeIds + '"的数据项？').then(function() {
        return delEntering(seaeIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('safety/entering/export', {
        ...this.queryParams
      }, `entering_${new Date().getTime()}.xlsx`)
    },

      //&&&&&&&&&&&&&&&&&&&&&&&&&&//
      handleFileChange(file, fileList) {
        const uploadedFile = file.raw; // 获取上传的文件对象

        // 更新文件信息
        this.$set(this.fileInfo, 'fileName', uploadedFile.name);
        this.$set(this.fileInfo, 'filePath', '');
        this.$set(this.fileInfo, 'fileType', this.getFileType(uploadedFile.type));
        this.$set(this.fileInfo, 'fileSize', this.formatFileSize(uploadedFile.size));
        this.$set(this.fileInfo, 'uploadDate', new Date().toISOString().split('T')[0]);
        this.getUserInfo();
    },
    prehandleUploadSuccess(res, file) {
      //将返回的地址存入数据库中
      // console.log(res.url);
      this.form.preRectificationPhoto = res.url;

      if (res.code === 200) {
        this.path = res.url;
        this.uploadList.push({ name: res.fileName, url: res.fileName });
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$refs.fileUpload.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },
    posthandleUploadSuccess(res, file) {
      //将返回的地址存入数据库中
      // console.log(res.url);
      this.form.postRectificationPhoto = res.url;

      if (res.code === 200) {
        this.path = res.url;
        this.uploadList.push({ name: res.fileName, url: res.fileName });
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$refs.fileUpload.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },
    formatFileSize(sizeInBytes) {
      const KB = 1024;
      const MB = KB * 1024;
      const GB = MB * 1024;

      if (sizeInBytes < KB) {
        return sizeInBytes + "B";
      } else if (sizeInBytes < MB) {
        return (sizeInBytes / KB).toFixed(2) + "KB";
      } else if (sizeInBytes < GB) {
        return (sizeInBytes / MB).toFixed(2) + "MB";
      } else {
        return (sizeInBytes / GB).toFixed(2) + "GB";
      }
    },
    getFileType(fullType) {
      if (fullType.includes('jpeg') || fullType.includes('jpg')) {
        return 'jpg';
      } else if (fullType.includes('png')) {
        return 'png';
      } else {
        return 'other';
      }
    },
    getUserInfo() {
      getUserProfile().then(response => {
        const userInfo = response.data;
        this.$set(this.fileInfo, 'createUsername', userInfo.userName);
        getDept(userInfo.deptId).then(response => {
          const deptInfo = response.data;
          this.$set(this.fileInfo, 'departmentCategory', deptInfo.deptName);
        });
      }).catch(error => {
        console.error('获取用户信息失败:', error);
      });
    },
    uploadedSuccessfully() {
      if (this.number > 0 && this.uploadList.length === this.number) {
        this.fileList = this.fileList.concat(this.uploadList);
        this.uploadList = [];
        this.number = 0;
        this.$emit("input", this.fileList.map(file => file.url).join(','));
      }
    },
    previewImage(photoUrl) {
      // 在这里实现预览图片的逻辑
      // 例如，可以打开一个模态框显示图片
      console.log('预览图片', photoUrl);
      if (!photoUrl) {
        // 如果URL为空，显示提示信息
        this.$message({
          message: '没有图片可供预览',
          type: 'warning'
        });
        return;
      }
      window.open(photoUrl, '_blank');
    },
  },








};
</script>

<style scoped>
.upload-file-uploader {
  display: inline-block;
}
</style>

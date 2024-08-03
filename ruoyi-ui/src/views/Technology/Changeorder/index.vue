<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="number">-->
<!--        <el-input-->
<!--          v-model="queryParams.number"-->
<!--          placeholder="请输入序号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="变更单号" prop="changeorderId">-->
<!--        <el-input-->
<!--          v-model="queryParams.changeorderId"-->
<!--          placeholder="请输入变更单号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="信息描述" prop="information">-->
<!--        <el-input-->
<!--          v-model="queryParams.information"-->
<!--          placeholder="请输入信息描述"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="接收人/实施人" prop="receiver">-->
<!--        <el-input-->
<!--          v-model="queryParams.receiver"-->
<!--          placeholder="请输入接收人/实施人"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="完成情况" prop="isfinish">-->
<!--        <el-input-->
<!--          v-model="queryParams.isfinish"-->
<!--          placeholder="请输入完成情况"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="分配时间" prop="assignmentTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.assignmentTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择分配时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="完成时间" prop="finishTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.finishTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择完成时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="凭证归档" prop="voucher">-->
<!--        <el-input-->
<!--          v-model="queryParams.voucher"-->
<!--          placeholder="请输入凭证归档"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="文件地址" prop="filepath">-->
<!--        <el-input-->
<!--          v-model="queryParams.filepath"-->
<!--          placeholder="请输入文件地址"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="图片地址" prop="imagepath">-->
<!--        <el-input-->
<!--          v-model="queryParams.imagepath"-->
<!--          placeholder="请输入图片地址"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
          v-hasPermi="['Technology:Changeorder:add']"
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
<!--          v-hasPermi="['Technology:Changeorder:edit']"-->
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
          v-hasPermi="['Technology:Changeorder:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['Technology:Changeorder:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
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

          <!--          &lt;!&ndash; 进度动画条 &ndash;&gt;-->
          <!--          <div v-if="progress > 0">-->
          <!--            <el-progress-->
          <!--              :percentage="progress"-->
          <!--              color="rgb(19, 194, 194)"-->
          <!--            ></el-progress>-->
          <!--          </div>-->

          <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取 消</el-button>
          <el-button type="primary" @click="fileSend()">确 定</el-button>
        </span>
        </el-dialog>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ChangeorderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="changetableId" />-->
      <el-table-column label="序号" align="center" prop="number" />
      <el-table-column label="变更单号" align="center" prop="changeorderId" />
      <el-table-column label="信息描述" align="center" prop="information" />
      <el-table-column label="接收人/实施人" align="center" prop="receiver" />
      <el-table-column label="完成情况" align="center" prop="isfinish" />
      <el-table-column label="分配时间" align="center" prop="assignmentTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.assignmentTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="finishTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finishTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="凭证归档" align="center" prop="voucher" />
      <el-table-column label="类型/生效时间" align="center" prop="type" />
      <el-table-column label="备注" align="center" prop="remark" />
<!--      <el-table-column label="文件地址" align="center" prop="filepath" />-->
<!--      <el-table-column label="附件下载" align="center" prop="filePath">-->
<!--        <template slot-scope="scope">-->
<!--          <el-button-->
<!--            type="primary"-->
<!--            icon="el-icon-download"-->
<!--            size="mini"-->
<!--            @click="downloadFile(scope.row.filepath)"-->
<!--          >-->
<!--            下载-->
<!--          </el-button>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="附件下载" align="center" prop="filepath">
        <template slot-scope="scope">
          <div v-if="scope.row.filepath">
            <el-button
              type="primary"
              icon="el-icon-download"
              size="mini"
              @click="downloadFile(scope.row.filepath)"
            >
              下载
            </el-button>
          </div>
          <div v-else>
            <span>无文件</span>
          </div>
        </template>
      </el-table-column>
      <!--      <el-table-column label="图片地址" align="center" prop="imagepath" />-->
      <el-table-column label="图片地址" align="center" prop="imagepath">
        <template slot-scope="scope">
          <div v-if="scope.row.imagepath">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-view"
              @click="handlePreviewImage(scope.row)"
            >图片预览</el-button>
          </div>
          <div v-else>
            <span>无图片</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Technology:Changeorder:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Technology:Changeorder:remove']"
          >删除</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-upload"
            @click="handleUpload(scope.row)"
          >上传附件</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-picture"
            @click="handleImageUpload(scope.row)"
          >上传图片</el-button>
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

    <!-- 上传文件对话框 -->
    <el-dialog :title="title" :visible.sync="formUploadDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--表单上传-->
              <el-upload
                v-model="form.filepath"
                class="upload-form-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :on-change="handleFileChange"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :on-exceed="handleExceed"
                :on-success="handleUploadSuccess"
                :limit=limit
                :file-list="fileList"
              >
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="uploadSubmitForm">确 定</el-button>
        <el-button @click="uploadCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 上传图片对话框 -->
    <el-dialog :title="imageUploadTitle" :visible.sync="imageUploadDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="imageForm" :model="imageForm" :rules="imageRules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传图片按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--表单上传-->
              <el-upload
                v-model="imageForm.imagepath"
                class="upload-form-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :on-change="handleImageFileChange"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :on-exceed="handleExceed"
                :on-success="handleImageUploadSuccess"
                :limit=limit
                :file-list="imageFileList"
                accept="image/*"
              >
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="uploadImageSubmitForm">确 定</el-button>
        <el-button @click="uploadImageCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 预览图片对话框 -->
    <el-dialog :title="previewImageTitle" :visible.sync="previewImageDialogVisible" width="50%" :center="true" append-to-body>
      <el-image :src="previewImageUrl" fit="contain" style="width: 100%; height: auto;"></el-image>
    </el-dialog>


<!--        <el-row>-->
<!--          <el-col :span="12">-->
<!--            <el-form-item label="附件标题" prop="formTitle">-->
<!--              <el-input v-model="form.formTitle" placeholder="请输入表单标题"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span='12'>-->
<!--            <el-form-item label="表单存储内容" prop="scope">-->
<!--              <el-input v-model="form.scope" placeholder="请输入表单存储内容"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->

<!--        <el-row>-->
<!--          <el-col :span='12'>-->
<!--            <el-form-item label="表单大小" prop="formSize">-->
<!--              <el-input v-model="form.formSize" placeholder="请输入表单大小" :disabled="true"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--          <el-col :span='12'>-->
<!--            <el-form-item label="表单标签名称" prop="remark">-->
<!--              <el-input v-model="form.remark" placeholder="请输入表单标签名称"/>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->
<!--        <el-row>-->
<!--          <el-col :span='12'>-->
<!--            <el-form-item label="所属科室" prop="departmentCategory">-->
<!--              <el-select v-model="form.departmentCategory" placeholder="请输入表单所属科室">-->
<!--                &lt;!&ndash; 循环遍历this.deptList中的部门数据 &ndash;&gt;-->
<!--                <el-option v-for="dept in deptList" :key="dept.deptId" :label="dept.deptName" :value="dept.deptName"></el-option>-->
<!--              </el-select>-->
<!--            </el-form-item>-->
<!--          </el-col>-->
<!--        </el-row>-->


    <!-- 添加或修改变更单留存对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="序号" prop="number">
          <el-input v-model="form.number" placeholder="请输入序号" />
        </el-form-item>
        <el-form-item label="变更单号" prop="changeorderId">
          <el-input v-model="form.changeorderId" placeholder="请输入变更单号" />
        </el-form-item>
        <el-form-item label="信息描述" prop="information">
          <el-input v-model="form.information" placeholder="请输入信息描述" />
        </el-form-item>
        <el-form-item label="接收人/实施人" prop="receiver">
          <el-input v-model="form.receiver" placeholder="请输入接收人/实施人" />
        </el-form-item>
        <el-form-item label="完成情况" prop="isfinish">
          <el-input v-model="form.isfinish" placeholder="请输入完成情况" />
        </el-form-item>
        <el-form-item label="分配时间" prop="assignmentTime">
          <el-date-picker clearable
            v-model="form.assignmentTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择分配时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成时间" prop="finishTime">
          <el-date-picker clearable
            v-model="form.finishTime"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择完成时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="凭证归档" prop="voucher">
          <el-input v-model="form.voucher" placeholder="请输入凭证归档" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="文件地址" prop="filepath">
          <el-input v-model="form.filepath" placeholder="请输入文件地址" />
        </el-form-item>
        <el-form-item label="图片地址" prop="imagepath">
          <el-input v-model="form.imagepath" placeholder="请输入图片地址" />
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
import { listChangeorder, getChangeorder, delChangeorder, addChangeorder, updateChangeorder } from "@/api/Technology/Changeorder";
import {uploadFile} from "../../../api/Technology/Changeorder";
import {getToken} from "../../../utils/auth";

export default {
  name: "Changeorder",
  props: {
    value: [String, Object, Array],
    limit: {
      type: Number,
      default: 1,
    },
    fileSize: {
      type: Number,
      default: 5,
    },
    fileType: {
      type: Array,
      default: () => ["doc", "docx", "pdf"],
    },
    isShowTip: {
      type: Boolean,
      default: true,
    },
  },
  data() {
    return {
      // 其他数据属性
      previewImageDialogVisible: false,
      previewImageTitle: "预览图片",
      previewImageUrl: "",
      imageUploadTitle: "",
      imageForm: {},
      imageFileList: [],
      imageRules: {
        // 图片上传的校验规则
      },
      uploadList: [],
      path: "",
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      fileList: [],
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
      // 变更单留存表格数据
      ChangeorderList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      formUploadDialogVisible: false,
      imageUploadDialogVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        number: null,
        changeorderId: null,
        information: null,
        receiver: null,
        isfinish: null,
        assignmentTime: null,
        finishTime: null,
        voucher: null,
        type: null,
        filepath: null,
        imagepath: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      },
      showDialog: false,
      progress: 0,
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 预览图片 */
    handlePreviewImage(row) {
      if (row.imagepath) {
        this.previewImageUrl = row.imagepath;
        this.previewImageDialogVisible = true;
      } else {
        this.$message.warning("当前行没有图片可供预览");
      }
    },
    /** 上传图片 */
    handleImageUpload(row) {
      if (row.imagepath) {
        this.$confirm('当前行已存在图片，再次上传将会替换当前图片，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.reset();
          this.imageForm = { ...row }; // 将当前行的数据复制到 imageForm 对象
          this.imageUploadDialogVisible = true;
          this.imageUploadTitle = "上传图片";
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消上传'
          });
        });
      } else {
        this.reset();
        this.imageForm = { ...row }; // 将当前行的数据复制到 imageForm 对象
        this.imageUploadDialogVisible = true;
        this.imageUploadTitle = "上传图片";
      }
    },

    /** 图片文件改变事件 */
    handleImageFileChange(file, fileList) {
      this.imageFileList = fileList;
      const uploadedFile = file.raw; // 获取上传的文件对象
      this.imageForm.imagepath = this.path; // 将文件路径填充到对应的输入框
      console.log('上传图片时的imageForm', this.imageForm);
    },

    /** 图片上传成功回调 */
    handleImageUploadSuccess(res, file) {
      if (res.code === 200) {
        console.log("图片上传成功回调");
        console.log(res);
        this.path = res.url;
        this.imageForm.imagepath = res.url; // 更新 imageForm 对象的 imagePath 属性
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        this.$refs.fileUpload.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },

    /** 上传图片提交按钮 */
    uploadImageSubmitForm() {
      // 首先检查 imageFileList 是否为空
      if (!this.imageForm.imagepath) {
        this.$message.error("请上传图片");
        return;
      }
      this.$refs["imageForm"].validate(valid => {
        if (valid) {
          this.imageForm.newFlag = 1;
          updateChangeorder(this.imageForm).then(response => {
            this.$modal.msgSuccess("上传成功");
            this.imageUploadDialogVisible = false;
            this.getList();
          });
        }
      });
      this.imageFileList = [];
    },

    /** 图片上传取消按钮 */
    uploadImageCancel() {
      this.imageUploadDialogVisible = false;
      this.reset();
      this.imageFileList = [];
    },
    /** 文件下载 */
    downloadFile(url) {
      fetch(url)
        .then((response) => response.blob())
        .then((blob) => {
          const downloadUrl = window.URL.createObjectURL(blob);
          const link = document.createElement("a");
          link.href = downloadUrl;
          link.setAttribute(
            "download",
            decodeURIComponent(url.split("/").pop())
          ); // 解码文件名
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(downloadUrl);
        })
        .catch((error) => console.error("Download error:", error));
    },
    /** 查询变更单留存列表 */
    getList() {
      this.loading = true;
      listChangeorder(this.queryParams).then(response => {
        this.ChangeorderList = response.rows;
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
        changetableId: null,
        number: null,
        changeorderId: null,
        information: null,
        receiver: null,
        isfinish: null,
        assignmentTime: null,
        finishTime: null,
        voucher: null,
        type: null,
        remark: null,
        filepath: null,
        imagepath: null
      };
      this.resetForm("form");
    },
    // 文件上传取消按钮
    uploadCancel() {
      this.formUploadDialogVisible = false;
      this.reset();
      this.fileList = [];
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      const uploadedFile = file.raw; // 获取上传的文件对象
      // const uploadedFileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.')); // 获取上传文件的文件名
      // // 发起请求检查文件名是否存在于数据库中
      // const isFormNameDuplicate = this.ChangeorderList.some(item => item.formName === uploadedFileName);
      // console.log("发起请求检查文件名是否存在于数据库中",isFormNameDuplicate);

      // if (isFormNameDuplicate) {
      //   // 如果文件名重复，弹出警告框
      //   this.$modal.msgWarning('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
      //   console.log('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
      //   return false; // 中断上传流程
      // }
      if (res.code === 200) {
        console.log("上传成功回调");
        console.log(res);
        this.path = res.url;
        this.uploadList.push({name: res.fileName, url: res.fileName});
        this.form.filepath = res.url; // 更新 form 对象的 filePath 属性
        console.log("此时的文件路径" ,this.form.filepath)
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
    handleExceed(files, fileList) {
      this.$message.warning(`当前限制选择 1 个文件`);
    },
    handleRemove(file, fileList) {
      console.log(file, fileList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleFileChange(file, fileList) {
      this.fileList = fileList;
      console.log('上传文件时的form1',this.form);
      console.log('fileList=====>',this.fileList);
      console.log("上传文件时的file====>", file);
      const uploadedFile = file.raw; // 获取上传的文件对象
      // // 将文件名填充到对应的输入框
      // this.form.fileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.'));
      // //将文件路径填充到对应的输入框
      this.form.filepath = this.path;
      // // 将文件类型填充到对应的输入框
      // this.form.fileType = this.getFileType(this.form.filePath);
      // // 将文件大小填充到对应的输入框
      // this.form.fileSize = this.formatFileSize(uploadedFile.size);
      // this.form.formSize = uploadedFile.size;
      // 获取当前时间作为上传日期，并填充到对应的输入框
      const currentDate = new Date();
      // this.form.effectiveDate = currentDate.toISOString().split('T')[0];
      // this.getUserInfo();
      console.log('上传文件时的form2',this.form);
    },
    getFileType(filepath) {
      // 获取文件名的后缀名
      const fileExtension = filepath.split(".").pop();
      console.log("fileExtension=>", fileExtension);

      // 根据文件后缀名判断文件类型
      switch (fileExtension.toLowerCase()) {
        case "jpg":
          return "图片";
        case "pdf":
          return "pdf";
        case "doc":
        case "docx":
          return "word";
        case "xls":
        case "xlsx":
          return "Excel 文档";
        case "ppt":
        case "pptx":
          return "PowerPoint 文档";
        // 可以根据需要添加更多的文件类型判断
        default:
          return "未知类型";
      }
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
      this.ids = selection.map(item => item.changetableId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 上传文件 */
    handleUpload(row) {
      if (row.filepath) {
        this.$confirm('当前行已存在附件，再次上传将会替换当前文件，是否继续？', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
      this.reset();
      this.formUploadDialogVisible = true;
      this.form = { ...row }; // 将当前行的数据复制到 form 对象
      this.title = "上传文件";
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消上传'
          });
        });
      } else {
        this.reset();
        this.form = { ...row }; // 将当前行的数据复制到 form 对象
        this.formUploadDialogVisible = true;
        this.title = "上传文件";
      }
    },
    /** 上传文件提交按钮 */
    uploadSubmitForm() {
      // 首先检查 fileList 是否为空
      if (!this.form.filepath) {
        this.$message.error("请上传文件");
        return;
      }
      this.$refs["form"].validate(valid => {
        if (valid) {
          updateChangeorder(this.form).then(response => {
            console.log("本条文件记录" ,this.form)
            this.$modal.msgSuccess("上传成功");
            this.formUploadDialogVisible = false;
            this.getList();
          });
        }
      });
      this.fileList = [];
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加变更单留存";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const changetableId = row.changetableId || this.ids
      getChangeorder(changetableId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改变更单留存";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.changetableId != null) {
            updateChangeorder(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addChangeorder(this.form).then(response => {
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
      const changetableIds = row.changetableId || this.ids;
      this.$modal.confirm('是否确认删除变更单留存编号为"' + changetableIds + '"的数据项？').then(function() {
        return delChangeorder(changetableIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('Technology/Changeorder/export', {
        ...this.queryParams
      }, `Changeorder_${new Date().getTime()}.xlsx`)
    },
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

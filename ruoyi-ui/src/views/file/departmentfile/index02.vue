<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文件标题" prop="fileTitle">
        <el-input
          v-model="queryParams.fileTitle"
          placeholder="请输入文件标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="文件名称" prop="fileName">
        <el-input
          v-model="queryParams.fileName"
          placeholder="请输入文件名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
<!--      <el-form-item label="文件大小" prop="fileSize">-->
<!--        <el-input-->
<!--          v-model="queryParams.fileSize"-->
<!--          placeholder="请输入文件大小"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
      <el-form-item label="所属科室" prop="departmentCategory">
        <el-input
          v-model="queryParams.departmentCategory"
          placeholder="请输入所属科室"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="标签" prop="fileTag">
        <el-input
          v-model="queryParams.fileTag"
          placeholder="请输入标签"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上传日期" prop="uploadDate">
        <el-date-picker clearable
          v-model="queryParams.uploadDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择上传日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上传人" prop="uploadUsername">
        <el-input
          v-model="queryParams.uploadUsername"
          placeholder="请输入上传人"
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
          v-hasPermi="['departmentFile:table:add']"
        >上传</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['departmentFile:table:edit']"
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
          v-hasPermi="['departmentFile:table:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['departmentFile:table:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="tableList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="文件id" align="center" prop="fileId" />
      <el-table-column label="文件标题" align="center" prop="fileTitle" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件下载" align="center" prop="filePath">
        <template v-slot:default="scope">
          <a v-if="scope.row.filePath" @click.prevent="downloadFile(scope.row.filePath)" style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="文件类型" align="center" prop="fileType" />
      <el-table-column label="文件大小" align="center" prop="fileSize" />
      <el-table-column label="所属科室" align="center" prop="departmentCategory" />
      <el-table-column label="标签" align="center" prop="fileTag" />
      <el-table-column label="上传日期" align="center" prop="uploadDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上传人" align="center" prop="uploadUsername" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="previewFile(scope.row.filePath)"
          >预览
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['departmentFile:table:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['departmentFile:table:remove']"
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

    <!-- 修改部门文件对话框 -->
    <el-dialog :title="title" :visible.sync="fileModifyDialogVisible" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件标题" prop="fileTitle">
          <el-input v-model="form.fileTitle" placeholder="请输入文件标题" />
        </el-form-item>
        <el-form-item label="所属科室" prop="departmentCategory">
          <el-select v-model="form.departmentCategory" placeholder="请输入文件所属科室">
            <!-- 循环遍历this.deptList中的部门数据 -->
            <el-option v-for="dept in deptList" :key="dept.deptId" :label="dept.deptName" :value="dept.deptName"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签" prop="fileTag">
          <el-input v-model="form.fileTag" placeholder="请输入标签" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="modifyCancel">取 消</el-button>
      </div>
    </el-dialog>
<!--  上传部门文件对话框  -->
    <el-dialog :title="title" :visible.sync="fileUploadDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--科室文件上传-->
              <el-upload
                v-model="form.filePath"
                class="upload-file-uploader"
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
        <el-row>
          <el-col :span="12">
            <el-form-item label="文件标题" prop="fileTitle">
              <el-input v-model="form.fileTitle" placeholder="请输入文件标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="所属科室" prop="departmentCategory">
              <el-select v-model="form.departmentCategory" placeholder="请输入文件所属科室">
                <!-- 循环遍历this.deptList中的部门数据 -->
                <el-option v-for="dept in deptList" :key="dept.deptId" :label="dept.deptName" :value="dept.deptName"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="24">
            <el-form-item label="标签" prop="fileTag">
              <el-input v-model="form.fileTag" placeholder="请输入文件标签" />
            </el-form-item>
          </el-col>
        </el-row>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="uploadCancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import { listTable, getTable, delTable, addTable, updateTable } from "@/api/file/departmentfile";
import {getToken} from "@/utils/auth";
import {getDept02, getUserProfile02, listDept02, word2Pdf} from "@/api/file/filemanagement";

export default {
  name: "departmentFile",
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
      //部门列表
      deptList: [],
      //当前账号的dept
      thisDept: null,
      //文件上传绑定的部门
      fileDept: null,
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      path: '',
      baseUrl: process.env.VUE_APP_BASE_API,
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
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
      // 部门文件表格数据
      tableList: [],
      fileList: [], //上传文件列表
      uploadList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      fileUploadDialogVisible : false,
      fileModifyDialogVisible : false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        fileTitle: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        departmentCategory: null,
        fileTag: null,
        uploadDate: null,
        uploadUsername: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        fileTitle: [
          { required: true, message: "文件标题不能为空", trigger: "blur" }
        ],
        fileTag: [
          { required: true, message: "标签不能为空", trigger: "blur" }
        ]

      }
    };
  },
  created() {
    getUserProfile02().then(response => {
      // 处理成功的情况
      console.log('成功获取用户信息response.data====>', response.data.dept.deptName
      );
      this.thisDept =  response.data.dept.deptName;
      this.getList();
    }).catch(error => {
      // 处理失败的情况
      console.error('获取用户信息失败:', error);
    });

    //获取部门列表
    listDept02().then(response => {
      this.deptList = response.data;
    });
  },
  // 路由钩子，每次进入该路由时都会调用getList方法
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.getList(); // 在路由导航完成后调用getList
    });
  },
  methods: {
    /** 查询部门文件列表 */
    getList() {
      this.loading = true;
      // 如果部门是研发或企管，则不添加departmentCategory到queryParams
      if (!['研发'].includes(this.thisDept)) {
        this.queryParams.departmentCategory = this.thisDept;
      }
      listTable(this.queryParams).then(response => {
        this.tableList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 文件上传取消按钮
    uploadCancel() {
      this.fileUploadDialogVisible = false;
      this.reset();
      this.fileList = [];
    },
    // 文件修改取消按钮
    modifyCancel() {
      this.fileModifyDialogVisible = false;
      this.reset();
      this.fileList = [];
    },
    // 表单重置
    reset() {
      this.form = {
        fileId: null,
        fileTitle: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        departmentCategory: null,
        fileTag: null,
        uploadDate: null,
        uploadUsername: null
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
      this.ids = selection.map(item => item.fileId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 上传按钮操作 */
    handleAdd() {
      this.reset();
      this.fileUploadDialogVisible = true;
      this.title = "上传部门文件";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const fileId = row.fileId || this.ids
      getTable(fileId).then(response => {
        this.form = response.data;
        this.fileModifyDialogVisible = true;
        this.title = "修改部门文件";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.fileId != null) {
            updateTable(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.fileModifyDialogVisible = false;
              this.getList();
            });
          } else {
            // 首先检查 fileList 是否为空
            if (!this.form.filePath) {
              this.$message.error("请上传文件");
              return;
            }
            addTable(this.form).then(response => {
              this.$modal.msgSuccess("上传成功");
              this.fileUploadDialogVisible = false;
              this.getList();
              this.fileList = [];
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const fileIds = row.fileId || this.ids;
      this.$modal.confirm('是否确认删除部门文件编号为"' + fileIds + '"的数据项？').then(function() {
        return delTable(fileIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('departmentFile/table/export', {
        ...this.queryParams
      }, `table_${new Date().getTime()}.xlsx`)
    },
    /** 文件下载 */
    downloadFile(url) {
      fetch(url)
        .then(response => response.blob())
        .then(blob => {
          const downloadUrl = window.URL.createObjectURL(blob);
          const link = document.createElement('a');
          link.href = downloadUrl;
          link.setAttribute('download', decodeURIComponent(url.split('/').pop())); // 解码文件名
          document.body.appendChild(link);
          link.click();
          document.body.removeChild(link);
          window.URL.revokeObjectURL(downloadUrl);
        })
        .catch(error => console.error('Download error:', error));
    },
    /** 上传文件时的动作*/
    handleFileChange(file, fileList) {
      this.fileList = fileList;
      console.log('上传文件时的form1',this.form);
      console.log('上传文件时的fileList=====>',this.fileList);
      console.log("上传文件时的file====>", file);
      const uploadedFile = file.raw; // 获取上传的文件对象
      // 将文件名填充到对应的输入框
      this.form.fileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.'));
      //将文件路径填充到对应的输入框
      this.form.filePath = this.path;
      // 将文件类型填充到对应的输入框
      this.form.fileType = this.getFileType(this.form.filePath);
      // 将文件大小填充到对应的输入框
      // this.form.fileSize = this.formatFileSize(uploadedFile.size);
      this.form.fileSize = uploadedFile.size;
      // 获取当前时间作为上传日期，并填充到对应的输入框
      const currentDate = new Date();
      this.form.uploadDate = currentDate.toISOString().split('T')[0];
      this.getUserInfo();
      console.log('上传文件时的form2',this.form);
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      const uploadedFile = file.raw; // 获取上传的文件对象
      const uploadedFileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.')); // 获取上传文件的文件名
      // 发起请求检查文件名是否存在于数据库中
      const isFormNameDuplicate = this.tableList.some(item => item.fileName === uploadedFileName);
      console.log("tableList=====>",this.tableList);
      console.log("发起请求检查文件名是否存在于数据库中",isFormNameDuplicate);

      if (isFormNameDuplicate) {
        // 如果文件名重复，弹出警告框
        this.$modal.msgWarning('同名文件已存在');
        console.log('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
        return false; // 中断上传流程
      }
      if (res.code === 200) {
        console.log("上传成功回调");
        console.log(res);
        this.path = res.url;
        this.uploadList.push({name: res.formName, url: res.formName});
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        this.handleRemove(file);
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
    handleRemove(file, formList) {
      console.log(file, formList);
    },
    handlePreview(file) {
      console.log(file);
    },
    handleExceed(files, formList) {
      this.$message.warning(`当前限制选择 1 个文件`);
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
    //文件预览
    previewFile(filePath) {
      // 获取文件类型
      const fileType = this.getFileType(filePath);

      // 检查文件类型是否为 'pdf' 或 'word'
      if (fileType === 'pdf' || fileType === 'word') {
        switch (fileType) {
          case 'pdf':
            console.log("fileType1111:",fileType);
            window.open(filePath, '_blank');
            break;
          case 'word':
            const pdfFilePath = this.convertToPdfPath(filePath);
            console.log("filePath:",filePath);
            console.log("pdfFilePath:",pdfFilePath);
            this.loading = true;
            word2Pdf(filePath, pdfFilePath).then(response => {
              this.loading = false;
              window.open(pdfFilePath, '_blank');

            });
            break;
        }
      } else {
        // 如果文件类型既不是 'pdf' 也不是 'word'，给出提示信息
        this.$message({
          message: '无法预览此文件类型',
          type: 'warning'
        });
      }
    },
    convertToPdfPath(wordFilePath) {
      // 找到文件路径中的最后一个点的位置
      const lastDotIndex = wordFilePath.lastIndexOf('.');

      if (lastDotIndex != -1) {
        // 获取文件路径中最后一个点之前的部分（文件名部分）
        const prefix = wordFilePath.substring(0, lastDotIndex);

        // 将文件名部分与 .pdf 后缀拼接，形成 PDF 文件路径
        const pdfFilePath = prefix + ".pdf";

        return pdfFilePath;
      } else {
        // 文件路径中没有点，无法更改后缀
        throw new IllegalArgumentException("文件路径无效：" + wordFilePath);
      }
    },

    getFileType(filePath) {
      const FILE_TYPE_MAP = {
        'pdf': 'pdf',
        'doc': 'word',
        'docx': 'word',
        'xls': 'xls',
        'xlsx': 'xlsx',
        'ppt': 'ppt',
        'pptx': 'pptx',
        'txt': 'txt',
        'jpg': 'jpg',
        'jpeg': 'jpeg',
        'png': 'png',
        'gif': 'gif',
        'bmp': 'bmp',
      }
      // 获取文件名的后缀名
      const formExtension = filePath.split('.').pop();
      // 转换为小写并查找映射
      const type = FILE_TYPE_MAP[formExtension.toLowerCase()];

      // 返回文件类型，如果找不到则返回 'unknown'
      return type || '未知类型';
    },
    // 调用接口获取用户信息
    getUserInfo() {
      getUserProfile02().then(response => {
        // 处理成功的情况
        console.log('成功获取用户信息:', response.data)
        const userInfo = response.data // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        // 填充到对应的输入框中
        this.form.uploadUsername = userInfo.userName
        //根据部门id获取部门名称
        getDept02(userInfo.deptId).then(response => {
          const deptInfo = response.data
          this.form.departmentCategory = deptInfo.deptName
        })
      }).catch(error => {
        // 处理失败的情况
        console.error('获取用户信息失败:', error)
      })
    },

  }
};
</script>

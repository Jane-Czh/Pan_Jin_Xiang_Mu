<template>
  <div class="app-container">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="制度检索" name="1">
        <div>
          <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="制度标题" prop="regulationsTitle">
              <el-input
                v-model="queryParams.regulationsTitle"
                placeholder="请输入制度标题"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="适用范围" prop="useScope">
              <el-input
                v-model="queryParams.useScope"
                placeholder="请输入适用范围"
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
            <el-form-item label="生效日期" prop="effectiveDate">
              <el-date-picker clearable
                              v-model="queryParams.effectiveDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择生效日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="制度内容" prop="regulationsAddress">
              <el-input
                v-model="queryParams.regulationsAddress"
                placeholder="请输入制度内容"
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
            <el-form-item label="文件路径" prop="filePath">
              <el-input
                v-model="queryParams.filePath"
                placeholder="请输入文件路径"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="文件类型" prop="fileType">
              <el-input
                v-model="queryParams.fileType"
                placeholder="请输入文件类型"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="文件大小" prop="fileSize">
              <el-input
                v-model="queryParams.fileSize"
                placeholder="请输入文件大小"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度创建日期" prop="createDate">
              <el-date-picker clearable
                              v-model="queryParams.createDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择制度创建日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="制度创建人" prop="createUsername">
              <el-input
                v-model="queryParams.createUsername"
                placeholder="请输入制度创建人"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度使用状态" prop="useState">
              <el-input
                v-model="queryParams.useState"
                placeholder="请输入制度使用状态"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度所属科室" prop="departmentCategory">
              <el-input
                v-model="queryParams.departmentCategory"
                placeholder="请输入制度所属科室"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度标签名称" prop="fileTag">
              <el-input
                v-model="queryParams.fileTag"
                placeholder="请输入制度标签名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="历史版本制度" prop="oldRegulationsId">
              <el-input
                v-model="queryParams.oldRegulationsId"
                placeholder="请输入历史版本制度"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="修订时间" prop="revisionDate">
              <el-date-picker clearable
                              v-model="queryParams.revisionDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择修订时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="修订人" prop="reviser">
              <el-input
                v-model="queryParams.reviser"
                placeholder="请输入修订人"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-collapse-item>
    </el-collapse>


    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['file:filemanagement:add']"
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
          v-hasPermi="['file:filemanagement:edit']"
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
          v-hasPermi="['file:filemanagement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['file:filemanagement:export']"
        >导出</el-button>
      </el-col >


      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filemanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id(主键)" align="center" prop="regulationsId" />
      <el-table-column label="制度标题" align="center" prop="regulationsTitle" />
      <el-table-column label="适用范围" align="center" prop="useScope" />
      <el-table-column label="上传日期" align="center" prop="uploadDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="生效日期" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="制度内容" align="center" prop="regulationsAddress" />
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <el-table-column label="文件路径" align="center" prop="filePath" />
      <el-table-column label="文件类型" align="center" prop="fileType" />
      <el-table-column label="文件大小" align="center" prop="fileSize" />
      <el-table-column label="制度创建日期" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="制度创建人" align="center" prop="createUsername" />
      <el-table-column label="制度使用状态" align="center" prop="useState" />
      <el-table-column label="制度所属科室" align="center" prop="departmentCategory" />
      <el-table-column label="制度标签名称" align="center" prop="fileTag" />
      <el-table-column label="历史版本制度" align="center" prop="oldRegulationsId" />
      <el-table-column label="修订时间" align="center" prop="revisionDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.revisionDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="修订内容" align="center" prop="revisionContent" />
      <el-table-column label="修订人" align="center" prop="reviser" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['file:filemanagement:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:filemanagement:remove']"
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

    <!-- 添加或修改文件管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="1000px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px" >
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <el-upload
                class="upload-demo"
                action="https://jsonplaceholder.typicode.com/posts/"
                :on-change="handleFileChange"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :before-remove="beforeRemove"
                multiple
                :limit="3"
                :auto-upload="false"
                :on-exceed="handleExceed"
                :file-list="fileList"
              >
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="制度标题" prop="regulationsTitle">
              <el-input v-model="form.regulationsTitle" placeholder="请输入制度标题" />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="适用范围" prop="useScope">
              <el-input v-model="form.useScope" placeholder="请输入适用范围" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="上传日期" prop="uploadDate" >
              <el-date-picker clearable
                              v-model="form.uploadDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择上传日期">
                              :disabled="true"
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="生效日期" prop="effectiveDate">
              <el-date-picker clearable
                              v-model="form.effectiveDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择生效日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span='12'>
            <el-form-item label="文件名称" prop="fileName" >
              <el-input v-model="form.fileName" placeholder="请输入文件名称" :disabled="true"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="文件路径" prop="filePath" >
              <el-input v-model="form.filePath" placeholder="文件名显示区域" :disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span='12'>
            <el-form-item label="文件类型" prop="fileType" >
              <el-input v-model="form.fileType" placeholder="请输入文件类型" :disabled="true"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="文件大小" prop="fileSize" >
              <el-input v-model="form.fileSize" placeholder="请输入文件大小" :disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度创建日期" prop="createDate">
              <el-date-picker clearable
                              v-model="form.createDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择制度创建日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度创建人" prop="createUsername">
              <el-input v-model="form.createUsername" placeholder="请输入制度创建人" />
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度使用状态" prop="useState">
              <el-input v-model="form.useState" placeholder="请输入制度使用状态" />
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度所属科室" prop="departmentCategory">
              <el-input v-model="form.departmentCategory" placeholder="请输入制度所属科室" />
            </el-form-item>
          </el-col>
        </el-row>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import { listFilemanagement, getFilemanagement, delFilemanagement, addFilemanagement, updateFilemanagement } from "@/api/file/filemanagement";

  export default {
    name: "Filemanagement",
    data() {
      return {
        input:'',
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
        // 文件管理表格数据
        filemanagementList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          regulationsTitle: null,
          useScope: null,
          uploadDate: null,
          effectiveDate: null,
          regulationsAddress: null,
          fileName: null,
          filePath: null,
          fileType: null,
          fileSize: null,
          createDate: null,
          createUsername: null,
          useState: null,
          departmentCategory: null,
          fileTag: null,
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null
        },
         //

        // 表单参数
        form: {
          regulationsTitle: null,
          useScope: null,
          effectiveDate: null,
          regulationsAddress: null,
          fileName: null,
          filePath:null,
          fileType: null,
          fileSize: null,
          createDate: null,
          createUsername: null,
          useState: null,
          departmentCategory: null,
          fileTag: null,
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null
        },
        // 表单校验
        rules: {
          regulationsTitle: [
            { required: true, message: "制度标题不能为空", trigger: "blur" }
          ],
          useScope: [
            { required: true, message: "适用范围不能为空", trigger: "blur" }
          ],
          uploadDate: [
            { required: true, message: "上传日期不能为空", trigger: "blur" }
          ],
          effectiveDate: [
            { required: true, message: "生效日期不能为空", trigger: "blur" }
          ],
          regulationsAddress: [
            { required: true, message: "制度内容不能为空", trigger: "blur" }
          ],
          fileName: [
            { required: true, message: "文件名称不能为空", trigger: "blur" }
          ],
          filePath: [
            { required: true, message: "文件路径不能为空", trigger: "blur" }
          ],
          fileType: [
            { required: true, message: "文件类型不能为空", trigger: "blur" }
          ],
          fileSize: [
            { required: true, message: "文件大小不能为空", trigger: "blur" }
          ],
          createDate: [
            { required: true, message: "制度创建日期不能为空", trigger: "blur" }
          ],
          createUsername: [
            { required: true, message: "制度创建人不能为空", trigger: "blur" }
          ],
          useState: [
            { required: true, message: "制度使用状态不能为空", trigger: "blur" }
          ],
          departmentCategory: [
            { required: true, message: "制度所属科室不能为空", trigger: "blur" }
          ],
        },
        uploadVisible: false,
        ruleForm: {
          remark: ''
        },
        fileList: [],
        fd: {}
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询文件管理列表 */
      getList() {
        this.loading = true;
        listFilemanagement(this.queryParams).then(response => {
          this.filemanagementList = response.rows;
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
          regulationsId: null,
          regulationsTitle: null,
          useScope: null,
          uploadDate:null,
          effectiveDate: null,
          regulationsAddress: null,
          fileName:null,
          filePath: null,
          fileType: null,
          fileSize: null,
          createDate: null,
          createUsername: null,
          useState: null,
          departmentCategory: null,
          fileTag: null,
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null
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
        this.ids = selection.map(item => item.regulationsId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 上传制度文件 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "上传制度文件";
      },
      /** 修改制度文件 */
      handleUpdate(row) {
        this.reset();
        const regulationsId = row.regulationsId || this.ids
        getFilemanagement(regulationsId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改制度文件";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.regulationsId != null) {
              updateFilemanagement(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addFilemanagement(this.form).then(response => {
                this.$modal.msgSuccess("上传成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const regulationsIds = row.regulationsId || this.ids;
        this.$modal.confirm('是否确认删除文件管理编号为"' + regulationsIds + '"的数据项？').then(function() {
          return delFilemanagement(regulationsIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('file/filemanagement/export', {
          ...this.queryParams
        }, `filemanagement_${new Date().getTime()}.xlsx`)
      },
      // handleFileChange(file, fileList) {
      //   this.input = file.name;
      // },
      handleFileChange(file, fileList) {
        const uploadedFile = file.raw; // 获取上传的文件对象

        // 将文件名填充到对应的输入框
        this.form.fileName = uploadedFile.name;
        // //将文件路径填充到对应的输入框
        // this.form.filePath = uploadedFile.webkitRelativePath || uploadedFile.relativePath || "文件路径不可用";
        // 将文件类型填充到对应的输入框
        this.form.fileType = this.getFileType(uploadedFile.type);

        // 将文件大小填充到对应的输入框
        this.form.fileSize = this.formatFileSize(uploadedFile.size);

        // 获取当前时间作为上传日期，并填充到对应的输入框
        const currentDate = new Date();
        this.form.uploadDate = currentDate.toISOString().split('T')[0];
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
        return this.$confirm(`确定移除 ${ file.name }？`);
      },
      // handleChange(val) {
      //   console.log(val);
      // }

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
      }
    }
  };
</script>

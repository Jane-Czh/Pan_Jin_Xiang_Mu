<template>
  <div class="app-container">
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
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">提交</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>
    <el-upload
      class="upload-demo"
      action="https://jsonplaceholder.typicode.com/posts/"
      :on-preview="handlePreview"
      :on-remove="handleRemove"
      :before-remove="beforeRemove"
      multiple
      :limit="3"
      :on-exceed="handleExceed"
      :file-list="fileList">
      <el-button size="small" type="primary">点击上传</el-button>
      <div slot="tip" class="el-upload__tip">只能上传word/pdf文件</div>
    </el-upload>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

  </div>

</template>

<script>
  import { listFilemanagement, getFilemanagement, delFilemanagement, addFilemanagement, updateFilemanagement } from "@/api/file/filemanagement";

  export default {
    name: "Filemanagement",
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
          effectiveDate: null,
          regulationsAddress: null,
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
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          regulationsTitle: [
            { required: true, message: "制度标题不能为空", trigger: "blur" }
          ],
          useScope: [
            { required: true, message: "适用范围不能为空", trigger: "blur" }
          ],
          effectiveDate: [
            { required: true, message: "生效日期不能为空", trigger: "blur" }
          ],
          regulationsAddress: [
            { required: true, message: "制度内容不能为空", trigger: "blur" }
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
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {

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
          effectiveDate: null,
          regulationsAddress: null,
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
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      handleRemove(file, fileList) {
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 3 个文件，本次选择了 ${files.length} 个文件，共选择了 ${files.length + fileList.length} 个文件`);
      },
      beforeRemove(file, fileList) {
        return this.$confirm(`确定移除 ${ file.name }？`);
      }
    }
  };
</script>
<style>
  .upload-demo {
    /* 设置上传按钮位置 */
    text-align: center;
  }

  /* 设置上传按钮大小 */
  .el-upload .el-button {
    font-size: 16px;
    height: 50px;
    width: 150px;
  }
</style>

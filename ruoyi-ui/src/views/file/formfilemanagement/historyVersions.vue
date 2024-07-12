<template>
  <div class="app-container">
    <el-collapse v-model="activeNames" @change="handleChange">
      <el-collapse-item title="制度检索" name="1">
        <div>
          <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
            <el-form-item label="表单标题" prop="formTitle">
              <el-input
                v-model="queryParams.formTitle"
                placeholder="请输入表单标题"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="存储表单内容" prop="scope">
              <el-input
                v-model="queryParams.scope"
                placeholder="请输入存储表单内容"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="表单上传时间" prop="effectiveDate">
              <el-date-picker clearable
                              v-model="queryParams.effectiveDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择表单上传时间">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="表单名称" prop="formName">
              <el-input
                v-model="queryParams.formName"
                placeholder="请输入表单名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <!--            <el-form-item label="表单大小" prop="formSize">-->
            <!--              <el-input-->
            <!--                v-model="queryParams.formSize"-->
            <!--                placeholder="请输入表单大小"-->
            <!--                clearable-->
            <!--                @keyup.enter.native="handleQuery"-->
            <!--              />-->
            <!--            </el-form-item>-->
            <el-form-item label="上传人" prop="createUsername">
              <el-input
                v-model="queryParams.createUsername"
                placeholder="请输入上传人"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="表单所属科室" prop="departmentCategory">
              <el-input
                v-model="queryParams.departmentCategory"
                placeholder="请输入表单所属科室"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <!--            <el-form-item label="历史表单" prop="oldFormId">-->
            <!--              <el-input-->
            <!--                v-model="queryParams.oldFormId"-->
            <!--                placeholder="请输入历史表单"-->
            <!--                clearable-->
            <!--                @keyup.enter.native="handleQuery"-->
            <!--              />-->
            <!--            </el-form-item>-->
            <!--            <el-form-item label="修订时间" prop="revisionTime">-->
            <!--              <el-date-picker clearable-->
            <!--                              v-model="queryParams.revisionTime"-->
            <!--                              type="date"-->
            <!--                              value-format="yyyy-MM-dd"-->
            <!--                              placeholder="请选择修订时间">-->
            <!--              </el-date-picker>-->
            <!--            </el-form-item>-->
            <!--            <el-form-item label="修订人" prop="reviser">-->
            <!--              <el-input-->
            <!--                v-model="queryParams.reviser"-->
            <!--                placeholder="请输入修订人"-->
            <!--                clearable-->
            <!--                @keyup.enter.native="handleQuery"-->
            <!--              />-->
            <!--            </el-form-item>-->
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
              <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-collapse-item>
    </el-collapse>


    <el-table v-loading="loading" :data="formmanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="formId" />-->
      <el-table-column label="表单标题" align="center" prop="formTitle" />
      <el-table-column label="存储表单内容" align="center" prop="scope" />
      <el-table-column label="表单上传时间" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表单名称" align="center" prop="formName" />
      <el-table-column label="表单类型" align="center" prop="formType" />
      <el-table-column label="表单下载" align="center" prop="formPath">
        <template slot-scope="scope">
          <a :href="baseUrl + scope.row.formPath" download style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="表单大小" align="center" prop="formSize" />
      <el-table-column label="上传人" align="center" prop="createUsername" />
      <el-table-column label="表单所属科室" align="center" prop="departmentCategory" />
      <el-table-column label="备注" align="center" prop="remark" />
<!--      <el-table-column label="历史表单" align="center" prop="oldFormId" />-->
<!--      <el-table-column label="新版本表单" align="center" prop="newFormId"/>-->
<!--      <el-table-column label="标志位" align="center" prop="newFlag"/>-->
<!--      <el-table-column label="修订时间" align="center" prop="revisionTime" width="180">-->
<!--        <template slot-scope="scope">-->
<!--          <span>{{ parseTime(scope.row.revisionTime, '{y}-{m}-{d}') }}</span>-->
<!--        </template>-->
<!--      </el-table-column>-->
<!--      <el-table-column label="修订内容" align="center" prop="revisionContent" />-->
<!--      <el-table-column label="修订人" align="center" prop="reviser" />-->
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleModify(scope.row)"
            v-hasPermi="['file:formfilemanagement:edit']"
            :disabled="thisDept !== scope.row.departmentCategory && thisDept !== '研发'"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:formfilemanagement:remove']"
            :disabled="thisDept !== scope.row.departmentCategory && thisDept !== '研发'"
          >删除
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="查看" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="previewFile(scope.row.formPath)"
          >预览
          </el-button>
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
    <!-- 修改文件对话框 -->
    <el-dialog :title="title" :visible.sync="formModifyDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="表单标题" prop="formTitle">
              <el-input v-model="form.formTitle" placeholder="请输入表单标题"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="表单存储内容" prop="scope">
              <el-input v-model="form.scope" placeholder="请输入表单存储内容"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="备注" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入备注信息"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="所属科室" prop="departmentCategory">
              <el-select v-model="form.departmentCategory" placeholder="请输入表单所属科室">
                <!-- 循环遍历this.deptList中的部门数据 -->
                <el-option v-for="dept in deptList" :key="dept.deptId" :label="dept.deptName" :value="dept.deptName"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="modifySubmitForm">确 定</el-button>
        <el-button @click="modifyCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
  import {
    listFormfilemanagement,
    getFormfilemanagement,
    delFormfilemanagement,
    addFormfilemanagement,
    updateFormfilemanagement,
    getFormHistory
  } from "@/api/file/formfilemanagement";
  import {getUserProfile02} from '@/api/file/filemanagement'
  import {getDept02} from '@/api/file/filemanagement'
  import {getToken} from "@/utils/auth"
  import {listDept02, word2Pdf} from "../../../api/file/filemanagement";

  export default {
    name: "HistoryVersions",
    data() {
      return {
        //部门列表
        deptList: [],
        //当前账号的dept
        thisDept: null,
        //文件上传绑定的部门
        fileDept: null,
        number: 0,
        uploadList: [],
        formList: [],
        fd: {},
        path: '',
        baseUrl: process.env.VUE_APP_BASE_API,
        uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
        // uploadFileUrl:"/profile/upload"+"/"+this.file.name,
        headers: {
          Authorization: "Bearer " + getToken(),
        },
        userProfile: {}, // 存储用户个人信息的对象
        input: '',
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
        formmanagementList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        formUpdateDialogVisible: false,
        formModifyDialogVisible: false,
        formUploadDialogVisible: false,
        historyVersionsDialogVisible: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          formTitle: null,
          scope: null,
          effectiveDate: null,
          formName: null,
          formType: null,
          formPath: null,
          formSize: null,
          createUsername: null,
          departmentCategory: null,
          oldFormId: null,
          revisionTime: null,
          revisionContent: null,
          reviser: null,
          newFlag: null,
          newFormId: null
        },

        // 表单参数
        form: {},
        // 表单校验
        rules: {
          formTitle: [
            { required: true, message: "表单名称不能为空", trigger: "blur" }
          ],
          effectiveDate: [
            { required: true, message: "表单上传时间不能为空", trigger: "blur" }
          ],
          formType: [
            { required: true, message: "表单类型word/pdf不能为空", trigger: "change" }
          ],
          formSize: [
            { required: true, message: "表单大小不能为空", trigger: "blur" }
          ],
          createUsername: [
            { required: true, message: "上传人不能为空", trigger: "blur" }
          ],
          departmentCategory: [
            { required: true, message: "表单所属科室不能为空", trigger: "blur" }
          ],
        },
        uploadVisible: false,

        ruleForm: {
          remark: ''
        },


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
        return this.isShowTip && (this.formType || this.formSize);
      },
    },
    created() {
      this.getList();
      getUserProfile02().then(response => {
        // 处理成功的情况
        console.log('成功获取用户信息response.data====>', response.data.dept.deptName
        );
        // const userInfo =; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        this.thisDept =  response.data.dept.deptName;
        //根据部门id获取部门名称
        // getDept02(userInfo.deptId).then(response => {
        //   const deptInfo = response.data;
        //   console.log("deptInfo======>",deptInfo);
        //   this.thisDept = deptInfo.deptName;
        //   console.log("thisDept======>",this.thisDept);
        // })
      }).catch(error => {
        // 处理失败的情况
        console.error('获取用户信息失败:', error);
      });
      //获取部门列表
      listDept02().then(response => {
        this.deptList = response.data;
      });
    },
    methods: {
      /** 查询文件管理列表 */
      getList() {
        this.loading = true;
        const newFormId = this.$route.params.formId;
        console.log("newFormId=>：",newFormId);

        getFormHistory(newFormId).then(response =>{
          console.log("response:：",response);
          this.formmanagementList = response;
          console.log("response.rows:：",response.rows);
          console.log("formmanagementList:：",this.formmanagementList);
          this.loading = false;
        });
      },
      // 文件修改取消按钮
      modifyCancel() {
        this.formModifyDialogVisible = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          formId: null,
          formTitle: null,
          scope: null,
          effectiveDate: null,
          formName: null,
          formType: null,
          formPath: null,
          formSize: null,
          createUsername: null,
          departmentCategory: null,
          remark: null,
          oldFormId: null,
          revisionTime: null,
          revisionContent: null,
          reviser: null,
          newFlag: null,
          newFormId: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.loading = true;
        const newFormId = this.$route.params.formId;
        getFormHistory(newFormId).then(response =>{
          this.formmanagementList = response;
          this.formmanagementList = this.formmanagementList.filter(file => {
            // 处理每个筛选条件
            const matchesFormTitle = !this.queryParams.formTitle || file.formTitle.includes(this.queryParams.formTitle);
            const matchesScope = !this.queryParams.scope || file.scope.includes(this.queryParams.scope);
            const matchesEffectiveDate = !this.queryParams.effectiveDate || file.effectiveDate === this.queryParams.effectiveDate;
            const matchesFormName = !this.queryParams.formName || file.formName.includes(this.queryParams.formName);
            const matchesFormType = !this.queryParams.formType || file.formType === this.queryParams.formType;
            const matchesFormPath = !this.queryParams.formPath || file.formPath.includes(this.queryParams.formPath);
            const matchesFormSize = !this.queryParams.formSize || file.formSize === this.queryParams.formSize;
            const matchesCreateUsername = !this.queryParams.createUsername || file.createUsername.includes(this.queryParams.createUsername);
            const matchesDepartmentCategory = !this.queryParams.departmentCategory || file.departmentCategory.includes(this.queryParams.departmentCategory);
            const matchesOldFormId = !this.queryParams.oldFormId || file.oldFormId === this.queryParams.oldFormId;
            const matchesRevisionTime = !this.queryParams.revisionTime || file.revisionTime === this.queryParams.revisionTime;
            const matchesRevisionContent = !this.queryParams.revisionContent || file.revisionContent.includes(this.queryParams.revisionContent);
            const matchesReviser = !this.queryParams.reviser || file.reviser.includes(this.queryParams.reviser);
            const matchesNewFlag = this.queryParams.newFlag === null || file.newFlag === this.queryParams.newFlag;
            const matchesNewFormId = !this.queryParams.newFormId || file.newFormId === this.queryParams.newFormId;

            // 返回满足所有条件的文件
            return matchesFormTitle &&
              matchesScope &&
              matchesEffectiveDate &&
              matchesFormName &&
              matchesFormType &&
              matchesFormPath &&
              matchesFormSize &&
              matchesCreateUsername &&
              matchesDepartmentCategory &&
              matchesOldFormId &&
              matchesRevisionTime &&
              matchesRevisionContent &&
              matchesReviser &&
              matchesNewFlag &&
              matchesNewFormId;
          });
          this.loading = false;
        });
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.queryParams.pageNum = 1;
        this.getList();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.regulationsId)
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 修改表单文件 */
      handleModify(row) {
        this.reset();
        const formId = row.formId || this.ids
        getFormfilemanagement(formId).then(response => {
          this.form = response.data;
          this.formModifyDialogVisible = true;
          this.title = "修改表单文件";
        });
      },
      /** 修改文件提交按钮 */
      modifySubmitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.formId != null) {
              updateFormfilemanagement(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.formModifyDialogVisible = false;
                this.getList();
                console.log("修改文件提交按钮=>",this.form);
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const formIds = row.formId || this.ids;
        this.$modal.confirm('是否确认删除？').then(function () {
          return delFormfilemanagement(formIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
        if(row.oldFormId != null) {
          getFormfilemanagement(row.oldFormId).then(response => {
            console.log("当前表单2=>",row);
            const lastForm = response.data;
            console.log("response=>",response);
            console.log("上一表单=>",lastForm);
            lastForm.newFormId = row.newFormId;
            console.log("上一表单=>",lastForm);
            updateFormfilemanagement(lastForm).then(response => {
            });
          });
          getFormfilemanagement(row.newFormId).then(response => {
            console.log("当前表单3=>",this.form);
            const newForm = response.data;
            console.log("上一表单=>",newForm);
            newForm.oldFormId = row.oldFormId;
            console.log("上一表单=>",newForm);
            updateFormfilemanagement(newForm).then(response => {
            });
          });
          this.getList();
        }
        this.getList();
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
      getFileType(filePath) {
        // 获取文件名的后缀名
        const fileExtension = filePath.split('.').pop();
        console.log("fileExtension=>",fileExtension);

        // 根据文件后缀名判断文件类型
        switch (fileExtension.toLowerCase()) {
          case 'pdf':
            return 'pdf';
          case 'doc':
          case 'docx':
            return 'word';
          case 'xls':
          case 'xlsx':
            return 'Excel 文档';
          case 'ppt':
          case 'pptx':
            return 'PowerPoint 文档';
          // 可以根据需要添加更多的文件类型判断
          default:
            return '未知类型';
        }
      },
      // 调用接口获取用户信息
      getUserInfo() {
        getUserProfile02().then(response => {
          // 处理成功的情况
          console.log('成功获取用户信息:', response.data)
          const userInfo = response.data // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
          // 填充到对应的输入框中
          this.form.createUsername = userInfo.userName
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
      //文件预览
      previewFile(filePath) {
        const fileType = this.getFileType(filePath);
        console.log("filePath:",filePath);
        console.log("fileType:",fileType);
        switch (fileType) {
          case 'pdf':
            console.log("fileType1111:",fileType);
            window.open(filePath, '_blank');
            break;
          case 'word':
            const pdfFilePath = this.convertToPdfPath(filePath);
            console.log("filePath:",filePath);
            console.log("pdfFilePath:",pdfFilePath);
            word2Pdf(filePath,pdfFilePath).then(response => {
              window.open(pdfFilePath, '_blank');
            })

            break;
        }
        // 使用 window.open 方法打开一个新窗口，并将文件路径传递给该窗口

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


    }
  };
</script>

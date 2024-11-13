<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="主责部门" prop="departmentCategory">
        <el-select
          v-model="queryParams.departmentCategory"
          placeholder="请选择主责部门"
          clearable
          @change="handleDepartmentChange"
        >
          <el-option
            v-for="item in departments"
            :key="item"
            :label="item"
            :value="item"
          />
        </el-select>
      </el-form-item>
      <!-- 2. 可选, 从已有的业务模块中进行选择 ; 当上级部门被选了, 就只能从对应的业务模块中进行选择 -->
      <el-form-item label="业务模块" prop="businesses">
        <el-select
          v-model="queryParams.businesses"
          placeholder="请选择业务模块"
          clearable
          :disabled="!queryParams.departmentCategory"
          @change="handleModuleChange"
        >
          <el-option
            v-for="item in modules"
            :key="item.bmId"
            :label="item.moduleName"
            :value="item.moduleName"
          />
        </el-select>
      </el-form-item>


      <!-- 3. 可选, 从已有的细分业务中进行选择 ; 当上级业务模块被选了, 就只能从对应的细分业务中进行选择 -->
      <el-form-item label="细分业务" prop="subBusinesses">
        <el-select
          v-model="queryParams.subBusinesses"
          placeholder="请选择细分业务"
          clearable
          :disabled="!queryParams.businesses"
        >
          <el-option
            v-for="item in subBusinesses"
            :key="item.subbId"
            :label="item.subBusinessesName"
            :value="item.subBusinessesName"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="表单名称" prop="formTitle">
        <el-input
          v-model="queryParams.formTitle"
          placeholder="请输入表单标名称"
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
      <!--            <el-form-item label="表单大小" prop="formSize">-->
      <!--              <el-input-->
      <!--                v-model="queryParams.formSize"-->
      <!--                placeholder="请输入表单大小"-->
      <!--                clearable-->
      <!--                @keyup.enter.native="handleQuery"-->
      <!--              />-->
      <!--            </el-form-item>-->
      <el-form-item label="表单类型" prop="formType">
        <el-input
          v-model="queryParams.formType"
          placeholder="请输入表单类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="关键字" prop="remark">
        <el-input
          v-model="queryParams.remark"
          placeholder="请输入关键字"
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

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleUpload"
          v-hasPermi="['file:formfilemanagement:add']"
        >上传</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['file:formfilemanagement:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportAll"
          v-hasPermi="['file:formfilemanagement:list']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="formmanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="id" align="center" prop="formId" />-->
      <el-table-column label="主责部门" align="center" prop="departmentCategory" />
      <el-table-column label="业务模块" align="center" prop="businesses" />
      <el-table-column label="细分业务" align="center" prop="subBusinesses" />
      <el-table-column label="表单名称" align="center" prop="formTitle" />
      <el-table-column label="存储表单内容" align="center" prop="scope" />
      <el-table-column label="表单类型" align="center" prop="formType" />
      <el-table-column label="关联流程" align="center">
        <template slot-scope="scope">
          <el-tooltip
            class="item"
            effect="dark"
            content="点击查看详情！"
            placement="top"
          >
            <!-- popover：1、制度文件显示 -->
            <el-popover
              placement="bottom"
              title="绑定的流程"
              trigger="click"
            >
              <template slot="reference">
                <span class="file" @click="handleProjectDetails(scope.row)">
                  <i class="el-icon-files"></i>
                </span>
              </template>
              <!-- slot插槽展示自定义内容 -->
              <div v-if="projectNames.length != 0">
                <ul>
                  <li v-for="(file, index) in projectNames" :key="index">
                    {{ file }}
                  </li>
                </ul>
              </div>
              <div v-else>"无绑定"</div>
              <!-- slot插槽over -->
            </el-popover>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="关联制度" align="center" prop="regulationId" />
      <el-table-column label="关键字" align="center" prop="remark" />
      <el-table-column label="表单上传时间" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="表单下载" align="center" prop="formPath">
        <template v-slot:default="scope">
          <a v-if="scope.row.formPath" @click.prevent="downloadFile(scope.row.formPath)" style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleModify(scope.row)"
            v-hasPermi="['file:formfilemanagement:edit']"
            :disabled="thisDept !== scope.row.departmentCategory && (thisDept !== '研发'||'企管'||'总部')"
          >更新
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:formfilemanagement:remove']"
            :disabled="thisDept !== scope.row.departmentCategory && (thisDept !== '研发'||'企管'||'总部')"
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
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-operation"
            @click="handleHistoryVersions(scope.row)"
          >历史版本
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

    <!-- 上传文件对话框 -->
    <el-dialog :title="title" :visible.sync="formUploadDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--表单上传-->
              <el-upload
                v-model="form.formPath"
                class="upload-form-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :on-change="handleFileChange"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :on-exceed="handleExceed"
                :on-success="handleUploadSuccess"
                :limit=limit
                :file-list="formList"
              >
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="表单标题" prop="formTitle">
              <el-input v-model="form.formTitle" placeholder="请输入表单标题"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="表单存储内容" prop="scope">
              <el-input v-model="form.scope" placeholder="请输入表单存储内容"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="主责部门" prop="departmentCategory">
              <el-select
                v-model="form.departmentCategory"
                placeholder="请选择主责部门"
                clearable
                @change="handleDepartmentChange"
              >
                <el-option
                  v-for="item in departments"
                  :key="item"
                  :label="item"
                  :value="item"
                />

              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- 2. 可选, 从已有的业务模块中进行选择 ; 当上级部门被选了, 就只能从对应的业务模块中进行选择 -->
            <el-form-item label="业务模块" prop="businesses">
              <el-select
                v-model="form.businesses"
                placeholder="请选择业务模块"
                clearable
                :disabled="!form.departmentCategory"
                @change="handleModuleChange"
              >
                <el-option
                  v-for="item in modules"
                  :key="item.bm_id"
                  :label="item.moduleName"
                  :value="item.moduleName"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="12">
            <!-- 3. 可选, 从已有的细分业务中进行选择 ; 当上级业务模块被选了, 就只能从对应的细分业务中进行选择 -->
            <el-form-item label="细分业务" prop="subBusinesses">
              <el-select
                v-model="form.subBusinesses"
                placeholder="请选择细分业务"
                clearable
                :disabled="!form.businesses"
              >
                <el-option
                  v-for="item in subBusinesses"
                  :key="item.subb_id"
                  :label="item.subBusinessesName"
                  :value="item.subBusinessesName"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="关键字" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入关键字"/>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="uploadSubmitForm">确 定</el-button>
        <el-button @click="uploadCancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 更新文件对话框 -->
    <el-dialog :title="title" :visible.sync="formUpdateDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-upload
          v-model="form.formPath"
          class="upload-form-uploader"
          :action="uploadFileUrl"
          :headers="headers"
          :on-change="handleFileChange"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :on-exceed="handleExceed"
          :on-success="handleUploadSuccess"
          :limit=limit
          :file-list="formList"
        >
          <el-button size="small" type="primary">点击上传</el-button>
        </el-upload>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateSubmitForm">确 定</el-button>
        <el-button @click="updateCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改文件对话框 -->
    <el-dialog :title="title" :visible.sync="formModifyDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--文件上传-->
              <el-upload
                class="upload-file-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :on-change="handleFileChange"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :on-exceed="handleExceed"
                :on-success="handleUploadSuccess"
                multiple
                :limit=limit
                :file-list="formList"
              >
                <el-button size="small" type="primary">点击上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="表单标题" prop="formTitle">
              <el-input v-model="form.formTitle" placeholder="请输入表单标题"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="表单存储内容" prop="scope">
              <el-input v-model="form.scope" placeholder="请输入表单存储内容"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="主责部门" prop="departmentCategory"  required="true">
              <el-select
                v-model="form.departmentCategory"
                placeholder="请选择主责部门"
                clearable
                @change="handleDepartmentChange"
              >
                <el-option
                  v-for="item in departments"
                  :key="item"
                  :label="item"
                  :value="item"
                />

              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <!-- 2. 可选, 从已有的业务模块中进行选择 ; 当上级部门被选了, 就只能从对应的业务模块中进行选择 -->
            <el-form-item label="业务模块">
              <el-select
                v-model="form.businesses"
                placeholder="请选择业务模块"
                clearable
                :disabled="!form.departmentCategory"
                @change="handleModuleChange"
              >
                <el-option
                  v-for="item in modules"
                  :key="item.bm_id"
                  :label="item.moduleName"
                  :value="item.moduleName"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col  :span="12">
            <!-- 3. 可选, 从已有的细分业务中进行选择 ; 当上级业务模块被选了, 就只能从对应的细分业务中进行选择 -->
            <el-form-item label="细分业务">
              <el-select
                v-model="form.subBusinesses"
                placeholder="请选择细分业务"
                clearable
                :disabled="!form.businesses"
              >
                <el-option
                  v-for="item in subBusinesses"
                  :key="item.subb_id"
                  :label="item.subBusinessesName"
                  :value="item.subBusinessesName"
                />
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="关键字" prop="remark">
              <el-input v-model="form.remark" placeholder="请输入关键字"/>
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
  updateFormfilemanagement
} from "@/api/file/formfilemanagement";
import {getFilemanagement, getUserProfile02} from '@/api/file/filemanagement'
import {listDept02} from "../../../api/file/filemanagement";
import {getDept02} from '@/api/file/filemanagement'
import {getToken} from "@/utils/auth"
import {word2Pdf} from "../../../api/file/filemanagement";
import {listProject} from "@/api/system/project";
//业务模块api，
import { listModules } from "@/api/function/modules";
//细分业务api
import {listBusinesses, listBusinessess} from "@/api/function/businesses";
//导出总台账excel功能
import * as XLSX from "xlsx";
import { saveAs } from "file-saver";
import {Loading} from "element-ui";

export default {
  name: "Formfilemanagement",
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
      departments: [],
      // 查询参数
      subBusinessesList: [], //获取的全部的业务列表
      subBusinesses: [], //过滤后的细分业务列表

      // 业务模块 查询参数
      moduleQueryParams: {
        pageNum: 1,
        pageSize: 5000,
        moduleName: null,
        parentDepartment: null,
        isDeleted: null,
        description: null,
      },

      // 细分业务 查询参数
      xifenQueryParams: {
        pageNum: 1,
        pageSize: 5000,
        subBusinessesName: null,
        parentDepartment: null,
        parentModule: null,
        isDeleted: null,
        description: null,
      },
      modules: [], //过滤后 业务模块 数据
      modulesList: [],//全部的 业务模块 数据
      //表单展示数据
      formData: {
        //主责部门
        department: "",
        //业务模块
        businessesModules: "",
        //细分业务
        subBusinesses: "",
      },
      projectNames:[], //关联流程名称列表
      projectNamesString : "",  //关联流程名称列表（用”，“拼接）
      //部门列表
      deptList: [],
      //当前账号的dept
      thisDept: null,
      //文件上传绑定的部门
      fileDept: null,
      activeNames: [], // 默认展开的折叠项的名字
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
        remark: null,
        oldFormId: null,
        revisionTime: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        newFormId: null,
        businesses: null,
        subBusinesses: null,
        regulationId: null,
      },
      //流程查询参数
      projecQueryParams: {
        pageNum: 1,
        pageSize: 10,
        id: null,
        name: null,
        type: null
      },
      // 表单参数
      form: {
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
        newFormId: null,
        businesses: null,
        subBusinesses: null,
        regulationId: null,},
      // 表单校验
      rules: {
        formTitle: [
          { required: true, message: "表单名称不能为空", trigger: "blur" }
        ],
        scope: [
          { required: true, message: "表单存储内容不能为空", trigger: "blur" }
        ],
        departmentCategory: [
          {required: true, message: "表单主责部门不能为空", trigger: "blur"}
        ],
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
          this.formList = list.map(item => {
            if (typeof item === "string") {
              item = {name: item, url: item};
            }
            item.uid = item.uid || new Date().getTime() + temp++;
            return item;
          });
        } else {
          this.formList = [];
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
  this.getDeptList();
  },
  // 路由钩子，每次进入该路由时都会调用getList方法
  beforeRouteEnter(to, from, next) {
    next(vm => {
      vm.getList(); // 在路由导航完成后调用getList
    });
  },
  methods: {
    /** 查询文件管理列表 */
    getList() {
      this.loading = true;
      // 如果部门是研发或企管，则不添加departmentCategory到queryParams
      // if (!['研发', '企管'].includes(this.thisDept)) {
      //   this.queryParams.departmentCategory = this.thisDept;
      // }
      console.log("thisDept=>",this.thisDept);
      console.log("queryParams=>",this.queryParams);
      listFormfilemanagement(this.queryParams).then(response => {
        console.log("response:：",response);
        this.formmanagementList = response.rows;
        console.log("formmanagementList=>",this.formmanagementList);
        this.total = response.total;
        this.loading = false;
      });
    },
    // 查询绑定的流程信息
    handleProjectDetails(row) {
      return listProject(this.projecQueryParams).then(response => {
        console.log("response111:", response);
        const projectList = response;
        this.projectNames = [];

        projectList.forEach(process => {
          if (process.type && process.type.includes(row.formId)) {
            this.projectNames.push(process.name);
            console.log("projectNames=>", this.projectNames);
          }
        });

        // 将 projectNames 转换为用逗号分隔的字符串
        this.projectNamesString = this.projectNames.join(",");
      });
    },
    // 文件上传取消按钮
    uploadCancel() {
      this.formUploadDialogVisible = false;
      this.reset();
      this.formList = [];
    },
    // 文件修改取消按钮
    modifyCancel() {
      this.formModifyDialogVisible = false;
      this.reset();
      this.formList = [];
    },
    // 文件更新取消按钮
    updateCancel() {
      this.formUpdateDialogVisible = false;
      this.reset();
      this.formList = [];
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
        newFormId: null,
        businesses: null,
        subBusinesses: null,
        regulationId: null,
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
      this.ids = selection.map(item => item.formId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 上传表单文件 */
    handleUpload() {
      this.reset();
      this.formUploadDialogVisible = true;
      this.title = "上传表单文件";
    },
    /** 修改表单文件 */
    handleModify(row) {
      this.reset();
      const formId = row.formId || this.ids
      getFormfilemanagement(formId).then(response => {
        this.form = response.data;
        this.formModifyDialogVisible = true;
        this.title = "更新表单文件";
      });
    },
    /** 更新表单文件 */
    handleUpdate(row){
      this.reset();
      const formId = row.formId || this.ids;
      getFormfilemanagement(formId).then(response => {
        this.form = response.data;
        // this.form.newFlag = 0;
        console.log("更新表单文件=>",this.form);
        this.formUpdateDialogVisible = true;
        this.title = "更新表单文件";
      });
    },
    /** 历史版本管理 */
    handleHistoryVersions(row) {
      const formId = row.formId;
      this.$router.push("/file/formfilemanagement/historyVersions/" + formId);
    },
    /** 上传文件提交按钮 */
    uploadSubmitForm() {
      // 首先检查 fileList 是否为空
      if (!this.form.formPath) {
        this.$message.error("请上传文件");
        return;
      }
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.newFlag = 1;
          addFormfilemanagement(this.form).then(response => {
            this.$modal.msgSuccess("上传成功");
            this.formUploadDialogVisible = false;
            this.getList();
            console.log("上传文件提交按钮=>",this.form);
          });
        }
      });
      this.formList = [];
    },
    /** 修改表单文件提交按钮 */
    modifySubmitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.formId != null) {
            console.log("newform=>",this.form);
            this.form.oldFormId = this.form.formId;
            addFormfilemanagement(this.form).then(response => {
              const newId = response.data;
              this.$modal.msgSuccess("更新成功");
              this.formModifyDialogVisible = false;
              this.form.newformId = null;
              //更新历史版本制度
              getFormfilemanagement(this.form.oldFormId).then(response => {
                const lastForm = response.data;
                lastForm.newFlag = 0;
                lastForm.newFormId = newId;
                console.log("上一表单=>",lastForm);
                updateFormfilemanagement(lastForm).then(response => {
                  this.getList();
                });
              });
              console.log("更新文件提交按钮1=>",this.form);
              console.log("response=>",response);
            });
            // updateFormfilemanagement(this.form).then(response => {
            //   this.$modal.msgSuccess("修改成功");
            //   this.formModifyDialogVisible = false;
            //   this.getList();
            //   console.log("修改表单文件提交按钮=>",this.form);
            // });
          }
        }
      });
      this.formList = [];
    },
    /** 更新表单文件提交按钮 */
    updateSubmitForm() {
      // 检查 formList 是否为空
      if (this.formList.length === 0) {
        this.$message.error("请上传文件");
        return;
      }
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.formId != null) {
            console.log("newform=>",this.form);
            this.form.oldFormId = this.form.formId;
            addFormfilemanagement(this.form).then(response => {
              const newId = response.data;
              this.$modal.msgSuccess("更新成功");
              this.formUpdateDialogVisible = false;
              this.form.newformId = null;
              //更新历史版本制度
              getFormfilemanagement(this.form.oldFormId).then(response => {
                const lastForm = response.data;
                lastForm.newFlag = 0;
                lastForm.newFormId = newId;
                console.log("上一表单=>",lastForm);
                updateFormfilemanagement(lastForm).then(response => {
                  this.getList();
                });
              });
              console.log("更新文件提交按钮1=>",this.form);
              console.log("response=>",response);
            });
          }
        }
      });
      this.formList = [];
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      console.log("当前表单1=>",row);
      //将id或ids统一转换为数组
      const formIds = [].concat(row.formId || this.ids);
      console.log("formIds=>",formIds);
      // 遍历所有需要删除的ID
      const permissionCheckPromises = formIds.map(id => {
        return getFormfilemanagement(id).then(response => {
          const thisForm = response.data;
          console.log("response------>:", thisForm);

          // 检查权限，确保 this.thisDept 与表单的 departmentCategory 匹配
          if (this.thisDept !== thisForm.departmentCategory  && (this.thisDept !== '研发'||'企管'||'总部')) {
            this.$modal.msgError('没有权限删除该表单!');
            throw new Error('没有权限删除');
          }

          return thisForm;
        });
      });
      Promise.all(permissionCheckPromises).then(thisForms => {
        this.$modal.confirm('是否确认删除？').then(function () {
          formIds.forEach(id => {
            console.log("Processing ID:", id);
            getFormfilemanagement(id).then(response => {
              const thisForm = response.data;
              console.log("response------>:", response);
              if(thisForm.oldFormId != null) {
                getFormfilemanagement(thisForm.oldFormId).then(response => {
                  const lastForm = response.data;
                  lastForm.newFlag = 1;
                  console.log("上一表单=>",lastForm);
                  updateFormfilemanagement(lastForm).then(response => {
                  });
                });
              }
            });
          });
          return delFormfilemanagement(formIds);
        }).then(() => {
          this.$modal.msgSuccess("删除成功");
          this.getList();
          console.log("删除文件刷新");
        }).then(() => {
          this.getList();
          console.log("删除文件刷新2");
        }).catch(() => {
        });
      })
    },
    // /** 删除按钮操作 */
    // handleDelete(row) {
    //   console.log("当前表单1=>",row);
    //   const formIds = row.formId || this.ids;
    //   this.$modal.confirm('是否确认删除？').then(function () {
    //     return delFormfilemanagement(formIds);
    //   }).then(() => {
    //     this.getList();
    //     this.$modal.msgSuccess("删除成功");
    //   }).catch(() => {
    //   });
    //   if(row.oldFormId != null) {
    //     getFormfilemanagement(row.oldFormId).then(response => {
    //       console.log("当前表单3=>",this.form);
    //       const lastForm = response.data;
    //       console.log("上一表单=>",lastForm);
    //       lastForm.newFlag = 1;
    //       console.log("上一表单=>",lastForm);
    //       updateFormfilemanagement(lastForm).then(response => {
    //       });
    //     });
    //     this.getList();
    //   }
    // },
    /** 导出按钮操作 */
    handleExport() {
      this.download('file/formfilemanagement/export', {
        ...this.queryParams
      }, `formmanagement_${new Date().getTime()}.xlsx`)
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
    validateFile(rule, value, callback) {
      if (this.form.formList.length === 0) {
        return callback(new Error('文件路径不能为空'));
      }
      // 可以添加其他校验逻辑，例如文件类型等
      callback();
    },
    // 上传前校检格式和大小
    handleBeforeUpload(file) {
      console.log("handleBeforeUpload:file=====>",file);
      // 上传前校检文件格式
      const allowedTypes = ['application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/pdf'];
      const isAllowedType = allowedTypes.includes(file.type);
      if (!isAllowedType) {
        this.$message.error('文件类型不匹配，请重新上传word或pdf文件');
      }
      return isAllowedType;
    },
    /** 上传文件时的动作*/
    handleFileChange(file, formList) {
      this.formList = formList;
      console.log('上传文件时的form1',this.form);
      console.log('上传文件时的formList=====>',this.formList);
      console.log("上传文件时的file====>", file);
      const uploadedFile = file.raw; // 获取上传的文件对象
      // 将文件名填充到对应的输入框
      this.form.formName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.'));
      //将文件路径填充到对应的输入框
      this.form.formPath = this.path;
      // 将文件类型填充到对应的输入框
      this.form.formType = this.getFileType(this.form.formPath);
      // 将文件大小填充到对应的输入框
      // this.form.fileSize = this.formatFileSize(uploadedFile.size);
      this.form.formSize = uploadedFile.size;
      // 获取当前时间作为上传日期，并填充到对应的输入框
      const currentDate = new Date();
      this.form.effectiveDate = currentDate.toISOString().split('T')[0];
      console.log('上传文件时的form2',this.form);
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      const uploadedFile = file.raw; // 获取上传的文件对象
      this.path = res.url;
      const uploadedFileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.')); // 获取上传文件的文件名
      // 发起请求检查文件名是否存在于数据库中
      const isFormNameDuplicate = this.formmanagementList.some(item => item.formName === uploadedFileName);
      console.log("发起请求检查文件名是否存在于数据库中",isFormNameDuplicate);

      if (isFormNameDuplicate) {
        // 如果文件名重复，弹出警告框
        this.$modal.msgWarning('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
        console.log('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
        return false; // 中断上传流程
      }
      if (res.code === 200) {
        console.log("上传成功回调");
        console.log(res);
        this.uploadList.push({name: res.formName, url: res.formName});
        this.uploadedSuccessfully();
      } else {
        this.number--;
        this.$modal.closeLoading();
        this.$modal.msgError(res.msg);
        this.$refs.fileUpload.handleRemove(file);
        this.uploadedSuccessfully();
      }
    },
    // 更新成功回调
    handleUpdateSuccess(res, file) {
      if (res.code === 200) {
        console.log("上传成功回调");
        console.log(res);
        this.path = res.url;
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
        this.formList = this.formList.concat(this.uploadList);
        this.uploadList = [];
        this.number = 0;
        this.$emit("input", this.listToString(this.formList));
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
    getFileType(formPath) {
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
      const formExtension = formPath.split('.').pop();
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

    async handleDepartmentChange(department) {
      this.formData.businessesModules = ""; // 重置上级业务模块选择
      this.modules = []; // 清空之前的模块
      if (department) {
        try {
          await listModules(this.moduleQueryParams).then((response) => {
            this.modulesList = response.rows;
          });

          for (let i = 0; i < this.modulesList.length; i++) {
            console.log("123===" + this.modulesList[i].parentDepartment);
            // 根据部门字段进行筛选
            if (this.modulesList[i].parentDepartment === department) {
              this.modules.push(this.modulesList[i]);
            }
          }

          console.log("this.modules===", this.modules);
        } catch (error) {
          console.error("Failed to fetch modules:", error);
        }
      }
    },


    //通过 业务模块内容 限制选择: 细分业务内容
    async handleModuleChange(module) {
      this.formData.subBusinesses = ""; // 重置细分业务选择
      this.subBusinesses = []; // 清空之前的细分业务
      if (module) {
        try {
          // 获取所有细分业务
          await listBusinessess(this.xifenQueryParams).then((response) => {
            this.subBusinessesList = response.rows;
          });

          for (let i = 0; i < this.subBusinessesList.length; i++) {
            console.log("12321===" + this.subBusinessesList[i]);
            // 根据业务模块字段进行筛选
            if (this.subBusinessesList[i].parentModule === module) {
              this.subBusinesses.push(this.subBusinessesList[i]);
            }
          }

          console.log("this.subBusinesses===", this.subBusinesses);
        } catch (error) {
          console.error("Failed to fetch sub-businesses:", error);
        }
      }
    },

    exportAll(){
      console.log("this.formmanagementList=======>",this.formmanagementList);
      const loadingInstance = Loading.service({
        lock: true,
        text: "正在导出，请稍后...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });

      const promises = this.formmanagementList.map((form) => {
        return this.handleProjectDetails(form).then((projectNames) => {
          return {
            主责部门 : form.departmentCategory,
            业务模块 : form.businesses,
            细分业务 : form.subBusinesses,
            表单名称 : form.formTitle,
            存储表单内容 : form.scope,
            表单类型 : form.formType,
            关联流程 :this.projectNamesString,
            表单上传日期 : form.effectiveDate,
          };
        });
      });
      Promise.all(promises)
        .then((data) => {
          const ws = XLSX.utils.json_to_sheet(data);
          const wb = XLSX.utils.book_new();
          XLSX.utils.book_append_sheet(wb, ws, "项目列表");

          const wbout = XLSX.write(wb, { bookType: "xlsx", type: "array" });
          saveAs(
            new Blob([wbout], { type: "application/octet-stream" }),
            "表单总台账.xlsx"
          );
        })
        .finally(() => {
          loadingInstance.close();
        })
        .catch((error) => {
          console.error("导出失败:", error);
          loadingInstance.close();
        });

    },
    /** 查询部门列表 */
    getDeptList() {
      listDept02().then((response) => {
        // 过滤掉 deptName 为 "产品研发"、"研发"、"测试" 和 "总部" 的部门
        const filteredData = response.data.filter(
          (department) =>
            department.deptName !== "产品研发" &&
            department.deptName !== "研发" &&
            department.deptName !== "测试" &&
            department.deptName !== "总部" &&
            department.deptName !== "合力（盘锦）"
        );

        // 将每个过滤后的部门的 deptName 放入 departments 数组
        this.departments = filteredData.map(
          (department) => department.deptName
        );
      });
    },

  }
};
</script>

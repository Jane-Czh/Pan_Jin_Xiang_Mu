<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
             label-width="68px">

      <!-- 部门 进行搜索  -->
      <el-form-item label="主责部门" prop="mainResponsibleDepartment" >
        <el-select
          v-model="queryParams.mainResponsibleDepartment"
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
          :disabled="!queryParams.mainResponsibleDepartment"
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
      <el-form-item label="专业分类" prop="classificationOfSpecialties">
        <el-select
          v-model="queryParams.classificationOfSpecialties"
          placeholder="请选择制度专业分类"
          clearable
        >
          <el-option
            v-for="item in classificationOfSpecialtiesList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="制度等级" prop="regulationLeval">
        <el-select
          v-model="queryParams.regulationLeval"
          placeholder="请选择制度专业分类"
          clearable
        >
          <el-option
            v-for="item in regulationLevalList"
            :key="item"
            :label="item"
            :value="item"
          ></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发布日期" prop="createDate">
        <el-date-picker clearable
                        v-model="queryParams.createDate"
                        type="date"
                        value-format="yyyy-MM-dd"
                        placeholder="请选择制度创建日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="关键字" prop="fileTag">
        <el-input
          v-model="queryParams.fileTag"
          placeholder="请输入关键字"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!--            <el-form-item label="修订时间" prop="revisionDate">-->
      <!--              <el-date-picker clearable-->
      <!--                              v-model="queryParams.revisionDate"-->
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
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--          v-hasPermi="['file:filemanagement:remove']"-->
<!--        >删除-->
<!--        </el-button>-->
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportAll"
          v-hasPermi="['file:filemanagement:export']"
        >导出</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>


    <el-table v-loading="loading" :data="filemanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <!--      <el-table-column label="id(主键)" align="center" prop="regulationsId"/>-->
      <el-table-column label="主责部门" align="center" prop="mainResponsibleDepartment" />
      <el-table-column label="业务模块" align="center" prop="businesses" />
      <el-table-column label="细分业务" align="center" prop="subBusinesses" />
      <el-table-column label="制度名称" align="center" prop="regulationsTitle">
        <template slot-scope="scope">
          <a  @click.prevent="previewFile(scope.row.pdfPath)" style="color: #6495ED;">{{scope.row.regulationsTitle}}</a>
        </template>
      </el-table-column>
      <el-table-column label="专业分类" align="center" prop="classificationOfSpecialties" />
      <el-table-column label="制度编号" align="center" prop="regulationNumber" />
      <el-table-column label="制度等级" align="center" prop="regulationLeval" />
      <el-table-column label="关键字" align="center" prop="fileTag" />
      <el-table-column label="发布日期" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="实施日期" align="center" prop="effectiveDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="关联流程" align="center">
        <template slot-scope="scope">
          <!-- 111111111111制度文件 -->
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
      <el-table-column label="关联表单" align="center" prop="formId" />
      <el-table-column label="状态" align="center" prop="revisionContent"/>
      <el-table-column label="是否加密" align="center" prop="encryption">
        <template slot-scope="scope">
          <span>{{ scope.row.encryption === 1 ? '是' : '否' }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最新上传日期" align="center" prop="uploadDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="文件名称" align="center" prop="fileName"/>-->
      <!--      <el-table-column label="文件下载" align="center" prop="filePath">-->
      <!--        <template slot-scope="scope">-->
      <!--          <a @click.prevent="downloadFile(scope.row.filePath)" style="color: #6495ED;">点击下载</a>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column label="pdf下载" align="center" prop="pdfPath">
        <template v-slot:default="scope">
          <a v-if="scope.row.pdfPath" @click.prevent="downloadFile(scope.row.pdfPath)" style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="word下载" align="center" prop="wordPath">
        <template v-slot:default="scope">
          <a
            v-if="scope.row.wordPath"
            :disabled="thisDept !== scope.row.mainResponsibleDepartment && thisDept !== '研发' && thisDept !== '企业管理科' && thisDept !== '总部'"
            @click.prevent="downloadFile(scope.row.wordPath)"
            :style="{ color: (thisDept === scope.row.mainResponsibleDepartment || thisDept === '研发' || thisDept === '企业管理科' || thisDept === '总部') ? '#6495ED' : '#CCC', pointerEvents: (thisDept === scope.row.mainResponsibleDepartment || thisDept === '研发' || thisDept === '企业管理科' || thisDept === '总部') ? 'auto' : 'none' }"
          >
            点击下载
          </a>
        </template>
      </el-table-column>
      <!--      <el-table-column label="文件类型" align="center" prop="fileType"/>-->
      <!--      <el-table-column label="文件大小" align="center" prop="fileSize"/>-->
      <!--      <el-table-column label="pdf文件大小" align="center" prop="pdfSize"/>-->
      <!--      <el-table-column label="word文件大小" align="center" prop="wordSize"/>-->
      <!--      <el-table-column label="制度上传人" align="center" prop="uploadUsername"/>-->
      <!--      <el-table-column label="制度使用状态" align="center" prop="useState"/>-->
      <!--      <el-table-column label="历史版本制度" align="center" prop="oldRegulationsId"/>-->
      <!--      <el-table-column label="新版本制度" align="center" prop="newRegulationsId"/>-->
      <!--      <el-table-column label="标志位" align="center" prop="newFlag"/>-->
      <!--      <el-table-column label="修订时间" align="center" prop="revisionDate" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.revisionDate, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="修订内容" align="center" prop="revisionContent"/>-->
      <!--      <el-table-column label="修订人" align="center" prop="reviser"/>-->



      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

          <!--          <template v-if="this.thisDept == '研发' || this.thisDept == this.form.departmentCategory">-->
          <!--          :disabled="this.thisDept !== scope.row.departmentCategory"-->
          <!--            <el-button-->
          <!--              size="mini"-->
          <!--              type="text"-->
          <!--              icon="el-icon-upload"-->
          <!--              @click="handleUpdate(scope.row)"-->
          <!--              v-hasPermi="['file:filemanagement:edit']"-->
          <!--              :disabled="thisDept !== scope.row.departmentCategory && thisDept !== '研发'"-->
          <!--            >-->
          <!--              更新-->
          <!--            </el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleModify(scope.row)"
            v-hasPermi="['file:filemanagement:edit']"
            :disabled="thisDept !== scope.row.mainResponsibleDepartment && thisDept !== '研发' && thisDept !== '企业管理科' && thisDept !== '总部'"
          >
            修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['file:filemanagement:remove']"
            :disabled="thisDept !== scope.row.mainResponsibleDepartment && thisDept !== '研发' && thisDept !== '企业管理科' && thisDept !== '总部'"
          >
            删除
          </el-button>
          <!--          </template>-->
        </template>
      </el-table-column>

      <el-table-column label="查看" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-view"
            @click="previewFile(scope.row.pdfPath)"
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
    <el-dialog :title="title" :visible.sync="fileModifyDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="制度名称" prop="regulationsTitle">
              <el-input v-model="form.regulationsTitle" placeholder="请输入制度名称"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度编号" prop="regulationNumber">
              <el-input v-model="form.regulationNumber" placeholder="请输入制度编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="主责部门" prop="mainResponsibleDepartment" >
              <el-select
                v-model="form.mainResponsibleDepartment"
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
                :disabled="!form.mainResponsibleDepartment"
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
            <el-form-item label="制度等级" prop="regulationLeval">
              <el-select
                v-model="form.regulationLeval"
                placeholder="请选择制度专业分类"
                clearable
              >
                <el-option
                  v-for="item in regulationLevalList"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度专业分类" prop="classificationOfSpecialties">
              <el-select
                v-model="form.classificationOfSpecialties"
                placeholder="请选择制度专业分类"
                clearable
              >
                <el-option
                  v-for="item in classificationOfSpecialtiesList"
                  :key="item"
                  :label="item"
                  :value="item"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度发布日期" prop="createDate">
              <el-date-picker clearable
                              v-model="form.createDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择制度发布日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度实施日期" prop="effectiveDate">
              <el-date-picker clearable
                              v-model="form.effectiveDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择制度实施日期">
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="关键字" prop="fileTag">
              <el-input  autosize v-model="form.fileTag" placeholder="请输入关键字" />
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
    listFilemanagement,
    getFilemanagement,
    delFilemanagement,
    addFilemanagement,
    updateFilemanagement,
    getRegulationsHistory
  } from "@/api/file/filemanagement";
  import {
    listProject,
    getProject,
    getProject1,
    getProject2,
    delProject,
    addProject,
    updateProject,
    getProjectByName,
    getProjectFileName,
  } from "@/api/system/project";
  import {getUserProfile02} from '@/api/file/filemanagement'
  import {getDept02} from '@/api/file/filemanagement'
  import {getToken} from "@/utils/auth"
  import {listDept02, word2Pdf} from "../../../api/file/filemanagement";
  //业务模块api，
  import { listModules } from "@/api/function/modules";
  //细分业务api
  import { listBusinesses } from "@/api/function/businesses";
  import {Loading} from "element-ui";
  import * as XLSX from "xlsx";

  export default {
    name: "HistoryVersions",
    data() {
      return {
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
        //制度专业类别
        classificationOfSpecialtiesList: [
          "安全管理",
          "财务管理",
          "采购管理",
          "成本管理",
          "党群管理",
          "法人治理",
          "会计核算管理",
          "监察管理",
          "人力资源管理",
          "设备管理",
          "生产管理",
          "投资、规划管理",
          "信息管理",
          "研发管理",
          "营销管理",
          "质量管理",
          "综合管理"
        ],
        //部门
        departments: [],
        //制度等级
        regulationLevalList: [
          "公司级",
          "部门级"
        ],
        //表单展示数据
        formData: {
          //主责部门
          department: "",
          //业务模块
          businessesModules: "",
          //细分业务
          subBusinesses: "",
        },
        projectNames:[],  //关联流程名称列表
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
        fileList: [],
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
        filemanagementList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        fileUpdateDialogVisible: false,
        fileModifyDialogVisible: false,
        fileUploadDialogVisible: false,
        historyVersionsDialogVisible: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          regulationsTitle: null,
          useScope: null,
          uploadDate: null,
          effectiveDate: null,
          fileName: null,
          filePath: null,
          pdfPath: null,
          wordPath: null,
          fileType: null,
          fileSize: null,
          pdfSize: null,
          wordSize: null,
          fileContent: null,
          createDate: null,
          uploadUsername: null,
          useState: null,
          departmentCategory: null,
          mainResponsibleDepartment: null,
          classificationOfSpecialties: null,
          regulationLeval: null,
          regulationNumber: null,
          fileTag: null,
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null,
          projectIds: null,
          newFlag: null,
          addFlag: null,
          newRegulationsId: null,
          businesses: null,
          subBusinesses: null,
          formId: null,
          encryption: null,
        },
        //流程查询参数
        projecQueryParams: {
          pageNum: 1,
          pageSize: 10,
          id: null,
          name: null,
          state: null
        },
        //流程节点查询参数
        nodeQueryParams: {
          pageNum: 1,
          pageSize: 10,
          id: null,
          projectId: null,
          name: null,
          state: null
        },

        // 表单参数
        form: {
          regulationsId: null,
          regulationsTitle: null,
          useScope: null,
          uploadDate: null,
          effectiveDate: null,
          fileName: null,
          filePath: null,
          pdfPath: null,
          wordPath: null,
          fileType: null,
          fileSize: null,
          pdfSize: null,
          wordSize: null,
          fileContent: null,
          createDate: null,
          uploadUsername: null,
          useState: null,
          departmentCategory: null,
          mainResponsibleDepartment: null,
          classificationOfSpecialties: null,
          regulationLeval: null,
          regulationNumber: null,
          fileTag: null,
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null,
          formId: null,
          projectIds: null,
          newFlag: null,
          addFlag: null,
          newRegulationsId: null,
          businesses: null,
          subBusinesses: null,
          encryption: null
        },
        // 表单校验
        rules: {
          regulationsTitle: [
            {required: true, message: "制度标题不能为空", trigger: "blur"}
          ],
          useScope: [
            {required: true, message: "适用范围不能为空", trigger: "blur"}
          ],
          uploadDate: [
            {required: true, message: "上传日期不能为空", trigger: "blur"}
          ],
          effectiveDate: [
            {required: true, message: "生效日期不能为空", trigger: "blur"}
          ],
          fileName: [
            {required: true, message: "文件名称不能为空", trigger: "blur"}
          ],
          filePath: [
            { required: true, message: "文件路径不能为空", trigger: "blur" }
          ],
          fileType: [
            {required: true, message: "文件类型不能为空", trigger: "blur"}
          ],
          fileSize: [
            {required: true, message: "文件大小不能为空", trigger: "blur"}
          ],
          createDate: [
            {required: true, message: "制度创建日期不能为空", trigger: "blur"}
          ],
          uploadUsername: [
            {required: true, message: "制度上传人不能为空", trigger: "blur"}
          ],
          useState: [
            {required: true, message: "制度使用状态不能为空", trigger: "blur"}
          ],
          departmentCategory: [
            {required: true, message: "制度所属科室不能为空", trigger: "blur"}
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
        return this.isShowTip && (this.fileType || this.fileSize);
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
      this.getDeptList();

    },
    methods: {
      /** 查询文件管理列表 */
      getList() {
        this.loading = true;
        const newRegulationId = this.$route.params.regulationsId;
        console.log("newRegulationId=>：",newRegulationId);

        getRegulationsHistory(newRegulationId).then(response =>{
          console.log("response:：",response);
          this.filemanagementList = response;
          console.log("response.rows:：",response.rows);
          console.log("filemanagementList:：",this.filemanagementList);
          this.loading = false;
        })
      },
      /** 查询绑定的流程信息 */
      handleProjectDetails(row) {
        return listProject(this.projecQueryParams).then(response => {
          console.log("response111:", response);
          const projectList = response;
          this.projectNames = [];

          projectList.forEach(process => {
            if (process.state && process.state.includes(row.regulationsId)) {
              this.projectNames.push(process.name);
              console.log("projectNames=>", this.projectNames);
            }
          });

          // 将 projectNames 转换为用逗号分隔的字符串
          this.projectNamesString = this.projectNames.join(",");
        });
      },
      // 文件修改取消按钮
      modifyCancel() {
        this.fileModifyDialogVisible = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          regulationsId: null,
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
          uploadUsername: null,
          useState: null,
          departmentCategory: null,
          fileTag: null,
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null,
          newRegulationsId: null,
          encryption: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
       // this.getList();
        this.loading = true;
        const newRegulationId = this.$route.params.regulationsId;
        console.log("newRegulationId=>：",newRegulationId);

        getRegulationsHistory(newRegulationId).then(response =>{
          this.filemanagementList = response;
          this.filemanagementList = this.filemanagementList.filter(file => {
            // 处理每个筛选条件
            const matchesRegulationsTitle = !this.queryParams.regulationsTitle || file.regulationsTitle.includes(this.queryParams.regulationsTitle);
            const matchesUseScope = !this.queryParams.useScope || file.useScope.includes(this.queryParams.useScope);
            const matchesUploadDate = !this.queryParams.uploadDate || file.uploadDate === this.queryParams.uploadDate;
            const matchesEffectiveDate = !this.queryParams.effectiveDate || file.effectiveDate === this.queryParams.effectiveDate;
            const matchesRegulationsAddress = !this.queryParams.regulationsAddress || file.regulationsAddress.includes(this.queryParams.regulationsAddress);
            const matchesFileName = !this.queryParams.fileName || file.fileName.includes(this.queryParams.fileName);
            const matchesFilePath = !this.queryParams.filePath || file.filePath.includes(this.queryParams.filePath);
            const matchesFileType = !this.queryParams.fileType || file.fileType === this.queryParams.fileType;
            const matchesFileSize = !this.queryParams.fileSize || file.fileSize === this.queryParams.fileSize;
            const matchesCreateDate = !this.queryParams.createDate || file.createDate === this.queryParams.createDate;
            const matchesUploadUsername = !this.queryParams.uploadUsername || file.uploadUsername.includes(this.queryParams.uploadUsername);
            const matchesUseState = !this.queryParams.useState || file.useState === this.queryParams.useState;
            const matchesDepartmentCategory = !this.queryParams.departmentCategory || file.departmentCategory.includes(this.queryParams.departmentCategory);
            const matchesFileTag = !this.queryParams.fileTag || file.fileTag.includes(this.queryParams.fileTag);
            const matchesOldRegulationsId = !this.queryParams.oldRegulationsId || file.oldRegulationsId === this.queryParams.oldRegulationsId;
            const matchesRevisionDate = !this.queryParams.revisionDate || file.revisionDate === this.queryParams.revisionDate;
            const matchesRevisionContent = !this.queryParams.revisionContent || file.revisionContent.includes(this.queryParams.revisionContent);
            const matchesReviser = !this.queryParams.reviser || file.reviser.includes(this.queryParams.reviser);
            const matchesNewFlag = this.queryParams.newFlag === null || file.newFlag === this.queryParams.newFlag;
            const matchesNewRegulationsId = !this.queryParams.newRegulationsId || file.newRegulationsId === this.queryParams.newRegulationsId;
            const matchesMainResponsibleDepartment = !this.queryParams.mainResponsibleDepartment || file.mainResponsibleDepartment.includes(this.queryParams.mainResponsibleDepartment);
            const matchesClassificationOfSpecialties = !this.queryParams.classificationOfSpecialties || file.classificationOfSpecialties.includes(this.queryParams.classificationOfSpecialties);
            const matchesRegulationLeval = !this.queryParams.regulationLeval || file.regulationLeval.includes(this.queryParams.regulationLeval);
            const matchesRegulationNumber = !this.queryParams.regulationNumber || file.regulationNumber.includes(this.queryParams.regulationNumber);
            // 返回满足所有条件的文件
            return matchesRegulationsTitle &&
              matchesUseScope &&
              matchesUploadDate &&
              matchesEffectiveDate &&
              matchesRegulationsAddress &&
              matchesFileName &&
              matchesFilePath &&
              matchesFileType &&
              matchesFileSize &&
              matchesCreateDate &&
              matchesUploadUsername &&
              matchesUseState &&
              matchesDepartmentCategory &&
              matchesFileTag &&
              matchesOldRegulationsId &&
              matchesRevisionDate &&
              matchesRevisionContent &&
              matchesReviser &&
              matchesNewFlag &&
              matchesNewRegulationsId &&
              matchesMainResponsibleDepartment &&
              matchesClassificationOfSpecialties &&
              matchesRegulationLeval &&
              matchesRegulationNumber;
          });
          this.loading = false;
        })

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
      /** 修改制度文件 */
      handleModify(row) {
        this.reset();
        const regulationsId = row.regulationsId || this.ids
        getFilemanagement(regulationsId).then(response => {
          this.form = response.data;
          this.fileModifyDialogVisible = true;
          this.title = "修改制度文件";
        });
      },
      /** 修改文件提交按钮 */
      modifySubmitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.regulationsId != null) {
              updateFilemanagement(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.fileModifyDialogVisible = false;
                this.getList();
                console.log("修改文件提交按钮=>",this.form);
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        // 检查权限
        if (this.thisDept !== row.mainResponsibleDepartment  && ![ '研发', '企业管理科', '总部' ].includes(this.thisDept)) {
          this.$modal.msgError('没有权限删除该制度!');
          return; // 中止删除操作
        }
        const regulationsIds = row.regulationsId || this.ids;
        this.$modal.confirm('是否确认删除？').then(function () {
          return delFilemanagement(regulationsIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {
        });
        if(row.oldRegulationsId != null) {
          getFilemanagement(row.oldRegulationsId).then(response => {
            console.log("当前表单1=>",this.form);
            const lastForm = response.data;
            console.log("上一表单1=>",lastForm);
            lastForm.newRegulationsId = row.newRegulationsId;
            console.log("上一表单1=>",lastForm);
            updateFilemanagement(lastForm).then(response => {
            });
          });
          getFilemanagement(row.newRegulationsId).then(response => {
            console.log("当前表单2=>",this.form);
            const newForm = response.data;
            console.log("上一表单2=>",newForm);
            newForm.oldRegulationsId = row.oldRegulationsId;
            console.log("上一表单2=>",newForm);
            updateFilemanagement(newForm).then(response => {
            });
          });
          this.getList();
        }
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('file/filemanagement/export', {
          ...this.queryParams
        }, `filemanagement_${new Date().getTime()}.xlsx`)
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
        if (this.form.fileList.length === 0) {
          return callback(new Error('文件路径不能为空'));
        }
        // 可以添加其他校验逻辑，例如文件类型等
        callback();
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
      //文件预览
      previewFile(pdfPath) {
        if(pdfPath) {
          window.open(pdfPath, '_blank');
        } else {
          this.$modal.msgError('没有上传pdf文件，无法预览！');
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
            await listBusinesses(this.xifenQueryParams).then((response) => {
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
        const loadingInstance = Loading.service({
          lock: true,
          text: "正在导出，请稍后...",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)",
        });

        const promises = this.filemanagementList.map((regulation) => {
          return this.handleProjectDetails(regulation).then((projectNames) => {
            return {
              主责部门 : regulation.mainResponsibleDepartment,
              制度名称 : regulation.regulationsTitle,
              专业分类 : regulation.classificationOfSpecialties,
              制度编号 : regulation.regulationNumber,
              制度等级 : regulation.regulationLeval,
              关键字 : regulation.fileTag,
              发布日期 : regulation.createDate,
              实施日期 : regulation.effectiveDate,
              关联流程 :this.projectNamesString,
              关联表单 :regulation.formId,
              状态 :regulation.revisionContent,
              最新上传日期 : regulation.uploadDate,
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
              "制度历史版本台账.xlsx"
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

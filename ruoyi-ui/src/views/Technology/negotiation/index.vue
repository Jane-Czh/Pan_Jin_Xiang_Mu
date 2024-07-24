<template>
  <div class="app-container">
    <el-form
      :model="queryParams"
      ref="queryForm"
      size="small"
      :inline="true"
      v-show="showSearch"
      label-width="68px"
    >
      <!--      <el-form-item label="变更时间" prop="changTime">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.changTime"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择变更时间">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="发放对象" prop="changeTarget">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.changeTarget"-->
      <!--          placeholder="请输入发放对象"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="变更单状态" prop="fileState">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileState"-->
      <!--          placeholder="请输入变更单状态"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="审查状态" prop="examineState">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.examineState"-->
      <!--          placeholder="请输入审查状态"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="协商状态" prop="consultState">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.consultState"-->
      <!--          placeholder="请输入协商状态"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="用户审核状态" prop="userReviewState">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.userReviewState"-->
      <!--          placeholder="请输入用户审核状态"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="样品状态" prop="sampleState">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.sampleState"-->
      <!--          placeholder="请输入样品状态"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="修订时间" prop="revisionDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.revisionDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择修订时间">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="修订人" prop="reviser">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.reviser"-->
      <!--          placeholder="请输入修订人"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="标志位(是否为最新文件)" prop="newFlag">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.newFlag"-->
      <!--          placeholder="请输入标志位(是否为最新文件)"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="该制度下一制度id" prop="newRegulationsId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.newRegulationsId"-->
      <!--          placeholder="请输入该制度下一制度id"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="历史版本制度" prop="oldRegulationsId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.oldRegulationsId"-->
      <!--          placeholder="请输入历史版本制度"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="变更单标题" prop="regulationsTitle">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.regulationsTitle"-->
      <!--          placeholder="请输入变更单标题"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="适用范围" prop="useScope">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.useScope"-->
      <!--          placeholder="请输入适用范围"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="上传日期" prop="uploadDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.uploadDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择上传日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="生效日期" prop="effectiveDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.effectiveDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择生效日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="变更单名称" prop="fileName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileName"-->
      <!--          placeholder="请输入变更单名称"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="文件路径" prop="filePath">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.filePath"-->
      <!--          placeholder="请输入文件路径"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="文件大小" prop="fileSize">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileSize"-->
      <!--          placeholder="请输入文件大小"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="制度创建日期" prop="createDate">-->
      <!--        <el-date-picker clearable-->
      <!--          v-model="queryParams.createDate"-->
      <!--          type="date"-->
      <!--          value-format="yyyy-MM-dd"-->
      <!--          placeholder="请选择制度创建日期">-->
      <!--        </el-date-picker>-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="制度上传人" prop="uploadUsername">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.uploadUsername"-->
      <!--          placeholder="请输入制度上传人"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="制度使用状态" prop="useState">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.useState"-->
      <!--          placeholder="请输入制度使用状态"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="制度所属科室" prop="departmentCategory">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.departmentCategory"-->
      <!--          placeholder="请输入制度所属科室"-->
      <!--          clearable-->
      <!--          @keyup.enter.native="handleQuery"-->
      <!--        />-->
      <!--      </el-form-item>-->
      <!--      <el-form-item label="制度标签名称" prop="fileTag">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileTag"-->
      <!--          placeholder="请输入制度标签名称"-->
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
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="primary"-->
      <!--          plain-->
      <!--          icon="el-icon-plus"-->
      <!--          size="mini"-->
      <!--          @click="handleUpload"-->
      <!--          v-hasPermi="['file:filemanagement:add']"-->
      <!--        >上传-->
      <!--        </el-button>-->
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
          >删除
        </el-button>
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="ChangeorderList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="主键" align="center" prop="tfcoId" />-->
      <el-table-column label="变更单名称" align="center" prop="fileName" />
      <!--      <el-table-column label="变更时间" align="center" prop="changTime" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.changTime, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="发放对象" align="center" prop="changeTarget" />-->
      <el-table-column label="变更单状态" align="center" prop="fileState" />
      <el-table-column label="审查状态" align="center" prop="examineState" />
      <!-- 在examineState列后添加审核按钮列 -->
      <el-table-column
        label="审查操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            :disabled="
              scope.row.examineState === '已通过' ||
              scope.row.examineState === '未通过'
            "
            @click="handleExamine(scope.row)"
            >审查
          </el-button>
        </template>
      </el-table-column>
      <el-table-column label="协商状态" align="center" prop="consultState" />
      <!--      <el-table-column label="协商操作" align="center" class-name="small-padding fixed-width">-->
      <!--        <template slot-scope="scope">-->
      <!--          <el-button size="mini" type="text":disabled="scope.row.examineState !== '已通过'|| scope.row.consultState === '已通过'|| scope.row.consultState === '未通过'|| scope.row.fileState === '审查通过,请更新协商文件'"-->
      <!--                     @click="handleConsult(scope.row)">协商</el-button>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <el-table-column
        label="协商操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            :disabled="
              scope.row.examineState !== '已通过' ||
              scope.row.consultState === '已通过' ||
              scope.row.consultState === '未通过' ||
              scope.row.fileState === '审查通过,请更新协商文件'
            "
            @click="handleConsult(scope.row)"
            >协商
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="用户审核状态"
        align="center"
        prop="userReviewState"
      />
      <el-table-column
        label="用户审核操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            :disabled="
              scope.row.consultState !== '已通过' ||
              scope.row.userReviewState === '已通过' ||
              scope.row.userReviewState === '未通过' ||
              scope.row.fileState === '协商通过,请更新用户审核文件'
            "
            @click="handleUserReview(scope.row)"
            >用户审核</el-button
          >
        </template>
      </el-table-column>
      <el-table-column label="采样状态" align="center" prop="sampleState" />
      <el-table-column
        label="采样检测操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            :disabled="
              scope.row.userReviewState !== '已通过' ||
              scope.row.sampleState === '已通过' ||
              scope.row.sampleState === '未通过' ||
              scope.row.fileState === '审核通过,请更新样品检查文件'
            "
            @click="handleSample(scope.row)"
            >样品检查</el-button
          >
        </template>
      </el-table-column>
      <!--      <el-table-column label="修订时间" align="center" prop="revisionDate" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.revisionDate, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="修订内容" align="center" prop="revisionContent" />-->
      <!--      <el-table-column label="修订人" align="center" prop="reviser" />-->
      <!--      <el-table-column label="标志位(是否为最新文件)" align="center" prop="newFlag" />-->
      <!--      <el-table-column label="该制度下一制度id" align="center" prop="newRegulationsId" />-->
      <!--      <el-table-column label="历史版本制度" align="center" prop="oldRegulationsId" />-->
      <!--      <el-table-column label="变更单标题" align="center" prop="regulationsTitle" />-->
      <!--      <el-table-column label="适用范围" align="center" prop="useScope" />-->
      <el-table-column
        label="变更单上传人"
        align="center"
        prop="uploadUsername"
      />
      <el-table-column
        label="上传日期"
        align="center"
        prop="uploadDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.uploadDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <!--      <el-table-column label="生效日期" align="center" prop="effectiveDate" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.effectiveDate, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!-- <el-table-column label="下载" align="center" prop="filePath">
        <template slot-scope="scope">
          <a :href="baseUrl + scope.row.filePath" download>点击下载</a>
        </template>
      </el-table-column> -->
      <el-table-column label="文件下载" align="center" prop="filePath">
        <template slot-scope="scope">
          <el-button
            type="primary"
            icon="el-icon-download"
            size="mini"
            @click="downloadFile(scope.row.filePath)"
          >
            下载
          </el-button>
        </template>
      </el-table-column>
      <!--      <el-table-column label="文件类型" align="center" prop="fileType" />-->
      <!--      <el-table-column label="文件大小" align="center" prop="fileSize" />-->
      <el-table-column
        label="变更单创建日期"
        align="center"
        prop="createDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>

      <!--      <el-table-column label="制度使用状态" align="center" prop="useState" />-->
      <!--      <el-table-column label="制度所属科室" align="center" prop="departmentCategory" />-->
      <!--      <el-table-column label="制度标签名称" align="center" prop="fileTag" />-->
      <!--      <el-table-column label="审核标签" align="center" prop="reviewLayer" />-->
      <!--      <el-table-column label="协商标签" align="center" prop="negotiationLayer" />-->
      <!--      <el-table-column label="用户审核标签" align="center" prop="userReviewlayer" />-->
      <!--      <el-table-column label="样品检测标签" align="center" prop="sampleLayer" />-->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-upload"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Technology:Changeorder:edit']"
            v-if="scope.row.newFlag !== 0"
            >更新
          </el-button>
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-edit"-->
          <!--            @click="handleModify(scope.row)"-->
          <!--            v-hasPermi="['file:filemanagement:edit']"-->
          <!--          >修改-->
          <!--          </el-button>-->
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Technology:Changeorder:remove']"
            >删除
          </el-button>
        </template>
      </el-table-column>
      <el-table-column
        label="查看"
        align="center"
        class-name="small-padding fixed-width"
      >
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
            icon="el-icon-s-operation"
            @click="handleHistoryVersions(scope.row)"
            >用户审核
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total > 0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />
    <!-- 上传文件对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="fileUploadDialogVisible"
      width="1000px"
      :center="true"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center; height: 100px">
              <!--文件上传-->
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
            </div>
          </el-col>
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="变更单创建日期" prop="createDate">
              <el-date-picker
                clearable
                v-model="form.createDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择创建日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>

        <el-row>
          <!--          <el-col :span='12'>-->
          <!--            <el-form-item label="文件大小" prop="fileSize">-->
          <!--              <el-input v-model="form.fileSize" placeholder="请输入文件大小" :disabled="false"/>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="uploadSubmitForm">确 定</el-button>
        <el-button @click="uploadCancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 更新文件对话框 -->
    <el-dialog
      :title="title"
      :visible.sync="fileUpdateDialogVisible"
      width="1000px"
      :center="true"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-upload
          class="update-file-uploader"
          :action="uploadFileUrl"
          :headers="headers"
          :on-change="handleFileChange"
          :on-preview="handlePreview"
          :on-remove="handleRemove"
          :before-remove="beforeRemove"
          :on-exceed="handleExceed"
          :on-success="handleUpdateSuccess"
          multiple
          :limit="3"
          :file-list="fileList"
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
    <el-dialog
      :title="title"
      :visible.sync="fileModifyDialogVisible"
      width="1000px"
      :center="true"
      append-to-body
    >
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <el-form-item label="变更单创建日期" prop="createDate">
              <el-date-picker
                clearable
                v-model="form.createDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择变更单创建日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="modifySubmitForm">确 定</el-button>
        <el-button @click="modifyCancel">取 消</el-button>
      </div>
    </el-dialog>

    <custom-confirm-dialog
      :title="'提示'"
      :message="'是否通过协商？'"
      :visible.sync="dialogExamineVisible"
      @confirm="handleDialogConfirm"
      @cancel="handleDialogCancel"
      @close="handleDialogClose"
    ></custom-confirm-dialog>
  </div>
</template>

<script>
import {
  listChangeorder,
  getChangeorder,
  delChangeorder,
  addChangeorder,
  updateChangeorder,
} from "@/api/Technology/Changeorder";
import { getUserProfile } from "@/api/system/user";
import { getDept } from "@/api/system/dept";
import { getToken } from "@/utils/auth";
import CustomConfirmDialog from "@/views/Technology/Changeorder/CustomConfirmDialog.vue";
import { word2Pdf } from "@/api/file/filemanagement";
// import {
//   word2Pdf
// } from "../../../api/file/filemanagement";

export default {
  name: "negotiation",
  components: { CustomConfirmDialog },
  data() {
    return {
      dialogExamineVisible: false,
      currentRow: null,

      activeNames: [], // 默认展开的折叠项的名字
      number: 0,
      uploadList: [],
      fileList: [],
      fd: {},
      path: "",
      baseUrl: process.env.VUE_APP_BASE_API,
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      // uploadFileUrl:"/profile/upload"+"/"+this.file.name,
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      userProfile: {}, // 存储用户个人信息的对象
      input: "",
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
      fileUpdateDialogVisible: false,
      fileModifyDialogVisible: false,
      fileUploadDialogVisible: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        changTime: null,
        changeTarget: null,
        fileState: null,
        examineState: null,
        consultState: null,
        userReviewState: null,
        sampleState: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        newRegulationsId: null,
        oldRegulationsId: null,
        regulationsTitle: null,
        useScope: null,
        uploadDate: null,
        effectiveDate: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        createDate: null,
        uploadUsername: null,
        useState: null,
        departmentCategory: null,
        fileTag: null,
        reviewLayer: null,
        negotiationLayer: null,
        userReviewlayer: null,
        sampleLayer: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {},
    };
  },
  watch: {
    value: {
      handler(val) {
        if (val) {
          let temp = 1;
          // 首先将值转为数组
          const list = Array.isArray(val) ? val : this.value.split(",");
          // 然后将数组转为对象数组
          this.fileList = list.map((item) => {
            if (typeof item === "string") {
              item = { name: item, url: item };
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
      immediate: true,
    },
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
      const newRegulationId = this.$route.params.reviewLayer;
      console.log("newRegulationId=>：", newRegulationId);

      listChangeorder(this.queryParams).then((response) => {
        // 过滤数据，只保留 negotiationLayer 和 newRegulationId 相等的记录
        this.ChangeorderList = response.rows.filter(
          (item) => item.negotiationLayer === Number(newRegulationId)
        );
        console.log("newRegulationIddddddd=>：", newRegulationId);
        this.total = this.ChangeorderList.length; // 更新总记录数
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
    },
    // 文件更新取消按钮
    updateCancel() {
      this.fileUpdateDialogVisible = false;
      this.reset();
      this.fileList = [];
    },
    // 表单重置
    reset() {
      this.form = {
        tfcoId: null,
        changTime: null,
        changeTarget: null,
        fileState: null,
        examineState: null,
        consultState: null,
        userReviewState: null,
        sampleState: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        newRegulationsId: null,
        oldRegulationsId: null,
        regulationsTitle: null,
        useScope: null,
        uploadDate: null,
        effectiveDate: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        createDate: null,
        uploadUsername: null,
        useState: null,
        departmentCategory: null,
        fileTag: null,
        reviewLayer: null,
        negotiationLayer: null,
        userReviewlayer: null,
        sampleLayer: null,
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
      this.ids = selection.map((item) => item.tfcoId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 上传制度文件 */
    handleUpload() {
      this.reset();
      this.fileUploadDialogVisible = true;
      this.title = "上传文件";
    },
    /** 修改制度文件 */
    handleModify(row) {
      this.reset();
      const tfcoId = row.tfcoId || this.ids;
      getChangeorder(tfcoId).then((response) => {
        this.form = response.data;
        this.fileModifyDialogVisible = true;
        this.title = "修改变更单信息";
      });
    },
    /** 更新文件 */
    handleUpdate(row) {
      this.reset();
      const tfcoId = row.tfcoId || this.ids;
      getChangeorder(tfcoId).then((response) => {
        this.form = response.data;
        this.form.newFlag = 0;
        console.log("更新文件=>", this.form);
        // 加入判断条件
        if (this.form.fileState === "审查通过,请更新协商文件") {
          this.form.fileState = "等待协商";
        }
        if (this.form.fileState === "审查不通过,等待更新文件并重新审查") {
          this.form.fileState = "等待审查";
          this.form.examineState = "未审查";
        }

        if (this.form.fileState === "审核通过,请更新样品检查文件") {
          this.form.fileState = "等待样品检查";
        }
        if (this.form.fileState === "用户审核不通过,等待更新文件并重新审核") {
          this.form.fileState = "等待审核";
          this.form.userReviewState = "未审核";
        }
        if (this.form.fileState === "样品检查不通过,等待更新文件并重新检测") {
          this.form.fileState = "等待检测";
          this.form.sampleState = "未采样";
        }
        this.fileUpdateDialogVisible = true;
        this.title = "更新变更单";
      });
    },
    /** 历史版本管理 */
    handleHistoryVersions(row) {
      const negotiationLayer = row.negotiationLayer;
      this.$router.push("/userReviewlayer/userreviewlayer/" + negotiationLayer);
    },
    // /** 制度修改频率 */
    // handleRevisionFrequency(row) {
    //   const tfcoId = row.tfcoId;
    //   this.$router.push("/file/filemanagement/revisionFrequency/" + tfcoId);
    // },
    /** 上传文件提交按钮 */
    // uploadSubmitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       this.form.newFlag = 1;
    //       // 设置 examineState 为未审核
    //       this.form.fileState = "等待审查";
    //       this.form.examineState = "未审查";
    //       this.form.consultState = "未协商";
    //       this.form.userReviewState = "未审核";
    //       this.form.sampleState = "未采样";
    //       this.form.reviewLayer =1;
    //       this.form.negotiationLayer =0;
    //       this.form.userReviewlayer =0;
    //       this.form.sampleLayer =0;
    //       addChangeorder(this.form).then(response => {
    //         this.$modal.msgSuccess("上传成功");
    //         this.fileUploadDialogVisible = false;
    //         this.getList();
    //         console.log("上传文件提交按钮=>",this.form);
    //       });
    //     }
    //   });
    // },
    /** 修改文件提交按钮 */
    modifySubmitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.tfcoId != null) {
            updateChangeorder(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.fileModifyDialogVisible = false;
              this.getList();
              console.log("修改文件提交按钮=>", this.form);
            });
          }
        }
      });
    },
    /** 更新文件提交按钮 */
    updateSubmitForm() {
      if (this.form.fileName === null) {
        this.$modal.msgError("请上传文件");
        return;
      }
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.tfcoId != null) {
            updateChangeorder(this.form);
            this.form.newFlag = 1;
            this.form.oldRegulationsId = this.form.tfcoId;
            if (this.form.fileState === "协商通过,请更新用户审核文件") {
              this.form.fileState = "等待审核";
              this.form.userReviewlayer = this.form.negotiationLayer;
              this.form.negotiationLayer = 0;
            }
            if (this.form.fileState === "协商不通过,等待更新文件并重新协商") {
              this.form.fileState = "等待协商";
              this.form.consultState = "未协商";
            }
            addChangeorder(this.form).then((response) => {
              const newId = response.data;
              this.$modal.msgSuccess("更新成功");
              this.fileUpdateDialogVisible = false;
              this.form.newRegulationsId = null;
              //更新历史版本制度
              getChangeorder(this.form.oldRegulationsId).then((response) => {
                const lastForm = response.data;
                lastForm.newFlag = 0;
                lastForm.newRegulationsId = newId;
                console.log("上一表单=>", lastForm);
                updateChangeorder(lastForm).then((response) => {});
                // updateChangeorder(this.form).then(response => {
                // });
              });
              this.getList();
              console.log("更新文件提交按钮1=>", this.form);
              console.log("response=>", response);
            });
            this.getList();
          }
        }
      });
      this.fileList = [];
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      console.log("当前表单1=>", row);
      const tfcoIds = row.tfcoId || this.ids;
      this.$modal
        .confirm('是否确认删除文件编号为"' + tfcoIds + '"的数据项？')
        .then(function () {
          return delChangeorder(tfcoIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
      if (row.oldRegulationsId != null) {
        getChangeorder(row.oldRegulationsId).then((response) => {
          console.log("当前表单3=>", this.form);
          const lastForm = response.data;
          console.log("上一表单=>", lastForm);
          lastForm.newFlag = 1;
          console.log("上一表单=>", lastForm);
          updateChangeorder(lastForm).then((response) => {});
        });
        this.getList();
      }
    },
    // 在 methods 部分添加 handleExamine 方法
    handleExamine(row) {
      this.$confirm("是否通过审查？", "提示", {
        confirmButtonText: "通过",
        cancelButtonText: "不通过",
        type: "warning",
      })
        .then(() => {
          // 设置examineState为已通过
          row.examineState = "已通过";
          row.fileState = "审查通过,请更新协商文件";
          // 调用API将更新后的数据保存到数据库中
          saveToDatabase(row);
          this.$message({
            type: "success",
            message: "审查通过",
            customClass: "success-message", // 添加自定义类名
          });
          // 在此处执行审核通过的逻辑操作
        })
        .catch(() => {
          row.examineState = "未通过";
          row.fileState = "审查不通过,等待更新文件并重新审查";
          saveToDatabase(row);
          this.$message({
            type: "success",
            message: "审查未通过",
            customClass: "error-message", // 添加自定义类名
          });
          // this.$message({
          //   type: 'info',
          //   message: '取消审查'
          // });
        });
    },
    // 在 methods 部分添加 handleConsult 方法
    handleConsult(row) {
      this.currentRow = row;
      this.dialogExamineVisible = true;
    },
    handleDialogConfirm() {
      this.currentRow.consultState = "已通过";
      this.currentRow.fileState = "协商通过,请更新用户审核文件";
      saveToDatabase(this.currentRow);
      this.$message({
        type: "success",
        message: "协商通过",
        customClass: "success-message", // 添加自定义类名
      });
      this.dialogExamineVisible = false;
    },
    handleDialogCancel() {
      this.currentRow.consultState = "未通过";
      this.currentRow.fileState = "协商不通过,等待更新文件并重新协商";
      saveToDatabase(this.currentRow);
      this.$message({
        type: "error",
        message: "协商未通过",
        customClass: "error-message", // 添加自定义类名
      });
      this.dialogExamineVisible = false;
    },
    handleDialogClose() {
      // 用户关闭了对话框，不做任何事情
      console.log("用户关闭了对话框");
      this.dialogExamineVisible = false;
    },
    // handleConsult(row) {
    //   this.$confirm('是否通过协商？', '提示', {
    //     confirmButtonText: '通过',
    //     cancelButtonText: '不通过',
    //     type: 'warning'
    //   }).then(() => {
    //     // 设置consultState为已通过
    //     row.consultState = "已通过";
    //     row.fileState = "协商通过,请更新用户审核文件";
    //     // 调用API将更新后的数据保存到数据库中
    //     saveToDatabasecon(row);
    //     this.$message({
    //       type: 'success',
    //       message: '协商通过',
    //       customClass: 'success-message' // 添加自定义类名
    //     });
    //     // 在此处执行审核通过的逻辑操作
    //   }).catch(() => {
    //     // 设置examineState为已通过
    //     row.consultState = "未通过";
    //     row.fileState = "协商不通过,等待更新文件并重新协商";
    //     // 调用API将更新后的数据保存到数据库中
    //     saveToDatabasecon(row);
    //     this.$message({
    //       type: 'success',
    //       message: '协商未通过',
    //       customClass: 'error-message' // 添加自定义类名
    //     });
    //     // this.$message({
    //     //   type: 'info',
    //     //   message: '取消通过'
    //     // });
    //     // 在此处执行审核不通过的逻辑操作
    //   });
    // },
    // 在 methods 部分添加 handleUserReview 方法
    handleUserReview(row) {
      this.$confirm("是否通过审核？", "提示", {
        confirmButtonText: "通过",
        cancelButtonText: "不通过",
        type: "warning",
      })
        .then(() => {
          // 设置examineState为已通过
          row.userReviewState = "已通过";
          row.fileState = "审核通过,请更新样品检查文件";
          // 调用API将更新后的数据保存到数据库中
          saveToDatabaseuser(row);
          this.$message({
            type: "success",
            message: "审核通过",
            customClass: "success-message", // 添加自定义类名
          });
          // 在此处执行审核通过的逻辑操作
        })
        .catch(() => {
          // 设置examineState为未通过
          row.userReviewState = "未通过";
          row.fileState = "用户审核不通过,等待更新文件并重新审核";
          // 调用API将更新后的数据保存到数据库中
          saveToDatabaseuser(row);
          this.$message({
            type: "success",
            message: "审核未通过",
            customClass: "error-message", // 添加自定义类名
          });
          // this.$message({
          //   type: 'info',
          //   message: '取消通过'
          // });
          // 在此处执行审核不通过的逻辑操作
        });
    },
    // 在 methods 部分添加 handleSample 方法
    handleSample(row) {
      this.$confirm("是否通过检查？", "提示", {
        confirmButtonText: "通过",
        cancelButtonText: "不通过",
        type: "warning",
      })
        .then(() => {
          // 设置examineState为已通过
          row.sampleState = "已通过";
          row.fileState = "变更单等待发放";
          // 调用API将更新后的数据保存到数据库中
          saveToDatabaseusample(row);
          this.$message({
            type: "success",
            message: "样品检查通过",
            customClass: "success-message", // 添加自定义类名
          });
          // 在此处执行审核通过的逻辑操作
        })
        .catch(() => {
          // 设置examineState为未通过
          row.sampleState = "未通过";
          row.fileState = "样品检查不通过,等待更新文件并重新检测";
          // 调用API将更新后的数据保存到数据库中
          saveToDatabaseusample(row);
          this.$message({
            type: "success",
            message: "样品检查未通过",
            customClass: "error-message", // 添加自定义类名
          });
          // this.$message({
          //   type: 'info',
          //   message: '取消通过'
          // });
          // 在此处执行审核不通过的逻辑操作
        });
    },

    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "Technology/Changeorder/export",
        {
          ...this.queryParams,
        },
        `Changeorder_${new Date().getTime()}.xlsx`
      );
    },
    // 上传前校检格式和大小
    handleBeforeUpload(file) {
      // 校检文件类型
      if (this.fileType) {
        const fileName = file.name.split(".");
        const fileExt = fileName[fileName.length - 1];
        const isTypeOk = this.fileType.indexOf(fileExt) >= 0;
        if (!isTypeOk) {
          this.$modal.msgError(
            `文件格式不正确, 请上传${this.fileType.join("/")}格式文件!`
          );
          return false;
        }
      }
      // 校检文件大小
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$modal.msgError(`上传文件大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      // this.$modal.loading("正在上传文件，请稍候...");
      this.number++;
      return true;
    },
    /** 上传文件时的动作*/
    handleFileChange(file, fileList) {
      console.log(this.ChangeorderList);
      const uploadedFile = file.raw; // 获取上传的文件对象
      // 将文件名填充到对应的输入框
      this.form.fileName = uploadedFile.name;
      //将文件路径填充到对应的输入框
      this.form.filePath = this.path;
      // 将文件类型填充到对应的输入框
      this.form.fileType = this.getFileType(this.form.filePath);
      // 将文件大小填充到对应的输入框
      // this.form.fileSize = this.formatFileSize(uploadedFile.size);
      this.form.fileSize = uploadedFile.size;
      // 获取当前时间作为上传日期，并填充到对应的输入框
      const currentDate = new Date();
      this.form.uploadDate = currentDate.toISOString().split("T")[0];
      this.getUserInfo();
    },
    // 上传成功回调
    handleUploadSuccess(res, file) {
      const uploadedFile = file.raw; // 获取上传的文件对象
      const uploadedFileName = uploadedFile.name; // 获取上传文件的文件名
      // 发起请求检查文件名是否存在于数据库中
      const isFileNameDuplicate = this.ChangeorderList.some(
        (item) => item.fileName === uploadedFileName
      );
      console.log("发起请求检查文件名是否存在于数据库中", isFileNameDuplicate);

      if (isFileNameDuplicate) {
        // 如果文件名重复，弹出警告框
        this.$modal.msgError(
          "同名文件已存在！"
        );
        console.log("同名文件已存在！");
        return false; // 中断上传流程
      }
      if (res.code === 200) {
        console.log("上传成功回调");
        console.log(res);
        this.path = res.url;
        this.uploadList.push({ name: res.fileName, url: res.fileName });
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
        this.uploadList.push({ name: res.fileName, url: res.fileName });
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
      this.$message.warning(
        `当前限制选择 2 个文件，本次选择了 ${files.length} 个文件，共选择了 ${
          files.length + fileList.length
        } 个文件`
      );
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
    getFileType(filePath) {
      // 获取文件名的后缀名
      const fileExtension = filePath.split(".").pop();
      console.log("fileExtension=>", fileExtension);

      // 根据文件后缀名判断文件类型
      switch (fileExtension.toLowerCase()) {
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
    // 调用接口获取用户信息
    getUserInfo() {
      getUserProfile()
        .then((response) => {
          // 处理成功的情况
          console.log("成功获取用户信息:", response.data);
          const userInfo = response.data; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
          // 填充到对应的输入框中
          this.form.uploadUsername = userInfo.userName;
          //根据部门id获取部门名称
          getDept(userInfo.deptId).then((response) => {
            const deptInfo = response.data;
            this.form.departmentCategory = deptInfo.deptName;
          });
        })
        .catch((error) => {
          // 处理失败的情况
          console.error("获取用户信息失败:", error);
        });
    },
    // //文件预览
    previewFile(filePath) {
      const fileType = this.getFileType(filePath);
      console.log("filePath:", filePath);
      console.log("fileType:", fileType);
      switch (fileType) {
        case "pdf":
          console.log("fileType1111:", fileType);
          window.open(filePath, "_blank");
          break;
        case "word":
          const pdfFilePath = this.convertToPdfPath(filePath);
          console.log("filePath:", filePath);
          console.log("pdfFilePath:", pdfFilePath);
          word2Pdf(filePath, pdfFilePath).then((response) => {
            window.open(pdfFilePath, "_blank");
          });
          //http://172.19.4.28:81/file/172.19.4.28:8080/profile/upload/2024/06/27/%E6%96%87%E4%BB%B64_20240627173517A010.pdf
          //http://172.19.4.28:8080/profile/upload/2024/06/27/%E6%96%87%E4%BB%B64_20240627173517A010.pdf
          break;
      }
      // 使用 window.open 方法打开一个新窗口，并将文件路径传递给该窗口
    },
    convertToPdfPath(wordFilePath) {
      // 找到文件路径中的最后一个点的位置
      const lastDotIndex = wordFilePath.lastIndexOf(".");

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
  },
};
// 保存数据到数据库的函数
function saveToDatabase(data) {
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
// 保存数据到数据库的函数
function saveToDatabase1(data) {
  this.row.fileState = "等待协商";
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
function saveToDatabasecon(data) {
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
function saveToDatabasecon1(data) {
  this.row.fileState = "等待用户审核";
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
function saveToDatabaseuser(data) {
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
function saveToDatabaseuser1(data) {
  this.row.fileState = "等待样品检查";
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
function saveToDatabaseusample(data) {
  // 调用相应的API来保存数据到数据库
  updateChangeorder(data)
    .then((response) => {
      // 更新成功后的处理逻辑
      this.$modal.msgSuccess("修改成功");
      this.fileModifyDialogVisible = false;
      this.getList();
    })
    .catch((error) => {
      // 更新失败后的处理逻辑
      console.error("更新失败:", error);
      this.$modal.msgError("修改失败");
    });
}
</script>
<style>
/* 在项目的样式文件中添加以下CSS */
.el-message--success {
  color: green;
}

.el-message--error {
  color: red;
}
</style>


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
      <!--      <el-form-item label="制度标题" prop="regulationsTitle">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.regulationsTitle"-->
      <!--          placeholder="请输入制度标题"-->
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
      <!--      <el-form-item label="文件名称" prop="fileName">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.fileName"-->
      <!--          placeholder="请输入文件名称"-->
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
      <!--      <el-form-item label="历史版本制度" prop="oldRegulationsId">-->
      <!--        <el-input-->
      <!--          v-model="queryParams.oldRegulationsId"-->
      <!--          placeholder="请输入历史版本制度"-->
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
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
          >搜索</el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
          >重置</el-button
        >
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
          v-hasPermi="['Technology:retention:add']"
          >上传</el-button
        >
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="success"-->
      <!--          plain-->
      <!--          icon="el-icon-edit"-->
      <!--          size="mini"-->
      <!--          :disabled="single"-->
      <!--          @click="handleUpdate"-->
      <!--          v-hasPermi="['Technology:retention:edit']"-->
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
          v-hasPermi="['Technology:retention:remove']"
          >删除</el-button
        >
      </el-col>
      <!--      <el-col :span="1.5">-->
      <!--        <el-button-->
      <!--          type="warning"-->
      <!--          plain-->
      <!--          icon="el-icon-download"-->
      <!--          size="mini"-->
      <!--          @click="handleExport"-->
      <!--          v-hasPermi="['Technology:retention:export']"-->
      <!--        >导出</el-button>-->
      <!--      </el-col>-->
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="retentionList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <!--      <el-table-column label="主键" align="center" prop="tfemId" />-->
      <el-table-column
        label="文件标题"
        align="center"
        prop="regulationsTitle"
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
      <el-table-column label="文件名称" align="center" prop="fileName" />
      <!-- <el-table-column label="文件路径" align="center" prop="filePath">
        <template slot-scope="scope">
          <a :href="scope.row.filePath" download>点击下载</a>
        </template>
      </el-table-column> -->
      <el-table-column label="文件路径" align="center" prop="filePath">
        <template slot-scope="scope">
          <a @click.prevent="downloadFile(scope.row.filePath)">点击下载</a>
        </template>
      </el-table-column>

      <el-table-column label="文件类型" align="center" prop="fileType" />
      <!--      <el-table-column label="文件大小" align="center" prop="fileSize" />-->
      <el-table-column
        label="文件创建日期"
        align="center"
        prop="createDate"
        width="180"
      >
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, "{y}-{m}-{d}") }}</span>
        </template>
      </el-table-column>
      <el-table-column
        label="文件上传人"
        align="center"
        prop="uploadUsername"
      />
      <!--      <el-table-column label="制度使用状态" align="center" prop="useState" />-->
      <!--      <el-table-column label="制度所属科室" align="center" prop="departmentCategory" />-->
      <!--      <el-table-column label="制度标签名称" align="center" prop="fileTag" />-->
      <!--      <el-table-column label="历史版本制度" align="center" prop="oldRegulationsId" />-->
      <!--      <el-table-column label="修订时间" align="center" prop="revisionDate" width="180">-->
      <!--        <template slot-scope="scope">-->
      <!--          <span>{{ parseTime(scope.row.revisionDate, '{y}-{m}-{d}') }}</span>-->
      <!--        </template>-->
      <!--      </el-table-column>-->
      <!--      <el-table-column label="修订内容" align="center" prop="revisionContent" />-->
      <!--      <el-table-column label="修订人" align="center" prop="reviser" />-->
      <!--      <el-table-column label="标志位(是否为最新文件)" align="center" prop="newFlag" />-->
      <!--      <el-table-column label="该制度下一制度id" align="center" prop="newRegulationsId" />-->
      <el-table-column
        label="操作"
        align="center"
        class-name="small-padding fixed-width"
      >
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['Technology:retention:edit']"
            >更新</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleModify(scope.row)"
            v-hasPermi="['Technology:retention:edit']"
            >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['Technology:retention:remove']"
            >删除</el-button
          >
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
          <!--          <el-button-->
          <!--            size="mini"-->
          <!--            type="text"-->
          <!--            icon="el-icon-s-operation"-->
          <!--            @click="handleHistoryVersions(scope.row)"-->
          <!--          >历史版本-->
          <!--          </el-button>-->
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
            <el-form-item label="文件标题" prop="regulationsTitle">
              <el-input
                v-model="form.regulationsTitle"
                placeholder="请输入制度标题"
              />
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="文件创建日期" prop="createDate">
              <el-date-picker
                clearable
                v-model="form.createDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择制度创建日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <!--          <el-col :span="12">-->
          <!--            <el-form-item label="适用范围" prop="useScope">-->
          <!--              <el-input v-model="form.useScope" placeholder="请输入适用范围"/>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
          <!--          <el-col :span='12'>-->
          <!--            <el-form-item label="生效日期" prop="effectiveDate">-->
          <!--              <el-date-picker clearable-->
          <!--                              v-model="form.effectiveDate"-->
          <!--                              type="date"-->
          <!--                              value-format="yyyy-MM-dd"-->
          <!--                              placeholder="请选择生效日期">-->
          <!--              </el-date-picker>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
        </el-row>

        <el-row>
          <el-col :span="12">
            <el-form-item label="文件大小" prop="fileSize">
              <el-input
                v-model="form.fileSize"
                placeholder="请输入文件大小"
                :disabled="true"
              />
            </el-form-item>
          </el-col>
          <!--          <el-col :span='12'>-->
          <!--            <el-form-item label="制度使用状态" prop="useState">-->
          <!--              <el-select v-model="form.useState" placeholder="请选择制度使用状态">-->
          <!--                <el-option label="正常" value="正常"></el-option>-->
          <!--                <el-option label="停用" value="停用"></el-option>-->
          <!--              </el-select>-->
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
            <el-form-item label="文件标题" prop="regulationsTitle">
              <el-input
                v-model="form.regulationsTitle"
                placeholder="请输入制度标题"
              />
            </el-form-item>
          </el-col>
          <!--          <el-col :span="12">-->
          <!--            <el-form-item label="适用范围" prop="useScope">-->
          <!--              <el-input v-model="form.useScope" placeholder="请输入适用范围"/>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
        </el-row>
        <el-row>
          <el-col :span="12">
            <!--            <el-form-item label="生效日期" prop="effectiveDate">-->
            <!--              <el-date-picker clearable-->
            <!--                              v-model="form.effectiveDate"-->
            <!--                              type="date"-->
            <!--                              value-format="yyyy-MM-dd"-->
            <!--                              placeholder="请选择生效日期">-->
            <!--              </el-date-picker>-->
            <!--            </el-form-item>-->
          </el-col>
          <el-col :span="12">
            <el-form-item label="文件创建日期" prop="createDate">
              <el-date-picker
                clearable
                v-model="form.createDate"
                type="date"
                value-format="yyyy-MM-dd"
                placeholder="请选择制度创建日期"
              >
              </el-date-picker>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <!--          <el-col :span='12'>-->
          <!--            <el-form-item label="制度使用状态" prop="useState">-->
          <!--              <el-select v-model="form.useState" placeholder="请选择制度使用状态">-->
          <!--                <el-option label="正常" value="正常"></el-option>-->
          <!--                <el-option label="停用" value="停用"></el-option>-->
          <!--              </el-select>-->
          <!--            </el-form-item>-->
          <!--          </el-col>-->
        </el-row>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="modifySubmitForm">确 定</el-button>
        <el-button @click="modifyCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>

  <!--    &lt;!&ndash; 添加或修改申报材料统计对话框 &ndash;&gt;-->
  <!--    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>-->
  <!--      <el-form ref="form" :model="form" :rules="rules" label-width="80px">-->
  <!--        <el-form-item label="制度标题" prop="regulationsTitle">-->
  <!--          <el-input v-model="form.regulationsTitle" placeholder="请输入制度标题" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="上传日期" prop="uploadDate">-->
  <!--          <el-date-picker clearable-->
  <!--            v-model="form.uploadDate"-->
  <!--            type="date"-->
  <!--            value-format="yyyy-MM-dd"-->
  <!--            placeholder="请选择上传日期">-->
  <!--          </el-date-picker>-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="文件名称" prop="fileName">-->
  <!--          <el-input v-model="form.fileName" placeholder="请输入文件名称" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="文件路径" prop="filePath">-->
  <!--          <el-input v-model="form.filePath" placeholder="请输入文件路径" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="文件大小" prop="fileSize">-->
  <!--          <el-input v-model="form.fileSize" placeholder="请输入文件大小" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="制度创建日期" prop="createDate">-->
  <!--          <el-date-picker clearable-->
  <!--            v-model="form.createDate"-->
  <!--            type="date"-->
  <!--            value-format="yyyy-MM-dd"-->
  <!--            placeholder="请选择制度创建日期">-->
  <!--          </el-date-picker>-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="制度上传人" prop="uploadUsername">-->
  <!--          <el-input v-model="form.uploadUsername" placeholder="请输入制度上传人" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="制度使用状态" prop="useState">-->
  <!--          <el-input v-model="form.useState" placeholder="请输入制度使用状态" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="制度所属科室" prop="departmentCategory">-->
  <!--          <el-input v-model="form.departmentCategory" placeholder="请输入制度所属科室" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="制度标签名称" prop="fileTag">-->
  <!--          <el-input v-model="form.fileTag" placeholder="请输入制度标签名称" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="历史版本制度" prop="oldRegulationsId">-->
  <!--          <el-input v-model="form.oldRegulationsId" placeholder="请输入历史版本制度" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="修订时间" prop="revisionDate">-->
  <!--          <el-date-picker clearable-->
  <!--            v-model="form.revisionDate"-->
  <!--            type="date"-->
  <!--            value-format="yyyy-MM-dd"-->
  <!--            placeholder="请选择修订时间">-->
  <!--          </el-date-picker>-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="修订内容">-->
  <!--          <editor v-model="form.revisionContent" :min-height="192"/>-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="修订人" prop="reviser">-->
  <!--          <el-input v-model="form.reviser" placeholder="请输入修订人" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="标志位(是否为最新文件)" prop="newFlag">-->
  <!--          <el-input v-model="form.newFlag" placeholder="请输入标志位(是否为最新文件)" />-->
  <!--        </el-form-item>-->
  <!--        <el-form-item label="该制度下一制度id" prop="newRegulationsId">-->
  <!--          <el-input v-model="form.newRegulationsId" placeholder="请输入该制度下一制度id" />-->
  <!--        </el-form-item>-->
  <!--      </el-form>-->
  <!--      <div slot="footer" class="dialog-footer">-->
  <!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
  <!--        <el-button @click="cancel">取 消</el-button>-->
  <!--      </div>-->
  <!--    </el-dialog>-->
  <!--  </div>-->
</template>

<script>
import {
  listRetention,
  getRetention,
  delRetention,
  addRetention,
  updateRetention,
} from "@/api/Technology/retention";
import { getUserProfile } from "@/api/system/user";
import { getDept } from "@/api/system/dept";
import { getToken } from "@/utils/auth";
import { word2Pdf } from "@/api/file/filemanagement";

export default {
  name: "Retention",
  data() {
    return {
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
      // 申报材料统计表格数据
      retentionList: [],
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
        regulationsTitle: null,
        uploadDate: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        createDate: null,
        uploadUsername: null,
        // useState: null,
        departmentCategory: null,
        fileTag: null,
        oldRegulationsId: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        newRegulationsId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        regulationsTitle: [
          { required: true, message: "制度标题不能为空", trigger: "blur" },
        ],
        createDate: [
          { required: true, message: "制度创建日期不能为空", trigger: "blur" },
        ],
        uploadUsername: [
          { required: true, message: "制度上传人不能为空", trigger: "blur" },
        ],
        // useState: [
        //   { required: true, message: "制度使用状态不能为空", trigger: "blur" }
        // ],
        departmentCategory: [
          { required: true, message: "制度所属科室不能为空", trigger: "blur" },
        ],
      },
      uploadVisible: false,

      ruleForm: {
        remark: "",
      },
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
    // /** 文件下载 */
    // downloadFile(url) {
    //   fetch(url)
    //     .then((response) => response.blob())
    //     .then((blob) => {
    //       const downloadUrl = window.URL.createObjectURL(blob);
    //       const link = document.createElement("a");
    //       link.href = downloadUrl;
    //       link.setAttribute(
    //         "download",
    //         decodeURIComponent(url.split("/").pop())
    //       ); // 解码文件名
    //       document.body.appendChild(link);
    //       link.click();
    //       document.body.removeChild(link);
    //       window.URL.revokeObjectURL(downloadUrl);
    //     })
    //     .catch((error) => console.error("Download error:", error));
    // },
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
    /** 查询申报材料统计列表 */
    getList() {
      this.loading = true;
      listRetention(this.queryParams).then((response) => {
        this.retentionList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 文件上传取消按钮
    uploadCancel() {
      this.fileUploadDialogVisible = false;
      this.reset();
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
    },
    // 取消按钮
    // cancel() {
    //   this.open = false;
    //   this.reset();
    // },
    // 表单重置
    reset() {
      this.form = {
        tfemId: null,
        regulationsTitle: null,
        uploadDate: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        createDate: null,
        uploadUsername: null,
        // useState: null,
        departmentCategory: null,
        fileTag: null,
        oldRegulationsId: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        newRegulationsId: null,
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
      this.ids = selection.map((item) => item.tfemId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 上传制度文件 */
    handleUpload() {
      this.reset();
      this.fileUploadDialogVisible = true;
      this.title = "上传文件";
    },
    /** 新增按钮操作 */
    // handleAdd() {
    //   this.reset();
    //   this.open = true;
    //   this.title = "添加申报材料统计";
    // },
    /** 更新文件 */
    handleUpdate(row) {
      this.reset();
      const tfemId = row.tfemId || this.ids;
      getRetention(tfemId).then((response) => {
        this.form = response.data;
        // this.form.newFlag = 0;
        console.log("更新文件=>", this.form);
        this.fileUpdateDialogVisible = true;
        this.title = "更新制度文件";
      });
    },
    /** 修改按钮操作 */
    handleModify(row) {
      this.reset();
      const tfemId = row.tfemId || this.ids;
      getRetention(tfemId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改申报材料统计";
      });
    },
    /** 历史版本管理 */
    handleHistoryVersions(row) {
      const tfemId = row.tfemId;
      this.$router.push("/Technology/retention/historyVersions/" + tfemId);
    },
    /** 上传文件提交按钮 */
    uploadSubmitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          this.form.newFlag = 1;
          addRetention(this.form).then((response) => {
            this.$modal.msgSuccess("上传成功");
            this.fileUploadDialogVisible = false;
            this.getList();
            console.log("上传文件提交按钮=>", this.form);
          });
        }
      });
    },
    /** 修改文件提交按钮 */
    modifySubmitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.tfemId != null) {
            updateRetention(this.form).then((response) => {
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
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.tfemId != null) {
            console.log("newform=>", this.form);
            this.form.oldRegulationsId = this.form.tfemId;
            addRetention(this.form).then((response) => {
              const newId = response.data;
              this.$modal.msgSuccess("更新成功");
              this.fileUpdateDialogVisible = false;
              this.form.newRegulationsId = null;
              //更新历史版本制度
              getRetention(this.form.oldRegulationsId).then((response) => {
                const lastForm = response.data;
                lastForm.newFlag = 0;
                lastForm.newRegulationsId = newId;
                console.log("上一表单=>", lastForm);
                updateRetention(lastForm).then((response) => {});
                // updateFilemanagement(this.form).then(response => {
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
    },
    /** 提交按钮 */
    // submitForm() {
    //   this.$refs["form"].validate(valid => {
    //     if (valid) {
    //       if (this.form.tfemId != null) {
    //         updateRetention(this.form).then(response => {
    //           this.$modal.msgSuccess("修改成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       } else {
    //         addRetention(this.form).then(response => {
    //           this.$modal.msgSuccess("新增成功");
    //           this.open = false;
    //           this.getList();
    //         });
    //       }
    //     }
    //   });
    // },
    /** 删除按钮操作 */
    handleDelete(row) {
      const tfemIds = row.tfemId || this.ids;
      this.$modal
        .confirm('是否确认删除申报材料统计编号为"' + tfemIds + '"的数据项？')
        .then(function () {
          return delRetention(tfemIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
      if (row.oldRegulationsId != null) {
        getRetention(row.oldRegulationsId).then((response) => {
          console.log("当前表单3=>", this.form);
          const lastForm = response.data;
          console.log("上一表单=>", lastForm);
          lastForm.newFlag = 1;
          console.log("上一表单=>", lastForm);
          updateRetention(lastForm).then((response) => {});
        });
        this.getList();
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "Technology/retention/export",
        {
          ...this.queryParams,
        },
        `retention_${new Date().getTime()}.xlsx`
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
      console.log(this.retentionList);
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
      const isFileNameDuplicate = this.retentionList.some(
        (item) => item.fileName === uploadedFileName
      );
      console.log("发起请求检查文件名是否存在于数据库中", isFileNameDuplicate);

      if (isFileNameDuplicate) {
        // 如果文件名重复，弹出警告框
        this.$modal.msgError(
          "同名文件已存在，如需上传该制度新版本，请到“更新”处上传！"
        );
        console.log("同名文件已存在，如需上传该制度新版本，请到“更新”处上传！");
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
    //文件预览
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
          console.log("fileType2222:", fileType);
          const pdfFilePath = this.convertToPdfPath(filePath);
          console.log("filePath:", filePath);
          console.log("pdfFilePath:", pdfFilePath);
          word2Pdf(filePath, pdfFilePath).then((response) => {});
          window.open(pdfFilePath, "_blank");
          break;
        // this.convertToPdfPath(filePath).then(pdfFilePath => {
        //   console.log("pdfFilePath:",pdfFilePath);
        //   window.open(pdfFilePath, '_blank');
        // });
        // break;
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

        return pdfFilePath; // 显式地返回名为 pdfFilePath 的值
      } else {
        // 文件路径中没有点，无法更改后缀
        throw new IllegalArgumentException("文件路径无效：" + wordFilePath);
      }
    },
    // convertToPdfPath(wordFilePath) {
    //   return new Promise((resolve, reject) => {
    //     // 使用后端接口将Word转换为PDF
    //     word2Pdf(wordFilePath).then(response => {
    //       resolve(response.data.pdfFilePath);
    //     }).catch(error => {
    //       reject(error);
    //     });
    //   });
    // },
  },
};
</script>

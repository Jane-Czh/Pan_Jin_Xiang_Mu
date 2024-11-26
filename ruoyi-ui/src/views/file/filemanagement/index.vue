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
<!--      <el-form-item label="制度范围" prop="useScope">-->
<!--        <el-input-->
<!--          v-model="queryParams.useScope"-->
<!--          placeholder="请选择制度范围"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
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
      <el-form-item label="制度名称" prop="regulationsTitle">
        <el-input
          v-model="queryParams.regulationsTitle"
          placeholder="请输入制度名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
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
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleUpload"
          v-hasPermi="['file:filemanagement:add']"
        >上传
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleModify"
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
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          @click="handleRecycleBin"
          v-hasPermi="['file:filemanagement:remove']"
        >回收站
        </el-button>
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
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportAllAdd"
          v-hasPermi="['file:filemanagement:export']"
        >新增台账导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="exportAllHistory"
          v-hasPermi="['file:filemanagement:export']"
        >历史台账导出</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="exportAllCombined"-->
<!--          v-hasPermi="['file:filemanagement:export']"-->
<!--        >制度表单导出</el-button>-->
<!--      </el-col>-->

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
            :disabled="thisDept !== scope.row.mainResponsibleDepartment && thisDept !== '研发' && thisDept !== '企管' && thisDept !== '总部'"
            @click.prevent="downloadFile(scope.row.wordPath)"
            :style="{ color: (thisDept === scope.row.mainResponsibleDepartment || thisDept === '研发' || thisDept === '企管' || thisDept === '总部') ? '#6495ED' : '#CCC', pointerEvents: (thisDept === scope.row.mainResponsibleDepartment || thisDept === '研发' || thisDept === '企管' || thisDept === '总部') ? 'auto' : 'none' }"
          >
            点击下载
          </a>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">

<!--          <template v-if="this.thisDept == '研发' || this.thisDept == this.form.mainResponsibleDepartment">-->
<!--          :disabled="this.thisDept !== scope.row.mainResponsibleDepartment-->
            <el-button
              size="mini"
              type="text"
              icon="el-icon-upload"
              @click="handleModify(scope.row)"
              v-hasPermi="['file:filemanagement:edit']"
              :disabled="thisDept !== scope.row.departmentCategory && ![ '研发', '企管', '总部' ].includes(thisDept)"
            >
              更新
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['file:filemanagement:remove']"
              :disabled="thisDept !== scope.row.departmentCategory && ![ '研发', '企管', '总部' ].includes(thisDept)"
            >
              删除
            </el-button>
<!--          </template>-->
<!--            <el-button-->
<!--              size="mini"-->
<!--              type="text"-->
<!--              icon="el-icon-edit"-->
<!--              @click="handleModify(scope.row)"-->
<!--              v-hasPermi="['file:filemanagement:edit']"-->
<!--            >-->
<!--             更新-->
<!--            </el-button>-->
<!--            <el-button-->
<!--              size="mini"-->
<!--              type="text"-->
<!--              icon="el-icon-delete"-->
<!--              @click="handleDelete(scope.row)"-->
<!--              v-hasPermi="['file:filemanagement:remove']"-->
<!--            >-->
<!--              删除-->
<!--            </el-button>-->
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
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-operation"
            @click="handleHistoryVersions(scope.row)"
          >历史版本
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-s-operation"
            @click="handleRevisionFrequency(scope.row)"
          >制度修订频率
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
    <el-dialog :title="title" :visible.sync="fileUploadDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
              <div style="display: flex; justify-content: center;height: 100px;">
                <!--pdf文件上传-->
                <el-upload
                  class="upload-file-uploader"
                  :action="uploadFileUrl"
                  :headers="headers"
                  :before-upload="pdfHandleBeforeUpload"
                  :on-change="pdfHandleFileChange"
                  :on-preview="handlePreview"
                  :on-remove="pdfHandleRemove"
                  :on-exceed="handleExceed"
                  :on-success="pdfHandleUploadSuccess"
                  :limit=limit
                  :file-list="pdfList"
                >
                  <el-button size="small" type="primary">pdf上传</el-button>
                </el-upload>
              </div>
          </el-col>
          <el-col :span="12">
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--word文件上传-->
              <el-upload
                class="upload-file-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :before-upload="wordHandleBeforeUpload"
                :on-change="wordHandleFileChange"
                :on-preview="handlePreview"
                :on-remove="wordHandleRemove"
                :on-exceed="handleExceed"
                :on-success="wordHandleUploadSuccess"
                :limit=limit
                :file-list="wordList"
              >
                <el-button size="small" type="primary">word上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>

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
          <el-col  :span="12">
            <el-form-item label="关联表单">
              <el-select
                v-model="form.formId"
                multiple
                placeholder="请选择关联表单"
                filterable
                clearable
              >
                <el-option
                  v-for="item in formmanagementList"
                  :key="item.formId"
                  :label="item.formTitle"
                  :value="item.formTitle"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
            <el-form-item label="是否加密" prop="encryption">
              <el-radio v-model="form.encryption" label="1">是</el-radio>
              <el-radio v-model="form.encryption" label="0">否</el-radio>
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
        <el-button type="primary" @click="uploadSubmitForm">确 定</el-button>
        <el-button @click="uploadCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改文件对话框 -->
    <el-dialog :title="title" :visible.sync="fileModifyDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="12">
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--pdf文件上传-->
              <el-upload
                class="upload-file-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :before-upload="pdfHandleBeforeUpload"
                :on-change="pdfHandleFileChange"
                :on-preview="handlePreview"
                :on-remove="pdfHandleRemove"
                :on-exceed="handleExceed"
                :on-success="pdfHandleUploadSuccess"
                :limit=limit
                :file-list="pdfList"
              >
                <el-button size="small" type="primary">pdf上传</el-button>
              </el-upload>
            </div>
          </el-col>
          <el-col :span="12">
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--word文件上传-->
              <el-upload
                class="upload-file-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :before-upload="wordHandleBeforeUpload"
                :on-change="wordHandleFileChange"
                :on-preview="handlePreview"
                :on-remove="wordHandleRemove"
                :on-exceed="handleExceed"
                :on-success="wordHandleUploadSuccess"
                :limit=limit
                :file-list="wordList"
              >
                <el-button size="small" type="primary">word上传</el-button>
              </el-upload>
            </div>
          </el-col>
        </el-row>
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
            <el-form-item label="关联表单">
              <el-select
                v-model="form.formId"
                placeholder="请选择关联表单"
                multiple
                filterable
                clearable
              >
                <el-option
                  v-for="item in formmanagementList"
                  :key="item.formId"
                  :label="item.formTitle"
                  :value="item.formTitle"
                />
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
            <el-form-item label="是否加密" prop="encryption">
              <el-radio v-model="form.encryption" :label="1">是</el-radio>
              <el-radio v-model="form.encryption" :label="0">否</el-radio>
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
  updateFilemanagement, listAllFilemanagement, listHistoryFilemanagement, listAddFilemanagement
} from "@/api/file/filemanagement";
  import {
    listProject,
  } from "@/api/system/project";
  import {listNode,getNode} from "@/api/file/flownode";
  import {getUserProfile02} from '@/api/file/filemanagement'
  import {getDept02} from '@/api/file/filemanagement'
  import {getToken} from "@/utils/auth"
  import {word2Pdf} from "../../../api/file/filemanagement";
  import mammoth from 'mammoth';
  import {listDept02} from "../../../api/file/filemanagement";
  //业务模块api，
  import {listModules, listModuless} from "@/api/function/modules";
  //细分业务api
  import {listBusinesses, listBusinessess} from "@/api/function/businesses";
  //导出总台账excel功能
  import * as XLSX from "xlsx";
  import { saveAs } from "file-saver";
  import {Loading} from "element-ui";
  import { listDept } from "@/api/system/project";
import {listFormfilemanagement, listFormfilemanagement3, updateFormfilemanagement} from "@/api/file/formfilemanagement";  //获取部门列表
  import { mapActions } from 'vuex';


  export default {
    name: "Filemanagement",
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
        projectNames:[], //关联流程名称列表
        projectNamesString : "",  //关联流程名称列表（用”，“拼接）
        allProjectList:[],  //所有制度关联的流程名称列表
        //当前账号的dept
        thisDept: null,
        roles: [], //当前账号角色信息
        //文件上传绑定的部门
        fileDept: null,
        number: 0,
        uploadList: [],
        fileList: [],
        pdfList: [],  //上传的pdf文件list
        wordList: [], //上传的word文件list
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
        // 制度文件管理表格数据
        filemanagementList: [],
        addFilemanagementList: [], // 新增文件管理表格数据
        historyFilemanagementList: [], //历史版本制度文件管理表格数据
        // 表单文件管理表格数据
        formmanagementList: [],
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
        // 表单查询参数
        formQueryParams: {
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
          subBusinesses: null
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
            {required: true, message: "制度范围不能为空", trigger: "blur"}
          ],
          createDate: [
            {required: true, message: "制度发布日期不能为空", trigger: "blur"}
          ],
          effectiveDate: [
            {required: true, message: "制度实施日期不能为空", trigger: "blur"}
          ],
          departmentCategory: [
            {required: true, message: "制度所属科室不能为空", trigger: "blur"}
          ],
          mainResponsibleDepartment: [
            {required: true, message: "制度主责部门不能为空", trigger: "blur"}
          ],
          businesses: [
            {required: true, message: "业务模块不能为空", trigger: "blur"}
          ],
          subBusinesses: [
            {required: true, message: "细分业务不能为空", trigger: "blur"}
          ],
          classificationOfSpecialties: [
            {required: true, message: "专业分类不能为空", trigger: "blur"}
          ],
          regulationLeval: [
            {required: true, message: "制度等级不能为空", trigger: "blur"}
          ],
          regulationNumber: [
            {required: true, message: "制度编号不能为空", trigger: "blur"}
          ],
          encryption: [
            {required: true, message: "是否加密选项不能为空", trigger: "blur"}
          ]

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
            this.pdfList = [];
            this.wordList = [];
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
    async created() {
     await getUserProfile02().then(response => {
        // 处理成功的情况
        console.log('成功获取用户信息response.data====>', response.data);
        console.log('成功获取用户信息response.data.dept.deptName====>', response.data.dept.deptName);
        // const userInfo =; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
        this.thisDept =  response.data.dept.deptName;
        this.roles = response.data.roles;
        console.log("this.roles===>",this.roles);
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
        console.log("刷新页面");
        console.log("thisDept=>",this.thisDept);
        // 如果部门是研发或总部，则不添加departmentCategory到queryParams
        // if (!['研发', '总部'].includes(this.thisDept)) {
        //   this.queryParams.departmentCategory = this.thisDept;
        // }
        if (this.roles.some(role => role.roleName === '制度加密')) {
          listFilemanagement(this.queryParams).then(response => {
            console.log("response111:：", response);
            this.filemanagementList = response.rows;
            console.log("filemanagementList=>", this.filemanagementList);
            this.total = response.total;
            this.loading = false;
          });
        } else {
          const query = {
            ...this.queryParams,
            encryption: 0
          }
          listFilemanagement(query).then(response => {
            console.log("response222:：", response);
            this.filemanagementList = response.rows;
            console.log("filemanagementList=>", this.filemanagementList);
            this.total = response.total;
            this.loading = false;
          });
        }
        listFormfilemanagement3(this.formQueryParams).then(response => {
          console.log("response:：", response);
          this.formmanagementList = response.rows;
          console.log("formmanagementList=>", this.formmanagementList);

          // // 获取filemanagementList中所有formTitle
          // const formTitlesToExclude = this.filemanagementList.map(file => file.formId);
          // console.log("formTitlesToExclude=>", formTitlesToExclude);
          //
          // // 过滤formmanagementList，排除formTitlesToExclude中的记录
          // this.formmanagementList = this.formmanagementList.filter(form => !formTitlesToExclude.includes(form.formTitle));

          console.log("过滤后的formmanagementList=>", this.formmanagementList);
        });


        console.log("this.queryParams====>",this.queryParams);
        //获取历史制度
        listHistoryFilemanagement(this.queryParams).then(response => {
          console.log("response:：", response);
          this.historyFilemanagementList = response;
          console.log("historyFilemanagementList=>", this.historyFilemanagementList);
        });
        //获取新增制度
        listAddFilemanagement(this.queryParams).then(response => {
          console.log("response:：", response);
          this.addFilemanagementList = response;
          console.log("this.addFilemanagementList=>", this.addFilemanagementList);
        });

      },

      // 查询绑定的流程信息
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
          console.log("projectNamesString=>", this.projectNamesString);
          // 将projectNamesString插入到allProjectList中
          this.allProjectList.push(this.projectNamesString);
          console.log("this.allProjectList====>",this.allProjectList);
          // 使用事件总线发送数据
        });
      },

      // 文件上传取消按钮
      uploadCancel() {
        this.fileUploadDialogVisible = false;
        this.reset();
        this.pdfList = [];
        this.wordList = [];
      },
      // 文件修改取消按钮
      modifyCancel() {
        this.fileModifyDialogVisible = false;
        this.reset();
        this.pdfList = [];
        this.wordList = [];
      },
      // 文件更新取消按钮
      updateCancel() {
        this.fileUpdateDialogVisible = false;
        this.reset();
        this.pdfList = [];
        this.wordList = [];
      },
      // 表单重置
      reset() {
        this.form = {
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
          subBusinesses: null
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
        this.single = selection.length !== 1
        this.multiple = !selection.length
      },
      /** 上传制度文件 */
      handleUpload() {
        this.reset();
        this.fileUploadDialogVisible = true;
        this.title = "上传制度文件";
      },
      /** 修改（更新）制度文件 */
      handleModify(row) {
        this.reset();
        const regulationsId = row.regulationsId || this.ids
        getFilemanagement(regulationsId).then(response => {
          this.form = response.data;
          console.log("responseqqqqqqqqqqqqqqqqqqqqqqqqq:", response);
          this.fileModifyDialogVisible = true;
          this.title = "更新制度文件";
        });
      },
      /** 更新文件 */
      handleUpdate(row) {
        this.reset();
        const regulationsId = row.regulationsId || this.ids;
        getFilemanagement(regulationsId).then(response => {
          this.form = response.data;
          // this.form.newFlag = 0;
          console.log("更新文件=>", this.form);
          this.fileUpdateDialogVisible = true;
          this.title = "更新制度文件";
        });
      },
      /** 历史版本管理 */
      handleHistoryVersions(row) {
        const regulationsId = row.regulationsId;
        this.$router.push("/file/filemanagement/historyVersions/" + regulationsId);
      },
      /** 回收站路由跳转 */
      handleRecycleBin(row) {
        this.$router.push("/file/filemanagement/recycleBin");
      },
      /** 制度修改频率 */
      handleRevisionFrequency(row) {
        const regulationsId = row.regulationsId;
        this.$router.push("/file/filemanagement/revisionFrequency/" + regulationsId);
      },
      /** 上传文件提交按钮 */
      uploadSubmitForm() {
        // 首先检查 fileList 是否为空
        if (!this.form.wordPath && !this.form.pdfPath) {
          this.$message.error("请上传文件！");
          return;
        }
        this.$refs["form"].validate(valid => {
          if (valid) {
            // 将 form.formId 数组转换为字符串
            this.form.formId = this.form.formId.join(',');
            console.log("this.form.formId=>", this.form.formId);
            this.form.newFlag = 1;
            this.form.addFlag = 1;
            this.form.revisionContent = "新增";
            addFilemanagement(this.form).then(response => {
              this.$modal.msgSuccess("上传成功");
              this.fileUploadDialogVisible = false;
              this.getList();
              console.log("上传文件提交按钮=>", this.form);
            });
            // 将字符串拆分成数组
            const formTitles = this.form.formId.split(',');
            if (this.form.formId) {
              //将制度文件绑定到表单文件
              formTitles.forEach(formTitle => {
                const queryParams = {
                  formTitle: formTitle
                };
                listFormfilemanagement(queryParams).then(response => {
                  const resultForm = response.rows;
                  console.log("resultForm=>", resultForm);
                  // 将 this.form.regulationsTitle 添加到 resultForm[0].regulationId 中
                  const existingRegulations = resultForm[0].regulationId ? resultForm[0].regulationId.split(',') : [];
                  if (!existingRegulations.includes(this.form.regulationsTitle)) {
                    existingRegulations.push(this.form.regulationsTitle);
                  }
                  resultForm[0].regulationId = existingRegulations.join(',');
                  return updateFormfilemanagement(resultForm[0]);
                });
              })
            }
          }
        });
        this.pdfList = [];
        this.wordList = [];
      },
      /** 修改（更新）文件提交按钮 */
      modifySubmitForm(file, fileList) {
        console.log("Before validate - fileList:", this.fileList);
        console.log("file=======>:", file);
        this.$refs["form"].validate(async valid => {
          if (valid) {
            if (this.form.regulationsId != null) {
              const thisId = this.form.regulationsId;
              console.log("thisId=>", thisId);
              console.log("newform=>", this.form);
              this.form.oldRegulationsId = this.form.regulationsId;
              this.form.addFlag = 0;
              this.form.formId = this.form.formId.join(','); // 将字符串拆分成数组

              try {
                const response = await addFilemanagement(this.form);
                const newId = response.data;
                this.$modal.msgSuccess("更新成功");
                this.fileModifyDialogVisible = false;
                this.form.newRegulationsId = null;

                // 更新历史版本制度
                const lastFormResponse = await getFilemanagement(this.form.oldRegulationsId);
                const lastForm = lastFormResponse.data;
                lastForm.newFlag = 0;
                lastForm.newRegulationsId = newId;
                lastForm.revisionContent = "更新";
                console.log("上一表单=>", lastForm);

                // 将制度文件解绑到表单文件
                const formTitles = lastForm.formId.split(',');
                const unbindPromises = formTitles.map(formTitle => {
                  const queryParams = { formTitle };
                  return listFormfilemanagement(queryParams).then(response => {
                    const resultForm = response.rows;
                    console.log("resultForm=>", resultForm);
                    const existingRegulations = resultForm[0].regulationId ? resultForm[0].regulationId.split(',') : [];
                    const updatedRegulations = existingRegulations.filter(regulation => regulation !== lastForm.regulationsTitle);
                    resultForm[0].regulationId = updatedRegulations.join(',');
                    console.log("Updated regulationId=>", resultForm[0].regulationId);
                    return updateFormfilemanagement(resultForm[0]);
                  });
                });

                await Promise.all(unbindPromises);
                await updateFilemanagement(lastForm);
                this.getList();

                // 将制度文件绑定到表单文件
                const bindPromises = this.form.formId.split(',').map(formTitle => {
                  const queryParams = { formTitle };
                  return listFormfilemanagement(queryParams).then(response => {
                    const resultForm = response.rows;
                    console.log("更新的resultForm=>", resultForm);
                    const existingRegulations = resultForm[0].regulationId ? resultForm[0].regulationId.split(',') : [];
                    if (!existingRegulations.includes(this.form.regulationsTitle)) {
                      existingRegulations.push(this.form.regulationsTitle);
                      console.log("existingRegulations=>", existingRegulations);
                    }
                    resultForm[0].regulationId = existingRegulations.join(',');
                    console.log("resultForm[0]=>", resultForm[0]);
                    return updateFormfilemanagement(resultForm[0]);
                  });
                });

                await Promise.all(bindPromises);
              } catch (error) {
                console.error('处理过程中发生错误', error);
                this.$modal.msgError("更新失败");
              }
            }
          }
        });

        this.pdfList = [];
        this.wordList = [];
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        console.log("当前表单1=>", row);
        // 将id或ids统一转换为数组
        const regulationsIds = [].concat(row.regulationsId || this.ids);
        console.log("regulationsIds=>", regulationsIds);

        // 遍历所有需要删除的ID
        const permissionCheckPromises = regulationsIds.map(id => {
          return getFilemanagement(id).then(response => {
            const thisForm = response.data;
            console.log("response------>:", thisForm);

            // 检查权限，确保 this.thisDept 与表单的 mainResponsibleDepartment 匹配
            if (this.thisDept !== thisForm.departmentCategory  && ![ '研发', '企管', '总部' ].includes(this.thisDept)) {
              this.$modal.msgError('没有权限删除该制度!');
              throw new Error('没有权限删除');
            }

            return thisForm;
          });
        });

        // 等待权限检查完成
        Promise.all(permissionCheckPromises)
          .then((forms) => {
            return this.$modal.confirm('是否移入回收站？').then(() => {
              // 遍历所有需要删除的ID，并处理表单更新逻辑
              const updatePromises = forms.map(thisForm => {
                // 将当前表单的 newFlag 设置为 2（表示逻辑删除）
                thisForm.newFlag = 2;
                thisForm.revisionContent = "删除";
                // thisForm.formId = '';
                console.log("更新表单 newFlag 为 2 =>", thisForm);

                // 将字符串拆分成数组
                const formTitles = thisForm.formId.split(',');

                // 将制度文件解绑到表单文件
                const unbindPromises = formTitles.map(formTitle => {
                  const queryParams = {
                    formTitle: formTitle
                  };
                  return listFormfilemanagement(queryParams).then(response => {
                    const resultForm = response.rows;
                    console.log("resultForm=>", resultForm);
                    const existingRegulations = resultForm[0].regulationId ? resultForm[0].regulationId.split(',') : [];
                    const updatedRegulations = existingRegulations.filter(regulation => regulation !== thisForm.regulationsTitle);
                    resultForm[0].regulationId = updatedRegulations.join(',');
                    console.log("Updated regulationId=>", resultForm[0].regulationId);
                    return updateFormfilemanagement(resultForm[0]);
                  }).catch(error => {
                    console.error("解绑表单文件失败", error);
                    throw error; // 重新抛出错误以便在外部捕获
                  });
                });

                // 等待所有解绑操作完成
                return Promise.all(unbindPromises).then(() => {
                  thisForm.formId = '';
                  // 如果存在旧表单，处理旧表单
                  if (thisForm.oldRegulationsId != null) {
                    return getFilemanagement(thisForm.oldRegulationsId).then(response => {
                      const lastForm = response.data;
                      lastForm.newFlag = 1; // 将旧表单标记为未删除的状态
                      console.log("更新旧表单 newFlag 为 1 =>", lastForm);

                      // 先更新当前表单，再更新旧表单
                      return updateFilemanagement(thisForm).then(() => {
                        return updateFilemanagement(lastForm);
                      });
                    }).catch(error => {
                      console.error("获取旧表单失败", error);
                      throw error; // 重新抛出错误以便在外部捕获
                    });
                  } else {
                    // 如果没有旧表单，直接更新当前表单
                    return updateFilemanagement(thisForm);
                  }
                });
              });

              // 等待所有更新操作完成后执行后续逻辑
              return Promise.all(updatePromises);
            });
          })
          .then(() => {
            this.$modal.msgSuccess("移除成功");
            this.getList(); // 刷新列表
            console.log("删除文件刷新");
          })
          .catch((error) => {
            if (error.message !== '没有权限删除') {
              console.error("删除操作失败", error);
            }
          });
      },

      /** 导出按钮操作 */
      handleExport() {
        this.download('file/filemanagement/export', {
          ...this.exportQueryParams,
          exportFields: this.exportList
        }, `制度文件管理_${new Date().getTime()}.xlsx`)
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
      // pdf文件上传前校检格式和大小
      pdfHandleBeforeUpload(file, pdfList) {
        console.log("handleBeforeUpload:pdf=====>", file);
        // 上传前校检文件格式
        const allowedTypes = ['application/pdf'];
        const isAllowedType = allowedTypes.includes(file.type);
        if (!isAllowedType) {
          this.$message.error('文件类型不匹配，请重新上传pdf文件');
        }
        return isAllowedType;
      },
      // word文件上传前校检格式和大小
      wordHandleBeforeUpload(file, wordList) {
        console.log("handleBeforeUpload:word=====>", file);
        // 上传前校检文件格式
        const allowedTypes = ['application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document'];
        const isAllowedType = allowedTypes.includes(file.type);
        if (!isAllowedType) {
          this.$message.error('文件类型不匹配，请重新上传word文件');
        }
        return isAllowedType;
      },
      pdfHandleFileChange(file, pdfList) {
        this.pdfList = pdfList;
        console.log("file====>", file);
        console.log("pdfList====>", pdfList);
        this.form.pdfPath = this.path;
        this.form.pdfSize = file.size;
        this.form.fileName = file.name.substring(0, file.name.lastIndexOf('.'));
        const currentDate = new Date();
        this.form.uploadDate = currentDate.toISOString().split('T')[0];
        this.getUserInfo();
      },
      wordHandleFileChange(file, wordList) {
        this.wordList = wordList
        console.log("file====>", file);
        console.log("wordList====>", wordList);
        this.form.wordPath = this.path;
        this.form.wordSize = file.size;
        this.form.fileName = file.name.substring(0, file.name.lastIndexOf('.'));
        const currentDate = new Date();
        this.form.uploadDate = currentDate.toISOString().split('T')[0];
        this.getUserInfo();
      },
      readWordFile(file) {
        console.log("readWordFile=======>");
        const reader = new FileReader();
        reader.onload = (e) => {
          mammoth.extractRawText({arrayBuffer: e.target.result})
            .then((result) => {
              this.form.fileContent = result.value; // 将文件内容赋值给 fileContent
              console.log("fileContent=======>", this.form.fileContent);
              this.getUserInfo();
            })
            .catch((err) => {
              console.error('Error reading Word file:', err);
            });
        };
        reader.readAsArrayBuffer(file); // 读取文件内容为 ArrayBuffer
      },

      readPdfFile(file) {
        const reader = new FileReader();
        reader.onload = (e) => {
          const typedArray = new Uint8Array(e.target.result);
          pdfjsLib.getDocument({data: typedArray}).promise.then((pdf) => {
            let content = '';
            const numPages = pdf.numPages;

            const extractTextFromPage = (pageNum) => {
              return pdf.getPage(pageNum).then((page) => {
                return page.getTextContent().then((textContent) => {
                  const pageText = textContent.items.map(item => item.str).join(' ');
                  content += pageText + ' ';
                  if (pageNum < numPages) {
                    return extractTextFromPage(pageNum + 1);
                  } else {
                    this.form.fileContent = content.trim();
                    console.log("fileContent=======>", this.form.fileContent);
                    this.getUserInfo();
                  }
                });
              });
            };

            return extractTextFromPage(1);
          }).catch((err) => {
            console.error('Error reading PDF file:', err);
          });
        };
        reader.readAsArrayBuffer(file); // 读取文件内容为 ArrayBuffer
      },
      // pdf上传成功回调
      pdfHandleUploadSuccess(res, file) {
        const uploadedFile = file.raw; // 获取上传的文件对象
        console.log("上传成功回调file=====>", file);
        console.log("上传成功回调uploadedFile=====>", uploadedFile);
        this.path = res.url;
        const uploadedFileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.')); // 获取上传文件的文件名
        // 发起请求检查文件名是否存在于数据库中
        const isFileNameDuplicate = this.filemanagementList.some(item => item.fileName === uploadedFileName);
        console.log("发起请求检查文件名是否存在于数据库中", isFileNameDuplicate);

        if (isFileNameDuplicate) {
          // 如果文件名重复，弹出警告框
          this.$modal.msgWarning('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
          console.log('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
          return false; // 中断上传流程
        }
        if (res.code === 200) {
          console.log("上传成功回调");
          console.log(res);
          this.path = res.url;
          this.uploadList.push({name: res.fileName, url: res.fileName});
          this.pdfUploadedSuccessfully();
        } else {
          this.number--;
          this.$modal.closeLoading();
          this.$modal.msgError(res.msg);
          this.pdfHandleRemove(file);
          this.pdfUploadedSuccessfully();
        }
      },
      // word上传成功回调
      wordHandleUploadSuccess(res, file) {
        const uploadedFile = file.raw; // 获取上传的文件对象
        console.log("上传成功回调file=====>", file);
        console.log("上传成功回调uploadedFile=====>", uploadedFile);
        this.path = res.url;
        const uploadedFileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.')); // 获取上传文件的文件名
        // 发起请求检查文件名是否存在于数据库中
        const isFileNameDuplicate = this.filemanagementList.some(item => item.fileName === uploadedFileName);
        console.log("发起请求检查文件名是否存在于数据库中", isFileNameDuplicate);

        if (isFileNameDuplicate) {
          // 如果文件名重复，弹出警告框
          this.$modal.msgWarning('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
          console.log('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
          return false; // 中断上传流程
        }
        if (res.code === 200) {
          console.log("上传成功回调");
          console.log(res);
          this.path = res.url;
          this.uploadList.push({name: res.fileName, url: res.fileName});
          this.wordUploadedSuccessfully();
        } else {
          this.number--;
          this.$modal.closeLoading();
          this.$modal.msgError(res.msg);
          this.wordHandleRemove(file);
          this.wordUploadedSuccessfully();
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
          this.pdfHandleRemove(file);
          this.uploadedSuccessfully();
        }
      },
      // pdf上传结束处理
      pdfUploadedSuccessfully() {
        if (this.number > 0 && this.uploadList.length === this.number) {
          this.pdfList = this.pdfList.concat(this.uploadList);
          this.uploadList = [];
          this.number = 0;
          this.$emit("input", this.listToString(this.pdfList));
          this.$modal.closeLoading();
        }
      },
      // word上传结束处理
      wordUploadedSuccessfully() {
        if (this.number > 0 && this.uploadList.length === this.number) {
          this.wordList = this.wordList.concat(this.uploadList);
          this.uploadList = [];
          this.number = 0;
          this.$emit("input", this.listToString(this.wordList));
          this.$modal.closeLoading();
        }
      },
      //pdf移除
      pdfHandleRemove(file, pdfList) {
        // 清除对应字段
        if (file.name.endsWith('.pdf')) {
          this.form.pdfPath = '';
          this.form.pdfSize = '';
          this.form.departmentCategory = '';
          this.pdfList = pdfList;
        }
      },
      //word移除
      wordHandleRemove(file, wordList) {
        // 清除对应字段
        if (file.name.endsWith('.doc') || file.name.endsWith('.docx')) {
          this.form.wordPath = '';
          this.form.wordSize = '';
        }
        this.form.departmentCategory = '';
        this.wordList = wordList;
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
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
      getFileType(filePath) {
        // 获取文件名的后缀名
        const fileExtension = filePath.split('.').pop();
        console.log("fileExtension=>", fileExtension);

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
        if (pdfPath) {
          window.open(pdfPath, '_blank');
        } else {
          this.$modal.msgError('没有上传pdf文件，无法预览！');
        }
        // const fileType = this.getFileType(filePath);
        // console.log("filePath:",filePath);
        // console.log("fileType:",fileType);
        // switch (fileType) {
        //   case 'pdf':
        //     console.log("fileType1111:",fileType);
        //     window.open(filePath, '_blank');
        //     break;
        //   case 'word':
        //     const pdfFilePath = this.convertToPdfPath(filePath);
        //     console.log("filePath:",filePath);
        //     console.log("pdfFilePath:",pdfFilePath);
        //     word2Pdf(filePath,pdfFilePath).then(response => {
        //       window.open(pdfFilePath, '_blank');
        //     })
        //     break;
        // }
        // // 使用 window.open 方法打开一个新窗口，并将文件路径传递给该窗口

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
            await listModuless(this.moduleQueryParams).then((response) => {
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
        console.log("module===", module);
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
// 使用命名空间映射actions
      ...mapActions('exportData', ['updateExportedData']),
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
              制度范围 : regulation.useScope,
              制度编号 : regulation.regulationNumber,
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
              "制度总台账.xlsx"
            );

            // 提交数据到Vuex Store
            this.updateExportedData(data);


          })
          .finally(() => {
            loadingInstance.close();
          })
          .catch((error) => {
            console.error("导出失败:", error);
            loadingInstance.close();
          });

      },

      exportAllAdd(){
        const loadingInstance = Loading.service({
          lock: true,
          text: "正在导出，请稍后...",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)",
        });



        const promises = this.addFilemanagementList.map((regulation) => {
          return this.handleProjectDetails(regulation).then((projectNames) => {
            return {
              主责部门 : regulation.mainResponsibleDepartment,
              制度名称 : regulation.regulationsTitle,
              专业分类 : regulation.classificationOfSpecialties,
              制度范围 : regulation.useScope,
              制度编号 : regulation.regulationNumber,
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
              "制度总台账.xlsx"
            );

            // 提交数据到Vuex Store
            this.updateExportedData(data);


          })
          .finally(() => {
            loadingInstance.close();
          })
          .catch((error) => {
            console.error("导出失败:", error);
            loadingInstance.close();
          });

      },

      exportAllHistory(){
        const loadingInstance = Loading.service({
          lock: true,
          text: "正在导出，请稍后...",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)",
        });



        const promises = this.historyFilemanagementList.map((regulation) => {
          return this.handleProjectDetails(regulation).then((projectNames) => {
            return {
              主责部门 : regulation.mainResponsibleDepartment,
              制度名称 : regulation.regulationsTitle,
              专业分类 : regulation.classificationOfSpecialties,
              制度范围 : regulation.useScope,
              制度编号 : regulation.regulationNumber,
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
              "制度总台账.xlsx"
            );

            // 提交数据到Vuex Store
            this.updateExportedData(data);


          })
          .finally(() => {
            loadingInstance.close();
          })
          .catch((error) => {
            console.error("导出失败:", error);
            loadingInstance.close();
          });

      },

      // 合并导出制度文件和表单文件
      exportAllCombined() {
        const loadingInstance = Loading.service({
          lock: true,
          text: "正在导出，请稍后...",
          spinner: "el-icon-loading",
          background: "rgba(0, 0, 0, 0.7)",
        });
        // 制度文件导出数据
        const regulationPromises = this.filemanagementList.map((regulation) => {
          return this.handleProjectDetails(regulation).then(() => {
            return {
              主责部门: regulation.mainResponsibleDepartment,
              业务模块: regulation.businesses || '', // 制度文件没有业务模块字段，设置为空
              细分业务: regulation.subBusinesses || '', // 制度文件没有细分业务字段，设置为空
              制度名称: regulation.regulationsTitle,
              制度等级: regulation.regulationLeval || '', // 如果有制度等级字段，使用它，否则为空
              表单名称: '', // 制度文件没有表单名称字段，设置为空
            };
          });
        });

        // 表单文件导出数据
        const formPromises = this.formmanagementList.map((form) => {
          return this.handleProjectDetails(form).then(() => {
            return {
              主责部门: form.departmentCategory,
              业务模块: form.businesses,
              细分业务: form.subBusinesses,
              制度名称: '', // 表单文件没有制度名称字段，设置为空
              制度等级: '', // 表单文件没有制度等级字段，设置为空
              表单名称: form.formTitle,
            };
          });
        });

        // 合并两组数据
        Promise.all([...regulationPromises, ...formPromises])
          .then((data) => {
            // 创建合并后的数据结构
            const mergedData = {};

            // 将制度文件和表单文件数据合并到一个对象中
            data.forEach((item) => {
              const key = `${item.主责部门}-${item.业务模块}-${item.细分业务}`;
              if (!mergedData[key]) {
                mergedData[key] = {
                  主责部门: item.主责部门,
                  业务模块: item.业务模块,
                  细分业务: item.细分业务,
                  制度名称: [],
                  制度等级: [],
                  表单名称: [],
                };
              }
              if (item.制度名称) {
                mergedData[key].制度名称.push(item.制度名称);
              }
              if (item.制度等级) {
                mergedData[key].制度等级.push(item.制度等级);
              }
              if (item.表单名称) {
                mergedData[key].表单名称.push(item.表单名称);
              }
            });

            // 将合并后的对象转换为数组并格式化字段
            const mergedArray = Object.values(mergedData).map((entry) => {
              return {
                主责部门: entry.主责部门,
                业务模块: entry.业务模块,
                细分业务: entry.细分业务,
                制度名称: entry.制度名称.join(', '),
                制度等级: entry.制度等级.join(', '),
                表单名称: entry.表单名称.join(', '),
              };
            });

            // 创建 Excel 工作表
            const ws = XLSX.utils.json_to_sheet(mergedArray);
            // 创建 Excel 工作簿
            const wb = XLSX.utils.book_new();
            XLSX.utils.book_append_sheet(wb, ws, "综合总台账");

            // 写出 Excel 文件
            const wbout = XLSX.write(wb, { bookType: "xlsx", type: "array" });
            saveAs(
              new Blob([wbout], { type: "application/octet-stream" }),
              "综合总台账.xlsx"
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
          console.log("response======>",response);
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
          console.log("this.departments======>",this.departments);
        });
      },
    }
  };
</script>

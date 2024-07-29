<template>
  <div class="app-container">
    <el-collapse v-model="activeNames" >
      <el-collapse-item title="制度检索" name="search">
        <div>
          <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
                   label-width="68px">
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
            <el-form-item label="文件名称" prop="fileName">
              <el-input
                v-model="queryParams.fileName"
                placeholder="请输入文件名称"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
<!--            <el-form-item label="文件路径" prop="filePath">-->
<!--              <el-input-->
<!--                v-model="queryParams.filePath"-->
<!--                placeholder="请输入文件路径"-->
<!--                clearable-->
<!--                @keyup.enter.native="handleQuery"-->
<!--              />-->
<!--            </el-form-item>-->
<!--            <el-form-item label="文件类型" prop="fileType">-->
<!--              <el-input-->
<!--                v-model="queryParams.fileType"-->
<!--                placeholder="请输入文件类型"-->
<!--                clearable-->
<!--                @keyup.enter.native="handleQuery"-->
<!--              />-->
<!--            </el-form-item>-->
<!--            <el-form-item label="文件大小" prop="fileSize">-->
<!--              <el-input-->
<!--                v-model="queryParams.fileSize"-->
<!--                placeholder="请输入文件大小"-->
<!--                clearable-->
<!--                @keyup.enter.native="handleQuery"-->
<!--              />-->
<!--            </el-form-item>-->
            <el-form-item label="制度创建日期" prop="createDate">
              <el-date-picker clearable
                              v-model="queryParams.createDate"
                              type="date"
                              value-format="yyyy-MM-dd"
                              placeholder="请选择制度创建日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="制度上传人" prop="createUsername">
              <el-input
                v-model="queryParams.uploadUsername"
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
            <el-form-item label="制度主责部门" prop="mainResponsibleDepartment">
              <el-input
                v-model="queryParams.mainResponsibleDepartment"
                placeholder="请输入制度主责部门"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度专业分类" prop="classificationOfSpecialties">
              <el-input
                v-model="queryParams.classificationOfSpecialties"
                placeholder="请输入制度专业分类"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度等级" prop="regulationLeval">
              <el-input
                v-model="queryParams.regulationLeval"
                placeholder="请输入制度等级"
                clearable
                @keyup.enter.native="handleQuery"
              />
            </el-form-item>
            <el-form-item label="制度编号" prop="regulationNumber">
              <el-input
                v-model="queryParams.regulationNumber"
                placeholder="请输入制度编号"
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
          @click="handleUpload"
          v-hasPermi="['file:filemanagement:add']"
        >上传
        </el-button>
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
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['file:filemanagement:export']"
        >导出</el-button>
      </el-col>

      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="filemanagementList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
<!--      <el-table-column label="id(主键)" align="center" prop="regulationsId"/>-->
      <el-table-column label="制度标题" align="center" prop="regulationsTitle">S
        <template slot-scope="scope">
        <a  @click.prevent="previewFile(scope.row.pdfPath)" style="color: #6495ED;">{{scope.row.regulationsTitle}}</a>
        </template>
      </el-table-column>
      <el-table-column label="适用范围" align="center" prop="useScope"/>
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
      <el-table-column label="文件名称" align="center" prop="fileName"/>
<!--      <el-table-column label="文件下载" align="center" prop="filePath">-->
<!--        <template slot-scope="scope">-->
<!--          <a @click.prevent="downloadFile(scope.row.filePath)" style="color: #6495ED;">点击下载</a>-->
<!--        </template>-->
<!--      </el-table-column>-->
      <el-table-column label="PDF下载" align="center" prop="pdfPath">
        <template v-slot:default="scope">
          <a v-if="scope.row.pdfPath" @click.prevent="downloadFile(scope.row.pdfPath)" style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
      <el-table-column label="Word下载" align="center" prop="wordPath">
        <template v-slot:default="scope">
          <a v-if="scope.row.wordPath" @click.prevent="downloadFile(scope.row.wordPath)" style="color: #6495ED;">点击下载</a>
        </template>
      </el-table-column>
<!--      <el-table-column label="文件类型" align="center" prop="fileType"/>-->
<!--      <el-table-column label="文件大小" align="center" prop="fileSize"/>-->
      <el-table-column label="pdf文件大小" align="center" prop="pdfSize"/>
      <el-table-column label="word文件大小" align="center" prop="wordSize"/>
      <el-table-column label="制度创建日期" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="制度上传人" align="center" prop="uploadUsername"/>
      <el-table-column label="制度使用状态" align="center" prop="useState"/>
      <el-table-column label="制度所属科室" align="center" prop="departmentCategory"/>
      <el-table-column label="制度主责部门" align="center" prop="mainResponsibleDepartment" />
      <el-table-column label="制度专业分类" align="center" prop="classificationOfSpecialties" />
      <el-table-column label="制度等级" align="center" prop="regulationLeval" />
      <el-table-column label="制度编号" align="center" prop="regulationNumber" />
      <el-table-column label="制度标签名称" align="center" prop="fileTag" />
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
              :disabled="thisDept !== scope.row.departmentCategory && thisDept !== '研发'"
            >
             更新
            </el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['file:filemanagement:remove']"
              :disabled="thisDept !== scope.row.departmentCategory && thisDept !== '研发'"
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
          <el-col :span="24">
              <!-- 使用Flex布局将上传文件按钮水平居中 -->
              <div style="display: flex; justify-content: center;height: 100px;">
                <!--文件上传-->
                <el-upload
                  class="upload-file-uploader"
                  :action="uploadFileUrl"
                  :headers="headers"
                  :before-upload="handleBeforeUpload"
                  :on-change="handleFileChange"
                  :on-preview="handlePreview"
                  :on-remove="handleRemove"
                  :on-exceed="handleExceed"
                  :on-success="handleUploadSuccess"
                  multiple
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
            <el-form-item label="制度标题" prop="regulationsTitle">
              <el-input v-model="form.regulationsTitle" placeholder="请输入制度标题"/>
            </el-form-item>
          </el-col>
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
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="适用范围" prop="useScope">
              <el-input v-model="form.useScope" placeholder="请输入适用范围"/>
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
            <el-form-item label="pdf文件大小" prop="pdfSize">
              <el-input v-model="form.pdfSize" placeholder="请输入pdf文件大小" :disabled="true"/>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="word文件大小" prop="wordSize">
              <el-input v-model="form.wordSize" placeholder="请输入word文件大小" :disabled="true"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度使用状态" prop="useState">
              <el-select v-model="form.useState" placeholder="请选择制度使用状态">
                <el-option label="正常" value="正常"></el-option>
                <el-option label="停用" value="停用"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="所属科室" prop="departmentCategory">
              <el-select v-model="form.departmentCategory" placeholder="请输入制度所属科室">
                <!-- 循环遍历this.deptList中的部门数据 -->
                <el-option v-for="dept in deptList" :key="dept.deptId" :label="dept.deptName" :value="dept.deptName"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度主责部门" prop="mainResponsibleDepartment">
              <el-input v-model="form.mainResponsibleDepartment" placeholder="请输入制度主责部门" />
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度专业分类" prop="classificationOfSpecialties">
              <el-input v-model="form.classificationOfSpecialties" placeholder="请输入制度专业分类" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度等级" prop="regulationLeval">
              <el-input v-model="form.regulationLeval" placeholder="请输入制度等级" />
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度编号" prop="regulationNumber">
              <el-input v-model="form.regulationNumber" placeholder="请输入制度编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="制度标签名称" prop="fileTag">
              <el-input  autosize v-model="form.fileTag" placeholder="请输入制度标签名称" />
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
    <el-dialog :title="title" :visible.sync="fileUpdateDialogVisible" width="1000px" :center="true" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="110px">
        <el-row>
          <el-col :span="24">
            <!-- 使用Flex布局将上传文件按钮水平居中 -->
            <div style="display: flex; justify-content: center;height: 100px;">
              <!--文件上传-->
              <el-upload
                v-model="form.filePath"
                class="upload-file-uploader"
                :action="uploadFileUrl"
                :headers="headers"
                :before-upload="handleBeforeUpload"
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
<!--        <el-upload-->
<!--          class="update-file-uploader"-->
<!--          :action="uploadFileUrl"-->
<!--          :headers="headers"-->
<!--          :before-upload="handleBeforeUpload"-->
<!--          :on-change="handleFileChange"-->
<!--          :on-preview="handlePreview"-->
<!--          :on-remove="handleRemove"-->
<!--          :on-exceed="handleExceed"-->
<!--          :on-success="handleUploadSuccess"-->
<!--          :limit="limit"-->
<!--          :file-list="fileList"-->
<!--        >-->
<!--          <el-button size="small" type="primary">点击上传</el-button>-->
<!--        </el-upload>-->

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="updateSubmitForm">确 定</el-button>
        <el-button @click="updateCancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改文件对话框 -->
    <el-dialog :title="title" :visible.sync="fileModifyDialogVisible" width="1000px" :center="true" append-to-body>
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
                :before-upload="handleBeforeUpload"
                :on-change="handleFileChange"
                :on-preview="handlePreview"
                :on-remove="handleRemove"
                :on-exceed="handleExceed"
                :on-success="handleUploadSuccess"
                multiple
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
            <el-form-item label="制度标题" prop="regulationsTitle">
              <el-input v-model="form.regulationsTitle" placeholder="请输入制度标题"/>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="适用范围" prop="useScope">
              <el-input v-model="form.useScope" placeholder="请输入适用范围"/>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
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
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度使用状态" prop="useState">
              <el-select v-model="form.useState" placeholder="请选择制度使用状态">
                <el-option label="正常" value="正常"></el-option>
                <el-option label="停用" value="停用"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="所属科室" prop="departmentCategory">
              <el-select v-model="form.departmentCategory" placeholder="请输入制度所属科室">
                <!-- 循环遍历this.deptList中的部门数据 -->
                <el-option v-for="dept in deptList" :key="dept.deptId" :label="dept.deptName" :value="dept.deptName"></el-option>
              </el-select>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度主责部门" prop="mainResponsibleDepartment">
              <el-input v-model="form.mainResponsibleDepartment" placeholder="请输入制度主责部门" />
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度专业分类" prop="classificationOfSpecialties">
              <el-input v-model="form.classificationOfSpecialties" placeholder="请输入制度专业分类" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span='12'>
            <el-form-item label="制度等级" prop="regulationLeval">
              <el-input v-model="form.regulationLeval" placeholder="请输入制度等级" />
            </el-form-item>
          </el-col>
          <el-col :span='12'>
            <el-form-item label="制度编号" prop="regulationNumber">
              <el-input v-model="form.regulationNumber" placeholder="请输入制度编号" />
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col>
            <el-form-item label="制度标签名称" prop="fileTag">
              <el-input v-model="form.fileTag" placeholder="请输入制度标签名称" />
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
    updateFilemanagement
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
  // import pdfjsLib from 'pdfjs-dist';

  export default {
    name: "Filemanagement",
    props: {
      value: [String, Object, Array],
      limit: {
        type: Number,
        default: 2,
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
        projectNames:[], //关联流程名称列表
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
          newRegulationsId: null
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
          projectIds: null,
          newFlag: null,
          newRegulationsId: null
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
          mainResponsibleDepartment: [
            {required: true, message: "制度主责部门不能为空", trigger: "blur"}
          ],

          classificationOfSpecialties: [
            {required: true, message: "分类专业不能为空", trigger: "blur"}
          ],
          regulationLeval: [
            {required: true, message: "制度级别不能为空", trigger: "blur"}
          ],
          regulationNumber: [
            {required: true, message: "制度编号不能为空", trigger: "blur"}
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
      getUserProfile02().then(response => {
        // 处理成功的情况
        console.log('成功获取用户信息response.data====>', response.data);
        console.log('成功获取用户信息response.data.dept.deptName====>', response.data.dept.deptName);
        // const userInfo =; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
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
      /** 查询文件管理列表 */
      getList() {
        this.loading = true;
        console.log("刷新页面");
        // 如果部门是研发或企管，则不添加departmentCategory到queryParams
        if (!['研发', '企管'].includes(this.thisDept)) {
          this.queryParams.departmentCategory = this.thisDept;
        }
        listFilemanagement(this.queryParams).then(response => {
          console.log("response:：",response);
          this.filemanagementList = response.rows;
          console.log("filemanagementList=>",this.filemanagementList);
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 查询绑定的流程信息 */
      handleProjectDetails(row) {
        let projectList;
        let nodeList;
        this.projectNames = [];
        listProject(this.projecQueryParams).then(response => {
          console.log("response111:：",response);
          projectList = response;
          console.log("projectNames:",this.projectNames);
          projectList.forEach(process => {
            if (process.state && process.state.includes(row.regulationsId)) {
              this.projectNames.push(process.name);
              console.log("projectNames=>",this.projectNames);
            }
          });

        });
        // listNode(this.nodeQueryParams).then(response => {
        //   console.log("response222:：",response);
        //   console.log("row:",row);
        //   nodeList = response.rows;
        //   nodeList.forEach(node => {
        //     if (node.state && node.state.includes(row.regulationsId)) {
        //       getNode(node.projectId).then(response1 => {
        //         console.log("response333=>",response1);
        //         this.projectNames.push(response.rows.name);
        //       })
        //       console.log("projectNames=>",this.projectNames);
        //     }
        //   });
        // })
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
          regulationsId: null,
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
          oldRegulationsId: null,
          revisionDate: null,
          revisionContent: null,
          reviser: null,
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
      /** 修改制度文件 */
      handleModify(row) {
        this.reset();
        const regulationsId = row.regulationsId || this.ids
        getFilemanagement(regulationsId).then(response => {
          this.form = response.data;
          this.fileModifyDialogVisible = true;
          this.title = "更新制度文件";
        });
      },
      /** 更新文件 */
      handleUpdate(row){
        this.reset();
        const regulationsId = row.regulationsId || this.ids;
        getFilemanagement(regulationsId).then(response => {
          this.form = response.data;
          // this.form.newFlag = 0;
          console.log("更新文件=>",this.form);
          this.fileUpdateDialogVisible = true;
          this.title = "更新制度文件";
        });
      },
      /** 历史版本管理 */
      handleHistoryVersions(row) {
        const regulationsId = row.regulationsId;
        this.$router.push("/file/filemanagement/historyVersions/" + regulationsId);
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
              this.form.newFlag = 1;
              addFilemanagement(this.form).then(response => {
                this.$modal.msgSuccess("上传成功");
                this.fileUploadDialogVisible = false;
                this.getList();
                console.log("上传文件提交按钮=>",this.form);
              });
          }
        });
        this.fileList = [];
      },
      /** 修改文件提交按钮 */
      modifySubmitForm(file,fileList) {
        console.log("Before validate - fileList:", this.fileList);
        console.log("file=======>:", file);
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.regulationsId != null) {
              const thisId = this.form.regulationsId;
              console.log("thisId=>",thisId);
              console.log("newform=>",this.form);
              // 初始化文件路径为 null 或空字符串
              let pdfFilePath = '';
              let wordFilePath = '';

              // 遍历文件列表，检查文件名是否包含 .pdf 或 .word 后缀
              this.fileList.forEach(item => {
                if (item.name.toLowerCase().endsWith('.pdf')) {
                  pdfFilePath = item.response ? item.response.url : '';
                } else if (item.name.toLowerCase().endsWith('.doc') || item.name.toLowerCase().endsWith('.docx')) {
                  wordFilePath = item.response ? item.response.url : '';
                }
              });

              // 如果没有 .pdf 文件或 .word 文件，将对应的路径设置为空
              if (!pdfFilePath) {
                this.form.pdfPath = '';
                this.form.pdfSize = '';
              } else {
                this.form.pdfPath = pdfFilePath;
              }

              if (!wordFilePath) {
                this.form.wordPath = '';
                this.form.wordSize = '';
              } else {
                this.form.wordPath = wordFilePath;
              }

              this.form.oldRegulationsId = this.form.regulationsId;
              console.log("this.form.newRegulationsId=>",this.form.newRegulationsId);
              console.log("(this.form.newRegulationsId == null)",(this.form.newRegulationsId == null));
              console.log("(thisId == this.form.newRegulationsId)",(thisId === this.form.newRegulationsId));
              addFilemanagement(this.form).then(response => {
                const newId = response.data;
                this.$modal.msgSuccess("更新成功");
                this.fileModifyDialogVisible = false;
                this.form.newRegulationsId = null;
                //更新历史版本制度
                getFilemanagement(this.form.oldRegulationsId).then(response => {
                  const lastForm = response.data;
                  lastForm.newFlag = 0;
                  lastForm.newRegulationsId = newId;
                  console.log("上一表单=>",lastForm);
                  updateFilemanagement(lastForm).then(response => {
                    this.getList();
                  });
                });
                // this.getList();
                console.log("更新文件提交按钮1=>",this.form);
                console.log("response=>",response);
              });

              // updateFilemanagement(this.form).then(response => {
              //   this.$modal.msgSuccess("修改成功");
              //   this.fileModifyDialogVisible = false;
              //   this.getList();
              //   console.log("修改文件提交按钮=>",this.form);
              // });
            }
          }
        });
        this.fileList = [];
      },
      /** 更新文件提交按钮 */
      updateSubmitForm() {
        console.log("Before validate - fileList:", this.fileList);
        console.log("form12345===>",this.form);
        // 检查 fileList 是否为空
        if (this.fileList.length === 0) {
          this.$message.error("请上传文件");
          return;
        }
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.regulationsId != null) {
              const thisId = this.form.regulationsId;
              console.log("thisId=>",thisId);
              console.log("newform=>",this.form);
              this.form.oldRegulationsId = this.form.regulationsId;
              console.log("this.form.newRegulationsId=>",this.form.newRegulationsId);
              console.log("(this.form.newRegulationsId == null)",(this.form.newRegulationsId == null));
              console.log("(thisId == this.form.newRegulationsId)",(thisId === this.form.newRegulationsId));
              addFilemanagement(this.form).then(response => {
                const newId = response.data;
                this.$modal.msgSuccess("更新成功");
                this.fileUpdateDialogVisible = false;
                this.form.newRegulationsId = null;
                //更新历史版本制度
                getFilemanagement(this.form.oldRegulationsId).then(response => {
                  const lastForm = response.data;
                  lastForm.newFlag = 0;
                  lastForm.newRegulationsId = newId;
                  console.log("上一表单=>",lastForm);
                  updateFilemanagement(lastForm).then(response => {
                    this.getList();
                  });
                });
                // this.getList();
                console.log("更新文件提交按钮1=>",this.form);
                console.log("response=>",response);
              });
            }
          }
        });
        this.fileList = [];
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        console.log("当前表单1=>",row);
        //将id或ids统一转换为数组
        const regulationsIds = [].concat(row.regulationsId || this.ids);
        // const regulationsIds = row.regulationsId || this.ids;
        console.log("regulationsIds=>",regulationsIds);
        this.$modal.confirm('是否确认删除？').then(function () {
          regulationsIds.forEach(id => {
            console.log("Processing ID:", id);
            getFilemanagement(id).then(response => {
              const thisForm = response.data;
              console.log("response------>:", response);
              if(thisForm.oldRegulationsId != null) {
                getFilemanagement(thisForm.oldRegulationsId).then(response => {
                  const lastForm = response.data;
                  console.log("上一表单=>",lastForm);
                  lastForm.newFlag = 1;
                  console.log("上一表单=>",lastForm);
                  updateFilemanagement(lastForm).then(response => {
                  });
                });
              }
            });
          });
          return delFilemanagement(regulationsIds);
        }).then(() => {
          this.$modal.msgSuccess("删除成功");
          this.getList();
          console.log("删除文件刷新");
        }).then(() => {
          this.getList();
          console.log("删除文件刷新2");
        }).catch(() => {
        });
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
      // 上传前校检格式和大小
      handleBeforeUpload(file,fileList) {
        console.log("handleBeforeUpload:file=====>",file);
        // 上传前校检文件格式
        const allowedTypes = ['application/msword', 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'application/pdf'];
        const isAllowedType = allowedTypes.includes(file.type);
        if (!isAllowedType) {
          this.$message.error('文件类型不匹配，请重新上传word或pdf文件');
        }
        return isAllowedType;
      },
      handleFileChange(file, fileList) {
        this.fileList = fileList;
        console.log("file====>", file);
        console.log("fileList====>", fileList);
        // 检查文件类型
        if (file.name.endsWith('.pdf')) {
          this.form.pdfPath = this.path;
          this.form.pdfSize = file.size;
        } else if (file.name.endsWith('.doc') || file.name.endsWith('.docx')) {
          this.form.wordPath = this.path;
          this.form.wordSize = file.size;
        }
        this.form.fileName = file.name.substring(0, file.name.lastIndexOf('.'));
        const currentDate = new Date();
        this.form.uploadDate = currentDate.toISOString().split('T')[0];
        this.getUserInfo();
        // if (fileList.length === 2) {
        //   const uploadedWordFile = fileList.find(f => f.name.endsWith('.doc') || f.name.endsWith('.docx'));
        //   const uploadedPdfFile = fileList.find(f => f.name.endsWith('.pdf'));
        //
        //   if (uploadedWordFile && uploadedPdfFile) {
        //     this.form.fileName = uploadedPdfFile.name.substring(0, uploadedPdfFile.name.lastIndexOf('.'));
        //     this.form.wordSize = uploadedWordFile.size;
        //     this.form.pdfSize = uploadedPdfFile.size;
        //
        //     if (uploadedWordFile.response && uploadedPdfFile.response) {
        //       this.form.wordPath = uploadedWordFile.response.url;
        //       this.form.pdfPath = uploadedPdfFile.response.url;
        //     }
        //
        //     const currentDate = new Date();
        //     this.form.uploadDate = currentDate.toISOString().split('T')[0];
        //
        //     this.getUserInfo();
        //   } else {
        //     this.$message.warning('请上传一个Word文件和一个PDF文件');
        //   }
        // }
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
          pdfjsLib.getDocument({ data: typedArray }).promise.then((pdf) => {
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
      // 上传成功回调
      handleUploadSuccess(res, file) {
        const uploadedFile = file.raw; // 获取上传的文件对象
        console.log("上传成功回调file=====>",file);
        console.log("上传成功回调uploadedFile=====>",uploadedFile);
        this.path = res.url;
        const uploadedFileName = uploadedFile.name.substring(0, uploadedFile.name.lastIndexOf('.')); // 获取上传文件的文件名
        // 发起请求检查文件名是否存在于数据库中
        const isFileNameDuplicate = this.filemanagementList.some(item => item.fileName === uploadedFileName);
        console.log("发起请求检查文件名是否存在于数据库中",isFileNameDuplicate);

        if (isFileNameDuplicate) {
          // 如果文件名重复，弹出警告框
          this.$modal.msgError('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
          console.log('同名文件已存在，如需上传该制度新版本，请到“更新”处上传！');
          return false; // 中断上传流程
        }
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
          this.fileList = this.fileList.concat(this.uploadList);
          this.uploadList = [];
          this.number = 0;
          this.$emit("input", this.listToString(this.fileList));
          this.$modal.closeLoading();
        }
      },
      handleRemove(file, fileList) {
        // 清除对应字段
        if (file.name.endsWith('.pdf')) {
          this.form.pdfPath = '';
          this.form.pdfSize = '';
        } else if (file.name.endsWith('.doc') || file.name.endsWith('.docx')) {
          this.form.wordPath = '';
          this.form.wordSize = '';
        }
        this.form.departmentCategory = '';
        this.fileList = fileList;
        console.log(file, fileList);
      },
      handlePreview(file) {
        console.log(file);
      },
      handleExceed(files, fileList) {
        this.$message.warning(`当前限制选择 2 个文件`);
      },
      // beforeRemove(file, fileList) {
      //   return this.$confirm(`确定移除 ${file.name}？`);
      // },
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
      // getFileType(fullType) {  //获取详细的文件类型
      //   // 根据完整的文件类型(fullType)获取简短的文件类型
      //   if (fullType.includes('pdf')) {
      //     return 'pdf';
      //   } else if (fullType.includes('word')) {
      //     return 'word';
      //   } else {
      //     // 其他类型的文件处理方式
      //     return 'other';
      //   }
      // },
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

    }
  };
</script>

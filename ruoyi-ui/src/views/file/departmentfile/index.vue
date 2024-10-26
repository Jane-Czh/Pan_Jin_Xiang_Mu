<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col :span="4" :xs="24">
        <div class="header-container">
          <el-input
            v-model="searchFolderName"
            placeholder="请输入文件夹名称"
            clearable
            size="small"
            prefix-icon="el-icon-search"
            style="margin-bottom: 20px"
          />
        </div>
        <div class="header-container">
          <el-tree
            :data="filteredFoldersTree"
            node-key="id"
            :props="defaultProps"
            :expand-on-click-node="false"
            default-expand-all
            highlight-current
            :filter-node-method="filterNode"
            ref="tree"
            @node-click="handleNodeClick"
            @check-change="handleSelectionChange"

          >
      <span class="custom-tree-node" slot-scope="{ node, data }" @contextmenu.prevent="handleContextMenu($event, data)">
        <i class="el-icon-folder" style="margin-right: 8px;"></i> <!-- 文件夹图标 -->
        <span>{{ node.label }}</span>
      </span>
          </el-tree>
        </div>


        <!-- 右键菜单 -->
        <ul class="context-menu" v-show="contextMenuVisible" :style="{ top: contextMenuTop + 'px', left: contextMenuLeft + 'px' }">
          <li @click="handleAdd(contextMenuData)">
            <el-button type="text" size="mini" v-hasPermi="['file:folders:add']">新建文件夹</el-button>
          </li>
          <li @click="handleUpload(contextMenuData)">
            <el-button type="text" size="mini" v-hasPermi="['file:folders:add']">上传文件</el-button>
          </li>
          <li @click="handleUpdate(contextMenuData)">
            <el-button type="text" size="mini" v-hasPermi="['file:folders:edit']">修改</el-button>
          </li>
          <li @click="handleDelete(contextMenuData)">
            <el-button type="text" size="mini" v-hasPermi="['file:folders:remove']">删除</el-button>
          </li>
        </ul>
      </el-col>
      <el-col :span="20" :xs="24">
        <el-form :model="fileQueryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="文件名称" prop="fileName">
            <el-input
              v-model="fileQueryParams.fileName"
              placeholder="请输入文件名称"
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
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="primary"-->
          <!--          plain-->
          <!--          icon="el-icon-plus"-->
          <!--          size="mini"-->
          <!--          @click="handleAdd"-->
          <!--          v-hasPermi="['file:folders:add']"-->
          <!--        >新建文件夹</el-button>-->
          <!--      </el-col>-->
          <!--      <el-col :span="1.5">-->
          <!--        <el-button-->
          <!--          type="warning"-->
          <!--          plain-->
          <!--          icon="el-icon-download"-->
          <!--          size="mini"-->
          <!--          @click="handleExport"-->
          <!--          v-hasPermi="['file:folders:export']"-->
          <!--        >导出</el-button>-->
          <!--      </el-col>-->
<!--                  <el-col :span="1.5">-->
<!--                    <el-button-->
<!--                      type="info"-->
<!--                      plain-->
<!--                      icon="el-icon-upload"-->
<!--                      size="mini"-->
<!--                      @click="handleUpload"-->
<!--                      v-hasPermi="['file:files:upload']"-->
<!--                    >上传文件</el-button>-->
<!--                  </el-col>-->
          <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>



        <!-- 文件列表 -->
        <el-table
          v-loading="loading"
          :data="files"      style="width: 100%"
          stripe>
          <el-table-column align="center" prop="fileName" label="文件名" />
          <el-table-column align="center" prop="fileSize" label="大小" />
          <el-table-column align="center" prop="fileType" label="类型" />
          <el-table-column align="center" prop="uploadTime" label="上传时间" width="180" />
          <el-table-column align="center" label="操作" width="150">
            <template slot-scope="scope">
              <el-button size="mini" type="primary" @click="downloadFile(scope.row.filePath)">下载</el-button>
              <el-button size="mini" type="danger" @click="deleteFile(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="listFilesByFolderId"
        />
      </el-col>
    </el-row>


    <!-- 添加或修改folders对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文件夹名称" prop="folderName">
          <el-input v-model="form.folderName" placeholder="请输入文件夹名称" />
        </el-form-item>
        <el-form-item label="上级文件夹" prop="parentId">
          <el-select v-model="form.parentId" placeholder="请选择上级文件夹" clearable @click.native="getDataList">
            <el-option
              v-for="item in dataList"
              :key="item.id"
              :label="item.folderName"
              :value="item.id"
            ></el-option>
          </el-select>
        </el-form-item>
<!--        <el-form-item label="创建时间" prop="createdTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.createdTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择创建时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="更新时间" prop="updatedTime">-->
<!--          <el-date-picker clearable-->
<!--            v-model="form.updatedTime"-->
<!--            type="date"-->
<!--            value-format="yyyy-MM-dd"-->
<!--            placeholder="请选择更新时间">-->
<!--          </el-date-picker>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 文件上传对话框 -->
    <el-dialog :title="uploadTitle" :visible.sync="uploadOpen" width="400px" append-to-body>
      <el-form ref="form" :model="fileform" :rules="rules" label-width="80px">
        <el-upload
          class="department-upload"
          drag
          :action="uploadFileUrl"
          :headers="headers"
          :on-success="handleUploadSuccess"
          :data="uploadFormData"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
        </el-upload>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="uploadOpen = false">关闭</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listFolders, getFolders, delFolders, addFolders, updateFolders } from "@/api/file/folders";
import {getToken} from "@/utils/auth";
import {
  addDepartmentfile,
  delDepartmentfile,
  getAllfilesByFolderId,
  listDepartmentfile
} from "@/api/file/departmentfiles";
import {listUser} from "@/api/system/user";
import {getUserProfile02} from "@/api/file/filemanagement";
export default {
  name: "Folders",
  data() {
    return {
      thisDept: null, // 当前账号的dept
      // 文件夹名称
      searchFolderName: undefined,
      contextMenuVisible: false, // 右键菜单是否显示
      contextMenuTop: 0,  // 右键菜单垂直位置
      contextMenuLeft: 0, // 右键菜单水平位置
      contextMenuData: null, // 右键菜单数据
      headers: {
        Authorization: "Bearer " + getToken(),
      },
      baseUrl: process.env.VUE_APP_BASE_API,
      files: [], // 当前文件夹下的文件列表
      uploadFileUrl: process.env.VUE_APP_BASE_API + "/common/upload",
      selectedFolderId: 1,  // 当前选中的文件夹ID
      uploadOpen: false, // 文件上传对话框是否显示
      uploadTitle: "上传文件", // 文件上传对话框标题
      uploadFormData: { folderId: null }, // 文件上传表单数据
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
      // folders树形数据
      foldersTree: [],
      filteredFoldersTree: [], // 部门过滤后的树形数据
      dataList: [], //数据库查询结果
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 文件夹查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        folderName: null,
        parentId: null,
        createdTime: null,
        updatedTime: null
      },
      // 文件查询参数
      fileQueryParams: {
        pageNum: 1,
        pageSize: 10,
        fileName: null,
        filePath: null,
        fileSize: null,
        fileType: null,
        folderId: null,
        uploadTime: null
      },
      // 表单参数
      form: {},
      fileform: {
        fileName: null,
        filePath: null,
        fileSize: null,
        fileType: null,
        folderId: null,
        uploadTime: null
      },
      // 表单校验
      rules: {},
      // 树形结构配置
      defaultProps: {
        children: 'children',
        label: 'folderName'
      }
    };
  },
  watch: {
    // 根据名称筛选部门树
    searchFolderName(val) {
      this.$refs.tree.filter(val);
    }
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
    this.listFilesByFolderId();
  },
  methods: {
    /** 查询folders列表 */
    getList() {
      this.loading = true;
      listFolders(this.queryParams).then(response => {
        this.dataList = response.rows;
        this.foldersTree = this.convertToTree(this.dataList);
        console.log("this.foldersTree:", this.foldersTree);
        this.filterFolders(); // 过滤文件夹数据
        console.log("this.filteredFoldersTree:", this.filteredFoldersTree);
        this.loading = false;
      });
    },
    filterFolders() {
      if (this.thisDept === '总部' || this.thisDept === '研发') {
        this.filteredFoldersTree = this.foldersTree; // 企管科可以看到全部文件夹
      } else {
        this.filteredFoldersTree = this.filterByDept(this.foldersTree, this.thisDept);
      }
    },
    filterByDept(folders, dept) {
      return folders.reduce((acc, folder) => {
        console.log(`Checking folder: ${folder.folderName} for dept: ${dept}`);
        const filteredChildren = this.filterByDept(folder.children || [], dept);
        console.log(`Filtered children for folder ${folder.folderName}:`, filteredChildren);

        if (folder.folderName.toLowerCase().includes(dept.toLowerCase())) {
          console.log(`Folder ${folder.folderName} matches dept ${dept}, adding all children`);
          // 添加当前文件夹及其所有子文件夹
          acc.push({
            ...folder,
            children: folder.children || []
          });
        } else if (filteredChildren.length > 0) {
          console.log(`Folder ${folder.folderName} has matching children`);
          acc.push({
            ...folder,
            children: filteredChildren
          });
        } else {
          console.log(`Folder ${folder.folderName} does not match dept ${dept}`);
        }
        return acc;
      }, []);
    },

    /** 获取文件夹下的文件列表 */
    listFilesByFolderId() {
      this.loading = true;
      const queryParams = {
        folderId: this.selectedFolderId
      }
      if (this.selectedFolderId) {
        listDepartmentfile(queryParams).then(response => {
          console.log("response:", response);
          this.files = response.rows;
          this.total = response.total;
          console.log("files:", this.files);
          this.loading = false;
        });
      }
    },
    /** 查询当前文件夹以及子文件夹下的所有文件 */
    listAllFilesByFolderId(folderId) {
      this.loading = true;
      if (this.selectedFolderId) {
        getAllfilesByFolderId(folderId,this.fileQueryParams.fileName).then(response => {
          console.log("response:", response);
          this.files = response.rows;
          this.total = response.total;
          console.log("files:", this.files);
          this.loading = false;
        });
      }
    },
    //用于选择器获取数据
    getDataList() {
      listFolders(this.queryParams).then(response => {
        this.dataList = response.rows;
        console.log("dataList:", this.dataList);
      });
    },

    // 将线性数据转换为树形结构
    convertToTree(data) {
      const map = {};
      const result = [];

      data.forEach(item => {
        map[item.id] = { ...item, children: [] };
      });

      data.forEach(item => {
        const id = item.id;
        const parentId = item.parentId;

        if (parentId === null) {
          result.push(map[id]);
        } else {
          if (map[parentId]) {
            map[parentId].children.push(map[id]);
          }
        }
      });

      return result;
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        folderName: null,
        parentId: null,
        createdTime: null,
        updatedTime: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.fileQueryParams.pageNum = 1;
      this.listAllFilesByFolderId(this.selectedFolderId);
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("fileQueryParams");
      this.fileQueryParams.pageNum = 1;
      this.listFilesByFolderId();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd(row) {
      this.reset();
      this.form.parentId = row.id;
      this.open = true;
      this.title = "添加folders";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getFolders(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改folders";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateFolders(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addFolders(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除文件夹按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除该文件夹？').then(function() {
        return delFolders(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('file/folders/export', {
        ...this.fileQueryParams
      }, `folders_${new Date().getTime()}.xlsx`)
    },
    /** 节点点击事件 */
    handleNodeClick(data) {
      this.selectedFolderId = data.id;
      console.log('Node clicked:', data);
      this.listFilesByFolderId();
    },
    //文件上传
    handleUpload(row) {
      if (!row.id) {
        this.$message.warning("请先选择一个文件夹");
        return;
      }
      this.uploadFormData.folderId = row.id;
      this.uploadOpen = true;
    },
    handleUploadSuccess(response, file, fileList) {
      if (response && response.code === 200) {
        console.log("file:", file);
        this.$message.success("文件上传成功");
        this.fileform.fileName = response.originalFilename.substring(0, response.originalFilename.lastIndexOf('.'));
        this.fileform.filePath = response.url;
        this.fileform.fileSize = file.raw.size;
        this.fileform.folderId = this.selectedFolderId;
        const fileTypeIndex = response.originalFilename.lastIndexOf('.');
        this.fileform.fileType = fileTypeIndex !== -1 ? response.originalFilename.substring(fileTypeIndex + 1) : '';
        this.fileform.uploadTime = new Date().toISOString();
        addDepartmentfile(this.fileform).then(response => {
        })
        this.listFilesByFolderId(); // 重新获取文件列表
        this.uploadOpen = false;
      } else {
        this.$message.error("文件上传失败: " + response);
      }
    },
    /** 下载文件 */
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
    /** 删除文件 */
    deleteFile(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除该文件？').then(function() {
        return delDepartmentfile(ids);
      }).then(() => {
        this.listFilesByFolderId();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    handleContextMenu(event, data) {
      this.contextMenuTop = event.clientY; //获取鼠标点击位置的垂直坐标，并设置为右键菜单的顶部位置
      this.contextMenuLeft = event.clientX; //获取鼠标点击位置的水平坐标，并设置为右键菜单的左侧位置
      this.contextMenuData = data;
      this.selectedFolderId = data.id;
      console.log("data:", data);
      console.log("contextMenuData:", this.contextMenuData);
      this.contextMenuVisible = true;
      window.addEventListener('click', this.closeContextMenu); // 点击其他区域时关闭右键菜单
    },
    closeContextMenu() {
      this.contextMenuVisible = false;
      window.removeEventListener('click', this.closeContextMenu); // 移除监听器
    },
    // 筛选节点
    filterNode(value, data) {
      if (!value) return true;
      return data.folderName.indexOf(value) !== -1;
    },
  }
};
</script>
<style scoped>.context-menu {
  position: fixed;
  background: white;
  border: 1px solid #ccc;
  box-shadow: 2px 2px 2px rgba(0, 0, 0, 0.2);
  list-style-type: none;
  padding: 0;
  margin: 0;
  z-index: 1000;
}

.context-menu li {
  padding: 8px 16px;
  cursor: pointer;
}

.context-menu li:hover {
  background-color: #f0f0f0;
}
</style>

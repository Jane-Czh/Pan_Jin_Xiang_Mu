<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <!-- 1  :name= this.$props.activeName -->
    <el-tab-pane
      label="制度文件"
      name="first"
      style="background-color: #f2f2f2; padding: 2px"
    >
      <!-- 检索框 -->
      <el-input
        v-model="searchQuery"
        placeholder="请输入[表单文件名称]进行检索"
        @input="handleSearch"
        style="margin-bottom: 10px"
      />

      <!-- 制度文件table -->
      <el-table
        ref="multipleTable"
        :data="filteredFileList"
        style="width: 100%"
        border
        height="300px"
        @selection-change="handleSelectionChange"
        :selectable="selectEnable"
      >
        <el-table-column
          type="selection"
          width="55"
          align="center"
          :filtered-value="true"
          :reserve-selection="true"
        />
        <el-table-column label="序号" align="center" prop="id">
          <template slot-scope="scope">
            <span>{{ scope.$index + 1 }}</span>
          </template>
        </el-table-column>

        <!-- <el-table-column label="id(主键)" align="center" prop="regulationsId" /> -->
        <el-table-column label="文件名称" align="center" prop="fileName" />
        <el-table-column
          label="制度所属科室"
          align="center"
          prop="departmentCategory"
        />
        <el-table-column
          label="制度标题"
          align="center"
          prop="regulationsTitle"
        />
      </el-table>

      <!-- 分页功能 -->
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getRegularFileData"
      />
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import { listFilemanagementAll } from "@/api/system/project";

export default {
  props: {
    selectedFileNames: {
      type: Array,
      default: () => [],
    },
    activeName: {
      type: String,
      default: "first",
    },
  },
  name: "CustomTabs",
  inject: ["reload"],
  data() {
    return {
      filemanagementList: [],
      ids: [],
      names: [],
      searchQuery: "", // 检索框绑定的数据属性
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
        reviser: null,
      },
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
    };
  },
  computed: {
    filteredFileList() {
      return this.filemanagementList.filter((item) =>
        item.fileName.includes(this.searchQuery)
      );
    },
  },
  created() {
    this.getRegularFileData();
  },
  mounted() {
    this.setFilesSelected(
      JSON.parse(JSON.stringify(this.$props.selectedFileNames))
    );
  },
  methods: {
    getRegularFileData() {
      listFilemanagementAll(this.queryParams).then((response) => {
        this.filemanagementList = response.rows;
        this.total = response.total;
      });
      this.filemanagementList = this.filemanagementList.slice(
        (this.pageIndex - 1) * this.pageSize,
        this.pageIndex * this.pageSize
      );
    },
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.regulationsId);
      this.names = selection.map((item) => item.fileName);
    },
    getSelectedIdsAndNames() {
      return {
        ids: this.ids,
        names: this.names,
      };
    },
    setFilesSelected() {
      setTimeout(() => {
        this.$nextTick(() => {
          this.selectedFileNames.forEach((name) => {
            const row = this.filemanagementList.find(
              (item) => item.fileName === name
            );
            if (row) {
              this.$refs.multipleTable.toggleRowSelection(row, true);
            }
          });
        });
      }, 1000);
    },
    handleSearch() {
      this.pageIndex = 1;
      this.getRegularFileData();
    },
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getRegularFileData();
    },
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getRegularFileData();
    },
  },
};
</script>

<style>
/* 可以根据需要添加样式 */
</style>

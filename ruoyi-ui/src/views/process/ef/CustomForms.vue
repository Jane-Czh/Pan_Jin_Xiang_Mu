<!-- 节点绑定 表单文件 -->
<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <el-tab-pane
      label="表单文件"
      style="background-color: #f2f2f2; padding: 2px"
    >
      <!-- 检索框 -->
      <el-input
        v-model="searchQuery"
        placeholder="输入文件名称自动搜索"
        @input="handleSearch"
        style="margin-bottom: 10px"
      />

      <!-- 表单文件 -->
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

        <el-table-column label="表单名称" align="center" prop="formTitle" />

        <el-table-column
          label="表单所属科室"
          align="center"
          prop="departmentCategory"
        />
        <el-table-column label="文件名称" align="center" prop="formName" />
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
// import { listFormfilemanagementAll } from "@/api/system/project";
import { listFormfilemanagement } from "@/api/system/project";

export default {
  props: {
    selectedFileNames: {
      // 接收父组件传递的已绑定的文件信息
      type: Array,
      default: () => [],
    },
  },
  name: "CustomTabs",
  inject: ["reload"],
  data() {
    return {
      // 制度文件数据
      filemanagementList: [],
      // activeName: "first",
      // 制度文件数据

      // 选中数组传给 node
      ids: [],
      names: [],
      // 从node传来的 已绑定的文件信息
      selectedFileNames: [],
      searchQuery: "", // 检索框绑定的数据属性

      // 制度查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
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
      // 总条数
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
    };
  },
  computed: {
    filteredFileList() {
      // 过滤文件列表
      return this.filemanagementList.filter((item) =>
        item.formName.includes(this.searchQuery)
      );
    },
  },
  created() {
    this.getRegularFileData();
  },
  mounted() {
    // 当组件挂载时，设置已绑定的文件信息为选中状态--展示回显效果
    this.setFilesSelected(
      JSON.parse(JSON.stringify(this.$props.selectedFileNames))
    );
  },
  methods: {
    hello() {
      console.log("hello");
    },
    /** 查询表单文件列表 */
    getRegularFileData() {
      listFormfilemanagement(this.queryParams).then((response) => {
        this.filemanagementList = response.rows;
        this.total = response.total;
      });
      //数据分页
      this.filemanagementList = this.filemanagementList.slice(
        (this.pageIndex - 1) * this.pageSize,
        this.pageIndex * this.pageSize
      );
    },

    handleClick(tab, event) {
      console.log(tab, event);
    },

    /** 保存选择的行数据 */
    handleSelectionChange(selection) {
      // 选中的行数据
      this.ids = selection.map((item) => item.formId);
      this.names = selection.map((item) => item.formName);
      // 触发自定义事件，并传递 ids 和 names 数据
      // this.$emit("selection-change", { ids: this.ids, names: this.names });
    },
    //传递给node数据ids&names
    getSelectedIdsAndNames() {
      return {
        ids: this.ids,
        names: this.names,
      };
    },

    //接收node的数据
    setFilesSelected() {
      setTimeout(() => {
        // 将已绑定的文件信息设置为已选状态
        this.$nextTick(() => {
          this.selectedFileNames.forEach((name) => {
            const row = this.filemanagementList.find(
              (item) => item.formName === name
            );
            if (row) {
              this.$refs.multipleTable.toggleRowSelection(row, true);
            }
          });
        });
      }, 1000);
    },

    // 检索框输入事件
    handleSearch() {
      // 在检索时自动更新显示的文件列表
      this.pageIndex = 1;
      this.getRegularFileData();
    },

    //分页功能相关
    // 每页数
    sizeChangeHandle(val) {
      this.pageSize = val;
      this.pageIndex = 1;
      this.getDataList();
    },
    // 当前页
    currentChangeHandle(val) {
      this.pageIndex = val;
      this.getDataList();
    },
  },
};
</script>

<style>
/* 可以根据需要添加样式 */
</style>

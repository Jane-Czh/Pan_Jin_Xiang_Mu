<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <!-- 1 -->
    <el-tab-pane
      label="制度文件"
      name="first"
      style="background-color: #f2f2f2; padding: 2px"
    >
      <!-- 制度文件table -->
      <el-table
        ref="multipleTable"
        :data="filemanagementList"
        style="width: 100%"
        border
        height="170px"
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
import { listFilemanagement } from "@/api/file/filemanagement";

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
      activeName: "first",
      // 制度文件数据

      // 选中数组传给 node
      ids: [],
      names: [],
      // 从node传来的 已绑定的文件信息
      // selectedFileNames: [],

      // 制度查询参数
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
      // 总条数
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
    };
  },
  created() {
    this.getRegularFileData();
  },
  mounted() {
    // 当组件挂载时，设置已绑定的文件信息为选中状态
    // this.setSelectedFileNames(this.props.selectedFileNames);
  },

  destroyed() {
    this.$destroy();
  },

  methods: {
    hello() {
      console.log("hello");
    },
    /** 查询制度文件列表 */
    getRegularFileData() {
      listFilemanagement(this.queryParams).then((response) => {
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
      this.ids = selection.map((item) => item.regulationsId);
      this.names = selection.map((item) => item.fileName);
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
    setSelectedFileNames(name) {
      // 接收父组件传递的已绑定的文件信息
      // this.props.selectedFileNames = names;
      // 设置已绑定的文件信息为已选状态
      this.setFilesSelected(name);
    },
    setFilesSelected(name) {
      if (name != null) {
        // 将已绑定的文件信息设置为已选状态
        this.$nextTick(() => {
          name.forEach((name) => {
            const row = this.filemanagementList.find(
              (item) => item.fileName === name
            );
            if (row) {
              this.$refs.multipleTable.toggleRowSelection(row, true);
            }
          });
        });
      }
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

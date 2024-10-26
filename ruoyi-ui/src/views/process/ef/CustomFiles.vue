<template>
  <el-tabs v-model="activeName" @tab-click="handleClick">
    <!-- 1、制度文件 -->
    <el-tab-pane
      label="制度文件"
      name="first"
      style="background-color: #f2f2f2; padding: 2px"
    >
      <el-input
        placeholder="输入文件名称自动搜索"
        v-model="queryParams.regulationsTitle"
        @input="getRegularFileData"
        style="margin-bottom: 10px"
      />
      <!-- 制度文件table -->
      <el-table
        ref="multipleRegulationTable"
        :data="filemanagementList"
        style="width: 100%"
        border
        height="300px"
        @selection-change="handleRegulationSelectionChange"
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
        <el-table-column label="制度标题" align="center" prop="regulationsTitle" />
        <el-table-column
          label="制度所属科室"
          align="center"
          prop="departmentCategory"
        />
        <el-table-column
          label="文件名称"
          align="center"
          prop="fileName"
        />
      </el-table>
    </el-tab-pane>
    <!-- --------------------------------------------- -->

    <!-- 2、表单文件 -->
    <el-tab-pane
      label="表单文件"
      name="second"
      style="background-color: #f2f2f2; padding: 2px"
    >
      <el-input
        placeholder="输入表单标题自动搜索表单文件"
        v-model="queryParams.formTitle"
        @input="getFormFileData"
        style="margin-bottom: 10px"
      />
      <!-- 表单文件table -->
      <el-table
        ref="multipleFormTable"
        :data="formsmanagementList"
        style="width: 100%"
        border
        height="300px"
        @selection-change="handleFormSelectionChange"
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

        <el-table-column label="文件名称" align="center" prop="formName" />
        <el-table-column
          label="表单所属科室"
          align="center"
          prop="departmentCategory"
        />
        <el-table-column
          label="表单标题"
          align="center"
          prop="formTitle"
        />
      </el-table>
    </el-tab-pane>
    <!-- ---------------------------------------------------------------- -->
  </el-tabs>
</template>

<script>
import { listFilemanagementAll, listFormfilemanagementAll } from "@/api/system/project";

export default {
  props: {
    // 接收父组件传递的已绑定的文件信息
    selectedRegulationNames: {
      type: Array, // 声明为数组类型
      default: () => [], // 默认值为空数组
    },
    selectedFormNames: {
      type: Array, // 声明为数组类型
      default: () => [], // 默认值为空数组
    },
  },
  beforeDestroy() {
    // 在组件销毁之前清除 props 中的参数
    this.$props.selectedRegulationNames = null;
    this.$props.selectedFormNames = null;
  },
  name: "CustomFiles",
  inject: ["reload"],
  data() {
    return {
      // activeName: "second",

      // 制度文件数据
      filemanagementList: [],
      // 制度文件中 选中数组传给 node
      idsRegulation: [],
      namesRegulation: [],

      // 表单文件数据
      formsmanagementList: [],
      // 表单文件中 选中数组传给 node
      idsForm: [],
      namesForm: [],

      // 制度查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        regulationsTitle: null,
        formTitle: null,
      },

      // 总条数
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
    };
  },
  created() {
    //获取制度文件数据
    this.getRegularFileData();
    //获取表单文件数据
    this.getFormFileData();
  },
  mounted() {
    // 当组件挂载时，设置已绑定的文件信息为选中状态
    this.setSelectedRegulationNames(
      JSON.parse(JSON.stringify(this.$props.selectedRegulationNames))
    );
    this.setSelectedFormNames(Object.assign([], this.$props.selectedFormNames));
  },
  methods: {
    /** 1.1 查询制度文件列表 */
    getRegularFileData() {
      listFilemanagementAll(this.queryParams).then((response) => {
        this.filemanagementList = response.rows;
        this.total = response.total;
      });
    },

    /** 2.1 保存制度 选择的行数据 */
    handleRegulationSelectionChange(selection) {
      // 选中的行数据
      this.idsRegulation = selection.map((item) => item.regulationsId);
      this.namesRegulation = selection.map((item) => item.fileName);
    },
    //传递给node数据ids&names
    getSelectedRegulationIdsAndNames() {
      return {
        idsRegulation: this.idsRegulation,
        namesRegulation: this.namesRegulation,
      };
    },

    /** 1.3 接收 回显的参数 */
    setSelectedRegulationNames(names) {
      if (names != null) {
        setTimeout(() => {
          this.$nextTick(() => {
            names.forEach((name) => {
              const row = this.filemanagementList.find(
                (item) => item.fileName === name
              );
              if (row) {
                this.$refs.multipleRegulationTable.toggleRowSelection(
                  row,
                  true
                );
              }
            });
          });
        }, 1000);
      }
    },

    /** 2.1 查询表单文件列表 */
    getFormFileData() {
      listFormfilemanagementAll(this.queryParams).then((response) => {
        this.formsmanagementList = response.rows;
        this.total = response.total;
      });
    },

    /** 2.2 保存表单 选择的行数据 */
    handleFormSelectionChange(selection) {
      // 选中的行数据
      this.idsForm = selection.map((item) => item.formId);
      this.namesForm = selection.map((item) => item.formName);
    },
    //传递给node数据ids&names
    getSelectedFormIdsAndNames() {
      return {
        idsForm: this.idsForm,
        namesForm: this.namesForm,
      };
    },

    /** 2.3 接收 回显的参数 */
    setSelectedFormNames(names) {
      if (names != null) {
        setTimeout(() => {
          this.$nextTick(() => {
            names.forEach((name) => {
              const row = this.formsmanagementList.find(
                (item) => item.formName === name
              );
              if (row) {
                this.$refs.multipleFormTable.toggleRowSelection(row, true);
              }
            });
          });
        }, 1000);
      }
    },

    //分页功能相关
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

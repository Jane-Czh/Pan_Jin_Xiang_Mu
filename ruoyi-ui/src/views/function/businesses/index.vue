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
      <el-form-item label="细分业务" prop="subBusinessesName">
        <el-input
          v-model="queryParams.subBusinessesName"
          placeholder="请输入细分业务名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="上级部门" prop="parentDepartment">
        <el-input
          v-model="queryParams.parentDepartment"
          placeholder="请输入上级部门"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="业务模块" prop="parentModule">
        <el-input
          v-model="queryParams.parentModule"
          placeholder="请输入上级业务模块"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <!-- <el-form-item label="逻辑删除flag" prop="isDeleted">
        <el-input
          v-model="queryParams.isDeleted"
          placeholder="请输入逻辑删除flag"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
      <!-- <el-form-item label="描述" prop="description">
        <el-input
          v-model="queryParams.description"
          placeholder="请输入描述"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item> -->
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
          @click="handleAdd"
          v-hasPermi="['system:businesses:add']"
          >新增</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['system:businesses:edit']"
          >修改</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['system:businesses:remove']"
          >删除</el-button
        >
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:businesses:export']"
          >导出</el-button
        >
      </el-col>
      <right-toolbar
        :showSearch.sync="showSearch"
        @queryTable="getList"
      ></right-toolbar>
    </el-row>

    <el-table
      v-loading="loading"
      :data="businessesList"
      @selection-change="handleSelectionChange"
    >
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="序号" align="center" prop="id">
        <template slot-scope="scope">
          <!-- <span>{{ scope.$index + 1 }}</span> -->
          <span>{{ (pageIndex - 1) * pageSize + scope.$index + 1 }}</span>
        </template>
      </el-table-column>
      <!-- <el-table-column label="${comment}" align="center" prop="subbId" /> -->

      <el-table-column
        label="主责部门"
        align="center"
        prop="parentDepartment"
      />
      <el-table-column
        label="业务模块"
        align="center"
        prop="parentModule"
      />
      <el-table-column
        label="细分业务名称"
        align="center"
        prop="subBusinessesName"
      />
      <!-- <el-table-column label="逻辑删除flag" align="center" prop="isDeleted" /> -->
      <el-table-column label="描述" align="center" prop="description" />
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
            v-hasPermi="['system:businesses:edit']"
            >修改</el-button
          >
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['system:businesses:remove']"
            >删除</el-button
          >
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

    <!-- 添加或修改【请填写功能名称】对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item
          label="细分业务名称"
          prop="subBusinessesName"
          required="true"
        >
          <el-input
            v-model="form.subBusinessesName"
            placeholder="请输入细分业务名称"
          />
        </el-form-item>
        <!-- <el-form-item label="上级部门" prop="parentDepartment">
          <el-input
            v-model="form.parentDepartment"
            placeholder="请输入上级部门"
          />
        </el-form-item> -->
        <!-- 1. 可选, 从10个已有的部门中进行选择 -->
        <el-form-item label="上级部门" prop="parentDepartment">
          <el-select
            v-model="form.parentDepartment"
            placeholder="请选择上级部门"
            clearable
            @change="handleDepartmentChange"
          >
            <el-option
              v-for="item in departments"
              :key="item"
              :label="item"
              :value="item"
            />
            <el-option :label="'空'" :value="''" />
          </el-select>
        </el-form-item>

        <!-- 2. 可选, 从已有的业务模块中进行选择 ; 当上级部门被选了, 就只能从对应的业务模块中进行选择 -->
        <el-form-item label="上级业务模块" prop="parentModule">
          <el-select
            v-model="form.parentModule"
            placeholder="请选择上级业务模块"
            clearable
            :disabled="!form.parentDepartment"
          >
            <el-option
              v-for="item in modules"
              :key="item.bm_id"
              :label="item.moduleName"
              :value="item.moduleName"
            />
          </el-select>
        </el-form-item>

        <!-- <el-form-item label="上级业务模块" prop="parentModule">
          <el-input
            v-model="form.parentModule"
            placeholder="请输入上级业务模块"
          />
        </el-form-item> -->

        <!-- <el-form-item label="逻辑删除flag" prop="isDeleted">
          <el-input v-model="form.isDeleted" placeholder="请输入逻辑删除flag" />
        </el-form-item> -->
        <el-form-item label="描述" prop="description">
          <el-input v-model="form.description" placeholder="请输入描述" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {
  listBusinesses,
  getBusinesses,
  delBusinesses,
  addBusinesses,
  updateBusinesses,
} from "@/api/function/businesses";

import { listModules } from "@/api/function/modules";
import { listDept } from "@/api/system/project";

export default {
  name: "Businesses",
  data() {
    return {
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
      // 【请填写功能名称】表格数据
      businessesList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 细分业务 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        subBusinessesName: null,
        parentDepartment: null,
        parentModule: null,
        isDeleted: null,
        description: null,
      },
      // module 查询参数
      moduleQueryParams: {
        pageNum: 1,
        pageSize: 5000,
        moduleName: null,
        parentDepartment: null,
        isDeleted: null,
        description: null,
      },

      // 表单参数
      // form: {},
      form: {
        parentDepartment: "", //上级部门
        parentModule: "", //上级业务模块 --> 细分业务
      },
      modules: [],
      modulesList: [],

      // 表单校验
      rules: {},

      // 分页参数
      pageIndex: 1,
      pageSize: 10,
      totalPage: 0,
      // departments: [
      //   "安环设备科",
      //   "财务科",
      //   "党群办公室",
      //   "供应科",
      //   "技术科",
      //   "企业管理科",
      //   "生产管理科",
      //   "市场科",
      //   "执纪监督室",
      //   "质量科",
      // ],

      departments: [],
      // 查询参数
      queryDeptParams: {
        deptName: undefined,
        status: undefined,
      },
    };
  },
  created() {
    this.getList();
    this.getDeptList();
  },
  methods: {
    /** 查询部门列表 */
    getDeptList() {
      listDept(this.queryDeptParams).then((response) => {
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
    
    async handleDepartmentChange(department) {
      this.form.parentModule = ""; // 重置上级业务模块选择
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

    /** 查询【请填写功能名称】列表 */
    getList() {
      this.loading = true;
      listBusinesses(this.queryParams).then((response) => {
        this.businessesList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        subbId: null,
        subBusinessesName: null,
        parentDepartment: null,
        parentModule: null,
        isDeleted: null,
        description: null,
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
      this.ids = selection.map((item) => item.subbId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加【请填写功能名称】";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const subbId = row.subbId || this.ids;
      getBusinesses(subbId).then((response) => {
        this.form = response.data;
        this.open = true;
        this.title = "修改【请填写功能名称】";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.subbId != null) {
            updateBusinesses(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBusinesses(this.form).then((response) => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const subbIds = row.subbId || this.ids;
      this.$modal
        .confirm(
          '是否确认删除【请填写功能名称】编号为"' + subbIds + '"的数据项？'
        )
        .then(function () {
          return delBusinesses(subbIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download(
        "system/businesses/export",
        {
          ...this.queryParams,
        },
        `businesses_${new Date().getTime()}.xlsx`
      );
    },
  },
};
</script>

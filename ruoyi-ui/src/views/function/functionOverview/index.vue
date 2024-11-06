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
      <!-- 部门 进行搜索  -->
      <el-form-item label="主责部门" prop="mainResponsibleDepartment">
        <el-select
          v-model="queryParams.mainResponsibleDepartment"
          :placeholder="`总数： ${departments.length}`"
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
          :placeholder="`总数： ${modules.length}`"
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
          :placeholder="`总数： ${subBusinesses.length}`"
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
      <el-form-item>
        <el-button
          type="primary"
          icon="el-icon-search"
          size="mini"
          @click="handleQuery"
        >搜索
        </el-button
        >
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery"
        >重置
        </el-button
        >
      </el-form-item>
    </el-form>


    <el-row>
      <el-col :span="8">
        <el-card shadow="hover" class="total-card">
          <!--          ${modules.length}-->
          <div class="title">主责部门总数: {{ departments.length }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="total-card">
          <div class="title">业务模块总数: {{ this.modulesList.length }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="total-card">
          <div class="title">细分业务总数: {{ this.subBusinessesList.length }}</div>
        </el-card>
      </el-col>
    </el-row>


    <el-row>
      <el-col :span="8">
        <el-card shadow="hover" class="first-row-card">
          <div class="title">流程</div>
          <div class="subtitle">A级数量: {{ processACounts }}</div>
          <div class="subtitle">B级数量: {{ processBCounts }}</div>
          <div class="subtitle">C级数量: {{ processCCounts }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="first-row-card">
          <div class="title">制度</div>
          <div class="subtitle">公司级数量: {{ companyRegulationCounts }}</div>
          <div class="subtitle">部门级数量: {{ departmentRegulationCounts }}</div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="first-row-card">
          <div class="title">表单</div>
          <div class="subtitle">数量: {{ formCounts }}</div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col :span="8">
        <el-card shadow="hover" class="second-row-card">
          <el-card>
            <div>A级</div>
            <br>
            <div class="content" ref="scrollContainer">
              <el-card v-for="(item, index) in processAList" :key="item.id" shadow="hover" class="policy-card">
                <div class="policy-item">
                  <span>{{ index + 1 }}.  </span>
                  <span>{{ item.name }}</span>
                </div>
              </el-card>
            </div>
          </el-card>
          <el-card>
            <div>B级</div>
            <br>
            <div class="content" ref="scrollContainer">
              <el-card v-for="(item, index) in processBList" :key="item.id" shadow="hover" class="policy-card">
                <div class="policy-item">
                  <span>{{ index + 1 }}.  </span>
                  <span>{{ item.name }}</span>
                </div>
              </el-card>
            </div>
          </el-card>
          <el-card>
            <div>C级</div>
            <br>
            <div class="content" ref="scrollContainer">
              <el-card v-for="(item, index) in processCList" :key="item.id" shadow="hover" class="policy-card">
                <div class="policy-item">
                  <span>{{ index + 1 }}.  </span>
                  <span>{{ item.name }}</span>
                </div>
              </el-card>
            </div>
          </el-card>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="second-row-card">
          <el-card>
            <div>公司级</div>
            <br>
            <div class="content" ref="scrollContainer">
              <el-card v-for="(item, index) in companyRegulationList" :key="item.id" shadow="hover" class="policy-card">
                <div class="policy-item">
                  <span>{{ index + 1 }}.  </span>
                  <span>{{ item.regulationsTitle }}</span>
                </div>
              </el-card>
            </div>
          </el-card>
          <el-card>
            <div>部门级</div>
            <br>
            <div class="content" ref="scrollContainer">
              <el-card v-for="(item, index) in departmentRegulationList" :key="item.id" shadow="hover"
                       class="policy-card">
                <div class="policy-item">
                  <span>{{ index + 1 }}.  </span>
                  <span>{{ item.regulationsTitle }}</span>
                </div>
              </el-card>
            </div>
          </el-card>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card shadow="hover" class="second-row-card">
          <div class="content" ref="scrollContainer">
            <el-card v-for="(item, index) in formList" :key="item.id" shadow="hover" class="policy-card">
              <div class="policy-item">
                <span>{{ index + 1 }}.  </span>
                <span>{{ item.formTitle }}</span>
              </div>
            </el-card>
          </div>
        </el-card>
      </el-col>
    </el-row>

  </div>
</template>

<script>
import {getProjectByName, listDept, listProject2} from "@/api/system/project";
import {addModules, delModules, getModules, listModules, listModuless, updateModules} from "@/api/function/modules";
import {listBusinessess} from "@/api/function/businesses";
import {listFilemanagement2} from "@/api/file/filemanagement";
import {listFormfilemanagement3} from "@/api/file/formfilemanagement";

export default {
  data() {
    return {

      /* ------流程-------- */
      processList: [],  //流程列表
      processAList: [],  //流程A列表
      processBList: [],  //流程B列表
      processCList: [],  //流程C列表
      processACounts: 0, //A级流程数量
      processBCounts: 0, //B级流程数量
      processCCounts: 0, //C级流程数量

      totalDepartments: 0,
      totalModule: 0,
      totalXifen: 0,

      /* ------表单-------- */
      formList: [],  //表单列表
      formCounts: 0,  //表单数量

      /* ------制度-------- */
      regulationList: [],  //制度列表
      companyRegulationList: [], //公司级制度列表
      departmentRegulationList: [],  //部门级制度列表
      companyRegulationCounts: 0,  //公司级制度数量
      departmentRegulationCounts: 0, //部门级制度数量

      /* ------查询-------- */
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
      //表单展示数据
      formData: {
        //主责部门
        department: "",
        //业务模块
        businessesModules: "",
        //细分业务
        subBusinesses: "",
      },
      modules: [], //过滤后 业务模块 数据
      modulesList: [],//全部的 业务模块 数据
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10000,
        mainResponsibleDepartment: null,
        businesses: null,
        subBusinesses: null
      },

      // 表单参数
      form: {},
      // 显示搜索条件
      showSearch: true,
    };
  },
  created() {
    this.getList();
    this.getDeptList();
    this.handleDepartmentChange();
    this.handleModuleChange();
  },
  methods: {
    /** 查询列表 */
    getList() {
      this.loading = true;
      //查询所有制度
      listFilemanagement2(this.queryParams).then((response) => {
        this.regulationList = response.rows;
        this.companyRegulationList = this.regulationList.filter(item => item.regulationLeval === '公司级');
        this.departmentRegulationList = this.regulationList.filter(item => item.regulationLeval === '部门级');
        this.companyRegulationCounts = this.companyRegulationList.length;
        this.departmentRegulationCounts = this.departmentRegulationList.length;
      });
      //表单查询参数
      const formQueryParams = {
        pageNum: 1,
        pageSize: 10000,
        departmentCategory: this.queryParams.mainResponsibleDepartment,
        businesses: this.queryParams.businesses,
        subBusinesses: this.queryParams.subBusinesses
      };
      //查询所有表单
      listFormfilemanagement3(formQueryParams).then((response) => {
        this.formList = response.rows;
        this.formCounts = response.total;
      });

      // 流程查询参数
      const processQueryParams = {
        pageNum: 1,
        pageSize: 10000,
        department: this.queryParams.mainResponsibleDepartment,
        businessesModules: this.queryParams.businesses,
        subBusinesses: this.queryParams.subBusinesses
      };

      //查询所有流程
      listProject2(processQueryParams).then((response) => {
        this.processList = response;
        this.processAList = this.processList.filter(item => item.level === 'A级');
        this.processBList = this.processList.filter(item => item.level === 'B级');
        this.processCList = this.processList.filter(item => item.level === 'C级');
        console.log("this.processList===>", this.processList);
        this.processACounts = this.processAList.length;
        this.processBCounts = this.processBList.length;
        this.processCCounts = this.processCList.length;
      });

      this.loading = false;
    },

    //搜索
    searchByName() {
      this.loading = true;
      //查询所有制度
      listFilemanagement2(this.queryParams).then((response) => {
        this.regulationList = response.rows;
        this.companyRegulationList = this.regulationList.filter(item => item.regulationLeval === '公司级');
        this.departmentRegulationList = this.regulationList.filter(item => item.regulationLeval === '部门级');
        this.companyRegulationCounts = this.companyRegulationList.length;
        this.departmentRegulationCounts = this.departmentRegulationList.length;
      });
      //表单查询参数
      const formQueryParams = {
        pageNum: 1,
        pageSize: 10000,
        departmentCategory: this.queryParams.mainResponsibleDepartment,
        businesses: this.queryParams.businesses,
        subBusinesses: this.queryParams.subBusinesses
      };
      //查询所有表单
      listFormfilemanagement3(formQueryParams).then((response) => {
        this.formList = response.rows;
        this.formCounts = response.total;
      });


      this.processList = [];
      // 流程查询参数
      const processQueryParams = {
        pageNum: 1,
        pageSize: 10000,
        department: this.queryParams.mainResponsibleDepartment,
        businessesModules: this.queryParams.businesses,
        subBusinesses: this.queryParams.subBusinesses
      };
      getProjectByName(processQueryParams).then((response) => {
        console.log("processList123===>", response);

        this.processList = response;
        console.log("this.projectList===>", this.processList);
        this.processAList = this.processList.filter(item => item.level === 'A级');
        this.processBList = this.processList.filter(item => item.level === 'B级');
        this.processCList = this.processList.filter(item => item.level === 'C级');
        console.log("this.processListA===>", this.processAList);
        this.processACounts = this.processAList.length;
        this.processBCounts = this.processBList.length;
        this.processCCounts = this.processCList.length;


      });
    },
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

    // 表单重置
    reset() {
      this.form = {
        bmId: null,
        moduleName: null,
        parentDepartment: null,
        isDeleted: null,
        description: null,
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.searchByName();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.getList();
    },
    async handleDepartmentChange(department) {
      this.formData.businessesModules = ""; // 重置上级业务模块选择
      this.modules = []; // 清空之前的模块
      await listModuless(this.moduleQueryParams).then((response) => {
        this.modulesList = response.rows;
      });
      if (department) {
        try {
          for (let i = 0; i < this.modulesList.length; i++) {
            console.log("123===" + this.modulesList[i].parentDepartment);
            // 根据部门字段进行筛选
            if (this.modulesList[i].parentDepartment === department) {
              this.queryParams.businesses = '';
              this.queryParams.subBusinesses = '';
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

      // 获取所有细分业务
      await listBusinessess(this.xifenQueryParams).then((response) => {
        this.subBusinessesList = response.rows;
      });
      if (module) {
        try {
          for (let i = 0; i < this.subBusinessesList.length; i++) {
            console.log("12321===" + this.subBusinessesList[i]);
            // 根据业务模块字段进行筛选
            if (this.subBusinessesList[i].parentModule === module) {
              this.queryParams.subBusinesses = '';
              this.subBusinesses.push(this.subBusinessesList[i]);
            }
          }

          console.log("this.subBusinesses===", this.subBusinesses);
        } catch (error) {
          console.error("Failed to fetch sub-businesses:", error);
        }
      }
    },
  }
};
</script>

<style scoped>

.app-container {
  height: 100%;

}

.title {
  font-size: 1.2em; /* 字体略大 */
}

.total-card {
  margin: 10px;
  width: 90%;
  background-color: #f9f9f9;
  transition: box-shadow 0.3s ease;
  height: 50px; /* 第一行卡片的高度 */
}

.first-row-card {
  margin: 10px;
  width: 90%;
  background-color: #f9f9f9;
  transition: box-shadow 0.3s ease;
  height: 130px; /* 第一行卡片的高度 */
}

.first-row-card:hover {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}

.second-row-card {
  margin: 10px;
  width: 90%;
  background-color: #f9f9f9;
  transition: box-shadow 0.3s ease;
}

.second-row-card:hover {
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12), 0 0 6px rgba(0, 0, 0, 0.04);
}

.content {
  overflow-y: auto;
  max-height: 200px; /* 可根据需要调整最大高度，超过则滚动条出现 */
  padding: 10px;
}


</style>

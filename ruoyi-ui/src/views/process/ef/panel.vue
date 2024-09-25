<template>
  <div v-if="easyFlowVisible" style="height: calc(100vh)">
    <el-row>
      <!--顶部工具菜单-->
      <el-col :span="24">
        <div class="ef-tooltar">
          <el-link type="primary" :underline="false">{{ data.name }}</el-link>
          <el-divider direction="vertical"></el-divider>

          <!-- 流程json下载 -->
          <!-- <el-divider direction="vertical"></el-divider>
          <el-button
            type="text"
            icon="el-icon-download"
            size="large"
            @click="downloadData"
          ></el-button>  -->

          <!-- <el-divider direction="vertical"></el-divider> -->
          <!-- <el-button
            type="text"
            icon="el-icon-plus"
            size="large"
            @click="zoomAdd"
          ></el-button>
          <el-divider direction="vertical"></el-divider>
          <el-button
            type="text"
            icon="el-icon-minus"
            size="large"
            @click="zoomSub"
          ></el-button>
          <el-divider direction="vertical"></el-divider> -->

          <!-- <el-divider direction="vertical"></el-divider> -->

          <!-- <el-button
            type="info"
            plain
            round
            icon="el-icon-document"
            @click="dataInfo"
            size="mini"
            >流程信息</el-button
          > -->

          <!-- <el-button
            type="primary"
            plain
            round
            @click="dataReloadA"
            icon="el-icon-refresh"
            size="mini"
            >流程模板A</el-button
          >
          <el-button
            type="primary"
            plain
            round
            @click="dataReloadB"
            icon="el-icon-refresh"
            size="mini"
            >流程模板B</el-button
          > -->
          <!-- <el-divider direction="vertical"></el-divider> -->

          <el-button type="primary" plain size="mini" @click="toggleDialog"
            >自定义模板库</el-button
          >
          <!-- 弹出的面板 -->
          <el-dialog
            :visible.sync="dialogSBDSVisible"
            title="用户自定义模板库"
            style="height: 50%"
          >
            <el-table :data="modelProjectList" height="250">
              <el-table-column
                type="index"
                label="序号"
                style="width: 25%"
                align="center"
              ></el-table-column>

              <el-table-column
                label="流程模板名称"
                prop="name"
                style="width: 25%"
                align="center"
              >
              </el-table-column>

              <el-table-column label="操作" align="center" style="width: 25%">
                <template slot-scope="scope">
                  <el-button
                    @click="dataReloadSB(JSON.parse(scope.row.data))"
                    type="primary"
                    size="small"
                    plain
                    icon="el-icon-refresh"
                  >
                    应用
                  </el-button>

                  <el-button
                    type="danger"
                    size="small"
                    plain
                    icon="el-icon-delete"
                    @click="handleDelete(scope.row)"
                    v-hasPermi="['process:ef:deleteModel']"
                    >删除</el-button
                  >
                </template>
              </el-table-column>

              <el-table-column
                label="备注"
                prop="zz"
                style="width: 25%"
                align="center"
              >
              </el-table-column>
            </el-table>
          </el-dialog>

          <el-divider direction="vertical"></el-divider>

          <el-button
            type="success"
            plain
            icon="el-icon-document-add"
            @click="openSBDialog()"
            v-hasPermi="['process:ef:addModel']"
            size="mini"
            >保存为模板</el-button
          >
          <el-divider direction="vertical"></el-divider>

          <!-- 右侧button -->
          <div style="float: right; margin-right: 5px">
            <el-divider direction="vertical"></el-divider>

            <el-button
              type="text"
              icon="el-icon-document"
              @click="openHelp"
              size="mini"
              >使用说明</el-button
            >
            <el-divider direction="vertical"></el-divider>
            <!-- 删除节点、连线等 -->
            <el-button
              type="text"
              icon="el-icon-delete"
              size="large"
              @click="deleteElement"
              :disabled="!this.activeElement.type"
            ></el-button>
            <el-divider direction="vertical"></el-divider>

            <!-- <el-button type="primary" plain round @click="dataReloadC" icon="el-icon-refresh" size="mini">切换流程C</el-button> -->
            <el-button
              type="primary"
              plain
              round
              @click="dataReloadD"
              icon="el-icon-refresh"
              size="mini"
              >自定义样式</el-button
            >
            <el-divider direction="vertical"></el-divider>
            <!-- <el-button type="primary" plain round @click="dataReloadE" icon="el-icon-refresh" size="mini">力导图</el-button> -->

            <!-- 弹出面板 设置流程绑定文件(制度&表单) -->
            <el-button
              v-if="
                this.regulationFiles.length == 0 && this.formFiles.length == 0
              "
              type="primary"
              plain
              round
              icon="el-icon-folder-add"
              @click="bandFiles()"
              size="mini"
              >支撑文件绑定</el-button
            >
            <el-button
              v-else
              type="primary"
              round
              icon="el-icon-folder-add"
              @click="bandMoreFiles(), temp()"
              size="mini"
              >更改已绑定文件</el-button
            >
            <el-divider direction="vertical"></el-divider>
            <!-- 对流程进行保存 v-hasPermi="['process:ef:add']" -->
            <el-button
              type="success"
              plain
              round
              icon="el-icon-check"
              @click="openDialog()"
              size="mini"
              v-hasPermi="['process:ef:add']"
              >保存流程</el-button
            >
          </div>
          <!-- 弹出对话框提示填写项目流程名称 绑定文件 -->
          <!-- <el-dialog
            title="填写流程名称"
            :visible.sync="dialogVisible"
            width="30%"
            :close-on-click-modal="false"
            destroy-on-close="true"
          >
            <el-form ref="form" :model="formData" label-width="80px">
              <el-form-item label="流程名称" required>
                <el-input
                  v-model="formData.project_Name"
                  @input="validateSB1"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="save">保存</el-button>
            </div>
          </el-dialog> -->

          <el-dialog
            title="填写流程名称"
            :visible.sync="dialogVisible"
            width="30%"
            :close-on-click-modal="false"
            destroy-on-close="true"
          >
            <el-form ref="form" :model="formData" label-width="80px">
              <el-form-item label="流程名称" required>
                <el-input
                  v-model="formData.project_Name"
                  @input="validateSB1"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>
              <el-form-item label="流程编号" required>
                <el-input
                  v-model="formData.number"
                  @input="validateNumber"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>

              <!-- 1\部门 -->
              <el-form-item label="主责部门" required="true">
                <el-select
                  v-model="formData.department"
                  placeholder="请选择部门"
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

              <el-form-item label="流程等级" required>
                <el-select
                  v-model="formData.level"
                  placeholder="请选择流程等级"
                >
                  <el-option
                    v-for="level in levels"
                    :key="level"
                    :label="level"
                    :value="level"
                  ></el-option>
                </el-select>
              </el-form-item>
              <!-- -------------------------------------------------- -->
              <!-- 2. 可选, 从已有的业务模块中进行选择 ; 当上级部门被选了, 就只能从对应的业务模块中进行选择 -->
              <el-form-item label="业务模块">
                <el-select
                  v-model="formData.businessesModules"
                  placeholder="请选择业务模块"
                  clearable
                  :disabled="!formData.department"
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

              <!-- 3. 可选, 从已有的细分业务中进行选择 ; 当上级业务模块被选了, 就只能从对应的细分业务中进行选择 -->
              <el-form-item label="细分业务">
                <el-select
                  v-model="formData.subBusinesses"
                  placeholder="请选择细分业务"
                  clearable
                  :disabled="!formData.businessesModules"
                >
                  <el-option
                    v-for="item in subBusinesses"
                    :key="item.subb_id"
                    :label="item.subBusinessesName"
                    :value="item.subBusinessesName"
                  />
                </el-select>
              </el-form-item>
              <!-- -------------------------------------------------- -->

              <el-form-item label="流程目的">
                <el-input
                  v-model="formData.purpose"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>
              <el-form-item label="适用范围">
                <el-input
                  v-model="formData.applicationScope"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="save">保存</el-button>
            </div>
          </el-dialog>

          <!--  -------------------------------------------  -->
          <!-- 弹出对话框提示填写项目流程模板sbsbsb-->
          <el-dialog
            title="填写自定义流程模板名称"
            :visible.sync="dialogSBVisible"
            width="30%"
            append-to-body
          >
            <el-form ref="form" :model="formData" label-width="80px">
              <el-form-item label="名称" required>
                <el-input
                  v-model="formData.project_Name"
                  @input="validateSB1"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>
              <el-form-item label="备注">
                <el-input
                  v-model="formData.marks"
                  @input="validateSB2"
                  maxlength="20"
                  show-word-limit
                ></el-input>
              </el-form-item>
            </el-form>

            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogSBVisible = false">取消</el-button>
              <el-button type="primary" @click="saveModel">保存</el-button>
            </div>
          </el-dialog>
          <!--  -------------------------------------------  -->

          <!-- 绑定文件的dialog ------------------------------------------- -->
          <el-dialog
            title="流程绑定文件(请选择需要绑定的文件)"
            :visible.sync="dialogFilesVisible"
            width="60%"
            style="height: 1000px"
            :before-close="handleClose"
            destroy-on-close="true"
          >
            <!-- ref 组件 -->
            <div v-if="this.dialogFilesVisible">
              <custom-files
                ref="customFiles"
                :selectedFormNames="this.selectedFormsTemp"
                :selectedRegulationNames="this.selectedRegulationTemp"
              ></custom-files>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button @click="cancle()">取消选择</el-button>
              <el-button type="primary" @click="confirmDialog()"
                >确 定</el-button
              >
            </span>
          </el-dialog>

          <!-- 继续绑定制度文件的dialog -->
          <el-dialog
            title="继续绑定文件"
            :visible.sync="dialogMoreFilesVisible"
            width="60%"
            style="height: 1000px"
            :before-close="handleClose"
            destroy-on-close="true"
          >
            <!-- ref 组件 el-table 显示制度文件的数据   v-on:backShow="backShow" -->
            <div v-if="this.dialogMoreFilesVisible">
              <custom-files
                ref="customFiles"
                :selectedFormNames="this.selectedFormsTemp"
                :selectedRegulationNames="this.selectedRegulationTemp"
              ></custom-files>
            </div>
            <span slot="footer" class="dialog-footer">
              <el-button @click="cancleBand()">取消绑定</el-button>
              <el-button type="primary" @click="confirmDialog()"
                >确 定</el-button
              >
            </span>
          </el-dialog>

          <!-- 绑定文件 ------------------------------------------- over -->
        </div>
      </el-col>
    </el-row>
    <!-- 主体部分 -->
    <div style="display: flex; height: calc(100% - 47px)">
      <!-- 左侧 nodeMenu -->
      <div style="width: 230px; border-right: 1px solid #dce3e8">
        <node-menu @addNode="addNode" ref="nodeMenu"></node-menu>
      </div>
      <!-- 流程部分 -->
      <div id="efContainer" ref="efContainer" class="container" v-flowDrag>
        <template v-for="node in data.nodeList">
          <flow-node
            :id="node.id"
            :key="node.id"
            :node="node"
            :activeElement="activeElement"
            @changeNodeSite="changeNodeSite"
            @nodeRightMenu="nodeRightMenu"
            @clickNode="clickNode"
            @openFileDialog="openFileDialog(node)"
          >
          </flow-node>
        </template>
        <!-- 给画布一个默认的宽度和高度 -->
        <div style="position: absolute; top: 2000px; left: 2000px">&nbsp;</div>
      </div>

      <!-- 右侧表单 -->
      <div
        style="
          width: 300px;
          border-left: 1px solid #dce3e8;
          background-color: #fbfbfb;
        "
      >
        <flow-node-form
          ref="nodeForm"
          @setLineLabel="setLineLabel"
          @repaintEverything="repaintEverything"
        ></flow-node-form>
      </div>
    </div>
    <!-- 流程数据详情 转到info.vue页面-->
    <flow-info v-if="flowInfoVisible" ref="flowInfo" :data="data"></flow-info>
    <flow-info-model ref="flowInfoModel" :data="data"></flow-info-model>
    <flow-help v-if="flowHelpVisible" ref="flowHelp"></flow-help>
  </div>
</template>

<script>
import draggable from "vuedraggable";
// import { jsPlumb } from 'jsplumb'
// 使用修改后的jsplumb
import "./jsplumb";
import { easyFlowMixin } from "@/views/process/ef/mixins";
import flowNode from "@/views/process/ef/node";
import nodeMenu from "@/views/process/ef/node_menu";
import FlowInfo from "@/views/process/ef/info";
import FlowInfoModel from "@/views/process/ef/infoModel";
import FlowHelp from "@/views/process/ef/help";
import FlowNodeForm from "./node_form";
import lodash from "lodash";
import { getDataA } from "./data_A";
import { getDataB } from "./data_B";
import { getDataC } from "./data_C";
import { getDataD } from "./data_D";
import { getDataE } from "./data_E";
import { ForceDirected } from "./force-directed";
import axios from "axios";
import { nanoid } from "nanoid";
//绑定文件
import CustomFiles from "./CustomFiles.vue";
//获取用户信息-用户名
import { getUserProfile } from "@/api/system/user";
//获取用户信息-部门
// import { getDept } from "@/api/system/dept";
import { getDept } from "@/api/system/project";
//流程名称重名检测, 保存project数据, 保存节点数据, 保存连线数据
import {
  listProject2,
  saveProject,
  saveSBModelProject,
  listSBModelProject,
  delSBProject,
  saveNode,
  saveLine,
} from "@/api/system/project";
//业务模块api，
import { listModuless } from "@/api/function/modules";
//细分业务api
import { listBusinessess } from "@/api/function/businesses";
import { listDept } from "@/api/system/project";
export default {
  name: "Project",
  inject: ["reload"],
  data() {
    return {
      subBusinessesList: [],
      subBusinesses: [], //过滤后的细分业务列表

      // 业务模块 module 查询参数
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

      // 表单参数
      // form: {
      //   parentDepartment: "", //上级部门
      //   parentModule: "", //上级业务模块
      // },
      modules: [], //过滤后的上级 业务模块
      modulesList: [],
      //部门
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
      levels: ["A级", "B级", "C级"],
      //用户名
      uploadUsername: null,
      //所属部门
      departmentCategory: null,
      //---------------------------------------
      //绑定的制度文件names
      regulationFiles: [],
      idsRegulation: null,
      selectedRegulationTemp: [],

      //绑定的表单文件names
      formFiles: [],
      idsForm: null,
      selectedFormsTemp: [],

      emptyFile: [],

      //文件绑定的dialog
      dialogFilesVisible: false,
      //文件继续绑定的dialog
      dialogMoreFilesVisible: false,

      //填写 流程项目名称的提示框
      dialogVisible: false,
      //填写 流程模板SB名称的提示框
      dialogSBVisible: false,
      //流程模板SBDS el-table
      dialogSBDSVisible: false,
      //保存输入的流程名称
      formData: {
        project_Name: "",
        //备注
        formData: "",
        //流程编号
        number: "",
        //主责部门
        department: "",
        //流程等级
        level: "",
        //流程目的
        purpose: "",
        //适用范围
        applicationScope: "",

        //业务模块
        businessesModules: "",
        //细分业务
        subBusinesses: "",
      },

      // 流程表格数据
      projectList: [],
      // 流程模板数据
      modelProjectList: [],

      //项目名称参数
      project_Id: null,
      // jsPlumb 实例
      jsPlumb: null,
      // 控制画布销毁
      easyFlowVisible: true,
      // 控制流程数据显示与隐藏
      flowInfoVisible: false,
      // 是否加载完毕标志位
      loadEasyFlowFinish: false,
      flowHelpVisible: false,
      // 数据
      data: {},
      // 激活的元素、可能是节点、可能是连线
      activeElement: {
        // 可选值 node 、line
        type: undefined,
        // 节点ID
        nodeId: undefined,
        // 连线ID
        sourceId: undefined,
        targetId: undefined,
      },
      zoom: 0.5,
    };
  },
  // 一些基础配置移动该文件中
  mixins: [easyFlowMixin],
  components: {
    draggable,
    flowNode,
    nodeMenu,
    FlowInfo,
    FlowInfoModel,
    FlowNodeForm,
    FlowHelp,
    //绑定文件
    CustomFiles,
  },
  directives: {
    flowDrag: {
      bind(el, binding, vnode, oldNode) {
        if (!binding) {
          return;
        }
        el.onmousedown = (e) => {
          if (e.button == 2) {
            // 右键不管
            return;
          }
          //  鼠标按下，计算当前原始距离可视区的高度
          let disX = e.clientX;
          let disY = e.clientY;
          el.style.cursor = "move";

          document.onmousemove = function (e) {
            // 移动时禁止默认事件
            e.preventDefault();
            const left = e.clientX - disX;
            disX = e.clientX;
            el.scrollLeft += -left;

            const top = e.clientY - disY;
            disY = e.clientY;
            el.scrollTop += -top;
          };

          document.onmouseup = function (e) {
            el.style.cursor = "auto";
            document.onmousemove = null;
            document.onmouseup = null;
          };
        };
      },
    },
  },
  mounted() {
    this.jsPlumb = jsPlumb.getInstance();
    this.$nextTick(() => {
      // 默认加载流程A的数据(模板)、在这里可以根据具体的业务返回符合流程数据格式的数据即可
      this.dataReload(getDataB());
    });
    //获取当前用户信息
    this.getUserInfo();
    //获取现有流程名称
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
    // 通过 department部门 限制选择:  业务模块内容 this.modules
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

    // ---------------------------------------
    validateSB1() {
      const regex = /^[\u4e00-\u9fa5\dA-Za-z.\(\)\-（）]*$/;
      this.formData.project_Name = this.formData.project_Name
        .split("")
        .filter((char) => regex.test(char))
        .join("");
    },
    validateSB2() {
      const regex = /^[\u4e00-\u9fa5\dA-Za-z.\(\)\-（）]*$/;
      this.formData.project_Name = this.formData.project_Name
        .split("")
        .filter((char) => regex.test(char))
        .join("");
    },
    toggleDialog() {
      this.dialogSBDSVisible = !this.dialogSBDSVisible;
    },
    // 调用接口获取用户信息
    getUserInfo() {
      getUserProfile()
        .then((response) => {
          // 处理成功的情况
          // console.log("成功获取用户信息:", response.data);
          const userInfo = response.data; // 假设返回的用户信息对象包含 createUsername 和 departmentCategory 字段
          // 填充到对应的输入框中
          this.uploadUsername = userInfo.userName;
          //根据部门id获取部门名称
          getDept(userInfo.deptId).then((response) => {
            const deptInfo = response.data;
            this.departmentCategory = deptInfo.deptName;
          });
        })
        .catch((error) => {
          // 处理失败的情况
          console.error("获取用户信息失败:", error);
        });
    },

    //------------------------------------------------
    handleClose(done) {
      // 不要执行阻止关闭的操作，直接调用 done() 函数
      done();
    },

    /**流程绑定文件(制度&表单)*/

    // 无绑定文件时,进行文件的绑定
    bandFiles() {
      this.dialogFilesVisible = true;
    },
    // 已经绑定了文件,进行修改
    bandMoreFiles() {
      this.dialogMoreFilesVisible = true;
    },

    //取消选择-对应节点还未绑定文件时
    cancle() {
      this.regulationFiles = this.emptyFile;
      this.formFiles = this.emptyFile;

      this.selectedRegulationTemp = this.emptyFile;
      this.selectedFormsTemp = this.emptyFile;

      this.dialogFilesVisible = false;
    },
    //取消绑定-对应节点已经绑定了文件想要取绑
    cancleBand() {
      this.dialogMoreFilesVisible = false;

      this.regulationFiles = this.emptyFile;
      this.formFiles = this.emptyFile;

      this.selectedRegulationTemp = this.emptyFile;
      this.selectedFormsTemp = this.emptyFile;

      // 取消绑定后显示提示消息
      this.$message({
        type: "success",
        message: "取消绑定成功！",
      });
    },

    temp() {
      this.regulationFiles = this.selectedRegulationTemp;
      // console.log("temp1 == ", this.regulationFiles);
      this.formFiles = this.selectedFormsTemp;
      // console.log("temp 2 ", this.formFiles);
    },

    //展示选择的文件名的dialog 点击确定后进行[绑定文件]  -- regulationFiles 、 formFiles
    confirmDialog() {
      this.$confirm("确认进行绑定？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          /**处理制度文件 */
          // 调用 CustomFiles 组件的方法来获取 选中的 [制度文件] 的 [idsRegulation] 和 [namesRegulation] 数据
          let { idsRegulation, namesRegulation } =
            this.$refs.customFiles.getSelectedRegulationIdsAndNames();
          // 将获取到的filenames给本地的展示变量：this.regulationFiles
          this.regulationFiles = namesRegulation;
          this.selectedRegulationTemp = this.regulationFiles;
          this.idsRegulation = idsRegulation;

          /**处理表单文件 */
          let { idsForm, namesForm } =
            this.$refs.customFiles.getSelectedFormIdsAndNames();
          // 将获取到的filenames给本地的展示变量：this.formFiles
          this.formFiles = namesForm;
          this.selectedFormsTemp = this.formFiles;
          this.idsForm = idsForm;

          // console.log("hihihi111111", this.idsRegulation);
          // console.log("hihihi222222", this.regulationFiles);

          // console.log("hihihi333333", this.idsForm);
          // console.log("hihihi444444", this.formFiles);

          //将文件名[idsRegulation]绑定到 流程的 字段上
          if (
            this.regulationFiles.length === 0 &&
            this.formFiles.length === 0
          ) {
            this.$message.warning("未选择文件,无法绑定！请先选择文件再绑定！");
          } else {
            // this.selectedTemp = this.selectedFileNames;
            //TODO 保存流程save 的时候 将 idsRegulation、idsForm 其保存到 流程的指定字段中

            this.dialogFilesVisible = false;
            this.dialogMoreFilesVisible = false;
            // 绑定后显示提示消息
            this.$message({
              type: "success",
              message: "绑定成功！",
            });
          }
        })
        .catch(() => {
          // 用户点击取消按钮的操作
          // 在此处可以执行确认绑定的逻辑

          this.regulationFiles = this.emptyFile;
          this.formFiles = this.emptyFile;

          this.dialogFilesVisible = true;
          this.dialogFilesVisible = false;
          this.dialogMoreFilesVisible = false;

          // 清除选择的文件名
          // this.selectedFileNames = this.selectedFileName;
        });
    },

    // backShow() {
    //   let { idsRegulation, namesRegulation } =
    //     this.$refs.customFiles.getSelectedRegulationIdsAndNames();
    //   // 将获取到的filenames给本地的展示变量：this.regulationFiles
    //   this.regulationFiles = namesRegulation;
    //   this.selectedRegulationTemp = this.regulationFiles;
    //   this.idsRegulation = idsRegulation;

    //   /**处理表单文件 */
    //   let { idsForm, namesForm } =
    //     this.$refs.customFiles.getSelectedFormIdsAndNames();
    //   // 将获取到的filenames给本地的展示变量：this.formFiles
    //   this.formFiles = namesForm;
    //   this.selectedFormsTemp = this.formFiles;
    //   this.idsForm = idsForm;

    //   console.log("hihihi111111", this.idsRegulation);
    //   console.log("hihihi222222", JSON.parse(JSON.stringify(this.regulationFiles)));

    //   console.log("hihihi333333", this.idsForm);
    //   console.log("hihihi444444", this.formFiles);
    // },

    // 返回唯一标识
    getUUID() {
      return Math.random().toString(36).substr(3, 10);
    },

    //流程创建
    jsPlumbInit() {
      this.jsPlumb.ready(() => {
        // 导入默认配置
        this.jsPlumb.importDefaults(this.jsplumbSetting);
        // 会使整个jsPlumb立即重绘。
        this.jsPlumb.setSuspendDrawing(false, true);
        // 初始化节点
        this.loadEasyFlow();
        // 单点击了连接线, https://www.cnblogs.com/ysx215/p/7615677.html
        this.jsPlumb.bind("click", (conn, originalEvent) => {
          this.activeElement.type = "line";
          this.activeElement.sourceId = conn.sourceId;
          this.activeElement.targetId = conn.targetId;
          this.$refs.nodeForm.lineInit({
            from: conn.sourceId,
            to: conn.targetId,
            label: conn.getLabel(),
          });
        });
        // 连线
        this.jsPlumb.bind("connection", (evt) => {
          let from = evt.source.id;
          let to = evt.target.id;
          if (this.loadEasyFlowFinish) {
            this.data.lineList.push({ from: from, to: to });
          }
        });

        // 删除连线回调
        this.jsPlumb.bind("connectionDetached", (evt) => {
          this.deleteLine(evt.sourceId, evt.targetId);
        });

        // 改变线的连接节点
        this.jsPlumb.bind("connectionMoved", (evt) => {
          this.changeLine(evt.originalSourceId, evt.originalTargetId);
        });

        // 连线右击
        this.jsPlumb.bind("contextmenu", (evt) => {
          console.log("contextmenu", evt);
        });

        // 连线
        this.jsPlumb.bind("beforeDrop", (evt) => {
          let from = evt.sourceId;
          let to = evt.targetId;
          if (from === to) {
            this.$message.error("节点不支持连接自己");
            return false;
          }
          if (this.hasLine(from, to)) {
            this.$message.error("该关系已存在,不允许重复创建");
            return false;
          }
          if (this.hashOppositeLine(from, to)) {
            this.$message.error("不支持两个节点之间连线回环");
            return false;
          }
          this.$message.success("连接成功");
          return true;
        });

        // beforeDetach
        this.jsPlumb.bind("beforeDetach", (evt) => {
          console.log("beforeDetach", evt);
        });
        this.jsPlumb.setContainer(this.$refs.efContainer);
      });
    },

    // 加载流程图
    loadEasyFlow() {
      // 初始化[节点]
      for (var i = 0; i < this.data.nodeList.length; i++) {
        let node = this.data.nodeList[i];
        // 设置源点，可以拖出线连接其他节点
        this.jsPlumb.makeSource(
          node.id,
          lodash.merge(this.jsplumbSourceOptions, {})
        );
        // // 设置目标点，其他源点拖出的线可以连接该节点
        this.jsPlumb.makeTarget(node.id, this.jsplumbTargetOptions);
        if (!node.viewOnly) {
          this.jsPlumb.draggable(node.id, {
            containment: "parent",
            stop: function (el) {
              // 拖拽节点结束后的对调
              console.log("拖拽结束: ", el);
            },
          });
        }
      }
      // 初始化[连线]
      for (var i = 0; i < this.data.lineList.length; i++) {
        let line = this.data.lineList[i];
        var connParam = {
          source: line.from,
          target: line.to,
          label: line.label ? line.label : "",
          connector: line.connector ? line.connector : "",
          anchors: line.anchors ? line.anchors : undefined,
          paintStyle: line.paintStyle ? line.paintStyle : undefined,
        };
        this.jsPlumb.connect(connParam, this.jsplumbConnectOptions);
      }
      this.$nextTick(function () {
        this.loadEasyFlowFinish = true;
      });
    },

    // 设置连线条件
    setLineLabel(from, to, label) {
      var conn = this.jsPlumb.getConnections({
        source: from,
        target: to,
      })[0];
      if (!label || label === "") {
        conn.removeClass("flowLabel");
        conn.addClass("emptyFlowLabel");
      } else {
        conn.addClass("flowLabel");
      }
      conn.setLabel({
        label: label,
      });
      this.data.lineList.forEach(function (line) {
        if (line.from == from && line.to == to) {
          line.label = label;
        }
      });
    },
    // 删除激活的元素
    deleteElement() {
      if (this.activeElement.type === "node") {
        this.deleteNode(this.activeElement.nodeId);
      } else if (this.activeElement.type === "line") {
        this.$confirm("确定删除所点击的线吗?", "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning",
        })
          .then(() => {
            var conn = this.jsPlumb.getConnections({
              source: this.activeElement.sourceId,
              target: this.activeElement.targetId,
            })[0];
            this.jsPlumb.deleteConnection(conn);
          })
          .catch(() => {});
      }
    },
    // 删除线
    deleteLine(from, to) {
      this.data.lineList = this.data.lineList.filter(function (line) {
        if (line.from == from && line.to == to) {
          return false;
        }
        return true;
      });
    },
    // 改变连线
    changeLine(oldFrom, oldTo) {
      this.deleteLine(oldFrom, oldTo);
    },
    // 改变节点的位置
    changeNodeSite(data) {
      for (var i = 0; i < this.data.nodeList.length; i++) {
        let node = this.data.nodeList[i];
        if (node.id === data.nodeId) {
          node.left = data.left;
          node.top = data.top;
        }
      }
    },
    /**
     * 拖拽结束后添加新的节点
     * @param evt
     * @param nodeMenu 被添加的节点对象
     * @param mousePosition 鼠标拖拽结束的坐标
     */
    addNode(evt, nodeMenu, mousePosition) {
      var screenX = evt.originalEvent.clientX,
        screenY = evt.originalEvent.clientY;
      let efContainer = this.$refs.efContainer;
      var containerRect = efContainer.getBoundingClientRect();
      var left = screenX,
        top = screenY;
      // 计算是否拖入到容器中
      if (
        left < containerRect.x ||
        left > containerRect.width + containerRect.x ||
        top < containerRect.y ||
        containerRect.y > containerRect.y + containerRect.height
      ) {
        this.$message.error("请把节点拖入到画布中");
        return;
      }
      left = left - containerRect.x + efContainer.scrollLeft;
      top = top - containerRect.y + efContainer.scrollTop;
      // 居中
      left -= 85;
      top -= 16;

      // 动态生成名字
      var origName = nodeMenu.name;
      var nodeName = origName;
      var index = 1;
      while (index < 10000) {
        var repeat = false;
        for (var i = 0; i < this.data.nodeList.length; i++) {
          let node = this.data.nodeList[i];
          if (node.name === nodeName) {
            nodeName = origName + index;
            repeat = true;
          }
        }
        if (repeat) {
          index++;
          continue;
        }
        break;
      }

      var nodeId = this.getUUID();
      // 初始化拖动的节点信息
      var node = {
        id: nodeId,
        name: nodeName,
        type: "no",
        left: left + "px",
        top: top + "px",
        ico: nodeMenu.ico,
        state: "no",
      };
      /**
       * 这里可以进行业务判断、是否能够添加该节点
       */
      this.data.nodeList.push(node);
      this.$nextTick(function () {
        this.jsPlumb.makeSource(nodeId, this.jsplumbSourceOptions);
        this.jsPlumb.makeTarget(nodeId, this.jsplumbTargetOptions);
        this.jsPlumb.draggable(nodeId, {
          containment: "parent",
          stop: function (el) {
            // 拖拽节点结束后的对调
            console.log("拖拽结束: ", el);
          },
        });
      });
    },
    /**
     * 删除节点
     * @param nodeId 被删除节点的ID
     */
    deleteNode(nodeId) {
      this.$confirm("确定要删除选中的节点" + "?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        closeOnClickModal: false,
      })
        .then(() => {
          /**
           * 这里需要进行业务判断，是否可以删除
           */
          this.data.nodeList = this.data.nodeList.filter(function (node) {
            if (node.id === nodeId) {
              // 伪删除，将节点隐藏，否则会导致位置错位
              // node.show = false
              return false;
            }
            return true;
          });
          this.$nextTick(function () {
            this.jsPlumb.removeAllEndpoints(nodeId);
          });

          // 删除后显示提示消息
          this.$message({
            type: "success",
            message: "删除成功！",
          });
        })
        .catch(() => {});
      return true;
    },

    //点击节点
    clickNode(nodeId) {
      this.activeElement.type = "node";
      this.activeElement.nodeId = nodeId;

      this.$refs.nodeForm.nodeInit(this.data, nodeId);
    },

    // 是否具有该线
    hasLine(from, to) {
      for (var i = 0; i < this.data.lineList.length; i++) {
        var line = this.data.lineList[i];
        if (line.from === from && line.to === to) {
          return true;
        }
      }
      return false;
    },
    // 是否含有相反的线
    hashOppositeLine(from, to) {
      return this.hasLine(to, from);
    },
    nodeRightMenu(nodeId, evt) {
      this.menu.show = true;
      this.menu.curNodeId = nodeId;
      this.menu.left = evt.x + "px";
      this.menu.top = evt.y + "px";
    },
    repaintEverything() {
      this.jsPlumb.repaint();
    },

    // 流程数据信息 info.vue页面
    dataInfo() {
      this.flowInfoVisible = true;
      this.$nextTick(function () {
        this.$refs.flowInfo.init();
      });
    },

    // 加载流程图 数据：node、line
    dataReload(data) {
      this.easyFlowVisible = false;
      //流程图-节点数据
      this.data.nodeList = [];
      //流程图-边数据
      this.data.lineList = [];

      this.$nextTick(() => {
        data = lodash.cloneDeep(data);
        this.easyFlowVisible = true;
        this.data = data;
        this.$nextTick(() => {
          this.jsPlumb = jsPlumb.getInstance();
          this.$nextTick(() => {
            this.jsPlumbInit();
          });
        });
      });
    },

    /**
     *       console.log("0923=====>this bug bug bug =======>"+data)
      //0923 todo 对data数据的唯一ids进行一个替换
      let newData = changeDatasIds(data);
      console.log("0923=====>this bug bug fix new Data =======>"+newData)
     */

    // 模拟载入自定义模板数据
    dataReloadSB(data) {
      this.dialogSBDSVisible = false;
      //ori ----> this.dataReload(data);
      //增加对数据的ids转换, 但是保留原始数据间的联系
     

      // 创建一个 id 映射，用于保存旧 ID 到新 ID 的映射关系
      let idMapping = {};

      // 遍历 nodeList，生成新的 id 并替换
      data.nodeList.forEach((node) => {
        const newId = nanoid(); // 生成新的唯一 id
        idMapping[node.id] = newId; // 保存旧 id 与新 id 的映射关系
        node.id = newId; // 替换节点的 id
      });

      // 遍历 lineList，替换 from 和 to 字段
      data.lineList.forEach((line) => {
        if (idMapping[line.from]) {
          line.from = idMapping[line.from]; // 替换 from 字段
        }
        if (idMapping[line.to]) {
          line.to = idMapping[line.to]; // 替换 to 字段
        }
      });

      // 调用 dataReload 方法重新载入修改后的数据
      this.dataReload(data);
    },

    changeDatasIds(data) {
      const newDataA = data.nodeList.forEach((node) => {
        const newId = nanoid();
        const oriId = node.id;
        node.id = newId;
        // Update lineList from/to fields
        data.lineList.forEach((line) => {
          if (line.from === oriId) {
            line.from = newId;
          }
          if (line.to === oriId) {
            line.to = newId;
          }
        });
      });
      return newDataA;
    },

    // 模拟载入数据dataA
    dataReloadA() {
      this.dataReload(getDataA());
    },

    // 模拟载入数据dataB
    dataReloadB() {
      this.dataReload(getDataB());
    },
    // 模拟载入数据dataC
    // dataReloadC() {
    //   this.dataReload(getDataC());
    // },

    // 模拟载入数据dataD --> 自定义样式
    dataReloadD() {
      // this.dataReload(getDataD());
      this.dataReload(getDataD());
    },
    // 模拟加载数据dataE，自适应创建坐标
    // dataReloadE() {
    //   let dataE = getDataE();
    //   let tempData = lodash.cloneDeep(dataE);
    //   let data = ForceDirected(tempData);
    //   this.dataReload(data);
    //   this.$message({
    //     message: "力导图每次产生的布局是不一样的",
    //     type: "warning",
    //   });
    // },
    zoomAdd() {
      if (this.zoom >= 1) {
        return;
      }
      this.zoom = this.zoom + 0.1;
      this.$refs.efContainer.style.transform = `scale(${this.zoom})`;
      this.jsPlumb.setZoom(this.zoom);
    },
    //控件大小
    zoomSub() {
      if (this.zoom <= 0) {
        return;
      }
      this.zoom = this.zoom - 0.1;
      this.$refs.efContainer.style.transform = `scale(${this.zoom})`;
      this.jsPlumb.setZoom(this.zoom);
    },
    // 下载数据
    downloadData() {
      this.$confirm("确定要下载该流程数据吗？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        closeOnClickModal: false,
      })
        .then(() => {
          var datastr =
            "data:text/json;charset=utf-8," +
            encodeURIComponent(JSON.stringify(this.data, null, "\t"));
          var downloadAnchorNode = document.createElement("a");
          downloadAnchorNode.setAttribute("href", datastr);
          downloadAnchorNode.setAttribute("download", "data.json");
          downloadAnchorNode.click();
          downloadAnchorNode.remove();
          this.$message.success("正在下载中,请稍后...");
        })
        .catch(() => {});
    },
    openHelp() {
      this.flowHelpVisible = true;
      this.$nextTick(function () {
        this.$refs.flowHelp.init();
      });
    },
    //保存流程面板
    openDialog() {
      this.dialogVisible = true;
    },

    //保存sb流程模板
    openSBDialog() {
      this.dialogSBVisible = true;
    },

    /** 查询流程列表--> 获取流程的名称进行比较是否 用户输入的流程名称有重名的 */
    getList() {
      this.projectList = [];
      this.modelProjectList = [];

      listProject2(this.queryParams).then((response) => {
        // console.log("manage/index从后端获取的response===>", response);
        for (var i = 0; i < response.length; i++) {
          this.projectList.push(response[i]);
        }
        // console.log("panel init  projectList===>", this.projectList);

        // 按照updateDate字段进行排序
        // this.projectList.sort((a, b) => {
        //   // 按照updateDate字段从小到大排序
        //   return new Date(a.createDate) - new Date(b.createDate);
        // });
      });

      listSBModelProject().then((response) => {
        // console.log("manage/index从后端获取的response===>", response);
        for (var i = 0; i < response.length; i++) {
          this.modelProjectList.push(response[i]);
        }
      });
    },

    //---------------------------------------------------------------------------------
    //sb 填写 流程模板名称
    //then sb 自定义流程模板, 保存 sb
    saveModel() {
      //从 dataInfoModel() 获取 当前panel中的数据 sbsbssbsbbbsssb
      const flowJsonData = this.dataInfoModel();
      console.log("panel flowJsonData=========>" + flowJsonData);

      //++++前置的一些判断+++++
      //填写 项目流程名称
      // 项目流程为空，则提示需要填入名称 --  this.formData.project_Name
      if (
        this.formData.project_Name === "" ||
        this.formData.project_Name === null
      ) {
        this.$message({
          type: "warning",
          message: `请填入名称再保存!!!`,
        });
        return;
      }

      // 如果存在同名的项目流程，则提示已存在 --  this.formData.project_Name
      const projectExists = this.modelProjectList.find(
        (project) => project.name === this.formData.project_Name
      );
      if (projectExists) {
        this.$message({
          type: "warning",
          message: `名称为 ${this.formData.project_Name} 的流程模板已存在!!!`,
        });
        return;
      }
      //++++++++

      const allJsonData = {
        id: nanoid(),
        name: this.formData.project_Name, // 使用  用户填入的流程名称 作为项目名称
        data: flowJsonData,
        zz: this.formData.marks,
        sb: "",
        ds: "",
      };

      // 后端api, 将数据存储
      // 发送项目数据到后端
      saveSBModelProject(allJsonData)
        .then((response) => {
          console.log("Project saved successfully:", response);
        })
        .catch((error) => {
          console.error("Error saving project:", error);
        });

      //填写项目流程名称面板关闭
      this.dialogSBVisible = false;

      // 保存成功后显示提示消息
      this.$message({
        type: "success",
        message: "流程模板保存成功！",
      });

      //最后清空数据
      this.formData.project_Name = null;
      this.formData.marks = null;

      this.getList();
      this.reload();
    },

    dataInfoModel() {
      return this.$refs.flowInfoModel.init();
    },

    //---------------------------------------------------------------------------------

    /** 单个删除操作 */
    handleDelete(row) {
      const ids = row.id;
      this.$modal
        .confirm(`是否确认删除该流程模板？`)
        .then(() => {
          // 只有当用户确认删除时才执行删除操作
          return delSBProject(ids);
        })
        .then(() => {
          // 处理成功的删除操作
          // this.getList();
          this.reload();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {
          // 处理用户取消操作或者任何删除过程中出现的错误
        });
    },
    //---------------------------------------------------------------------------------
    //保存流程
    save() {
      // console.log("this.uploadUsername =========>", this.uploadUsername);
      // console.log(
      //   " this.departmentCategory =========>",
      //   this.departmentCategory
      // );
      console.log("this.data ===>", this.data);
      //填写 项目流程名称
      // this.openDialog();

      // 项目流程为空，则提示需要填入名称 --  this.formData.project_Name
      if (
        this.formData.project_Name === "" ||
        this.formData.project_Name === null
      ) {
        this.$message({
          type: "warning",
          message: `请填入流程名称再保存!!!`,
        });
        return;
      }

      // 如果存在同名的项目流程，则提示已存在 --  this.formData.project_Name
      const projectExists = this.projectList.find(
        (project) => project.name === this.formData.project_Name
      );
      if (projectExists) {
        this.$message({
          type: "warning",
          message: `名称为 ${this.formData.project_Name} 的流程已存在!!!`,
        });
        return;
      }

      this.project_Id = nanoid();
      // //将date分解为project、node、line
      const { projectData, nodeData, lineData } = this.splitData(
        this.data,
        this.project_Id
      );
      console.log("projectData ======= 08010801 ====", projectData);

      // 发送项目数据到后端
      saveProject(projectData)
        .then((response) => {
          console.log("Project saved successfully:", response);
        })
        .catch((error) => {
          console.error("Error saving project:", error);
        });

      // 发送节点数据到后端
      nodeData.forEach((node) => {
        console.log("this.data.node.state ===>", node.state);
        saveNode(node)
          .then((response) => {
            console.log("Node saved successfully:", response);
          })
          .catch((error) => {
            console.error("Error saving node:", error);
          });
      });

      // 发送连线数据到后端
      lineData.forEach((line) => {
        saveLine(line)
          .then((response) => {
            console.log("Line saved successfully:", response);
          })
          .catch((error) => {
            console.error("Error saving line:", error);
          });
      });

      //填写项目流程名称面板关闭
      this.dialogVisible = false;

      // 保存成功后显示提示消息
      this.$message({
        type: "success",
        message: "流程保存成功！",
      });
      this.formData.project_Name = null;

      this.reload();
    },

    //将this.data数据进行拆分
    splitData(data, id) {
      const projectData = {
        id: id, //随机id
        // name: data.name, // 使用流程的名称作为项目名称
        name: this.formData.project_Name, // 使用  用户填入的流程名称 作为项目名称
        // create_date: , 后台记录

        createBy: this.uploadUsername + "/" + this.departmentCategory,
        // 初次创建流程无对应的更新部分的信息
        // updateBy: "/",
        // updateDate: "/",
        // 保存绑定的文件 this.idsRegulation & this.idsForm
        state:
          this.idsRegulation != null
            ? JSON.stringify(this.idsRegulation)
            : null, //制度文件ids
        type: this.idsForm != null ? JSON.stringify(this.idsForm) : null, //表单文件ids
        //0731新增
        number: this.formData.number,
        department: this.formData.department,
        level: this.formData.level,
        purpose: this.formData.purpose,
        applicationScope: this.formData.applicationScope,

        //业务模块
        businessesModules: this.formData.businessesModules,
        //细分业务
        subBusinesses: this.formData.subBusinesses,
      };

      const nodeData = data.nodeList.map((node) => ({
        id: node.id,
        projectId: id, // 统一的 projectId
        name: node.name,
        type: node.type != "no" ? JSON.stringify(node.type) : node.type,
        left: node.left,
        top: node.top,
        ico: node.ico,
        // state: node.state,
        state: node.state != "no" ? JSON.stringify(node.state) : node.state,

        //0727新增字段
        department: node.department,
        description: node.description,
        operationalStaff: node.operationalStaff,
        date: node.date,
      }));

      const lineData = data.lineList.map((line) => ({
        id: line.id, // 填入线条ID
        projectId: id, // 统一的 projectId
        from: line.from,
        to: line.to,
        label: line.label || "", // 如果没有条件，可以设置为空字符串
      }));

      return { projectData, nodeData, lineData };
    },
  },
};
</script>

<style>
.button-group {
  margin-top: 10px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #409eff;
}
.el-icon-arrow-down {
  font-size: 12px;
}
</style>
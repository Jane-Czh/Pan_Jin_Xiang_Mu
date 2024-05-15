<template>
  <el-dialog
    :title="`更新流程 [ ${data.name} ]`"
    :visible.sync="easyFlowVisible"
    width="90%"
    center="true"
    destroy-on-close="true"
    append-to-body
  >
    <div v-if="easyFlowVisible" style="height: calc(80vh)">
      <el-row>
        <!--顶部工具菜单-->
        <el-col :span="24">
          <div class="ef-tooltar">
            <el-link type="primary" :underline="false">{{ data.name }}</el-link>
            <el-divider direction="vertical"></el-divider>
            <el-button
              type="text"
              icon="el-icon-delete"
              size="large"
              @click="deleteElement"
              :disabled="!this.activeElement.type"
            ></el-button>
            <!--
          <el-divider direction="vertical"></el-divider>
          <el-button
            type="text"
            icon="el-icon-download"
            size="large"
            @click="downloadData"
          ></el-button> -->
            <el-divider direction="vertical"></el-divider>
            <el-button
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
            <!-- 右侧button -->
            <div style="float: right; margin-right: 5px">
              <!-- 弹出面板 设置流程绑定文件(制度&表单)  v-if="this.regulationFiles.length === 0 && this.formFiles.length === 0" -->
              <el-button
                v-if="this.projectState === null && this.projectType === null"
                type="primary"
                plain
                round
                icon="el-icon-folder-add"
                @click="bandFiles()"
                size="mini"
                >点击绑定文件</el-button
              >
              <el-button
                v-else
                type="primary"
                round
                icon="el-icon-folder-add"
                @click="bandMoreFiles()"
                size="mini"
                >更改绑定文件</el-button
              >

              <!-- 对流程进行保存 -->
              <el-button
                type="success"
                plain
                round
                icon="el-icon-check"
                @click="updateProject()"
                size="mini"
                >更新流程</el-button
              >
            </div>

            <!-- 1、绑定文件的dialog ------------------------------------------- -->
            <el-dialog
              title="流程绑定文件(请选择需要绑定的文件)"
              :visible.sync="dialogFilesVisible"
              width="60%"
              style="height: 1000px"
              :before-close="handleClose"
              destroy-on-close="true"
              append-to-body
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
            <!-- 2、继续绑定制度文件的dialog -->
            <el-dialog
              title="更改绑定的文件"
              :visible.sync="dialogMoreFilesVisible"
              width="60%"
              style="height: 1000px"
              :before-close="handleClose"
              destroy-on-close="true"
              append-to-body
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

      <div style="display: flex; height: calc(100% - 47px)">
        <!-- 左侧node-menu -->
        <div style="width: 230px; border-right: 1px solid #dce3e8">
          <node-menu @addNode="addNode" ref="nodeMenu"></node-menu>
        </div>
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
            >
            </flow-node>
          </template>
          <!-- 给画布一个默认的宽度和高度 -->
          <div style="position: absolute; top: 3000px; left: 3000px">
            &nbsp;
          </div>
        </div>
        <!-- 右侧表单 -->
        <div
          style="
            width: 250px;
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
    </div>
  </el-dialog>
</template>

  <script>
import draggable from "vuedraggable";
// import { jsPlumb } from 'jsplumb'
// 使用修改后的jsplumb
import "./jsplumb";
import { easyFlowMixin } from "@/views/process/ef/mixins";
import flowNode from "@/views/process/ef/edit_node";
import nodeMenu from "@/views/process/ef/node_menu";
import FlowInfo from "@/views/process/ef/info";
import FlowHelp from "@/views/process/ef/help";
import FlowNodeForm from "./node_form";
import lodash from "lodash";

import axios from "axios";
import { nanoid } from "nanoid";
//获取用户信息-用户名
import { getUserProfile } from "@/api/system/user";
//获取用户信息-部门
import { getDept } from "@/api/system/dept";

// 绑定文件
import CustomFiles from "./CustomFiles.vue";
// 文件api
import { listFilemanagement } from "@/api/file/filemanagement";
export default {
  inject: ["reload"],
  data() {
    return {
      //用户名
      uploadUsername: null,
      //所属部门
      departmentCategory: null,

      // 后台表单文件数据
      formsmanagementList: [],
      // 后台制度文件数据
      filemanagementList: [],

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

      // index 传递过来 的当前project字段: id、state(制度ids)、type(表单ids)
      indexProjectId: null,
      projectState: null,
      projectType: null,

      // -------------------------------------------------------
      //填写 流程项目名称的提示框
      dialogVisible: false,
      formData: {
        project_Name: "",
      },

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
      // 组件销毁前将 data<-nullData 防止出现旧数据
      nullData: {},
      // 节点原始数据,判断是否修改
      oriData: {},
      // 流程原始数据,判断是否修改
      oriState: {},
      oriType: {},

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

      // 制度查询参数
    };
  },
  // 一些基础配置移动该文件中
  mixins: [easyFlowMixin],
  components: {
    draggable,
    flowNode,
    nodeMenu,
    FlowInfo,
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

    //对于从index中传递过来需要回显的filenames进行渲染
    // this.getRegularFileData();
    // this.getFormFileData();
    //获取当前用户信息
    this.getUserInfo();
  },

  beforeDestroy() {
    // 在组件销毁前
    this.data = this.nullData;
    this.oriData = this.nullData;
  },

  methods: {
    // 调用接口获取用户信息
    getUserInfo() {
      getUserProfile()
        .then((response) => {
          // 处理成功的情况
          console.log("成功获取用户信息:", response.data);
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
      //将传递进来的 ids 置空
      this.projectState = null;
      this.projectType = null;

      this.dialogMoreFilesVisible = false;

      this.regulationFiles = this.emptyFile;
      this.formFiles = this.emptyFile;

      this.selectedRegulationTemp = this.emptyFile;
      this.selectedFormsTemp = this.emptyFile;
    },

    temp(namesRegulation, namesForm) {
      this.regulationFiles = namesRegulation;
      this.formFiles = namesForm;
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

          console.log("hihihi111==namesRegulation==", idsRegulation);
          console.log("hihihi111==namesRegulation==", namesRegulation);
          console.log("hihihi222==namesForm==", idsForm);
          console.log("hihihi222==namesForm==", namesForm);

          // console.log(
          //   "hihihi3332==this.regulationFiles==",
          //   this.regulationFiles
          // );
          // console.log("hihihi44224==this.formFiles==", this.formFiles);

          this.dialogMoreFilesVisible = false;

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
          }
        })
        .catch(() => {
          // 用户点击取消按钮的操作
          // 在此处可以执行确认绑定的逻辑

          this.regulationFiles = this.emptyFile;
          this.formFiles = this.emptyFile;

          this.dialogFilesVisible = false;
          this.dialogMoreFilesVisible = false;
          // 清除选择的文件名
          // this.selectedFileNames = this.selectedFileName;
        });
    },

    // ----------------------------------------------
    /**流程已经绑定的文件的回显*/
    // ----------------------------------------------

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
      var nodeId = this.getUUID();
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
      var node = {
        id: nodeId,
        name: nodeName,
        type: nodeMenu.type,
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
      this.$confirm("确定要删除节点" + nodeId + "?", "提示", {
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
        })
        .catch(() => {});
      return true;
    },
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
    // dataInfo() {
    //   this.flowInfoVisible = true;
    //   this.$nextTick(function () {
    //     this.$refs.flowInfo.init();
    //   });
    // },

    // 加载流程图 数据：node、line
    dataReload(data, id, state, type) {
      // console.log("edit panel state===============>", state);
      // console.log("edit panel  type===============>", type);
      this.indexProjectId = id;

      this.idsRegulation = state;
      this.idsForm = type;

      /**
       * 逻辑上：如果用户更改了流程绑定的文件，就从confirm中获取新的ids存储，否则就直接保存 state、 type 传递过来的 ids
       */
      /**
       * 1、对于当前project传递进来的 state(制度文件ids)、type(表单文件ids
       * 2、根据获取到的state(制度文件ids)、type(表单文件ids)，在文件管理部分找对应的文件数据filenames 进行回显 --->  regulationFiles formFiles
       *                                         也就是将传过来的对应的文件ids， 根据ids获取文件名称 进行回显
       */

      if (state != null) {
        this.projectState = state;
        this.getRegularFileData(state);
      }
      if (type != null) {
        this.projectType = type;
        this.getFormFileData(type);
      }

      // console.log("this.regulationFiles 22========", this.regulationFiles);
      // console.log("this.formFiles 22========", this.formFiles);

      //保存原始数据
      this.oriData = data;
      this.oriState = state;
      this.oriType = type;

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

    /**根据project的state(制度文件ids)、type(表单文件ids)查找filenames --> regulationFiles、formFiles
     * this.projectState = state;
     * this.projectType = type;
     */
    /** 1.1 查询制度文件列表 */
    getRegularFileData(state) {
      this.regulationFiles = [];

      listFilemanagement(this.queryParams)
        .then((response) => {
          this.formmanagementList = response.rows;
          // console.log(" this.filemanagementList ==>", this.filemanagementList);
        })
        .then(() => {
          // 制度文件数据filemanagementList 进行筛选 id==node.state的数据,将其赋值给selectedFileNames

          JSON.parse(state).forEach((stateId) => {
            let row = this.formmanagementList.find(
              (item) =>
                JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
            );
            if (row != null) {
              this.regulationFiles.push(row.fileName);
              this.selectedRegulationTemp.push(row.fileName);
            }
          });
        });
    },

    /** 1.2 查询表单文件列表 */
    getFormFileData(type) {
      this.formFiles = [];

      listFilemanagement(this.queryParams)
        .then((response) => {
          this.formsmanagementList = response.rows;
          // console.log(" this.filemanagementList ==>", this.filemanagementList);
        })
        .then(() => {
          // 制度文件数据filemanagementList 进行筛选 id==node.state的数据,将其赋值给selectedFileNames

          JSON.parse(type).forEach((stateId) => {
            let row = this.formsmanagementList.find(
              (item) =>
                JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
            );
            if (row != null) {
              this.formFiles.push(row.fileName);
              this.selectedFormsTemp.push(row.fileName);
            }
          });
        });

      console.log("edit panel this.formFiles ========", this.formFiles);
    },

    // -----------------------------------------------------------------------

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

    updateId() {
      //更新node、line的id
      // console.log("ori data===>", this.data);
      let temp = this.data.nodeList.forEach((node) => {
        const newId = nanoid();
        const oriId = node.id;
        // console.log("ori id===", oriId);
        // console.log("new id===", newId);
        node.id = newId;
        // Update lineList from/to fields
        this.data.lineList.forEach((line) => {
          if (line.from === oriId) {
            line.from = newId;
          }
          if (line.to === oriId) {
            line.to = newId;
          }
        });
      });
      // console.log("after data===>", this.data);
    },
    /**
     * 保存更新流程
     * ：保存旧流程，与新流程进行比较，比较有差异才进行更新数据库且插入更新时间等信息,若无更新内容，弹出提示:"无更新内容！"
     * ：保存的新流程需要与旧流程进行绑定,且由于mysql存储的一致性,需要对原来的node、line的id进行改变
     */

    updateProject() {
      //this.oriData === this.data 并不是在比较对象的内容，而是在比较对象的引用是否相同
      if (
        JSON.stringify(this.oriData) === JSON.stringify(this.data) &&
        JSON.stringify(this.idsForm) === JSON.stringify(this.oriType) &&
        JSON.stringify(this.idsRegulation) === JSON.stringify(this.oriState)
      ) {
        this.$message.warning("未进行修改流程,无法保存!请修改后再保存!!");
      } else {
        //projectId 在对应的 node节点属性中有.
        const oldProjectId = this.data.nodeList[0].projectId; //JSON.stringify(oldProjectId)
        const oldProjectName = this.data.name; //JSON.stringify(oldProjectId)
        //改变node、line的id
        this.updateId();

        //对project赋新的id
        this.project_Id = nanoid();
        // //将date分解为project、node、line
        const { projectData, nodeData, lineData } = this.splitData(
          this.data,
          this.project_Id,
          JSON.stringify(oldProjectId),
          JSON.stringify(oldProjectName)
        );

        // project数据
        axios
          .post(
            "http://localhost:8081/project/updateProjectFromOld",
            projectData
          )
          .then((response) => {
            console.log("Project saved successfully:", response.data);
          })
          .catch((error) => {
            console.error("Error saving project:", error);
          });

        // 节点数据
        for (var i = 0; i < nodeData.length; i++) {
          // console.log("type data state===>",typeof(nodeData[i].state))
          axios
            .post("http://localhost:8081/node/saveNode", nodeData[i])
            .then((response) => {
              console.log("Nodes saved successfully:", response.data);
            })
            .catch((error) => {
              console.error("Error saving nodes:", error);
            });
        }

        // 连线数据
        for (var i = 0; i < lineData.length; i++) {
          axios
            .post("http://localhost:8081/line/saveLine", lineData[i])
            .then((response) => {
              console.log("Lines saved successfully:", response.data);
            })
            .catch((error) => {
              console.error("Error saving lines:", error);
            });
        }

        // 保存成功后显示提示消息
        this.$message({
          type: "success",
          message: "流程更新成功！",
        });
        //让面板消失 、页面刷新装载更新后的数据
        this.easyFlowVisible = false;
        this.reload();
      }
    },

    //将this.data数据进行拆分为project、node、line
    splitData(data, id, oldId, oldName) {
      const projectData = {
        id: id,
        oldVersion: JSON.parse(oldId),
        name: JSON.parse(oldName), // JSON.parse(this.formData.project_Name)JSON.stringify( 使用原来的流程的名称作为项目名称 TODO可以进修改，且流程绑定的文件也可以进行修改(增加和解绑)

        // create_date: , 后台记录

        // create_by: 更新流程无create_by,
        updateBy: this.uploadUsername + "/" + this.departmentCategory,
        // 保存绑定的文件 this.idsRegulation & this.idsForm
        state:
          this.idsRegulation == null
            ? this.idsRegulation
            : JSON.stringify(this.idsRegulation), //制度文件ids
        type:
          this.idsForm == null ? this.idsForm : JSON.stringify(this.idsForm), //表单文件ids
      };

      const nodeData = data.nodeList.map((node) => ({
        id: node.id, //nodeId在node在被创建的时候就已经被赋值了
        projectId: id, // 填入统一的项目ID
        name: node.name,
        type: node.type != "no" ? JSON.stringify(node.type) : node.type,
        left: node.left,
        top: node.top,
        ico: node.ico,
        state: node.state != "no" ? JSON.stringify(node.state) : node.state,
      }));

      const lineData = data.lineList.map((line) => ({
        id: nanoid(), // 随机生成line的ID
        projectId: id, // 填入统一的项目ID
        from: line.from,
        to: line.to,
        label: line.label || "", // 如果没有条件，可以设置为空字符串
      }));

      return { projectData, nodeData, lineData };
    },
  },
};
</script>

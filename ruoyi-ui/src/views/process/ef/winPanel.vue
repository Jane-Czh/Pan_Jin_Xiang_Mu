<template>
  <div v-if="easyFlowVisible" style="height: calc(100vh)">
    <el-row>
      <!--顶部工具菜单-->
      <el-col :span="24">
        <div class="ef-tooltar">
          <el-link type="primary" :underline="false"
            >预览中-流程名称: 【{{ data.name }}】</el-link
          >
          <el-divider direction="vertical"></el-divider>

          <div style="float: right; margin-right: 5px">
            <el-divider direction="vertical"></el-divider>
            <el-button
              type="text"
              icon="el-icon-download"
              size="large"
              @click="download()"
              >图片导出</el-button
            >
            <el-divider direction="vertical"></el-divider>
          </div>
        </div>
      </el-col>
    </el-row>

    <!-- 主体部分 -->
    <div style="display: flex; height: calc(100% - 47px)">
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
    </div>
  </div>
</template>

<script>
import html2canvas from "html2canvas";
import domtoimage from "dom-to-image";
import { Loading } from 'element-ui'; // 确保您已经引入了 Element UI 的 Loading 组件  

import draggable from "vuedraggable";
// import { jsPlumb } from 'jsplumb'
// 使用修改后的jsplumb
import "./jsplumb";
import { easyFlowMixin } from "@/views/process/ef/mixins";
// import flowNode from "@/views/process/ef/node";
import flowNode from "@/views/process/ef/show_node";
import nodeMenu from "@/views/process/ef/node_menu";
import FlowInfo from "@/views/process/ef/info";
import FlowInfoModel from "@/views/process/ef/infoModel";
import FlowHelp from "@/views/process/ef/help";
import FlowNodeForm from "./node_form";
import lodash from "lodash";
import { getDataA } from "./data_A";
import { getDataB } from "./data_B";
import { getDataD } from "./data_D";

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
  getProject0,
} from "@/api/system/project";

export default {
  name: "Project",
  inject: ["reload"],
  data() {
    return {
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
    this.getThisProjectData();
    this.jsPlumb = jsPlumb.getInstance();
    // this.$nextTick(() => {
    //   this.dataReload(this.data);
    // });
    //获取当前用户信息
    // this.getUserInfo();
    // //获取现有流程名称
    // this.getList();
  },

  methods: {
    // download() {
    //   // 获取 div 元素
    //   const element = this.$refs.efContainer;

    //   // 使用 html2canvas 将 div 渲染为 canvas
    //   html2canvas(element).then(canvas => {
    //     // 将 canvas 转为图片数据 URL
    //     const imgData = canvas.toDataURL('image/jpeg');

    //     // 创建一个下载链接并触发下载
    //     const link = document.createElement('a');
    //     link.href = imgData;
    //     link.download = 'flowchart.jpg';
    //     link.click();
    //   });
    // },

    //   download() {
    //   // 获取容器元素
    //   const container = this.$refs.efContainer;

    //   // 获取 SVG 元素
    //   const svgElements = container.querySelectorAll('svg');

    //   // 处理每个 SVG 元素
    //   svgElements.forEach((svg) => {
    //     // 获取 SVG 的 XML 字符串
    //     const serializer = new XMLSerializer();
    //     let svgString = serializer.serializeToString(svg);

    //     // 创建 Blob 对象
    //     const blob = new Blob([svgString], { type: 'image/svg+xml;charset=utf-8' });

    //     // 创建一个下载链接并触发下载
    //     const link = document.createElement('a');
    //     link.href = URL.createObjectURL(blob);
    //     link.download = 'flowchart.svg';
    //     link.click();
    //   });
    // },

    download() {
      const element = this.$refs.efContainer;

      // 显示加载实例
      const loadingInstance = Loading.service({
        lock: true,
        text: "正在导出，请稍后...",
        spinner: "el-icon-loading",
        background: "rgba(0, 0, 0, 0.7)",
      });

      domtoimage
        .toJpeg(element, {
          quality: 0.95,
          bgcolor: "white", // 设置背景颜色为白色
        })
        .then((dataUrl) => {
          const link = document.createElement("a");
          link.href = dataUrl;
          link.download = "flowchart.jpeg";
          link.click();

          // 关闭加载实例
          loadingInstance.close();
        })
        .catch((error) => {
          console.error("Oops, something went wrong!", error);

          // 发生错误时关闭加载实例
          loadingInstance.close();
        });
    },
    /** 根据传递进来的流程id 查询当前流程的完整信息 */
    getThisProjectData() {
      const id = this.$route.params.id;
      console.log("win panel get project by id ===>", id);
      //api -- 获取当前流程数据
      getProject0(id).then((response) => {
        console.log("win panel get project by id data===>", response.data);
        this.$nextTick(() => {
          this.dataReload(response.data);
        });
      });
    },

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

    //------------------------------------------------
    handleClose(done) {
      // 不要执行阻止关闭的操作，直接调用 done() 函数
      done();
    },

    /**流程绑定文件(制度&表单)*/



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
    // changeNodeSite(data) {
    //   for (var i = 0; i < this.data.nodeList.length; i++) {
    //     let node = this.data.nodeList[i];
    //     if (node.id === data.nodeId) {
    //       node.left = data.left;
    //       node.top = data.top;
    //     }
    //   }
    // },
    /**
     * 拖拽结束后添加新的节点
     * @param evt
     * @param nodeMenu 被添加的节点对象
     * @param mousePosition 鼠标拖拽结束的坐标
     */
    // addNode(evt, nodeMenu, mousePosition) {
    //   var screenX = evt.originalEvent.clientX,
    //     screenY = evt.originalEvent.clientY;
    //   let efContainer = this.$refs.efContainer;
    //   var containerRect = efContainer.getBoundingClientRect();
    //   var left = screenX,
    //     top = screenY;
    //   // 计算是否拖入到容器中
    //   if (
    //     left < containerRect.x ||
    //     left > containerRect.width + containerRect.x ||
    //     top < containerRect.y ||
    //     containerRect.y > containerRect.y + containerRect.height
    //   ) {
    //     this.$message.error("请把节点拖入到画布中");
    //     return;
    //   }
    //   left = left - containerRect.x + efContainer.scrollLeft;
    //   top = top - containerRect.y + efContainer.scrollTop;
    //   // 居中
    //   left -= 85;
    //   top -= 16;

    //   // 动态生成名字
    //   var origName = nodeMenu.name;
    //   var nodeName = origName;
    //   var index = 1;
    //   while (index < 10000) {
    //     var repeat = false;
    //     for (var i = 0; i < this.data.nodeList.length; i++) {
    //       let node = this.data.nodeList[i];
    //       if (node.name === nodeName) {
    //         nodeName = origName + index;
    //         repeat = true;
    //       }
    //     }
    //     if (repeat) {
    //       index++;
    //       continue;
    //     }
    //     break;
    //   }

    //   var nodeId = this.getUUID();
    //   // 初始化拖动的节点信息
    //   var node = {
    //     id: nodeId,
    //     name: nodeName,
    //     type: "no",
    //     left: left + "px",
    //     top: top + "px",
    //     ico: nodeMenu.ico,
    //     state: "no",
    //   };
    //   /**
    //    * 这里可以进行业务判断、是否能够添加该节点
    //    */
    //   this.data.nodeList.push(node);
    //   this.$nextTick(function () {
    //     this.jsPlumb.makeSource(nodeId, this.jsplumbSourceOptions);
    //     this.jsPlumb.makeTarget(nodeId, this.jsplumbTargetOptions);
    //     this.jsPlumb.draggable(nodeId, {
    //       containment: "parent",
    //       stop: function (el) {
    //         // 拖拽节点结束后的对调
    //         console.log("拖拽结束: ", el);
    //       },
    //     });
    //   });
    // },
    /**
     * 删除节点
     * @param nodeId 被删除节点的ID
     */
   

    // //点击节点
    // clickNode(nodeId) {
    //   this.activeElement.type = "node";
    //   this.activeElement.nodeId = nodeId;

    //   this.$refs.nodeForm.nodeInit(this.data, nodeId);
    // },

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
<template>
  <el-dialog
    :title="`查看[ ${data.name} ]流程详情`"
    :visible.sync="easyFlowVisible"
    width="80%"
    center="true"
    destroy-on-close="true"
  >
    <div v-if="easyFlowVisible" style="height: calc(60vh)">
    <div style="display: flex; height: calc(100% - 30px)">
      <!-- <div style="width: 230px; border-right: 1px solid #dce3e8">
          <node-menu @addNode="addNode" ref="nodeMenu"></node-menu>
        </div> -->
      <div
        id="efContainer"
        ref="efContainer"
        class="container"
        v-flowDrag
        style="width: 100%"
      >
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
        <div style="position: absolute; top: 3000px; left: 3000px">&nbsp;</div>
      </div>
      <!-- 右侧表单 -->
      <div
        style="
          width: 0;
          overflow: hidden;
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
import flowNode from "@/views/process/ef/show_node";
import nodeMenu from "@/views/process/ef/node_menu";
import FlowInfo from "@/views/process/ef/info";
import FlowHelp from "@/views/process/ef/help";
import FlowNodeForm from "./node_form";
import lodash from "lodash";



export default {
  data() {
    return {
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
    FlowNodeForm,
    FlowHelp
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
  },
  beforeDestroy() {
    // 在组件销毁前
  },
  methods: {
   
    //流程创建
    jsPlumbInit() {
      this.jsPlumb.ready(() => {
        // 导入默认配置
        this.jsPlumb.importDefaults(this.jsplumbSetting);
        // 会使整个jsPlumb立即重绘。
        this.jsPlumb.setSuspendDrawing(false, true);
        // 初始化节点
        this.loadEasyFlow();

        // 连线
        this.jsPlumb.bind("connection", (evt) => {
          let from = evt.source.id;
          let to = evt.target.id;
          if (this.loadEasyFlowFinish) {
            this.data.lineList.push({ from: from, to: to });
          }
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
      // for (var i = 0; i < this.data.nodeList.length; i++) {
      //   let node = this.data.nodeList[i];

        // 设置源点，可以拖出线连接其他节点
        // this.jsPlumb.makeSource(
        //   node.id,
        //   lodash.merge(this.jsplumbSourceOptions, {})
        // );

        // // 设置目标点，其他源点拖出的线可以连接该节点
        // this.jsPlumb.makeTarget(node.id, this.jsplumbTargetOptions);
        // if (!node.viewOnly) {
        //   this.jsPlumb.draggable(node.id, {
        //     containment: "parent",
        //     stop: function (el) {
        //       // 拖拽节点结束后的对调
        //       console.log("拖拽结束: ", el);
        //     },
        //   });
        // }
      // }

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

    // clickNode(nodeId) {
    //   this.activeElement.type = "node";
    //   this.activeElement.nodeId = nodeId;
    //   this.$refs.nodeForm.nodeInit(this.data, nodeId);
    // },

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


  },
};
</script>
  
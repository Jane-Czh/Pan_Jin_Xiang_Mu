<template>
  <div>
    <!-- 查看[历史]流程数据详情-->
    <!-- <his-panel ref="HisPanel" v-if="showHisPanelVisible"></his-panel> -->
    <el-dialog
      :title="`查看[ ${data.name} ]流程详情`"
      :visible.sync="easyFlowVisible"
      width="85%"
      :before-close="handleClose"
      destroy-on-close="true"
      append-to-body
    >
      <div class="container">
        <!-- 1、左侧50%流程图内容 -->
        <div
          v-if="easyFlowVisible"
          style="width: 55%; height: calc(80vh)"
          class="left-side"
        >
          <el-row>
            <!--顶部工具菜单-->
            <el-col :span="24">
              <div class="ef-tooltar">
                <el-link type="primary" :underline="false">{{
                  data.name
                }}</el-link>
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
                <el-divider direction="vertical"></el-divider>
                <!-- 历史 -->
                <!-- <el-button
                type="primary"
                icon="el-icon-folder-opened"
                size="large"
                @click="historyView()"
              ></el-button> -->
                <el-divider direction="vertical"></el-divider>
              </div>
            </el-col>
          </el-row>
          <div style="display: flex; height: calc(100% - 47px)">
            <div
              id="myContainer"
              ref="myContainer"
              class="container"
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
                  @openFileDialog="openFileDialog(node)"
                >
                </flow-node>
              </template>
              <!-- 给画布一个默认的宽度和高度 -->
              <div style="position: absolute; top: 2000px; left: 2000px">
                &nbsp;
              </div>
            </div>
          </div>
        </div>

        <!-- 2、右侧50%历史数据部分 v-if="!showHisPanelVisible" -->
        <div style="width: 50" class="right-side line-between-divs" v-if="!showHisPanelVisible">
          <el-table v-loading="loading" :data="historyProjectList" stripe>
            <el-table-column
              label="序号"
              align="center"
              prop="id"
              style="height: 100px; width: 100px"
            >
              <template slot-scope="scope">
                <span>{{ scope.$index + 1 }}</span>
              </template>
            </el-table-column>
            <!-- <el-table-column label="流程名称" align="center" prop="name" /> -->
            <el-table-column
              label="创建时间"
              align="center"
              prop="createDate"
              width="180"
            >
              <template slot-scope="scope">
                <i class="el-icon-time"></i>
                <span>{{
                  parseTime(scope.row.createDate, "{y}-{m}-{d}  {h}:{i}:{s}")
                }}</span>
              </template>
            </el-table-column>
            <el-table-column label="更新人" align="center" prop="createBy" />
            <el-table-column
              label="更新时间"
              align="center"
              prop="updateDate"
              width="180"
            >
              <template slot-scope="scope">
                <!-- <i class="el-icon-time"></i> -->
                <span>{{
                  parseTime(scope.row.updateDate, "{y}-{m}-{d}  {h}:{i}:{s}")
                }}</span>
              </template>
            </el-table-column>
            <el-table-column label="修改人" align="center" prop="updateBy" />
            <el-table-column
              label="操作"
              align="center"
              class-name="small-padding fixed-width"
            >
              <template slot-scope="scope">
                <!-- <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
                v-hasPermi="['system:project:edit']"
                >修改</el-button
              > -->

                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-document"
                  @click="view(scope.row)"
                >
                  查看</el-button
                >

                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  v-hasPermi="['system:project:remove']"
                  >删除</el-button
                >
                <!-- <el-button
                size="mini"
                type="text"
                icon="el-icon-edit-outline"
                @click="edit(scope.row)"
              >
                更新</el-button
              > -->
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 2、右侧展示部分 -->
        <div v-else style="width: 50%; height: calc(80vh)" class="left-side line-between-divss">
          <el-row>
            <!--顶部工具菜单-->
            <el-col :span="24">
              <div class="ef-tooltar">
                <el-link type="primary" :underline="false"
                  >历史版本{{ historyData.name }}</el-link
                >
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
                <el-divider direction="vertical"></el-divider>
                <!-- 关闭查看历史 -->
                <el-button
                  style="float: right"
                  type="primary"
                  icon="el-icon-close"
                  size="large"
                  @click="historyView()"
                ></el-button>
                <!-- <el-divider direction="vertical"></el-divider> -->
              </div>
            </el-col>
          </el-row>
          <div style="display: flex; height: calc(100% - 47px)">
            <div
              id="efContainer"
              ref="efContainer"
              class="container"
            >
              <template v-for="node in this.historyData.nodeList">
                <flow-node
                  :id="node.id"
                  :key="node.id"
                  :node="node"
                  :activeElement="historyActiveElement"
                  @changeNodeSite="changeNodeSite"
                  @nodeRightMenu="nodeRightMenu"
                  @clickNode="clickNode"
                  @openFileDialog="openFileDialog(node)"
                >
                </flow-node>
              </template>
              <!-- 给画布一个默认的宽度和高度 -->
              <div style="position: absolute; top: 2000px; right: 2000px">
                &nbsp;
              </div>
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
  </div>
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
import {
  historyListProject,
  delHistoryProject,
  getProject,
} from "@/api/system/project";

//历史流程的查看
import HisPanel from "@/views/process/ef/history_panel";
import HisNode from "@/views/process/ef/history_node";

export default {
  inject: ["reload"],
  data() {
    return {
      // 控制查看[历史]流程数据显示与隐藏
      showHisPanelVisible: false,
      //填写 流程项目名称的提示框
      dialogVisible: false,
      formData: {
        project_Name: "",
      },
      //项目名称参数
      project_Id: null,
      // jsPlumb 流程实例
      jsPlumb: null,
      hjsPlumb: null,

      // 控制画布销毁
      easyFlowVisible: true,
      // 控制流程数据显示与隐藏
      flowInfoVisible: false,

      // 是否加载完毕标志位(最新流程的)
      loadEasyFlowFinish: false,
      // 是否加载完毕标志位(历史流程的)
      historyLoadEasyFlowFinish: false,

      // 流程数据
      data: {},
      // 历史流程数据
      historyData: {},

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

      //历史流程 激活的元素、可能是节点、可能是连线
      historyActiveElement: {
        // 可选值 node 、line
        type: undefined,
        // 节点ID
        nodeId: undefined,
        // 连线ID
        sourceId: undefined,
        targetId: undefined,
      },
      zoom: 0.2,

      // 历史流程数据相关部分
      // 总条数
      total: 0,
      // 流程表格数据
      historyProjectList: [],
      //
      empty: [],
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

    HisPanel,
    HisNode,
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
    this.hjsPlumb = jsPlumb.getInstance();
  },
  beforeDestroy() {
    // 在组件销毁前
  },
  created() {},
  methods: {
    //关闭查看历史流程按钮
    historyView() {
      this.historyData = [];
      this.historyLoadEasyFlowFinish = false;
      this.showHisPanelVisible = false;
    },

    //dialog关闭
    handleClose() {
      this.easyFlowVisible = false;
    },

    //查看历史流程
    view(row) {
      //getProject 从后端读取指定id的流程数据
      getProject(row.id).then((response) => {
        // 获取show_panel.vue组件的实例 this.$refs.ShowPanel, 调用方法dataReload()
        // this.$refs.HisPanel.dataReload(response.data);
        this.historyDataReload(response.data);
      });
    },

    /** 查询当前流程的历史流程列表 */
    gethistoryList(data) {
      // 清空历史数据列表
      this.historyProjectList = [];

      //填装数据
      historyListProject(data.id).then((response) => {
        for (var i = 0; i < response.length; i++) {
          this.historyProjectList.push(response[i]);
        }
        // 按照updateDate字段进行排序
        this.historyProjectList.sort((a, b) => {
          // 将null值的数据置于最前面
          if (a.updateDate === null) {
            return -1;
          } else if (b.updateDate === null) {
            return 1;
          } else {
            // 按照updateDate字段从小到大排序
            return new Date(a.updateDate) - new Date(b.updateDate);
          }
        });
        //TODO 分页功能、分科室搜索功能等
        // this.total = response.total;
      });

      // console.log("historyProjectList===>", this.historyProjectList)
    },

    /** 单个删除操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm(`是否确认删除流程[ ${row.name} ]？`)
        .then(
          delHistoryProject(ids).then((response) => {})
          // function () { return delProject(ids);}
        )
        .then(() => {
          // this.getList();
          this.reload();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },

    //当前流程创建
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
        this.jsPlumb.setContainer(this.$refs.myContainer);
      });

    },

    // 加载流程图
    loadEasyFlow() {
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

    //历史流程创建
    historyJsPlumbInit() {
      this.hjsPlumb.ready(() => {
        // 导入默认配置
        this.hjsPlumb.importDefaults(this.jsplumbSetting);
        // 会使整个jsPlumb立即重绘。
        this.hjsPlumb.setSuspendDrawing(false, true);
        // 初始化节点
        this.hisrotyLoadEasyFlow();

        // 连线
        this.hjsPlumb.bind("connection", (evt) => {
          let from = evt.source.id;
          let to = evt.target.id;
          if (this.historyLoadEasyFlowFinish) {
            this.historyData.lineList.push({ from: from, to: to });
          }
        });

        // beforeDetach
        this.hjsPlumb.bind("beforeDetach", (evt) => {
          console.log("beforeDetach", evt);
        });
        this.hjsPlumb.setContainer(this.$refs.efContainer);
      });
    },

    // 加载流程图
    hisrotyLoadEasyFlow() {
      // 初始化[连线]
      for (var i = 0; i < this.historyData.lineList.length; i++) {
        let line = this.historyData.lineList[i];
        var connParam = {
          source: line.from,
          target: line.to,
          label: line.label ? line.label : "",
          connector: line.connector ? line.connector : "",
          anchors: line.anchors ? line.anchors : undefined,
          paintStyle: line.paintStyle ? line.paintStyle : undefined,
        };
        this.hjsPlumb.connect(connParam, this.jsplumbConnectOptions);
      }
      this.$nextTick(function () {
        this.historyLoadEasyFlowFinish = true;
      });
    },

    // clickNode(nodeId) {
    //   this.activeElement.type = "node";
    //   this.activeElement.nodeId = nodeId;
    //   this.$refs.nodeForm.nodeInit(this.data, nodeId);
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
        //加载历史数据
        this.gethistoryList(data);

        this.$nextTick(() => {
          this.jsPlumb = jsPlumb.getInstance();
          console.log("this.jsPlumb11111111111111111", this.jsPlumb);
          this.$nextTick(() => {
            this.jsPlumbInit();
          });
        });
      });
    },

    //加载历史流程图 数据：node、line
    historyDataReload(datas) {
      this.showHisPanelVisible = false;
      //流程图-节点数据
      this.historyData.nodeList = [];
      //流程图-边数据
      this.historyData.lineList = [];

      this.$nextTick(() => {
        this.historyData = lodash.cloneDeep(datas);
        this.showHisPanelVisible = true;
        this.$nextTick(() => {
          this.hjsPlumb = jsPlumb.getInstance();
          console.log("this.hjsPlumb222222222222222", this.hjsPlumb);

          this.$nextTick(() => {
            this.historyJsPlumbInit();
          });
        });
      });
    },
  },
};
</script>

<style>
.container {
  display: flex;
  width: 100%;
}

.left-side,
.right-side {
  width: 50%;
  padding: 20px;
  box-sizing: border-box;
}
.line-between-divs {
  position: relative;
}

.line-between-divs::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0%;
  width: 2px;
  height: 100%;
  background-color: rgb(8, 2, 2);
}

.line-between-divss {
  position: relative;
}

.line-between-divss::after {
  content: "";
  position: absolute;
  top: 0;
  left: 0%;
  width: 2px;
  height: 100%;
  background-image: linear-gradient(to bottom, gray 50%, transparent 50%);
  background-size: 1px 5px; /* 控制虚线的高度和间隔 */
}

</style>

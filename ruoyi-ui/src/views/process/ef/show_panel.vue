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
      <div class="containers">
        <!-- 1、左侧50%流程图内容 -->
        <div
          v-if="easyFlowVisible"
          style="width: 50%; height: calc(80vh)"
          class="left-side"
        >
          <el-row>
            <!--顶部工具菜单-->
            <el-col :span="24">
              <div class="ef-tooltar">
                <el-link type="primary" :underline="false"
                  >当前最新版本流程：{{ data.name }}</el-link
                >
                <el-divider direction="vertical"></el-divider>

                <el-button
                  size="mini"
                  type="text"
                  @click="updateDetails(data.id)"
                  >{{ buttonText }}</el-button
                >

                <el-divider direction="vertical"></el-divider>
                <!-- 打开新窗口，页面展示最新流程 -->
                <el-button size="mini" type="text" @click="openNewWin(data.id)"
                  >全屏预览</el-button
                >

                <el-divider direction="vertical"></el-divider>
              </div>
            </el-col>
          </el-row>
          <!-- 流程展示部分 -->
          <!-- <div style="display: flex; height: calc(100% - 47px)"> -->
          <div
            style="
              display: flex;
              flex-direction: column;
              height: calc(100% - 47px);
            "
          >
            <!-- <div id="myContainer" ref="myContainer" class="container"> -->
            <div
              id="myContainer"
              ref="myContainer"
              class="container"
              style="flex: 1"
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
            <!-- 新增的详情展示部分 -->
            <div
              v-if="showDetails"
              style="margin-top: 20px; padding: 10px; border: 1px solid #ccc"
            >
              <h3 style="font-weight: bold">更新详情内容：</h3>
              <!-- <p>{{ detailsData.file }}</p> -->
              <div v-html="formattedContent(detailsData.file)"></div>
            </div>
          </div>
          <!-- div over -->
          <!-- 新增的当前流程的 变更详情展示 部分 -->
        </div>

        <!-- 2-if、右侧50%历史数据部分 v-if="!showHisPanelVisible" -->
        <div
          style="width: 50%"
          class="right-side line-between-divs"
          v-if="!showHisPanelVisible"
        >
          <el-table
            v-loading="loading"
            :data="historyProjectList"
            height="calc(100% - 10px)"
            stripe
            row-key="id"
            :expand-row-keys="expandArr"
          >
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
            <el-table-column label="流程名称" align="center" prop="name" />
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
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-document"
                  @click="view(scope.row)"
                >
                  查看</el-button
                >
                <!-- v-hasPermi="['system:project:remove']" -->
                <el-button
                  size="mini"
                  type="text"
                  icon="el-icon-delete"
                  @click="handleDelete(scope.row)"
                  >删除</el-button
                >

                <el-button
                  size="mini"
                  type="text"
                  @click="openNewWin(scope.row.id)"
                  >全屏预览</el-button
                >

                <!-- 更新详情button -->
                <el-button
                  @click="toggleRow(scope.row)"
                  size="mini"
                  type="text"
                  :icon="
                    isExpanded(scope.row)
                      ? 'el-icon-caret-top'
                      : 'el-icon-caret-bottom'
                  "
                  >{{
                    isExpanded(scope.row) ? "关闭详情" : "更新详情"
                  }}</el-button
                >
              </template>
            </el-table-column>

            <!-- 展开行：展示更新的详细信息 -->
            <el-table-column type="expand" width="1">
              <template slot-scope="props">
                <!-- 根据展开状态显示不同的按钮文本 -->
                <div v-show="isExpanded(props.row)">
                  <h3 style="font-weight: bold">更新详情内容：</h3>
                  <!-- {{ formatFileContent(props.row.file) }} 
                   -->
                  <div v-html="formattedContent(props.row.file)"></div>
                </div>
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 2-else、右侧展示部分 -->
        <div
          v-else
          style="width: 50%; height: calc(80vh)"
          class="left-right line-between-divss"
        >
          <el-row>
            <!--顶部工具菜单-->
            <el-col :span="24">
              <div class="ef-tooltar">
                <el-link type="primary" :underline="false"
                  >历史版本{{ historyData.name }}</el-link
                >
                <!-- <el-divider direction="vertical"></el-divider>
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
                ></el-button> -->

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

                <!-- 流程信息的Json数据 -->
                <!-- <el-button
                  type="info"
                  plain
                  round
                  icon="el-icon-document"
                  @click="dataInfo"
                  size="mini"
                  >流程信息</el-button
                > -->
              </div>
            </el-col>
          </el-row>
          <div style="display: flex; height: calc(100% - 47px)">
            <div id="efContainer" ref="efContainer" class="container">
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
              <!-- <div style="position: absolute; top: 2000px; right: 2000px">
                &nbsp;
              </div> -->
            </div>
          </div>
        </div>
      </div>
    </el-dialog>
    <!-- 流程数据详情 -->
    <!-- <flow-info v-if="flowInfoVisible" ref="flowInfo" :data="data"></flow-info> -->
  </div>
</template>

  <script>
import draggable from "vuedraggable";
// import { jsPlumb } from 'jsplumb'
// 使用修改后的jsplumb
import "./jsplumb";
import "./hjsplumb";
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
import { getProjectEntityById } from "@/api/system/project";

export default {
  inject: ["reload"],
  data() {
    return {
      // 其他数据
      showDetails: false, // 控制展示区域显示的变量
      detailsData: null, // 存储获取到的数据
      buttonText: "更新详情", // 控制按钮文字的变量

      // 显示历史流程更新详情的查看
      expandArr: [], // 表示展开的行的 name 数组

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

      // 控制流程数据显示与隐藏
      flowInfoVisible: false,
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
    //open new windows -- 打开新窗口，转到数据展示部分
    //路由跳转
    openNewWin(id) {
      //item 是当前的最新版本流程
      // console.log("item========>",item)
      //设置id为每个流程都单独展示一个展示数据的页面
      this.$router.push("/process/ef/winPanel/" + id);
    },

    // 其他方法
    async updateDetails(id) {
      if (this.showDetails) {
        this.showDetails = false;
        this.buttonText = "更新详情";
      } else {
        // 模拟获取数据
        const data = await this.fetchData(id);
        this.detailsData = data;
        this.showDetails = true;
        this.buttonText = "关闭详情";
      }
    },
    async fetchData(id) {
      try {
        const response = await getProjectEntityById(id);
        console.log(response);
        return response;
      } catch (error) {
        console.error("Error fetching data:", error);
        return { title: "Error", content: "Failed to fetch data." };
      }
    },

    //更新详细数据
    toggleRow(row) {
      const rowKey = row.id; // 使用唯一标识 id
      if (this.expandArr.includes(rowKey)) {
        // 如果当前行已经展开，则折叠它
        this.expandArr = this.expandArr.filter((key) => key !== rowKey);
      } else {
        // 否则，只展开当前行
        this.expandArr = [rowKey];
      }
    },
    isExpanded(row) {
      return this.expandArr.includes(row.id);
    },

    //格式化换行
    formattedContent(content) {
      if (content) {
        // 确认替换前内容
        console.log("Original content:", content);
        const formatted = content.replace(/\\+/g, "<br>");
        // 确认替换后内容
        console.log("Formatted content:", formatted);
        return formatted;
      }
      return "无更新内容！";
    },
    // ------------------------------------------------------
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
        console.log("historyProjectList===>", this.historyProjectList);
      });

      // console.log("historyProjectList===>", this.historyProjectList)
    },

    /** 单个删除操作 */

    // handleDelete(row) {
    //   const ids = row.id || this.ids;
    //   this.$modal
    //     .confirm(`是否确认删除流程[ ${row.name} ]？`)
    //     .then(
    //       delHistoryProject(ids).then((response) => {})
    //       // function () { return delProject(ids);}
    //     )
    //     .then(() => {
    //       // this.getList();
    //       this.reload();
    //       this.$modal.msgSuccess("删除成功");
    //     })
    //     .catch(() => {});
    // },

    /** 单个删除操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal
        .confirm(
          `是否确认删除该历史版本流程[ ${row.name} ]？`
        )
        .then(() => {
          // 只有当用户确认删除时才执行删除操作
          return delHistoryProject(ids);
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
        // console.log("this.$refs.myContainer====>", this.$refs.myContainer);
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
        // console.log("this.$refs.efContainer====>", this.$refs.efContainer);
        this.hjsPlumb.setContainer(this.$refs.efContainer);
      });
    },

    // 流程数据信息
    dataInfo() {
      this.flowInfoVisible = true;
      this.$nextTick(function () {
        this.$refs.flowInfo.init();
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

    //控件大小--当前版本
    zoomAddMy() {
      if (this.zoom >= 1) {
        return;
      }
      this.zoom = this.zoom + 0.1;
      this.$refs.myContainer.style.transform = `scale(${this.zoom})`;
      this.jsPlumb.setZoom(this.zoom);
    },

    zoomSubMy() {
      if (this.zoom <= 0) {
        return;
      }
      this.zoom = this.zoom - 0.1;
      this.$refs.myContainer.style.transform = `scale(${this.zoom})`;
      this.jsPlumb.setZoom(this.zoom);
    },

    //控件大小--历史版本
    zoomAdd() {
      if (this.zoom >= 1) {
        return;
      }
      this.zoom = this.zoom + 0.1;
      this.$refs.efContainer.style.transform = `scale(${this.zoom})`;
      this.jsPlumb.setZoom(this.zoom);
    },

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
.containers {
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

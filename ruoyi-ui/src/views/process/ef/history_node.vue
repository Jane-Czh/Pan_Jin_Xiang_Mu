<template>
  <div
    ref="node"
    :style="nodeContainerStyle"
    @click="clickNode"
    @mouseup="changeNodeSite"
    :class="nodeContainerClass"
  >
    <!-- 最左侧的那条竖线 -->
    <div class="ef-node-left"></div>
    <!-- 节点类型的图标 -->
    <div class="ef-node-left-ico flow-node-drag">
      <i :class="nodeIcoClass"></i>
    </div>
    <!-- 节点名称 -->
    <div class="ef-node-text" :show-overflow-tooltip="true">
      {{ node.name }}
    </div>
    <!-- 节点状态图标 -> 改为表单绑定  -->
    <div class="ef-node-right-ico">
      <!-- 如果节点状态为 'no',悬浮效果为可绑定文件,显示链接文件图标 -->

      <el-tooltip
        v-if="node.state === 'no'"
        class="item"
        effect="dark"
        content="无文件绑定"
        placement="top"
      >
        <li class="el-icon-link ef-node-file-no"></li>
      </el-tooltip>

      <!-- 否则，显示已链接文件图标 -->

      <div
        v-else
        class="item"
        @mouseover="hoverPanel = true"
        
      >
        <li
          class="el-icon-link ef-node-file-yes"
          @click="dialogVisible = true"
        ></li>
        <!-- 悬浮时显示的面板，展示绑定的文件list  JSON.parse(this.fileHyperLinks[index]) -->
        <div v-if="hoverPanel" class="tooltip-panel" @mouseleave="hoverPanel = false">
          <p>--已绑定文件--</p>
          <ul>
            <li v-for="(file, index) in this.nodeFileNames" :key="index">
              <a target="_blank"
              :href="baseUrl + file" dowmnload>{{ file }}</a>
            </li>
          </ul>
        </div>

        <!-- 提示可继续文件的绑定或取消绑定 -->
        <el-tooltip
          v-if="node.state !== 'no'"
          class="item"
          effect="dark"
          content="已绑定如下文件"
          placement="top"
        >
          <li
            class="el-icon-files ef-node-file-yes"
            @click="(dialogMoreVisible = true), tempFile(node)"
          ></li>
        </el-tooltip>
      </div>
    </div>

    <!-- 绑定文件的dialog -->
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <span>请选择需要绑定的表单</span>
      <li
        class="el-icon-link ef-node-file-no-temp"
        @click="openFileDialog(node)"
      ></li>
      <br />
      <!-- 展示选择的表单名 -->
      <span v-if="selectedFileName">{{ selectedFileName }}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="(dialogVisible = false), (selectedFileName = null)"
          >取 消</el-button
        >
        <el-button type="primary" @click="confirmDialog(node)">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>
  
  <script>
import FlowNodeForm from "./node_form";
import { listFilemanagement } from "@/api/file/filemanagement";
export default {
  props: {
    node: Object,
    activeElement: Object,
  },
  data() {
    return {

      baseUrl: process.env.VUE_APP_BASE_API,

      //表单绑定的dialog
      dialogVisible: false,
      //存储绑定表单文件名称
      selectedFileName: null,
      //悬浮面板
      hoverPanel: false,

      // 制度文件数据
      filemanagementList: [],
      // 表单文件数据
      // node.state展示数据
      nodeFileNames: [],

      // 文件下载链接
      fileHyperLinks: [],
    };
  },
  components: {
    FlowNodeForm,
  },
  computed: {
    nodeContainerClass() {
      return {
        "ef-node-container": true,
        "ef-node-active":
          this.activeElement.type == "node"
            ? this.activeElement.nodeId === this.node.id
            : false,
      };
    },
    // 节点容器样式
    nodeContainerStyle() {
      return {
        top: this.node.top,
        left: this.node.left,
      };
    },
    nodeIcoClass() {
      var nodeIcoClass = {};
      nodeIcoClass[this.node.ico] = true;
      // 添加该class可以推拽连线出来，viewOnly 可以控制节点是否运行编辑
      nodeIcoClass["flow-node-drag"] = this.node.viewOnly ? false : true;
      return nodeIcoClass;
    },
  },
  created() {
    this.getRegularFileData();
  },
  methods: {
    /** 查询制度文件列表 */
    // getRegularFileData() {
    //   this.nodeFileNames = [];
    //   this.fileHyperLinks = [];
    //   //查询数据
    //   listFilemanagement(this.queryParams)
    //     .then((response) => {
    //       this.filemanagementList = response.rows;
    //     })
    //     .then(() => {
    //       // 制度文件数据filemanagementList 进行筛选 id==node.state的数据,将其赋值给selectedFileNames
    //       JSON.parse(this.node.state).forEach((stateId) => {
    //         let row = this.filemanagementList.find(
    //           (item) =>
    //             JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
    //         );
    //         console.log("this show node row-===->",row)
    //         //将匹配的记录的文件名、链接保存 (nodeFileNames、fileHyperLinks)
    //         this.nodeFileNames.push(row.fileName);
    //         this.fileHyperLinks.push(row.filePath);
    //       });
    //     });
    //   console.log("show_pane==>nodeFileNames==>", this.nodeFileNames);
    //   console.log("show_pane==>fileHyperLinks==>", this.fileHyperLinks);
    // },

    getRegularFileData() {
      this.nodeFileNames = [];
      this.fileHyperLinks = [];

      listFilemanagement(this.queryParams)
        .then((response) => {
          this.filemanagementList = response.rows;
          // console.log(" this.filemanagementList ==>", this.filemanagementList);
        })
        .then(() => {
          // 制度文件数据filemanagementList 进行筛选 id==node.state的数据,将其赋值给selectedFileNames
          if (this.node.state != "no") {
            JSON.parse(this.node.state).forEach((stateId) => {
              let row = this.filemanagementList.find(
                (item) =>
                  JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
              );
              if (row != null) {
                //将匹配的记录的文件名、链接保存 (nodeFileNames、fileHyperLinks)
                this.nodeFileNames.push(row.fileName);
                this.fileHyperLinks.push(row.filePath);
              }
            });
          }
        });
      // console.log("show_pane==>nodeFileNames==>", this.nodeFileNames);
      // console.log("show_pane==>fileHyperLinks==>", this.fileHyperLinks);
    },

    // 点击节点
    clickNode() {
      this.$emit("clickNode", this.node.id);
    },
    // 鼠标移动后抬起
    changeNodeSite() {
      // 避免抖动
      if (
        this.node.left == this.$refs.node.style.left &&
        this.node.top == this.$refs.node.style.top
      ) {
        return;
      }
      this.$emit("changeNodeSite", {
        nodeId: this.node.id,
        left: this.$refs.node.style.left,
        top: this.$refs.node.style.top,
      });
    },

    //TODO 正对node.state中的绑定文件名，去文件查找其地址，使得提供的文件能够下载/预览
  },
};
</script>
    
    <style scoped>
.item {
  position: relative;
}

.tooltip-panel {
  position: absolute;
  width: 178px;
  height: auto;
  background-color: #ffffff; /* 白色背景 */
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  z-index: 999;
  display: none;
  top: calc(100%); /* 让面板位于节点下方 */
  left: calc(100% - 78px); /* 水平居中 */
  transform: translateX(-50%); /* 水平居中 */
  font-size: 10px; /* 设置字体大小为 12px */
  color: black; /* 设置字体颜色为蓝色 */
}

.item:hover .tooltip-panel {
  display: block;
}
</style>
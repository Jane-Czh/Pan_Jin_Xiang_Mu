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

    <!--功能开发: 节点状态state & 类型type -> 改为文件绑定(制度、表单)  -->

    <!-- 1.1 右侧的[制度文件]显示-->
    <div class="ef-node-right-ico">
      <!-- 1、如果节点状态为 'no',悬浮效果为"无文件绑定",icon 显示链接文件的图标 -->
      <el-tooltip
        v-if="node.state === 'no'"
        class="item"
        effect="dark"
        content="无制度文件绑定"
        placement="top"
      >
        <li class="el-icon-link ef-node-file-no"></li>
      </el-tooltip>

      <!-- 2、否则，显示icon已链接文件图标, 且悬浮效果为 已绑定的制度文件list -->
      <div v-else class="item" @mouseover="hoverPanel = true">
        <li class="el-icon-files ef-node-file-yes"></li>
        <!-- 悬浮时显示的面板，展示绑定的文件list  JSON.parse(this.fileHyperLinks[index]) -->
        <div
          v-if="hoverPanel"
          class="tooltip-panel"
          @mouseleave="hoverPanel = false"
        >
          <p>--已绑定制度文件如下--</p>
          <ul>
            <li v-for="(file, index) in this.nodeFileNames" :key="index">
              {{ file }}
              <el-divider direction="vertical"></el-divider>
              <!-- 下载文件  -->
              <!-- <a target="_blank"  download :href="`baseUrl +'/' + this.fileHyperLinks[index]`"> </a -->
              <i
                class="el-icon-download download-icon"
                @click.prevent="
                  downloadRegularFile(index, this.fileHyperLinks[index])
                "
              ></i>

              <el-divider direction="vertical"></el-divider>
              <!-- 预览文件 -->
              <i class="el-icon-view preview-icon"></i>
            </li>
          </ul>
        </div>
      </div>
    </div>

    <!-- ---------------------------------------------- -->
    <!-- 1.2 右侧的[表单文件]绑定-->
    <div class="ef-node-rightform-ico">
      <!-- 1、如果节点状态为 'no',悬浮效果为可绑定文件,显示链接文件图标 -->
      <el-tooltip
        v-if="node.type === 'no'"
        class="item"
        effect="dark"
        content="无表单文件绑定"
        placement="top"
      >
        <li class="el-icon-paperclip ef-node-file-no"></li>
      </el-tooltip>

      <!-- 2、否则，icon显示已链接表单图标, 且悬浮效果为 已绑定的表单文件的list -->
      <div v-else class="item" @mouseover="hoverPanel = true">
        <li class="el-icon-tickets ef-node-file-yes"></li>
        <!-- 悬浮时显示的面板，展示绑定的文件list  JSON.parse(this.fileHyperLinks[index]) -->
        <div
          v-if="hoverPanel"
          class="tooltip-panel"
          @mouseleave="hoverFormPanel = false"
        >
          <p>--已绑定表单文件如下--</p>
          <ul>
            <li v-for="(file, index) in this.nodeFormNames" :key="index">
              <!-- <a
                target="_blank"
                :href="`baseUrl +'/' + this.formHyperLinks[index]`"
                dowmnload
                >{{ file }}</a
              > -->
              {{ file }}
              <el-divider direction="vertical"></el-divider>
              <!-- 下载文件  -->
              <!-- <a target="_blank"  download :href="`baseUrl +'/' + this.fileHyperLinks[index]`"> </a -->
              <i
                class="el-icon-download download-icon"
                @click.prevent="
                  downloadRegularFile(index, this.fileHyperLinks[index])
                "
              ></i>

              <el-divider direction="vertical"></el-divider>
              <!-- 预览文件 -->
              <i class="el-icon-view preview-icon"></i>
            </li>
          </ul>
        </div>
      </div>
    </div>
    <!-- over ---------------------------------------------- over -->
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

      //[制度文件]的悬浮面板
      hoverPanel: false,

      //Restful 获取的 [制度文件]数据
      filemanagementList: [],
      // 制度文件名称, 悬浮面板的展示数据
      nodeFileNames: [],
      // 制度文件下载链接
      fileHyperLinks: [],

      // <!-- ---------------------------------------------- -->
      //表单文件悬浮面板
      hoverFormPanel: false,

      //Restful 获取的 [表单文件]数据
      formList: [],
      // 表单文件名称, 悬浮面板的展示数据
      nodeFormNames: [],
      // 表单文件下载链接
      formHyperLinks: [],

      queryParams: {
        pageNum: 1,
        pageSize: 10,
        regulationsTitle: null,
        useScope: null,
        uploadDate: null,
        effectiveDate: null,
        regulationsAddress: null,
        fileName: null,
        filePath: null,
        fileType: null,
        fileSize: null,
        createDate: null,
        uploadUsername: null,
        useState: null,
        departmentCategory: null,
        fileTag: null,
        oldRegulationsId: null,
        revisionDate: null,
        revisionContent: null,
        reviser: null,
        newFlag: null,
        oldFlag: null,
      },
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
    this.getFormFileData();
  },
  methods: {
    //下载制度文件
    downloadRegularFile(index, url) {
      // const downloadUrl = `${this.baseUrl}/${this.fileHyperLinks[index]}`;
      // // 创建一个隐藏的链接并设置其下载属性
      // const link = document.createElement("a");
      // link.href = downloadUrl;
      // link.download = true;
      // // 将链接添加到文档中并触发点击事件
      // document.body.appendChild(link);
      // link.click();
      // // 清理掉创建的链接
      // document.body.removeChild(link);
    },
    //下载表单文件
    downloadFormFile(index, url) {
      // const downloadUrl = `${this.baseUrl}/${this.fileHyperLinks[index]}`;
      // // 创建一个隐藏的链接并设置其下载属性
      // const link = document.createElement("a");
      // link.href = downloadUrl;
      // link.download = true;
      // // 将链接添加到文档中并触发点击事件
      // document.body.appendChild(link);
      // link.click();
      // // 清理掉创建的链接
      // document.body.removeChild(link);
    },

    // 正对绑定的文件id，去文件管理 查找其地址，使得提供的文件能够 下载/预览

    /** 查询制度文件列表 */
    getRegularFileData() {
      //存储制度文件名称
      this.nodeFileNames = [];
      //存储相应的下载地址
      this.fileHyperLinks = [];

      listFilemanagement(this.queryParams)
        .then((response) => {
          this.filemanagementList = response.rows;
        })
        .then(() => {
          // 制度文件数据filemanagementList 进行筛选 id==node.state的数据,将其赋值给nodeFileNames
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
    },

    /** 查询表单文件列表 */
    getFormFileData() {
      //存储表单文件名称
      this.nodeFormNames = [];
      //存储相应的下载地址
      this.formHyperLinks = [];

      listFilemanagement(this.queryParams)
        .then((response) => {
          this.formList = response.rows;
        })
        .then(() => {
          // 表单文件数据formList 进行筛选 id==node.type 的数据,将其赋值给 nodeFormNames
          if (this.node.type != "no") {
            JSON.parse(this.node.type).forEach((stateId) => {
              let row = this.formList.find(
                (item) =>
                  JSON.stringify(item.regulationsId) === JSON.stringify(stateId)
              );
              if (row != null) {
                //将匹配的记录的文件名、链接保存 (nodeFileNames、fileHyperLinks)
                this.nodeFormNames.push(row.fileName);
                this.formHyperLinks.push(row.filePath);
              }
            });
          }
        });
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
  },
};
</script>

<style scoped>
.item {
  position: relative;
}
/**制度文件面板 */
.tooltip-panel {
  position: absolute;
  width: 250px;
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

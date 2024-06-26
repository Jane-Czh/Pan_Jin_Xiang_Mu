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
              <!-- {{ this.fileHyperLinks[index] }} -->
              <el-divider direction="vertical"></el-divider>
              <!-- 下载文件  -->
              <i class="el-icon-download download-icon">
                <a :href="baseUrl + fileHyperLinks[index]" download>点击下载</a>
              </i>
              <el-divider direction="vertical"></el-divider>
              <!-- 预览文件 -->
              <i
                class="el-icon-view preview-icon"
                @click="previewFile(fileHyperLinks[index])"
              >
                <a href="#"> 预览 </a>
              </i>
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
              {{ file }}
              <!-- {{ this.formHyperLinks[index] }} -->
              <el-divider direction="vertical"></el-divider>
              <!-- 下载文件  -->
              <i class="el-icon-download download-icon">
                <a :href="baseUrl + formHyperLinks[index]" download>点击下载</a>
              </i>
              <el-divider direction="vertical"></el-divider>
              <!-- 预览文件 -->
              <i
                class="el-icon-view preview-icon"
                @click="previewFile(formHyperLinks[index])"
              >
                <a href="#"> 预览 </a>
              </i>
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
// //制度文件api
// import { listFilemanagement, word2Pdf } from "@/api/file/filemanagement";
// //表单文件api
// import { listFormfilemanagement } from "@/api/file/formfilemanagement";

import { listFilemanagement, listFormfilemanagement } from "@/api/system/project";
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
    // 文件预览
    previewFile(filePath) {
      console.log("filePath=======>", filePath);

      const fileType = this.getFileType(filePath);
      switch (fileType) {
        case "pdf":
          window.open(filePath, "_blank");
          break;
        case "word":
          const pdfFilePath = this.convertToPdfPath(filePath);
          word2Pdf(filePath, pdfFilePath).then((response) => {});
          window.open(pdfFilePath, "_blank");
          break;
      }
      // 使用 window.open 方法打开一个新窗口，并将文件路径传递给该窗口
    },

    getFileType(filePath) {
      // 获取文件名的后缀名
      const fileExtension = filePath.split(".").pop();
      // 根据文件后缀名判断文件类型
      switch (fileExtension.toLowerCase()) {
        case "pdf":
          return "pdf";
        case "doc":
        case "docx":
          return "word";
        case "xls":
        case "xlsx":
          return "Excel 文档";
        case "ppt":
        case "pptx":
          return "PowerPoint 文档";
        // 可以根据需要添加更多的文件类型判断
        default:
          return "未知类型";
      }
    },

    convertToPdfPath(wordFilePath) {
      // 找到文件路径中的最后一个点的位置
      const lastDotIndex = wordFilePath.lastIndexOf(".");

      if (lastDotIndex != -1) {
        // 获取文件路径中最后一个点之前的部分（文件名部分）
        const prefix = wordFilePath.substring(0, lastDotIndex);

        // 将文件名部分与 .pdf 后缀拼接，形成 PDF 文件路径
        const pdfFilePath = prefix + ".pdf";

        return pdfFilePath;
      } else {
        // 文件路径中没有点，无法更改后缀
        throw new IllegalArgumentException("文件路径无效：" + wordFilePath);
      }
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
          // console.log(
          //   "制度文件名称===== this.nodeFileNames===>",
          //   this.nodeFileNames
          // );

          // console.log(
          //   "制度文件===》预览部分的filePath==== this.fileHyperLinks===>",
          //   this.fileHyperLinks
          // );
        });
    },

    /** 查询表单文件列表 */
    getFormFileData() {
      //存储表单文件名称
      this.nodeFormNames = [];
      //存储相应的下载地址
      this.formHyperLinks = [];

      listFormfilemanagement(this.queryParams)
        .then((response) => {
          this.formList = response.rows;
        })
        .then(() => {
          // 表单文件数据formList 进行筛选 id==node.type 的数据,将其赋值给 nodeFormNames
          if (this.node.type != "no") {
            JSON.parse(this.node.type).forEach((stateId) => {
              let row = this.formList.find(
                (item) =>
                  JSON.stringify(item.formId) === JSON.stringify(stateId)
              );
              if (row != null) {
                //将匹配的记录的文件名、链接保存 (nodeFileNames、formHyperLinks)
                this.nodeFormNames.push(row.formName);
                this.formHyperLinks.push(row.formPath);
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
  top: calc(100%-100px); /* 让面板位于节点下方 */
  left: calc(100% - 78px); /* 水平居中 */
  transform: translateX(-50%); /* 水平居中 */
  font-size: 10px; /* 设置字体大小为 12px */
  color: black; /* 设置字体颜色为蓝色 */
}

.item:hover .tooltip-panel {
  display: block;
}
</style>

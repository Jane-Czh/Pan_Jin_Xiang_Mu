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
    <!-- 节点状态图标 -> 改为文件绑定(制度、表单、文档)  -->
    <div class="ef-node-right-ico">
      <!-- 如果节点状态为 'no',悬浮效果为可绑定文件,显示链接文件图标 -->
      <el-tooltip
        v-if="node.state === 'no'"
        class="item"
        effect="dark"
        content="点击进行文件绑定"
        placement="top"
      >
        <li
          class="el-icon-link ef-node-file-no"
          @click="dialogVisible = true"
        ></li>
      </el-tooltip>

      <!-- 否则，显示已链接文件图标 -->
      <el-tooltip
        v-else
        class="item"
        effect="dark"
        :content="`已绑定文件【 ${node.state} 】`"
        placement="top"
      >
        <li
          class="el-icon-files ef-node-file-yes"
          @click="dialogMoreVisible = true,tempFile(node)"
        ></li>
      </el-tooltip>
    </div>
    <!-- 绑定文件的dialog -->
    <el-dialog
      title="流程节点绑定文件"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
      destroy-on-close="true"
    >
      <span>请选择需要绑定的文件</span>
      <li
        class="el-icon-link ef-node-file-no-temp"
        @click="openFileDialog(node)"
      ></li>
      <br />
      <!-- 展示选择的表单名 -->
      <!-- <span v-if="selectedFileName">{{ selectedFileName }}</span> -->
      <ul v-if="this.selectedFileNames">
        <li v-for="(fileName, index) in this.selectedFileNames" :key="index">
          {{ fileName }}
        </li>
      </ul>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取消选择</el-button>
        <el-button type="primary" @click="confirmDialog(node)">确 定</el-button>
      </span>
    </el-dialog>
    <!-- 继续绑定文件的dialog -->
    <el-dialog
      title="请继续绑定文件"
      :visible.sync="dialogMoreVisible"
      width="30%"
      :before-close="handleClose"
      destroy-on-close="true"
    >
      <span>请选择需要绑定的文件</span>
      <li
        class="el-icon-link ef-node-file-no-temp"
        @click="openFileDialog(node)"
      ></li>
      <br />
      <!-- 展示选择的表单名 -->
      <!-- <span v-if="selectedFileName">{{ selectedFileName }}</span> -->
      <ul v-if="this.selectedFileNames">
        <li v-for="(fileName, index) in this.selectedFileNames" :key="index">
          {{ fileName }}
        </li>
      </ul>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancleBand(node)">取消绑定</el-button>
        <el-button type="primary" @click="confirmDialog(node)">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import FlowNodeForm from "./node_form";

export default {
  props: {
    node: Object,
    activeElement: Object,
  },
  data() {
    return {
      //表单绑定的dialog
      dialogVisible: false,
      //继续绑定文件dialog
      dialogMoreVisible: false,
      //存储绑定表单文件名称
      //清零
      selectedFileName: [],
      //选择
      selectedFileNames: [],
      //临时
      selectedTemp: [],
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
  methods: {
    //节点绑定文件
    /**
     * @param state 当前节点是否绑定文件
     * 说明:
     *  没有绑定文件: 悬浮效果显示“可绑定文件”
     *        点击效果为：从表单list中可以选择selectbox进行绑定,存储的是表单的名称即可
     *  绑定了文件的: 悬浮效果显示绑定了的文件的名称
     *        点击效果为：继续进行绑定或对绑定的文件进行解除绑定
     *
     * 此外，点击了节点时，在右侧编辑中，可用下拉列表展示已经绑定了的文件(流程关联--查看功能中)
     *
     *
     * 节点可以绑定多个文件，此外还能解绑文件
     */
    //openFileDialog中的取消button
    cancle() {
      this.dialogVisible = false;
      this.selectedFileNames = this.selectedFileName;
    },
    cancleBand(node) {
      this.dialogMoreVisible = false;
      this.selectedFileNames = this.selectedFileName;
      this.selectedTemp = this.selectedFileName;
      node.state = "no";
      this.$refs.nodeForm.save(this.data, node.id);
      // 进行画布节点信息的更新
      this.$emit("repaintEverything");
    },

    //绑定文件
    chooseFiles() {
      //扩充一次能绑定多个文件,且不选择重复的文件
      const input = document.createElement("input");
      input.type = "file";
      input.onchange = (e) => {
        const file = e.target.files[0];
        if (file) {
          // 获取文件名
          const fileName = file.name;
          console.log("fileName==>", fileName);
          // 检查文件名是否已经存在于数组中
          if (!this.selectedFileNames.includes(fileName)) {
            // 将文件名存储在组件数据中
            this.selectedFileNames.push(fileName);
          } else {
            // 如果文件名已经存在于数组中，则给出提示
            this.$message.warning("文件已经在选定列表中,请勿重复添加!!!");
          }
        } else {
          // 如果用户取消选择文件，则给出提示
          this.$message.warning("未选择文件");
        }
        // 触发下一次文件选择对话框
        input.click();
      };
      // 触发文件选择对话框
      input.click();
    },

    openFileDialog(node) {
      this.chooseFiles();
    },

    tempFile(node){
      if (node.state != "no") {
        //提示还需要绑定表单吗？或者对绑定的文件进行解绑
        this.selectedFileNames = this.selectedTemp;
        // console.log("this.selectedFileNames++====:", this.selectedFileNames);
      }
    },
    //展示选择的文件名的dialog
    confirmDialog(node) {
      this.$confirm("确认进行绑定？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          if (this.selectedFileNames.length === 0) {
            this.$message.warning("未选择文件,无法绑定！请先选择文件再绑定！");
          } else {
            //将文件名绑定到 node.state, 并进行更新节点信息
            this.selectedTemp = this.selectedFileNames;
            node.state = this.selectedFileNames;
            console.log("this.selectedTemp==>", this.selectedTemp);
            this.$refs.nodeForm.save(this.data, node.id);
            // 进行画布节点信息的更新
            this.$emit("repaintEverything");
          }
        })
        .catch(() => {
          // 用户点击取消按钮的操作
          // 在此处可以执行确认绑定的逻辑
          this.dialogVisible = false;
          this.dialogMoreVisible = false;
          // 清除选择的文件名
          this.selectedFileNames = this.selectedFileName;
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

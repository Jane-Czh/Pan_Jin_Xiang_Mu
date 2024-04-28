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

    <!--功能开发: 节点状态图标 -> 改为文件绑定(制度、表单、文档)  -->

    <!-- 1.1 右侧的制度文件绑定-->
    <div class="ef-node-right-ico">
      <!-- 1、如果节点状态为 'no',悬浮效果为可绑定文件,显示链接文件图标 -->
      <el-tooltip
        v-if="node.state === 'no'"
        class="item"
        effect="dark"
        content="点击进行制度文件绑定"
        placement="top"
      >
        <li
          class="el-icon-link ef-node-file-no"
          @click="dialogVisible = true"
        ></li>
      </el-tooltip>

      <!-- 2、否则，显示已链接文件图标 -->
      <el-tooltip
        v-else
        class="item"
        effect="dark"
        :content="`已绑定制度文件【 ${this.selectedFileNames} 】`"
        placement="top"
      >
        <li
          class="el-icon-files ef-node-file-yes"
          @click="tempFile(node), (dialogMoreVisible = true)"
        ></li>
        <div>{{ this.selectedFileNames }}</div>
      </el-tooltip>
    </div>

    <!-- ---------------------------------------------- -->

    <!-- 1.2 右侧的表单文件绑定-->
    <div class="ef-node-rightform-ico">
      <!-- 1、如果节点状态为 'no',悬浮效果为可绑定文件,显示链接文件图标 -->
      <el-tooltip
        v-if="node.type === 'no'"
        class="item"
        effect="dark"
        content="点击进行表单文件绑定"
        placement="top"
      >
        <li
          class="el-icon-paperclip ef-node-file-no"
          @click="formsVisible = true"
        ></li>
      </el-tooltip>

      <!-- 2、否则，显示已链接表单 图标 -->
      <el-tooltip
        v-else
        class="item"
        effect="dark"
        :content="`已绑定表单文件【 ${this.selectedFormsNames} 】`"
        placement="top"
      >
        <li
          class="el-icon-tickets ef-node-file-yes"
          @click="tempForms(node), (formsMoreVisible = true)"
        ></li>
        <div>{{ this.selectedFormsNames }}</div>
      </el-tooltip>
    </div>

    <!-- ---------------------------------------------- -->

    <!-- 2、显示绑定的文件(制度和表单)的dialog -->
    <!-- 2.1、绑定制度文件的dialog -->
    <el-dialog
      title="流程节点绑定文件(请选择需要绑定的制度文件)"
      :visible.sync="dialogVisible"
      width="60%"
      style="height: 1000px"
      :before-close="handleClose"
      destroy-on-close="true"
    >
      <!-- ref 组件 el-table 显示制度文件的数据 -->
      <div>
        <custom-tabs ref="customTabs"></custom-tabs>
      </div>
      <!-- 展示选择的表单名 -->
      <!-- <span v-if="selectedFileName">{{ selectedFileName }}</span> -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancle()">取消选择</el-button>
        <el-button type="primary" @click="confirmDialog(node)">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 2.2、继续绑定制度文件的dialog -->
    <el-dialog
      title="继续绑定制度文件"
      :visible.sync="dialogMoreVisible"
      width="60%"
      style="height: 1000px"
      :before-close="handleClose"
      destroy-on-close="true"
    >
      <!-- ref 组件 el-table 显示制度文件的数据 -->
      <div>
        <custom-tabs ref="customTabs" :selectedFileNames="this.selectedFileNames"></custom-tabs>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancleBand(node)">取消绑定</el-button>
        <el-button type="primary" @click="confirmDialog(node)">确 定</el-button>
      </span>
    </el-dialog>

    <!-- ---------------------------------------------- -->

    <!-- 2.2、绑定表单文件的dialog: formsVisible  formsMoreVisible-->
    <el-dialog
      title="流程节点绑定文件(请选择需要绑定的表单文件)"
      :visible.sync="formsVisible"
      width="60%"
      style="height: 1000px"
      :before-close="handleClose"
      destroy-on-close="true"
    >
      <!-- ref 组件 el-table 显示制度文件的数据 -->
      <div>
        <custom-forms ref="customForms"></custom-forms>
      </div>
      <!-- 展示选择的表单名 -->
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancleForms()">取消选择</el-button>
        <el-button type="primary" @click="confirmFormsDialog(node)"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <!-- 2.2、继续绑定表单文件的dialog -->
    <el-dialog
      title="继续绑定表单文件"
      :visible.sync="formsMoreVisible"
      width="60%"
      style="height: 1000px"
      :before-close="handleClose"
      destroy-on-close="true"
    >
      <!-- ref 组件 el-table 显示制度文件的数据 -->
      <div>
        <custom-forms ref="customForms"></custom-forms>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancleFormsBand(node)">取消绑定</el-button>
        <el-button type="primary" @click="confirmFormsDialog(node)"
          >确 定</el-button
        >
      </span>
    </el-dialog>

    <!-- over ---------------------------------------------- over-->
  </div>
</template>

<script>
import FlowNodeForm from "./node_form";
import CustomTabs from "./CustomTabs.vue";
import CustomForms from "./CustomForms.vue";

export default {
  props: {
    node: Object,
    activeElement: Object,
  },
  data() {
    return {
      //制度文件绑定的dialog
      dialogVisible: false,
      //制度文件继续绑定的dialog
      dialogMoreVisible: false,
      //表单文件绑定的dialog
      formsVisible: false,
      //表单文件继续绑定的dialog
      formsMoreVisible: false,

      /**存储的文件名称**/
      //清零
      selectedFileName: [],

      //选择进行展示的 [制度文件] names
      selectedFileNames: [],
      //临时制度文件names保存
      selectedTemp: [],

      //选择进行展示的 [表单文件] names
      selectedFormsNames: [],
      //临时表单文件names保存
      selectedFormsTemp: [],
    };
  },
  components: {
    FlowNodeForm,
    CustomTabs,
    CustomForms,
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

    /** 制度文件部分 */
    //取消选择-对应节点还未绑定文件时
    cancle() {
      this.dialogVisible = false;
      this.selectedFileNames = this.selectedFileName;
      this.selectedTemp = this.selectedFileName;
    },
    //取消绑定-对应节点已经绑定了文件想要取绑
    cancleBand(node) {
      this.dialogMoreVisible = false;
      this.selectedFileNames = this.selectedFileName;
      this.selectedTemp = this.selectedFileName;
      node.state = "no";
      this.$refs.nodeForm.save(this.data, node.id);
      // 进行画布节点信息的更新
      this.$emit("repaintEverything");
    },

    // 继续绑定文件--回显效果
    tempFile(node) {
      if (JSON.stringify(node.state) != JSON.stringify("no")) {
        //提示还需要绑定表单吗？或者对绑定的文件进行解绑
        this.selectedFileNames = this.selectedTemp;
        this.$nextTick(function () {
          this.$refs.customTabs.setSelectedFileNames(this.selectedFileNames);
        });
      }
    },

    //展示选择的文件名的dialog 点击确定后进行[绑定文件]
    confirmDialog(node) {
      this.$confirm("确认进行绑定？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 在这里处理 ids 和 names 数据

          // 调用 CustomTabs 组件的方法来获取 ids 和 names 数据
          const { ids, names } = this.$refs.customTabs.getSelectedIdsAndNames();
          // 将获取到的filenames给本地的展示变量：this.selectedFileNames
          this.selectedFileNames = names;

          if (this.selectedFileNames.length === 0) {
            this.$message.warning("未选择文件,无法绑定！请先选择文件再绑定！");
          } else {
            this.selectedTemp = this.selectedFileNames;
            //将文件名[ids]绑定到 node.state, 并进行更新节点信息
            node.state = ids;
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
          // this.selectedFileNames = this.selectedFileName;
        });
    },

    /** 表单文件部分 */
    // 继续绑定文件
    tempForms(node) {
      if (JSON.stringify(node.type) != JSON.stringify("no")) {
        //提示还需要绑定表单吗？或者对绑定的文件进行解绑
        this.selectedFormsNames = this.selectedFormsTemp;

        this.$refs.customForms.setSelectedFileNames(this.selectedFormsNames);
      }
    },
    // 确认绑定按钮
    confirmFormsDialog(node) {
      this.$confirm("确认进行绑定？", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          // 在这里处理 ids 和 names 数据

          // 调用 CustomForms 组件的方法来获取 ids 和 names 数据
          const { ids, names } =
            this.$refs.customForms.getSelectedIdsAndNames();
          // 将获取到的filenames给本地的展示变量：this.selectedFormsNames
          this.selectedFormsNames = names;

          if (this.selectedFormsNames.length === 0) {
            this.$message.warning("未选择文件,无法绑定！请先选择文件再绑定！");
          } else {
            this.selectedFormsTemp = this.selectedFormsNames;
            //将文件名[ids]绑定到 node.type, 并进行更新节点信息
            node.type = ids;
            this.$refs.nodeForm.save(this.data, node.id);
            // 进行画布节点信息的更新
            this.$emit("repaintEverything");
          }
        })
        .catch(() => {
          // 用户点击取消按钮的操作
          // 在此处可以执行确认绑定的逻辑
          this.formsVisible = false;
          this.formsMoreVisible = false;
          // 清除选择的文件名
          // this.selectedFormsNames = this.selectedFileName;
        });
    },

    //取消选择-对应节点还未绑定文件时 formsVisible  formsMoreVisible  selectedFormsNames selectedFormsTemp
    cancleForms() {
      this.formsVisible = false;
      this.selectedFormsNames = this.selectedFileName;
      this.selectedFormsTemp = this.selectedFileName;
    },
    //取消绑定-对应节点已经绑定了文件想要取绑
    cancleFormsBand() {
      this.formsMoreVisible = false;
      this.selectedFormsNames = this.selectedFileName;
      this.selectedFormsTemp = this.selectedFileName;

      node.type = "no";
      this.$refs.nodeForm.save(this.data, node.id);
      // 进行画布节点信息的更新
      this.$emit("repaintEverything");
    },

    /** ------------------------------------- */

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

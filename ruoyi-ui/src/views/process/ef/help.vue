<template>
  <el-dialog
    title="帮助"
    :visible.sync="dialogVisible"
    width="70%"
    customClass="flowHelp"
  >
    <el-tabs tab-position="left">
      <el-tab-pane label="如何新增">
        <el-divider content-position="left">如何新增</el-divider>
        <div>按住鼠标拖拽左侧组件到中间画布中松开鼠标即可</div>
      </el-tab-pane>
      <el-tab-pane label="如何删除">
        <el-divider content-position="left">删除节点/连线</el-divider>
        <div>鼠标先点中需要删除的节点/连线，再点击左上角的删除图标。
          <br>
          判断是否已选中节点/连线：查看删除图标是否亮起。
        </div>
        <!-- <el-divider content-position="left">通过代码删除</el-divider>
                <pre>this.deleteNode(nodeId)</pre> -->
      </el-tab-pane>
      <el-tab-pane label="如何移动">
        <el-divider content-position="left">如何移动</el-divider>
        <div>
          鼠标移动到节点中，当鼠标变为可拖拽的图标时按下鼠标移动到新的位置松开鼠标
        </div>
      </el-tab-pane>
      <el-tab-pane label="如何连线">
        <el-divider content-position="left">如何连线</el-divider>
        <div>
          鼠标移动到节点中左侧的图标上，当鼠标变为+时按下鼠标移动到另一个节点中松开鼠标
        </div>
      </el-tab-pane>
      <el-tab-pane label="如何添加条件">
        <el-divider content-position="left">如何添加条件</el-divider>
        <div>
          点击画布中的连线，在页面右侧会出现一个表单，输入新的条件，点击【保存】
        </div>
      </el-tab-pane>
      <!-- <el-tab-pane label="如何进行后端交互存储">
                <el-divider content-position="left">如何进行后端交互存储</el-divider>
                <div>参考: https://gitee.com/xiaoka2017/easy-flow-sdk</div>
            </el-tab-pane> -->
      <el-tab-pane label="如何绑定文件">
        <el-divider content-position="left">如何绑定文件</el-divider>
        <div>
          点击流程节点中的链接标志:
          <i class="el-icon-paperclip"></i> 为绑定表单文件,
          <i class="el-icon-link"></i> 为绑定制度文件.
        </div>
      </el-tab-pane>
      <!--  -->
      <el-tab-pane label="如何编辑节点属性">
        <el-divider content-position="left">如何编辑节点属性</el-divider>
        <div>
          点击选中节点，在页面右侧的【编辑】侧边栏中进行编辑相关属性.
        </div>
      </el-tab-pane>
    </el-tabs>
  </el-dialog>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
    };
  },
  components: {},
  methods: {
    init() {
      this.dialogVisible = true;
    },
  },
};
</script>

<style>
.flowHelp {
  height: 80%;
}
</style>

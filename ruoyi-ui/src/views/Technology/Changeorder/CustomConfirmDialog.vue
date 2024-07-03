<template>
  <el-dialog
    :visible.sync="visible"
    :title="title"
    @close="handleClose"
  >
    <span>{{ message }}</span>
    <span slot="footer" class="dialog-footer">
      <el-button @click="handleCancel">不通过</el-button>
      <el-button type="primary" @click="handleConfirm">通过</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  props: {
    title: String,
    message: String,
    visible: Boolean,
  },
  methods: {
    handleConfirm() {
      this.$emit('confirm');
      this.visible = false;
    },
    handleCancel() {
      this.$emit('cancel');
      this.visible = false;
    },
    handleClose() {
      this.$emit('close');
    },
  },
};
</script>

<template>
  <el-col :span="1.5">
    <!--Excel 参数导入 -->
    <el-button type="primary" icon="el-icon-share" size="mini" plain @click="showDialog = true" v-if="true">导入Excel
    </el-button>

    <el-dialog title="导入Excel" :visible.sync="showDialog" width="30%" @close="resetFileInput">
      <!-- 下拉框 -->
      <el-form :model="form" ref="form" label-width="90px">
        <el-form-item label="选择表类型">
          <el-select v-model="selectedType" placeholder="请选择Excel类型">
            <el-option :label="name" value="profit"></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <i class="el-icon-upload"></i>
      <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />
      <!-- 进度动画条 -->
      <div v-if="progress > 0">
        <el-progress :percentage="progress" color="rgb(19, 194, 194)"></el-progress>
      </div>
      <span slot="footer" class="dialog-footer">
        <el-button @click="showDialog = false">取 消</el-button>
        <el-button type="primary" @click="fileSend()">确 定</el-button>
      </span>
    </el-dialog>
  </el-col>


</template>

<script>


export default {
  props: {
    name: { type: String, default: null },
    url: { type: String, default: null },
    // dataName: { type: String, default: '金额' },
    // xAxisData: { type: Array, default: () => [] },
    // yAxisData: { type: Array, default: () => [] },
  },
  data() {
    return {
      loading: false,
      showDialog: false,
      progress: 0,
      selectedType: '',
    }
  },
  mounted() {

  },
  methods: {

    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    /** 导入按钮 */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      formData.append("excelFile", file);
      if (this.selectedType === 'profit') {
        axios({
          method: "post",
          url: "http://localhost:8080" + this.url,

          headers: {
            "Content-Type": "multipart/form-data",
          },
          withCredentials: true,
          data: formData,
          onUploadProgress: (progressEvent) => {
            this.progress = Math.round(
              (progressEvent.loaded * 100) / progressEvent.total
            );
          },
        })
          .then(response => {
            // 处理请求成功的情况
            this.showDialog = false; // 关闭上传面板
          })
          .catch(error => {
            // 处理请求失败的情况
            console.error('上传失败：', error);
          });

        this.$message.success("上传成功");

        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板

          // location.reload(); // 调用此方法刷新页面数据
        }, 2000); // 2000毫秒后关闭
      }
    },


  },
}
</script>
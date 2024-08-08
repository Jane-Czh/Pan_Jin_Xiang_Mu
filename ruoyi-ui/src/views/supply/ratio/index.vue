<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="物料号" prop="materialNumber">
        <el-input
          v-model="queryParams.materialNumber"
          placeholder="请输入物料号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="物料类别" prop="materialClass">
        <el-input
          v-model="queryParams.materialClass"
          placeholder="请输入物料类别"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商代码" prop="supplierCode">
        <el-input
          v-model="queryParams.supplierCode"
          placeholder="请输入供应商代码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供应商名称" prop="supplierName">
        <el-input
          v-model="queryParams.supplierName"
          placeholder="请输入供应商名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="供货比例" prop="supplyProportion">
        <el-input
          v-model="queryParams.supplyProportion"
          placeholder="请输入供货比例"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="付款方式" prop="paymentMethod">
        <el-input
          v-model="queryParams.paymentMethod"
          placeholder="请输入付款方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="比例统计方式" prop="proportionStatisticalMethod">
        <el-input
          v-model="queryParams.proportionStatisticalMethod"
          placeholder="请输入比例统计方式"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="是否入驻第三方" prop="thirdParty">
        <el-input
          v-model="queryParams.thirdParty"
          placeholder="请输入是否入驻第三方"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="备注" prop="Remarks">
        <el-input
          v-model="queryParams.Remarks"
          placeholder="请输入备注"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="实际比例" prop="actualProportion">
        <el-input
          v-model="queryParams.actualProportion"
          placeholder="请输入实际比例"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="差异" prop="difference">
        <el-input
          v-model="queryParams.difference"
          placeholder="请输入差异"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['supply:ratio:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['supply:ratio:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['supply:ratio:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['supply:ratio:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="syncReport"
        v-hasPermi="['supply:ratio:syncReport']"
      >同步数据</el-button>
      </el-col>
      <el-col :span="1.5">
        <!--Excel 参数导入 -->

        <!-- <el-button
          type="primary"
          @click="showDialog = true"
        ><i class="fa fa-download"></i>导入Excel文件
        </el-button> -->

        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
          v-hasPermi="['supply:ratio:import']"
        >导入Excel文件</el-button>

        <el-dialog
          title="导入Excel文件"
          :visible.sync="showDialog"
          width="30%"
          :before-close="handleClose"
          @close="resetFileInput"
        >
          <i class="el-icon-upload"></i>
          <input type="file" id="inputFile" ref="fileInput" @change="checkFile" />

          <!-- 进度动画条 -->
          <div v-if="progress > 0">
            <el-progress
              :percentage="progress"
              color="rgb(19, 194, 194)"
            ></el-progress>
          </div>

          <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取 消</el-button>
          <el-button type="primary" @click="fileSend()">确 定</el-button>
        </span>
        </el-dialog>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="ratioList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="srftId" /> -->
      <el-table-column label="物料号" align="center" prop="materialNumber" />
      <el-table-column label="物料类别" align="center" prop="materialClass" />
      <el-table-column label="供应商代码" align="center" prop="supplierCode" />
      <el-table-column label="供应商名称" align="center" prop="supplierName" />
      <el-table-column label="供货比例" align="center" prop="supplyProportion" />
      <el-table-column label="付款方式" align="center" prop="paymentMethod" />
      <el-table-column label="比例统计方式" align="center" prop="proportionStatisticalMethod" />
      <el-table-column label="是否入驻第三方" align="center" prop="thirdParty" />
      <el-table-column label="备注" align="center" prop="Remarks" />
      <el-table-column label="实际比例" align="center" prop="actualProportion" />
      <el-table-column label="差异" align="center" prop="difference" >
        <template slot-scope="scope">
          <span :style="{ color: getDifferenceColor(scope.row.difference) }">
            {{ scope.row.difference }}
          </span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['supply:ratio:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['supply:ratio:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改月供货比例统计对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="物料号" prop="materialNumber">
          <el-input v-model="form.materialNumber" placeholder="请输入物料号" />
        </el-form-item>
        <el-form-item label="物料类别" prop="materialClass">
          <el-input v-model="form.materialClass" placeholder="请输入物料类别" />
        </el-form-item>
        <el-form-item label="供应商代码" prop="supplierCode">
          <el-input v-model="form.supplierCode" placeholder="请输入供应商代码" />
        </el-form-item>
        <el-form-item label="供应商名称" prop="supplierName">
          <el-input v-model="form.supplierName" placeholder="请输入供应商名称" />
        </el-form-item>
        <el-form-item label="供货比例" prop="supplyProportion">
          <el-input v-model="form.supplyProportion" placeholder="请输入供货比例" />
        </el-form-item>
        <el-form-item label="付款方式" prop="paymentMethod">
          <el-input v-model="form.paymentMethod" placeholder="请输入付款方式" />
        </el-form-item>
        <el-form-item label="比例统计方式" prop="proportionStatisticalMethod">
          <el-input v-model="form.proportionStatisticalMethod" placeholder="请输入比例统计方式" />
        </el-form-item>
        <el-form-item label="是否入驻第三方" prop="thirdParty">
          <el-input v-model="form.thirdParty" placeholder="请输入是否入驻第三方" />
        </el-form-item>
        <el-form-item label="备注" prop="Remarks">
          <el-input v-model="form.Remarks" placeholder="请输入备注" />
        </el-form-item>
        <el-form-item label="实际比例" prop="actualProportion">
          <el-input v-model="form.actualProportion" placeholder="请输入实际比例" />
        </el-form-item>
        <el-form-item label="差异" prop="difference">
          <el-input v-model="form.difference" placeholder="请输入差异" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listRatio, getRatio, delRatio, addRatio, updateRatio, synchronization, uploadImport } from "@/api/supply/ratio";
import axios from 'axios'

export default {
  name: "Ratio",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 月供货比例统计表格数据
      ratioList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        materialNumber: null,
        materialClass: null,
        supplierCode: null,
        supplierName: null,
        supplyProportion: null,
        paymentMethod: null,
        proportionStatisticalMethod: null,
        thirdParty: null,
        Remarks: null,
        actualProportion: null,
        difference: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        // materialNumber: [
        //   { required: true, message: "物料号不能为空", trigger: "blur" }
        // ],
        supplierCode: [
          { required: true, message: "供应商代码不能为空", trigger: "blur" }
        ],
        supplierName: [
          { required: true, message: "供应商名称不能为空", trigger: "blur" }
        ],
        supplyProportion: [
          { required: true, message: "供货比例不能为空", trigger: "blur" }
        ],
      },

      //新增参数
      showDialog: false,
      progress: 0

    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询月供货比例统计列表 */
    getList() {
      this.loading = true;
      listRatio(this.queryParams).then(response => {
        this.ratioList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        srftId: null,
        materialNumber: null,
        materialClass: null,
        supplierCode: null,
        supplierName: null,
        supplyProportion: null,
        paymentMethod: null,
        proportionStatisticalMethod: null,
        thirdParty: null,
        Remarks: null,
        actualProportion: null,
        difference: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.srftId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加月供货比例统计";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const srftId = row.srftId || this.ids
      getRatio(srftId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改月供货比例统计";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.srftId != null) {
            updateRatio(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addRatio(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const srftIds = row.srftId || this.ids;
      this.$modal.confirm('是否确认删除月供货比例统计编号为"' + srftIds + '"的数据项？').then(function() {
        return delRatio(srftIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('supply/ratio/export', {
        ...this.queryParams
      }, `ratio_${new Date().getTime()}.xlsx`)
    },
    syncReport() {

      synchronization()
      .then(response => {
        this.$message.success("同步成功");
        location.reload();
        // 如果请求成功，可以进行下一步操作
      })
      .catch(error => {
        console.error('There was an error!', error);
      });

      // 页面刷新
      // window.location.reload();
    },

    getDifferenceColor(difference) {
      // 移除百分比符号并转换为数字
      const diffNum = parseFloat(difference.replace('%', '')) / 100;
      // 检查转换是否成功
      if (isNaN(diffNum)) {
        // 如果转换失败，返回默认颜色
        return 'black';
      }
      // 如果绝对值大于3%，返回红色，否则返回默认颜色
      return Math.abs(diffNum) > 0.03 ? 'red' : 'black';
    },

    /*  %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  */
    fileSend() {
      const formData = new FormData();
      const file = document.getElementById("inputFile").files[0]; // 获取文件对象
      // console.log(file);
      formData.append("file", file);
      // console.log("file====>",formData)
      // axios({
      //   method: "post",
      //   // this $axios.post,
      //   url: "http://localhost:8080/supply/ratio/import",
      //   // params:{
      //   //   userName: this.$store.state.user.name,
      //   // },
      //   headers: {
      //     "Content-Type": "multipart/form-data",
      //   },
      //   withCredentials: true,
      //   data: formData,
      //   onUploadProgress: (progressEvent) => {
      //     this.progress = Math.round(
      //       (progressEvent.loaded * 100) / progressEvent.total
      //     );
      //   },
      // });
      // this.$message.success("上传成功");

      uploadImport(formData)
        .then(response => {
          // 文件上传成功
          // console.log('File uploaded successfully:', response);
          
          // 2秒后关闭上传面板并刷新页面
          setTimeout(() => {
            this.showDialog = false; // 关闭上传面板
            location.reload(); // 刷新页面数据
          }, 2000); // 2000毫秒后执行
          this.$message.success("上传成功");
        })
        .catch(error => {
          // 处理错误
          console.error('Error uploading file:', error);
        });

    },

    handleClose(done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => {});
    },
    //导入excel，取消按钮绑定取消所选的xlsx
    resetFileInput() {
      this.$refs.fileInput.value = "";
    },
    //检查文件是否为excel
    checkFile() {
      const file = this.$refs.fileInput.files[0];
      const fileName = file.name;
      const fileExt = fileName.split(".").pop(); // 获取文件的扩展名

      if (fileExt !== "xlsx" && fileExt !== "xlsm") {
        this.$message.error("只能上传 Excel 文件！");
        this.$refs.fileInput.value = ""; // 清空文件选择框
      }
    }


  }
};
</script>

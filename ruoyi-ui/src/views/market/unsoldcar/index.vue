<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车型" prop="vehicleModel">
        <el-input
          v-model="queryParams.vehicleModel"
          placeholder="请输入车型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数量" prop="Number">
        <el-input
          v-model="queryParams.Number"
          placeholder="请输入数量"
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
          v-hasPermi="['market:unsoldcar:add']"
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
          v-hasPermi="['market:unsoldcar:edit']"
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
          v-hasPermi="['market:unsoldcar:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['market:unsoldcar:export']"
        >导出</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
        type="warning"
        plain
        icon="el-icon-download"
        size="mini"
        @click="syncReport"
        v-hasPermi="['market:unsoldcar:syncReport']"
      >同步数据</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="unsoldcarList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <!-- <el-table-column label="主键" align="center" prop="ucId" /> -->
      <!-- <el-table-column label="车型" align="center" prop="vehicleModel" /> -->
      <el-table-column label="车型" align="center">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            style="color: #409EFF; cursor: pointer;" 
            @click="showDialog(scope.row)"
          >{{ scope.row.vehicleModel }}</el-button>
        </template>
      </el-table-column>
      <el-table-column label="数量" align="center" prop="number" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['market:unsoldcar:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['market:unsoldcar:remove']"
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


    <el-dialog
      title="车型详情"
      :visible.sync="dialogVisible"
      width="75%"
    >
      <p>车型：{{ selectedRow.vehicleModel }}</p>
      <el-table v-loading="loading" :data="marketInventoryCarDetail" >

        <el-table-column label="车号" align="center" prop="wagonNumber" />
        <el-table-column label="车型" align="center" prop="vehicleModel" />
        <el-table-column label="门架" align="center" prop="doorFrame" />
        <el-table-column label="属具" align="center" prop="accessory" />
        <el-table-column label="阀片数" align="center" prop="valveBlockNumber" />
        <el-table-column label="配置" align="center" prop="configuration" />
        <el-table-column label="计划完工期" align="center" prop="planndeCompletionTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.planndeCompletionTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">关闭</el-button>
      </span>
    </el-dialog>

    <!-- 添加或修改统计库存车数量对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车型" prop="vehicleModel">
          <el-input v-model="form.vehicleModel" placeholder="请输入车型" />
        </el-form-item>
        <el-form-item label="数量" prop="Number">
          <el-input v-model="form.Number" placeholder="请输入数量" />
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
import { listUnsoldcar, getUnsoldcar, delUnsoldcar, addUnsoldcar, updateUnsoldcar, synchronization, vehicleTypequery } from "@/api/market/unsoldcar";

export default {
  name: "Unsoldcar",
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
      marketInventoryCarDetailtotal: 0,
      // 统计库存车数量表格数据
      unsoldcarList: [],
      marketInventoryCarDetail: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        vehicleModel: null,
        Number: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        vehicleModel: [
          { required: true, message: "车型不能为空", trigger: "blur" }
        ],
      },
      pageIndex:1,
      pagesize: 10,
      totalPage:0,

      dialogVisible: false, // 控制对话框的显示与隐藏
      selectedRow: {}, // 存储被点击的行数据
    };
  },
  created() {
    this.getList();
  },
  methods: {

    showDialog(row) {
      this.selectedRow = row; // 将被点击的行数据赋值给selectedRow
      this.dialogVisible = true; // 显示对话框
      
      this.marketInventoryCarDetail = [];
      vehicleTypequery(row.vehicleModel).then(response => {

        response.rows.forEach(item => {
      // 创建一个新的对象，只包含需要的字段
          const carDetail = {
            micdId: item.micdId,
            wagonNumber: item.wagonNumber,
            vehicleModel: item.vehicleModel,
            doorFrame: item.doorFrame,
            valveBlockNumber: item.valveBlockNumber,
            planndeCompletionTime: item.planndeCompletionTime,
            configuration: item.configuration,
            accessory: item.accessory
          };
          // 将新的对象添加到 this.marketInventoryCarDetail 中
          this.marketInventoryCarDetail.push(carDetail);
        });
        console.log(this.marketInventoryCarDetail);

      }).catch(error => {
      console.error("Error calling vehicleTypequery:", error);
      // 这里可以添加错误处理逻辑，比如显示错误消息给用户
    });
    },
    /** 查询统计库存车数量列表 */
    getList() {
      this.unsoldcarList = [];
      this.loading = true;
      listUnsoldcar(this.queryParams).then(response => {
        this.unsoldcarList = response.rows;
        this.total = response.total;
      });  
      this.loading = false;
      //数据分页
      this.unsoldcarList = this.unsoldcarList.slice(
      (this.pageIndex - 1) * this.pageSize,
      this.pageIndex * this.pageSize
      );
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        ucId: null,
        vehicleModel: null,
        Number: null
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
      this.ids = selection.map(item => item.ucId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加统计库存车数量";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const ucId = row.ucId || this.ids
      getUnsoldcar(ucId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改统计库存车数量";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.ucId != null) {
            updateUnsoldcar(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addUnsoldcar(this.form).then(response => {
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
      const ucIds = row.ucId || this.ids;
      this.$modal.confirm('是否确认删除统计库存车数量编号为"' + ucIds + '"的数据项？').then(function() {
        return delUnsoldcar(ucIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('market/unsoldcar/export', {
        ...this.queryParams
      }, `unsoldcar_${new Date().getTime()}.xlsx`)
    },
    syncReport() {

      synchronization()
      .then(response => {
        // if (!response.ok) {
        //   throw new Error('Network response was not ok');
        // }
        this.$message.success("同步成功");
        location.reload();
        // 如果请求成功，可以进行下一步操作
      })
      .catch(error => {
        console.error('There was an error!', error);
      });
      // 使用 Fetch API 发送 POST 请求到后端
      // fetch('http://localhost:8080/market/unsoldcar/synchronization', {
      //   method: 'POST',
      //   headers: {
      //     'Content-Type': 'application/json'
      //   }
      // })
      // .then(response => {
      //   if (!response.ok) {
      //     throw new Error('Network response was not ok');
      //   }
      //   // 如果请求成功，可以进行下一步操作
      // })
      // .catch(error => {
      //   console.error('There was an error!', error);
      // });



    }
  }
};
</script>

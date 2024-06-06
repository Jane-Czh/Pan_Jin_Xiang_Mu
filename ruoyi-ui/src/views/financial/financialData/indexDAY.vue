<template>
  <div class="app-container">
    <el-form :model="queryParams2" ref="queryForm2" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <!-- <el-form-item label="当日在制品金额" prop="inProgressDayRevenue">
        <el-input v-model="queryParams2.inProgressDayRevenue" placeholder="请输入当日在制品金额" clearable
          @keyup.enter.native="handleQuery2" />
      </el-form-item> -->

      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery2">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery2">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd2"
          v-hasPermi="['financial:data:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate2"
          v-hasPermi="['financial:data:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete2"
          v-hasPermi="['financial:data:remove']">删除</el-button>
      </el-col>
      <!-- <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['financial:data:export']">导出</el-button>
      </el-col> -->
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList2"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dataList2" @selection-change="handleSelectionChange2">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="当日在制品金额" align="center" prop="inProgressDayRevenue" />
      <el-table-column label="日期-年月" align="center" prop="dataTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.dataTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate2(scope.row)"
            v-hasPermi="['financial:data:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete2(scope.row)"
            v-hasPermi="['financial:data:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams2.pageNum" :limit.sync="queryParams2.pageSize"
      @pagination="getList2" />

    <!-- 添加或修改[财务]每日填报指标[当日再制品金额]对话框 -->
    <el-dialog :title="title" :visible.sync="open2" width="500px" append-to-body>
      <el-form ref="form2" :model="form2" :rules="rules2" label-width="120px">
        <el-form-item label="当日在制品金额" prop="inProgressDayRevenue">
          <el-input v-model="form2.inProgressDayRevenue" placeholder="请输入当日在制品金额" />
        </el-form-item>
        <el-form-item label="日期-年月" prop="dataTime">
          <el-date-picker clearable v-model="form2.dataTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择日期-年月">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm2">确 定</el-button>
        <el-button @click="cancel2">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listData2, getData2, delData2, addData2, updateData2 } from "@/api/financial/dayData";

export default {
  name: "Data",
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
      // [财务]每日填报指标[当日再制品金额]表格数据
      dataList2: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open2: false,
      // 查询参数
      queryParams2: {
        pageNum: 1,
        pageSize: 10,
        inProgressDayRevenue: null,
        dataTime: null,
      },
      // 表单参数
      form2: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList2();
  },
  methods: {
    /** 查询[财务]每日填报指标[当日再制品金额]列表 */
    getList2() {
      this.loading = true;
      listData2(this.queryParams2).then(response => {
        this.dataList2 = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel2() {
      this.open2 = false;
      this.reset2();
    },
    // 表单重置
    reset2() {
      this.form2 = {
        id: null,
        inProgressDayRevenue: null,
        dataTime: null,
        createBy: null,
        createTime: null,
        updateBy: null,
        updateTime: null
      };
      this.resetForm("form2");
    },
    /** 搜索按钮操作 */
    handleQuery2() {
      this.queryParams2.pageNum = 1;
      this.getList2();
    },
    /** 重置按钮操作 */
    resetQuery2() {
      this.resetForm("queryForm2");
      this.handleQuery2();
    },
    // 多选框选中数据
    handleSelectionChange2(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd2() {
      this.reset2();
      this.open2 = true;
      this.title = "添加[财务]每日填报指标[当日再制品金额]";
    },
    /** 修改按钮操作 */
    handleUpdate2(row) {
      this.reset2();
      const id = row.id || this.ids
      getData2(id).then(response => {
        this.form2 = response.data;
        this.open2 = true;
        this.title = "修改[财务]每日填报指标[当日再制品金额]";
      });
    },

    /** 提交按钮 */
    submitForm2() {
      this.$refs["form2"].validate(valid => {
        if (valid) {
          if (this.form2.id != null) {
            updateData2(this.form2).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open2 = false;
              this.getList2();
            });
          } else {
            addData2(this.form2).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open2 = false;
              this.getList2();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete2(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除[财务]每日填报指标[当日再制品金额]编号为"' + ids + '"的数据项？').then(function () {
        return delData2(ids);
      }).then(() => {
        this.getList2();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    // TODO修改删除提示字（编号→年月）
    // /** 导出按钮操作 */
    // handleExport() {
    //   this.download('financial/data/export', {
    //     ...this.queryParams2
    //   }, `data_${new Date().getTime()}.xlsx`)
    // }
  }
};
</script>
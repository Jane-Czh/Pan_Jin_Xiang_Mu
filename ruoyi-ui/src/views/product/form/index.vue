<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
<!--      <el-form-item label="序号" prop="Number">-->
<!--        <el-input-->
<!--          v-model="queryParams.Number"-->
<!--          placeholder="请输入序号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="员工编号" prop="idNumber">-->
<!--        <el-input-->
<!--          v-model="queryParams.idNumber"-->
<!--          placeholder="请输入员工编号"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="姓名" prop="Name">-->
<!--        <el-input-->
<!--          v-model="queryParams.Name"-->
<!--          placeholder="请输入姓名"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="性别" prop="Gender">-->
<!--        <el-input-->
<!--          v-model="queryParams.Gender"-->
<!--          placeholder="请输入性别"-->
<!--          clearable-->
<!--          @keyup.enter.native="handleQuery"-->
<!--        />-->
<!--      </el-form-item>-->
<!--      <el-form-item label="第一次上班打卡时间" prop="firstTimeClockingInAtWork">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.firstTimeClockingInAtWork"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择第一次上班打卡时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="第一次下班打卡时间" prop="firstTimeClockingInAfterWork">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.firstTimeClockingInAfterWork"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择第一次下班打卡时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="第二次上班打卡时间" prop="secondTimeClockingInAtWork">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.secondTimeClockingInAtWork"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择第二次上班打卡时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="第二次下班打卡时间" prop="secondTimeClockingInAfterWork">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.secondTimeClockingInAfterWork"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择第二次下班打卡时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="正常上班时间" prop="normalWorkingHours">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.normalWorkingHours"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择正常上班时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
<!--      <el-form-item label="正常下班时间" prop="normalClosingTime">-->
<!--        <el-date-picker clearable-->
<!--          v-model="queryParams.normalClosingTime"-->
<!--          type="date"-->
<!--          value-format="yyyy-MM-dd"-->
<!--          placeholder="请选择正常下班时间">-->
<!--        </el-date-picker>-->
<!--      </el-form-item>-->
      <el-form-item>
<!--        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>-->
<!--        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>-->
        <el-button type="primary" @click="showSetWorkTimeDialog">设置上下班时间</el-button>
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
          v-hasPermi="['product:form:add']"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          @click="handleUpdate"-->
<!--          v-hasPermi="['product:form:edit']"-->
<!--        >修改正常上下班时刻</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['product:form:remove']"
        >删除</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="warning"-->
<!--          plain-->
<!--          icon="el-icon-download"-->
<!--          size="mini"-->
<!--          @click="handleExport"-->
<!--          v-hasPermi="['product:form:export']"-->
<!--        >导出</el-button>-->
<!--      </el-col>-->
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-download"
          size="mini"
          @click="showDialog = true"
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

<!--          &lt;!&ndash; 进度动画条 &ndash;&gt;-->
<!--          <div v-if="progress > 0">-->
<!--            <el-progress-->
<!--              :percentage="progress"-->
<!--              color="rgb(19, 194, 194)"-->
<!--            ></el-progress>-->
<!--          </div>-->

          <span slot="footer" class="dialog-footer">
          <el-button @click="showDialog = false">取 消</el-button>
          <el-button type="primary" @click="fileSend()">确 定</el-button>
        </span>
        </el-dialog>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="formList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
<!--      <el-table-column label="主键" align="center" prop="pcifId" />-->
<!--      <el-table-column label="序号" align="center" prop="number" />-->
      <el-table-column label="员工编号" align="center" prop="idNumber" />
      <el-table-column label="姓名" align="center" prop="name" />
      <el-table-column label="性别" align="center" prop="gender" />
      <el-table-column label="第一次上班打卡时间" align="center" prop="firstTimeClockingInAtWork" width="180">
<!--        <template slot-scope="scope">-->
<!--          <span>{{parseTime(scope.row.normalClosingTime, 'HH:mm:ss') }}</span>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="第一次下班打卡时间" align="center" prop="firstTimeClockingInAfterWork" width="180">
<!--        <template slot-scope="scope">-->
<!--          <span>{{  formatTime(scope.row.normalClosingTime, 'HH:mm:ss') }}</span>-->
<!--        </template>-->
      </el-table-column>
      <el-table-column label="第二次上班打卡时间" align="center" prop="secondTimeClockingInAtWork" width="180">

      </el-table-column>
      <el-table-column label="第二次下班打卡时间" align="center" prop="secondTimeClockingInAfterWork" width="180">

      </el-table-column>
      <el-table-column label="正常上班时间" align="center" prop="normalWorkingHours" width="180">

      </el-table-column>
      <el-table-column label="正常下班时间" align="center" prop="normalClosingTime" width="180">

      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            icon="el-icon-edit"
            size="mini"
            type="text"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['product:form:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['product:form:remove']"
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
    <el-dialog title="设置上下班时间" :visible.sync="showWorkTimeDialog">
      <el-form :model="workTimeForm" label-width="120px">
        <el-form-item label="正常上班时间">
          <el-time-picker v-model="workTimeForm.startTime" placeholder="选择正常上班时间"></el-time-picker>
        </el-form-item>
        <el-form-item label="正常下班时间">
          <el-time-picker v-model="workTimeForm.endTime" placeholder="选择正常下班时间"></el-time-picker>
        </el-form-item>
      </el-form>
      <el-button type="primary" @click="submitWorkTime">确认</el-button>
      <el-button @click="closeWorkTimeDialog">取消</el-button>
    </el-dialog>

    <!-- 添加或
    员工打卡表对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
<!--        <el-form-item label="序号" prop="number">-->
<!--          <el-input v-model="form.number" placeholder="请输入序号" />-->
<!--        </el-form-item>-->
        <el-form-item label="员工编号" prop="idNumber">
          <el-input v-model="form.idNumber" placeholder="请输入员工编号" />
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-input v-model="form.gender" placeholder="请输入性别" />
        </el-form-item>
        <el-form-item label="第一次上班打卡时间" prop="firstTimeClockingInAtWork">
          <el-date-picker clearable
            v-model="form.firstTimeClockingInAtWork"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择第一次上班打卡时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第一次下班打卡时间" prop="firstTimeClockingInAfterWork">
          <el-date-picker clearable
            v-model="form.firstTimeClockingInAfterWork"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择第一次下班打卡时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第二次上班打卡时间" prop="secondTimeClockingInAtWork">
          <el-date-picker clearable
            v-model="form.secondTimeClockingInAtWork"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择第二次上班打卡时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="第二次下班打卡时间" prop="secondTimeClockingInAfterWork">
          <el-date-picker clearable
            v-model="form.secondTimeClockingInAfterWork"
                          type="datetime"
                          value-format="yyyy-MM-dd HH:mm:ss"
            placeholder="请选择第二次下班打卡时间">
          </el-date-picker>
        </el-form-item>
<!--        <el-form-item label="正常上班时间" prop="normalWorkingHours">-->
<!--          <el-time-picker clearable-->
<!--            v-model="form.normalWorkingHours"-->
<!--            value-format="HH:mm:ss"-->
<!--            placeholder="请选择正常上班时间">-->
<!--          </el-time-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="正常下班时间" prop="normalClosingTime">-->
<!--          <el-time-picker clearable-->
<!--                          v-model="form.normalClosingTime"-->
<!--                          value-format="HH:mm:ss"-->
<!--                          placeholder="请选择正常下班时间">-->
<!--          </el-time-picker>-->
<!--        </el-form-item>-->
<!--        <el-form-item label="正常下班时间" prop="normalClosingTime">-->
<!--          <el-time-picker-->
<!--            clearable-->
<!--            v-model="form.normalClosingTime"-->
<!--            format="HH:mm:ss"-->
<!--            placeholder="请选择正常下班时间"-->
<!--          >-->
<!--          </el-time-picker>-->
<!--        </el-form-item>-->
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listForm, getForm, delForm, addForm, updateForm,  updateWorkTime,uploadFile} from "@/api/product/form";
import axios from "axios";
import dayjs from 'dayjs';
import {getrelTime, getTime} from "@/api/product/time";


export default {

  name: "Form",
  data() {
    const validateIdNumber = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入员工编号'));
      } else {
        const regex = /^\d+$/;
        if (!regex.test(value)) {
          callback(new Error('员工编号只能包含数字'));
        } else {
          callback();
        }
      }
    };
    const validateName = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入姓名'));
      } else {
        const regex = /^[\u4e00-\u9fa5a-zA-Z]+$/;
        if (!regex.test(value)) {
          callback(new Error('姓名只能包含中文字符或英文字符'));
        } else {
          callback();
        }
      }
    };
    const validateGender = (rule, value, callback) => {
      if (!value) {
        callback(new Error('请输入性别'));
      } else {
        const regex = /^(男|女)$/;
        if (!regex.test(value)) {
          callback(new Error('性别只能为男或女'));
        } else {
          callback();
        }
      }
    };

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
      // 员工打卡表表格数据
      formList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        number: null,
        idNumber: null,
        name: null,
        gender: null,
        firstTimeClockingInAtWork: null,
        firstTimeClockingInAfterWork: null,
        secondTimeClockingInAtWork: null,
        secondTimeClockingInAfterWork: null,
        normalWorkingHours: null,
        normalClosingTime: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        idNumber: [
          { required: true, message: '请输入员工编号', trigger: 'blur' },
          { validator: validateIdNumber, trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { validator: validateName, trigger: 'blur' }
        ],
        gender: [
          { required: true, message: '请输入性别', trigger: 'blur' },
          { validator: validateGender, trigger: 'blur' }
        ],
        firstTimeClockingInAtWork: [
          { required: true, message: '请输入首次上班打卡时间', trigger: 'blur' }
        ],
        firstTimeClockingInAfterWork: [
          { required: true, message: '请输入首次下班打卡时间', trigger: 'blur' }
        ]
      },
      //新增参数
      showDialog: false,
      progress: 0,
      showWorkTimeDialog:false,
      workrelTimeForm: {
        startTime: '', // 用户设置的正常上班时间
        endTime: ''     // 用户设置的正常下班时间
      },
      workingHours: '',
      workTimeForm: {
        startTime: '', // 用户设置的正常上班时间
        endTime: ''     // 用户设置的正常下班时间
      }
    };
  },
  created() {
    this.getList();
    this.fetchtime();
  },
  methods: {

      showSetWorkTimeDialog() {
        this.showWorkTimeDialog = true; // 展示设置工作时间的对话框
      },

    // 格式化时间的方法
    formatTime(time) {
      console.log(time);
      return dayjs(time).format('HH:mm:ss');
    },

// 修改后的 submitWorkTime 方法
    async submitWorkTime() {
      try {
        // 将 workTimeForm 中的数据传递给后端进行保存
        await updateWorkTime({
          startTime: this.workTimeForm.startTime,
          endTime: this.workTimeForm.endTime,
        });

        this.$message.success('成功设置上下班时间');
        this.showWorkTimeDialog = false; // 关闭设置工作时间的对话框
        console.log('设置的正常上班时间为：', this.workTimeForm.startTime);
        this.getList();
      } catch (error) {
        this.$message.error('设置上下班时间失败');
      }
    },

// 关闭设置工作时间的对话框
    closeWorkTimeDialog() {
      this.showWorkTimeDialog = false; // 关闭设置工作时间的对话框
    },
//獲取上下班時間
    fetchtime(){
      getrelTime().then(response => {
        this.workrelTimeForm.startTime = response.data.workingHours;
        this.workrelTimeForm.endTime = response.data.offHours;
      });
    },

    /** 查询员工打卡表列表 */
    getList() {
      this.loading = true;
      listForm(this.queryParams).then(response => {
        this.formList = response.rows;
        this.total = response.total;
        this.loading = false;
        console.log(this.formList)
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
        pcifId: null,
        Number: null,
        idNumber: null,
        Name: null,
        Gender: null,
        firstTimeClockingInAtWork: null,
        firstTimeClockingInAfterWork: null,
        secondTimeClockingInAtWork: null,
        secondTimeClockingInAfterWork: null,
        normalWorkingHours: null,
        normalClosingTime: null
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
      this.ids = selection.map(item => item.pcifId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加员工打卡表";

    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const pcifId = row.pcifId || this.ids
      getForm(pcifId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改员工打卡表";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.pcifId != null) {
            updateForm(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            // 新增操作
            const firstonTime = dayjs(this.form.firstTimeClockingInAtWork);
            const firstoffTime = dayjs(this.form.firstTimeClockingInAfterWork);

            // 提取日期部分
            const dateParton = firstonTime.isValid() ? firstonTime.format('YYYY-MM-DD') : 'Invalid Date';
            const datePartoff = firstoffTime.isValid() ? firstoffTime.format('YYYY-MM-DD') : 'Invalid Date';
            // 检查 startTime 的格式
            const startTime = dayjs(this.workrelTimeForm.startTime, 'HH:mm:ss');
            const endTime = dayjs(this.workrelTimeForm.endTime, 'HH:mm:ss');
            const timeParton = startTime.isValid() ? startTime.format('HH:mm:ss') : 'Invalid Date';
            const timePartoff = endTime.isValid() ? endTime.format('HH:mm:ss') : 'Invalid Date';
            // 拼接日期和时间
            this.form.normalWorkingHours = `${dateParton} ${timeParton}`;
            this.form.normalClosingTime = `${datePartoff} ${timePartoff}`;
            // 调试输出
            console.log("更新文件提交按钮1=>", this.form.normalWorkingHours);
            console.log("更新文件提交按钮2=>", this.form.normalClosingTime);
            addForm(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
        else {
          console.log('表单验证失败!');
          return false;
        }
      });
    },

    /** 删除按钮操作 */
    handleDelete(row) {
      const pcifIds = row.pcifId || this.ids;
      this.$modal.confirm('是否确认删除员工打卡表编号为"' + pcifIds + '"的数据项？').then(function() {
        return delForm(pcifIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('product/form/export', {
        ...this.queryParams
      }, `form_${new Date().getTime()}.xlsx`)
    },



    // fileSend() {
    //   const formData = new FormData();
    //   const file = document.getElementById("inputFile").files[0]; // 获取文件对象
    //   console.log(file);
    //   formData.append("file", file);
    //   console.log("file====>",formData)
    //   axios({
    //     method: "post",
    //     // this $axios.post,
    //     url: "http://localhost:8080/product/form/FOimport",
    //     // params:{
    //     //   userName: this.$store.state.user.name,
    //     // },
    //     headers: {
    //       "Content-Type": "multipart/form-data",
    //     },
    //     withCredentials: true,
    //     data: formData,
    //     onUploadProgress: (progressEvent) => {
    //       this.progress = Math.round(
    //         (progressEvent.loaded * 100) / progressEvent.total
    //       );
    //     },
    //   });
    //   // this.$message.success("上传成功");
    //
    //
    //   setTimeout(() => {
    //     this.showDialog = false; // 关闭上传面板
    //
    //     // location.reload(); // 调用此方法刷新页面数据
    //   }, 2000); // 2000毫秒后关闭
    // },
    async fileSend() {
      try {
        const fileInput = document.getElementById("inputFile");
        if (!fileInput.files.length) {
          this.$message.error("请选择上传文件");
          return;
        }
        const formData = new FormData();
        const file = document.getElementById("inputFile").files[0]; // 获取文件对象
        console.log(file);
        formData.append("file", file);
        console.log("file====>", formData);

        await uploadFile(formData); // 调用 uploadFile API

        // 上传成功后的处理
        this.$message.success("上传成功"); // 提示上传成功
        setTimeout(() => {
          this.showDialog = false; // 关闭上传面板
          // location.reload(); // 调用此方法刷新页面数据
        }, 1000); // 2000毫秒后关闭
        this.getList();
      } catch (error) {
        console.error('上传失败!', error);
      }
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

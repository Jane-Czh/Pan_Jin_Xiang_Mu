<template>
  <div class="current-page">
    <el-tabs v-model="activeName" type="border-card">
      <el-tab-pane label="销售指标类" name="first">
        <div class="SalesIndex">
          <el-dialog :visible.sync="dialogVisible" title=" 订单总台数">
  <!-- <el-form ref="form" :model="formData" label-width="100px">
    <!-- <el-form-item label="主要车型">
      <el-input v-model="formData.vehicleModel"></el-input>
    </el-form-item> -->
    <!-- <el-form-item label="接单日期">
      <el-date-picker v-model="formData.orderAcceptanceTime" type="date"></el-date-picker>
    </el-form-item> -->
    <!-- <el-form-item label="CPD车型总台数"> -->
      <!-- <el-input v-model="formData.cpdTotal"></el-input>  数字校验-->
       <!-- <el-form-item label="订单总台数">
       <el-input v-model.number="formData.cpdTotal" type="number"></el-input>
    </el-form-item>
  </el-form> --> -->
  <div slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">取消</el-button>
    <el-button type="primary" @click="submitForm">确定</el-button>
  </div>
</el-dialog>

          <div v-for="(item, index) in SalesIndex" :key="index" class="SalesIndex-item">
            <div class="SalesIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="SalesIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="经营指标类" name="second">
        <div class="OperatingEfficiencyIndex">
          <div v-for="(item, index) in OperatingEfficiencyIndex" :key="index" class="OperatingEfficiencyIndex-item">
            <div class="OperatingEfficiencyIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="OperatingEfficiencyIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
      <el-tab-pane label="全部" name="third">
        <div class="allIndex">
          <div v-for="(item, index) in allIndex" :key="index" class="allIndex-item">
            <div class="allIndex-item__body">
              <i :class="item.icon" />
              <div class="main-content">
                <h3>{{ item.title }}</h3>
                <div>{{ item.content }}</div>
              </div>
            </div>
            <div class="allIndex-item__footer">
              <el-button type="text" style="font-size: 18px;" @click="toDetail(item, index)">指标数据图</el-button>
            </div>
          </div>
        </div>
      </el-tab-pane>
    </el-tabs>


  </div>

</template>

<script>
import { getIndex11 } from '@/api/market/index'
import axios from "axios";
export default {
  components: {},
  props: [],
  data() {
    return {
      item:null,
      index:null,
        dialogVisible: false, // 控制对话框的显示与隐藏
   
    formData: { // 表单数据模型
      vehicleModel: '', // 主要车型
      orderAcceptanceTime: '', // 接单日期

      cpdTotal: '' 
      
      },// CPD车型总台数
           rules: {
      cpdTotal: [
        { type: 'number', message: 'CPD车型总台数必须是数字', trigger: 'blur' }
      ]
    },
      activeName: 'first',
      allIndex: [
        { id: '11', icon: 'el-icon-s-data', title: '各销售网点月度(I类)电车盘锦区域内订单占比 ', content: '各销售网点月度(I类)电车盘锦区域内订单占比', path: '/market/index11' },
        { id: '12', icon: 'el-icon-s-data', title: '各销售网点月度(IV-V类)内燃车盘锦区域内订单占比', content: '各销售网点月度(IV-V类)内燃车盘锦区域内订单占比', path: '/market/index12' },
        { id: '13', icon: 'el-icon-s-data', title: '各主要车型产量月度比率', content: '各主要车型产量月度比率', path: '/market/index13' },
        { id: '14', icon: 'el-icon-s-data', title: '各主要车型同比增长比例', content: '各主要车型同比增长比例', path: '/market/index14' },
        { id: '15', icon: 'el-icon-s-data', title: '各销售网点月度各主要车型分布表', content: '各销售网点月度各主要车型分布表', path: '/market/index15' },
        { id: '16', icon: 'el-icon-s-data', title: '各网点已接单未发车总台数', content: '各网点已接单未发车总台数', path: '/market/index16' },
        { id: '17', icon: 'el-icon-s-data', title: '各网点未排产订单台数', content: '各网点未排产订单台数', path: '/market/index17' },
        { id: '18', icon: 'el-icon-s-data', title: '各网点已完工未发车订单数', content: '各网点已完工未发车订单数', path: '/market/index18' },
        { id: '19', icon: 'el-icon-s-data', title: '各网点已到期未完工订单数', content: '各网点已到期未完工订单数', path: '/market/index19' },
        { id: '39', icon: 'el-icon-s-data', title: '商品车计划兑现率', content: '商品车计划兑现率', path: '/market/index39' },
        { id: '40', icon: 'el-icon-s-data', title: '订单兑现率', content: '订单兑现率', path: '/market/index40' },
        { id: '42', icon: 'el-icon-s-data', title: '大吨位叉车整机交货天数平均数', content: '大吨位叉车整机交货天数平均数', path: '/market/index42' },
        { id: '43', icon: 'el-icon-s-data', title: '电动车整机交货天数', content: '电动车整机交货天数', path: '/market/index43' },
        { id: '44', icon: 'el-icon-s-data', title: '小吨位内燃叉车整机交货天数', content: '小吨位内燃叉车整机交货天数', path: '/market/index44' },
        { id: '45', icon: 'el-icon-s-data', title: '电动车整机制造天数', content: '电动车整机制造天数', path: '/market/index45' },
        { id: '46', icon: 'el-icon-s-data', title: '小吨位内燃叉车整机制造天数', content: '小吨位内燃叉车整机制造天数', path: '/market/index46' },
         { id: '47', icon: 'el-icon-s-data', title: '大吨位叉车整机制造天数', content: '大吨位叉车整机制造天数', path: '/market/index47' },
                        { id: '48', icon: 'el-icon-s-data', title: '越野车整机制造天数', content: '越野车整机制造天数', path: '/market/index48' },

        { id: '72', icon: 'el-icon-s-data', title: '日/月/年接单数', content: '日/月/年接单数', path: '/market/index72' },
        { id: '73', icon: 'el-icon-s-data', title: '日/月/年交货数', content: '日/月/年交货数', path: '/market/index73' },
      ],
      SalesIndex: [
        { id: '11', icon: 'el-icon-s-data', title: '各销售网点月度(I类)电车盘锦区域内订单占比 ', content: '各销售网点月度(I类)电车盘锦区域内订单占比', path: '/market/index11' },
        { id: '12', icon: 'el-icon-s-data', title: '各销售网点月度(IV-V类)内燃车盘锦区域内订单占比', content: '各销售网点月度(IV-V类)内燃车盘锦区域内订单占比', path: '/market/index12' },
        { id: '13', icon: 'el-icon-s-data', title: '各主要车型产量月度比率', content: '各主要车型产量月度比率', path: '/market/index13' },
        { id: '14', icon: 'el-icon-s-data', title: '各主要车型同比增长比例', content: '各主要车型同比增长比例', path: '/market/index14' },
        { id: '15', icon: 'el-icon-s-data', title: '各销售网点月度各主要车型分布表', content: '各销售网点月度各主要车型分布表', path: '/market/index15' },
        { id: '16', icon: 'el-icon-s-data', title: '各网点已接单未发车总台数', content: '各网点已接单未发车总台数', path: '/market/index16' },
        { id: '17', icon: 'el-icon-s-data', title: '各网点未排产订单台数', content: '各网点未排产订单台数', path: '/market/index17' },
        { id: '18', icon: 'el-icon-s-data', title: '各网点已完工未发车订单数', content: '各网点已完工未发车订单数', path: '/market/index18' },

        { id: '19', icon: 'el-icon-s-data', title: '各网点已到期未完工订单数', content: '各网点已到期未完工订单数', path: '/market/index19' },
      ],
      OperatingEfficiencyIndex: [
        { id: '39', icon: 'el-icon-s-data', title: '商品车计划兑现率', content: '商品车计划兑现率', path: '/market/index39' },
        { id: '40', icon: 'el-icon-s-data', title: '订单兑现率', content: '订单兑现率', path: '/market/index40' },
        { id: '42', icon: 'el-icon-s-data', title: '大吨位叉车整机交货天数平均数', content: '大吨位叉车整机交货天数平均数', path: '/market/index42' },
        { id: '43', icon: 'el-icon-s-data', title: '电动车整机交货天数', content: '电动车整机交货天数', path: '/market/index43' },
        { id: '45', icon: 'el-icon-s-data', title: '电动车整机制造天数', content: '电动车整机制造天数', path: '/market/index45' },
        { id: '46', icon: 'el-icon-s-data', title: '小吨位内燃叉车整机制造天数', content: '小吨位内燃叉车整机制造天数', path: '/market/index46' },
        { id: '47', icon: 'el-icon-s-data', title: '大吨位叉车整机制造天数', content: '大吨位叉车整机制造天数', path: '/market/index47' },
                { id: '48', icon: 'el-icon-s-data', title: '越野车整机制造天数', content: '越野车整机制造天数', path: '/market/index48' },

       { id: '44', icon: 'el-icon-s-data', title: '小吨位内燃叉车整机交货天数', content: '小吨位内燃叉车整机交货天数', path: '/market/index44' },
        { id: '72', icon: 'el-icon-s-data', title: '日/月/年接单数', content: '日/月/年接单数', path: '/market/index72' },
        { id: '73', icon: 'el-icon-s-data', title: '日/月/年交货数', content: '日/月/年交货数', path: '/market/index73' },
      ],


      // formData: {},
      rules: {},
    }
  },
  computed: {},
  watch: {},
  created() {
  },
  mounted() { },
  methods: {
    
    
    toDetail(item, index) {
      //  this.dialogVisible = true
       this.item=item
       this.index=index
       this.$router.push(item.path);
    },
 submitForm() {
  // 创建 FormData 对象
  const formData = new FormData();

  // 将表单数据添加到 FormData 对象中
  formData.append("vehicleModel", this.formData.vehicleModel); // 添加车型
  formData.append("orderAcceptanceTime", this.formData.orderAcceptanceTime); // 添加接单日期
  formData.append("cpdTotal", this.formData.cpdTotal); // 添加CPD车型总台数

  // 发送 POST 请求到后端
  axios({
    method: "post",
    url: "http://localhost:8080/market/Index/IClassProportion",
    headers: {
      "Content-Type": "multipart/form-data",
    },
    data: formData,
    onUploadProgress: (progressEvent) => {
      this.progress = Math.round(
        (progressEvent.loaded * 100) / progressEvent.total
      );
    },
  })
    .then((response) => {
      // 处理响应数据
      console.log("Response:", response);
      // 关闭对话框
      this.dialogVisible = false;
      // 跳转页面
      this.$router.push(this.item.path);
    })
    .catch((error) => {
      // 处理错误
      console.error("Error:", error);
    });
}

  }
}

</script>
<style lang="scss" scoped>
.current-page {
  padding: 16px;
}


.SalesIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .SalesIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .SalesIndex-item__body,
  .SalesIndex-item__footer {
    padding: 16px;
  }

  .SalesIndex-item__body {
    display: flex;
  }

  .SalesIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .SalesIndex-item__body i {
    font-size: 30px;
  }

  .SalesIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .SalesIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }
}

.OperatingEfficiencyIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .OperatingEfficiencyIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .OperatingEfficiencyIndex-item__body,
  .OperatingEfficiencyIndex-item__footer {
    padding: 16px;
  }

  .OperatingEfficiencyIndex-item__body {
    display: flex;
  }

  .OperatingEfficiencyIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .OperatingEfficiencyIndex-item__body i {
    font-size: 30px;
  }

  .OperatingEfficiencyIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .OperatingEfficiencyIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;

  }
}

.allIndex {
  display: grid;
  grid-template-columns: repeat(3, 1fr);
  gap: 16px;

  .allIndex-item {
    /* width: calc(100% / 3); */
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, .1);
  }

  .allIndex-item__body,
  .allIndex-item__footer {
    padding: 16px;
  }

  .allIndex-item__body {
    display: flex;
  }

  .allIndex-item__body h3 {
    margin-top: 0;
    font-weight: bold;
  }

  .allIndex-item__body i {
    font-size: 30px;
  }

  .allIndex-item__body i+.main-content {
    margin-left: 10px;
  }

  .allIndex-item__footer {
    border-top: 1px solid #eee;
    text-align: right;
  }
}
</style>

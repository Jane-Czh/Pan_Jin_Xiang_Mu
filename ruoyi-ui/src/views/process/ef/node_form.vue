<template>
  <div>
    <div class="ef-node-form">
      <div class="ef-node-form-header">编辑</div>
      <div class="ef-node-form-body">
        <el-form
          :model="node"
          ref="dataForm"
          label-width="80px"
          v-show="type === 'node'"
        >
          <!-- <el-form-item label="类型">
                        <el-input v-model="node.type" :disabled="true"></el-input>
                    </el-form-item> -->
          <el-form-item label="名称">
            <el-input v-model="node.name"></el-input>
          </el-form-item>
          <!-- <el-form-item label="left坐标">
            <el-input v-model="node.left" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="top坐标">
            <el-input v-model="node.top" :disabled="true"></el-input>
          </el-form-item> -->

          <!-- <el-form-item label="部门">
            <el-input v-model="node.department"></el-input>
          </el-form-item> -->
         

          <el-form-item label="执行人员">
            <el-input v-model="node.operationalStaff"></el-input>
          </el-form-item>

          <el-form-item label="描述">
            <el-input v-model="node.description"></el-input>
          </el-form-item>

          <el-form-item label="部门">
            <el-select
              v-model="node.department"
              placeholder="请选择或填入"
              allow-create
              filterable
            >
              <el-option
                v-for="item in departments"
                :key="item"
                :label="item"
                :value="item"
              >
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="时间">
            <el-date-picker
              v-model="node.date"
              type="date"
              placeholder="选择日期"
              style="width: 160px;"
            >
            </el-date-picker>
          </el-form-item>



          <el-form-item>
            <el-button icon="el-icon-close">取消</el-button>
            <el-button type="primary" icon="el-icon-check" @click="save"
              >保存</el-button
            >
          </el-form-item>
        </el-form>

        <!-- 为line添加条件 -->
        <el-form
          :model="line"
          ref="dataForm"
          label-width="80px"
          v-show="type === 'line'"
        >
          <el-form-item label="条件">
            <el-input v-model="line.label"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button icon="el-icon-close">重置</el-button>
            <el-button type="primary" icon="el-icon-check" @click="saveLine"
              >保存</el-button
            >
          </el-form-item>
        </el-form>
      </div>

    </div>
  </div>
</template>

<script>
import { cloneDeep } from "lodash";

export default {
  data() {
    return {
      visible: true,
      // node 或 line
      type: "node",
      // 存储node节点的相关信息
      node: {},
      departments: ['财务', '市场', '安环', '生产', '供应', '技术', '企管', '党群', '质量'] ,// 可以根据需要修改这些部门名称

      line: {},
      data: {},
      stateList: [
        {
          state: "success",
          label: "成功",
        },
        {
          state: "warning",
          label: "警告",
        },
        {
          state: "error",
          label: "错误",
        },
        {
          state: "running",
          label: "运行中",
        },
      ],
    };
  },
  methods: {
    /**
     * 表单修改，这里可以根据传入的ID进行业务信息获取
     * @param data
     * @param id
     */
    nodeInit(data, id) {
      this.type = "node";
      this.data = data;
      data.nodeList.filter((node) => {
        if (node.id === id) {
          this.node = cloneDeep(node);
        }
      });
    },
    lineInit(line) {
      this.type = "line";
      this.line = line;
    },
    // 修改连线
    saveLine() {
      /**
       * @param from 连线的开始节点
       * @param to 连线的指向节点
       * @param label 连线上的条件
       */

      this.$emit("setLineLabel", this.line.from, this.line.to, this.line.label);
    },
    save() {
      this.data.nodeList.filter((node) => {
        if (node.id === this.node.id) {
          node.name = this.node.name;
          node.left = this.node.left;
          node.top = this.node.top;
          node.ico = this.node.ico;

          //0727新增
          node.department = this.node.department;
          node.description = this.node.description;
          node.operationalStaff = this.node.operationalStaff;
          node.date = this.node.date;

          //->制度文件
          node.state = this.node.state;
          //->表单文件
          node.type = this.node.type;

          // node.state = JSON.parse(this.node.state);
          this.$emit("repaintEverything");
        }
      });
    },
  },
};
</script>



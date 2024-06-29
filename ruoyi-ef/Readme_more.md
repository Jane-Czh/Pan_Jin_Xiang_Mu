### 前端页面
1. info.vue
    
2. node_form.vue 
    右侧编辑的区域：当点击选中的node时，panel中的方法 clickNode(nodeId) 会对应到
node_form.vue中的nodeInit
3. node_menu.vue
    左侧的节点选项：开始节点、结束节点
4. node.vue
    点击节点后，获取右侧信息
5. panel.vue
     顶部工具菜单
### 流程图依赖关系
 project： 不同的节点组成的一个流程这里被称之为项目。一个项目下可以包含多种节点和连线。
 node：通过project_id和项目进行关联。
 line: 通过project_id和项目进行关联。同时设置了约束（两个节点之间不能存在多个相同的连线）。
import { listFilemanagement } from "@/api/file/filemanagement";

export default (await import('vue')).defineComponent({
props: {
selectedFileNames: {
// 接收父组件传递的已绑定的文件信息
type: Array,
default: () => [],
},
},
name: "CustomFiles",
inject: ["reload"],
data() {
return {
activeName: "first",

// 制度文件数据
filemanagementList: [],
// 制度文件中 选中数组传给 node
idsRegulation: [],
namesRegulation: [],
// 从node传来的 已绑定的文件信息
// 表单文件数据
formsmanagementList: [],
// 表单文件中 选中数组传给 node
idsForm: [],
namesForm: [],
// 从node传来的 已绑定的文件信息
// 制度查询参数
queryParams: {
pageNum: 1,
pageSize: 10,
regulationsTitle: null,
useScope: null,
uploadDate: null,
effectiveDate: null,
regulationsAddress: null,
fileName: null,
filePath: null,
fileType: null,
fileSize: null,
createDate: null,
createUsername: null,
useState: null,
departmentCategory: null,
fileTag: null,
oldRegulationsId: null,
revisionDate: null,
revisionContent: null,
reviser: null,
},
// 总条数
pageIndex: 1,
pageSize: 10,
totalPage: 0,
};
},
created() {
this.getRegularFileData();
},
mounted() {
// 当组件挂载时，设置已绑定的文件信息为选中状态
this.setSelectedFileNames(this.props.selectedFileNames);
},
methods: {
hello() {
console.log("hello");
},
/** 查询制度文件列表 */
getRegularFileData() {
listFilemanagement(this.queryParams).then((response) => {
this.filemanagementList = response.rows;
this.total = response.total;
});

//数据分页
this.filemanagementList = this.filemanagementList.slice(
(this.pageIndex - 1) * this.pageSize,
this.pageIndex * this.pageSize
);
},

/** 查询表单文件列表 */
getFormFileData() {
listFilemanagement(this.queryParams).then((response) => {
this.filemanagementList = response.rows;
this.total = response.total;
});
},

handleClick(tab, event) {
console.log(tab, event);
},

/** 保存制度 选择的行数据 */
handleRegulationSelectionChange(selection) {
// 选中的行数据
this.ids = selection.map((item) => item.regulationsId);
this.names = selection.map((item) => item.fileName);
// 触发自定义事件，并传递 ids 和 names 数据
// this.$emit("selection-change", { ids: this.ids, names: this.names });
},
//传递给node数据ids&names
getSelectedRegulationIdsAndNames() {
return {
idsRegulation: this.ids,
namesRegulation: this.names,
};
},

//传递给node数据ids&names
getSelectedRegulationIdsAndNames() {
return {
idsRegulation: this.ids,
namesRegulation: this.names,
};
},

//接收node的数据
setSelectedFileNames(names) {
// 接收父组件传递的已绑定的文件信息
this.selectedFileNames = names;
// 设置已绑定的文件信息为已选状态
this.setFilesSelected();
},
setFilesSelected() {
// 将已绑定的文件信息设置为已选状态
this.$nextTick(() => {
this.selectedFileNames.forEach((name) => {
const row = this.filemanagementList.find(
(item) => item.fileName === name
);
if (row) {
this.$refs.multipleTable.toggleRowSelection(row, true);
}
});
});
},

//分页功能相关
// 每页数
sizeChangeHandle(val) {
this.pageSize = val;
this.pageIndex = 1;
this.getDataList();
},
// 当前页
currentChangeHandle(val) {
this.pageIndex = val;
this.getDataList();
},
},
});

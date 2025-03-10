import request from '@/utils/request'

// 不需要以下两处标记的ip了, 版本进行了融合 2024.0625
// 部署的时候将localhost --> ip 172.19.9.246  
// 同时还有：pane.vue--save()、 edit_panel.vue--updateProject() 



//保存sb 流程模板数据
export function saveSBModelProject(data) {
  return request({
    url: '/ef/model/save',
    method: 'post',
    data: data
  })
}
//查询sb 流程模板数据
export function listSBModelProject() {
  return request({
    url: '/ef/model/list',
    method: 'get',
  })
}

export function delSBProject(id) {
  return request({
    url: '/ef/model/delete/' + id,
    method: 'delete'
  })
}

//--------

//pane.vue--save()的接口改造 saveProject()、saveNode() 、saveLine()
export function saveProject(data) {
  return request({
    url: '/project/saveProject',
    method: 'post',
    data: data
  })
}

// 保存节点数据
export function saveNode(data) {
  return request({
    url: '/node/saveNode',
    method: 'post',
    data: data
  });
}

// 保存连线数据
export function saveLine(data) {
  return request({
    url: '/line/saveLine',
    method: 'post',
    data: data
  });
}

//edit_panel.vue--updateProject() 接口的改进
// 更新项目数据
export function updateProjectFromOld(data) {
  return request({
    url: '/project/updateProjectFromOld',
    method: 'post',
    data: data
  });
}

//--------------------------------------------

// 流程管理-更新流程名称
export function updateProject(data) {
  return request({
    url: '/project/updateProjectName',
    method: 'post',
    data: data
  })
}

// 查询指定id的流程projectEntity
export function getProjectEntityById(id) {
  return request({
    url: '/project/queryData/' + id,
    method: 'get'
  })
}


// 查询指定id的流程详细
export function compareProjects(id) {
  return request({
    url: '/project/compareData/' + id,
    method: 'get'
  })
}


// 按照name查询流程
export function getProjectByName(query) {
  return request({
    url: '/project/searchList',
    method: 'post',
    data: query,

  })
}

// 职能体系 模块 按照 部门、业务模块、细分业务进行查询
export function getProjectByName2(query) {
  return request({
    url: '/project/searchList2',
    method: 'post',
    data: query,

  })
}

// 按照制度流程 file name查询流程
export function getProjectFileName(query) {
  return request({
    url: '/project/searchListByFileName',
    method: 'post',
    data: {
      filename: query.filename
    }

  })
}


// 根据id获取流程名称name
export function getProjectName(id) {
  return request({
    url: '/project/projectName/' + id,
    method: 'get',
  })
}

// 指标:当前流程变更次数统计
export function getIndicatorUpdateCounts(data) {
  return request({
    url: `/project/indicators/updateCounts`,
    method: 'post',
    data: {
      startTime: data.startTime,
      endTime: data.endTime,
      id: data.id,
    }
  })
}


// 查询流程列表
export function listProject(query) {
  return request({
    url: '/project/list',
    method: 'get',
    params: query
  })
}

// 查询流程列表--panel
export function listProject2(query) {
  return request({
    url: '/project/list/list',
    method: 'get',
    params: query
  })
}

// 查询流程列表--职能体系
export function listProject3(query) {
  return request({
    url: '/project/list/query',
    method: 'post',
    params: query
  })
}

// 查询指定id的流程详细
//edit编辑
export function getProject(id) {
  return request({
    url: '/project/data/' + id,
    method: 'get'
  })
}
//update
export function getProject1(id) {
  return request({
    url: '/project/data1/' + id,
    method: 'get'
  })
}
//view
export function getProject2(id) {
  return request({
    url: '/project/data2/' + id,
    method: 'get'
  })
}

//指标 -- 无权限即可查
export function getProject0(id) {
  return request({
    url: '/project/data0/' + id,
    method: 'get'
  })
}

// 查询指定id流程的 历史相关流程列表
export function historyListProject(id) {
  return request({
    url: '/project/historyList/' + id,
    method: 'get',
  })
}

// 新增流程
// export function addProject(data) {
//   return request({
//     url: 'http://localhost:8080/project',
//     method: 'post',
//     data: data
//   })
// }



// 删除流程: 删除指定id得流程project
export function delProject(id) {
  return request({
    url: '/project/delete/' + id,
    method: 'delete'
  })
}

// 删除流程: 删除指定id得流程project
export function delHistoryProject(id) {
  return request({
    url: '/project/deleteHistory/' + id,
    method: 'delete'
  })
}

//查询文件
// 1.1 查询制度文件列表 newest == 1
export function listFilemanagement(query) {
  return request({
    url: '/file/filemanagement/list2',
    method: 'get',
    params: query
  })
}

// 1.2 查询制度文件列表 所有的文件
export function listFilemanagementAll(query) {
  return request({
    url: '/file/filemanagement/list3',
    method: 'get',
    params: query
  })
}


// 2.1查询表单文件列表 newest == 1
export function listFormfilemanagement(query) {
  return request({
    url: '/file/formfilemanagement/list2',
    method: 'get',
    params: query
  })
}

//2.2 查询所有的 表单文件列表
export function listFormfilemanagementAll(query) {
  return request({
    url: '/file/formfilemanagement/list1',
    method: 'get',
    params: query
  })
}



// 通过depId查询部门详细
export function getDept(deptId) {
  return request({
    url: '/system/dept/dept/' + deptId,
    method: 'get'
  })
}

//直接获取部门list
export function listDept(query) {
  return request({
    url: '/system/dept/dept/list',
    method: 'get',
    params: query
  })
}


//0912 sbi玩意 --获取总台账信息
export function getAllShowEntity() {
  return request({
    url: '/project/getAll',
    method: 'get',
  })
}


//0912 sb 
export function listProjectWithTime(timeData) {
  return request({
    url: '/project/list/time',
    method: 'post',
    data: {
      startTime: timeData.startTime,
      endTime: timeData.endTime,
    }
  })
}


// 根据 时间 和 专业分类统计 流程总数
export function getProcessCountsByClassification(data1,data2) {
  return request({
    url: `/project/getDataByclassification`,
    method: 'post',
    data: {
      startTime: data1.startTime,
      endTime: data1.endTime,
      mainResponsibleDepartment: data2
    }
  })
}

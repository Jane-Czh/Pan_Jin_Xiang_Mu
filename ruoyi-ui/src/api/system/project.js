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
    data: {
      name: query.name
    }

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
// 查询制度文件列表
export function listFilemanagement(query) {
  return request({
    url: '/file/filemanagement/list2',
    method: 'get',
    params: query
  })
}

//查询表单文件列表
export function listFormfilemanagement(query) {
  return request({
    url: '/file/formfilemanagement/list2',
    method: 'get',
    params: query
  })
}
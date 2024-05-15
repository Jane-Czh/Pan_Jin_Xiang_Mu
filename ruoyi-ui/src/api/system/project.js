import request from '@/utils/request'

// 部署的时候将localhost --> ip 172.19.9.246
// 同时还有：pane.vue--save()、 edit_panel.vue--updateProject()


// 按照name查询流程
export function getProjectByName(query) {
  return request({
    url: 'http://localhost:8081/project/searchList',
    method: 'post',
    data: {
      name: query.name
    }

  })
}


// 根据id获取流程名称name
export function getProjectName(id) {
  return request({
    url: 'http://localhost:8081/project/projectName/' + id,
    method: 'get',
  })
}

// 指标:当前流程变更次数统计
export function getIndicatorUpdateCounts(data) {
  return request({
    url: `http://localhost:8081/project/indicators/updateCounts`,
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
    url: 'http://localhost:8081/project/list',
    method: 'get',
    params: query
  })
}

// 查询指定id的流程详细
export function getProject(id) {
  return request({
    url: 'http://localhost:8081/project/data/' + id,
    method: 'get'
  })
}

// 查询指定id流程的 历史相关流程列表
export function historyListProject(id) {
  return request({
    url: 'http://localhost:8081/project/historyList/' + id,
    method: 'get',
  })
}

// 新增流程
// export function addProject(data) {
//   return request({
//     url: 'http://localhost:8081/project',
//     method: 'post',
//     data: data
//   })
// }

// 修改流程
export function updateProject(data) {
  return request({
    url: 'http://localhost:8081/project/update',
    method: 'post',
    data: data
  })
}

// 删除流程: 删除指定id得流程project
export function delProject(id) {
  return request({
    url: 'http://localhost:8081/project/delete/' + id,
    method: 'delete'
  })
}

// 删除流程: 删除指定id得流程project
export function delHistoryProject(id) {
  return request({
    url: 'http://localhost:8081/project/deleteHistory/' + id,
    method: 'delete'
  })
}

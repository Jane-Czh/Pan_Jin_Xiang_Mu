import request from '@/utils/request'

// 查询历史项目信息管理列表
export function listHistory(query) {
  return request({
    url: '/project/history/list',
    method: 'get',
    params: query
  })
}

// 查询历史项目信息管理详细
export function getHistory(projectId) {
  return request({
    url: '/project/history/' + projectId,
    method: 'get'
  })
}

// 新增历史项目信息管理
export function addHistory(data) {
  return request({
    url: '/project/history',
    method: 'post',
    data: data
  })
}

// 修改历史项目信息管理
export function updateHistory(data) {
  return request({
    url: '/project/history',
    method: 'put',
    data: data
  })
}

// 删除历史项目信息管理
export function delHistory(projectId) {
  return request({
    url: '/project/history/' + projectId,
    method: 'delete'
  })
}
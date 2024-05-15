import request from '@/utils/request'

// 查询项目基本信息列表
export function listInfo(query) {
  return request({
    url: '/project/Info/list',
    method: 'get',
    params: query
  })
}

// 查询项目基本信息详细
export function getInfo(projectId) {
  return request({
    url: '/project/Info/' + projectId,
    method: 'get'
  })
}

// 新增项目基本信息
export function addInfo(data) {
  return request({
    url: '/project/Info',
    method: 'post',
    data: data
  })
}

// 修改项目基本信息
export function updateInfo(data) {
  return request({
    url: '/project/Info',
    method: 'put',
    data: data
  })
}

// 删除项目基本信息
export function delInfo(projectId) {
  return request({
    url: '/project/Info/' + projectId,
    method: 'delete'
  })
}

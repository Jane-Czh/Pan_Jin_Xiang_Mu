import request from '@/utils/request'

// 查询项目基本信息表列表
export function listInfo(query) {
  return request({
    url: '/project/info/list',
    method: 'get',
    params: query
  })
}

// 查询项目基本信息表详细
export function getInfo(projectId) {
  return request({
    url: '/project/info/' + projectId,
    method: 'get'
  })
}

// 新增项目基本信息表
export function addInfo(data) {
  return request({
    url: '/project/info',
    method: 'post',
    data: data
  })
}

// 修改项目基本信息表
export function updateInfo(data) {
  return request({
    url: '/project/info',
    method: 'put',
    data: data
  })
}

// 删除项目基本信息表
export function delInfo(projectId) {
  return request({
    url: '/project/info/' + projectId,
    method: 'delete'
  })
}

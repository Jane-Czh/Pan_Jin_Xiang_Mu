import request from '@/utils/request'

// 查询十一项管理指标列表

export function listManagement(query) {
  return request({
    url: '/enterprise/data/management/list',
    method: 'get',
    params: query
  })
}

// 查询十一项管理指标详细

export function getManagement(emId) {
  return request({
    url: '/enterprise/data/management/' + emId,
    method: 'get'
  })
}

// 新增十一项管理指标

export function addManagement(data) {
  return request({
    url: '/enterprise/data/management',
    method: 'post',
    data: data
  })
}

// 修改十一项管理指标

export function updateManagement(data) {
  return request({
    url: '/enterprise/data/management',
    method: 'put',
    data: data
  })
}

// 删除十一项管理指标

export function delManagement(emIds) {
  return request({
    url: '/enterprise/data/management/' + emIds,
    method: 'delete'
  })
}
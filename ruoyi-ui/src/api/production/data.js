import request from '@/utils/request'

// 查询[生产]手动填报指标功能列表
export function listData(query) {
  return request({
    url: '/production/data/fill/list',
    method: 'get',
    params: query
  })
}

// 查询[生产]手动填报指标功能详细
export function getData(productionId) {
  return request({
    url: '/production/data/fill/' + productionId,
    method: 'get'
  })
}

// 新增[生产]手动填报指标功能
export function addData(data) {
  return request({
    url: '/production/data/fill',
    method: 'post',
    data: data
  })
}

// 修改[生产]手动填报指标功能
export function updateData(data) {
  return request({
    url: '/production/data/fill',
    method: 'put',
    data: data
  })
}

// 删除[生产]手动填报指标功能
export function delData(productionId) {
  return request({
    url: '/production/data/fill/' + productionId,
    method: 'delete'
  })
}








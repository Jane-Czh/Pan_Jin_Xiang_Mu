import request from '@/utils/request'

// 查询[安全环保]指标填报列表
export function listData(query) {
  return request({
    url: '/safety/data/list',
    method: 'get',
    params: query
  })
}

// 查询[安全环保]指标填报详细
export function getData(safetyEpId) {
  return request({
    url: '/safety/data/' + safetyEpId,
    method: 'get'
  })
}

// 新增[安全环保]指标填报
export function addData(data) {
  return request({
    url: '/safety/data',
    method: 'post',
    data: data
  })
}

// 修改[安全环保]指标填报
export function updateData(data) {
  return request({
    url: '/safety/data',
    method: 'put',
    data: data
  })
}

// 删除[安全环保]指标填报
export function delData(safetyEpId) {
  return request({
    url: '/safety/data/' + safetyEpId,
    method: 'delete'
  })
}






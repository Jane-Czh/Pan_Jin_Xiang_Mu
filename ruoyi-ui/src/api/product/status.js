import request from '@/utils/request'

// 查询计划完成情况列表
export function listStatus(query) {
  return request({
    url: '/product/status/list',
    method: 'get',
    params: query
  })
}

// 查询计划完成情况详细
export function getStatus(mpcId) {
  return request({
    url: '/product/status/' + mpcId,
    method: 'get'
  })
}

// 新增计划完成情况
export function addStatus(data) {
  return request({
    url: '/product/status',
    method: 'post',
    data: data
  })
}

// 修改计划完成情况
export function updateStatus(data) {
  return request({
    url: '/product/status',
    method: 'put',
    data: data
  })
}

// 删除计划完成情况
export function delStatus(mpcId) {
  return request({
    url: '/product/status/' + mpcId,
    method: 'delete'
  })
}

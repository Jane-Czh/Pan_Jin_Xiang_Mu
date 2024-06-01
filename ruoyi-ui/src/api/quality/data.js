import request from '@/utils/request'

// 查询[质量]指标填报列表
export function listHandFill(query) {
  return request({
    url: '/quality/data/handfill/list',
    method: 'get',
    params: query
  })
}

// 查询[质量]指标填报详细
export function getHandFill(qihfId) {
  return request({
    url: '/quality/data/handfill/' + qihfId,
    method: 'get'
  })
}

// 新增[质量]指标填报
export function addHandFill(data) {
  return request({
    url: '/quality/data/handfill',
    method: 'post',
    data: data
  })
}

// 修改[质量]指标填报
export function updateHandFill(data) {
  return request({
    url: '/quality/data/handfill',
    method: 'put',
    data: data
  })
}

// 删除[质量]指标填报
export function delHandFill(qihfId) {
  return request({
    url: '/quality/data/handfill/' + qihfId,
    method: 'delete'
  })
}



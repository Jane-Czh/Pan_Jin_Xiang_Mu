import request from '@/utils/request'

// 查询质量售后表列表
export function listMetrics(query) {
  return request({
    url: '/quality/data/metrics/list',
    method: 'get',
    params: query
  })
}

// 查询质量指标-统计详细
export function getMetrics(qcId) {
  return request({
    url: '/quality/data/metrics/' + qcId,
    method: 'get'
  })
}

// 新增质量指标-统计
export function addMetrics(data) {
  return request({
    url: '/quality/data/metrics',
    method: 'post',
    data: data
  })
}

// 修改质量指标-统计
export function updateMetrics(data) {
  return request({
    url: '/quality/data/metrics',
    method: 'put',
    data: data
  })
}

// 删除质量指标-统计
export function delMetrics(qcId) {
  return request({
    url: '/quality/data/metrics/' + qcId,
    method: 'delete'
  })
}
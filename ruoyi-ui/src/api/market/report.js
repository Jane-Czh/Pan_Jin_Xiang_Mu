import request from '@/utils/request'

// 查询市场科销售快报功能列表
export function listReport(query) {
  return request({
    url: '/market/report/list',
    method: 'get',
    params: query
  })
}

// 查询市场科销售快报功能详细
export function getReport(mfqrId) {
  return request({
    url: '/market/report/' + mfqrId,
    method: 'get'
  })
}

// 新增市场科销售快报功能
export function addReport(data) {
  return request({
    url: '/market/report',
    method: 'post',
    data: data
  })
}

// 修改市场科销售快报功能
export function updateReport(data) {
  return request({
    url: '/market/report',
    method: 'put',
    data: data
  })
}

// 删除市场科销售快报功能
export function delReport(mfqrId) {
  return request({
    url: '/market/report/' + mfqrId,
    method: 'delete'
  })
}

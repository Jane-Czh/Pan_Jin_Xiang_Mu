import request from '@/utils/request'

// 查询员工统计列表
export function listStatistics(query) {
  return request({
    url: '/Enterprisemanagement/statistics/list',
    method: 'get',
    params: query
  })
}

// 查询员工统计详细
export function getStatistics(emesId) {
  return request({
    url: '/Enterprisemanagement/statistics/' + emesId,
    method: 'get'
  })
}

// 新增员工统计
export function addStatistics(data) {
  return request({
    url: '/Enterprisemanagement/statistics',
    method: 'post',
    data: data
  })
}

// 修改员工统计
export function updateStatistics(data) {
  return request({
    url: '/Enterprisemanagement/statistics',
    method: 'put',
    data: data
  })
}

// 删除员工统计
export function delStatistics(emesId) {
  return request({
    url: '/Enterprisemanagement/statistics/' + emesId,
    method: 'delete'
  })
}

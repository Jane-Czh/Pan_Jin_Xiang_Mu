import request from '@/utils/request'

// 查询同配置车型交货天数对比功能列表
export function listCompare(query) {
  return request({
    url: '/market/compare/list',
    method: 'get',
    params: query
  })
}

// 查询同配置车型交货天数对比功能详细
export function getCompare(mfcdId) {
  return request({
    url: '/market/compare/' + mfcdId,
    method: 'get'
  })
}

// 新增同配置车型交货天数对比功能
export function addCompare(data) {
  return request({
    url: '/market/compare',
    method: 'post',
    data: data
  })
}

// 修改同配置车型交货天数对比功能
export function updateCompare(data) {
  return request({
    url: '/market/compare',
    method: 'put',
    data: data
  })
}

// 删除同配置车型交货天数对比功能
export function delCompare(mfcdId) {
  return request({
    url: '/market/compare/' + mfcdId,
    method: 'delete'
  })
}

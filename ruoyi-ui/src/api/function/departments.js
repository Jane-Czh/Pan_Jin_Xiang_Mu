import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listDepartments(query) {
  return request({
    url: '/system/departments/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getDepartments(depId) {
  return request({
    url: '/system/departments/' + depId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addDepartments(data) {
  return request({
    url: '/system/departments',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateDepartments(data) {
  return request({
    url: '/system/departments',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delDepartments(depId) {
  return request({
    url: '/system/departments/' + depId,
    method: 'delete'
  })
}

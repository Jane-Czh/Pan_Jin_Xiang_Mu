import request from '@/utils/request'

// 查询员工花名单导入列表
export function listRoster(query) {
  return request({
    url: '/Enterprisemanagement/roster/list',
    method: 'get',
    params: query
  })
}

// 查询员工花名单导入详细
export function getRoster(emprId) {
  return request({
    url: '/Enterprisemanagement/roster/' + emprId,
    method: 'get'
  })
}

// 新增员工花名单导入
export function addRoster(data) {
  return request({
    url: '/Enterprisemanagement/roster',
    method: 'post',
    data: data
  })
}

// 修改员工花名单导入
export function updateRoster(data) {
  return request({
    url: '/Enterprisemanagement/roster',
    method: 'put',
    data: data
  })
}

// 删除员工花名单导入
export function delRoster(emprId) {
  return request({
    url: '/Enterprisemanagement/roster/' + emprId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询部门文件列表
export function listTable(query) {
  return request({
    url: '/departmentFile/table/list',
    method: 'get',
    params: query
  })
}

// 查询部门文件详细
export function getTable(fileId) {
  return request({
    url: '/departmentFile/table/' + fileId,
    method: 'get'
  })
}

// 新增部门文件
export function addTable(data) {
  return request({
    url: '/departmentFile/table',
    method: 'post',
    data: data
  })
}

// 修改部门文件
export function updateTable(data) {
  return request({
    url: '/departmentFile/table',
    method: 'put',
    data: data
  })
}

// 删除部门文件
export function delTable(fileId) {
  return request({
    url: '/departmentFile/table/' + fileId,
    method: 'delete'
  })
}

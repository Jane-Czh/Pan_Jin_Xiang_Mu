import request from '@/utils/request'

// 查询加班统计列表
export function listOsTable(query) {
  return request({
    url: '/product/OsTable/list',
    method: 'get',
    params: query
  })
}

// 查询加班统计详细
export function getOsTable(postId) {
  return request({
    url: '/product/OsTable/' + postId,
    method: 'get'
  })
}

// 新增加班统计
export function addOsTable(data) {
  return request({
    url: '/product/OsTable',
    method: 'post',
    data: data
  })
}

// 修改加班统计
export function updateOsTable(data) {
  return request({
    url: '/product/OsTable',
    method: 'put',
    data: data
  })
}

// 删除加班统计
export function delOsTable(postId) {
  return request({
    url: '/product/OsTable/' + postId,
    method: 'delete'
  })
}

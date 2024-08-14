import request from '@/utils/request'

// 查询项目信息回收列表
export function listRecycle(query) {
  return request({
    url: '/project/recycle/list',
    method: 'get',
    params: query
  })
}

// 查询项目信息回收详细
export function getRecycle(projectId) {
  return request({
    url: '/project/recycle/' + projectId,
    method: 'get'
  })
}

// 新增项目信息回收
export function addRecycle(data) {
  return request({
    url: '/project/recycle',
    method: 'post',
    data: data
  })
}

// 修改项目信息回收
export function updateRecycle(data) {
  return request({
    url: '/project/recycle',
    method: 'put',
    data: data
  })
}

// 删除项目信息回收
export function delRecycle(projectId) {
  return request({
    url: '/project/recycle/' + projectId,
    method: 'delete'
  })
}

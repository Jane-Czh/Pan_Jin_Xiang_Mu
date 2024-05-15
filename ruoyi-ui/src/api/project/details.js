import request from '@/utils/request'

// 查询项目详情列表
export function listDetails(query) {
  return request({
    url: '/project/details/list',
    method: 'get',
    params: query
  })
}

// 查询项目详情详细
export function getDetails(detailsId) {
  return request({
    url: '/project/details/' + detailsId,
    method: 'get'
  })
}

// 新增项目详情
export function addDetails(data) {
  return request({
    url: '/project/details',
    method: 'post',
    data: data
  })
}

// 修改项目详情
export function updateDetails(data) {
  return request({
    url: '/project/details',
    method: 'put',
    data: data
  })
}

// 删除项目详情
export function delDetails(detailsId) {
  return request({
    url: '/project/details/' + detailsId,
    method: 'delete'
  })
}

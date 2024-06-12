import request from '@/utils/request'

// 查询财务-利润列表
export function listInterests(query) {
  return request({
    url: '/financial/data/interests/list',
    method: 'get',
    params: query
  })
}

// 查询财务-利润详细
export function getInterests(fiId) {
  return request({
    url: '/financial/data/interests/' + fiId,
    method: 'get'
  })
}

// 新增财务-利润
export function addInterests(data) {
  return request({
    url: '/financial/data/interests',
    method: 'post',
    data: data
  })
}

// 修改财务-利润
export function updateInterests(data) {
  return request({
    url: '/financial/data/interests',
    method: 'put',
    data: data
  })
}

// 删除财务-利润
export function delInterests(fiId) {
  return request({
    url: '/financial/data/interests/' + fiId,
    method: 'delete'
  })
}
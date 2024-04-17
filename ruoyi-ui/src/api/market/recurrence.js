import request from '@/utils/request'

// 查询售后问题复发率列表
export function listRecurrence(query) {
  return request({
    url: '/market/recurrence/list',
    method: 'get',
    params: query
  })
}

// 查询售后问题复发率详细
export function getRecurrence(masrrId) {
  return request({
    url: '/market/recurrence/' + masrrId,
    method: 'get'
  })
}

// 新增售后问题复发率
export function addRecurrence(data) {
  return request({
    url: '/market/recurrence',
    method: 'post',
    data: data
  })
}

// 修改售后问题复发率
export function updateRecurrence(data) {
  return request({
    url: '/market/recurrence',
    method: 'put',
    data: data
  })
}

// 删除售后问题复发率
export function delRecurrence(masrrId) {
  return request({
    url: '/market/recurrence/' + masrrId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询月供货比例统计列表
export function listRatio(query) {
  return request({
    url: '/supply/ratio/list',
    method: 'get',
    params: query
  })
}

// 查询月供货比例统计详细
export function getRatio(srftId) {
  return request({
    url: '/supply/ratio/' + srftId,
    method: 'get'
  })
}

// 新增月供货比例统计
export function addRatio(data) {
  return request({
    url: '/supply/ratio',
    method: 'post',
    data: data
  })
}

// 修改月供货比例统计
export function updateRatio(data) {
  return request({
    url: '/supply/ratio',
    method: 'put',
    data: data
  })
}

// 删除月供货比例统计
export function delRatio(srftId) {
  return request({
    url: '/supply/ratio/' + srftId,
    method: 'delete'
  })
}

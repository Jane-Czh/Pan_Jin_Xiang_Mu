import request from '@/utils/request'

// 查询库存车明细列表
export function listDetail(query) {
  return request({
    url: '/market/detail/list',
    method: 'get',
    params: query
  })
}

// 查询库存车明细详细
export function getDetail(micdId) {
  return request({
    url: '/market/detail/' + micdId,
    method: 'get'
  })
}

// 新增库存车明细
export function addDetail(data) {
  return request({
    url: '/market/detail',
    method: 'post',
    data: data
  })
}

// 修改库存车明细
export function updateDetail(data) {
  return request({
    url: '/market/detail',
    method: 'put',
    data: data
  })
}

// 删除库存车明细
export function delDetail(micdId) {
  return request({
    url: '/market/detail/' + micdId,
    method: 'delete'
  })
}

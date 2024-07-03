import request from '@/utils/request'

// 查询商品车台账列表
export function listCVimport(query) {
  return request({
    url: '/market/CVimport/list',
    method: 'get',
    params: query
  })
}

// 查询商品车台账详细
export function getCVimport(mcvId) {
  return request({
    url: '/market/CVimport/' + mcvId,
    method: 'get'
  })
}

// 新增商品车台账
export function addCVimport(data) {
  return request({
    url: '/market/CVimport',
    method: 'post',
    data: data
  })
}

// 修改商品车台账
export function updateCVimport(data) {
  return request({
    url: '/market/CVimport',
    method: 'put',
    data: data
  })
}

// 删除商品车台账
export function delCVimport(mcvId) {
  return request({
    url: '/market/CVimport/' + mcvId,
    method: 'delete'
  })
}
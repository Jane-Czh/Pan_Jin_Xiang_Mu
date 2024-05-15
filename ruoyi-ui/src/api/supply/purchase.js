import request from '@/utils/request'

// 查询采购订单导入列表
export function listPurchase(query) {
  return request({
    url: '/supply/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询采购订单导入详细
export function getPurchase(spId) {
  return request({
    url: '/supply/purchase/' + spId,
    method: 'get'
  })
}

// 新增采购订单导入
export function addPurchase(data) {
  return request({
    url: '/supply/purchase',
    method: 'post',
    data: data
  })
}

// 修改采购订单导入
export function updatePurchase(data) {
  return request({
    url: '/supply/purchase',
    method: 'put',
    data: data
  })
}

// 删除采购订单导入
export function delPurchase(spId) {
  return request({
    url: '/supply/purchase/' + spId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询售后台账导入列表
export function listLedger(query) {
  return request({
    url: '/market/ledger/list',
    method: 'get',
    params: query
  })
}

// 查询售后台账导入详细
export function getLedger(maslId) {
  return request({
    url: '/market/ledger/' + maslId,
    method: 'get'
  })
}

// 新增售后台账导入
export function addLedger(data) {
  return request({
    url: '/market/ledger',
    method: 'post',
    data: data
  })
}

// 修改售后台账导入
export function updateLedger(data) {
  return request({
    url: '/market/ledger',
    method: 'put',
    data: data
  })
}

// 删除售后台账导入
export function delLedger(maslId) {
  return request({
    url: '/market/ledger/' + maslId,
    method: 'delete'
  })
}

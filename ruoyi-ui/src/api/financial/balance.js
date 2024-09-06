import request from '@/utils/request'

// 查询财务-资产负债列表
export function listBalance(query) {
  return request({
    url: '/financial/data/balance/list',
    method: 'get',
    params: query
  })
}

// 查询财务-资产负债详细
export function getBalance(fbId) {
  return request({
    url: '/financial/data/balance/' + fbId,
    method: 'get'
  })
}

// 新增财务-资产负债
export function addBalance(data) {
  return request({
    url: '/financial/data/balance',
    method: 'post',
    data: data
  })
}

// 修改财务-资产负债
export function updateBalance(data) {
  return request({
    url: '/financial/data/balance',
    method: 'put',
    data: data
  })
}

// 删除财务-资产负债
export function delBalance(fbId) {
  return request({
    url: '/financial/data/balance/' + fbId,
    method: 'delete'
  })
}


// 资产负债更新按钮
export function refreshBalance() {
  return request({
    url: '/financial/data/calculate',
    method: 'get'
  })
}
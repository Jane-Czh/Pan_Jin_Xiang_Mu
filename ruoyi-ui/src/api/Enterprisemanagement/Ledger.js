import request from '@/utils/request'

// 查询特种作业台账导入列表
export function listLedger(query) {
  return request({
    url: '/Enterprisemanagement/Ledger/list',
    method: 'get',
    params: query
  })
}

// 查询特种作业台账导入详细
export function getLedger(emsiId) {
  return request({
    url: '/Enterprisemanagement/Ledger/' + emsiId,
    method: 'get'
  })
}

// 新增特种作业台账导入
export function addLedger(data) {
  return request({
    url: '/Enterprisemanagement/Ledger',
    method: 'post',
    data: data
  })
}

// 修改特种作业台账导入
export function updateLedger(data) {
  return request({
    url: '/Enterprisemanagement/Ledger',
    method: 'put',
    data: data
  })
}

// 删除特种作业台账导入
export function delLedger(emsiId) {
  return request({
    url: '/Enterprisemanagement/Ledger/' + emsiId,
    method: 'delete'
  })
}

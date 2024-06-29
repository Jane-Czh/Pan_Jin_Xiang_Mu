import request from '@/utils/request'

// 查询劳动合同台账导入列表
export function listLbaorLedger(query) {
  return request({
    url: '/Enterprisemanagement/LbaorLedger/list',
    method: 'get',
    params: query
  })
}

// 查询劳动合同台账导入详细
export function getLbaorLedger(emlcId) {
  return request({
    url: '/Enterprisemanagement/LbaorLedger/' + emlcId,
    method: 'get'
  })
}

// 新增劳动合同台账导入
export function addLbaorLedger(data) {
  return request({
    url: '/Enterprisemanagement/LbaorLedger',
    method: 'post',
    data: data
  })
}

// 修改劳动合同台账导入
export function updateLbaorLedger(data) {
  return request({
    url: '/Enterprisemanagement/LbaorLedger',
    method: 'put',
    data: data
  })
}

// 删除劳动合同台账导入
export function delLbaorLedger(emlcId) {
  return request({
    url: '/Enterprisemanagement/LbaorLedger/' + emlcId,
    method: 'delete'
  })
}

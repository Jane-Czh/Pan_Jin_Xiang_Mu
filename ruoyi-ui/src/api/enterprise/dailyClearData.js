import request from '@/utils/request'


export function listDailyClearData(query) {
  return request({
    url: '/enterprise/data/dailyclear/list',
    method: 'get',
    params: query
  })
}

// 查询[企业管理]日清日结详细
export function getDailyClearData(esId) {
  return request({
    url: '/enterprise/data/dailyclear/' + esId,
    method: 'get'
  })
}

// 新增[企业管理]日清日结
export function addDailyClearData(data) {
  return request({
    url: '/enterprise/data/dailyclear',
    method: 'post',
    data: data
  })
}

// 修改[企业管理]日清日结
export function updateDailyClearData(data) {
  return request({
    url: '/enterprise/data/dailyclear',
    method: 'put',
    data: data
  })
}

// 删除[企业管理]日清日结
export function delDailyClearData(esIds) {
  return request({
    url: '/enterprise/data/dailyclear/' + esIds,
    method: 'delete'
  })
}


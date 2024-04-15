import request from '@/utils/request'

// 查询市场科销售台账列表
export function listImport(query) {
  return request({
    url: '/market/import/list',
    method: 'get',
    params: query
  })
}

// 查询市场科销售台账详细
export function getImport(msId) {
  return request({
    url: '/market/import/' + msId,
    method: 'get'
  })
}

// 新增市场科销售台账
export function addImport(data) {
  return request({
    url: '/market/import',
    method: 'post',
    data: data
  })
}

// 修改市场科销售台账
export function updateImport(data) {
  return request({
    url: '/market/import',
    method: 'put',
    data: data
  })
}

// 删除市场科销售台账
export function delImport(msId) {
  return request({
    url: '/market/import/' + msId,
    method: 'delete'
  })
}

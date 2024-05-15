import request from '@/utils/request'

// 查询销售台账存储列表
export function listStorage(query) {
  return request({
    url: '/market/Storage/list',
    method: 'get',
    params: query
  })
}

// 查询销售台账存储详细
export function getStorage(mstsId) {
  return request({
    url: '/market/Storage/' + mstsId,
    method: 'get'
  })
}

// 新增销售台账存储
export function addStorage(data) {
  return request({
    url: '/market/Storage',
    method: 'post',
    data: data
  })
}

// 修改销售台账存储
export function updateStorage(data) {
  return request({
    url: '/market/Storage',
    method: 'put',
    data: data
  })
}

// 删除销售台账存储
export function delStorage(mstsId) {
  return request({
    url: '/market/Storage/' + mstsId,
    method: 'delete'
  })
}

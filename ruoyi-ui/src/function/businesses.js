import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listBusinesses(query) {
  return request({
    url: '/system/businesses/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getBusinesses(subbId) {
  return request({
    url: '/system/businesses/' + subbId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addBusinesses(data) {
  return request({
    url: '/system/businesses',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateBusinesses(data) {
  return request({
    url: '/system/businesses',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delBusinesses(subbId) {
  return request({
    url: '/system/businesses/' + subbId,
    method: 'delete'
  })
}

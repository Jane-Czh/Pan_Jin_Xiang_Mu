import request from '@/utils/request'

// 查询安环科考核录入列表
export function listEntering(query) {
  return request({
    url: '/safety/entering/list',
    method: 'get',
    params: query
  })
}

// 查询安环科考核录入详细
export function getEntering(seaeId) {
  return request({
    url: '/safety/entering/' + seaeId,
    method: 'get'
  })
}

// 新增安环科考核录入
export function addEntering(data) {
  return request({
    url: '/safety/entering',
    method: 'post',
    data: data
  })
}

// 修改安环科考核录入
export function updateEntering(data) {
  return request({
    url: '/safety/entering',
    method: 'put',
    data: data
  })
}

// 删除安环科考核录入
export function delEntering(seaeId) {
  return request({
    url: '/safety/entering/' + seaeId,
    method: 'delete'
  })
}

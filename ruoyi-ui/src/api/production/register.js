import request from '@/utils/request'

// 查询记录标准作业信息列表
export function listRegister(query) {
  return request({
    url: '/production/register/list',
    method: 'get',
    params: query
  })
}

// 查询记录标准作业信息详细
export function getRegister(psjrfId) {
  return request({
    url: '/production/register/' + psjrfId,
    method: 'get'
  })
}

// 新增记录标准作业信息
export function addRegister(data) {
  return request({
    url: '/production/register',
    method: 'post',
    data: data
  })
}

// 修改记录标准作业信息
export function updateRegister(data) {
  return request({
    url: '/production/register',
    method: 'put',
    data: data
  })
}

// 删除记录标准作业信息
export function delRegister(psjrfId) {
  return request({
    url: '/production/register/' + psjrfId,
    method: 'delete'
  })
}

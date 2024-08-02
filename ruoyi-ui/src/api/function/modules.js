import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listModules(query) {
  return request({
    url: '/system/modules/list',
    method: 'get',
    params: query
  })
}

//无权限版本
export function listModuless(query) {
  return request({
    url: '/system/modules/list/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getModules(bmId) {
  return request({
    url: '/system/modules/' + bmId,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addModules(data) {
  return request({
    url: '/system/modules',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateModules(data) {
  return request({
    url: '/system/modules',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delModules(bmId) {
  return request({
    url: '/system/modules/' + bmId,
    method: 'delete'
  })
}

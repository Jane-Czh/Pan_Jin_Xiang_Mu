import request from '@/utils/request'

// 查询【请填写功能名称】列表
export function listNode(query) {
  return request({
    url: '/file/node/list',
    method: 'get',
    params: query
  })
}

// 查询【请填写功能名称】详细
export function getNode(id) {
  return request({
    url: '/file/node/' + id,
    method: 'get'
  })
}

// 新增【请填写功能名称】
export function addNode(data) {
  return request({
    url: '/file/node',
    method: 'post',
    data: data
  })
}

// 修改【请填写功能名称】
export function updateNode(data) {
  return request({
    url: '/file/node',
    method: 'put',
    data: data
  })
}

// 删除【请填写功能名称】
export function delNode(id) {
  return request({
    url: '/file/node/' + id,
    method: 'delete'
  })
}

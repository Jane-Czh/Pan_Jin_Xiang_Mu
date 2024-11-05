import request from '@/utils/request'

// 查询folders列表
export function listFolders(query) {
  return request({
    url: '/file/folders/list',
    method: 'get',
    params: query
  })
}

// 查询folders详细
export function getFolders(id) {
  return request({
    url: '/file/folders/' + id,
    method: 'get'
  })
}

// 新增folders
export function addFolders(data) {
  return request({
    url: '/file/folders',
    method: 'post',
    data: data
  })
}

// 修改folders
export function updateFolders(data) {
  return request({
    url: '/file/folders',
    method: 'put',
    data: data
  })
}

// 删除folders
export function delFolders(id) {
  return request({
    url: '/file/folders/' + id,
    method: 'delete'
  })
}

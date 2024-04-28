import request from '@/utils/request'

// 查询文件管理列表
export function listFilemanagement(query) {
  return request({
    url: '/file/filemanagement/list',
    method: 'get',
    params: query
  })
}

// 查询文件管理详细
export function getFilemanagement(regulationsId) {
  return request({
    url: '/file/filemanagement/' + regulationsId,
    method: 'get'
  })
}

// 新增文件管理
export function addFilemanagement(data) {
  return request({
    url: '/file/filemanagement',
    method: 'post',
    data: data
  })
}

// 修改文件管理
export function updateFilemanagement(data) {
  return request({
    url: '/file/filemanagement',
    method: 'put',
    data: data
  })
}

// 删除文件管理
export function delFilemanagement(regulationsId) {
  return request({
    url: '/file/filemanagement/' + regulationsId,
    method: 'delete'
  })
}

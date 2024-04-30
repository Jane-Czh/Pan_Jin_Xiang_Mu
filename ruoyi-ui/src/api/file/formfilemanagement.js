import request from '@/utils/request'

// 查询表单文件管理列表
export function listFormfilemanagement(query) {
  return request({
    url: '/file/formfilemanagement/list',
    method: 'get',
    params: query
  })
}

// 查询表单文件管理详细
export function getFormfilemanagement(formId) {
  return request({
    url: '/file/formfilemanagement/' + formId,
    method: 'get'
  })
}

// 新增表单文件管理
export function addFormfilemanagement(data) {
  return request({
    url: '/file/formfilemanagement',
    method: 'post',
    data: data
  })
}

// 修改表单文件管理
export function updateFormfilemanagement(data) {
  return request({
    url: '/file/formfilemanagement',
    method: 'put',
    data: data
  })
}

// 删除表单文件管理
export function delFormfilemanagement(formId) {
  return request({
    url: '/file/formfilemanagement/' + formId,
    method: 'delete'
  })
}

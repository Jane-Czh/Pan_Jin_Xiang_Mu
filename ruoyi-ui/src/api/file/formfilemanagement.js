import request from '@/utils/request'

// 查询表单文件管理列表
export function listFormfilemanagement(query) {
  return request({
    url: '/file/formfilemanagement/list',
    method: 'get',
    params: query
  })
}

// 查询表单文件管理列表（无权限+不分页）
export function listFormfilemanagement3(query) {
  return request({
    url: '/file/formfilemanagement/list3',
    method: 'get',
    params: query
  })
}

// 查询表单文件管理列表（全部+无权限）
export function listFormfilemanagement1(query) {
  return request({
    url: '/file/formfilemanagement/list1',
    method: 'get',
    params: query
  })
}

// 查询历史文件列表
export function getFormHistory(currentId) {
  return request({
    url: '/file/formfilemanagement/history/' + currentId,
    method: 'get',
    // params: query
  });
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

// word转pdf
export function word2Pdf(inPath, outPath) {
  return request({
    url: '/file/formfilemanagement/convert',
    method: 'post',
    params: { inPath, outPath }
  });
}

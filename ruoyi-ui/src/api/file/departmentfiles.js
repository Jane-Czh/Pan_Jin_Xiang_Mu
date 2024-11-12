import request from '@/utils/request'

// 查询departmentfile列表
export function listDepartmentfile(query) {
  return request({
    url: '/file/departmentfile/list',
    method: 'get',
    params: query
  })
}

// 查询departmentfile详细
export function getDepartmentfile(id) {
  return request({
    url: '/file/departmentfile/' + id,
    method: 'get'
  })
}

// 查询当前文件夹以及子文件夹下的所有文件
export function getAllfilesByFolderId(folderId,fileName) {
  return request({
    url: '/file/departmentfile/quary/' + folderId,
    method: 'get',
    params: {fileName}
  })
}

// 新增departmentfile
export function addDepartmentfile(data) {
  return request({
    url: '/file/departmentfile',
    method: 'post',
    data: data
  })
}

// 修改departmentfile
export function updateDepartmentfile(data) {
  return request({
    url: '/file/departmentfile',
    method: 'put',
    data: data
  })
}

// 删除departmentfile
export function delDepartmentfile(id) {
  return request({
    url: '/file/departmentfile/' + id,
    method: 'delete'
  })
}

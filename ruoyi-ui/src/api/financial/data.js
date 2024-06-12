import request from '@/utils/request'

// 查询[财务]手动填报指标列表
export function listData(query) {
  return request({
    url: '/financial/data/fill/list',
    method: 'get',
    params: query
  })
}

// 查询[财务]手动填报指标详细
export function getData(fihfId) {
  return request({
    url: '/financial/data/fill/' + fihfId,
    method: 'get'
  })
}

// 新增[财务]手动填报指标
export function addData(data) {
  return request({
    url: '/financial/data/fill',
    method: 'post',
    data: data
  })
}

// 修改[财务]手动填报指标
export function updateData(data) {
  return request({
    url: '/financial/data/fill',
    method: 'put',
    data: data
  })
}

// 删除[财务]手动填报指标
export function delData(fihfId) {
  return request({
    url: '/financial/data/fill/' + fihfId,
    method: 'delete'
  })
}




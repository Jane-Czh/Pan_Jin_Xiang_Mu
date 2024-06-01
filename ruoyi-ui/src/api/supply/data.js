import request from '@/utils/request'

// 查询供应科-指标-集采物料字典列表
export function listDictionary(query) {
  return request({
    url: '/supply/dictionary/list',
    method: 'get',
    params: query
  })
}

// 查询供应科-指标-集采物料字典详细
export function getDictionary(scmId) {
  return request({
    url: '/supply/dictionary/' + scmId,
    method: 'get'
  })
}

// 新增供应科-指标-集采物料字典
export function addDictionary(data) {
  return request({
    url: '/supply/dictionary',
    method: 'post',
    data: data
  })
}

// 修改供应科-指标-集采物料字典
export function updateDictionary(data) {
  return request({
    url: '/supply/dictionary',
    method: 'put',
    data: data
  })
}

// 删除供应科-指标-集采物料字典
export function delDictionary(scmId) {
  return request({
    url: '/supply/dictionary/' + scmId,
    method: 'delete'
  })
}
import request from '@/utils/request'

// 查询供应科-指标-集团管控物资列表
export function listData(query) {
  return request({
    url: '/supply/data/list',
    method: 'get',
    params: query
  })
}

// 查询供应科-指标-集团管控物资详细
export function getData(scmId) {
  return request({
    url: '/supply/data/' + scmId,
    method: 'get'
  })
}

// 新增供应科-指标-集团管控物资
export function addData(data) {
  return request({
    url: '/supply/data',
    method: 'post',
    data: data
  })
}

// 修改供应科-指标-集团管控物资
export function updateData(data) {
  return request({
    url: '/supply/data',
    method: 'put',
    data: data
  })
}

// 删除供应科-指标-集团管控物资
export function delData(scmId) {
  return request({
    url: '/supply/data/' + scmId,
    method: 'delete'
  })
}
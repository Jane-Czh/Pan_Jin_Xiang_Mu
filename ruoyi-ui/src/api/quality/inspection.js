import request from '@/utils/request'

// 查询质检部分字段列表
export function listInspection(query) {
  return request({
    url: '/quality/data/inspection/list',
    method: 'get',
    params: query
  })
}

// 查询质检部分字段详细
export function getInspection(qiId) {
  return request({
    url: '/quality/data/inspection/' + qiId,
    method: 'get'
  })
}

// 新增质检部分字段
export function addInspection(data) {
  return request({
    url: '/quality/data/inspection',
    method: 'post',
    data: data
  })
}

// 修改质检部分字段
export function updateInspection(data) {
  return request({
    url: '/quality/data/inspection',
    method: 'put',
    data: data
  })
}

// 删除质检部分字段
export function delInspection(qiId) {
  return request({
    url: '/quality/data/inspection/' + qiId,
    method: 'delete'
  })
}
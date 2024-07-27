import request from '@/utils/request'

// 查询设置上下班时间列表
export function listTime(query) {
  return request({
    url: '/product/time/list',
    method: 'get',
    params: query
  })
}

// 查询设置上下班时间详细
export function getTime(commuteTimeId) {
  return request({
    url: '/product/time/' + commuteTimeId,
    method: 'get'
  })
}

export function getrelTime(commuteTimeId) {
  return request({
    url: '/product/time/reltime',
    method: 'get'
  })
}

// 新增设置上下班时间
export function addTime(data) {
  return request({
    url: '/product/time',
    method: 'post',
    data: data
  })
}

// 修改设置上下班时间
export function updateTime(data) {
  return request({
    url: '/product/time',
    method: 'put',
    data: data
  })
}

// 删除设置上下班时间
export function delTime(commuteTimeId) {
  return request({
    url: '/product/time/' + commuteTimeId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询企管劳动合同到期提醒列表
export function listReminder(query) {
  return request({
    url: '/Enterprisemanagement/reminder/list',
    method: 'get',
    params: query
  })
}

// 查询企管劳动合同到期提醒详细
export function getReminder(emlwId) {
  return request({
    url: '/Enterprisemanagement/reminder/' + emlwId,
    method: 'get'
  })
}

// 新增企管劳动合同到期提醒
export function addReminder(data) {
  return request({
    url: '/Enterprisemanagement/reminder',
    method: 'post',
    data: data
  })
}

// 修改企管劳动合同到期提醒
export function updateReminder(data) {
  return request({
    url: '/Enterprisemanagement/reminder',
    method: 'put',
    data: data
  })
}

// 删除企管劳动合同到期提醒
export function delReminder(emlwId) {
  return request({
    url: '/Enterprisemanagement/reminder/' + emlwId,
    method: 'delete'
  })
}

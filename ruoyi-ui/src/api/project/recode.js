import request from '@/utils/request'

// 查询更新记录列表
export function listRecode(query) {
  return request({
    url: '/updata_recode/recode/list',
    method: 'get',
    params: query
  })
}

// 查询更新记录详细
export function getRecode(operId) {
  return request({
    url: '/updata_recode/recode/' + operId,
    method: 'get'
  })
}

// 新增更新记录
export function addRecode(data) {
  return request({
    url: '/updata_recode/recode',
    method: 'post',
    data: data
  })
}

// 修改更新记录
export function updateRecode(data) {
  return request({
    url: '/updata_recode/recode',
    method: 'put',
    data: data
  })
}

// 删除更新记录
export function delRecode(operId) {
  return request({
    url: '/updata_recode/recode/' + operId,
    method: 'delete'
  })
}

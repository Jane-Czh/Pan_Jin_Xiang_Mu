import request from '@/utils/request'

// 查询项目修改记录列表
export function listRecode(query) {
  return request({
    url: '/project/recode/list',
    method: 'get',
    params: query
  })
}

// 查询项目修改记录详细
export function getRecode(recodeId) {
  return request({
    url: '/project/recode/' + recodeId,
    method: 'get'
  })
}

// 新增项目修改记录
export function addRecode(data) {
  return request({
    url: '/project/recode',
    method: 'post',
    data: data
  })
}

// 修改项目修改记录
export function updateRecode(data) {
  return request({
    url: '/project/recode',
    method: 'put',
    data: data
  })
}

// 删除项目修改记录
export function delRecode(recodeId) {
  return request({
    url: '/project/recode/' + recodeId,
    method: 'delete'
  })
}

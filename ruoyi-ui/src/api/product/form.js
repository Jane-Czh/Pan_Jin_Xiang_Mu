import request from '@/utils/request'

// 查询员工打卡表列表
export function listForm(query) {
  return request({
    url: '/product/form/list',
    method: 'get',
    params: query
  })
}

// 查询员工打卡表详细
export function getForm(pcifId) {
  return request({
    url: '/product/form/' + pcifId,
    method: 'get'
  })
}

// 新增员工打卡表
export function addForm(data) {
  return request({
    url: '/product/form',
    method: 'post',
    data: data
  })
}

// 修改员工打卡表
export function updateForm(data) {
  return request({
    url: '/product/form',
    method: 'put',
    data: data
  })
}

// 删除员工打卡表
export function delForm(pcifId) {
  return request({
    url: '/product/form/' + pcifId,
    method: 'delete'
  })
}

import request from '@/utils/request'

// 查询企管特种作业证到期预警列表
export function listSpecialwarning(query) {
  return request({
    url: '/Enterprisemanagement/specialwarning/list',
    method: 'get',
    params: query
  })
}

// 查询企管特种作业证到期预警详细
export function getSpecialwarning(emsoId) {
  return request({
    url: '/Enterprisemanagement/specialwarning/' + emsoId,
    method: 'get'
  })
}

// 新增企管特种作业证到期预警
export function addSpecialwarning(data) {
  return request({
    url: '/Enterprisemanagement/specialwarning',
    method: 'post',
    data: data
  })
}

// 修改企管特种作业证到期预警
export function updateSpecialwarning(data) {
  return request({
    url: '/Enterprisemanagement/specialwarning',
    method: 'put',
    data: data
  })
}

// 删除企管特种作业证到期预警
export function delSpecialwarning(emsoId) {
  return request({
    url: '/Enterprisemanagement/specialwarning/' + emsoId,
    method: 'delete'
  })
}

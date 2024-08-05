import request from '@/utils/request'

// 查询技术-项目计划完成统计列表
export function listTechNewProjectDesign(query) {
  return request({
    url: '/tech/data/newProject/list',
    method: 'get',
    params: query
  })
}

// 查询技术-项目计划完成统计详细
export function getTechNewProjectDesign(tdId) {
  return request({
    url: '/tech/data/newProject/' + tdId,
    method: 'get'
  })
}

// 新增技术-项目计划完成统计
export function addTechNewProjectDesign(data) {
  return request({
    url: '/tech/data/newProject',
    method: 'post',
    data: data
  })
}

// 修改技术-项目计划完成统计
export function updateTechNewProjectDesign(data) {
  return request({
    url: '/tech/data/newProject',
    method: 'put',
    data: data
  })
}

// 删除技术-项目计划完成统计
export function delTechNewProjectDesign(tdId) {
  return request({
    url: '/tech/data/newProject/' + tdId,
    method: 'delete'
  })
}

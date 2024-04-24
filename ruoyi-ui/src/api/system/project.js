import request from '@/utils/request'
import axios from 'axios'


// 查询流程列表
export function listProject(query) {
  return request({
    url: 'http://localhost:8081/project/list',
    method: 'get',
    params: query
  })
}

// 查询指定id的流程详细
export function getProject(id) {
  return request({
    url: 'http://localhost:8081/project/data/' + id,
    method: 'get'
  })
}

// 查询指定id流程的 历史相关流程列表
export function historyListProject(id) {
  return request({
    url: 'http://localhost:8081/project/historyList/' + id,
    method: 'get',
  })
}

// 新增流程
// export function addProject(data) {
//   return request({
//     url: 'http://localhost:8081/project',
//     method: 'post',
//     data: data
//   })
// }

// 修改流程
export function updateProject(data) {
  return request({
    url: 'http://localhost:8081/project/update',
    method: 'post',
    data: data
  })
}

// 删除流程: 删除指定id得流程project
export function delProject(id) {
  return request({
    url: 'http://localhost:8081/project/delete/' + id,
    method: 'delete'
  })
}

// 删除流程: 删除指定id得流程project
export function delHistoryProject(id) {
  return request({
    url: 'http://localhost:8081/project/deleteHistory/' + id,
    method: 'delete'
  })
}

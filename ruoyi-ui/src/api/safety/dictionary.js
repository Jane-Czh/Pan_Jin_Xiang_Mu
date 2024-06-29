import request from '@/utils/request'

// 查询重点设备字典列表
export function listDictionary(query) {
  return request({
    url: '/safety/data/dictionary/list',
    method: 'get',
    params: query
  })
}

// 查询重点设备字典详细
export function getDictionary(skId) {
  return request({
    url: '/safety/data/dictionary/' + skId,
    method: 'get'
  })
}

// 新增重点设备字典
export function addDictionary(data) {
  return request({
    url: '/safety/data/dictionary',
    method: 'post',
    data: data
  })
}

// 修改重点设备字典
export function updateDictionary(data) {
  return request({
    url: '/safety/data/dictionary',
    method: 'put',
    data: data
  })
}

// 删除重点设备字典
export function delDictionary(skId) {
  return request({
    url: '/safety/data/dictionary/' + skId,
    method: 'delete'
  })
}
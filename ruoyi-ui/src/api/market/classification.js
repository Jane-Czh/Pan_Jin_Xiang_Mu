import request from '@/utils/request'

// 查询售后问题分类列表
export function listClassification(query) {
  return request({
    url: '/market/classification/list',
    method: 'get',
    params: query
  })
}

// 查询售后问题分类详细
export function getClassification(maspcId) {
  return request({
    url: '/market/classification/' + maspcId,
    method: 'get'
  })
}

// 新增售后问题分类
export function addClassification(data) {
  return request({
    url: '/market/classification',
    method: 'post',
    data: data
  })
}

// 修改售后问题分类
export function updateClassification(data) {
  return request({
    url: '/market/classification',
    method: 'put',
    data: data
  })
}

// 删除售后问题分类
export function delClassification(maspcId) {
  return request({
    url: '/market/classification/' + maspcId,
    method: 'delete'
  })
}

export function synchronization(){
  return request({
    url: '/market/classification/synchronization',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },

  });
}

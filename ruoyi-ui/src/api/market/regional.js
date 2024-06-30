import request from '@/utils/request'

// 查询售后区域分类列表
export function listRegional(query) {
  return request({
    url: '/market/regional/list',
    method: 'get',
    params: query
  })
}

// 查询售后区域分类详细
export function getRegional(masrcId) {
  return request({
    url: '/market/regional/' + masrcId,
    method: 'get'
  })
}

// 新增售后区域分类
export function addRegional(data) {
  return request({
    url: '/market/regional',
    method: 'post',
    data: data
  })
}

// 修改售后区域分类
export function updateRegional(data) {
  return request({
    url: '/market/regional',
    method: 'put',
    data: data
  })
}

// 删除售后区域分类
export function delRegional(masrcId) {
  return request({
    url: '/market/regional/' + masrcId,
    method: 'delete'
  })
}

export function synchronization(){
  return request({
    url: '/market/regional/synchronization',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },

  });
}
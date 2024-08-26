import request from '@/utils/request'

// 查询统计库存车数量列表
export function listUnsoldcar(query) {
  return request({
    url: '/market/unsoldcar/list',
    method: 'get',
    params: query
  })
}

// 查询统计库存车数量详细
export function getUnsoldcar(ucId) {
  return request({
    url: '/market/unsoldcar/' + ucId,
    method: 'get'
  })
}

// 新增统计库存车数量
export function addUnsoldcar(data) {
  return request({
    url: '/market/unsoldcar',
    method: 'post',
    data: data
  })
}

// 修改统计库存车数量
export function updateUnsoldcar(data) {
  return request({
    url: '/market/unsoldcar',
    method: 'put',
    data: data
  })
}

// 删除统计库存车数量
export function delUnsoldcar(ucId) {
  return request({
    url: '/market/unsoldcar/' + ucId,
    method: 'delete'
  })
}

export function synchronization(){
  return request({
    url: '/market/unsoldcar/synchronization',
    method: 'post',
    headers: {
      'Content-Type': 'application/json'
    },

  });
}

export function vehicleTypequery(type){
  return request({
    url: '/market/unsoldcar/vehicleTypequery/' + type,
    method: 'get',
  });
}
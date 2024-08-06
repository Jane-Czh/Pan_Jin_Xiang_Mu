import request from '@/utils/request'

// 查询Tech_Non_Standard_Order列表
export function listTech_Non_Standard_Order(query) {
  return request({
    url: '/tech/data/nonStandardOrder/list',
    method: 'get',
    params: query
  })
}

// 查询Tech_Non_Standard_Order详细
export function getTech_Non_Standard_Order(tnId) {
  return request({
    url: '/tech/data/nonStandardOrder/' + tnId,
    method: 'get'
  })
}

// 新增Tech_Non_Standard_Order
export function addTech_Non_Standard_Order(data) {
  return request({
    url: '/tech/data/nonStandardOrder',
    method: 'post',
    data: data
  })
}

// 修改Tech_Non_Standard_Order
export function updateTech_Non_Standard_Order(data) {
  return request({
    url: '/tech/data/nonStandardOrder',
    method: 'put',
    data: data
  })
}

// 删除Tech_Non_Standard_Order
export function delTech_Non_Standard_Order(tnId) {
  return request({
    url: '/tech/data/nonStandardOrder/' + tnId,
    method: 'delete'
  })
}


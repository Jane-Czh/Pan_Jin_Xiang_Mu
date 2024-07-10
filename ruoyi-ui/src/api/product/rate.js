import request from '@/utils/request'

// 查询自制件合格率列表
export function listRate(query) {
  return request({
    url: '/product/rate/list',
    method: 'get',
    params: query
  })
}

// 查询自制件合格率详细
export function getRate(pqrId) {
  return request({
    url: '/product/rate/' + pqrId,
    method: 'get'
  })
}

// 新增自制件合格率
export function addRate(data) {
  return request({
    url: '/product/rate',
    method: 'post',
    data: data
  })
}

// 修改自制件合格率
export function updateRate(data) {
  return request({
    url: '/product/rate',
    method: 'put',
    data: data
  })
}

// 删除自制件合格率
export function delRate(pqrId) {
  return request({
    url: '/product/rate/' + pqrId,
    method: 'delete'
  })
}

export function uploadFile(formData) {
  return request({
    url: '/product/rate/Dimport',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    withCredentials: true,
    data: formData,
  });
}

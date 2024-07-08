import request from '@/utils/request'

// 查询加班申请列表
export function listOaform(query) {
  return request({
    url: '/product/Oaform/list',
    method: 'get',
    params: query
  })
}

// 查询加班申请详细
export function getOaform(poafId) {
  return request({
    url: '/product/Oaform/' + poafId,
    method: 'get'
  })
}

// 新增加班申请
export function addOaform(data) {
  return request({
    url: '/product/Oaform',
    method: 'post',
    data: data
  })
}

// 修改加班申请
export function updateOaform(data) {
  return request({
    url: '/product/Oaform',
    method: 'put',
    data: data
  })
}

// 删除加班申请
export function delOaform(poafId) {
  return request({
    url: '/product/Oaform/' + poafId,
    method: 'delete'
  })
}

export function uploadFile(formData) {
  return request({
    url: '/product/Oaform/Oaimport',
    method: 'post',
    headers: {
      'Content-Type': 'multipart/form-data',
    },
    withCredentials: true,
    data: formData,
  });
}
